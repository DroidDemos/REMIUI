package com.google.android.finsky.detailspage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.activities.BinderFactory;
import com.google.android.finsky.activities.DetailsSummaryViewBinder;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries.Listener;

public class TitleModule extends FinskyModule<TitleModuleData> implements Listener {
    private DetailsSummaryViewBinder mSummaryViewBinder;

    protected static class TitleModuleData extends ModuleData {
        Document document;

        protected TitleModuleData() {
        }
    }

    public int getLayoutResId() {
        return R.layout.title_module;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null) {
            setUpSummaryViewBinder();
            this.mLibraries.addListener(this);
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null) {
            this.mModuleData = new TitleModuleData();
            ((TitleModuleData) this.mModuleData).document = detailsDoc;
            setUpSummaryViewBinder();
            this.mLibraries.addListener(this);
        } else if (hasDetailsLoaded) {
            ((TitleModuleData) this.mModuleData).document = detailsDoc;
            this.mFinskyModuleController.refreshModule(this, false);
        }
    }

    private void setUpSummaryViewBinder() {
        if (this.mSummaryViewBinder != null) {
            this.mSummaryViewBinder.onDestroyView();
        }
        this.mSummaryViewBinder = BinderFactory.getSummaryViewBinder(this.mDfeToc, ((TitleModuleData) this.mModuleData).document.getBackend(), this.mDfeApi.getAccount());
        this.mSummaryViewBinder.init(this.mContext, this.mNavigationManager, this.mBitmapLoader, this.mDetailsFragment2, true, this.mContinueUrl, null, this.mParentNode);
    }

    public void bindView(View view) {
        this.mSummaryViewBinder.bind(((TitleModuleData) this.mModuleData).document, true, view);
    }

    public void onDestroyModule() {
        this.mSummaryViewBinder.onDestroyView();
        this.mLibraries.removeListener(this);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                this.mSummaryViewBinder.onPositiveClick(requestCode, extraArguments);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                Intent intent = new Intent("android.settings.WIFI_SETTINGS");
                intent.setFlags(537526272);
                this.mDetailsFragment2.startActivity(intent);
                return;
            default:
                return;
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                this.mSummaryViewBinder.onNegativeClick(requestCode, extraArguments);
                return;
            default:
                return;
        }
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        this.mFinskyModuleController.refreshModule(this, false);
    }
}
