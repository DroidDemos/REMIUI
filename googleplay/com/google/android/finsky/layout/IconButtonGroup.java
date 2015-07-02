package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.layout.ForegroundRelativeLayout;

public class IconButtonGroup extends ForegroundRelativeLayout {
    private boolean mAllowIcon;
    private ImageView mContinueButtonIcon;
    private TextView mContinueButtonLabel;
    private int mLabelBackendId;

    public IconButtonGroup(Context context) {
        this(context, null);
    }

    public IconButtonGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mAllowIcon = true;
        this.mLabelBackendId = 0;
        setWillNotDraw(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContinueButtonLabel = (TextView) findViewById(R.id.continue_button_label);
        this.mContinueButtonIcon = (ImageView) findViewById(R.id.continue_button_icon);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        boolean result = super.dispatchPopulateAccessibilityEvent(event);
        event.getText().clear();
        return result;
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mContinueButtonIcon.setEnabled(enabled);
        this.mContinueButtonIcon.setAlpha(enabled ? 255 : 127);
        this.mContinueButtonLabel.setEnabled(enabled);
    }

    public void setText(String text) {
        this.mContinueButtonLabel.setText(text.toUpperCase());
    }

    public void setBackendForLabel(int backend) {
        this.mLabelBackendId = backend;
        updateLabelBackground();
    }

    public void setIconDrawable(Drawable drawable) {
        this.mContinueButtonIcon.setImageDrawable(drawable);
        updateIconVisibility();
    }

    public void setAllowIcon(boolean allowIcon) {
        this.mAllowIcon = allowIcon;
        updateIconVisibility();
    }

    private void updateIconVisibility() {
        boolean visible;
        int i = 0;
        if (!this.mAllowIcon || this.mContinueButtonIcon.getDrawable() == null) {
            visible = false;
        } else {
            visible = true;
        }
        ImageView imageView = this.mContinueButtonIcon;
        if (!visible) {
            i = 8;
        }
        imageView.setVisibility(i);
        updateLabelBackground();
    }

    private void updateLabelBackground() {
        Context ctx = getContext();
        this.mContinueButtonLabel.setBackgroundResource(this.mContinueButtonIcon.getVisibility() == 8 ? CorpusResourceUtils.getPlayActionButtonBaseBackgroundDrawable(ctx, this.mLabelBackendId) : CorpusResourceUtils.getPlayActionButtonStartBackgroundDrawable(ctx, this.mLabelBackendId));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LayoutParams iconLp = this.mContinueButtonIcon.getLayoutParams();
        LayoutParams labelLp = this.mContinueButtonLabel.getLayoutParams();
        int width = 0;
        int height = 0;
        if (this.mContinueButtonIcon.getVisibility() != 8) {
            this.mContinueButtonIcon.measure(MeasureSpec.makeMeasureSpec(iconLp.width, 1073741824), MeasureSpec.makeMeasureSpec(iconLp.height, 1073741824));
            width = this.mContinueButtonIcon.getMeasuredWidth();
            height = this.mContinueButtonIcon.getMeasuredHeight();
        }
        this.mContinueButtonLabel.measure(0, MeasureSpec.makeMeasureSpec(labelLp.height, 1073741824));
        setMeasuredDimension(width + this.mContinueButtonLabel.getMeasuredWidth(), Math.max(height, this.mContinueButtonLabel.getMeasuredHeight()));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = 0;
        if (this.mContinueButtonIcon.getVisibility() != 8) {
            this.mContinueButtonIcon.layout(0, 0, this.mContinueButtonIcon.getMeasuredWidth(), this.mContinueButtonIcon.getMeasuredHeight());
            x = this.mContinueButtonIcon.getMeasuredWidth();
        }
        this.mContinueButtonLabel.layout(x, 0, this.mContinueButtonLabel.getMeasuredWidth() + x, this.mContinueButtonLabel.getMeasuredHeight());
    }
}
