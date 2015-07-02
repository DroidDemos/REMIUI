package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.android.vending.R;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import java.util.List;

public class DetailsTextIconContainer extends ViewGroup {
    private final int mChildGap;

    public DetailsTextIconContainer(Context context) {
        this(context, null);
    }

    public DetailsTextIconContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mChildGap = context.getResources().getDimensionPixelSize(R.dimen.details_text_section_icon_gap);
    }

    public void populate(List<Pair<Image, String>> imagePairs, BitmapLoader bitmapLoader) {
        populate(imagePairs, bitmapLoader, 0);
    }

    public void populate(List<Pair<Image, String>> imagePairs, BitmapLoader bitmapLoader, int color) {
        if (imagePairs == null || imagePairs.isEmpty()) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int imagesToCreate = Math.max(0, imagePairs.size() - getChildCount()); imagesToCreate > 0; imagesToCreate--) {
            addView((FifeImageView) inflater.inflate(R.layout.details_text_icon_single, this, false));
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            FifeImageView imageView = (FifeImageView) getChildAt(i);
            if (i < imagePairs.size()) {
                imageView.setVisibility(0);
                Pair<Image, String> imagePair = (Pair) imagePairs.get(i);
                Image image = imagePair.first;
                imageView.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
                imageView.setContentDescription((CharSequence) imagePair.second);
                if (color != 0) {
                    imageView.setColorFilter(color, Mode.SRC_IN);
                }
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int height = 0;
        int spaceLeft = (((int) (((float) MeasureSpec.getSize(widthMeasureSpec)) * 0.75f)) - paddingLeft) - paddingRight;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams childLp = child.getLayoutParams();
            if (childLp.width <= spaceLeft) {
                child.setVisibility(0);
                child.measure(MeasureSpec.makeMeasureSpec(childLp.width, 1073741824), MeasureSpec.makeMeasureSpec(childLp.height, 1073741824));
                spaceLeft -= childLp.width + this.mChildGap;
                width += childLp.width + this.mChildGap;
                height = Math.max(height, childLp.height);
            } else {
                child.setVisibility(8);
            }
        }
        setMeasuredDimension((width + paddingLeft) + paddingRight, (getPaddingTop() + height) + getPaddingBottom());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        int width = getWidth();
        int height = getHeight();
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int visibleChildCount = 0;
        int visibleChildTotalWidth = 0;
        for (i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                visibleChildCount++;
                visibleChildTotalWidth += child.getMeasuredWidth();
            }
        }
        if (visibleChildCount > 1) {
            visibleChildTotalWidth += this.mChildGap * (visibleChildCount - 1);
        }
        int x = paddingLeft + ((((width - visibleChildTotalWidth) - paddingLeft) - paddingRight) / 2);
        int paddingTop = getPaddingTop();
        int heightForContent = (height - paddingTop) - getPaddingBottom();
        for (i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() == 0) {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                int childY = paddingTop + ((heightForContent - childHeight) / 2);
                child.layout(x, childY, x + childWidth, childY + childHeight);
                x += this.mChildGap + childWidth;
            }
        }
    }
}
