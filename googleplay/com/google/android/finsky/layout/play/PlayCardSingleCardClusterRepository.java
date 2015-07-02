package com.google.android.finsky.layout.play;

import android.util.SparseArray;
import com.android.vending.R;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata.CardMetadata;

public class PlayCardSingleCardClusterRepository {
    public static final CardMetadata SINGLE_CARD_COL2;
    public static final CardMetadata SINGLE_CARD_COL2_16x9;
    public static final CardMetadata SINGLE_CARD_COL2_16x9_WITH_BUTTON;
    public static final CardMetadata SINGLE_CARD_COL2_WITH_BUTTON;
    public static final CardMetadata SINGLE_CARD_COL3;
    public static final CardMetadata SINGLE_CARD_COL3_16x9;
    public static final CardMetadata SINGLE_CARD_COL3_16x9_WITH_BUTTON;
    public static final CardMetadata SINGLE_CARD_COL3_WITH_BUTTON;
    private static final SparseArray<PlayCardSingleCardClusterMetadata> sClusters16x9;
    private static final SparseArray<PlayCardSingleCardClusterMetadata> sClusters16x9_button;
    private static final SparseArray<PlayCardSingleCardClusterMetadata> sClusters1x1;
    private static final SparseArray<PlayCardSingleCardClusterMetadata> sClusters1x1_button;

    static {
        sClusters1x1 = new SparseArray();
        sClusters16x9 = new SparseArray();
        SINGLE_CARD_COL2 = new CardMetadata(R.layout.play_card_single, 4, 2, 1.0f);
        SINGLE_CARD_COL3 = new CardMetadata(R.layout.play_card_single, 6, 2, 1.0f);
        SINGLE_CARD_COL2_16x9 = new CardMetadata(R.layout.play_card_single, 4, 3, 1.441f);
        SINGLE_CARD_COL3_16x9 = new CardMetadata(R.layout.play_card_single, 6, 3, 1.441f);
        sClusters1x1.append(0, new PlayCardSingleCardClusterMetadata(4, 2).withMerchImageHSpan(0).addTile(SINGLE_CARD_COL2, 0, 0));
        sClusters1x1.append(1, new PlayCardSingleCardClusterMetadata(6, 2).withMerchImageHSpan(2).addTile(SINGLE_CARD_COL2, 0, 0));
        sClusters1x1.append(3, new PlayCardSingleCardClusterMetadata(8, 2).withMerchImageHSpan(2).addTile(SINGLE_CARD_COL3, 0, 0));
        sClusters1x1.append(5, new PlayCardSingleCardClusterMetadata(10, 2).withMerchImageHSpan(4).addTile(SINGLE_CARD_COL3, 0, 0));
        sClusters16x9.append(0, new PlayCardSingleCardClusterMetadata(4, 3).withMerchImageHSpan(0).addTile(SINGLE_CARD_COL2_16x9, 0, 0));
        sClusters16x9.append(1, new PlayCardSingleCardClusterMetadata(6, 3).withMerchImageHSpan(2).addTile(SINGLE_CARD_COL2_16x9, 0, 0));
        sClusters16x9.append(3, new PlayCardSingleCardClusterMetadata(8, 3).withMerchImageHSpan(2).addTile(SINGLE_CARD_COL3_16x9, 0, 0));
        sClusters16x9.append(5, new PlayCardSingleCardClusterMetadata(10, 3).withMerchImageHSpan(4).addTile(SINGLE_CARD_COL3_16x9, 0, 0));
        sClusters1x1_button = new SparseArray();
        sClusters16x9_button = new SparseArray();
        SINGLE_CARD_COL2_WITH_BUTTON = new CardMetadata(R.layout.play_card_single_with_button, 4, 2, 1.0f);
        SINGLE_CARD_COL3_WITH_BUTTON = new CardMetadata(R.layout.play_card_single_with_button, 6, 2, 1.0f);
        SINGLE_CARD_COL2_16x9_WITH_BUTTON = new CardMetadata(R.layout.play_card_single_with_button, 4, 3, 1.441f);
        SINGLE_CARD_COL3_16x9_WITH_BUTTON = new CardMetadata(R.layout.play_card_single_with_button, 6, 3, 1.441f);
        sClusters1x1_button.append(0, new PlayCardSingleCardClusterMetadata(4, 2).withMerchImageHSpan(0).addTile(SINGLE_CARD_COL2_WITH_BUTTON, 0, 0));
        sClusters1x1_button.append(1, new PlayCardSingleCardClusterMetadata(6, 2).withMerchImageHSpan(2).addTile(SINGLE_CARD_COL2_WITH_BUTTON, 0, 0));
        sClusters1x1_button.append(3, new PlayCardSingleCardClusterMetadata(8, 2).withMerchImageHSpan(2).addTile(SINGLE_CARD_COL3_WITH_BUTTON, 0, 0));
        sClusters1x1_button.append(5, new PlayCardSingleCardClusterMetadata(10, 2).withMerchImageHSpan(4).addTile(SINGLE_CARD_COL3_WITH_BUTTON, 0, 0));
        sClusters16x9_button.append(0, new PlayCardSingleCardClusterMetadata(4, 3).withMerchImageHSpan(0).addTile(SINGLE_CARD_COL2_16x9_WITH_BUTTON, 0, 0));
        sClusters16x9_button.append(1, new PlayCardSingleCardClusterMetadata(6, 3).withMerchImageHSpan(2).addTile(SINGLE_CARD_COL2_16x9_WITH_BUTTON, 0, 0));
        sClusters16x9_button.append(3, new PlayCardSingleCardClusterMetadata(8, 3).withMerchImageHSpan(2).addTile(SINGLE_CARD_COL3_16x9_WITH_BUTTON, 0, 0));
        sClusters16x9_button.append(5, new PlayCardSingleCardClusterMetadata(10, 3).withMerchImageHSpan(4).addTile(SINGLE_CARD_COL3_16x9_WITH_BUTTON, 0, 0));
    }

    public static PlayCardSingleCardClusterMetadata getMetadata(int documentType, int columns, boolean hasActionButton) {
        boolean use1x1;
        if (PlayCardClusterMetadata.getAspectRatio(documentType) == 1.0f) {
            use1x1 = true;
        } else {
            use1x1 = false;
        }
        SparseArray<PlayCardSingleCardClusterMetadata> source = hasActionButton ? use1x1 ? sClusters1x1_button : sClusters16x9_button : use1x1 ? sClusters1x1 : sClusters16x9;
        return (PlayCardSingleCardClusterMetadata) source.get(PlayCardClusterRepository.getConfigurationKey(columns, false));
    }
}
