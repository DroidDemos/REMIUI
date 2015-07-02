package com.google.android.finsky.layout.play;

import com.android.vending.R;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class PlayCardClusterMetadata {
    public static final CardMetadata CARD_LARGE;
    public static final CardMetadata CARD_LARGEMINUS_16x9;
    public static final CardMetadata CARD_LARGE_16x9;
    public static final CardMetadata CARD_MEDIUM;
    public static final CardMetadata CARD_MEDIUMPLUS;
    public static final CardMetadata CARD_MEDIUMPLUS_16x9;
    public static final CardMetadata CARD_MEDIUM_16x9;
    public static final CardMetadata CARD_MINI;
    public static final CardMetadata CARD_MINI_16x9;
    public static final CardMetadata CARD_PERSON;
    public static final CardMetadata CARD_SMALL;
    public static final CardMetadata CARD_SMALL_16x9;
    private boolean mAlignToParentEndIfNecessary;
    private CardMetadata mCardMetadataForMinCellHeight;
    private int mHeight;
    private int mLeadingGap;
    private boolean mRespectChildHeight;
    private boolean mRespectChildThumbnailAspectRatio;
    private List<ClusterTileMetadata> mTiles;
    private int mViewportWidth;
    private int mWidth;

    public static class CardMetadata {
        private final int mHSpan;
        private final int mLayoutId;
        private final float mThumbnailAspectRatio;
        private final int mVSpan;

        public CardMetadata(int layoutId, int hSpan, int vSpan, float thumbnailAspectRatio) {
            this.mLayoutId = layoutId;
            this.mHSpan = hSpan;
            this.mVSpan = vSpan;
            this.mThumbnailAspectRatio = thumbnailAspectRatio;
        }

        public int getLayoutId() {
            return this.mLayoutId;
        }

        public int getHSpan() {
            return this.mHSpan;
        }

        public int getVSpan() {
            return this.mVSpan;
        }

        public float getThumbnailAspectRatio() {
            return this.mThumbnailAspectRatio;
        }
    }

    public static class ClusterTileMetadata {
        private final CardMetadata mCardMetadata;
        private final boolean mRespectChildThumbnailAspectRatio;
        private final int mXStart;
        private final int mYStart;

        public ClusterTileMetadata(CardMetadata cardMetadata, int xStart, int yStart, boolean respectChildThumbnailAspectRatio) {
            this.mCardMetadata = cardMetadata;
            this.mXStart = xStart;
            this.mYStart = yStart;
            this.mRespectChildThumbnailAspectRatio = respectChildThumbnailAspectRatio;
        }

        public CardMetadata getCardMetadata() {
            return this.mCardMetadata;
        }

        public int getXStart() {
            return this.mXStart;
        }

        public int getYStart() {
            return this.mYStart;
        }
    }

    static {
        CARD_MINI = new CardMetadata(R.layout.play_card_mini, 2, 3, 1.0f);
        CARD_SMALL = new CardMetadata(R.layout.play_card_small, 2, 3, 1.0f);
        CARD_MEDIUM = new CardMetadata(R.layout.play_card_medium, 4, 2, 1.0f);
        CARD_MEDIUMPLUS = new CardMetadata(R.layout.play_card_medium_plus, 4, 3, 1.0f);
        CARD_LARGE = new CardMetadata(R.layout.play_card_large, 4, 6, 1.0f);
        CARD_MINI_16x9 = new CardMetadata(R.layout.play_card_mini, 2, 4, 1.441f);
        CARD_SMALL_16x9 = new CardMetadata(R.layout.play_card_small, 2, 4, 1.441f);
        CARD_MEDIUM_16x9 = new CardMetadata(R.layout.play_card_medium, 4, 2, 1.441f);
        CARD_MEDIUMPLUS_16x9 = new CardMetadata(R.layout.play_card_medium_plus, 4, 4, 1.441f);
        CARD_LARGEMINUS_16x9 = new CardMetadata(R.layout.play_card_large_minus, 6, 4, 1.441f);
        CARD_LARGE_16x9 = new CardMetadata(R.layout.play_card_large, 4, 8, 1.441f);
        CARD_PERSON = new CardMetadata(R.layout.play_card_person, 2, 3, 1.0f);
    }

    public PlayCardClusterMetadata(int width, int height) {
        this.mTiles = Lists.newArrayList();
        this.mWidth = width;
        this.mHeight = height;
        this.mViewportWidth = width;
    }

    public PlayCardClusterMetadata addTile(CardMetadata cardMetadata, int xStart, int yStart) {
        this.mTiles.add(new ClusterTileMetadata(cardMetadata, xStart, yStart, false));
        return this;
    }

    public PlayCardClusterMetadata addFlexiTile(CardMetadata cardMetadata, int xStart, int yStart) {
        this.mTiles.add(new ClusterTileMetadata(cardMetadata, xStart, yStart, true));
        return this;
    }

    public PlayCardClusterMetadata setRespectChildHeight() {
        this.mRespectChildHeight = true;
        return this;
    }

    public PlayCardClusterMetadata setRespectChildThumbnailAspectRatio() {
        this.mRespectChildThumbnailAspectRatio = true;
        return this;
    }

    public PlayCardClusterMetadata setCardMetadataForMinCellHeight(CardMetadata cardMetadataForMinCellHeight) {
        this.mCardMetadataForMinCellHeight = cardMetadataForMinCellHeight;
        return this;
    }

    public PlayCardClusterMetadata withLeadingGap(int leadingGap) {
        this.mLeadingGap = leadingGap;
        return this;
    }

    public PlayCardClusterMetadata setAlignToParentEndIfNecessary() {
        this.mAlignToParentEndIfNecessary = true;
        return this;
    }

    public PlayCardClusterMetadata withViewportWidth(int viewportWidth) {
        this.mViewportWidth = viewportWidth;
        return this;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getViewportWidth() {
        return this.mViewportWidth;
    }

    public int getTileCount() {
        return this.mTiles.size();
    }

    public ClusterTileMetadata getTileMetadata(int tileIndex) {
        return (ClusterTileMetadata) this.mTiles.get(tileIndex);
    }

    public int getLeadingGap() {
        return this.mLeadingGap;
    }

    public int getTrailingGap() {
        return 0;
    }

    public boolean shouldRespectChildHeight() {
        return this.mRespectChildHeight;
    }

    public boolean shouldRespectChildThumbnailAspectRatio(int tileIndex) {
        return this.mRespectChildThumbnailAspectRatio || ((ClusterTileMetadata) this.mTiles.get(tileIndex)).mRespectChildThumbnailAspectRatio;
    }

    public CardMetadata getCardMetadataForMinCellHeight() {
        return this.mCardMetadataForMinCellHeight;
    }

    public boolean shouldAlignToParentEndIfNecessary() {
        return this.mAlignToParentEndIfNecessary;
    }

    public static float getAspectRatio(int documentType) {
        switch (documentType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                return 1.0f;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 0.5f;
            default:
                return 1.441f;
        }
    }
}
