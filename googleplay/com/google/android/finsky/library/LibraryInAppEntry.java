package com.google.android.finsky.library;

public class LibraryInAppEntry extends LibraryEntry {
    public final String signature;
    public final String signedPurchaseData;

    public LibraryInAppEntry(String accountName, String libraryId, String docId, int offerType, String signedPurchaseData, String signature, long documentHash) {
        super(accountName, libraryId, 3, docId, 11, offerType, documentHash, Long.MAX_VALUE, false);
        this.signedPurchaseData = signedPurchaseData;
        this.signature = signature;
    }
}
