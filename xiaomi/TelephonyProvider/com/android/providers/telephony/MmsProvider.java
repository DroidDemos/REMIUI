package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.FileObserver;
import android.os.ParcelFileDescriptor;
import android.provider.MiuiSettings.Secure;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import android.util.Log;
import com.android.common.Search;
import com.android.providers.telephony.FirewallDatabaseHelper.TABLE;
import com.android.providers.telephony.MmsSmsUtils.OperationPerfProfiler;
import com.google.android.collect.Sets;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.SmsCb.Conversations;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.ThreadSettings;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;
import miui.provider.yellowpage.YellowPageUtils.TagYellowPage;

public class MmsProvider extends ContentProvider {
    private static final boolean DEBUG = false;
    private static final boolean LOCAL_LOGV = false;
    private static final int MMS_ALL = 0;
    private static final int MMS_ALL_ID = 1;
    private static final int MMS_ALL_PART = 10;
    private static final int MMS_DRAFTS = 6;
    private static final int MMS_DRAFTS_ID = 7;
    private static final int MMS_DRM_STORAGE = 17;
    private static final int MMS_DRM_STORAGE_ID = 18;
    private static final int MMS_INBOX = 2;
    private static final int MMS_INBOX_ID = 3;
    private static final int MMS_MSG_ADDR = 13;
    private static final int MMS_MSG_PART = 11;
    private static final int MMS_OUTBOX = 8;
    private static final int MMS_OUTBOX_ID = 9;
    static final int MMS_PARTNAME_LENGTH_LIMIT = 100;
    private static final int MMS_PART_ID = 12;
    private static final int MMS_REPORT_REQUEST = 16;
    private static final int MMS_REPORT_STATUS = 15;
    private static final int MMS_REPORT_STATUS_EXT = 21;
    private static final int MMS_SEEN = 20;
    private static final int MMS_SENDING_RATE = 14;
    private static final int MMS_SENT = 4;
    private static final int MMS_SENT_ID = 5;
    private static final int MMS_THREADS = 19;
    private static final HashSet<String> SYNC_COLUMNS;
    static final String TABLE_ADDR = "addr";
    static final String TABLE_DRM = "drm";
    static final String TABLE_PART = "part";
    static final String TABLE_PDU = "pdu";
    static final String TABLE_RATE = "rate";
    static final String TABLE_WORDS = "words";
    private static final String TAG = "MmsProvider";
    static final HashSet<String> THREAD_AFFECTING_ROWS;
    private static final String VND_ANDROID_DIR_MMS = "vnd.android-dir/mms";
    private static final String VND_ANDROID_MMS = "vnd.android/mms";
    private static final UriMatcher sURLMatcher;
    private MmsSmsDatabaseHelper mOpenHelper;

    public int update(android.net.Uri r51, android.content.ContentValues r52, java.lang.String r53, java.lang.String[] r54) {
        /* JADX: method processing error */
/*
        Error: java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:2219)
	at java.util.ArrayList.grow(ArrayList.java:242)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:216)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:208)
	at java.util.ArrayList.add(ArrayList.java:440)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:463)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
	at jadx.core.utils.BlockUtils.collectWhileDominates(BlockUtils.java:464)
*/
        /*
        r50 = this;
        if (r52 == 0) goto L_0x0011;
    L_0x0002:
        r46 = "_data";
        r0 = r52;
        r1 = r46;
        r46 = r0.containsKey(r1);
        if (r46 == 0) goto L_0x0011;
    L_0x000e:
        r16 = 0;
    L_0x0010:
        return r16;
    L_0x0011:
        com.android.providers.telephony.MmsSmsUtils.setPreviousOpTime();
        r32 = new com.android.providers.telephony.MmsSmsUtils$OperationPerfProfiler;
        r46 = new java.lang.StringBuilder;
        r46.<init>();
        r47 = "update ";
        r46 = r46.append(r47);
        r0 = r46;
        r1 = r51;
        r46 = r0.append(r1);
        r47 = " with selection ";
        r46 = r46.append(r47);
        r0 = r46;
        r1 = r53;
        r46 = r0.append(r1);
        r46 = r46.toString();
        r0 = r32;
        r1 = r46;
        r0.<init>(r1);
        r14 = com.android.providers.telephony.MmsSmsUtils.callerIsSyncAdapter(r51);
        r46 = com.android.providers.telephony.MmsSmsUtils.hasBlockedFlag(r51);
        if (r46 != 0) goto L_0x0058;
    L_0x004c:
        r46 = "block_type";
        r0 = r52;
        r1 = r46;
        r46 = r0.containsKey(r1);
        if (r46 == 0) goto L_0x0089;
    L_0x0058:
        r11 = 1;
    L_0x0059:
        if (r14 != 0) goto L_0x008b;
    L_0x005b:
        if (r11 != 0) goto L_0x008b;
    L_0x005d:
        r46 = "deleted";
        r0 = r52;
        r1 = r46;
        r46 = r0.containsKey(r1);
        if (r46 != 0) goto L_0x0081;
    L_0x0069:
        r46 = "sync_state";
        r0 = r52;
        r1 = r46;
        r46 = r0.containsKey(r1);
        if (r46 != 0) goto L_0x0081;
    L_0x0075:
        r46 = "need_download";
        r0 = r52;
        r1 = r46;
        r46 = r0.containsKey(r1);
        if (r46 == 0) goto L_0x008b;
    L_0x0081:
        r46 = new java.lang.IllegalArgumentException;
        r47 = "The non-sync-callers should not specify DELETED, SYNC_STATE OR NEED_DOWNLOAD in values.";
        r46.<init>(r47);
        throw r46;
    L_0x0089:
        r11 = 0;
        goto L_0x0059;
    L_0x008b:
        r46 = sURLMatcher;
        r0 = r46;
        r1 = r51;
        r26 = r0.match(r1);
        r30 = 0;
        r28 = 0;
        r35 = 0;
        r19 = 0;
        switch(r26) {
            case 0: goto L_0x00ca;
            case 1: goto L_0x00c6;
            case 2: goto L_0x00ca;
            case 3: goto L_0x00c6;
            case 4: goto L_0x00ca;
            case 5: goto L_0x00c6;
            case 6: goto L_0x00ca;
            case 7: goto L_0x00c6;
            case 8: goto L_0x00ca;
            case 9: goto L_0x00c6;
            case 10: goto L_0x00a0;
            case 11: goto L_0x02ef;
            case 12: goto L_0x02ef;
            case 13: goto L_0x00a0;
            case 14: goto L_0x00a0;
            case 15: goto L_0x00a0;
            case 16: goto L_0x00a0;
            case 17: goto L_0x00a0;
            case 18: goto L_0x00a0;
            case 19: goto L_0x00a0;
            case 20: goto L_0x02f3;
            default: goto L_0x00a0;
        };
    L_0x00a0:
        r46 = "MmsProvider";
        r47 = new java.lang.StringBuilder;
        r47.<init>();
        r48 = "Update operation for '";
        r47 = r47.append(r48);
        r0 = r47;
        r1 = r51;
        r47 = r0.append(r1);
        r48 = "' not implemented.";
        r47 = r47.append(r48);
        r47 = r47.toString();
        android.util.Log.w(r46, r47);
        r16 = 0;
        goto L_0x0010;
    L_0x00c6:
        r28 = r51.getLastPathSegment();
    L_0x00ca:
        r30 = 1;
        r35 = "pdu";
    L_0x00ce:
        r0 = r50;
        r0 = r0.mOpenHelper;
        r46 = r0;
        r5 = r46.getWritableDatabase();
        r16 = 0;
        r46 = "pdu";
        r0 = r35;
        r1 = r46;
        r46 = r0.equals(r1);
        if (r46 == 0) goto L_0x05ed;
    L_0x00e6:
        r0 = r50;
        r1 = r52;
        r0.filterUnsupportedKeys(r1);
        r21 = new android.content.ContentValues;
        r0 = r21;
        r1 = r52;
        r0.<init>(r1);
        r46 = "read";
        r0 = r21;
        r1 = r46;
        r33 = r0.getAsInteger(r1);
        if (r33 == 0) goto L_0x012a;
    L_0x0102:
        r46 = r33.intValue();
        r47 = 1;
        r0 = r46;
        r1 = r47;
        if (r0 != r1) goto L_0x012a;
    L_0x010e:
        r46 = "seen";
        r0 = r21;
        r1 = r46;
        r2 = r33;
        r0.put(r1, r2);
        r46 = "advanced_seen";
        r47 = 3;
        r47 = java.lang.Integer.valueOf(r47);
        r0 = r21;
        r1 = r46;
        r2 = r47;
        r0.put(r1, r2);
    L_0x012a:
        if (r14 == 0) goto L_0x037e;
    L_0x012c:
        r46 = "sync_state";
        r0 = r21;
        r1 = r46;
        r46 = r0.containsKey(r1);
        if (r46 != 0) goto L_0x0149;
    L_0x0138:
        r46 = "sync_state";
        r47 = 2;
        r47 = java.lang.Integer.valueOf(r47);
        r0 = r21;
        r1 = r46;
        r2 = r47;
        r0.put(r1, r2);
    L_0x0149:
        r46 = "block_type";
        r0 = r21;
        r1 = r46;
        r46 = r0.containsKey(r1);
        if (r46 == 0) goto L_0x0190;
    L_0x0155:
        r46 = "block_type";
        r0 = r21;
        r1 = r46;
        r46 = r0.getAsInteger(r1);
        r13 = r46.intValue();
        r46 = 1;
        r0 = r46;
        if (r13 <= r0) goto L_0x03c1;
    L_0x0169:
        r46 = "deleted";
        r47 = 1;
        r47 = java.lang.Integer.valueOf(r47);
        r0 = r21;
        r1 = r46;
        r2 = r47;
        r0.put(r1, r2);
        r46 = r50.getContext();
        r0 = r46;
        r1 = r51;
        r46 = com.android.providers.telephony.MmsSmsUtils.inPrivateWhiteList(r0, r1);
        if (r46 == 0) goto L_0x03b1;
    L_0x0188:
        r0 = r51;
        r1 = r53;
        r53 = com.android.providers.telephony.MmsSmsUtils.getSelectionByPrivatePermission(r0, r1);
    L_0x0190:
        if (r11 == 0) goto L_0x019c;
    L_0x0192:
        r46 = "msg_box=1";
        r0 = r53;
        r1 = r46;
        r53 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);
    L_0x019c:
        r0 = r50;
        r1 = r21;
        r0.crackFullDateValues(r1);
        r10 = com.android.providers.telephony.MmsSmsUtils.getAddressList(r21);
        r46 = "addresses";
        r0 = r21;
        r1 = r46;
        r0.remove(r1);
        r46 = "error_type";
        r0 = r21;
        r1 = r46;
        r0.remove(r1);
        r20 = r53;
        r12 = 0;
        r46 = r50.getContext();
        r0 = r46;
        r1 = r51;
        r46 = com.android.providers.telephony.MmsSmsUtils.inBlockedWhiteList(r0, r1);
        if (r46 == 0) goto L_0x03d4;
    L_0x01ca:
        r12 = com.android.providers.telephony.MmsSmsUtils.getSelectionByBlockedPermission(r51);
    L_0x01ce:
        r46 = android.text.TextUtils.isEmpty(r12);
        if (r46 == 0) goto L_0x03e2;
    L_0x01d4:
        if (r14 != 0) goto L_0x01e0;
    L_0x01d6:
        r46 = "deleted=0";
        r0 = r20;
        r1 = r46;
        r20 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);
    L_0x01e0:
        if (r28 == 0) goto L_0x0201;
    L_0x01e2:
        r46 = new java.lang.StringBuilder;
        r46.<init>();
        r47 = "_id=";
        r46 = r46.append(r47);
        r0 = r46;
        r1 = r28;
        r46 = r0.append(r1);
        r46 = r46.toString();
        r0 = r20;
        r1 = r46;
        r20 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);
    L_0x0201:
        r5.beginTransaction();
        r46 = "pdu";	 Catch:{ all -> 0x02ea }
        r47 = "DISTINCT thread_id";	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        r2 = r20;	 Catch:{ all -> 0x02ea }
        r3 = r54;	 Catch:{ all -> 0x02ea }
        r36 = com.android.providers.telephony.MmsSmsUtils.queryLongValuesToSet(r5, r0, r1, r2, r3);	 Catch:{ all -> 0x02ea }
        r29 = 0;	 Catch:{ all -> 0x02ea }
        r46 = "msg_box";	 Catch:{ all -> 0x02ea }
        r0 = r52;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r27 = r0.getAsInteger(r1);	 Catch:{ all -> 0x02ea }
        r31 = 0;	 Catch:{ all -> 0x02ea }
        if (r27 == 0) goto L_0x0273;	 Catch:{ all -> 0x02ea }
    L_0x0224:
        r46 = r27.intValue();	 Catch:{ all -> 0x02ea }
        r47 = 4;	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        if (r0 != r1) goto L_0x0273;	 Catch:{ all -> 0x02ea }
    L_0x0230:
        r46 = "error_type";	 Catch:{ all -> 0x02ea }
        r0 = r52;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r17 = r0.getAsInteger(r1);	 Catch:{ all -> 0x02ea }
        if (r17 == 0) goto L_0x0273;	 Catch:{ all -> 0x02ea }
    L_0x023c:
        r46 = "pdu";	 Catch:{ all -> 0x02ea }
        r47 = "_id";	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        r2 = r20;	 Catch:{ all -> 0x02ea }
        r3 = r54;	 Catch:{ all -> 0x02ea }
        r29 = com.android.providers.telephony.MmsSmsUtils.queryLongValuesToSet(r5, r0, r1, r2, r3);	 Catch:{ all -> 0x02ea }
        r31 = new android.content.ContentValues;	 Catch:{ all -> 0x02ea }
        r46 = 3;	 Catch:{ all -> 0x02ea }
        r0 = r31;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r0.<init>(r1);	 Catch:{ all -> 0x02ea }
        r46 = "err_type";	 Catch:{ all -> 0x02ea }
        r0 = r31;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r2 = r17;	 Catch:{ all -> 0x02ea }
        r0.put(r1, r2);	 Catch:{ all -> 0x02ea }
        r46 = "proto_type";	 Catch:{ all -> 0x02ea }
        r47 = 1;	 Catch:{ all -> 0x02ea }
        r47 = java.lang.Integer.valueOf(r47);	 Catch:{ all -> 0x02ea }
        r0 = r31;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r2 = r47;	 Catch:{ all -> 0x02ea }
        r0.put(r1, r2);	 Catch:{ all -> 0x02ea }
    L_0x0273:
        r46 = "pdu";	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r21;	 Catch:{ all -> 0x02ea }
        r2 = r20;	 Catch:{ all -> 0x02ea }
        r3 = r54;	 Catch:{ all -> 0x02ea }
        r16 = r5.update(r0, r1, r2, r3);	 Catch:{ all -> 0x02ea }
        if (r16 <= 0) goto L_0x05e2;	 Catch:{ all -> 0x02ea }
    L_0x0283:
        if (r10 == 0) goto L_0x028e;	 Catch:{ all -> 0x02ea }
    L_0x0285:
        r0 = r50;	 Catch:{ all -> 0x02ea }
        r1 = r20;	 Catch:{ all -> 0x02ea }
        r2 = r54;	 Catch:{ all -> 0x02ea }
        r0.updateAddresses(r5, r1, r2, r10);	 Catch:{ all -> 0x02ea }
    L_0x028e:
        if (r31 == 0) goto L_0x03ea;	 Catch:{ all -> 0x02ea }
    L_0x0290:
        r22 = r29.iterator();	 Catch:{ all -> 0x02ea }
    L_0x0294:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x03ea;	 Catch:{ all -> 0x02ea }
    L_0x029a:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r23 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r46 = "msg_id";	 Catch:{ all -> 0x02ea }
        r47 = java.lang.Long.valueOf(r23);	 Catch:{ all -> 0x02ea }
        r0 = r31;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r2 = r47;	 Catch:{ all -> 0x02ea }
        r0.put(r1, r2);	 Catch:{ all -> 0x02ea }
        r46 = "pending_msgs";	 Catch:{ all -> 0x02ea }
        r47 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02ea }
        r47.<init>();	 Catch:{ all -> 0x02ea }
        r48 = "msg_id=";	 Catch:{ all -> 0x02ea }
        r47 = r47.append(r48);	 Catch:{ all -> 0x02ea }
        r0 = r47;	 Catch:{ all -> 0x02ea }
        r1 = r23;	 Catch:{ all -> 0x02ea }
        r47 = r0.append(r1);	 Catch:{ all -> 0x02ea }
        r47 = r47.toString();	 Catch:{ all -> 0x02ea }
        r48 = 0;	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r31;	 Catch:{ all -> 0x02ea }
        r2 = r47;	 Catch:{ all -> 0x02ea }
        r3 = r48;	 Catch:{ all -> 0x02ea }
        r46 = r5.update(r0, r1, r2, r3);	 Catch:{ all -> 0x02ea }
        if (r46 != 0) goto L_0x0294;	 Catch:{ all -> 0x02ea }
    L_0x02dc:
        r46 = "pending_msgs";	 Catch:{ all -> 0x02ea }
        r47 = 0;	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        r2 = r31;	 Catch:{ all -> 0x02ea }
        r5.insert(r0, r1, r2);	 Catch:{ all -> 0x02ea }
        goto L_0x0294;
    L_0x02ea:
        r46 = move-exception;
        r5.endTransaction();
        throw r46;
    L_0x02ef:
        r35 = "part";
        goto L_0x00ce;
    L_0x02f3:
        r35 = "pdu";
        r46 = r51.getPathSegments();
        r47 = 1;
        r15 = r46.get(r47);
        r15 = (java.lang.String) r15;
        java.lang.Integer.parseInt(r15);	 Catch:{ Exception -> 0x0345 }
        r46 = "MmsProvider";	 Catch:{ Exception -> 0x0345 }
        r47 = 2;	 Catch:{ Exception -> 0x0345 }
        r46 = android.util.Log.isLoggable(r46, r47);	 Catch:{ Exception -> 0x0345 }
        if (r46 == 0) goto L_0x0328;	 Catch:{ Exception -> 0x0345 }
    L_0x030e:
        r46 = "MmsProvider";	 Catch:{ Exception -> 0x0345 }
        r47 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0345 }
        r47.<init>();	 Catch:{ Exception -> 0x0345 }
        r48 = "query seen of sms: categoryId=";	 Catch:{ Exception -> 0x0345 }
        r47 = r47.append(r48);	 Catch:{ Exception -> 0x0345 }
        r0 = r47;	 Catch:{ Exception -> 0x0345 }
        r47 = r0.append(r15);	 Catch:{ Exception -> 0x0345 }
        r47 = r47.toString();	 Catch:{ Exception -> 0x0345 }
        android.util.Log.d(r46, r47);	 Catch:{ Exception -> 0x0345 }
    L_0x0328:
        r46 = new java.lang.StringBuilder;
        r46.<init>();
        r47 = "exists (SELECT 1 FROM threads WHERE threads._id=pdu.thread_id AND threads.sp_type=";
        r46 = r46.append(r47);
        r0 = r46;
        r46 = r0.append(r15);
        r47 = ")";
        r46 = r46.append(r47);
        r19 = r46.toString();
        goto L_0x00ce;
    L_0x0345:
        r18 = move-exception;
        r47 = "MmsProvider";
        r46 = new java.lang.StringBuilder;
        r46.<init>();
        r48 = "Bad service category id: ";
        r0 = r46;
        r1 = r48;
        r48 = r0.append(r1);
        r46 = r51.getPathSegments();
        r49 = 1;
        r0 = r46;
        r1 = r49;
        r46 = r0.get(r1);
        r46 = (java.lang.String) r46;
        r0 = r48;
        r1 = r46;
        r46 = r0.append(r1);
        r46 = r46.toString();
        r0 = r47;
        r1 = r46;
        android.util.Log.e(r0, r1);
        r16 = 0;
        goto L_0x0010;
    L_0x037e:
        r46 = r21.keySet();
        r22 = r46.iterator();
    L_0x0386:
        r46 = r22.hasNext();
        if (r46 == 0) goto L_0x0149;
    L_0x038c:
        r25 = r22.next();
        r25 = (java.lang.String) r25;
        r46 = SYNC_COLUMNS;
        r0 = r46;
        r1 = r25;
        r46 = r0.contains(r1);
        if (r46 == 0) goto L_0x0386;
    L_0x039e:
        r46 = "sync_state";
        r47 = 0;
        r47 = java.lang.Integer.valueOf(r47);
        r0 = r21;
        r1 = r46;
        r2 = r47;
        r0.put(r1, r2);
        goto L_0x0149;
    L_0x03b1:
        r46 = r50.getContext();
        r0 = r51;
        r1 = r46;
        r2 = r53;
        r53 = com.android.providers.telephony.MmsSmsUtils.getSelectionByPrivatePermission(r0, r1, r2);
        goto L_0x0190;
    L_0x03c1:
        r46 = "deleted";
        r47 = 0;
        r47 = java.lang.Integer.valueOf(r47);
        r0 = r21;
        r1 = r46;
        r2 = r47;
        r0.put(r1, r2);
        goto L_0x0190;
    L_0x03d4:
        r46 = r50.getContext();
        r0 = r51;
        r1 = r46;
        r12 = com.android.providers.telephony.MmsSmsUtils.getSelectionByBlockedPermission(r0, r1);
        goto L_0x01ce;
    L_0x03e2:
        r0 = r20;
        r20 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r12);
        goto L_0x01e0;
    L_0x03ea:
        r43 = 0;
        r42 = 0;
        r45 = 0;
        r40 = 0;
        r44 = 0;
        r41 = 0;
        r39 = 0;
        r38 = 0;
        r37 = 0;
        r46 = "thread_id";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x0414;	 Catch:{ all -> 0x02ea }
    L_0x0408:
        r43 = 1;	 Catch:{ all -> 0x02ea }
        r42 = 1;	 Catch:{ all -> 0x02ea }
        r45 = 1;	 Catch:{ all -> 0x02ea }
        r40 = 1;	 Catch:{ all -> 0x02ea }
        r44 = 1;	 Catch:{ all -> 0x02ea }
        r41 = 1;	 Catch:{ all -> 0x02ea }
    L_0x0414:
        r46 = "deleted";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x042a;	 Catch:{ all -> 0x02ea }
    L_0x0420:
        r42 = 1;	 Catch:{ all -> 0x02ea }
        r45 = 1;	 Catch:{ all -> 0x02ea }
        r40 = 1;	 Catch:{ all -> 0x02ea }
        r44 = 1;	 Catch:{ all -> 0x02ea }
        r41 = 1;	 Catch:{ all -> 0x02ea }
    L_0x042a:
        r46 = "msg_box";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x043e;	 Catch:{ all -> 0x02ea }
    L_0x0436:
        r42 = 1;	 Catch:{ all -> 0x02ea }
        r45 = 1;	 Catch:{ all -> 0x02ea }
        r40 = 1;	 Catch:{ all -> 0x02ea }
        r41 = 1;	 Catch:{ all -> 0x02ea }
    L_0x043e:
        r46 = "read";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x044e;	 Catch:{ all -> 0x02ea }
    L_0x044a:
        if (r11 != 0) goto L_0x044e;	 Catch:{ all -> 0x02ea }
    L_0x044c:
        r45 = 1;	 Catch:{ all -> 0x02ea }
    L_0x044e:
        r46 = "sub";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 != 0) goto L_0x0466;	 Catch:{ all -> 0x02ea }
    L_0x045a:
        r46 = "sub_cs";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x0468;	 Catch:{ all -> 0x02ea }
    L_0x0466:
        r44 = 1;	 Catch:{ all -> 0x02ea }
    L_0x0468:
        r46 = "date";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x0478;	 Catch:{ all -> 0x02ea }
    L_0x0474:
        r44 = 1;	 Catch:{ all -> 0x02ea }
        r41 = 1;	 Catch:{ all -> 0x02ea }
    L_0x0478:
        r46 = "sim_id";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x0488;	 Catch:{ all -> 0x02ea }
    L_0x0484:
        if (r11 != 0) goto L_0x0488;	 Catch:{ all -> 0x02ea }
    L_0x0486:
        r41 = 1;	 Catch:{ all -> 0x02ea }
    L_0x0488:
        if (r11 == 0) goto L_0x0498;	 Catch:{ all -> 0x02ea }
    L_0x048a:
        r46 = "read";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x04d7;	 Catch:{ all -> 0x02ea }
    L_0x0496:
        r39 = 1;	 Catch:{ all -> 0x02ea }
    L_0x0498:
        r4 = com.android.providers.telephony.BatchModeHelper.getInstance();	 Catch:{ all -> 0x02ea }
        if (r43 == 0) goto L_0x04b7;	 Catch:{ all -> 0x02ea }
    L_0x049e:
        r46 = r50.getContext();	 Catch:{ all -> 0x02ea }
        r47 = "thread_id";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        r47 = r0.getAsLong(r1);	 Catch:{ all -> 0x02ea }
        r47 = r47.longValue();	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        r4.updateThread(r0, r5, r1);	 Catch:{ all -> 0x02ea }
    L_0x04b7:
        if (r42 == 0) goto L_0x04e4;	 Catch:{ all -> 0x02ea }
    L_0x04b9:
        r22 = r36.iterator();	 Catch:{ all -> 0x02ea }
    L_0x04bd:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x04e4;	 Catch:{ all -> 0x02ea }
    L_0x04c3:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r6 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r46 = r50.getContext();	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r4.updateThreadMessageCount(r0, r5, r6);	 Catch:{ all -> 0x02ea }
        goto L_0x04bd;	 Catch:{ all -> 0x02ea }
    L_0x04d7:
        r42 = 1;	 Catch:{ all -> 0x02ea }
        r45 = 1;	 Catch:{ all -> 0x02ea }
        r40 = 1;	 Catch:{ all -> 0x02ea }
        r44 = 1;	 Catch:{ all -> 0x02ea }
        r38 = 1;	 Catch:{ all -> 0x02ea }
        r37 = 1;	 Catch:{ all -> 0x02ea }
        goto L_0x0498;	 Catch:{ all -> 0x02ea }
    L_0x04e4:
        if (r45 == 0) goto L_0x04fe;	 Catch:{ all -> 0x02ea }
    L_0x04e6:
        r22 = r36.iterator();	 Catch:{ all -> 0x02ea }
    L_0x04ea:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x04fe;	 Catch:{ all -> 0x02ea }
    L_0x04f0:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r6 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r4.updateThreadUnreadCount(r5, r6);	 Catch:{ all -> 0x02ea }
        goto L_0x04ea;	 Catch:{ all -> 0x02ea }
    L_0x04fe:
        if (r40 == 0) goto L_0x0518;	 Catch:{ all -> 0x02ea }
    L_0x0500:
        r22 = r36.iterator();	 Catch:{ all -> 0x02ea }
    L_0x0504:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x0518;	 Catch:{ all -> 0x02ea }
    L_0x050a:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r6 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r4.updateThreadErrorState(r5, r6);	 Catch:{ all -> 0x02ea }
        goto L_0x0504;	 Catch:{ all -> 0x02ea }
    L_0x0518:
        if (r44 == 0) goto L_0x0538;	 Catch:{ all -> 0x02ea }
    L_0x051a:
        r22 = r36.iterator();	 Catch:{ all -> 0x02ea }
    L_0x051e:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x0538;	 Catch:{ all -> 0x02ea }
    L_0x0524:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r6 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r46 = r50.getContext();	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r4.updateThreadSnippet(r0, r5, r6);	 Catch:{ all -> 0x02ea }
        goto L_0x051e;	 Catch:{ all -> 0x02ea }
    L_0x0538:
        if (r41 == 0) goto L_0x0552;	 Catch:{ all -> 0x02ea }
    L_0x053a:
        r22 = r36.iterator();	 Catch:{ all -> 0x02ea }
    L_0x053e:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x0552;	 Catch:{ all -> 0x02ea }
    L_0x0544:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r6 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r4.updateThreadLastSimId(r5, r6);	 Catch:{ all -> 0x02ea }
        goto L_0x053e;	 Catch:{ all -> 0x02ea }
    L_0x0552:
        if (r39 == 0) goto L_0x056c;	 Catch:{ all -> 0x02ea }
    L_0x0554:
        r22 = r36.iterator();	 Catch:{ all -> 0x02ea }
    L_0x0558:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x056c;	 Catch:{ all -> 0x02ea }
    L_0x055e:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r6 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r4.updateBlockedThreadUnreadCount(r5, r6);	 Catch:{ all -> 0x02ea }
        goto L_0x0558;	 Catch:{ all -> 0x02ea }
    L_0x056c:
        if (r38 == 0) goto L_0x058a;	 Catch:{ all -> 0x02ea }
    L_0x056e:
        r22 = r36.iterator();	 Catch:{ all -> 0x02ea }
    L_0x0572:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x058a;	 Catch:{ all -> 0x02ea }
    L_0x0578:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r6 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r8 = com.android.providers.telephony.MmsSmsUtils.getAddressIdFromThreadId(r5, r6);	 Catch:{ all -> 0x02ea }
        r4.updateBlockedThread(r5, r6, r8);	 Catch:{ all -> 0x02ea }
        goto L_0x0572;	 Catch:{ all -> 0x02ea }
    L_0x058a:
        if (r37 == 0) goto L_0x05a4;	 Catch:{ all -> 0x02ea }
    L_0x058c:
        r22 = r36.iterator();	 Catch:{ all -> 0x02ea }
    L_0x0590:
        r46 = r22.hasNext();	 Catch:{ all -> 0x02ea }
        if (r46 == 0) goto L_0x05a4;	 Catch:{ all -> 0x02ea }
    L_0x0596:
        r46 = r22.next();	 Catch:{ all -> 0x02ea }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x02ea }
        r6 = r46.longValue();	 Catch:{ all -> 0x02ea }
        r4.updateBlockedThreadLastSimId(r5, r6);	 Catch:{ all -> 0x02ea }
        goto L_0x0590;	 Catch:{ all -> 0x02ea }
    L_0x05a4:
        if (r30 == 0) goto L_0x05b2;	 Catch:{ all -> 0x02ea }
    L_0x05a6:
        r46 = r21.size();	 Catch:{ all -> 0x02ea }
        r47 = 1;	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        if (r0 > r1) goto L_0x05ca;	 Catch:{ all -> 0x02ea }
    L_0x05b2:
        r46 = r21.size();	 Catch:{ all -> 0x02ea }
        r47 = 1;	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        if (r0 != r1) goto L_0x05d3;	 Catch:{ all -> 0x02ea }
    L_0x05be:
        r46 = "sync_state";	 Catch:{ all -> 0x02ea }
        r0 = r21;	 Catch:{ all -> 0x02ea }
        r1 = r46;	 Catch:{ all -> 0x02ea }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x02ea }
        if (r46 != 0) goto L_0x05d3;	 Catch:{ all -> 0x02ea }
    L_0x05ca:
        r46 = r50.getContext();	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        notifyChange(r0, r14);	 Catch:{ all -> 0x02ea }
    L_0x05d3:
        if (r11 == 0) goto L_0x05e2;	 Catch:{ all -> 0x02ea }
    L_0x05d5:
        r46 = r50.getContext();	 Catch:{ all -> 0x02ea }
        r47 = miui.provider.ExtraTelephony.MmsSms.BLOCKED_THREAD_CONTENT_URI;	 Catch:{ all -> 0x02ea }
        r0 = r46;	 Catch:{ all -> 0x02ea }
        r1 = r47;	 Catch:{ all -> 0x02ea }
        r4.notifyChange(r0, r1);	 Catch:{ all -> 0x02ea }
    L_0x05e2:
        r5.setTransactionSuccessful();	 Catch:{ all -> 0x02ea }
        r5.endTransaction();
    L_0x05e8:
        r32.prof();
        goto L_0x0010;
    L_0x05ed:
        r46 = "part";
        r0 = r35;
        r1 = r46;
        r46 = r0.equals(r1);
        if (r46 == 0) goto L_0x0705;
    L_0x05f9:
        r21 = new android.content.ContentValues;
        r0 = r21;
        r1 = r52;
        r0.<init>(r1);
        r20 = r53;
        switch(r26) {
            case 11: goto L_0x066a;
            case 12: goto L_0x069b;
            default: goto L_0x0607;
        };
    L_0x0607:
        r5.beginTransaction();
        r46 = "part";	 Catch:{ all -> 0x0665 }
        r47 = "DISTINCT mid";	 Catch:{ all -> 0x0665 }
        r0 = r46;	 Catch:{ all -> 0x0665 }
        r1 = r47;	 Catch:{ all -> 0x0665 }
        r2 = r20;	 Catch:{ all -> 0x0665 }
        r3 = r54;	 Catch:{ all -> 0x0665 }
        r29 = com.android.providers.telephony.MmsSmsUtils.queryLongValuesToSet(r5, r0, r1, r2, r3);	 Catch:{ all -> 0x0665 }
        r46 = "part";	 Catch:{ all -> 0x0665 }
        r0 = r46;	 Catch:{ all -> 0x0665 }
        r1 = r21;	 Catch:{ all -> 0x0665 }
        r2 = r20;	 Catch:{ all -> 0x0665 }
        r3 = r54;	 Catch:{ all -> 0x0665 }
        r16 = r5.update(r0, r1, r2, r3);	 Catch:{ all -> 0x0665 }
        if (r16 <= 0) goto L_0x06fd;	 Catch:{ all -> 0x0665 }
    L_0x062a:
        r34 = com.android.providers.telephony.MmsSmsUtils.suppressMakingMmsPreview(r51);	 Catch:{ all -> 0x0665 }
        r4 = com.android.providers.telephony.BatchModeHelper.getInstance();	 Catch:{ all -> 0x0665 }
        r22 = r29.iterator();	 Catch:{ all -> 0x0665 }
    L_0x0636:
        r46 = r22.hasNext();	 Catch:{ all -> 0x0665 }
        if (r46 == 0) goto L_0x06cc;	 Catch:{ all -> 0x0665 }
    L_0x063c:
        r46 = r22.next();	 Catch:{ all -> 0x0665 }
        r46 = (java.lang.Long) r46;	 Catch:{ all -> 0x0665 }
        r23 = r46.longValue();	 Catch:{ all -> 0x0665 }
        if (r14 != 0) goto L_0x0652;	 Catch:{ all -> 0x0665 }
    L_0x0648:
        r0 = r23;	 Catch:{ all -> 0x0665 }
        r4.markMmsDirty(r5, r0);	 Catch:{ all -> 0x0665 }
        r0 = r23;	 Catch:{ all -> 0x0665 }
        r4.clearFileId(r5, r0);	 Catch:{ all -> 0x0665 }
    L_0x0652:
        if (r34 != 0) goto L_0x065f;	 Catch:{ all -> 0x0665 }
    L_0x0654:
        r46 = r50.getContext();	 Catch:{ all -> 0x0665 }
        r0 = r46;	 Catch:{ all -> 0x0665 }
        r1 = r23;	 Catch:{ all -> 0x0665 }
        r4.remakeMmsPreview(r0, r5, r1);	 Catch:{ all -> 0x0665 }
    L_0x065f:
        r0 = r23;	 Catch:{ all -> 0x0665 }
        r4.updateThreadHasAttachmentByMsg(r5, r0);	 Catch:{ all -> 0x0665 }
        goto L_0x0636;
    L_0x0665:
        r46 = move-exception;
        r5.endTransaction();
        throw r46;
    L_0x066a:
        r46 = new java.lang.StringBuilder;
        r46.<init>();
        r47 = "mid=";
        r47 = r46.append(r47);
        r46 = r51.getPathSegments();
        r48 = 0;
        r0 = r46;
        r1 = r48;
        r46 = r0.get(r1);
        r46 = (java.lang.String) r46;
        r0 = r47;
        r1 = r46;
        r46 = r0.append(r1);
        r46 = r46.toString();
        r0 = r53;
        r1 = r46;
        r20 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);
        goto L_0x0607;
    L_0x069b:
        r46 = new java.lang.StringBuilder;
        r46.<init>();
        r47 = "_id=";
        r47 = r46.append(r47);
        r46 = r51.getPathSegments();
        r48 = 1;
        r0 = r46;
        r1 = r48;
        r46 = r0.get(r1);
        r46 = (java.lang.String) r46;
        r0 = r47;
        r1 = r46;
        r46 = r0.append(r1);
        r46 = r46.toString();
        r0 = r53;
        r1 = r46;
        r20 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);
        goto L_0x0607;
    L_0x06cc:
        r46 = "mid";	 Catch:{ all -> 0x0665 }
        r0 = r21;	 Catch:{ all -> 0x0665 }
        r1 = r46;	 Catch:{ all -> 0x0665 }
        r46 = r0.containsKey(r1);	 Catch:{ all -> 0x0665 }
        if (r46 == 0) goto L_0x06f2;	 Catch:{ all -> 0x0665 }
    L_0x06d8:
        r46 = "mid";	 Catch:{ all -> 0x0665 }
        r0 = r21;	 Catch:{ all -> 0x0665 }
        r1 = r46;	 Catch:{ all -> 0x0665 }
        r46 = r0.getAsLong(r1);	 Catch:{ all -> 0x0665 }
        r23 = r46.longValue();	 Catch:{ all -> 0x0665 }
        if (r14 != 0) goto L_0x06ed;	 Catch:{ all -> 0x0665 }
    L_0x06e8:
        r0 = r23;	 Catch:{ all -> 0x0665 }
        r4.markMmsDirty(r5, r0);	 Catch:{ all -> 0x0665 }
    L_0x06ed:
        r0 = r23;	 Catch:{ all -> 0x0665 }
        r4.updateThreadHasAttachmentByMsg(r5, r0);	 Catch:{ all -> 0x0665 }
    L_0x06f2:
        if (r30 == 0) goto L_0x06fd;	 Catch:{ all -> 0x0665 }
    L_0x06f4:
        r46 = r50.getContext();	 Catch:{ all -> 0x0665 }
        r0 = r46;	 Catch:{ all -> 0x0665 }
        notifyChange(r0, r14);	 Catch:{ all -> 0x0665 }
    L_0x06fd:
        r5.setTransactionSuccessful();	 Catch:{ all -> 0x0665 }
        r5.endTransaction();
        goto L_0x05e8;
    L_0x0705:
        r16 = 0;
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    static {
        THREAD_AFFECTING_ROWS = new HashSet();
        SYNC_COLUMNS = Sets.newHashSet(new String[]{"_id", Search.SOURCE, "bind_id", "marker", "m_type", WapPush.DATE, "date_ms_part", "date_sent", "sub", "sub_cs", "st", WapPush.THREAD_ID, "deleted", WapPush.READ, WapPush.LOCKED, "file_id", "mx_status", "timed", TABLE.ACCOUNT, "msg_box", WapPush.SIM_ID, "block_type", "mx_type", "mx_extension"});
        sURLMatcher = new UriMatcher(-1);
        sURLMatcher.addURI("mms", null, MMS_ALL);
        sURLMatcher.addURI("mms", "#", MMS_ALL_ID);
        sURLMatcher.addURI("mms", "inbox", MMS_INBOX);
        sURLMatcher.addURI("mms", "inbox/#", MMS_INBOX_ID);
        sURLMatcher.addURI("mms", "sent", MMS_SENT);
        sURLMatcher.addURI("mms", "sent/#", MMS_SENT_ID);
        sURLMatcher.addURI("mms", "drafts", MMS_DRAFTS);
        sURLMatcher.addURI("mms", "drafts/#", MMS_DRAFTS_ID);
        sURLMatcher.addURI("mms", "outbox", MMS_OUTBOX);
        sURLMatcher.addURI("mms", "outbox/#", MMS_OUTBOX_ID);
        sURLMatcher.addURI("mms", TABLE_PART, MMS_ALL_PART);
        sURLMatcher.addURI("mms", "#/part", MMS_MSG_PART);
        sURLMatcher.addURI("mms", "part/#", MMS_PART_ID);
        sURLMatcher.addURI("mms", "#/addr", MMS_MSG_ADDR);
        sURLMatcher.addURI("mms", TABLE_RATE, MMS_SENDING_RATE);
        sURLMatcher.addURI("mms", "report-status/#", MMS_REPORT_STATUS);
        sURLMatcher.addURI("mms", "report-request/#", MMS_REPORT_REQUEST);
        sURLMatcher.addURI("mms", TABLE_DRM, MMS_DRM_STORAGE);
        sURLMatcher.addURI("mms", "drm/#", MMS_DRM_STORAGE_ID);
        sURLMatcher.addURI("mms", MmsSmsProvider.TABLE_THREADS, MMS_THREADS);
        sURLMatcher.addURI("mms", "seen/#", MMS_SEEN);
        sURLMatcher.addURI("mms", "report-status-ext/#", MMS_REPORT_STATUS_EXT);
    }

    public boolean onCreate() {
        Injector.setMmsSmsProviderOps(this);
        this.mOpenHelper = MmsSmsDatabaseHelper.getInstance(getContext());
        return true;
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        BatchModeHelper helper = BatchModeHelper.getInstance();
        helper.beginBatchOps(getContext(), this.mOpenHelper.getWritableDatabase());
        ContentProviderResult[] contentProviderResultArr = null;
        try {
            contentProviderResultArr = super.applyBatch(operations);
            return contentProviderResultArr;
        } finally {
            helper.endBatchOps();
        }
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MmsSmsUtils.setPreviousOpTime();
        OperationPerfProfiler operationPerfProfiler = new OperationPerfProfiler("query " + uri + " with selection " + selection);
        boolean callerIsSyncAdapter = MmsSmsUtils.callerIsSyncAdapter(uri);
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        int match = sURLMatcher.match(uri);
        if (!(match == MMS_PART_ID || match == MMS_INBOX_ID)) {
            Cursor emptyCursor = Secure.checkPrivacyAndReturnCursor(getContext());
            if (emptyCursor != null) {
                return emptyCursor;
            }
        }
        String groupby = null;
        switch (match) {
            case MMS_ALL /*0*/:
                constructQueryForBox(qb, MMS_ALL);
                break;
            case MMS_ALL_ID /*1*/:
                qb.setTables(TABLE_PDU);
                qb.appendWhere("_id=" + ((String) uri.getPathSegments().get(MMS_ALL)));
                break;
            case MMS_INBOX /*2*/:
                constructQueryForBox(qb, MMS_ALL_ID);
                break;
            case MMS_INBOX_ID /*3*/:
            case MMS_SENT_ID /*5*/:
            case MMS_DRAFTS_ID /*7*/:
            case MMS_OUTBOX_ID /*9*/:
                qb.setTables(TABLE_PDU);
                qb.appendWhere("_id=" + ((String) uri.getPathSegments().get(MMS_ALL_ID)));
                qb.appendWhere(" AND msg_box=" + getMessageBoxByMatch(match));
                break;
            case MMS_SENT /*4*/:
                constructQueryForBox(qb, MMS_INBOX);
                break;
            case MMS_DRAFTS /*6*/:
                constructQueryForBox(qb, MMS_INBOX_ID);
                break;
            case MMS_OUTBOX /*8*/:
                constructQueryForBox(qb, MMS_SENT);
                break;
            case MMS_ALL_PART /*10*/:
                qb.setTables(TABLE_PART);
                break;
            case MMS_MSG_PART /*11*/:
                qb.setTables(TABLE_PART);
                qb.appendWhere("mid=" + ((String) uri.getPathSegments().get(MMS_ALL)));
                break;
            case MMS_PART_ID /*12*/:
                qb.setTables(TABLE_PART);
                qb.appendWhere("_id=" + ((String) uri.getPathSegments().get(MMS_ALL_ID)));
                break;
            case MMS_MSG_ADDR /*13*/:
                qb.setTables(TABLE_ADDR);
                qb.appendWhere("msg_id=" + ((String) uri.getPathSegments().get(MMS_ALL)));
                break;
            case MMS_SENDING_RATE /*14*/:
                qb.setTables(TABLE_RATE);
                break;
            case MMS_REPORT_STATUS /*15*/:
                qb.setTables("addr INNER JOIN (SELECT P1._id AS id1, P2._id AS id2, P3._id AS id3, ifnull(P2.st, 0) AS delivery_status, ifnull(P3.read_status, 0) AS read_status FROM pdu P1 INNER JOIN pdu P2 ON P1.m_id=P2.m_id AND P2.m_type=134 LEFT JOIN pdu P3 ON P1.m_id=P3.m_id AND P3.m_type=136 UNION SELECT P1._id AS id1, P2._id AS id2, P3._id AS id3, ifnull(P2.st, 0) AS delivery_status, ifnull(P3.read_status, 0) AS read_status FROM pdu P1 INNER JOIN pdu P3 ON P1.m_id=P3.m_id AND P3.m_type=136 LEFT JOIN pdu P2 ON P1.m_id=P2.m_id AND P2.m_type=134) T ON (msg_id=id2 AND type=151) OR (msg_id=id3 AND type=137)");
                qb.appendWhere("T.id1 = " + uri.getLastPathSegment());
                qb.setDistinct(true);
                break;
            case MMS_REPORT_REQUEST /*16*/:
                qb.setTables("addr join pdu on pdu._id = addr.msg_id");
                qb.appendWhere("pdu._id = " + uri.getLastPathSegment());
                qb.appendWhere(" AND addr.type = 151");
                break;
            case MMS_DRM_STORAGE_ID /*18*/:
                qb.setTables(TABLE_DRM);
                qb.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case MMS_THREADS /*19*/:
                qb.setTables(TABLE_PDU);
                groupby = WapPush.THREAD_ID;
                break;
            case MMS_SEEN /*20*/:
                String categoryId = (String) uri.getPathSegments().get(MMS_ALL_ID);
                try {
                    Integer.parseInt(categoryId);
                    if (Log.isLoggable(TAG, MMS_INBOX)) {
                        Log.d(TAG, "query seen of sms: categoryId=" + categoryId);
                    }
                    qb.setTables(TABLE_PDU);
                    qb.appendWhere("exists (SELECT 1 FROM threads WHERE threads._id=pdu.thread_id AND threads.sp_type=" + categoryId + ")");
                    break;
                } catch (Exception e) {
                    Log.e(TAG, "Bad service category id: " + ((String) uri.getPathSegments().get(MMS_ALL_ID)));
                    return null;
                }
            case MMS_REPORT_STATUS_EXT /*21*/:
                qb.setTables("(SELECT P1._id AS id1, P2._id AS id2, ifnull(P2.st, 0) AS delivery_status FROM pdu P1 INNER JOIN pdu P2 ON P1.m_id=P2.m_id AND P2.m_type=134)");
                qb.appendWhere("id1 = " + uri.getLastPathSegment());
                qb.setDistinct(true);
                break;
            default:
                Log.e(TAG, "query: invalid request: " + uri);
                return null;
        }
        String finalSortOrder = null;
        if (!TextUtils.isEmpty(sortOrder)) {
            finalSortOrder = sortOrder;
        } else if (qb.getTables().equals(TABLE_PDU)) {
            finalSortOrder = "date DESC, date_ms_part DESC";
        } else if (qb.getTables().equals(TABLE_PART)) {
            finalSortOrder = "seq";
        }
        if (qb.getTables().equals(TABLE_PDU)) {
            selection = MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), selection);
            String blockSelection = MmsSmsUtils.getSelectionByBlockedPermission(uri, getContext());
            if (!TextUtils.isEmpty(blockSelection)) {
                selection = MmsSmsUtils.concatenateWhere(selection, blockSelection);
                if (projection != null) {
                    crackFullDateProjection(projection);
                }
            } else if (!callerIsSyncAdapter) {
                selection = MmsSmsUtils.concatenateWhere(selection, "deleted = 0");
                if (projection != null) {
                    crackFullDateProjection(projection);
                }
            }
        }
        Cursor ret = qb.query(this.mOpenHelper.getReadableDatabase(), projection, selection, selectionArgs, groupby, null, finalSortOrder);
        if (ret != null) {
            ret.setNotificationUri(getContext().getContentResolver(), uri);
        }
        operationPerfProfiler.prof();
        return ret;
    }

    private void crackFullDateProjection(String[] projection) {
        for (int i = MMS_ALL; i < projection.length; i += MMS_ALL_ID) {
            if (projection[i].equals("date_full")) {
                projection[i] = "(date*1000+date_ms_part) AS date_full";
            }
        }
    }

    private void constructQueryForBox(SQLiteQueryBuilder qb, int msgBox) {
        qb.setTables(TABLE_PDU);
        if (msgBox != 0) {
            qb.appendWhere("msg_box=" + msgBox);
        }
    }

    public String getType(Uri uri) {
        switch (sURLMatcher.match(uri)) {
            case MMS_ALL /*0*/:
            case MMS_INBOX /*2*/:
            case MMS_SENT /*4*/:
            case MMS_DRAFTS /*6*/:
            case MMS_OUTBOX /*8*/:
                return VND_ANDROID_DIR_MMS;
            case MMS_ALL_ID /*1*/:
            case MMS_INBOX_ID /*3*/:
            case MMS_SENT_ID /*5*/:
            case MMS_DRAFTS_ID /*7*/:
            case MMS_OUTBOX_ID /*9*/:
                return VND_ANDROID_MMS;
            case MMS_PART_ID /*12*/:
                SQLiteDatabase readableDatabase = this.mOpenHelper.getReadableDatabase();
                String str = TABLE_PART;
                String[] strArr = new String[MMS_ALL_ID];
                strArr[MMS_ALL] = "ct";
                String[] strArr2 = new String[MMS_ALL_ID];
                strArr2[MMS_ALL] = uri.getLastPathSegment();
                Cursor cursor = readableDatabase.query(str, strArr, "_id = ?", strArr2, null, null, null);
                if (cursor != null) {
                    try {
                        if (cursor.getCount() == MMS_ALL_ID && cursor.moveToFirst()) {
                            String string = cursor.getString(MMS_ALL);
                            return string;
                        }
                        Log.e(TAG, "cursor.count() != 1: " + uri);
                        cursor.close();
                    } finally {
                        cursor.close();
                    }
                } else {
                    Log.e(TAG, "cursor == null: " + uri);
                }
                return "*/*";
            default:
                return "*/*";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri insert(android.net.Uri r81, android.content.ContentValues r82) {
        /*
        r80 = this;
        if (r82 == 0) goto L_0x000f;
    L_0x0002:
        r4 = "_data";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 == 0) goto L_0x000f;
    L_0x000c:
        r66 = 0;
    L_0x000e:
        return r66;
    L_0x000f:
        com.android.providers.telephony.MmsSmsUtils.setPreviousOpTime();
        r64 = new com.android.providers.telephony.MmsSmsUtils$OperationPerfProfiler;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "insert ";
        r4 = r4.append(r5);
        r5 = r81.toString();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r0 = r64;
        r0.<init>(r4);
        r25 = com.android.providers.telephony.MmsSmsUtils.callerIsSyncAdapter(r81);
        r26 = com.android.providers.telephony.MmsSmsUtils.checkDuplication(r81);
        r4 = "block_type";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 == 0) goto L_0x0079;
    L_0x0042:
        r4 = "block_type";
        r0 = r82;
        r4 = r0.getAsInteger(r4);
        r4 = r4.intValue();
        r5 = 1;
        if (r4 <= r5) goto L_0x0079;
    L_0x0051:
        r43 = 1;
    L_0x0053:
        if (r43 == 0) goto L_0x007c;
    L_0x0055:
        r4 = "block_type";
        r0 = r82;
        r4 = r0.getAsInteger(r4);
        r22 = r4.intValue();
    L_0x0061:
        if (r82 == 0) goto L_0x00a5;
    L_0x0063:
        if (r25 != 0) goto L_0x007f;
    L_0x0065:
        if (r43 != 0) goto L_0x007f;
    L_0x0067:
        r4 = "deleted";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 == 0) goto L_0x007f;
    L_0x0071:
        r4 = new java.lang.IllegalArgumentException;
        r5 = "The non-sync-callers should not specify DELETED=1 for insert";
        r4.<init>(r5);
        throw r4;
    L_0x0079:
        r43 = 0;
        goto L_0x0053;
    L_0x007c:
        r22 = 0;
        goto L_0x0061;
    L_0x007f:
        if (r25 != 0) goto L_0x0093;
    L_0x0081:
        r4 = "sync_state";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 == 0) goto L_0x0093;
    L_0x008b:
        r4 = new java.lang.IllegalArgumentException;
        r5 = "The non-sync-callers should not specify SYNC_STATE in values.";
        r4.<init>(r5);
        throw r4;
    L_0x0093:
        r4 = "need_download";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 == 0) goto L_0x00a5;
    L_0x009d:
        r4 = new java.lang.IllegalArgumentException;
        r5 = "Should not specify NEED_DOWNLOAD in values.";
        r4.<init>(r5);
        throw r4;
    L_0x00a5:
        r4 = "need_full_insert_uri";
        r5 = 0;
        r0 = r81;
        r52 = com.android.providers.telephony.MmsSmsUtils.readBooleanQueryParameter(r0, r4, r5);
        r47 = 0;
        r54 = 1;
        r4 = sURLMatcher;
        r0 = r81;
        r46 = r4.match(r0);
        r73 = "pdu";
        switch(r46) {
            case 0: goto L_0x00dd;
            case 1: goto L_0x00bf;
            case 2: goto L_0x030d;
            case 3: goto L_0x00bf;
            case 4: goto L_0x0311;
            case 5: goto L_0x00bf;
            case 6: goto L_0x0315;
            case 7: goto L_0x00bf;
            case 8: goto L_0x0319;
            case 9: goto L_0x00bf;
            case 10: goto L_0x00bf;
            case 11: goto L_0x031d;
            case 12: goto L_0x00bf;
            case 13: goto L_0x0323;
            case 14: goto L_0x0329;
            case 15: goto L_0x00bf;
            case 16: goto L_0x00bf;
            case 17: goto L_0x032f;
            default: goto L_0x00bf;
        };
    L_0x00bf:
        r4 = "MmsProvider";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "insert: invalid request: ";
        r5 = r5.append(r7);
        r0 = r81;
        r5 = r5.append(r0);
        r5 = r5.toString();
        android.util.Log.e(r4, r5);
        r66 = 0;
        goto L_0x000e;
    L_0x00dd:
        r4 = "msg_box";
        r0 = r82;
        r48 = r0.getAsInteger(r4);
        if (r48 == 0) goto L_0x0309;
    L_0x00e7:
        r48 = (java.lang.Integer) r48;
        r47 = r48.intValue();
    L_0x00ed:
        r0 = r80;
        r4 = r0.mOpenHelper;
        r3 = r4.getWritableDatabase();
        r36 = 0;
        r66 = android.provider.Telephony.Mms.CONTENT_URI;
        r67 = 0;
        r4 = "need_notify";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 == 0) goto L_0x010c;
    L_0x0105:
        r4 = "need_notify";
        r0 = r82;
        r0.remove(r4);
    L_0x010c:
        r4 = "pdu";
        r0 = r73;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0649;
    L_0x0116:
        r41 = "";
        if (r52 == 0) goto L_0x011c;
    L_0x011a:
        r41 = "ignored/";
    L_0x011c:
        r4 = "date";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 != 0) goto L_0x0335;
    L_0x0126:
        r4 = "date_full";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 != 0) goto L_0x0335;
    L_0x0130:
        r17 = 1;
    L_0x0132:
        r4 = "msg_box";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 != 0) goto L_0x0339;
    L_0x013c:
        r18 = 1;
    L_0x013e:
        r0 = r80;
        r1 = r82;
        r0.filterUnsupportedKeys(r1);
        r36 = new android.content.ContentValues;
        r0 = r36;
        r1 = r82;
        r0.<init>(r1);
        r4 = "deleted";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 != 0) goto L_0x0164;
    L_0x0158:
        r4 = "deleted";
        r5 = 0;
        r5 = java.lang.Integer.valueOf(r5);
        r0 = r36;
        r0.put(r4, r5);
    L_0x0164:
        if (r25 == 0) goto L_0x033d;
    L_0x0166:
        r4 = "sync_state";
        r0 = r82;
        r4 = r0.containsKey(r4);
        if (r4 != 0) goto L_0x017c;
    L_0x0170:
        r4 = "sync_state";
        r5 = 2;
        r5 = java.lang.Integer.valueOf(r5);
        r0 = r36;
        r0.put(r4, r5);
    L_0x017c:
        r0 = r80;
        r1 = r36;
        r0.crackFullDateValues(r1);
        if (r17 == 0) goto L_0x01a7;
    L_0x0185:
        r75 = java.lang.System.currentTimeMillis();
        r4 = "date";
        r7 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r7 = r75 / r7;
        r5 = java.lang.Long.valueOf(r7);
        r0 = r36;
        r0.put(r4, r5);
        r4 = "date_ms_part";
        r7 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r7 = r75 % r7;
        r5 = java.lang.Long.valueOf(r7);
        r0 = r36;
        r0.put(r4, r5);
    L_0x01a7:
        if (r18 == 0) goto L_0x01b6;
    L_0x01a9:
        if (r47 == 0) goto L_0x01b6;
    L_0x01ab:
        r4 = "msg_box";
        r5 = java.lang.Integer.valueOf(r47);
        r0 = r36;
        r0.put(r4, r5);
    L_0x01b6:
        r4 = 1;
        r0 = r47;
        if (r0 == r4) goto L_0x01c7;
    L_0x01bb:
        r4 = "read";
        r5 = 1;
        r5 = java.lang.Integer.valueOf(r5);
        r0 = r36;
        r0.put(r4, r5);
    L_0x01c7:
        r4 = "read";
        r0 = r36;
        r65 = r0.getAsInteger(r4);
        if (r65 == 0) goto L_0x01ed;
    L_0x01d1:
        r4 = r65.intValue();
        r5 = 1;
        if (r4 != r5) goto L_0x01ed;
    L_0x01d8:
        r4 = "seen";
        r0 = r36;
        r1 = r65;
        r0.put(r4, r1);
        r4 = "advanced_seen";
        r5 = 3;
        r5 = java.lang.Integer.valueOf(r5);
        r0 = r36;
        r0.put(r4, r5);
    L_0x01ed:
        r4 = "block_type";
        r0 = r36;
        r4 = r0.containsKey(r4);
        if (r4 == 0) goto L_0x0212;
    L_0x01f7:
        r4 = "block_type";
        r0 = r36;
        r4 = r0.getAsInteger(r4);
        r4 = r4.intValue();
        r5 = 1;
        if (r4 <= r5) goto L_0x0212;
    L_0x0206:
        r4 = "deleted";
        r5 = 1;
        r5 = java.lang.Integer.valueOf(r5);
        r0 = r36;
        r0.put(r4, r5);
    L_0x0212:
        r3.beginTransaction();
        r20 = com.android.providers.telephony.MmsSmsUtils.getAddressList(r36);	 Catch:{ all -> 0x0350 }
        r4 = "addresses";
        r0 = r36;
        r0.remove(r4);	 Catch:{ all -> 0x0350 }
        r4 = "error_type";
        r0 = r36;
        r0.remove(r4);	 Catch:{ all -> 0x0350 }
        r4 = "thread_id";
        r0 = r36;
        r74 = r0.getAsLong(r4);	 Catch:{ all -> 0x0350 }
        if (r74 != 0) goto L_0x0251;
    L_0x0231:
        if (r20 == 0) goto L_0x0251;
    L_0x0233:
        r77 = android.os.Binder.clearCallingIdentity();	 Catch:{ all -> 0x0350 }
        r4 = r80.getContext();	 Catch:{ all -> 0x034b }
        r0 = r20;
        r4 = android.provider.Telephony.Threads.getOrCreateThreadId(r4, r0);	 Catch:{ all -> 0x034b }
        r74 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x034b }
        r4 = "thread_id";
        r0 = r36;
        r1 = r74;
        r0.put(r4, r1);	 Catch:{ all -> 0x034b }
        android.os.Binder.restoreCallingIdentity(r77);	 Catch:{ all -> 0x0350 }
    L_0x0251:
        r21 = 0;
        r4 = "m_type";
        r0 = r36;
        r51 = r0.getAsInteger(r4);	 Catch:{ all -> 0x0350 }
        if (r51 == 0) goto L_0x0267;
    L_0x025d:
        r4 = r51.intValue();	 Catch:{ all -> 0x0350 }
        r0 = r80;
        r21 = r0.getAddressTypeFromMsgType(r4);	 Catch:{ all -> 0x0350 }
    L_0x0267:
        if (r26 == 0) goto L_0x09be;
    L_0x0269:
        r23 = 0;
        r4 = "date";
        r0 = r36;
        r29 = r0.getAsLong(r4);	 Catch:{ all -> 0x02fb }
        r4 = "date_ms_part";
        r0 = r36;
        r30 = r0.getAsLong(r4);	 Catch:{ all -> 0x02fb }
        if (r29 == 0) goto L_0x02d4;
    L_0x027d:
        if (r20 == 0) goto L_0x02d4;
    L_0x027f:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02fb }
        r4.<init>();	 Catch:{ all -> 0x02fb }
        r5 = "date=";
        r4 = r4.append(r5);	 Catch:{ all -> 0x02fb }
        r5 = r29.toString();	 Catch:{ all -> 0x02fb }
        r4 = r4.append(r5);	 Catch:{ all -> 0x02fb }
        r6 = r4.toString();	 Catch:{ all -> 0x02fb }
        if (r30 == 0) goto L_0x02b3;
    L_0x0298:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02fb }
        r4.<init>();	 Catch:{ all -> 0x02fb }
        r4 = r4.append(r6);	 Catch:{ all -> 0x02fb }
        r5 = " AND date_ms_part=";
        r4 = r4.append(r5);	 Catch:{ all -> 0x02fb }
        r5 = r30.toString();	 Catch:{ all -> 0x02fb }
        r4 = r4.append(r5);	 Catch:{ all -> 0x02fb }
        r6 = r4.toString();	 Catch:{ all -> 0x02fb }
    L_0x02b3:
        r4 = "pdu";
        r5 = 4;
        r5 = new java.lang.String[r5];	 Catch:{ all -> 0x02fb }
        r7 = 0;
        r8 = "_id";
        r5[r7] = r8;	 Catch:{ all -> 0x02fb }
        r7 = 1;
        r8 = "sync_state";
        r5[r7] = r8;	 Catch:{ all -> 0x02fb }
        r7 = 2;
        r8 = "marker";
        r5[r7] = r8;	 Catch:{ all -> 0x02fb }
        r7 = 3;
        r8 = "deleted";
        r5[r7] = r8;	 Catch:{ all -> 0x02fb }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r23 = r3.query(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ all -> 0x02fb }
    L_0x02d4:
        if (r23 == 0) goto L_0x04fb;
    L_0x02d6:
        r4 = r23.getCount();	 Catch:{ all -> 0x02fb }
        if (r4 <= 0) goto L_0x04fb;
    L_0x02dc:
        r69 = new java.util.HashSet;	 Catch:{ all -> 0x02fb }
        r69.<init>();	 Catch:{ all -> 0x02fb }
        r38 = r20.iterator();	 Catch:{ all -> 0x02fb }
    L_0x02e5:
        r4 = r38.hasNext();	 Catch:{ all -> 0x02fb }
        if (r4 == 0) goto L_0x0354;
    L_0x02eb:
        r19 = r38.next();	 Catch:{ all -> 0x02fb }
        r19 = (java.lang.String) r19;	 Catch:{ all -> 0x02fb }
        r4 = com.android.providers.telephony.MmsSmsUtils.getLast7DigitRev(r19);	 Catch:{ all -> 0x02fb }
        r0 = r69;
        r0.add(r4);	 Catch:{ all -> 0x02fb }
        goto L_0x02e5;
    L_0x02fb:
        r4 = move-exception;
        r9 = r67;
    L_0x02fe:
        if (r23 == 0) goto L_0x0303;
    L_0x0300:
        r23.close();	 Catch:{ all -> 0x0304 }
    L_0x0303:
        throw r4;	 Catch:{ all -> 0x0304 }
    L_0x0304:
        r4 = move-exception;
    L_0x0305:
        r3.endTransaction();
        throw r4;
    L_0x0309:
        r47 = 1;
        goto L_0x00ed;
    L_0x030d:
        r47 = 1;
        goto L_0x00ed;
    L_0x0311:
        r47 = 2;
        goto L_0x00ed;
    L_0x0315:
        r47 = 3;
        goto L_0x00ed;
    L_0x0319:
        r47 = 4;
        goto L_0x00ed;
    L_0x031d:
        r54 = 0;
        r73 = "part";
        goto L_0x00ed;
    L_0x0323:
        r54 = 0;
        r73 = "addr";
        goto L_0x00ed;
    L_0x0329:
        r54 = 0;
        r73 = "rate";
        goto L_0x00ed;
    L_0x032f:
        r54 = 0;
        r73 = "drm";
        goto L_0x00ed;
    L_0x0335:
        r17 = 0;
        goto L_0x0132;
    L_0x0339:
        r18 = 0;
        goto L_0x013e;
    L_0x033d:
        r4 = "sync_state";
        r5 = 0;
        r5 = java.lang.Integer.valueOf(r5);
        r0 = r36;
        r0.put(r4, r5);
        goto L_0x017c;
    L_0x034b:
        r4 = move-exception;
        android.os.Binder.restoreCallingIdentity(r77);	 Catch:{ all -> 0x0350 }
        throw r4;	 Catch:{ all -> 0x0350 }
    L_0x0350:
        r4 = move-exception;
        r9 = r67;
        goto L_0x0305;
    L_0x0354:
        r4 = -1;
        r0 = r23;
        r0.moveToPosition(r4);	 Catch:{ all -> 0x02fb }
    L_0x035a:
        r4 = r23.moveToNext();	 Catch:{ all -> 0x02fb }
        if (r4 == 0) goto L_0x04fb;
    L_0x0360:
        r4 = 0;
        r0 = r23;
        r39 = r0.getLong(r4);	 Catch:{ all -> 0x02fb }
        r24 = 0;
        r57 = new java.util.HashSet;	 Catch:{ all -> 0x03d4 }
        r57.<init>();	 Catch:{ all -> 0x03d4 }
        r8 = "addr";
        r4 = 1;
        r9 = new java.lang.String[r4];	 Catch:{ all -> 0x03d4 }
        r4 = 0;
        r5 = "address";
        r9[r4] = r5;	 Catch:{ all -> 0x03d4 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x03d4 }
        r4.<init>();	 Catch:{ all -> 0x03d4 }
        r5 = "msg_id=";
        r4 = r4.append(r5);	 Catch:{ all -> 0x03d4 }
        r0 = r39;
        r4 = r4.append(r0);	 Catch:{ all -> 0x03d4 }
        r5 = " AND ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x03d4 }
        r5 = "type";
        r4 = r4.append(r5);	 Catch:{ all -> 0x03d4 }
        r5 = "=";
        r4 = r4.append(r5);	 Catch:{ all -> 0x03d4 }
        r0 = r21;
        r4 = r4.append(r0);	 Catch:{ all -> 0x03d4 }
        r10 = r4.toString();	 Catch:{ all -> 0x03d4 }
        r11 = 0;
        r12 = 0;
        r13 = 0;
        r14 = "address";
        r7 = r3;
        r24 = r7.query(r8, r9, r10, r11, r12, r13, r14);	 Catch:{ all -> 0x03d4 }
        if (r24 == 0) goto L_0x03e0;
    L_0x03b1:
        r4 = r24.getCount();	 Catch:{ all -> 0x03d4 }
        if (r4 <= 0) goto L_0x03e0;
    L_0x03b7:
        r4 = -1;
        r0 = r24;
        r0.moveToPosition(r4);	 Catch:{ all -> 0x03d4 }
    L_0x03bd:
        r4 = r24.moveToNext();	 Catch:{ all -> 0x03d4 }
        if (r4 == 0) goto L_0x03e0;
    L_0x03c3:
        r4 = 0;
        r0 = r24;
        r4 = r0.getString(r4);	 Catch:{ all -> 0x03d4 }
        r4 = com.android.providers.telephony.MmsSmsUtils.getLast7DigitRev(r4);	 Catch:{ all -> 0x03d4 }
        r0 = r57;
        r0.add(r4);	 Catch:{ all -> 0x03d4 }
        goto L_0x03bd;
    L_0x03d4:
        r4 = move-exception;
        r9 = r67;
    L_0x03d7:
        if (r24 == 0) goto L_0x03dc;
    L_0x03d9:
        r24.close();	 Catch:{ all -> 0x03dd }
    L_0x03dc:
        throw r4;	 Catch:{ all -> 0x03dd }
    L_0x03dd:
        r4 = move-exception;
        goto L_0x02fe;
    L_0x03e0:
        r0 = r57;
        r1 = r69;
        r4 = r0.equals(r1);	 Catch:{ all -> 0x03d4 }
        if (r4 == 0) goto L_0x09ba;
    L_0x03ea:
        r9 = r39;
        r4 = 3;
        r0 = r23;
        r4 = r0.getInt(r4);	 Catch:{ all -> 0x0491 }
        if (r4 <= 0) goto L_0x042c;
    L_0x03f5:
        r79 = 1;
    L_0x03f7:
        if (r25 == 0) goto L_0x04d4;
    L_0x03f9:
        r4 = 1;
        r0 = r23;
        r72 = r0.getInt(r4);	 Catch:{ all -> 0x0491 }
        r4 = 2;
        r0 = r23;
        r44 = r0.getLong(r4);	 Catch:{ all -> 0x0491 }
        r4 = "marker";
        r0 = r36;
        r53 = r0.getAsLong(r4);	 Catch:{ all -> 0x0491 }
        if (r53 == 0) goto L_0x0423;
    L_0x0411:
        r4 = r53.longValue();	 Catch:{ all -> 0x0491 }
        r4 = (r4 > r44 ? 1 : (r4 == r44 ? 0 : -1));
        if (r4 > 0) goto L_0x0420;
    L_0x0419:
        r4 = 65538; // 0x10002 float:9.1838E-41 double:3.238E-319;
        r0 = r72;
        if (r0 != r4) goto L_0x0423;
    L_0x0420:
        switch(r72) {
            case 0: goto L_0x0472;
            case 1: goto L_0x042f;
            case 2: goto L_0x044d;
            case 3: goto L_0x0494;
            case 4: goto L_0x04b4;
            case 65538: goto L_0x044d;
            default: goto L_0x0423;
        };
    L_0x0423:
        if (r24 == 0) goto L_0x0428;
    L_0x0425:
        r24.close();	 Catch:{ all -> 0x03dd }
    L_0x0428:
        r67 = r9;
        goto L_0x035a;
    L_0x042c:
        r79 = 0;
        goto L_0x03f7;
    L_0x042f:
        r4 = "MmsProvider";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0491 }
        r5.<init>();	 Catch:{ all -> 0x0491 }
        r7 = "The state of downloaded message ";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.append(r9);	 Catch:{ all -> 0x0491 }
        r7 = " is SYNCING.";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.toString();	 Catch:{ all -> 0x0491 }
        android.util.Log.w(r4, r5);	 Catch:{ all -> 0x0491 }
    L_0x044d:
        r4 = "pdu";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0491 }
        r5.<init>();	 Catch:{ all -> 0x0491 }
        r7 = "_id=";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.append(r9);	 Catch:{ all -> 0x0491 }
        r5 = r5.toString();	 Catch:{ all -> 0x0491 }
        r7 = 0;
        r0 = r36;
        r3.update(r4, r0, r5, r7);	 Catch:{ all -> 0x0491 }
        if (r52 == 0) goto L_0x0423;
    L_0x046a:
        if (r79 == 0) goto L_0x046f;
    L_0x046c:
        r41 = "restored/";
        goto L_0x0423;
    L_0x046f:
        r41 = "updated/";
        goto L_0x0423;
    L_0x0472:
        r4 = "MmsProvider";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0491 }
        r5.<init>();	 Catch:{ all -> 0x0491 }
        r7 = "The state of downloaded message ";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.append(r9);	 Catch:{ all -> 0x0491 }
        r7 = " is DIRTY. ignoring.";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.toString();	 Catch:{ all -> 0x0491 }
        android.util.Log.i(r4, r5);	 Catch:{ all -> 0x0491 }
        goto L_0x0423;
    L_0x0491:
        r4 = move-exception;
        goto L_0x03d7;
    L_0x0494:
        r4 = "MmsProvider";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0491 }
        r5.<init>();	 Catch:{ all -> 0x0491 }
        r7 = "The state of downloaded message ";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.append(r9);	 Catch:{ all -> 0x0491 }
        r7 = " is SYNC_STATE_ERROR. Skip.";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.toString();	 Catch:{ all -> 0x0491 }
        android.util.Log.w(r4, r5);	 Catch:{ all -> 0x0491 }
        goto L_0x0423;
    L_0x04b4:
        r4 = "MmsProvider";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0491 }
        r5.<init>();	 Catch:{ all -> 0x0491 }
        r7 = "The state of downloaded message ";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.append(r9);	 Catch:{ all -> 0x0491 }
        r7 = " is SYNC_STATE_NOT_UPLOADALBE. Skip.";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.toString();	 Catch:{ all -> 0x0491 }
        android.util.Log.w(r4, r5);	 Catch:{ all -> 0x0491 }
        goto L_0x0423;
    L_0x04d4:
        r4 = "pdu";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0491 }
        r5.<init>();	 Catch:{ all -> 0x0491 }
        r7 = "_id=";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0491 }
        r5 = r5.append(r9);	 Catch:{ all -> 0x0491 }
        r5 = r5.toString();	 Catch:{ all -> 0x0491 }
        r7 = 0;
        r0 = r36;
        r3.update(r4, r0, r5, r7);	 Catch:{ all -> 0x0491 }
        if (r52 == 0) goto L_0x0423;
    L_0x04f1:
        if (r79 == 0) goto L_0x04f7;
    L_0x04f3:
        r41 = "restored/";
        goto L_0x0423;
    L_0x04f7:
        r41 = "updated/";
        goto L_0x0423;
    L_0x04fb:
        r9 = r67;
        if (r23 == 0) goto L_0x0502;
    L_0x04ff:
        r23.close();	 Catch:{ all -> 0x0304 }
    L_0x0502:
        r4 = 0;
        r4 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1));
        if (r4 != 0) goto L_0x053b;
    L_0x0508:
        r4 = "file_id";
        r0 = r36;
        r34 = r0.getAsString(r4);	 Catch:{ all -> 0x0304 }
        r5 = "need_download";
        r4 = android.text.TextUtils.isEmpty(r34);	 Catch:{ all -> 0x0304 }
        if (r4 != 0) goto L_0x0646;
    L_0x0518:
        r4 = 1;
    L_0x0519:
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ all -> 0x0304 }
        r0 = r36;
        r0.put(r5, r4);	 Catch:{ all -> 0x0304 }
        r4 = 0;
        r0 = r73;
        r1 = r36;
        r9 = r3.insert(r0, r4, r1);	 Catch:{ all -> 0x0304 }
        if (r52 == 0) goto L_0x052f;
    L_0x052d:
        r41 = "inserted/";
    L_0x052f:
        if (r20 == 0) goto L_0x053b;
    L_0x0531:
        r7 = r80;
        r8 = r3;
        r11 = r21;
        r12 = r20;
        r7.updateAddresses(r8, r9, r11, r12);	 Catch:{ all -> 0x0304 }
    L_0x053b:
        r4 = 4;
        r0 = r47;
        if (r0 != r4) goto L_0x0598;
    L_0x0540:
        r4 = "error_type";
        r0 = r82;
        r32 = r0.getAsInteger(r4);	 Catch:{ all -> 0x0304 }
        if (r32 == 0) goto L_0x0598;
    L_0x054a:
        r62 = new android.content.ContentValues;	 Catch:{ all -> 0x0304 }
        r4 = 3;
        r0 = r62;
        r0.<init>(r4);	 Catch:{ all -> 0x0304 }
        r4 = "proto_type";
        r5 = 1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x0304 }
        r0 = r62;
        r0.put(r4, r5);	 Catch:{ all -> 0x0304 }
        r4 = "msg_id";
        r5 = java.lang.Long.valueOf(r9);	 Catch:{ all -> 0x0304 }
        r0 = r62;
        r0.put(r4, r5);	 Catch:{ all -> 0x0304 }
        r4 = "err_type";
        r0 = r62;
        r1 = r32;
        r0.put(r4, r1);	 Catch:{ all -> 0x0304 }
        r4 = "pending_msgs";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0304 }
        r5.<init>();	 Catch:{ all -> 0x0304 }
        r7 = "msg_id=";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0304 }
        r5 = r5.append(r9);	 Catch:{ all -> 0x0304 }
        r5 = r5.toString();	 Catch:{ all -> 0x0304 }
        r7 = 0;
        r0 = r62;
        r4 = r3.update(r4, r0, r5, r7);	 Catch:{ all -> 0x0304 }
        if (r4 != 0) goto L_0x0598;
    L_0x0590:
        r4 = "pending_msgs";
        r5 = 0;
        r0 = r62;
        r3.insert(r4, r5, r0);	 Catch:{ all -> 0x0304 }
    L_0x0598:
        if (r74 == 0) goto L_0x05c9;
    L_0x059a:
        r4 = r74.longValue();	 Catch:{ all -> 0x0304 }
        r7 = 0;
        r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r4 == 0) goto L_0x05c9;
    L_0x05a4:
        r4 = com.android.providers.telephony.BatchModeHelper.getInstance();	 Catch:{ all -> 0x0304 }
        r5 = r80.getContext();	 Catch:{ all -> 0x0304 }
        r7 = r74.longValue();	 Catch:{ all -> 0x0304 }
        r4.updateThread(r5, r3, r7);	 Catch:{ all -> 0x0304 }
        if (r43 == 0) goto L_0x05c9;
    L_0x05b5:
        r4 = r74.longValue();	 Catch:{ all -> 0x0304 }
        r15 = com.android.providers.telephony.MmsSmsUtils.getAddressIdFromThreadId(r3, r4);	 Catch:{ all -> 0x0304 }
        r11 = com.android.providers.telephony.BatchModeHelper.getInstance();	 Catch:{ all -> 0x0304 }
        r13 = r74.longValue();	 Catch:{ all -> 0x0304 }
        r12 = r3;
        r11.updateBlockedThread(r12, r13, r15);	 Catch:{ all -> 0x0304 }
    L_0x05c9:
        r3.setTransactionSuccessful();	 Catch:{ all -> 0x0304 }
        r3.endTransaction();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = r66;
        r4 = r4.append(r0);
        r5 = "/";
        r4 = r4.append(r5);
        r0 = r41;
        r4 = r4.append(r0);
        r4 = r4.append(r9);
        r4 = r4.toString();
        r66 = android.net.Uri.parse(r4);
        if (r43 == 0) goto L_0x060a;
    L_0x05f4:
        r4 = android.provider.Telephony.Mms.Inbox.CONTENT_URI;
        r4 = android.content.ContentUris.withAppendedId(r4, r9);
        r4 = r4.buildUpon();
        r5 = "blocked_flag";
        r7 = "1";
        r4 = r4.appendQueryParameter(r5, r7);
        r66 = r4.build();
    L_0x060a:
        if (r54 == 0) goto L_0x0641;
    L_0x060c:
        r4 = r80.getContext();
        r0 = r25;
        notifyChange(r4, r0);
        if (r43 == 0) goto L_0x0641;
    L_0x0617:
        r42 = new android.content.Intent;
        r4 = "android.intent.action.FIREWALL_UPDATED";
        r0 = r42;
        r0.<init>(r4);
        r4 = 3;
        r0 = r22;
        if (r0 == r4) goto L_0x0630;
    L_0x0625:
        r4 = 13;
        r0 = r22;
        if (r0 == r4) goto L_0x0630;
    L_0x062b:
        r4 = 6;
        r0 = r22;
        if (r0 != r4) goto L_0x09ad;
    L_0x0630:
        r4 = "notification_show_type";
        r5 = 0;
        r0 = r42;
        r0.putExtra(r4, r5);
    L_0x0638:
        r4 = r80.getContext();
        r0 = r42;
        r4.sendBroadcast(r0);
    L_0x0641:
        r64.prof();
        goto L_0x000e;
    L_0x0646:
        r4 = 0;
        goto L_0x0519;
    L_0x0649:
        r4 = "addr";
        r0 = r73;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x06c5;
    L_0x0653:
        r36 = new android.content.ContentValues;
        r0 = r36;
        r1 = r82;
        r0.<init>(r1);
        r4 = "address";
        r0 = r36;
        com.android.providers.telephony.MmsSmsUtils.removeSpaceForAddressValue(r0, r4);
        r4 = r81.getPathSegments();
        r5 = 0;
        r4 = r4.get(r5);
        r4 = (java.lang.String) r4;
        r4 = java.lang.Long.valueOf(r4);
        r49 = r4.longValue();
        r4 = "msg_id";
        r5 = java.lang.Long.valueOf(r49);
        r0 = r36;
        r0.put(r4, r5);
        r4 = 0;
        r0 = r73;
        r1 = r36;
        r9 = r3.insert(r0, r4, r1);
        r4 = 0;
        r4 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1));
        if (r4 > 0) goto L_0x069b;
    L_0x0690:
        r4 = "MmsProvider";
        r5 = "Failed to insert address.";
        android.util.Log.e(r4, r5);
        r66 = 0;
        goto L_0x000e;
    L_0x069b:
        if (r25 != 0) goto L_0x06a6;
    L_0x069d:
        r4 = com.android.providers.telephony.BatchModeHelper.getInstance();
        r0 = r49;
        r4.markMmsDirty(r3, r0);
    L_0x06a6:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = r66;
        r4 = r4.append(r0);
        r5 = "/addr/";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r4 = r4.toString();
        r66 = android.net.Uri.parse(r4);
        goto L_0x060a;
    L_0x06c5:
        r4 = "part";
        r0 = r73;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0873;
    L_0x06cf:
        r36 = new android.content.ContentValues;
        r0 = r36;
        r1 = r82;
        r0.<init>(r1);
        r4 = r81.getPathSegments();
        r5 = 0;
        r4 = r4.get(r5);
        r4 = (java.lang.String) r4;
        r4 = java.lang.Long.valueOf(r4);
        r49 = r4.longValue();
        r4 = 11;
        r0 = r46;
        if (r0 != r4) goto L_0x06fc;
    L_0x06f1:
        r4 = "mid";
        r5 = java.lang.Long.valueOf(r49);
        r0 = r36;
        r0.put(r4, r5);
    L_0x06fc:
        r4 = "ct";
        r0 = r82;
        r28 = r0.getAsString(r4);
        r4 = "text/plain";
        r0 = r28;
        r63 = r4.equals(r0);
        r4 = "application/smil";
        r0 = r28;
        r70 = r4.equals(r0);
        if (r63 != 0) goto L_0x07fb;
    L_0x0716:
        if (r70 != 0) goto L_0x07fb;
    L_0x0718:
        r4 = "cl";
        r0 = r82;
        r27 = r0.getAsString(r4);
        r4 = android.text.TextUtils.isEmpty(r27);
        if (r4 != 0) goto L_0x07f0;
    L_0x0726:
        r33 = new java.io.File;
        r0 = r33;
        r1 = r27;
        r0.<init>(r1);
        r35 = r33.getName();
        r4 = r35.length();
        r5 = 100;
        if (r4 <= r5) goto L_0x0747;
    L_0x073b:
        r4 = r35.length();
        r4 = r4 + -100;
        r0 = r35;
        r35 = r0.substring(r4);
    L_0x0747:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "_";
        r4 = r4.append(r5);
        r0 = r35;
        r4 = r4.append(r0);
        r27 = r4.toString();
    L_0x075c:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r80.getContext();
        r7 = "parts";
        r8 = 0;
        r5 = r5.getDir(r7, r8);
        r5 = r5.getPath();
        r4 = r4.append(r5);
        r5 = "/PART_";
        r4 = r4.append(r5);
        r7 = java.lang.System.currentTimeMillis();
        r4 = r4.append(r7);
        r0 = r27;
        r4 = r4.append(r0);
        r61 = r4.toString();
        r4 = com.google.android.mms.util.DownloadDrmHelper.isDrmConvertNeeded(r28);
        if (r4 == 0) goto L_0x0796;
    L_0x0792:
        r61 = com.google.android.mms.util.DownloadDrmHelper.modifyDrmFwLockFileExtension(r61);
    L_0x0796:
        r4 = "_data";
        r0 = r36;
        r1 = r61;
        r0.put(r4, r1);
        r60 = new java.io.File;
        r60.<init>(r61);
        r4 = r60.exists();
        if (r4 != 0) goto L_0x07fb;
    L_0x07aa:
        r4 = r60.createNewFile();	 Catch:{ IOException -> 0x07cb }
        if (r4 != 0) goto L_0x07f4;
    L_0x07b0:
        r4 = new java.lang.IllegalStateException;	 Catch:{ IOException -> 0x07cb }
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x07cb }
        r5.<init>();	 Catch:{ IOException -> 0x07cb }
        r7 = "Unable to create new partFile: ";
        r5 = r5.append(r7);	 Catch:{ IOException -> 0x07cb }
        r0 = r61;
        r5 = r5.append(r0);	 Catch:{ IOException -> 0x07cb }
        r5 = r5.toString();	 Catch:{ IOException -> 0x07cb }
        r4.<init>(r5);	 Catch:{ IOException -> 0x07cb }
        throw r4;	 Catch:{ IOException -> 0x07cb }
    L_0x07cb:
        r31 = move-exception;
        r4 = "MmsProvider";
        r5 = "createNewFile";
        r0 = r31;
        android.util.Log.e(r4, r5, r0);
        r4 = new java.lang.IllegalStateException;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "Unable to create new partFile: ";
        r5 = r5.append(r7);
        r0 = r61;
        r5 = r5.append(r0);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x07f0:
        r27 = "";
        goto L_0x075c;
    L_0x07f4:
        r4 = 1;
        r5 = 0;
        r0 = r60;
        r0.setReadable(r4, r5);	 Catch:{ IOException -> 0x07cb }
    L_0x07fb:
        r3.beginTransaction();
        r4 = 0;
        r0 = r73;
        r1 = r36;
        r9 = r3.insert(r0, r4, r1);	 Catch:{ all -> 0x086c }
        r4 = 0;
        r4 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1));
        if (r4 > 0) goto L_0x081b;
    L_0x080d:
        r4 = "MmsProvider";
        r5 = "MmsProvider.insert: failed!";
        android.util.Log.e(r4, r5);	 Catch:{ all -> 0x09b7 }
        r66 = 0;
        r3.endTransaction();
        goto L_0x000e;
    L_0x081b:
        r71 = com.android.providers.telephony.MmsSmsUtils.suppressMakingMmsPreview(r81);	 Catch:{ all -> 0x09b7 }
        r37 = com.android.providers.telephony.BatchModeHelper.getInstance();	 Catch:{ all -> 0x09b7 }
        if (r25 != 0) goto L_0x0840;
    L_0x0825:
        r0 = r37;
        r1 = r49;
        r0.markMmsDirty(r3, r1);	 Catch:{ all -> 0x09b7 }
        r0 = r37;
        r1 = r49;
        r0.clearFileId(r3, r1);	 Catch:{ all -> 0x09b7 }
        if (r71 != 0) goto L_0x0840;
    L_0x0835:
        r4 = r80.getContext();	 Catch:{ all -> 0x09b7 }
        r0 = r37;
        r1 = r49;
        r0.remakeMmsPreview(r4, r3, r1);	 Catch:{ all -> 0x09b7 }
    L_0x0840:
        r0 = r37;
        r1 = r49;
        r0.updateThreadHasAttachmentByMsg(r3, r1);	 Catch:{ all -> 0x09b7 }
        r3.setTransactionSuccessful();	 Catch:{ all -> 0x09b7 }
        r3.endTransaction();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = r66;
        r4 = r4.append(r0);
        r5 = "/part/";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r4 = r4.toString();
        r66 = android.net.Uri.parse(r4);
        goto L_0x060a;
    L_0x086c:
        r4 = move-exception;
        r9 = r67;
    L_0x086f:
        r3.endTransaction();
        throw r4;
    L_0x0873:
        r4 = "rate";
        r0 = r73;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x08c3;
    L_0x087d:
        r4 = "sent_time";
        r0 = r82;
        r4 = r0.getAsLong(r4);
        r55 = r4.longValue();
        r4 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r58 = r55 - r4;
        r3.beginTransaction();
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x08be }
        r4.<init>();	 Catch:{ all -> 0x08be }
        r5 = "sent_time<=";
        r4 = r4.append(r5);	 Catch:{ all -> 0x08be }
        r0 = r58;
        r4 = r4.append(r0);	 Catch:{ all -> 0x08be }
        r4 = r4.toString();	 Catch:{ all -> 0x08be }
        r5 = 0;
        r0 = r73;
        r3.delete(r0, r4, r5);	 Catch:{ all -> 0x08be }
        r4 = 0;
        r0 = r73;
        r1 = r82;
        r3.insert(r0, r4, r1);	 Catch:{ all -> 0x08be }
        r3.setTransactionSuccessful();	 Catch:{ all -> 0x08be }
        r3.endTransaction();
        r9 = r67;
        goto L_0x060a;
    L_0x08be:
        r4 = move-exception;
        r3.endTransaction();
        throw r4;
    L_0x08c3:
        r4 = "drm";
        r0 = r73;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0992;
    L_0x08cd:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r80.getContext();
        r7 = "parts";
        r8 = 0;
        r5 = r5.getDir(r7, r8);
        r5 = r5.getPath();
        r4 = r4.append(r5);
        r5 = "/PART_";
        r4 = r4.append(r5);
        r7 = java.lang.System.currentTimeMillis();
        r4 = r4.append(r7);
        r61 = r4.toString();
        r36 = new android.content.ContentValues;
        r4 = 1;
        r0 = r36;
        r0.<init>(r4);
        r4 = "_data";
        r0 = r36;
        r1 = r61;
        r0.put(r4, r1);
        r60 = new java.io.File;
        r60.<init>(r61);
        r4 = r60.exists();
        if (r4 != 0) goto L_0x0959;
    L_0x0913:
        r4 = r60.createNewFile();	 Catch:{ IOException -> 0x0934 }
        if (r4 != 0) goto L_0x0959;
    L_0x0919:
        r4 = new java.lang.IllegalStateException;	 Catch:{ IOException -> 0x0934 }
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0934 }
        r5.<init>();	 Catch:{ IOException -> 0x0934 }
        r7 = "Unable to create new file: ";
        r5 = r5.append(r7);	 Catch:{ IOException -> 0x0934 }
        r0 = r61;
        r5 = r5.append(r0);	 Catch:{ IOException -> 0x0934 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x0934 }
        r4.<init>(r5);	 Catch:{ IOException -> 0x0934 }
        throw r4;	 Catch:{ IOException -> 0x0934 }
    L_0x0934:
        r31 = move-exception;
        r4 = "MmsProvider";
        r5 = "createNewFile";
        r0 = r31;
        android.util.Log.e(r4, r5, r0);
        r4 = new java.lang.IllegalStateException;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "Unable to create new file: ";
        r5 = r5.append(r7);
        r0 = r61;
        r5 = r5.append(r0);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x0959:
        r4 = 0;
        r0 = r73;
        r1 = r36;
        r9 = r3.insert(r0, r4, r1);
        r4 = 0;
        r4 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1));
        if (r4 > 0) goto L_0x0973;
    L_0x0968:
        r4 = "MmsProvider";
        r5 = "MmsProvider.insert: failed!";
        android.util.Log.e(r4, r5);
        r66 = 0;
        goto L_0x000e;
    L_0x0973:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = r66;
        r4 = r4.append(r0);
        r5 = "/drm/";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r4 = r4.toString();
        r66 = android.net.Uri.parse(r4);
        goto L_0x060a;
    L_0x0992:
        r4 = new java.lang.AssertionError;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "Unknown table type: ";
        r5 = r5.append(r7);
        r0 = r73;
        r5 = r5.append(r0);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x09ad:
        r4 = "notification_show_type";
        r5 = 1;
        r0 = r42;
        r0.putExtra(r4, r5);
        goto L_0x0638;
    L_0x09b7:
        r4 = move-exception;
        goto L_0x086f;
    L_0x09ba:
        r9 = r67;
        goto L_0x0423;
    L_0x09be:
        r9 = r67;
        goto L_0x0502;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    private void crackFullDateValues(ContentValues values) {
        if (values.containsKey("date_full")) {
            long dateFull = values.getAsLong("date_full").longValue();
            values.remove("date_full");
            values.put(WapPush.DATE, Long.valueOf(dateFull / 1000));
            values.put("date_ms_part", Long.valueOf(dateFull % 1000));
        }
    }

    private int getMessageBoxByMatch(int match) {
        switch (match) {
            case MMS_INBOX /*2*/:
            case MMS_INBOX_ID /*3*/:
                return MMS_ALL_ID;
            case MMS_SENT /*4*/:
            case MMS_SENT_ID /*5*/:
                return MMS_INBOX;
            case MMS_DRAFTS /*6*/:
            case MMS_DRAFTS_ID /*7*/:
                return MMS_INBOX_ID;
            case MMS_OUTBOX /*8*/:
            case MMS_OUTBOX_ID /*9*/:
                return MMS_SENT;
            default:
                throw new IllegalArgumentException("bad Arg: " + match);
        }
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String table;
        int i = MMS_ALL;
        MmsSmsUtils.setPreviousOpTime();
        OperationPerfProfiler profiler = new OperationPerfProfiler("delete " + uri + " with selection " + selection);
        boolean callerIsSyncAdapter = MmsSmsUtils.readBooleanQueryParameter(uri, "caller_is_syncadapter", LOCAL_LOGV);
        int match = sURLMatcher.match(uri);
        String extraSelection = null;
        switch (match) {
            case MMS_ALL /*0*/:
            case MMS_INBOX /*2*/:
            case MMS_SENT /*4*/:
            case MMS_DRAFTS /*6*/:
            case MMS_OUTBOX /*8*/:
                table = TABLE_PDU;
                if (match != 0) {
                    extraSelection = "msg_box=" + getMessageBoxByMatch(match);
                    break;
                }
                break;
            case MMS_ALL_ID /*1*/:
            case MMS_INBOX_ID /*3*/:
            case MMS_SENT_ID /*5*/:
            case MMS_DRAFTS_ID /*7*/:
            case MMS_OUTBOX_ID /*9*/:
                table = TABLE_PDU;
                extraSelection = "_id=" + uri.getLastPathSegment();
                break;
            case MMS_ALL_PART /*10*/:
                table = TABLE_PART;
                break;
            case MMS_MSG_PART /*11*/:
                table = TABLE_PART;
                extraSelection = "mid=" + ((String) uri.getPathSegments().get(MMS_ALL));
                break;
            case MMS_PART_ID /*12*/:
                table = TABLE_PART;
                extraSelection = "_id=" + ((String) uri.getPathSegments().get(MMS_ALL_ID));
                break;
            case MMS_MSG_ADDR /*13*/:
                table = TABLE_ADDR;
                extraSelection = "msg_id=" + ((String) uri.getPathSegments().get(MMS_ALL));
                break;
            case MMS_DRM_STORAGE /*17*/:
                table = TABLE_DRM;
                break;
            default:
                Log.w(TAG, "No match for URI '" + uri + "'");
                break;
        }
        String finalSelection = MmsSmsUtils.concatenateWhere(selection, extraSelection);
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        if (TABLE_PDU.equals(table)) {
            i = deleteMessages(getContext(), db, finalSelection, selectionArgs, uri);
        } else if (TABLE_PART.equals(table)) {
            i = deleteParts(getContext(), db, finalSelection, selectionArgs, callerIsSyncAdapter);
        } else if (TABLE_DRM.equals(table)) {
            i = deleteTempDrmData(db, finalSelection, selectionArgs);
        } else if (TABLE_ADDR.equals(table)) {
            i = deleteAddrs(db, finalSelection, selectionArgs, callerIsSyncAdapter);
        } else {
            i = db.delete(table, finalSelection, selectionArgs);
        }
        profiler.prof();
        return i;
    }

    static int deleteMessages(Context context, SQLiteDatabase db, String selection, String[] selectionArgs, Uri uri) {
        boolean hasXiaomiAccount = MmsSmsUtils.hasXiaomiAccount(context);
        boolean callerIsSyncAdapter = MmsSmsUtils.callerIsSyncAdapter(uri);
        boolean hasBlockedFlag = MmsSmsUtils.hasBlockedFlag(uri);
        int count = MMS_ALL;
        db.beginTransaction();
        try {
            HashSet<Long> threadIds;
            if (!MmsSmsUtils.SUPPORT_SYNC_ADAPTER || !hasXiaomiAccount || callerIsSyncAdapter || hasBlockedFlag) {
                threadIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_PDU, "DISTINCT thread_id", selection, selectionArgs);
                count = db.delete(TABLE_PDU, selection, selectionArgs);
            } else {
                ContentValues values = new ContentValues();
                values.put("deleted", Integer.valueOf(MMS_ALL_ID));
                values.put("sync_state", Integer.valueOf(MMS_ALL));
                String finalSelection = MmsSmsUtils.concatenateWhere(selection, "deleted=0");
                threadIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_PDU, "DISTINCT thread_id", finalSelection, selectionArgs);
                count = db.update(TABLE_PDU, values, finalSelection, selectionArgs);
            }
            if (count > 0) {
                BatchModeHelper helper = BatchModeHelper.getInstance();
                Iterator i$ = threadIds.iterator();
                while (i$.hasNext()) {
                    long threadId = ((Long) i$.next()).longValue();
                    if (hasBlockedFlag) {
                        helper.updateBlockedThread(db, threadId, MmsSmsUtils.getAddressIdFromThreadId(db, threadId));
                    } else {
                        helper.updateThread(context, db, threadId);
                        helper.updateThreadHasAttachment(db, threadId);
                    }
                }
                notifyChange(context, callerIsSyncAdapter);
                helper.broadcastDeletedContents(context, uri);
            }
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();
        }
    }

    public static int deleteParts(Context context, SQLiteDatabase db, String selection, String[] selectionArgs, boolean callerIsSyncAdapter) {
        db.beginTransaction();
        int result = MMS_ALL;
        try {
            HashSet<Long> msgIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_PART, "DISTINCT mid", selection, selectionArgs);
            result = deleteDataRows(db, TABLE_PART, selection, selectionArgs);
            if (result > 0) {
                BatchModeHelper helper = BatchModeHelper.getInstance();
                Iterator i$ = msgIds.iterator();
                while (i$.hasNext()) {
                    long msgId = ((Long) i$.next()).longValue();
                    if (!callerIsSyncAdapter) {
                        helper.markMmsDirty(db, msgId);
                        helper.clearFileId(db, msgId);
                        helper.remakeMmsPreview(context, db, msgId);
                    }
                    helper.updateThreadHasAttachmentByMsg(db, msgId);
                }
            }
            db.setTransactionSuccessful();
            return result;
        } finally {
            db.endTransaction();
        }
    }

    public static int deleteAddrs(SQLiteDatabase db, String selection, String[] selectionArgs, boolean callerIsSyncAdapter) {
        db.beginTransaction();
        int result = MMS_ALL;
        try {
            HashSet<Long> msgIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_ADDR, "DISTINCT msg_id", selection, selectionArgs);
            result = db.delete(TABLE_ADDR, selection, selectionArgs);
            if (result > 0 && !callerIsSyncAdapter) {
                BatchModeHelper helper = BatchModeHelper.getInstance();
                Iterator i$ = msgIds.iterator();
                while (i$.hasNext()) {
                    helper.markMmsDirty(db, ((Long) i$.next()).longValue());
                }
            }
            db.setTransactionSuccessful();
            return result;
        } finally {
            db.endTransaction();
        }
    }

    private static int deleteTempDrmData(SQLiteDatabase db, String selection, String[] selectionArgs) {
        return deleteDataRows(db, TABLE_DRM, selection, selectionArgs);
    }

    private static int deleteDataRows(SQLiteDatabase db, String table, String selection, String[] selectionArgs) {
        String[] strArr = new String[MMS_ALL_ID];
        strArr[MMS_ALL] = ThreadSettings.WALLPAPER;
        Cursor cursor = db.query(table, strArr, selection, selectionArgs, null, null, null);
        if (cursor == null) {
            return MMS_ALL;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            return MMS_ALL;
        }
        while (cursor.moveToNext()) {
            try {
                String path = cursor.getString(MMS_ALL);
                if (path != null) {
                    new File(path).delete();
                }
            } catch (Throwable th) {
                cursor.close();
            }
        }
        cursor.close();
        return db.delete(table, selection, selectionArgs);
    }

    private void updateAddresses(SQLiteDatabase db, String selection, String[] selectionArgs, HashSet<String> addresses) {
        String str = TABLE_PDU;
        String[] strArr = new String[MMS_INBOX];
        strArr[MMS_ALL] = "_id";
        strArr[MMS_ALL_ID] = "m_type";
        Cursor msgCursor = db.query(str, strArr, selection, selectionArgs, null, null, null);
        if (msgCursor != null) {
            try {
                msgCursor.moveToPosition(-1);
                while (msgCursor.moveToNext()) {
                    long msgId = msgCursor.getLong(MMS_ALL);
                    int addressType = getAddressTypeFromMsgType(msgCursor.getInt(MMS_ALL));
                    if (addressType != 0) {
                        updateAddresses(db, msgId, addressType, (HashSet) addresses);
                    }
                }
            } finally {
                msgCursor.close();
            }
        }
    }

    private int getAddressTypeFromMsgType(int msgType) {
        switch (msgType) {
            case 128:
                return 151;
            case 130:
            case 132:
                return 137;
            default:
                return MMS_ALL;
        }
    }

    private void updateAddresses(SQLiteDatabase db, long msgId, int addressType, HashSet<String> addresses) {
        ContentValues addrValues = new ContentValues();
        addrValues.put("msg_id", Long.valueOf(msgId));
        addrValues.put(AntispamNumber.TYPE, Integer.valueOf(addressType));
        addrValues.put("charset", Integer.valueOf(106));
        Iterator i$ = addresses.iterator();
        while (i$.hasNext()) {
            addrValues.put(TagYellowPage.ADDRESS, (String) i$.next());
            db.insert(TABLE_ADDR, null, addrValues);
        }
    }

    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        String[] strArr = new String[MMS_INBOX];
        strArr[MMS_ALL] = "mid";
        strArr[MMS_ALL_ID] = ThreadSettings.WALLPAPER;
        Cursor c = query(uri, strArr, null, null, null);
        if (c == null) {
            return null;
        }
        int count;
        if (c != null) {
            try {
                count = c.getCount();
            } catch (Throwable th) {
                c.close();
            }
        } else {
            count = MMS_ALL;
        }
        if (count == 0) {
            throw new FileNotFoundException("No entry for " + uri);
        } else if (count > MMS_ALL_ID) {
            throw new FileNotFoundException("Multiple items at " + uri);
        } else {
            c.moveToFirst();
            long msgId = c.getLong(MMS_ALL);
            String path = c.getString(MMS_ALL_ID);
            c.close();
            if (path == null) {
                throw new FileNotFoundException("Column _data not found.");
            }
            if (Log.isLoggable(TAG, MMS_INBOX)) {
                Log.d(TAG, "openFile: uri=" + uri + ", mode=" + mode);
            }
            ParcelFileDescriptor fd = ParcelFileDescriptor.open(new File(path), ParcelFileDescriptor.parseMode(mode));
            if (MmsSmsUtils.suppressMakingMmsPreview(uri) || mode.indexOf(119) == -1) {
                return fd;
            }
            final long finalMsgId = msgId;
            new FileObserver(path, MMS_OUTBOX) {
                public void onEvent(int event, String path) {
                    ContentValues v = new ContentValues();
                    v.put("preview_type", Integer.valueOf(MmsProvider.MMS_ALL));
                    v.put(Conversations.SNIPPET, (String) null);
                    v.put("preview_data", (byte[]) null);
                    MmsProvider.this.mOpenHelper.getWritableDatabase().update(MmsProvider.TABLE_PDU, v, "_id=" + finalMsgId, null);
                    stopWatching();
                }
            }.startWatching();
            return fd;
        }
    }

    private void filterUnsupportedKeys(ContentValues values) {
        values.remove("d_tm_tok");
        values.remove("s_vis");
        values.remove("r_chg");
        values.remove("r_chg_dl_tok");
        values.remove("r_chg_dl");
        values.remove("r_chg_id");
        values.remove("r_chg_sz");
        values.remove("p_s_by");
        values.remove("p_s_d");
        values.remove("store");
        values.remove("mm_st");
        values.remove("mm_flg_tok");
        values.remove("mm_flg");
        values.remove("store_st");
        values.remove("store_st_txt");
        values.remove("stored");
        values.remove("totals");
        values.remove("mb_t");
        values.remove("mb_t_tok");
        values.remove("qt");
        values.remove("mb_qt");
        values.remove("mb_qt_tok");
        values.remove("m_cnt");
        values.remove("start");
        values.remove("d_ind");
        values.remove("e_des");
        values.remove("limit");
        values.remove("r_r_mod");
        values.remove("r_r_mod_txt");
        values.remove("st_txt");
        values.remove("apl_id");
        values.remove("r_apl_id");
        values.remove("aux_apl_id");
        values.remove("drm_c");
        values.remove("adp_a");
        values.remove("repl_id");
        values.remove("cl_id");
        values.remove("cl_st");
        values.remove("_id");
    }

    private static void notifyChange(Context context, boolean callerIsSyncAdapter) {
        BatchModeHelper helper = BatchModeHelper.getInstance();
        helper.notifyChange(context, MmsSms.CONTENT_URI);
        if (!callerIsSyncAdapter) {
            helper.requestSync(context, "local.sms.sync");
        }
    }
}
