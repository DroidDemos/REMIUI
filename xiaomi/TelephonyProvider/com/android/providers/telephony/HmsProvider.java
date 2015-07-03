package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import com.android.common.speech.LoggingEvents;
import com.android.providers.telephony.MmsSmsUtils.OperationPerfProfiler;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Iterator;
import miui.provider.ExtraTelephony.Hms;

public class HmsProvider extends ContentProvider {
    public static final String AUTHORITY = "hms";
    private static final int HMS_ADDRESS = 3;
    private static final int HMS_ALL = 0;
    private static final int HMS_ALL_ID = 1;
    private static final int HMS_SEEN = 4;
    private static final int HMS_THREAD_ID = 2;
    private static final boolean LOCAL_LOGV = false;
    private static final String SEPARATOR = " AND ";
    public static final String TABLE_HMS = "hms";
    private static final String TABLE_HMS_JOIN_THREADS = "hms, threads";
    private static final String TAG = "HmsProvider";
    private static final String VND_ANDROID_DIR_HMS = "vnd.android-dir/hms";
    private static HashSet<String> sUNSUPPORTPARAMETERS;
    private static final UriMatcher sURLMatcher;
    private MmsSmsDatabaseHelper mOpenHelper;

    static {
        sUNSUPPORTPARAMETERS = new HashSet();
        sURLMatcher = new UriMatcher(-1);
        sURLMatcher.addURI(TABLE_HMS, null, HMS_ALL);
        sURLMatcher.addURI(TABLE_HMS, "#", HMS_ALL_ID);
        sURLMatcher.addURI(TABLE_HMS, "threadId/*", HMS_THREAD_ID);
        sURLMatcher.addURI(TABLE_HMS, "address/*", HMS_ADDRESS);
        sURLMatcher.addURI(TABLE_HMS, "seen/#", HMS_SEEN);
        sUNSUPPORTPARAMETERS.add("locked=0");
        sUNSUPPORTPARAMETERS.add("locked=1");
        sUNSUPPORTPARAMETERS.add("sync_state=0");
    }

    public boolean onCreate() {
        this.mOpenHelper = MmsSmsDatabaseHelper.getInstance(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        OperationPerfProfiler operationPerfProfiler = new OperationPerfProfiler("query " + uri.toString() + " with selection " + selection);
        String finalSelection = selection;
        String str;
        switch (sURLMatcher.match(uri)) {
            case HMS_ALL /*0*/:
                break;
            case HMS_ALL_ID /*1*/:
                long id = Long.parseLong((String) uri.getPathSegments().get(HMS_ALL));
                if (id > 0) {
                    finalSelection = MmsSmsUtils.concatenateWhere(selection, "_id = " + id);
                    break;
                }
                break;
            case HMS_THREAD_ID /*2*/:
                long threadId = Long.parseLong((String) uri.getPathSegments().get(HMS_ALL_ID));
                if (threadId > 0) {
                    finalSelection = MmsSmsUtils.concatenateWhere("thread_id = " + threadId, selection);
                    break;
                }
                break;
            case HMS_ADDRESS /*3*/:
                str = selection;
                finalSelection = MmsSmsUtils.concatenateWhere(str, "address = " + DatabaseUtils.sqlEscapeString(URLDecoder.decode((String) uri.getPathSegments().get(HMS_ALL_ID))));
                break;
            case HMS_SEEN /*4*/:
                str = selection;
                finalSelection = MmsSmsUtils.concatenateWhere(str, "exists (SELECT 1 FROM threads WHERE threads._id=hms.thread_id AND threads.sp_type=" + ((String) uri.getPathSegments().get(HMS_ALL_ID)) + ")");
                break;
            default:
                throw new IllegalStateException("Unrecognized URI:" + uri);
        }
        Cursor cursor = null;
        SQLiteDatabase db = this.mOpenHelper.getReadableDatabase();
        db.beginTransaction();
        try {
            cursor = db.query(TABLE_HMS, projection, finalSelection, selectionArgs, null, null, null, null);
            if (cursor != null) {
                cursor.setNotificationUri(getContext().getContentResolver(), Hms.CONTENT_URI);
            }
            db.setTransactionSuccessful();
            operationPerfProfiler.prof();
            return cursor;
        } finally {
            db.endTransaction();
        }
    }

    public String getType(Uri uri) {
        return VND_ANDROID_DIR_HMS;
    }

    public Uri insert(Uri uri, ContentValues values) {
        OperationPerfProfiler profiler = new OperationPerfProfiler("insert " + uri.toString());
        String table = TABLE_HMS;
        Uri retUri = null;
        Integer read = values.getAsInteger(WapPush.READ);
        if (read != null && read.intValue() == HMS_ALL_ID) {
            values.put(WapPush.SEEN, read);
            values.put("advanced_seen", Integer.valueOf(HMS_ADDRESS));
        }
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            long rowID = db.insert(TABLE_HMS, null, values);
            if (rowID >= 0) {
                retUri = Uri.parse("content://hms/" + rowID);
            }
            long threadId = values.getAsLong(WapPush.THREAD_ID).longValue();
            if (threadId > 0) {
                BatchModeHelper.getInstance().updateThread(getContext(), db, threadId);
            }
            db.setTransactionSuccessful();
            notifyChange(getContext(), uri.buildUpon().appendQueryParameter("newMsg", "true").build());
            profiler.prof();
            return retUri;
        } finally {
            db.endTransaction();
        }
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        OperationPerfProfiler profiler = new OperationPerfProfiler("delete " + uri.toString() + " with selection " + selection);
        int counts = HMS_ALL;
        String finalSelection = selection;
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        switch (sURLMatcher.match(uri)) {
            case HMS_ALL /*0*/:
                counts = deleteMessages(getContext(), db, finalSelection, selectionArgs, uri);
                break;
            case HMS_ALL_ID /*1*/:
                try {
                    long messageId = Long.parseLong(uri.getLastPathSegment());
                    if (messageId > 0) {
                        counts = deleteMessages(getContext(), db, "_id = " + messageId, null, uri);
                        break;
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Bad argument id + " + uri.getLastPathSegment());
                }
                break;
            default:
                throw new IllegalStateException("Unrecognized URI:" + uri);
        }
        profiler.prof();
        return counts;
    }

    static int deleteMessages(Context context, SQLiteDatabase database, String finalSelection, String[] selectionArgs) {
        return deleteMessages(context, database, finalSelection, selectionArgs, null);
    }

    static int deleteMessages(Context context, SQLiteDatabase db, String selection, String[] selectionArgs, Uri uri) {
        String finalSelection = filterSelection(selection);
        HashSet<Long> threadIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_HMS, "DISTINCT thread_id", finalSelection, selectionArgs);
        int counts = HMS_ALL;
        db.beginTransaction();
        try {
            counts = db.delete(TABLE_HMS, finalSelection, selectionArgs);
            if (counts > 0) {
                Iterator i$ = threadIds.iterator();
                while (i$.hasNext()) {
                    BatchModeHelper.getInstance().updateThread(context, db, ((Long) i$.next()).longValue());
                }
            }
            notifyChange(context, uri);
            db.setTransactionSuccessful();
            return counts;
        } finally {
            db.endTransaction();
        }
    }

    private void filterUnsupportedKeys(ContentValues values) {
        values.remove("sync_state");
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        OperationPerfProfiler profiler = new OperationPerfProfiler("update " + uri.toString() + " with selection " + selection);
        String finalSelection = filterSelection(selection);
        filterUnsupportedKeys(values);
        String extraSelection = LoggingEvents.EXTRA_CALLING_APP_NAME;
        switch (sURLMatcher.match(uri)) {
            case HMS_ALL_ID /*1*/:
                extraSelection = "_id = " + ((String) uri.getPathSegments().get(HMS_ALL));
                break;
            case HMS_THREAD_ID /*2*/:
                extraSelection = "thread_id = " + ((String) uri.getPathSegments().get(HMS_ALL_ID));
                break;
            case HMS_SEEN /*4*/:
                String str = selection;
                extraSelection = MmsSmsUtils.concatenateWhere(str, "exists (SELECT 1 FROM threads WHERE threads._id=hms.thread_id AND threads.sp_type=" + ((String) uri.getPathSegments().get(HMS_ALL_ID)) + ")");
                break;
        }
        Integer read = values.getAsInteger(WapPush.READ);
        if (read != null && read.intValue() == HMS_ALL_ID) {
            values.put(WapPush.SEEN, read);
            values.put("advanced_seen", Integer.valueOf(HMS_ADDRESS));
        }
        finalSelection = MmsSmsUtils.concatenateWhere(selection, extraSelection);
        int counts = HMS_ALL;
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            HashSet<Long> threadIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_HMS, "DISTINCT thread_id", finalSelection, selectionArgs);
            counts = db.update(TABLE_HMS, values, finalSelection, selectionArgs);
            if (counts > 0) {
                Iterator i$ = threadIds.iterator();
                while (i$.hasNext()) {
                    BatchModeHelper.getInstance().updateThread(getContext(), db, ((Long) i$.next()).longValue());
                }
            }
            notifyChange(getContext(), uri);
            db.setTransactionSuccessful();
            profiler.prof();
            return counts;
        } finally {
            db.endTransaction();
        }
    }

    private static String filterSelection(String selection) {
        String finalSelection = selection;
        Iterator i$ = sUNSUPPORTPARAMETERS.iterator();
        while (i$.hasNext()) {
            finalSelection = removeUnSupportSelection(finalSelection, (String) i$.next());
        }
        return finalSelection;
    }

    private static String removeUnSupportSelection(String selection, String UnSpportSelection) {
        String finalSelection = selection;
        if (TextUtils.isEmpty(selection) || TextUtils.isEmpty(UnSpportSelection) || !selection.contains(UnSpportSelection)) {
            return finalSelection;
        }
        String[] selections = selection.split(SEPARATOR);
        if (selections == null || selections.length <= 0) {
            return finalSelection;
        }
        StringBuilder builder = new StringBuilder();
        String[] arr$ = selections;
        int len$ = arr$.length;
        for (int i$ = HMS_ALL; i$ < len$; i$ += HMS_ALL_ID) {
            String string = arr$[i$];
            if (!string.contains(UnSpportSelection.trim())) {
                if (builder.length() == 0) {
                    builder.append(string);
                } else {
                    builder.append(" AND (" + string + ")");
                }
            }
        }
        return builder.toString();
    }

    private static void notifyChange(Context context, Uri uri) {
        BatchModeHelper batchModeHelper = BatchModeHelper.getInstance();
        batchModeHelper.notifyChange(context, uri);
        batchModeHelper.notifyChange(context, MmsSms.CONTENT_CONVERSATIONS_URI);
        batchModeHelper.notifyChange(context, Hms.CONTENT_URI);
        batchModeHelper.notifyChange(context, MmsSms.CONTENT_URI);
    }
}
