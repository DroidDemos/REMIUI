package com.xiaomi.activate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.google.android.collect.Lists;
import java.util.List;
import org.apache.thrift.transport.TTransportException;

public class ActivateDatabaseHelper extends SQLiteOpenHelper {
    private static final String ACTIVATE_INFO_TABLE_CREATE;
    private static final String ACTIVATE_INFO_TABLE_NAME = "activate_info";
    private static final String DATABASE_NAME = "activate_info.db";
    private static final int DATABASE_VERSION = 4;
    private static final String FIELD_INSERTED_TO_SERVER = "insertedToServer";
    private static final String FIELD_NEXT_AUTO_ACTIVATION_TIME = "nextAutoActivationTime";
    private static final String FIELD_PASS_TOKEN = "passToken";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_SIM_ID = "simId";
    private static final String FIELD_USER_ID = "userId";
    private static final String FIELD_VKEY1 = "vkey1";
    private static final String FIELD_VKEY2 = "vkey2";
    private static final String TAG = "ActivateDatabase";
    private static volatile ActivateDatabaseHelper sInstance;
    private Context mContext;

    private void mergeActivateInfoFromOldHost(android.database.sqlite.SQLiteDatabase r14) {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:33:0x00cb in {4, 14, 16, 17, 19, 22, 25, 27, 28, 30, 32, 34, 35, 36, 37, 38} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r13 = this;
        r12 = 3;
        r7 = 2;
        r6 = 0;
        r5 = 1;
        r3 = 0;
        r0 = r13.mContext;
        r0 = r0.getContentResolver();
        r1 = "content://xiaomi-old-activate-info/";
        r1 = android.net.Uri.parse(r1);
        r2 = 9;
        r2 = new java.lang.String[r2];
        r4 = "_id";
        r2[r6] = r4;
        r4 = "simId";
        r2[r5] = r4;
        r4 = "phone";
        r2[r7] = r4;
        r4 = "userId";
        r2[r12] = r4;
        r4 = 4;
        r5 = "passToken";
        r2[r4] = r5;
        r4 = 5;
        r5 = "vkey1";
        r2[r4] = r5;
        r4 = 6;
        r5 = "vkey2";
        r2[r4] = r5;
        r4 = 7;
        r5 = "nextAutoActivationTime";
        r2[r4] = r5;
        r4 = 8;
        r5 = "insertedToServer";
        r2[r4] = r5;
        r4 = r3;
        r5 = r3;
        r8 = r0.query(r1, r2, r3, r4, r5);
        if (r8 != 0) goto L_0x004f;
    L_0x0047:
        r0 = "ActivateDatabase";
        r1 = "Failed to get xmsf activate info";
        android.util.Log.e(r0, r1);
    L_0x004e:
        return;
    L_0x004f:
        r0 = "ActivateDatabase";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1.<init>();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r2 = "Got ";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r2 = r8.getCount();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r2 = " activate info entries from old host";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.toString();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        android.util.Log.i(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
    L_0x0071:
        r0 = r8.moveToNext();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        if (r0 == 0) goto L_0x014d;	 Catch:{ all -> 0x0148, all -> 0x00cb }
    L_0x0077:
        r0 = 1;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r10 = r8.getString(r0);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = "activate_info";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = 1;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r2 = new java.lang.String[r0];	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = 0;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r3 = "_id";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r2[r0] = r3;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r3 = "simId=?";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = 1;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r4 = new java.lang.String[r0];	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = 0;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r4[r0] = r10;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r5 = 0;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r6 = 0;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r7 = 0;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = r14;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r9 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        if (r9 != 0) goto L_0x00a3;	 Catch:{ all -> 0x0148, all -> 0x00cb }
    L_0x0098:
        r0 = "ActivateDatabase";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = "Failed to check activate database";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        android.util.Log.e(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r8.close();
        goto L_0x004e;
    L_0x00a3:
        r0 = r9.getCount();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        if (r0 <= 0) goto L_0x00d0;	 Catch:{ all -> 0x0148, all -> 0x00cb }
    L_0x00a9:
        r0 = "ActivateDatabase";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1.<init>();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r2 = "item ";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.append(r10);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r2 = " already exists, ignore.";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.toString();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        android.util.Log.i(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
    L_0x00c7:
        r9.close();
        goto L_0x0071;
    L_0x00cb:
        r0 = move-exception;
        r8.close();
        throw r0;
    L_0x00d0:
        r0 = "ActivateDatabase";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1.<init>();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r2 = "insert item ";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.append(r10);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r1.toString();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        android.util.Log.i(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11 = new android.content.ContentValues;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.<init>();	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "simId";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.put(r0, r10);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "phone";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = 2;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r8.getString(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.put(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "userId";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = 3;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r8.getString(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.put(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "passToken";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = 4;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r8.getString(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.put(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "vkey1";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = 5;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r8.getString(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.put(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "vkey2";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = 6;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r8.getString(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.put(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "nextAutoActivationTime";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = 7;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r8.getLong(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = java.lang.Long.valueOf(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.put(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "insertedToServer";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = 8;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = r8.getInt(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r11.put(r0, r1);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r0 = "activate_info";	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r1 = 0;	 Catch:{ all -> 0x0148, all -> 0x00cb }
        r14.insert(r0, r1, r11);	 Catch:{ all -> 0x0148, all -> 0x00cb }
        goto L_0x00c7;
    L_0x0148:
        r0 = move-exception;
        r9.close();
        throw r0;	 Catch:{ all -> 0x0148, all -> 0x00cb }
    L_0x014d:
        r8.close();
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.activate.ActivateDatabaseHelper.mergeActivateInfoFromOldHost(android.database.sqlite.SQLiteDatabase):void");
    }

    static {
        ACTIVATE_INFO_TABLE_CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER DEFAULT 0)", new Object[]{ACTIVATE_INFO_TABLE_NAME, "_id", FIELD_SIM_ID, FIELD_PHONE, FIELD_USER_ID, FIELD_PASS_TOKEN, FIELD_VKEY1, FIELD_VKEY2, FIELD_NEXT_AUTO_ACTIVATION_TIME, FIELD_INSERTED_TO_SERVER});
        sInstance = null;
    }

    public static synchronized ActivateDatabaseHelper getInstance() {
        ActivateDatabaseHelper activateDatabaseHelper;
        synchronized (ActivateDatabaseHelper.class) {
            if (sInstance == null) {
                sInstance = new ActivateDatabaseHelper(ActivateExternal.getApp());
            }
            activateDatabaseHelper = sInstance;
        }
        return activateDatabaseHelper;
    }

    private ActivateDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ACTIVATE_INFO_TABLE_CREATE);
        List<ActivateInfo> infoList = Lists.newArrayList();
        ActivateExternal.getSysInteface().readAndRemoveOldActivateInfo(infoList);
        for (ActivateInfo info : infoList) {
            updateActivateInfo(db, info);
        }
        mergeActivateInfoFromOldHost(db);
    }

    public void updateActivateInfo(ActivateInfo info) {
        updateActivateInfo(getInstance().getWritableDatabase(), info);
    }

    private void updateActivateInfo(SQLiteDatabase db, ActivateInfo info) {
        ContentValues v = new ContentValues(8);
        v.put(FIELD_SIM_ID, info.hashedSimId);
        v.put(FIELD_PHONE, info.phone);
        v.put(FIELD_USER_ID, info.simUserId);
        v.put(FIELD_PASS_TOKEN, info.simPassToken);
        v.put(FIELD_VKEY1, info.vkey1);
        v.put(FIELD_VKEY2, info.vkey2);
        v.put(FIELD_NEXT_AUTO_ACTIVATION_TIME, Long.valueOf(info.nextAutoActivationTime));
        v.put(FIELD_INSERTED_TO_SERVER, Boolean.valueOf(info.insertedToServer));
        if (db.update(ACTIVATE_INFO_TABLE_NAME, v, "simId=?", new String[]{info.hashedSimId}) > 0) {
            Log.v(TAG, "1 entry updated in activate info database");
            return;
        }
        db.insert(ACTIVATE_INFO_TABLE_NAME, null, v);
        Log.v(TAG, "1 entry inserted in activate info database");
    }

    public boolean readActivateInfo(String simId, ActivateInfo info) {
        return readActivateInfo(getInstance().getReadableDatabase(), simId, info);
    }

    private boolean readActivateInfo(SQLiteDatabase db, String simId, ActivateInfo info) {
        boolean z = false;
        SQLiteDatabase sQLiteDatabase = db;
        Cursor c = sQLiteDatabase.query(ACTIVATE_INFO_TABLE_NAME, new String[]{FIELD_PHONE, FIELD_USER_ID, FIELD_PASS_TOKEN, FIELD_VKEY1, FIELD_VKEY2, FIELD_NEXT_AUTO_ACTIVATION_TIME, FIELD_INSERTED_TO_SERVER}, "simId=?", new String[]{info.hashedSimId}, null, null, null);
        if (c == null) {
            Log.e(TAG, "Error reading activate info database");
            return false;
        }
        try {
            if (c.getCount() == 0) {
                Log.v(TAG, "No activate info for simId");
                return false;
            } else if (c.getCount() > 1) {
                Log.e(TAG, "Multiple entries for simId");
                c.close();
                return false;
            } else {
                c.moveToPosition(0);
                info.hashedSimId = simId;
                info.phone = c.getString(0);
                info.simUserId = c.getString(1);
                info.simPassToken = c.getString(2);
                info.vkey1 = c.getString(3);
                info.vkey2 = c.getString(DATABASE_VERSION);
                info.nextAutoActivationTime = c.getLong(5);
                if (c.getInt(6) > 0) {
                    z = true;
                }
                info.insertedToServer = z;
                c.close();
                return true;
            }
        } finally {
            c.close();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ".");
        switch (oldVersion) {
            case TTransportException.NOT_OPEN /*1*/:
                if (newVersion > 1) {
                    db.beginTransaction();
                    try {
                        upgradeDatabaseToVersion2(db);
                        db.setTransactionSuccessful();
                        break;
                    } catch (Throwable ex) {
                        Log.e(TAG, ex.getMessage(), ex);
                        return;
                    } finally {
                        db.endTransaction();
                    }
                } else {
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                break;
            case TTransportException.TIMED_OUT /*3*/:
                break;
            default:
                return;
        }
        if (newVersion > 2) {
            db.beginTransaction();
            try {
                upgradeDatabaseToVersion3(db);
                db.setTransactionSuccessful();
                if (newVersion > 3) {
                    db.beginTransaction();
                    try {
                        upgradeDatabaseToVersion4(db);
                        db.setTransactionSuccessful();
                    } catch (Throwable ex2) {
                        Log.e(TAG, ex2.getMessage(), ex2);
                    } finally {
                        db.endTransaction();
                    }
                }
            } catch (Throwable ex22) {
                Log.e(TAG, ex22.getMessage(), ex22);
            } finally {
                db.endTransaction();
            }
        }
    }

    private void upgradeDatabaseToVersion2(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE activate_info ADD COLUMN insertedToServer INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion3(SQLiteDatabase db) {
        mergeActivateInfoFromOldHost(db);
    }

    private void upgradeDatabaseToVersion4(SQLiteDatabase db) {
        Log.v(TAG, "Start fixing database column types");
        String oldTableName = "old_activate_info";
        db.execSQL("ALTER TABLE activate_info RENAME TO old_activate_info");
        db.execSQL(ACTIVATE_INFO_TABLE_CREATE);
        Cursor c = db.query("old_activate_info", new String[]{FIELD_SIM_ID, FIELD_PHONE, FIELD_USER_ID, FIELD_PASS_TOKEN, FIELD_VKEY1, FIELD_VKEY2, FIELD_NEXT_AUTO_ACTIVATION_TIME, FIELD_INSERTED_TO_SERVER}, null, null, null, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                try {
                    String hashedSimId = c.getString(0);
                    String phone = c.getString(1);
                    String simUserId = c.getString(2);
                    String simPassToken = c.getString(3);
                    String vkey1 = c.getString(DATABASE_VERSION);
                    String vkey2 = c.getString(5);
                    long nextAutoActivationTime = c.getLong(6);
                    boolean insertedToServer = c.getInt(7) > 0;
                    if ((vkey1 == null || vkey1.length() == 32) && ((vkey2 == null || vkey2.length() == 16) && (hashedSimId == null || hashedSimId.length() == 16))) {
                        if (phone != null) {
                            boolean isChineseNumber = phone.length() == 11 && phone.charAt(0) == '1';
                            boolean shouldAddPlus = Character.isDigit(phone.charAt(0)) && !isChineseNumber;
                            if (shouldAddPlus) {
                                Log.w(TAG, "Fixed a number by adding '+': " + ActivateHelper.mask(phone));
                                phone = "+" + phone;
                            }
                        }
                        ContentValues contentValues = new ContentValues(8);
                        contentValues.put(FIELD_SIM_ID, hashedSimId);
                        contentValues.put(FIELD_PHONE, phone);
                        contentValues.put(FIELD_USER_ID, simUserId);
                        contentValues.put(FIELD_PASS_TOKEN, simPassToken);
                        contentValues.put(FIELD_VKEY1, vkey1);
                        contentValues.put(FIELD_VKEY2, vkey2);
                        contentValues.put(FIELD_NEXT_AUTO_ACTIVATION_TIME, Long.valueOf(nextAutoActivationTime));
                        contentValues.put(FIELD_INSERTED_TO_SERVER, Boolean.valueOf(insertedToServer));
                        if (db.update(ACTIVATE_INFO_TABLE_NAME, contentValues, "simId=?", new String[]{hashedSimId}) > 0) {
                            Log.v(TAG, "1 entry updated in v4 activate info database");
                        } else {
                            db.insert(ACTIVATE_INFO_TABLE_NAME, null, contentValues);
                            Log.v(TAG, "1 entry inserted in v4 activate info database");
                        }
                    } else {
                        Log.w(TAG, "Skipped an invalid old activate entry.");
                    }
                } finally {
                    c.close();
                }
            }
        }
        db.execSQL("DROP TABLE old_activate_info");
    }
}
