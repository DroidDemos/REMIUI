package com.google.android.finsky.layout.play;

import com.android.vending.R;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata.CardMetadata;

public class PlayCardRateClusterRepository {
    private static final PlayCardClusterMetadata[] sClusters;

    static {
        sClusters = new PlayCardClusterMetadata[6];
        CardMetadata CARD_RATE = new CardMetadata(R.layout.play_card_rate, 4, 2, 1.0f);
        CardMetadata CARD_RATE_WIDE_3 = new CardMetadata(R.layout.play_card_rate, 6, 2, 1.0f);
        CardMetadata CARD_RATE_WIDE_5 = new CardMetadata(R.layout.play_card_rate, 5, 2, 1.0f);
        sClusters[0] = new PlayCardClusterMetadata(4, 2).addTile(CARD_RATE, 0, 0).addTile(CARD_RATE, 4, 0).setRespectChildThumbnailAspectRatio();
        sClusters[1] = new PlayCardClusterMetadata(6, 2).addTile(CARD_RATE_WIDE_3, 0, 0).addTile(CARD_RATE_WIDE_3, 6, 0).setRespectChildThumbnailAspectRatio();
        sClusters[3] = new PlayCardClusterMetadata(8, 2).addTile(CARD_RATE, 0, 0).addTile(CARD_RATE, 4, 0).addTile(CARD_RATE, 8, 0).setRespectChildThumbnailAspectRatio();
        sClusters[5] = new PlayCardClusterMetadata(10, 2).addTile(CARD_RATE_WIDE_5, 0, 0).addTile(CARD_RATE_WIDE_5, 5, 0).addTile(CARD_RATE_WIDE_5, 10, 0).setRespectChildThumbnailAspectRatio();
    }

    public static PlayCardClusterMetadata getMetadata(int columns) {
        return sClusters[PlayCardClusterRepository.getConfigurationKey(columns, false)];
    }
}
