package com.android.common.content;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;
import java.util.ArrayList;

public abstract class SQLiteContentProvider extends ContentProvider implements SQLiteTransactionListener {
    private static final int MAX_OPERATIONS_PER_YIELD_POINT = 500;
    private static final int SLEEP_AFTER_YIELD_DELAY = 4000;
    private static final String TAG = "SQLiteContentProvider";
    private final ThreadLocal<Boolean> mApplyingBatch;
    protected SQLiteDatabase mDb;
    private volatile boolean mNotifyChange;
    private SQLiteOpenHelper mOpenHelper;

    protected abstract int deleteInTransaction(Uri uri, String str, String[] strArr);

    protected abstract SQLiteOpenHelper getDatabaseHelper(Context context);

    protected abstract Uri insertInTransaction(Uri uri, ContentValues contentValues);

    protected abstract void notifyChange();

    protected abstract int updateInTransaction(Uri uri, ContentValues contentValues, String str, String[] strArr);

    public SQLiteContentProvider() {
        this.mApplyingBatch = new ThreadLocal();
    }

    public int getMaxOperationsPerYield() {
        return MAX_OPERATIONS_PER_YIELD_POINT;
    }

    public boolean onCreate() {
        this.mOpenHelper = getDatabaseHelper(getContext());
        return true;
    }

    public SQLiteOpenHelper getDatabaseHelper() {
        return this.mOpenHelper;
    }

    private boolean applyingBatch() {
        return this.mApplyingBatch.get() != null && ((Boolean) this.mApplyingBatch.get()).booleanValue();
    }

    public Uri insert(Uri uri, ContentValues values) {
        Uri result = null;
        if (applyingBatch()) {
            result = insertInTransaction(uri, values);
            if (result != null) {
                this.mNotifyChange = true;
            }
        } else {
            this.mDb = this.mOpenHelper.getWritableDatabase();
            this.mDb.beginTransactionWithListener(this);
            try {
                result = insertInTransaction(uri, values);
                if (result != null) {
                    this.mNotifyChange = true;
                }
                this.mDb.setTransactionSuccessful();
                onEndTransaction();
            } finally {
                this.mDb.endTransaction();
            }
        }
        return result;
    }

    public int bulkInsert(Uri uri, ContentValues[] values) {
        int numValues = values.length;
        this.mDb = this.mOpenHelper.getWritableDatabase();
        this.mDb.beginTransactionWithListener(this);
        int i = 0;
        while (i < numValues) {
            try {
                if (insertInTransaction(uri, values[i]) != null) {
                    this.mNotifyChange = true;
                }
                boolean savedNotifyChange = this.mNotifyChange;
                SQLiteDatabase savedDb = this.mDb;
                this.mDb.yieldIfContendedSafely();
                this.mDb = savedDb;
                this.mNotifyChange = savedNotifyChange;
                i++;
            } catch (Throwable th) {
                this.mDb.endTransaction();
            }
        }
        this.mDb.setTransactionSuccessful();
        this.mDb.endTransaction();
        onEndTransaction();
        return numValues;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        if (applyingBatch()) {
            count = updateInTransaction(uri, values, selection, selectionArgs);
            if (count > 0) {
                this.mNotifyChange = true;
            }
        } else {
            this.mDb = this.mOpenHelper.getWritableDatabase();
            this.mDb.beginTransactionWithListener(this);
            try {
                count = updateInTransaction(uri, values, selection, selectionArgs);
                if (count > 0) {
                    this.mNotifyChange = true;
                }
                this.mDb.setTransactionSuccessful();
                onEndTransaction();
            } finally {
                this.mDb.endTransaction();
            }
        }
        return count;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        if (applyingBatch()) {
            count = deleteInTransaction(uri, selection, selectionArgs);
            if (count > 0) {
                this.mNotifyChange = true;
            }
        } else {
            this.mDb = this.mOpenHelper.getWritableDatabase();
            this.mDb.beginTransactionWithListener(this);
            try {
                count = deleteInTransaction(uri, selection, selectionArgs);
                if (count > 0) {
                    this.mNotifyChange = true;
                }
                this.mDb.setTransactionSuccessful();
                onEndTransaction();
            } finally {
                this.mDb.endTransaction();
            }
        }
        return count;
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        int ypCount = 0;
        int opCount = 0;
        this.mDb = this.mOpenHelper.getWritableDatabase();
        this.mDb.beginTransactionWithListener(this);
        try {
            this.mApplyingBatch.set(Boolean.valueOf(true));
            int numOperations = operations.size();
            ContentProviderResult[] results = new ContentProviderResult[numOperations];
            for (int i = 0; i < numOperations; i++) {
                opCount++;
                if (opCount > getMaxOperationsPerYield()) {
                    throw new OperationApplicationException("Too many content provider operations between yield points. The maximum number of operations per yield point is 500", ypCount);
                }
                ContentProviderOperation operation = (ContentProviderOperation) operations.get(i);
                if (i > 0 && operation.isYieldAllowed()) {
                    opCount = 0;
                    boolean savedNotifyChange = this.mNotifyChange;
                    if (this.mDb.yieldIfContendedSafely(4000)) {
                        this.mDb = this.mOpenHelper.getWritableDatabase();
                        this.mNotifyChange = savedNotifyChange;
                        ypCount++;
                    }
                }
                results[i] = operation.apply(this, results, i);
            }
            this.mDb.setTransactionSuccessful();
            return results;
        } finally {
            this.mApplyingBatch.set(Boolean.valueOf(false));
            this.mDb.endTransaction();
            onEndTransaction();
        }
    }

    public void onBegin() {
        onBeginTransaction();
    }

    public void onCommit() {
        beforeTransactionCommit();
    }

    public void onRollback() {
    }

    protected void onBeginTransaction() {
    }

    protected void beforeTransactionCommit() {
    }

    protected void onEndTransaction() {
        if (this.mNotifyChange) {
            this.mNotifyChange = false;
            notifyChange();
        }
    }
}
