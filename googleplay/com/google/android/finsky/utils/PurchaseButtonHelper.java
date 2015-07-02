package com.google.android.finsky.utils;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.DocUtils.OfferFilter;
import com.google.android.play.utils.PlayUtils;
import com.google.android.wallet.instrumentmanager.R;

public class PurchaseButtonHelper {

    public static class DocumentAction {
        public Account account;
        public int actionType;
        public Document document;
        public OfferFilter offerFilter;
        public String offerFullText;
        public String offerText;
        public int offerType;

        public DocumentAction() {
            reset();
        }

        public void reset() {
            this.actionType = 0;
            this.offerText = null;
            this.offerFullText = null;
            this.offerType = -1;
            this.offerFilter = null;
            this.document = null;
            this.account = null;
        }
    }

    public static class DocumentActions {
        public int actionCount;
        public final DocumentAction[] actions;
        public int backend;
        public boolean displayAsOwned;
        public String listingOfferFullText;
        public String listingOfferText;
        public int status;

        public DocumentActions() {
            this.actions = new DocumentAction[2];
            for (int i = 0; i < 2; i++) {
                this.actions[i] = new DocumentAction();
            }
            reset();
        }

        public void reset() {
            this.displayAsOwned = false;
            this.status = 0;
            this.backend = -1;
            this.actionCount = 0;
            this.listingOfferText = null;
            this.listingOfferFullText = null;
            for (int i = 0; i < 2; i++) {
                this.actions[i].reset();
            }
        }

        public boolean hasAction() {
            return this.actionCount > 0;
        }

        public boolean hasStatus() {
            return this.status != 0;
        }

        private void addAction(int actionType, String offerText, String offerFullText, int offerType, OfferFilter offerFilter, Document document, Account account) {
            if (this.actionCount < 2) {
                this.actions[this.actionCount].reset();
                this.actions[this.actionCount].actionType = actionType;
                this.actions[this.actionCount].offerText = offerText;
                this.actions[this.actionCount].offerFullText = offerFullText;
                this.actions[this.actionCount].offerType = offerType;
                this.actions[this.actionCount].offerFilter = offerFilter;
                this.actions[this.actionCount].document = document;
                this.actions[this.actionCount].account = account;
                this.actionCount++;
                return;
            }
            FinskyLog.wtf("Trying to add action %d but no more room for actions", Integer.valueOf(actionType));
        }

        public void addOfferAction(int actionType, Offer offer, Document document, Account account) {
            String str;
            String str2 = offer.formattedAmount;
            if (PurchaseButtonHelper.shouldAddFullText(offer)) {
                str = offer.formattedFullAmount;
            } else {
                str = null;
            }
            addAction(actionType, str2, str, offer.offerType, null, document, account);
        }

        public void addDisambiguationAction(int actionType, Offer offer, Document document, Account account) {
            String str;
            String str2 = offer.formattedAmount;
            if (PurchaseButtonHelper.shouldAddFullText(offer)) {
                str = offer.formattedFullAmount;
            } else {
                str = null;
            }
            addAction(actionType, str2, str, 0, null, document, account);
        }

        public void addAction(int actionType, Document document, Account account) {
            addAction(actionType, null, null, -1, null, document, account);
        }

        public int addActionFromOfferList(int actionType, Offer[] offers, OfferFilter offerFilter, Document document, Account account) {
            int numMatches = 0;
            Offer offerMatch = null;
            for (Offer offer : offers) {
                if (offerFilter.matches(offer.offerType)) {
                    offerMatch = offer;
                    numMatches++;
                }
            }
            if (numMatches == 0) {
                return 0;
            }
            if (numMatches == 1) {
                addOfferAction(actionType, offerMatch, document, account);
                return 1;
            }
            Offer displayOffer = DocUtils.getLowestPricedOffer(offers, true, offerFilter);
            addAction(actionType, displayOffer.formattedAmount, PurchaseButtonHelper.shouldAddFullText(displayOffer) ? displayOffer.formattedFullAmount : null, 0, offerFilter, document, account);
            return numMatches;
        }

        public DocumentAction getAction(int i) {
            if (i < this.actionCount) {
                return this.actions[i];
            }
            FinskyLog.wtf("Request for invalid action #%d (%d available actions)", Integer.valueOf(i), Integer.valueOf(this.actionCount));
            return null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.actionCount; i++) {
                if (i != 0) {
                    sb.append(',');
                }
                sb.append(this.actions[i].actionType);
            }
            return sb.toString();
        }
    }

    public static class TextStyle {
        public String offerFullText;
        public String offerText;
        public int resourceId;

        public TextStyle() {
            this.resourceId = -1;
            this.offerText = null;
            this.offerFullText = null;
        }

        public void reset() {
            this.resourceId = -1;
            this.offerText = null;
            this.offerFullText = null;
        }

        public String getString(Resources res) {
            if (this.resourceId == -1) {
                return this.offerText;
            }
            if (this.offerText == null) {
                return res.getString(this.resourceId);
            }
            return res.getString(this.resourceId, new Object[]{this.offerText});
        }
    }

    public static void getDocumentActions(Account currentAccount, Installer installer, Libraries libraries, AppStates appStates, DfeToc dfeToc, Document document, DocumentActions outDocumentActions) {
        outDocumentActions.reset();
        outDocumentActions.backend = document.getBackend();
        Library library = libraries.getAccountLibrary(currentAccount);
        Offer listingOffer = DocUtils.getListingOffer(document, dfeToc, library);
        Account owner = LibraryUtils.getOwnerWithCurrentAccount(document, libraries, currentAccount);
        boolean ownedByUser = owner != null;
        boolean isPreordered = ownedByUser && LibraryUtils.isPreordered(document, library);
        if (listingOffer != null || isPreordered) {
            outDocumentActions.displayAsOwned = ownedByUser;
            switch (outDocumentActions.backend) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                case R.styleable.WalletImFormEditText_required /*4*/:
                    if (ownedByUser) {
                        if (isPreordered) {
                            outDocumentActions.addAction(9, document, owner);
                            outDocumentActions.status = 3;
                            return;
                        }
                        outDocumentActions.addAction(6, document, owner);
                        if (LibraryUtils.isOfferOwned(document, library, 3) || LibraryUtils.isOfferOwned(document, library, 4)) {
                            outDocumentActions.status = 7;
                        } else {
                            outDocumentActions.status = 6;
                        }
                        if (outDocumentActions.backend == 4 && !PlayUtils.isTv(FinskyApp.get())) {
                            outDocumentActions.addAction(12, document, currentAccount);
                            return;
                        }
                        return;
                    } else if (LibraryUtils.isAvailable(document, dfeToc, library)) {
                        addOfferActions(document, listingOffer, currentAccount, library, outDocumentActions);
                        return;
                    } else {
                        return;
                    }
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    getActionsForApp(currentAccount, owner, ownedByUser, installer, library, libraries, appStates, dfeToc, document, listingOffer, outDocumentActions);
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    getActionsForMagazine(currentAccount, library, libraries, dfeToc, document, listingOffer, outDocumentActions);
                    return;
                default:
                    FinskyLog.wtf("Unsupported backend: %d", Integer.valueOf(document.getBackend()));
                    if (LibraryUtils.isAvailable(document, dfeToc, library)) {
                        addOfferActions(document, listingOffer, currentAccount, library, outDocumentActions);
                        return;
                    }
                    return;
            }
        }
    }

    private static void getActionsForApp(Account currentAccount, Account owner, boolean ownedByUser, Installer installer, Library accountLibrary, Libraries libraries, AppStates appStates, DfeToc dfeToc, Document document, Offer listingOffer, DocumentActions outDocumentActions) {
        outDocumentActions.displayAsOwned = false;
        if (listingOffer != null) {
            Account accountToUse;
            if (owner != null) {
                accountToUse = owner;
            } else {
                accountToUse = currentAccount;
            }
            if (document.getDocumentType() == 1) {
                AppDetails appDetails = document.getAppDetails();
                AppActionAnalyzer appActions = new AppActionAnalyzer(appDetails.packageName, appStates, libraries);
                if (appActions.isInstalled) {
                    if (appActions.isDisabled) {
                        outDocumentActions.status = 2;
                        outDocumentActions.addAction(14, document, accountToUse);
                    } else if (!appActions.hasUpdateAvailable(document)) {
                        outDocumentActions.addAction(6, document, accountToUse);
                    } else if (installer.getState(appDetails.packageName) != InstallerState.NOT_TRACKED) {
                        outDocumentActions.status = 9;
                    } else {
                        outDocumentActions.addOfferAction(8, listingOffer, document, accountToUse);
                        outDocumentActions.status = 5;
                    }
                    outDocumentActions.displayAsOwned = true;
                    return;
                } else if (installer.getState(appDetails.packageName) != InstallerState.NOT_TRACKED) {
                    outDocumentActions.status = 1;
                    return;
                }
            }
            if (!LibraryUtils.isAvailable(document, dfeToc, accountLibrary)) {
                return;
            }
            if (ownedByUser && listingOffer.checkoutFlowRequired) {
                outDocumentActions.displayAsOwned = true;
                outDocumentActions.addOfferAction(7, listingOffer, document, accountToUse);
                outDocumentActions.status = 6;
            } else if (LibraryUtils.isAvailable(document, dfeToc, accountLibrary)) {
                addOfferActions(document, listingOffer, accountToUse, accountLibrary, outDocumentActions);
            }
        }
    }

    private static void getActionsForMagazine(Account currentAccount, Library accountLibrary, Libraries libraries, DfeToc dfeToc, Document document, Offer listingOffer, DocumentActions outDocumentActions) {
        if (listingOffer != null) {
            outDocumentActions.listingOfferText = listingOffer.formattedAmount;
            if (shouldAddFullText(listingOffer)) {
                outDocumentActions.listingOfferFullText = listingOffer.formattedFullAmount;
            }
        }
        Document issueDoc = (document.getDocumentType() == 17 || document.getDocumentType() == 25) ? document : null;
        if (document.getDocumentType() == 16 || document.getDocumentType() == 24) {
            issueDoc = DocUtils.getMagazineCurrentIssueDocument(document);
        }
        boolean issueOwned = false;
        if (!(issueDoc == null || LibraryUtils.getOwnerWithCurrentAccount(issueDoc, libraries, currentAccount) == null)) {
            issueOwned = true;
            outDocumentActions.displayAsOwned = true;
            outDocumentActions.addAction(6, issueDoc, currentAccount);
        }
        if (document.hasSubscriptions()) {
            if (LibraryUtils.getOwnerWithCurrentAccount(document.getSubscriptionsList(), libraries, currentAccount) != null) {
                if (issueDoc == null) {
                    outDocumentActions.displayAsOwned = true;
                    outDocumentActions.addAction(6, document, currentAccount);
                }
                outDocumentActions.status = 8;
            } else if (LibraryUtils.isAvailable(document, dfeToc, accountLibrary)) {
                outDocumentActions.addDisambiguationAction(2, listingOffer, document, currentAccount);
            }
        }
        if (!issueOwned && issueDoc != null && LibraryUtils.isAvailable(issueDoc, dfeToc, accountLibrary)) {
            Offer issueOffer = DocUtils.getMagazineIssueOffer(issueDoc, dfeToc, accountLibrary);
            if (issueOffer != null) {
                if (issueOffer.offerType == 2) {
                    outDocumentActions.addAction(6, issueDoc, currentAccount);
                } else if (issueOffer.checkoutFlowRequired) {
                    outDocumentActions.addOfferAction(issueOffer.micros > 0 ? 4 : 7, issueOffer, issueDoc, currentAccount);
                } else {
                    outDocumentActions.addAction(6, issueDoc, currentAccount);
                }
                if (!outDocumentActions.hasStatus()) {
                    outDocumentActions.status = 4;
                }
            }
        }
    }

    private static boolean shouldAddFullText(Offer offer) {
        return offer.hasMicros && offer.hasFullPriceMicros && offer.hasFormattedFullAmount && offer.fullPriceMicros > offer.micros;
    }

    private static void addOfferActions(Document document, Offer listingOffer, Account currentAccount, Library accountLibrary, DocumentActions outDocumentActions) {
        outDocumentActions.status = 4;
        outDocumentActions.listingOfferText = listingOffer.formattedAmount;
        if (shouldAddFullText(listingOffer)) {
            outDocumentActions.listingOfferFullText = listingOffer.formattedFullAmount;
        }
        Offer[] offers = document.getAvailableOffers();
        Offer sampleOffer = getSampleOffer(offers);
        boolean hasSampleOffer = sampleOffer != null;
        int availableButtons = 2 - (hasSampleOffer ? 1 : 0);
        if (offers.length <= 2) {
            for (Offer offer : offers) {
                int offerType = offer.offerType;
                if (!(offerType == 2 || offerType == 11)) {
                    if (Document.isPreorderOffer(offer)) {
                        outDocumentActions.addOfferAction(5, offer, document, currentAccount);
                    } else if (OfferFilter.RENTAL.matches(offerType)) {
                        outDocumentActions.addOfferAction(3, offer, document, currentAccount);
                    } else if (!OfferFilter.PURCHASE.matches(offerType)) {
                        FinskyLog.w("Don't know how to show action for offer type %d on document %s", Integer.valueOf(offerType), document);
                    } else if (offer.micros == 0) {
                        outDocumentActions.addOfferAction(7, offer, document, currentAccount);
                    } else {
                        outDocumentActions.addOfferAction(1, offer, document, currentAccount);
                    }
                }
            }
        } else if (availableButtons >= 2) {
            if ((0 + outDocumentActions.addActionFromOfferList(1, offers, OfferFilter.PURCHASE, document, currentAccount)) + outDocumentActions.addActionFromOfferList(3, offers, OfferFilter.RENTAL, document, currentAccount) != offers.length) {
                FinskyLog.wtf("Could only handle %d of %d offers", Integer.valueOf((0 + outDocumentActions.addActionFromOfferList(1, offers, OfferFilter.PURCHASE, document, currentAccount)) + outDocumentActions.addActionFromOfferList(3, offers, OfferFilter.RENTAL, document, currentAccount)), Integer.valueOf(offers.length));
            }
        } else if (availableButtons == 1) {
            outDocumentActions.addDisambiguationAction(13, listingOffer, document, currentAccount);
        } else {
            FinskyLog.wtf("We ran out of buttons without displaying any offers?", new Object[0]);
        }
        if (!hasSampleOffer) {
            return;
        }
        if (LibraryUtils.isOfferOwned(document, accountLibrary, 2)) {
            outDocumentActions.addOfferAction(10, sampleOffer, document, currentAccount);
        } else {
            outDocumentActions.addOfferAction(11, sampleOffer, document, currentAccount);
        }
    }

    private static Offer getSampleOffer(Offer[] offers) {
        for (Offer offer : offers) {
            if (offer.offerType == 2) {
                return offer;
            }
        }
        return null;
    }

    public static void getListingStyle(DocumentActions documentActions, TextStyle outListingStyle) {
        outListingStyle.reset();
        if (documentActions.hasStatus()) {
            switch (documentActions.status) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    outListingStyle.resourceId = com.android.vending.R.string.installing;
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    outListingStyle.resourceId = com.android.vending.R.string.disabled_list_state;
                    return;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    outListingStyle.resourceId = com.android.vending.R.string.preordered_list_state;
                    return;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    outListingStyle.offerFullText = documentActions.listingOfferFullText;
                    outListingStyle.offerText = documentActions.listingOfferText;
                    return;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    outListingStyle.resourceId = com.android.vending.R.string.updates_list_state;
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    outListingStyle.resourceId = com.android.vending.R.string.purchased_list_state;
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    outListingStyle.resourceId = com.android.vending.R.string.rented_list_state;
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    outListingStyle.resourceId = com.android.vending.R.string.subscribed_list_state;
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    outListingStyle.resourceId = com.android.vending.R.string.updating;
                    return;
                default:
                    FinskyLog.wtf("Unrecognized status %d", Integer.valueOf(documentActions.status));
                    return;
            }
        } else if (documentActions.displayAsOwned && documentActions.backend == 3) {
            outListingStyle.resourceId = com.android.vending.R.string.installed_list_state;
        }
    }

    public static void getActionStyle(DocumentAction action, int backend, TextStyle outActionStyle) {
        getActionStyleForFormat(action, backend, false, true, outActionStyle);
    }

    public static void getActionStyleLong(DocumentAction action, int backend, TextStyle outActionStyle) {
        getActionStyleForFormat(action, backend, true, true, outActionStyle);
    }

    public static void getActionStyleWithoutBuyPrefix(DocumentAction action, int backend, TextStyle outActionStyle) {
        getActionStyleForFormat(action, backend, false, false, outActionStyle);
    }

    public static void getActionStatus(DocumentActions documentActions, TextStyle outActionStyle) {
        outActionStyle.reset();
        switch (documentActions.status) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                outActionStyle.resourceId = com.android.vending.R.string.installing;
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                outActionStyle.resourceId = com.android.vending.R.string.disabled_list_state;
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                outActionStyle.resourceId = com.android.vending.R.string.preordered_list_state;
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                outActionStyle.resourceId = com.android.vending.R.string.updating;
                return;
            default:
                FinskyLog.wtf("Expected to have an available action with status %d", Integer.valueOf(documentActions.status));
                return;
        }
    }

    private static void getActionStyleForFormat(DocumentAction action, int backend, boolean longFormat, boolean useBuyPrefix, TextStyle outActionStyle) {
        outActionStyle.reset();
        switch (action.actionType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (action.offerType == 0) {
                    outActionStyle.resourceId = com.android.vending.R.string.purchase_resolution;
                } else if (backend == 4) {
                    if (action.offerType == 7) {
                        outActionStyle.resourceId = com.android.vending.R.string.buy_hd;
                    } else {
                        outActionStyle.resourceId = com.android.vending.R.string.buy_sd;
                    }
                } else if (useBuyPrefix) {
                    outActionStyle.resourceId = com.android.vending.R.string.buy;
                } else {
                    outActionStyle.resourceId = -1;
                }
                outActionStyle.offerText = action.offerText;
                outActionStyle.offerFullText = action.offerFullText;
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                outActionStyle.resourceId = com.android.vending.R.string.magazine_subscribe;
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (action.offerType == 0) {
                    outActionStyle.resourceId = com.android.vending.R.string.rent_resolution;
                } else if (backend != 4) {
                    outActionStyle.resourceId = com.android.vending.R.string.rent;
                } else if (action.offerType == 4) {
                    outActionStyle.resourceId = com.android.vending.R.string.rent_hd;
                } else {
                    outActionStyle.resourceId = com.android.vending.R.string.rent_sd;
                }
                outActionStyle.offerText = action.offerText;
                outActionStyle.offerFullText = action.offerFullText;
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                outActionStyle.resourceId = longFormat ? com.android.vending.R.string.magazine_buy_current_long : com.android.vending.R.string.magazine_buy_current;
                outActionStyle.offerText = action.offerText;
                outActionStyle.offerFullText = action.offerFullText;
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                if (backend != 4) {
                    outActionStyle.resourceId = com.android.vending.R.string.preorder;
                } else if (action.offerType == 7) {
                    outActionStyle.resourceId = com.android.vending.R.string.preorder_hd;
                } else {
                    outActionStyle.resourceId = com.android.vending.R.string.preorder_sd;
                }
                outActionStyle.offerText = action.offerText;
                outActionStyle.offerFullText = action.offerFullText;
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                switch (backend) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        outActionStyle.resourceId = com.android.vending.R.string.read;
                        return;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        outActionStyle.resourceId = com.android.vending.R.string.listen;
                        return;
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        outActionStyle.resourceId = com.android.vending.R.string.play;
                        return;
                    default:
                        outActionStyle.resourceId = com.android.vending.R.string.open;
                        return;
                }
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                if (backend == 3) {
                    outActionStyle.resourceId = com.android.vending.R.string.install;
                    return;
                } else if (backend == 6) {
                    outActionStyle.resourceId = com.android.vending.R.string.add_to_newsstand;
                    return;
                } else {
                    outActionStyle.resourceId = com.android.vending.R.string.add_to_library;
                    return;
                }
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                outActionStyle.resourceId = com.android.vending.R.string.updates_list_state;
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                outActionStyle.resourceId = com.android.vending.R.string.cancel_preorder;
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                outActionStyle.resourceId = com.android.vending.R.string.sample;
                return;
            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                outActionStyle.resourceId = com.android.vending.R.string.download;
                return;
            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                outActionStyle.resourceId = com.android.vending.R.string.purchase_or_rent_resolution;
                outActionStyle.offerText = action.offerText;
                outActionStyle.offerFullText = action.offerFullText;
                return;
            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                outActionStyle.resourceId = com.android.vending.R.string.enable;
                return;
            default:
                FinskyLog.wtf("Unrecognized action %d", Integer.valueOf(action.actionType));
                return;
        }
    }

    public static boolean canCreateClickListener(DocumentAction action) {
        return (action.actionType == 9 || action.actionType == 12) ? false : true;
    }

    public static OnClickListener getActionClickListener(final DocumentAction action, int backend, NavigationManager navigationManager, String continueUrl, PlayStoreUiElementNode clickNode, final Context context) {
        int elementType;
        switch (action.actionType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                if (action.offerType != 7) {
                    if (backend != 4) {
                        elementType = 200;
                        break;
                    }
                    elementType = 231;
                    break;
                }
                elementType = 232;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                elementType = 226;
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (action.offerType != 4) {
                    if (backend != 4) {
                        elementType = 200;
                        break;
                    }
                    elementType = 228;
                    break;
                }
                elementType = 229;
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                elementType = 200;
                break;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                elementType = 200;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return navigationManager.getOpenClickListener(action.document, action.account, clickNode);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                elementType = 221;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                elementType = 217;
                break;
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                elementType = 222;
                break;
            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                return new OnClickListener() {
                    public void onClick(View v) {
                        context.getPackageManager().setApplicationEnabledSetting(action.document.getDocId(), 1, 0);
                    }
                };
            default:
                FinskyLog.wtf("Unrecognized action %d", action);
                return null;
        }
        return navigationManager.getBuyImmediateClickListener(action.account, action.document, action.offerType, action.offerFilter, continueUrl, elementType, clickNode);
    }
}
