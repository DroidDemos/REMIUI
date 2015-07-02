package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.StreamSpacer;

public class StreamHeroSpacerView extends View implements StreamSpacer {
    private float mHeroImageAspectRatio;
    private final int mLeadingSpacerHeight;
    private boolean mShowsHeroImage;

    public StreamHeroSpacerView(Context context) {
        this(context, null);
    }

    public StreamHeroSpacerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(context, 2, 0);
    }

    public void configureHeroImage(boolean showsHeroImage, float heroImageAspectRatio) {
        this.mShowsHeroImage = showsHeroImage;
        this.mHeroImageAspectRatio = heroImageAspectRatio;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, HeroGraphicView.getSpacerHeight(getContext(), width, this.mShowsHeroImage, this.mHeroImageAspectRatio) - this.mLeadingSpacerHeight);
    }
}
