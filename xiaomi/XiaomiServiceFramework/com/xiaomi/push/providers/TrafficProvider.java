package com.xiaomi.push.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import com.xiaomi.smack.util.TrafficUtils;

public class TrafficProvider extends ContentProvider {
    public static final String AUTHORITY = "com.xiaomi.push.providers.TrafficProvider";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.xiaomi.push.traffic";
    public static final Uri CONTENT_URI;
    private static final int IMSI = 2;
    private static final int TRAFFICS = 1;
    public static final String TRAFFIC_TABLE_NAME = "traffic";
    public static final String UPDATE_IMSI = "update_imsi";
    private static final UriMatcher sUriMatcher;
    private SQLiteOpenHelper dbHelper;

    public interface TrafficColumns extends BaseColumns {
        public static final String BYTES = "bytes";
        public static final String IMSI = "imsi";
        public static final String MESSAGE_TS = "message_ts";
        public static final String NETWORK_TYPE = "network_type";
        public static final String PACKAGE_NAME = "package_name";
        public static final String RCV = "rcv";
    }

    static {
        CONTENT_URI = Uri.parse("content://com.xiaomi.push.providers.TrafficProvider/traffic");
        sUriMatcher = new UriMatcher(-1);
        sUriMatcher.addURI(AUTHORITY, TRAFFIC_TABLE_NAME, TRAFFICS);
        sUriMatcher.addURI(AUTHORITY, UPDATE_IMSI, IMSI);
    }

    public boolean onCreate() {
        this.dbHelper = new TrafficDatabaseHelper(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c;
        synchronized (TrafficDatabaseHelper.DataBaseLock) {
            switch (sUriMatcher.match(uri)) {
                case TRAFFICS /*1*/:
                    c = this.dbHelper.getReadableDatabase().query(TRAFFIC_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
        return c;
    }

    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case TRAFFICS /*1*/:
                return CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    public int bulkInsert(Uri uri, ContentValues[] aryValues) {
        return 0;
    }

    public int delete(Uri uri, String where, String[] whereArgs) {
        return 0;
    }

    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        switch (sUriMatcher.match(uri)) {
            case IMSI /*2*/:
                if (values != null && values.containsKey(TrafficColumns.IMSI)) {
                    TrafficUtils.updateIMSI(values.getAsString(TrafficColumns.IMSI));
                    break;
                }
        }
        return 0;
    }
}
