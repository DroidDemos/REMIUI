package com.google.android.finsky.detailspage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.FinskyModuleController;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.play.image.BitmapLoader;
import java.util.List;

public class CombinedTitleModule extends FinskyModule<CombinedTitleModuleData> implements FinskyModuleController {
    private DiscoveryBarModule mDiscoveryBarModule;
    private boolean mHasDiscoveryBarModuleBindedView;
    private boolean mHasWarningMessageModuleBindedView;
    private List<FinskyModule> mNestedModules;
    private TitleModule mTitleModule;
    private WarningMessageModule mWarningMessageModule;

    protected static class CombinedTitleModuleData extends ModuleData {
        DiscoveryBarModuleData discoveryBarModuleData;
        TitleModuleData titleModuleData;
        WarningMessageModuleData warningMessageModuleData;

        protected CombinedTitleModuleData() {
        }
    }

    public int getLayoutResId() {
        return R.layout.combined_title_module;
    }

    public void init(Context context, FinskyModuleController finskyModuleController, DfeToc dfeToc, DfeApi dfeApi, DfeApi socialDfeApi, BitmapLoader bitmapLoader, NavigationManager navigationManager, DetailsFragment2 detailsFragment2, String detailsUrl, String continueUrl, Libraries libraries, boolean useWideLayout, PlayStoreUiElementNode parentNode) {
        super.init(context, finskyModuleController, dfeToc, dfeApi, socialDfeApi, bitmapLoader, navigationManager, detailsFragment2, detailsUrl, continueUrl, libraries, this.mUseWideLayout, parentNode);
        this.mTitleModule = new TitleModule();
        this.mDiscoveryBarModule = new DiscoveryBarModule();
        this.mWarningMessageModule = new WarningMessageModule();
        this.mNestedModules = Lists.newArrayList();
        this.mNestedModules.add(this.mTitleModule);
        this.mNestedModules.add(this.mDiscoveryBarModule);
        this.mNestedModules.add(this.mWarningMessageModule);
        for (int i = 0; i < this.mNestedModules.size(); i++) {
            ((FinskyModule) this.mNestedModules.get(i)).init(context, this, dfeToc, dfeApi, socialDfeApi, bitmapLoader, navigationManager, detailsFragment2, detailsUrl, continueUrl, libraries, this.mUseWideLayout, parentNode);
        }
    }

    public boolean readyForDisplay() {
        return this.mTitleModule.readyForDisplay();
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null) {
            this.mTitleModule.onRestoreModuleData(((CombinedTitleModuleData) this.mModuleData).titleModuleData);
            this.mDiscoveryBarModule.onRestoreModuleData(((CombinedTitleModuleData) this.mModuleData).discoveryBarModuleData);
            this.mWarningMessageModule.onRestoreModuleData(((CombinedTitleModuleData) this.mModuleData).warningMessageModuleData);
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        for (int i = 0; i < this.mNestedModules.size(); i++) {
            ((FinskyModule) this.mNestedModules.get(i)).bindModule(hasDetailsLoaded, detailsDoc, dfeDetails, socialDetailsDoc, socialDfeDetails);
        }
    }

    public void bindView(View view) {
        CombinedTitleModuleLayout combinedTitleModuleLayout = (CombinedTitleModuleLayout) view;
        this.mTitleModule.bindView(combinedTitleModuleLayout.getTitleModuleLayout());
        if (this.mDiscoveryBarModule.readyForDisplay()) {
            this.mDiscoveryBarModule.bindView(combinedTitleModuleLayout.getDiscoveryBarModuleLayout());
            this.mHasDiscoveryBarModuleBindedView = true;
        }
        if (this.mWarningMessageModule.readyForDisplay()) {
            this.mWarningMessageModule.bindView(combinedTitleModuleLayout.getWarningMessageModuleLayout());
            this.mHasWarningMessageModuleBindedView = true;
        }
    }

    public void onRecycleView(View view) {
        CombinedTitleModuleLayout combinedTitleModuleLayout = (CombinedTitleModuleLayout) view;
        this.mTitleModule.onRecycleView(combinedTitleModuleLayout.getTitleModuleLayout());
        if (this.mHasDiscoveryBarModuleBindedView) {
            this.mDiscoveryBarModule.onRecycleView(combinedTitleModuleLayout.getDiscoveryBarModuleLayout());
        }
        if (this.mHasWarningMessageModuleBindedView) {
            this.mWarningMessageModule.onRecycleView(combinedTitleModuleLayout.getWarningMessageModuleLayout());
        }
        super.onRecycleView(view);
    }

    public CombinedTitleModuleData onSaveModuleData() {
        if (this.mModuleData == null) {
            this.mModuleData = new CombinedTitleModuleData();
        }
        ((CombinedTitleModuleData) this.mModuleData).titleModuleData = (TitleModuleData) this.mTitleModule.onSaveModuleData();
        ((CombinedTitleModuleData) this.mModuleData).discoveryBarModuleData = (DiscoveryBarModuleData) this.mDiscoveryBarModule.onSaveModuleData();
        ((CombinedTitleModuleData) this.mModuleData).warningMessageModuleData = (WarningMessageModuleData) this.mWarningMessageModule.onSaveModuleData();
        return (CombinedTitleModuleData) super.onSaveModuleData();
    }

    public void onDestroyModule() {
        for (int i = 0; i < this.mNestedModules.size(); i++) {
            ((FinskyModule) this.mNestedModules.get(i)).onDestroyModule();
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        this.mTitleModule.onPositiveClick(requestCode, extraArguments);
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        this.mTitleModule.onNegativeClick(requestCode, extraArguments);
    }

    public void refreshModule(FinskyModule module, boolean animateRefresh) {
        this.mFinskyModuleController.refreshModule(this, animateRefresh);
    }

    public void removeModule(FinskyModule module) {
        FinskyLog.wtf("Unsupported operation: nested module trying to remove itself", new Object[0]);
    }
}
