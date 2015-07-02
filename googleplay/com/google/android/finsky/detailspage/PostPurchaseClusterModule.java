package com.google.android.finsky.detailspage;

import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.utils.LibraryUtils;

public class PostPurchaseClusterModule extends DfeListCardClusterModule implements Listener {
    private Document mDetailsDoc;
    private DfeDetails mDfeDetails;
    private boolean mHasDetailsLoaded;
    private Boolean mIsEnabled;
    private Document mSocialDetailsDoc;
    private DfeDetails mSocialDfeDetails;
    private Boolean mWasOwnedWhenPageLoaded;

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mIsEnabled == null) {
            boolean z = ((Boolean) G.forcePostPurchaseCrossSell.get()).booleanValue() || detailsDoc.getBackend() == 3 || FinskyApp.get().getExperiments().isEnabled("cl:ui.enable_post_purchase_xsell_for_all_corpora");
            this.mIsEnabled = Boolean.valueOf(z);
        }
        if (this.mIsEnabled.booleanValue()) {
            if (this.mWasOwnedWhenPageLoaded == null) {
                this.mWasOwnedWhenPageLoaded = Boolean.valueOf(LibraryUtils.isOwned(detailsDoc, this.mLibraries));
                this.mLibraries.addListener(this);
            }
            this.mHasDetailsLoaded = hasDetailsLoaded;
            this.mDetailsDoc = detailsDoc;
            this.mDfeDetails = dfeDetails;
            this.mSocialDetailsDoc = socialDetailsDoc;
            this.mSocialDfeDetails = socialDfeDetails;
        }
    }

    public void onDestroyModule() {
        super.onDestroyModule();
        this.mLibraries.removeListener(this);
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        boolean wasPurchasedInPageLifetime = !this.mWasOwnedWhenPageLoaded.booleanValue() && LibraryUtils.isOwned(this.mDetailsDoc, this.mLibraries);
        if (wasPurchasedInPageLifetime) {
            super.bindModule(this.mHasDetailsLoaded, this.mDetailsDoc, this.mDfeDetails, this.mSocialDetailsDoc, this.mSocialDfeDetails);
            this.mWasOwnedWhenPageLoaded = Boolean.valueOf(true);
        }
    }

    public String getHeaderString(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return (detailsDoc.getPostPurchaseCrossSellSection() != null ? detailsDoc.getPostPurchaseCrossSellSection() : detailsDoc.getCrossSellSection()).header;
    }

    public String getMoreBrowseUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return (detailsDoc.getPostPurchaseCrossSellSection() != null ? detailsDoc.getPostPurchaseCrossSellSection() : detailsDoc.getCrossSellSection()).browseUrl;
    }

    public String getDfeListUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        SectionMetadata postPurchaseSection = detailsDoc.getPostPurchaseCrossSellSection() != null ? detailsDoc.getPostPurchaseCrossSellSection() : detailsDoc.getCrossSellSection();
        return postPurchaseSection != null ? postPurchaseSection.listUrl : null;
    }
}
