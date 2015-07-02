package com.google.android.finsky.utils;

import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.library.LibraryInAppSubscriptionEntry;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocUtils {
    private static final Map<String, Integer> PREFIX_TO_BACKEND;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$utils$DocUtils$OfferFilter;

        static {
            $SwitchMap$com$google$android$finsky$utils$DocUtils$OfferFilter = new int[OfferFilter.values().length];
            try {
                $SwitchMap$com$google$android$finsky$utils$DocUtils$OfferFilter[OfferFilter.PURCHASE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$utils$DocUtils$OfferFilter[OfferFilter.RENTAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum OfferFilter {
        PURCHASE,
        RENTAL;

        public boolean matches(int offerType) {
            switch (AnonymousClass1.$SwitchMap$com$google$android$finsky$utils$DocUtils$OfferFilter[ordinal()]) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    if (offerType == 1 || offerType == 7) {
                        return true;
                    }
                    return false;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return offerType == 3 || offerType == 4;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    static {
        PREFIX_TO_BACKEND = new HashMap();
        PREFIX_TO_BACKEND.put("app", Integer.valueOf(3));
        PREFIX_TO_BACKEND.put("album", Integer.valueOf(2));
        PREFIX_TO_BACKEND.put("artist", Integer.valueOf(2));
        PREFIX_TO_BACKEND.put("book", Integer.valueOf(1));
        PREFIX_TO_BACKEND.put("device", Integer.valueOf(5));
        PREFIX_TO_BACKEND.put("magazine", Integer.valueOf(6));
        PREFIX_TO_BACKEND.put("magazineissue", Integer.valueOf(6));
        PREFIX_TO_BACKEND.put("newsedition", Integer.valueOf(6));
        PREFIX_TO_BACKEND.put("newsissue", Integer.valueOf(6));
        PREFIX_TO_BACKEND.put("movie", Integer.valueOf(4));
        PREFIX_TO_BACKEND.put("song", Integer.valueOf(2));
        PREFIX_TO_BACKEND.put("tvepisode", Integer.valueOf(7));
        PREFIX_TO_BACKEND.put("tvseason", Integer.valueOf(7));
        PREFIX_TO_BACKEND.put("tvshow", Integer.valueOf(7));
    }

    public static int docidToBackend(String docid) {
        int length = docid.length();
        if (length <= 0) {
            return -1;
        }
        for (int index = 0; index < length; index++) {
            char c = docid.charAt(index);
            if (c == '-' || c == ':') {
                Integer backend = (Integer) PREFIX_TO_BACKEND.get(docid.substring(0, index));
                if (backend != null) {
                    return backend.intValue();
                }
                return -1;
            }
        }
        return 3;
    }

    public static boolean canRate(Libraries libraries, Document document) {
        switch (document.getDocumentType()) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (libraries.getAppOwners(document.getAppDetails().packageName).isEmpty()) {
                    return false;
                }
                return true;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                return true;
            default:
                return false;
        }
    }

    public static String getPackageNameForSubscription(String subscriptionDocId) {
        if (subscriptionDocId.startsWith("subs:")) {
            return extractPackageNameForInApp(subscriptionDocId);
        }
        return null;
    }

    public static String getPackageNameForInApp(String inAppDocId) {
        if (inAppDocId.startsWith("inapp:")) {
            return extractPackageNameForInApp(inAppDocId);
        }
        return null;
    }

    private static String extractPackageNameForInApp(String docId) {
        int start = docId.indexOf(58);
        int end = docId.indexOf(58, start + 1);
        if (start <= 0 || start >= end || end >= docId.length()) {
            return null;
        }
        return docId.substring(start + 1, end);
    }

    public static String extractSkuForInApp(String docId) {
        int start = docId.lastIndexOf(58);
        if (start <= 0 || start >= docId.length()) {
            return null;
        }
        return docId.substring(start + 1, docId.length());
    }

    public static boolean hasAutoRenewingSubscriptions(Libraries libraries, String packageName) {
        for (AccountLibrary accountLibrary : libraries.getAccountLibraries()) {
            List<LibraryInAppSubscriptionEntry> subscriptionList = accountLibrary.getSubscriptionPurchasesForPackage(packageName);
            for (int i = 0; i < subscriptionList.size(); i++) {
                if (((LibraryInAppSubscriptionEntry) subscriptionList.get(i)).isAutoRenewing) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getAvailabilityRestrictionResourceId(Document document) {
        int restriction = document.getAvailabilityRestriction();
        int resourceId = com.android.vending.R.string.availability_restriction_generic;
        switch (restriction) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                resourceId = com.android.vending.R.string.availability_restriction_country;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                resourceId = com.android.vending.R.string.availability_restriction_not_in_group;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                if (document.getDocumentType() != 1) {
                    resourceId = com.android.vending.R.string.availability_restriction_hardware;
                    break;
                }
                resourceId = com.android.vending.R.string.availability_restriction_hardware_app;
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                resourceId = com.android.vending.R.string.availability_restriction_carrier;
                break;
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                resourceId = com.android.vending.R.string.availability_restriction_country_or_carrier;
                break;
            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                resourceId = com.android.vending.R.string.availability_restriction_search_level;
                break;
        }
        FinskyLog.d("Item is not available. Reason: " + restriction, new Object[0]);
        return resourceId;
    }

    public static Offer getListingOffer(Document document, DfeToc dfeToc, Library library) {
        if (document.getDocumentType() != 16 && document.getDocumentType() != 24) {
            return getLowestPricedOffer(document.getAvailableOffers(), true);
        }
        List<Document> subscriptions = getSubscriptions(document, dfeToc, library);
        int subscriptionCount = subscriptions.size();
        if (subscriptionCount > 0) {
            Offer[] subscriptionOffers;
            if (subscriptionCount == 1) {
                subscriptionOffers = ((Document) subscriptions.get(0)).getAvailableOffers();
            } else {
                int i;
                int numOffers = 0;
                for (i = 0; i < subscriptionCount; i++) {
                    numOffers += ((Document) subscriptions.get(i)).getAvailableOffers().length;
                }
                int dstPos = 0;
                subscriptionOffers = new Offer[numOffers];
                for (i = 0; i < subscriptionCount; i++) {
                    Offer[] offers = ((Document) subscriptions.get(i)).getAvailableOffers();
                    System.arraycopy(offers, 0, subscriptionOffers, dstPos, offers.length);
                    dstPos += offers.length;
                }
            }
            Offer cheapestSubscription = getLowestPricedOffer(subscriptionOffers, false);
            if (cheapestSubscription == null) {
                cheapestSubscription = getLowestPricedOffer(subscriptionOffers, true);
            }
            if (cheapestSubscription != null) {
                return cheapestSubscription;
            }
        }
        Document currentIssue = getMagazineCurrentIssueDocument(document);
        if (currentIssue != null) {
            Offer currentOffer = getMagazineIssueOffer(currentIssue, dfeToc, library);
            if (currentOffer != null && currentOffer.hasFormattedAmount) {
                return currentOffer;
            }
        }
        return null;
    }

    public static int getNumberOfValidOffers(Offer[] offers) {
        int result = 0;
        for (Offer offer : offers) {
            if (offer.hasFormattedAmount) {
                int offerType = offer.offerType;
                if (offerType == 1 || offerType == 7 || offerType == 3 || offerType == 4) {
                    result++;
                }
            }
        }
        return result;
    }

    public static Offer getLowestPricedOffer(Offer[] offers, boolean freeOk) {
        return getLowestPricedOffer(offers, freeOk, null);
    }

    public static Offer getLowestPricedOffer(Offer[] offers, boolean freeOk, OfferFilter offerFilter) {
        Offer lowestPricedOffer = null;
        long lowestPrice = Long.MAX_VALUE;
        for (Offer offer : offers) {
            if (offer.hasFormattedAmount) {
                int offerType = offer.offerType;
                if ((offerType == 1 || offerType == 7 || offerType == 3 || offerType == 4) && (offerFilter == null || offerFilter.matches(offerType))) {
                    long currentPrice = offer.micros;
                    if ((freeOk || currentPrice != 0) && currentPrice < lowestPrice) {
                        lowestPrice = currentPrice;
                        lowestPricedOffer = offer;
                    }
                }
            }
        }
        return lowestPricedOffer;
    }

    private static float getOfferDiscountRatio(Offer offer) {
        if (!offer.hasFullPriceMicros) {
            return 0.0f;
        }
        long fullMicros = offer.fullPriceMicros;
        long discountMicros = fullMicros - offer.micros;
        if (fullMicros <= 0 || discountMicros <= 0) {
            return 0.0f;
        }
        return ((float) discountMicros) / ((float) fullMicros);
    }

    public static boolean hasDiscount(Offer offer) {
        return getOfferDiscountRatio(offer) > 0.0f;
    }

    private static Offer getOfferWithLargestDiscountIfAny(Offer[] offers) {
        float currentLargestDiscount = 0.0f;
        Offer currentResult = null;
        for (Offer offer : offers) {
            float offerDiscount = getOfferDiscountRatio(offer);
            if (offerDiscount > currentLargestDiscount) {
                currentLargestDiscount = offerDiscount;
                currentResult = offer;
            }
        }
        return currentResult;
    }

    public static Offer getOfferWithLargestDiscountIfAny(Document document, DfeToc dfeToc, Library library) {
        if (document.getDocumentType() != 16 && document.getDocumentType() != 24) {
            return getOfferWithLargestDiscountIfAny(document.getAvailableOffers());
        }
        List<Document> subscriptions = getSubscriptions(document, dfeToc, library);
        float currentLargestDiscount = 0.0f;
        Offer currentResult = null;
        int subscriptionCount = subscriptions.size();
        for (int i = 0; i < subscriptionCount; i++) {
            Offer subscriptionLargestDiscountOffer = getOfferWithLargestDiscountIfAny(((Document) subscriptions.get(i)).getAvailableOffers());
            if (subscriptionLargestDiscountOffer != null) {
                float subscriptionDiscount = getOfferDiscountRatio(subscriptionLargestDiscountOffer);
                if (subscriptionDiscount > currentLargestDiscount) {
                    currentLargestDiscount = subscriptionDiscount;
                    currentResult = subscriptionLargestDiscountOffer;
                }
            }
        }
        Document currentIssue = getMagazineCurrentIssueDocument(document);
        if (currentIssue == null) {
            return currentResult;
        }
        Offer currentIssueOffer = getMagazineIssueOffer(currentIssue, dfeToc, library);
        if (currentIssueOffer == null || !currentIssueOffer.hasFormattedAmount) {
            return currentResult;
        }
        float currentIssueDiscount = getOfferDiscountRatio(currentIssueOffer);
        if (currentIssueDiscount <= currentLargestDiscount) {
            return currentResult;
        }
        currentLargestDiscount = currentIssueDiscount;
        return currentIssueOffer;
    }

    public static Offer getMagazineIssueOffer(Document issueDoc, DfeToc dfeToc, Library library) {
        if ((issueDoc.getDocumentType() != 17 && issueDoc.getDocumentType() != 25) || !LibraryUtils.isAvailable(issueDoc, dfeToc, library)) {
            return null;
        }
        Offer[] currentOffers = issueDoc.getAvailableOffers();
        if (currentOffers.length > 0) {
            return currentOffers[0];
        }
        return null;
    }

    public static Document getMagazineCurrentIssueDocument(Document doc) {
        if (doc.getDocumentType() != 16 && doc.getDocumentType() != 24) {
            throw new IllegalArgumentException("This method should be called only on magazine docs. Passed type " + doc.getDocumentType());
        } else if (doc.getChildCount() == 0) {
            return null;
        } else {
            return doc.getChildAt(0);
        }
    }

    public static List<Document> getSubscriptions(Document doc, DfeToc dfeToc, Library library) {
        if (!doc.hasSubscriptions()) {
            return Collections.emptyList();
        }
        List<Document> newArrayList = Lists.newArrayList();
        List<Document> docSubscriptions = doc.getSubscriptionsList();
        int docSubscriptionCount = docSubscriptions.size();
        for (int i = 0; i < docSubscriptionCount; i++) {
            Document subscription = (Document) docSubscriptions.get(i);
            if (LibraryUtils.isAvailable(subscription, dfeToc, library) && subscription.getAvailableOffers().length > 0) {
                newArrayList.add(subscription);
            }
        }
        return newArrayList;
    }

    public static Docid createDocid(int backend, int docType, String backendDocid) {
        Docid docId = new Docid();
        docId.backend = backend;
        docId.hasBackend = true;
        docId.type = docType;
        docId.hasType = true;
        docId.backendDocid = backendDocid;
        docId.hasBackendDocid = true;
        return docId;
    }

    public static boolean isInAppDocid(Docid docid) {
        return docid.backend == 3 && (docid.type == 11 || docid.type == 15);
    }

    public static String getMusicSubscriptionDocid(String backendDocid) {
        return "music-subscription_" + backendDocid;
    }
}
