package com.android.providers.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import java.util.HashSet;
import java.util.Iterator;
import miui.telephony.MultiSimManager;

public class BatchModeHelper {
    private static final String TAG = "BatchModeHelper";
    private static ThreadLocal<BatchModeHelper> sInstances;
    private int mBatchLevel;
    private HashSet<Long> mBlockedThreadsToUpdateLastSimId;
    private HashSet<Long> mBlockedThreadsToUpdateMessageCount;
    private HashSet<Long> mBlockedThreadsToUpdateSnippet;
    private HashSet<Long> mBlockedThreadsToUpdateUnreadCount;
    private Context mContext;
    private SQLiteDatabase mDb;
    private HashSet<Uri> mDeletedUriToBroadcast;
    private HashSet<Long> mMmsToClearFileId;
    private HashSet<Long> mMmsToMarkDirty;
    private HashSet<Long> mMmsToRemakePreview;
    private boolean mSyncRequested;
    private HashSet<String> mSyncRequestedName;
    private HashSet<Long> mThreadsToUpdateErrorState;
    private HashSet<Long> mThreadsToUpdateHasAttachment;
    private HashSet<Long> mThreadsToUpdateLastSimId;
    private HashSet<Long> mThreadsToUpdateMessageCount;
    private HashSet<Long> mThreadsToUpdateSnippet;
    private HashSet<Long> mThreadsToUpdateStickTime;
    private HashSet<Long> mThreadsToUpdateUnreadCount;
    private boolean mUpdateBlockEntry;
    private HashSet<Uri> mUrisToNotify;

    public BatchModeHelper() {
        this.mBatchLevel = 0;
        this.mThreadsToUpdateMessageCount = new HashSet();
        this.mThreadsToUpdateUnreadCount = new HashSet();
        this.mThreadsToUpdateErrorState = new HashSet();
        this.mThreadsToUpdateSnippet = new HashSet();
        this.mThreadsToUpdateLastSimId = new HashSet();
        this.mThreadsToUpdateHasAttachment = new HashSet();
        this.mThreadsToUpdateStickTime = new HashSet();
        this.mUpdateBlockEntry = false;
        this.mMmsToMarkDirty = new HashSet();
        this.mMmsToClearFileId = new HashSet();
        this.mMmsToRemakePreview = new HashSet();
        this.mUrisToNotify = new HashSet();
        this.mDeletedUriToBroadcast = new HashSet();
        this.mBlockedThreadsToUpdateMessageCount = new HashSet();
        this.mBlockedThreadsToUpdateUnreadCount = new HashSet();
        this.mBlockedThreadsToUpdateSnippet = new HashSet();
        this.mBlockedThreadsToUpdateLastSimId = new HashSet();
        this.mSyncRequestedName = new HashSet();
        this.mSyncRequested = false;
    }

    static {
        sInstances = new ThreadLocal();
    }

    public static BatchModeHelper getInstance() {
        BatchModeHelper instance = (BatchModeHelper) sInstances.get();
        if (instance != null) {
            return instance;
        }
        instance = new BatchModeHelper();
        sInstances.set(instance);
        return instance;
    }

    public void beginBatchOps(Context context, SQLiteDatabase db) {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " entering batch level " + (this.mBatchLevel + 1));
        if (this.mBatchLevel == 0) {
            this.mContext = context;
            this.mDb = db;
            this.mThreadsToUpdateMessageCount.clear();
            this.mThreadsToUpdateUnreadCount.clear();
            this.mThreadsToUpdateErrorState.clear();
            this.mThreadsToUpdateSnippet.clear();
            this.mThreadsToUpdateLastSimId.clear();
            this.mThreadsToUpdateHasAttachment.clear();
            this.mThreadsToUpdateStickTime.clear();
            this.mMmsToMarkDirty.clear();
            this.mMmsToClearFileId.clear();
            this.mMmsToRemakePreview.clear();
            this.mUrisToNotify.clear();
            this.mDeletedUriToBroadcast.clear();
            this.mSyncRequested = false;
            this.mBlockedThreadsToUpdateMessageCount.clear();
            this.mBlockedThreadsToUpdateUnreadCount.clear();
            this.mBlockedThreadsToUpdateSnippet.clear();
            this.mBlockedThreadsToUpdateLastSimId.clear();
            this.mSyncRequestedName.clear();
            this.mDb.beginTransaction();
        }
        this.mBatchLevel++;
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " entered batch level " + this.mBatchLevel);
    }

    public void endBatchOps() {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " exiting batch level " + this.mBatchLevel);
        this.mBatchLevel--;
        if (this.mBatchLevel < 0) {
            throw new IllegalStateException("Negative batch level");
        }
        if (this.mBatchLevel == 0) {
            try {
                Iterator i$ = this.mThreadsToUpdateMessageCount.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateThreadMessageCount(this.mContext, this.mDb, ((Long) i$.next()).longValue());
                }
                i$ = this.mThreadsToUpdateUnreadCount.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateThreadUnreadCount(this.mDb, ((Long) i$.next()).longValue());
                }
                i$ = this.mThreadsToUpdateErrorState.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateThreadErrorState(this.mDb, ((Long) i$.next()).longValue());
                }
                if (this.mThreadsToUpdateSnippet.size() > 0) {
                    Long[] threadsToUpdateSnippet = new Long[this.mThreadsToUpdateSnippet.size()];
                    this.mThreadsToUpdateSnippet.toArray(threadsToUpdateSnippet);
                    MmsSmsDatabaseHelper.updateThreadSnippet(this.mContext, this.mDb, threadsToUpdateSnippet);
                }
                if (MultiSimManager.getInstance().getMultiSimCount() > 1) {
                    i$ = this.mThreadsToUpdateLastSimId.iterator();
                    while (i$.hasNext()) {
                        MmsSmsDatabaseHelper.updateThreadLastSimId(this.mDb, ((Long) i$.next()).longValue());
                    }
                }
                i$ = this.mThreadsToUpdateHasAttachment.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateThreadHasAttachment(this.mDb, ((Long) i$.next()).longValue());
                }
                i$ = this.mThreadsToUpdateStickTime.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateStickTime(this.mDb, ((Long) i$.next()).longValue());
                }
                i$ = this.mMmsToMarkDirty.iterator();
                while (i$.hasNext()) {
                    internalMarkMmsDirty(this.mDb, ((Long) i$.next()).longValue());
                }
                i$ = this.mMmsToClearFileId.iterator();
                while (i$.hasNext()) {
                    internalClearFileId(this.mDb, ((Long) i$.next()).longValue());
                }
                MmsSmsUtils.makeMmsPreview(this.mContext, this.mDb, true, this.mMmsToRemakePreview);
                ContentResolver cr = this.mContext.getContentResolver();
                i$ = this.mUrisToNotify.iterator();
                while (i$.hasNext()) {
                    cr.notifyChange((Uri) i$.next(), null, false);
                }
                i$ = this.mDeletedUriToBroadcast.iterator();
                while (i$.hasNext()) {
                    internalBroadcastDeletedContents(this.mContext, (Uri) i$.next());
                }
                if (this.mSyncRequested) {
                    i$ = this.mSyncRequestedName.iterator();
                    while (i$.hasNext()) {
                        MmsSmsUtils.requestSync(this.mContext, (String) i$.next());
                    }
                }
                i$ = this.mBlockedThreadsToUpdateMessageCount.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateBlockedThreadMessageCount(this.mDb, ((Long) i$.next()).longValue());
                }
                i$ = this.mBlockedThreadsToUpdateUnreadCount.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateBlockedThreadUnreadCount(this.mDb, ((Long) i$.next()).longValue());
                }
                i$ = this.mBlockedThreadsToUpdateSnippet.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateBlockedThreadSnippet(this.mDb, ((Long) i$.next()).longValue());
                }
                i$ = this.mBlockedThreadsToUpdateLastSimId.iterator();
                while (i$.hasNext()) {
                    MmsSmsDatabaseHelper.updateBlockedThreadLastSimId(this.mDb, ((Long) i$.next()).longValue());
                }
                if (this.mUpdateBlockEntry) {
                    MmsSmsDatabaseHelper.updateThreadBlockEntry(this.mDb);
                }
                this.mDb.setTransactionSuccessful();
            } finally {
                this.mDb.endTransaction();
            }
        }
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " exited batch level " + (this.mBatchLevel + 1));
    }

    public void updateThread(Context context, SQLiteDatabase db, long threadId) {
        updateThreadMessageCount(context, db, threadId);
        updateThreadUnreadCount(db, threadId);
        updateThreadErrorState(db, threadId);
        updateThreadSnippet(context, db, threadId);
        updateThreadLastSimId(db, threadId);
        updateThreadStickTime(db, threadId);
    }

    public void updateThreadMessageCount(Context context, SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mThreadsToUpdateMessageCount.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateThreadMessageCount(context, db, threadId);
        }
    }

    public void updateThreadUnreadCount(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mThreadsToUpdateUnreadCount.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateThreadUnreadCount(db, threadId);
        }
    }

    public void updateThreadErrorState(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mThreadsToUpdateErrorState.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateThreadErrorState(db, threadId);
        }
    }

    public void updateThreadSnippet(Context context, SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mThreadsToUpdateSnippet.add(Long.valueOf(threadId));
            return;
        }
        MmsSmsDatabaseHelper.updateThreadSnippet(context, db, Long.valueOf(threadId));
    }

    public void updateThreadLastSimId(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mThreadsToUpdateLastSimId.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateThreadLastSimId(db, threadId);
        }
    }

    public void updateThreadHasAttachment(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mThreadsToUpdateHasAttachment.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateThreadHasAttachment(db, threadId);
        }
    }

    public void updateThreadStickTime(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mThreadsToUpdateStickTime.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateStickTime(db, threadId);
        }
    }

    public void updateThreadHasAttachmentByMsg(SQLiteDatabase db, long msgId) {
        long threadId = MmsSmsUtils.msgIdToThreadId(db, msgId);
        if (threadId > 0) {
            updateThreadHasAttachment(db, threadId);
        }
    }

    public void updateThreadBlockEntry(SQLiteDatabase db) {
        if (this.mBatchLevel == 0) {
            MmsSmsDatabaseHelper.updateThreadBlockEntry(db);
        } else {
            this.mUpdateBlockEntry = true;
        }
    }

    public void updateBlockedThread(SQLiteDatabase db, long threadId, long addressId) {
        if (threadId != -1 && addressId != -1) {
            if (MmsSmsDatabaseHelper.deleteEmptyBlockedThread(db, threadId) > 0) {
                updateThreadBlockEntry(db);
                return;
            }
            MmsSmsDatabaseHelper.checkToCreateBlockedThread(db, threadId, addressId);
            updateBlockedThreadMessageCount(db, threadId);
            updateBlockedThreadUnreadCount(db, threadId);
            updateBlockedThreadSnippet(db, threadId);
            updateBlockedThreadLastSimId(db, threadId);
            updateThreadBlockEntry(db);
        }
    }

    public void updateBlockedThreadMessageCount(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mBlockedThreadsToUpdateMessageCount.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateBlockedThreadMessageCount(db, threadId);
        }
    }

    public void updateBlockedThreadUnreadCount(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mBlockedThreadsToUpdateUnreadCount.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateBlockedThreadUnreadCount(db, threadId);
        }
    }

    public void updateBlockedThreadSnippet(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mBlockedThreadsToUpdateSnippet.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateBlockedThreadSnippet(db, threadId);
        }
    }

    public void updateBlockedThreadLastSimId(SQLiteDatabase db, long threadId) {
        if (this.mBatchLevel > 0) {
            this.mBlockedThreadsToUpdateLastSimId.add(Long.valueOf(threadId));
        } else {
            MmsSmsDatabaseHelper.updateBlockedThreadLastSimId(db, threadId);
        }
    }

    public void markMmsDirty(SQLiteDatabase db, long mmsId) {
        if (this.mBatchLevel > 0) {
            this.mMmsToMarkDirty.add(Long.valueOf(mmsId));
        } else {
            internalMarkMmsDirty(db, mmsId);
        }
    }

    private void internalMarkMmsDirty(SQLiteDatabase db, long mmsId) {
        db.execSQL("UPDATE pdu SET sync_state=0 WHERE _id=" + mmsId);
    }

    public void clearFileId(SQLiteDatabase db, long mmsId) {
        if (this.mBatchLevel > 0) {
            this.mMmsToClearFileId.add(Long.valueOf(mmsId));
        } else {
            internalClearFileId(db, mmsId);
        }
    }

    public void remakeMmsPreview(Context context, SQLiteDatabase db, long mmsId) {
        if (this.mBatchLevel > 0) {
            this.mMmsToRemakePreview.add(Long.valueOf(mmsId));
        } else {
            MmsSmsUtils.makeMmsPreview(context, db, true, mmsId);
        }
    }

    private void internalClearFileId(SQLiteDatabase db, long mmsId) {
        db.execSQL("UPDATE pdu SET file_id=null, need_download=0  WHERE _id=" + mmsId);
    }

    public void broadcastDeletedContents(Context context, Uri uri) {
        if (this.mBatchLevel > 0) {
            this.mDeletedUriToBroadcast.add(uri);
        } else {
            internalBroadcastDeletedContents(context, uri);
        }
    }

    private void internalBroadcastDeletedContents(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.CONTENT_CHANGED");
        intent.putExtra("deleted_contents", uri);
        context.sendBroadcast(intent);
    }

    public void notifyChange(Context context, Uri uri) {
        if (this.mBatchLevel > 0) {
            this.mUrisToNotify.add(uri);
        } else {
            context.getContentResolver().notifyChange(uri, null, false);
        }
    }

    public void requestSync(Context context, String name) {
        if (this.mBatchLevel > 0) {
            this.mSyncRequested = true;
            if (!this.mSyncRequestedName.contains(name)) {
                this.mSyncRequestedName.add(name);
                return;
            }
            return;
        }
        MmsSmsUtils.requestSync(context, name);
    }
}
