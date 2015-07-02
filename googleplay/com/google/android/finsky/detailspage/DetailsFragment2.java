package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.DetailsDataBasedFragment;
import com.google.android.finsky.activities.ReviewFeedbackDialog.CommentRating;
import com.google.android.finsky.activities.ReviewFeedbackDialog.Listener;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.FinskyModuleController;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.detailspage.ModulesAdapter.Module;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.actionbar.ActionBarBackgroundUpdater;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.FinskyConfigurator;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class DetailsFragment2 extends DetailsDataBasedFragment implements Listener, SimpleAlertDialog.Listener, FinskyModuleController, InstallerListener {
    private ActionBarBackgroundUpdater mActionBarBackgroundUpdater;
    private List<Module> mAddedModules;
    private List<FinskyModule> mAllModules;
    private boolean mFetchSocialDetailsDocument;
    private boolean mHasSavedScrollState;
    private FinskyHeaderListLayout mHeaderListLayout;
    private HeroGraphicView mHeroGraphicView;
    private int mHeroSpacerHeight;
    private boolean mIsFirstTimeHeroBinding;
    private ModulesAdapter mModulesAdapter;
    private Boolean mPrevUseWideLayout;
    private RecyclerView mRecyclerView;
    private List<ModuleData> mSavedModuleData;
    private int mSavedScrollModuleHeight;
    private int mSavedScrollModuleIndex;
    private int mSavedScrollModuleOffset;
    private boolean mShowsHeroView;
    private SocialDetailsErrorListener mSocialDetailsErrorListener;
    private DfeDetails mSocialDfeDetails;

    private static class PlayHeaderListConfigurator extends FinskyConfigurator {
        private int mHeaderHeight;
        private boolean mIsShowingHeroImage;

        public PlayHeaderListConfigurator(Context context, boolean isShowingHeroImage, int headerHeight) {
            super(context);
            this.mIsShowingHeroImage = isShowingHeroImage;
            this.mHeaderHeight = headerHeight;
        }

        protected void addBackgroundView(LayoutInflater inflater, ViewGroup root) {
            if (this.mIsShowingHeroImage) {
                inflater.inflate(R.layout.hero_graphic, root);
            }
        }

        protected void addContentView(LayoutInflater inflater, ViewGroup root) {
            inflater.inflate(R.layout.header_list_recycler_view, root);
        }

        protected int getHeaderHeight() {
            return this.mHeaderHeight;
        }

        protected int getListViewId() {
            return R.id.recycler_view;
        }

        protected boolean alwaysUseFloatingBackground() {
            return !this.mIsShowingHeroImage;
        }

        protected float getBackgroundViewParallaxRatio() {
            return 0.5f;
        }

        protected int getToolbarTitleMode() {
            return this.mIsShowingHeroImage ? 0 : 1;
        }
    }

    private class SocialDetailsErrorListener implements ErrorListener {
        private SocialDetailsErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            FinskyLog.e("Volley error while fetching social details doc: %s", error.getClass());
            DetailsFragment2.this.mFetchSocialDetailsDocument = false;
            DetailsFragment2.this.onDataChanged();
        }
    }

    public DetailsFragment2() {
        this.mAddedModules = Lists.newArrayList();
        this.mSavedModuleData = Lists.newArrayList();
        this.mIsFirstTimeHeroBinding = true;
    }

    public static DetailsFragment2 newInstance(Document document, String url, String continueUrl, String dfeAccount, boolean acquisitionOverride) {
        DetailsFragment2 fragment = new DetailsFragment2();
        fragment.setDfeAccount(dfeAccount);
        fragment.setDfeTocAndUrl(FinskyApp.get().getToc(), url);
        fragment.setInitialDocument(document);
        fragment.setArgument("finsky.DetailsFragment.continueUrl", continueUrl);
        fragment.setArgument("finsky.DetailsFragment.acquisitionOverride", acquisitionOverride);
        return fragment;
    }

    public int getLayoutRes() {
        return R.layout.header_list_container;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = super.onCreateView(inflater, container, savedInstanceState);
        Context context = container.getContext();
        Document doc = getDocument();
        this.mShowsHeroView = HeroGraphicView.shouldShowDetailsHeroGraphic(doc, this.mUseWideLayout);
        this.mHeroSpacerHeight = HeroGraphicView.getDetailsHeroSpacerHeight(context, doc, this.mUseWideLayout);
        this.mHeaderListLayout = (FinskyHeaderListLayout) this.mDataView;
        this.mHeaderListLayout.configure(new PlayHeaderListConfigurator(context, this.mShowsHeroView, this.mHeroSpacerHeight));
        this.mHeaderListLayout.setFloatingControlsBackground(new ColorDrawable(CorpusResourceUtils.getPrimaryColor(getActivity(), doc.getBackend())));
        this.mActionBarBackgroundUpdater = new ActionBarBackgroundUpdater(this.mHeaderListLayout);
        this.mHeaderListLayout.setOnLayoutChangedListener(this.mActionBarBackgroundUpdater);
        this.mActionBarBackgroundUpdater.updateBackground();
        this.mHeroGraphicView = (HeroGraphicView) this.mHeaderListLayout.findViewById(R.id.hero_promo);
        this.mRecyclerView = (RecyclerView) this.mHeaderListLayout.findViewById(R.id.recycler_view);
        this.mRecyclerView.setSaveEnabled(false);
        this.mHeaderListLayout.configureEventInterception(this.mHeroGraphicView);
        FinskyApp.get().getInstaller().addListener(this);
        return result;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        if (this.mSocialDfeDetails != null) {
            this.mSocialDfeDetails.addDataChangedListener(this);
            this.mSocialDetailsErrorListener = new SocialDetailsErrorListener();
            this.mSocialDfeDetails.addErrorListener(this.mSocialDetailsErrorListener);
        }
        super.onActivityCreated(savedInstanceState);
    }

    protected void requestData() {
        super.requestData();
        boolean acquisitionOverride = getArguments().getBoolean("finsky.DetailsFragment.acquisitionOverride");
        Document doc = getDocument();
        if (acquisitionOverride) {
            String docId;
            String str = "mAcquisitionOverride true for docId=%s - I hope it came from deep link!";
            Object[] objArr = new Object[1];
            if (doc != null) {
                docId = doc.getDocId();
            } else {
                docId = null;
            }
            objArr[0] = docId;
            FinskyLog.w(str, objArr);
        }
        boolean isApp = doc.getBackend() == 3;
        this.mFetchSocialDetailsDocument = false;
        if (isApp) {
            String dfeAccountName = this.mDfeApi.getAccountName();
            String currentAccountName = FinskyApp.get().getCurrentAccountName();
            if (!dfeAccountName.equals(currentAccountName)) {
                FinskyLog.d("Using current account %s to fetch social details for %s", FinskyLog.scrubPii(currentAccountName), doc.getDocId());
                this.mFetchSocialDetailsDocument = true;
                requestSocialDetailsDocument(currentAccountName);
            }
            FinskyApp.get().getEventLogger(currentAccountName).logBackgroundEvent(new BackgroundEventBuilder(509).setDocument(getDocument().getDocId()).setOperationSuccess(this.mFetchSocialDetailsDocument).build());
        }
    }

    private void requestSocialDetailsDocument(String currentAccount) {
        if (this.mSocialDfeDetails != null) {
            this.mSocialDfeDetails.removeDataChangedListener(this);
            this.mSocialDfeDetails.removeErrorListener(this.mSocialDetailsErrorListener);
        }
        this.mSocialDfeDetails = new DfeDetails(FinskyApp.get().getDfeApi(currentAccount), this.mUrl, true, null);
        this.mSocialDfeDetails.addDataChangedListener(this);
        this.mSocialDetailsErrorListener = new SocialDetailsErrorListener();
        this.mSocialDfeDetails.addErrorListener(this.mSocialDetailsErrorListener);
    }

    private void setupModules(Document doc) {
        DfeApi socialDataDfeApi;
        if (this.mModulesAdapter != null) {
            FinskyLog.wtf("Modules system is already set up", new Object[0]);
        }
        String continueUrl = getArguments().getString("finsky.DetailsFragment.continueUrl");
        LayoutManager linearLayoutManager = new LinearLayoutManager(this.mRecyclerView.getContext());
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.addItemDecoration(new ModuleDividerItemDecoration(this.mContext));
        this.mRecyclerView.addItemDecoration(new ModuleMarginItemDecoration(this.mContext, this.mUseWideLayout));
        this.mAllModules = ModulesManager.generateModules(doc, this.mUseWideLayout);
        if (this.mSavedModuleData.isEmpty()) {
            while (this.mSavedModuleData.size() < this.mAllModules.size()) {
                this.mSavedModuleData.add(null);
            }
        }
        if (this.mPrevUseWideLayout != null) {
            ModulesManager.remapModuleDataAfterRotation(this.mSavedModuleData, doc, this.mPrevUseWideLayout.booleanValue(), this.mUseWideLayout);
        }
        if (this.mSavedModuleData.size() != this.mAllModules.size()) {
            FinskyLog.wtf("SavedModuleData list and AllModules list mismatch", new Object[0]);
        }
        if (this.mFetchSocialDetailsDocument) {
            socialDataDfeApi = FinskyApp.get().getDfeApi();
        } else {
            socialDataDfeApi = this.mDfeApi;
        }
        for (int i = 0; i < this.mAllModules.size(); i++) {
            FinskyModule module = (FinskyModule) this.mAllModules.get(i);
            module.init(getActivity(), this, getToc(), this.mDfeApi, socialDataDfeApi, this.mBitmapLoader, this.mNavigationManager, this, this.mUrl, continueUrl, getLibraries(), this.mUseWideLayout, this);
            module.onRestoreModuleData((ModuleData) this.mSavedModuleData.get(i));
            if (module.readyForDisplay()) {
                this.mAddedModules.add(this.mAllModules.get(i));
            }
        }
        this.mModulesAdapter = new ModulesAdapter(this.mAddedModules);
        this.mRecyclerView.setAdapter(this.mModulesAdapter);
        if (!this.mHasSavedScrollState) {
            return;
        }
        if (this.mSavedScrollModuleIndex == 0) {
            int i2 = this.mSavedScrollModuleOffset + this.mSavedScrollModuleHeight;
            linearLayoutManager.scrollToPositionWithOffset(0, Math.min(prevBottomOffset, this.mHeaderListLayout.getHeaderHeight()) - this.mHeaderListLayout.getHeaderHeight());
            return;
        }
        linearLayoutManager.scrollToPositionWithOffset(this.mAddedModules.indexOf(this.mAllModules.get(ModulesManager.remapModuleIndexAfterRotation(this.mSavedScrollModuleIndex, doc, this.mPrevUseWideLayout.booleanValue(), this.mUseWideLayout))), this.mSavedScrollModuleOffset);
    }

    protected void onInitViewBinders() {
    }

    protected void rebindViews(Bundle savedInstanceState) {
        Document doc = getDocument();
        if (doc != null) {
            DfeDetails socialDfeDetails;
            Document socialDoc;
            DfeDetails detailsData = getDetailsData();
            if (this.mFetchSocialDetailsDocument) {
                socialDfeDetails = this.mSocialDfeDetails;
            } else {
                socialDfeDetails = detailsData;
            }
            if (this.mFetchSocialDetailsDocument) {
                socialDoc = this.mSocialDfeDetails.getDocument();
            } else {
                socialDoc = doc;
            }
            boolean hasDetailsDataLoaded = this.mFetchSocialDetailsDocument ? hasDetailsDataLoaded() && this.mSocialDfeDetails.isReady() : hasDetailsDataLoaded();
            if (this.mModulesAdapter == null) {
                setupModules(doc);
            }
            for (int i = 0; i < this.mAllModules.size(); i++) {
                FinskyModule module = (FinskyModule) this.mAllModules.get(i);
                module.bindModule(hasDetailsDataLoaded, doc, detailsData, socialDoc, socialDfeDetails);
                if (module.readyForDisplay() && !this.mAddedModules.contains(module)) {
                    addModule(module);
                }
            }
            rebindHero(doc);
        }
    }

    private void rebindHero(Document doc) {
        if (this.mHeroGraphicView != null) {
            this.mShowsHeroView = HeroGraphicView.shouldShowDetailsHeroGraphic(doc, this.mUseWideLayout);
            int currHeaderHeight = this.mHeroSpacerHeight;
            this.mHeroSpacerHeight = HeroGraphicView.getDetailsHeroSpacerHeight(this.mContext, doc, this.mUseWideLayout);
            if (!(this.mHeroSpacerHeight == currHeaderHeight || this.mHeaderListLayout == null)) {
                this.mHeaderListLayout.setTabMode(2, this.mHeroSpacerHeight);
            }
            this.mHeroGraphicView.bindDetailsHero(doc, this.mBitmapLoader, this.mUseWideLayout, this);
            if (this.mIsFirstTimeHeroBinding && doc.getDocumentType() == 2) {
                int lengthToScroll = this.mHeaderListLayout.getHeaderHeight() - getResources().getDimensionPixelSize(R.dimen.hero_image_height);
                if (lengthToScroll > 0) {
                    ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(0, -lengthToScroll);
                }
            }
            this.mIsFirstTimeHeroBinding = false;
        }
    }

    public void rebindActionBar() {
        this.mActionBarController.enableActionBarOverlay();
        this.mPageFragmentHost.updateBreadcrumb(getDocument().getTitle());
        this.mPageFragmentHost.updateCurrentBackendId(getDocument().getBackend(), true);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    public void refreshModule(FinskyModule module, boolean animate) {
        if (this.mModulesAdapter != null) {
            if (!module.readyForDisplay()) {
                FinskyLog.wtf("FinskyModule that is not ready for display asked for refresh", new Object[0]);
            } else if (this.mAddedModules.contains(module)) {
                int position = this.mAddedModules.indexOf(module);
                if (animate) {
                    this.mModulesAdapter.refreshModuleWithAnimation(position);
                } else {
                    this.mModulesAdapter.refreshModuleWithoutAnimation(position);
                }
            } else {
                addModule(module);
            }
        }
    }

    public void removeModule(FinskyModule module) {
        if (this.mModulesAdapter != null && this.mAddedModules.contains(module)) {
            this.mModulesAdapter.removeModule(this.mAddedModules.indexOf(module));
            this.mAddedModules.remove(module);
        }
    }

    private void addModule(FinskyModule module) {
        if (this.mAddedModules.contains(module)) {
            FinskyLog.wtf("Trying to add a module that is already added", new Object[0]);
        } else if (module.readyForDisplay()) {
            int addPosition = 0;
            int i = 0;
            while (i < this.mAllModules.size() && addPosition != this.mAddedModules.size() && this.mAllModules.get(i) != module) {
                if (this.mAllModules.get(i) == this.mAddedModules.get(addPosition)) {
                    addPosition++;
                }
                i++;
            }
            this.mAddedModules.add(addPosition, module);
            this.mModulesAdapter.addModule(module, addPosition);
        } else {
            FinskyLog.wtf("Trying to add a module that is not ready for display", new Object[0]);
        }
    }

    public void recordState(Bundle savedInstanceState) {
    }

    public void onDestroyView() {
        int firstVisiblePosition = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        View firstVisibleView = this.mRecyclerView.getChildAt(0);
        if (firstVisibleView != null) {
            this.mHasSavedScrollState = true;
            this.mSavedScrollModuleIndex = this.mAllModules.indexOf(this.mAddedModules.get(firstVisiblePosition));
            this.mSavedScrollModuleOffset = firstVisibleView.getTop();
            this.mSavedScrollModuleHeight = firstVisibleView.getHeight();
        }
        this.mPrevUseWideLayout = Boolean.valueOf(this.mUseWideLayout);
        this.mModulesAdapter.onDestroyed();
        this.mSavedModuleData.clear();
        for (int i = 0; i < this.mAllModules.size(); i++) {
            FinskyModule module = (FinskyModule) this.mAllModules.get(i);
            this.mSavedModuleData.add(module.onSaveModuleData());
            module.onDestroyModule();
        }
        if (this.mSocialDfeDetails != null) {
            this.mSocialDfeDetails.removeDataChangedListener(this);
            this.mSocialDfeDetails.removeErrorListener(this.mSocialDetailsErrorListener);
        }
        this.mHeaderListLayout.detach();
        if (this.mActionBarBackgroundUpdater != null) {
            this.mActionBarBackgroundUpdater.resetBackground();
        }
        FinskyApp.get().getInstaller().removeListener(this);
        this.mHeaderListLayout = null;
        this.mActionBarBackgroundUpdater = null;
        this.mHeroGraphicView = null;
        this.mRecyclerView = null;
        this.mModulesAdapter = null;
        this.mAllModules.clear();
        this.mAddedModules.clear();
        super.onDestroyView();
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        Document doc = getDocument();
        if (doc != null && doc.getDocumentType() == 1 && packageName.equals(doc.getAppDetails().packageName)) {
            rebindActionBar();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (int i = 0; i < this.mAllModules.size(); i++) {
            ((FinskyModule) this.mAllModules.get(i)).onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        for (int i = 0; i < this.mAllModules.size(); i++) {
            ((FinskyModule) this.mAllModules.get(i)).onPositiveClick(requestCode, extraArguments);
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        for (int i = 0; i < this.mAllModules.size(); i++) {
            ((FinskyModule) this.mAllModules.get(i)).onNegativeClick(requestCode, extraArguments);
        }
    }

    public void onReviewFeedback(String docId, String reviewId, CommentRating newRating) {
        for (int i = 0; i < this.mAllModules.size(); i++) {
            ((FinskyModule) this.mAllModules.get(i)).onReviewFeedback(docId, reviewId, newRating);
        }
    }

    protected int getPlayStoreUiElementType() {
        return 2;
    }

    protected int getActionBarColor() {
        return CorpusResourceUtils.getPrimaryColor(this.mContext, getDocument().getBackend());
    }
}
