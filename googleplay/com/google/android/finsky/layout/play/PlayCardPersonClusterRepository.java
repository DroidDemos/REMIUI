package com.google.android.finsky.layout.play;

import android.util.SparseArray;

public class PlayCardPersonClusterRepository {
    private static final SparseArray<PlayCardClusterMetadata> sClusters;

    static {
        sClusters = new SparseArray();
        sClusters.append(0, new PlayCardClusterMetadata(6, 3).setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_PERSON, 0, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 2, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 4, 0));
        sClusters.append(1, new PlayCardClusterMetadata(8, 3).setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_PERSON, 0, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 2, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 4, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 6, 0));
        sClusters.append(2, new PlayCardClusterMetadata(8, 3).setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_PERSON, 0, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 2, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 4, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 6, 0));
        sClusters.append(3, new PlayCardClusterMetadata(8, 3).setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_PERSON, 0, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 2, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 4, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 6, 0));
        sClusters.append(4, new PlayCardClusterMetadata(8, 3).setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_PERSON, 0, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 2, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 4, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 6, 0));
        sClusters.append(5, new PlayCardClusterMetadata(10, 3).setRespectChildHeight().addTile(PlayCardClusterMetadata.CARD_PERSON, 0, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 2, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 4, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 6, 0).addTile(PlayCardClusterMetadata.CARD_PERSON, 8, 0));
    }

    public static PlayCardClusterMetadata getMetadata(int columns, boolean useTallTemplates) {
        return (PlayCardClusterMetadata) sClusters.get(PlayCardClusterRepository.getConfigurationKey(columns, useTallTemplates));
    }
}
