package com.xiaomi.account.legacy.activate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.google.android.collect.Lists;
import com.xiaomi.passport.widget.AlertDialog;
import java.util.List;
import miui.external.SdkConstants.SdkReturnCode;

public class ActivateDatabaseHelper extends SQLiteOpenHelper {
    private static final String ACTIVATE_INFO_TABLE_CREATE;
    public static final String ACTIVATE_INFO_TABLE_NAME = "activate_info";
    private static final String DATABASE_NAME = "activate_info.db";
    private static final int DATABASE_VERSION = 3;
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

    static {
        ACTIVATE_INFO_TABLE_CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s STRING, %s STRING, %s STRING, %s STRING, %s STRING, %s STRING, %s INTEGER, %s INTEGER DEFAULT 0)", new Object[]{ACTIVATE_INFO_TABLE_NAME, "_id", FIELD_SIM_ID, FIELD_PHONE, FIELD_USER_ID, FIELD_PASS_TOKEN, FIELD_VKEY1, FIELD_VKEY2, FIELD_NEXT_AUTO_ACTIVATION_TIME, FIELD_INSERTED_TO_SERVER});
        sInstance = null;
    }

    public static synchronized ActivateDatabaseHelper getInstance(Context context) {
        ActivateDatabaseHelper activateDatabaseHelper;
        synchronized (ActivateDatabaseHelper.class) {
            if (sInstance == null) {
                sInstance = new ActivateDatabaseHelper(context.getApplicationContext());
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
        if (!ActivateOldInfoReader.readActivateInfoV2(this.mContext, infoList)) {
            ActivateOldInfoReader.readActivateInfoV1(this.mContext, infoList);
        }
        ActivateOldInfoReader.clearActivateInfoV2(this.mContext);
        ActivateOldInfoReader.clearActivateInfoV1(this.mContext);
        for (ActivateInfo info : infoList) {
            updateActivateInfo(db, info);
        }
    }

    private void updateActivateInfo(SQLiteDatabase db, ActivateInfo info) {
        ContentValues v = new ContentValues(4);
        v.put(FIELD_SIM_ID, info.hashedSimId);
        v.put(FIELD_PHONE, info.phone);
        v.put(FIELD_USER_ID, info.simUserId);
        v.put(FIELD_PASS_TOKEN, info.simPassToken);
        v.put(FIELD_VKEY1, info.vkey1);
        v.put(FIELD_VKEY2, info.vkey2);
        v.put(FIELD_NEXT_AUTO_ACTIVATION_TIME, Long.valueOf(info.nextAutoActivationTime));
        v.put(FIELD_INSERTED_TO_SERVER, Boolean.valueOf(info.insertedToServer));
        if (db.update(ACTIVATE_INFO_TABLE_NAME, v, "simId=?", new String[]{info.hashedSimId}) > 0) {
            Log.v(TAG, "1 entry updated in activated info database");
            return;
        }
        db.insert(ACTIVATE_INFO_TABLE_NAME, null, v);
        Log.v(TAG, "1 entry inserted in activated info database");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ".");
        switch (oldVersion) {
            case SdkReturnCode.LOW_SDK_VERSION /*1*/:
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
            case AlertDialog.THEME_DARK /*2*/:
                break;
            default:
                return;
        }
        if (newVersion > 2) {
            db.beginTransaction();
            try {
                upgradeDatabaseToVersion3(db);
                db.setTransactionSuccessful();
            } catch (Throwable ex2) {
                Log.e(TAG, ex2.getMessage(), ex2);
            } finally {
                db.endTransaction();
            }
        }
    }

    private void upgradeDatabaseToVersion2(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE activate_info ADD COLUMN insertedToServer INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion3(SQLiteDatabase db) {
    }
}
