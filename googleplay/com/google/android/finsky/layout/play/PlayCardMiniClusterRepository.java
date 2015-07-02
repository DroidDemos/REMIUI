package com.google.android.finsky.layout.play;

import android.util.SparseArray;

public class PlayCardMiniClusterRepository {
    private static final SparseArray<PlayCardClusterMetadata> sClusters16x9;
    private static final SparseArray<PlayCardClusterMetadata> sClusters1x1;

    static {
        sClusters1x1 = new SparseArray();
        sClusters16x9 = new SparseArray();
        sClusters1x1.append(1, new PlayCardClusterMetadata(6, 3).setRespectChildThumbnailAspectRatio().setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_MINI, 0, 0).addTile(PlayCardClusterMetadata.CARD_MINI, 2, 0).addTile(PlayCardClusterMetadata.CARD_MINI, 4, 0));
        sClusters1x1.append(3, new PlayCardClusterMetadata(8, 3).setRespectChildThumbnailAspectRatio().setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_MINI, 0, 0).addTile(PlayCardClusterMetadata.CARD_MINI, 2, 0).addTile(PlayCardClusterMetadata.CARD_MINI, 4, 0).addTile(PlayCardClusterMetadata.CARD_MINI, 6, 0));
        sClusters16x9.append(1, new PlayCardClusterMetadata(6, 4).setRespectChildThumbnailAspectRatio().setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 4, 0));
        sClusters16x9.append(3, new PlayCardClusterMetadata(8, 4).setRespectChildThumbnailAspectRatio().setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 6, 0));
    }

    public static PlayCardClusterMetadata getMetadata(int documentType, int columns) {
        boolean use1x1;
        if (PlayCardClusterMetadata.getAspectRatio(documentType) == 1.0f) {
            use1x1 = true;
        } else {
            use1x1 = false;
        }
        return (PlayCardClusterMetadata) (use1x1 ? sClusters1x1 : sClusters16x9).get(PlayCardClusterRepository.getConfigurationKey(columns, false));
    }
}
