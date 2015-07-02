package com.google.android.finsky.layout.play;

import android.util.SparseArray;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class PlayCardClusterRepository {
    private static final SparseArray<List<PlayCardClusterMetadata>> sClusters16x9;
    private static final SparseArray<List<PlayCardClusterMetadata>> sClusters1x1;

    static {
        sClusters1x1 = new SparseArray();
        sClusters16x9 = new SparseArray();
        List<PlayCardClusterMetadata> clustersTwoColumns1x1 = Lists.newArrayList();
        clustersTwoColumns1x1.add(0, new PlayCardClusterMetadata(4, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersTwoColumns1x1.add(1, new PlayCardClusterMetadata(4, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersTwoColumns1x1.add(2, new PlayCardClusterMetadata(4, 2).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM, 0, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersTwoColumns1x1.add(3, new PlayCardClusterMetadata(4, 2).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM, 0, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersTwoColumns1x1.add(4, new PlayCardClusterMetadata(4, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight().setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        sClusters1x1.append(0, clustersTwoColumns1x1);
        List<PlayCardClusterMetadata> clustersThreeColumns1x1 = Lists.newArrayList();
        clustersThreeColumns1x1.add(0, new PlayCardClusterMetadata(6, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersThreeColumns1x1.add(1, new PlayCardClusterMetadata(6, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersThreeColumns1x1.add(2, new PlayCardClusterMetadata(6, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersThreeColumns1x1.add(3, new PlayCardClusterMetadata(6, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersThreeColumns1x1.add(3, new PlayCardClusterMetadata(6, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        sClusters1x1.append(1, clustersThreeColumns1x1);
        List<PlayCardClusterMetadata> clustersThreeTallColumns1x1 = Lists.newArrayList();
        clustersThreeTallColumns1x1.add(0, new PlayCardClusterMetadata(6, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersThreeTallColumns1x1.add(1, new PlayCardClusterMetadata(6, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersThreeTallColumns1x1.add(2, new PlayCardClusterMetadata(6, 6).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 3).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersThreeTallColumns1x1.add(3, new PlayCardClusterMetadata(6, 6).addTile(PlayCardClusterMetadata.CARD_LARGE, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 3).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersThreeTallColumns1x1.add(3, new PlayCardClusterMetadata(6, 6).addTile(PlayCardClusterMetadata.CARD_LARGE, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 3).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        sClusters1x1.append(2, clustersThreeTallColumns1x1);
        List<PlayCardClusterMetadata> clustersFourColumns1x1 = Lists.newArrayList();
        clustersFourColumns1x1.add(0, new PlayCardClusterMetadata(8, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersFourColumns1x1.add(1, new PlayCardClusterMetadata(8, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFourColumns1x1.add(2, new PlayCardClusterMetadata(8, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFourColumns1x1.add(3, new PlayCardClusterMetadata(8, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFourColumns1x1.add(3, new PlayCardClusterMetadata(8, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        sClusters1x1.append(3, clustersFourColumns1x1);
        List<PlayCardClusterMetadata> clustersFourTallColumns1x1 = Lists.newArrayList();
        clustersFourTallColumns1x1.add(0, new PlayCardClusterMetadata(8, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersFourTallColumns1x1.add(1, new PlayCardClusterMetadata(8, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFourTallColumns1x1.add(2, new PlayCardClusterMetadata(8, 6).addTile(PlayCardClusterMetadata.CARD_LARGE, 0, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM, 4, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM, 4, 2).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM, 4, 4).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFourTallColumns1x1.add(3, new PlayCardClusterMetadata(8, 6).addTile(PlayCardClusterMetadata.CARD_LARGE, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 3).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFourTallColumns1x1.add(4, new PlayCardClusterMetadata(8, 8).addTile(PlayCardClusterMetadata.CARD_LARGE, 0, 0).addTile(PlayCardClusterMetadata.CARD_LARGE, 4, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM, 0, 6).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM, 4, 6).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        sClusters1x1.append(4, clustersFourTallColumns1x1);
        List<PlayCardClusterMetadata> clustersFiveColumns1x1 = Lists.newArrayList();
        clustersFiveColumns1x1.add(0, new PlayCardClusterMetadata(10, 3).addTile(PlayCardClusterMetadata.CARD_SMALL, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 8, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersFiveColumns1x1.add(1, new PlayCardClusterMetadata(10, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 8, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFiveColumns1x1.add(2, new PlayCardClusterMetadata(10, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 8, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFiveColumns1x1.add(3, new PlayCardClusterMetadata(10, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 8, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        clustersFiveColumns1x1.add(4, new PlayCardClusterMetadata(10, 3).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 0, 0).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL, 8, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL));
        sClusters1x1.append(5, clustersFiveColumns1x1);
        List<PlayCardClusterMetadata> clustersTwoColumns16x9 = Lists.newArrayList();
        clustersTwoColumns16x9.add(0, new PlayCardClusterMetadata(4, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersTwoColumns16x9.add(1, new PlayCardClusterMetadata(4, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersTwoColumns16x9.add(2, new PlayCardClusterMetadata(4, 2).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 0, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersTwoColumns16x9.add(3, new PlayCardClusterMetadata(4, 2).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 0, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersTwoColumns16x9.add(4, new PlayCardClusterMetadata(4, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        sClusters16x9.append(0, clustersTwoColumns16x9);
        List<PlayCardClusterMetadata> clustersThreeColumns16x9 = Lists.newArrayList();
        clustersThreeColumns16x9.add(0, new PlayCardClusterMetadata(6, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersThreeColumns16x9.add(1, new PlayCardClusterMetadata(6, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersThreeColumns16x9.add(2, new PlayCardClusterMetadata(6, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersThreeColumns16x9.add(3, new PlayCardClusterMetadata(6, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersThreeColumns16x9.add(4, new PlayCardClusterMetadata(6, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        sClusters16x9.append(1, clustersThreeColumns16x9);
        List<PlayCardClusterMetadata> clustersThreeTallColumns16x9 = Lists.newArrayList();
        clustersThreeTallColumns16x9.add(0, new PlayCardClusterMetadata(6, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersThreeTallColumns16x9.add(1, new PlayCardClusterMetadata(6, 8).addTile(PlayCardClusterMetadata.CARD_LARGEMINUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 4).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersThreeTallColumns16x9.add(2, new PlayCardClusterMetadata(6, 8).addTile(PlayCardClusterMetadata.CARD_LARGEMINUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 4).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersThreeTallColumns16x9.add(3, new PlayCardClusterMetadata(6, 8).addTile(PlayCardClusterMetadata.CARD_LARGEMINUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 4).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersThreeTallColumns16x9.add(4, new PlayCardClusterMetadata(6, 8).addTile(PlayCardClusterMetadata.CARD_LARGEMINUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 4).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        sClusters16x9.append(2, clustersThreeTallColumns16x9);
        List<PlayCardClusterMetadata> clustersFourColumns16x9 = Lists.newArrayList();
        clustersFourColumns16x9.add(0, new PlayCardClusterMetadata(8, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersFourColumns16x9.add(1, new PlayCardClusterMetadata(8, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFourColumns16x9.add(2, new PlayCardClusterMetadata(8, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFourColumns16x9.add(3, new PlayCardClusterMetadata(8, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFourColumns16x9.add(4, new PlayCardClusterMetadata(8, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        sClusters16x9.append(3, clustersFourColumns16x9);
        List<PlayCardClusterMetadata> clustersFourTallColumns16x9 = Lists.newArrayList();
        clustersFourTallColumns16x9.add(0, new PlayCardClusterMetadata(8, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersFourTallColumns16x9.add(1, new PlayCardClusterMetadata(8, 4).addTile(PlayCardClusterMetadata.CARD_MEDIUMPLUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFourTallColumns16x9.add(2, new PlayCardClusterMetadata(8, 8).addTile(PlayCardClusterMetadata.CARD_LARGE_16x9, 0, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 4, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 4, 2).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 4, 4).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 4, 6).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFourTallColumns16x9.add(3, new PlayCardClusterMetadata(8, 8).addTile(PlayCardClusterMetadata.CARD_LARGE_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 4).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFourTallColumns16x9.add(4, new PlayCardClusterMetadata(8, 8).addTile(PlayCardClusterMetadata.CARD_LARGE_16x9, 0, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 4, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 4, 2).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 4, 4).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 4, 6).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        sClusters16x9.append(4, clustersFourTallColumns16x9);
        List<PlayCardClusterMetadata> clustersFiveColumns16x9 = Lists.newArrayList();
        clustersFiveColumns16x9.add(0, new PlayCardClusterMetadata(10, 4).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 2, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 4, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 8, 0).setRespectChildThumbnailAspectRatio().setRespectChildHeight());
        clustersFiveColumns16x9.add(1, new PlayCardClusterMetadata(10, 4).addTile(PlayCardClusterMetadata.CARD_LARGEMINUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 8, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFiveColumns16x9.add(2, new PlayCardClusterMetadata(10, 4).addTile(PlayCardClusterMetadata.CARD_LARGEMINUS_16x9, 0, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 6, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 6, 2).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFiveColumns16x9.add(3, new PlayCardClusterMetadata(10, 4).addTile(PlayCardClusterMetadata.CARD_LARGEMINUS_16x9, 0, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 6, 0).addTile(PlayCardClusterMetadata.CARD_SMALL_16x9, 8, 0).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        clustersFiveColumns16x9.add(4, new PlayCardClusterMetadata(10, 4).addTile(PlayCardClusterMetadata.CARD_LARGEMINUS_16x9, 0, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 6, 0).addFlexiTile(PlayCardClusterMetadata.CARD_MEDIUM_16x9, 6, 2).setCardMetadataForMinCellHeight(PlayCardClusterMetadata.CARD_SMALL_16x9));
        sClusters16x9.append(5, clustersFiveColumns16x9);
    }

    public static int getConfigurationKey(int columns, boolean useTallTemplates) {
        switch (columns) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 0;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (useTallTemplates) {
                    return 2;
                }
                return 1;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return useTallTemplates ? 4 : 3;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return 5;
            default:
                FinskyLog.wtf("Unsupported number of columns %d", Integer.valueOf(columns));
                return 0;
        }
    }

    public static PlayCardClusterMetadata getMetadata(int documentType, int columns, boolean useTallTemplates, int signalStrength) {
        return (PlayCardClusterMetadata) ((List) ((PlayCardClusterMetadata.getAspectRatio(documentType) > 1.0f ? 1 : (PlayCardClusterMetadata.getAspectRatio(documentType) == 1.0f ? 0 : -1)) == 0 ? sClusters1x1 : sClusters16x9).get(getConfigurationKey(columns, useTallTemplates))).get(signalStrength);
    }
}
