package com.xiaomi.xmsf.push.service.log;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;

public class LogProvider extends ContentProvider {
    public static final String AUTHORITY = "com.xiaomi.xmsf";
    private static final int URI_LOG_LATEST = 1;
    private static final int URI_LOG_OLD = 2;
    private static final UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(-1);
        mUriMatcher.addURI(AUTHORITY, "log", URI_LOG_LATEST);
        mUriMatcher.addURI(AUTHORITY, "log/#", URI_LOG_OLD);
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        throw new IllegalStateException("unsupported action");
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues values) {
        throw new IllegalStateException("unsupported action");
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new IllegalStateException("unsupported action");
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new IllegalStateException("unsupported action");
    }

    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        String filename;
        switch (mUriMatcher.match(uri)) {
            case URI_LOG_LATEST /*1*/:
                filename = ConfigureLog4J.FILE_NAME;
                break;
            case URI_LOG_OLD /*2*/:
                long id = ContentUris.parseId(uri);
                if (id <= 0) {
                    filename = ConfigureLog4J.FILE_NAME;
                    break;
                }
                filename = "xmsf.log." + id;
                break;
            default:
                throw new IllegalStateException("unsupported action");
        }
        return ParcelFileDescriptor.open(getContext().getFileStreamPath(filename), 268435456);
    }
}
