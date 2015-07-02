package com.miui.yellowpage.providers.telocation;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.base.provider.Telocation;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public class TelocationProvider extends ContentProvider {
    private static final UriMatcher zU;
    private a zT;

    static {
        zU = new UriMatcher(-1);
        zU.addURI("telocation", "customlocations", 0);
    }

    public String getType(Uri uri) {
        switch (zU.match(uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return "vnd.android.cursor.dir/custom_telocations";
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }

    public boolean onCreate() {
        this.zT = new a(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        switch (zU.match(uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                Cursor query = this.zT.getReadableDatabase().query("location", strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), uri);
                return query;
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        switch (zU.match(uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                long insert = this.zT.getWritableDatabase().insert("location", ConfigConstant.WIRELESS_FILENAME, contentValues);
                if (insert > 0) {
                    Uri withAppendedId = ContentUris.withAppendedId(Telocation.CONTENT_CUSTOM_LOCATION_URI, insert);
                    getContext().getContentResolver().notifyChange(withAppendedId, null);
                    return withAppendedId;
                }
                throw new SQLException("Failed to insert row into " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        switch (zU.match(uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                int delete = this.zT.getWritableDatabase().delete("location", str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return delete;
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        switch (zU.match(uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                int update = this.zT.getWritableDatabase().update("location", contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }
}
