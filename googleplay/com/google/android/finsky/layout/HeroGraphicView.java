package com.google.android.finsky.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.DetailsSummaryViewBinder;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.lightpurchase.AgeVerificationActivity;
import com.google.android.finsky.layout.DetailsSectionStack.NoBottomSeparator;
import com.google.android.finsky.layout.DetailsSectionStack.NoTopSeparator;
import com.google.android.finsky.layout.actionbar.FinskySearchToolbar;
import com.google.android.finsky.layout.actionbar.OverlayableImageHost;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.EditorialSeriesContainer;
import com.google.android.finsky.protos.DocumentV2.SeriesAntenna;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.image.ThumbnailUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;
import com.google.android.play.layout.ForegroundRelativeLayout;
import java.util.List;

public class HeroGraphicView extends ForegroundRelativeLayout implements NoBottomSeparator, NoTopSeparator, OverlayableImageHost, OnLoadedListener {
    protected View mBottomOverlayView;
    private int mBottomOverlayViewBottom;
    private OnLoadedListener mCallerOnLoadedListener;
    private int mCorpusFillMode;
    private View mCorpusFillView;
    private int mCurrentOverlayTopOffset;
    protected float mDefaultAspectRatio;
    private final int mDefaultHeight;
    private int mFullScreenModePeekAmount;
    private FifeImageView mHeroImageView;
    private boolean mIsFrozenInCorpusFill;
    private final boolean mIsFullScreenMode;
    private boolean mIsImageLoaded;
    private boolean mIsInBackgroundLayer;
    private int mLeadingSpacerVerticalSpan;
    private boolean mNeedsToShowPlayIconAfterUnfreezing;
    private ImageView mPlayImageView;
    private boolean mShouldTopAlignImage;
    private boolean mSupportsLeadingOverlayTransparency;
    private TextView mTitle;
    private View mTopOverlayView;

    public HeroGraphicView(Context context) {
        this(context, null);
    }

    public HeroGraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDefaultHeight = context.getResources().getDimensionPixelSize(R.dimen.hero_image_height);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.HeroGraphicView);
        this.mIsFullScreenMode = viewAttrs.getBoolean(0, false);
        this.mShouldTopAlignImage = this.mIsFullScreenMode;
        viewAttrs.recycle();
        this.mCorpusFillMode = 0;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeroImageView = (FifeImageView) findViewById(R.id.hero_image);
        this.mPlayImageView = (ImageView) findViewById(R.id.play_icon);
        this.mCorpusFillView = findViewById(R.id.corpus_fill);
        this.mTopOverlayView = findViewById(R.id.hero_image_top_overlay);
        this.mBottomOverlayView = findViewById(R.id.hero_image_bottom_overlay);
        this.mTitle = (TextView) findViewById(R.id.hero_title);
    }

    public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
        this.mIsImageLoaded = true;
        if (!this.mIsFrozenInCorpusFill) {
            this.mHeroImageView.setVisibility(0);
        }
        if (this.mShouldTopAlignImage && bitmap != null) {
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            int imageWidth = this.mHeroImageView.getWidth();
            float bitmapAspectRatio = ((float) bitmapHeight) / ((float) bitmapWidth);
            Matrix matrix = new Matrix();
            matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) bitmapWidth, (float) bitmapHeight), new RectF(0.0f, 0.0f, (float) imageWidth, (float) ((int) (((float) imageWidth) * bitmapAspectRatio))), ScaleToFit.FILL);
            this.mHeroImageView.setScaleType(ScaleType.MATRIX);
            this.mHeroImageView.setImageMatrix(matrix);
        }
        if (this.mCallerOnLoadedListener != null) {
            this.mCallerOnLoadedListener.onLoaded(imageView, bitmap);
        }
    }

    public void onLoadedAndFadedIn(FifeImageView imageView) {
        if (!this.mIsFrozenInCorpusFill) {
            setCorpusFillMode(0);
            this.mHeroImageView.setVisibility(0);
            if (this.mNeedsToShowPlayIconAfterUnfreezing) {
                this.mPlayImageView.setVisibility(0);
            }
        }
    }

    public void freezeCorpusFill(long fadeDurationMs) {
        boolean z = true;
        this.mIsFrozenInCorpusFill = true;
        if (this.mPlayImageView.getVisibility() != 0) {
            z = false;
        }
        this.mNeedsToShowPlayIconAfterUnfreezing = z;
        this.mCorpusFillView.setAlpha(0.0f);
        this.mCorpusFillView.animate().alpha(1.0f).setDuration(fadeDurationMs).start();
        if (!this.mIsFullScreenMode) {
            this.mHeroImageView.setVisibility(4);
            this.mPlayImageView.setVisibility(4);
        }
    }

    public void unfreezeCorpusFill(long fadeDurationMs) {
        this.mIsFrozenInCorpusFill = false;
        if (this.mIsImageLoaded) {
            this.mHeroImageView.setVisibility(0);
            this.mHeroImageView.setAlpha(0.0f);
            this.mHeroImageView.animate().alpha(1.0f).setDuration(fadeDurationMs).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    HeroGraphicView.this.setCorpusFillMode(0);
                }
            }).start();
            if (this.mNeedsToShowPlayIconAfterUnfreezing) {
                this.mPlayImageView.setVisibility(0);
                this.mPlayImageView.setScaleX(0.0f);
                this.mPlayImageView.setScaleY(0.0f);
                this.mPlayImageView.animate().scaleY(1.0f).setDuration(fadeDurationMs).start();
                this.mPlayImageView.animate().scaleX(1.0f).setDuration(fadeDurationMs).start();
            }
        }
    }

    public void showHeroOverlay() {
        this.mTopOverlayView.setVisibility(0);
    }

    public void showPlayIcon(String youtubeUrl, String title, boolean preferVideosAppForPlayback, boolean isMature, int backend, PlayStoreUiElementNode parentNode) {
        this.mPlayImageView.setVisibility(0);
        final String accountName = FinskyApp.get().getCurrentAccountName();
        final PlayStoreUiElementNode playStoreUiElementNode = parentNode;
        final boolean z = isMature;
        final int i = backend;
        final String str = youtubeUrl;
        final boolean z2 = preferVideosAppForPlayback;
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                FinskyApp.get().getEventLogger().logClickEvent(120, null, playStoreUiElementNode);
                if (z && FinskyApp.get().getClientMutationCache(accountName).isAgeVerificationRequired()) {
                    intent = AgeVerificationActivity.createIntent(accountName, i, null);
                } else {
                    intent = HeroGraphicView.this.getClickIntent(str, z2);
                }
                HeroGraphicView.this.getContext().startActivity(intent);
            }
        });
        if (!TextUtils.isEmpty(title)) {
            setContentDescription(R.string.content_description_generic_trailer, title);
        }
        if (this.mIsFrozenInCorpusFill && !this.mIsFullScreenMode) {
            this.mNeedsToShowPlayIconAfterUnfreezing = true;
            this.mPlayImageView.setVisibility(4);
        }
    }

    private Intent getClickIntent(String youtubeUrl, boolean preferVideosAppForPlayback) {
        if (preferVideosAppForPlayback) {
            return IntentUtils.createMovieTrailerIntentForUrl(getContext().getPackageManager(), Uri.parse(youtubeUrl), FinskyApp.get().getCurrentAccountName());
        }
        return IntentUtils.createYouTubeIntentForUrl(getContext().getPackageManager(), Uri.parse(youtubeUrl), FinskyApp.get().getCurrentAccountName());
    }

    public void setContentDescription(int resourceId, Object... formatArgs) {
        Context context = getContext();
        setContentDescription(formatArgs == null ? context.getString(resourceId) : context.getString(resourceId, formatArgs));
    }

    public float getCurrentAspectRatio() {
        Drawable drawable = this.mHeroImageView.getDrawable();
        if (drawable == null || !this.mHeroImageView.isLoaded()) {
            return this.mDefaultAspectRatio;
        }
        return ((float) drawable.getIntrinsicHeight()) / ((float) drawable.getIntrinsicWidth());
    }

    public void bindTvEpisode(BitmapLoader bitmapLoader, Document doc) {
        this.mIsInBackgroundLayer = false;
        this.mShouldTopAlignImage = true;
        this.mDefaultAspectRatio = 0.5625f;
        Image bestImage = getHeroGraphic(doc, 13, -1);
        if (bestImage == null) {
            setVisibility(8);
            return;
        }
        this.mHeroImageView.setImage(bestImage.imageUrl, bestImage.supportsFifeUrlOptions, bitmapLoader);
        setBackgroundResource(0);
    }

    public void bindEditorialVideoFooter(BitmapLoader bitmapLoader, List<Image> images) {
        this.mIsInBackgroundLayer = false;
        this.mDefaultAspectRatio = 0.5625f;
        Image bestImage = ThumbnailUtils.getBestImage(images, 0, getResources().getDimensionPixelSize(R.dimen.play_profile_header_height));
        this.mHeroImageView.setImage(bestImage.imageUrl, bestImage.supportsFifeUrlOptions, bitmapLoader);
        setBackgroundResource(0);
    }

    public void bindGeneric(Document document, int imageType) {
        BitmapLoader bitmapLoader = FinskyApp.get().getBitmapLoader();
        Image coverImage = (Image) document.getImages(imageType).get(0);
        if (coverImage == null) {
            this.mHeroImageView.setVisibility(8);
            return;
        }
        this.mHeroImageView.setVisibility(0);
        this.mHeroImageView.setImage(coverImage.imageUrl, coverImage.supportsFifeUrlOptions, bitmapLoader);
        this.mDefaultAspectRatio = 0.0f;
    }

    public void bindProfile(Document document) {
        BitmapLoader bitmapLoader = FinskyApp.get().getBitmapLoader();
        Image coverImage = (Image) document.getImages(15).get(0);
        if (coverImage == null) {
            this.mHeroImageView.setVisibility(8);
            return;
        }
        setCorpusFillMode(1);
        setCorpusForFill(document.getBackend());
        this.mHeroImageView.setVisibility(0);
        this.mHeroImageView.setImage(coverImage.imageUrl, coverImage.supportsFifeUrlOptions, bitmapLoader);
        configureDetailsAspectRatio(coverImage, document, false);
        this.mFullScreenModePeekAmount = FinskySearchToolbar.getToolbarHeight(getContext()) * 2;
        this.mIsInBackgroundLayer = true;
    }

    public void setCorpusForFill(int backendId) {
        setFillColor(getDefaultFillColor(backendId));
    }

    public void setFillColor(int color) {
        if (this.mCorpusFillView != null) {
            this.mCorpusFillView.setBackgroundColor(color);
        }
    }

    public void setCorpusFillMode(int corpusFillMode) {
        int i = 8;
        if (this.mCorpusFillView != null && !this.mIsFrozenInCorpusFill && this.mCorpusFillMode != corpusFillMode) {
            int i2;
            this.mCorpusFillMode = corpusFillMode;
            FifeImageView fifeImageView = this.mHeroImageView;
            if (this.mCorpusFillMode == 2) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            fifeImageView.setVisibility(i2);
            View view = this.mCorpusFillView;
            if (this.mCorpusFillMode != 0) {
                i = 0;
            }
            view.setVisibility(i);
        }
    }

    private int getDefaultFillColor(int backendId) {
        return CorpusResourceUtils.getPrimaryColor(getContext(), backendId);
    }

    public boolean isShowingNoImageFallbackFill() {
        return this.mCorpusFillMode == 2;
    }

    private void bindHero(Image hero, Document doc, BitmapLoader bitmapLoader, boolean supportsLeadingOverlayTransparency, boolean hideIfNoImages, boolean preferVideosAppForPlayback, PlayStoreUiElementNode parentNode) {
        this.mIsInBackgroundLayer = true;
        setCorpusForFill(doc.getBackend());
        if (hero != null) {
            setCorpusFillMode(1);
            List<Image> videoPreviews = doc.getImages(3);
            boolean hasVideoPreviews = (videoPreviews == null || videoPreviews.isEmpty()) ? false : true;
            this.mHeroImageView.setOnLoadedListener(this);
            if (NavigationManager.areTransitionsEnabled() && !this.mIsFullScreenMode) {
                this.mHeroImageView.setToFadeInAfterLoad(false);
            }
            this.mHeroImageView.setImage(hero.imageUrl, hero.supportsFifeUrlOptions, bitmapLoader);
            if (hasVideoPreviews) {
                showPlayIcon(((Image) videoPreviews.get(0)).imageUrl, null, preferVideosAppForPlayback, doc.isMature(), doc.getBackend(), parentNode);
            }
            configureDetailsAspectRatio(hero, doc, supportsLeadingOverlayTransparency);
            setVisibility(0);
        } else if (hideIfNoImages) {
            setVisibility(8);
        } else {
            setCorpusFillMode(2);
        }
    }

    private void configureDetailsAspectRatio(Image imageData, Document doc, boolean supportsLeadingOverlayTransparency) {
        if (!this.mIsFullScreenMode) {
            this.mSupportsLeadingOverlayTransparency = supportsLeadingOverlayTransparency;
            this.mDefaultAspectRatio = getHeroAspectRatio(doc.getDocumentType());
        } else if (imageData.dimension != null && imageData.dimension.hasWidth && imageData.dimension.hasHeight) {
            this.mDefaultAspectRatio = ((float) imageData.dimension.height) / ((float) imageData.dimension.width);
        } else {
            this.mDefaultAspectRatio = getDetailsHeroAspectRatio(doc, this.mIsFullScreenMode);
        }
    }

    public void bindDetailsHero(Document doc, BitmapLoader bitmapLoader, boolean isWideLayout, PlayStoreUiElementNode parentNode) {
        bindHero(getDetailsHeroGraphic(doc, isWideLayout), doc, bitmapLoader, false, false, shouldPreferVideosAppForPlayback(doc), parentNode);
    }

    public void bindDetailsMovieTrailer(Document doc, BitmapLoader bitmapLoader, boolean hideIfNoImages, PlayStoreUiElementNode parentNode) {
        if (!this.mIsFullScreenMode) {
            this.mHeroImageView.setFocusPoint(0.5f, 0.0f);
            this.mHeroImageView.setDefaultZoom(1.0f);
        }
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, false, hideIfNoImages, true, parentNode);
        setContentDescription(R.string.content_description_movie_trailer, new Object[0]);
    }

    public void bindDetailsAppPromo(Document doc, BitmapLoader bitmapLoader, boolean hideIfNoImages, PlayStoreUiElementNode parentNode) {
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, false, hideIfNoImages, false, parentNode);
        showHeroOverlay();
        setContentDescription(R.string.content_description_generic_trailer, doc.getTitle());
    }

    public void bindDetailsCreator(Document doc, BitmapLoader bitmapLoader, boolean hideIfNoImages, PlayStoreUiElementNode parentNode) {
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, false, hideIfNoImages, false, parentNode);
        showHeroOverlay();
        setContentDescription(R.string.content_description_generic_poster, doc.getTitle());
    }

    public void bindDetailsArtist(Document doc, BitmapLoader bitmapLoader, boolean hideIfNoImages, PlayStoreUiElementNode parentNode) {
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, false, hideIfNoImages, false, parentNode);
        showHeroOverlay();
        setContentDescription(CorpusResourceUtils.getItemThumbnailContentDescription(doc, getResources()));
    }

    public void bindDetailsTvShow(Document doc, BitmapLoader bitmapLoader, boolean hideIfNoImages, PlayStoreUiElementNode parentNode) {
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, false, hideIfNoImages, false, parentNode);
        showHeroOverlay();
        setContentDescription(CorpusResourceUtils.getItemThumbnailContentDescription(doc, getResources()));
    }

    public void bindNewsstand(Document doc, BitmapLoader bitmapLoader, boolean hideIfNoImages, PlayStoreUiElementNode parentNode) {
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, false, hideIfNoImages, false, parentNode);
        showHeroOverlay();
        setContentDescription(CorpusResourceUtils.getItemThumbnailContentDescription(doc, getResources()));
    }

    public void bindDetailsAlbumWithArtist(Document doc, BitmapLoader bitmapLoader, PlayStoreUiElementNode parentNode) {
        setCorpusForFill(doc.getBackend());
        if (doc.getAlbumDetails().details.artist.length <= 0 || TextUtils.isEmpty(doc.getAlbumDetails().details.artist[0].detailsUrl)) {
            setCorpusFillMode(2);
            return;
        }
        setCorpusFillMode(1);
        Document artistDoc = doc.getCreatorDoc();
        if (artistDoc != null) {
            bindDetailsArtist(artistDoc, bitmapLoader, false, parentNode);
        }
    }

    public void bindDetailsDefault(Document doc, BitmapLoader bitmapLoader, boolean hideIfNoImages, OnLoadedListener onLoadedListener, PlayStoreUiElementNode parentNode) {
        this.mCallerOnLoadedListener = onLoadedListener;
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, true, hideIfNoImages, false, parentNode);
        showHeroOverlay();
        setContentDescription(CorpusResourceUtils.getItemThumbnailContentDescription(doc, getResources()));
    }

    public void bindDetailsAntenna(Document doc, BitmapLoader bitmapLoader, PlayStoreUiElementNode parentNode) {
        SeriesAntenna antenna = doc.getAntennaInfo();
        setFillColor(UiUtils.getFillColor(antenna, getDefaultFillColor(doc.getBackend())));
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, false, false, false, parentNode);
        this.mTitle.setText(antenna.episodeTitle);
        this.mTitle.setVisibility(0);
        this.mBottomOverlayView.setVisibility(0);
        setContentDescription(R.string.content_description_generic_trailer, doc.getTitle());
        this.mShouldTopAlignImage = true;
        this.mIsInBackgroundLayer = false;
    }

    public void bindDetailsEditorial(Document doc, BitmapLoader bitmapLoader, PlayStoreUiElementNode parentNode) {
        EditorialSeriesContainer editorial = doc.getEditorialSeriesContainer();
        setFillColor(UiUtils.getFillColor(editorial, getDefaultFillColor(doc.getBackend())));
        bindHero(getHeroGraphic(doc), doc, bitmapLoader, false, false, false, parentNode);
        this.mTitle.setText(TextUtils.isEmpty(editorial.episodeTitle) ? editorial.seriesTitle : editorial.episodeTitle);
        this.mTitle.setVisibility(0);
        this.mBottomOverlayView.setVisibility(0);
        setContentDescription(R.string.content_description_generic_trailer, doc.getTitle());
        this.mShouldTopAlignImage = true;
        this.mFullScreenModePeekAmount = FinskySearchToolbar.getToolbarHeight(getContext()) * 2;
    }

    public int getOverlayTransparencyHeightFromTop() {
        if (this.mSupportsLeadingOverlayTransparency) {
            return getHeight() - this.mDefaultHeight;
        }
        return 0;
    }

    public int getOverlayableImageHeight() {
        return getHeight();
    }

    public void setOverlayableImageTopPadding(int topPadding) {
        setPadding(0, topPadding, 0, 0);
        this.mCurrentOverlayTopOffset = topPadding;
        int newOffset = topPadding - this.mHeroImageView.getTop();
        this.mHeroImageView.offsetTopAndBottom(newOffset);
        if (this.mCorpusFillView != null) {
            this.mCorpusFillView.offsetTopAndBottom(newOffset);
        }
        this.mPlayImageView.offsetTopAndBottom(newOffset);
        this.mTopOverlayView.offsetTopAndBottom(((int) (((float) topPadding) / 0.5f)) - this.mTopOverlayView.getTop());
    }

    public int getLeadingSpacerVerticalSpan() {
        return this.mLeadingSpacerVerticalSpan;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.mPlayImageView.measure(0, 0);
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int availableHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.mLeadingSpacerVerticalSpan = getSpacerHeight(getContext(), availableWidth, this.mHeroImageView.getVisibility() != 8, this.mDefaultAspectRatio);
        if (this.mIsFullScreenMode && isShowingNoImageFallbackFill() && this.mFullScreenModePeekAmount == 0) {
            this.mLeadingSpacerVerticalSpan /= 2;
        }
        int height = getHeroHeight(getContext(), availableWidth, availableHeight, this.mIsFullScreenMode, this.mIsInBackgroundLayer, this.mDefaultAspectRatio);
        if (this.mTitle.getVisibility() != 8) {
            this.mTitle.measure(MeasureSpec.makeMeasureSpec(availableWidth, 1073741824), 0);
        }
        if (this.mHeroImageView.getVisibility() == 8) {
            this.mBottomOverlayView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, 1073741824));
        } else {
            int heroImageHeight;
            if (!this.mIsFullScreenMode || this.mFullScreenModePeekAmount <= 0) {
                heroImageHeight = height;
            } else {
                heroImageHeight = this.mLeadingSpacerVerticalSpan + this.mFullScreenModePeekAmount;
            }
            this.mHeroImageView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(heroImageHeight, 1073741824));
            if (this.mBottomOverlayView.getVisibility() != 8) {
                int bottomOverlayHeight = 0;
                if (this.mIsImageLoaded) {
                    Drawable heroDrawable = this.mHeroImageView.getDrawable();
                    if (heroDrawable == null) {
                        this.mBottomOverlayViewBottom = height;
                    } else {
                        this.mBottomOverlayViewBottom = Math.min((availableWidth * heroDrawable.getIntrinsicHeight()) / heroDrawable.getIntrinsicWidth(), heroImageHeight);
                    }
                    bottomOverlayHeight = this.mBottomOverlayViewBottom - Math.min(heroImageHeight / 2, this.mLeadingSpacerVerticalSpan - this.mTitle.getMeasuredHeight());
                }
                this.mBottomOverlayView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(bottomOverlayHeight, 1073741824));
            }
        }
        if (!(this.mCorpusFillView == null || this.mCorpusFillView.getVisibility() == 8)) {
            int corpusFillHeight;
            if (this.mCorpusFillMode == 2) {
                if (this.mIsFullScreenMode) {
                    corpusFillHeight = this.mLeadingSpacerVerticalSpan + (FinskySearchToolbar.getToolbarHeight(getContext()) * 2);
                } else {
                    corpusFillHeight = this.mLeadingSpacerVerticalSpan;
                }
            } else if (!this.mIsFullScreenMode || this.mDefaultAspectRatio <= 0.0f) {
                corpusFillHeight = height;
            } else {
                corpusFillHeight = Math.min((int) (((float) availableWidth) * this.mDefaultAspectRatio), this.mHeroImageView.getMeasuredHeight());
            }
            this.mCorpusFillView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(corpusFillHeight, 1073741824));
        }
        if (this.mTopOverlayView.getVisibility() != 8) {
            this.mTopOverlayView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(this.mTopOverlayView.getLayoutParams().height, 1073741824));
        }
        setMeasuredDimension(availableWidth, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int paddingRight = getPaddingRight();
        int topOffset = this.mCurrentOverlayTopOffset;
        int heroMeasuredWidth = this.mHeroImageView.getMeasuredWidth();
        int imageLeft = ((width - heroMeasuredWidth) - paddingRight) / 2;
        this.mHeroImageView.layout(imageLeft, topOffset, imageLeft + heroMeasuredWidth, this.mHeroImageView.getMeasuredHeight() + topOffset);
        if (this.mPlayImageView.getVisibility() != 8) {
            int playMeasuredWidth = this.mPlayImageView.getMeasuredWidth();
            int heroMeasuredHeight = this.mPlayImageView.getMeasuredHeight();
            imageLeft = ((width - playMeasuredWidth) - paddingRight) / 2;
            int imageTop = topOffset + (((this.mLeadingSpacerVerticalSpan == 0 ? height : this.mLeadingSpacerVerticalSpan) - heroMeasuredHeight) / 2);
            this.mPlayImageView.layout(imageLeft, imageTop, imageLeft + playMeasuredWidth, imageTop + heroMeasuredHeight);
        }
        if (this.mCorpusFillView != null) {
            if (this.mCorpusFillView.getVisibility() != 8) {
                int corpusFillTop;
                int corpusFillHeight = this.mCorpusFillView.getMeasuredHeight();
                int i = this.mCorpusFillMode;
                if (r0 == 2) {
                    corpusFillTop = (int) (((float) topOffset) * 0.5f);
                } else {
                    corpusFillTop = topOffset;
                }
                this.mCorpusFillView.layout(0, corpusFillTop, this.mCorpusFillView.getMeasuredWidth(), corpusFillTop + corpusFillHeight);
            }
        }
        if (this.mTopOverlayView.getVisibility() != 8) {
            int overlayTop = (int) (((float) topOffset) / 0.5f);
            this.mTopOverlayView.layout(0, overlayTop, width, this.mTopOverlayView.getMeasuredHeight() + overlayTop);
        }
        if (this.mBottomOverlayView.getVisibility() != 8) {
            this.mBottomOverlayView.layout(0, this.mBottomOverlayViewBottom - this.mBottomOverlayView.getMeasuredHeight(), width, this.mBottomOverlayViewBottom);
        }
        if (this.mTitle.getVisibility() != 8) {
            int titleBottom = this.mLeadingSpacerVerticalSpan == 0 ? height : this.mLeadingSpacerVerticalSpan;
            this.mTitle.layout(0, titleBottom - this.mTitle.getMeasuredHeight(), width, titleBottom);
        }
    }

    public static int getSpacerHeight(Context context, int availableWidth, boolean showsImage, float imageAspectRatio) {
        if (!showsImage) {
            return FinskySearchToolbar.getToolbarHeight(context) * 3;
        }
        Resources res = context.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        if (imageAspectRatio <= 0.0f || screenHeight <= screenWidth) {
            return Math.min(res.getDimensionPixelSize(R.dimen.play_profile_header_height), screenHeight / 2);
        }
        return (int) (((float) availableWidth) * imageAspectRatio);
    }

    public static int getHeroHeight(Context context, int availableWidth, int availableHeight, boolean allowFullScreen, boolean isInBackgroundLayer, float imageAspectRatio) {
        if (allowFullScreen && isInBackgroundLayer) {
            return availableHeight;
        }
        Resources res = context.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        if (imageAspectRatio <= 0.0f || screenHeight <= screenWidth) {
            return Math.min(res.getDimensionPixelSize(R.dimen.play_profile_header_height), screenHeight / 2);
        }
        return (int) (((float) availableWidth) * imageAspectRatio);
    }

    public static float getDetailsHeroAspectRatio(Document detailsDoc, boolean isWideLayout) {
        boolean isAlbumOnWideLayout = isWideLayout && detailsDoc.getDocumentType() == 2;
        return getHeroAspectRatio(isAlbumOnWideLayout ? 3 : detailsDoc.getDocumentType());
    }

    public static float getHeroAspectRatio(int docType) {
        switch (docType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                return 0.48828125f;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 0.5f;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                return 0.5625f;
            default:
                return PlayCardClusterMetadata.getAspectRatio(docType);
        }
    }

    public static boolean shouldShowDetailsHeroGraphic(Document doc, boolean isWideLayout) {
        if (isWideLayout) {
            return true;
        }
        switch (doc.getDocumentType()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return false;
            default:
                if (getDetailsHeroGraphic(doc, isWideLayout) == null) {
                    return false;
                }
                return true;
        }
    }

    private static boolean shouldShowDetailsHeroCorpusFill(Document doc, boolean isWideLayout) {
        if (isWideLayout && getDetailsHeroGraphic(doc, isWideLayout) == null) {
            return true;
        }
        return false;
    }

    public static int getDetailsHeroSpacerHeight(Context context, Document doc, boolean useWideLayout) {
        Resources res = context.getResources();
        boolean heroShowsCorpusFill = shouldShowDetailsHeroCorpusFill(doc, useWideLayout);
        if (!shouldShowDetailsHeroGraphic(doc, useWideLayout)) {
            return FinskyHeaderListLayout.getMinimumHeaderHeight(context, 2, 0);
        }
        int result = getSpacerHeight(context, res.getDisplayMetrics().widthPixels, true, getDetailsHeroAspectRatio(doc, useWideLayout));
        if (heroShowsCorpusFill) {
            result /= 2;
        }
        if (useWideLayout || !DetailsSummaryViewBinder.supportsThumbnailPeekingOnNonWideLayout(doc.getDocumentType())) {
            return result;
        }
        return result - res.getDimensionPixelSize(R.dimen.summary_thumbnail_peek);
    }

    public static Image getDetailsHeroGraphic(Document doc, boolean isWideLayout) {
        Document docForHero;
        boolean isAlbumOnWideLayout = isWideLayout && doc.getDocumentType() == 2;
        if (isAlbumOnWideLayout) {
            docForHero = doc.getCreatorDoc();
        } else {
            docForHero = doc;
        }
        if (docForHero == null) {
            return null;
        }
        return getHeroGraphic(docForHero);
    }

    public static boolean shouldPreferVideosAppForPlayback(Document doc) {
        return doc.getDocumentType() == 6;
    }

    public static Image getHeroGraphic(Document doc) {
        switch (doc.getDocumentType()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return getHeroGraphic(doc, 2, 0);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return getHeroGraphic(doc, 4, 0);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return getHeroGraphic(doc, 4, 0);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return null;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return getHeroGraphic(doc, 0, 13);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomGestures /*12*/:
                return getHeroGraphic(doc, 1, -1);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                return getHeroGraphic(doc, 14, -1);
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                return getHeroGraphic(doc, 14, -1);
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                return getHeroGraphic(doc, 2, 4);
            default:
                return getHeroGraphic(doc, 4, 0);
        }
    }

    private static Image getHeroGraphic(Document doc, int preferredHeroType, int fallbackHeroType) {
        boolean hasFallbackHero = true;
        if (doc == null) {
            return null;
        }
        boolean hasPreferredHero;
        List<Image> preferredHeroImages = doc.getImages(preferredHeroType);
        List<Image> fallbackHeroImages = doc.getImages(fallbackHeroType);
        if (preferredHeroImages == null || preferredHeroImages.isEmpty()) {
            hasPreferredHero = false;
        } else {
            hasPreferredHero = true;
        }
        if (fallbackHeroImages == null || fallbackHeroImages.isEmpty()) {
            hasFallbackHero = false;
        }
        if (hasPreferredHero || hasFallbackHero) {
            return hasPreferredHero ? (Image) preferredHeroImages.get(0) : (Image) fallbackHeroImages.get(0);
        } else {
            return null;
        }
    }
}
