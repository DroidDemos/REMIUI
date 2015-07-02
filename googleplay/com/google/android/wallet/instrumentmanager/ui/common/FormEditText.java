package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.AutoCompleteTextView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.common.util.AndroidUtils;
import com.google.android.wallet.instrumentmanager.ui.common.listeners.EditTextAutoAdvanceListener;
import com.google.android.wallet.instrumentmanager.ui.common.validator.AbstractValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.AndValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.ComposedValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.PatternValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.RequiredValidator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class FormEditText extends AutoCompleteTextView implements ValidatableComponent {
    private static final Pattern PATTERN_DIGITS_ONLY;
    private Completable mAutoAdvanceCompletable;
    private Validatable mAutoAdvanceValidatable;
    private EditTextAutoAdvanceListener mAutoAdvancedListener;
    private boolean mBlockCompletion;
    private int mCachedThreshold;
    private final ComposedValidator mFocusChangeAndValidateableValidator;
    private int mMaxFieldLength;
    private final TextWatcher mOnTextChangeValidationTextWatcher;
    private Validatable mOutOfFocusValidatable;
    private boolean mRequired;
    private String mRequiredError;
    private RequiredValidator mRequiredValidator;
    private CharSequence mSavedError;
    private final ComposedValidator mTextChangedValidator;
    LinkedList<TextWatcher> mTextWatcherList;
    private final TextWatcher mTextWatcherWrapper;
    private boolean mValidateWhenNotVisible;

    private class FormEditTextInputConnection extends InputConnectionWrapper {
        public FormEditTextInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        public boolean commitText(CharSequence text, int newCursorPosition) {
            FormEditText.this.mSavedError = FormEditText.this.getError();
            boolean ret = super.commitText(text, newCursorPosition);
            if (FormEditText.this.mSavedError != null) {
                FormEditText.this.setError(FormEditText.this.mSavedError);
            }
            return ret;
        }
    }

    static {
        PATTERN_DIGITS_ONLY = Pattern.compile("\\d*");
    }

    public FormEditText(Context context) {
        super(context);
        this.mRequired = true;
        this.mValidateWhenNotVisible = false;
        this.mRequiredError = null;
        this.mMaxFieldLength = Integer.MAX_VALUE;
        this.mOnTextChangeValidationTextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!FormEditText.this.mTextChangedValidator.isValid(FormEditText.this) && FormEditText.this.mTextChangedValidator.getErrorMessage() != null) {
                    FormEditText.this.setError(FormEditText.this.mTextChangedValidator.getErrorMessage());
                } else if (FormEditText.this.isFocused() && s != null && s.length() > 0 && FormEditText.this.getError() != null) {
                    FormEditText.this.setError(null);
                    FormEditText.this.mSavedError = null;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
        this.mTextWatcherWrapper = new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Iterator i$ = FormEditText.this.getTextWatcherList().iterator();
                while (i$.hasNext()) {
                    ((TextWatcher) i$.next()).afterTextChanged(s);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Iterator i$ = FormEditText.this.getTextWatcherList().iterator();
                while (i$.hasNext()) {
                    ((TextWatcher) i$.next()).beforeTextChanged(s, start, count, after);
                }
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Iterator i$ = FormEditText.this.getTextWatcherList().iterator();
                while (i$.hasNext()) {
                    ((TextWatcher) i$.next()).onTextChanged(s, start, before, count);
                }
                if (FormEditText.this.getError() != null) {
                    FormEditText.this.announceError();
                }
            }
        };
        this.mFocusChangeAndValidateableValidator = new AndValidator(new AbstractValidator[0]);
        this.mTextChangedValidator = new AndValidator(new AbstractValidator[0]);
        this.mOutOfFocusValidatable = this;
        readAttributesAndAddListeners(context, null);
    }

    public FormEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRequired = true;
        this.mValidateWhenNotVisible = false;
        this.mRequiredError = null;
        this.mMaxFieldLength = Integer.MAX_VALUE;
        this.mOnTextChangeValidationTextWatcher = /* anonymous class already generated */;
        this.mTextWatcherWrapper = /* anonymous class already generated */;
        this.mFocusChangeAndValidateableValidator = new AndValidator(new AbstractValidator[0]);
        this.mTextChangedValidator = new AndValidator(new AbstractValidator[0]);
        this.mOutOfFocusValidatable = this;
        readAttributesAndAddListeners(context, attrs);
    }

    public FormEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mRequired = true;
        this.mValidateWhenNotVisible = false;
        this.mRequiredError = null;
        this.mMaxFieldLength = Integer.MAX_VALUE;
        this.mOnTextChangeValidationTextWatcher = /* anonymous class already generated */;
        this.mTextWatcherWrapper = /* anonymous class already generated */;
        this.mFocusChangeAndValidateableValidator = new AndValidator(new AbstractValidator[0]);
        this.mTextChangedValidator = new AndValidator(new AbstractValidator[0]);
        this.mOutOfFocusValidatable = this;
        readAttributesAndAddListeners(context, attrs);
    }

    public void enableAutoAdvance(Completable completable, Validatable validatable, boolean autoRetreat) {
        if (this.mAutoAdvancedListener == null || this.mAutoAdvanceCompletable != completable || this.mAutoAdvanceValidatable != validatable) {
            EditTextAutoAdvanceListener newAutoAdvanceListener = new EditTextAutoAdvanceListener(this, completable, validatable, autoRetreat);
            if (this.mAutoAdvancedListener == null) {
                addTextChangedListener(newAutoAdvanceListener);
            }
            this.mAutoAdvancedListener = newAutoAdvanceListener;
            this.mAutoAdvanceCompletable = completable;
            this.mAutoAdvanceValidatable = validatable;
        }
    }

    public void setAutoAdvancedListener(AutoAdvancedListener listener) {
        if (this.mAutoAdvancedListener != null) {
            this.mAutoAdvancedListener.setAutoAdvancedListener(listener);
        }
    }

    public AutoAdvancedListener getAutoAdvancerListener() {
        return this.mAutoAdvancedListener.getAutoAdvancedListener();
    }

    public int getMaxFieldLength() {
        return this.mMaxFieldLength;
    }

    private void readAttributesAndAddListeners(Context context, AttributeSet attrs) {
        AbstractValidator xmlValidator;
        TypedArray maxLengthTypedArray = context.obtainStyledAttributes(attrs, new int[]{16843104});
        this.mMaxFieldLength = maxLengthTypedArray.getInt(0, Integer.MAX_VALUE);
        maxLengthTypedArray.recycle();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WalletImFormEditText);
        this.mRequired = typedArray.getBoolean(R.styleable.WalletImFormEditText_required, true);
        this.mValidateWhenNotVisible = typedArray.getBoolean(R.styleable.WalletImFormEditText_validateWhenNotVisible, false);
        CharSequence validatorErrorString = typedArray.getString(R.styleable.WalletImFormEditText_validatorErrorString);
        switch (typedArray.getInt(R.styleable.WalletImFormEditText_validatorType, 0)) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (TextUtils.isEmpty(validatorErrorString)) {
                    validatorErrorString = context.getString(R.string.wallet_im_error_only_numeric_digits_allowed);
                }
                xmlValidator = new PatternValidator(validatorErrorString, PATTERN_DIGITS_ONLY);
                setInputType(2);
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (TextUtils.isEmpty(validatorErrorString)) {
                    validatorErrorString = context.getString(R.string.wallet_im_error_email_address_invalid);
                }
                xmlValidator = new PatternValidator(validatorErrorString, Patterns.EMAIL_ADDRESS);
                setInputType(33);
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                xmlValidator = new PatternValidator(validatorErrorString, Pattern.compile(typedArray.getString(R.styleable.WalletImFormEditText_validatorRegexp)));
                break;
            default:
                xmlValidator = null;
                break;
        }
        if (this.mRequired) {
            this.mRequiredError = typedArray.getString(R.styleable.WalletImFormEditText_requiredErrorString);
            if (TextUtils.isEmpty(this.mRequiredError)) {
                this.mRequiredError = context.getString(R.string.wallet_im_error_field_must_not_be_empty);
            }
            onRequiredChanged(false);
        }
        if (xmlValidator != null) {
            addValidator(xmlValidator);
        }
        typedArray.recycle();
        setImeOptions((getImeOptions() | 33554432) | 268435456);
        super.addTextChangedListener(this.mTextWatcherWrapper);
        addTextChangedListenerToFront(this.mOnTextChangeValidationTextWatcher);
        setThreshold(Integer.MAX_VALUE);
    }

    public void replaceTextAndPreventFilter(CharSequence text) {
        this.mBlockCompletion = true;
        if (isFocused()) {
            replaceText(text);
        } else {
            setText(text);
        }
        this.mBlockCompletion = false;
    }

    public boolean enoughToFilter() {
        if (this.mBlockCompletion) {
            return false;
        }
        return super.enoughToFilter();
    }

    private LinkedList<TextWatcher> getTextWatcherList() {
        if (this.mTextWatcherList == null) {
            this.mTextWatcherList = new LinkedList();
        }
        return this.mTextWatcherList;
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        getTextWatcherList().addLast(textWatcher);
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        getTextWatcherList().remove(textWatcher);
    }

    public void addTextChangedListenerToFront(TextWatcher textWatcher) {
        getTextWatcherList().addFirst(textWatcher);
    }

    public void addValidator(AbstractValidator validator) {
        this.mFocusChangeAndValidateableValidator.add(validator);
    }

    public void addOnTextChangeValidator(AbstractValidator validator) {
        addValidator(validator);
        this.mTextChangedValidator.add(validator);
    }

    public void removeValidator(AbstractValidator validator) {
        this.mFocusChangeAndValidateableValidator.remove(validator);
        this.mTextChangedValidator.remove(validator);
    }

    public boolean isValid() {
        return !(this.mValidateWhenNotVisible || getVisibility() == 0) || this.mFocusChangeAndValidateableValidator.isValid(this);
    }

    public boolean validate() {
        boolean valid = isValid();
        if (!valid && this.mFocusChangeAndValidateableValidator.getErrorMessage() != null) {
            setError(this.mFocusChangeAndValidateableValidator.getErrorMessage());
        } else if (getError() != null) {
            setError(null);
        }
        return valid;
    }

    String getErrorTextForAccessibility() {
        return getResources().getString(R.string.wallet_im_accessibility_event_form_field_error, new Object[]{getHint(), getError()});
    }

    void announceError() {
        if (VERSION.SDK_INT >= 16 && AndroidUtils.isAccessibilityEnabled(getContext())) {
            announceForAccessibility(getErrorTextForAccessibility());
        }
    }

    public void setRequired(boolean required) {
        this.mRequired = required;
        onRequiredChanged(false);
    }

    public String getRequiredError() {
        return this.mRequiredError;
    }

    public void setRequiredError(String requiredError) {
        this.mRequiredError = requiredError;
        onRequiredChanged(true);
    }

    public void setOnOutOfFocusValidatable(Validatable validatable) {
        this.mOutOfFocusValidatable = validatable;
    }

    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (!(focused || this.mOutOfFocusValidatable == null)) {
            this.mOutOfFocusValidatable.validate();
        }
        if (focused && getError() != null) {
            announceError();
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setThreshold(this.mCachedThreshold);
    }

    public void setThreshold(int threshold) {
        this.mCachedThreshold = threshold;
        if (shouldShowAutocomplete()) {
            super.setThreshold(threshold);
            return;
        }
        super.setThreshold(Integer.MAX_VALUE);
        dismissDropDown();
    }

    public int getCachedThreshold() {
        return this.mCachedThreshold;
    }

    private boolean shouldShowAutocomplete() {
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        int density = (int) getResources().getDisplayMetrics().density;
        if (density != 0 && rect.height() / density > 140) {
            return true;
        }
        return false;
    }

    private void onRequiredChanged(boolean replaceExistingValidator) {
        if (replaceExistingValidator && this.mRequiredValidator != null) {
            removeValidator(this.mRequiredValidator);
            this.mRequiredValidator = null;
        }
        if (this.mRequired) {
            if (this.mRequiredValidator == null) {
                this.mRequiredValidator = new RequiredValidator(this.mRequiredError);
            }
            addValidator(this.mRequiredValidator);
        } else if (this.mRequiredValidator != null) {
            removeValidator(this.mRequiredValidator);
        }
    }

    public void beginBatchEdit() {
        if (getError() != null) {
            setError(getError());
        }
        super.beginBatchEdit();
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        InputConnection target = super.onCreateInputConnection(outAttrs);
        if (target != null) {
            return new FormEditTextInputConnection(target, true);
        }
        return null;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.dispatchPopulateAccessibilityEvent(event);
        if (VERSION.SDK_INT < 16 && event.getEventType() == 8 && getError() != null) {
            event.setContentDescription(getErrorTextForAccessibility());
        }
        return true;
    }
}
