package com.google.android.finsky.utils;

import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.library.LibraryEntry;
import com.google.android.finsky.protos.DocumentV2.VoucherInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VoucherUtils {
    private static LibraryEntry sLibraryEntryForVoucherOwnership;

    public static boolean hasVouchers(AccountLibrary accountLibrary) {
        for (LibraryEntry entry : accountLibrary.getLibrary(AccountLibrary.LIBRARY_ID_COMMERCE)) {
            if (entry.getDocType() == 29) {
                return true;
            }
        }
        return false;
    }

    public static Collection<String> getVoucherIds(AccountLibrary accountLibrary) {
        ArrayList<String> result = null;
        for (LibraryEntry entry : accountLibrary.getLibrary(AccountLibrary.LIBRARY_ID_COMMERCE)) {
            if (entry.getDocType() == 29) {
                if (result == null) {
                    result = new ArrayList();
                }
                result.add(entry.getDocId());
            }
        }
        return result == null ? Collections.emptySet() : result;
    }

    public static boolean hasApplicableVouchers(Document doc, AccountLibrary accountLibrary) {
        return hasOwnedVouchers(doc, accountLibrary) && !LibraryUtils.isOwned(doc, (Library) accountLibrary);
    }

    static boolean hasOwnedVouchers(Document doc, AccountLibrary accountLibrary) {
        if (doc.hasVouchers()) {
            for (VoucherInfo docVoucher : doc.getVouchers()) {
                if (isVoucherOwned(docVoucher.doc.backendDocid, accountLibrary)) {
                    return true;
                }
            }
        }
        return false;
    }

    static synchronized boolean isVoucherOwned(String backendDocid, Library library) {
        boolean contains;
        synchronized (VoucherUtils.class) {
            if (sLibraryEntryForVoucherOwnership == null) {
                sLibraryEntryForVoucherOwnership = new LibraryEntry(LibraryEntry.UNKNOWN_ACCOUNT, AccountLibrary.LIBRARY_ID_COMMERCE, 10, backendDocid, 29, 1);
            } else {
                sLibraryEntryForVoucherOwnership.updateInPlace(backendDocid);
            }
            contains = library.contains(sLibraryEntryForVoucherOwnership);
        }
        return contains;
    }
}
