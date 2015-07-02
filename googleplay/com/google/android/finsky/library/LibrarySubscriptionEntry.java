package com.google.android.finsky.library;

public class LibrarySubscriptionEntry extends LibraryEntry {
    public final long initiationTimestampMs;
    public final boolean isAutoRenewing;
    public final long trialUntilTimestampMs;

    public LibrarySubscriptionEntry(String accountName, String libraryId, int backend, String docId, int offerType, long documentHash, Long validUntilTimestampMs, long initiationTimestampMs, long trialUntilTimestampMs, boolean autoRenewing) {
        super(accountName, libraryId, backend, docId, 15, offerType, documentHash, validUntilTimestampMs.longValue(), false);
        this.initiationTimestampMs = initiationTimestampMs;
        this.trialUntilTimestampMs = trialUntilTimestampMs;
        this.isAutoRenewing = autoRenewing;
    }
}
