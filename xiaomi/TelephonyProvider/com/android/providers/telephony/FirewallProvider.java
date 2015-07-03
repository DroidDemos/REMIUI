package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.provider.MiuiSettings.Secure;
import android.text.TextUtils;
import android.util.Log;
import com.android.providers.telephony.FirewallDatabaseHelper.TABLE;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import java.util.ArrayList;
import miui.provider.ExtraTelephony.AntiSpamMode;
import miui.provider.ExtraTelephony.Blacklist;
import miui.provider.ExtraTelephony.FirewallLog;
import miui.provider.ExtraTelephony.Keyword;
import miui.provider.ExtraTelephony.Whitelist;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;
import miui.provider.yellowpage.YellowPageUtils.PhoneLookup;
import miui.provider.yellowpage.YellowPageUtils.Provider;
import miui.telephony.PhoneNumberUtils;

public class FirewallProvider<ContentProviderOperation> extends ContentProvider {
    private static final String AUTHORITY = "firewall";
    private static final String BLACKLIST_TABLE = "blacklist";
    private static final String KEYWORD_TABLE = "keyword";
    private static final String LOG_TABLE = "fwlog";
    private static final String MODE_TABLE = "mode";
    private static final String TAG = "FirewallProvider";
    private static final int URI_ACCOUNT = 10;
    private static final int URI_ACCOUNT_ITEM = 11;
    private static final int URI_CATEGORY = 16;
    private static final int URI_CATEGORY_ITEM = 17;
    private static final int URI_CLOUDANTISPAM = 14;
    private static final int URI_CLOUDANTISPAM_ITEM = 15;
    private static final int URI_MARKEDNUMBER = 12;
    private static final int URI_MARKEDNUMBER_ITEM = 13;
    private static final int URL_BLACKLIST = 1;
    private static final int URL_BLACKLIST_ID = 2;
    private static final int URL_KEYWORD = 8;
    private static final int URL_KEYWORD_ID = 9;
    private static final int URL_LOG = 3;
    private static final int URL_LOG_CONVERSATION = 7;
    private static final int URL_LOG_ID = 4;
    private static final int URL_MODE = 18;
    private static final int URL_MODE_ID = 19;
    private static final int URL_WHITELIST = 5;
    private static final int URL_WHITELIST_ID = 6;
    private static final String WHITELIST_TABLE = "whitelist";
    private static final UriMatcher sURIMatcher;
    private SQLiteStatement mInsertBL;
    private SQLiteStatement mInsertKW;
    private SQLiteStatement mInsertLog;
    private SQLiteStatement mInsertMode;
    private SQLiteStatement mInsertWL;
    private SQLiteOpenHelper mOpenHelper;

    public FirewallProvider() {
        this.mInsertBL = null;
        this.mInsertLog = null;
        this.mInsertWL = null;
        this.mInsertMode = null;
        this.mInsertKW = null;
    }

    static {
        sURIMatcher = new UriMatcher(-1);
        sURIMatcher.addURI(AUTHORITY, BLACKLIST_TABLE, URL_BLACKLIST);
        sURIMatcher.addURI(AUTHORITY, "blacklist/*", URL_BLACKLIST_ID);
        sURIMatcher.addURI(AUTHORITY, "log", URL_LOG);
        sURIMatcher.addURI(AUTHORITY, "log/*", URL_LOG_ID);
        sURIMatcher.addURI(AUTHORITY, WHITELIST_TABLE, URL_WHITELIST);
        sURIMatcher.addURI(AUTHORITY, "whitelist/*", URL_WHITELIST_ID);
        sURIMatcher.addURI(AUTHORITY, "logconversation", URL_LOG_CONVERSATION);
        sURIMatcher.addURI(AUTHORITY, KEYWORD_TABLE, URL_KEYWORD);
        sURIMatcher.addURI(AUTHORITY, "keyword/*", URL_KEYWORD_ID);
        sURIMatcher.addURI(AUTHORITY, MODE_TABLE, URL_MODE);
        sURIMatcher.addURI(AUTHORITY, "mode/*", URL_MODE_ID);
        sURIMatcher.addURI(AUTHORITY, TABLE.ACCOUNT, URI_ACCOUNT);
        sURIMatcher.addURI(AUTHORITY, "account/*", URI_ACCOUNT_ITEM);
        sURIMatcher.addURI(AUTHORITY, "markednumber", URI_MARKEDNUMBER);
        sURIMatcher.addURI(AUTHORITY, "markednumber/*", URI_MARKEDNUMBER_ITEM);
        sURIMatcher.addURI(AUTHORITY, "cloudantispam", URI_CLOUDANTISPAM);
        sURIMatcher.addURI(AUTHORITY, "cloudantispam/*", URI_CLOUDANTISPAM_ITEM);
        sURIMatcher.addURI(AUTHORITY, "category", URI_CATEGORY);
        sURIMatcher.addURI(AUTHORITY, "category/*", URI_CATEGORY_ITEM);
    }

    public boolean onCreate() {
        this.mOpenHelper = new FirewallDatabaseHelper(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] projectionIn, String selection, String[] selectionArgs, String sort) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        int match = sURIMatcher.match(uri);
        String groupBy = null;
        if (match == URL_LOG || match == URL_LOG_ID || match == URL_LOG_CONVERSATION) {
            Cursor emptyCursor = Secure.checkPrivacyAndReturnCursor(getContext());
            if (emptyCursor != null) {
                return emptyCursor;
            }
        }
        switch (match) {
            case URL_BLACKLIST /*1*/:
                qb.setTables(BLACKLIST_TABLE);
                break;
            case URL_BLACKLIST_ID /*2*/:
                qb.setTables(BLACKLIST_TABLE);
                qb.appendWhere(bindWhereOfPhoneNumberQuery(uri.getLastPathSegment()));
                break;
            case URL_LOG /*3*/:
                qb.setTables(LOG_TABLE);
                break;
            case URL_LOG_ID /*4*/:
                qb.setTables(LOG_TABLE);
                qb.appendWhere(bindWhereOfPhoneNumberQuery(uri.getLastPathSegment()));
                break;
            case URL_WHITELIST /*5*/:
                qb.setTables(WHITELIST_TABLE);
                break;
            case URL_WHITELIST_ID /*6*/:
                qb.setTables(WHITELIST_TABLE);
                qb.appendWhere(bindWhereOfPhoneNumberQuery(uri.getLastPathSegment()));
                break;
            case URL_LOG_CONVERSATION /*7*/:
                qb.setTables("(select * from fwlog order by date ASC)");
                groupBy = PhoneLookup.NUMBER;
                break;
            case URL_KEYWORD /*8*/:
                qb.setTables(KEYWORD_TABLE);
                break;
            case URL_KEYWORD_ID /*9*/:
                qb.setTables(KEYWORD_TABLE);
                qb.appendWhere("_id='" + uri.getLastPathSegment() + "'");
                break;
            case URI_ACCOUNT /*10*/:
                qb.setTables(TABLE.ACCOUNT);
                break;
            case URI_ACCOUNT_ITEM /*11*/:
                qb.appendWhere("_id='" + uri.getLastPathSegment() + "'");
                qb.setTables(TABLE.ACCOUNT);
                break;
            case URL_MODE /*18*/:
                qb.setTables(MODE_TABLE);
                break;
            case URL_MODE_ID /*19*/:
                qb.setTables(MODE_TABLE);
                qb.appendWhere("_id='" + uri.getLastPathSegment() + "'");
                break;
            default:
                Log.e(TAG, "Unknown query URI: " + uri);
                return null;
        }
        Cursor ret = qb.query(this.mOpenHelper.getReadableDatabase(), projectionIn, selection, selectionArgs, groupBy, null, sort);
        ret.setNotificationUri(getContext().getContentResolver(), uri);
        return ret;
    }

    public String getType(Uri url) {
        switch (sURIMatcher.match(url)) {
            case URL_BLACKLIST /*1*/:
                return "vnd.android.cursor.dir/firewall-blacklist";
            case URL_BLACKLIST_ID /*2*/:
                return "vnd.android.cursor.item/firewall-blacklist";
            case URL_LOG /*3*/:
                return "vnd.android.cursor.dir/antispam-log";
            case URL_LOG_ID /*4*/:
                return "vnd.android.cursor.item/antispam-log";
            case URL_WHITELIST /*5*/:
                return "vnd.android.cursor.dir/firewall-whitelist";
            case URL_WHITELIST_ID /*6*/:
                return "vnd.android.cursor.item/firewall-whitelist";
            case URL_KEYWORD /*8*/:
                return "vnd.android.cursor.dir/antispam-keyword";
            case URL_KEYWORD_ID /*9*/:
                return "vnd.android.cursor.item/antispam-keyword";
            case URI_ACCOUNT /*10*/:
            case URI_ACCOUNT_ITEM /*11*/:
            case URI_MARKEDNUMBER /*12*/:
            case URI_MARKEDNUMBER_ITEM /*13*/:
            case URI_CLOUDANTISPAM /*14*/:
            case URI_CLOUDANTISPAM_ITEM /*15*/:
            case URI_CATEGORY /*16*/:
            case URI_CATEGORY_ITEM /*17*/:
                return "*/*";
            case URL_MODE /*18*/:
                return "vnd.android.cursor.dir/antispam-mode";
            case URL_MODE_ID /*19*/:
                return "vnd.android.cursor.item/antispam-mode";
            default:
                throw new IllegalArgumentException("Unknown URL " + url);
        }
    }

    public Uri insert(Uri uri, ContentValues initialValues) {
        Uri result = null;
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        long insertId = 0;
        long rowID;
        switch (sURIMatcher.match(uri)) {
            case URL_BLACKLIST /*1*/:
                if (initialValues.containsKey(PhoneLookup.NUMBER)) {
                    if (this.mInsertBL == null) {
                        this.mInsertBL = db.compileStatement("INSERT OR IGNORE INTO blacklist(number,display_number,notes,state) VALUES (?,?,?,?)");
                    }
                    bindString(this.mInsertBL, URL_BLACKLIST, PhoneNumberUtils.normalizeNumber(initialValues.getAsString(PhoneLookup.NUMBER)));
                    bindString(this.mInsertBL, URL_BLACKLIST_ID, initialValues.getAsString(PhoneLookup.NUMBER));
                    bindString(this.mInsertBL, URL_LOG, initialValues.getAsString("notes"));
                    bindString(this.mInsertBL, URL_LOG_ID, initialValues.getAsString("state"));
                    rowID = this.mInsertBL.executeInsert();
                    if (rowID > 0) {
                        result = ContentUris.withAppendedId(Blacklist.CONTENT_URI, rowID);
                        getContext().getContentResolver().notifyChange(Blacklist.CONTENT_URI, null);
                        break;
                    }
                }
                break;
            case URL_LOG /*3*/:
                if (initialValues.containsKey(PhoneLookup.NUMBER)) {
                    if (this.mInsertLog == null) {
                        this.mInsertLog = db.compileStatement("INSERT OR IGNORE INTO fwlog(number,date,type,reason,data1,data2,read,mode) VALUES (?,?,?,?,?,?,?,?)");
                    }
                    bindString(this.mInsertLog, URL_BLACKLIST, PhoneNumberUtils.normalizeNumber(initialValues.getAsString(PhoneLookup.NUMBER)));
                    bindLong(this.mInsertLog, URL_BLACKLIST_ID, initialValues.getAsLong(WapPush.DATE), Long.valueOf(0));
                    bindLong(this.mInsertLog, URL_LOG, initialValues.getAsLong(AntispamNumber.TYPE), Long.valueOf(0));
                    bindLong(this.mInsertLog, URL_LOG_ID, initialValues.getAsLong("reason"), Long.valueOf(0));
                    bindString(this.mInsertLog, URL_WHITELIST, initialValues.getAsString("data1"));
                    bindString(this.mInsertLog, URL_WHITELIST_ID, initialValues.getAsString("data2"));
                    bindLong(this.mInsertLog, URL_LOG_CONVERSATION, initialValues.getAsLong(WapPush.READ), Long.valueOf(0));
                    bindString(this.mInsertLog, URL_KEYWORD, initialValues.getAsString(MODE_TABLE));
                    rowID = this.mInsertLog.executeInsert();
                    if (rowID > 0) {
                        result = ContentUris.withAppendedId(FirewallLog.CONTENT_URI, rowID);
                        getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI, null);
                        getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI_LOG_CONVERSATION, null);
                    }
                    sendNofication();
                    break;
                }
            case URL_WHITELIST /*5*/:
                if (initialValues.containsKey(PhoneLookup.NUMBER)) {
                    if (this.mInsertWL == null) {
                        this.mInsertWL = db.compileStatement("INSERT OR IGNORE INTO whitelist(number,display_number,notes,state,isdisplay,vip) VALUES (?,?,?,?,?,?)");
                    }
                    bindString(this.mInsertWL, URL_BLACKLIST, PhoneNumberUtils.normalizeNumber(initialValues.getAsString(PhoneLookup.NUMBER)));
                    bindString(this.mInsertWL, URL_BLACKLIST_ID, initialValues.getAsString(PhoneLookup.NUMBER));
                    bindString(this.mInsertWL, URL_LOG, initialValues.getAsString("notes"));
                    bindString(this.mInsertWL, URL_LOG_ID, initialValues.getAsString("state"));
                    bindString(this.mInsertWL, URL_WHITELIST, initialValues.getAsString("isdisplay"));
                    bindLong(this.mInsertWL, URL_WHITELIST_ID, initialValues.getAsLong("vip"), Long.valueOf(0));
                    rowID = this.mInsertWL.executeInsert();
                    if (rowID > 0) {
                        result = ContentUris.withAppendedId(Whitelist.CONTENT_URI, rowID);
                        getContext().getContentResolver().notifyChange(Whitelist.CONTENT_URI, null);
                        break;
                    }
                }
                break;
            case URL_KEYWORD /*8*/:
                if (initialValues.containsKey("data")) {
                    if (this.mInsertKW == null) {
                        this.mInsertKW = db.compileStatement("INSERT OR IGNORE INTO keyword(data) VALUES (?)");
                    }
                    bindString(this.mInsertKW, URL_BLACKLIST, initialValues.getAsString("data"));
                    rowID = this.mInsertKW.executeInsert();
                    if (rowID > 0) {
                        result = ContentUris.withAppendedId(Keyword.CONTENT_URI, rowID);
                        getContext().getContentResolver().notifyChange(Keyword.CONTENT_URI, null);
                        break;
                    }
                }
                break;
            case URL_MODE /*18*/:
                if (initialValues.containsKey(Provider.NAME)) {
                    if (this.mInsertMode == null) {
                        this.mInsertMode = db.compileStatement("INSERT OR IGNORE INTO mode(name,state) VALUES (?,?)");
                    }
                    bindString(this.mInsertMode, URL_BLACKLIST, initialValues.getAsString(Provider.NAME));
                    bindString(this.mInsertMode, URL_BLACKLIST_ID, initialValues.getAsString("state"));
                    rowID = this.mInsertMode.executeInsert();
                    if (rowID > 0) {
                        result = ContentUris.withAppendedId(AntiSpamMode.CONTENT_URI, rowID);
                        getContext().getContentResolver().notifyChange(AntiSpamMode.CONTENT_URI, null);
                        break;
                    }
                }
                break;
            case URI_ACCOUNT /*10*/:
                insertId = db.insert(TABLE.ACCOUNT, null, initialValues);
                break;
            default:
                throw new UnsupportedOperationException("Cannot insert that URL: " + uri);
        }
        if (insertId <= 0) {
            return result;
        }
        result = ContentUris.withAppendedId(uri, insertId);
        getContext().getContentResolver().notifyChange(result, null);
        return result;
    }

    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        int count;
        String[] strArr;
        switch (sURIMatcher.match(uri)) {
            case URL_BLACKLIST /*1*/:
                count = db.delete(BLACKLIST_TABLE, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Blacklist.CONTENT_URI, null);
                return count;
            case URL_BLACKLIST_ID /*2*/:
                strArr = new String[URL_BLACKLIST];
                strArr[0] = uri.getLastPathSegment();
                count = db.delete(BLACKLIST_TABLE, "_id=?", strArr);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Blacklist.CONTENT_URI, null);
                return count;
            case URL_LOG /*3*/:
                count = db.delete(LOG_TABLE, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI, null);
                getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI_LOG_CONVERSATION, null);
                return count;
            case URL_LOG_ID /*4*/:
                strArr = new String[URL_BLACKLIST];
                strArr[0] = uri.getLastPathSegment();
                count = db.delete(LOG_TABLE, "_id=?", strArr);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI, null);
                return count;
            case URL_WHITELIST /*5*/:
                count = db.delete(WHITELIST_TABLE, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Whitelist.CONTENT_URI, null);
                return count;
            case URL_WHITELIST_ID /*6*/:
                strArr = new String[URL_BLACKLIST];
                strArr[0] = uri.getLastPathSegment();
                count = db.delete(WHITELIST_TABLE, "_id=?", strArr);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Whitelist.CONTENT_URI, null);
                return count;
            case URL_KEYWORD /*8*/:
                count = db.delete(KEYWORD_TABLE, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Keyword.CONTENT_URI, null);
                return count;
            case URL_KEYWORD_ID /*9*/:
                strArr = new String[URL_BLACKLIST];
                strArr[0] = uri.getLastPathSegment();
                count = db.delete(KEYWORD_TABLE, "_id=?", strArr);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Keyword.CONTENT_URI, null);
                return count;
            case URI_ACCOUNT /*10*/:
                return db.delete(TABLE.ACCOUNT, where, whereArgs);
            case URI_ACCOUNT_ITEM /*11*/:
                return db.delete(TABLE.ACCOUNT, "_id=" + uri.getLastPathSegment(), whereArgs);
            case URL_MODE /*18*/:
                count = db.delete(MODE_TABLE, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(AntiSpamMode.CONTENT_URI, null);
                return count;
            case URL_MODE_ID /*19*/:
                strArr = new String[URL_BLACKLIST];
                strArr[0] = uri.getLastPathSegment();
                count = db.delete(MODE_TABLE, "_id=?", strArr);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(AntiSpamMode.CONTENT_URI, null);
                return count;
            default:
                throw new UnsupportedOperationException("Cannot delete that URL: " + uri);
        }
    }

    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        int count;
        String[] strArr;
        switch (sURIMatcher.match(uri)) {
            case URL_BLACKLIST /*1*/:
                count = db.update(BLACKLIST_TABLE, values, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Blacklist.CONTENT_URI, null);
                return count;
            case URL_BLACKLIST_ID /*2*/:
                if (where == null && whereArgs == null) {
                    strArr = new String[URL_BLACKLIST];
                    strArr[0] = uri.getLastPathSegment();
                    count = db.update(BLACKLIST_TABLE, values, "_id=?", strArr);
                    if (count <= 0) {
                        return count;
                    }
                    getContext().getContentResolver().notifyChange(Blacklist.CONTENT_URI, null);
                    return count;
                }
                throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
            case URL_LOG /*3*/:
                count = db.update(LOG_TABLE, values, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI, null);
                getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI_LOG_CONVERSATION, null);
                return count;
            case URL_LOG_ID /*4*/:
                if (where == null && whereArgs == null) {
                    strArr = new String[URL_BLACKLIST];
                    strArr[0] = uri.getLastPathSegment();
                    count = db.update(LOG_TABLE, values, "_id=?", strArr);
                    if (count <= 0) {
                        return count;
                    }
                    getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI, null);
                    getContext().getContentResolver().notifyChange(FirewallLog.CONTENT_URI_LOG_CONVERSATION, null);
                    return count;
                }
                throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
            case URL_WHITELIST /*5*/:
                count = db.update(WHITELIST_TABLE, values, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Whitelist.CONTENT_URI, null);
                return count;
            case URL_WHITELIST_ID /*6*/:
                if (where == null && whereArgs == null) {
                    strArr = new String[URL_BLACKLIST];
                    strArr[0] = uri.getLastPathSegment();
                    count = db.update(WHITELIST_TABLE, values, "_id=?", strArr);
                    if (count <= 0) {
                        return count;
                    }
                    getContext().getContentResolver().notifyChange(Whitelist.CONTENT_URI, null);
                    return count;
                }
                throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
            case URL_KEYWORD /*8*/:
                count = db.update(KEYWORD_TABLE, values, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(Keyword.CONTENT_URI, null);
                return count;
            case URL_KEYWORD_ID /*9*/:
                if (where == null && whereArgs == null) {
                    strArr = new String[URL_BLACKLIST];
                    strArr[0] = uri.getLastPathSegment();
                    count = db.update(KEYWORD_TABLE, values, "_id=?", strArr);
                    if (count <= 0) {
                        return count;
                    }
                    getContext().getContentResolver().notifyChange(Keyword.CONTENT_URI, null);
                    return count;
                }
                throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
            case URI_ACCOUNT /*10*/:
                return db.update(TABLE.ACCOUNT, values, where, whereArgs);
            case URI_ACCOUNT_ITEM /*11*/:
                return db.update(TABLE.ACCOUNT, values, "_id=" + uri.getLastPathSegment(), whereArgs);
            case URL_MODE /*18*/:
                count = db.update(MODE_TABLE, values, where, whereArgs);
                if (count <= 0) {
                    return count;
                }
                getContext().getContentResolver().notifyChange(AntiSpamMode.CONTENT_URI, null);
                return count;
            case URL_MODE_ID /*19*/:
                if (where == null && whereArgs == null) {
                    strArr = new String[URL_BLACKLIST];
                    strArr[0] = uri.getLastPathSegment();
                    count = db.update(MODE_TABLE, values, "_id=?", strArr);
                    if (count <= 0) {
                        return count;
                    }
                    getContext().getContentResolver().notifyChange(AntiSpamMode.CONTENT_URI, null);
                    return count;
                }
                throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
            default:
                throw new UnsupportedOperationException("Cannot update that URL: " + uri);
        }
    }

    private void sendNofication() {
        getContext().sendBroadcast(new Intent("android.intent.action.FIREWALL_UPDATED"));
    }

    private void bindString(SQLiteStatement stmt, int index, String value) {
        if (value == null) {
            stmt.bindNull(index);
        } else {
            stmt.bindString(index, value);
        }
    }

    private void bindLong(SQLiteStatement stmt, int index, Long value, Long defVal) {
        if (value == null) {
            stmt.bindLong(index, defVal.longValue());
        } else {
            stmt.bindLong(index, value.longValue());
        }
    }

    private String bindWhereOfPhoneNumberQuery(String number) {
        if (TextUtils.isEmpty(number)) {
            return null;
        }
        String substrStatement = "substr(number, -7, 7)";
        number = PhoneNumberUtils.normalizeNumber(number);
        int length = number.length();
        if (length < URL_LOG_CONVERSATION) {
            return substrStatement + "='" + number + "'";
        }
        return substrStatement + "='" + number.substring(length - 7) + "' AND PHONE_NUMBERS_EQUAL(number, '" + number + "', 0)";
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
}
