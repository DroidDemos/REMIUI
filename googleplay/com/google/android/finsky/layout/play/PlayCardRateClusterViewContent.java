package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.play.image.BitmapLoader;

public class PlayCardRateClusterViewContent extends PlayCardClusterViewContent {
    private int[] mTileIndexToDocumentIndexMapping;

    public PlayCardRateClusterViewContent(Context context) {
        this(context, null);
    }

    public PlayCardRateClusterViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void syncIndexMapping() {
        int docCount = getDocCount();
        if (docCount != 0 && this.mMetadata != null) {
            int tileCount = this.mMetadata.getTileCount();
            if (this.mTileIndexToDocumentIndexMapping == null) {
                this.mTileIndexToDocumentIndexMapping = new int[tileCount];
            }
            for (int i = 0; i < tileCount; i++) {
                this.mTileIndexToDocumentIndexMapping[i] = -1;
            }
            int currDocIndex = 0;
            int currTileIndex = 0;
            while (currDocIndex < docCount && currTileIndex < tileCount) {
                String childDocId = getDoc(currDocIndex).getDocId();
                boolean wasReviewed = this.mClientMutationCache.getCachedReview(childDocId, null) != null;
                boolean wasSkipped = this.mClientMutationCache.isDismissedRecommendation(childDocId);
                if (wasReviewed || wasSkipped) {
                    currDocIndex++;
                } else {
                    int currTileIndex2 = currTileIndex + 1;
                    int currDocIndex2 = currDocIndex + 1;
                    this.mTileIndexToDocumentIndexMapping[currTileIndex] = currDocIndex;
                    currDocIndex = currDocIndex2;
                    currTileIndex = currTileIndex2;
                }
            }
        }
    }

    public void createContent(DfeApi dfeApi, NavigationManager navigationManager, BitmapLoader bitmapLoader, PlayCardDismissListener dismissListener, PlayCardHeap cardHeap, PlayStoreUiElementNode parentNode) {
        syncIndexMapping();
        super.createContent(dfeApi, navigationManager, bitmapLoader, dismissListener, cardHeap, parentNode);
    }

    public boolean hasItemsToRate() {
        return this.mTileIndexToDocumentIndexMapping[0] >= 0;
    }

    protected int tileIndexToDocumentIndex(int tileIndex) {
        return this.mTileIndexToDocumentIndexMapping[tileIndex];
    }
}
