package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.android.vending.R;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata.CardMetadata;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata.ClusterTileMetadata;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.layout.PlayCardViewBase;
import java.util.List;

public class PlayCardClusterViewContent extends ViewGroup implements Recyclable {
    private BitmapLoader mBitmapLoader;
    protected int mCardContentHorizontalPadding;
    protected int mCardContentPaddingBottom;
    protected int mCardContentPaddingTop;
    private final int mCardInset;
    protected ClientMutationCache mClientMutationCache;
    private Document mClusterDocumentData;
    private Document mClusterLoggingDocument;
    private LayoutInflater mInflater;
    private List<Document> mLooseDocumentsData;
    protected PlayCardClusterMetadata mMetadata;
    private NavigationManager mNavigationManager;
    private String mParentId;
    private PlayStoreUiElementNode mParentNode;
    private final int mSmallCardContentMinHeight;

    public PlayCardClusterViewContent(Context context) {
        this(context, null);
    }

    public PlayCardClusterViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mInflater = LayoutInflater.from(context);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlayCardClusterViewContent);
        this.mCardContentPaddingTop = viewAttrs.getDimensionPixelSize(0, 0);
        this.mCardContentPaddingBottom = viewAttrs.getDimensionPixelSize(1, 0);
        viewAttrs.recycle();
        Resources res = context.getResources();
        this.mSmallCardContentMinHeight = res.getDimensionPixelSize(R.dimen.play_small_card_content_min_height);
        this.mCardInset = res.getDimensionPixelSize(R.dimen.play_card_default_inset);
    }

    public void setClusterDocumentData(Document clusterDoc) {
        if (this.mLooseDocumentsData != null) {
            throw new IllegalStateException("Already initialized with loose documents");
        }
        this.mClusterDocumentData = clusterDoc;
        this.mParentId = this.mClusterDocumentData.getDocId();
    }

    public void setLooseDocumentsData(List<Document> looseDocs, String parentId) {
        if (this.mClusterDocumentData != null) {
            throw new IllegalStateException("Already initialized with cluster document");
        }
        this.mLooseDocumentsData = looseDocs;
        this.mParentId = parentId;
    }

    public void onRecycle() {
        this.mClusterDocumentData = null;
        this.mLooseDocumentsData = null;
    }

    public Document getClusterLoggingDocument() {
        if (this.mClusterLoggingDocument == null) {
            return this.mClusterDocumentData;
        }
        return this.mClusterLoggingDocument;
    }

    public void setClusterLoggingDocument(Document document) {
        this.mClusterLoggingDocument = document;
    }

    public void setCardContentVerticalPadding(int mCardContentVerticalPadding) {
        this.mCardContentPaddingTop = mCardContentVerticalPadding;
        this.mCardContentPaddingBottom = mCardContentVerticalPadding;
        requestLayout();
    }

    public void setCardContentHorizontalPadding(int cardContentHorizontalPadding) {
        if (this.mCardContentHorizontalPadding != cardContentHorizontalPadding) {
            this.mCardContentHorizontalPadding = cardContentHorizontalPadding;
            requestLayout();
        }
    }

    public int getCardContentHorizontalPadding() {
        return this.mCardContentHorizontalPadding;
    }

    public void setMetadata(PlayCardClusterMetadata metadata, ClientMutationCache clientMutationCache) {
        this.mMetadata = metadata;
        this.mClientMutationCache = clientMutationCache;
    }

    protected final int getDocCount() {
        return this.mClusterDocumentData != null ? this.mClusterDocumentData.getChildCount() : this.mLooseDocumentsData.size();
    }

    protected final Document getDoc(int index) {
        if (index < 0 || index >= getDocCount()) {
            return null;
        }
        return this.mClusterDocumentData != null ? this.mClusterDocumentData.getChildAt(index) : (Document) this.mLooseDocumentsData.get(index);
    }

    public PlayCardClusterMetadata getMetadata() {
        return this.mMetadata;
    }

    private PlayCardViewBase getCardFromHeap(CardMetadata cardMetadata, PlayCardHeap cardHeap) {
        PlayCardViewBase card = cardHeap.getCard(cardMetadata, this.mInflater);
        card.setThumbnailAspectRatio(cardMetadata.getThumbnailAspectRatio());
        return card;
    }

    protected int getIndexOfFirstCard() {
        return 0;
    }

    public void removeAllCards() {
        int indexOfFirstCard = getIndexOfFirstCard();
        if (indexOfFirstCard == 0) {
            removeAllViews();
            return;
        }
        while (getChildCount() > indexOfFirstCard) {
            removeViewAt(indexOfFirstCard);
        }
    }

    public int getCardChildIndex(View card) {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) == card) {
                return i - getIndexOfFirstCard();
            }
        }
        return -1;
    }

    public PlayCardViewBase getCardChildAt(int cardIndex) {
        return (PlayCardViewBase) getChildAt(getIndexOfFirstCard() + cardIndex);
    }

    public int getCardChildCount() {
        return getChildCount() - getIndexOfFirstCard();
    }

    public void createContent(DfeApi dfeApi, NavigationManager navigationManager, BitmapLoader bitmapLoader, PlayCardDismissListener dismissListener, PlayCardHeap cardHeap, PlayStoreUiElementNode parentNode) {
        this.mNavigationManager = navigationManager;
        this.mBitmapLoader = bitmapLoader;
        this.mParentNode = parentNode;
        int tilesToBind = getNumberOfTilesToBind();
        int tileIndex = 0;
        while (tileIndex < this.mMetadata.getTileCount()) {
            PlayCardViewBase card = getCardFromHeap(this.mMetadata.getTileMetadata(tileIndex).getCardMetadata(), cardHeap);
            bindCardContent(card, tileIndex, tileIndex < tilesToBind ? tileIndexToDocumentIndex(tileIndex) : -1, dismissListener);
            addView(card);
            tileIndex++;
        }
    }

    protected int getNumberOfTilesToBind() {
        return this.mMetadata.getTileCount();
    }

    protected int tileIndexToDocumentIndex(int tileIndex) {
        return tileIndex;
    }

    public void hideCardAt(int tileIndex) {
        final PlayCardViewBase card = getCardChildAt(tileIndex);
        card.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getContext(), 250, new AnimationListenerAdapter() {
            public void onAnimationEnd(Animation animation) {
                card.clearCardState();
            }
        }));
    }

    public void bindCardAt(int tileIndex, int docIndex, PlayCardDismissListener dismissListener) {
        bindCardContent(getCardChildAt(tileIndex), tileIndex, docIndex, dismissListener);
    }

    public void bindCardContent(PlayCardViewBase card, int tileIndex, int docIndex, PlayCardDismissListener dismissListener) {
        Document doc = getDoc(docIndex);
        if (doc == null) {
            card.clearCardState();
            return;
        }
        PlayCardDismissListener playCardDismissListener;
        boolean docHasReasons = doc.hasReasons();
        card.setThumbnailAspectRatio(this.mMetadata.shouldRespectChildThumbnailAspectRatio(tileIndex) ? PlayCardClusterMetadata.getAspectRatio(doc.getDocumentType()) : this.mMetadata.getTileMetadata(tileIndex).getCardMetadata().getThumbnailAspectRatio());
        ((FifeImageView) card.getThumbnail().getImageView()).freezeImage();
        boolean isDismissable = docHasReasons;
        boolean isDismissed = isDismissable && dismissListener != null && this.mClientMutationCache.isDismissedRecommendation(doc.getDocId());
        String str = this.mParentId;
        BitmapLoader bitmapLoader = this.mBitmapLoader;
        NavigationManager navigationManager = this.mNavigationManager;
        if (isDismissable) {
            playCardDismissListener = dismissListener;
        } else {
            playCardDismissListener = null;
        }
        PlayCardUtils.bindCard(card, doc, str, bitmapLoader, navigationManager, isDismissed, playCardDismissListener, this.mParentNode, false, -1);
    }

    public int getLeadingGap(int availableWidth) {
        return getPaddingLeft() + ((int) (((float) this.mMetadata.getLeadingGap()) * getCellSize(availableWidth)));
    }

    public int getTrailingGap(int availableWidth) {
        return getPaddingRight() + ((int) (((float) this.mMetadata.getTrailingGap()) * getCellSize(availableWidth)));
    }

    protected float getCellSize(int availableWidth) {
        return (((float) availableWidth) - ((float) (this.mCardContentHorizontalPadding * 2))) / ((float) this.mMetadata.getWidth());
    }

    private float getCellHeight(float cellSize) {
        CardMetadata cardMetadataForMinCellHeight = this.mMetadata.getCardMetadataForMinCellHeight();
        if (cardMetadataForMinCellHeight == null) {
            return cellSize;
        }
        int cardHSpan = cardMetadataForMinCellHeight.getHSpan();
        return ((((float) this.mSmallCardContentMinHeight) + (((((float) cardHSpan) * cellSize) - ((float) (this.mCardInset * 2))) * cardMetadataForMinCellHeight.getThumbnailAspectRatio())) + ((float) (this.mCardInset * 2))) / ((float) cardMetadataForMinCellHeight.getVSpan());
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int rows = this.mMetadata.getHeight();
        float cellSize = getCellSize(availableWidth);
        float cellHeight = getCellHeight(cellSize);
        boolean respectChildHeight = this.mMetadata.shouldRespectChildHeight();
        int tileCount = this.mMetadata.getTileCount();
        int childOffset = getIndexOfFirstCard();
        int childMaxHeight = 0;
        int visibleCardCount = 0;
        for (int tileIndex = 0; tileIndex < tileCount; tileIndex++) {
            ClusterTileMetadata tileMetadata = this.mMetadata.getTileMetadata(tileIndex);
            int cardHSpan = tileMetadata.getCardMetadata().getHSpan();
            int cardVSpan = tileMetadata.getCardMetadata().getVSpan();
            View card = getChildAt(childOffset + tileIndex);
            int cardWidth = (int) (((float) cardHSpan) * cellSize);
            int cardHeight = (int) (((float) cardVSpan) * cellHeight);
            int childWidthSpec = MeasureSpec.makeMeasureSpec(cardWidth, 1073741824);
            if (respectChildHeight) {
                card.measure(childWidthSpec, 0);
                childMaxHeight = Math.max(childMaxHeight, card.getMeasuredHeight());
            } else {
                card.measure(childWidthSpec, MeasureSpec.makeMeasureSpec(cardHeight, 1073741824));
            }
            if (card.getVisibility() == 0) {
                visibleCardCount++;
            }
        }
        if (visibleCardCount == 0) {
            setMeasuredDimension(availableWidth, 0);
            return;
        }
        int targetHeight = this.mCardContentPaddingTop + this.mCardContentPaddingBottom;
        if (respectChildHeight) {
            targetHeight += childMaxHeight;
        } else {
            targetHeight += (int) (((float) rows) * cellHeight);
        }
        setMeasuredDimension(availableWidth, targetHeight);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = getHeight();
        float cellSize = getCellSize(getWidth());
        float cellHeight = getCellHeight(cellSize);
        int paddingLeft = this.mCardContentHorizontalPadding;
        int tileCount = this.mMetadata.getTileCount();
        int clusterRowCount = this.mMetadata.getHeight();
        int childOffset = getIndexOfFirstCard();
        int extraColumnOffset = getExtraColumnOffset();
        for (int tileIndex = 0; tileIndex < tileCount; tileIndex++) {
            ClusterTileMetadata tileMetadata = this.mMetadata.getTileMetadata(tileIndex);
            int cardXStart = tileMetadata.getXStart() + extraColumnOffset;
            int cardYStart = tileMetadata.getYStart();
            PlayCardViewBase card = (PlayCardViewBase) getChildAt(childOffset + tileIndex);
            int cardLeft = paddingLeft + ((int) (((float) cardXStart) * cellSize));
            int cardBottom = height - this.mCardContentPaddingBottom;
            if (!this.mMetadata.shouldRespectChildHeight()) {
                cardBottom -= (int) (((float) (clusterRowCount - (cardYStart + tileMetadata.getCardMetadata().getVSpan()))) * cellHeight);
            }
            card.layout(cardLeft, cardBottom - card.getMeasuredHeight(), card.getMeasuredWidth() + cardLeft, cardBottom);
            ((FifeImageView) card.getThumbnail().getImageView()).unfreezeImage(true);
        }
    }

    protected int getExtraColumnOffset() {
        int tileCount = this.mMetadata.getTileCount();
        int childOffset = getIndexOfFirstCard();
        if (!this.mMetadata.shouldAlignToParentEndIfNecessary()) {
            return 0;
        }
        int templateColumnCount = this.mMetadata.getWidth();
        int rightmostOccupiedColumn = 0;
        for (int tileIndex = 0; tileIndex < tileCount; tileIndex++) {
            if (getChildAt(childOffset + tileIndex).getVisibility() != 4) {
                ClusterTileMetadata tileMetadata = this.mMetadata.getTileMetadata(tileIndex);
                rightmostOccupiedColumn = Math.max(rightmostOccupiedColumn, tileMetadata.getXStart() + tileMetadata.getCardMetadata().getHSpan());
            }
        }
        if (rightmostOccupiedColumn == 0) {
            return templateColumnCount - this.mMetadata.getLeadingGap();
        }
        return templateColumnCount - rightmostOccupiedColumn;
    }
}
