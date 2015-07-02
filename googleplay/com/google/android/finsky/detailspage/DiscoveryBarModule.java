package com.google.android.finsky.detailspage;

import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.DiscoveryBar;
import com.google.android.finsky.protos.Details.DiscoveryBadge;

public class DiscoveryBarModule extends FinskyModule<DiscoveryBarModuleData> {

    protected static class DiscoveryBarModuleData extends ModuleData {
        Document detailsDoc;
        DiscoveryBadge[] discoveryBadges;
        boolean hasSavedScrollPosition;
        int savedScrollPosition;

        protected DiscoveryBarModuleData() {
        }
    }

    public int getLayoutResId() {
        return R.layout.discovery_bar;
    }

    public boolean readyForDisplay() {
        if (this.mModuleData == null) {
            return true;
        }
        boolean z = ((DiscoveryBarModuleData) this.mModuleData).discoveryBadges != null && ((DiscoveryBarModuleData) this.mModuleData).discoveryBadges.length > 0;
        return z;
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (hasDetailsLoaded && this.mModuleData == null) {
            this.mModuleData = new DiscoveryBarModuleData();
            DiscoveryBadge[] discoveryBadges = socialDfeDetails.getDiscoveryBadges();
            ((DiscoveryBarModuleData) this.mModuleData).detailsDoc = socialDetailsDoc;
            ((DiscoveryBarModuleData) this.mModuleData).discoveryBadges = discoveryBadges;
            if (((DiscoveryBarModuleData) this.mModuleData).discoveryBadges == null || ((DiscoveryBarModuleData) this.mModuleData).discoveryBadges.length <= 0) {
                this.mFinskyModuleController.removeModule(this);
            } else {
                this.mFinskyModuleController.refreshModule(this, true);
            }
        }
    }

    public void bindView(View view) {
        DiscoveryBar discoveryBar = (DiscoveryBar) view;
        if (this.mModuleData == null) {
            discoveryBar.showPlaceholderView();
        } else if (!discoveryBar.hasConfigured()) {
            discoveryBar.configure(this.mNavigationManager, this.mBitmapLoader, ((DiscoveryBarModuleData) this.mModuleData).detailsDoc, ((DiscoveryBarModuleData) this.mModuleData).discoveryBadges, FinskyApp.get().getToc(), FinskyApp.get().getPackageManager(), true, ((DiscoveryBarModuleData) this.mModuleData).hasSavedScrollPosition, ((DiscoveryBarModuleData) this.mModuleData).savedScrollPosition, this.mParentNode);
            ((DiscoveryBarModuleData) this.mModuleData).hasSavedScrollPosition = false;
        }
    }

    public void onRecycleView(View view) {
        DiscoveryBar discoveryBar = (DiscoveryBar) view;
        if (this.mModuleData != null) {
            ((DiscoveryBarModuleData) this.mModuleData).hasSavedScrollPosition = true;
            ((DiscoveryBarModuleData) this.mModuleData).savedScrollPosition = discoveryBar.getScrollPosition();
        }
    }
}
