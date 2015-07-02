package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.WishlistHelper;
import com.google.android.finsky.utils.WishlistHelper.WishlistStatusListener;

public class DetailsSummaryWishlistView extends ImageView implements WishlistStatusListener {
    private Document mDoc;
    private boolean mIsConfigured;

    public DetailsSummaryWishlistView(Context context) {
        this(context, null);
    }

    public DetailsSummaryWishlistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void configure(final Document doc, final NavigationManager navigationManager) {
        if (WishlistHelper.shouldHideWishlistAction(doc, FinskyApp.get().getDfeApi())) {
            setVisibility(8);
            return;
        }
        this.mDoc = doc;
        setVisibility(0);
        final Account currAccount = FinskyApp.get().getCurrentAccount();
        syncVisuals(WishlistHelper.isInWishlist(doc, currAccount), doc.getBackend());
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DfeApi dfeApi = FinskyApp.get().getDfeApi();
                FinskyApp.get().getEventLogger().logClickEvent(WishlistHelper.isInWishlist(doc, currAccount) ? 205 : 204, null, navigationManager.getActivePage());
                WishlistHelper.processWishlistClick(DetailsSummaryWishlistView.this, doc, dfeApi);
            }
        });
        this.mIsConfigured = true;
    }

    private void syncVisuals(boolean isInWishlist, int backendId) {
        if (isInWishlist) {
            setImageResource(CorpusResourceUtils.getWishlistOnDrawable(backendId));
            setContentDescription(getContext().getString(R.string.content_description_wishlist_remove));
            return;
        }
        setImageResource(CorpusResourceUtils.getWishlistOffDrawable(backendId));
        setContentDescription(getContext().getString(R.string.content_description_wishlist_add));
    }

    public void onWishlistStatusChanged(String docId, boolean isInWishlist, boolean isCommitted) {
        if (this.mIsConfigured && docId.equals(this.mDoc.getDocId())) {
            syncVisuals(isInWishlist, this.mDoc.getBackend());
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        WishlistHelper.addWishlistStatusListener(this);
    }

    public void onDetachedFromWindow() {
        WishlistHelper.removeWishlistStatusListener(this);
        super.onDetachedFromWindow();
    }
}
