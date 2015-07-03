package com.android.providers.telephony;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import miui.provider.yellowpage.YellowPageUtils;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;
import miui.provider.yellowpage.YellowPageUtils.PhoneLookup;
import miui.telephony.PhoneNumberUtils;

public class FirewallDatabaseHelper extends SQLiteOpenHelper {
    private static final int BLOCKED_DATA_COLUMN = 3;
    private static final int BLOCKED_DATE_COLUMN = 1;
    private static final int BLOCKED_NUMBER_COLUMN = 0;
    private static final String[] BLOCKED_PROJECTION;
    private static final int BLOCKED_REASON_COLUMN = 2;
    private static final int BLOCKED_TYPE_COLUMN = 4;
    private static final String DATABASE_NAME = "firewall.db";
    private static final int DATABASE_VERSION = 12;
    private static final int MARKEDNUMBER_CAT_TITLE_COLUMN = 1;
    private static final int MARKEDNUMBER_NUMBER_COLUMN = 0;
    private static final String[] MARKEDNUMBER_PROJECTION;
    private static final int MARKEDNUMBER_TITLE_TYPE_COLUMN = 2;
    private static final int REASON_FILTER_SMS = 65536;
    private static final int REASON_IMPORT_SMS = 131072;
    private static final String SQL_CREATE_ACCOUNT_TABLE = "CREATE TABLE account(_id INTEGER PRIMARY KEY AUTOINCREMENT,account_name TEXT NOT NULL,account_type TEXT NOT NULL,data TEXT)";
    private static final String TAG = "FirewallDatabaseHelper";
    private final Context mContext;

    public interface TABLE {
        public static final String ACCOUNT = "account";
        public static final String BLACKLIST = "blacklist";
        public static final String FWLOG = "fwlog";
        public static final String KEYWORD = "keyword";
        public static final String MODE = "mode";
        public static final String WHITELIST = "whitelist";
    }

    public FirewallDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        createOldTables(db);
        createAccountTable(db);
        createModeTable(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade triggered");
        int version = oldVersion;
        if (version == MARKEDNUMBER_CAT_TITLE_COLUMN) {
            db.execSQL("ALTER TABLE fwlog ADD COLUMN read INTEGER NOT NULL DEFAULT 0");
            version = MARKEDNUMBER_TITLE_TYPE_COLUMN;
        }
        if (version == MARKEDNUMBER_TITLE_TYPE_COLUMN) {
            db.execSQL("ALTER TABLE blacklist ADD COLUMN notes TEXT");
            db.execSQL("ALTER TABLE whitelist ADD COLUMN notes TEXT");
            db.execSQL("CREATE TABLE keyword(_id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT NOT NULL DEFAULT NULL);");
            version = BLOCKED_DATA_COLUMN;
        }
        if (version == BLOCKED_DATA_COLUMN) {
            upgradeToVersion4(db);
            version = BLOCKED_TYPE_COLUMN;
        }
        if (version == BLOCKED_TYPE_COLUMN) {
            upgradeToVersion5(db);
            version = 5;
        }
        if (version == 5) {
            upgradeToVersion6(db);
            version = 6;
        }
        if (version == 6) {
            upgradeToVersion7(db);
            version = 7;
        }
        if (version == 7) {
            upgradeToVersion8(db);
            version = 8;
        }
        if (version == 8) {
            upgradeToVersion9(db);
            version = 9;
        }
        if (version == 9) {
            upgradeToVersion10(db);
            version = 10;
        }
        if (version == 10) {
            upgradeToVersion11(db);
        }
        Log.w(TAG, "Upgrading database from version " + oldVersion + "to " + newVersion);
    }

    private void createAccountTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACCOUNT_TABLE);
        Log.d(TAG, "account table has been created");
    }

    private void createModeTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mode(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL DEFAULT NULL, state TEXT NOT NULL DEFAULT NULL);");
        Log.d(TAG, "mode table has been created");
    }

    private void createOldTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE blacklist(_id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT DEFAULT NULL, display_number TEXT DEFAULT NULL, notes TEXT DEFAULT NULL, state INTEGER NOT NULL DEFAULT 0);");
        db.execSQL("CREATE TABLE fwlog(_id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT DEFAULT NULL, date INTEGER NOT NULL DEFAULT 0, type INTEGER NOT NULL DEFAULT 0, reason INTEGER NOT NULL DEFAULT 0, data1 TEXT DEFAULT NULL, data2 TEXT DEFAULT NULL, read INTEGER NOT NULL DEFAULT 0, mode TEXT );");
        db.execSQL("CREATE TABLE whitelist(_id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT DEFAULT NULL, display_number TEXT DEFAULT NULL, notes TEXT DEFAULT NULL, state INTEGER NOT NULL DEFAULT 0, isdisplay INTEGER NOT NULL DEFAULT 0, vip INTEGER DEFAULT 0);");
        db.execSQL("CREATE TABLE keyword(_id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT NOT NULL DEFAULT NULL);");
    }

    private void upgradeToVersion4(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE blacklist RENAME TO tmp_blacklist");
        db.execSQL("ALTER TABLE whitelist RENAME TO tmp_whitelist");
        db.execSQL("CREATE TABLE blacklist(_id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT DEFAULT NULL, display_number TEXT DEFAULT NULL, notes TEXT DEFAULT NULL);");
        db.execSQL("CREATE TABLE whitelist(_id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT DEFAULT NULL, display_number TEXT DEFAULT NULL, notes TEXT DEFAULT NULL);");
        Cursor c = db.query("tmp_blacklist", null, null, null, null, null, null);
        if (c != null) {
            String number;
            while (c.moveToNext()) {
                try {
                    number = c.getString(MARKEDNUMBER_NUMBER_COLUMN);
                    db.execSQL("INSERT INTO blacklist (number, display_number, notes) VALUES ('" + number + "'" + ", '" + number + "'" + ", '" + c.getString(MARKEDNUMBER_CAT_TITLE_COLUMN) + "'" + ")");
                } finally {
                    c.close();
                }
            }
            c = db.query("tmp_whitelist", null, null, null, null, null, null);
            if (c != null) {
                while (c.moveToNext()) {
                    try {
                        number = c.getString(MARKEDNUMBER_NUMBER_COLUMN);
                        db.execSQL("INSERT INTO whitelist (number, display_number, notes) VALUES ('" + number + "'" + ", '" + number + "'" + ", '" + c.getString(MARKEDNUMBER_CAT_TITLE_COLUMN) + "'" + ")");
                    } finally {
                        c.close();
                    }
                }
                db.execSQL("DROP TABLE IF EXISTS tmp_blacklist");
                db.execSQL("DROP TABLE IF EXISTS tmp_whitelist");
            }
        }
    }

    private void upgradeToVersion5(SQLiteDatabase db) {
        createAccountTable(db);
    }

    private void upgradeToVersion6(SQLiteDatabase db) {
        long id;
        String number;
        Cursor c = db.query(TABLE.BLACKLIST, null, null, null, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                id = c.getLong(MARKEDNUMBER_NUMBER_COLUMN);
                number = c.getString(MARKEDNUMBER_CAT_TITLE_COLUMN);
                if (TextUtils.isEmpty(number)) {
                    db.execSQL("DELETE FROM blacklist WHERE _id=" + id);
                } else {
                    try {
                        String normalizeNumber = convertNumberToNormalizedNumber(number);
                        db.execSQL("UPDATE blacklist SET number='" + normalizeNumber + "'" + ", display_number='" + normalizeNumber + "'" + " WHERE _id=" + id);
                    } finally {
                        c.close();
                    }
                }
            }
        }
        c = db.query(TABLE.WHITELIST, null, null, null, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                try {
                    id = c.getLong(MARKEDNUMBER_NUMBER_COLUMN);
                    number = c.getString(MARKEDNUMBER_CAT_TITLE_COLUMN);
                    if (TextUtils.isEmpty(number)) {
                        db.execSQL("DELETE FROM whitelist WHERE _id=" + id);
                    } else {
                        normalizeNumber = convertNumberToNormalizedNumber(number);
                        db.execSQL("UPDATE whitelist SET number='" + normalizeNumber + "'" + ", display_number='" + normalizeNumber + "'" + " WHERE _id=" + id);
                    }
                } finally {
                    c.close();
                }
            }
        }
        c = db.query(TABLE.FWLOG, null, null, null, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                try {
                    id = c.getLong(MARKEDNUMBER_NUMBER_COLUMN);
                    number = c.getString(MARKEDNUMBER_CAT_TITLE_COLUMN);
                    if (TextUtils.isEmpty(number)) {
                        db.execSQL("DELETE FROM fwlog WHERE _id=" + id);
                    } else {
                        db.execSQL("UPDATE fwlog SET number='" + convertNumberToNormalizedNumber(number) + "'" + " WHERE _id=" + id);
                    }
                } finally {
                    c.close();
                }
            }
        }
    }

    private String convertNumberToNormalizedNumber(String number) {
        number = number.trim();
        if (number.equals("-3") || number.equals("-2") || number.equals("-1")) {
            return number;
        }
        if (number.contains("(") && number.contains(")")) {
            number = number.substring(number.indexOf(")") + MARKEDNUMBER_CAT_TITLE_COLUMN).trim();
            int pos = number.indexOf("-");
            if (pos != -1) {
                number = "0" + number.substring(MARKEDNUMBER_NUMBER_COLUMN, pos) + number.substring(pos + MARKEDNUMBER_CAT_TITLE_COLUMN);
            }
        }
        return PhoneNumberUtils.normalizeNumber(number);
    }

    static {
        String[] strArr = new String[BLOCKED_DATA_COLUMN];
        strArr[MARKEDNUMBER_NUMBER_COLUMN] = PhoneLookup.NUMBER;
        strArr[MARKEDNUMBER_CAT_TITLE_COLUMN] = "cat_title";
        strArr[MARKEDNUMBER_TITLE_TYPE_COLUMN] = AntispamNumber.TYPE;
        MARKEDNUMBER_PROJECTION = strArr;
        BLOCKED_PROJECTION = new String[]{PhoneLookup.NUMBER, WapPush.DATE, "reason", "data1", AntispamNumber.TYPE};
    }

    private void upgradeToVersion7(SQLiteDatabase db) {
        Cursor cursor = db.query("sqlite_master", null, "type = 'table' AND name = 'markednumber'", null, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.getCount() > 0) {
                    cursor.close();
                } else {
                    return;
                }
            } finally {
                cursor.close();
            }
        }
        cursor = db.query("markednumber join category on category._id = markednumber.cat_id", MARKEDNUMBER_PROJECTION, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    String number = cursor.getString(MARKEDNUMBER_NUMBER_COLUMN);
                    if (cursor.getInt(MARKEDNUMBER_TITLE_TYPE_COLUMN) == 0) {
                        YellowPageUtils.markAntiSpam(this.mContext, number, YellowPageUtils.createAntispamCategory(this.mContext, cursor.getString(MARKEDNUMBER_CAT_TITLE_COLUMN)), false);
                    }
                } finally {
                    cursor.close();
                }
            }
        }
    }

    private void upgradeToVersion8(final SQLiteDatabase db) {
        new Thread(new Runnable() {
            public void run() {
                FirewallDatabaseHelper.this.moveSms(db);
            }
        }).start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void moveSms(android.database.sqlite.SQLiteDatabase r18) {
        /*
        r17 = this;
        r13 = 0;
        r2 = "fwlog";
        r3 = BLOCKED_PROJECTION;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r18;
        r11 = r1.query(r2, r3, r4, r5, r6, r7, r8);
        if (r11 == 0) goto L_0x00c5;
    L_0x0012:
        r9 = new miui.provider.BatchOperation;	 Catch:{ Exception -> 0x00a9 }
        r0 = r17;
        r1 = r0.mContext;	 Catch:{ Exception -> 0x00a9 }
        r1 = r1.getContentResolver();	 Catch:{ Exception -> 0x00a9 }
        r2 = "sms";
        r9.<init>(r1, r2);	 Catch:{ Exception -> 0x00a9 }
    L_0x0021:
        r1 = r11.moveToNext();	 Catch:{ Exception -> 0x00a9 }
        if (r1 == 0) goto L_0x00ec;
    L_0x0027:
        r1 = 0;
        r14 = r11.getString(r1);	 Catch:{ Exception -> 0x00a9 }
        r1 = 4;
        r16 = r11.getInt(r1);	 Catch:{ Exception -> 0x00a9 }
        r1 = 2;
        r0 = r16;
        if (r0 != r1) goto L_0x0021;
    L_0x0036:
        r1 = android.provider.Telephony.Sms.Inbox.CONTENT_URI;	 Catch:{ Exception -> 0x00a9 }
        r10 = android.content.ContentProviderOperation.newInsert(r1);	 Catch:{ Exception -> 0x00a9 }
        r1 = "address";
        r10.withValue(r1, r14);	 Catch:{ Exception -> 0x00a9 }
        r1 = "date";
        r2 = 1;
        r2 = r11.getString(r2);	 Catch:{ Exception -> 0x00a9 }
        r10.withValue(r1, r2);	 Catch:{ Exception -> 0x00a9 }
        r1 = "body";
        r2 = 3;
        r2 = r11.getString(r2);	 Catch:{ Exception -> 0x00a9 }
        r10.withValue(r1, r2);	 Catch:{ Exception -> 0x00a9 }
        r1 = "protocol";
        r2 = 0;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00a9 }
        r10.withValue(r1, r2);	 Catch:{ Exception -> 0x00a9 }
        r1 = "status";
        r2 = -1;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00a9 }
        r10.withValue(r1, r2);	 Catch:{ Exception -> 0x00a9 }
        r1 = "read";
        r2 = 1;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00a9 }
        r10.withValue(r1, r2);	 Catch:{ Exception -> 0x00a9 }
        r1 = 2;
        r15 = r11.getInt(r1);	 Catch:{ Exception -> 0x00a9 }
        r1 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r15 != r1) goto L_0x009a;
    L_0x007c:
        r1 = "block_type";
        r2 = 4;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00a9 }
        r10.withValue(r1, r2);	 Catch:{ Exception -> 0x00a9 }
    L_0x0086:
        r1 = r10.build();	 Catch:{ Exception -> 0x00a9 }
        r9.add(r1);	 Catch:{ Exception -> 0x00a9 }
        r1 = r9.size();	 Catch:{ Exception -> 0x00a9 }
        r2 = 100;
        if (r1 <= r2) goto L_0x0021;
    L_0x0095:
        r9.execute();	 Catch:{ Exception -> 0x00a9 }
        r13 = 1;
        goto L_0x0021;
    L_0x009a:
        r1 = 131072; // 0x20000 float:1.83671E-40 double:6.47582E-319;
        if (r15 != r1) goto L_0x00dc;
    L_0x009e:
        r1 = "block_type";
        r2 = 5;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00a9 }
        r10.withValue(r1, r2);	 Catch:{ Exception -> 0x00a9 }
        goto L_0x0086;
    L_0x00a9:
        r12 = move-exception;
        r1 = "FirewallDatabaseHelper";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e7 }
        r2.<init>();	 Catch:{ all -> 0x00e7 }
        r3 = "Exception when upgradeToVersion8: ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00e7 }
        r2 = r2.append(r12);	 Catch:{ all -> 0x00e7 }
        r2 = r2.toString();	 Catch:{ all -> 0x00e7 }
        android.util.Log.e(r1, r2);	 Catch:{ all -> 0x00e7 }
        r11.close();
    L_0x00c5:
        if (r13 == 0) goto L_0x00db;
    L_0x00c7:
        r1 = "fwlog";
        r2 = "type<>?";
        r3 = 1;
        r3 = new java.lang.String[r3];
        r4 = 0;
        r5 = 1;
        r5 = java.lang.String.valueOf(r5);
        r3[r4] = r5;
        r0 = r18;
        r0.delete(r1, r2, r3);
    L_0x00db:
        return;
    L_0x00dc:
        r1 = "block_type";
        r2 = 3;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00a9 }
        r10.withValue(r1, r2);	 Catch:{ Exception -> 0x00a9 }
        goto L_0x0086;
    L_0x00e7:
        r1 = move-exception;
        r11.close();
        throw r1;
    L_0x00ec:
        r1 = r9.size();	 Catch:{ Exception -> 0x00a9 }
        if (r1 <= 0) goto L_0x00f6;
    L_0x00f2:
        r13 = 1;
        r9.execute();	 Catch:{ Exception -> 0x00a9 }
    L_0x00f6:
        r11.close();
        goto L_0x00c5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.FirewallDatabaseHelper.moveSms(android.database.sqlite.SQLiteDatabase):void");
    }

    private void upgradeToVersion9(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE blacklist ADD COLUMN state INTEGER NOT NULL DEFAULT 0");
        db.execSQL("ALTER TABLE whitelist ADD COLUMN state INTEGER NOT NULL DEFAULT 0");
        db.execSQL("ALTER TABLE whitelist ADD COLUMN isdisplay INTEGER NOT NULL DEFAULT 0");
        createModeTable(db);
    }

    private void upgradeToVersion10(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE fwlog ADD COLUMN mode");
    }

    private void upgradeToVersion11(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE whitelist ADD COLUMN vip INTEGER DEFAULT 0");
        exportVip(db);
    }

    private void exportVip(final SQLiteDatabase db) {
        new Thread() {
            public void run() {
                String insertVip = "insert into whitelist (number,display_number,notes,state,isdisplay,vip) values(?,?,?,?,?,?)";
                Cursor cursor = db.rawQuery("select * from whitelist where number not like '%*%'", null);
                while (cursor.moveToNext()) {
                    db.execSQL(insertVip, new String[]{cursor.getString(cursor.getColumnIndex(PhoneLookup.NUMBER)), cursor.getString(cursor.getColumnIndex("display_number")), cursor.getString(cursor.getColumnIndex("notes")), cursor.getString(cursor.getColumnIndex("state")), cursor.getString(cursor.getColumnIndex("isdisplay")), "1"});
                }
                cursor.close();
            }
        }.start();
    }
}
