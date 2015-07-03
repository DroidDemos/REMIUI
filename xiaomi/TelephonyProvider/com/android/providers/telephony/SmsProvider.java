package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.provider.MiuiSettings.Secure;
import android.provider.Telephony.MmsSms;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.common.Search;
import com.android.common.speech.LoggingEvents;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.telephony.SmsMessageBase;
import com.android.providers.telephony.FirewallDatabaseHelper.TABLE;
import com.android.providers.telephony.MmsSmsUtils.OperationPerfProfiler;
import com.google.android.collect.Sets;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.SmsCb;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.SmsCb.Conversations;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.TextBasedSmsCbColumns;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import miui.provider.ExtraTelephony;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;
import miui.provider.yellowpage.YellowPageUtils.TagYellowPage;
import miui.telephony.MultiSimManager;

public class SmsProvider extends ContentProvider {
    private static final String[] CONTACT_QUERY_PROJECTION;
    private static final Uri ICC2_URI;
    private static final String[] ICC_COLUMNS;
    private static final Uri ICC_URI;
    private static final boolean LOCAL_LOGV = false;
    private static final Uri NOTIFICATION_URI;
    private static final Integer ONE;
    private static final int PERSON_ID_COLUMN = 0;
    private static final int SMS_ALL = 0;
    private static final int SMS_ALL_ICC = 22;
    private static final int SMS_ALL_ICC2 = 24;
    private static final int SMS_ALL_ID = 1;
    private static final int SMS_ATTACHMENT = 16;
    private static final int SMS_ATTACHMENT_ID = 17;
    private static final int SMS_CONVERSATIONS = 10;
    private static final int SMS_CONVERSATIONS_ID = 11;
    private static final int SMS_DRAFT = 6;
    private static final int SMS_DRAFT_ID = 7;
    private static final int SMS_FAILED = 26;
    private static final int SMS_FAILED_ID = 27;
    private static final int SMS_ICC = 23;
    private static final int SMS_ICC2 = 25;
    private static final int SMS_INBOX = 2;
    private static final int SMS_INBOX_ID = 3;
    private static final int SMS_MX_STATUS = 30;
    private static final int SMS_NEW_THREAD_ID = 18;
    private static final int SMS_OUTBOX = 8;
    private static final int SMS_OUTBOX_ID = 9;
    private static final int SMS_QUERY_THREAD_ID = 19;
    private static final int SMS_QUEUED = 28;
    private static final int SMS_RAW_MESSAGE = 15;
    private static final int SMS_SEEN = 31;
    private static final int SMS_SENT = 4;
    private static final int SMS_SENT_ID = 5;
    private static final int SMS_STATUS_ID = 20;
    private static final int SMS_STATUS_PENDING = 21;
    private static final int SMS_UNDELIVERED = 29;
    private static final HashSet<String> SYNC_COLUMNS;
    private static final String TABLE_RAW = "raw";
    static final String TABLE_SMS = "sms";
    private static final String TABLE_SR_PENDING = "sr_pending";
    private static final String TABLE_WORDS = "words";
    private static final String TAG = "SmsProvider";
    private static final String VND_ANDROID_DIR_SMS = "vnd.android.cursor.dir/sms";
    private static final String VND_ANDROID_SMS = "vnd.android.cursor.item/sms";
    private static final String VND_ANDROID_SMSCHAT = "vnd.android.cursor.item/sms-chat";
    private static final HashMap<String, String> sConversationProjectionMap;
    private static final String[] sIDProjection;
    private static final UriMatcher sURLMatcher;
    private MmsSmsDatabaseHelper mOpenHelper;

    static {
        NOTIFICATION_URI = Uri.parse("content://sms");
        ICC_URI = Uri.parse("content://sms/icc");
        ICC2_URI = Uri.parse("content://sms/icc2");
        ONE = Integer.valueOf(SMS_ALL_ID);
        String[] strArr = new String[SMS_ALL_ID];
        strArr[SMS_ALL] = "person";
        CONTACT_QUERY_PROJECTION = strArr;
        ICC_COLUMNS = new String[]{"service_center_address", TagYellowPage.ADDRESS, "message_class", TextBasedSmsCbColumns.BODY, WapPush.DATE, TagYellowPage.DELETED, "index_on_icc", "is_status_report", "transport_type", AntispamNumber.TYPE, WapPush.LOCKED, "error_code", "timed", "_id"};
        strArr = new String[SMS_QUERY_THREAD_ID];
        strArr[SMS_ALL] = "_id";
        strArr[SMS_ALL_ID] = Search.SOURCE;
        strArr[SMS_INBOX] = "bind_id";
        strArr[SMS_INBOX_ID] = "marker";
        strArr[SMS_SENT] = AntispamNumber.TYPE;
        strArr[SMS_SENT_ID] = WapPush.DATE;
        strArr[SMS_DRAFT] = "date_sent";
        strArr[SMS_DRAFT_ID] = TextBasedSmsCbColumns.BODY;
        strArr[SMS_OUTBOX] = TagYellowPage.DELETED;
        strArr[SMS_OUTBOX_ID] = TagYellowPage.ADDRESS;
        strArr[SMS_CONVERSATIONS] = WapPush.THREAD_ID;
        strArr[SMS_CONVERSATIONS_ID] = "deleted";
        strArr[12] = WapPush.READ;
        strArr[13] = WapPush.LOCKED;
        strArr[14] = "mx_status";
        strArr[SMS_RAW_MESSAGE] = "timed";
        strArr[SMS_ATTACHMENT] = TABLE.ACCOUNT;
        strArr[SMS_ATTACHMENT_ID] = WapPush.SIM_ID;
        strArr[SMS_NEW_THREAD_ID] = "block_type";
        SYNC_COLUMNS = Sets.newHashSet(strArr);
        sConversationProjectionMap = new HashMap();
        strArr = new String[SMS_ALL_ID];
        strArr[SMS_ALL] = "_id";
        sIDProjection = strArr;
        sURLMatcher = new UriMatcher(-1);
        sURLMatcher.addURI(TABLE_SMS, null, SMS_ALL);
        sURLMatcher.addURI(TABLE_SMS, "#", SMS_ALL_ID);
        sURLMatcher.addURI(TABLE_SMS, "inbox", SMS_INBOX);
        sURLMatcher.addURI(TABLE_SMS, "inbox/#", SMS_INBOX_ID);
        sURLMatcher.addURI(TABLE_SMS, "sent", SMS_SENT);
        sURLMatcher.addURI(TABLE_SMS, "sent/#", SMS_SENT_ID);
        sURLMatcher.addURI(TABLE_SMS, "draft", SMS_DRAFT);
        sURLMatcher.addURI(TABLE_SMS, "draft/#", SMS_DRAFT_ID);
        sURLMatcher.addURI(TABLE_SMS, "outbox", SMS_OUTBOX);
        sURLMatcher.addURI(TABLE_SMS, "outbox/#", SMS_OUTBOX_ID);
        sURLMatcher.addURI(TABLE_SMS, "undelivered", SMS_UNDELIVERED);
        sURLMatcher.addURI(TABLE_SMS, "failed", SMS_FAILED);
        sURLMatcher.addURI(TABLE_SMS, "failed/#", SMS_FAILED_ID);
        sURLMatcher.addURI(TABLE_SMS, "queued", SMS_QUEUED);
        sURLMatcher.addURI(TABLE_SMS, "conversations", SMS_CONVERSATIONS);
        sURLMatcher.addURI(TABLE_SMS, "conversations/*", SMS_CONVERSATIONS_ID);
        sURLMatcher.addURI(TABLE_SMS, TABLE_RAW, SMS_RAW_MESSAGE);
        sURLMatcher.addURI(TABLE_SMS, "attachments", SMS_ATTACHMENT);
        sURLMatcher.addURI(TABLE_SMS, "attachments/#", SMS_ATTACHMENT_ID);
        sURLMatcher.addURI(TABLE_SMS, "threadID", SMS_NEW_THREAD_ID);
        sURLMatcher.addURI(TABLE_SMS, "threadID/*", SMS_QUERY_THREAD_ID);
        sURLMatcher.addURI(TABLE_SMS, "status/#", SMS_STATUS_ID);
        sURLMatcher.addURI(TABLE_SMS, "mx_status/#", SMS_MX_STATUS);
        sURLMatcher.addURI(TABLE_SMS, TABLE_SR_PENDING, SMS_STATUS_PENDING);
        sURLMatcher.addURI(TABLE_SMS, "icc", SMS_ALL_ICC);
        sURLMatcher.addURI(TABLE_SMS, "icc/#", SMS_ICC);
        sURLMatcher.addURI(TABLE_SMS, "icc2", SMS_ALL_ICC2);
        sURLMatcher.addURI(TABLE_SMS, "icc2/#", SMS_ICC2);
        sURLMatcher.addURI(TABLE_SMS, "sim", SMS_ALL_ICC);
        sURLMatcher.addURI(TABLE_SMS, "sim/#", SMS_ICC);
        sURLMatcher.addURI(TABLE_SMS, "sim2", SMS_ALL_ICC2);
        sURLMatcher.addURI(TABLE_SMS, "sim2/#", SMS_ICC2);
        sURLMatcher.addURI(TABLE_SMS, "seen/#", SMS_SEEN);
        sConversationProjectionMap.put(Conversations.SNIPPET, "sms.body AS snippet");
        sConversationProjectionMap.put(WapPush.THREAD_ID, "sms.thread_id AS thread_id");
        sConversationProjectionMap.put(Conversations.MESSAGE_COUNT, "groups.msg_count AS msg_count");
        sConversationProjectionMap.put("delta", null);
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

    public Cursor query(Uri url, String[] projectionIn, String selection, String[] selectionArgs, String sort) {
        MmsSmsUtils.setPreviousOpTime();
        OperationPerfProfiler operationPerfProfiler = new OperationPerfProfiler("query " + url + " with selection " + selection);
        boolean callerIsSyncAdapter = MmsSmsUtils.callerIsSyncAdapter(url);
        Cursor emptyCursor = Secure.checkPrivacyAndReturnCursor(getContext());
        if (emptyCursor != null) {
            return emptyCursor;
        }
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (sURLMatcher.match(url)) {
            case SMS_ALL /*0*/:
                constructQueryForBox(qb, SMS_ALL);
                break;
            case SMS_ALL_ID /*1*/:
                qb.setTables(TABLE_SMS);
                qb.appendWhere("(_id = " + ((String) url.getPathSegments().get(SMS_ALL)) + ")");
                break;
            case SMS_INBOX /*2*/:
                constructQueryForBox(qb, SMS_ALL_ID);
                break;
            case SMS_INBOX_ID /*3*/:
            case SMS_SENT_ID /*5*/:
            case SMS_DRAFT_ID /*7*/:
            case SMS_OUTBOX_ID /*9*/:
            case SMS_FAILED_ID /*27*/:
                qb.setTables(TABLE_SMS);
                qb.appendWhere("(_id = " + ((String) url.getPathSegments().get(SMS_ALL_ID)) + ")");
                break;
            case SMS_SENT /*4*/:
                constructQueryForBox(qb, SMS_INBOX);
                break;
            case SMS_DRAFT /*6*/:
                constructQueryForBox(qb, SMS_INBOX_ID);
                break;
            case SMS_OUTBOX /*8*/:
                constructQueryForBox(qb, SMS_SENT);
                break;
            case SMS_CONVERSATIONS /*10*/:
                qb.setTables("sms, (SELECT thread_id AS group_thread_id, MAX(date)AS group_date,COUNT(*) AS msg_count FROM sms GROUP BY thread_id) AS groups");
                qb.appendWhere("sms.thread_id = groups.group_thread_id AND sms.date =groups.group_date");
                qb.setProjectionMap(sConversationProjectionMap);
                break;
            case SMS_CONVERSATIONS_ID /*11*/:
                try {
                    int threadID = Integer.parseInt((String) url.getPathSegments().get(SMS_ALL_ID));
                    if (Log.isLoggable(TAG, SMS_INBOX)) {
                        Log.d(TAG, "query conversations: threadID=" + threadID);
                    }
                    qb.setTables(TABLE_SMS);
                    qb.appendWhere("thread_id = " + threadID);
                    break;
                } catch (Exception e) {
                    Log.e(TAG, "Bad conversation thread id: " + ((String) url.getPathSegments().get(SMS_ALL_ID)));
                    return null;
                }
            case SMS_RAW_MESSAGE /*15*/:
                qb.setTables(TABLE_RAW);
                break;
            case SMS_ATTACHMENT /*16*/:
                qb.setTables("attachments");
                break;
            case SMS_ATTACHMENT_ID /*17*/:
                qb.setTables("attachments");
                qb.appendWhere("(sms_id = " + ((String) url.getPathSegments().get(SMS_ALL_ID)) + ")");
                break;
            case SMS_QUERY_THREAD_ID /*19*/:
                qb.setTables("canonical_addresses");
                if (projectionIn == null) {
                    projectionIn = sIDProjection;
                    break;
                }
                break;
            case SMS_STATUS_ID /*20*/:
                qb.setTables(TABLE_SMS);
                qb.appendWhere("(_id = " + ((String) url.getPathSegments().get(SMS_ALL_ID)) + ")");
                break;
            case SMS_STATUS_PENDING /*21*/:
                qb.setTables(TABLE_SR_PENDING);
                break;
            case SMS_ALL_ICC /*22*/:
                return getAllMessagesFromIcc(SMS_ALL);
            case SMS_ICC /*23*/:
                return getSingleMessageFromIcc(SMS_ALL, (String) url.getPathSegments().get(SMS_ALL_ID));
            case SMS_ALL_ICC2 /*24*/:
                return getAllMessagesFromIcc(SMS_ALL_ID);
            case SMS_ICC2 /*25*/:
                return getSingleMessageFromIcc(SMS_ALL_ID, (String) url.getPathSegments().get(SMS_ALL_ID));
            case SMS_FAILED /*26*/:
                constructQueryForBox(qb, SMS_SENT_ID);
                break;
            case SMS_QUEUED /*28*/:
                constructQueryForBox(qb, SMS_DRAFT);
                break;
            case SMS_UNDELIVERED /*29*/:
                constructQueryForUndelivered(qb);
                break;
            case SMS_MX_STATUS /*30*/:
                qb.setTables(TABLE_SMS);
                qb.appendWhere("(mx_id=" + ((String) url.getPathSegments().get(SMS_ALL_ID)) + ")");
                Log.d(TAG, "querying mx status");
                break;
            case SMS_SEEN /*31*/:
                String categoryId = (String) url.getPathSegments().get(SMS_ALL_ID);
                try {
                    Integer.parseInt(categoryId);
                    if (Log.isLoggable(TAG, SMS_INBOX)) {
                        Log.d(TAG, "query seen of sms: categoryId=" + categoryId);
                    }
                    qb.setTables(TABLE_SMS);
                    qb.appendWhere("exists (SELECT 1 FROM threads WHERE threads._id=sms.thread_id AND threads.sp_type=" + categoryId + ")");
                    break;
                } catch (Exception e2) {
                    Log.e(TAG, "Bad service category id: " + ((String) url.getPathSegments().get(SMS_ALL_ID)));
                    return null;
                }
            default:
                Log.e(TAG, "Invalid request: " + url);
                return null;
        }
        String orderBy = null;
        if (!TextUtils.isEmpty(sort)) {
            orderBy = sort;
        } else if (qb.getTables().equals(TABLE_SMS)) {
            orderBy = SmsCb.DEFAULT_SORT_ORDER;
        }
        if (qb.getTables().equals(TABLE_SMS)) {
            selection = MmsSmsUtils.getSelectionByPrivatePermission(url, getContext(), selection);
            String blockSelection = MmsSmsUtils.getSelectionByBlockedPermission(url, getContext());
            if (!TextUtils.isEmpty(blockSelection)) {
                selection = MmsSmsUtils.concatenateWhere(selection, blockSelection);
            } else if (!callerIsSyncAdapter) {
                selection = MmsSmsUtils.concatenateWhere(selection, "deleted=0");
            }
        }
        SQLiteDatabase db = this.mOpenHelper.getReadableDatabase();
        Cursor cursor = null;
        db.beginTransaction();
        try {
            cursor = qb.query(db, projectionIn, selection, selectionArgs, null, null, orderBy);
            if (cursor != null) {
                cursor.setNotificationUri(getContext().getContentResolver(), NOTIFICATION_URI);
            }
            db.setTransactionSuccessful();
            operationPerfProfiler.prof();
            return cursor;
        } finally {
            db.endTransaction();
        }
    }

    private Object[] convertIccToSms(SmsMessage message, Pair<String, String> concatSms, int id) {
        Object[] row = new Object[14];
        row[SMS_ALL] = message.getServiceCenterAddress();
        if (message.getStatusOnIcc() == SMS_ALL_ID || message.getStatusOnIcc() == SMS_INBOX_ID) {
            row[SMS_ALL_ID] = message.getDisplayOriginatingAddress();
        } else {
            row[SMS_ALL_ID] = getRecipientAddress(message);
        }
        String concatSmsIndex = null;
        String concatSmsBody = null;
        if (concatSms != null) {
            concatSmsIndex = concatSms.first;
            concatSmsBody = concatSms.second;
        }
        row[SMS_INBOX] = String.valueOf(message.getMessageClass());
        if (concatSmsBody == null) {
            concatSmsBody = message.getDisplayMessageBody();
        }
        row[SMS_INBOX_ID] = concatSmsBody;
        if (message.getStatusOnIcc() == SMS_ALL_ID || message.getStatusOnIcc() == SMS_INBOX_ID) {
            row[SMS_SENT] = Long.valueOf(message.getTimestampMillis());
        } else {
            Long timeStamp = Long.valueOf(message.getTimestampMillis());
            if (timeStamp.longValue() == 0) {
                row[SMS_SENT] = Long.valueOf(System.currentTimeMillis());
            } else {
                row[SMS_SENT] = timeStamp;
            }
        }
        row[SMS_SENT_ID] = Integer.valueOf(message.getStatusOnIcc());
        if (concatSmsIndex == null) {
            concatSmsIndex = Integer.valueOf(message.getIndexOnIcc());
        }
        row[SMS_DRAFT] = concatSmsIndex;
        row[SMS_DRAFT_ID] = Boolean.valueOf(message.isStatusReportMessage());
        row[SMS_OUTBOX] = TABLE_SMS;
        if (message.getStatusOnIcc() == SMS_ALL_ID || message.getStatusOnIcc() == SMS_INBOX_ID) {
            row[SMS_OUTBOX_ID] = Integer.valueOf(SMS_ALL_ID);
        } else if (message.getStatusOnIcc() == SMS_DRAFT_ID) {
            row[SMS_OUTBOX_ID] = Integer.valueOf(SMS_INBOX_ID);
        } else {
            row[SMS_OUTBOX_ID] = Integer.valueOf(SMS_INBOX);
        }
        row[SMS_CONVERSATIONS] = Integer.valueOf(SMS_ALL);
        row[SMS_CONVERSATIONS_ID] = Integer.valueOf(SMS_ALL);
        row[12] = Integer.valueOf(SMS_ALL);
        row[13] = Integer.valueOf(id);
        return row;
    }

    private Cursor getSingleMessageFromIcc(int slotId, String msgIndex) {
        long token;
        Cursor cursor = null;
        try {
            int index = Integer.parseInt(msgIndex);
            token = Binder.clearCallingIdentity();
            getSmsManager(slotId);
            ArrayList<SmsMessage> msgs = SmsManager.getAllMessagesFromIcc();
            Binder.restoreCallingIdentity(token);
            if (msgs == null || msgs.isEmpty()) {
                Log.e(TAG, "getSingleMessageFromIcc messages is null");
            } else {
                SmsMessage msg = (SmsMessage) msgs.get(index);
                if (msg == null) {
                    throw new IllegalArgumentException("Message not retrieved. ID: " + msgIndex);
                }
                MatrixCursor cursor2 = new MatrixCursor(ICC_COLUMNS, SMS_ALL_ID);
                cursor2.addRow(convertIccToSms(msg, null, SMS_ALL));
                cursor = withIccNotificationUri(cursor2, slotId);
            }
            return cursor;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad SMS ICC ID: " + msgIndex);
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(token);
        }
    }

    private Cursor getAllMessagesFromIcc(int slotId) {
        ArrayList<SmsMessage> msgs = null;
        long token = Binder.clearCallingIdentity();
        try {
            getSmsManager(slotId);
            msgs = SmsManager.getAllMessagesFromIcc();
            if (msgs == null || msgs.isEmpty()) {
                Log.e(TAG, "getAllMessagesFromIcc messages is null");
                return null;
            }
            int count = msgs.size();
            MatrixCursor cursor = new MatrixCursor(ICC_COLUMNS, count);
            for (int i = SMS_ALL; i < count; i += SMS_ALL_ID) {
                SmsMessage msg = (SmsMessage) msgs.get(i);
                Pair<String, String> concatSms = null;
                if (msg != null) {
                    SmsHeader header = getSmsUserDataHeader(msg);
                    if (!(header == null || header.concatRef == null)) {
                        concatSms = getConcatSms(msgs, i);
                    }
                    cursor.addRow(convertIccToSms(msg, concatSms, i));
                }
            }
            return withIccNotificationUri(cursor, slotId);
        } finally {
            Binder.restoreCallingIdentity(token);
        }
    }

    private Cursor withIccNotificationUri(Cursor cursor, int slotId) {
        if (slotId == 0) {
            cursor.setNotificationUri(getContext().getContentResolver(), ICC_URI);
        } else if (slotId == SMS_ALL_ID) {
            cursor.setNotificationUri(getContext().getContentResolver(), ICC2_URI);
        }
        return cursor;
    }

    private Pair<String, String> getConcatSms(ArrayList<SmsMessage> msgs, int start) {
        SmsHeader header;
        int i;
        int refNumber = SMS_ALL;
        int msgCount = SMS_ALL;
        SmsMessage msg = (SmsMessage) msgs.get(start);
        if (msg != null) {
            header = getSmsUserDataHeader(msg);
            if (!(header == null || header.concatRef == null)) {
                msgCount = header.concatRef.msgCount;
                refNumber = header.concatRef.refNumber;
            }
        }
        ArrayList<SmsMessage> list = new ArrayList();
        list.add(msg);
        for (i = start + SMS_ALL_ID; i < msgs.size(); i += SMS_ALL_ID) {
            msg = (SmsMessage) msgs.get(i);
            if (msg != null) {
                header = getSmsUserDataHeader(msg);
                if (!(header == null || header.concatRef == null || refNumber != header.concatRef.refNumber)) {
                    list.add(msg);
                    msgs.set(i, null);
                    if (msgCount == list.size()) {
                        break;
                    }
                }
            }
        }
        StringBuilder index = new StringBuilder();
        StringBuilder body = new StringBuilder();
        for (i = SMS_ALL; i < msgCount; i += SMS_ALL_ID) {
            for (int j = SMS_ALL; j < list.size(); j += SMS_ALL_ID) {
                msg = (SmsMessage) list.get(j);
                if (i == getSmsUserDataHeader(msg).concatRef.seqNumber - 1) {
                    index.append(msg.getIndexOnIcc());
                    index.append(";");
                    body.append(msg.getDisplayMessageBody());
                    break;
                }
            }
        }
        return new Pair(index.toString(), body.toString());
    }

    private SmsManager getSmsManager(int slotId) {
        SmsManager mgr;
        if (MultiSimManager.getInstance().isMultiSimSupported()) {
            mgr = SmsManager.getDefault(slotId);
        } else {
            mgr = SmsManager.getDefault();
        }
        if (mgr == null) {
            Log.d(TAG, "getSmsManager is null");
        }
        return mgr;
    }

    private SmsHeader getSmsUserDataHeader(SmsMessage message) {
        return message.mWrappedSmsMessage.getUserDataHeader();
    }

    private String getRecipientAddress(SmsMessage message) {
        String address = LoggingEvents.EXTRA_CALLING_APP_NAME;
        SmsMessageBase smsMessageBase = message.mWrappedSmsMessage;
        try {
            Method getRecipientAddress = SmsMessageBase.class.getDeclaredMethod("getRecipientAddress", new Class[SMS_ALL]);
            Log.d(TAG, "convertIccToSms: getRecipientAddress is abstract");
            try {
                return (String) getRecipientAddress.invoke(smsMessageBase, new Object[SMS_ALL]);
            } catch (Exception e) {
                Log.e(TAG, "Couldn't invoke this method", e);
                return address;
            }
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return address;
        }
    }

    private void constructQueryForBox(SQLiteQueryBuilder qb, int type) {
        qb.setTables(TABLE_SMS);
        if (type != 0) {
            qb.appendWhere("type=" + type);
        }
    }

    private void constructQueryForUndelivered(SQLiteQueryBuilder qb) {
        qb.setTables(TABLE_SMS);
        qb.appendWhere("(type=4 OR type=5 OR type=6)");
    }

    public String getType(Uri url) {
        switch (url.getPathSegments().size()) {
            case SMS_ALL /*0*/:
                return VND_ANDROID_DIR_SMS;
            case SMS_ALL_ID /*1*/:
                try {
                    Integer.parseInt((String) url.getPathSegments().get(SMS_ALL));
                    return VND_ANDROID_SMS;
                } catch (NumberFormatException e) {
                    return VND_ANDROID_DIR_SMS;
                }
            case SMS_INBOX /*2*/:
                if (((String) url.getPathSegments().get(SMS_ALL)).equals("conversations")) {
                    return VND_ANDROID_SMSCHAT;
                }
                return VND_ANDROID_SMS;
            default:
                return null;
        }
    }

    public Uri insert(Uri url, ContentValues initialValues) {
        long token = Binder.clearCallingIdentity();
        try {
            Uri insertInner = insertInner(url, initialValues);
            return insertInner;
        } finally {
            Binder.restoreCallingIdentity(token);
        }
    }

    private Uri insertInner(Uri url, ContentValues initialValues) {
        boolean isBlocked;
        int blockType;
        boolean needFullInsertUri;
        int type;
        int match;
        String table;
        Integer typeObj;
        SQLiteDatabase db;
        ContentValues contentValues;
        ContentValues values;
        long rowID;
        String insertPath;
        boolean addDate;
        boolean addType;
        Integer read;
        long threadId;
        Long date;
        String address;
        Cursor c;
        boolean wasDeleted;
        int update;
        int syncState;
        Uri uri;
        Intent intent;
        MmsSmsUtils.setPreviousOpTime();
        OperationPerfProfiler operationPerfProfiler = new OperationPerfProfiler("insert " + url.toString());
        boolean callerIsSyncAdapter = MmsSmsUtils.callerIsSyncAdapter(url);
        boolean checkDuplication = MmsSmsUtils.checkDuplication(url);
        if (initialValues.containsKey("block_type")) {
            if (initialValues.getAsInteger("block_type").intValue() > SMS_ALL_ID) {
                isBlocked = true;
                blockType = isBlocked ? initialValues.getAsInteger("block_type").intValue() : SMS_ALL;
                if (initialValues != null) {
                    if (!(callerIsSyncAdapter || isBlocked)) {
                        if (initialValues.containsKey("deleted")) {
                            throw new IllegalArgumentException("The non-sync-callers AND non-blocked-url should not specify DELETED for inserting.");
                        }
                    }
                    if (!callerIsSyncAdapter) {
                        if (initialValues.containsKey("sync_state")) {
                            throw new IllegalArgumentException("The non-sync-callers should not specify DELETED and SYNC_STATE in values.");
                        }
                    }
                }
                needFullInsertUri = MmsSmsUtils.readBooleanQueryParameter(url, "need_full_insert_uri", LOCAL_LOGV);
                type = SMS_ALL;
                match = sURLMatcher.match(url);
                table = TABLE_SMS;
                switch (match) {
                    case SMS_ALL /*0*/:
                        typeObj = initialValues.getAsInteger(AntispamNumber.TYPE);
                        if (typeObj == null) {
                            type = SMS_ALL_ID;
                            break;
                        }
                        type = typeObj.intValue();
                        break;
                    case SMS_INBOX /*2*/:
                        type = SMS_ALL_ID;
                        break;
                    case SMS_SENT /*4*/:
                        type = SMS_INBOX;
                        break;
                    case SMS_DRAFT /*6*/:
                        type = SMS_INBOX_ID;
                        break;
                    case SMS_OUTBOX /*8*/:
                        type = SMS_SENT;
                        break;
                    case SMS_RAW_MESSAGE /*15*/:
                        table = TABLE_RAW;
                        break;
                    case SMS_ATTACHMENT /*16*/:
                        table = "attachments";
                        break;
                    case SMS_NEW_THREAD_ID /*18*/:
                        table = "canonical_addresses";
                        break;
                    case SMS_STATUS_PENDING /*21*/:
                        table = TABLE_SR_PENDING;
                        break;
                    case SMS_FAILED /*26*/:
                        type = SMS_SENT_ID;
                        break;
                    case SMS_QUEUED /*28*/:
                        type = SMS_DRAFT;
                        break;
                    default:
                        Log.e(TAG, "Invalid request: " + url);
                        return null;
                }
                db = this.mOpenHelper.getWritableDatabase();
                if (table.equals(TABLE_SMS)) {
                    if (initialValues != null) {
                        contentValues = new ContentValues(SMS_ALL_ID);
                    } else {
                        values = initialValues;
                    }
                    rowID = db.insert(table, null, values);
                    if (rowID > 0) {
                        return Uri.parse("content://" + table + "/" + rowID);
                    }
                }
                insertPath = LoggingEvents.EXTRA_CALLING_APP_NAME;
                if (needFullInsertUri) {
                    insertPath = "ignored/";
                }
                addDate = LOCAL_LOGV;
                addType = LOCAL_LOGV;
                if (initialValues != null) {
                    contentValues = new ContentValues(SMS_ALL_ID);
                    addDate = true;
                    addType = true;
                } else {
                    contentValues = new ContentValues(initialValues);
                    if (!initialValues.containsKey(WapPush.DATE)) {
                        addDate = true;
                    }
                    if (!initialValues.containsKey(AntispamNumber.TYPE)) {
                        addType = true;
                    }
                }
                MmsSmsUtils.removeSpaceForAddressValue(values, TagYellowPage.ADDRESS);
                if (initialValues.containsKey("import_sms")) {
                    values.remove("import_sms");
                }
                if (initialValues.containsKey("creator")) {
                    values.remove("creator");
                }
                if (initialValues.containsKey("phone_id")) {
                    values.remove("phone_id");
                }
                if (!values.containsKey("deleted")) {
                    values.put("deleted", Integer.valueOf(SMS_ALL));
                }
                if (callerIsSyncAdapter) {
                    values.put("sync_state", Integer.valueOf(SMS_ALL));
                } else {
                    if (!values.containsKey("sync_state")) {
                        values.put("sync_state", Integer.valueOf(SMS_INBOX));
                    }
                }
                if (addDate) {
                    values.put(WapPush.DATE, new Long(System.currentTimeMillis()));
                }
                if (addType && type != 0) {
                    values.put(AntispamNumber.TYPE, Integer.valueOf(type));
                }
                read = values.getAsInteger(WapPush.READ);
                if (read != null && read.intValue() == SMS_ALL_ID) {
                    values.put(WapPush.SEEN, read);
                    values.put("advanced_seen", Integer.valueOf(SMS_INBOX_ID));
                }
                if (values.containsKey("block_type")) {
                    if (values.getAsInteger("block_type").intValue() > SMS_ALL_ID) {
                        values.put("deleted", Integer.valueOf(SMS_ALL_ID));
                    }
                }
                db.beginTransaction();
                try {
                    threadId = MmsSmsUtils.getThreadId(getContext(), values);
                    values.remove("addresses");
                    if (!values.containsKey(WapPush.THREAD_ID) && threadId > 0) {
                        values.put(WapPush.THREAD_ID, Long.valueOf(threadId));
                    }
                    if (values.getAsInteger(AntispamNumber.TYPE).intValue() == SMS_INBOX_ID) {
                        db.delete(TABLE_SMS, "thread_id=" + values.getAsString(WapPush.THREAD_ID) + " AND type=" + SMS_INBOX_ID, null);
                    }
                    if (type != SMS_ALL_ID) {
                        values.put(WapPush.READ, ONE);
                    }
                    rowID = 0;
                    if (checkDuplication) {
                        date = values.getAsLong(WapPush.DATE);
                        address = values.getAsString(TagYellowPage.ADDRESS);
                        if (!(date == null || address == null)) {
                            c = null;
                            if (!(date == null || address == null)) {
                                String str = TABLE_SMS;
                                String[] strArr = new String[SMS_SENT_ID];
                                strArr[SMS_ALL] = "_id";
                                strArr[SMS_ALL_ID] = TagYellowPage.ADDRESS;
                                strArr[SMS_INBOX] = "sync_state";
                                strArr[SMS_INBOX_ID] = "marker";
                                strArr[SMS_SENT] = "deleted";
                                c = db.query(str, strArr, "date=" + date.toString(), null, null, null, null);
                            }
                            if (c != null && c.getCount() > 0) {
                                c.moveToPosition(-1);
                                while (c.moveToNext()) {
                                    if (MmsSmsUtils.getLast7DigitRev(address).equals(MmsSmsUtils.getLast7DigitRev(c.getString(SMS_ALL_ID)))) {
                                        rowID = c.getLong(SMS_ALL);
                                        wasDeleted = c.getInt(SMS_SENT) <= 0 ? true : LOCAL_LOGV;
                                        if (callerIsSyncAdapter) {
                                            update = db.update(TABLE_SMS, values, "_id=" + rowID, null);
                                            if (needFullInsertUri) {
                                                if (wasDeleted) {
                                                    insertPath = "updated/";
                                                } else {
                                                    insertPath = "restored/";
                                                }
                                            }
                                        } else {
                                            syncState = c.getInt(SMS_INBOX);
                                            long marker = c.getLong(SMS_INBOX_ID);
                                            Long newMarker = values.getAsLong("marker");
                                            if (newMarker != null && (newMarker.longValue() > marker || syncState == 65538)) {
                                                switch (syncState) {
                                                    case SMS_ALL /*0*/:
                                                        Log.i(TAG, "The state of downloaded message " + rowID + " is DIRTY. ignoring.");
                                                        break;
                                                    case SMS_ALL_ID /*1*/:
                                                        Log.w(TAG, "The state of downloaded message " + rowID + " is SYNCING.");
                                                        break;
                                                    case SMS_INBOX /*2*/:
                                                    case 65538:
                                                        break;
                                                    case SMS_INBOX_ID /*3*/:
                                                        Log.w(TAG, "The state of downloaded message " + rowID + " is SYNC_STATE_ERROR. Skip.");
                                                        break;
                                                    case SMS_SENT /*4*/:
                                                        Log.w(TAG, "The state of downloaded message " + rowID + " is SYNC_STATE_NOT_UPLOADALBE. Skip.");
                                                        break;
                                                }
                                                update = db.update(TABLE_SMS, values, "_id=" + rowID, null);
                                                if (needFullInsertUri) {
                                                    if (wasDeleted) {
                                                        insertPath = "updated/";
                                                    } else {
                                                        insertPath = "restored/";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (c != null) {
                                c.close();
                            }
                        }
                    }
                    if (rowID == 0) {
                        rowID = db.insert(table, TextBasedSmsCbColumns.BODY, values);
                        if (needFullInsertUri) {
                            insertPath = "inserted/";
                        }
                    }
                    if (rowID <= 0) {
                        uri = Uri.parse("content://" + table + "/" + insertPath + rowID);
                        if (threadId > 0) {
                            BatchModeHelper.getInstance().updateThread(getContext(), db, threadId);
                            if (isBlocked) {
                                BatchModeHelper.getInstance().updateBlockedThread(db, threadId, MmsSmsUtils.getAddressIdFromThreadId(db, threadId));
                                intent = new Intent("android.intent.action.FIREWALL_UPDATED");
                                if (blockType != SMS_INBOX_ID || blockType == 13 || blockType == SMS_DRAFT) {
                                    intent.putExtra("notification_show_type", SMS_ALL);
                                } else {
                                    intent.putExtra("notification_show_type", SMS_ALL_ID);
                                }
                                getContext().sendBroadcast(intent);
                            }
                        }
                        notifyChange(getContext(), uri, callerIsSyncAdapter);
                        if (isBlocked) {
                            notifyBlockedChange(getContext());
                            uri = uri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
                        }
                        db.setTransactionSuccessful();
                        operationPerfProfiler.prof();
                        db.endTransaction();
                        return uri;
                    }
                    Log.e(TAG, "insert: failed!");
                    db.endTransaction();
                } catch (Throwable th) {
                    db.endTransaction();
                }
                return null;
            }
        }
        isBlocked = LOCAL_LOGV;
        if (isBlocked) {
        }
        if (initialValues != null) {
            if (initialValues.containsKey("deleted")) {
                throw new IllegalArgumentException("The non-sync-callers AND non-blocked-url should not specify DELETED for inserting.");
            }
            if (callerIsSyncAdapter) {
                if (initialValues.containsKey("sync_state")) {
                    throw new IllegalArgumentException("The non-sync-callers should not specify DELETED and SYNC_STATE in values.");
                }
            }
        }
        needFullInsertUri = MmsSmsUtils.readBooleanQueryParameter(url, "need_full_insert_uri", LOCAL_LOGV);
        type = SMS_ALL;
        match = sURLMatcher.match(url);
        table = TABLE_SMS;
        switch (match) {
            case SMS_ALL /*0*/:
                typeObj = initialValues.getAsInteger(AntispamNumber.TYPE);
                if (typeObj == null) {
                    type = typeObj.intValue();
                    break;
                }
                type = SMS_ALL_ID;
                break;
            case SMS_INBOX /*2*/:
                type = SMS_ALL_ID;
                break;
            case SMS_SENT /*4*/:
                type = SMS_INBOX;
                break;
            case SMS_DRAFT /*6*/:
                type = SMS_INBOX_ID;
                break;
            case SMS_OUTBOX /*8*/:
                type = SMS_SENT;
                break;
            case SMS_RAW_MESSAGE /*15*/:
                table = TABLE_RAW;
                break;
            case SMS_ATTACHMENT /*16*/:
                table = "attachments";
                break;
            case SMS_NEW_THREAD_ID /*18*/:
                table = "canonical_addresses";
                break;
            case SMS_STATUS_PENDING /*21*/:
                table = TABLE_SR_PENDING;
                break;
            case SMS_FAILED /*26*/:
                type = SMS_SENT_ID;
                break;
            case SMS_QUEUED /*28*/:
                type = SMS_DRAFT;
                break;
            default:
                Log.e(TAG, "Invalid request: " + url);
                return null;
        }
        db = this.mOpenHelper.getWritableDatabase();
        if (table.equals(TABLE_SMS)) {
            if (initialValues != null) {
                values = initialValues;
            } else {
                contentValues = new ContentValues(SMS_ALL_ID);
            }
            rowID = db.insert(table, null, values);
            if (rowID > 0) {
                return Uri.parse("content://" + table + "/" + rowID);
            }
        }
        insertPath = LoggingEvents.EXTRA_CALLING_APP_NAME;
        if (needFullInsertUri) {
            insertPath = "ignored/";
        }
        addDate = LOCAL_LOGV;
        addType = LOCAL_LOGV;
        if (initialValues != null) {
            contentValues = new ContentValues(initialValues);
            if (initialValues.containsKey(WapPush.DATE)) {
                addDate = true;
            }
            if (initialValues.containsKey(AntispamNumber.TYPE)) {
                addType = true;
            }
        } else {
            contentValues = new ContentValues(SMS_ALL_ID);
            addDate = true;
            addType = true;
        }
        MmsSmsUtils.removeSpaceForAddressValue(values, TagYellowPage.ADDRESS);
        if (initialValues.containsKey("import_sms")) {
            values.remove("import_sms");
        }
        if (initialValues.containsKey("creator")) {
            values.remove("creator");
        }
        if (initialValues.containsKey("phone_id")) {
            values.remove("phone_id");
        }
        if (values.containsKey("deleted")) {
            values.put("deleted", Integer.valueOf(SMS_ALL));
        }
        if (callerIsSyncAdapter) {
            values.put("sync_state", Integer.valueOf(SMS_ALL));
        } else {
            if (values.containsKey("sync_state")) {
                values.put("sync_state", Integer.valueOf(SMS_INBOX));
            }
        }
        if (addDate) {
            values.put(WapPush.DATE, new Long(System.currentTimeMillis()));
        }
        values.put(AntispamNumber.TYPE, Integer.valueOf(type));
        read = values.getAsInteger(WapPush.READ);
        values.put(WapPush.SEEN, read);
        values.put("advanced_seen", Integer.valueOf(SMS_INBOX_ID));
        if (values.containsKey("block_type")) {
            if (values.getAsInteger("block_type").intValue() > SMS_ALL_ID) {
                values.put("deleted", Integer.valueOf(SMS_ALL_ID));
            }
        }
        db.beginTransaction();
        threadId = MmsSmsUtils.getThreadId(getContext(), values);
        values.remove("addresses");
        values.put(WapPush.THREAD_ID, Long.valueOf(threadId));
        if (values.getAsInteger(AntispamNumber.TYPE).intValue() == SMS_INBOX_ID) {
            db.delete(TABLE_SMS, "thread_id=" + values.getAsString(WapPush.THREAD_ID) + " AND type=" + SMS_INBOX_ID, null);
        }
        if (type != SMS_ALL_ID) {
            values.put(WapPush.READ, ONE);
        }
        rowID = 0;
        if (checkDuplication) {
            date = values.getAsLong(WapPush.DATE);
            address = values.getAsString(TagYellowPage.ADDRESS);
            c = null;
            String str2 = TABLE_SMS;
            String[] strArr2 = new String[SMS_SENT_ID];
            strArr2[SMS_ALL] = "_id";
            strArr2[SMS_ALL_ID] = TagYellowPage.ADDRESS;
            strArr2[SMS_INBOX] = "sync_state";
            strArr2[SMS_INBOX_ID] = "marker";
            strArr2[SMS_SENT] = "deleted";
            c = db.query(str2, strArr2, "date=" + date.toString(), null, null, null, null);
            c.moveToPosition(-1);
            while (c.moveToNext()) {
                if (MmsSmsUtils.getLast7DigitRev(address).equals(MmsSmsUtils.getLast7DigitRev(c.getString(SMS_ALL_ID)))) {
                    rowID = c.getLong(SMS_ALL);
                    if (c.getInt(SMS_SENT) <= 0) {
                    }
                    if (callerIsSyncAdapter) {
                        update = db.update(TABLE_SMS, values, "_id=" + rowID, null);
                        if (needFullInsertUri) {
                            if (wasDeleted) {
                                insertPath = "updated/";
                            } else {
                                insertPath = "restored/";
                            }
                        }
                    } else {
                        syncState = c.getInt(SMS_INBOX);
                        long marker2 = c.getLong(SMS_INBOX_ID);
                        Long newMarker2 = values.getAsLong("marker");
                        switch (syncState) {
                            case SMS_ALL /*0*/:
                                Log.i(TAG, "The state of downloaded message " + rowID + " is DIRTY. ignoring.");
                                break;
                            case SMS_ALL_ID /*1*/:
                                Log.w(TAG, "The state of downloaded message " + rowID + " is SYNCING.");
                                break;
                            case SMS_INBOX /*2*/:
                            case 65538:
                                break;
                            case SMS_INBOX_ID /*3*/:
                                Log.w(TAG, "The state of downloaded message " + rowID + " is SYNC_STATE_ERROR. Skip.");
                                break;
                            case SMS_SENT /*4*/:
                                Log.w(TAG, "The state of downloaded message " + rowID + " is SYNC_STATE_NOT_UPLOADALBE. Skip.");
                                break;
                        }
                        update = db.update(TABLE_SMS, values, "_id=" + rowID, null);
                        if (needFullInsertUri) {
                            if (wasDeleted) {
                                insertPath = "updated/";
                            } else {
                                insertPath = "restored/";
                            }
                        }
                    }
                    if (c != null) {
                        c.close();
                    }
                }
            }
            if (c != null) {
                c.close();
            }
        }
        if (rowID == 0) {
            rowID = db.insert(table, TextBasedSmsCbColumns.BODY, values);
            if (needFullInsertUri) {
                insertPath = "inserted/";
            }
        }
        if (rowID <= 0) {
            Log.e(TAG, "insert: failed!");
            db.endTransaction();
        } else {
            uri = Uri.parse("content://" + table + "/" + insertPath + rowID);
            if (threadId > 0) {
                BatchModeHelper.getInstance().updateThread(getContext(), db, threadId);
                if (isBlocked) {
                    BatchModeHelper.getInstance().updateBlockedThread(db, threadId, MmsSmsUtils.getAddressIdFromThreadId(db, threadId));
                    intent = new Intent("android.intent.action.FIREWALL_UPDATED");
                    if (blockType != SMS_INBOX_ID) {
                    }
                    intent.putExtra("notification_show_type", SMS_ALL);
                    getContext().sendBroadcast(intent);
                }
            }
            notifyChange(getContext(), uri, callerIsSyncAdapter);
            if (isBlocked) {
                notifyBlockedChange(getContext());
                uri = uri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
            }
            db.setTransactionSuccessful();
            operationPerfProfiler.prof();
            db.endTransaction();
            return uri;
        }
        return null;
    }

    public int delete(Uri url, String where, String[] whereArgs) {
        MmsSmsUtils.setPreviousOpTime();
        OperationPerfProfiler profiler = new OperationPerfProfiler("delete " + url + " with selection " + where);
        int count = SMS_ALL;
        int match = sURLMatcher.match(url);
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        Context context = getContext();
        switch (match) {
            case SMS_ALL /*0*/:
            case SMS_INBOX /*2*/:
                count = deleteMessages(context, db, where, whereArgs, url);
                break;
            case SMS_ALL_ID /*1*/:
            case SMS_INBOX_ID /*3*/:
                try {
                    long messageId = Long.parseLong(url.getLastPathSegment());
                    if (messageId > 0) {
                        count = deleteMessages(context, db, "_id=" + messageId, null, url);
                        break;
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Bad message id: " + url.getLastPathSegment());
                }
                break;
            case SMS_CONVERSATIONS_ID /*11*/:
                try {
                    long threadId = Long.parseLong((String) url.getPathSegments().get(SMS_ALL_ID));
                    if (threadId > 0) {
                        count = deleteMessages(context, db, MmsSmsUtils.concatenateWhere(where, "thread_id=" + threadId), null, url);
                        break;
                    }
                } catch (Exception e2) {
                    throw new IllegalArgumentException("Bad conversation thread id: " + ((String) url.getPathSegments().get(SMS_ALL_ID)));
                }
                break;
            case SMS_RAW_MESSAGE /*15*/:
                count = db.delete(TABLE_RAW, where, whereArgs);
                break;
            case SMS_STATUS_PENDING /*21*/:
                count = db.delete(TABLE_SR_PENDING, where, whereArgs);
                break;
            case SMS_ALL_ICC /*22*/:
                return deleteMessageFromIcc(SMS_ALL, url.getQueryParameter("msgIndex"));
            case SMS_ICC /*23*/:
                return deleteMessageFromIcc(SMS_ALL, (String) url.getPathSegments().get(SMS_ALL_ID));
            case SMS_ALL_ICC2 /*24*/:
                return deleteMessageFromIcc(SMS_ALL_ID, url.getQueryParameter("msgIndex"));
            case SMS_ICC2 /*25*/:
                return deleteMessageFromIcc(SMS_ALL_ID, (String) url.getPathSegments().get(SMS_ALL_ID));
            default:
                throw new IllegalArgumentException("Unknown URL");
        }
        profiler.prof();
        return count;
    }

    static int deleteMessages(Context context, SQLiteDatabase db, String selection, String[] selectionArgs, Uri uri) {
        boolean hasXiaomiAccount = MmsSmsUtils.hasXiaomiAccount(context);
        boolean callerIsSyncAdapter = MmsSmsUtils.callerIsSyncAdapter(uri);
        boolean hasBlockedFlag = MmsSmsUtils.hasBlockedFlag(uri);
        int count = SMS_ALL;
        db.beginTransaction();
        try {
            HashSet<Long> threadIds;
            if (!MmsSmsUtils.SUPPORT_SYNC_ADAPTER || !hasXiaomiAccount || callerIsSyncAdapter || hasBlockedFlag) {
                threadIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_SMS, "DISTINCT thread_id", selection, selectionArgs);
                count = db.delete(TABLE_SMS, selection, selectionArgs);
            } else {
                ContentValues values = new ContentValues();
                values.put("deleted", Integer.valueOf(SMS_ALL_ID));
                values.put("sync_state", Integer.valueOf(SMS_ALL));
                String finalSelection = MmsSmsUtils.concatenateWhere(selection, "deleted=0");
                threadIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_SMS, "DISTINCT thread_id", finalSelection, selectionArgs);
                count = db.update(TABLE_SMS, values, finalSelection, selectionArgs);
            }
            if (count > 0) {
                Iterator i$ = threadIds.iterator();
                while (i$.hasNext()) {
                    long threadId = ((Long) i$.next()).longValue();
                    if (hasBlockedFlag) {
                        BatchModeHelper.getInstance().updateBlockedThread(db, threadId, MmsSmsUtils.getAddressIdFromThreadId(db, threadId));
                    } else {
                        BatchModeHelper.getInstance().updateThread(context, db, threadId);
                    }
                }
                notifyChange(context, uri, callerIsSyncAdapter);
                if (hasBlockedFlag) {
                    notifyBlockedChange(context);
                }
            }
            db.setTransactionSuccessful();
            return count;
        } finally {
            db.endTransaction();
        }
    }

    private int deleteMessageFromIcc(int slotId, String msgIndexs) {
        ContentResolver cr;
        int count = SMS_ALL;
        long token;
        try {
            Log.d(TAG, "deleteMessageFromIcc msgIndexs = " + msgIndexs);
            String[] indexs = msgIndexs.split(";");
            SmsManager mgr = getSmsManager(slotId);
            token = Binder.clearCallingIdentity();
            String[] arr$ = indexs;
            int len$ = arr$.length;
            for (int i$ = SMS_ALL; i$ < len$; i$ += SMS_ALL_ID) {
                count += mgr.deleteMessageFromIcc(Integer.parseInt(arr$[i$])) ? SMS_ALL_ID : SMS_ALL;
            }
            Binder.restoreCallingIdentity(token);
            cr = getContext().getContentResolver();
            if (slotId == 0) {
                cr.notifyChange(ICC_URI, null);
            } else if (slotId == SMS_ALL_ID) {
                cr.notifyChange(ICC2_URI, null);
            }
            return count;
        } catch (NumberFormatException e) {
            try {
                throw new IllegalArgumentException("Bad SMS ICC ID: " + msgIndexs);
            } catch (Throwable th) {
                cr = getContext().getContentResolver();
                if (slotId == 0) {
                    cr.notifyChange(ICC_URI, null);
                } else if (slotId == SMS_ALL_ID) {
                    cr.notifyChange(ICC2_URI, null);
                }
            }
        } catch (Throwable th2) {
            Binder.restoreCallingIdentity(token);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int update(android.net.Uri r37, android.content.ContentValues r38, java.lang.String r39, java.lang.String[] r40) {
        /*
        r36 = this;
        com.android.providers.telephony.MmsSmsUtils.setPreviousOpTime();
        r20 = new com.android.providers.telephony.MmsSmsUtils$OperationPerfProfiler;
        r33 = new java.lang.StringBuilder;
        r33.<init>();
        r34 = "update ";
        r33 = r33.append(r34);
        r0 = r33;
        r1 = r37;
        r33 = r0.append(r1);
        r34 = " with selection ";
        r33 = r33.append(r34);
        r0 = r33;
        r1 = r39;
        r33 = r0.append(r1);
        r33 = r33.toString();
        r0 = r20;
        r1 = r33;
        r0.<init>(r1);
        r13 = com.android.providers.telephony.MmsSmsUtils.callerIsSyncAdapter(r37);
        r33 = com.android.providers.telephony.MmsSmsUtils.hasBlockedFlag(r37);
        if (r33 != 0) goto L_0x0047;
    L_0x003b:
        r33 = "block_type";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);
        if (r33 == 0) goto L_0x006c;
    L_0x0047:
        r10 = 1;
    L_0x0048:
        if (r13 != 0) goto L_0x006e;
    L_0x004a:
        if (r10 != 0) goto L_0x006e;
    L_0x004c:
        r33 = "deleted";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);
        if (r33 != 0) goto L_0x0064;
    L_0x0058:
        r33 = "sync_state";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);
        if (r33 == 0) goto L_0x006e;
    L_0x0064:
        r33 = new java.lang.IllegalArgumentException;
        r34 = "The non-sync-callers should not specify DELETED and SYNC_STATE in values.";
        r33.<init>(r34);
        throw r33;
    L_0x006c:
        r10 = 0;
        goto L_0x0048;
    L_0x006e:
        r15 = 0;
        r22 = "sms";
        r17 = 0;
        r0 = r36;
        r0 = r0.mOpenHelper;
        r33 = r0;
        r5 = r33.getWritableDatabase();
        r33 = sURLMatcher;
        r0 = r33;
        r1 = r37;
        r33 = r0.match(r1);
        switch(r33) {
            case 0: goto L_0x00af;
            case 1: goto L_0x027e;
            case 2: goto L_0x00af;
            case 3: goto L_0x02a7;
            case 4: goto L_0x00af;
            case 5: goto L_0x02a7;
            case 6: goto L_0x00af;
            case 7: goto L_0x02a7;
            case 8: goto L_0x00af;
            case 9: goto L_0x02a7;
            case 10: goto L_0x00af;
            case 11: goto L_0x02d0;
            case 12: goto L_0x008a;
            case 13: goto L_0x008a;
            case 14: goto L_0x008a;
            case 15: goto L_0x00ad;
            case 16: goto L_0x008a;
            case 17: goto L_0x008a;
            case 18: goto L_0x008a;
            case 19: goto L_0x008a;
            case 20: goto L_0x0313;
            case 21: goto L_0x027a;
            case 22: goto L_0x008a;
            case 23: goto L_0x008a;
            case 24: goto L_0x008a;
            case 25: goto L_0x008a;
            case 26: goto L_0x00af;
            case 27: goto L_0x02a7;
            case 28: goto L_0x00af;
            case 29: goto L_0x008a;
            case 30: goto L_0x033c;
            case 31: goto L_0x0355;
            default: goto L_0x008a;
        };
    L_0x008a:
        r33 = new java.lang.UnsupportedOperationException;
        r34 = new java.lang.StringBuilder;
        r34.<init>();
        r35 = "URI ";
        r34 = r34.append(r35);
        r0 = r34;
        r1 = r37;
        r34 = r0.append(r1);
        r35 = " not supported";
        r34 = r34.append(r35);
        r34 = r34.toString();
        r33.<init>(r34);
        throw r33;
    L_0x00ad:
        r22 = "raw";
    L_0x00af:
        r0 = r39;
        r1 = r17;
        r39 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);
        r33 = "address";
        r0 = r38;
        r1 = r33;
        com.android.providers.telephony.MmsSmsUtils.removeSpaceForAddressValue(r0, r1);
        r33 = "read";
        r0 = r38;
        r1 = r33;
        r21 = r0.getAsInteger(r1);
        if (r21 == 0) goto L_0x00f4;
    L_0x00cc:
        r33 = r21.intValue();
        r34 = 1;
        r0 = r33;
        r1 = r34;
        if (r0 != r1) goto L_0x00f4;
    L_0x00d8:
        r33 = "seen";
        r0 = r38;
        r1 = r33;
        r2 = r21;
        r0.put(r1, r2);
        r33 = "advanced_seen";
        r34 = 3;
        r34 = java.lang.Integer.valueOf(r34);
        r0 = r38;
        r1 = r33;
        r2 = r34;
        r0.put(r1, r2);
    L_0x00f4:
        r33 = "block_type";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);
        if (r33 == 0) goto L_0x0145;
    L_0x0100:
        r33 = "block_type";
        r0 = r38;
        r1 = r33;
        r33 = r0.getAsInteger(r1);
        r12 = r33.intValue();
        r33 = 1;
        r0 = r33;
        if (r12 <= r0) goto L_0x03b7;
    L_0x0114:
        r33 = "deleted";
        r34 = 1;
        r34 = java.lang.Integer.valueOf(r34);
        r0 = r38;
        r1 = r33;
        r2 = r34;
        r0.put(r1, r2);
        r33 = r36.getContext();
        r0 = r33;
        r1 = r37;
        r33 = com.android.providers.telephony.MmsSmsUtils.inPrivateWhiteList(r0, r1);
        if (r33 == 0) goto L_0x03a7;
    L_0x0133:
        r0 = r37;
        r1 = r39;
        r39 = com.android.providers.telephony.MmsSmsUtils.getSelectionByPrivatePermission(r0, r1);
    L_0x013b:
        r33 = "type=1";
        r0 = r39;
        r1 = r33;
        r39 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);
    L_0x0145:
        if (r13 == 0) goto L_0x03ca;
    L_0x0147:
        r33 = "sync_state";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);
        if (r33 != 0) goto L_0x0164;
    L_0x0153:
        r33 = "sync_state";
        r34 = 2;
        r34 = java.lang.Integer.valueOf(r34);
        r0 = r38;
        r1 = r33;
        r2 = r34;
        r0.put(r1, r2);
    L_0x0164:
        r11 = 0;
        r33 = r36.getContext();
        r0 = r33;
        r1 = r37;
        r33 = com.android.providers.telephony.MmsSmsUtils.inBlockedWhiteList(r0, r1);
        if (r33 == 0) goto L_0x03fd;
    L_0x0173:
        r11 = com.android.providers.telephony.MmsSmsUtils.getSelectionByBlockedPermission(r37);
    L_0x0177:
        r33 = android.text.TextUtils.isEmpty(r11);
        if (r33 == 0) goto L_0x040b;
    L_0x017d:
        if (r13 != 0) goto L_0x0189;
    L_0x017f:
        r33 = "deleted=0";
        r0 = r39;
        r1 = r33;
        r39 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);
    L_0x0189:
        r5.beginTransaction();
        r33 = "sms";
        r34 = "DISTINCT thread_id";
        r0 = r33;
        r1 = r34;
        r2 = r39;
        r3 = r40;
        r23 = com.android.providers.telephony.MmsSmsUtils.queryLongValuesToSet(r5, r0, r1, r2, r3);	 Catch:{ all -> 0x0275 }
        r0 = r22;
        r1 = r38;
        r2 = r39;
        r3 = r40;
        r15 = r5.update(r0, r1, r2, r3);	 Catch:{ all -> 0x0275 }
        if (r15 <= 0) goto L_0x0519;
    L_0x01aa:
        r30 = 0;
        r29 = 0;
        r32 = 0;
        r27 = 0;
        r31 = 0;
        r28 = 0;
        r26 = 0;
        r25 = 0;
        r24 = 0;
        r33 = "thread_id";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x01d4;
    L_0x01c8:
        r30 = 1;
        r29 = 1;
        r32 = 1;
        r27 = 1;
        r31 = 1;
        r28 = 1;
    L_0x01d4:
        r33 = "type";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x01e8;
    L_0x01e0:
        r29 = 1;
        r32 = 1;
        r27 = 1;
        r28 = 1;
    L_0x01e8:
        r33 = "read";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x01f8;
    L_0x01f4:
        if (r10 != 0) goto L_0x01f8;
    L_0x01f6:
        r32 = 1;
    L_0x01f8:
        r33 = "body";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x0206;
    L_0x0204:
        r31 = 1;
    L_0x0206:
        r33 = "date";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x0216;
    L_0x0212:
        r31 = 1;
        r28 = 1;
    L_0x0216:
        r33 = "sim_id";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x0226;
    L_0x0222:
        if (r10 != 0) goto L_0x0226;
    L_0x0224:
        r28 = 1;
    L_0x0226:
        if (r10 == 0) goto L_0x0236;
    L_0x0228:
        r33 = "read";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x0413;
    L_0x0234:
        r26 = 1;
    L_0x0236:
        r4 = com.android.providers.telephony.BatchModeHelper.getInstance();	 Catch:{ all -> 0x0275 }
        if (r30 == 0) goto L_0x0255;
    L_0x023c:
        r33 = r36.getContext();	 Catch:{ all -> 0x0275 }
        r34 = "thread_id";
        r0 = r38;
        r1 = r34;
        r34 = r0.getAsLong(r1);	 Catch:{ all -> 0x0275 }
        r34 = r34.longValue();	 Catch:{ all -> 0x0275 }
        r0 = r33;
        r1 = r34;
        r4.updateThread(r0, r5, r1);	 Catch:{ all -> 0x0275 }
    L_0x0255:
        if (r29 == 0) goto L_0x0421;
    L_0x0257:
        r18 = r23.iterator();	 Catch:{ all -> 0x0275 }
    L_0x025b:
        r33 = r18.hasNext();	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x0421;
    L_0x0261:
        r33 = r18.next();	 Catch:{ all -> 0x0275 }
        r33 = (java.lang.Long) r33;	 Catch:{ all -> 0x0275 }
        r6 = r33.longValue();	 Catch:{ all -> 0x0275 }
        r33 = r36.getContext();	 Catch:{ all -> 0x0275 }
        r0 = r33;
        r4.updateThreadMessageCount(r0, r5, r6);	 Catch:{ all -> 0x0275 }
        goto L_0x025b;
    L_0x0275:
        r33 = move-exception;
        r5.endTransaction();
        throw r33;
    L_0x027a:
        r22 = "sr_pending";
        goto L_0x00af;
    L_0x027e:
        r33 = new java.lang.StringBuilder;
        r33.<init>();
        r34 = "_id=";
        r34 = r33.append(r34);
        r33 = r37.getPathSegments();
        r35 = 0;
        r0 = r33;
        r1 = r35;
        r33 = r0.get(r1);
        r33 = (java.lang.String) r33;
        r0 = r34;
        r1 = r33;
        r33 = r0.append(r1);
        r17 = r33.toString();
        goto L_0x00af;
    L_0x02a7:
        r33 = new java.lang.StringBuilder;
        r33.<init>();
        r34 = "_id=";
        r34 = r33.append(r34);
        r33 = r37.getPathSegments();
        r35 = 1;
        r0 = r33;
        r1 = r35;
        r33 = r0.get(r1);
        r33 = (java.lang.String) r33;
        r0 = r34;
        r1 = r33;
        r33 = r0.append(r1);
        r17 = r33.toString();
        goto L_0x00af;
    L_0x02d0:
        r33 = r37.getPathSegments();
        r34 = 1;
        r6 = r33.get(r34);
        r6 = (java.lang.String) r6;
        java.lang.Long.parseLong(r6);	 Catch:{ Exception -> 0x02f6 }
        r33 = new java.lang.StringBuilder;
        r33.<init>();
        r34 = "thread_id=";
        r33 = r33.append(r34);
        r0 = r33;
        r33 = r0.append(r6);
        r17 = r33.toString();
        goto L_0x00af;
    L_0x02f6:
        r16 = move-exception;
        r33 = "SmsProvider";
        r34 = new java.lang.StringBuilder;
        r34.<init>();
        r35 = "Bad conversation thread id: ";
        r34 = r34.append(r35);
        r0 = r34;
        r34 = r0.append(r6);
        r34 = r34.toString();
        android.util.Log.e(r33, r34);
        goto L_0x00af;
    L_0x0313:
        r33 = new java.lang.StringBuilder;
        r33.<init>();
        r34 = "_id=";
        r34 = r33.append(r34);
        r33 = r37.getPathSegments();
        r35 = 1;
        r0 = r33;
        r1 = r35;
        r33 = r0.get(r1);
        r33 = (java.lang.String) r33;
        r0 = r34;
        r1 = r33;
        r33 = r0.append(r1);
        r17 = r33.toString();
        goto L_0x00af;
    L_0x033c:
        r33 = new java.lang.StringBuilder;
        r33.<init>();
        r34 = "mx_id=";
        r33 = r33.append(r34);
        r34 = android.content.ContentUris.parseId(r37);
        r33 = r33.append(r34);
        r17 = r33.toString();
        goto L_0x00af;
    L_0x0355:
        r33 = r37.getPathSegments();
        r34 = 1;
        r14 = r33.get(r34);
        r14 = (java.lang.String) r14;
        java.lang.Integer.parseInt(r14);	 Catch:{ Exception -> 0x0389 }
        r33 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0389 }
        r33.<init>();	 Catch:{ Exception -> 0x0389 }
        r34 = "exists (SELECT 1 FROM threads WHERE threads._id=sms.thread_id AND threads.sp_type=";
        r33 = r33.append(r34);	 Catch:{ Exception -> 0x0389 }
        r0 = r33;
        r33 = r0.append(r14);	 Catch:{ Exception -> 0x0389 }
        r34 = ")";
        r33 = r33.append(r34);	 Catch:{ Exception -> 0x0389 }
        r33 = r33.toString();	 Catch:{ Exception -> 0x0389 }
        r0 = r39;
        r1 = r33;
        r39 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r1);	 Catch:{ Exception -> 0x0389 }
        goto L_0x00af;
    L_0x0389:
        r16 = move-exception;
        r33 = "SmsProvider";
        r34 = new java.lang.StringBuilder;
        r34.<init>();
        r35 = "Bad service category id: ";
        r34 = r34.append(r35);
        r0 = r34;
        r34 = r0.append(r14);
        r34 = r34.toString();
        android.util.Log.e(r33, r34);
        r33 = 0;
    L_0x03a6:
        return r33;
    L_0x03a7:
        r33 = r36.getContext();
        r0 = r37;
        r1 = r33;
        r2 = r39;
        r39 = com.android.providers.telephony.MmsSmsUtils.getSelectionByPrivatePermission(r0, r1, r2);
        goto L_0x013b;
    L_0x03b7:
        r33 = "deleted";
        r34 = 0;
        r34 = java.lang.Integer.valueOf(r34);
        r0 = r38;
        r1 = r33;
        r2 = r34;
        r0.put(r1, r2);
        goto L_0x013b;
    L_0x03ca:
        r33 = r38.keySet();
        r18 = r33.iterator();
    L_0x03d2:
        r33 = r18.hasNext();
        if (r33 == 0) goto L_0x0164;
    L_0x03d8:
        r19 = r18.next();
        r19 = (java.lang.String) r19;
        r33 = SYNC_COLUMNS;
        r0 = r33;
        r1 = r19;
        r33 = r0.contains(r1);
        if (r33 == 0) goto L_0x03d2;
    L_0x03ea:
        r33 = "sync_state";
        r34 = 0;
        r34 = java.lang.Integer.valueOf(r34);
        r0 = r38;
        r1 = r33;
        r2 = r34;
        r0.put(r1, r2);
        goto L_0x0164;
    L_0x03fd:
        r33 = r36.getContext();
        r0 = r37;
        r1 = r33;
        r11 = com.android.providers.telephony.MmsSmsUtils.getSelectionByBlockedPermission(r0, r1);
        goto L_0x0177;
    L_0x040b:
        r0 = r39;
        r39 = com.android.providers.telephony.MmsSmsUtils.concatenateWhere(r0, r11);
        goto L_0x0189;
    L_0x0413:
        r29 = 1;
        r32 = 1;
        r27 = 1;
        r31 = 1;
        r25 = 1;
        r24 = 1;
        goto L_0x0236;
    L_0x0421:
        if (r32 == 0) goto L_0x043b;
    L_0x0423:
        r18 = r23.iterator();	 Catch:{ all -> 0x0275 }
    L_0x0427:
        r33 = r18.hasNext();	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x043b;
    L_0x042d:
        r33 = r18.next();	 Catch:{ all -> 0x0275 }
        r33 = (java.lang.Long) r33;	 Catch:{ all -> 0x0275 }
        r6 = r33.longValue();	 Catch:{ all -> 0x0275 }
        r4.updateThreadUnreadCount(r5, r6);	 Catch:{ all -> 0x0275 }
        goto L_0x0427;
    L_0x043b:
        if (r27 == 0) goto L_0x0455;
    L_0x043d:
        r18 = r23.iterator();	 Catch:{ all -> 0x0275 }
    L_0x0441:
        r33 = r18.hasNext();	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x0455;
    L_0x0447:
        r33 = r18.next();	 Catch:{ all -> 0x0275 }
        r33 = (java.lang.Long) r33;	 Catch:{ all -> 0x0275 }
        r6 = r33.longValue();	 Catch:{ all -> 0x0275 }
        r4.updateThreadErrorState(r5, r6);	 Catch:{ all -> 0x0275 }
        goto L_0x0441;
    L_0x0455:
        if (r31 == 0) goto L_0x0475;
    L_0x0457:
        r18 = r23.iterator();	 Catch:{ all -> 0x0275 }
    L_0x045b:
        r33 = r18.hasNext();	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x0475;
    L_0x0461:
        r33 = r18.next();	 Catch:{ all -> 0x0275 }
        r33 = (java.lang.Long) r33;	 Catch:{ all -> 0x0275 }
        r6 = r33.longValue();	 Catch:{ all -> 0x0275 }
        r33 = r36.getContext();	 Catch:{ all -> 0x0275 }
        r0 = r33;
        r4.updateThreadSnippet(r0, r5, r6);	 Catch:{ all -> 0x0275 }
        goto L_0x045b;
    L_0x0475:
        if (r28 == 0) goto L_0x048f;
    L_0x0477:
        r18 = r23.iterator();	 Catch:{ all -> 0x0275 }
    L_0x047b:
        r33 = r18.hasNext();	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x048f;
    L_0x0481:
        r33 = r18.next();	 Catch:{ all -> 0x0275 }
        r33 = (java.lang.Long) r33;	 Catch:{ all -> 0x0275 }
        r6 = r33.longValue();	 Catch:{ all -> 0x0275 }
        r4.updateThreadLastSimId(r5, r6);	 Catch:{ all -> 0x0275 }
        goto L_0x047b;
    L_0x048f:
        if (r26 == 0) goto L_0x04a9;
    L_0x0491:
        r18 = r23.iterator();	 Catch:{ all -> 0x0275 }
    L_0x0495:
        r33 = r18.hasNext();	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x04a9;
    L_0x049b:
        r33 = r18.next();	 Catch:{ all -> 0x0275 }
        r33 = (java.lang.Long) r33;	 Catch:{ all -> 0x0275 }
        r6 = r33.longValue();	 Catch:{ all -> 0x0275 }
        r4.updateBlockedThreadUnreadCount(r5, r6);	 Catch:{ all -> 0x0275 }
        goto L_0x0495;
    L_0x04a9:
        if (r25 == 0) goto L_0x04c7;
    L_0x04ab:
        r18 = r23.iterator();	 Catch:{ all -> 0x0275 }
    L_0x04af:
        r33 = r18.hasNext();	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x04c7;
    L_0x04b5:
        r33 = r18.next();	 Catch:{ all -> 0x0275 }
        r33 = (java.lang.Long) r33;	 Catch:{ all -> 0x0275 }
        r6 = r33.longValue();	 Catch:{ all -> 0x0275 }
        r8 = com.android.providers.telephony.MmsSmsUtils.getAddressIdFromThreadId(r5, r6);	 Catch:{ all -> 0x0275 }
        r4.updateBlockedThread(r5, r6, r8);	 Catch:{ all -> 0x0275 }
        goto L_0x04af;
    L_0x04c7:
        if (r24 == 0) goto L_0x04e1;
    L_0x04c9:
        r18 = r23.iterator();	 Catch:{ all -> 0x0275 }
    L_0x04cd:
        r33 = r18.hasNext();	 Catch:{ all -> 0x0275 }
        if (r33 == 0) goto L_0x04e1;
    L_0x04d3:
        r33 = r18.next();	 Catch:{ all -> 0x0275 }
        r33 = (java.lang.Long) r33;	 Catch:{ all -> 0x0275 }
        r6 = r33.longValue();	 Catch:{ all -> 0x0275 }
        r4.updateBlockedThreadLastSimId(r5, r6);	 Catch:{ all -> 0x0275 }
        goto L_0x04cd;
    L_0x04e1:
        r33 = r38.size();	 Catch:{ all -> 0x0275 }
        r34 = 1;
        r0 = r33;
        r1 = r34;
        if (r0 > r1) goto L_0x0505;
    L_0x04ed:
        r33 = r38.size();	 Catch:{ all -> 0x0275 }
        r34 = 1;
        r0 = r33;
        r1 = r34;
        if (r0 != r1) goto L_0x0519;
    L_0x04f9:
        r33 = "sync_state";
        r0 = r38;
        r1 = r33;
        r33 = r0.containsKey(r1);	 Catch:{ all -> 0x0275 }
        if (r33 != 0) goto L_0x0519;
    L_0x0505:
        r33 = r36.getContext();	 Catch:{ all -> 0x0275 }
        r0 = r33;
        r1 = r37;
        notifyChange(r0, r1, r13);	 Catch:{ all -> 0x0275 }
        if (r10 == 0) goto L_0x0519;
    L_0x0512:
        r33 = r36.getContext();	 Catch:{ all -> 0x0275 }
        notifyBlockedChange(r33);	 Catch:{ all -> 0x0275 }
    L_0x0519:
        r5.setTransactionSuccessful();	 Catch:{ all -> 0x0275 }
        r5.endTransaction();
        r20.prof();
        r33 = r15;
        goto L_0x03a6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.SmsProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private static void notifyChange(Context context, Uri uri, boolean callerIsSyncAdapter) {
        BatchModeHelper helper = BatchModeHelper.getInstance();
        helper.notifyChange(context, uri);
        helper.notifyChange(context, MmsSms.CONTENT_CONVERSATIONS_URI);
        helper.notifyChange(context, ExtraTelephony.MmsSms.CONTENT_ALL_LOCKED_URI);
        helper.notifyChange(context, ExtraTelephony.MmsSms.CONTENT_PREVIEW_URI);
        if (!callerIsSyncAdapter) {
            helper.requestSync(context, "local.sms.sync");
        }
    }

    private static void notifyBlockedChange(Context context) {
        BatchModeHelper helper = BatchModeHelper.getInstance();
        helper.notifyChange(context, ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI);
        helper.notifyChange(context, ExtraTelephony.MmsSms.BLOCKED_THREAD_CONTENT_URI);
    }
}
