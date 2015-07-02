package com.google.android.finsky.activities;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.config.G;
import com.google.android.finsky.fragments.DeepLinkShimFragment;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DetailsSummaryWishlistView;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.layout.ScreenshotGallery;
import com.google.android.finsky.layout.WarningMessageSection;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.RootUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.play.layout.PlayActionButton;
import com.google.android.play.layout.PlayCardThumbnail;
import com.google.android.play.layout.StarRatingBar;
import java.text.NumberFormat;

public class InlineAppDetailsFragment extends PageFragment implements RootUiElementNode {
    private Account mAccount;
    private DfeDetails mDfeDetails;
    private DfeToc mDfeToc;
    private Document mDoc;
    private String mDocId;
    protected GenericUiElementNode mDocumentUiElementNode;
    private boolean mHasBeenAuthenticated;
    private Handler mImpressionHandler;
    private long mImpressionId;
    private Libraries mLibraries;
    private String mReferrer;
    private PlayStoreUiElement mRootUiElementProto;
    private Bundle mSavedInstanceState;
    private boolean mSentImpression;

    public static InlineAppDetailsFragment newInstance(String docId, String referrer) {
        InlineAppDetailsFragment fragment = new InlineAppDetailsFragment();
        fragment.init(docId, referrer);
        return fragment;
    }

    public InlineAppDetailsFragment() {
        this.mHasBeenAuthenticated = false;
        this.mImpressionId = FinskyEventLog.getNextImpressionId();
        this.mRootUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(14);
        this.mDocumentUiElementNode = null;
        this.mSentImpression = false;
    }

    public void init(String docId, String referrer) {
        this.mDocId = docId;
        this.mReferrer = referrer;
    }

    public void onAttach(Activity activity) {
        this.mImpressionHandler = new Handler(activity.getMainLooper());
        super.onAttach(activity);
    }

    public boolean isDataReady() {
        return this.mDfeDetails != null && this.mDfeDetails.isReady();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mAccount = FinskyApp.get().getDfeApi().getAccount();
        this.mLibraries = FinskyApp.get().getLibraries();
        this.mDfeToc = FinskyApp.get().getToc();
        this.mSavedInstanceState = savedInstanceState;
        if (savedInstanceState != null) {
            this.mDocId = savedInstanceState.getString("docId");
            this.mReferrer = savedInstanceState.getString("referrer");
        }
        switchToLoadingImmediately();
    }

    protected void onInitViewBinders() {
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putString("docId", this.mDocId);
            bundle.putString("referrer", this.mReferrer);
        }
    }

    protected void requestData() {
        if (this.mDfeDetails != null) {
            this.mDfeDetails.removeDataChangedListener(this);
            this.mDfeDetails.removeErrorListener(this);
        }
        this.mDfeDetails = new DfeDetails(FinskyApp.get().getDfeApi(), DfeUtils.createDetailsUrlFromId(this.mDocId));
        this.mDfeDetails.addDataChangedListener(this);
        this.mDfeDetails.addErrorListener(this);
    }

    protected void rebindViews() {
        if (isDataReady() && this.mHasBeenAuthenticated) {
            this.mDoc = this.mDfeDetails.getDocument();
            if (this.mDoc.getBackend() != 3) {
                FinskyLog.e("Only apps are supported: %s", this.mDocId);
                getActivity().finish();
                return;
            }
            updateDetailsSections();
            if (!TextUtils.isEmpty(this.mReferrer) && this.mSavedInstanceState == null) {
                DeepLinkShimFragment.saveExternalReferrer(this.mReferrer, this.mDoc.getFullDocid());
                if (((Boolean) G.logInlineAppInstallReferrerEnabled.get()).booleanValue()) {
                    FinskyApp.get().getEventLogger().logDeepLinkEvent(11, null, this.mDocId, this.mReferrer, null);
                }
            }
            FinskyEventLog.startNewImpression(this);
            FinskyEventLog.setServerLogCookie(this.mRootUiElementProto, this.mDfeDetails.getServerLogsCookie());
            if (this.mDoc != null) {
                if (this.mDocumentUiElementNode == null) {
                    this.mDocumentUiElementNode = new GenericUiElementNode(209, null, null, this);
                }
                this.mDocumentUiElementNode.setServerLogsCookie(this.mDoc.getServerLogsCookie());
                if (!this.mSentImpression) {
                    childImpression(this.mDocumentUiElementNode);
                    this.mSentImpression = true;
                }
            }
        }
    }

    protected int getLayoutRes() {
        return R.layout.inline_app_details_frame;
    }

    public void setHasBeenAuthenticated() {
        this.mHasBeenAuthenticated = true;
    }

    public void showError(VolleyError error) {
        switchToError(ErrorStrings.get(this.mContext, error));
    }

    private void updateDetailsSections() {
        if (this.mDoc != null) {
            View fragmentView = getView();
            setupItemDetails(this.mDoc, fragmentView);
            TextView shortDescriptionPanel = (TextView) fragmentView.findViewById(R.id.short_description_panel);
            if (shortDescriptionPanel != null) {
                if (TextUtils.isEmpty(this.mDoc.getPromotionalDescription())) {
                    shortDescriptionPanel.setText(Html.fromHtml(this.mDoc.getRawDescription()));
                } else {
                    shortDescriptionPanel.setText(this.mDoc.getPromotionalDescription());
                }
            }
            ScreenshotGallery screenshotsPanel = (ScreenshotGallery) fragmentView.findViewById(R.id.screenshots_panel);
            if (screenshotsPanel != null) {
                screenshotsPanel.bind(this.mDoc, this.mBitmapLoader, this.mNavigationManager, true);
            }
            TextView moreDetails = (TextView) fragmentView.findViewById(R.id.more_details);
            if (moreDetails != null) {
                moreDetails.setText(getString(R.string.more_details).toUpperCase());
                moreDetails.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("market://details?id=" + InlineAppDetailsFragment.this.mDoc.getAppDetails().packageName));
                        InlineAppDetailsFragment.this.startActivity(intent);
                    }
                });
            }
            WarningMessageSection warningMessagePanel = (WarningMessageSection) fragmentView.findViewById(R.id.warning_message_panel);
            if (warningMessagePanel != null) {
                warningMessagePanel.bind(this.mDoc, this.mDfeToc, this.mLibraries, this.mAccount);
            }
        }
    }

    private void setupItemDetails(Document doc, View parent) {
        TextView title = (TextView) parent.findViewById(R.id.title);
        if (title != null) {
            title.setText(doc.getTitle());
            title.setSelected(true);
        }
        ViewGroup creatorPanel = (ViewGroup) parent.findViewById(R.id.title_creator_panel);
        DecoratedTextView creator = (DecoratedTextView) creatorPanel.findViewById(R.id.creator);
        if (creator != null) {
            creatorPanel.setVisibility(0);
            creator.setText(PlayCardUtils.getDocDisplaySubtitle(doc));
            BadgeUtils.configureCreatorBadge(doc, this.mBitmapLoader, creator);
        }
        DetailsSummaryWishlistView wishlist = (DetailsSummaryWishlistView) parent.findViewById(R.id.title_wishlist_button);
        if (wishlist != null) {
            wishlist.configure(doc, this.mNavigationManager);
        }
        Resources res = this.mContext.getResources();
        PlayCardThumbnail thumbnail = (PlayCardThumbnail) parent.findViewById(R.id.title_thumbnail_frame);
        thumbnail.setVisibility(0);
        thumbnail.updateCoverPadding(-1);
        LayoutParams thumbnailLp = thumbnail.getLayoutParams();
        thumbnailLp.width = res.getDimensionPixelSize(R.dimen.inline_app_thumbnail_size);
        thumbnailLp.height = res.getDimensionPixelSize(R.dimen.inline_app_thumbnail_size);
        DocImageView thumbnailCover = (DocImageView) thumbnail.getImageView();
        thumbnailCover.setScaleType(ScaleType.FIT_START);
        thumbnailCover.bind(doc, this.mBitmapLoader, PlayCardImageTypeSequence.CORE_IMAGE_TYPES);
        thumbnailCover.setFocusable(false);
        thumbnailCover.setContentDescription(CorpusResourceUtils.getItemThumbnailContentDescription(doc, res));
        StarRatingBar starRatingBar = (StarRatingBar) parent.findViewById(R.id.star_rating_bar);
        if (starRatingBar != null && doc.hasRating()) {
            starRatingBar.setRating(doc.getStarRating());
        }
        TextView ratingCount = (TextView) parent.findViewById(R.id.rating_count);
        if (ratingCount != null) {
            ratingCount.setText(NumberFormat.getIntegerInstance().format((double) ((float) doc.getRatingCount())));
        }
        AppDetails appDetails = doc.getAppDetails();
        if (appDetails != null && appDetails.declaresIab) {
            TextView inAppPurchaseLabelView = (TextView) parent.findViewById(R.id.title_extra_label_in_app_purchase);
            inAppPurchaseLabelView.setText(R.string.in_app_purchases);
            inAppPurchaseLabelView.setVisibility(0);
        }
        syncDynamicSection(parent, doc, FinskyApp.get().getToc(), this.mAccount);
    }

    protected void syncDynamicSection(View parent, Document doc, DfeToc dfeToc, Account account) {
        if (LibraryUtils.isAvailable(doc, dfeToc, FinskyApp.get().getLibraries().getAccountLibrary(account))) {
            setupActionButtons(parent, doc, dfeToc, account);
        }
    }

    protected void setupActionButtons(View parent, Document doc, DfeToc dfeToc, Account account) {
        PlayActionButton buyButton = (PlayActionButton) parent.findViewById(R.id.buy_button);
        PlayActionButton launchButton = (PlayActionButton) parent.findViewById(R.id.launch_button);
        launchButton.setVisibility(8);
        buyButton.setVisibility(8);
        Libraries libraries = FinskyApp.get().getLibraries();
        AppStates appStates = FinskyApp.get().getAppStates();
        String appPackageName = doc.getAppDetails().packageName;
        AppActionAnalyzer actions = new AppActionAnalyzer(appPackageName, appStates, libraries);
        Account installAccount = AppActionAnalyzer.getInstallAccount(appPackageName, account, appStates, libraries);
        if (actions.isLaunchable && !actions.isContinueLaunch) {
            launchButton.setVisibility(0);
            launchButton.configure(doc.getBackend(), (int) R.string.open, this.mNavigationManager.getOpenClickListener(this.mDoc, this.mAccount, this));
        }
        if (!actions.isInstalled && LibraryUtils.isAvailable(doc, dfeToc, libraries)) {
            Account owner = LibraryUtils.getOwnerWithCurrentAccount(doc, FinskyApp.get().getLibraries(), account);
            buyButton.setVisibility(0);
            boolean isOwned = owner != null;
            buyButton.configure(doc.getBackend(), getBuyButtonString(doc, isOwned, 1), getBuyButtonListener(installAccount, doc, getBuyButtonLoggingElementType(doc, isOwned, 1)));
        }
    }

    private OnClickListener getBuyButtonListener(Account account, Document doc, int logElementType) {
        final OnClickListener listener = this.mNavigationManager.getBuyImmediateClickListener(account, doc, 1, null, null, logElementType, null);
        return new OnClickListener() {
            public void onClick(View v) {
                Activity activity = InlineAppDetailsFragment.this.getActivity();
                if (activity instanceof InlineAppDetailsDialog) {
                    ((InlineAppDetailsDialog) activity).hideDialog();
                }
                listener.onClick(v);
            }
        };
    }

    private int getBuyButtonLoggingElementType(Document doc, boolean isOwnedByUser, int offerType) {
        if (isOwnedByUser) {
            return 221;
        }
        if (!doc.needsCheckoutFlow(offerType)) {
            if (doc.getBackend() == 3) {
                return 221;
            }
            if (doc.getBackend() == 1) {
                return 225;
            }
        }
        return 200;
    }

    private String getBuyButtonString(Document doc, boolean isOwnedByUser, int offerType) {
        if (isOwnedByUser) {
            return this.mContext.getString(R.string.install);
        }
        boolean needsCheckoutFlow = doc.needsCheckoutFlow(offerType);
        if (!needsCheckoutFlow) {
            if (doc.getBackend() == 3) {
                return getActivity().getString(R.string.install);
            }
            if (doc.getBackend() == 1) {
                return this.mContext.getString(R.string.open);
            }
        }
        boolean useBuyVerb = FinskyApp.get().getExperiments().isEnabled("cl:billing.show_buy_verb_in_button");
        Offer purchaseOffer = doc.getOffer(offerType);
        if (purchaseOffer == null || !purchaseOffer.hasFormattedAmount) {
            return "";
        }
        String formattedAmount = purchaseOffer.formattedAmount;
        if (!useBuyVerb || !needsCheckoutFlow) {
            return formattedAmount;
        }
        return this.mContext.getString(R.string.buy, new Object[]{formattedAmount});
    }

    public void startNewImpression() {
        this.mImpressionId = FinskyEventLog.getNextImpressionId();
    }

    public void flushImpression() {
        FinskyEventLog.flushImpression(this.mImpressionHandler, this.mImpressionId, this);
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.rootImpression(this.mImpressionHandler, this.mImpressionId, this, childNode);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mRootUiElementProto;
    }
}
