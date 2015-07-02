package com.google.android.play.search;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.google.android.play.R;

public class PlaySearchPlateTextContainer extends FrameLayout implements TextWatcher, OnFocusChangeListener, OnEditorActionListener, PlaySearchListener {
    private PlaySearchController mController;
    private final InputMethodManager mInputManager;
    private ImageView mSearchBoxIdleText;
    private EditText mSearchBoxTextInput;
    private boolean mSuspendTextChangeCallback;
    private PlaySearchVoiceController mVoiceController;

    public PlaySearchPlateTextContainer(Context context) {
        this(context, null);
    }

    public PlaySearchPlateTextContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaySearchPlateTextContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mInputManager = (InputMethodManager) context.getSystemService("input_method");
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mSearchBoxIdleText = (ImageView) findViewById(R.id.search_box_idle_text);
        this.mSearchBoxTextInput = (EditText) findViewById(R.id.search_box_text_input);
        switchToMode(1);
        this.mSearchBoxIdleText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlaySearchPlateTextContainer.this.mController != null) {
                    PlaySearchPlateTextContainer.this.mController.setMode(2);
                }
            }
        });
    }

    public void setPlaySearchController(PlaySearchController playSearchController) {
        if (this.mController != null) {
            this.mController.removePlaySearchListener(this);
        }
        this.mController = playSearchController;
        this.mController.addPlaySearchListener(this);
        this.mVoiceController = new PlaySearchVoiceController(this.mController);
    }

    public void onModeChanged(int searchMode) {
        switchToMode(searchMode);
    }

    public void onQueryChanged(String query, boolean canUpdateSuggestions) {
        setText(query);
    }

    public void onSuggestionClicked(PlaySearchSuggestionModel model) {
    }

    public void onSearch(String query) {
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == 3) {
            triggerSearch();
            return true;
        }
        if (event != null) {
            boolean relevantKeyEvent;
            int keyCode = event.getKeyCode();
            if (keyCode == 66 || keyCode == 160 || keyCode == 84) {
                relevantKeyEvent = true;
            } else {
                relevantKeyEvent = false;
            }
            if (relevantKeyEvent) {
                if (event.getAction() != 1) {
                    return true;
                }
                triggerSearch();
                return true;
            }
        }
        return false;
    }

    public void onTextChanged(CharSequence text, int start, int before, int count) {
        if (this.mController != null && !this.mSuspendTextChangeCallback) {
            this.mController.setQuery(text.toString());
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
    }

    public void afterTextChanged(Editable editable) {
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && this.mController != null && this.mController.getMode() == 3) {
            this.mController.setMode(1);
            if (this.mVoiceController != null) {
                this.mVoiceController.cancelPendingVoiceSearch(getContext());
            }
            if (!TextUtils.isEmpty(this.mController.getQuery())) {
                this.mController.onSearch();
            }
        }
        if (!hasWindowFocus) {
            unfocusInputBoxAndHideKeyboard();
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (this.mController != null && !hasFocus) {
            this.mController.setMode(1);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mVoiceController != null) {
            this.mVoiceController.cancelPendingVoiceSearch(getContext());
        }
    }

    private void triggerSearch() {
        if (TextUtils.getTrimmedLength(this.mController.getQuery()) > 0) {
            this.mController.onSearch();
        }
    }

    private void requestVoice() {
        if (this.mController != null && this.mVoiceController != null) {
            this.mVoiceController.requestVoiceSearch(getContext());
        }
    }

    private void focusInputBoxAndShowKeyboard() {
        this.mSearchBoxTextInput.requestFocus();
        this.mInputManager.showSoftInput(this.mSearchBoxTextInput, 2);
    }

    private void unfocusInputBoxAndHideKeyboard() {
        this.mInputManager.hideSoftInputFromWindow(this.mSearchBoxTextInput.getWindowToken(), 0);
    }

    private void setText(String text) {
        if (!this.mSearchBoxTextInput.getText().toString().equals(text)) {
            this.mSuspendTextChangeCallback = true;
            this.mSearchBoxTextInput.setText(text);
            this.mSearchBoxTextInput.setSelection(text.length());
            this.mSuspendTextChangeCallback = false;
        }
    }

    private void switchToMode(int searchMode) {
        if (searchMode == 2) {
            this.mSearchBoxIdleText.setVisibility(8);
            this.mSearchBoxTextInput.setVisibility(0);
            this.mSearchBoxTextInput.setOnFocusChangeListener(this);
            this.mSearchBoxTextInput.addTextChangedListener(this);
            this.mSearchBoxTextInput.setOnEditorActionListener(this);
            focusInputBoxAndShowKeyboard();
            if (this.mController != null) {
                this.mController.notifyQueryChange();
                return;
            }
            return;
        }
        if (searchMode == 1) {
            this.mSearchBoxIdleText.setVisibility(0);
            this.mSearchBoxTextInput.setVisibility(8);
            this.mSearchBoxTextInput.setText("");
            this.mSearchBoxTextInput.setOnFocusChangeListener(null);
            this.mSearchBoxTextInput.setOnEditorActionListener(null);
        } else if (searchMode == 3) {
            if (this.mController != null) {
                this.mController.setQuery("");
            }
            requestVoice();
        }
        unfocusInputBoxAndHideKeyboard();
        this.mSearchBoxTextInput.removeTextChangedListener(this);
    }
}
