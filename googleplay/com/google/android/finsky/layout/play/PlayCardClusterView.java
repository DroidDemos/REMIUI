package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import com.android.vending.R;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardViewBase;
import java.util.List;

public class PlayCardClusterView extends PlayClusterView {
    protected ClientMutationCache mClientMutationCache;
    protected PlayCardClusterViewContent mContent;
    protected DfeApi mDfeApi;
    protected PlayCardDismissListener mStreamDismissListener;

    public PlayCardClusterView(Context context) {
        this(context, null);
    }

    public PlayCardClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContent = (PlayCardClusterViewContent) findViewById(R.id.cluster_content);
    }

    public PlayCardClusterMetadata getMetadata() {
        return this.mContent.getMetadata();
    }

    public PlayCardViewBase getCardChildAt(int cardIndex) {
        return this.mContent.getCardChildAt(cardIndex);
    }

    public void removeAllCards() {
        this.mContent.removeAllCards();
    }

    public int getCardChildCount() {
        return this.mContent.getCardChildCount();
    }

    public boolean hasCards() {
        return getCardChildCount() > 0;
    }

    public PlayCardClusterView withClusterDocumentData(Document clusterDoc) {
        this.mContent.setClusterDocumentData(clusterDoc);
        return this;
    }

    public PlayCardClusterView withLooseDocumentsData(List<Document> looseDocs, String parentId) {
        this.mContent.setLooseDocumentsData(looseDocs, parentId);
        return this;
    }

    public void onRecycle() {
        super.onRecycle();
        this.mContent.onRecycle();
    }

    public void setCardContentHorizontalPadding(int cardContentHorizontalPadding) {
        this.mContent.setCardContentHorizontalPadding(cardContentHorizontalPadding);
    }

    public void createContent(PlayCardClusterMetadata metadata, ClientMutationCache clientMutationCache, DfeApi dfeApi, NavigationManager navigationManager, BitmapLoader bitmapLoader, PlayCardDismissListener dismissListener, PlayCardHeap cardHeap, PlayStoreUiElementNode parentNode) {
        this.mClientMutationCache = clientMutationCache;
        this.mDfeApi = dfeApi;
        this.mStreamDismissListener = dismissListener;
        this.mContent.setMetadata(metadata, clientMutationCache);
        Document clusterDoc = this.mContent.getClusterLoggingDocument();
        configureLogging(clusterDoc == null ? null : clusterDoc.getServerLogsCookie(), parentNode);
        cardHeap.recycle(this);
        this.mContent.createContent(dfeApi, navigationManager, bitmapLoader, dismissListener, cardHeap, getParentOfChildren());
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        if (!(this.mHeader == null || this.mHeader.getVisibility() == 8)) {
            this.mHeader.measure(widthMeasureSpec, 0);
            height += this.mHeader.getMeasuredHeight();
        }
        MarginLayoutParams contentLp = (MarginLayoutParams) this.mContent.getLayoutParams();
        this.mContent.measure(widthMeasureSpec, 0);
        setMeasuredDimension(width, height + ((contentLp.topMargin + this.mContent.getMeasuredHeight()) + contentLp.bottomMargin));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int y = getPaddingTop();
        if (!(this.mHeader == null || this.mHeader.getVisibility() == 8)) {
            this.mHeader.layout(0, y, width, this.mHeader.getMeasuredHeight() + y);
            y += this.mHeader.getMeasuredHeight();
        }
        y += ((MarginLayoutParams) this.mContent.getLayoutParams()).topMargin;
        this.mContent.layout(0, y, width, this.mContent.getMeasuredHeight() + y);
    }
}
