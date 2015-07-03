package com.xiaomi.account.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.account.R;

public class ImageValuePreference extends Preference {
    private Drawable mImageValue;
    private Drawable mRightArrowDrawable;
    private boolean mShowRightArrow;

    public ImageValuePreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutResource(R.layout.preference_image_value);
    }

    public ImageValuePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutResource(R.layout.preference_image_value);
    }

    public ImageValuePreference(Context context) {
        this(context, null);
    }

    public void setImageValueDrawable(Drawable imageValue) {
        if (imageValue != null && this.mImageValue != imageValue) {
            this.mImageValue = imageValue;
            notifyChanged();
        }
    }

    public void setImageValueRes(int imageRes) {
        setImageValueDrawable(getContext().getResources().getDrawable(imageRes));
    }

    public Drawable getImageValue() {
        return this.mImageValue;
    }

    public boolean isShowRightArrow() {
        return this.mShowRightArrow;
    }

    public void setShowRightArrow(boolean isShowRightArrow) {
        this.mShowRightArrow = isShowRightArrow;
    }

    public void setRightArrowRes(int rightArrowRes) {
        setRightArrowDrawable(getContext().getResources().getDrawable(rightArrowRes));
    }

    public void setRightArrowDrawable(Drawable rightArrow) {
        if ((rightArrow == null && this.mRightArrowDrawable != null) || (rightArrow != null && this.mRightArrowDrawable != rightArrow)) {
            this.mRightArrowDrawable = rightArrow;
            setShowRightArrow(rightArrow != null);
            notifyChanged();
        }
    }

    public Drawable getRightArrowDrawable() {
        return this.mRightArrowDrawable;
    }

    protected void onBindView(View view) {
        super.onBindView(view);
        ImageView imageValueView = (ImageView) view.findViewById(R.id.image_right);
        if (imageValueView != null) {
            Drawable drawable = getImageValue();
            if (drawable != null) {
                imageValueView.setImageDrawable(drawable);
                imageValueView.setVisibility(0);
            } else {
                imageValueView.setVisibility(8);
            }
        }
        ImageView rightArrowView = (ImageView) view.findViewById(R.id.arrow_right);
        if (rightArrowView != null) {
            int i;
            if (this.mShowRightArrow) {
                i = 0;
            } else {
                i = 8;
            }
            rightArrowView.setVisibility(i);
            if (this.mShowRightArrow) {
                rightArrowView.setVisibility(0);
                if (this.mRightArrowDrawable != null) {
                    rightArrowView.setImageDrawable(this.mRightArrowDrawable);
                    return;
                } else {
                    this.mRightArrowDrawable = rightArrowView.getDrawable();
                    return;
                }
            }
            rightArrowView.setVisibility(8);
        }
    }
}
