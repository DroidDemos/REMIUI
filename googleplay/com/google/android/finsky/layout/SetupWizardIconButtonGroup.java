package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.vending.R;

public class SetupWizardIconButtonGroup extends LinearLayout {
    private Button mContinueButton;
    private ImageView mContinueButtonIcon;

    public SetupWizardIconButtonGroup(Context context) {
        this(context, null);
    }

    public SetupWizardIconButtonGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContinueButton = (Button) findViewById(R.id.continue_button_label);
        this.mContinueButtonIcon = (ImageView) findViewById(R.id.continue_button_icon);
    }

    public void setOnClickListener(OnClickListener l) {
        this.mContinueButton.setOnClickListener(l);
        this.mContinueButton.setClickable(l != null);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mContinueButtonIcon.setEnabled(enabled);
        this.mContinueButtonIcon.setAlpha(enabled ? 255 : 127);
        this.mContinueButton.setEnabled(enabled);
    }

    public void setText(String text) {
        this.mContinueButton.setText(text);
    }

    public void setIconDrawable(Drawable drawable) {
        this.mContinueButtonIcon.setImageDrawable(drawable);
    }
}
