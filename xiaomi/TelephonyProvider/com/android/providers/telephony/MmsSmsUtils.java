package com.android.providers.telephony;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.provider.Settings.System;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import android.util.Log;
import com.android.common.speech.LoggingEvents;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.SmsCb.Conversations;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import java.util.Collection;
import java.util.HashSet;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import miui.provider.yellowpage.YellowPageUtils.TagYellowPage;
import miui.security.SecurityManager;
import miui.util.XMAccountUtils;
import miui.yellowpage.MiPubUtils;
import miui.yellowpage.YellowPageMipub;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;

public class MmsSmsUtils {
    private static final String[] BLOCKED_MSG_WHITELIST;
    public static final String IN_BLOCKED_WHITELIST = "in_blocked_wl";
    public static final String IN_PRIVATE_WHITELIST = "in_private_wl";
    private static final String[] PRIVATE_MSG_WHITELIST;
    private static final String[] PROVIDER_CALL_WHITELIST;
    private static String[] SPECIAL_INTERNATIONAL_CODE;
    public static boolean SUPPORT_SYNC_ADAPTER;
    private static String TAG;
    private static volatile long sPreviousOpTime;
    private static SyncHandler sSyncHandler;
    private static String sXiaomiAccountName;
    private static boolean sXiaomiAccountQueried;

    public static class OperationPerfProfiler {
        private static final long OPERATION_TIME_LIMIT = 500;
        private String mOp;
        private long mStartTime;

        public OperationPerfProfiler(String op) {
            this.mStartTime = System.currentTimeMillis();
            this.mOp = op;
        }

        public void prof() {
            if ((System.currentTimeMillis() - this.mStartTime) - OPERATION_TIME_LIMIT >= OPERATION_TIME_LIMIT) {
                Log.e(MmsSmsUtils.TAG, String.format("Operation %s takes over %d ms.", new Object[]{this.mOp, Long.valueOf(duration)}));
            }
        }
    }

    static {
        TAG = "MmsSmsUtils";
        SUPPORT_SYNC_ADAPTER = true;
        sXiaomiAccountQueried = false;
        sXiaomiAccountName = null;
        sPreviousOpTime = 0;
        PRIVATE_MSG_WHITELIST = new String[]{"com.android.mms", "com.miui.cloudservice", "com.miui.mmslite"};
        BLOCKED_MSG_WHITELIST = new String[]{"com.android.mms", "com.miui.antispam", "com.miui.mmslite"};
        PROVIDER_CALL_WHITELIST = new String[]{"com.android.phone", "com.android.providers.telephony"};
        SPECIAL_INTERNATIONAL_CODE = new String[]{"60"};
    }

    public static HashSet<Long> queryLongValuesToSet(SQLiteDatabase db, String table, String longColumn, String selection, String[] selectionArgs) {
        Cursor c = null;
        HashSet<Long> set = new HashSet();
        try {
            c = db.query(table, new String[]{longColumn}, selection, selectionArgs, null, null, null);
            if (c != null) {
                c.moveToPosition(-1);
                while (c.moveToNext()) {
                    set.add(Long.valueOf(c.getLong(0)));
                }
            }
            if (c != null) {
                c.close();
            }
            return set;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
        }
    }

    public static void requestSync(Context context, String name) {
        if (sSyncHandler == null) {
            sSyncHandler = new SyncHandler();
        }
        if (!TextUtils.isEmpty(name)) {
            sSyncHandler.scheduleSync(context, name);
        }
    }

    public static String readStringQueryParameter(Uri uri, String parameter, String defaultValue) {
        String query = uri.getEncodedQuery();
        if (query == null) {
            return defaultValue;
        }
        int index = query.indexOf(parameter);
        if (index == -1) {
            return defaultValue;
        }
        index += parameter.length() + 1;
        int endIndex = query.indexOf(38, index);
        if (endIndex == -1) {
            endIndex = query.length();
        }
        return query.substring(index, endIndex);
    }

    public static boolean readBooleanQueryParameter(Uri uri, String parameter, boolean defaultValue) {
        boolean z = true;
        String query = uri.getEncodedQuery();
        if (query == null) {
            return defaultValue;
        }
        int index = query.indexOf(parameter);
        if (index == -1) {
            return defaultValue;
        }
        index += parameter.length();
        if (matchQueryParameter(query, index, "=0", false) || matchQueryParameter(query, index, "=false", true)) {
            z = false;
        }
        return z;
    }

    public static boolean callerIsSyncAdapter(Uri uri) {
        return SUPPORT_SYNC_ADAPTER && readBooleanQueryParameter(uri, "caller_is_syncadapter", false);
    }

    public static boolean checkDuplication(Uri uri) {
        return readBooleanQueryParameter(uri, "check_duplication", false);
    }

    public static boolean suppressMakingMmsPreview(Uri uri) {
        return SUPPORT_SYNC_ADAPTER && readBooleanQueryParameter(uri, "supress_making_mms_preview", false);
    }

    private static boolean matchQueryParameter(String query, int index, String value, boolean ignoreCase) {
        int length = value.length();
        if (!query.regionMatches(ignoreCase, index, value, 0, length)) {
            return false;
        }
        if (query.length() == index + length || query.charAt(index + length) == '&') {
            return true;
        }
        return false;
    }

    public static long msgIdToThreadId(SQLiteDatabase db, long msgId) {
        Cursor c = null;
        try {
            String[] strArr = new String[]{WapPush.THREAD_ID};
            String str = "_id=" + msgId;
            c = db.query("pdu", strArr, str, null, null, null, null);
            if (c == null || !c.moveToFirst()) {
                if (c != null) {
                    c.close();
                }
                return 0;
            }
            long threadId = c.getLong(0);
            if (c == null) {
                return threadId;
            }
            c.close();
            return threadId;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
        }
    }

    public static HashSet<String> getAddressList(ContentValues values) {
        String addresses = values.getAsString("addresses");
        if (addresses == null) {
            addresses = values.getAsString(TagYellowPage.ADDRESS);
        }
        if (addresses == null) {
            return null;
        }
        String[] addressArray = addresses.split(",");
        HashSet<String> addressSet = new HashSet();
        for (Object add : addressArray) {
            addressSet.add(add);
        }
        return addressSet;
    }

    public static long getThreadId(Context context, ContentValues values) {
        Long threadId = values.getAsLong(WapPush.THREAD_ID);
        if (threadId != null && threadId.longValue() > 0) {
            return threadId.longValue();
        }
        HashSet<String> addressSet = getAddressList(values);
        if (addressSet == null) {
            return 0;
        }
        long token = Binder.clearCallingIdentity();
        try {
            long orCreateThreadId = Threads.getOrCreateThreadId(context, addressSet);
            return orCreateThreadId;
        } finally {
            Binder.restoreCallingIdentity(token);
        }
    }

    public static long getThreadIdByAddr(Context context, String address) {
        ContentValues values = new ContentValues();
        values.put(TagYellowPage.ADDRESS, address);
        return getThreadId(context, values);
    }

    public static String concatenateWhere(String a, String b) {
        if (a == null) {
            a = LoggingEvents.EXTRA_CALLING_APP_NAME;
        }
        if (b == null) {
            b = LoggingEvents.EXTRA_CALLING_APP_NAME;
        }
        int ga = a.toUpperCase().indexOf("GROUP BY");
        int gb = b.toUpperCase().indexOf("GROUP BY");
        if (ga == -1) {
            ga = a.length();
        }
        if (gb == -1) {
            gb = b.length();
        }
        String la = a.substring(0, ga).trim();
        String lb = b.substring(0, gb).trim();
        String l = LoggingEvents.EXTRA_CALLING_APP_NAME;
        if (la.isEmpty()) {
            l = lb;
        } else if (lb.isEmpty()) {
            l = la;
        } else {
            l = "(" + la + ") AND (" + lb + ")";
        }
        if (gb < b.length()) {
            return l + " " + b.substring(gb);
        }
        if (ga < a.length()) {
            return l + " " + a.substring(ga);
        }
        return l;
    }

    public static String getLast7DigitRev(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (!(ch == ' ' || ch == '-')) {
                builder.append(ch);
                if (builder.length() == 7) {
                    break;
                }
            }
        }
        return builder.toString();
    }

    public static void makeMmsPreview(Context context, SQLiteDatabase db, boolean remake, long mmsId) {
        if (remake) {
            ContentValues v = new ContentValues();
            v.put("preview_type", Integer.valueOf(0));
            v.put(Conversations.SNIPPET, (String) null);
            v.put("preview_data", (byte[]) null);
            db.update("pdu", v, "_id=" + mmsId, null);
        }
    }

    public static void makeMmsPreview(Context context, SQLiteDatabase db, boolean remake, Collection<Long> mmsIds) {
        if (!mmsIds.isEmpty() && remake) {
            ContentValues v = new ContentValues();
            v.put("preview_type", Integer.valueOf(0));
            v.put(Conversations.SNIPPET, (String) null);
            v.put("preview_data", (byte[]) null);
            db.beginTransaction();
            try {
                for (Long longValue : mmsIds) {
                    db.update("pdu", v, "_id=" + longValue.longValue(), null);
                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }

    private static String queryXiaomiAccountName(Context context) {
        Account[] accounts = AccountManager.get(context).getAccountsByType("com.xiaomi");
        if (accounts.length == 0) {
            return null;
        }
        return accounts[0].name;
    }

    public static String getXiaomiAccountName(Context context) {
        if (!sXiaomiAccountQueried) {
            sXiaomiAccountQueried = true;
            sXiaomiAccountName = queryXiaomiAccountName(context);
        }
        return sXiaomiAccountName;
    }

    public static boolean hasXiaomiAccount(Context context) {
        return getXiaomiAccountName(context) != null;
    }

    public static void setXiaomiAccountName(String name) {
        sXiaomiAccountQueried = true;
        sXiaomiAccountName = name;
    }

    public static synchronized void setPreviousOpTime() {
        synchronized (MmsSmsUtils.class) {
            sPreviousOpTime = System.currentTimeMillis();
        }
    }

    public static synchronized long getPrevousOpTime() {
        long j;
        synchronized (MmsSmsUtils.class) {
            j = sPreviousOpTime;
        }
        return j;
    }

    public static String getSelectionByPrivatePermission(Uri url, Context context, String selection) {
        if (checkPrivateMsgWhiteList(context)) {
            return getSelectionByPrivatePermission(url, selection);
        }
        return concatenateWhere(selection, String.format(MmsSmsProvider.PRIVATE_MSG_CONDITION, new Object[]{"NULL"}));
    }

    public static String getSelectionByPrivatePermission(Uri url, String selection) {
        String privacyFlag = url.getQueryParameter("privacy_flag");
        if (TextUtils.isEmpty(privacyFlag)) {
            return selection;
        }
        if (privacyFlag.equals("1")) {
            return concatenateWhere(selection, String.format(MmsSmsProvider.PRIVATE_MSG_CONDITION, new Object[]{"not NULL"}));
        } else if (!privacyFlag.equals("0")) {
            return selection;
        } else {
            return concatenateWhere(selection, String.format(MmsSmsProvider.PRIVATE_MSG_CONDITION, new Object[]{"NULL"}));
        }
    }

    public static boolean checkPrivateMsgWhiteList(Context context) {
        return SecurityManager.checkCallingPackage(context, PRIVATE_MSG_WHITELIST);
    }

    public static String getSelectionByBlockedPermission(Uri uri, Context context) {
        if (checkBlockedMsgWhiteList(context)) {
            return getSelectionByBlockedPermission(uri);
        }
        return null;
    }

    public static String getSelectionByBlockedPermission(Uri uri) {
        String blockedFlag = uri.getQueryParameter("blocked_flag");
        if (!TextUtils.isEmpty(blockedFlag)) {
            if (blockedFlag.equals("1")) {
                return MmsSmsProvider.BLOCKED_MSG_CONDITION;
            }
            if (blockedFlag.equals("2")) {
                return "(deleted = 1 AND block_type > 1) OR deleted=0";
            }
        }
        return null;
    }

    public static boolean checkBlockedMsgWhiteList(Context context) {
        return SecurityManager.checkCallingPackage(context, BLOCKED_MSG_WHITELIST);
    }

    public static boolean hasBlockedFlag(Uri uri) {
        String blockedFlag = uri.getQueryParameter("blocked_flag");
        if (TextUtils.isEmpty(blockedFlag) || !blockedFlag.equals("1")) {
            return false;
        }
        return true;
    }

    public static boolean inBlockedWhiteList(Context context, Uri uri) {
        if ("true".equals(uri.getQueryParameter(IN_BLOCKED_WHITELIST)) && SecurityManager.checkCallingPackage(context, PROVIDER_CALL_WHITELIST)) {
            return true;
        }
        return false;
    }

    public static boolean inPrivateWhiteList(Context context, Uri uri) {
        if ("true".equals(uri.getQueryParameter(IN_PRIVATE_WHITELIST)) && SecurityManager.checkCallingPackage(context, PROVIDER_CALL_WHITELIST)) {
            return true;
        }
        return false;
    }

    public static void clearOldMsgState(Context context) {
        System.putInt(context.getContentResolver(), "mms_upload_old_msg_state", 0);
        System.putString(context.getContentResolver(), "mms_upload_old_msg_accounts", null);
    }

    public static void clearWildMsgState(Context context) {
        System.putInt(context.getContentResolver(), "mms_sync_wild_msg_state", 0);
        System.putString(context.getContentResolver(), "mms_sync_wild_numbers", null);
    }

    public static void clearCommonMarkers(Context context) {
        System.putString(context.getContentResolver(), "mms_thread_marker", "0");
        System.putString(context.getContentResolver(), "mms_private_address_marker", "0");
    }

    public static String removeSpaceForAddress(String address) {
        if (!Mms.isPhoneNumber(address)) {
            return address;
        }
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c > ' ') {
                b.append(c);
            }
        }
        return b.toString();
    }

    public static void removeSpaceForAddressValue(ContentValues v, String key) {
        if (v.containsKey(key)) {
            v.put(key, removeSpaceForAddress(v.getAsString(key)));
        }
    }

    public static long getAddressIdFromThreadId(SQLiteDatabase db, long threadId) {
        long j = -1;
        if (threadId != -1) {
            j = -1;
            Cursor c = db.rawQuery(" SELECT recipient_ids FROM threads WHERE _id=" + threadId, null);
            if (c != null) {
                try {
                    if (c.moveToFirst()) {
                        j = c.getLong(0);
                    }
                    c.close();
                } catch (Throwable th) {
                    c.close();
                }
            }
        }
        return j;
    }

    public static int getServiceCategory(Context context, String number, String snippet) {
        if (!MiPubUtils.isServiceNumber(context, number)) {
            return 0;
        }
        String name = null;
        if (XMAccountUtils.isXiaomiAccount(number)) {
            YellowPageMipub mipub = MiPubUtils.getMipub(context, number);
            if (mipub != null) {
                name = mipub.getYpName();
            }
        } else {
            YellowPagePhone phone = YellowPageUtils.getPhoneInfo(context, number, false);
            if (phone != null && phone.isYellowPage()) {
                name = phone.getYellowPageName();
            }
        }
        if (!TextUtils.isEmpty(name) && ExtraTelephony.BANK_CATEGORY_PATTERN.matcher(name).find()) {
            return 2;
        }
        if (number.startsWith("106") && !TextUtils.isEmpty(snippet) && ExtraTelephony.BANK_CATEGORY_SNIPPET_PATTERN.matcher(snippet).find()) {
            return 2;
        }
        return 1;
    }

    private static int compareSpecialIntlCode(String str) {
        for (String code : SPECIAL_INTERNATIONAL_CODE) {
            if (str.startsWith(code)) {
                return code.length() - 1;
            }
        }
        return -1;
    }

    public static String deleteSpecialIntlCode(String str) {
        String result = str;
        if (!Build.IS_INTERNATIONAL_BUILD) {
            return result;
        }
        if (!TextUtils.isEmpty(str)) {
            char ch = str.charAt(0);
            String s;
            int offset;
            if (ch == '+') {
                if (str.length() > 1) {
                    s = str.substring(1);
                    offset = compareSpecialIntlCode(s);
                    if (offset >= 0) {
                        result = s.substring(offset);
                    }
                }
            } else if (ch == '0' && str.length() > 2 && str.charAt(1) == '0') {
                s = str.substring(2);
                offset = compareSpecialIntlCode(s);
                if (offset >= 0) {
                    result = s.substring(offset);
                }
            }
        }
        return result;
    }
}
