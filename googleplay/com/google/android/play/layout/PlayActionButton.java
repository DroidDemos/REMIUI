package com.google.android.play.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.android.play.R;
import com.google.android.play.utils.PlayCorpusUtils;

public class PlayActionButton extends Button {
    private int mActionBottomPadding;
    private int mActionStyle;
    private int mActionTopPadding;
    private int mActionXPadding;
    private boolean mDrawAsLabel;
    private int mOriginalBackendId;
    private String mOriginalText;
    private int mPriority;
    private boolean mUseAllCapsInLabelMode;

    public PlayActionButton(Context context) {
        this(context, null);
    }

    public PlayActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlayActionButton);
        this.mDrawAsLabel = viewAttrs.getBoolean(R.styleable.PlayActionButton_draw_as_label, false);
        this.mUseAllCapsInLabelMode = viewAttrs.getBoolean(R.styleable.PlayActionButton_use_all_caps_in_label_mode, true);
        this.mActionStyle = viewAttrs.getInt(R.styleable.PlayActionButton_action_style, 0);
        this.mActionXPadding = viewAttrs.getDimensionPixelSize(R.styleable.PlayActionButton_action_xpadding, 0);
        this.mActionTopPadding = viewAttrs.getDimensionPixelSize(R.styleable.PlayActionButton_action_top_padding, 0);
        this.mActionBottomPadding = viewAttrs.getDimensionPixelSize(R.styleable.PlayActionButton_action_bottom_padding, 0);
        this.mPriority = viewAttrs.getInt(R.styleable.PlayActionButton_local_priority, -1);
        viewAttrs.recycle();
    }

    public void setDrawAsLabel(boolean drawAsLabel) {
        if (this.mDrawAsLabel != drawAsLabel) {
            this.mDrawAsLabel = drawAsLabel;
            syncAppearance();
        }
    }

    public void setActionStyle(int actionStyle) {
        if (this.mActionStyle != actionStyle) {
            this.mActionStyle = actionStyle;
            syncAppearance();
        }
    }

    public void setOnClickListener(OnClickListener l) {
        String error = "Don't call PlayActionButton.setOnClickListener() directly";
        Log.e("PlayCommon", error);
        Log.wtf("PlayCommon", error);
        throw new UnsupportedOperationException("Call PlayActionButton.configure()");
    }

    public void resetClickListener() {
        super.setOnClickListener(null);
        setClickable(false);
        setFocusable(false);
    }

    public void configure(int backendId, int textResourceId, OnClickListener onClickListener) {
        configure(backendId, getResources().getString(textResourceId), onClickListener);
    }

    public void configure(int backendId, String text, OnClickListener onClickListener) {
        boolean isClickable;
        this.mOriginalText = text;
        this.mOriginalBackendId = backendId;
        if (onClickListener != null) {
            isClickable = true;
        } else {
            isClickable = false;
        }
        if (isClickable) {
            setFocusable(true);
            super.setOnClickListener(onClickListener);
            setClickable(true);
        } else {
            setFocusable(false);
            super.setOnClickListener(null);
            setClickable(false);
        }
        syncAppearance();
    }

    private void syncAppearance() {
        boolean skipActionPaddings;
        CharSequence toUpperCase = (this.mOriginalText == null || (this.mDrawAsLabel && !this.mUseAllCapsInLabelMode)) ? this.mOriginalText : this.mOriginalText.toUpperCase();
        setText(toUpperCase);
        Context context = getContext();
        if (!this.mDrawAsLabel) {
            switch (this.mActionStyle) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    setBackgroundResource(PlayCorpusUtils.getPlayActionButtonBackgroundDrawable(context, this.mOriginalBackendId));
                    setTextColor(getResources().getColorStateList(R.color.play_action_button_text));
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    setBackgroundResource(R.drawable.play_action_button_secondary);
                    setTextColor(PlayCorpusUtils.getPrimaryTextColor(context, this.mOriginalBackendId));
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    setBackgroundResource(PlayCorpusUtils.getPlayActionButtonBackgroundSecondaryDrawable(context, this.mOriginalBackendId));
                    setTextColor(PlayCorpusUtils.getPrimaryTextColor(context, this.mOriginalBackendId));
                    break;
                default:
                    break;
            }
        }
        int i;
        if (isClickable()) {
            i = R.drawable.play_highlight_overlay_light;
        } else {
            i = 0;
        }
        setBackgroundResource(i);
        setTextColor(PlayCorpusUtils.getPrimaryTextColor(context, this.mActionStyle == 2 ? 0 : this.mOriginalBackendId));
        if (!this.mDrawAsLabel || isClickable()) {
            skipActionPaddings = false;
        } else {
            skipActionPaddings = true;
        }
        if (skipActionPaddings) {
            setPadding(0, 0, 0, 0);
        } else {
            setPadding(this.mActionXPadding, this.mActionTopPadding, this.mActionXPadding, this.mActionBottomPadding);
        }
    }

    public int getPriority() {
        return this.mPriority;
    }
}
