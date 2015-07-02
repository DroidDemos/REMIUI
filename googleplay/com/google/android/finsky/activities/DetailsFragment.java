package com.google.android.finsky.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.SharedElementCallback;
import android.text.Html;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ReviewFeedbackDialog.CommentRating;
import com.google.android.finsky.activities.ReviewFeedbackDialog.Listener;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.experiments.FinskyExperiments;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.AvatarHeader;
import com.google.android.finsky.layout.DetailsAvatarClusterSection;
import com.google.android.finsky.layout.DetailsBylinesSection;
import com.google.android.finsky.layout.DetailsColumnLayout;
import com.google.android.finsky.layout.DetailsFlagItemSection;
import com.google.android.finsky.layout.DetailsInnerColumnLayout;
import com.google.android.finsky.layout.DetailsPackSection;
import com.google.android.finsky.layout.DetailsPartialFadeSection;
import com.google.android.finsky.layout.DetailsSecondaryActionsSection;
import com.google.android.finsky.layout.DetailsSummary;
import com.google.android.finsky.layout.DetailsTextSection;
import com.google.android.finsky.layout.DiscoveryBar;
import com.google.android.finsky.layout.EpisodeList;
import com.google.android.finsky.layout.EpisodeList.SeasonSelectionListener;
import com.google.android.finsky.layout.GooglePlusShareSection;
import com.google.android.finsky.layout.RateReviewSection;
import com.google.android.finsky.layout.ReviewSamplesSection;
import com.google.android.finsky.layout.ScreenshotGallery;
import com.google.android.finsky.layout.SongList;
import com.google.android.finsky.layout.SubscriptionsSection;
import com.google.android.finsky.layout.WarningMessageSection;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.transition.DetailsExitTransition;
import com.google.android.finsky.transition.ReverseContentTransition;
import com.google.android.finsky.transition.ReverseHeroTransition;
import com.google.android.finsky.utils.BgDataDisabledError;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntMath;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.transition.BaseTransitionListener;
import java.util.List;
import java.util.Map;

public class DetailsFragment extends DetailsDataBasedFragment implements Listener, SimpleAlertDialog.Listener, TextSectionStateListener, InstallerListener, SeasonSelectionListener, Libraries.Listener {
    private boolean mAcquisitionOverride;
    private String mContinueUrl;
    private FifeImageView mCoverInSource;
    private DetailsSummary mDetailsPanel;
    private EpisodeList mEpisodeList;
    private boolean mFetchSocialDetailsDocument;
    private final int mMaxCreatorBodyOfWorksRows;
    private final int mMaxCreatorMoreByRows;
    private final int mMaxRelatedItemRows;
    private final int mMaxRelatedMusicItemRows;
    private Bitmap mOriginalCoverBitmap;
    private RateReviewSection mRateReviewSection;
    private int mRelatedMusicCardItemsPerRow;
    private String mRevealTransitionCoverName;
    private Interpolator mRevealTransitionInterpolator;
    private String mRevealTransitionPrimaryContainerName;
    private ReviewDialogListener mReviewDialogListener;
    private final SeasonListViewBinder mSeasonListViewBinder;
    private Handler mSecondaryDelayedLoadingHandler;
    private SocialDetailsErrorListener mSocialDetailsErrorListener;
    private DfeDetails mSocialDfeDetails;
    private final SongListViewBinder mSongListViewBinder;
    private int mSourceHeight;
    private int mSourceLeft;
    private int mSourceTop;
    private int mSourceWidth;
    private final SubscriptionsViewBinder mSubscriptionsViewBinder;
    private DetailsSummaryViewBinder mSummaryViewBinder;
    private Boolean mWasOwnedWhenPageLoaded;

    private class SocialDetailsErrorListener implements ErrorListener {
        private SocialDetailsErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            FinskyLog.e("Volley error while fetching social details doc: %s", error.getClass());
            DetailsFragment.this.mFetchSocialDetailsDocument = false;
            DetailsFragment.this.onDataChanged();
        }
    }

    public static DetailsFragment newInstance(Document document, String url, String continueUrl, String dfeAccount, boolean acquisitionOverride, View sharedPrimaryContainer, View sharedCoverView) {
        FinskyApp finskyApp = FinskyApp.get();
        DetailsFragment fragment = new DetailsFragment();
        fragment.setDfeAccount(dfeAccount);
        fragment.setDfeTocAndUrl(finskyApp.getToc(), url);
        fragment.setInitialDocument(document);
        fragment.setArgument("finsky.DetailsFragment.continueUrl", continueUrl);
        fragment.setArgument("finsky.DetailsFragment.acquisitionOverride", acquisitionOverride);
        boolean hasSharedElements = (sharedPrimaryContainer == null || sharedCoverView == null) ? false : true;
        if (NavigationManager.areTransitionsEnabled() && hasSharedElements && !finskyApp.getResources().getBoolean(R.bool.use_wide_details_layout)) {
            fragment.mRevealTransitionPrimaryContainerName = sharedPrimaryContainer.getTransitionName();
            fragment.mRevealTransitionCoverName = sharedCoverView.getTransitionName();
            Drawable sourceCoverDrawable = ((ImageView) sharedCoverView).getDrawable();
            if (sourceCoverDrawable != null) {
                fragment.mOriginalCoverBitmap = ((BitmapDrawable) sourceCoverDrawable).getBitmap();
            }
            fragment.configureEnterTransition(finskyApp);
            fragment.configureEnterSharedElementCallback();
        }
        return fragment;
    }

    public DetailsFragment() {
        this.mSeasonListViewBinder = new SeasonListViewBinder();
        this.mSongListViewBinder = new SongListViewBinder();
        this.mSubscriptionsViewBinder = new SubscriptionsViewBinder();
        this.mWasOwnedWhenPageLoaded = null;
        Resources res = FinskyApp.get().getResources();
        this.mMaxCreatorMoreByRows = res.getInteger(R.integer.creator_moreby_item_rows);
        this.mMaxCreatorBodyOfWorksRows = res.getInteger(R.integer.creator_body_of_works_rows);
        this.mMaxRelatedItemRows = res.getInteger(R.integer.related_item_rows);
        this.mMaxRelatedMusicItemRows = res.getInteger(R.integer.music_item_rows);
        this.mSecondaryDelayedLoadingHandler = new Handler(Looper.getMainLooper());
        if (!NavigationManager.areTransitionsEnabled()) {
            return;
        }
        if (this.mUseWideLayout) {
            setExitTransition(null);
            return;
        }
        DetailsExitTransition exitTransition = new DetailsExitTransition();
        exitTransition.setInterpolator(AnimationUtils.loadInterpolator(FinskyApp.get(), 17563661));
        exitTransition.setDuration(400);
        setExitTransition(exitTransition);
    }

    protected void onInitViewBinders() {
        this.mSongListViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager);
        this.mSubscriptionsViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, FinskyApp.get().getLibraries());
        this.mSeasonListViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this, this.mBitmapLoader, FinskyApp.get().getLibraries(), this);
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
        this.mContinueUrl = getArguments().getString("finsky.DetailsFragment.continueUrl");
        this.mAcquisitionOverride = getArguments().getBoolean("finsky.DetailsFragment.acquisitionOverride");
        Document doc = getDocument();
        if (this.mAcquisitionOverride) {
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

    protected int getLayoutRes() {
        return R.layout.details_frame;
    }

    private int getRepresentativeBackendId() {
        return getDocument().getBackend();
    }

    protected void rebindViews(Bundle savedInstanceState) {
        rebindActionBar();
        boolean toDelaySecondarySectionsLoad = NavigationManager.areTransitionsEnabled() && !TextUtils.isEmpty(this.mRevealTransitionCoverName);
        updateDetailsSections(savedInstanceState, toDelaySecondarySectionsLoad);
    }

    public void rebindActionBar() {
        this.mPageFragmentHost.updateBreadcrumb(getDocument().getTitle());
        this.mPageFragmentHost.updateActionBarMode(false);
        if (isDataReady()) {
            this.mPageFragmentHost.updateCurrentBackendId(getDocument().getBackend(), false);
        }
    }

    private void updateDetailsSections(Bundle savedInstanceState, boolean delaySecondaryLoad) {
        Document doc = getDocument();
        if (doc != null) {
            DfeDetails socialDfeDetails;
            Document socialDoc;
            if (this.mWasOwnedWhenPageLoaded == null) {
                if (savedInstanceState != null) {
                    if (savedInstanceState.containsKey("finsky.DetailsFragment.wasDocOwnedWhenPageWasLoaded")) {
                        this.mWasOwnedWhenPageLoaded = Boolean.valueOf(savedInstanceState.getBoolean("finsky.DetailsFragment.wasDocOwnedWhenPageWasLoaded"));
                    }
                }
                this.mWasOwnedWhenPageLoaded = Boolean.valueOf(LibraryUtils.isOwned(doc, getLibraries()));
            }
            View fragmentView = getView();
            inflateSectionsIfNecessary(doc, (DetailsInnerColumnLayout) fragmentView.findViewById(R.id.details_container));
            this.mDetailsPanel = (DetailsSummary) fragmentView.findViewById(R.id.item_details_panel);
            final DfeDetails detailsData = getDetailsData();
            DfeApi socialDataDfeApi = this.mFetchSocialDetailsDocument ? FinskyApp.get().getDfeApi() : this.mDfeApi;
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
            updatePrimaryDetailsSections(doc, savedInstanceState, socialDataDfeApi, socialDfeDetails, socialDoc, hasDetailsDataLoaded);
            if (delaySecondaryLoad) {
                final Document document = doc;
                final Bundle bundle = savedInstanceState;
                final DfeApi dfeApi = socialDataDfeApi;
                final DfeDetails dfeDetails = socialDfeDetails;
                final Document document2 = socialDoc;
                final boolean z = hasDetailsDataLoaded;
                this.mSecondaryDelayedLoadingHandler.postDelayed(new Runnable() {
                    public void run() {
                        DetailsFragment.this.updateSecondaryDetailsSections(document, bundle, detailsData, dfeApi, dfeDetails, document2, z);
                    }
                }, 400);
                return;
            }
            updateSecondaryDetailsSections(doc, savedInstanceState, detailsData, socialDataDfeApi, socialDfeDetails, socialDoc, hasDetailsDataLoaded);
        }
    }

    private void updatePrimaryDetailsSections(Document doc, Bundle savedInstanceState, DfeApi socialDataDfeApi, DfeDetails socialDfeDetails, Document socialDoc, boolean hasDetailsDataLoaded) {
        View fragmentView = getView();
        if (this.mDetailsPanel != null) {
            ViewGroup thumbnailFrame = (ViewGroup) fragmentView.findViewById(R.id.title_thumbnail_frame);
            this.mSummaryViewBinder.bind(doc, true, this.mDetailsPanel, thumbnailFrame);
            if (!(this.mOriginalCoverBitmap == null || TextUtils.isEmpty(this.mRevealTransitionCoverName))) {
                this.mSummaryViewBinder.setCoverFromBitmap(this.mOriginalCoverBitmap);
            }
        }
        configurePromoHeroImage(fragmentView, doc, savedInstanceState);
        WarningMessageSection warningMessagePanel = (WarningMessageSection) fragmentView.findViewById(R.id.warning_message_panel);
        if (warningMessagePanel != null) {
            warningMessagePanel.bind(doc, getToc(), getLibraries(), this.mDfeApi.getAccount());
        }
        DiscoveryBar discoveryBar = (DiscoveryBar) fragmentView.findViewById(R.id.discovery_bar);
        if (discoveryBar != null) {
            discoveryBar.configure(this.mNavigationManager, this.mBitmapLoader, socialDoc, socialDfeDetails != null ? socialDfeDetails.getDiscoveryBadges() : null, FinskyApp.get().getToc(), FinskyApp.get().getPackageManager(), hasDetailsDataLoaded, false, 0, this);
        }
    }

    private void updateSecondaryDetailsSections(Document doc, Bundle savedInstanceState, DfeDetails detailsData, DfeApi socialDataDfeApi, DfeDetails socialDfeDetails, Document socialDoc, boolean hasDetailsDataLoaded) {
        View fragmentView = getView();
        if (fragmentView != null) {
            int docType = doc.getDocumentType();
            Resources res = this.mContext.getResources();
            AvatarHeader creatorPageAvatarHeader = (AvatarHeader) fragmentView.findViewById(R.id.avatar_header);
            if (creatorPageAvatarHeader != null) {
                if (this.mDetailsPanel != null) {
                    creatorPageAvatarHeader.setVisibility(8);
                } else {
                    creatorPageAvatarHeader.setVisibility(0);
                    creatorPageAvatarHeader.bind(this.mNavigationManager, this.mBitmapLoader, doc, 4);
                }
            }
            SubscriptionsSection subscriptionsSection = (SubscriptionsSection) fragmentView.findViewById(R.id.subscriptions_section);
            if (subscriptionsSection != null) {
                this.mSubscriptionsViewBinder.bind(this, subscriptionsSection, this.mDfeApi, doc, R.layout.subscription_item, savedInstanceState, this);
            }
            this.mRateReviewSection = (RateReviewSection) fragmentView.findViewById(R.id.rate_and_review_section);
            if (this.mRateReviewSection != null) {
                if (getToc().isGplusSignupEnabled()) {
                    this.mRateReviewSection.configure(FinskyApp.get().getClientMutationCache(FinskyApp.get().getCurrentAccountName()), FinskyApp.get().getLibraries(), this, socialDoc, hasDetailsDataLoaded, socialDfeDetails != null ? socialDfeDetails.getUserReview() : null, this.mNavigationManager, this);
                } else {
                    this.mRateReviewSection.setVisibility(8);
                }
            }
            boolean isMagazine = doc.getBackend() == 6;
            boolean hasCreatorDoc = doc.hasCreatorDoc();
            DetailsPackSection creatorRelatedPanel = (DetailsPackSection) fragmentView.findViewById(R.id.creator_related_panel);
            DetailsAvatarClusterSection creatorEntrySection = (DetailsAvatarClusterSection) fragmentView.findViewById(R.id.creator_page_entry);
            if (hasCreatorDoc && creatorEntrySection != null) {
                if (creatorRelatedPanel != null) {
                    creatorRelatedPanel.setVisibility(8);
                }
                creatorEntrySection.setVisibility(0);
                creatorEntrySection.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
                String moreLabel = res.getString(R.string.see_more_results_no_count);
                if (doc.getDocumentType() == 5) {
                    moreLabel = res.getString(R.string.explore);
                }
                creatorEntrySection.bind(doc.getCreatorDoc(), doc.getCreator(), null, doc.getMoreByListUrl(), moreLabel, 4, 14, this.mCardItemsPerRow, this.mMaxCreatorMoreByRows, hasDetailsDataLoaded, this);
            } else if (creatorRelatedPanel != null) {
                if (creatorEntrySection != null) {
                    creatorEntrySection.setVisibility(8);
                }
                if (isMagazine) {
                    creatorRelatedPanel.setVisibility(8);
                } else {
                    creatorRelatedPanel.setVisibility(0);
                    creatorRelatedPanel.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
                    creatorRelatedPanel.bind(doc, doc.getMoreByHeader(), null, doc.getMoreByListUrl(), doc.getMoreByBrowseUrl(), this.mCardItemsPerRow, this.mMaxCreatorMoreByRows, hasDetailsDataLoaded, this);
                }
            }
            DetailsTextSection descriptionPanel = (DetailsTextSection) fragmentView.findViewById(R.id.description_panel);
            if (descriptionPanel != null) {
                boolean isOwned;
                if (docType == 1) {
                    String appPackageName = doc.getAppDetails().packageName;
                    isOwned = (FinskyApp.get().getPackageInfoRepository().get(appPackageName) != null) || (FinskyApp.get().getInstaller().getState(appPackageName) != InstallerState.NOT_TRACKED);
                } else {
                    isOwned = LibraryUtils.isOwned(doc, FinskyApp.get().getLibraries().getAccountLibrary(this.mDfeApi.getAccount()));
                }
                descriptionPanel.bindDescription(this.mNavigationManager, doc, hasDetailsDataLoaded, isOwned, savedInstanceState, this, this);
            }
            ReviewSamplesSection reviewsPanel = (ReviewSamplesSection) fragmentView.findViewById(R.id.sample_reviews_panel);
            if (reviewsPanel != null) {
                this.mReviewDialogListener = new ReviewDialogListener(this.mContext, this, FinskyApp.get().getCurrentAccountName(), socialDoc, socialDfeDetails, reviewsPanel, this.mRateReviewSection);
                reviewsPanel.bind(socialDataDfeApi, socialDoc, hasDetailsDataLoaded, this, this.mNavigationManager, this);
            }
            DetailsSecondaryActionsSection secondaryActionsPanel = (DetailsSecondaryActionsSection) fragmentView.findViewById(R.id.secondary_actions_panel);
            if (secondaryActionsPanel != null) {
                secondaryActionsPanel.bind(socialDoc, hasDetailsDataLoaded, this.mUrl, savedInstanceState, socialDataDfeApi, this);
            }
            DetailsBylinesSection bylinesPanel = (DetailsBylinesSection) fragmentView.findViewById(R.id.bylines_panel);
            if (bylinesPanel != null) {
                bylinesPanel.bind(doc, hasDetailsDataLoaded, this);
            }
            DetailsPackSection relatedPanel = (DetailsPackSection) fragmentView.findViewById(R.id.related_panel);
            if (relatedPanel != null) {
                boolean displayRelated = doc.getBackend() != 3 || doc.hasCreatorRelatedContent() || (creatorRelatedPanel == null && creatorEntrySection == null);
                if (displayRelated) {
                    boolean isMusic = doc.getBackend() == 2;
                    int itemsMaxRows = isMusic ? this.mMaxRelatedMusicItemRows : this.mMaxRelatedItemRows;
                    int itemsPerRow = isMusic ? this.mRelatedMusicCardItemsPerRow : this.mCardItemsPerRow;
                    relatedPanel.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
                    relatedPanel.bind(doc, doc.getRelatedHeader(), null, doc.getRelatedListUrl(), doc.getRelatedBrowseUrl(), itemsPerRow, itemsMaxRows, hasDetailsDataLoaded, this);
                } else {
                    relatedPanel.setVisibility(8);
                }
            }
            DetailsPackSection crossSellPanel = (DetailsPackSection) fragmentView.findViewById(R.id.cross_sell_panel);
            if (crossSellPanel != null) {
                if (doc.hasCrossSell()) {
                    crossSellPanel.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
                    crossSellPanel.bind(doc, doc.getCrossSellHeader(), null, doc.getCrossSellListUrl(), doc.getCrossSellBrowseUrl(), this.mCardItemsPerRow, this.mMaxRelatedItemRows, hasDetailsDataLoaded, this);
                } else {
                    crossSellPanel.setVisibility(8);
                }
            }
            DetailsPackSection postPurchasePanel = (DetailsPackSection) fragmentView.findViewById(R.id.post_purchase_panel);
            if (postPurchasePanel != null) {
                boolean isSectionEnabled;
                SectionMetadata crossSellSection;
                boolean wasNotInLibraryWhenPageWasLoaded;
                boolean wasPurchasedInPageLifetime;
                FinskyExperiments experiments = FinskyApp.get().getExperiments();
                if (!(((Boolean) G.forcePostPurchaseCrossSell.get()).booleanValue() || doc.getBackend() == 3)) {
                    if (!experiments.isEnabled("cl:ui.enable_post_purchase_xsell_for_all_corpora")) {
                        isSectionEnabled = false;
                        crossSellSection = doc.getPostPurchaseCrossSellSection() == null ? doc.getPostPurchaseCrossSellSection() : doc.getCrossSellSection();
                        wasNotInLibraryWhenPageWasLoaded = this.mWasOwnedWhenPageLoaded == null && !this.mWasOwnedWhenPageLoaded.booleanValue();
                        if (wasNotInLibraryWhenPageWasLoaded) {
                            if (LibraryUtils.isOwned(doc, getLibraries())) {
                                wasPurchasedInPageLifetime = true;
                                if (isSectionEnabled || crossSellSection == null || !wasPurchasedInPageLifetime) {
                                    postPurchasePanel.setVisibility(8);
                                } else {
                                    postPurchasePanel.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
                                    postPurchasePanel.bind(doc, crossSellSection.header, null, crossSellSection.listUrl, null, this.mCardItemsPerRow, this.mMaxRelatedItemRows, hasDetailsDataLoaded, this);
                                }
                            }
                        }
                        wasPurchasedInPageLifetime = false;
                        if (isSectionEnabled) {
                        }
                        postPurchasePanel.setVisibility(8);
                    }
                }
                isSectionEnabled = true;
                if (doc.getPostPurchaseCrossSellSection() == null) {
                }
                if (this.mWasOwnedWhenPageLoaded == null) {
                }
                if (wasNotInLibraryWhenPageWasLoaded) {
                    if (LibraryUtils.isOwned(doc, getLibraries())) {
                        wasPurchasedInPageLifetime = true;
                        if (isSectionEnabled) {
                        }
                        postPurchasePanel.setVisibility(8);
                    }
                }
                wasPurchasedInPageLifetime = false;
                if (isSectionEnabled) {
                }
                postPurchasePanel.setVisibility(8);
            }
            DetailsPackSection moreByPanel = (DetailsPackSection) fragmentView.findViewById(R.id.core_content_panel);
            if (moreByPanel != null) {
                if (doc.hasMoreBy() && isMagazine) {
                    moreByPanel.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
                    moreByPanel.bind(doc, doc.getMoreByHeader(), null, doc.getMoreByListUrl(), doc.getMoreByBrowseUrl(), this.mCardItemsPerRow, this.mMaxRelatedItemRows, hasDetailsDataLoaded, this);
                } else {
                    moreByPanel.setVisibility(8);
                }
            }
            boolean isMovie = docType == 6;
            ScreenshotGallery screenshotsPanel = (ScreenshotGallery) fragmentView.findViewById(R.id.screenshots_panel);
            if (screenshotsPanel != null) {
                if (isMovie) {
                    screenshotsPanel.setVisibility(8);
                } else {
                    screenshotsPanel.bind(doc, this.mBitmapLoader, this.mNavigationManager, hasDetailsDataLoaded);
                }
            }
            DetailsFlagItemSection flagContentPanel = (DetailsFlagItemSection) fragmentView.findViewById(R.id.flag_content_panel);
            if (flagContentPanel != null) {
                flagContentPanel.bind(doc, this.mNavigationManager, hasDetailsDataLoaded, this);
            }
            SongList songList = (SongList) fragmentView.findViewById(R.id.song_list);
            if (songList != null) {
                if (!TextUtils.isEmpty(doc.getCoreContentListUrl())) {
                    boolean z;
                    int i;
                    songList.setVisibility(0);
                    boolean isArtist = docType == 3;
                    this.mSongListViewBinder.restoreInstanceState(savedInstanceState);
                    SongListViewBinder songListViewBinder = this.mSongListViewBinder;
                    Document document = getDocument();
                    String coreContentHeader = doc.getCoreContentHeader();
                    String coreContentListUrl = doc.getCoreContentListUrl();
                    if (isArtist) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (isArtist) {
                        i = 5;
                    } else {
                        i = Integer.MAX_VALUE;
                    }
                    songListViewBinder.bind(songList, document, coreContentHeader, null, coreContentListUrl, z, i, hasDetailsDataLoaded, getLibraries(), this.mBitmapLoader, this);
                } else if (TextUtils.isEmpty(doc.getRelatedDocTypeListUrl())) {
                    songList.setVisibility(8);
                } else {
                    songList.setVisibility(0);
                    this.mSongListViewBinder.bind(songList, null, doc.getRelatedDocTypeHeader(), null, doc.getRelatedDocTypeListUrl(), false, 5, hasDetailsDataLoaded, getLibraries(), this.mBitmapLoader, this);
                }
                if (songList.getVisibility() == 0) {
                    String songDocId = Uri.parse(this.mUrl).getQueryParameter("tid");
                    if (songDocId != null) {
                        songList.setHighlightedSong(songDocId, (ScrollView) getActivity().findViewById(R.id.details_scroller));
                    }
                } else {
                    songList.setVisibility(8);
                }
            }
            DetailsPackSection bodyOfWorkPanel = (DetailsPackSection) fragmentView.findViewById(R.id.body_of_work_panel);
            if (bodyOfWorkPanel != null) {
                bodyOfWorkPanel.setVisibility(0);
                bodyOfWorkPanel.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
                int numRows = this.mMaxCreatorBodyOfWorksRows;
                if (doc.getBackend() == 2 && doc.hasBodyOfWork()) {
                    bodyOfWorkPanel.setNoDataHeader(res.getString(R.string.no_albums_for_sale));
                    numRows = this.mMaxRelatedItemRows;
                }
                bodyOfWorkPanel.bind(doc, doc.getBodyOfWorkHeader(), null, doc.getBodyOfWorkListUrl(), doc.getBodyOfWorkBrowseUrl(), this.mCardItemsPerRow, numRows, hasDetailsDataLoaded, this);
            }
            GooglePlusShareSection shareSection = (GooglePlusShareSection) fragmentView.findViewById(R.id.share_panel);
            if (shareSection != null) {
                shareSection.bind(doc, this, hasDetailsDataLoaded, this);
            }
            DetailsTextSection aboutAuthorPanel = (DetailsTextSection) fragmentView.findViewById(R.id.about_author_panel);
            if (aboutAuthorPanel != null) {
                if (hasCreatorDoc) {
                    aboutAuthorPanel.setVisibility(8);
                } else {
                    aboutAuthorPanel.setVisibility(0);
                    aboutAuthorPanel.bindAboutAuthor(this.mNavigationManager, doc, hasDetailsDataLoaded, savedInstanceState, this, this);
                }
            }
            this.mEpisodeList = (EpisodeList) fragmentView.findViewById(R.id.episode_list);
            if (this.mEpisodeList != null) {
                this.mEpisodeList.addSeasonSelectionListener(this);
                if (docType != 18 || TextUtils.isEmpty(doc.getCoreContentListUrl())) {
                    this.mEpisodeList.setVisibility(8);
                } else {
                    this.mEpisodeList.setVisibility(0);
                    Uri detailsUri = Uri.parse(this.mUrl);
                    this.mSeasonListViewBinder.bind(getLibraries(), this.mEpisodeList, detailsUri.getQueryParameter("cdid"), detailsUri.getQueryParameter("gdid"), doc.getCoreContentHeader(), null, doc.getCoreContentListUrl(), hasDetailsDataLoaded, this);
                    this.mSeasonListViewBinder.restoreInstanceState(savedInstanceState);
                }
            }
            TextView footerPanel = (TextView) fragmentView.findViewById(R.id.details_generic_footer);
            if (footerPanel != null) {
                String footerHtml = detailsData.getFooterHtml();
                if (TextUtils.isEmpty(footerHtml) || !hasDetailsDataLoaded) {
                    footerPanel.setVisibility(8);
                } else {
                    footerPanel.setVisibility(0);
                    footerPanel.setText(Html.fromHtml(footerHtml));
                }
            }
            View loadingSection = fragmentView.findViewById(R.id.loading_footer);
            boolean screenshotsVisible = screenshotsPanel != null && screenshotsPanel.getVisibility() == 0;
            if ((hasDetailsDataLoaded || screenshotsVisible) && loadingSection != null) {
                loadingSection.setVisibility(8);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void bindPromoHeroImage(com.google.android.finsky.api.model.Document r18, android.os.Bundle r19) {
        /*
        r17 = this;
        r0 = r17;
        r2 = r0.mUseWideLayout;
        if (r2 != 0) goto L_0x002b;
    L_0x0006:
        r5 = 1;
    L_0x0007:
        r14 = r18.getDocumentType();
        switch(r14) {
            case 1: goto L_0x002d;
            case 3: goto L_0x005e;
            case 6: goto L_0x004e;
            case 8: goto L_0x003d;
            case 16: goto L_0x007e;
            case 17: goto L_0x007e;
            case 18: goto L_0x006e;
            case 19: goto L_0x006e;
            case 20: goto L_0x006e;
            case 30: goto L_0x003d;
            default: goto L_0x000e;
        };
    L_0x000e:
        r0 = r17;
        r2 = r0.mUseWideLayout;
        if (r2 == 0) goto L_0x00b3;
    L_0x0014:
        r2 = 5;
        if (r14 != r2) goto L_0x008e;
    L_0x0017:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r3 = r18.getBackend();
        r2.setCorpusForFill(r3);
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r3 = 2;
        r2.setCorpusFillMode(r3);
    L_0x002a:
        return;
    L_0x002b:
        r5 = 0;
        goto L_0x0007;
    L_0x002d:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r0 = r17;
        r3 = r0.mBitmapLoader;
        r0 = r18;
        r1 = r17;
        r2.bindDetailsAppPromo(r0, r3, r5, r1);
        goto L_0x002a;
    L_0x003d:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r0 = r17;
        r3 = r0.mBitmapLoader;
        r4 = 0;
        r0 = r18;
        r1 = r17;
        r2.bindDetailsCreator(r0, r3, r4, r1);
        goto L_0x002a;
    L_0x004e:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r0 = r17;
        r3 = r0.mBitmapLoader;
        r0 = r18;
        r1 = r17;
        r2.bindDetailsMovieTrailer(r0, r3, r5, r1);
        goto L_0x002a;
    L_0x005e:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r0 = r17;
        r3 = r0.mBitmapLoader;
        r0 = r18;
        r1 = r17;
        r2.bindDetailsArtist(r0, r3, r5, r1);
        goto L_0x002a;
    L_0x006e:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r0 = r17;
        r3 = r0.mBitmapLoader;
        r0 = r18;
        r1 = r17;
        r2.bindDetailsTvShow(r0, r3, r5, r1);
        goto L_0x002a;
    L_0x007e:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r0 = r17;
        r3 = r0.mBitmapLoader;
        r0 = r18;
        r1 = r17;
        r2.bindNewsstand(r0, r3, r5, r1);
        goto L_0x002a;
    L_0x008e:
        r2 = 2;
        if (r14 != r2) goto L_0x00a1;
    L_0x0091:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r0 = r17;
        r3 = r0.mBitmapLoader;
        r0 = r18;
        r1 = r17;
        r2.bindDetailsAlbumWithArtist(r0, r3, r1);
        goto L_0x002a;
    L_0x00a1:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r0 = r17;
        r4 = r0.mBitmapLoader;
        r6 = 0;
        r3 = r18;
        r7 = r17;
        r2.bindDetailsDefault(r3, r4, r5, r6, r7);
        goto L_0x002a;
    L_0x00b3:
        r2 = 5;
        if (r14 != r2) goto L_0x00c1;
    L_0x00b6:
        r0 = r17;
        r2 = r0.mPromoHeroView;
        r3 = 8;
        r2.setVisibility(r3);
        goto L_0x002a;
    L_0x00c1:
        r2 = 2;
        if (r14 != r2) goto L_0x0111;
    L_0x00c4:
        r16 = 1;
    L_0x00c6:
        if (r16 == 0) goto L_0x0114;
    L_0x00c8:
        r2 = com.google.android.finsky.FinskyApp.get();
        r2 = r2.getExperiments();
        r3 = "cl:details.album_start_cover_expanded";
        r2 = r2.isEnabled(r3);
        if (r2 == 0) goto L_0x0114;
    L_0x00d8:
        r13 = 1;
    L_0x00d9:
        r2 = com.google.android.finsky.utils.FinskyPreferences.lastAutomaticHeroSequenceOnDetailsTimeShown;
        r3 = r18.getBackend();
        r2 = r2.get(r3);
        r15 = r2.exists();
        if (r13 != 0) goto L_0x0116;
    L_0x00e9:
        if (r15 != 0) goto L_0x0116;
    L_0x00eb:
        if (r19 == 0) goto L_0x00f7;
    L_0x00ed:
        r2 = "finsky.DetailsFragment.afterFirstLoad";
        r0 = r19;
        r2 = r0.containsKey(r2);
        if (r2 != 0) goto L_0x0116;
    L_0x00f7:
        r12 = 1;
    L_0x00f8:
        r0 = r17;
        r6 = r0.mSingleColumnScroller;
        r0 = r17;
        r7 = r0.mColumnLayout;
        r0 = r17;
        r9 = r0.mPromoHeroView;
        r0 = r17;
        r10 = r0.mBitmapLoader;
        r8 = r18;
        r11 = r17;
        r6.bindDetailsHero(r7, r8, r9, r10, r11, r12, r13);
        goto L_0x002a;
    L_0x0111:
        r16 = 0;
        goto L_0x00c6;
    L_0x0114:
        r13 = 0;
        goto L_0x00d9;
    L_0x0116:
        r12 = 0;
        goto L_0x00f8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.activities.DetailsFragment.bindPromoHeroImage(com.google.android.finsky.api.model.Document, android.os.Bundle):void");
    }

    protected void inflateSectionsIfNecessary(Document doc, DetailsInnerColumnLayout detailsContainer) {
        LayoutInflater layoutInflater = LayoutInflater.from(detailsContainer.getContext());
        int currSectionOrderId = R.layout.details_section_generic;
        switch (doc.getDocumentType()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                currSectionOrderId = R.layout.details_section_album;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                currSectionOrderId = R.layout.details_section_artist;
                break;
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                currSectionOrderId = R.layout.details_section_tv;
                break;
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                currSectionOrderId = R.layout.details_section_creator;
                break;
        }
        if (detailsContainer.getChildCount() == 0) {
            if (this.mUseWideLayout) {
                layoutInflater.inflate(R.layout.details_dummy_header, detailsContainer, true);
            } else {
                layoutInflater.inflate(R.layout.hero_graphic, detailsContainer, true);
            }
            layoutInflater.inflate(currSectionOrderId, detailsContainer, true);
        }
        if (NavigationManager.areTransitionsEnabled()) {
            DetailsColumnLayout detailsColumnLayout = this.mColumnLayout;
            r0.getCardTransitionTarget().setTransitionName(this.mRevealTransitionPrimaryContainerName);
            if (!TextUtils.isEmpty(this.mRevealTransitionCoverName)) {
                Transition heroSlideFade = new ReverseHeroTransition();
                heroSlideFade.setInterpolator(this.mRevealTransitionInterpolator);
                heroSlideFade.addTarget(R.id.hero_promo);
                heroSlideFade.setDuration(400);
                Transition contentFade = new Fade();
                Transition contentSlide = new ReverseContentTransition();
                contentSlide.setInterpolator(this.mRevealTransitionInterpolator);
                int sectionCount = detailsContainer.getChildCount();
                List<Integer> idsToFade = Lists.newArrayList();
                for (int i = 0; i < sectionCount; i++) {
                    View child = detailsContainer.getChildAt(i);
                    View view = this.mPromoHeroView;
                    if (child != r0) {
                        if (child instanceof DetailsPartialFadeSection) {
                            ((DetailsPartialFadeSection) child).addParticipatingChildViewIds(idsToFade);
                        } else {
                            contentSlide.addTarget(child.getId());
                        }
                    }
                }
                for (Integer idToFade : idsToFade) {
                    contentFade.addTarget(idToFade.intValue());
                }
                contentFade.setDuration(100);
                contentSlide.setDuration(400);
                Transition titleBackgroundFade = new Fade();
                titleBackgroundFade.addTarget(R.id.title_background);
                titleBackgroundFade.setDuration(200);
                TransitionSet set = new TransitionSet();
                set.addTransition(heroSlideFade);
                set.addTransition(titleBackgroundFade);
                set.addTransition(contentFade);
                set.addTransition(contentSlide);
                setEnterTransition(set);
            }
        }
    }

    public boolean isDataReady() {
        if (!this.mFetchSocialDetailsDocument || this.mSocialDfeDetails.isReady()) {
            return super.isDataReady();
        }
        return false;
    }

    public void onDataChanged() {
        if (isAdded() && isDataReady() && this.mSummaryViewBinder == null) {
            this.mSummaryViewBinder = BinderFactory.getSummaryViewBinder(getToc(), getRepresentativeBackendId(), this.mDfeApi.getAccount());
            this.mSummaryViewBinder.init(this.mContext, this.mNavigationManager, this.mBitmapLoader, this, true, this.mContinueUrl, this.mRevealTransitionCoverName, this);
        }
        super.onDataChanged();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getLibraries().addListener(this);
        FinskyApp.get().getInstaller().addListener(this);
        configureContentView();
        this.mRelatedMusicCardItemsPerRow = IntMath.ceil(this.mCardItemsPerRow, 2);
        return view;
    }

    public void onDestroyView() {
        getLibraries().removeListener(this);
        FinskyApp.get().getInstaller().removeListener(this);
        if (this.mSocialDfeDetails != null) {
            this.mSocialDfeDetails.removeDataChangedListener(this);
            this.mSocialDfeDetails.removeErrorListener(this.mSocialDetailsErrorListener);
        }
        recordState();
        if (this.mSummaryViewBinder != null) {
            this.mSummaryViewBinder.onDestroyView();
        }
        this.mSubscriptionsViewBinder.onDestroyView();
        this.mSeasonListViewBinder.onDestroyView();
        if (this.mEpisodeList != null) {
            this.mEpisodeList.removeSeasonSelectionListener();
        }
        this.mSongListViewBinder.onDestroyView();
        super.onDestroyView();
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                if (this.mSummaryViewBinder != null) {
                    this.mSummaryViewBinder.onPositiveClick(requestCode, extraArguments);
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (this.mSubscriptionsViewBinder != null) {
                    this.mSubscriptionsViewBinder.onPositiveClick(requestCode, extraArguments);
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                Intent intent = new Intent("android.settings.WIFI_SETTINGS");
                intent.setFlags(537526272);
                getActivity().startActivity(intent);
                return;
            default:
                FinskyLog.w("Unknown request code %d", Integer.valueOf(requestCode));
                return;
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                if (this.mSummaryViewBinder != null) {
                    this.mSummaryViewBinder.onNegativeClick(requestCode, extraArguments);
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (this.mSubscriptionsViewBinder != null) {
                    this.mSubscriptionsViewBinder.onNegativeClick(requestCode, extraArguments);
                    return;
                }
                return;
            default:
                FinskyLog.w("Unknown request code %d", Integer.valueOf(requestCode));
                return;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 43) {
            switch (resultCode) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    this.mReviewDialogListener.onSaveReview(data.getStringExtra("doc_id"), data.getIntExtra("rating", 0), data.getStringExtra("review_title"), data.getStringExtra("review_comment"), (Document) data.getParcelableExtra("author"));
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    this.mReviewDialogListener.onDeleteReview(data.getStringExtra("doc_id"));
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    this.mReviewDialogListener.onCancelReview();
                    return;
                default:
                    return;
            }
        }
    }

    public void onReviewFeedback(String docId, String reviewId, CommentRating newRating) {
        this.mReviewDialogListener.onReviewFeedback(docId, reviewId, newRating);
    }

    public void onSeasonSelected(Document seasonDoc) {
        DetailsTextSection descriptionPanel = (DetailsTextSection) getView().findViewById(R.id.description_panel);
        if (descriptionPanel != null) {
            descriptionPanel.updateExtras(seasonDoc);
            descriptionPanel.syncCollapsedExtraIcons();
        }
    }

    protected void recordState(Bundle savedInstanceState) {
        View view = getView();
        if (view != null) {
            ViewGroup detailsContainer = (ViewGroup) view.findViewById(R.id.details_container);
            savedInstanceState.putBoolean("finsky.DetailsFragment.afterFirstLoad", true);
            if (this.mWasOwnedWhenPageLoaded != null) {
                savedInstanceState.putBoolean("finsky.DetailsFragment.wasDocOwnedWhenPageWasLoaded", this.mWasOwnedWhenPageLoaded.booleanValue());
            }
            if (detailsContainer.findViewById(R.id.description_panel) != null) {
            }
            if (((SubscriptionsSection) detailsContainer.findViewById(R.id.subscriptions_section)) != null) {
                this.mSubscriptionsViewBinder.saveInstanceState(savedInstanceState);
            }
            DetailsSecondaryActionsSection secondaryActionsPanel = (DetailsSecondaryActionsSection) detailsContainer.findViewById(R.id.secondary_actions_panel);
            if (secondaryActionsPanel != null) {
                secondaryActionsPanel.onSavedInstanceState(savedInstanceState);
            }
            if (((SongList) detailsContainer.findViewById(R.id.song_list)) != null) {
                this.mSongListViewBinder.saveInstanceState(savedInstanceState);
            }
            if (this.mEpisodeList != null) {
                this.mSeasonListViewBinder.saveInstanceState(savedInstanceState);
            }
            if (detailsContainer.findViewById(R.id.about_author_panel) == null) {
            }
        }
    }

    public void onErrorResponse(VolleyError error) {
        if (!canChangeFragmentManagerState()) {
            FinskyLog.e("Volley error: %s", error.getMessage());
        } else if (error instanceof BgDataDisabledError) {
            BackgroundDataDialog.show(getFragmentManager(), getActivity());
        } else {
            ErrorDialog.show(getFragmentManager(), null, ErrorStrings.get(getActivity(), error), true);
        }
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        if (this.mDfeApi != null) {
            refresh();
        }
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        Document doc = getDocument();
        if (doc != null && doc.getDocumentType() == 1 && doc.getAppDetails().packageName.equals(packageName) && event != InstallerPackageEvent.DOWNLOADING) {
            updateDetailsSections(this.mSavedInstanceState, false);
        }
    }

    protected int getPlayStoreUiElementType() {
        return 2;
    }

    private void configureEnterTransition(Context context) {
        TransitionInflater transitionInflater = TransitionInflater.from(context);
        this.mRevealTransitionInterpolator = AnimationUtils.loadInterpolator(context, 17563661);
        Transition enterTransition = transitionInflater.inflateTransition(R.transition.details_transition);
        enterTransition.setInterpolator(this.mRevealTransitionInterpolator);
        enterTransition.addListener(new BaseTransitionListener() {
            private Drawable mTitleBackground;
            private List<View> mViewsToFade;

            {
                this.mViewsToFade = Lists.newArrayList();
            }

            public void onTransitionStart(Transition transition) {
                View view = DetailsFragment.this.getView();
                if (view != null) {
                    DetailsInnerColumnLayout detailsContainer = (DetailsInnerColumnLayout) view.findViewById(R.id.details_container);
                    int childCount = detailsContainer.getChildCount();
                    if (childCount != 0) {
                        View title = DetailsFragment.this.mColumnLayout.findViewById(DetailsFragment.this.mUseWideLayout ? R.id.details_title_combined : R.id.item_details_panel);
                        if (title != null) {
                            this.mTitleBackground = title.getBackground();
                            this.mTitleBackground.setAlpha(0);
                        }
                        DetailsFragment.this.mPromoHeroView.freezeCorpusFill(400);
                        for (int i = 0; i < childCount; i++) {
                            View child = detailsContainer.getChildAt(i);
                            View view2 = DetailsFragment.this.mPromoHeroView;
                            if (child != r0) {
                                if (child instanceof DetailsPartialFadeSection) {
                                    ((DetailsPartialFadeSection) child).addParticipatingChildViews(this.mViewsToFade);
                                } else {
                                    this.mViewsToFade.add(child);
                                }
                            }
                        }
                        for (View alpha : this.mViewsToFade) {
                            alpha.setAlpha(0.0f);
                        }
                        detailsContainer.setSectionSeparatorAlphaMultiplier(0.0f);
                        if (!TextUtils.isEmpty(DetailsFragment.this.mRevealTransitionCoverName)) {
                            final View hero = view.findViewById(R.id.hero_promo);
                            int heroWidth = hero.getMeasuredWidth();
                            int heroHeight = hero.getMeasuredHeight();
                            hero.setZ(100.0f);
                            int translationX = (DetailsFragment.this.mSourceLeft + (DetailsFragment.this.mSourceWidth / 2)) - (heroWidth / 2);
                            int translationY = DetailsFragment.this.mSourceTop - ((heroHeight - DetailsFragment.this.mSourceHeight) / 2);
                            hero.setTranslationX((float) translationX);
                            hero.setTranslationY((float) translationY);
                            AnimatorSet translateSet = new AnimatorSet();
                            translateSet.setInterpolator(DetailsFragment.this.mRevealTransitionInterpolator);
                            Animator[] animatorArr = new Animator[2];
                            r26 = new float[2];
                            r26[0] = (float) translationX;
                            r26[1] = 0.0f;
                            animatorArr[0] = ObjectAnimator.ofFloat(hero, "translationX", r26);
                            r26 = new float[2];
                            r26[0] = (float) translationY;
                            r26[1] = 0.0f;
                            animatorArr[1] = ObjectAnimator.ofFloat(hero, "translationY", r26);
                            translateSet.playTogether(animatorArr);
                            translateSet.addListener(new AnimatorListenerAdapter() {
                                public void onAnimationEnd(Animator animation) {
                                    hero.setZ(0.0f);
                                }
                            });
                            translateSet.setDuration(400);
                            translateSet.start();
                            ViewAnimationUtils.createCircularReveal(hero, heroWidth / 2, heroHeight / 2, 0.0f, FloatMath.sqrt(((float) (heroWidth * heroWidth)) + ((float) (heroHeight * heroHeight))) / 2.0f).setDuration(400).start();
                        }
                    }
                }
            }

            public void onTransitionEnd(Transition transition) {
                ViewGroup view = (ViewGroup) DetailsFragment.this.getView();
                if (!(view == null || DetailsFragment.this.mSummaryViewBinder == null)) {
                    DetailsFragment.this.mSummaryViewBinder.bindCoverFromDocument();
                    AnimatorSet fadeInSet = new AnimatorSet();
                    List<Animator> fadeInAnimators = Lists.newArrayList();
                    DetailsFragment.this.mPromoHeroView.unfreezeCorpusFill(400);
                    for (View viewToFade : this.mViewsToFade) {
                        fadeInAnimators.add(PlayAnimationUtils.getFadeAnimator(viewToFade, 0.0f, 1.0f, 0, 400, null));
                    }
                    fadeInAnimators.add(ObjectAnimator.ofFloat((DetailsInnerColumnLayout) view.findViewById(R.id.details_container), "sectionSeparatorAlphaMultiplier", new float[]{0.0f, 1.0f}).setDuration(400));
                    fadeInSet.playTogether(fadeInAnimators);
                    fadeInSet.start();
                }
                if (this.mTitleBackground != null) {
                    ObjectAnimator.ofInt(this.mTitleBackground, "alpha", new int[]{0, 255}).setDuration(400).start();
                }
            }
        });
        enterTransition.setDuration(400);
        setSharedElementEnterTransition(enterTransition);
    }

    private void configureEnterSharedElementCallback() {
        setEnterSharedElementCallback(new SharedElementCallback() {
            private boolean mIsOpeningTarget;

            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> list) {
                this.mIsOpeningTarget = DetailsFragment.this.getView() == null;
                if (sharedElements != null && !sharedElements.isEmpty()) {
                    int i;
                    if (this.mIsOpeningTarget) {
                        for (i = 0; i < sharedElementNames.size(); i++) {
                            View sharedElement = (View) sharedElements.get(i);
                            if (((String) sharedElementNames.get(i)).startsWith("transition_card_details:cover:")) {
                                DetailsFragment.this.mCoverInSource = (FifeImageView) sharedElement;
                                DetailsFragment.this.mCoverInSource.setAlpha(0.0f);
                                int[] loc = new int[2];
                                int sourceOwnLeft = 0;
                                int sourceOwnTop = 0;
                                View parent = sharedElement;
                                while (parent != null) {
                                    int parentId = parent.getId();
                                    if (parentId == R.id.play_card) {
                                        parent.getLocationInWindow(loc);
                                        sourceOwnLeft = loc[0];
                                        sourceOwnTop = loc[1];
                                        DetailsFragment.this.mSourceWidth = parent.getMeasuredWidth();
                                        DetailsFragment.this.mSourceHeight = parent.getMeasuredHeight();
                                    }
                                    if (parentId == R.id.drawer_layout) {
                                        break;
                                    }
                                    parent = (View) parent.getParent();
                                }
                                parent.getLocationInWindow(loc);
                                DetailsFragment.this.mSourceLeft = sourceOwnLeft - loc[0];
                                DetailsFragment.this.mSourceTop = sourceOwnTop - loc[1];
                            }
                        }
                        return;
                    }
                    FifeImageView titleCover = null;
                    for (i = 0; i < sharedElementNames.size(); i++) {
                        if (((String) sharedElementNames.get(i)).startsWith("transition_card_details:cover:")) {
                            titleCover = (FifeImageView) sharedElements.get(i);
                            break;
                        }
                    }
                    if (!(DetailsFragment.this.mOriginalCoverBitmap == null || titleCover == null)) {
                        titleCover.freezeImage();
                        titleCover.setImageBitmap(DetailsFragment.this.mOriginalCoverBitmap);
                        titleCover.measure(MeasureSpec.makeMeasureSpec(titleCover.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(titleCover.getMeasuredHeight(), 1073741824));
                        titleCover.layout(titleCover.getLeft(), titleCover.getTop(), titleCover.getRight(), titleCover.getBottom());
                        if (DetailsFragment.this.mCoverInSource != null) {
                            DetailsFragment.this.mCoverInSource.setImageBitmap(DetailsFragment.this.mOriginalCoverBitmap);
                            DetailsFragment.this.mCoverInSource.freezeImage();
                        }
                    }
                    DetailsFragment.this.mOriginalCoverBitmap = null;
                }
            }

            public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
                this.mIsOpeningTarget = false;
                if (DetailsFragment.this.mCoverInSource != null && DetailsFragment.this.getView() != null) {
                    DetailsFragment.this.mCoverInSource.unfreezeImage(false);
                    DetailsFragment.this.mCoverInSource = null;
                }
            }

            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                if (!this.mIsOpeningTarget) {
                    for (int i = 0; i < names.size(); i++) {
                        String sharedElementName = (String) names.get(i);
                        if (sharedElementName.startsWith("transition_card_details:cover:")) {
                            View sharedCover = (View) sharedElements.get(sharedElementName);
                            if (sharedCover != null) {
                                int[] location = new int[2];
                                sharedCover.getLocationInWindow(location);
                                if (location[1] + sharedCover.getMeasuredHeight() <= ((ScrollView) DetailsFragment.this.getView().findViewById(R.id.details_scroller)).getScrollY()) {
                                    sharedElements.remove(sharedElementName);
                                    sharedCover.setTransitionName(null);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    }
                }
            }
        });
    }
}
