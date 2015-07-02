package com.google.android.finsky.layout.play;

import com.android.vending.R;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata.CardMetadata;

public class PlayCardRateAndSuggestClusterRepository {
    private static final PlayCardClusterMetadata[] sClusters16x9;
    private static final PlayCardClusterMetadata[] sClusters1x1;

    static {
        sClusters1x1 = new PlayCardClusterMetadata[6];
        sClusters16x9 = new PlayCardClusterMetadata[6];
        CardMetadata CARD_RATE_MINI = new CardMetadata(R.layout.play_card_rate, 6, 3, 1.0f);
        CardMetadata CARD_RATE_MINI_16x9 = new CardMetadata(R.layout.play_card_rate, 6, 4, 1.441f);
        CardMetadata CARD_SUGGESTION_MINI = new CardMetadata(R.layout.play_card_quick_suggestion_mini, 2, 3, 1.0f);
        CardMetadata CARD_SUGGESTION_MINI_16x9 = new CardMetadata(R.layout.play_card_quick_suggestion_mini, 2, 4, 1.441f);
        CardMetadata CARD_RATE = new CardMetadata(R.layout.play_card_rate, 4, 3, 1.0f);
        CardMetadata CARD_RATE_16x9 = new CardMetadata(R.layout.play_card_rate, 4, 4, 1.441f);
        CardMetadata CARD_SUGGESTION = new CardMetadata(R.layout.play_card_quick_suggestion_small, 2, 3, 1.0f);
        CardMetadata CARD_SUGGESTION_16x9 = new CardMetadata(R.layout.play_card_quick_suggestion_small, 2, 4, 1.441f);
        sClusters1x1[0] = new PlayCardClusterMetadata(12, 3).withViewportWidth(6).addTile(CARD_RATE_MINI, 0, 0).addTile(CARD_SUGGESTION_MINI, 6, 0).addTile(CARD_SUGGESTION_MINI, 8, 0).addTile(CARD_SUGGESTION_MINI, 10, 0);
        sClusters1x1[1] = new PlayCardClusterMetadata(14, 3).withViewportWidth(8).addTile(CARD_RATE_MINI, 0, 0).addTile(CARD_SUGGESTION_MINI, 6, 0).addTile(CARD_SUGGESTION_MINI, 8, 0).addTile(CARD_SUGGESTION_MINI, 10, 0).addTile(CARD_SUGGESTION_MINI, 12, 0);
        sClusters1x1[2] = new PlayCardClusterMetadata(10, 3).withViewportWidth(6).addTile(CARD_RATE, 0, 0).addTile(CARD_SUGGESTION, 4, 0).addTile(CARD_SUGGESTION, 6, 0).addTile(CARD_SUGGESTION, 8, 0);
        sClusters1x1[3] = new PlayCardClusterMetadata(12, 3).withViewportWidth(8).addTile(CARD_RATE, 0, 0).addTile(CARD_SUGGESTION, 4, 0).addTile(CARD_SUGGESTION, 6, 0).addTile(CARD_SUGGESTION, 8, 0).addTile(CARD_SUGGESTION, 10, 0);
        sClusters1x1[4] = new PlayCardClusterMetadata(12, 3).withViewportWidth(8).addTile(CARD_RATE, 0, 0).addTile(CARD_SUGGESTION, 4, 0).addTile(CARD_SUGGESTION, 6, 0).addTile(CARD_SUGGESTION, 8, 0).addTile(CARD_SUGGESTION, 10, 0);
        sClusters1x1[5] = new PlayCardClusterMetadata(14, 3).withViewportWidth(10).addTile(CARD_RATE, 0, 0).addTile(CARD_SUGGESTION, 4, 0).addTile(CARD_SUGGESTION, 6, 0).addTile(CARD_SUGGESTION, 8, 0).addTile(CARD_SUGGESTION, 10, 0).addTile(CARD_SUGGESTION, 12, 0);
        sClusters16x9[0] = new PlayCardClusterMetadata(12, 4).withViewportWidth(6).addTile(CARD_RATE_MINI_16x9, 0, 0).addTile(CARD_SUGGESTION_MINI_16x9, 6, 0).addTile(CARD_SUGGESTION_MINI_16x9, 8, 0).addTile(CARD_SUGGESTION_MINI_16x9, 10, 0);
        sClusters16x9[1] = new PlayCardClusterMetadata(14, 4).withViewportWidth(8).addTile(CARD_RATE_MINI_16x9, 0, 0).addTile(CARD_SUGGESTION_MINI_16x9, 6, 0).addTile(CARD_SUGGESTION_MINI_16x9, 8, 0).addTile(CARD_SUGGESTION_MINI_16x9, 10, 0).addTile(CARD_SUGGESTION_MINI_16x9, 12, 0);
        sClusters16x9[2] = new PlayCardClusterMetadata(10, 4).withViewportWidth(6).addTile(CARD_RATE_16x9, 0, 0).addTile(CARD_SUGGESTION_16x9, 4, 0).addTile(CARD_SUGGESTION_16x9, 6, 0).addTile(CARD_SUGGESTION_16x9, 8, 0);
        sClusters16x9[3] = new PlayCardClusterMetadata(12, 4).withViewportWidth(8).addTile(CARD_RATE_16x9, 0, 0).addTile(CARD_SUGGESTION_16x9, 4, 0).addTile(CARD_SUGGESTION_16x9, 6, 0).addTile(CARD_SUGGESTION_16x9, 8, 0).addTile(CARD_SUGGESTION_16x9, 10, 0);
        sClusters16x9[4] = new PlayCardClusterMetadata(12, 4).withViewportWidth(8).addTile(CARD_RATE_16x9, 0, 0).addTile(CARD_SUGGESTION_16x9, 4, 0).addTile(CARD_SUGGESTION_16x9, 6, 0).addTile(CARD_SUGGESTION_16x9, 8, 0).addTile(CARD_SUGGESTION_16x9, 10, 0);
        sClusters16x9[5] = new PlayCardClusterMetadata(14, 4).withViewportWidth(10).addTile(CARD_RATE_16x9, 0, 0).addTile(CARD_SUGGESTION_16x9, 4, 0).addTile(CARD_SUGGESTION_16x9, 6, 0).addTile(CARD_SUGGESTION_16x9, 8, 0).addTile(CARD_SUGGESTION_16x9, 10, 0).addTile(CARD_SUGGESTION_16x9, 12, 0);
    }

    public static PlayCardClusterMetadata getMetadata(int documentType, int columns, boolean useTallTemplates) {
        return ((PlayCardClusterMetadata.getAspectRatio(documentType) > 1.0f ? 1 : (PlayCardClusterMetadata.getAspectRatio(documentType) == 1.0f ? 0 : -1)) == 0 ? sClusters1x1 : sClusters16x9)[PlayCardClusterRepository.getConfigurationKey(columns, useTallTemplates)];
    }
}
