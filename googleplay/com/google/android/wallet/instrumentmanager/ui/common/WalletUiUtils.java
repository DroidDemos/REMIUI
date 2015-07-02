package com.google.android.wallet.instrumentmanager.ui.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.common.util.AndroidUtils;
import com.google.android.wallet.instrumentmanager.ui.common.validator.AbstractValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.AndValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.PatternValidator;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public final class WalletUiUtils {
    private static final AtomicInteger sNextGeneratedId;

    static {
        sNextGeneratedId = new AtomicInteger(1);
    }

    public static void setViewBackgroundOrHide(View view, TypedValue typedValue) {
        if (typedValue == null || typedValue.type == 0) {
            view.setVisibility(8);
        } else if (typedValue.type < 28 || typedValue.type > 31) {
            view.setBackgroundResource(typedValue.resourceId);
        } else {
            view.setBackgroundColor(typedValue.data);
        }
    }

    public static void animateViewAppearing(View view, int startDeltaY, int endDeltaY) {
        animateViewAppearing(view, startDeltaY, endDeltaY, -1);
    }

    public static void animateViewAppearing(View view, int startDeltaY, int endDeltaY, long animationDurationMs) {
        if (VERSION.SDK_INT < 14) {
            view.setVisibility(0);
            view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.wallet_im_fade_in));
        } else if (startDeltaY < endDeltaY) {
            view.setVisibility(0);
            view.setTranslationY((float) endDeltaY);
            view.setAlpha(0.0f);
            final long previousDuration = view.animate().getDuration();
            final View view2 = view;
            final int i = startDeltaY;
            final int i2 = endDeltaY;
            final long j = animationDurationMs;
            view.animate().setDuration(1).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    view2.animate().setListener(null).setDuration(previousDuration);
                    WalletUiUtils.animateViewAppearingInternal(view2, i, i2, j);
                }
            }).start();
        } else {
            animateViewAppearingInternal(view, startDeltaY, endDeltaY, animationDurationMs);
        }
    }

    private static void animateViewAppearingInternal(final View view, int startDeltaY, int endDeltaY, long animationDurationMs) {
        view.animate().cancel();
        view.setVisibility(0);
        view.setTranslationY((float) startDeltaY);
        view.setAlpha(0.0f);
        ViewPropertyAnimator animation = view.animate().alpha(1.0f).translationY((float) endDeltaY);
        if (animationDurationMs >= 0) {
            final long previousDuration = view.animate().getDuration();
            animation.setDuration(animationDurationMs).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    view.animate().setListener(null).setDuration(previousDuration);
                }
            });
        }
        animation.start();
    }

    public static void animateViewDisappearingToGone(View view, int startDeltaY, int endDeltaY) {
        animateViewDisappearingInternal(view, startDeltaY, endDeltaY, 8);
    }

    public static void animateViewDisappearingToInvisible(View view, int startDeltaY, int endDeltaY) {
        animateViewDisappearingInternal(view, startDeltaY, endDeltaY, 4);
    }

    private static void animateViewDisappearingInternal(final View view, int startDeltaY, int endDeltaY, final int finalViewVisibility) {
        if (VERSION.SDK_INT >= 14) {
            view.setTranslationY((float) startDeltaY);
            view.animate().translationY((float) endDeltaY).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(finalViewVisibility);
                    view.setAlpha(1.0f);
                    view.animate().setListener(null);
                }
            }).start();
            return;
        }
        view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.wallet_im_fade_out));
        view.setVisibility(finalViewVisibility);
    }

    public static void playShakeAnimationIfPossible(Context context, View view, final int shakeCount) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, 1.0f});
            final float shakeDelta = context.getResources().getDimension(R.dimen.wallet_im_shake_animation_delta);
            animator.setInterpolator(new TimeInterpolator() {
                public float getInterpolation(float input) {
                    return (((float) Math.sin(((((double) input) * 2.0d) * 3.141592653589793d) * ((double) shakeCount))) * (1.0f - input)) * shakeDelta;
                }
            });
            animator.start();
        }
    }

    public static int getViewHeightWithMargins(View view) {
        int verticalMarginHeight = 0;
        LayoutParams params = view.getLayoutParams();
        if (params instanceof MarginLayoutParams) {
            MarginLayoutParams marginParams = (MarginLayoutParams) params;
            verticalMarginHeight = marginParams.topMargin + marginParams.bottomMargin;
        }
        return view.getHeight() + verticalMarginHeight;
    }

    private static boolean showKeyboard(final EditText focusView, boolean hasNextView) {
        if (!focusView.requestFocus()) {
            return false;
        }
        if (hasNextView && (focusView.getImeOptions() & 255) == 0) {
            focusView.setImeOptions(5);
        }
        final InputMethodManager inputMethodManager = (InputMethodManager) focusView.getContext().getSystemService("input_method");
        if (inputMethodManager.showSoftInput(focusView, 1)) {
            return true;
        }
        focusView.postDelayed(new Runnable() {
            public void run() {
                inputMethodManager.showSoftInput(focusView, 1);
            }
        }, 300);
        return true;
    }

    public static void requestFocusAndAnnounceError(View view) {
        if (view.hasFocus()) {
            if (view instanceof FormEditText) {
                ((FormEditText) view).announceError();
            } else if (view instanceof FormSpinner) {
                ((FormSpinner) view).announceError();
            }
        } else if (!view.requestFocus() && (view instanceof FormSpinner)) {
            ((FormSpinner) view).announceError();
        }
    }

    public static boolean showSoftKeyboardOnFirstEditText(View rootView) {
        ArrayList<View> focusables = rootView.getFocusables(130);
        EditText focusView = null;
        boolean hasNextView = false;
        int length = focusables.size();
        for (int i = 0; i < length; i++) {
            View view = (View) focusables.get(i);
            if (view instanceof EditText) {
                if (focusView != null) {
                    hasNextView = true;
                    break;
                }
                focusView = (EditText) view;
            }
        }
        return focusView != null && showKeyboard(focusView, hasNextView);
    }

    public static boolean hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        if (imm == null) {
            return false;
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return true;
    }

    public static void announceForAccessibility(View view, CharSequence text) {
        if (!AndroidUtils.isAccessibilityEnabled(view.getContext())) {
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            view.announceForAccessibility(text);
            return;
        }
        AccessibilityEvent event = AccessibilityEvent.obtain(8);
        event.setEnabled(true);
        event.getText().add(text);
        ((AccessibilityManager) view.getContext().getSystemService("accessibility")).sendAccessibilityEvent(event);
    }

    public static FormEditText createFormEditTextForTextUiField(UiField uiField, int viewId, LayoutInflater themedInflater) {
        if (uiField.textField == null) {
            throw new IllegalArgumentException("UiField.textField is not defined");
        }
        FormEditText editText = (FormEditText) themedInflater.inflate(R.layout.view_form_edit_text, null, false);
        editText.setId(viewId);
        applyUiFieldSpecificationToFormEditText(uiField, editText);
        return editText;
    }

    public static void applyUiFieldSpecificationToFormEditText(UiField uiField, FormEditText editText) {
        int inputType;
        editText.setHint(uiField.label);
        editText.setText(uiField.textField.initialValue);
        editText.setRequired(!uiField.isOptional);
        if (uiField.textField.maxLength > 0) {
            editText.setFilters(new InputFilter[]{new LengthFilter(uiField.textField.maxLength)});
            editText.enableAutoAdvance(new InputLengthCompletable(editText, uiField.textField.maxLength), editText, false);
        }
        switch (uiField.textField.keyboardLayout) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                inputType = 1;
                if (uiField.textField.isMasked) {
                    inputType = 1 | 128;
                    break;
                }
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                inputType = 2;
                if (uiField.textField.isMasked) {
                    throw new IllegalArgumentException("Number passwords are not supported");
                }
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                inputType = 33;
                break;
            default:
                throw new IllegalArgumentException("TextField.keyboardLayout " + uiField.textField.keyboardLayout + " is not supported");
        }
        Typeface originalTypeface = editText.getTypeface();
        editText.setInputType(inputType);
        editText.setTypeface(originalTypeface);
        if (uiField.textField.validation.length > 0) {
            AndValidator compoundRegexValidator = new AndValidator(new AbstractValidator[0]);
            for (Validation validation : uiField.textField.validation) {
                compoundRegexValidator.add(new PatternValidator(validation.errorMessage, Pattern.compile(validation.regex)));
            }
            editText.addValidator(compoundRegexValidator);
        }
    }

    public static int generateViewId() {
        if (VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        int result;
        int newValue;
        do {
            result = sNextGeneratedId.get();
            newValue = result + 1;
            if (newValue > 16777215) {
                newValue = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(result, newValue));
        return result;
    }
}
