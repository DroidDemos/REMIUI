package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;

public class SetupWizardIllustration extends LinearLayout {
    private final float mAspectRatio;
    private final float mBaselineGridSize;
    private final boolean mCollapsable;
    private FifeImageView mImageView;
    private final int mStatusBarOffset;
    private TextView mTitleView;
    private final boolean mUseTabletGraphic;

    public SetupWizardIllustration(Context context) {
        this(context, null);
    }

    public SetupWizardIllustration(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SetupWizardIllustration, 0, 0);
        this.mCollapsable = a.getBoolean(0, false);
        this.mAspectRatio = a.getFloat(1, 0.0f);
        a.recycle();
        this.mBaselineGridSize = (float) getResources().getDimensionPixelSize(R.dimen.setup_wizard_baseline_grid_size);
        this.mStatusBarOffset = getResources().getDimensionPixelOffset(R.dimen.setup_wizard_status_bar_offset);
        this.mUseTabletGraphic = getResources().getBoolean(R.bool.setup_wizard_use_tablet_graphic);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mImageView = (FifeImageView) findViewById(R.id.setup_wizard_header_graphic);
        this.mTitleView = (TextView) findViewById(R.id.title);
    }

    public void configure(String imageUrl, boolean collapseIfPossible) {
        if (this.mImageView != null) {
            if (this.mCollapsable && collapseIfPossible) {
                this.mImageView.setVisibility(8);
                return;
            }
            this.mImageView.setVisibility(0);
            this.mImageView.setImage(imageUrl, true, FinskyApp.get().getBitmapLoader());
            this.mImageView.setOnLoadedListener(new OnLoadedListener() {
                public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
                    if (bitmap != null) {
                        int dstWidth;
                        int dstHeight;
                        SetupWizardIllustration.this.mImageView.setScaleType(ScaleType.MATRIX);
                        int layoutWidth = SetupWizardIllustration.this.mImageView.getWidth();
                        int layoutHeight = SetupWizardIllustration.this.mImageView.getHeight();
                        int srcWidth = bitmap.getWidth();
                        int srcHeight = bitmap.getHeight();
                        float srcAspectRatio = ((float) srcWidth) / ((float) srcHeight);
                        if (SetupWizardIllustration.this.mUseTabletGraphic) {
                            SetupWizardIllustration.this.mImageView.setBackgroundResource(R.drawable.setup_wizard_illustration_bg_tablet);
                            dstWidth = (int) (((float) layoutHeight) * srcAspectRatio);
                            dstHeight = layoutHeight;
                        } else {
                            dstWidth = Math.max(layoutWidth, (int) (((float) layoutHeight) * srcAspectRatio));
                            dstHeight = Math.max(layoutHeight, (int) (((float) layoutWidth) / srcAspectRatio));
                        }
                        RectF srcRect = new RectF(0.0f, 0.0f, (float) srcWidth, (float) srcHeight);
                        RectF dstRect = new RectF(0.0f, 0.0f, (float) dstWidth, (float) dstHeight);
                        Matrix matrix = new Matrix();
                        matrix.setRectToRect(srcRect, dstRect, ScaleToFit.FILL);
                        SetupWizardIllustration.this.mImageView.setImageMatrix(matrix);
                    }
                }

                public void onLoadedAndFadedIn(FifeImageView imageView) {
                }
            });
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean hasImage;
        int i = 0;
        if (this.mImageView == null || this.mImageView.getVisibility() == 8) {
            hasImage = false;
        } else {
            hasImage = true;
        }
        if (this.mTitleView != null) {
            LayoutParams layoutParams = (LayoutParams) this.mTitleView.getLayoutParams();
            if (!hasImage) {
                i = this.mStatusBarOffset;
            }
            layoutParams.topMargin = i;
        }
        if (hasImage && this.mAspectRatio != 0.0f) {
            int height = (int) (((float) MeasureSpec.getSize(widthMeasureSpec)) / this.mAspectRatio);
            this.mImageView.getLayoutParams().height = (int) (((float) height) - (((float) height) % this.mBaselineGridSize));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
