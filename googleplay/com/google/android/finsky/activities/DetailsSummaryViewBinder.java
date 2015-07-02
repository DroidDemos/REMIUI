package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DetailsSummary;
import com.google.android.finsky.layout.DetailsSummaryDynamic;
import com.google.android.finsky.layout.DetailsSummaryWishlistView;
import com.google.android.finsky.layout.DetailsTitleCreatorBlock;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.library.RevokeListenerWrapper;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.protos.DocDetails.VideoDetails;
import com.google.android.finsky.protos.RevokeResponse;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.finsky.utils.PurchaseButtonHelper;
import com.google.android.finsky.utils.PurchaseButtonHelper.DocumentAction;
import com.google.android.finsky.utils.PurchaseButtonHelper.DocumentActions;
import com.google.android.finsky.utils.PurchaseButtonHelper.TextStyle;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayActionButton;
import com.google.android.play.layout.PlayCardThumbnail;

public class DetailsSummaryViewBinder {
    protected final Account mAccount;
    protected boolean mBindingDynamicSection;
    private BitmapLoader mBitmapLoader;
    protected ViewGroup mButtonSection;
    protected PageFragment mContainerFragment;
    protected Context mContext;
    protected String mContinueUrl;
    protected DfeApi mDfeApi;
    protected DfeToc mDfeToc;
    protected Document mDoc;
    protected DetailsSummaryDynamic mDynamicSection;
    protected final FinskyEventLog mEventLogger;
    protected boolean mHideDynamicFeatures;
    protected boolean mIsBinderDestroyed;
    private boolean mIsCancelingPreorder;
    private boolean mIsCompactMode;
    protected boolean mIsPendingRefund;
    private View[] mLayouts;
    protected NavigationManager mNavigationManager;
    protected PlayStoreUiElementNode mParentNode;
    private String mRevealTransitionCoverName;

    public DetailsSummaryViewBinder(DfeToc dfeToc, Account account) {
        this.mAccount = account;
        this.mDfeToc = dfeToc;
        this.mDfeApi = FinskyApp.get().getDfeApi(account.name);
        this.mEventLogger = FinskyApp.get().getEventLogger(account);
    }

    public void init(Context context, NavigationManager navManager, BitmapLoader bitmapLoader, PageFragment fragment, boolean trackPackageStatus, String continueUrl, String revealTransitionCoverName, PlayStoreUiElementNode parentNode) {
        this.mContext = context;
        this.mNavigationManager = navManager;
        this.mBitmapLoader = bitmapLoader;
        this.mContainerFragment = fragment;
        this.mContinueUrl = continueUrl;
        this.mRevealTransitionCoverName = revealTransitionCoverName;
        this.mParentNode = parentNode;
    }

    public void bind(Document document, boolean bindDynamicSection, View... views) {
        this.mLayouts = views;
        this.mDoc = document;
        this.mBindingDynamicSection = bindDynamicSection;
        this.mDynamicSection = (DetailsSummaryDynamic) findViewById(R.id.title_details_summary_dynamic);
        this.mButtonSection = (ViewGroup) findViewById(R.id.button_container);
        setupItemDetails();
        if (bindDynamicSection) {
            syncDynamicSection();
            updateButtonActionStyle();
        } else {
            this.mButtonSection.setVisibility(8);
        }
        UiUtils.syncContainerVisibility(this.mDynamicSection, 8);
    }

    public void onDestroyView() {
        this.mIsBinderDestroyed = true;
        if (this.mButtonSection != null) {
            int buttonCount = this.mButtonSection.getChildCount();
            for (int i = 0; i < buttonCount; i++) {
                View child = this.mButtonSection.getChildAt(i);
                if (child instanceof PlayActionButton) {
                    ((PlayActionButton) child).resetClickListener();
                }
            }
        }
    }

    protected View findViewById(int id) {
        if (this.mLayouts == null) {
            return null;
        }
        for (View layout : this.mLayouts) {
            if (layout != null) {
                View found = layout.findViewById(id);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public void refresh() {
        if (!this.mIsBinderDestroyed) {
            bind(this.mDoc, this.mBindingDynamicSection, this.mLayouts);
        }
    }

    protected void setupItemDetails() {
        boolean showThumbnail;
        int thumbnailMode;
        int docType = this.mDoc.getDocumentType();
        TextView title = (TextView) findViewById(R.id.title_title);
        if (title != null) {
            title.setText(this.mDoc.getTitle());
            title.setSelected(true);
        }
        ViewGroup creatorPanel = (ViewGroup) findViewById(R.id.title_creator_panel);
        DecoratedTextView creator = (DecoratedTextView) creatorPanel.findViewById(R.id.title_creator);
        if (creator != null) {
            if (docType == 6 || docType == 3 || docType == 2 || docType == 4 || docType == 5 || docType == 30) {
                creatorPanel.setVisibility(8);
            } else {
                creatorPanel.setVisibility(0);
                creator.setText(PlayCardUtils.getDocDisplaySubtitle(this.mDoc));
                BadgeUtils.configureCreatorBadge(this.mDoc, this.mBitmapLoader, creator);
                BadgeUtils.configureContentRatingBadge(this.mDoc, this.mBitmapLoader, (ImageView) creatorPanel.findViewById(R.id.content_rating_image));
            }
        }
        BadgeUtils.configureTipperSticker(this.mDoc, (DecoratedTextView) findViewById(R.id.title_tipper_sticker));
        DetailsTitleCreatorBlock creatorBlock = (DetailsTitleCreatorBlock) findViewById(R.id.title_creator_block);
        if (creatorBlock != null) {
            creatorBlock.bind(this.mDoc, this.mNavigationManager, this.mBitmapLoader, this.mEventLogger, this.mParentNode);
        }
        DetailsSummaryWishlistView wishlist = (DetailsSummaryWishlistView) findViewById(R.id.title_wishlist_button);
        if (wishlist != null) {
            if (this.mIsCompactMode) {
                wishlist.setVisibility(8);
            } else {
                wishlist.configure(this.mDoc, this.mNavigationManager);
            }
        }
        Resources res = this.mContext.getResources();
        PlayCardThumbnail thumbnail = (PlayCardThumbnail) findViewById(R.id.title_thumbnail_frame);
        boolean isWideLayout = res.getBoolean(R.bool.use_wide_details_layout);
        switch (docType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                showThumbnail = true;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                showThumbnail = isWideLayout;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                break;
        }
        showThumbnail = false;
        DetailsSummary summary = (DetailsSummary) findViewById(R.id.item_details_panel);
        if (this.mIsCompactMode || showThumbnail) {
            thumbnail.setVisibility(0);
            thumbnail.updateCoverPadding(this.mDoc.getBackend());
            LayoutParams thumbnailLp = thumbnail.getLayoutParams();
            thumbnailLp.width = CorpusResourceUtils.getRegularDetailsIconWidth(this.mContext, docType);
            thumbnailLp.height = CorpusResourceUtils.getRegularDetailsIconHeight(this.mContext, docType);
            DocImageView thumbnailCover = (DocImageView) thumbnail.getImageView();
            thumbnailCover.setScaleType(ScaleType.FIT_START);
            if (NavigationManager.areTransitionsEnabled()) {
                thumbnailCover.setTransitionName(this.mRevealTransitionCoverName);
            }
            if (TextUtils.isEmpty(this.mRevealTransitionCoverName)) {
                thumbnailCover.bind(this.mDoc, this.mBitmapLoader, PlayCardImageTypeSequence.CORE_IMAGE_TYPES);
            }
            thumbnailCover.setFocusable(this.mBindingDynamicSection);
            thumbnailCover.setContentDescription(CorpusResourceUtils.getItemThumbnailContentDescription(this.mDoc, res));
            if (this.mBindingDynamicSection) {
                thumbnailCover.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if (DetailsSummaryViewBinder.this.mDoc.getImages(4) != null) {
                            DetailsSummaryViewBinder.this.mNavigationManager.goToImagesLightbox(DetailsSummaryViewBinder.this.mDoc, 0, 4);
                        } else if (DetailsSummaryViewBinder.this.mDoc.getImages(0) != null) {
                            DetailsSummaryViewBinder.this.mNavigationManager.goToImagesLightbox(DetailsSummaryViewBinder.this.mDoc, 0, 0);
                        }
                    }
                });
                thumbnailCover.setForeground(res.getDrawable(R.drawable.play_highlight_overlay_dark));
            }
            if (isWideLayout) {
                thumbnailMode = 0;
            } else if (supportsThumbnailPeekingOnNonWideLayout(docType)) {
                thumbnailMode = HeroGraphicView.getDetailsHeroGraphic(this.mDoc, isWideLayout) != null ? 1 : 2;
            } else {
                thumbnailMode = 2;
            }
        } else {
            thumbnail.setVisibility(8);
            if (isWideLayout) {
                thumbnailMode = 0;
            } else {
                thumbnailMode = 2;
            }
        }
        summary.setThumbnailMode(thumbnailMode);
        if (!this.mIsCompactMode) {
            setupBylines();
            setupExtraLabels();
            setupExtraLabelsApp();
        }
    }

    public static boolean supportsThumbnailPeekingOnNonWideLayout(int docType) {
        return docType == 6 || docType == 16 || docType == 17;
    }

    public void setCoverFromBitmap(Bitmap coverBitmap) {
        PlayCardThumbnail thumbnail = (PlayCardThumbnail) findViewById(R.id.title_thumbnail_frame);
        if (thumbnail != null) {
            ((DocImageView) thumbnail.getImageView()).setImageBitmap(coverBitmap);
        }
    }

    public void bindCoverFromDocument() {
        PlayCardThumbnail thumbnail = (PlayCardThumbnail) findViewById(R.id.title_thumbnail_frame);
        if (thumbnail != null) {
            ((DocImageView) thumbnail.getImageView()).bind(this.mDoc, this.mBitmapLoader, PlayCardImageTypeSequence.CORE_IMAGE_TYPES);
        }
    }

    private void setupBylines() {
        ViewGroup bylinesContainer = (ViewGroup) findViewById(R.id.title_bylines);
        bylinesContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        switch (this.mDoc.getDocumentType()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                VideoDetails videoDetails = this.mDoc.getVideoDetails();
                if (!(this.mDoc.hasPreorderOffer() || TextUtils.isEmpty(videoDetails.releaseDate))) {
                    addByline(inflater, bylinesContainer, videoDetails.releaseDate);
                }
                if (TextUtils.isEmpty(videoDetails.contentRating)) {
                    addByline(inflater, bylinesContainer, this.mContext.getString(R.string.no_movie_rating));
                } else {
                    addByline(inflater, bylinesContainer, videoDetails.contentRating);
                }
                if (!TextUtils.isEmpty(videoDetails.duration)) {
                    addByline(inflater, bylinesContainer, videoDetails.duration);
                    break;
                }
                break;
        }
        bylinesContainer.setVisibility(bylinesContainer.getChildCount() > 0 ? 0 : 8);
    }

    private void addByline(LayoutInflater inflater, ViewGroup parent, CharSequence text) {
        TextView byline = (TextView) inflater.inflate(R.layout.details_summary_byline_label, parent, false);
        byline.setText(text);
        parent.addView(byline);
    }

    private void setupExtraLabels() {
        ViewGroup extraLabelsContainer = (ViewGroup) findViewById(R.id.title_extra_labels);
        extraLabelsContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        if (shouldDisplayOfferNote()) {
            CharSequence offerNote = this.mDoc.getOfferNote();
            if (!TextUtils.isEmpty(offerNote)) {
                addExtraLabel(inflater, extraLabelsContainer, offerNote);
            }
        }
        Libraries libraries = FinskyApp.get().getLibraries();
        Account owner = LibraryUtils.getOwnerWithCurrentAccount(this.mDoc, libraries, this.mAccount);
        if (owner != null) {
            Library library = libraries.getAccountLibrary(this.mAccount);
            if (LibraryUtils.isPreordered(this.mDoc, library)) {
                String preorderNote;
                Offer listingOffer = this.mDoc.getOffer(LibraryUtils.getOwnedPurchaseOfferType(this.mDoc, library));
                if (listingOffer == null || !shouldShowPreorderOnSaleDate(listingOffer)) {
                    preorderNote = this.mContext.getString(R.string.owned_preorder_note_no_sale_date);
                } else {
                    long onSaleDate = listingOffer.onSaleDate;
                    preorderNote = this.mContext.getString(R.string.owned_preorder_note, new Object[]{DateUtils.formatShortDisplayDateUtc(onSaleDate)});
                }
                addExtraLabel(inflater, extraLabelsContainer, preorderNote);
            }
        }
        if (owner == null && !FinskyApp.get().getExperiments().isEnabled("cl:billing.hide_sale_prices")) {
            CharSequence discountNote = getDiscountOfferNote(libraries);
            if (!TextUtils.isEmpty(discountNote)) {
                addExtraLabel(inflater, extraLabelsContainer, discountNote);
            }
        }
        extraLabelsContainer.setVisibility(extraLabelsContainer.getChildCount() > 0 ? 0 : 8);
    }

    private void addExtraLabel(LayoutInflater inflater, ViewGroup parent, CharSequence text) {
        TextView extraLabel = (TextView) inflater.inflate(R.layout.details_summary_extra_label, parent, false);
        extraLabel.setText(text);
        extraLabel.setTextColor(CorpusResourceUtils.getPrimaryColor(this.mContext, this.mDoc.getBackend()));
        parent.addView(extraLabel);
    }

    private void setupExtraLabelsApp() {
        int i = 8;
        ViewGroup extraLabelsBottomContainer = (ViewGroup) findViewById(R.id.title_extra_labels_bottom);
        if (this.mDoc.getDocumentType() != 1) {
            extraLabelsBottomContainer.setVisibility(8);
            return;
        }
        boolean hasExtraLabelsBottom = false;
        ViewGroup extraLabelsBottomLeading = (ViewGroup) extraLabelsBottomContainer.findViewById(R.id.extra_labels_bottom_leading);
        extraLabelsBottomLeading.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        AppDetails appDetails = this.mDoc.getAppDetails();
        if (!(FinskyApp.get().getExperiments().isEnabled("cl:details.hide_download_count_in_title") || TextUtils.isEmpty(appDetails.numDownloads))) {
            addExtraLabelBottom(inflater, extraLabelsBottomLeading, appDetails.numDownloads);
            hasExtraLabelsBottom = true;
        }
        if (this.mDoc.hasOptimalDeviceClassWarning()) {
            addExtraLabelBottom(inflater, extraLabelsBottomLeading, this.mDoc.getOptimalDeviceClassWarning().localizedMessage);
            hasExtraLabelsBottom = true;
        }
        if (appDetails != null && appDetails.declaresIab) {
            ((TextView) extraLabelsBottomContainer.findViewById(R.id.title_extra_label_in_app_purchase)).setText(R.string.in_app_purchases);
            hasExtraLabelsBottom = true;
        }
        if (hasExtraLabelsBottom) {
            i = 0;
        }
        extraLabelsBottomContainer.setVisibility(i);
    }

    protected void addExtraLabelBottom(LayoutInflater inflater, ViewGroup parent, CharSequence text) {
        TextView extraLabel = (TextView) inflater.inflate(R.layout.details_summary_extra_label_bottom, parent, false);
        extraLabel.setText(text);
        parent.addView(extraLabel);
    }

    private boolean shouldShowPreorderOnSaleDate(Offer purchaseOffer) {
        if (!purchaseOffer.hasPreorderFulfillmentDisplayDate || purchaseOffer.preorderFulfillmentDisplayDate <= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    protected boolean shouldDisplayOfferNote() {
        return LibraryUtils.getOwnerWithCurrentAccount(this.mDoc, FinskyApp.get().getLibraries(), this.mAccount) == null;
    }

    private CharSequence getDiscountOfferNote(Libraries libraries) {
        Offer offerWithLargestDiscount = DocUtils.getOfferWithLargestDiscountIfAny(this.mDoc, this.mDfeToc, libraries.getAccountLibrary(this.mAccount));
        if (offerWithLargestDiscount == null) {
            return null;
        }
        int listPriceNoteResourceId = getListPriceNoteResourceId(this.mDoc, offerWithLargestDiscount);
        String fullPrice = offerWithLargestDiscount.formattedFullAmount;
        String listPriceNote = this.mContext.getString(listPriceNoteResourceId, new Object[]{fullPrice});
        CharSequence listPriceBuilder = new SpannableStringBuilder(listPriceNote);
        int fullPriceStart = listPriceNote.indexOf(fullPrice);
        if (fullPriceStart < 0) {
            return listPriceBuilder;
        }
        listPriceBuilder.setSpan(new StrikethroughSpan(), fullPriceStart, fullPrice.length() + fullPriceStart, 17);
        return listPriceBuilder;
    }

    private static int getListPriceNoteResourceId(Document doc, Offer offer) {
        int documentType = doc.getDocumentType();
        int offerType = offer.offerType;
        if (documentType == 6) {
            switch (offerType) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    return R.string.list_price_sd;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return R.string.list_price_rental_sd;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    return R.string.list_price_rental_hd;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    return R.string.list_price_hd;
            }
        }
        if (documentType == 5 && offerType == 3) {
            return R.string.list_price_rental;
        }
        return R.string.list_price;
    }

    protected void setupBuyButtons(Account account, PlayActionButton buyButton, PlayActionButton buyButtonSecondary, boolean isOwnedByUser) {
        Offer purchaseOffer = this.mDoc.getOffer(1);
        if (purchaseOffer != null) {
            String buttonText;
            int logEventType;
            buyButton.setVisibility(0);
            if (this.mDoc.hasPreorderOffer()) {
                buttonText = this.mContext.getString(R.string.preorder, new Object[]{purchaseOffer.formattedAmount});
                logEventType = 234;
            } else {
                buttonText = getBuyButtonString(isOwnedByUser, 1);
                logEventType = getBuyButtonLoggingElementType(isOwnedByUser, 1);
            }
            buyButton.configure(this.mDoc.getBackend(), buttonText, this.mNavigationManager.getBuyImmediateClickListener(account, this.mDoc, purchaseOffer.offerType, null, this.mContinueUrl, logEventType, null));
        }
    }

    protected final String getBuyButtonString(boolean isOwnedByUser, int offerType) {
        if (isOwnedByUser) {
            return this.mContext.getString(R.string.install);
        }
        boolean needsCheckoutFlow = this.mDoc.needsCheckoutFlow(offerType);
        if (!needsCheckoutFlow) {
            if (this.mDoc.getBackend() == 3) {
                return this.mContext.getString(R.string.install);
            }
            if (this.mDoc.getBackend() == 1) {
                return this.mContext.getString(R.string.open);
            }
        }
        boolean useBuyVerb = FinskyApp.get().getExperiments().isEnabled("cl:billing.show_buy_verb_in_button");
        Offer purchaseOffer = this.mDoc.getOffer(offerType);
        if (purchaseOffer == null || !purchaseOffer.hasFormattedAmount) {
            return "";
        }
        String formattedAmount = purchaseOffer.formattedAmount;
        if (!useBuyVerb || !needsCheckoutFlow) {
            return formattedAmount;
        }
        return this.mContext.getString(R.string.buy, new Object[]{formattedAmount});
    }

    protected int getBuyButtonLoggingElementType(boolean isOwnedByUser, int offerType) {
        if (isOwnedByUser) {
            return 221;
        }
        if (!this.mDoc.needsCheckoutFlow(offerType)) {
            if (this.mDoc.getBackend() == 3) {
                return 221;
            }
            if (this.mDoc.getBackend() == 1) {
                return 225;
            }
        }
        return 200;
    }

    protected void setupActionButtons(boolean isInTransientState) {
        PlayActionButton tryButton = (PlayActionButton) findViewById(R.id.try_button);
        PlayActionButton buyButton = (PlayActionButton) findViewById(R.id.buy_button);
        PlayActionButton buyButton2 = (PlayActionButton) findViewById(R.id.buy_button2);
        PlayActionButton launchButton = (PlayActionButton) findViewById(R.id.launch_button);
        PlayActionButton downloadButton = (PlayActionButton) findViewById(R.id.download_button);
        launchButton.setVisibility(8);
        if (tryButton != null) {
            tryButton.setVisibility(8);
        }
        buyButton.setVisibility(8);
        if (buyButton2 != null) {
            buyButton2.setVisibility(8);
        }
        if (downloadButton != null) {
            downloadButton.setVisibility(8);
        }
        if (!this.mHideDynamicFeatures && !isInTransientState && displayActionButtonsIfNecessary(launchButton, buyButton, buyButton2, tryButton, downloadButton)) {
            syncButtonSectionVisibility();
        }
    }

    private void updateButtonActionStyle() {
        int i;
        int highestPriority = Integer.MAX_VALUE;
        for (i = 0; i < this.mButtonSection.getChildCount(); i++) {
            PlayActionButton button = (PlayActionButton) this.mButtonSection.getChildAt(i);
            if (button.getVisibility() == 0 && button.getPriority() < highestPriority) {
                highestPriority = button.getPriority();
            }
        }
        for (i = 0; i < this.mButtonSection.getChildCount(); i++) {
            button = (PlayActionButton) this.mButtonSection.getChildAt(i);
            if (button.getVisibility() == 0) {
                if (button.getPriority() == highestPriority) {
                    button.setActionStyle(0);
                } else {
                    button.setActionStyle(2);
                }
            }
        }
    }

    protected boolean displayActionButtonsIfNecessary(PlayActionButton launchButton, PlayActionButton buyButton, PlayActionButton buyButton2, PlayActionButton tryButton, PlayActionButton downloadButton) {
        Libraries libraries = FinskyApp.get().getLibraries();
        Library library = libraries.getAccountLibrary(this.mAccount);
        Account owner = LibraryUtils.getOwnerWithCurrentAccount(this.mDoc, libraries, this.mAccount);
        if (owner != null) {
            if (LibraryUtils.isPreordered(this.mDoc, library)) {
                configureCancelButton(launchButton, this.mDoc, owner);
            } else {
                configureLaunchButton(launchButton, this.mDoc, owner);
            }
            return true;
        } else if (!LibraryUtils.isAvailable(this.mDoc, this.mDfeToc, library)) {
            return false;
        } else {
            setupBuyButtons(this.mAccount, buyButton, buyButton2, false);
            if (this.mDoc.hasSample() && tryButton != null) {
                tryButton.setVisibility(0);
                if (LibraryUtils.isOfferOwned(this.mDoc, library, 2)) {
                    tryButton.configure(this.mDoc.getBackend(), (int) R.string.sample, this.mNavigationManager.getOpenClickListener(this.mDoc, this.mAccount, this.mContainerFragment));
                } else {
                    tryButton.configure(this.mDoc.getBackend(), R.string.sample, this.mNavigationManager.getBuyImmediateClickListener(this.mAccount, this.mDoc, 2, null, this.mContinueUrl, 222, null));
                }
            }
            return true;
        }
    }

    protected boolean displayActionButtonsIfNecessaryNew(PlayActionButton launchButton, PlayActionButton buyButton, PlayActionButton buyButton2, PlayActionButton tryButton, PlayActionButton downloadButton) {
        Libraries libraries = FinskyApp.get().getLibraries();
        int buyButtonsConfigured = 0;
        int totalButtonsConfigured = 0;
        DocumentActions documentActions = new DocumentActions();
        PurchaseButtonHelper.getDocumentActions(this.mAccount, null, libraries, null, this.mDfeToc, this.mDoc, documentActions);
        for (int i = 0; i < documentActions.actionCount; i++) {
            if (totalButtonsConfigured >= 2) {
                FinskyLog.w("Not supposed to have more than %d actions available", Integer.valueOf(2));
                break;
            }
            DocumentAction action = documentActions.getAction(i);
            if (action.actionType == 6 || action.actionType == 9) {
                configureActionButton(launchButton, action, documentActions.backend, libraries);
                totalButtonsConfigured++;
            } else if (action.actionType == 11 || action.actionType == 10) {
                configureActionButton(tryButton, action, documentActions.backend, libraries);
                totalButtonsConfigured++;
            } else if (action.actionType == 12) {
                configureActionButton(downloadButton, action, documentActions.backend, libraries);
                totalButtonsConfigured++;
            } else {
                if (buyButtonsConfigured == 0) {
                    configureActionButton(buyButton, action, documentActions.backend, libraries);
                } else {
                    configureActionButton(buyButton2, action, documentActions.backend, libraries);
                }
                buyButtonsConfigured++;
                totalButtonsConfigured++;
            }
        }
        return documentActions.hasAction();
    }

    private void configureActionButton(PlayActionButton button, DocumentAction action, int backend, Libraries libraries) {
        button.setVisibility(0);
        TextStyle actionStyle = new TextStyle();
        Resources res = this.mContext.getResources();
        if (res.getBoolean(R.bool.use_wide_details_layout)) {
            PurchaseButtonHelper.getActionStyleLong(action, backend, actionStyle);
        } else {
            PurchaseButtonHelper.getActionStyle(action, backend, actionStyle);
        }
        OnClickListener clickListener = null;
        if (PurchaseButtonHelper.canCreateClickListener(action)) {
            clickListener = PurchaseButtonHelper.getActionClickListener(action, backend, this.mNavigationManager, this.mContinueUrl, null, this.mContext);
        } else if (action.actionType == 9) {
            clickListener = getCancelPreorderClickListener(action.document, action.account);
        } else if (action.actionType == 12) {
            clickListener = getDownloadClickListener(action.document, action.account);
        } else {
            FinskyLog.wtf("Can't create a click listener for action %d", Integer.valueOf(action.actionType));
        }
        button.configure(backend, actionStyle.getString(res), clickListener);
    }

    protected void configureLaunchButton(PlayActionButton launchButton, Document doc, Account owner) {
        launchButton.setVisibility(0);
        launchButton.setEnabled(true);
        launchButton.configure(doc.getBackend(), CorpusResourceUtils.getOpenButtonStringId(doc.getBackend()), this.mNavigationManager.getOpenClickListener(doc, owner, this.mContainerFragment));
    }

    private void configureCancelButton(PlayActionButton cancelButton, Document doc, Account owner) {
        boolean z = false;
        cancelButton.setVisibility(0);
        if (!this.mIsCancelingPreorder) {
            z = true;
        }
        cancelButton.setEnabled(z);
        cancelButton.configure(doc.getBackend(), (int) R.string.cancel_preorder, getCancelPreorderClickListener(doc, owner));
    }

    protected OnClickListener getCancelPreorderClickListener(final Document doc, final Account owner) {
        return new OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = DetailsSummaryViewBinder.this.mContainerFragment.getFragmentManager();
                if (fragmentManager.findFragmentByTag("DetailsSummaryViewBinder.confirm_cancel_dialog") == null) {
                    DetailsSummaryViewBinder.this.mEventLogger.logClickEvent(235, null, DetailsSummaryViewBinder.this.mParentNode);
                    String message = DetailsSummaryViewBinder.this.mContext.getResources().getString(R.string.confirm_preorder_cancel, new Object[]{doc.getTitle()});
                    Builder builder = new Builder();
                    builder.setMessage(message).setPositiveId(R.string.yes).setNegativeId(R.string.no).setEventLog(305, doc.getServerLogsCookie(), 245, 246, null);
                    Bundle extraArguments = new Bundle();
                    extraArguments.putParcelable("DetailsSummaryViewBinder.doc", doc);
                    extraArguments.putString("DetailsSummaryViewBinder.ownerAccountName", owner.name);
                    builder.setCallback(DetailsSummaryViewBinder.this.mContainerFragment, 7, extraArguments);
                    builder.build().show(fragmentManager, "DetailsSummaryViewBinder.confirm_cancel_dialog");
                }
            }
        };
    }

    protected OnClickListener getDownloadClickListener(Document doc, Account owner) {
        return null;
    }

    protected void syncDynamicSection() {
        setDynamicFeaturesVisibility(LibraryUtils.isAvailable(this.mDoc, this.mDfeToc, FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount)));
        this.mButtonSection.setVisibility(4);
        if (!this.mHideDynamicFeatures) {
            if (this.mIsPendingRefund) {
                showDynamicStatus(R.string.refunding);
            } else {
                setupActionButtons(false);
            }
        }
    }

    protected void syncButtonSectionVisibility() {
        UiUtils.syncContainerVisibility(this.mButtonSection, 4);
    }

    protected void showDynamicStatus(int statusStringId) {
        TextView dynamicStatus = (TextView) this.mDynamicSection.findViewById(R.id.summary_dynamic_status);
        this.mButtonSection.setVisibility(8);
        dynamicStatus.setVisibility(0);
        dynamicStatus.setText(this.mContext.getResources().getString(statusStringId));
    }

    public void setDynamicFeaturesVisibility(boolean isDynamicVisible) {
        this.mHideDynamicFeatures = !isDynamicVisible;
    }

    public void setCompactMode(boolean isCompactMode) {
        this.mIsCompactMode = isCompactMode;
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 7) {
            Document doc = (Document) extraArguments.getParcelable("DetailsSummaryViewBinder.doc");
            DfeApi dfeApi = FinskyApp.get().getDfeApi(extraArguments.getString("DetailsSummaryViewBinder.ownerAccountName"));
            RevokeListenerWrapper revokeListenerWrapper = new RevokeListenerWrapper(FinskyApp.get().getLibraryReplicators(), dfeApi.getAccount(), new Listener<RevokeResponse>() {
                public void onResponse(RevokeResponse response) {
                    Toast.makeText(DetailsSummaryViewBinder.this.mContext, R.string.cancel_preorder_okay, 0).show();
                }
            });
            ErrorListener errorListener = new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    DetailsSummaryViewBinder.this.mIsCancelingPreorder = false;
                    DetailsSummaryViewBinder.this.refresh();
                    Toast.makeText(DetailsSummaryViewBinder.this.mContext, R.string.cancel_preorder_error, 0).show();
                }
            };
            this.mIsCancelingPreorder = true;
            refresh();
            dfeApi.revoke(doc.getDocId(), 1, revokeListenerWrapper, errorListener);
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }
}
