package com.google.android.finsky.navigationmanager;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.vending.R;
import com.android.volley.Response.Listener;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AntennaFragment;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.activities.DetailsDataBasedFragment;
import com.google.android.finsky.activities.DetailsFragment;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.activities.FlagItemDialog;
import com.google.android.finsky.activities.FreeSongOfTheDayFragment;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.activities.PeopleDetailsFragment;
import com.google.android.finsky.activities.ReviewsActivity;
import com.google.android.finsky.activities.ScreenshotsActivity;
import com.google.android.finsky.activities.SearchFragment;
import com.google.android.finsky.activities.TabbedBrowseFragment;
import com.google.android.finsky.activities.myaccount.MyAccountFragment;
import com.google.android.finsky.activities.myaccount.OrderHistoryFragment;
import com.google.android.finsky.activities.myapps.MyAppsTabbedFragment;
import com.google.android.finsky.activities.mywishlist.MyWishlistFragment;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.lightpurchase.LightPurchaseFlowActivity;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.RedeemCodeActivity;
import com.google.android.finsky.detailspage.DetailsFragment2;
import com.google.android.finsky.detailspage.ExpandedDescriptionFragment;
import com.google.android.finsky.fragments.DeepLinkShimFragment;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.DetailsTextSection.ExpandedData;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.DocumentV2.CallToAction;
import com.google.android.finsky.protos.RateSuggestedContentResponse;
import com.google.android.finsky.protos.ResolveLink.RedeemGiftCard;
import com.google.android.finsky.protos.ResolveLink.ResolvedLink;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.DetailsShimFragment;
import com.google.android.finsky.utils.DocUtils.OfferFilter;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.HighlightUtils;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.MainThreadStack;
import com.google.android.finsky.utils.UiUtils;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class NavigationManager {
    private static boolean RESPECT_TASKS_IN_UP;
    private MainActivity mActivity;
    private final Stack<NavigationState> mBackStack;
    protected FragmentManager mFragmentManager;

    static {
        RESPECT_TASKS_IN_UP = VERSION.SDK_INT >= 16;
    }

    public NavigationManager(MainActivity activity) {
        this.mBackStack = new MainThreadStack();
        init(activity);
    }

    public static boolean areTransitionsEnabled() {
        return false;
    }

    public void clear() {
        this.mBackStack.removeAllElements();
        while (this.mFragmentManager.getBackStackEntryCount() > 0) {
            this.mFragmentManager.popBackStackImmediate();
        }
    }

    public void init(MainActivity activity) {
        this.mActivity = activity;
        this.mFragmentManager = this.mActivity.getSupportFragmentManager();
    }

    private boolean canNavigate() {
        return (this.mActivity == null || this.mActivity.isStateSaved()) ? false : true;
    }

    public void handleDeepLink(String url, String referringPackage) {
        handleDeepLink(Uri.parse(url), referringPackage);
    }

    public void handleDeepLink(Uri uri, String referringPackage) {
        showPage(9, DeepLinkShimFragment.newInstance(uri, referringPackage), false, new View[0]);
    }

    public void goToAggregatedHome(DfeToc dfeToc) {
        if (dfeToc != null) {
            if (canNavigate()) {
                clear();
                if (dfeToc.getCorpusList().size() == 1) {
                    CorpusMetadata corpusMetadata = (CorpusMetadata) dfeToc.getCorpusList().get(0);
                    showPage(2, TabbedBrowseFragment.newInstance(corpusMetadata.landingUrl, corpusMetadata.name, corpusMetadata.backend, dfeToc, null), true, new View[0]);
                    return;
                }
                String landingUrl;
                if (TextUtils.isEmpty(dfeToc.getHomeUrl())) {
                    landingUrl = dfeToc.getCorpus(3).landingUrl;
                } else {
                    landingUrl = dfeToc.getHomeUrl();
                }
                goToAggregatedHome(dfeToc, landingUrl);
                return;
            }
            this.mActivity.restartOnResume();
        }
    }

    public void goToAggregatedHome(DfeToc dfeToc, String landingUrl) {
        showPage(1, TabbedBrowseFragment.newInstance(landingUrl, this.mActivity.getString(R.string.launcher_name), 0, dfeToc, null), true, new View[0]);
    }

    public void goToCorpusHome(String url, String title, int backendId, DfeToc dfeToc) {
        if (canNavigate()) {
            showPage(2, TabbedBrowseFragment.newInstance(url, title, backendId, dfeToc, null), false, new View[0]);
        }
    }

    public void goToSocialHome(String url, DfeToc dfeToc) {
        if (canNavigate() && !TextUtils.isEmpty(dfeToc.getSocialHomeUrl())) {
            showPage(12, TabbedBrowseFragment.newInstance(url, FinskyApp.get().getString(R.string.side_drawer_social_home), 9, dfeToc, null), false, new View[0]);
        }
    }

    public void goToOrderHistory(String listUrl, String title, DfeToc dfeToc) {
        if (canNavigate()) {
            showPage(15, OrderHistoryFragment.newInstance(listUrl, title, dfeToc), false, new View[0]);
        }
    }

    public void goToExpandedDescription(ExpandedData expandedData, DfeToc dfeToc) {
        if (canNavigate()) {
            showPage(14, ExpandedDescriptionFragment.newInstance(expandedData, dfeToc), false, new View[0]);
        }
    }

    public void goBrowse(String url, String title, int backendId, DfeToc dfeToc, PlayStoreUiElementNode clickLogNode) {
        if (canNavigate()) {
            FinskyApp.get().getEventLogger().logClickEvent(clickLogNode);
            if (url.equals(dfeToc.getSocialHomeUrl())) {
                goToSocialHome(url, dfeToc);
                return;
            }
            int pageType = 4;
            if (!(dfeToc == null || dfeToc.getCorpus(url) == null)) {
                pageType = 2;
            }
            showPage(pageType, TabbedBrowseFragment.newInstance(url, title, backendId, dfeToc, null), false, new View[0]);
        }
    }

    public void goRedeem(String accountName, RedeemGiftCard redeemGiftCard) {
        String prefillCode = null;
        String partnerPayload = null;
        if (redeemGiftCard != null) {
            if (!TextUtils.isEmpty(redeemGiftCard.prefillCode)) {
                prefillCode = redeemGiftCard.prefillCode;
            }
            if (!TextUtils.isEmpty(redeemGiftCard.partnerPayload)) {
                partnerPayload = redeemGiftCard.partnerPayload;
            }
        }
        getActivityForResolveLink().startActivityForResult(RedeemCodeActivity.createIntent(accountName, 3, prefillCode, partnerPayload), 34);
    }

    public void resolveLink(ResolvedLink link, String title, int backendId, DfeToc dfeToc, PlayStoreUiElementNode clickLogNode) {
        if (canNavigate()) {
            FinskyApp.get().getEventLogger().logClickEvent(clickLogNode);
            if (!TextUtils.isEmpty(link.browseUrl)) {
                goBrowse(link.browseUrl, title, backendId, dfeToc, null);
            } else if (!TextUtils.isEmpty(link.detailsUrl)) {
                goToDocPage(link.detailsUrl);
            } else if (link.directPurchase != null) {
                FinskyLog.wtf("Direct purchase deprecated.", new Object[0]);
                goToDocPage(link.directPurchase.detailsUrl);
            } else if (!TextUtils.isEmpty(link.homeUrl)) {
                goToAggregatedHome(dfeToc, link.homeUrl);
            } else if (link.redeemGiftCard != null) {
                goRedeem(FinskyApp.get().getCurrentAccountName(), link.redeemGiftCard);
            } else if (!TextUtils.isEmpty(link.searchUrl)) {
                goToSearch(link.searchUrl, link.query, link.backend, null);
            }
        }
    }

    public void resolveLink(Link link, DfeToc dfeToc, PackageManager packageManager) {
        resolveLink(link, null, dfeToc, packageManager);
    }

    public void resolveLink(Link link, String title, DfeToc dfeToc, PackageManager packageManager) {
        Activity activity = getActivityForResolveLink();
        if (link.resolvedLink != null) {
            resolveLink(link.resolvedLink, title, -1, dfeToc, null);
        } else if (link.hasUriBackend) {
            int backendId = link.uriBackend;
            if (!IntentUtils.isConsumptionAppInstalled(packageManager, backendId)) {
                showAppNeededDialog(backendId);
            } else if (TextUtils.isEmpty(link.uri)) {
                activity.startActivity(IntentUtils.buildConsumptionAppLaunchIntent(activity, backendId, FinskyApp.get().getCurrentAccountName()));
            } else {
                activity.startActivity(IntentUtils.buildConsumptionAppUrlIntent(activity, backendId, link.uri, FinskyApp.get().getCurrentAccountName()));
            }
        } else {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(link.uri));
            activity.startActivity(intent);
        }
    }

    public void goToAllReviews(Document document, String reviewsUrl, boolean isRottenTomatoesReviews) {
        if (this.mActivity != null && !this.mActivity.isStateSaved()) {
            ReviewsActivity.show(this.mActivity, document, reviewsUrl, isRottenTomatoesReviews);
        }
    }

    public void goToMyDownloads(DfeToc dfeToc) {
        if (canNavigate()) {
            showPage(3, MyAppsTabbedFragment.newInstance(dfeToc), false, new View[0]);
        }
    }

    public void goToMyWishlist() {
        if (canNavigate()) {
            showPage(10, MyWishlistFragment.newInstance(), false, new View[0]);
        }
    }

    public void goToMyAccount() {
        if (canNavigate()) {
            showPage(13, MyAccountFragment.newInstance(), false, new View[0]);
        }
    }

    public void goToSearch(String query, int backend, PlayStoreUiElementNode clickLogNode) {
        goToSearch(DfeUtils.formSearchUrl(query, backend), query, backend, clickLogNode);
    }

    public void goToSearch(String searchUrl, String displayedQuery, int displayedBackend, PlayStoreUiElementNode clickLogNode) {
        if (canNavigate()) {
            showPage(7, SearchFragment.newInstance(displayedQuery, searchUrl, displayedBackend), false, new View[0]);
            FinskyApp.get().getEventLogger().logClickEvent(clickLogNode);
        }
    }

    public void searchFromSuggestion(String query, int backend) {
        if (canNavigate()) {
            showPage(7, SearchFragment.newInstance(query, DfeUtils.formSearchUrl(query, backend), backend), true, new View[0]);
        }
    }

    public void searchFromFullPageReplaced(String query, int backend) {
        if (canNavigate()) {
            showPage(7, SearchFragment.newInstance(query, DfeUtils.formSearchUrlWithFprDisabled(query, backend), backend), true, new View[0]);
        }
    }

    public void goToSettings() {
        this.mActivity.openSettings();
    }

    public void goToUrl(String url) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent.putExtra("com.android.browser.application_id", this.mActivity.getPackageName());
        this.mActivity.startActivity(intent);
    }

    public void goToFlagContent(String url) {
        if (canNavigate()) {
            FlagItemDialog.show(this.mActivity, url);
        }
    }

    public void goToDocPage(String url) {
        if (canNavigate()) {
            showPage(6, DetailsShimFragment.newInstance(url, null, null), false, new View[0]);
        }
    }

    public void goToDocPage(String url, String continueUrl, String overrideAccount) {
        if (canNavigate()) {
            showPage(6, DetailsShimFragment.newInstance(url, continueUrl, overrideAccount), false, new View[0]);
        }
    }

    public void goToDocPage(Document doc) {
        goToDocPage(doc, doc.getDetailsUrl(), null, false, null, null, null);
    }

    public void goToDocPage(Document doc, View sharedPrimaryContainer, View sharedCoverView) {
        goToDocPage(doc, doc.getDetailsUrl(), null, false, null, sharedPrimaryContainer, sharedCoverView);
    }

    public void replaceDetailsShimWithDocPage(Document doc, String originalUrl, String continueUrl, String overrideAccount) {
        goToDocPage(doc, originalUrl, continueUrl, true, overrideAccount, null, null);
    }

    private void goToDocPage(Document doc, String originalUrl, String continueUrl, boolean replaceTop, String overrideAccount, View sharedPrimaryContainer, View sharedCoverView) {
        if (canNavigate()) {
            int type = doc.getDocumentType();
            if (doc.getBackend() == 2 && doc.hasAntennaInfo()) {
                if (shouldShowDetailsPageV2()) {
                    showPage(5, DetailsFragment2.newInstance(doc, originalUrl, null, null, false), replaceTop, sharedPrimaryContainer, sharedCoverView);
                } else {
                    showPage(5, AntennaFragment.newInstance(doc, originalUrl), replaceTop, sharedPrimaryContainer, sharedCoverView);
                }
            } else if (doc.getBackend() == 2 && doc.hasDealOfTheDayInfo()) {
                showPage(5, FreeSongOfTheDayFragment.newInstance(doc, originalUrl), replaceTop, sharedPrimaryContainer, sharedCoverView);
            } else {
                switch (type) {
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        String accountForDetails;
                        Fragment appDetailsFragment;
                        String packageName = doc.getAppDetails().packageName;
                        String currentAccountName = FinskyApp.get().getCurrentAccountName();
                        boolean overriding = !TextUtils.isEmpty(overrideAccount);
                        if (overriding) {
                            accountForDetails = overrideAccount;
                        } else {
                            accountForDetails = AppActionAnalyzer.getAppDetailsAccount(packageName, currentAccountName, FinskyApp.get().getAppStates(), FinskyApp.get().getLibraries());
                        }
                        if (overriding || !currentAccountName.equals(accountForDetails)) {
                            FinskyLog.d("Selecting account %s for package %s. overriding=[%s]", FinskyLog.scrubPii(accountForDetails), packageName, Boolean.valueOf(overriding));
                        }
                        if (shouldShowDetailsPageV2()) {
                            appDetailsFragment = DetailsFragment2.newInstance(doc, originalUrl, continueUrl, accountForDetails, overriding);
                        } else {
                            appDetailsFragment = DetailsFragment.newInstance(doc, originalUrl, continueUrl, accountForDetails, overriding, sharedPrimaryContainer, sharedCoverView);
                        }
                        showPage(5, appDetailsFragment, replaceTop, sharedPrimaryContainer, sharedCoverView);
                        return;
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                        Resources resources = this.mActivity.getResources();
                        ErrorDialog.show(this.mActivity.getSupportFragmentManager(), resources.getString(R.string.error), resources.getString(R.string.unsupported_page), false);
                        return;
                    case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                        showPage(11, PeopleDetailsFragment.newInstance(doc, originalUrl), replaceTop, sharedPrimaryContainer, sharedCoverView);
                        return;
                    default:
                        Fragment detailsFragment;
                        if (shouldShowDetailsPageV2()) {
                            detailsFragment = DetailsFragment2.newInstance(doc, originalUrl, continueUrl, null, false);
                        } else {
                            detailsFragment = DetailsFragment.newInstance(doc, originalUrl, continueUrl, null, false, sharedPrimaryContainer, sharedCoverView);
                        }
                        showPage(5, detailsFragment, replaceTop, sharedPrimaryContainer, sharedCoverView);
                        return;
                }
            }
        }
    }

    public void goToScreenshots(Document doc, int initialIndex) {
        goToImagesLightbox(doc, initialIndex, 1);
    }

    public void goToImagesLightbox(Document doc, int initialIndex, int imageType) {
        if (this.mActivity != null && !this.mActivity.isStateSaved()) {
            ScreenshotsActivity.show(this.mActivity, doc, initialIndex, imageType);
        }
    }

    public int getCurrentPageType() {
        return this.mBackStack.isEmpty() ? 0 : ((NavigationState) this.mBackStack.peek()).pageType;
    }

    public void terminate() {
        this.mActivity = null;
    }

    public void serialize(Bundle savedInstanceState) {
        if (this.mBackStack != null && !this.mBackStack.isEmpty()) {
            savedInstanceState.putParcelableArrayList("nm_state", new ArrayList(this.mBackStack));
        }
    }

    public boolean goBack() {
        return goBack(true);
    }

    private boolean goBack(boolean logClickEvent) {
        if (this.mActivity == null || this.mActivity.isStateSaved()) {
            return false;
        }
        if (logClickEvent) {
            FinskyApp.get().getEventLogger().logClickEvent(600, null, getActivePage());
        }
        try {
            FinskyLog.startTiming();
            NavigationState currentEntry = (NavigationState) this.mBackStack.pop();
            this.mFragmentManager.popBackStack();
            NavigationState newStackEntry = (NavigationState) this.mBackStack.peek();
            return true;
        } catch (EmptyStackException e) {
            return false;
        }
    }

    public boolean deserialize(Bundle savedInstanceState) {
        List<NavigationState> contents = savedInstanceState.getParcelableArrayList("nm_state");
        if (contents == null || contents.size() == 0 || getActivePage() == null) {
            return false;
        }
        for (NavigationState st : contents) {
            this.mBackStack.push(st);
        }
        getActivePage().rebindActionBar();
        return true;
    }

    public void refreshPage() {
        if (!this.mBackStack.isEmpty()) {
            PageFragment active = getActivePage();
            if (active != null) {
                active.refresh();
                if (active.isDataReady()) {
                    active.onDataChanged();
                    return;
                }
                return;
            }
            FinskyLog.e("Called refresh but there was no active page", new Object[0]);
        }
    }

    public PageFragment getActivePage() {
        return (PageFragment) this.mFragmentManager.findFragmentById(R.id.content_frame);
    }

    public void showAppNeededDialog(int docBackend) {
        ConsumptionUtils.showAppNeededDialog(this.mActivity, docBackend, this.mFragmentManager, null, 1);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1 && extraArguments != null) {
            String detailsUrl = extraArguments.getString("dialog_details_url");
            if (detailsUrl != null && canNavigate()) {
                goToDocPage(detailsUrl);
            }
        }
    }

    public void openItem(Account account, Document doc) {
        ConsumptionUtils.openItem(this.mActivity, account, doc, this.mFragmentManager, null, 1);
    }

    public OnClickListener getClickListener(Document doc, PlayStoreUiElementNode clickLogNode) {
        return getClickListener(doc, clickLogNode, null, -1);
    }

    public OnClickListener getClickListener(final Document doc, final PlayStoreUiElementNode clickLogNode, String searchQuery, int searchBackend) {
        if (!hasClickListener(doc)) {
            return null;
        }
        if (doc.hasLinkAnnotation()) {
            if (doc.getLinkAnnotation().resolvedLink == null) {
                return new OnClickListener() {
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(doc.getLinkAnnotation().uri)));
                        FinskyApp.get().getEventLogger().logClickEvent(clickLogNode);
                    }
                };
            }
            return getResolvedLinkClickListener(doc, FinskyApp.get().getToc(), doc.getLinkAnnotation().resolvedLink, searchQuery, searchBackend, clickLogNode);
        } else if (!doc.hasContainerAnnotation() || TextUtils.isEmpty(doc.getContainerAnnotation().browseUrl)) {
            return new OnClickListener() {
                public void onClick(View v) {
                    HighlightUtils.trackClickUrl(doc);
                    String detailsUrl = doc.getDetailsUrl();
                    if (doc.canUseAsPartialDocument()) {
                        if (NavigationManager.areTransitionsEnabled()) {
                            View anchorPrimary = UiUtils.findSharedElementView(v, "transition_card_details:card:");
                            View anchorCover = UiUtils.findSharedElementView(v, "transition_card_details:cover:");
                            if (!(anchorPrimary == null || anchorCover == null)) {
                                NavigationManager.this.goToDocPage(doc, anchorPrimary, anchorCover);
                                FinskyApp.get().getEventLogger().logClickEvent(clickLogNode);
                                return;
                            }
                        }
                        NavigationManager.this.goToDocPage(doc);
                    } else {
                        NavigationManager.this.goToDocPage(detailsUrl);
                    }
                    FinskyApp.get().getEventLogger().logClickEvent(clickLogNode);
                }
            };
        } else {
            return new OnClickListener() {
                public void onClick(View v) {
                    NavigationManager.this.goBrowse(doc.getContainerAnnotation().browseUrl, null, doc.getBackend(), FinskyApp.get().getToc(), clickLogNode);
                }
            };
        }
    }

    public static boolean hasClickListener(Document doc) {
        if (doc.hasLinkAnnotation() || doc.hasAntennaInfo() || !TextUtils.isEmpty(doc.getDetailsUrl()) || (doc.hasContainerAnnotation() && !TextUtils.isEmpty(doc.getContainerAnnotation().browseUrl))) {
            return true;
        }
        return false;
    }

    private OnClickListener getResolvedLinkClickListener(Document doc, DfeToc dfeToc, ResolvedLink resolvedLink, String searchQuery, int searchBackend, PlayStoreUiElementNode clickLogNode) {
        final ResolvedLink resolvedLink2 = resolvedLink;
        final Document document = doc;
        final DfeToc dfeToc2 = dfeToc;
        final PlayStoreUiElementNode playStoreUiElementNode = clickLogNode;
        final String str = searchQuery;
        final int i = searchBackend;
        return new OnClickListener() {
            public void onClick(View view) {
                if (!TextUtils.isEmpty(resolvedLink2.browseUrl)) {
                    NavigationManager.this.goBrowse(resolvedLink2.browseUrl, document.getTitle(), document.getBackend(), dfeToc2, playStoreUiElementNode);
                } else if (!TextUtils.isEmpty(resolvedLink2.searchUrl)) {
                    NavigationManager.this.goToSearch(resolvedLink2.searchUrl, str, i, playStoreUiElementNode);
                } else if (!TextUtils.isEmpty(resolvedLink2.detailsUrl)) {
                    FinskyApp.get().getEventLogger().logClickEvent(playStoreUiElementNode);
                    NavigationManager.this.goToDocPage(resolvedLink2.detailsUrl);
                } else if (!TextUtils.isEmpty(resolvedLink2.homeUrl)) {
                    NavigationManager.this.goBrowse(resolvedLink2.browseUrl, document.getTitle(), document.getBackend(), dfeToc2, playStoreUiElementNode);
                } else if (resolvedLink2.redeemGiftCard != null) {
                    FinskyApp.get().getEventLogger().logClickEvent(playStoreUiElementNode);
                    NavigationManager.this.goRedeem(FinskyApp.get().getCurrentAccountName(), resolvedLink2.redeemGiftCard);
                }
            }
        };
    }

    private void showPage(int pageType, Fragment fragment, boolean replaceTop, View... sharedViews) {
        FinskyLog.startTiming();
        FragmentTransaction ft = this.mFragmentManager.beginTransaction();
        if (areTransitionsEnabled() && sharedViews != null) {
            for (View sharedView : sharedViews) {
                if (sharedView != null) {
                    ft.addSharedElement(sharedView, sharedView.getTransitionName());
                }
            }
        }
        ft.setTransition(4099);
        ft.replace(R.id.content_frame, fragment);
        if (replaceTop) {
            popBackStack();
        }
        NavigationState state = new NavigationState(pageType);
        ft.addToBackStack(state.backstackName);
        this.mBackStack.push(state);
        ft.commit();
    }

    public void setActionBarOverlayEnabledForCurrent(boolean isActionBarOverlayEnabled) {
        if (!this.mBackStack.isEmpty()) {
            ((NavigationState) this.mBackStack.peek()).isActionBarOverlayEnabled = isActionBarOverlayEnabled;
        }
    }

    public void setCanTriggerSearchSurvey(boolean canTriggerSearchSurvey) {
        if (!this.mBackStack.isEmpty()) {
            NavigationState state = (NavigationState) this.mBackStack.peek();
            if (state.pageType == 7) {
                state.canTriggerSearchSurvey = canTriggerSearchSurvey;
            }
        }
    }

    public boolean canPromptSearchSurveyForCurrent() {
        int size = this.mBackStack.size();
        if (size < 2) {
            return false;
        }
        return ((NavigationState) this.mBackStack.elementAt(size - 2)).canTriggerSearchSurvey;
    }

    public boolean isActionBarOverlayEnabledForCurrent() {
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        return ((NavigationState) this.mBackStack.peek()).isActionBarOverlayEnabled;
    }

    public void popBackStack() {
        if (!this.mBackStack.isEmpty()) {
            this.mBackStack.pop();
        }
        this.mFragmentManager.popBackStack();
    }

    public OnClickListener getBuyImmediateClickListener(Account account, Document doc, int offerType, OfferFilter filter, String continueUrl, int logElementType, PlayStoreUiElementNode parentNode) {
        final PlayStoreUiElementNode rootNode = parentNode != null ? parentNode : getActivePage();
        final int i = logElementType;
        final Account account2 = account;
        final Document document = doc;
        final int i2 = offerType;
        final OfferFilter offerFilter = filter;
        final String str = continueUrl;
        return new OnClickListener() {
            public void onClick(View v) {
                FinskyApp.get().getEventLogger().logClickEvent(i, null, rootNode);
                NavigationManager.this.buy(account2, document, i2, offerFilter, str);
            }
        };
    }

    public void buy(Account account, Document doc, int offerType, OfferFilter filter, String appsContinueUrl) {
        if (canNavigate()) {
            this.mActivity.startActivityForResult(LightPurchaseFlowActivity.createIntent(account, doc, offerType, filter, doc.getServerLogsCookie(), appsContinueUrl), 33);
        }
    }

    public OnClickListener getOpenClickListener(final Document doc, final Account account, final PlayStoreUiElementNode parentNode) {
        return new OnClickListener() {
            public void onClick(View v) {
                FinskyApp.get().getEventLogger().logClickEvent(218, null, parentNode);
                NavigationManager.this.openItem(account, doc);
            }
        };
    }

    public Document getCurrentDocument() {
        if (this.mFragmentManager == null) {
            return null;
        }
        PageFragment active = getActivePage();
        if (active == null || !(active instanceof DetailsDataBasedFragment)) {
            return null;
        }
        return ((DetailsDataBasedFragment) active).getDocument();
    }

    public boolean goUp() {
        if (this.mActivity == null || this.mActivity.isStateSaved() || this.mBackStack.isEmpty()) {
            return true;
        }
        FinskyApp.get().getEventLogger().logClickEvent(602, null, getActivePage());
        DfeToc dfeToc = FinskyApp.get().getToc();
        if (RESPECT_TASKS_IN_UP) {
            Intent recreationIntent;
            Document currentDocument = getCurrentDocument();
            int currentBackend = currentDocument != null ? currentDocument.getBackend() : -1;
            if (dfeToc == null || currentBackend < 0 || dfeToc.getCorpus(currentBackend) == null || currentBackend == 0 || currentBackend == 9) {
                recreationIntent = IntentUtils.createAggregatedHomeIntent(this.mActivity);
            } else {
                recreationIntent = IntentUtils.createCorpusIntent(this.mActivity, currentDocument.getBackend(), dfeToc);
            }
            if (NavUtils.shouldUpRecreateTask(this.mActivity, recreationIntent)) {
                TaskStackBuilder.create(this.mActivity).addNextIntent(recreationIntent).startActivities();
                ActivityCompat.finishAffinity(this.mActivity);
                return true;
            }
        }
        switch (((NavigationState) this.mBackStack.peek()).pageType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomGestures /*12*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_useViewLifecycle /*13*/:
                goToAggregatedHome(dfeToc);
                return true;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomControls /*11*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_zOrderOnTop /*14*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiMapToolbar /*15*/:
                return goBack(false);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                unwindDetailsStack();
                return true;
            default:
                return true;
        }
    }

    public boolean canGoUp() {
        boolean z = true;
        if (isBackStackEmpty() || this.mBackStack.size() == 0) {
            return false;
        }
        NavigationState currentState = (NavigationState) this.mBackStack.peek();
        if (currentState.pageType == 1) {
            return false;
        }
        if (currentState.pageType != 2) {
            return true;
        }
        DfeToc toc = getActivePage().getToc();
        if (toc == null) {
            return false;
        }
        if (toc.getCorpusList().size() <= 1) {
            z = false;
        }
        return z;
    }

    private void unwindDetailsStack() {
        if (this.mActivity != null && !this.mActivity.isStateSaved()) {
            DfeToc dfeToc = FinskyApp.get().getToc();
            if (dfeToc != null) {
                while (!this.mBackStack.isEmpty()) {
                    int currType = ((NavigationState) this.mBackStack.peek()).pageType;
                    if (currType != 5 && currType != 6) {
                        break;
                    }
                    this.mBackStack.pop();
                }
                if (this.mBackStack.isEmpty()) {
                    this.mFragmentManager.popBackStack(this.mFragmentManager.getBackStackEntryAt(0).getName(), 1);
                    Document currentDocument = getCurrentDocument();
                    if (currentDocument != null) {
                        int backendId = currentDocument.getBackend();
                        CorpusMetadata corpus = dfeToc.getCorpus(backendId);
                        if (corpus != null) {
                            goToCorpusHome(corpus.landingUrl, corpus.name, backendId, dfeToc);
                            return;
                        }
                    }
                    goToAggregatedHome(dfeToc);
                    return;
                }
                this.mFragmentManager.popBackStack(((NavigationState) this.mBackStack.peek()).backstackName, 0);
            }
        }
    }

    public void addOnBackStackChangedListener(OnBackStackChangedListener listener) {
        this.mFragmentManager.addOnBackStackChangedListener(listener);
    }

    public void removeOnBackStackChangedListener(OnBackStackChangedListener listener) {
        this.mFragmentManager.removeOnBackStackChangedListener(listener);
    }

    public boolean isBackStackEmpty() {
        return this.mFragmentManager.getBackStackEntryCount() == 0;
    }

    public boolean flush() {
        return this.mFragmentManager.executePendingTransactions();
    }

    public boolean canSearch() {
        switch (getCurrentPageType()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_zOrderOnTop /*14*/:
                return false;
            default:
                return true;
        }
    }

    public void resolveCallToAction(CallToAction callToAction, DfeApi dfeApi, DfeToc dfeToc, PackageManager packageManager) {
        if (callToAction.type == 1) {
            resolveCallToActionDismiss(callToAction, dfeApi);
        } else if (callToAction.type == 2) {
            resolveLink(callToAction.link, dfeToc, packageManager);
        }
    }

    private void resolveCallToActionDismiss(CallToAction callToAction, DfeApi dfeApi) {
        dfeApi.rateSuggestedContent(callToAction.dismissalUrl, new Listener<RateSuggestedContentResponse>() {
            public void onResponse(RateSuggestedContentResponse arg) {
            }
        }, null);
    }

    protected Activity getActivityForResolveLink() {
        return this.mActivity;
    }

    private static boolean shouldShowDetailsPageV2() {
        if (FinskyPreferences.internalDetailsPageV2Enabled.exists()) {
            return ((Boolean) FinskyPreferences.internalDetailsPageV2Enabled.get()).booleanValue();
        }
        return FinskyApp.get().getExperiments().isEnabled("cl:details.details_page_v2_enabled");
    }
}
