package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.BucketRow;
import com.google.android.finsky.layout.IdentifiableLinearLayout;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardOrderedClusterView extends IdentifiableLinearLayout implements PlayStoreUiElementNode {
    private int mCardContentHorizontalPadding;
    private int mColumnCount;
    protected LinearLayout mContent;
    protected PlayCardClusterViewHeader mHeader;
    private PlayStoreUiElementNode mParentNode;
    private int mRowCount;
    private PlayStoreUiElement mUiElementProto;

    public PlayCardOrderedClusterView(Context context) {
        this(context, null);
    }

    public PlayCardOrderedClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(400);
        this.mParentNode = null;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContent = (LinearLayout) findViewById(R.id.cluster_content);
        this.mHeader = (PlayCardClusterViewHeader) findViewById(R.id.cluster_header);
    }

    public void inflateGrid(int rowCount, int columnCount, int cardContentHorizontalPadding) {
        this.mRowCount = rowCount;
        this.mColumnCount = columnCount;
        this.mCardContentHorizontalPadding = cardContentHorizontalPadding;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int row = 0; row < this.mRowCount; row++) {
            BucketRow listingCardsRow = (BucketRow) inflater.inflate(R.layout.bucket_row, null, false);
            listingCardsRow.setContentHorizontalPadding(cardContentHorizontalPadding);
            for (int i = 0; i < this.mColumnCount; i++) {
                View listingCard = inflater.inflate(R.layout.play_card_listing, listingCardsRow, false);
                listingCard.setVisibility(8);
                listingCardsRow.addView(listingCard);
            }
            this.mContent.addView(listingCardsRow);
        }
    }

    private int getNumDisplayedRows(int numChildren) {
        return Math.min((int) Math.ceil(((double) numChildren) / ((double) this.mColumnCount)), this.mRowCount);
    }

    private int getNumDisplayedColumns(int numChildren, int row) {
        if (row >= getNumDisplayedRows(numChildren)) {
            return 0;
        }
        return Math.min(numChildren - (this.mColumnCount * row), this.mColumnCount);
    }

    public void bind(Document clusterDoc, Document[] children, BitmapLoader bitmapLoader, NavigationManager navigationManager, OnClickListener headerClickListener, PlayStoreUiElementNode parentNode) {
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, clusterDoc.getServerLogsCookie());
        this.mParentNode = parentNode;
        bindEntries(clusterDoc, children, bitmapLoader, navigationManager);
        bindHeader((int) clusterDoc.getContainerAnnotation().estimatedResults, clusterDoc.getBackend(), clusterDoc.getTitle(), headerClickListener);
    }

    private void bindEntries(Document clusterDoc, Document[] children, BitmapLoader bitmapLoader, NavigationManager navigationManager) {
        int numChildren = children.length;
        for (int row = 0; row < this.mRowCount; row++) {
            ViewGroup rowOfDocuments = (ViewGroup) this.mContent.getChildAt(row);
            if (row >= getNumDisplayedRows(numChildren)) {
                rowOfDocuments.setVisibility(8);
            } else {
                rowOfDocuments.setVisibility(0);
                int displayedColumns = getNumDisplayedColumns(numChildren, row);
                for (int column = 0; column < this.mColumnCount; column++) {
                    PlayCardViewBase cardView = (PlayCardViewBase) rowOfDocuments.getChildAt(column);
                    if (column < displayedColumns) {
                        PlayCardUtils.bindCard(cardView, children[(this.mColumnCount * row) + column], clusterDoc.getDocId(), bitmapLoader, navigationManager, this);
                        cardView.setVisibility(0);
                    } else {
                        cardView.setVisibility(4);
                    }
                }
            }
        }
    }

    private void bindHeader(int estimatedResults, int backendId, String title, OnClickListener headerClickListener) {
        String more = null;
        if (estimatedResults > 0) {
            more = String.format(getResources().getString(R.string.more_results, new Object[]{Integer.valueOf(estimatedResults)}), new Object[]{Integer.valueOf(estimatedResults)});
        } else if (headerClickListener != null) {
            more = getResources().getString(R.string.more_results_no_count);
        }
        this.mHeader.setContent(backendId, title, null, more, headerClickListener);
        this.mHeader.setExtraHorizontalPadding(this.mCardContentHorizontalPadding);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
