package com.xiaomi.push.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.push.providers.TrafficProvider.TrafficColumns;
import com.xiaomi.push.service.MIPushAccount;

public class TrafficDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "traffic.db";
    private static int DATABASE_VERSION = 0;
    public static final Object DataBaseLock;
    private static final String[] TRAFFIC_Columns;
    public static final String TRAFFIC_TABLE = "traffic";
    private static final String ZERO_BASED_INTEGER = " INT DEFAULT -1 ";
    private static final String ZERO_BASED_LONG = " LONG DEFAULT 0 ";

    static {
        DATABASE_VERSION = 1;
        DataBaseLock = new Object();
        TRAFFIC_Columns = new String[]{MIPushAccount.PREF_KEY_PACKAGENAME, "TEXT", TrafficColumns.MESSAGE_TS, ZERO_BASED_LONG, TrafficColumns.BYTES, ZERO_BASED_LONG, TrafficColumns.NETWORK_TYPE, ZERO_BASED_INTEGER, TrafficColumns.RCV, ZERO_BASED_INTEGER, TrafficColumns.IMSI, "TEXT"};
    }

    public TrafficDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        synchronized (DataBaseLock) {
            try {
                createTrafficTable(db);
            } catch (Throwable e) {
                MyLog.e(e);
            }
        }
    }

    private void createTrafficTable(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder("CREATE TABLE traffic(_id INTEGER  PRIMARY KEY ,");
        for (int i = 0; i < TRAFFIC_Columns.length - 1; i += 2) {
            if (i != 0) {
                builder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
            builder.append(TRAFFIC_Columns[i]).append(" ").append(TRAFFIC_Columns[i + 1]);
        }
        builder.append(");");
        db.execSQL(builder.toString());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
