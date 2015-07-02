package com.google.android.finsky.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.DetailsFragment2;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.layout.DetailsColumnLayout;
import com.google.android.finsky.layout.DetailsExpandedContainer;
import com.google.android.finsky.layout.DummyOverlayableImageHost;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.ObservableScrollView.ScrollListener;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.scroll.DetailsScrollView;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Nfc;
import com.google.android.finsky.utils.Nfc.NfcHandler;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.VoucherUtils;

public abstract class DetailsDataBasedFragment extends UrlBasedPageFragment implements TextSectionStateListener {
    protected int mCardItemsPerRow;
    protected DetailsColumnLayout mColumnLayout;
    private DfeDetails mDetailsData;
    private Document mDocument;
    protected GenericUiElementNode mDocumentUiElementNode;
    protected DetailsExpandedContainer mExpandedContainer;
    private final Libraries mLibraries;
    private NfcHandler mNfcHandler;
    private long mPageCreationTime;
    protected HeroGraphicView mPromoHeroView;
    private PlayStoreUiElement mRootUiElementProto;
    protected Bundle mSavedInstanceState;
    protected ScrollListener mScrollListener;
    private boolean mSentImpression;
    protected DetailsScrollView mSingleColumnScroller;
    protected boolean mUseWideLayout;

    protected abstract int getPlayStoreUiElementType();

    protected abstract void rebindViews(Bundle bundle);

    protected abstract void recordState(Bundle bundle);

    public DetailsDataBasedFragment() {
        this.mSavedInstanceState = new Bundle();
        this.mRootUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(getPlayStoreUiElementType());
        this.mDocumentUiElementNode = null;
        this.mSentImpression = false;
        this.mLibraries = FinskyApp.get().getLibraries();
    }

    protected final Libraries getLibraries() {
        return this.mLibraries;
    }

    protected final void recordState() {
        recordState(this.mSavedInstanceState);
    }

    protected DfeDetails getDetailsData() {
        return this.mDetailsData;
    }

    protected void setInitialDocument(Document document) {
        setArgument("finsky.DetailsDataBasedFragment.document", (Parcelable) document);
    }

    protected void updateDocument(Document document) {
        this.mDocument = document;
        int streamType = Integer.MIN_VALUE;
        if (this.mDocument.getBackend() == 2) {
            streamType = 3;
        }
        getActivity().setVolumeControlStream(streamType);
    }

    protected final void rebindViews() {
        FinskyEventLog.setServerLogCookie(this.mRootUiElementProto, this.mDetailsData.getServerLogsCookie());
        if (this.mDocument != null) {
            if (this.mDocumentUiElementNode == null) {
                this.mDocumentUiElementNode = new GenericUiElementNode(209, null, null, this);
            }
            this.mDocumentUiElementNode.setServerLogsCookie(this.mDocument.getServerLogsCookie());
            if (hasDetailsDataLoaded() && !this.mSentImpression) {
                childImpression(this.mDocumentUiElementNode);
                this.mSentImpression = true;
            }
        }
        rebindViews(this.mSavedInstanceState);
        FinskyLog.d("Page [class=%s] loaded in [%s ms] (hasDetailsDataLoaded? %b)", getClass().getSimpleName(), Long.valueOf(System.currentTimeMillis() - this.mPageCreationTime), Boolean.valueOf(hasDetailsDataLoaded()));
    }

    protected boolean hasDetailsDataLoaded() {
        return this.mDetailsData != null && this.mDetailsData.isReady();
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mPageCreationTime = System.currentTimeMillis();
        this.mDocument = (Document) getArguments().getParcelable("finsky.DetailsDataBasedFragment.document");
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = super.onCreateView(inflater, container, savedInstanceState);
        Resources res = FinskyApp.get().getResources();
        this.mUseWideLayout = res.getBoolean(R.bool.use_wide_details_layout);
        int gridColumnCount = UiUtils.getFeaturedGridColumnCount(res, 1.0d);
        if (res.getBoolean(R.bool.play_can_use_mini_cards)) {
            this.mCardItemsPerRow = res.getInteger(R.integer.related_items_per_row);
        } else {
            this.mCardItemsPerRow = gridColumnCount;
        }
        this.mColumnLayout = (DetailsColumnLayout) result.findViewById(R.id.details_layout);
        this.mSingleColumnScroller = (DetailsScrollView) result.findViewById(R.id.details_scroller);
        if (this.mColumnLayout != null) {
            this.mExpandedContainer = (DetailsExpandedContainer) this.mColumnLayout.findViewById(R.id.details_expanded_container);
            this.mColumnLayout.configure(this);
        }
        return result;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mNfcHandler = Nfc.getHandler(this);
        if (savedInstanceState != null) {
            this.mSavedInstanceState = savedInstanceState;
        }
        switchToBlank();
        rebindActionBar();
        if (this.mDetailsData == null) {
            requestData();
        } else {
            this.mDetailsData.addDataChangedListener(this);
            this.mDetailsData.addErrorListener(this);
        }
        onDataChanged();
    }

    public void onPause() {
        super.onPause();
        PreviewController.setupOnBackStackChangedListener(this.mNavigationManager);
        this.mNfcHandler.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mNfcHandler.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        recordState(this.mSavedInstanceState);
        if (this.mSavedInstanceState != null) {
            bundle.putAll(this.mSavedInstanceState);
        }
        super.onSaveInstanceState(bundle);
    }

    public boolean isDataReady() {
        return this.mDocument != null;
    }

    protected void requestData() {
        if (this.mDetailsData != null) {
            this.mDetailsData.removeDataChangedListener(this);
            this.mDetailsData.removeErrorListener(this);
        }
        this.mDetailsData = new DfeDetails(this.mDfeApi, this.mUrl, false, VoucherUtils.getVoucherIds(FinskyApp.get().getLibraries().getAccountLibrary(this.mDfeApi.getAccount())));
        this.mDetailsData.addDataChangedListener(this);
        this.mDetailsData.addErrorListener(this);
    }

    public void onDataChanged() {
        if (isAdded() && isDataReady()) {
            if (hasDetailsDataLoaded()) {
                if (this.mDetailsData.getDocument() == null) {
                    this.mPageFragmentHost.showErrorDialog(null, this.mContext.getString(R.string.details_page_error), true);
                } else {
                    updateDocument(this.mDetailsData.getDocument());
                }
            }
            this.mNfcHandler.onDataChanged();
            super.onDataChanged();
        }
    }

    public Document getDocument() {
        return this.mDocument;
    }

    public void onDestroyView() {
        if (this.mDetailsData != null) {
            this.mDetailsData.removeDataChangedListener(this);
            this.mDetailsData.removeErrorListener(this);
            if (this instanceof DetailsFragment2) {
                this.mDetailsData = null;
            }
        }
        super.onDestroyView();
    }

    protected void configurePromoHeroImage(View fragmentView, Document doc, Bundle savedInstanceState) {
        this.mPromoHeroView = (HeroGraphicView) fragmentView.findViewById(R.id.hero_promo);
        boolean shouldEnableActionBarOverlay = false;
        if (this.mPromoHeroView != null) {
            bindPromoHeroImage(doc, savedInstanceState);
            if (this.mUseWideLayout) {
                DummyOverlayableImageHost dummyHeader = (DummyOverlayableImageHost) this.mSingleColumnScroller.findViewById(R.id.dummy_header);
                if (dummyHeader != null) {
                    boolean z;
                    if (this.mPromoHeroView.isShowingNoImageFallbackFill()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    dummyHeader.configureHeroImage(z, HeroGraphicView.getDetailsHeroAspectRatio(doc, this.mUseWideLayout));
                }
            }
            if (this.mPromoHeroView.getVisibility() == 0) {
                shouldEnableActionBarOverlay = true;
            } else {
                shouldEnableActionBarOverlay = false;
            }
        }
        if (shouldEnableActionBarOverlay) {
            if (this.mScrollListener != null) {
                this.mSingleColumnScroller.removeOnScrollListener(this.mScrollListener);
            }
            this.mScrollListener = UiUtils.enableActionBarOverlayScrolling(this.mSingleColumnScroller, this.mColumnLayout.getLeadingHeroSectionId(), this.mActionBarController);
            this.mColumnLayout.enableActionBarOverlayScrolling();
            return;
        }
        UiUtils.disableActionBarOverlayScrolling(this.mSingleColumnScroller, this.mScrollListener, this.mActionBarController);
        this.mColumnLayout.disableActionBarOverlayScrolling();
    }

    protected void bindPromoHeroImage(Document doc, Bundle savedInstanceState) {
    }

    protected void configureContentView() {
        if (this.mUseWideLayout) {
            this.mSingleColumnScroller.addOnScrollListener(new ScrollListener() {
                public void onScroll(int left, int top) {
                    int wideHeroPromoHeight = DetailsDataBasedFragment.this.mPromoHeroView.getHeight();
                    DetailsDataBasedFragment.this.mPromoHeroView.setOverlayableImageTopPadding(-((int) ((((float) wideHeroPromoHeight) * 0.5f) * (((float) Math.max(0, top)) / ((float) wideHeroPromoHeight)))));
                }
            });
        }
    }

    public boolean onBackPressed() {
        if (this.mColumnLayout == null) {
            return false;
        }
        int currentlyExpandedSectionId = this.mColumnLayout.getCurrentlyExpandedSectionId();
        if (currentlyExpandedSectionId == 0) {
            return false;
        }
        onSectionCollapsed(currentlyExpandedSectionId);
        return true;
    }

    public void onSectionExpanded(int sectionLayoutId) {
        this.mSingleColumnScroller.cancelScrollAnimationsIfRunning();
        this.mColumnLayout.expandSection(sectionLayoutId, this.mNavigationManager, getDocument().getBackend(), this);
        this.mActionBarController.enterActionBarSectionExpandedMode(getDocument().getTitle(), this.mExpandedContainer);
    }

    public void onSectionCollapsed(int sectionLayoutId) {
        this.mColumnLayout.collapseCurrentlyExpandedSection();
        this.mActionBarController.exitActionBarSectionExpandedMode();
    }

    public final PlayStoreUiElement getPlayStoreUiElement() {
        return this.mRootUiElementProto;
    }
}
