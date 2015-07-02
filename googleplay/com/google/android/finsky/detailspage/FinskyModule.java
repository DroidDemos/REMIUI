package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.activities.ReviewFeedbackDialog.CommentRating;
import com.google.android.finsky.activities.ReviewFeedbackDialog.Listener;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.ModulesAdapter.Module;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.play.image.BitmapLoader;

public abstract class FinskyModule<T extends ModuleData> extends Module implements Listener, SimpleAlertDialog.Listener {
    protected BitmapLoader mBitmapLoader;
    protected Context mContext;
    protected String mContinueUrl;
    protected DetailsFragment2 mDetailsFragment2;
    protected String mDetailsUrl;
    protected DfeApi mDfeApi;
    protected DfeToc mDfeToc;
    protected FinskyModuleController mFinskyModuleController;
    protected Libraries mLibraries;
    protected T mModuleData;
    protected NavigationManager mNavigationManager;
    protected PlayStoreUiElementNode mParentNode;
    protected DfeApi mSocialDfeApi;
    protected boolean mUseWideLayout;

    public static class ModuleData {
    }

    public interface FinskyModuleController {
        void refreshModule(FinskyModule finskyModule, boolean z);

        void removeModule(FinskyModule finskyModule);
    }

    public abstract void bindModule(boolean z, Document document, DfeDetails dfeDetails, Document document2, DfeDetails dfeDetails2);

    public abstract boolean readyForDisplay();

    public void init(Context context, FinskyModuleController finskyModuleController, DfeToc dfeToc, DfeApi dfeApi, DfeApi socialDfeApi, BitmapLoader bitmapLoader, NavigationManager navigationManager, DetailsFragment2 detailsFragment2, String detailsUrl, String continueUrl, Libraries libraries, boolean useWideLayout, PlayStoreUiElementNode parentNode) {
        this.mContext = context;
        this.mFinskyModuleController = finskyModuleController;
        this.mDfeToc = dfeToc;
        this.mDfeApi = dfeApi;
        this.mSocialDfeApi = socialDfeApi;
        this.mBitmapLoader = bitmapLoader;
        this.mNavigationManager = navigationManager;
        this.mDetailsFragment2 = detailsFragment2;
        this.mDetailsUrl = detailsUrl;
        this.mContinueUrl = continueUrl;
        this.mLibraries = libraries;
        this.mUseWideLayout = useWideLayout;
        this.mParentNode = parentNode;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        this.mModuleData = savedModuleData;
    }

    public T onSaveModuleData() {
        return this.mModuleData;
    }

    public void onDestroyModule() {
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onReviewFeedback(String docId, String reviewId, CommentRating newRating) {
    }
}
