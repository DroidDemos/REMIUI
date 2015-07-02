package com.google.android.finsky.library;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SQLiteLibrary implements Library {
    private static final String[] FULL_PROJECTION;
    private Context mContext;
    private SQLiteDatabase mDb;
    private SQLiteStatement mQueryContains;
    private SQLiteStatement mQueryRemove;
    private SQLiteStatement mQueryResetAccountLibraryId;
    private SQLiteStatement mQuerySize;

    private static class Helper extends SQLiteOpenHelper {
        public Helper(Context context) {
            super(context, "library.db", null, 8);
        }

        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE ownership (account STRING, library_id STRING, backend INTEGER, doc_id STRING, doc_type INTEGER, offer_type INTEGER, document_hash INTEGER, subs_valid_until_time INTEGER, app_certificate_hash STRING, app_refund_pre_delivery_endtime_ms INTEGER, app_refund_post_delivery_window_ms INTEGER, subs_auto_renewing INTEGER, subs_initiation_time INTEGER, subs_trial_until_time INTEGER, inapp_purchase_data STRING, inapp_signature STRING, preordered INTEGER, PRIMARY KEY (account, library_id, backend, doc_id, doc_type, offer_type))");
        }

        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            switch (oldVersion) {
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    sqLiteDatabase.execSQL("ALTER TABLE ownership ADD COLUMN inapp_purchase_data STRING");
                    sqLiteDatabase.execSQL("ALTER TABLE ownership ADD COLUMN inapp_signature STRING");
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    break;
                default:
                    recreateDatabase(sqLiteDatabase);
                    return;
            }
            sqLiteDatabase.execSQL("ALTER TABLE ownership ADD COLUMN preordered INTEGER");
        }

        public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            FinskyLog.d("Downgrading Library from %d to %d", Integer.valueOf(oldVersion), Integer.valueOf(newVersion));
            recreateDatabase(database);
        }

        private void recreateDatabase(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL("DROP TABLE ownership");
            } catch (SQLException e) {
            }
            onCreate(sqLiteDatabase);
        }
    }

    static {
        FULL_PROJECTION = new String[]{"account", "library_id", "backend", "doc_id", "doc_type", "offer_type", "document_hash", "subs_valid_until_time", "app_certificate_hash", "app_refund_pre_delivery_endtime_ms", "app_refund_post_delivery_window_ms", "subs_auto_renewing", "subs_initiation_time", "subs_trial_until_time", "inapp_purchase_data", "inapp_signature", "preordered"};
    }

    static ContentValues getFullContentValues(LibraryEntry entry) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("account", entry.getAccountName());
        contentValues.put("library_id", entry.getLibraryId());
        contentValues.put("backend", Integer.valueOf(entry.getBackendId()));
        contentValues.put("doc_id", entry.getDocId());
        contentValues.put("doc_type", Integer.valueOf(entry.getDocType()));
        contentValues.put("offer_type", Integer.valueOf(entry.getOfferType()));
        contentValues.put("document_hash", Long.valueOf(entry.getDocumentHash()));
        contentValues.put("preordered", Boolean.valueOf(entry.isPreordered()));
        if (entry.getValidUntilTimestampMs() != Long.MAX_VALUE) {
            contentValues.put("subs_valid_until_time", Long.valueOf(entry.getValidUntilTimestampMs()));
        } else {
            contentValues.putNull("subs_valid_until_time");
        }
        if (entry instanceof LibraryAppEntry) {
            LibraryAppEntry appEntry = (LibraryAppEntry) entry;
            contentValues.put("app_certificate_hash", Utils.commaPackStrings(appEntry.certificateHashes));
            contentValues.put("app_refund_pre_delivery_endtime_ms", Long.valueOf(appEntry.refundPreDeliveryEndtimeMs));
            contentValues.put("app_refund_post_delivery_window_ms", Long.valueOf(appEntry.refundPostDeliveryWindowMs));
        } else if (entry instanceof LibraryInAppSubscriptionEntry) {
            LibraryInAppSubscriptionEntry subscriptionEntry = (LibraryInAppSubscriptionEntry) entry;
            contentValues.put("subs_auto_renewing", Boolean.valueOf(subscriptionEntry.isAutoRenewing));
            contentValues.put("subs_initiation_time", Long.valueOf(subscriptionEntry.initiationTimestampMs));
            contentValues.put("subs_trial_until_time", Long.valueOf(subscriptionEntry.trialUntilTimestampMs));
            contentValues.put("inapp_purchase_data", subscriptionEntry.signedPurchaseData);
            contentValues.put("inapp_signature", subscriptionEntry.signature);
        } else if (entry instanceof LibrarySubscriptionEntry) {
            LibrarySubscriptionEntry subscriptionEntry2 = (LibrarySubscriptionEntry) entry;
            contentValues.put("subs_auto_renewing", Boolean.valueOf(subscriptionEntry2.isAutoRenewing));
            contentValues.put("subs_initiation_time", Long.valueOf(subscriptionEntry2.initiationTimestampMs));
            contentValues.put("subs_trial_until_time", Long.valueOf(subscriptionEntry2.trialUntilTimestampMs));
        } else if (entry instanceof LibraryInAppEntry) {
            LibraryInAppEntry inappEntry = (LibraryInAppEntry) entry;
            contentValues.put("inapp_purchase_data", inappEntry.signedPurchaseData);
            contentValues.put("inapp_signature", inappEntry.signature);
        }
        return contentValues;
    }

    public SQLiteLibrary(Context context) {
        this.mContext = context;
    }

    public static int getVersion() {
        return 8;
    }

    public void reopen() {
        this.mDb = new Helper(this.mContext).getWritableDatabase();
        this.mQueryContains = this.mDb.compileStatement("SELECT COUNT(*) FROM ownership WHERE account=? AND library_id=? AND backend=? AND doc_id=? AND doc_type=? AND offer_type=?");
        this.mQueryRemove = this.mDb.compileStatement("DELETE FROM ownership WHERE account=? AND library_id=? AND backend=? AND doc_id=? AND doc_type=? AND offer_type=?");
        this.mQueryResetAccountLibraryId = this.mDb.compileStatement("DELETE FROM ownership WHERE account=? AND library_id=?");
        this.mQuerySize = this.mDb.compileStatement("SELECT COUNT(*) FROM ownership");
    }

    public void close() {
        this.mQueryContains.close();
        this.mQueryRemove.close();
        this.mQueryResetAccountLibraryId.close();
        this.mQuerySize.close();
        this.mDb.close();
    }

    public synchronized boolean contains(LibraryEntry key) {
        bindPartialStatement(this.mQueryContains, key);
        return this.mQueryContains.simpleQueryForLong() == 1;
    }

    public synchronized void add(LibraryEntry entry) {
        this.mDb.replace("ownership", null, getFullContentValues(entry));
    }

    public synchronized LibraryEntry get(LibraryEntry key) {
        throw new UnsupportedOperationException("getEntry not supported.");
    }

    public synchronized void addAll(Collection<? extends LibraryEntry> entries) {
        this.mDb.beginTransaction();
        try {
            for (LibraryEntry entry : entries) {
                add(entry);
            }
            this.mDb.setTransactionSuccessful();
            this.mDb.endTransaction();
        } catch (Throwable th) {
            this.mDb.endTransaction();
        }
    }

    public synchronized void remove(LibraryEntry key) {
        bindPartialStatement(this.mQueryRemove, key);
        this.mQueryRemove.execute();
    }

    public int size() {
        return (int) this.mQuerySize.simpleQueryForLong();
    }

    public synchronized void reset() {
        this.mDb.delete("ownership", null, null);
    }

    public synchronized void resetAccountLibrary(Account account, String libraryId) {
        this.mQueryResetAccountLibraryId.bindString(1, account.name);
        this.mQueryResetAccountLibraryId.bindString(2, libraryId);
        this.mQueryResetAccountLibraryId.execute();
    }

    public synchronized Iterator<LibraryEntry> iterator() {
        final Cursor all;
        all = this.mDb.query("ownership", FULL_PROJECTION, null, null, null, null, null);
        return new Iterator<LibraryEntry>() {
            public boolean hasNext() {
                if (all.isAfterLast() || all.isLast()) {
                    all.close();
                    return false;
                } else if (all.isClosed()) {
                    return false;
                } else {
                    return true;
                }
            }

            public LibraryEntry next() {
                if (!all.moveToNext()) {
                    return null;
                }
                long validUntilTimestampMs;
                String accountName = all.getString(0).intern();
                String libraryId = all.getString(1);
                int backend = all.getInt(2);
                String docId = all.getString(3);
                int docType = all.getInt(4);
                int offerType = all.getInt(5);
                long documentHash = all.getLong(6);
                boolean preordered = all.getInt(16) > 0;
                if (all.isNull(7)) {
                    validUntilTimestampMs = Long.MAX_VALUE;
                } else {
                    validUntilTimestampMs = all.getLong(7);
                }
                if (!"u-wl".equals(libraryId)) {
                    if (docType == 1) {
                        return new LibraryAppEntry(accountName, docId, offerType, documentHash, Utils.commaUnpackStrings(all.getString(8)), all.getLong(9), all.getLong(10));
                    }
                    String purchaseInfo;
                    String signature;
                    if (docType == 15) {
                        if (backend == 3) {
                            boolean autoRenewing = all.getInt(11) > 0;
                            long initiationTimestamp = all.getLong(12);
                            long trialUntilTimestamp = all.getLong(13);
                            purchaseInfo = "";
                            if (!all.isNull(14)) {
                                purchaseInfo = all.getString(14);
                            }
                            signature = "";
                            if (!all.isNull(15)) {
                                signature = all.getString(15);
                            }
                            return new LibraryInAppSubscriptionEntry(accountName, libraryId, backend, docId, offerType, documentHash, validUntilTimestampMs, initiationTimestamp, trialUntilTimestamp, autoRenewing, purchaseInfo, signature);
                        }
                        return new LibrarySubscriptionEntry(accountName, libraryId, backend, docId, offerType, documentHash, Long.valueOf(validUntilTimestampMs), all.getLong(12), all.getLong(13), all.getInt(11) > 0);
                    } else if (docType == 11) {
                        purchaseInfo = all.getString(14);
                        signature = all.getString(15);
                        if (!(purchaseInfo == null || signature == null)) {
                            return new LibraryInAppEntry(accountName, libraryId, docId, offerType, purchaseInfo, signature, documentHash);
                        }
                    }
                }
                return new LibraryEntry(accountName, libraryId, backend, docId, docType, offerType, documentHash, validUntilTimestampMs, preordered);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            protected void finalize() throws Throwable {
                if (!all.isClosed()) {
                    all.close();
                }
                super.finalize();
            }
        };
    }

    public void resetKeepOnlyAccounts(List<Account> accounts) {
        StringBuilder builder = new StringBuilder();
        String[] accountNames = new String[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append('?');
            accountNames[i] = ((Account) accounts.get(i)).name;
        }
        if (this.mDb.delete("ownership", "account NOT IN (" + builder.toString() + ")", accountNames) > 0) {
            FinskyLog.d("Removed %d obsolete library rows.", Integer.valueOf(this.mDb.delete("ownership", "account NOT IN (" + builder.toString() + ")", accountNames)));
        }
    }

    private static void bindPartialStatement(SQLiteStatement statement, LibraryEntry entry) {
        statement.bindString(1, entry.getAccountName());
        statement.bindString(2, entry.getLibraryId());
        statement.bindLong(3, (long) entry.getBackendId());
        statement.bindString(4, entry.getDocId());
        statement.bindLong(5, (long) entry.getDocType());
        statement.bindLong(6, (long) entry.getOfferType());
    }
}
