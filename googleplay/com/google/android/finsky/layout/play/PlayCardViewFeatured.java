package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.PaletteAsyncListener;
import android.support.v7.graphics.Palette.Swatch;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView.ScaleType;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DetailsTextIconContainer;
import com.google.android.finsky.layout.DetailsTextSection.DetailsExtraPrimary;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.protos.DocDetails.ProductDetailsDescription;
import com.google.android.finsky.protos.DocDetails.ProductDetailsSection;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;
import com.google.android.play.layout.PlayActionButton;
import com.google.android.play.layout.PlayCardViewBase;
import com.google.android.play.layout.StarRatingBar;
import com.google.android.play.utils.collections.Lists;
import java.util.List;

public class PlayCardViewFeatured extends PlayCardViewBase {
    private PlayActionButton mActionButton;
    private int mBackgroundColor;
    private FifeImageView mBackgroundImage;
    private boolean mHasBackgroundImage;
    private DetailsTextIconContainer mIconContainer;
    private List<DetailsExtraPrimary> mIconList;
    private int mLayoutType;
    private View mMiniBottomRow;
    private View mSmallTitleRow;
    private StarRatingBar mStarRatingBar;

    public PlayCardViewFeatured(Context context) {
        this(context, null);
    }

    public PlayCardViewFeatured(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mThumbnailAspectRatio = 1.0f;
        this.mHasBackgroundImage = false;
        this.mBackgroundColor = context.getResources().getColor(R.color.play_fg_primary);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlayCardViewFeatured);
        this.mLayoutType = viewAttrs.getInt(0, 1);
        viewAttrs.recycle();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBackgroundImage = (FifeImageView) findViewById(R.id.featured_background);
        this.mStarRatingBar = (StarRatingBar) findViewById(R.id.star_rating_bar);
        this.mIconContainer = (DetailsTextIconContainer) findViewById(R.id.featured_icon_container);
        this.mActionButton = (PlayActionButton) findViewById(R.id.li_primary_action_button);
        this.mSmallTitleRow = findViewById(R.id.featured_small_title_row);
        this.mMiniBottomRow = findViewById(R.id.mini_featured_bottom_row);
    }

    public int getCardType() {
        return 15;
    }

    public void bindLoading() {
        super.bindLoading();
        if (this.mBackgroundImage != null) {
            this.mBackgroundImage.setVisibility(8);
        }
        if (this.mStarRatingBar != null) {
            this.mStarRatingBar.setVisibility(8);
        }
        if (this.mIconContainer != null) {
            this.mIconContainer.setVisibility(8);
        }
        if (this.mActionButton != null) {
            this.mActionButton.setVisibility(8);
        }
    }

    public void bind(Document doc, BitmapLoader bitmapLoader, boolean hasDetailsDataLoaded) {
        if (hasDetailsDataLoaded) {
            setVisibility(0);
            setIcons(doc);
            setBackgroundImage(doc, bitmapLoader);
            setThumbnail(doc, bitmapLoader);
            this.mTitle.setText(doc.getTitle());
            if (doc.hasRating()) {
                this.mStarRatingBar.setRating(doc.getStarRating());
                this.mStarRatingBar.setVisibility(0);
            } else {
                this.mStarRatingBar.setVisibility(8);
            }
            String description = doc.getPromotionalDescription();
            if (this.mDescription == null) {
                return;
            }
            if (TextUtils.isEmpty(description)) {
                this.mDescription.setVisibility(8);
                return;
            }
            this.mDescription.setMaxLines(4);
            this.mDescription.setText(description);
            this.mDescription.setVisibility(0);
            return;
        }
        setVisibility(8);
    }

    private void setThumbnail(Document doc, BitmapLoader bitmapLoader) {
        DocImageView thumbnailImage = (DocImageView) this.mThumbnail.getImageView();
        thumbnailImage.setScaleType(ScaleType.FIT_START);
        thumbnailImage.bind(doc, bitmapLoader, PlayCardImageTypeSequence.CORE_IMAGE_TYPES);
        thumbnailImage.setContentDescription(CorpusResourceUtils.getItemThumbnailContentDescription(doc, getContext().getResources()));
        if (this.mLayoutType != 2) {
            thumbnailImage.setOnLoadedListener(new OnLoadedListener() {
                public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
                    Palette.generateAsync(bitmap, new PaletteAsyncListener() {
                        public void onGenerated(Palette palette) {
                            Swatch swatch = palette.getDarkVibrantSwatch();
                            if (swatch != null) {
                                PlayCardViewFeatured.this.mBackgroundColor = swatch.getRgb();
                                PlayCardViewFeatured.this.setCardColor();
                            }
                        }
                    });
                }

                public void onLoadedAndFadedIn(FifeImageView imageView) {
                }
            });
        }
    }

    private void setCardColor() {
        if (this.mLayoutType != 2) {
            int red = Color.red(this.mBackgroundColor);
            int green = Color.green(this.mBackgroundColor);
            int blue = Color.blue(this.mBackgroundColor);
            if (this.mHasBackgroundImage) {
                this.mBackgroundImage.setColorFilter(Color.argb(190, red, green, blue));
            } else {
                this.mBackgroundImage.setBackgroundColor(this.mBackgroundColor);
            }
        }
    }

    private void setBackgroundImage(Document doc, BitmapLoader bitmapLoader) {
        boolean hasPreferredHero;
        List<Image> preferredHeroImages = doc.getImages(2);
        List<Image> fallbackHeroImages = doc.getImages(13);
        if (preferredHeroImages == null || preferredHeroImages.isEmpty()) {
            hasPreferredHero = false;
        } else {
            hasPreferredHero = true;
        }
        boolean hasFallbackHero;
        if (fallbackHeroImages == null || fallbackHeroImages.isEmpty()) {
            hasFallbackHero = false;
        } else {
            hasFallbackHero = true;
        }
        if (hasPreferredHero || hasFallbackHero) {
            setCardColor();
            this.mHasBackgroundImage = true;
            Image backgroundImage = hasPreferredHero ? (Image) preferredHeroImages.get(0) : (Image) fallbackHeroImages.get(0);
            this.mBackgroundImage.setImage(backgroundImage.imageUrl, backgroundImage.supportsFifeUrlOptions, bitmapLoader);
        } else {
            this.mHasBackgroundImage = false;
        }
        setCardColor();
    }

    private void setIcons(Document doc) {
        fillIconList(doc);
        List<Pair<Image, String>> extraIconPairs = Lists.newArrayList();
        if (this.mIconList != null) {
            for (DetailsExtraPrimary primary : this.mIconList) {
                if (!(primary.skipInCollapsedMode || primary.image == null)) {
                    extraIconPairs.add(new Pair(primary.image, primary.title));
                }
            }
        }
        Resources res = getContext().getResources();
        this.mIconContainer.populate(extraIconPairs, FinskyApp.get().getBitmapLoader(), this.mLayoutType == 2 ? res.getColor(R.color.black) : res.getColor(R.color.play_white));
    }

    private void fillIconList(Document doc) {
        this.mIconList = Lists.newArrayList();
        if (doc.hasBadgeContainer()) {
            for (Badge badge : doc.getBadgeContainer().badge) {
                this.mIconList.add(new DetailsExtraPrimary(badge.title, badge.description, badge.browseUrl, BadgeUtils.getImage(badge, 6), false));
            }
        }
        if (doc.hasProductDetails()) {
            for (ProductDetailsSection section : doc.getProductDetails().section) {
                for (ProductDetailsDescription description : section.description) {
                    this.mIconList.add(new DetailsExtraPrimary(section.title, description.description, null, description.image, false));
                }
            }
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int availableHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        MarginLayoutParams actionButtonLp = (MarginLayoutParams) this.mActionButton.getLayoutParams();
        switch (this.mLayoutType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                height = ((((this.mSmallTitleRow.getMeasuredHeight() + this.mBackgroundImage.getMeasuredHeight()) + this.mDescription.getMeasuredHeight()) + this.mIconContainer.getMeasuredHeight()) + this.mActionButton.getMeasuredHeight()) + actionButtonLp.bottomMargin;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                height = this.mSmallTitleRow.getMeasuredHeight() + this.mMiniBottomRow.getMeasuredHeight();
                break;
            default:
                height = (((((this.mThumbnail.getMeasuredHeight() + this.mTitle.getMeasuredHeight()) + this.mStarRatingBar.getMeasuredHeight()) + this.mDescription.getMeasuredHeight()) + this.mIconContainer.getMeasuredHeight()) + this.mActionButton.getMeasuredHeight()) + actionButtonLp.bottomMargin;
                break;
        }
        if (heightMode == 1073741824) {
            height = availableHeight;
        } else if (heightMode == Integer.MIN_VALUE) {
            height = Math.min(height, availableHeight);
        }
        if (this.mLayoutType != 2) {
            this.mBackgroundImage.measure(MeasureSpec.makeMeasureSpec((availableWidth - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec(height, 1073741824));
        }
        setMeasuredDimension(availableWidth, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.mBackgroundImage.layout(getPaddingLeft(), getPaddingTop(), this.mBackgroundImage.getMeasuredWidth(), this.mBackgroundImage.getMeasuredHeight());
    }
}
