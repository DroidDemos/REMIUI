package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.PaintDrawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.layout.FadingEdgeImageView;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.NextBanner;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;
import com.google.android.play.layout.ForegroundRelativeLayout;

public class PlayMerchBannerView extends ForegroundRelativeLayout implements Recyclable, Identifiable, PlayStoreUiElementNode, OnLoadedListener {
    private int mColumnCount;
    private final boolean mCompactHeight;
    private int mContentHorizontalPadding;
    private final int mFallbackMerchColor;
    private String mIdentifier;
    private int mMerchColor;
    private FadingEdgeImageView mMerchImage;
    private int mMinTextTrailingSpace;
    private PlayStoreUiElementNode mParentNode;
    private TextView mSubtitle;
    private TextView mTitle;
    private PlayStoreUiElement mUiElementProto;

    public PlayMerchBannerView(Context context) {
        this(context, null);
    }

    public PlayMerchBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources resources = context.getResources();
        this.mColumnCount = -1;
        this.mCompactHeight = resources.getBoolean(R.bool.play_merch_banner_compact);
        this.mFallbackMerchColor = resources.getColor(R.color.play_multi_primary);
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMerchImage = (FadingEdgeImageView) findViewById(R.id.merch_image);
        this.mTitle = (TextView) findViewById(R.id.banner_title);
        this.mSubtitle = (TextView) findViewById(R.id.banner_subtitle);
    }

    public void init(int columnCount, int contentHorizontalPadding) {
        if (columnCount <= 0) {
            FinskyLog.wtf("Merch banner doesn't support non-positive number of columns: " + columnCount + " passed", new Object[0]);
            return;
        }
        this.mColumnCount = columnCount;
        this.mContentHorizontalPadding = contentHorizontalPadding;
    }

    public void configureMerch(NextBanner banner, BitmapLoader bitmapLoader, Image merchImage, OnClickListener onClickListener, PlayStoreUiElementNode parentNode, byte[] serverLogsCookie) {
        this.mMerchColor = UiUtils.getFillColor(merchImage, this.mFallbackMerchColor);
        this.mMerchImage.setOnLoadedListener(this);
        this.mMerchImage.setImage(merchImage.imageUrl, merchImage.supportsFifeUrlOptions, bitmapLoader);
        if (this.mMerchImage.getDrawable() != null) {
            configureImageFadingEdge();
        } else {
            clearImageFadingEdge();
        }
        setBackgroundDrawable(new InsetDrawable(new PaintDrawable(this.mMerchColor), 0, getPaddingTop(), 0, getPaddingBottom()));
        this.mTitle.setText(banner.title);
        this.mSubtitle.setText(banner.subtitle);
        int textColor = UiUtils.getTextColor(banner, getFallbackMerchTextColor());
        this.mTitle.setTextColor(textColor);
        this.mSubtitle.setTextColor(textColor);
        setOnClickListener(onClickListener);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(409);
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, serverLogsCookie);
        this.mParentNode = parentNode;
        getParentNode().childImpression(this);
        requestLayout();
    }

    private void configureImageFadingEdge() {
        this.mMerchImage.configureFadingEdges(false, true, this.mMerchImage.getMeasuredWidth() / 4, this.mMerchColor);
    }

    private void clearImageFadingEdge() {
        this.mMerchImage.clearFadingEdges();
    }

    private int getFallbackMerchTextColor() {
        return getResources().getColor(UiUtils.isColorBright(this.mMerchColor) ? R.color.play_banner_dark_fg : R.color.play_banner_light_fg);
    }

    private int getMerchImageOffset(int height) {
        if (this.mColumnCount > 2) {
            return 0;
        }
        return (int) (((((float) height) * 1.7777778f) * 2.0f) / 10.0f);
    }

    private void measureTexts(int bannerWidth, int bannerHeight) {
        int i;
        int titleWidth = (bannerWidth - ((-getMerchImageOffset(bannerHeight)) + ((int) ((((float) bannerHeight) * 1.7777778f) * (this.mColumnCount <= 4 ? 0.85f : 1.0f))))) - this.mContentHorizontalPadding;
        this.mTitle.measure(MeasureSpec.makeMeasureSpec(titleWidth, 1073741824), 0);
        this.mSubtitle.measure(MeasureSpec.makeMeasureSpec(titleWidth, 1073741824), 0);
        int i2 = 0;
        Layout titleLayout = this.mTitle.getLayout();
        if (titleLayout != null) {
            for (i = 0; i < titleLayout.getLineCount(); i++) {
                i2 = Math.max(i2, (int) titleLayout.getLineWidth(i));
            }
        }
        Layout subtitleLayout = this.mSubtitle.getLayout();
        if (subtitleLayout != null) {
            for (i = 0; i < subtitleLayout.getLineCount(); i++) {
                i2 = Math.max(i2, (int) subtitleLayout.getLineWidth(i));
            }
        }
        if (i2 == 0) {
            i2 = titleWidth;
        }
        this.mMinTextTrailingSpace = titleWidth - i2;
    }

    public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
        configureImageFadingEdge();
    }

    public void onLoadedAndFadedIn(FifeImageView imageView) {
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (this.mColumnCount <= 0) {
            setMeasuredDimension(availableWidth, 0);
            return;
        }
        int fullHeight = availableWidth / this.mColumnCount;
        int finalContentHeight = fullHeight;
        if (this.mCompactHeight) {
            int compactHeight = (fullHeight * 2) / 3;
            measureTexts(availableWidth, compactHeight);
            if (this.mTitle.getMeasuredHeight() + this.mSubtitle.getMeasuredHeight() > compactHeight) {
                measureTexts(availableWidth, fullHeight);
            } else {
                finalContentHeight = compactHeight;
            }
        } else {
            measureTexts(availableWidth, fullHeight);
        }
        this.mMerchImage.measure(MeasureSpec.makeMeasureSpec((int) (1.7777778f * ((float) finalContentHeight)), 1073741824), MeasureSpec.makeMeasureSpec(finalContentHeight, 1073741824));
        if (this.mMerchImage.isLoaded()) {
            configureImageFadingEdge();
        } else {
            clearImageFadingEdge();
        }
        setMeasuredDimension(availableWidth, (getPaddingTop() + finalContentHeight) + getPaddingBottom());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int merchImageWidth = this.mMerchImage.getMeasuredWidth();
        if (merchImageWidth > 0) {
            int offset = getMerchImageOffset(height);
            this.mMerchImage.layout(-offset, paddingTop, (-offset) + merchImageWidth, this.mMerchImage.getMeasuredHeight() + paddingTop);
        } else {
            this.mMerchImage.layout(0, paddingTop, merchImageWidth, this.mMerchImage.getMeasuredHeight() + paddingTop);
        }
        int titleHeight = this.mTitle.getMeasuredHeight();
        int subtitleHeight = this.mSubtitle.getMeasuredHeight();
        int textY = paddingTop + (((((height - titleHeight) - subtitleHeight) - paddingTop) - paddingBottom) / 2);
        int textX = ((width - this.mContentHorizontalPadding) - this.mTitle.getMeasuredWidth()) + (this.mMinTextTrailingSpace / 2);
        this.mTitle.layout(textX, textY, this.mTitle.getMeasuredWidth() + textX, textY + titleHeight);
        textY += titleHeight;
        this.mSubtitle.layout(textX, textY, this.mSubtitle.getMeasuredWidth() + textX, textY + subtitleHeight);
    }

    public void onRecycle() {
        this.mMerchImage.clearImage();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new IllegalStateException("unwanted children");
    }
}
