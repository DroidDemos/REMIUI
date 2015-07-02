package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.android.wallet.instrumentmanager.R;

public class ButtonBar extends RelativeLayout {
    private boolean mCapitalizeButtonText;
    public Button mExpandButton;
    ImageView mLogoImage;
    private boolean mLogoImageDefined;
    public Button mPositiveButton;

    public ButtonBar(Context context) {
        super(context);
    }

    public ButtonBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributes(context, attrs);
    }

    public ButtonBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        readAttributes(context, attrs);
    }

    private void readAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WalletImButtonBar);
        this.mCapitalizeButtonText = typedArray.getBoolean(R.styleable.WalletImButtonBar_capitalizeButtonText, false);
        typedArray.recycle();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mLogoImage = (ImageView) findViewById(R.id.logo);
        TypedArray typedArray = getContext().obtainStyledAttributes(new int[]{R.attr.imButtonBarIntegratorLogoDrawable});
        TypedValue typedValue = typedArray.peekValue(0);
        typedArray.recycle();
        WalletUiUtils.setViewBackgroundOrHide(this.mLogoImage, typedValue);
        this.mLogoImageDefined = this.mLogoImage.getVisibility() == 0;
        this.mExpandButton = (Button) findViewById(R.id.expand_btn);
        this.mPositiveButton = (Button) findViewById(R.id.positive_btn);
        if (this.mCapitalizeButtonText) {
            this.mPositiveButton.setText(this.mPositiveButton.getText().toString().toUpperCase(getResources().getConfiguration().locale));
        }
    }

    protected Parcelable onSaveInstanceState() {
        Bundle outState = new Bundle();
        outState.putParcelable("superSavedInstanceState", super.onSaveInstanceState());
        outState.putBoolean("positiveButtonEnabled", this.mPositiveButton.isEnabled());
        outState.putBoolean("expandButtonEnabled", this.mExpandButton.isEnabled());
        return outState;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle savedInstanceState = (Bundle) state;
            super.onRestoreInstanceState(savedInstanceState.getParcelable("superSavedInstanceState"));
            this.mPositiveButton.setEnabled(savedInstanceState.getBoolean("positiveButtonEnabled"));
            this.mExpandButton.setEnabled(savedInstanceState.getBoolean("expandButtonEnabled"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void setPositiveButtonEnabled(boolean enabled) {
        this.mPositiveButton.setEnabled(enabled);
    }

    public void setExpandButtonEnabled(boolean enabled) {
        this.mExpandButton.setEnabled(enabled);
    }

    public void setPositiveButtonText(String buttonText) {
        if (this.mCapitalizeButtonText) {
            buttonText = buttonText.toUpperCase(getResources().getConfiguration().locale);
        }
        this.mPositiveButton.setText(buttonText);
    }

    public void setExpandButtonText(String buttonText) {
        this.mExpandButton.setText(buttonText);
    }

    public void setPositiveButtonOnClickListener(OnClickListener listener) {
        this.mPositiveButton.setOnClickListener(listener);
    }

    public void setExpandButtonOnClickListener(OnClickListener listener) {
        this.mExpandButton.setOnClickListener(listener);
    }

    public void showExpandButton(boolean show) {
        if (show) {
            this.mExpandButton.setVisibility(0);
            if (this.mLogoImageDefined) {
                this.mLogoImage.setVisibility(8);
                return;
            }
            return;
        }
        this.mExpandButton.setVisibility(8);
        if (this.mLogoImageDefined) {
            this.mLogoImage.setVisibility(0);
        }
    }
}
