package com.google.android.finsky.layout.play;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import com.android.vending.R;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.layout.FadingEdgeImageView;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;

public class PlayCardMerchClusterViewContent extends PlayCardClusterViewContent implements Recyclable, OnLoadedListener {
    private final int mFallbackMerchColor;
    private int mMerchColor;
    private FadingEdgeImageView mMerchImage;
    private int mMerchImagePosition;

    public PlayCardMerchClusterViewContent(Context context) {
        this(context, null);
    }

    public PlayCardMerchClusterViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFallbackMerchColor = context.getResources().getColor(R.color.play_multi_primary);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMerchImage = (FadingEdgeImageView) findViewById(R.id.merch_image);
    }

    protected int getIndexOfFirstCard() {
        return 1;
    }

    public void configureMerch(BitmapLoader bitmapLoader, int merchImagePosition, Image merchImage, String clusterTitle, OnClickListener merchClickListener) {
        boolean z = true;
        if (merchImagePosition == 0 || merchImagePosition == 1) {
            this.mMerchColor = UiUtils.getFillColor(merchImage, this.mFallbackMerchColor);
            this.mMerchImagePosition = merchImagePosition;
            this.mMerchImage.setOnLoadedListener(this);
            this.mMerchImage.setImage(merchImage.imageUrl, merchImage.supportsFifeUrlOptions, bitmapLoader);
            if (this.mMerchImage.getDrawable() != null) {
                configureImageFadingEdge();
            } else {
                clearImageFadingEdge();
            }
            this.mMerchImage.setOnClickListener(merchClickListener);
            FadingEdgeImageView fadingEdgeImageView = this.mMerchImage;
            if (merchClickListener == null) {
                z = false;
            }
            fadingEdgeImageView.setClickable(z);
            FadingEdgeImageView fadingEdgeImageView2 = this.mMerchImage;
            if (merchClickListener == null) {
                clusterTitle = null;
            }
            fadingEdgeImageView2.setContentDescription(clusterTitle);
            setBackgroundColor(this.mMerchColor);
            return;
        }
        throw new IllegalArgumentException("Merch image position " + merchImagePosition + " is not supported");
    }

    private void configureImageFadingEdge() {
        boolean isFadingLeftEdge;
        boolean isFadingRightEdge;
        if (this.mMerchImagePosition == 1) {
            isFadingLeftEdge = true;
        } else {
            isFadingLeftEdge = false;
        }
        if (this.mMerchImagePosition == 0) {
            isFadingRightEdge = true;
        } else {
            isFadingRightEdge = false;
        }
        this.mMerchImage.configureFadingEdges(isFadingLeftEdge, isFadingRightEdge, this.mMerchImage.getMeasuredWidth() / 4, this.mMerchColor);
    }

    private void clearImageFadingEdge() {
        this.mMerchImage.clearFadingEdges();
    }

    public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
        configureImageFadingEdge();
    }

    public void onLoadedAndFadedIn(FifeImageView imageView) {
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        this.mMerchImage.measure(MeasureSpec.makeMeasureSpec((int) (1.7777778f * ((float) height)), 1073741824), MeasureSpec.makeMeasureSpec(height, 1073741824));
        if (this.mMerchImage.isLoaded()) {
            configureImageFadingEdge();
        } else {
            clearImageFadingEdge();
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int width = getWidth();
        int height = getHeight();
        int merchImageWidth = this.mMerchImage.getMeasuredWidth();
        int merchImageHeight = this.mMerchImage.getMeasuredHeight();
        if (merchImageWidth > 0) {
            int imageFocalPointX = (merchImageHeight * 3) / 4;
            int offset;
            if (this.mMerchImagePosition == 0) {
                offset = (getLeadingGap(width) / 2) - imageFocalPointX;
                this.mMerchImage.layout(offset, 0, offset + merchImageWidth, height);
                return;
            }
            offset = (width - (getTrailingGap(width) / 2)) - imageFocalPointX;
            int rightEdge = offset + merchImageWidth;
            if (rightEdge < width) {
                offset += width - rightEdge;
            }
            this.mMerchImage.layout(offset, 0, offset + merchImageWidth, height);
            return;
        }
        this.mMerchImage.layout(0, 0, merchImageWidth, height);
    }

    public void onRecycle() {
        this.mMerchImage.clearImage();
    }
}
