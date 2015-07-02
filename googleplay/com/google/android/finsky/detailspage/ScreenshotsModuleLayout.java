package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.android.vending.R;
import com.google.android.finsky.adapters.ImageStripAdapter;
import com.google.android.finsky.adapters.ImageStripAdapter.OnImageChildViewTapListener;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoBottomDivider;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoTopDivider;
import com.google.android.finsky.detailspage.ModuleMarginItemDecoration.EdgeToEdge;
import com.google.android.finsky.layout.HorizontalStrip;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.protos.Common.Image.Dimension;

public class ScreenshotsModuleLayout extends FrameLayout implements OnImageChildViewTapListener, NoBottomDivider, NoTopDivider, EdgeToEdge, RetryButtonListener {
    private ScreenshotsClickListener mClickListener;
    private HorizontalStrip mImageStrip;
    private ImageStripAdapter mImageStripAdapter;
    private LayoutSwitcher mLayoutSwitcher;

    public interface ScreenshotsClickListener {
        void onImageClick(int i);

        void onRetryClick();
    }

    public ScreenshotsModuleLayout(Context context) {
        super(context);
    }

    public ScreenshotsModuleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        int screenshotsLeadingMargin;
        super.onFinishInflate();
        this.mImageStrip = (HorizontalStrip) findViewById(R.id.strip);
        Resources res = getResources();
        boolean useWideLayout = res.getBoolean(R.bool.use_wide_details_layout);
        if (useWideLayout) {
            int sideMargin = ModuleMarginItemDecoration.getDefaultSideMargin(res, useWideLayout);
            screenshotsLeadingMargin = Math.max(sideMargin - res.getDimensionPixelSize(R.dimen.screenshots_leading_peek), 0);
            LayoutParams stripBackgroundLp = (LayoutParams) findViewById(R.id.strip_background).getLayoutParams();
            stripBackgroundLp.leftMargin = sideMargin;
            stripBackgroundLp.rightMargin = sideMargin;
        } else {
            screenshotsLeadingMargin = 0;
        }
        this.mImageStrip.setMargins(screenshotsLeadingMargin, res.getDimensionPixelOffset(R.dimen.screenshots_spacing));
        this.mLayoutSwitcher = new LayoutSwitcher(this, R.id.strip, this);
        this.mLayoutSwitcher.switchToLoadingDelayed(500);
    }

    public void bind(Dimension[] dimensions, Drawable[] images, ScreenshotsClickListener listener, boolean inLoadingMode, boolean inErrorMode) {
        this.mClickListener = listener;
        if (inLoadingMode) {
            this.mLayoutSwitcher.switchToLoadingMode();
        } else if (inErrorMode) {
            this.mLayoutSwitcher.switchToErrorMode(getContext().getString(R.string.screenshots_not_loaded));
        } else {
            this.mLayoutSwitcher.switchToDataMode();
            if (this.mImageStripAdapter == null) {
                this.mImageStripAdapter = new ImageStripAdapter(images.length, this);
                this.mImageStrip.setAdapter(this.mImageStripAdapter);
            }
            this.mImageStripAdapter.setImages(images, dimensions);
        }
    }

    public void onImageChildViewTap(int index) {
        if (this.mClickListener != null) {
            this.mClickListener.onImageClick(index);
        }
    }

    public void onRetry() {
        if (this.mClickListener != null) {
            this.mClickListener.onRetryClick();
        }
    }
}
