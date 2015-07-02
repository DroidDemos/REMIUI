package com.google.android.finsky.layout.play;

public class PlayCardTrustedSourceClusterRepository {
    private static final PlayCardClusterMetadata[] sClusters;

    static {
        sClusters = new PlayCardClusterMetadata[6];
        sClusters[0] = new PlayCardClusterMetadata(4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight();
        sClusters[1] = new PlayCardClusterMetadata(6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight();
        sClusters[2] = new PlayCardClusterMetadata(6, 0).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight().setAlignToParentEndIfNecessary();
        sClusters[3] = new PlayCardClusterMetadata(8, 0).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight();
        sClusters[4] = new PlayCardClusterMetadata(8, 0).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight().setAlignToParentEndIfNecessary();
        sClusters[5] = new PlayCardClusterMetadata(10, 0).withLeadingGap(4).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 8, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight().setAlignToParentEndIfNecessary();
    }

    public static PlayCardClusterMetadata getMetadata(int columns, boolean useTallTemplates) {
        return sClusters[PlayCardClusterRepository.getConfigurationKey(columns, useTallTemplates)];
    }
}
