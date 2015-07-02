package com.google.android.finsky.layout.play;

import android.util.SparseArray;

public class PlayCardMerchClusterRepository {
    private static final SparseArray<PlayCardClusterMetadata> sClusters16x9;
    private static final SparseArray<PlayCardClusterMetadata> sClusters1x1;

    static {
        sClusters1x1 = new SparseArray();
        sClusters16x9 = new SparseArray();
        sClusters1x1.append(0, new PlayCardClusterMetadata(6, 3).withLeadingGap(2).addTile(PlayCardClusterMetadata.CARD_MINI, 2, 0).addTile(PlayCardClusterMetadata.CARD_MINI, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters1x1.append(1, new PlayCardClusterMetadata(8, 3).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_MINI, 4, 0).addTile(PlayCardClusterMetadata.CARD_MINI, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters1x1.append(2, new PlayCardClusterMetadata(6, 3).withLeadingGap(2).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters1x1.append(3, new PlayCardClusterMetadata(8, 3).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters1x1.append(4, new PlayCardClusterMetadata(8, 3).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters1x1.append(5, new PlayCardClusterMetadata(10, 3).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 8, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters16x9.append(0, new PlayCardClusterMetadata(6, 4).withLeadingGap(2).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters16x9.append(1, new PlayCardClusterMetadata(8, 4).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_MINI_16x9, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters16x9.append(2, new PlayCardClusterMetadata(6, 4).withLeadingGap(2).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters16x9.append(3, new PlayCardClusterMetadata(8, 4).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters16x9.append(4, new PlayCardClusterMetadata(8, 4).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters16x9.append(5, new PlayCardClusterMetadata(10, 4).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 8, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
    }

    public static PlayCardClusterMetadata getMetadata(int documentType, int columns, boolean useTallTemplates) {
        return (PlayCardClusterMetadata) ((PlayCardClusterMetadata.getAspectRatio(documentType) > 1.0f ? 1 : (PlayCardClusterMetadata.getAspectRatio(documentType) == 1.0f ? 0 : -1)) == 0 ? sClusters1x1 : sClusters16x9).get(PlayCardClusterRepository.getConfigurationKey(columns, useTallTemplates));
    }
}
