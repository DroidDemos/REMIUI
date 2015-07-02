package com.google.android.finsky.adapters;

import android.content.Context;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.WishlistHelper.WishlistStatusListener;
import com.google.android.play.image.BitmapLoader;
import java.util.Set;

public class MyWishlistRecyclerViewAdapter extends CardRecyclerViewAdapter implements WishlistStatusListener {
    private Set<String> mDismissedDocIds;

    public MyWishlistRecyclerViewAdapter(Context context, DfeApi dfeApi, NavigationManager navManager, BitmapLoader loader, DfeToc toc, ClientMutationCache clientMutationCache, DfeList dfeList, PlayStoreUiElementNode parentNode, boolean isRestoring) {
        super(context, dfeApi, navManager, loader, toc, clientMutationCache, dfeList, null, null, isRestoring, false, 2, parentNode);
        this.mDismissedDocIds = Sets.newHashSet();
    }

    public void onDataChanged() {
        this.mDismissedDocIds.clear();
        super.onDataChanged();
    }

    public void onWishlistStatusChanged(String docId, boolean isInWishlist, boolean isCommitted) {
        if (!isCommitted) {
            if (isInWishlist) {
                this.mDismissedDocIds.remove(docId);
            } else {
                this.mDismissedDocIds.add(docId);
            }
            notifyDataSetChanged();
        } else if (!isInWishlist) {
            this.mDismissedDocIds.remove(docId);
            for (int i = 0; i < this.mContainerList.getCount(); i++) {
                if (docId.equals(((Document) this.mContainerList.getItem(i)).getDocId())) {
                    this.mContainerList.removeItem(i);
                    break;
                }
            }
            updateAdapterData(this.mContainerList);
        }
    }

    protected boolean isDismissed(Document document) {
        return this.mDismissedDocIds.contains(document.getDocId());
    }
}
