package com.xiaomi.account.legacy.activate;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class ActivateInfoProvider extends ContentProvider {
    private static final String TAG = "ActivateInfoProvider";
    private ActivateDatabaseHelper mOpenHelper;

    public boolean onCreate() {
        this.mOpenHelper = ActivateDatabaseHelper.getInstance(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.v(TAG, "ActivateInfoProvider: query " + uri);
        return this.mOpenHelper.getReadableDatabase().query(ActivateDatabaseHelper.ACTIVATE_INFO_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
