package com.android.providers.telephony;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.android.common.speech.LoggingEvents;
import com.android.providers.telephony.FirewallDatabaseHelper.TABLE;
import com.android.providers.telephony.MmsSmsUtils.OperationPerfProfiler;
import com.google.android.mms.pdu.EncodedStringValue;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.SmsCb.Conversations;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.ThreadSettings;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import miui.os.Build;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;
import miui.provider.yellowpage.YellowPageUtils.YellowPagePhone;
import miui.telephony.MultiSimManager;
import miui.yellowpage.MiPubUtils;

public class MmsSmsDatabaseHelper extends SQLiteOpenHelper {
    static final long BLOCK_ENTRY_THREAD_ID = -100;
    static final String DATABASE_NAME = "mmssms.db";
    static final int DATABASE_VERSION = 95;
    private static final String PDU_CLEANUP_DELIVERY_READ_REPORT_TRIGGER = "CREATE TRIGGER cleanup_delivery_and_read_report AFTER DELETE ON pdu WHEN old.m_type=128 BEGIN   DELETE FROM pdu  WHERE (m_type=134    OR m_type=136)    AND m_id=old.m_id; END;";
    private static final String PDU_CLEANUP_PART_AND_ADDR_TRIGGER = "CREATE TRIGGER cleanup_part_and_addr DELETE ON pdu BEGIN   DELETE FROM part  WHERE mid=old._id;  DELETE FROM addr  WHERE msg_id=old._id;END;";
    private static final String PDU_DELETE_PENDING_ON_DELETE_TRIGGER = "CREATE TRIGGER delete_mms_pending_on_delete  AFTER DELETE ON pdu  BEGIN   DELETE FROM pending_msgs  WHERE msg_id=old._id; END;";
    private static final String PDU_DELETE_PENDING_ON_MARK_TRIGGER = "CREATE TRIGGER delete_mms_pending_on_mark  AFTER UPDATE of deleted ON pdu WHEN old.deleted = 0 AND new.deleted != 0 BEGIN   DELETE FROM pending_msgs  WHERE msg_id=old._id;  END;";
    private static final String PDU_MARK_DELIVERY_READ_REPORT_TRIGGER = "CREATE TRIGGER mark_delivery_and_read_report  AFTER UPDATE of deleted ON pdu WHEN old.m_type=128  BEGIN   UPDATE pdu SET deleted = new.deleted   WHERE (m_type=134    OR m_type=136)    AND m_id=old.m_id;  END;";
    private static final String PDU_ON_FILE_ID_CHANGE_TRIGGER = "CREATE TRIGGER on_file_id_change_trigger  AFTER UPDATE OF file_id ON pdu  WHEN new.file_id != null AND new.file_id != old.file_id  BEGIN   UPDATE pdu  SET need_download = (new.file_id != null AND new.file_id != '');   DELETE FROM part WHERE mid=old._id; END;";
    private static final String TAG = "MmsSmsDatabaseHelper";
    private static boolean sFakeLowStorageTest;
    private static MmsSmsDatabaseHelper sInstance;
    private static boolean sTriedAutoIncrement;
    private final Context mContext;
    private LowStorageMonitor mLowStorageMonitor;

    private class LowStorageMonitor extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(MmsSmsDatabaseHelper.TAG, "[LowStorageMonitor] onReceive intent " + action);
            if ("android.intent.action.DEVICE_STORAGE_OK".equals(action)) {
                MmsSmsDatabaseHelper.sTriedAutoIncrement = false;
            }
        }
    }

    static {
        sInstance = null;
        sTriedAutoIncrement = false;
        sFakeLowStorageTest = false;
    }

    private MmsSmsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        deleteOrphanedEntries();
    }

    static synchronized MmsSmsDatabaseHelper getInstance(Context context) {
        MmsSmsDatabaseHelper mmsSmsDatabaseHelper;
        synchronized (MmsSmsDatabaseHelper.class) {
            if (sInstance == null) {
                sInstance = new MmsSmsDatabaseHelper(context);
            }
            mmsSmsDatabaseHelper = sInstance;
        }
        return mmsSmsDatabaseHelper;
    }

    public static void updateThread(Context context, SQLiteDatabase db, long threadId) {
        if (threadId < 0) {
            updateAllThreads(context, db, null, null);
            return;
        }
        updateThreadMessageCount(context, db, threadId);
        updateThreadUnreadCount(db, threadId);
        updateThreadErrorState(db, threadId);
        updateThreadSnippet(context, db, Long.valueOf(threadId));
        updateThreadHasAttachment(db, threadId);
        updateThreadLastSimId(db, threadId);
        updateStickTime(db, threadId);
    }

    public static void updateThreadBlockEntry(SQLiteDatabase db) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateThreadBlockEntry");
        Cursor c = null;
        Cursor countCursor;
        try {
            c = db.rawQuery(" SELECT date, snippet, snippet_cs, recipient_ids FROM blocked_threads ORDER BY date DESC LIMIT 1;", null);
            ContentValues v = new ContentValues();
            v.put("_id", Long.valueOf(BLOCK_ENTRY_THREAD_ID));
            if (c == null || !c.moveToFirst()) {
                v.put(WapPush.DATE, Integer.valueOf(0));
                v.put(Conversations.SNIPPET, LoggingEvents.EXTRA_CALLING_APP_NAME);
                v.put("snippet_cs", Integer.valueOf(0));
                v.put("message_count", Integer.valueOf(0));
                v.put("recipient_ids", LoggingEvents.EXTRA_CALLING_APP_NAME);
            } else {
                v.put(WapPush.DATE, Long.valueOf(c.getLong(0)));
                v.put(Conversations.SNIPPET, c.getString(1));
                v.put("snippet_cs", Integer.valueOf(c.getInt(2)));
                v.put("recipient_ids", c.getString(3));
                countCursor = null;
                countCursor = db.rawQuery(" SELECT SUM(message_count), SUM(unread_count) FROM blocked_threads", null);
                if (countCursor != null && countCursor.moveToFirst()) {
                    v.put("message_count", Integer.valueOf(countCursor.getInt(0)));
                    v.put("unread_count", Integer.valueOf(countCursor.getInt(1)));
                }
                if (countCursor != null) {
                    countCursor.close();
                }
            }
            db.replace(MmsSmsProvider.TABLE_THREADS, null, v);
            if (c != null) {
                c.close();
            }
            prof.prof();
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
        }
    }

    public static void updateThreadMessageCount(Context context, SQLiteDatabase db, long threadId) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateThreadMessageCount.1");
        int rows = deleteObsoleteThread(context, db, threadId);
        prof.prof();
        if (rows <= 0) {
            prof = new OperationPerfProfiler("updateThreadMessageCount.2");
            db.execSQL("  UPDATE threads SET message_count =      (SELECT COUNT(_id) FROM sms      WHERE sms.type != 3 AND sms.thread_id = " + threadId + "        AND sms.deleted = 0) + " + "     (SELECT COUNT(_id) FROM pdu" + "      WHERE pdu.thread_id = " + threadId + "        AND m_type IN (132, 130, 128)" + "        AND pdu.msg_box != 3 AND pdu.deleted = 0) + " + "     (SELECT COUNT(_id) FROM hms " + "       WHERE hms.thread_id = " + threadId + ")" + "  WHERE threads._id = " + threadId + ";");
            db.execSQL("  UPDATE threads SET has_draft =      (SELECT COUNT(_id) FROM sms      WHERE sms.type=3 AND sms.thread_id = " + threadId + "        AND sms.deleted = 0) + " + "     (SELECT COUNT(_id) FROM pdu" + "      WHERE pdu.thread_id = " + threadId + "        AND pdu.msg_box = 3 AND pdu.deleted = 0) " + "  WHERE threads._id = " + threadId + ";");
            prof.prof();
        }
    }

    public static void updateThreadUnreadCount(SQLiteDatabase db, long threadId) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateThreadUnreadCount.1");
        db.execSQL("  UPDATE threads SET unread_count =      (SELECT COUNT(_id) FROM sms      WHERE sms.type=1 AND sms.thread_id = " + threadId + "        AND sms.read=0 AND sms.deleted=0) +" + "     (SELECT COUNT(_id) FROM pdu" + "      WHERE pdu.thread_id = " + threadId + "        AND m_type IN (132, 130, 128)" + "        AND pdu.msg_box=1 AND pdu.read=0 AND pdu.deleted = 0) + " + "     (SELECT COUNT(_id) FROM hms" + "       WHERE hms.thread_id = " + threadId + "          AND hms.read=0)" + "  WHERE threads._id = " + threadId + ";");
        prof.prof();
        prof = new OperationPerfProfiler("updateThreadUnreadCount.2");
        db.execSQL("UPDATE threads SET read = CASE unread_count WHEN 0 THEN 1 ELSE 0 END  WHERE threads._id=" + threadId);
        prof.prof();
    }

    public static void updateThreadErrorState(SQLiteDatabase db, long threadId) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateThreadErrorState");
        Cursor c = db.rawQuery("SELECT date*1000+date_ms_part AS date FROM pdu WHERE thread_id=" + threadId + " AND (" + "m_type" + "=" + 128 + " OR " + "m_type" + "=" + 132 + " OR " + "m_type" + "=" + 130 + ") AND deleted=0 " + " UNION SELECT date FROM sms WHERE thread_id=" + threadId + " AND deleted=0 ORDER BY date DESC LIMIT 10", null);
        if (c == null) {
            Log.e(TAG, "Cannot query recent 10 messages for thread " + threadId);
        }
        long date = 0;
        try {
            if (c.moveToLast()) {
                date = c.getLong(0);
            }
            c.close();
            db.execSQL("UPDATE threads SET error = CASE    (SELECT COUNT(msg_id) FROM pending_msgs JOIN pdu ON       pdu.thread_id=" + threadId + " AND err_type >= 10 AND" + "       pending_msgs.msg_id=pdu._id AND deleted=0" + "       AND date*1000+date_ms_part>=" + date + ") +" + "   (SELECT COUNT(_id) FROM pdu WHERE" + "       thread_id=" + threadId + "       AND msg_box=" + 2 + "       AND mx_status=" + 131073 + " AND deleted=0" + "       AND date*1000+date_ms_part>=" + date + ") +" + "   (SELECT COUNT(_id) FROM sms WHERE" + "       thread_id=" + threadId + "       AND type=" + 5 + "       AND deleted=0 AND date>=" + date + ")" + "   WHEN 0 THEN 0 ELSE 1 END" + "   WHERE threads._id=" + threadId);
            prof.prof();
        } catch (Throwable th) {
            c.close();
        }
    }

    public static void updateStickTime(SQLiteDatabase db, long threadId) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateStickTime");
        Cursor c = db.rawQuery("SELECT message_count FROM threads    WHERE threads._id=" + threadId + "    AND stick_time > 0 ", null);
        if (c != null) {
            long message_count = 0;
            try {
                if (c.moveToFirst()) {
                    message_count = c.getLong(0);
                }
                c.close();
                if (message_count == 0) {
                    db.execSQL("UPDATE threads SET stick_time = 0    WHERE threads._id=" + threadId);
                }
                prof.prof();
            } catch (Throwable th) {
                c.close();
            }
        }
    }

    public static void updateThreadSnippet(Context context, SQLiteDatabase db, Long... threadIds) {
        if (threadIds != null && threadIds.length != 0) {
            String threadIdStr = TextUtils.join(",", threadIds);
            OperationPerfProfiler prof = new OperationPerfProfiler("updateThreadSnippet " + threadIdStr);
            Cursor c = null;
            try {
                ContentValues v;
                String mmsTypeClause = "(m_type=128 OR m_type=132 OR m_type=130)";
                c = db.rawQuery("   SELECT date, snippet, snippet_cs, address, mx_type, thread_id FROM (      SELECT thread_id, date * 1000 + date_ms_part AS date, sub AS snippet, sub_cs AS snippet_cs, NULL AS address, mx_type FROM pdu WHERE          thread_id IN (" + threadIdStr + ") AND " + "(m_type=128 OR m_type=132 OR m_type=130)" + " AND deleted = 0 " + "      UNION SELECT thread_id, date, body AS snippet, 0 AS snippet_cs, address, NULL AS mx_type FROM sms WHERE deleted = 0 AND thread_id IN (" + threadIdStr + ") " + "      UNION SELECT thread_id, date, snippet, 0 AS snippet_cs, address , NULL AS mx_type FROM hms WHERE thread_id IN (" + threadIdStr + ") ORDER BY date ) " + "  GROUP BY thread_id;", null);
                ArrayList<Long> arrayList = new ArrayList(Arrays.asList(threadIds));
                if (c != null && c.getCount() > 0) {
                    c.moveToPosition(-1);
                    while (c.moveToNext()) {
                        long threadId = c.getLong(5);
                        v = new ContentValues(3);
                        v.put(WapPush.DATE, Long.valueOf(c.getLong(0)));
                        String snippet = c.getString(1);
                        int charset = c.getInt(2);
                        int mxType = c.getInt(4);
                        if (mxType <= 0 || !TextUtils.isEmpty(snippet)) {
                            v.put(Conversations.SNIPPET, snippet);
                            v.put("snippet_cs", Integer.valueOf(charset));
                        } else {
                            v.put(Conversations.SNIPPET, getDescByMx2Type(context, mxType));
                            v.put("snippet_cs", Integer.valueOf(0));
                        }
                        String address = c.getString(3);
                        if (MiPubUtils.isServiceNumber(context, address)) {
                            v.put("sp_type", Integer.valueOf(MmsSmsUtils.getServiceCategory(context, address, c.getString(1))));
                        }
                        db.update(MmsSmsProvider.TABLE_THREADS, v, "_id=" + threadId, null);
                        arrayList.remove(Long.valueOf(threadId));
                    }
                }
                if (arrayList.size() > 0) {
                    Long[] threadIdEmpty = new Long[arrayList.size()];
                    arrayList.toArray(threadIdEmpty);
                    String threadIdEmptyStr = TextUtils.join(",", threadIdEmpty);
                    v = new ContentValues(3);
                    v.put(WapPush.DATE, Integer.valueOf(0));
                    v.put(Conversations.SNIPPET, (String) null);
                    v.put("snippet_cs", (String) null);
                    db.update(MmsSmsProvider.TABLE_THREADS, v, "_id IN (" + threadIdEmptyStr + ")", null);
                }
                if (c != null) {
                    c.close();
                }
                prof.prof();
            } catch (Throwable th) {
                if (c != null) {
                    c.close();
                }
            }
        }
    }

    public static void updateThreadLastSimId(SQLiteDatabase db, long threadId) {
        if (MultiSimManager.getInstance().getMultiSimCount() != 1) {
            OperationPerfProfiler prof = new OperationPerfProfiler("updateThreadLastSimId");
            Cursor c = null;
            try {
                String mmsTypeClause = "(m_type=128 OR m_type=132 OR m_type=130)";
                c = db.rawQuery("  SELECT sim_id FROM (      SELECT date * 1000 + date_ms_part AS date, sim_id FROM pdu WHERE deleted = 0 AND msg_box != 3 AND thread_id=" + threadId + " AND " + "(m_type=128 OR m_type=132 OR m_type=130)" + "      UNION SELECT date, sim_id FROM sms WHERE type != 3 AND thread_id=" + threadId + " AND deleted = 0)" + "  ORDER BY date DESC LIMIT 1;", null);
                ContentValues v;
                if (c == null || !c.moveToFirst()) {
                    v = new ContentValues(1);
                    v.put("last_sim_id", Integer.valueOf(0));
                    db.update(MmsSmsProvider.TABLE_THREADS, v, "_id=" + threadId, null);
                } else {
                    v = new ContentValues(1);
                    v.put("last_sim_id", Long.valueOf(c.getLong(0)));
                    db.update(MmsSmsProvider.TABLE_THREADS, v, "_id=" + threadId, null);
                }
                if (c != null) {
                    c.close();
                }
                prof.prof();
            } catch (Throwable th) {
                if (c != null) {
                    c.close();
                }
            }
        }
    }

    public static void updateThreadHasAttachment(SQLiteDatabase db, long thread_id) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateThreadHasAttachment");
        db.execSQL("  UPDATE threads SET has_attachment =    CASE     (SELECT COUNT(*) FROM part JOIN pdu      WHERE pdu.thread_id = " + thread_id + "     AND part.ct != 'text/plain' AND part.ct != 'application/smil' " + "     AND part.mid = pdu._id " + "     AND pdu.deleted = 0)" + "   WHEN 0 THEN 0 " + "   ELSE 1 " + "   END " + "  WHERE threads._id =" + thread_id);
        prof.prof();
    }

    public static void updateAllThreads(Context context, SQLiteDatabase db, String where, String[] whereArgs) {
        Cursor c;
        if (where == null) {
            where = LoggingEvents.EXTRA_CALLING_APP_NAME;
        } else {
            where = "WHERE (" + where + ")";
        }
        String query = "SELECT _id FROM threads WHERE _id IN (SELECT DISTINCT thread_id FROM sms " + where + ")";
        db.beginTransaction();
        try {
            c = db.rawQuery(query, whereArgs);
            if (c != null) {
                while (c.moveToNext()) {
                    updateThread(context, db, (long) c.getInt(0));
                }
                c.close();
            }
            deleteObsoleteThread(context, db, -1);
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Throwable th) {
            db.endTransaction();
        }
    }

    public static int deleteObsoleteThread(Context context, SQLiteDatabase db, long threadId) {
        String selection = "stick_time=0 AND _id >0 AND _id NOT IN (SELECT thread_id FROM sms WHERE (thread_id is not null) UNION SELECT thread_id FROM pdu WHERE (thread_id is not null) UNION SELECT thread_id FROM hms WHERE (thread_id is not null)) ";
        if (MmsSmsUtils.hasXiaomiAccount(context)) {
            selection = "sync_state>=2 AND " + selection;
        }
        if (threadId != -1) {
            selection = "_id = " + threadId + " AND " + selection;
        }
        db.beginTransaction();
        Cursor c;
        try {
            int count = db.delete(MmsSmsProvider.TABLE_THREADS, selection, null);
            Log.i(TAG, "Deleted " + count + " obsolete threads");
            if (threadId == -1) {
                c = db.rawQuery("SELECT DISTINCT recipient_ids FROM threads WHERE _id > 0", null);
                if (c != null) {
                    String where = null;
                    if (c.getCount() > 0) {
                        HashSet<String> rids = new HashSet();
                        c.moveToPosition(-1);
                        while (c.moveToNext()) {
                            String[] ids = c.getString(0).split(" ");
                            for (int i = 0; i < ids.length; i++) {
                                if (!ids[i].isEmpty()) {
                                    rids.add(ids[i]);
                                }
                            }
                        }
                        where = "_id NOT IN (" + TextUtils.join(",", rids) + ")";
                    }
                    c.close();
                    Log.i(TAG, "Deleted " + db.delete("canonical_addresses", where, null) + " obsolete canonical addresses");
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            return count;
        } catch (Throwable th) {
            db.endTransaction();
        }
    }

    public static void updateBlockedThread(SQLiteDatabase db, long threadId) {
        if (threadId >= 0 && deleteEmptyBlockedThread(db, threadId) <= 0) {
            updateBlockedThreadMessageCount(db, threadId);
            updateBlockedThreadUnreadCount(db, threadId);
            updateBlockedThreadSnippet(db, threadId);
            updateBlockedThreadLastSimId(db, threadId);
            updateThreadBlockEntry(db);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void checkToCreateBlockedThread(android.database.sqlite.SQLiteDatabase r4, long r5, long r7) {
        /*
        r2 = -1;
        r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "SELECT * FROM blocked_threads WHERE _id=";
        r2 = r2.append(r3);
        r2 = r2.append(r5);
        r2 = r2.toString();
        r3 = 0;
        r0 = r4.rawQuery(r2, r3);
        if (r0 == 0) goto L_0x002d;
    L_0x0021:
        r2 = r0.getCount();	 Catch:{ Exception -> 0x0054 }
        if (r2 <= 0) goto L_0x002d;
    L_0x0027:
        if (r0 == 0) goto L_0x0006;
    L_0x0029:
        r0.close();
        goto L_0x0006;
    L_0x002d:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0054 }
        r2.<init>();	 Catch:{ Exception -> 0x0054 }
        r3 = "INSERT INTO blocked_threads (_id, recipient_ids) VALUES (";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0054 }
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x0054 }
        r3 = ", '";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0054 }
        r2 = r2.append(r7);	 Catch:{ Exception -> 0x0054 }
        r3 = "')";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0054 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0054 }
        r4.execSQL(r2);	 Catch:{ Exception -> 0x0054 }
        goto L_0x0027;
    L_0x0054:
        r1 = move-exception;
        r2 = "MmsSmsDatabaseHelper";
        r3 = "Insert into blocked_threads err: ";
        android.util.Log.e(r2, r3, r1);	 Catch:{ all -> 0x0062 }
        if (r0 == 0) goto L_0x0006;
    L_0x005e:
        r0.close();
        goto L_0x0006;
    L_0x0062:
        r2 = move-exception;
        if (r0 == 0) goto L_0x0068;
    L_0x0065:
        r0.close();
    L_0x0068:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsSmsDatabaseHelper.checkToCreateBlockedThread(android.database.sqlite.SQLiteDatabase, long, long):void");
    }

    public static int deleteEmptyBlockedThread(SQLiteDatabase db, long threadId) {
        String selection = "_id NOT IN (SELECT thread_id FROM sms WHERE ((thread_id is not null) AND deleted=1 AND block_type>1)UNION SELECT thread_id FROM pdu WHERE ((thread_id is not null) AND deleted=1 AND block_type>1)) ";
        if (threadId != -1) {
            selection = "_id=" + threadId + " AND " + selection;
        }
        int count = 0;
        db.beginTransaction();
        try {
            count = db.delete(MmsSmsProvider.TABLE_BLOCKED_THREADS, selection, null);
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();
        }
    }

    public static void updateBlockedThreadMessageCount(SQLiteDatabase db, long threadId) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateBlockedThreadMessageCount.1");
        db.execSQL("  UPDATE blocked_threads SET message_count =      (SELECT COUNT(_id) FROM sms      WHERE sms.thread_id = " + threadId + "        AND sms.block_type>" + 1 + " AND sms.deleted=1) + " + "     (SELECT COUNT(_id) FROM pdu" + "      WHERE pdu.thread_id = " + threadId + "        AND pdu.block_type>" + 1 + " AND pdu.deleted=1) " + "  WHERE blocked_threads._id = " + threadId + ";");
        prof.prof();
    }

    public static void updateBlockedThreadUnreadCount(SQLiteDatabase db, long threadId) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateBlockedThreadUnreadCount.1");
        db.execSQL("  UPDATE blocked_threads SET unread_count =      (SELECT COUNT(_id) FROM sms      WHERE sms.thread_id = " + threadId + "        AND sms.block_type>" + 1 + " AND sms.read=0 AND sms.deleted=1) +" + "     (SELECT COUNT(_id) FROM pdu" + "      WHERE pdu.thread_id = " + threadId + "        AND (m_type=132 OR m_type=130 OR m_type=128)" + "        AND pdu.block_type>" + 1 + " AND pdu.read=0 AND pdu.deleted=1)" + "  WHERE blocked_threads._id = " + threadId + ";");
        prof.prof();
    }

    public static void updateBlockedThreadSnippet(SQLiteDatabase db, long threadId) {
        OperationPerfProfiler prof = new OperationPerfProfiler("updateBlockedThreadSnippet");
        Cursor c = null;
        try {
            String mmsTypeClause = "(m_type=128 OR m_type=132 OR m_type=130)";
            c = db.rawQuery("   SELECT date, snippet, snippet_cs FROM (       SELECT date*1000 + date_ms_part AS date, sub AS snippet, sub_cs AS snippet_cs FROM pdu           WHERE thread_id=" + threadId + " AND " + "(m_type=128 OR m_type=132 OR m_type=130)" + "                 AND block_type>" + 1 + " AND deleted=1 " + "       UNION SELECT date, body AS snippet, 0 AS snippet_cs FROM sms" + "           WHERE thread_id=" + threadId + " AND block_type>" + 1 + " AND deleted=1)" + "  ORDER BY date DESC LIMIT 1;", null);
            if (c == null || !c.moveToFirst()) {
                db.delete(MmsSmsProvider.TABLE_BLOCKED_THREADS, "_id=" + threadId, null);
            } else {
                ContentValues v = new ContentValues();
                v.put(WapPush.DATE, Long.valueOf(c.getLong(0)));
                v.put(Conversations.SNIPPET, c.getString(1));
                v.put("snippet_cs", Integer.valueOf(c.getInt(2)));
                db.update(MmsSmsProvider.TABLE_BLOCKED_THREADS, v, "_id=" + threadId, null);
            }
            if (c != null) {
                c.close();
            }
            prof.prof();
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
        }
    }

    public static void updateBlockedThreadLastSimId(SQLiteDatabase db, long threadId) {
        if (MultiSimManager.getInstance().getMultiSimCount() != 1) {
            OperationPerfProfiler prof = new OperationPerfProfiler("updateBlockedThreadLastSimId");
            Cursor c = null;
            try {
                String mmsTypeClause = "(m_type=132 OR m_type=130)";
                c = db.rawQuery("  SELECT sim_id FROM (      SELECT date * 1000 + date_ms_part AS date, sim_id FROM pdu WHERE deleted=1 AND block_type>1 AND thread_id=" + threadId + " AND " + "(m_type=132 OR m_type=130)" + "      UNION SELECT date, sim_id FROM sms WHERE deleted=1 AND block_type>" + 1 + " AND thread_id=" + threadId + ")" + "  ORDER BY date DESC LIMIT 1;", null);
                ContentValues v;
                if (c == null || !c.moveToFirst()) {
                    v = new ContentValues(1);
                    v.put("last_sim_id", Integer.valueOf(0));
                    db.update(MmsSmsProvider.TABLE_BLOCKED_THREADS, v, "_id=" + threadId, null);
                } else {
                    v = new ContentValues(1);
                    v.put("last_sim_id", Long.valueOf(c.getLong(0)));
                    db.update(MmsSmsProvider.TABLE_BLOCKED_THREADS, v, "_id=" + threadId, null);
                }
                if (c != null) {
                    c.close();
                }
                prof.prof();
            } catch (Throwable th) {
                if (c != null) {
                    c.close();
                }
            }
        }
    }

    public void onCreate(SQLiteDatabase db) {
        createMmsTables(db);
        createSmsTables(db);
        createCommonTables(db);
        createHmsTable(db);
        createCommonTriggers(db);
        createMmsTriggers(db);
        createIndices(db);
        System.putInt(this.mContext.getContentResolver(), "mms_sync_wild_msg_state", 0);
        System.putString(this.mContext.getContentResolver(), "mms_sync_wild_numbers", null);
        MmsSmsUtils.clearOldMsgState(this.mContext);
        MmsSmsUtils.clearWildMsgState(this.mContext);
        MmsSmsUtils.clearCommonMarkers(this.mContext);
    }

    private void createIndices(SQLiteDatabase db) {
        createThreadIdIndex(db);
        createMiMsgIdIndex(db);
        createHmsIndex(db);
        createDateIndex(db);
        createSourceIndex(db);
    }

    private void createThreadIdIndex(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE INDEX IF NOT EXISTS typeThreadIdIndex ON sms (type, thread_id);");
            db.execSQL("CREATE INDEX IF NOT EXISTS sms_threadId_index ON sms (thread_id, block_type);");
            db.execSQL("CREATE INDEX IF NOT EXISTS mms_threadId_index ON pdu (thread_id, m_type, block_type);");
            db.execSQL("CREATE INDEX IF NOT EXISTS hms_threadId_index ON hms (thread_id);");
        } catch (Exception ex) {
            Log.e(TAG, "got exception creating indices: ", ex);
        }
    }

    private void createDateIndex(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE INDEX IF NOT EXISTS sms_date_index ON sms (date);");
            db.execSQL("CREATE INDEX IF NOT EXISTS hms_date_index ON hms (date);");
            db.execSQL("CREATE INDEX IF NOT EXISTS mms_date_index ON pdu (date, date_ms_part);");
        } catch (Exception ex) {
            Log.e(TAG, "got exception creating indices: ", ex);
        }
    }

    private void createSourceIndex(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE INDEX IF NOT EXISTS smsSourceIndex ON sms (source);");
            db.execSQL("CREATE INDEX IF NOT EXISTS mmsSourceIndex ON pdu (source);");
        } catch (Exception ex) {
            Log.e(TAG, "got exception creating indices: ", ex);
        }
    }

    private void createMiMsgIdIndex(SQLiteDatabase db) {
        db.execSQL("CREATE INDEX IF NOT EXISTS mx_msgId_index_on_sms ON sms(mx_id)");
        db.execSQL("CREATE INDEX IF NOT EXISTS mx_msgId_index_on_pdu ON pdu(mx_id)");
    }

    private void createHmsIndex(SQLiteDatabase db) {
        db.execSQL("CREATE INDEX seq_address_index ON hms(address, mx_seq)");
    }

    private void createMmsTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pdu (_id INTEGER PRIMARY KEY,thread_id INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,msg_box INTEGER,read INTEGER DEFAULT 0,m_id TEXT,sub TEXT,sub_cs INTEGER,ct_t TEXT,ct_l TEXT,exp INTEGER,m_cls TEXT,m_type INTEGER,v INTEGER,m_size INTEGER,pri INTEGER,rr INTEGER,rpt_a INTEGER,resp_st INTEGER,st INTEGER,tr_id TEXT,retr_st INTEGER,retr_txt TEXT,retr_txt_cs INTEGER,read_status INTEGER,ct_cls INTEGER,resp_txt TEXT,d_tm INTEGER,d_rpt INTEGER,locked INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,timed INTEGER DEFAULT 0,date_ms_part INTEGER DEFAULT 0,deleted INTEGER DEFAULT 0,sync_state INTEGER DEFAULT 0,marker INTEGER DEFAULT 0,source TEXT,bind_id INTEGER DEFAULT 0,mx_status INTEGER DEFAULT 0,mx_id INTEGER,file_id TEXT,need_download INTEGER DEFAULT 0,account TEXT,out_time INTEGER DEFAULT 0,snippet TEXT,preview_type INTEGER DEFAULT 0,preview_data BLOB,preview_data_ts INTEGER DEFAULT 0,sim_id INTEGER DEFAULT 0,block_type INTEGER DEFAULT 0,advanced_seen INTEGER DEFAULT 0,mx_type INTEGER DEFAULT 0,mx_extension TEXT,text_only INTEGER DEFAULT 0 );");
        db.execSQL("CREATE TABLE addr (_id INTEGER PRIMARY KEY,msg_id INTEGER,contact_id INTEGER,address TEXT,type INTEGER,charset INTEGER);");
        db.execSQL("CREATE TABLE part (_id INTEGER PRIMARY KEY,mid INTEGER,seq INTEGER DEFAULT 0,ct TEXT,name TEXT,chset INTEGER,cd TEXT,fn TEXT,cid TEXT,cl TEXT,ctt_s INTEGER,ctt_t TEXT,_data TEXT,text TEXT);");
        db.execSQL("CREATE TABLE rate (sent_time INTEGER);");
        db.execSQL("CREATE TABLE drm (_id INTEGER PRIMARY KEY,_data TEXT);");
    }

    private void createMmsTriggers(SQLiteDatabase db) {
        db.execSQL(PDU_CLEANUP_PART_AND_ADDR_TRIGGER);
        db.execSQL(PDU_CLEANUP_DELIVERY_READ_REPORT_TRIGGER);
        db.execSQL(PDU_MARK_DELIVERY_READ_REPORT_TRIGGER);
        db.execSQL(PDU_ON_FILE_ID_CHANGE_TRIGGER);
    }

    private void createSmsTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sms (_id INTEGER PRIMARY KEY,thread_id INTEGER,address TEXT,person INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,protocol INTEGER,read INTEGER DEFAULT 0,status INTEGER DEFAULT -1,type INTEGER,reply_path_present INTEGER,subject TEXT,body TEXT,service_center TEXT,locked INTEGER DEFAULT 0,error_code INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,timed INTEGER DEFAULT 0,deleted INTEGER DEFAULT 0,sync_state INTEGER DEFAULT 0,marker INTEGER DEFAULT 0,source TEXT,bind_id INTEGER DEFAULT 0,mx_status INTEGER DEFAULT 0,mx_id INTEGER,out_time INTEGER DEFAULT 0,account TEXT,sim_id INTEGER DEFAULT 0,block_type INTEGER DEFAULT 0,advanced_seen INTEGER DEFAULT 0);");
        db.execSQL("CREATE TABLE raw (_id INTEGER PRIMARY KEY,date INTEGER,reference_number INTEGER,count INTEGER,sequence INTEGER,destination_port INTEGER,address TEXT,sim_id INTEGER DEFAULT 0,pdu TEXT);");
        db.execSQL("CREATE TABLE attachments (sms_id INTEGER,content_url TEXT,offset INTEGER);");
        db.execSQL("CREATE TABLE sr_pending (reference_number INTEGER,action TEXT,data TEXT);");
    }

    private void createCommonTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE canonical_addresses (_id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT);");
        db.execSQL("CREATE TABLE private_addresses (_id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT,deleted INTEGER DEFAULT 0,sync_state INTEGER DEFAULT 0,marker INTEGER DEFAULT 0,source TEXT);");
        db.execSQL("CREATE TABLE threads (_id INTEGER PRIMARY KEY AUTOINCREMENT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,unread_count INTEGER DEFAULT 0,recipient_ids TEXT,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,type INTEGER DEFAULT 0,error INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0,has_draft INTEGER DEFAULT 0,stick_time INTEGER DEFAULT 0,private_addr_ids TEXT DEFAULT NULL,last_sim_id INTEGER DEFAULT 0,sp_type INTEGER DEFAULT 0,sync_state INTEGER DEFAULT 0,marker INTEGER DEFAULT 0,source TEXT,mx_seq TEXT);");
        db.execSQL("CREATE TABLE blocked_threads (_id INTEGER PRIMARY KEY AUTOINCREMENT,recipient_ids TEXT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,unread_count INTEGER DEFAULT 0,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,last_sim_id INTEGER DEFAULT 0,type INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0);");
        db.execSQL("CREATE TABLE sim_cards (_id INTEGER PRIMARY KEY,sim_id INTEGER NOT NULL DEFAULT 0,number TEXT,bind_id INTEGER NOT NULL DEFAULT 0,marker1 INTEGER NOT NULL DEFAULT 0,marker2 INTEGER NOT NULL DEFAULT 0,sync_extra_info TEXT,download_status INTEGER NOT NULL DEFAULT 1);");
        db.execSQL("CREATE TABLE pending_msgs (_id INTEGER PRIMARY KEY,proto_type INTEGER,msg_id INTEGER,msg_type INTEGER,err_type INTEGER,err_code INTEGER,retry_index INTEGER NOT NULL DEFAULT 0,due_time INTEGER,last_try INTEGER);");
    }

    private void createHmsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS hms (_id INTEGER PRIMARY KEY,thread_id INTEGER,type INTEGER DEFAULT 0,date INTEGER,address TEXT,read INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,snippet TEXT,mx_type INTEGER,mx_extension TEXT,mx_content TEXT,advanced_seen INTEGER DEFAULT 0,mx_message_id TEXT, mx_seq INTEGER);");
    }

    private void createCommonTriggers(SQLiteDatabase db) {
        db.execSQL("CREATE TRIGGER insert_mms_pending_on_insert AFTER INSERT ON pdu WHEN new.m_type=130  OR new.m_type=135 BEGIN   INSERT INTO pending_msgs    (proto_type,     msg_id,     msg_type,     err_type,     err_code,     retry_index,     due_time)   VALUES     (1,      new._id,      new.m_type,0,0,0,0);END;");
        db.execSQL("CREATE TRIGGER insert_mms_pending_on_update AFTER UPDATE ON pdu WHEN new.m_type=128  AND new.msg_box=4  AND old.msg_box!=4 BEGIN   INSERT INTO pending_msgs    (proto_type,     msg_id,     msg_type,     err_type,     err_code,     retry_index,     due_time)   VALUES     (1,      new._id,      new.m_type,0,0,0,0);END;");
        db.execSQL("CREATE TRIGGER delete_mms_pending_on_update AFTER UPDATE ON pdu WHEN old.msg_box=4  AND new.msg_box!=4 BEGIN   DELETE FROM pending_msgs  WHERE msg_id=new._id; END;");
        db.execSQL(PDU_DELETE_PENDING_ON_DELETE_TRIGGER);
        db.execSQL(PDU_DELETE_PENDING_ON_MARK_TRIGGER);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r5, int r6, int r7) {
        /*
        r4 = this;
        r1 = "MmsSmsDatabaseHelper";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Upgrading database from version ";
        r2 = r2.append(r3);
        r2 = r2.append(r6);
        r3 = " to ";
        r2 = r2.append(r3);
        r2 = r2.append(r7);
        r3 = ".";
        r2 = r2.append(r3);
        r2 = r2.toString();
        android.util.Log.w(r1, r2);
        switch(r6) {
            case 40: goto L_0x0039;
            case 41: goto L_0x0049;
            case 42: goto L_0x0059;
            case 43: goto L_0x0069;
            case 44: goto L_0x0079;
            case 45: goto L_0x0089;
            case 46: goto L_0x0099;
            case 47: goto L_0x00a9;
            case 48: goto L_0x00b9;
            case 49: goto L_0x00c6;
            case 50: goto L_0x00d8;
            case 51: goto L_0x00e8;
            case 52: goto L_0x00ec;
            case 53: goto L_0x00fc;
            case 54: goto L_0x010c;
            case 55: goto L_0x011c;
            case 56: goto L_0x012c;
            case 57: goto L_0x013c;
            case 58: goto L_0x014c;
            case 59: goto L_0x015c;
            case 60: goto L_0x016c;
            case 61: goto L_0x017c;
            case 62: goto L_0x018c;
            case 63: goto L_0x019c;
            case 64: goto L_0x01ac;
            case 65: goto L_0x01bc;
            case 66: goto L_0x01cc;
            case 67: goto L_0x01dc;
            case 68: goto L_0x01ec;
            case 69: goto L_0x01fc;
            case 70: goto L_0x020c;
            case 71: goto L_0x021c;
            case 72: goto L_0x022c;
            case 73: goto L_0x023c;
            case 74: goto L_0x024c;
            case 75: goto L_0x025c;
            case 76: goto L_0x026c;
            case 77: goto L_0x027c;
            case 78: goto L_0x028c;
            case 79: goto L_0x029c;
            case 80: goto L_0x02ac;
            case 81: goto L_0x02bc;
            case 82: goto L_0x02cc;
            case 83: goto L_0x02dc;
            case 84: goto L_0x02ec;
            case 85: goto L_0x02fc;
            case 86: goto L_0x030c;
            case 87: goto L_0x031c;
            case 88: goto L_0x032c;
            case 89: goto L_0x033c;
            case 90: goto L_0x034c;
            case 91: goto L_0x035c;
            case 92: goto L_0x036c;
            case 93: goto L_0x037c;
            case 94: goto L_0x038c;
            default: goto L_0x002b;
        };
    L_0x002b:
        r1 = "MmsSmsDatabaseHelper";
        r2 = "Destroying all old data.";
        android.util.Log.e(r1, r2);
        r4.dropAll(r5);
        r4.onCreate(r5);
    L_0x0038:
        return;
    L_0x0039:
        r1 = 40;
        if (r7 <= r1) goto L_0x0038;
    L_0x003d:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion41(r5);	 Catch:{ Throwable -> 0x039e }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x039e }
        r5.endTransaction();
    L_0x0049:
        r1 = 41;
        if (r7 <= r1) goto L_0x0038;
    L_0x004d:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion42(r5);	 Catch:{ Throwable -> 0x03b2 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x03b2 }
        r5.endTransaction();
    L_0x0059:
        r1 = 42;
        if (r7 <= r1) goto L_0x0038;
    L_0x005d:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion43(r5);	 Catch:{ Throwable -> 0x03c6 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x03c6 }
        r5.endTransaction();
    L_0x0069:
        r1 = 43;
        if (r7 <= r1) goto L_0x0038;
    L_0x006d:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion44(r5);	 Catch:{ Throwable -> 0x03da }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x03da }
        r5.endTransaction();
    L_0x0079:
        r1 = 44;
        if (r7 <= r1) goto L_0x0038;
    L_0x007d:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion45(r5);	 Catch:{ Throwable -> 0x03ee }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x03ee }
        r5.endTransaction();
    L_0x0089:
        r1 = 45;
        if (r7 <= r1) goto L_0x0038;
    L_0x008d:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion46(r5);	 Catch:{ Throwable -> 0x0402 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0402 }
        r5.endTransaction();
    L_0x0099:
        r1 = 46;
        if (r7 <= r1) goto L_0x0038;
    L_0x009d:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion47(r5);	 Catch:{ Throwable -> 0x0416 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0416 }
        r5.endTransaction();
    L_0x00a9:
        r1 = 47;
        if (r7 <= r1) goto L_0x0038;
    L_0x00ad:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion48(r5);	 Catch:{ Throwable -> 0x042a }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x042a }
        r5.endTransaction();
    L_0x00b9:
        r1 = 48;
        if (r7 <= r1) goto L_0x0038;
    L_0x00bd:
        r5.beginTransaction();
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x043e }
        r5.endTransaction();
    L_0x00c6:
        r1 = 49;
        if (r7 <= r1) goto L_0x0038;
    L_0x00ca:
        r5.beginTransaction();
        r1 = "CREATE INDEX IF NOT EXISTS typeThreadIdIndex ON sms (type, thread_id);";
        r5.execSQL(r1);	 Catch:{ Throwable -> 0x0452 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0452 }
        r5.endTransaction();
    L_0x00d8:
        r1 = 50;
        if (r7 <= r1) goto L_0x0038;
    L_0x00dc:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion51(r5);	 Catch:{ Throwable -> 0x0466 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0466 }
        r5.endTransaction();
    L_0x00e8:
        r1 = 51;
        if (r7 <= r1) goto L_0x0038;
    L_0x00ec:
        r1 = 52;
        if (r7 <= r1) goto L_0x0038;
    L_0x00f0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion53(r5);	 Catch:{ Throwable -> 0x047a }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x047a }
        r5.endTransaction();
    L_0x00fc:
        r1 = 53;
        if (r7 <= r1) goto L_0x0038;
    L_0x0100:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion54(r5);	 Catch:{ Throwable -> 0x048e }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x048e }
        r5.endTransaction();
    L_0x010c:
        r1 = 54;
        if (r7 <= r1) goto L_0x0038;
    L_0x0110:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion55(r5);	 Catch:{ Throwable -> 0x04a2 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x04a2 }
        r5.endTransaction();
    L_0x011c:
        r1 = 55;
        if (r7 <= r1) goto L_0x0038;
    L_0x0120:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion56(r5);	 Catch:{ Throwable -> 0x04b6 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x04b6 }
        r5.endTransaction();
    L_0x012c:
        r1 = 56;
        if (r7 <= r1) goto L_0x0038;
    L_0x0130:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion57(r5);	 Catch:{ Throwable -> 0x04ca }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x04ca }
        r5.endTransaction();
    L_0x013c:
        r1 = 57;
        if (r7 <= r1) goto L_0x0038;
    L_0x0140:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion58(r5);	 Catch:{ Throwable -> 0x04de }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x04de }
        r5.endTransaction();
    L_0x014c:
        r1 = 58;
        if (r7 <= r1) goto L_0x0038;
    L_0x0150:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion59(r5);	 Catch:{ Throwable -> 0x04f2 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x04f2 }
        r5.endTransaction();
    L_0x015c:
        r1 = 59;
        if (r7 <= r1) goto L_0x0038;
    L_0x0160:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion60(r5);	 Catch:{ Throwable -> 0x0506 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0506 }
        r5.endTransaction();
    L_0x016c:
        r1 = 60;
        if (r7 <= r1) goto L_0x0038;
    L_0x0170:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion61(r5);	 Catch:{ Throwable -> 0x051a }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x051a }
        r5.endTransaction();
    L_0x017c:
        r1 = 61;
        if (r7 <= r1) goto L_0x0038;
    L_0x0180:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion62(r5);	 Catch:{ Throwable -> 0x052e }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x052e }
        r5.endTransaction();
    L_0x018c:
        r1 = 62;
        if (r7 <= r1) goto L_0x0038;
    L_0x0190:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion63(r5);	 Catch:{ Throwable -> 0x0542 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0542 }
        r5.endTransaction();
    L_0x019c:
        r1 = 63;
        if (r7 <= r1) goto L_0x0038;
    L_0x01a0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion64(r5);	 Catch:{ Throwable -> 0x0556 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0556 }
        r5.endTransaction();
    L_0x01ac:
        r1 = 64;
        if (r7 <= r1) goto L_0x0038;
    L_0x01b0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion65(r5);	 Catch:{ Throwable -> 0x056a }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x056a }
        r5.endTransaction();
    L_0x01bc:
        r1 = 65;
        if (r7 <= r1) goto L_0x0038;
    L_0x01c0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion66(r5);	 Catch:{ Throwable -> 0x057e }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x057e }
        r5.endTransaction();
    L_0x01cc:
        r1 = 66;
        if (r7 <= r1) goto L_0x0038;
    L_0x01d0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion67(r5);	 Catch:{ Throwable -> 0x0592 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0592 }
        r5.endTransaction();
    L_0x01dc:
        r1 = 67;
        if (r7 <= r1) goto L_0x0038;
    L_0x01e0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion68(r5);	 Catch:{ Throwable -> 0x05a6 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x05a6 }
        r5.endTransaction();
    L_0x01ec:
        r1 = 68;
        if (r7 <= r1) goto L_0x0038;
    L_0x01f0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion69(r5);	 Catch:{ Throwable -> 0x05ba }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x05ba }
        r5.endTransaction();
    L_0x01fc:
        r1 = 69;
        if (r7 <= r1) goto L_0x0038;
    L_0x0200:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion70(r5);	 Catch:{ Throwable -> 0x05ce }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x05ce }
        r5.endTransaction();
    L_0x020c:
        r1 = 70;
        if (r7 <= r1) goto L_0x0038;
    L_0x0210:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion71(r5);	 Catch:{ Throwable -> 0x05e2 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x05e2 }
        r5.endTransaction();
    L_0x021c:
        r1 = 71;
        if (r7 <= r1) goto L_0x0038;
    L_0x0220:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion72(r5);	 Catch:{ Throwable -> 0x05f6 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x05f6 }
        r5.endTransaction();
    L_0x022c:
        r1 = 72;
        if (r7 <= r1) goto L_0x0038;
    L_0x0230:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion73(r5);	 Catch:{ Throwable -> 0x060a }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x060a }
        r5.endTransaction();
    L_0x023c:
        r1 = 73;
        if (r7 <= r1) goto L_0x0038;
    L_0x0240:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion74(r5);	 Catch:{ Throwable -> 0x061e }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x061e }
        r5.endTransaction();
    L_0x024c:
        r1 = 74;
        if (r7 <= r1) goto L_0x0038;
    L_0x0250:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion75(r5);	 Catch:{ Throwable -> 0x0632 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0632 }
        r5.endTransaction();
    L_0x025c:
        r1 = 75;
        if (r7 <= r1) goto L_0x0038;
    L_0x0260:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion76(r5);	 Catch:{ Throwable -> 0x0646 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0646 }
        r5.endTransaction();
    L_0x026c:
        r1 = 76;
        if (r7 <= r1) goto L_0x0038;
    L_0x0270:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion77(r5);	 Catch:{ Throwable -> 0x065a }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x065a }
        r5.endTransaction();
    L_0x027c:
        r1 = 77;
        if (r7 <= r1) goto L_0x0038;
    L_0x0280:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion78(r5);	 Catch:{ Throwable -> 0x066e }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x066e }
        r5.endTransaction();
    L_0x028c:
        r1 = 78;
        if (r7 <= r1) goto L_0x0038;
    L_0x0290:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion79(r5);	 Catch:{ Throwable -> 0x0682 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0682 }
        r5.endTransaction();
    L_0x029c:
        r1 = 79;
        if (r7 <= r1) goto L_0x0038;
    L_0x02a0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion80(r5);	 Catch:{ Throwable -> 0x0696 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0696 }
        r5.endTransaction();
    L_0x02ac:
        r1 = 80;
        if (r7 <= r1) goto L_0x0038;
    L_0x02b0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion81(r5);	 Catch:{ Throwable -> 0x06aa }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x06aa }
        r5.endTransaction();
    L_0x02bc:
        r1 = 81;
        if (r7 <= r1) goto L_0x0038;
    L_0x02c0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion82(r5);	 Catch:{ Throwable -> 0x06be }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x06be }
        r5.endTransaction();
    L_0x02cc:
        r1 = 82;
        if (r7 <= r1) goto L_0x0038;
    L_0x02d0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion83(r5);	 Catch:{ Throwable -> 0x06d2 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x06d2 }
        r5.endTransaction();
    L_0x02dc:
        r1 = 83;
        if (r7 <= r1) goto L_0x0038;
    L_0x02e0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion84(r5);	 Catch:{ Throwable -> 0x06e6 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x06e6 }
        r5.endTransaction();
    L_0x02ec:
        r1 = 84;
        if (r7 <= r1) goto L_0x0038;
    L_0x02f0:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion85(r5);	 Catch:{ Throwable -> 0x06fa }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x06fa }
        r5.endTransaction();
    L_0x02fc:
        r1 = 85;
        if (r7 <= r1) goto L_0x0038;
    L_0x0300:
        r5.beginTransaction();
        r4.upgradeDatabasesToVersion86(r5);	 Catch:{ Throwable -> 0x070e }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x070e }
        r5.endTransaction();
    L_0x030c:
        r1 = 86;
        if (r7 <= r1) goto L_0x0038;
    L_0x0310:
        r5.beginTransaction();
        r4.upgradeDatabasesToVersion86(r5);	 Catch:{ Throwable -> 0x0722 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0722 }
        r5.endTransaction();
    L_0x031c:
        r1 = 87;
        if (r7 <= r1) goto L_0x0038;
    L_0x0320:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion87(r5);	 Catch:{ Throwable -> 0x0736 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0736 }
        r5.endTransaction();
    L_0x032c:
        r1 = 88;
        if (r7 <= r1) goto L_0x0038;
    L_0x0330:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion88(r5);	 Catch:{ Throwable -> 0x074a }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x074a }
        r5.endTransaction();
    L_0x033c:
        r1 = 89;
        if (r7 <= r1) goto L_0x0038;
    L_0x0340:
        r5.beginTransaction();
        r4.updateDatabaseToVersion89(r5);	 Catch:{ Throwable -> 0x075e }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x075e }
        r5.endTransaction();
    L_0x034c:
        r1 = 90;
        if (r7 <= r1) goto L_0x0038;
    L_0x0350:
        r5.beginTransaction();
        r4.updateDatabaseToVersion90(r5);	 Catch:{ Throwable -> 0x0772 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0772 }
        r5.endTransaction();
    L_0x035c:
        r1 = 91;
        if (r7 <= r1) goto L_0x0038;
    L_0x0360:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion92(r5);	 Catch:{ Throwable -> 0x0786 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0786 }
        r5.endTransaction();
    L_0x036c:
        r1 = 92;
        if (r7 <= r1) goto L_0x0038;
    L_0x0370:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion93(r5);	 Catch:{ Throwable -> 0x079a }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x079a }
        r5.endTransaction();
    L_0x037c:
        r1 = 93;
        if (r7 <= r1) goto L_0x0038;
    L_0x0380:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion94(r5);	 Catch:{ Throwable -> 0x07ae }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x07ae }
        r5.endTransaction();
    L_0x038c:
        r1 = 94;
        if (r7 <= r1) goto L_0x0038;
    L_0x0390:
        r5.beginTransaction();
        r4.upgradeDatabaseToVersion95(r5);	 Catch:{ Throwable -> 0x07c2 }
        r5.setTransactionSuccessful();	 Catch:{ Throwable -> 0x07c2 }
        r5.endTransaction();
        goto L_0x0038;
    L_0x039e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x03ad }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x03ad }
        r5.endTransaction();
        goto L_0x002b;
    L_0x03ad:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x03b2:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x03c1 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x03c1 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x03c1:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x03c6:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x03d5 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x03d5 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x03d5:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x03da:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x03e9 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x03e9 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x03e9:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x03ee:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x03fd }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x03fd }
        r5.endTransaction();
        goto L_0x002b;
    L_0x03fd:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0402:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0411 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0411 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0411:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0416:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0425 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0425 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0425:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x042a:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0439 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0439 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0439:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x043e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x044d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x044d }
        r5.endTransaction();
        goto L_0x002b;
    L_0x044d:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0452:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0461 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0461 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0461:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0466:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0475 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0475 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0475:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x047a:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0489 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0489 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0489:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x048e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x049d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x049d }
        r5.endTransaction();
        goto L_0x002b;
    L_0x049d:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x04a2:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x04b1 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x04b1 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x04b1:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x04b6:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x04c5 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x04c5 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x04c5:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x04ca:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x04d9 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x04d9 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x04d9:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x04de:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x04ed }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x04ed }
        r5.endTransaction();
        goto L_0x002b;
    L_0x04ed:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x04f2:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0501 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0501 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0501:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0506:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0515 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0515 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0515:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x051a:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0529 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0529 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0529:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x052e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x053d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x053d }
        r5.endTransaction();
        goto L_0x002b;
    L_0x053d:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0542:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0551 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0551 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0551:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0556:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0565 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0565 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0565:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x056a:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0579 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0579 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0579:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x057e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x058d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x058d }
        r5.endTransaction();
        goto L_0x002b;
    L_0x058d:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0592:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x05a1 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x05a1 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x05a1:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x05a6:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x05b5 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x05b5 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x05b5:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x05ba:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x05c9 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x05c9 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x05c9:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x05ce:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x05dd }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x05dd }
        r5.endTransaction();
        goto L_0x002b;
    L_0x05dd:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x05e2:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x05f1 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x05f1 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x05f1:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x05f6:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0605 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0605 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0605:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x060a:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0619 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0619 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0619:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x061e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x062d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x062d }
        r5.endTransaction();
        goto L_0x002b;
    L_0x062d:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0632:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0641 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0641 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0641:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0646:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0655 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0655 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0655:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x065a:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0669 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0669 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0669:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x066e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x067d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x067d }
        r5.endTransaction();
        goto L_0x002b;
    L_0x067d:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0682:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0691 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0691 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0691:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0696:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x06a5 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x06a5 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x06a5:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x06aa:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x06b9 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x06b9 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x06b9:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x06be:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x06cd }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x06cd }
        r5.endTransaction();
        goto L_0x002b;
    L_0x06cd:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x06d2:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x06e1 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x06e1 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x06e1:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x06e6:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x06f5 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x06f5 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x06f5:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x06fa:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0709 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0709 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0709:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x070e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x071d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x071d }
        r5.endTransaction();
        goto L_0x002b;
    L_0x071d:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0722:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0731 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0731 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0731:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0736:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0745 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0745 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0745:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x074a:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0759 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0759 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0759:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x075e:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x076d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x076d }
        r5.endTransaction();
        goto L_0x002b;
    L_0x076d:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0772:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0781 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0781 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0781:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x0786:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0795 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0795 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x0795:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x079a:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x07a9 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x07a9 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x07a9:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x07ae:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x07bd }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x07bd }
        r5.endTransaction();
        goto L_0x002b;
    L_0x07bd:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
    L_0x07c2:
        r0 = move-exception;
        r1 = "MmsSmsDatabaseHelper";
        r2 = r0.getMessage();	 Catch:{ all -> 0x07d1 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x07d1 }
        r5.endTransaction();
        goto L_0x002b;
    L_0x07d1:
        r1 = move-exception;
        r5.endTransaction();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsSmsDatabaseHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    public void dropAll(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS canonical_addresses");
        db.execSQL("DROP TABLE IF EXISTS threads");
        db.execSQL("DROP TABLE IF EXISTS pending_msgs");
        db.execSQL("DROP TABLE IF EXISTS sms");
        db.execSQL("DROP TABLE IF EXISTS raw");
        db.execSQL("DROP TABLE IF EXISTS attachments");
        db.execSQL("DROP TABLE IF EXISTS threads");
        db.execSQL("DROP TABLE IF EXISTS sr_pending");
        db.execSQL("DROP TABLE IF EXISTS pdu;");
        db.execSQL("DROP TABLE IF EXISTS addr;");
        db.execSQL("DROP TABLE IF EXISTS part;");
        db.execSQL("DROP TABLE IF EXISTS rate;");
        db.execSQL("DROP TABLE IF EXISTS drm;");
        db.execSQL("DROP TABLE IF EXISTS sim_cards;");
        db.execSQL("DROP TABLE IF EXISTS hms");
    }

    private void upgradeDatabaseToVersion41(SQLiteDatabase db) {
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_move_mms");
        db.execSQL("CREATE TRIGGER update_threads_error_on_move_mms   BEFORE UPDATE OF msg_box ON pdu   WHEN (OLD.msg_box = 4 AND NEW.msg_box != 4)   AND (OLD._id IN (SELECT DISTINCT msg_id                   FROM pending_msgs                   WHERE err_type >= 10)) BEGIN   UPDATE threads SET error = error - 1  WHERE _id = OLD.thread_id; END;");
    }

    private void upgradeDatabaseToVersion42(SQLiteDatabase db) {
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_on_delete");
        db.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_sms");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_delete_sms");
    }

    private void upgradeDatabaseToVersion43(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE threads ADD COLUMN has_attachment INTEGER DEFAULT 0");
        updateThreadsAttachmentColumn(db);
    }

    private void upgradeDatabaseToVersion44(SQLiteDatabase db) {
        updateThreadsAttachmentColumn(db);
    }

    private void upgradeDatabaseToVersion45(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms ADD COLUMN locked INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN locked INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion46(SQLiteDatabase db) {
        Iterator i$;
        db.execSQL("ALTER TABLE part ADD COLUMN text TEXT");
        SQLiteDatabase sQLiteDatabase = db;
        Cursor textRows = sQLiteDatabase.query("part", new String[]{"_id", ThreadSettings.WALLPAPER, WapPush.TEXT}, "ct = 'text/plain' OR ct == 'application/smil'", null, null, null, null);
        ArrayList<String> filesToDelete = new ArrayList();
        db.beginTransaction();
        if (textRows != null) {
            int partDataColumn = textRows.getColumnIndex(ThreadSettings.WALLPAPER);
            while (textRows.moveToNext()) {
                String path = textRows.getString(partDataColumn);
                if (path != null) {
                    try {
                        InputStream is = new FileInputStream(path);
                        is.read(new byte[is.available()]);
                        String[] strArr = new String[]{new EncodedStringValue(data).getString()};
                        db.execSQL("UPDATE part SET _data = NULL, text = ?", strArr);
                        is.close();
                        filesToDelete.add(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        db.endTransaction();
                        i$ = filesToDelete.iterator();
                        while (i$.hasNext()) {
                            String pathToDelete = (String) i$.next();
                            try {
                                new File(pathToDelete).delete();
                            } catch (SecurityException ex) {
                                Log.e(TAG, "unable to clean up old mms file for " + pathToDelete, ex);
                            }
                        }
                        if (textRows != null) {
                            textRows.close();
                        }
                    }
                }
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        i$ = filesToDelete.iterator();
        while (i$.hasNext()) {
            pathToDelete = (String) i$.next();
            try {
                new File(pathToDelete).delete();
            } catch (SecurityException ex2) {
                Log.e(TAG, "unable to clean up old mms file for " + pathToDelete, ex2);
            }
        }
        if (textRows != null) {
            textRows.close();
        }
    }

    private void upgradeDatabaseToVersion47(SQLiteDatabase db) {
        updateThreadsAttachmentColumn(db);
    }

    private void upgradeDatabaseToVersion48(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms ADD COLUMN error_code INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion51(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms add COLUMN seen INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu add COLUMN seen INTEGER DEFAULT 0");
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(WapPush.SEEN, Integer.valueOf(1));
            Log.d(TAG, "[MmsSmsDb] upgradeDatabaseToVersion51: updated " + db.update("sms", contentValues, "read=1", null) + " rows in sms table to have READ=1");
            Log.d(TAG, "[MmsSmsDb] upgradeDatabaseToVersion51: updated " + db.update("pdu", contentValues, "read=1", null) + " rows in pdu table to have READ=1");
        } catch (Exception ex) {
            Log.e(TAG, "[MmsSmsDb] upgradeDatabaseToVersion51 caught ", ex);
        }
    }

    private void upgradeDatabaseToVersion53(SQLiteDatabase db) {
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_read_on_update");
    }

    private void upgradeDatabaseToVersion54(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms ADD COLUMN date_sent INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN date_sent INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion55(SQLiteDatabase db) {
        db.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_pdu");
        db.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_when_update_pdu");
    }

    private void upgradeDatabaseToVersion56(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE threads ADD COLUMN unread_count INTEGER DEFAULT 0");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_on_insert");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_on_insert");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_date_subject_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_date_subject_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_read_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_read_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_on_delete");
        db.execSQL("  UPDATE threads SET unread_count =   (SELECT COUNT(*) FROM sms   WHERE read =0      AND thread_id = threads._id) +   (SELECT COUNT(*) FROM pdu   WHERE read=0      AND thread_id = threads._id     AND (m_type=132 OR m_type=130 OR m_type=128)); ");
    }

    private void upgradeDatabaseToVersion57(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN timed INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN timed INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion58(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN date_ms_part INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN deleted INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN deleted INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN sync_state INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN sync_state INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN marker INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN marker INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN source TEXT");
        db.execSQL("ALTER TABLE sms ADD COLUMN source TEXT");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_on_insert");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_on_insert");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_date_subject_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_date_subject_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_read_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_read_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_on_delete");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_on_insert_part");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_on_update_part");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_on_delete_part");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_on_update_pdu");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_delete_mms");
        db.execSQL("DROP TRIGGER IF EXISTS cleanup_delivery_and_read_report");
        db.execSQL("DROP TRIGGER IF EXISTS delete_mms_pending_on_delete");
    }

    private void upgradeDatabaseToVersion59(SQLiteDatabase db) {
        Log.e(TAG, "Upgrade to 59");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_on_insert_part");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_on_update_part");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_on_delete_part");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_on_update_pdu");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_read_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_read_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_on_delete");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_delete_mms");
        db.execSQL("DROP TRIGGER IF EXISTS cleanup_delivery_and_read_report");
        db.execSQL("DROP TRIGGER IF EXISTS sms_words_update");
        db.execSQL("DROP TRIGGER IF EXISTS sms_words_delete");
        db.execSQL("DROP TRIGGER IF EXISTS mms_words_update");
        db.execSQL("DROP TRIGGER IF EXISTS mms_words_delete");
        db.execSQL("DROP TRIGGER IF EXISTS part_cleanup");
        db.execSQL("DROP TRIGGER IF EXISTS addr_cleanup");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_update_mms");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_move_mms");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_update_sms");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_on_insert");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_on_insert");
        db.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_date_subject_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_date_subject_on_update");
        db.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_move_mms");
        db.execSQL("DROP TRIGGER IF EXISTS delete_mms_pending_on_delete");
        db.execSQL("CREATE INDEX thread_index_on_pdu ON pdu(msg_box,thread_id)");
        db.execSQL("CREATE INDEX msg_index_on_part ON part(mid)");
        db.execSQL(PDU_CLEANUP_DELIVERY_READ_REPORT_TRIGGER);
        db.execSQL(PDU_MARK_DELIVERY_READ_REPORT_TRIGGER);
        db.execSQL(PDU_DELETE_PENDING_ON_DELETE_TRIGGER);
        db.execSQL(PDU_DELETE_PENDING_ON_MARK_TRIGGER);
        db.execSQL("ALTER TABLE pdu ADD COLUMN bind_id INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN bind_id INTEGER DEFAULT 0");
        db.execSQL("CREATE TABLE sim_cards (_id INTEGER PRIMARY KEY,sim_id INTEGER NOT NULL DEFAULT 0,number TEXT,bind_id INTEGER NOT NULL DEFAULT 0,marker1 INTEGER NOT NULL DEFAULT 0,marker2 INTEGER NOT NULL DEFAULT 0);");
    }

    private void upgradeDatabaseToVersion60(SQLiteDatabase db) {
        db.execSQL("DROP TRIGGER IF EXISTS part_cleanup");
        db.execSQL("DROP TRIGGER IF EXISTS addr_cleanup");
        db.execSQL(PDU_CLEANUP_PART_AND_ADDR_TRIGGER);
    }

    private void upgradeDatabaseToVersion61(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms ADD COLUMN mx_status INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN mx_id INTEGER");
        db.execSQL("ALTER TABLE pdu ADD COLUMN mx_status INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN mx_id INTEGER");
        db.execSQL("CREATE INDEX IF NOT EXISTS mx_msgId_index_on_sms ON sms(mx_id)");
        db.execSQL("CREATE INDEX IF NOT EXISTS mx_msgId_index_on_pdu ON pdu(mx_id)");
    }

    private void upgradeDatabaseToVersion62(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN file_id TEXT");
        db.execSQL("ALTER TABLE pdu ADD COLUMN need_download INTEGER DEFAULT 0");
        db.execSQL(PDU_ON_FILE_ID_CHANGE_TRIGGER);
    }

    private void upgradeDatabaseToVersion63(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms ADD COLUMN out_time INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion64(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE threads ADD COLUMN has_draft INTEGER DEFAULT 0");
        db.execSQL("UPDATE threads SET has_draft =      (SELECT COUNT(_id) FROM sms      WHERE sms.thread_id = threads._id        AND sms.type=3 AND sms.deleted = 0) +      (SELECT COUNT(_id) FROM pdu      WHERE pdu.thread_id = threads._id        AND pdu.msg_box = 3 AND pdu.deleted = 0) ");
    }

    private void upgradeDatabaseToVersion65(SQLiteDatabase db) {
        db.execSQL("DELETE FROM sim_cards;");
        System.putInt(this.mContext.getContentResolver(), "mms_sync_wild_msg_state", 0);
        System.putString(this.mContext.getContentResolver(), "mms_sync_wild_numbers", null);
    }

    private void upgradeDatabaseToVersion66(SQLiteDatabase db) {
        ContentValues v = new ContentValues();
        v.put(WapPush.DATE, Integer.valueOf(0));
        v.put(Conversations.SNIPPET, (String) null);
        v.put("snippet_cs", (String) null);
        db.update(MmsSmsProvider.TABLE_THREADS, v, "message_count=0 AND has_draft=0", null);
    }

    private void upgradeDatabaseToVersion67(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms ADD COLUMN account TEXT");
        db.execSQL("ALTER TABLE pdu ADD COLUMN account TEXT");
        ContentValues v = new ContentValues();
        v.put(TABLE.ACCOUNT, MmsSmsUtils.getXiaomiAccountName(this.mContext));
        db.update("sms", v, "bind_id>0", null);
        db.update("pdu", v, "bind_id>0", null);
    }

    private void upgradeDatabaseToVersion68(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN out_time INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion69(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN snippet TEXT");
        db.execSQL("ALTER TABLE pdu ADD COLUMN preview_type INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN preview_data BLOB");
    }

    private void upgradeDatabaseToVersion70(SQLiteDatabase db) {
        String selection = "m_type IS NULL";
        HashSet<Long> threadIds = MmsSmsUtils.queryLongValuesToSet(db, "pdu", WapPush.THREAD_ID, selection, null);
        if (db.delete("pdu", selection, null) > 0) {
            Iterator i$ = threadIds.iterator();
            while (i$.hasNext()) {
                updateThread(this.mContext, db, ((Long) i$.next()).longValue());
            }
        }
        ContentValues v = new ContentValues();
        v.put("preview_type", Integer.valueOf(0));
        v.put(Conversations.SNIPPET, (String) null);
        v.put("preview_data", (byte[]) null);
        db.update("pdu", v, null, null);
    }

    private void upgradeDatabaseToVersion71(SQLiteDatabase db) {
        ContentValues v = new ContentValues();
        v.put("ct_t", "application/vnd.wap.multipart.related");
        db.update("pdu", v, "ct_t IS NULL AND (m_type=128 OR m_type=132)", null);
    }

    private void upgradeDatabaseToVersion72(SQLiteDatabase db) {
        db.execSQL("UPDATE threads SET error = CASE    (SELECT COUNT(msg_id) FROM pending_msgs JOIN pdu ON       pdu.thread_id=threads._id AND err_type >= 10 AND       pending_msgs.msg_id=pdu._id AND deleted=0) +   (SELECT COUNT(_id) FROM pdu WHERE       thread_id=threads._id       AND msg_box=2       AND mx_status=131073 AND deleted=0) +   (SELECT COUNT(_id) FROM sms WHERE       thread_id=threads._id       AND type=5       AND deleted=0)   WHEN 0 THEN 0 ELSE 1 END");
    }

    private void upgradeDatabaseToVersion73(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN preview_data_ts INTEGER DEFAULT 0");
        db.execSQL("UPDATE pdu SET preview_data_ts=" + System.currentTimeMillis() + " WHERE " + "preview_data" + " IS NOT NULL");
    }

    private void upgradeDatabaseToVersion74(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE threads ADD COLUMN stick_time INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion75(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE threads ADD COLUMN private_addr_ids TEXT DEFAULT NULL");
        db.execSQL("ALTER TABLE pdu ADD COLUMN block_type INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN block_type INTEGER DEFAULT 0");
        db.execSQL("CREATE TABLE private_addresses (_id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT);");
    }

    private void upgradeDatabaseToVersion76(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim76(db);
        } else {
            upgradeDatabaseToVersionSim76(db);
        }
    }

    private void upgradeDatabaseToVersion77(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim77(db);
        } else {
            upgradeDatabaseToVersionSim77(db);
        }
    }

    private void upgradeDatabaseToVersion78(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim78(db);
        } else {
            upgradeDatabaseToVersionSim78(db);
        }
    }

    private void upgradeDatabaseToVersion79(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim79(db);
        } else {
            upgradeDatabaseToVersionSim79(db);
        }
    }

    private void upgradeDatabaseToVersion80(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim80(db);
        } else {
            upgradeDatabaseToVersionSim80(db);
        }
    }

    private void upgradeDatabaseToVersion81(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim81(db);
        } else {
            upgradeDatabaseToVersionSim81(db);
        }
    }

    private void upgradeDatabaseToVersion82(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim82(db);
        } else {
            upgradeDatabaseToVersionSim82(db);
        }
    }

    private void upgradeDatabaseToVersion83(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim83(db);
        } else {
            upgradeDatabaseToVersionSim83(db);
        }
    }

    private void upgradeDatabaseToVersion84(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim84(db);
        } else {
            upgradeDatabaseToVersionSim84(db);
        }
    }

    private void upgradeDatabaseToVersion85(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim85(db);
        } else {
            upgradeDatabaseToVersionSim85(db);
        }
    }

    private void upgradeDatabasesToVersion86(SQLiteDatabase db) {
        if (isMSimUpgrade()) {
            upgradeDatabaseToVersionMSim86(db);
        } else {
            upgradeDatabaseToVersionSim86(db);
        }
    }

    private void upgradeDatabaseToVersion87(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE hms ADD COLUMN mx_content TEXT");
    }

    private void upgradeDatabaseToVersion88(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE hms ADD COLUMN mx_message_id TEXT");
    }

    private void updateDatabaseToVersion89(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN text_only INTEGER DEFAULT 0");
    }

    private void updateDatabaseToVersion90(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE hms ADD COLUMN mx_seq INTEGER");
        db.execSQL("CREATE INDEX seq_address_index ON hms(address, mx_seq)");
    }

    private void upgradeDatabaseToVersion92(SQLiteDatabase db) {
        db.execSQL("CREATE INDEX IF NOT EXISTS sms_date_index ON sms (date);");
        db.execSQL("CREATE INDEX IF NOT EXISTS hms_date_index ON hms (date);");
        db.execSQL("CREATE INDEX IF NOT EXISTS sms_threadId_index ON sms (thread_id, block_type);");
        db.execSQL("CREATE INDEX IF NOT EXISTS mms_threadId_index ON pdu (thread_id, m_type, block_type);");
        db.execSQL("CREATE INDEX IF NOT EXISTS hms_threadId_index ON hms (thread_id);");
    }

    private void upgradeDatabaseToVersion93(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sim_cards ADD COLUMN sync_extra_info TEXT");
    }

    private void upgradeDatabaseToVersion94(SQLiteDatabase db) {
        db.execSQL("CREATE INDEX IF NOT EXISTS smsSourceIndex ON sms (source);");
        db.execSQL("CREATE INDEX IF NOT EXISTS mmsSourceIndex ON pdu (source);");
        db.execSQL("CREATE INDEX IF NOT EXISTS mms_date_index ON pdu (date, date_ms_part);");
    }

    private void upgradeDatabaseToVersion95(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sim_cards ADD COLUMN download_status INTEGER NOT NULL DEFAULT 1");
        int state = System.getInt(this.mContext.getContentResolver(), "mms_sync_wild_msg_state", 0);
        if (state == 3) {
            db.execSQL("UPDATE sim_cards SET download_status=2 WHERE download_status=1");
            System.putInt(this.mContext.getContentResolver(), "mms_sync_wild_msg_state", 5);
        } else if (state == 2) {
            System.putInt(this.mContext.getContentResolver(), "mms_sync_wild_msg_state", 4);
        } else if (state == 1) {
            System.putInt(this.mContext.getContentResolver(), "mms_sync_wild_msg_state", 5);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.database.sqlite.SQLiteDatabase getWritableDatabase() {
        /*
        r12 = this;
        monitor-enter(r12);
        r2 = 0;
        r8 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
    L_0x0004:
        if (r2 != 0) goto L_0x0020;
    L_0x0006:
        r9 = 6400; // 0x1900 float:8.968E-42 double:3.162E-320;
        if (r8 > r9) goto L_0x0020;
    L_0x000a:
        r2 = super.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0011 }
    L_0x000e:
        r8 = r8 * 2;
        goto L_0x0004;
    L_0x0011:
        r3 = move-exception;
        r9 = "MmsSmsDatabaseHelper";
        r10 = "Cannot open database in writable mode, wait for another try.";
        android.util.Log.w(r9, r10);	 Catch:{ all -> 0x002a }
        r9 = (long) r8;
        java.lang.Thread.sleep(r9);	 Catch:{ InterruptedException -> 0x001e }
        goto L_0x000e;
    L_0x001e:
        r9 = move-exception;
        goto L_0x000e;
    L_0x0020:
        if (r2 != 0) goto L_0x002d;
    L_0x0022:
        r9 = new android.database.sqlite.SQLiteException;	 Catch:{ all -> 0x002a }
        r10 = "Tried many times, but I can't open the database in writable mode";
        r9.<init>(r10);	 Catch:{ all -> 0x002a }
        throw r9;	 Catch:{ all -> 0x002a }
    L_0x002a:
        r9 = move-exception;
        monitor-exit(r12);
        throw r9;
    L_0x002d:
        r9 = sTriedAutoIncrement;	 Catch:{ all -> 0x002a }
        if (r9 != 0) goto L_0x0099;
    L_0x0031:
        r9 = 1;
        sTriedAutoIncrement = r9;	 Catch:{ all -> 0x002a }
        r9 = "threads";
        r6 = r12.hasAutoIncrement(r2, r9);	 Catch:{ all -> 0x002a }
        r9 = "canonical_addresses";
        r5 = r12.hasAutoIncrement(r2, r9);	 Catch:{ all -> 0x002a }
        r9 = "MmsSmsDatabaseHelper";
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002a }
        r10.<init>();	 Catch:{ all -> 0x002a }
        r11 = "[getWritableDatabase] hasAutoIncrementThreads: ";
        r10 = r10.append(r11);	 Catch:{ all -> 0x002a }
        r10 = r10.append(r6);	 Catch:{ all -> 0x002a }
        r11 = " hasAutoIncrementAddresses: ";
        r10 = r10.append(r11);	 Catch:{ all -> 0x002a }
        r10 = r10.append(r5);	 Catch:{ all -> 0x002a }
        r10 = r10.toString();	 Catch:{ all -> 0x002a }
        android.util.Log.d(r9, r10);	 Catch:{ all -> 0x002a }
        r1 = 1;
        r0 = 1;
        if (r6 != 0) goto L_0x0072;
    L_0x0066:
        r2.beginTransaction();	 Catch:{ all -> 0x002a }
        r12.upgradeThreadsTableToAutoIncrement(r2);	 Catch:{ Throwable -> 0x009b }
        r2.setTransactionSuccessful();	 Catch:{ Throwable -> 0x009b }
        r2.endTransaction();	 Catch:{ all -> 0x002a }
    L_0x0072:
        if (r5 != 0) goto L_0x0080;
    L_0x0074:
        r2.beginTransaction();	 Catch:{ all -> 0x002a }
        r12.upgradeAddressTableToAutoIncrement(r2);	 Catch:{ Throwable -> 0x00c2 }
        r2.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00c2 }
        r2.endTransaction();	 Catch:{ all -> 0x002a }
    L_0x0080:
        if (r1 == 0) goto L_0x00e9;
    L_0x0082:
        if (r0 == 0) goto L_0x00e9;
    L_0x0084:
        r9 = r12.mLowStorageMonitor;	 Catch:{ all -> 0x002a }
        if (r9 == 0) goto L_0x0099;
    L_0x0088:
        r9 = "MmsSmsDatabaseHelper";
        r10 = "Unregistering mLowStorageMonitor - we've upgraded";
        android.util.Log.d(r9, r10);	 Catch:{ all -> 0x002a }
        r9 = r12.mContext;	 Catch:{ all -> 0x002a }
        r10 = r12.mLowStorageMonitor;	 Catch:{ all -> 0x002a }
        r9.unregisterReceiver(r10);	 Catch:{ all -> 0x002a }
        r9 = 0;
        r12.mLowStorageMonitor = r9;	 Catch:{ all -> 0x002a }
    L_0x0099:
        monitor-exit(r12);
        return r2;
    L_0x009b:
        r4 = move-exception;
        r9 = "MmsSmsDatabaseHelper";
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00bd }
        r10.<init>();	 Catch:{ all -> 0x00bd }
        r11 = "Failed to add autoIncrement to threads;: ";
        r10 = r10.append(r11);	 Catch:{ all -> 0x00bd }
        r11 = r4.getMessage();	 Catch:{ all -> 0x00bd }
        r10 = r10.append(r11);	 Catch:{ all -> 0x00bd }
        r10 = r10.toString();	 Catch:{ all -> 0x00bd }
        android.util.Log.e(r9, r10, r4);	 Catch:{ all -> 0x00bd }
        r1 = 0;
        r2.endTransaction();	 Catch:{ all -> 0x002a }
        goto L_0x0072;
    L_0x00bd:
        r9 = move-exception;
        r2.endTransaction();	 Catch:{ all -> 0x002a }
        throw r9;	 Catch:{ all -> 0x002a }
    L_0x00c2:
        r4 = move-exception;
        r9 = "MmsSmsDatabaseHelper";
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e4 }
        r10.<init>();	 Catch:{ all -> 0x00e4 }
        r11 = "Failed to add autoIncrement to canonical_addresses: ";
        r10 = r10.append(r11);	 Catch:{ all -> 0x00e4 }
        r11 = r4.getMessage();	 Catch:{ all -> 0x00e4 }
        r10 = r10.append(r11);	 Catch:{ all -> 0x00e4 }
        r10 = r10.toString();	 Catch:{ all -> 0x00e4 }
        android.util.Log.e(r9, r10, r4);	 Catch:{ all -> 0x00e4 }
        r0 = 0;
        r2.endTransaction();	 Catch:{ all -> 0x002a }
        goto L_0x0080;
    L_0x00e4:
        r9 = move-exception;
        r2.endTransaction();	 Catch:{ all -> 0x002a }
        throw r9;	 Catch:{ all -> 0x002a }
    L_0x00e9:
        r9 = sFakeLowStorageTest;	 Catch:{ all -> 0x002a }
        if (r9 == 0) goto L_0x00f0;
    L_0x00ed:
        r9 = 0;
        sFakeLowStorageTest = r9;	 Catch:{ all -> 0x002a }
    L_0x00f0:
        r9 = r12.mLowStorageMonitor;	 Catch:{ all -> 0x002a }
        if (r9 != 0) goto L_0x0099;
    L_0x00f4:
        r9 = "MmsSmsDatabaseHelper";
        r10 = "[getWritableDatabase] turning on storage monitor";
        android.util.Log.d(r9, r10);	 Catch:{ all -> 0x002a }
        r9 = new com.android.providers.telephony.MmsSmsDatabaseHelper$LowStorageMonitor;	 Catch:{ all -> 0x002a }
        r9.<init>();	 Catch:{ all -> 0x002a }
        r12.mLowStorageMonitor = r9;	 Catch:{ all -> 0x002a }
        r7 = new android.content.IntentFilter;	 Catch:{ all -> 0x002a }
        r7.<init>();	 Catch:{ all -> 0x002a }
        r9 = "android.intent.action.DEVICE_STORAGE_LOW";
        r7.addAction(r9);	 Catch:{ all -> 0x002a }
        r9 = "android.intent.action.DEVICE_STORAGE_OK";
        r7.addAction(r9);	 Catch:{ all -> 0x002a }
        r9 = r12.mContext;	 Catch:{ all -> 0x002a }
        r10 = r12.mLowStorageMonitor;	 Catch:{ all -> 0x002a }
        r9.registerReceiver(r10, r7);	 Catch:{ all -> 0x002a }
        goto L_0x0099;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsSmsDatabaseHelper.getWritableDatabase():android.database.sqlite.SQLiteDatabase");
    }

    private boolean hasAutoIncrement(SQLiteDatabase db, String tableName) {
        boolean result = false;
        Cursor c = db.rawQuery("SELECT sql FROM sqlite_master WHERE type='table' AND name='" + tableName + "'", null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    String schema = c.getString(0);
                    if (schema != null) {
                        result = schema.contains("AUTOINCREMENT");
                    } else {
                        result = false;
                    }
                    Log.d(TAG, "[MmsSmsDb] tableName: " + tableName + " hasAutoIncrement: " + schema + " result: " + result);
                }
                c.close();
            } catch (Throwable th) {
                c.close();
            }
        }
        return result;
    }

    private void upgradeThreadsTableToAutoIncrement(SQLiteDatabase db) {
        if (hasAutoIncrement(db, MmsSmsProvider.TABLE_THREADS)) {
            Log.d(TAG, "[MmsSmsDb] upgradeThreadsTableToAutoIncrement: already upgraded");
            return;
        }
        Log.d(TAG, "[MmsSmsDb] upgradeThreadsTableToAutoIncrement: upgrading");
        db.execSQL("CREATE TABLE threads_temp (_id INTEGER PRIMARY KEY AUTOINCREMENT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,recipient_ids TEXT,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,type INTEGER DEFAULT 0,error INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0);");
        db.execSQL("INSERT INTO threads_temp SELECT * from threads;");
        db.execSQL("DROP TABLE threads;");
        db.execSQL("ALTER TABLE threads_temp RENAME TO threads;");
    }

    private void upgradeAddressTableToAutoIncrement(SQLiteDatabase db) {
        if (hasAutoIncrement(db, "canonical_addresses")) {
            Log.d(TAG, "[MmsSmsDb] upgradeAddressTableToAutoIncrement: already upgraded");
            return;
        }
        Log.d(TAG, "[MmsSmsDb] upgradeAddressTableToAutoIncrement: upgrading");
        db.execSQL("CREATE TABLE canonical_addresses_temp (_id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT);");
        db.execSQL("INSERT INTO canonical_addresses_temp SELECT * from canonical_addresses;");
        db.execSQL("DROP TABLE canonical_addresses;");
        db.execSQL("ALTER TABLE canonical_addresses_temp RENAME TO canonical_addresses;");
    }

    private void updateThreadsAttachmentColumn(SQLiteDatabase db) {
        db.execSQL("UPDATE threads SET has_attachment=1 WHERE _id IN   (SELECT DISTINCT pdu.thread_id FROM part    JOIN pdu ON pdu._id=part.mid    WHERE part.ct != 'text/plain' AND part.ct != 'application/smil')");
    }

    private void deleteOrphanedEntries() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete("sms", "thread_id NOT IN (SELECT _id from threads)", null);
            db.delete("pdu", "thread_id NOT IN (SELECT _id from threads)", null);
            db.delete("part", "mid NOT IN (SELECT _id from pdu)", null);
            db.delete("addr", "msg_id NOT IN (SELECT _id from pdu)", null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public static String getDescByMx2Type(Context context, int mxType) {
        String description = null;
        switch (mxType) {
            case YellowPagePhone.TYPE_ANTISPAM /*2*/:
                description = "picture";
                break;
            case YellowPagePhone.TYPE_MARKED /*3*/:
                description = "audio";
                break;
            case AntispamNumber.TYPE_ONE_RING_CALL /*4*/:
                description = "video";
                break;
            default:
                Log.d(TAG, "unsupported mxType : " + mxType);
                break;
        }
        if (TextUtils.isEmpty(description)) {
            return description;
        }
        return String.format("[%s]", new Object[]{description});
    }

    private void upgradeDatabaseToVersionMSim76(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN sim_id INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN sim_id INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersionSim76(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE private_addresses ADD COLUMN deleted INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE private_addresses ADD COLUMN sync_state INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE private_addresses ADD COLUMN marker INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE private_addresses ADD COLUMN source TEXT");
        db.execSQL("ALTER TABLE threads ADD COLUMN sync_state INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE threads ADD COLUMN marker INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE threads ADD COLUMN source TEXT");
        MmsSmsUtils.clearCommonMarkers(this.mContext);
    }

    private void upgradeDatabaseToVersionMSim77(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE threads ADD COLUMN last_sim_id INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersionSim77(SQLiteDatabase db) {
        Iterator i$ = MmsSmsUtils.queryLongValuesToSet(db, MmsSmsProvider.TABLE_THREADS, "_id", "error=1", null).iterator();
        while (i$.hasNext()) {
            updateThreadErrorState(db, ((Long) i$.next()).longValue());
        }
    }

    private void upgradeDatabaseToVersionMSim78(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE private_addresses ADD COLUMN deleted INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE private_addresses ADD COLUMN sync_state INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE private_addresses ADD COLUMN marker INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE private_addresses ADD COLUMN source TEXT");
        db.execSQL("ALTER TABLE threads ADD COLUMN sync_state INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE threads ADD COLUMN marker INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE threads ADD COLUMN source TEXT");
        MmsSmsUtils.clearCommonMarkers(this.mContext);
        Iterator i$ = MmsSmsUtils.queryLongValuesToSet(db, MmsSmsProvider.TABLE_THREADS, "_id", "error=1", null).iterator();
        while (i$.hasNext()) {
            updateThreadErrorState(db, ((Long) i$.next()).longValue());
        }
    }

    private void upgradeDatabaseToVersionSim78(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sim_cards RENAME TO old_sim_cards;");
        db.execSQL("CREATE TABLE sim_cards (_id INTEGER PRIMARY KEY,sim_id INTEGER NOT NULL DEFAULT 0,number TEXT,bind_id INTEGER NOT NULL DEFAULT 0,marker1 INTEGER NOT NULL DEFAULT 0,marker2 INTEGER NOT NULL DEFAULT 0);");
        db.execSQL("INSERT INTO sim_cards (number, bind_id, marker1, marker2) SELECT number, bind_id, marker1, marker2 FROM old_sim_cards;");
        db.execSQL("DROP TABLE old_sim_cards;");
    }

    private void upgradeDatabaseToVersionMSim79(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sim_cards RENAME TO old_sim_cards;");
        db.execSQL("CREATE TABLE sim_cards (_id INTEGER PRIMARY KEY,slot INTEGER NOT NULL DEFAULT 0,sim_id INTEGER NOT NULL DEFAULT 0,number TEXT,bind_id INTEGER NOT NULL DEFAULT 0,marker1 INTEGER NOT NULL DEFAULT 0,marker2 INTEGER NOT NULL DEFAULT 0);");
        db.execSQL("INSERT INTO sim_cards (number, bind_id, marker1, marker2, slot) SELECT number, bind_id, marker1, marker2, slot FROM old_sim_cards;");
        db.execSQL("DROP TABLE old_sim_cards;");
    }

    private void upgradeDatabaseToVersionSim79(SQLiteDatabase db) {
        ContentValues v = new ContentValues();
        v.put("preview_type", Integer.valueOf(0));
        v.putNull(Conversations.SNIPPET);
        v.putNull("preview_data");
        db.update("pdu", v, null, null);
    }

    private void upgradeDatabaseToVersionMSim80(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE raw ADD COLUMN sim_id INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersionSim80(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms RENAME TO old_sms;");
        db.execSQL("ALTER TABLE pdu RENAME TO old_pdu;");
        db.execSQL("CREATE TABLE sms (_id INTEGER PRIMARY KEY,thread_id INTEGER,address TEXT,person INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,protocol INTEGER,read INTEGER DEFAULT 0,status INTEGER DEFAULT -1,type INTEGER,reply_path_present INTEGER,subject TEXT,body TEXT,service_center TEXT,locked INTEGER DEFAULT 0,error_code INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,timed INTEGER DEFAULT 0,deleted INTEGER DEFAULT 0,sync_state INTEGER DEFAULT 0,marker INTEGER DEFAULT 0,source TEXT,bind_id INTEGER DEFAULT 0,mx_status INTEGER DEFAULT 0,mx_id INTEGER,out_time INTEGER DEFAULT 0,account TEXT,sim_id INTEGER DEFAULT 0,block_type INTEGER DEFAULT 0);");
        db.execSQL("CREATE TABLE pdu (_id INTEGER PRIMARY KEY,thread_id INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,msg_box INTEGER,read INTEGER DEFAULT 0,m_id TEXT,sub TEXT,sub_cs INTEGER,ct_t TEXT,ct_l TEXT,exp INTEGER,m_cls TEXT,m_type INTEGER,v INTEGER,m_size INTEGER,pri INTEGER,rr INTEGER,rpt_a INTEGER,resp_st INTEGER,st INTEGER,tr_id TEXT,retr_st INTEGER,retr_txt TEXT,retr_txt_cs INTEGER,read_status INTEGER,ct_cls INTEGER,resp_txt TEXT,d_tm INTEGER,d_rpt INTEGER,locked INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,timed INTEGER DEFAULT 0,date_ms_part INTEGER DEFAULT 0,deleted INTEGER DEFAULT 0,sync_state INTEGER DEFAULT 0,marker INTEGER DEFAULT 0,source TEXT,bind_id INTEGER DEFAULT 0,mx_status INTEGER DEFAULT 0,mx_id INTEGER,file_id TEXT,need_download INTEGER DEFAULT 0,account TEXT,out_time INTEGER DEFAULT 0,snippet TEXT,preview_type INTEGER DEFAULT 0,preview_data BLOB,preview_data_ts INTEGER DEFAULT 0,sim_id INTEGER DEFAULT 0,block_type INTEGER DEFAULT 0);");
        db.execSQL("INSERT INTO sms (_id, thread_id, address, person, date, date_sent, protocol, read, status, type, reply_path_present, subject, body, service_center, locked, error_code, seen, timed, deleted, sync_state, marker, source, bind_id, mx_status, mx_id, out_time, account) SELECT _id, thread_id, address, person, date, date_sent, protocol, read, status, type, reply_path_present, subject, body, service_center, locked, error_code, seen, timed, deleted, sync_state, marker, source, bind_id, mx_status, mx_id, out_time, account FROM old_sms;");
        db.execSQL("INSERT INTO pdu (_id, thread_id, date, date_sent, msg_box, read, m_id, sub, sub_cs, ct_t, ct_l, exp, m_cls, m_type, v, m_size, pri, rr, rpt_a, resp_st, st, tr_id, retr_st, retr_txt, retr_txt_cs, read_status, ct_cls, resp_txt, d_tm, d_rpt, locked, seen, timed, date_ms_part, deleted, sync_state, marker, source, bind_id, mx_status, mx_id, file_id, need_download, account, out_time, snippet, preview_type, preview_data, preview_data_ts)SELECT _id, thread_id, date, date_sent, msg_box, read, m_id, sub, sub_cs, ct_t, ct_l, exp, m_cls, m_type, v, m_size, pri, rr, rpt_a, resp_st, st, tr_id, retr_st, retr_txt, retr_txt_cs, read_status, ct_cls, resp_txt, d_tm, d_rpt, locked, seen, timed, date_ms_part, deleted, sync_state, marker, source, bind_id, mx_status, mx_id, file_id, need_download, account, out_time, snippet, preview_type, preview_data, preview_data_ts FROM old_pdu;");
        db.execSQL("DROP TABLE old_sms;");
        db.execSQL("DROP TABLE old_pdu;");
        db.execSQL("CREATE TABLE blocked_threads (_id INTEGER PRIMARY KEY AUTOINCREMENT,recipient_ids TEXT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,unread_count INTEGER DEFAULT 0,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,last_sim_id INTEGER DEFAULT 0,type INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0);");
    }

    private void upgradeDatabaseToVersionMSim81(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sim_cards RENAME TO old_sim_cards;");
        db.execSQL("CREATE TABLE sim_cards (_id INTEGER PRIMARY KEY,sim_id INTEGER NOT NULL DEFAULT 0,number TEXT,bind_id INTEGER NOT NULL DEFAULT 0,marker1 INTEGER NOT NULL DEFAULT 0,marker2 INTEGER NOT NULL DEFAULT 0);");
        db.execSQL("INSERT INTO sim_cards (sim_id, number, bind_id, marker1, marker2) SELECT sim_id, number, bind_id, marker1, marker2 FROM old_sim_cards;");
        db.execSQL("DROP TABLE old_sim_cards;");
    }

    private void upgradeDatabaseToVersionSim81(SQLiteDatabase db) {
        try {
            db.execSQL("ALTER TABLE pdu ADD COLUMN sim_id INTEGER DEFAULT 0");
        } catch (SQLiteException e) {
        }
        try {
            db.execSQL("ALTER TABLE sms ADD COLUMN sim_id INTEGER DEFAULT 0");
        } catch (SQLiteException e2) {
        }
        db.execSQL("ALTER TABLE threads ADD COLUMN last_sim_id INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE raw ADD COLUMN sim_id INTEGER DEFAULT 0");
        try {
            db.execSQL("ALTER TABLE sim_cards ADD COLUMN sim_id INTEGER NOT NULL DEFAULT 0");
        } catch (SQLiteException e3) {
        }
    }

    private void upgradeDatabaseToVersionMSim82(SQLiteDatabase db) {
        ContentValues v = new ContentValues();
        v.put("preview_type", Integer.valueOf(0));
        v.putNull(Conversations.SNIPPET);
        v.putNull("preview_data");
        db.update("pdu", v, null, null);
    }

    private void upgradeDatabaseToVersionSim82(SQLiteDatabase db) {
        try {
            db.execSQL("ALTER TABLE blocked_threads ADD COLUMN last_sim_id INTEGER DEFAULT 0");
        } catch (SQLiteException ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    private void upgradeDatabaseToVersionMSim83(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE sms RENAME TO old_sms;");
        db.execSQL("ALTER TABLE pdu RENAME TO old_pdu;");
        db.execSQL("CREATE TABLE sms (_id INTEGER PRIMARY KEY,thread_id INTEGER,address TEXT,person INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,protocol INTEGER,read INTEGER DEFAULT 0,status INTEGER DEFAULT -1,type INTEGER,reply_path_present INTEGER,subject TEXT,body TEXT,service_center TEXT,locked INTEGER DEFAULT 0,error_code INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,timed INTEGER DEFAULT 0,deleted INTEGER DEFAULT 0,sync_state INTEGER DEFAULT 0,marker INTEGER DEFAULT 0,source TEXT,bind_id INTEGER DEFAULT 0,mx_status INTEGER DEFAULT 0,mx_id INTEGER,out_time INTEGER DEFAULT 0,account TEXT,sim_id INTEGER DEFAULT 0,block_type INTEGER DEFAULT 0);");
        db.execSQL("CREATE TABLE pdu (_id INTEGER PRIMARY KEY,thread_id INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,msg_box INTEGER,read INTEGER DEFAULT 0,m_id TEXT,sub TEXT,sub_cs INTEGER,ct_t TEXT,ct_l TEXT,exp INTEGER,m_cls TEXT,m_type INTEGER,v INTEGER,m_size INTEGER,pri INTEGER,rr INTEGER,rpt_a INTEGER,resp_st INTEGER,st INTEGER,tr_id TEXT,retr_st INTEGER,retr_txt TEXT,retr_txt_cs INTEGER,read_status INTEGER,ct_cls INTEGER,resp_txt TEXT,d_tm INTEGER,d_rpt INTEGER,locked INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,timed INTEGER DEFAULT 0,date_ms_part INTEGER DEFAULT 0,deleted INTEGER DEFAULT 0,sync_state INTEGER DEFAULT 0,marker INTEGER DEFAULT 0,source TEXT,bind_id INTEGER DEFAULT 0,mx_status INTEGER DEFAULT 0,mx_id INTEGER,file_id TEXT,need_download INTEGER DEFAULT 0,account TEXT,out_time INTEGER DEFAULT 0,snippet TEXT,preview_type INTEGER DEFAULT 0,preview_data BLOB,preview_data_ts INTEGER DEFAULT 0,sim_id INTEGER DEFAULT 0,block_type INTEGER DEFAULT 0);");
        db.execSQL("INSERT INTO sms (_id, thread_id, address, person, date, date_sent, protocol, read, status, type, reply_path_present, subject, body, service_center, locked, error_code, seen, timed, deleted, sync_state, marker, source, bind_id, mx_status, mx_id, out_time, account, sim_id) SELECT _id, thread_id, address, person, date, date_sent, protocol, read, status, type, reply_path_present, subject, body, service_center, locked, error_code, seen, timed, deleted, sync_state, marker, source, bind_id, mx_status, mx_id, out_time, account, sim_id FROM old_sms;");
        db.execSQL("INSERT INTO pdu (_id, thread_id, date, date_sent, msg_box, read, m_id, sub, sub_cs, ct_t, ct_l, exp, m_cls, m_type, v, m_size, pri, rr, rpt_a, resp_st, st, tr_id, retr_st, retr_txt, retr_txt_cs, read_status, ct_cls, resp_txt, d_tm, d_rpt, locked, seen, timed, date_ms_part, deleted, sync_state, marker, source, bind_id, mx_status, mx_id, file_id, need_download, account, out_time, snippet, preview_type, preview_data, preview_data_ts, sim_id)SELECT _id, thread_id, date, date_sent, msg_box, read, m_id, sub, sub_cs, ct_t, ct_l, exp, m_cls, m_type, v, m_size, pri, rr, rpt_a, resp_st, st, tr_id, retr_st, retr_txt, retr_txt_cs, read_status, ct_cls, resp_txt, d_tm, d_rpt, locked, seen, timed, date_ms_part, deleted, sync_state, marker, source, bind_id, mx_status, mx_id, file_id, need_download, account, out_time, snippet, preview_type, preview_data, preview_data_ts, sim_id FROM old_pdu;");
        db.execSQL("DROP TABLE old_sms;");
        db.execSQL("DROP TABLE old_pdu;");
        db.execSQL("CREATE TABLE blocked_threads (_id INTEGER PRIMARY KEY AUTOINCREMENT,recipient_ids TEXT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,unread_count INTEGER DEFAULT 0,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,last_sim_id INTEGER DEFAULT 0,type INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0);");
    }

    private void upgradeDatabaseToVersionSim83(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE threads ADD COLUMN sp_type INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersionMSim84(SQLiteDatabase db) {
        try {
            db.execSQL("ALTER TABLE threads ADD COLUMN sp_type INTEGER DEFAULT 0");
        } catch (SQLiteException e) {
        }
    }

    private void upgradeDatabaseToVersionSim84(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE pdu ADD COLUMN advanced_seen INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE sms ADD COLUMN advanced_seen INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersionMSim85(SQLiteDatabase db) {
        try {
            db.execSQL("ALTER TABLE pdu ADD COLUMN advanced_seen INTEGER DEFAULT 0");
        } catch (SQLiteException e) {
        }
        try {
            db.execSQL("ALTER TABLE sms ADD COLUMN advanced_seen INTEGER DEFAULT 0");
        } catch (SQLiteException e2) {
        }
    }

    private void upgradeDatabaseToVersionSim85(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE threads ADD COLUMN mx_seq TEXT");
        db.execSQL("ALTER TABLE pdu ADD COLUMN mx_type INTEGER DEFAULT 0");
        db.execSQL("ALTER TABLE pdu ADD COLUMN mx_extension TEXT");
    }

    private void upgradeDatabaseToVersionMSim86(SQLiteDatabase db) {
        try {
            db.execSQL("ALTER TABLE threads ADD COLUMN mx_seq TEXT");
        } catch (SQLiteException e) {
        }
        try {
            db.execSQL("ALTER TABLE pdu ADD COLUMN mx_type INTEGER DEFAULT 0");
        } catch (SQLiteException e2) {
        }
        try {
            db.execSQL("ALTER TABLE pdu ADD COLUMN mx_extension TEXT");
        } catch (SQLiteException e3) {
        }
        db.execSQL("CREATE TABLE IF NOT EXISTS hms (_id INTEGER PRIMARY KEY,thread_id INTEGER,type INTEGER DEFAULT 0,date INTEGER,address TEXT,read INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,snippet TEXT,mx_type INTEGER,mx_extension TEXT,advanced_seen INTEGER DEFAULT 0);");
    }

    private void upgradeDatabaseToVersionSim86(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS hms (_id INTEGER PRIMARY KEY,thread_id INTEGER,type INTEGER DEFAULT 0,date INTEGER,address TEXT,read INTEGER DEFAULT 0,seen INTEGER DEFAULT 0,snippet TEXT,mx_type INTEGER,mx_extension TEXT,advanced_seen INTEGER DEFAULT 0);");
    }

    private boolean isMSimUpgrade() {
        if (Build.IS_HONGMI_TWO || Build.IS_HONGMI_TWO_S || Build.IS_HONGMI_THREE) {
            return true;
        }
        return false;
    }
}
