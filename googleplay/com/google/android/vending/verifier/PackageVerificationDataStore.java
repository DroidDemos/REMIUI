package com.google.android.vending.verifier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Utils;
import java.util.Map;

public class PackageVerificationDataStore {
    private static final String[] FULL_PROJECTION;
    private SQLiteDatabase mDb;
    private final Helper mHelper;

    private class Helper extends SQLiteOpenHelper {
        public Helper(Context context) {
            super(context, "package_verification.db", null, 1);
        }

        public void onCreate(SQLiteDatabase database) {
            database.execSQL("CREATE TABLE verification_cache (package_name STRING PRIMARY KEY, cache_fingerprint INTEGER, sha256_digest BLOB, length INTEGER, forward_locked INTEGER, suppress_user_warning INTEGER);");
        }

        private void recreateDatabase(SQLiteDatabase database) {
            try {
                database.execSQL("DROP TABLE verification_cache");
            } catch (SQLException e) {
            }
            onCreate(database);
        }

        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            recreateDatabase(database);
        }
    }

    static {
        FULL_PROJECTION = new String[]{"package_name", "cache_fingerprint", "sha256_digest", "length", "forward_locked", "suppress_user_warning"};
    }

    public PackageVerificationDataStore(Context context) {
        this.mHelper = new Helper(context);
    }

    void reopen() {
        Utils.ensureNotOnMainThread();
        this.mDb = this.mHelper.getWritableDatabase();
    }

    private void close() {
        Utils.ensureNotOnMainThread();
        this.mDb.close();
    }

    public synchronized PackageVerificationData get(String packageName) {
        PackageVerificationData packageVerificationData;
        reopen();
        Cursor cursor = this.mDb.query("verification_cache", FULL_PROJECTION, "package_name=?", new String[]{packageName}, null, null, null);
        try {
            if (cursor.getCount() != 1) {
                packageVerificationData = null;
            } else {
                cursor.moveToNext();
                packageVerificationData = packageVerificationDataFromCursor(cursor);
                cursor.close();
                close();
            }
        } finally {
            cursor.close();
            close();
        }
        return packageVerificationData;
    }

    public synchronized Map<String, PackageVerificationData> getAll() {
        Map<String, PackageVerificationData> result;
        reopen();
        Cursor cursor = this.mDb.query("verification_cache", FULL_PROJECTION, null, null, null, null, null);
        result = Maps.newHashMap();
        while (cursor.moveToNext()) {
            PackageVerificationData packageVerificationData = packageVerificationDataFromCursor(cursor);
            result.put(packageVerificationData.mPackageName, packageVerificationData);
        }
        cursor.close();
        close();
        return result;
    }

    public synchronized void put(PackageVerificationData packageVerificationData) {
        int i = 1;
        synchronized (this) {
            reopen();
            ContentValues values = new ContentValues();
            values.put("package_name", packageVerificationData.mPackageName);
            values.put("cache_fingerprint", Long.valueOf(packageVerificationData.mCacheFingerprint));
            values.put("sha256_digest", packageVerificationData.mSha256Digest);
            values.put("length", Long.valueOf(packageVerificationData.mApkLength));
            values.put("forward_locked", Integer.valueOf(packageVerificationData.mForwardLocked ? 1 : 0));
            String str = "suppress_user_warning";
            if (!packageVerificationData.mSuppressUserWarning) {
                i = 0;
            }
            values.put(str, Integer.valueOf(i));
            this.mDb.replace("verification_cache", null, values);
            close();
        }
    }

    public synchronized void remove(String packageName) {
        reopen();
        this.mDb.delete("verification_cache", "package_name=?", new String[]{packageName});
        close();
    }

    public synchronized void setSuppressUserWarning(String packageName, boolean suppressUserWarning) {
        int i = 1;
        synchronized (this) {
            reopen();
            ContentValues contentValues = new ContentValues();
            String str = "suppress_user_warning";
            if (!suppressUserWarning) {
                i = 0;
            }
            contentValues.put(str, Integer.valueOf(i));
            this.mDb.update("verification_cache", contentValues, "package_name=?", new String[]{packageName});
            close();
        }
    }

    private PackageVerificationData packageVerificationDataFromCursor(Cursor cursor) {
        String packageName = cursor.getString(0);
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        boolean forwardLocked;
        boolean suppressUserWarning;
        long fingerprint = cursor.getLong(1);
        byte[] sha256Digest = cursor.getBlob(2);
        long length = cursor.getLong(3);
        if (cursor.getInt(4) == 1) {
            forwardLocked = true;
        } else {
            forwardLocked = false;
        }
        if (cursor.getInt(5) == 1) {
            suppressUserWarning = true;
        } else {
            suppressUserWarning = false;
        }
        return new PackageVerificationData(packageName, fingerprint, sha256Digest, length, forwardLocked, suppressUserWarning);
    }

    public synchronized void reset() {
        reopen();
        this.mDb.delete("verification_cache", null, null);
        close();
    }
}
