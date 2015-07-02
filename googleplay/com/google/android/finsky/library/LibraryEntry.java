package com.google.android.finsky.library;

import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.Common.Docid;

public class LibraryEntry {
    public static final String UNKNOWN_ACCOUNT;
    private final String mAccountName;
    private int mBackendId;
    private String mDocId;
    private int mDocType;
    private final long mDocumentHash;
    private String mLibraryId;
    private int mOfferType;
    private final boolean mPreordered;
    private final long mValidUntilTimestampMs;

    static {
        UNKNOWN_ACCOUNT = null;
    }

    public static LibraryEntry fromDocument(String accountName, String libraryId, Document document, int offerType) {
        return new LibraryEntry(accountName, libraryId, document.getBackend(), document.getBackendDocId(), document.getDocumentType(), offerType);
    }

    public static LibraryEntry fromDocId(String accountName, String libraryId, Docid docId, int offerType) {
        return new LibraryEntry(accountName, libraryId, docId.backend, docId.backendDocid, docId.type, offerType);
    }

    public LibraryEntry(String accountName, String libraryId, int backendId, String docId, int docType, int offerType) {
        this(accountName, libraryId, backendId, docId, docType, offerType, Long.MIN_VALUE, Long.MAX_VALUE, false);
    }

    public LibraryEntry(String accountName, String libraryId, int backendId, String docId, int docType, int offerType, long documentHash, long validUntilTimestampMs, boolean preordered) {
        if (docId == null) {
            throw new NullPointerException();
        }
        this.mAccountName = accountName;
        this.mLibraryId = libraryId;
        this.mBackendId = backendId;
        this.mDocId = docId;
        this.mDocType = docType;
        this.mOfferType = offerType;
        this.mDocumentHash = documentHash;
        this.mValidUntilTimestampMs = validUntilTimestampMs;
        this.mPreordered = preordered;
    }

    public void updateInPlace(String libraryId, Docid docId, int offerType) {
        this.mLibraryId = libraryId;
        this.mBackendId = docId.backend;
        this.mDocId = docId.backendDocid;
        this.mDocType = docId.type;
        this.mOfferType = offerType;
    }

    public void updateInPlace(String docId) {
        this.mDocId = docId;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= this.mValidUntilTimestampMs;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LibraryEntry)) {
            return false;
        }
        LibraryEntry that = (LibraryEntry) o;
        if (this.mBackendId != that.mBackendId) {
            return false;
        }
        if (this.mDocType != that.mDocType) {
            return false;
        }
        if (this.mOfferType != that.mOfferType) {
            return false;
        }
        if (this.mAccountName != UNKNOWN_ACCOUNT && that.mAccountName != UNKNOWN_ACCOUNT && !this.mAccountName.equals(that.mAccountName)) {
            return false;
        }
        if (!this.mDocId.equals(that.mDocId)) {
            return false;
        }
        if (this.mLibraryId.equals(that.mLibraryId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.mLibraryId != null) {
            hashCode = this.mLibraryId.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + 0) * 31;
        if (this.mDocId != null) {
            i = this.mDocId.hashCode();
        }
        return ((((hashCode + i) * 31) + this.mDocType) * 31) + this.mOfferType;
    }

    public String getAccountName() {
        return this.mAccountName;
    }

    public String getLibraryId() {
        return this.mLibraryId;
    }

    public int getBackendId() {
        return this.mBackendId;
    }

    public String getDocId() {
        return this.mDocId;
    }

    public int getDocType() {
        return this.mDocType;
    }

    public int getOfferType() {
        return this.mOfferType;
    }

    public long getDocumentHash() {
        return this.mDocumentHash;
    }

    public long getValidUntilTimestampMs() {
        return this.mValidUntilTimestampMs;
    }

    public boolean isPreordered() {
        return this.mPreordered;
    }
}
