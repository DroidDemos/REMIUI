package com.google.android.finsky.library;

public class LibraryInAppSubscriptionEntry extends LibrarySubscriptionEntry {
    public final String signature;
    public final String signedPurchaseData;

    public LibraryInAppSubscriptionEntry(String accountName, String libraryId, int backend, String docId, int offerType, long documentHash, long validUntilTimestampMs, long initiationTimestampMs, long trialUntilTimestampMs, boolean autoRenewing, String signedPurchaseData, String signature) {
        super(accountName, libraryId, backend, docId, offerType, documentHash, Long.valueOf(validUntilTimestampMs), initiationTimestampMs, trialUntilTimestampMs, autoRenewing);
        this.signedPurchaseData = signedPurchaseData;
        this.signature = signature;
    }
}
