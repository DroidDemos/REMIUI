package com.google.android.finsky.layout.play;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.ImageView;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata.CardMetadata;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.layout.PlayCardSnippet;
import com.google.android.play.layout.PlayCardViewBase;
import java.util.List;

public class PlayCardHeap {
    private final SparseArray<List<PlayCardViewBase>> mHeap;

    public PlayCardHeap() {
        this.mHeap = new SparseArray();
    }

    public void recycle(PlayCardClusterView cluster) {
        Utils.ensureOnMainThread();
        if (cluster != null && cluster.hasCards()) {
            PlayCardClusterMetadata clusterMetadata = cluster.getMetadata();
            int tileCount = clusterMetadata.getTileCount();
            for (int tileIndex = 0; tileIndex < tileCount; tileIndex++) {
                PlayCardViewBase card = cluster.getCardChildAt(tileIndex);
                CardMetadata cardMetadata = clusterMetadata.getTileMetadata(tileIndex).getCardMetadata();
                if (card != null) {
                    ImageView snippetImageView;
                    ImageView imageView = card.getThumbnail().getImageView();
                    if (imageView instanceof FifeImageView) {
                        ((FifeImageView) imageView).clearImage();
                    }
                    PlayCardSnippet snippet1 = card.getSnippet1();
                    if (snippet1 != null) {
                        snippetImageView = snippet1.getImageView();
                        if (imageView instanceof FifeImageView) {
                            ((FifeImageView) snippetImageView).clearImage();
                        }
                    }
                    PlayCardSnippet snippet2 = card.getSnippet2();
                    if (snippet2 != null) {
                        snippetImageView = snippet2.getImageView();
                        if (imageView instanceof FifeImageView) {
                            ((FifeImageView) snippetImageView).clearImage();
                        }
                    }
                    PlayCardUtils.recycleLoggingData(card);
                    ((List) this.mHeap.get(cardMetadata.getLayoutId())).add(card);
                }
            }
            cluster.removeAllCards();
        }
    }

    public PlayCardViewBase getCard(CardMetadata cardMetadata, LayoutInflater inflater) {
        Utils.ensureOnMainThread();
        int layoutId = cardMetadata.getLayoutId();
        List<PlayCardViewBase> available = (List) this.mHeap.get(layoutId);
        if (available == null) {
            available = Lists.newArrayList();
            this.mHeap.put(layoutId, available);
        }
        if (available.isEmpty()) {
            return (PlayCardViewBase) inflater.inflate(layoutId, null, false);
        }
        return (PlayCardViewBase) available.remove(0);
    }
}
