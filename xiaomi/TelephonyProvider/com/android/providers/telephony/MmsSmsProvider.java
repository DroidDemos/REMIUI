package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.os.SystemProperties;
import android.provider.MiuiSettings.Secure;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import android.util.Log;
import com.android.common.Search;
import com.android.common.speech.LoggingEvents;
import com.android.providers.telephony.MmsSmsUtils.OperationPerfProfiler;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.SmsCb;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.SmsCb.Conversations;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.TextBasedSmsCbColumns;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import miui.provider.ExtraTelephony.Hms;
import miui.provider.ExtraTelephony.PrivateAddresses;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;
import miui.provider.yellowpage.YellowPageUtils.PhoneLookup;
import miui.provider.yellowpage.YellowPageUtils.TagYellowPage;

public class MmsSmsProvider extends ContentProvider {
    private static final String AUTHORITY = "mms-sms";
    public static final String BLOCKED_MSG_CONDITION = "deleted = 1 AND block_type > 1";
    private static final String[] CANONICAL_ADDRESSES_COLUMNS_1;
    private static final String[] CANONICAL_ADDRESSES_COLUMNS_2;
    private static final String[] EMPTY_STRING_ARRAY;
    private static final String HMS_EXTENSION_QUERY = "SELECT hms._id AS _id, thread_id, mx_content AS body, NULL as sub, hms.date AS date, threads.date as thread_date, hms.mx_type AS mx_type, hms.mx_extension AS mx_extension FROM hms JOIN threads ON hms.thread_id=threads._id AND threads.sp_type>=1 WHERE %s";
    private static final String HMS_EXTENSION_QUERY_SP = "SELECT hms._id AS _id, thread_id, mx_content AS body, NULL as sub, hms.date AS date, threads.date as thread_date FROM hms JOIN threads ON hms.thread_id=threads._id AND threads.sp_type>=1 WHERE %s";
    private static final String HMS_QUERY = "SELECT hms._id AS _id, thread_id, hms.snippet AS body, NULL as sub, hms.date AS date, threads.date as thread_date , hms.mx_type AS mx_type, hms.mx_extension AS mx_extension FROM hms JOIN threads ON hms.thread_id=threads._id AND threads.sp_type>=1 WHERE %s";
    private static final String HMS_QUERY_SP = "SELECT hms._id AS _id, thread_id, hms.snippet AS body, NULL as sub, hms.date AS date, threads.date as thread_date FROM hms JOIN threads ON hms.thread_id=threads._id AND threads.sp_type>=1 WHERE %s";
    private static final String HMS_QUERY_TAIL = " SELECT * from (SELECT hms._id AS _id, thread_id, hms.snippet AS body, NULL as sub, hms.date AS date, threads.date as thread_date , hms.mx_type AS mx_type, hms.mx_extension AS mx_extension FROM hms JOIN threads ON hms.thread_id=threads._id AND threads.sp_type>=1 WHERE %s UNION SELECT hms._id AS _id, thread_id, mx_content AS body, NULL as sub, hms.date AS date, threads.date as thread_date, hms.mx_type AS mx_type, hms.mx_extension AS mx_extension FROM hms JOIN threads ON hms.thread_id=threads._id AND threads.sp_type>=1 WHERE %s) GROUP BY _id ORDER BY thread_date DESC, thread_id DESC, date DESC";
    private static final String[] ID_PROJECTION;
    private static final boolean LOCAL_LOGV = false;
    private static final Set<String> MMS_COLUMNS;
    private static final String MMS_CONVERSATION_CONSTRAINT = "(msg_box != 3 AND (m_type = 128 OR m_type = 132 OR m_type = 130))";
    private static final String[] MMS_ONLY_COLUMNS;
    private static final String MMS_QUERY = "SELECT -pdu._id AS _id, thread_id, part.text AS body, NULL AS sub, pdu.date * 1000 + pdu.date_ms_part AS date, threads.date AS thread_date, pdu.mx_type AS mx_type, pdu.mx_extension AS mx_extension FROM threads,pdu,part ON threads._id=pdu.thread_id AND part.mid=pdu._id AND threads.private_addr_ids is %s WHERE pdu.deleted = 0 AND part.ct='text/plain' AND %s";
    private static final String MMS_QUERY_FOR_SP = "SELECT -pdu._id AS _id, thread_id, part.text AS body, NULL AS sub, pdu.date * 1000 + pdu.date_ms_part AS date, threads.date AS thread_date FROM threads,pdu,part ON threads._id=pdu.thread_id AND part.mid=pdu._id AND threads.private_addr_ids is NULL AND threads.sp_type>=1 WHERE pdu.deleted = 0 AND part.ct='text/plain' AND %s";
    private static final String[] MMS_SMS_COLUMNS;
    private static final String MMS_SUBJECT_QUERY = "SELECT -pdu._id AS _id, thread_id, NULL as body, sub,pdu.date * 1000 + pdu.date_ms_part AS date, threads.date as thread_date, pdu.mx_type AS mx_type, pdu.mx_extension AS mx_extension FROM pdu JOIN threads ON pdu.thread_id=threads._id AND threads.private_addr_ids is %s WHERE pdu.deleted = 0 AND %s AND sub_cs=106";
    private static final String MMS_SUBJECT_QUERY_FOR_SP = "SELECT -pdu._id AS _id, thread_id, NULL as body, sub,pdu.date * 1000 + pdu.date_ms_part AS date, threads.date as thread_date FROM pdu JOIN threads ON pdu.thread_id=threads._id AND threads.private_addr_ids is NULL AND threads.sp_type>=1 WHERE pdu.deleted = 0 AND %s AND sub_cs=106";
    public static final String NORMAL_MSG_CONDITION = "block_type <= 1";
    private static final String NO_DELETES_INSERTS_OR_UPDATES = "MmsSmsProvider does not support deletes, inserts, or updates for this URI.";
    public static final String PRIVATE_MSG_CONDITION = "thread_id IN (select _id from threads where private_addr_ids is %s)";
    private static final String SEARCH_COUNT_QUERY = "SELECT ((SELECT COUNT(_id) FROM sms WHERE (deleted=0) AND (%s)) + (SELECT COUNT(_id) FROM hms WHERE (%s)) + (SELECT COUNT(_id) FROM pdu WHERE (deleted=0) AND (%s) AND (m_type=128 OR m_type=132 OR m_type=130)))";
    private static final String SEARCH_SP_COUNT_QUERY = "SELECT ((SELECT COUNT(_id) FROM sms WHERE (deleted=0) AND block_type<=1 AND exists (SELECT 1 FROM threads WHERE threads.sp_type>=1 AND threads.private_addr_ids IS NULL AND threads._id=sms.thread_id)) + (SELECT COUNT(_id) FROM hms WHERE exists (SELECT 1 FROM threads WHERE threads.sp_type>=1 AND threads.private_addr_ids IS NULL AND threads._id=hms.thread_id) + (SELECT COUNT(_id) FROM pdu WHERE (deleted=0) AND (m_type=128 OR m_type=132 OR m_type=130) AND block_type<=1 AND exists (SELECT 1 FROM threads WHERE threads.sp_type >= 1 AND threads.private_addr_ids IS NULL AND threads._id=pdu.thread_id)))";
    private static final Set<String> SMS_COLUMNS;
    private static final String SMS_CONVERSATION_CONSTRAINT = "(type != 3)";
    private static final String SMS_MMS_HMS_QUERY_FOR_SP = "SELECT sms._id AS _id, thread_id, body, NULL as sub, sms.date AS date, threads.date as thread_date FROM sms JOIN threads ON sms.thread_id=threads._id AND threads.private_addr_ids is NULL AND threads.sp_type>=1 WHERE sms.deleted = 0 AND %s GROUP BY thread_id, sms.date UNION SELECT * from (SELECT -pdu._id AS _id, thread_id, NULL as body, sub,pdu.date * 1000 + pdu.date_ms_part AS date, threads.date as thread_date FROM pdu JOIN threads ON pdu.thread_id=threads._id AND threads.private_addr_ids is NULL AND threads.sp_type>=1 WHERE pdu.deleted = 0 AND %s AND sub_cs=106 UNION SELECT -pdu._id AS _id, thread_id, part.text AS body, NULL AS sub, pdu.date * 1000 + pdu.date_ms_part AS date, threads.date AS thread_date FROM threads,pdu,part ON threads._id=pdu.thread_id AND part.mid=pdu._id AND threads.private_addr_ids is NULL AND threads.sp_type>=1 WHERE pdu.deleted = 0 AND part.ct='text/plain' AND %s) GROUP BY _id UNION SELECT * from (SELECT hms._id AS _id, thread_id, hms.snippet AS body, NULL as sub, hms.date AS date, threads.date as thread_date FROM hms JOIN threads ON hms.thread_id=threads._id AND threads.sp_type>=1 WHERE %s UNION SELECT hms._id AS _id, thread_id, mx_content AS body, NULL as sub, hms.date AS date, threads.date as thread_date FROM hms JOIN threads ON hms.thread_id=threads._id AND threads.sp_type>=1 WHERE %s) GROUP BY _id ORDER BY thread_date DESC, thread_id DESC, date DESC";
    private static final String SMS_MMS_QUERY = "SELECT sms._id AS _id, thread_id, body, NULL as sub, sms.date AS date, threads.date as thread_date, NULL as mx_type, NULL as mx_extension FROM sms JOIN threads ON sms.thread_id=threads._id AND threads.private_addr_ids is %s WHERE sms.deleted = 0 AND %s GROUP BY thread_id, sms.date UNION SELECT * from (SELECT -pdu._id AS _id, thread_id, NULL as body, sub,pdu.date * 1000 + pdu.date_ms_part AS date, threads.date as thread_date, pdu.mx_type AS mx_type, pdu.mx_extension AS mx_extension FROM pdu JOIN threads ON pdu.thread_id=threads._id AND threads.private_addr_ids is %s WHERE pdu.deleted = 0 AND %s AND sub_cs=106 UNION SELECT -pdu._id AS _id, thread_id, part.text AS body, NULL AS sub, pdu.date * 1000 + pdu.date_ms_part AS date, threads.date AS thread_date, pdu.mx_type AS mx_type, pdu.mx_extension AS mx_extension FROM threads,pdu,part ON threads._id=pdu.thread_id AND part.mid=pdu._id AND threads.private_addr_ids is %s WHERE pdu.deleted = 0 AND part.ct='text/plain' AND %s) GROUP BY _id";
    private static final String[] SMS_ONLY_COLUMNS;
    private static final String SMS_QUERY = "SELECT sms._id AS _id, thread_id, body, NULL as sub, sms.date AS date, threads.date as thread_date, NULL as mx_type, NULL as mx_extension FROM sms JOIN threads ON sms.thread_id=threads._id AND threads.private_addr_ids is %s WHERE sms.deleted = 0 AND %s GROUP BY thread_id, sms.date";
    private static final String SMS_QUERY_FOR_SP = "SELECT sms._id AS _id, thread_id, body, NULL as sub, sms.date AS date, threads.date as thread_date FROM sms JOIN threads ON sms.thread_id=threads._id AND threads.private_addr_ids is NULL AND threads.sp_type>=1 WHERE sms.deleted = 0 AND %s GROUP BY thread_id, sms.date";
    public static final String TABLE_BLOCKED_THREADS = "blocked_threads";
    private static final String TABLE_CANONICAL_ADDRESSES = "canonical_addresses";
    public static final String TABLE_PENDING_MSG = "pending_msgs";
    public static final String TABLE_PRIVATE_ADDRESSES = "private_addresses";
    public static final String TABLE_SIM_CARDS = "sim_cards";
    public static final String TABLE_THREADS = "threads";
    public static final String TABLE_VALID_THREADS = "(SELECT * FROM threads WHERE _id > 0)";
    private static final String TAG = "MmsSmsProvider";
    private static final String[] THREADS_COLUMNS;
    private static final String THREAD_ADDRESS_SNIPPET_QUERY = "select threads._id, threads.snippet, canonical_addresses.address from threads join canonical_addresses where threads.recipient_ids=canonical_addresses._id AND threads._id > 0";
    private static final String[] UNION_COLUMNS;
    private static final String UNSEEN_SP_MSG_COUNT_QUERY = "SELECT ((SELECT COUNT(_id) FROM sms WHERE type = 1 AND deleted=0 AND block_type<=1 AND (advanced_seen=1 OR advanced_seen=2) AND exists (SELECT 1 FROM threads WHERE threads.sp_type>=1 AND threads.stick_time=0 AND threads.private_addr_ids IS NULL AND threads._id=sms.thread_id)) + (SELECT COUNT(_id) FROM pdu WHERE msg_box=1 AND deleted=0 AND block_type<=1 AND (advanced_seen=1 OR advanced_seen=2) AND (m_type=130 OR m_type=132) AND exists (SELECT 1 FROM threads WHERE threads.sp_type=1 AND threads.stick_time=0 AND threads.private_addr_ids IS NULL AND threads._id=pdu.thread_id)) + (SELECT COUNT(_id) FROM hms WHERE  + (advanced_seen =1 OR advanced_seen=2 )  AND exists (SELECT 1 FROM threads WHERE threads.sp_type>=1 AND threads.stick_time=0 AND threads.private_addr_ids IS NULL AND threads._id=hms.thread_id)))";
    private static final int URI_ALL_LOCKED_MESSAGE = 100;
    private static final int URI_BLOCKED_CONVERSATIONS = 109;
    private static final int URI_BLOCKED_CONVERSATION_ID = 110;
    private static final int URI_BLOCKED_THREAD_ID = 111;
    private static final int URI_CANONICAL_ADDRESS = 5;
    private static final int URI_CANONICAL_ADDRESSES = 13;
    private static final int URI_COMPLETE_CONVERSATIONS = 7;
    private static final int URI_CONVERSATIONS = 0;
    private static final int URI_CONVERSATIONS_GROUP_MESSAGES = 102;
    private static final int URI_CONVERSATIONS_MESSAGES = 1;
    private static final int URI_CONVERSATIONS_RECIPIENTS = 2;
    private static final int URI_CONVERSATIONS_SMART_MESSAGES = 104;
    private static final int URI_CONVERSATIONS_STATUS = 113;
    private static final int URI_CONVERSATIONS_SUBJECT = 9;
    private static final int URI_DRAFT = 12;
    private static final int URI_EXPIRED_MESSAGES = 105;
    private static final int URI_FIRST_LOCKED_MESSAGE_ALL = 16;
    private static final int URI_FIRST_LOCKED_MESSAGE_BY_THREAD_ID = 17;
    private static final int URI_GET_CDMA_MSGID = 200;
    private static final int URI_HMS_CONVERSATION_ID = 112;
    private static final UriMatcher URI_MATCHER;
    private static final int URI_MESSAGES_BY_PHONE = 3;
    private static final int URI_MESSAGES_PREVIEW = 101;
    private static final int URI_MESSAGE_ID_TO_THREAD = 18;
    private static final int URI_NOTIFICATIONS = 10;
    private static final int URI_OBSOLETE_THREADS = 11;
    private static final int URI_PENDING_MSG = 6;
    private static final int URI_PRIVATE_ADDRESSES = 107;
    private static final int URI_PRIVATE_ADDRESS_ID = 108;
    private static final int URI_RECENT_RECIPIENTS = 103;
    private static final int URI_SEARCH = 14;
    private static final int URI_SEARCH_COUNT = 19;
    private static final int URI_SEARCH_SUGGEST = 15;
    private static final int URI_SIM_CARDS = 106;
    private static final int URI_THREADS_ADDRESS_SNIPPET = 21;
    private static final int URI_THREAD_ID = 4;
    private static final int URI_UNDELIVERED_MSG = 8;
    private static final int URI_UNSEEN_SP_MSG_COUNT = 20;
    private static final String VND_ANDROID_DIR_MMS_SMS = "vnd.android-dir/mms-sms";
    private MmsSmsDatabaseHelper mOpenHelper;
    private boolean mUseStrictPhoneNumberComparation;

    static {
        URI_MATCHER = new UriMatcher(-1);
        String[] strArr = new String[URI_FIRST_LOCKED_MESSAGE_ALL];
        strArr[URI_CONVERSATIONS] = "_id";
        strArr[URI_CONVERSATIONS_MESSAGES] = "count(*)";
        strArr[URI_CONVERSATIONS_RECIPIENTS] = WapPush.DATE;
        strArr[URI_MESSAGES_BY_PHONE] = "date_sent";
        strArr[URI_THREAD_ID] = WapPush.READ;
        strArr[URI_CANONICAL_ADDRESS] = WapPush.THREAD_ID;
        strArr[URI_PENDING_MSG] = WapPush.LOCKED;
        strArr[URI_COMPLETE_CONVERSATIONS] = "timed";
        strArr[URI_UNDELIVERED_MSG] = "deleted";
        strArr[URI_CONVERSATIONS_SUBJECT] = "sync_state";
        strArr[URI_NOTIFICATIONS] = "marker";
        strArr[URI_OBSOLETE_THREADS] = Search.SOURCE;
        strArr[URI_DRAFT] = "mx_status";
        strArr[URI_CANONICAL_ADDRESSES] = "mx_id";
        strArr[URI_SEARCH] = WapPush.SIM_ID;
        strArr[URI_SEARCH_SUGGEST] = "block_type";
        MMS_SMS_COLUMNS = strArr;
        MMS_ONLY_COLUMNS = new String[]{"ct_cls", "ct_l", "ct_t", "d_rpt", "exp", "m_cls", "m_id", "m_size", "m_type", "msg_box", "pri", "read_status", "resp_st", "resp_txt", "retr_st", "retr_txt_cs", "rpt_a", "rr", "st", "sub", "sub_cs", "tr_id", "v", "date_ms_part", "file_id", "need_download", Conversations.SNIPPET, "preview_type", "preview_data", "preview_data_ts", "mx_type", "mx_extension", "text_only"};
        strArr = new String[URI_CONVERSATIONS_SUBJECT];
        strArr[URI_CONVERSATIONS] = TagYellowPage.ADDRESS;
        strArr[URI_CONVERSATIONS_MESSAGES] = TextBasedSmsCbColumns.BODY;
        strArr[URI_CONVERSATIONS_RECIPIENTS] = "person";
        strArr[URI_MESSAGES_BY_PHONE] = "reply_path_present";
        strArr[URI_THREAD_ID] = WapPush.SERVICE_ADDR;
        strArr[URI_CANONICAL_ADDRESS] = TagYellowPage.DELETED;
        strArr[URI_PENDING_MSG] = "subject";
        strArr[URI_COMPLETE_CONVERSATIONS] = AntispamNumber.TYPE;
        strArr[URI_UNDELIVERED_MSG] = "error_code";
        SMS_ONLY_COLUMNS = strArr;
        strArr = new String[URI_THREAD_ID];
        strArr[URI_CONVERSATIONS] = "_id";
        strArr[URI_CONVERSATIONS_MESSAGES] = WapPush.DATE;
        strArr[URI_CONVERSATIONS_RECIPIENTS] = "recipient_ids";
        strArr[URI_MESSAGES_BY_PHONE] = "message_count";
        THREADS_COLUMNS = strArr;
        strArr = new String[URI_CONVERSATIONS_MESSAGES];
        strArr[URI_CONVERSATIONS] = TagYellowPage.ADDRESS;
        CANONICAL_ADDRESSES_COLUMNS_1 = strArr;
        strArr = new String[URI_CONVERSATIONS_RECIPIENTS];
        strArr[URI_CONVERSATIONS] = "_id";
        strArr[URI_CONVERSATIONS_MESSAGES] = TagYellowPage.ADDRESS;
        CANONICAL_ADDRESSES_COLUMNS_2 = strArr;
        UNION_COLUMNS = new String[((MMS_SMS_COLUMNS.length + MMS_ONLY_COLUMNS.length) + SMS_ONLY_COLUMNS.length)];
        MMS_COLUMNS = new HashSet();
        SMS_COLUMNS = new HashSet();
        strArr = new String[URI_CONVERSATIONS_MESSAGES];
        strArr[URI_CONVERSATIONS] = "_id";
        ID_PROJECTION = strArr;
        EMPTY_STRING_ARRAY = new String[URI_CONVERSATIONS];
        URI_MATCHER.addURI(AUTHORITY, "conversations", URI_CONVERSATIONS);
        URI_MATCHER.addURI(AUTHORITY, "complete-conversations", URI_COMPLETE_CONVERSATIONS);
        URI_MATCHER.addURI(AUTHORITY, "conversations/#", URI_CONVERSATIONS_MESSAGES);
        URI_MATCHER.addURI(AUTHORITY, "conversations/#/group", URI_CONVERSATIONS_GROUP_MESSAGES);
        URI_MATCHER.addURI(AUTHORITY, "conversations/#/recipients", URI_CONVERSATIONS_RECIPIENTS);
        URI_MATCHER.addURI(AUTHORITY, "conversations/#/subject", URI_CONVERSATIONS_SUBJECT);
        URI_MATCHER.addURI(AUTHORITY, "conversations/obsolete", URI_OBSOLETE_THREADS);
        URI_MATCHER.addURI(AUTHORITY, "messages/byphone/*", URI_MESSAGES_BY_PHONE);
        URI_MATCHER.addURI(AUTHORITY, "threadID", URI_THREAD_ID);
        URI_MATCHER.addURI(AUTHORITY, "canonical-address/#", URI_CANONICAL_ADDRESS);
        URI_MATCHER.addURI(AUTHORITY, "canonical-addresses", URI_CANONICAL_ADDRESSES);
        URI_MATCHER.addURI(AUTHORITY, "search", URI_SEARCH);
        URI_MATCHER.addURI(AUTHORITY, "searchSuggest", URI_SEARCH_SUGGEST);
        URI_MATCHER.addURI(AUTHORITY, "pending", URI_PENDING_MSG);
        URI_MATCHER.addURI(AUTHORITY, "undelivered", URI_UNDELIVERED_MSG);
        URI_MATCHER.addURI(AUTHORITY, "notifications", URI_NOTIFICATIONS);
        URI_MATCHER.addURI(AUTHORITY, "draft", URI_DRAFT);
        URI_MATCHER.addURI(AUTHORITY, WapPush.LOCKED, URI_FIRST_LOCKED_MESSAGE_ALL);
        URI_MATCHER.addURI(AUTHORITY, "locked/#", URI_FIRST_LOCKED_MESSAGE_BY_THREAD_ID);
        URI_MATCHER.addURI(AUTHORITY, "locked/all", URI_ALL_LOCKED_MESSAGE);
        URI_MATCHER.addURI(AUTHORITY, "messageIdToThread", URI_MESSAGE_ID_TO_THREAD);
        URI_MATCHER.addURI(AUTHORITY, "message/preview", URI_MESSAGES_PREVIEW);
        URI_MATCHER.addURI(AUTHORITY, "recent-recipients", URI_RECENT_RECIPIENTS);
        URI_MATCHER.addURI(AUTHORITY, "conversations/#/*", URI_CONVERSATIONS_SMART_MESSAGES);
        URI_MATCHER.addURI(AUTHORITY, "expired/#", URI_EXPIRED_MESSAGES);
        URI_MATCHER.addURI(AUTHORITY, "sim-cards", URI_SIM_CARDS);
        URI_MATCHER.addURI(AUTHORITY, "sim-cards/#", URI_SIM_CARDS);
        URI_MATCHER.addURI(AUTHORITY, "searchCount", URI_SEARCH_COUNT);
        URI_MATCHER.addURI(AUTHORITY, "unseenSpMsgCount", URI_UNSEEN_SP_MSG_COUNT);
        URI_MATCHER.addURI(AUTHORITY, "address-snippet", URI_THREADS_ADDRESS_SNIPPET);
        URI_MATCHER.addURI(AUTHORITY, "private-addresses", URI_PRIVATE_ADDRESSES);
        URI_MATCHER.addURI(AUTHORITY, "private-addresses/#", URI_PRIVATE_ADDRESS_ID);
        URI_MATCHER.addURI(AUTHORITY, "blocked", URI_BLOCKED_CONVERSATIONS);
        URI_MATCHER.addURI(AUTHORITY, "blocked/#", URI_BLOCKED_CONVERSATION_ID);
        URI_MATCHER.addURI(AUTHORITY, "blocked-thread/#", URI_BLOCKED_THREAD_ID);
        URI_MATCHER.addURI(AUTHORITY, HmsProvider.TABLE_HMS, URI_HMS_CONVERSATION_ID);
        URI_MATCHER.addURI(AUTHORITY, "conversations/status/#", URI_CONVERSATIONS_STATUS);
        URI_MATCHER.addURI(AUTHORITY, "getCdmaMsgId", URI_GET_CDMA_MSGID);
        initializeColumnSets();
    }

    public boolean onCreate() {
        Injector.setMmsSmsProviderOps(this);
        this.mOpenHelper = MmsSmsDatabaseHelper.getInstance(getContext());
        this.mUseStrictPhoneNumberComparation = true;
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
        OperationPerfProfiler operationPerfProfiler = new OperationPerfProfiler("query " + uri.toString() + " with selection " + selection);
        boolean callerIsSyncAdapter = MmsSmsUtils.readBooleanQueryParameter(uri, "caller_is_syncadapter", LOCAL_LOGV);
        boolean privateMsgGranted = LOCAL_LOGV;
        if (MmsSmsUtils.checkPrivateMsgWhiteList(getContext())) {
            privateMsgGranted = true;
        }
        String privacyFlag = uri.getQueryParameter("privacy_flag");
        if (TextUtils.isEmpty(privacyFlag)) {
            privacyFlag = privateMsgGranted ? "2" : "0";
        }
        SQLiteDatabase db = this.mOpenHelper.getReadableDatabase();
        Cursor cursor = null;
        int uriMatch = URI_MATCHER.match(uri);
        if (uriMatch != URI_THREAD_ID) {
            Cursor emptyCursor = Secure.checkPrivacyAndReturnCursor(getContext());
            if (emptyCursor != null) {
                return emptyCursor;
            }
        }
        String[] strArr;
        String extraSelection;
        String finalSelection;
        String str;
        Object[] objArr;
        String sql;
        switch (uriMatch) {
            case URI_CONVERSATIONS /*0*/:
                String simple = uri.getQueryParameter("simple");
                String hms = uri.getQueryParameter(HmsProvider.TABLE_HMS);
                if (!TextUtils.isEmpty(hms)) {
                    if (hms.equalsIgnoreCase("true")) {
                        cursor = getSimpleConversations(projection, selection, selectionArgs, sortOrder);
                        break;
                    }
                }
                if (simple != null) {
                    if (simple.equals("true")) {
                        String threadType = uri.getQueryParameter("thread_type");
                        if (!TextUtils.isEmpty(threadType)) {
                            selection = MmsSmsUtils.concatenateWhere(selection, "type=" + threadType);
                        }
                        if (privateMsgGranted) {
                            if (privacyFlag.equals("1")) {
                                selection = MmsSmsUtils.concatenateWhere(selection, "private_addr_ids is not NULL");
                            } else {
                                if (privacyFlag.equals("0")) {
                                    selection = MmsSmsUtils.concatenateWhere(selection, "private_addr_ids is NULL");
                                }
                            }
                        } else {
                            selection = MmsSmsUtils.concatenateWhere(selection, "private_addr_ids is NULL");
                        }
                        String threadListType = uri.getQueryParameter("threads_list_type");
                        boolean isCompositeMode = LOCAL_LOGV;
                        if (!TextUtils.isEmpty(threadListType)) {
                            int category = URI_CONVERSATIONS;
                            try {
                                category = Integer.valueOf(threadListType).intValue();
                            } catch (Exception e) {
                                Log.d(TAG, "error format in service number category: " + threadListType);
                            }
                            if (category == 0) {
                                isCompositeMode = true;
                            } else if (category == URI_CONVERSATIONS_MESSAGES) {
                                selection = MmsSmsUtils.concatenateWhere(selection, "(sp_type >= 1 AND stick_time=0)");
                            } else {
                                selection = MmsSmsUtils.concatenateWhere(selection, "(sp_type = " + category + " AND " + "stick_time" + "=0)");
                            }
                        }
                        String placeHolder = uri.getQueryParameter("placeholder");
                        int placeHolderType = URI_CONVERSATIONS;
                        if (!TextUtils.isEmpty(placeHolder)) {
                            placeHolderType = Integer.parseInt(placeHolder);
                        }
                        cursor = getSimpleConversations(callerIsSyncAdapter, projection, selection, selectionArgs, sortOrder, isCompositeMode, placeHolderType);
                        break;
                    }
                }
                cursor = getConversations(projection, MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), selection), sortOrder);
                break;
            case URI_CONVERSATIONS_MESSAGES /*1*/:
                cursor = getConversationMessages((String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES), null, projection, MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), selection), sortOrder, null, null, null);
                break;
            case URI_CONVERSATIONS_RECIPIENTS /*2*/:
                cursor = getConversationById((String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES), projection, selection, selectionArgs, sortOrder);
                break;
            case URI_MESSAGES_BY_PHONE /*3*/:
                cursor = getMessagesByPhoneNumber((String) uri.getPathSegments().get(URI_CONVERSATIONS_RECIPIENTS), projection, selection, sortOrder);
                break;
            case URI_THREAD_ID /*4*/:
                List<String> recipients = getCleanRecipients(uri);
                strArr = new String[URI_CONVERSATIONS_MESSAGES];
                strArr[URI_CONVERSATIONS] = "_id";
                cursor = getThreadId(recipients, strArr, URI_CONVERSATIONS);
                break;
            case URI_CANONICAL_ADDRESS /*5*/:
                extraSelection = "_id=" + ((String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES));
                if (TextUtils.isEmpty(selection)) {
                    finalSelection = extraSelection;
                } else {
                    finalSelection = extraSelection + " AND " + selection;
                }
                cursor = db.query(TABLE_CANONICAL_ADDRESSES, CANONICAL_ADDRESSES_COLUMNS_1, finalSelection, selectionArgs, null, null, sortOrder);
                break;
            case URI_PENDING_MSG /*6*/:
                int proto;
                String finalOrder;
                String protoName = uri.getQueryParameter("protocol");
                String msgId = uri.getQueryParameter("message");
                if (TextUtils.isEmpty(protoName)) {
                    proto = -1;
                } else {
                    proto = protoName.equals("sms") ? URI_CONVERSATIONS : URI_CONVERSATIONS_MESSAGES;
                }
                extraSelection = proto != -1 ? "proto_type=" + proto : " 0=0 ";
                if (!TextUtils.isEmpty(msgId)) {
                    extraSelection = extraSelection + " AND msg_id=" + msgId;
                }
                finalSelection = TextUtils.isEmpty(selection) ? extraSelection : "(" + extraSelection + ") AND " + selection;
                if (TextUtils.isEmpty(sortOrder)) {
                    finalOrder = "due_time";
                } else {
                    finalOrder = sortOrder;
                }
                cursor = db.query(TABLE_PENDING_MSG, null, finalSelection, selectionArgs, null, null, finalOrder);
                break;
            case URI_COMPLETE_CONVERSATIONS /*7*/:
                cursor = getCompleteConversations(projection, MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), selection), sortOrder, null);
                break;
            case URI_UNDELIVERED_MSG /*8*/:
                cursor = getUndeliveredMessages(projection, MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), selection), selectionArgs, sortOrder);
                break;
            case URI_CONVERSATIONS_SUBJECT /*9*/:
                cursor = getConversationById((String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES), projection, selection, selectionArgs, sortOrder);
                break;
            case URI_DRAFT /*12*/:
                cursor = getDraftThread(projection, selection, sortOrder);
                break;
            case URI_CANONICAL_ADDRESSES /*13*/:
                cursor = db.query(TABLE_CANONICAL_ADDRESSES, CANONICAL_ADDRESSES_COLUMNS_2, selection, selectionArgs, null, null, sortOrder);
                break;
            case URI_SEARCH /*14*/:
                if (sortOrder == null && selection == null && selectionArgs == null && projection == null) {
                    String[] searchTokens = uri.getQueryParameter("pattern").split(" +");
                    StringBuilder bodyStmt = new StringBuilder();
                    StringBuilder subjectStmt = new StringBuilder();
                    StringBuilder textStmt = new StringBuilder();
                    StringBuilder snippetStmt = new StringBuilder();
                    StringBuilder mxExtensionStmt = new StringBuilder();
                    int i = URI_CONVERSATIONS;
                    while (i < searchTokens.length) {
                        String token = searchTokens[i];
                        try {
                            String str2 = new String(token.getBytes(StandardCharsets.UTF_8), "iso-8859-1");
                            if (i > 0) {
                                bodyStmt.append(" AND ");
                                subjectStmt.append(" AND ");
                                textStmt.append(" AND ");
                                snippetStmt.append(" AND ");
                                mxExtensionStmt.append(" AND ");
                            }
                            bodyStmt.append("body LIKE ");
                            bodyStmt.append(DatabaseUtils.sqlEscapeString("%" + token + "%"));
                            subjectStmt.append("sub LIKE ");
                            subjectStmt.append(DatabaseUtils.sqlEscapeString("%" + str2 + "%"));
                            textStmt.append("part.text LIKE ");
                            textStmt.append(DatabaseUtils.sqlEscapeString("%" + token + "%"));
                            snippetStmt.append("hms.snippet LIKE ");
                            snippetStmt.append(DatabaseUtils.sqlEscapeString("%" + token + "%"));
                            mxExtensionStmt.append("mx_content LIKE ");
                            mxExtensionStmt.append(DatabaseUtils.sqlEscapeString("%" + token + "%"));
                            i += URI_CONVERSATIONS_MESSAGES;
                        } catch (UnsupportedEncodingException e2) {
                            Log.e(TAG, "Cannot convert subject while searching");
                            return null;
                        }
                    }
                    if (TextUtils.isEmpty(uri.getQueryParameter("threads_list_type"))) {
                        boolean isPrivate = privacyFlag.equals("1");
                        String privateString = isPrivate ? "not null" : "null";
                        str = SMS_MMS_QUERY;
                        objArr = new Object[URI_PENDING_MSG];
                        objArr[URI_CONVERSATIONS] = privateString;
                        objArr[URI_CONVERSATIONS_MESSAGES] = bodyStmt.toString();
                        objArr[URI_CONVERSATIONS_RECIPIENTS] = privateString;
                        objArr[URI_MESSAGES_BY_PHONE] = subjectStmt.toString();
                        objArr[URI_THREAD_ID] = privateString;
                        objArr[URI_CANONICAL_ADDRESS] = textStmt.toString();
                        sql = String.format(str, objArr);
                        if (!isPrivate) {
                            StringBuilder stringBuilder = new StringBuilder(sql);
                            stringBuilder.append(" UNION ");
                            str = HMS_QUERY_TAIL;
                            objArr = new Object[URI_CONVERSATIONS_RECIPIENTS];
                            objArr[URI_CONVERSATIONS] = snippetStmt;
                            objArr[URI_CONVERSATIONS_MESSAGES] = mxExtensionStmt;
                            stringBuilder.append(String.format(str, objArr));
                            sql = stringBuilder.toString();
                        }
                    } else {
                        str = SMS_MMS_HMS_QUERY_FOR_SP;
                        objArr = new Object[URI_CANONICAL_ADDRESS];
                        objArr[URI_CONVERSATIONS] = bodyStmt.toString();
                        objArr[URI_CONVERSATIONS_MESSAGES] = subjectStmt.toString();
                        objArr[URI_CONVERSATIONS_RECIPIENTS] = textStmt.toString();
                        objArr[URI_MESSAGES_BY_PHONE] = snippetStmt;
                        objArr[URI_THREAD_ID] = mxExtensionStmt;
                        sql = String.format(str, objArr);
                    }
                    try {
                        cursor = db.rawQuery(sql, null);
                        break;
                    } catch (Exception ex) {
                        Log.e(TAG, "got exception: " + ex.toString());
                        break;
                    }
                }
                throw new IllegalArgumentException("do not specify sortOrder, selection, selectionArgs, or projectionwith this query");
            case URI_SEARCH_SUGGEST /*15*/:
                objArr = new Object[URI_CONVERSATIONS_MESSAGES];
                objArr[URI_CONVERSATIONS] = uri.getQueryParameter("pattern");
                String query = String.format("SELECT snippet(words, '', ' ', '', 1, 1) as snippet FROM words WHERE index_text MATCH '%s*' ORDER BY snippet LIMIT 50;", objArr);
                if (sortOrder == null && selection == null && selectionArgs == null && projection == null) {
                    cursor = db.rawQuery(query, null);
                    break;
                }
                throw new IllegalArgumentException("do not specify sortOrder, selection, selectionArgs, or projectionwith this query");
            case URI_FIRST_LOCKED_MESSAGE_ALL /*16*/:
                if (privacyFlag.equals("0")) {
                    selection = MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), selection);
                }
                cursor = getFirstLockedMessage(projection, selection, sortOrder);
                break;
            case URI_FIRST_LOCKED_MESSAGE_BY_THREAD_ID /*17*/:
                try {
                    cursor = getFirstLockedMessage(projection, "thread_id=" + Long.toString(Long.parseLong(uri.getLastPathSegment())), sortOrder);
                    break;
                } catch (NumberFormatException e3) {
                    Log.e(TAG, "Thread ID must be a long.");
                    break;
                }
            case URI_MESSAGE_ID_TO_THREAD /*18*/:
                try {
                    long id = Long.parseLong(uri.getQueryParameter("row_id"));
                    switch (Integer.parseInt(uri.getQueryParameter("table_to_use"))) {
                        case URI_CONVERSATIONS_MESSAGES /*1*/:
                            String[] strArr2 = new String[URI_CONVERSATIONS_MESSAGES];
                            strArr2[URI_CONVERSATIONS] = WapPush.THREAD_ID;
                            String[] strArr3 = new String[URI_CONVERSATIONS_MESSAGES];
                            strArr3[URI_CONVERSATIONS] = String.valueOf(id);
                            cursor = db.query("sms", strArr2, "_id=? AND deleted=0", strArr3, null, null, null);
                            break;
                        case URI_CONVERSATIONS_RECIPIENTS /*2*/:
                            strArr = new String[URI_CONVERSATIONS_MESSAGES];
                            strArr[URI_CONVERSATIONS] = String.valueOf(id);
                            cursor = db.rawQuery("SELECT thread_id FROM pdu,part WHERE ((part.mid=pdu._id) AND (pdu.deleted=0) AND (part._id=?))", strArr);
                            break;
                        default:
                            break;
                    }
                } catch (NumberFormatException e4) {
                    break;
                }
            case URI_SEARCH_COUNT /*19*/:
                if (sortOrder == null && selection == null && selectionArgs == null && projection == null) {
                    if (TextUtils.isEmpty(uri.getQueryParameter("threads_list_type"))) {
                        String searchString = null;
                        if (privacyFlag.equals("0")) {
                            str = PRIVATE_MSG_CONDITION;
                            objArr = new Object[URI_CONVERSATIONS_MESSAGES];
                            objArr[URI_CONVERSATIONS] = "NULL";
                            searchString = String.format(str, objArr);
                        } else {
                            if (privacyFlag.equals("1")) {
                                str = PRIVATE_MSG_CONDITION;
                                objArr = new Object[URI_CONVERSATIONS_MESSAGES];
                                objArr[URI_CONVERSATIONS] = "not NULL";
                                searchString = String.format(str, objArr);
                            }
                        }
                        str = SEARCH_COUNT_QUERY;
                        objArr = new Object[URI_MESSAGES_BY_PHONE];
                        objArr[URI_CONVERSATIONS] = searchString;
                        objArr[URI_CONVERSATIONS_MESSAGES] = searchString;
                        objArr[URI_CONVERSATIONS_RECIPIENTS] = searchString;
                        sql = String.format(str, objArr);
                    } else {
                        sql = SEARCH_SP_COUNT_QUERY;
                    }
                    try {
                        cursor = db.rawQuery(sql, null);
                        break;
                    } catch (Exception ex2) {
                        Log.e(TAG, "got exception: " + ex2.toString());
                        break;
                    }
                }
                throw new IllegalArgumentException("do not specify sortOrder, selection, selectionArgs, or projectionwith this query");
                break;
            case URI_UNSEEN_SP_MSG_COUNT /*20*/:
                cursor = db.rawQuery(UNSEEN_SP_MSG_COUNT_QUERY, null);
                break;
            case URI_THREADS_ADDRESS_SNIPPET /*21*/:
                cursor = db.rawQuery(THREAD_ADDRESS_SNIPPET_QUERY, null);
                break;
            case URI_ALL_LOCKED_MESSAGE /*100*/:
                cursor = getCompleteConversations(projection, MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), MmsSmsUtils.concatenateWhere(selection, "locked = 1")), "normalized_date DESC", null);
                break;
            case URI_MESSAGES_PREVIEW /*101*/:
                objArr = new Object[URI_CONVERSATIONS_RECIPIENTS];
                objArr[URI_CONVERSATIONS] = "sms._id as _id,address,date,type,body,error_code,thread_id";
                objArr[URI_CONVERSATIONS_MESSAGES] = Integer.valueOf(URI_CONVERSATIONS_MESSAGES);
                String smsQuery = String.format("SELECT %s FROM sms WHERE type=%d AND seen=0 AND deleted=0", objArr);
                objArr = new Object[URI_MESSAGES_BY_PHONE];
                objArr[URI_CONVERSATIONS] = "pdu._id as _id,address,date * 1000 + date_ms_part,m_type,sub,sub_cs,thread_id";
                objArr[URI_CONVERSATIONS_MESSAGES] = Integer.valueOf(137);
                objArr[URI_CONVERSATIONS_RECIPIENTS] = Integer.valueOf(URI_CONVERSATIONS_MESSAGES);
                String mmsQuery = String.format("SELECT %s FROM pdu,addr WHERE (seen=0) AND (deleted=0) AND (addr.msg_id=pdu._id) AND (addr.type=%d) AND (pdu.msg_box=%d)", objArr);
                objArr = new Object[URI_MESSAGES_BY_PHONE];
                objArr[URI_CONVERSATIONS] = smsQuery;
                objArr[URI_CONVERSATIONS_MESSAGES] = mmsQuery;
                objArr[URI_CONVERSATIONS_RECIPIENTS] = SmsCb.DEFAULT_SORT_ORDER;
                try {
                    cursor = db.rawQuery(String.format("%s UNION %s ORDER BY %s", objArr), selectionArgs);
                    break;
                } catch (Exception ex22) {
                    Log.e(TAG, "got exception: " + ex22.toString());
                    break;
                }
            case URI_CONVERSATIONS_GROUP_MESSAGES /*102*/:
                cursor = getConversationMessages((String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES), null, projection, MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), selection), sortOrder, "normalized_date,timed", null, LOCAL_LOGV);
                break;
            case URI_RECENT_RECIPIENTS /*103*/:
                try {
                    cursor = db.rawQuery("SELECT address,date FROM threads JOIN canonical_addresses ON recipient_ids = canonical_addresses._id WHERE (threads.message_count > 0 OR threads.has_draft > 0) AND canonical_addresses.address not like '%@xiaomi.com%' AND (threads.private_addr_ids is NULL) ORDER BY date DESC LIMIT 50", null);
                    break;
                } catch (Exception ex222) {
                    Log.e(TAG, "got exception: " + ex222.toString());
                    break;
                }
            case URI_CONVERSATIONS_SMART_MESSAGES /*104*/:
                String limit = uri.getQueryParameter("limit");
                if (TextUtils.isEmpty(limit)) {
                    limit = null;
                }
                cursor = getConversationMessages((String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES), (String) uri.getPathSegments().get(URI_CONVERSATIONS_RECIPIENTS), projection, MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), selection), sortOrder, null, limit, null);
                break;
            case URI_EXPIRED_MESSAGES /*105*/:
                try {
                    cursor = getCompleteConversations(projection, MmsSmsUtils.getSelectionByPrivatePermission(uri, getContext(), MmsSmsUtils.concatenateWhere(selection, "timed > 0 AND normalized_date < " + Long.parseLong(uri.getLastPathSegment()))), "normalized_date DESC", "thread_id,normalized_date,timed");
                    break;
                } catch (NumberFormatException e5) {
                    Log.e(TAG, "time must be a long.");
                    break;
                }
            case URI_SIM_CARDS /*106*/:
                cursor = db.query(TABLE_SIM_CARDS, projection, makeMarkerSelection(uri, selection), selectionArgs, null, null, null);
                break;
            case URI_PRIVATE_ADDRESSES /*107*/:
                finalSelection = selection;
                if (!callerIsSyncAdapter) {
                    finalSelection = MmsSmsUtils.concatenateWhere(selection, "deleted = 0");
                }
                cursor = db.query(TABLE_PRIVATE_ADDRESSES, projection, finalSelection, selectionArgs, null, null, sortOrder);
                break;
            case URI_BLOCKED_CONVERSATIONS /*109*/:
                cursor = db.query("blocked_threads JOIN canonical_addresses ON blocked_threads.recipient_ids=canonical_addresses._id", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case URI_BLOCKED_CONVERSATION_ID /*110*/:
                cursor = db.query("blocked_threads JOIN canonical_addresses ON blocked_threads.recipient_ids=canonical_addresses._id", projection, MmsSmsUtils.concatenateWhere(selection, "blocked_threads._id=" + uri.getLastPathSegment()), selectionArgs, null, null, sortOrder);
                break;
            case URI_BLOCKED_THREAD_ID /*111*/:
                cursor = getConversationMessages(uri.getLastPathSegment(), null, projection, selection, sortOrder, null, null, true);
                break;
            default:
                throw new IllegalStateException("Unrecognized URI:" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        operationPerfProfiler.prof();
        return cursor;
    }

    private static List<String> getCleanRecipients(Uri uri) {
        List<String> origRecipients = uri.getQueryParameters("recipient");
        List<String> recipients = new ArrayList(origRecipients.size());
        for (String origRecipient : origRecipients) {
            recipients.add(MmsSmsUtils.removeSpaceForAddress(origRecipient));
        }
        return recipients;
    }

    private static String makeMarkerSelection(Uri uri, String selection) {
        String finalSelection = selection;
        if (uri.getPathSegments().size() == URI_CONVERSATIONS_RECIPIENTS) {
            try {
                finalSelection = MmsSmsUtils.concatenateWhere(selection, "_id = " + Long.parseLong(uri.getLastPathSegment()));
            } catch (NumberFormatException e) {
                Log.e(TAG, "id must be a long.");
                return null;
            }
        }
        return finalSelection;
    }

    private long getSingleAddressId(String address) {
        String[] selectionArgs;
        boolean isEmail = Mms.isEmailAddress(address);
        boolean isPhoneNumber = Mms.isPhoneNumber(address);
        String refinedAddress = isEmail ? address.toLowerCase() : MmsSmsUtils.deleteSpecialIntlCode(address);
        String selection = "address=?";
        long retVal = -1;
        if (isPhoneNumber) {
            selection = selection + " OR PHONE_NUMBERS_EQUAL(address, ?, " + (this.mUseStrictPhoneNumberComparation ? "1" : "0") + ")";
            selectionArgs = new String[URI_CONVERSATIONS_RECIPIENTS];
            selectionArgs[URI_CONVERSATIONS] = refinedAddress;
            selectionArgs[URI_CONVERSATIONS_MESSAGES] = refinedAddress;
        } else {
            selectionArgs = new String[URI_CONVERSATIONS_MESSAGES];
            selectionArgs[URI_CONVERSATIONS] = refinedAddress;
        }
        Cursor cursor = null;
        try {
            cursor = this.mOpenHelper.getReadableDatabase().query(TABLE_CANONICAL_ADDRESSES, ID_PROJECTION, selection, selectionArgs, null, null, null);
            if (cursor.getCount() == 0) {
                ContentValues contentValues = new ContentValues(URI_CONVERSATIONS_MESSAGES);
                contentValues.put(TagYellowPage.ADDRESS, refinedAddress);
                retVal = this.mOpenHelper.getWritableDatabase().insert(TABLE_CANONICAL_ADDRESSES, TagYellowPage.ADDRESS, contentValues);
                Log.d(TAG, "getSingleAddressId: insert new canonical_address for xxxxxx, _id=" + retVal);
                return retVal;
            }
            if (cursor.moveToFirst()) {
                retVal = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
            }
            if (cursor != null) {
                cursor.close();
            }
            return retVal;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private Set<Long> getAddressIds(List<String> addresses) {
        Set<Long> result = new HashSet(addresses.size());
        for (String address : addresses) {
            if (!address.equals("insert-address-token")) {
                long id = getSingleAddressId(address);
                if (id != -1) {
                    result.add(Long.valueOf(id));
                }
            }
        }
        return result;
    }

    private long[] getSortedSet(Set<Long> numbers) {
        int size = numbers.size();
        long[] result = new long[size];
        int i = URI_CONVERSATIONS;
        for (Long number : numbers) {
            int i2 = i + URI_CONVERSATIONS_MESSAGES;
            result[i] = number.longValue();
            i = i2;
        }
        if (size > URI_CONVERSATIONS_MESSAGES) {
            Arrays.sort(result);
        }
        return result;
    }

    private String getSpaceSeparatedNumbers(long[] numbers) {
        int size = numbers.length;
        StringBuilder buffer = new StringBuilder();
        for (int i = URI_CONVERSATIONS; i < size; i += URI_CONVERSATIONS_MESSAGES) {
            if (i != 0) {
                buffer.append(' ');
            }
            buffer.append(numbers[i]);
        }
        return buffer.toString();
    }

    private String constructAddrSelection(String address) {
        String refinedAddress;
        boolean isEmail = Mms.isEmailAddress(address);
        boolean isPhoneNumber = Mms.isPhoneNumber(address);
        if (isEmail) {
            refinedAddress = address.toLowerCase();
        } else {
            refinedAddress = address;
        }
        String selection = "address= " + DatabaseUtils.sqlEscapeString(refinedAddress);
        if (!isPhoneNumber) {
            return selection;
        }
        return selection + " OR PHONE_NUMBERS_EQUAL(address, '" + refinedAddress + "', " + (this.mUseStrictPhoneNumberComparation ? "1" : "0") + ")";
    }

    private long checkPrivateThread(String addr) {
        SQLiteDatabase db = this.mOpenHelper.getReadableDatabase();
        db.beginTransaction();
        Cursor c = null;
        try {
            c = db.query(TABLE_PRIVATE_ADDRESSES, null, MmsSmsUtils.concatenateWhere(constructAddrSelection(addr), "deleted=0"), null, null, null, null);
            db.setTransactionSuccessful();
            if (c != null) {
                try {
                    if (c.getCount() > 0) {
                        c.moveToFirst();
                        long j = c.getLong(c.getColumnIndexOrThrow("_id"));
                        return j;
                    }
                    c.close();
                } finally {
                    c.close();
                }
            }
            return 0;
        } finally {
            db.endTransaction();
        }
    }

    private void insertThread(SQLiteDatabase db, String recipientIds, int numberOfRecipients, long privateAddrId, int defSyncState, int serviceNumberCategory) {
        ContentValues values = new ContentValues(URI_CANONICAL_ADDRESS);
        long date = System.currentTimeMillis();
        values.put(WapPush.DATE, Long.valueOf(date - (date % 1000)));
        values.put("recipient_ids", recipientIds);
        values.put("sync_state", Integer.valueOf(defSyncState));
        if (numberOfRecipients > URI_CONVERSATIONS_MESSAGES) {
            values.put(AntispamNumber.TYPE, Integer.valueOf(URI_CONVERSATIONS_MESSAGES));
        }
        values.put("message_count", Integer.valueOf(URI_CONVERSATIONS));
        if (privateAddrId > 0) {
            values.put("private_addr_ids", Long.toString(privateAddrId));
        }
        values.put("sp_type", Integer.valueOf(serviceNumberCategory));
        long threadId = db.insert(TABLE_THREADS, null, values);
        Log.d(TAG, "insertThread: created new thread_id " + threadId + " for recipientIds " + "xxxxxxx");
        getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(Threads.CONTENT_URI, threadId), null);
    }

    private Cursor getThreadId(List<String> recipients, String[] projection, int defSyncState) {
        Set<Long> addressIds = getAddressIds(recipients);
        String recipientIds = LoggingEvents.EXTRA_CALLING_APP_NAME;
        if (addressIds.size() == URI_CONVERSATIONS_MESSAGES) {
            for (Long addressId : addressIds) {
                recipientIds = Long.toString(addressId.longValue());
            }
        } else {
            recipientIds = getSpaceSeparatedNumbers(getSortedSet(addressIds));
        }
        if (Log.isLoggable(TAG, URI_CONVERSATIONS_RECIPIENTS)) {
            Log.d(TAG, "getThreadId: recipientIds (selectionArgs) =xxxxxxx");
        }
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        Cursor cursor = null;
        try {
            String[] strArr = new String[URI_CONVERSATIONS_MESSAGES];
            strArr[URI_CONVERSATIONS] = recipientIds;
            cursor = db.query(TABLE_VALID_THREADS, projection, "recipient_ids=?", strArr, null, null, null);
            if (cursor.getCount() == 0) {
                cursor.close();
                Log.d(TAG, "getThreadId: create new thread_id for recipients xxxxxxxx");
                long privateAddrId = 0;
                if (recipients.size() == URI_CONVERSATIONS_MESSAGES) {
                    privateAddrId = checkPrivateThread((String) recipients.get(URI_CONVERSATIONS));
                }
                int serviceNumberCategory = URI_CONVERSATIONS;
                if (recipients.size() == URI_CONVERSATIONS_MESSAGES) {
                    serviceNumberCategory = MmsSmsUtils.getServiceCategory(getContext(), (String) recipients.get(URI_CONVERSATIONS), null);
                }
                insertThread(db, recipientIds, recipients.size(), privateAddrId, defSyncState, serviceNumberCategory);
                String[] strArr2 = new String[URI_CONVERSATIONS_MESSAGES];
                strArr2[URI_CONVERSATIONS] = recipientIds;
                cursor = db.query(TABLE_VALID_THREADS, projection, "recipient_ids=?", strArr2, null, null, null);
            }
            if (cursor.getCount() > URI_CONVERSATIONS_MESSAGES) {
                Log.w(TAG, "getThreadId: why is cursorCount=" + cursor.getCount());
            }
            db.setTransactionSuccessful();
            return cursor;
        } finally {
            db.endTransaction();
        }
    }

    private static String[] handleNullMessageProjection(String[] projection) {
        return projection == null ? UNION_COLUMNS : projection;
    }

    private static String[] handleNullThreadsProjection(String[] projection) {
        return projection == null ? THREADS_COLUMNS : projection;
    }

    private static String handleNullSortOrder(String sortOrder) {
        return sortOrder == null ? "normalized_date ASC" : sortOrder;
    }

    private Cursor getSimpleConversations(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return this.mOpenHelper.getReadableDatabase().query(TABLE_THREADS, projection, selection, selectionArgs, null, null, sortOrder);
    }

    private Cursor getSimpleConversations(boolean callerIsSyncAdapter, String[] projection, String selection, String[] selectionArgs, String sortOrder, boolean isCompositeMode, int placeHolderType) {
        String table;
        String threadTable = TABLE_VALID_THREADS;
        if (placeHolderType > 0) {
            threadTable = TABLE_THREADS;
        }
        String finalSelection = selection;
        if (!callerIsSyncAdapter) {
            finalSelection = MmsSmsUtils.concatenateWhere(selection, "message_count>0 OR has_draft>0");
        }
        if (isCompositeMode) {
            String nonspSelection = MmsSmsUtils.concatenateWhere(finalSelection, "(sp_type=0 OR stick_time!=0)");
            SQLiteQueryBuilder sb = new SQLiteQueryBuilder();
            sb.setTables(threadTable);
            String nonspQuery = sb.buildQuery(null, nonspSelection, null, null, null, null);
            table = "(" + nonspQuery + " union SELECT * FROM (" + sb.buildQuery(null, MmsSmsUtils.concatenateWhere(finalSelection, "(sp_type>=1 AND stick_time=0)"), null, null, SmsCb.DEFAULT_SORT_ORDER, "1") + "))";
        } else {
            table = threadTable;
        }
        return this.mOpenHelper.getReadableDatabase().query(table, projection, finalSelection, selectionArgs, null, null, sortOrder);
    }

    private Cursor getDraftThread(String[] projection, String selection, String sortOrder) {
        String[] innerProjection = new String[URI_CONVERSATIONS_RECIPIENTS];
        innerProjection[URI_CONVERSATIONS] = "_id";
        innerProjection[URI_CONVERSATIONS_MESSAGES] = WapPush.THREAD_ID;
        SQLiteQueryBuilder mmsQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder smsQueryBuilder = new SQLiteQueryBuilder();
        mmsQueryBuilder.setTables("pdu");
        smsQueryBuilder.setTables("sms");
        selection = MmsSmsUtils.concatenateWhere(selection, "deleted=0");
        String mmsSubQuery = mmsQueryBuilder.buildUnionSubQuery("transport_type", innerProjection, MMS_COLUMNS, URI_CONVERSATIONS_MESSAGES, "mms", MmsSmsUtils.concatenateWhere(selection, "msg_box=3"), null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder = smsQueryBuilder;
        String[] strArr = innerProjection;
        String smsSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArr, SMS_COLUMNS, URI_CONVERSATIONS_MESSAGES, "sms", MmsSmsUtils.concatenateWhere(selection, "type=3"), null, null);
        SQLiteQueryBuilder unionQueryBuilder = new SQLiteQueryBuilder();
        unionQueryBuilder.setDistinct(true);
        String[] strArr2 = new String[URI_CONVERSATIONS_RECIPIENTS];
        strArr2[URI_CONVERSATIONS] = mmsSubQuery;
        strArr2[URI_CONVERSATIONS_MESSAGES] = smsSubQuery;
        String unionQuery = unionQueryBuilder.buildUnionQuery(strArr2, null, null);
        sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("(" + unionQuery + ")");
        return this.mOpenHelper.getReadableDatabase().rawQuery(sQLiteQueryBuilder.buildQuery(projection, null, null, null, sortOrder, null), EMPTY_STRING_ARRAY);
    }

    private Cursor getConversations(String[] projection, String selection, String sortOrder) {
        SQLiteQueryBuilder mmsQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder smsQueryBuilder = new SQLiteQueryBuilder();
        mmsQueryBuilder.setTables("pdu");
        smsQueryBuilder.setTables("sms");
        String[] columns = handleNullMessageProjection(projection);
        String[] innerMmsProjection = makeProjectionWithDateAndThreadId(UNION_COLUMNS, 1000);
        String[] innerSmsProjection = makeProjectionWithDateAndThreadId(UNION_COLUMNS, URI_CONVERSATIONS_MESSAGES);
        selection = MmsSmsUtils.concatenateWhere(selection, "deleted=0");
        String mmsSubQuery = mmsQueryBuilder.buildUnionSubQuery("transport_type", innerMmsProjection, MMS_COLUMNS, URI_CONVERSATIONS_MESSAGES, "mms", MmsSmsUtils.concatenateWhere(selection, MMS_CONVERSATION_CONSTRAINT), WapPush.THREAD_ID, "normalized_date = MAX(normalized_date)");
        SQLiteQueryBuilder sQLiteQueryBuilder = smsQueryBuilder;
        String[] strArr = innerSmsProjection;
        String smsSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArr, SMS_COLUMNS, URI_CONVERSATIONS_MESSAGES, "sms", MmsSmsUtils.concatenateWhere(selection, SMS_CONVERSATION_CONSTRAINT), WapPush.THREAD_ID, "date = MAX(date)");
        SQLiteQueryBuilder unionQueryBuilder = new SQLiteQueryBuilder();
        unionQueryBuilder.setDistinct(true);
        String[] strArr2 = new String[URI_CONVERSATIONS_RECIPIENTS];
        strArr2[URI_CONVERSATIONS] = mmsSubQuery;
        strArr2[URI_CONVERSATIONS_MESSAGES] = smsSubQuery;
        String unionQuery = unionQueryBuilder.buildUnionQuery(strArr2, null, null);
        sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("(" + unionQuery + ")");
        return this.mOpenHelper.getReadableDatabase().rawQuery(sQLiteQueryBuilder.buildQuery(columns, null, "tid", "normalized_date = MAX(normalized_date)", sortOrder, null), EMPTY_STRING_ARRAY);
    }

    private Cursor getFirstLockedMessage(String[] projection, String selection, String sortOrder) {
        SQLiteQueryBuilder mmsQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder smsQueryBuilder = new SQLiteQueryBuilder();
        mmsQueryBuilder.setTables("pdu");
        smsQueryBuilder.setTables("sms");
        String[] idColumn = new String[URI_CONVERSATIONS_MESSAGES];
        idColumn[URI_CONVERSATIONS] = "_id";
        selection = MmsSmsUtils.concatenateWhere(selection, "deleted=0");
        String mmsSubQuery = mmsQueryBuilder.buildUnionSubQuery("transport_type", idColumn, null, URI_CONVERSATIONS_MESSAGES, "mms", selection, "_id", "locked=1");
        String smsSubQuery = smsQueryBuilder.buildUnionSubQuery("transport_type", idColumn, null, URI_CONVERSATIONS_MESSAGES, "sms", selection, "_id", "locked=1");
        SQLiteQueryBuilder unionQueryBuilder = new SQLiteQueryBuilder();
        unionQueryBuilder.setDistinct(true);
        String[] strArr = new String[URI_CONVERSATIONS_RECIPIENTS];
        strArr[URI_CONVERSATIONS] = mmsSubQuery;
        strArr[URI_CONVERSATIONS_MESSAGES] = smsSubQuery;
        return this.mOpenHelper.getReadableDatabase().rawQuery(unionQueryBuilder.buildUnionQuery(strArr, null, "1"), EMPTY_STRING_ARRAY);
    }

    private Cursor getCompleteConversations(String[] projection, String selection, String sortOrder, String groupBy) {
        return this.mOpenHelper.getReadableDatabase().rawQuery(buildConversationQuery(projection, selection, selection, sortOrder, groupBy, null), EMPTY_STRING_ARRAY);
    }

    private String[] makeProjectionWithDateAndThreadId(String[] projection, int dateMultiple) {
        int projectionSize = projection.length;
        String[] result = new String[(projectionSize + URI_CONVERSATIONS_RECIPIENTS)];
        result[URI_CONVERSATIONS] = "thread_id AS tid";
        result[URI_CONVERSATIONS_MESSAGES] = "date * " + dateMultiple + " AS normalized_date";
        for (int i = URI_CONVERSATIONS; i < projectionSize; i += URI_CONVERSATIONS_MESSAGES) {
            result[i + URI_CONVERSATIONS_RECIPIENTS] = projection[i];
        }
        return result;
    }

    private Cursor getConversationMessages(String threadIdString, String numberString, String[] projection, String selection, String sortOrder, String groupBy, String limit, boolean isBlocked) {
        try {
            Long.parseLong(threadIdString);
            String finalSelection = "thread_id = " + threadIdString;
            String finalMmsSelection = MmsSmsUtils.concatenateWhere(selection, finalSelection);
            String finalSmsSelection = finalSelection;
            if (Mms.isPhoneNumber(numberString)) {
                String escapedPhoneNumber = DatabaseUtils.sqlEscapeString(numberString);
                finalSmsSelection = "(" + finalSmsSelection + " OR address = " + escapedPhoneNumber + " OR PHONE_NUMBERS_EQUAL(address, " + escapedPhoneNumber + ", " + (this.mUseStrictPhoneNumberComparation ? "1" : "0") + "))";
            }
            return this.mOpenHelper.getReadableDatabase().rawQuery(buildConversationQuery(projection, finalMmsSelection, MmsSmsUtils.concatenateWhere(selection, finalSmsSelection), sortOrder, groupBy, limit, isBlocked), EMPTY_STRING_ARRAY);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Thread ID must be a Long.");
            return null;
        }
    }

    private Cursor getMessagesByPhoneNumber(String phoneNumber, String[] projection, String selection, String sortOrder) {
        String escapedPhoneNumber = DatabaseUtils.sqlEscapeString(phoneNumber);
        selection = MmsSmsUtils.concatenateWhere(selection, "deleted = 0");
        String finalMmsSelection = MmsSmsUtils.concatenateWhere(selection, "pdu._id = matching_addresses.address_id");
        String finalSmsSelection = MmsSmsUtils.concatenateWhere(selection, "(address=" + escapedPhoneNumber + " OR PHONE_NUMBERS_EQUAL(address, " + escapedPhoneNumber + (this.mUseStrictPhoneNumberComparation ? ", 1))" : ", 0))"));
        SQLiteQueryBuilder mmsQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder smsQueryBuilder = new SQLiteQueryBuilder();
        mmsQueryBuilder.setDistinct(true);
        smsQueryBuilder.setDistinct(true);
        mmsQueryBuilder.setTables("pdu, (SELECT _id AS address_id FROM addr WHERE (address=" + escapedPhoneNumber + " OR PHONE_NUMBERS_EQUAL(addr.address, " + escapedPhoneNumber + (this.mUseStrictPhoneNumberComparation ? ", 1))) " : ", 0))) ") + "AS matching_addresses");
        smsQueryBuilder.setTables("sms");
        String[] columns = handleNullMessageProjection(projection);
        String mmsSubQuery = mmsQueryBuilder.buildUnionSubQuery("transport_type", columns, MMS_COLUMNS, URI_CONVERSATIONS, "mms", finalMmsSelection, null, null);
        String smsSubQuery = smsQueryBuilder.buildUnionSubQuery("transport_type", columns, SMS_COLUMNS, URI_CONVERSATIONS, "sms", finalSmsSelection, null, null);
        SQLiteQueryBuilder unionQueryBuilder = new SQLiteQueryBuilder();
        unionQueryBuilder.setDistinct(true);
        String[] strArr = new String[URI_CONVERSATIONS_RECIPIENTS];
        strArr[URI_CONVERSATIONS] = mmsSubQuery;
        strArr[URI_CONVERSATIONS_MESSAGES] = smsSubQuery;
        return this.mOpenHelper.getReadableDatabase().rawQuery(unionQueryBuilder.buildUnionQuery(strArr, sortOrder, null), EMPTY_STRING_ARRAY);
    }

    private Cursor getConversationById(String threadIdString, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            Long.parseLong(threadIdString);
            String finalSelection = MmsSmsUtils.concatenateWhere(selection, "_id=" + threadIdString + " AND (message_count>0 OR has_draft>0)");
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            String[] columns = handleNullThreadsProjection(projection);
            queryBuilder.setDistinct(true);
            queryBuilder.setTables(TABLE_THREADS);
            return queryBuilder.query(this.mOpenHelper.getReadableDatabase(), columns, finalSelection, selectionArgs, sortOrder, null, null);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Thread ID must be a Long.");
            return null;
        }
    }

    private static String joinPduAndPendingMsgTables() {
        return "pdu LEFT JOIN pending_msgs ON pdu._id = pending_msgs.msg_id";
    }

    private static String[] createMmsProjection(String[] old) {
        String[] newProjection = new String[old.length];
        for (int i = URI_CONVERSATIONS; i < old.length; i += URI_CONVERSATIONS_MESSAGES) {
            if (old[i].equals("_id")) {
                newProjection[i] = "pdu._id";
            } else {
                newProjection[i] = old[i];
            }
        }
        return newProjection;
    }

    private Cursor getUndeliveredMessages(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] mmsProjection = createMmsProjection(projection);
        SQLiteQueryBuilder mmsQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder smsQueryBuilder = new SQLiteQueryBuilder();
        mmsQueryBuilder.setTables(joinPduAndPendingMsgTables());
        smsQueryBuilder.setTables("sms");
        selection = MmsSmsUtils.concatenateWhere(selection, "deleted=0 AND thread_id IS NOT NULL");
        String finalMmsSelection = MmsSmsUtils.concatenateWhere(selection, "msg_box = 4 AND m_type = 128");
        String finalSmsSelection = MmsSmsUtils.concatenateWhere(selection, "(type = 4 OR type = 5 OR type = 6)");
        String[] smsColumns = handleNullMessageProjection(projection);
        String[] innerMmsProjection = makeProjectionWithDateAndThreadId(handleNullMessageProjection(mmsProjection), 1000);
        String[] innerSmsProjection = makeProjectionWithDateAndThreadId(smsColumns, URI_CONVERSATIONS_MESSAGES);
        Set<String> columnsPresentInTable = new HashSet(MMS_COLUMNS);
        columnsPresentInTable.add("pdu._id");
        columnsPresentInTable.add("err_type");
        String mmsSubQuery = mmsQueryBuilder.buildUnionSubQuery("transport_type", innerMmsProjection, columnsPresentInTable, URI_CONVERSATIONS_MESSAGES, "mms", finalMmsSelection, null, null);
        String smsSubQuery = smsQueryBuilder.buildUnionSubQuery("transport_type", innerSmsProjection, SMS_COLUMNS, URI_CONVERSATIONS_MESSAGES, "sms", finalSmsSelection, null, null);
        SQLiteQueryBuilder unionQueryBuilder = new SQLiteQueryBuilder();
        unionQueryBuilder.setDistinct(true);
        String[] strArr = new String[URI_CONVERSATIONS_RECIPIENTS];
        strArr[URI_CONVERSATIONS] = smsSubQuery;
        strArr[URI_CONVERSATIONS_MESSAGES] = mmsSubQuery;
        String unionQuery = unionQueryBuilder.buildUnionQuery(strArr, null, null);
        SQLiteQueryBuilder outerQueryBuilder = new SQLiteQueryBuilder();
        outerQueryBuilder.setTables("(" + unionQuery + ")");
        return this.mOpenHelper.getReadableDatabase().rawQuery(outerQueryBuilder.buildQuery(smsColumns, null, null, null, sortOrder, null), EMPTY_STRING_ARRAY);
    }

    private static String[] makeProjectionWithNormalizedDate(String[] projection, boolean isMms) {
        int projectionSize = projection.length;
        ArrayList<String> result = new ArrayList(projectionSize + URI_CONVERSATIONS_MESSAGES);
        if (isMms) {
            result.add("date * 1000 + date_ms_part AS normalized_date");
        } else {
            result.add("date AS normalized_date");
        }
        for (int i = URI_CONVERSATIONS; i < projectionSize; i += URI_CONVERSATIONS_MESSAGES) {
            String p = projection[i];
            if (!"count(*)".equals(p)) {
                result.add(p);
            }
        }
        String[] strs = new String[result.size()];
        result.toArray(strs);
        return strs;
    }

    private static String buildConversationQuery(String[] projection, String selectionForMms, String selectionForSms, String sortOrder, String groupBy, String limit, boolean blocked) {
        String[] mmsProjection = createMmsProjection(projection);
        SQLiteQueryBuilder mmsQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder smsQueryBuilder = new SQLiteQueryBuilder();
        mmsQueryBuilder.setDistinct(true);
        smsQueryBuilder.setDistinct(true);
        mmsQueryBuilder.setTables(joinPduAndPendingMsgTables());
        smsQueryBuilder.setTables("sms");
        String[] smsColumns = handleNullMessageProjection(projection);
        String[] innerMmsProjection = makeProjectionWithNormalizedDate(handleNullMessageProjection(mmsProjection), true);
        String[] innerSmsProjection = makeProjectionWithNormalizedDate(smsColumns, LOCAL_LOGV);
        Set<String> columnsPresentInTable = new HashSet(MMS_COLUMNS);
        columnsPresentInTable.add("pdu._id");
        columnsPresentInTable.add("err_type");
        if (blocked) {
            selectionForMms = MmsSmsUtils.concatenateWhere(selectionForMms, BLOCKED_MSG_CONDITION);
            selectionForSms = MmsSmsUtils.concatenateWhere(selectionForSms, BLOCKED_MSG_CONDITION);
        } else {
            selectionForMms = MmsSmsUtils.concatenateWhere(selectionForMms, "deleted=0");
            selectionForSms = MmsSmsUtils.concatenateWhere(selectionForSms, "deleted=0");
        }
        String mmsSubQuery = mmsQueryBuilder.buildUnionSubQuery("transport_type", innerMmsProjection, columnsPresentInTable, URI_CONVERSATIONS, "mms", MmsSmsUtils.concatenateWhere(selectionForMms, MMS_CONVERSATION_CONSTRAINT), null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder = smsQueryBuilder;
        String[] strArr = innerSmsProjection;
        String smsSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArr, SMS_COLUMNS, URI_CONVERSATIONS, "sms", MmsSmsUtils.concatenateWhere(selectionForSms, SMS_CONVERSATION_CONSTRAINT), null, null);
        SQLiteQueryBuilder unionQueryBuilder = new SQLiteQueryBuilder();
        unionQueryBuilder.setDistinct(true);
        String[] strArr2 = new String[URI_CONVERSATIONS_RECIPIENTS];
        strArr2[URI_CONVERSATIONS] = smsSubQuery;
        strArr2[URI_CONVERSATIONS_MESSAGES] = mmsSubQuery;
        String unionQuery = unionQueryBuilder.buildUnionQuery(strArr2, handleNullSortOrder(sortOrder), limit);
        sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("(" + unionQuery + ")");
        return sQLiteQueryBuilder.buildQuery(smsColumns, null, groupBy, null, sortOrder, null);
    }

    private static String buildConversationQuery(String[] projection, String selectionForMms, String selectionForSms, String sortOrder, String groupBy, String limit) {
        return buildConversationQuery(projection, selectionForMms, selectionForSms, sortOrder, groupBy, limit, LOCAL_LOGV);
    }

    public String getType(Uri uri) {
        return VND_ANDROID_DIR_MMS_SMS;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        MmsSmsUtils.setPreviousOpTime();
        OperationPerfProfiler operationPerfProfiler = new OperationPerfProfiler("delete " + uri.toString() + " with selection " + selection);
        boolean callerIsSyncAdapter = MmsSmsUtils.readBooleanQueryParameter(uri, "caller_is_syncadapter", LOCAL_LOGV);
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        Context context = getContext();
        int affectedRows = URI_CONVERSATIONS;
        String finalSelection = selection;
        BatchModeHelper helper;
        long threadId;
        switch (URI_MATCHER.match(uri)) {
            case URI_CONVERSATIONS /*0*/:
                helper = BatchModeHelper.getInstance();
                helper.beginBatchOps(getContext(), db);
                try {
                    finalSelection = MmsSmsUtils.concatenateWhere(selection, NORMAL_MSG_CONDITION);
                    affectedRows = MmsProvider.deleteMessages(context, db, finalSelection, selectionArgs, uri) + SmsProvider.deleteMessages(context, db, finalSelection, selectionArgs, uri);
                    affectedRows += HmsProvider.deleteMessages(context, db, selection, selectionArgs, uri);
                    break;
                } finally {
                    helper.endBatchOps();
                }
            case URI_CONVERSATIONS_MESSAGES /*1*/:
                try {
                    threadId = Long.parseLong(uri.getLastPathSegment());
                    affectedRows = deleteConversation(uri, selection, selectionArgs);
                    String str = TAG;
                    Object[] objArr = new Object[URI_CONVERSATIONS_MESSAGES];
                    objArr[URI_CONVERSATIONS] = Long.valueOf(threadId);
                    Log.d(str, String.format("Removed thread %d", objArr));
                    if (affectedRows > 0) {
                        MmsSmsDatabaseHelper.updateThread(getContext(), db, threadId);
                        if (!callerIsSyncAdapter) {
                            MmsSmsUtils.requestSync(context, "local.stkthrd.sync");
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Thread ID must be a long.");
                    break;
                }
                break;
            case URI_OBSOLETE_THREADS /*11*/:
                affectedRows = MmsSmsDatabaseHelper.deleteObsoleteThread(getContext(), db, -1);
                break;
            case URI_SIM_CARDS /*106*/:
                finalSelection = makeMarkerSelection(uri, selection);
                affectedRows = db.delete(TABLE_SIM_CARDS, finalSelection, selectionArgs);
                if (affectedRows > 0 && !callerIsSyncAdapter) {
                    MmsSmsUtils.requestSync(context, "local.sms.sync");
                    break;
                }
            case URI_PRIVATE_ADDRESSES /*107*/:
                break;
            case URI_PRIVATE_ADDRESS_ID /*108*/:
                String id = (String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES);
                finalSelection = MmsSmsUtils.concatenateWhere(selection, "_id=" + id);
                break;
            case URI_BLOCKED_CONVERSATIONS /*109*/:
                helper = BatchModeHelper.getInstance();
                helper.beginBatchOps(getContext(), db);
                try {
                    selection = MmsSmsUtils.concatenateWhere(selection, BLOCKED_MSG_CONDITION);
                    uri = uri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
                    affectedRows = MmsProvider.deleteMessages(context, db, selection, selectionArgs, uri);
                    affectedRows += SmsProvider.deleteMessages(context, db, selection, selectionArgs, uri);
                    break;
                } finally {
                    helper.endBatchOps();
                }
            case URI_BLOCKED_CONVERSATION_ID /*110*/:
                helper = BatchModeHelper.getInstance();
                helper.beginBatchOps(getContext(), db);
                try {
                    String address = uri.getQueryParameter("blocked_conv_addr");
                    threadId = MmsSmsUtils.getThreadIdByAddr(getContext(), address);
                    if (threadId != -1) {
                        selection = MmsSmsUtils.concatenateWhere(selection, "deleted = 1 AND block_type > 1 AND thread_id=" + threadId);
                        uri = uri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
                        affectedRows = MmsProvider.deleteMessages(context, db, selection, selectionArgs, uri) + SmsProvider.deleteMessages(context, db, selection, selectionArgs, uri);
                    }
                    helper.endBatchOps();
                    break;
                } catch (Throwable th) {
                    helper.endBatchOps();
                }
            case URI_HMS_CONVERSATION_ID /*112*/:
                this.mOpenHelper.getWritableDatabase().delete(TABLE_THREADS, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(NO_DELETES_INSERTS_OR_UPDATES);
        }
        if (!callerIsSyncAdapter) {
            finalSelection = MmsSmsUtils.concatenateWhere(finalSelection, "deleted=0");
        }
        db.beginTransaction();
        try {
            ContentValues v;
            Set<Long> ids = MmsSmsUtils.queryLongValuesToSet(db, TABLE_PRIVATE_ADDRESSES, "_id", finalSelection, selectionArgs);
            if (callerIsSyncAdapter) {
                affectedRows = db.delete(TABLE_PRIVATE_ADDRESSES, finalSelection, selectionArgs);
            } else {
                v = new ContentValues(URI_CONVERSATIONS_RECIPIENTS);
                v.put("deleted", Boolean.valueOf(true));
                v.put("sync_state", Integer.valueOf(URI_CONVERSATIONS));
                affectedRows = db.update(TABLE_PRIVATE_ADDRESSES, v, finalSelection, selectionArgs);
            }
            v = new ContentValues(URI_CONVERSATIONS_MESSAGES);
            v.putNull("private_addr_ids");
            for (Long longValue : ids) {
                long id2 = longValue.longValue();
                db.update(TABLE_THREADS, v, "private_addr_ids=" + id2, null);
            }
            db.setTransactionSuccessful();
            if (!callerIsSyncAdapter) {
                MmsSmsUtils.requestSync(context, "local.priaddr.sync");
            }
            db.endTransaction();
            if (affectedRows > 0) {
                context.getContentResolver().notifyChange(uri, null);
            }
            operationPerfProfiler.prof();
            return affectedRows;
        } catch (Throwable th2) {
            db.endTransaction();
        }
    }

    private int deleteConversation(Uri uri, String selection, String[] selectionArgs) {
        String threadId = uri.getLastPathSegment();
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        String finalSelection = MmsSmsUtils.concatenateWhere(selection, "thread_id = " + threadId);
        BatchModeHelper helper = BatchModeHelper.getInstance();
        helper.beginBatchOps(getContext(), db);
        int affectedRows = URI_CONVERSATIONS;
        try {
            affectedRows = (MmsProvider.deleteMessages(getContext(), db, finalSelection, selectionArgs, uri) + SmsProvider.deleteMessages(getContext(), db, finalSelection, selectionArgs, uri)) + HmsProvider.deleteMessages(getContext(), db, finalSelection, selectionArgs, uri);
            return affectedRows;
        } finally {
            helper.endBatchOps();
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        MmsSmsUtils.setPreviousOpTime();
        OperationPerfProfiler operationPerfProfiler = new OperationPerfProfiler("insert " + uri.toString());
        boolean callerIsSyncAdapter = MmsSmsUtils.readBooleanQueryParameter(uri, "caller_is_syncadapter", LOCAL_LOGV);
        boolean needFullInsertUri = MmsSmsUtils.readBooleanQueryParameter(uri, "need_full_insert_uri", LOCAL_LOGV);
        SQLiteDatabase db;
        Cursor c;
        String insertPath;
        long id;
        long marker;
        Uri parse;
        switch (URI_MATCHER.match(uri)) {
            case URI_CONVERSATIONS /*0*/:
                if (callerIsSyncAdapter) {
                    List<String> recipients = getCleanRecipients(uri);
                    db = this.mOpenHelper.getWritableDatabase();
                    db.beginTransaction();
                    try {
                        String[] strArr = new String[URI_MESSAGES_BY_PHONE];
                        strArr[URI_CONVERSATIONS] = "_id";
                        strArr[URI_CONVERSATIONS_MESSAGES] = "marker";
                        strArr[URI_CONVERSATIONS_RECIPIENTS] = "sync_state";
                        c = getThreadId(recipients, strArr, URI_CONVERSATIONS_RECIPIENTS);
                        insertPath = LoggingEvents.EXTRA_CALLING_APP_NAME;
                        if (needFullInsertUri) {
                            insertPath = "ignored/";
                        }
                        if (c != null) {
                            if (c.moveToFirst()) {
                                id = c.getLong(URI_CONVERSATIONS);
                                marker = c.getLong(URI_CONVERSATIONS_MESSAGES);
                                long newMarker = values.getAsLong("marker").longValue();
                                switch (c.getInt(URI_CONVERSATIONS_RECIPIENTS)) {
                                    case URI_CONVERSATIONS /*0*/:
                                        Log.i(TAG, "The state of downloaded thread " + id + " is DIRTY. ignoring.");
                                        break;
                                    case URI_CONVERSATIONS_MESSAGES /*1*/:
                                        Log.w(TAG, "The state of downloaded thread " + id + " is SYNCING.");
                                        break;
                                    case URI_CONVERSATIONS_RECIPIENTS /*2*/:
                                        break;
                                    case URI_MESSAGES_BY_PHONE /*3*/:
                                        Log.w(TAG, "The state of downloaded thread " + id + " is SYNC_STATE_ERROR. Skip.");
                                        break;
                                    case URI_THREAD_ID /*4*/:
                                        Log.w(TAG, "The state of downloaded thread " + id + " is SYNC_STATE_NOT_UPLOADALBE. Skip.");
                                        break;
                                }
                                db.update(TABLE_THREADS, values, "_id=" + id, null);
                                insertPath = "updated/";
                                db.setTransactionSuccessful();
                                parse = Uri.parse(PrivateAddresses.CONTENT_URI + "/" + insertPath + id);
                                c.close();
                                db.endTransaction();
                                return parse;
                            }
                            c.close();
                        }
                        db.endTransaction();
                        return null;
                    } catch (Throwable th) {
                        db.endTransaction();
                    }
                } else {
                    throw new IllegalArgumentException("Only syncer can create conversations here");
                }
            case URI_SIM_CARDS /*106*/:
                if (uri.getPathSegments().size() >= URI_CONVERSATIONS_RECIPIENTS) {
                    throw new UnsupportedOperationException(NO_DELETES_INSERTS_OR_UPDATES);
                }
                Long bindId = values.getAsLong("bind_id");
                if (bindId == null || bindId.longValue() <= 0) {
                    Log.e(TAG, "Invalid bindId " + bindId);
                    return null;
                }
                MmsSmsUtils.removeSpaceForAddressValue(values, PhoneLookup.NUMBER);
                db = this.mOpenHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    String str = TABLE_SIM_CARDS;
                    String[] strArr2 = new String[URI_CONVERSATIONS_MESSAGES];
                    strArr2[URI_CONVERSATIONS] = "_id";
                    c = db.query(str, strArr2, "bind_id=" + bindId, null, null, null, null);
                    if (c == null) {
                        db.endTransaction();
                        return null;
                    } else if (c.getCount() > 0) {
                        Log.e(TAG, "bindId " + bindId + " already exists!");
                        c.close();
                        db.endTransaction();
                        return null;
                    } else {
                        c.close();
                        parse = Uri.withAppendedPath(uri, String.valueOf(db.insert(TABLE_SIM_CARDS, null, values)));
                        if (!callerIsSyncAdapter) {
                            MmsSmsUtils.requestSync(getContext(), "local.sms.sync");
                        }
                        db.setTransactionSuccessful();
                        operationPerfProfiler.prof();
                        db.endTransaction();
                        return parse;
                    }
                } catch (Throwable th2) {
                    db.endTransaction();
                }
                break;
            case URI_PRIVATE_ADDRESSES /*107*/:
                String where;
                ContentValues contentValues = new ContentValues(values);
                if (!values.containsKey("deleted")) {
                    contentValues.put("deleted", Integer.valueOf(URI_CONVERSATIONS));
                }
                if (callerIsSyncAdapter) {
                    if (!values.containsKey("sync_state")) {
                        contentValues.put("sync_state", Integer.valueOf(URI_CONVERSATIONS_RECIPIENTS));
                    }
                } else {
                    contentValues.put("sync_state", Integer.valueOf(URI_CONVERSATIONS));
                }
                MmsSmsUtils.removeSpaceForAddressValue(contentValues, TagYellowPage.ADDRESS);
                String address = contentValues.getAsString(TagYellowPage.ADDRESS);
                if (Mms.isPhoneNumber(address)) {
                    where = "PHONE_NUMBERS_EQUAL(address, ?, " + (this.mUseStrictPhoneNumberComparation ? "1" : "0") + ")";
                } else {
                    where = "address=?";
                }
                db = this.mOpenHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    String str2 = TABLE_PRIVATE_ADDRESSES;
                    String[] strArr3 = new String[URI_THREAD_ID];
                    strArr3[URI_CONVERSATIONS] = "_id";
                    strArr3[URI_CONVERSATIONS_MESSAGES] = "sync_state";
                    strArr3[URI_CONVERSATIONS_RECIPIENTS] = "marker";
                    strArr3[URI_MESSAGES_BY_PHONE] = "deleted";
                    String[] strArr4 = new String[URI_CONVERSATIONS_MESSAGES];
                    strArr4[URI_CONVERSATIONS] = address;
                    c = db.query(str2, strArr3, where, strArr4, null, null, null);
                    insertPath = LoggingEvents.EXTRA_CALLING_APP_NAME;
                    if (needFullInsertUri) {
                        insertPath = "ignored/";
                    }
                    if (c != null) {
                        long addrId;
                        if (c.moveToFirst()) {
                            id = c.getLong(URI_CONVERSATIONS);
                            boolean wasDeleted = c.getInt(URI_MESSAGES_BY_PHONE) > 0 ? true : LOCAL_LOGV;
                            if (callerIsSyncAdapter) {
                                int syncState = c.getInt(URI_CONVERSATIONS_MESSAGES);
                                marker = c.getLong(URI_CONVERSATIONS_RECIPIENTS);
                                Long newMarker2 = contentValues.getAsLong("marker");
                                if (newMarker2 != null && newMarker2.longValue() > marker) {
                                    switch (syncState) {
                                        case URI_CONVERSATIONS /*0*/:
                                            Log.i(TAG, "The state of downloaded item " + id + " is DIRTY. ignoring.");
                                            break;
                                        case URI_CONVERSATIONS_MESSAGES /*1*/:
                                            Log.w(TAG, "The state of downloaded item " + id + " is SYNCING.");
                                            break;
                                        case URI_CONVERSATIONS_RECIPIENTS /*2*/:
                                            break;
                                        case URI_MESSAGES_BY_PHONE /*3*/:
                                            Log.w(TAG, "The state of downloaded item " + id + " is SYNC_STATE_ERROR. Skip.");
                                            break;
                                        case URI_THREAD_ID /*4*/:
                                            Log.w(TAG, "The state of downloaded item " + id + " is SYNC_STATE_NOT_UPLOADALBE. Skip.");
                                            break;
                                    }
                                    db.update(TABLE_PRIVATE_ADDRESSES, contentValues, "_id=" + id, null);
                                    if (needFullInsertUri) {
                                        if (wasDeleted) {
                                            insertPath = "restored/";
                                        } else {
                                            insertPath = "updated/";
                                        }
                                    }
                                }
                            } else {
                                db.update(TABLE_PRIVATE_ADDRESSES, contentValues, "_id=" + id, null);
                                if (needFullInsertUri) {
                                    if (wasDeleted) {
                                        insertPath = "restored/";
                                    } else {
                                        insertPath = "updated/";
                                    }
                                }
                                addrId = getSingleAddressId(address);
                                contentValues = new ContentValues(URI_CONVERSATIONS_MESSAGES);
                                contentValues.put("private_addr_ids", Long.valueOf(id));
                                db.update(TABLE_THREADS, contentValues, "recipient_ids=" + addrId, null);
                            }
                        } else {
                            id = db.insert(TABLE_PRIVATE_ADDRESSES, null, contentValues);
                            if (needFullInsertUri) {
                                insertPath = "inserted/";
                            }
                            addrId = getSingleAddressId(address);
                            contentValues = new ContentValues(URI_CONVERSATIONS_MESSAGES);
                            contentValues.put("private_addr_ids", Long.valueOf(id));
                            db.update(TABLE_THREADS, contentValues, "recipient_ids=" + addrId, null);
                        }
                        c.close();
                        db.setTransactionSuccessful();
                        if (!callerIsSyncAdapter) {
                            MmsSmsUtils.requestSync(getContext(), "local.priaddr.sync");
                        }
                        parse = Uri.parse(PrivateAddresses.CONTENT_URI + "/" + insertPath + id);
                        db.endTransaction();
                        return parse;
                    }
                    Log.e(TAG, "Failed querying existing private address");
                    db.endTransaction();
                    return null;
                } catch (Throwable th3) {
                    db.endTransaction();
                }
            case URI_GET_CDMA_MSGID /*200*/:
                int msgId = SystemProperties.getInt("persist.radio.cdma.msgid", URI_CONVERSATIONS_MESSAGES);
                String nextMsgId = Integer.toString((msgId % 65535) + URI_CONVERSATIONS_MESSAGES);
                SystemProperties.set("persist.radio.cdma.msgid", nextMsgId);
                Log.v(TAG, "next msgId is " + nextMsgId + " and current msgId is " + msgId);
                return Uri.parse("content://mms-sms/getCdmaMsgId/" + msgId);
            default:
                throw new UnsupportedOperationException(NO_DELETES_INSERTS_OR_UPDATES);
        }
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        MmsSmsUtils.setPreviousOpTime();
        OperationPerfProfiler operationPerfProfiler = new OperationPerfProfiler("update " + uri.toString() + " with selection " + selection);
        boolean callerIsSyncAdapter = MmsSmsUtils.readBooleanQueryParameter(uri, "caller_is_syncadapter", LOCAL_LOGV);
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        int affectedRows = URI_CONVERSATIONS;
        ContentValues finalValues;
        long threadId;
        switch (URI_MATCHER.match(uri)) {
            case URI_CONVERSATIONS /*0*/:
                finalValues = new ContentValues(values);
                boolean shouldSync = LOCAL_LOGV;
                if (callerIsSyncAdapter) {
                    if (!values.containsKey("sync_state")) {
                        finalValues.put("sync_state", Integer.valueOf(URI_CONVERSATIONS_RECIPIENTS));
                    }
                } else {
                    if (values.containsKey("stick_time")) {
                        finalValues.put("sync_state", Integer.valueOf(URI_CONVERSATIONS));
                        shouldSync = true;
                    }
                }
                affectedRows = db.update(TABLE_THREADS, finalValues, selection, selectionArgs);
                if (affectedRows > 0 && shouldSync) {
                    MmsSmsUtils.requestSync(getContext(), "local.stkthrd.sync");
                    break;
                }
            case URI_CONVERSATIONS_MESSAGES /*1*/:
                finalValues = new ContentValues(values);
                MmsSmsUtils.removeSpaceForAddressValue(finalValues, "addresses");
                affectedRows = updateConversation((String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES), finalValues, selection, selectionArgs);
                if (affectedRows > 0 && !callerIsSyncAdapter) {
                    MmsSmsUtils.requestSync(getContext(), "local.sms.sync");
                    break;
                }
            case URI_CANONICAL_ADDRESS /*5*/:
                finalValues = new ContentValues(values);
                if (finalValues.containsKey(TagYellowPage.ADDRESS)) {
                    finalValues.put(TagYellowPage.ADDRESS, MmsSmsUtils.deleteSpecialIntlCode(MmsSmsUtils.removeSpaceForAddress(finalValues.getAsString(TagYellowPage.ADDRESS))));
                }
                String extraSelection = "_id=" + ((String) uri.getPathSegments().get(URI_CONVERSATIONS_MESSAGES));
                affectedRows = db.update(TABLE_CANONICAL_ADDRESSES, finalValues, TextUtils.isEmpty(selection) ? extraSelection : extraSelection + " AND " + selection, null);
                break;
            case URI_PENDING_MSG /*6*/:
                BatchModeHelper helper = BatchModeHelper.getInstance();
                helper.beginBatchOps(getContext(), db);
                try {
                    HashSet<Long> msgIds = MmsSmsUtils.queryLongValuesToSet(db, TABLE_PENDING_MSG, "msg_id", selection, selectionArgs);
                    HashSet<Long> threadIds = new HashSet();
                    Iterator i$ = msgIds.iterator();
                    while (i$.hasNext()) {
                        long msgId = ((Long) i$.next()).longValue();
                        threadId = MmsSmsUtils.msgIdToThreadId(db, msgId);
                        helper.markMmsDirty(db, msgId);
                        threadIds.add(Long.valueOf(threadId));
                    }
                    affectedRows = db.update(TABLE_PENDING_MSG, values, selection, null);
                    i$ = threadIds.iterator();
                    while (i$.hasNext()) {
                        helper.updateThreadErrorState(db, ((Long) i$.next()).longValue());
                    }
                    break;
                } finally {
                    helper.endBatchOps();
                }
            case URI_SIM_CARDS /*106*/:
                finalValues = new ContentValues(values);
                MmsSmsUtils.removeSpaceForAddressValue(finalValues, PhoneLookup.NUMBER);
                affectedRows = db.update(TABLE_SIM_CARDS, finalValues, makeMarkerSelection(uri, selection), selectionArgs);
                if (affectedRows > 0 && !callerIsSyncAdapter) {
                    MmsSmsUtils.requestSync(getContext(), "local.sms.sync");
                    break;
                }
            case URI_PRIVATE_ADDRESSES /*107*/:
                finalValues = new ContentValues(values);
                MmsSmsUtils.removeSpaceForAddressValue(finalValues, TagYellowPage.ADDRESS);
                if (!callerIsSyncAdapter) {
                    selection = MmsSmsUtils.concatenateWhere(selection, "deleted = 0");
                    finalValues.put("sync_state", Integer.valueOf(URI_CONVERSATIONS));
                } else if (!finalValues.containsKey("sync_state")) {
                    finalValues.put("sync_state", Integer.valueOf(URI_CONVERSATIONS_RECIPIENTS));
                }
                affectedRows = db.update(TABLE_PRIVATE_ADDRESSES, values, selection, selectionArgs);
                if (!callerIsSyncAdapter && affectedRows > 0) {
                    MmsSmsUtils.requestSync(getContext(), "local.priaddr.sync");
                    break;
                }
            case URI_BLOCKED_CONVERSATIONS /*109*/:
                updateBlockedConversation(db, uri, values, selection, selectionArgs);
                break;
            case URI_BLOCKED_CONVERSATION_ID /*110*/:
                String address = uri.getQueryParameter("blocked_conv_addr");
                if (TextUtils.isEmpty(address)) {
                    threadId = Long.valueOf(uri.getLastPathSegment()).longValue();
                } else {
                    threadId = MmsSmsUtils.getThreadIdByAddr(getContext(), address);
                }
                updateBlockedConversation(db, uri, values, MmsSmsUtils.concatenateWhere(selection, "thread_id=" + threadId), selectionArgs);
                break;
            case URI_CONVERSATIONS_STATUS /*113*/:
                break;
            default:
                throw new UnsupportedOperationException(NO_DELETES_INSERTS_OR_UPDATES);
        }
        Integer read = values.getAsInteger(WapPush.READ);
        if (read != null && read.intValue() == URI_CONVERSATIONS_MESSAGES) {
            values.put(WapPush.SEEN, read);
        }
        operationPerfProfiler.prof();
        return affectedRows;
    }

    private int updateConversation(String threadIdString, ContentValues values, String selection, String[] selectionArgs) {
        try {
            Long.parseLong(threadIdString);
            Context context = getContext();
            String finalSelection = MmsSmsUtils.concatenateWhere(selection, "thread_id=" + threadIdString);
            ContentResolver cr = context.getContentResolver();
            BatchModeHelper helper = BatchModeHelper.getInstance();
            String inPrivateWlStr = String.valueOf(MmsSmsUtils.checkPrivateMsgWhiteList(context));
            String inBlockedWlStr = String.valueOf(MmsSmsUtils.checkBlockedMsgWhiteList(context));
            long token = Binder.clearCallingIdentity();
            try {
                helper.beginBatchOps(context, this.mOpenHelper.getWritableDatabase());
                int affectedRows = (cr.update(Mms.CONTENT_URI.buildUpon().appendQueryParameter(MmsSmsUtils.IN_BLOCKED_WHITELIST, inBlockedWlStr).appendQueryParameter(MmsSmsUtils.IN_PRIVATE_WHITELIST, inPrivateWlStr).build(), values, finalSelection, selectionArgs) + cr.update(Sms.CONTENT_URI.buildUpon().appendQueryParameter(MmsSmsUtils.IN_BLOCKED_WHITELIST, inBlockedWlStr).appendQueryParameter(MmsSmsUtils.IN_PRIVATE_WHITELIST, inPrivateWlStr).build(), values, finalSelection, selectionArgs)) + cr.update(Hms.CONTENT_URI, values, finalSelection, selectionArgs);
                helper.endBatchOps();
                Binder.restoreCallingIdentity(token);
                return affectedRows;
            } catch (Throwable th) {
                Binder.restoreCallingIdentity(token);
            }
        } catch (NumberFormatException e) {
            Log.e(TAG, "Thread ID must be a Long.");
            return URI_CONVERSATIONS;
        }
    }

    private static void initializeColumnSets() {
        int i;
        int commonColumnCount = MMS_SMS_COLUMNS.length;
        int mmsOnlyColumnCount = MMS_ONLY_COLUMNS.length;
        int smsOnlyColumnCount = SMS_ONLY_COLUMNS.length;
        Set<String> unionColumns = new HashSet();
        for (i = URI_CONVERSATIONS; i < commonColumnCount; i += URI_CONVERSATIONS_MESSAGES) {
            MMS_COLUMNS.add(MMS_SMS_COLUMNS[i]);
            SMS_COLUMNS.add(MMS_SMS_COLUMNS[i]);
            unionColumns.add(MMS_SMS_COLUMNS[i]);
        }
        for (i = URI_CONVERSATIONS; i < mmsOnlyColumnCount; i += URI_CONVERSATIONS_MESSAGES) {
            MMS_COLUMNS.add(MMS_ONLY_COLUMNS[i]);
            unionColumns.add(MMS_ONLY_COLUMNS[i]);
        }
        for (i = URI_CONVERSATIONS; i < smsOnlyColumnCount; i += URI_CONVERSATIONS_MESSAGES) {
            SMS_COLUMNS.add(SMS_ONLY_COLUMNS[i]);
            unionColumns.add(SMS_ONLY_COLUMNS[i]);
        }
        i = URI_CONVERSATIONS;
        for (String columnName : unionColumns) {
            int i2 = i + URI_CONVERSATIONS_MESSAGES;
            UNION_COLUMNS[i] = columnName;
            i = i2;
        }
    }

    private void updateBlockedConversation(SQLiteDatabase db, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        BatchModeHelper helper = BatchModeHelper.getInstance();
        Context context = getContext();
        String inPrivateWlStr = String.valueOf(MmsSmsUtils.checkPrivateMsgWhiteList(context));
        String inBlockedWlStr = String.valueOf(MmsSmsUtils.checkBlockedMsgWhiteList(context));
        long token = Binder.clearCallingIdentity();
        try {
            helper.beginBatchOps(context, db);
            String blockedFlag = "0";
            if (values.containsKey("block_type")) {
                if (values.getAsInteger("block_type").intValue() == 0) {
                    blockedFlag = "1";
                }
            } else {
                if (values.containsKey(WapPush.READ)) {
                    blockedFlag = "1";
                }
            }
            ContentResolver cr = context.getContentResolver();
            ContentValues mmsValues = values;
            cr.update(Inbox.CONTENT_URI.buildUpon().appendQueryParameter("blocked_flag", blockedFlag).appendQueryParameter(MmsSmsUtils.IN_BLOCKED_WHITELIST, inBlockedWlStr).appendQueryParameter(MmsSmsUtils.IN_PRIVATE_WHITELIST, inPrivateWlStr).build(), values, selection, selectionArgs);
            mmsValues.remove("deleted");
            cr.update(Mms.Inbox.CONTENT_URI.buildUpon().appendQueryParameter("blocked_flag", blockedFlag).appendQueryParameter(MmsSmsUtils.IN_BLOCKED_WHITELIST, inBlockedWlStr).appendQueryParameter(MmsSmsUtils.IN_PRIVATE_WHITELIST, inPrivateWlStr).build(), mmsValues, selection, selectionArgs);
            helper.updateThreadBlockEntry(db);
            helper.endBatchOps();
            Binder.restoreCallingIdentity(token);
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(token);
        }
    }
}
