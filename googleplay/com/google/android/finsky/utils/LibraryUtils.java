package com.google.android.finsky.utils;

import android.accounts.Account;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.library.LibraryEntry;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Offer;
import java.util.List;

public class LibraryUtils {
    private static LibraryEntry sLibraryEntryForOwnership;
    private static LibraryEntry sMusicAllAccessLibraryEntry;

    public static boolean isAvailable(Document document, DfeToc dfeToc, Library library) {
        boolean isAvailable;
        if (document.getBackend() != 0) {
            if (dfeToc != null) {
                if (dfeToc.getCorpus(document.getBackend()) == null) {
                    FinskyLog.d("Corpus for %s is not available.", document.getDocId());
                    return false;
                }
            } else if (document.getBackend() != 3) {
                return false;
            }
        }
        if (document.getAvailabilityRestriction() == 1) {
            isAvailable = true;
        } else {
            isAvailable = false;
        }
        if (!isAvailable && document.isAvailableIfOwned() && isOwned(document, library)) {
            FinskyLog.d("%s available because owned, overriding [restriction=%d].", document.getDocId(), Integer.valueOf(restriction));
            isAvailable = true;
        }
        if (isAvailable) {
            return isAvailable;
        }
        FinskyLog.d("%s not available [restriction=%d].", document.getDocId(), Integer.valueOf(restriction));
        return isAvailable;
    }

    public static Account getOwnerWithCurrentAccount(Document document, Libraries libraries, Account currentAccount) {
        if (isOwned(document, libraries.getAccountLibrary(currentAccount))) {
            return currentAccount;
        }
        if (document.getDocumentType() == 1) {
            return getFirstOwner(document, libraries);
        }
        return null;
    }

    public static Account getOwnerWithCurrentAccount(List<Document> docs, Libraries libraries, Account account) {
        int docCount = docs.size();
        for (int i = 0; i < docCount; i++) {
            Account owner = getOwnerWithCurrentAccount((Document) docs.get(i), libraries, account);
            if (owner != null) {
                return owner;
            }
        }
        return null;
    }

    public static Account getFirstOwner(Document document, Libraries libraries) {
        List<AccountLibrary> accountLibraries = libraries.getAccountLibraries();
        int accountLibraryCount = accountLibraries.size();
        for (int i = 0; i < accountLibraryCount; i++) {
            Library accountLibrary = (AccountLibrary) accountLibraries.get(i);
            if (isOwned(document, accountLibrary)) {
                return accountLibrary.getAccount();
            }
        }
        return null;
    }

    public static boolean isOwned(Document document, Library library) {
        return isOwned(document.getFullDocid(), library);
    }

    public static boolean isOwned(Docid docid, Library library) {
        if (isOfferOwned(docid, library, 1)) {
            return true;
        }
        boolean rentalsAvailable;
        if (docid.backend == 4 || docid.backend == 1) {
            rentalsAvailable = true;
        } else {
            rentalsAvailable = false;
        }
        if (rentalsAvailable && isOfferOwned(docid, library, 3)) {
            return true;
        }
        if (docid.backend == 4 && (isOfferOwned(docid, library, 7) || isOfferOwned(docid, library, 4))) {
            return true;
        }
        return false;
    }

    public static synchronized boolean isOfferOwned(Document document, Library library, int offerType) {
        boolean isOfferOwned;
        synchronized (LibraryUtils.class) {
            isOfferOwned = isOfferOwned(document.getFullDocid(), library, offerType);
        }
        return isOfferOwned;
    }

    public static synchronized boolean isOfferOwned(Docid docId, Library library, int offerType) {
        boolean z = false;
        synchronized (LibraryUtils.class) {
            String libraryId = AccountLibrary.getLibraryIdFromBackend(docId.backend);
            if (sLibraryEntryForOwnership == null) {
                sLibraryEntryForOwnership = LibraryEntry.fromDocId(LibraryEntry.UNKNOWN_ACCOUNT, libraryId, docId, offerType);
            } else {
                sLibraryEntryForOwnership.updateInPlace(libraryId, docId, offerType);
            }
            LibraryEntry libraryEntry = library.get(sLibraryEntryForOwnership);
            if (libraryEntry != null) {
                if (!libraryEntry.isExpired()) {
                    z = true;
                }
            }
        }
        return z;
    }

    public static boolean isAvailableInMusicAllAccess(Document doc) {
        Offer offer = doc.getOffer(11);
        if (offer == null || offer.subscriptionContentTerms == null) {
            return false;
        }
        Docid requiredSub = offer.subscriptionContentTerms.requiredSubscription;
        int backend = requiredSub.backend;
        if (new LibraryEntry(LibraryEntry.UNKNOWN_ACCOUNT, AccountLibrary.getLibraryIdFromBackend(backend), backend, requiredSub.backendDocid, requiredSub.type, 1).equals(getMusicAllAccessLibraryEntry())) {
            return true;
        }
        return false;
    }

    public static boolean isMusicAllAccessSubscriber(Library library) {
        return library.contains(getMusicAllAccessLibraryEntry());
    }

    private static LibraryEntry getMusicAllAccessLibraryEntry() {
        if (sMusicAllAccessLibraryEntry == null) {
            sMusicAllAccessLibraryEntry = new LibraryEntry(LibraryEntry.UNKNOWN_ACCOUNT, AccountLibrary.getLibraryIdFromBackend(2), 2, (String) G.musicAllAccessSubscriptionBackendDocid.get(), 15, 1);
        }
        return sMusicAllAccessLibraryEntry;
    }

    public static int getOwnedPurchaseOfferType(Document document, Library library) {
        if (isOfferOwned(document, library, 1)) {
            return 1;
        }
        if (isOfferOwned(document, library, 7)) {
            return 7;
        }
        return -1;
    }

    public static boolean isPreordered(Document document, Library library) {
        int offerType = getOwnedPurchaseOfferType(document, library);
        if (offerType == -1) {
            return false;
        }
        LibraryEntry libraryEntry = library.get(LibraryEntry.fromDocument(LibraryEntry.UNKNOWN_ACCOUNT, AccountLibrary.getLibraryIdFromBackend(document.getBackend()), document, offerType));
        if (libraryEntry == null || !libraryEntry.isPreordered()) {
            return false;
        }
        Offer listingOffer = document.getOffer(offerType);
        if (listingOffer == null || Document.isPreorderOffer(listingOffer)) {
            return true;
        }
        return false;
    }
}
