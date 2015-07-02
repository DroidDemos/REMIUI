package com.google.android.finsky.layout.play;

import com.google.android.finsky.layout.play.PlayCardClusterMetadata.CardMetadata;

public class PlayCardSingleCardClusterMetadata extends PlayCardClusterMetadata {
    private int mMerchImageHSpan;

    public PlayCardSingleCardClusterMetadata(int width, int height) {
        super(width, height);
    }

    public PlayCardSingleCardClusterMetadata withMerchImageHSpan(int merchImageHSpan) {
        this.mMerchImageHSpan = merchImageHSpan;
        return this;
    }

    public PlayCardSingleCardClusterMetadata addTile(CardMetadata cardMetadata, int xStart, int yStart) {
        super.addTile(cardMetadata, xStart, yStart);
        return this;
    }

    public int getTrailingGap() {
        return this.mMerchImageHSpan;
    }
}
