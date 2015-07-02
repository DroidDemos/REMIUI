package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.android.finsky.layout.DetailsSectionStack.NoBottomSeparator;
import com.google.android.finsky.layout.DetailsSectionStack.NoTopSeparator;
import com.google.android.finsky.layout.actionbar.OverlayableImageHost;

public class DummyOverlayableImageHost extends View implements NoBottomSeparator, NoTopSeparator, OverlayableImageHost {
    private float mHeroImageAspectRatio;
    private boolean mShowsHeroImage;

    public DummyOverlayableImageHost(Context context) {
        this(context, null);
    }

    public DummyOverlayableImageHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void configureHeroImage(boolean showsHeroImage, float heroImageAspectRatio) {
        this.mShowsHeroImage = showsHeroImage;
        this.mHeroImageAspectRatio = heroImageAspectRatio;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = HeroGraphicView.getSpacerHeight(getContext(), width, true, this.mHeroImageAspectRatio);
        if (!this.mShowsHeroImage) {
            height /= 2;
        }
        setMeasuredDimension(width, height);
    }

    public int getOverlayableImageHeight() {
        return getHeight();
    }

    public int getOverlayTransparencyHeightFromTop() {
        return 0;
    }

    public void setOverlayableImageTopPadding(int topPadding) {
    }
}
