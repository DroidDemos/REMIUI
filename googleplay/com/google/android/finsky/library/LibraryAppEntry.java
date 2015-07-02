package com.google.android.finsky.library;

public class LibraryAppEntry extends LibraryEntry {
    public static final String[] ANY_CERTIFICATE_HASHES;
    public final String[] certificateHashes;
    public final long refundPostDeliveryWindowMs;
    public final long refundPreDeliveryEndtimeMs;

    static {
        ANY_CERTIFICATE_HASHES = null;
    }

    public LibraryAppEntry(String accountName, String docId, int offerType, long documentHash, String[] certificateHashes) {
        this(accountName, docId, offerType, documentHash, certificateHashes, 0, 0);
    }

    public LibraryAppEntry(String accountName, String docId, int offerType, long documentHash, String[] certificateHashes, long refundPreDeliveryEndtimeMs, long refundPostDeliveryWindowMs) {
        super(accountName, AccountLibrary.LIBRARY_ID_APPS, 3, docId, 1, offerType, documentHash, Long.MAX_VALUE, false);
        this.certificateHashes = certificateHashes;
        this.refundPreDeliveryEndtimeMs = refundPreDeliveryEndtimeMs;
        this.refundPostDeliveryWindowMs = refundPostDeliveryWindowMs;
    }

    public String toString() {
        return String.format("{package=%s}", new Object[]{getDocId()});
    }

    public boolean hasMatchingCertificateHash(String[] matchCertificateHashes) {
        if (matchCertificateHashes == ANY_CERTIFICATE_HASHES) {
            return true;
        }
        for (String matchHash : matchCertificateHashes) {
            for (String libraryHash : this.certificateHashes) {
                if (matchHash.equals(libraryHash)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        if (!(other instanceof LibraryAppEntry)) {
            return true;
        }
        LibraryAppEntry libraryAppEntry = (LibraryAppEntry) other;
        if (this.certificateHashes.length != libraryAppEntry.certificateHashes.length) {
            return false;
        }
        for (String equals : this.certificateHashes) {
            boolean innerMatchFound = false;
            for (int j = 0; j < count; j++) {
                if (equals.equals(libraryAppEntry.certificateHashes[j])) {
                    innerMatchFound = true;
                    break;
                }
            }
            if (!innerMatchFound) {
                return false;
            }
        }
        return true;
    }
}
