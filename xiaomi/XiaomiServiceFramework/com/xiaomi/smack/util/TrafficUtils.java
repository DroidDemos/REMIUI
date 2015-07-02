package com.xiaomi.smack.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.push.providers.TrafficDatabaseHelper;
import com.xiaomi.push.providers.TrafficProvider;
import com.xiaomi.push.providers.TrafficProvider.TrafficColumns;
import com.xiaomi.push.service.MIPushAccount;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrafficUtils {
    private static final long TRAFFIC_INTERVAL = 5000;
    private static final long WRITE2DB_INTERVAL = 5000;
    private static TrafficDatabaseHelper dbHelper;
    private static String imsi;
    private static final Object lock;
    private static SerializedAsyncTaskProcessor mAsyncProcessor;
    private static int networkType;
    private static List<TrafficInfo> trafficList;

    static class TrafficInfo {
        public long bytes;
        public String imsi;
        public long messageTs;
        public int networkType;
        public String packageName;
        public int rcv;

        public TrafficInfo(String packageName, long messageTs, int networkType, int rcv, String imsi, long bytes) {
            this.packageName = "";
            this.messageTs = 0;
            this.networkType = -1;
            this.rcv = -1;
            this.imsi = "";
            this.bytes = 0;
            this.packageName = packageName;
            this.messageTs = messageTs;
            this.networkType = networkType;
            this.rcv = rcv;
            this.imsi = imsi;
            this.bytes = bytes;
        }

        public boolean canAccumulate(TrafficInfo info) {
            if (TextUtils.equals(info.packageName, this.packageName) && TextUtils.equals(info.imsi, this.imsi) && info.networkType == this.networkType && info.rcv == this.rcv && Math.abs(info.messageTs - this.messageTs) <= TrafficUtils.WRITE2DB_INTERVAL) {
                return true;
            }
            return false;
        }
    }

    static {
        mAsyncProcessor = new SerializedAsyncTaskProcessor(true);
        networkType = -1;
        lock = new Object();
        trafficList = Collections.synchronizedList(new ArrayList());
        imsi = "";
        dbHelper = null;
    }

    public static void notifyNetworkChanage(Context context) {
        networkType = getActiveNetworkType(context);
    }

    private static int getNetworkType(Context context) {
        if (networkType == -1) {
            networkType = getActiveNetworkType(context);
        }
        return networkType;
    }

    private static int getActiveNetworkType(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
            if (cm == null) {
                return -1;
            }
            try {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info != null) {
                    return info.getType();
                }
                return -1;
            } catch (Exception e) {
                return -1;
            }
        } catch (Exception e2) {
            return -1;
        }
    }

    private static synchronized String getIMSI(Context context) {
        String str;
        synchronized (TrafficUtils.class) {
            if (TextUtils.isEmpty(imsi)) {
                try {
                    TelephonyManager telephony = (TelephonyManager) context.getSystemService("phone");
                    if (telephony != null) {
                        imsi = telephony.getSubscriberId();
                    }
                } catch (Exception e) {
                }
                str = imsi;
            } else {
                str = imsi;
            }
        }
        return str;
    }

    public static synchronized void updateIMSI(String id) {
        synchronized (TrafficUtils.class) {
            if (!TextUtils.isEmpty(id)) {
                imsi = id;
            }
        }
    }

    public static void distributionTraffic(final XMPushService pushService, String packageName, long bytesLength, boolean isRx, long ts) {
        if (pushService != null && !TextUtils.isEmpty(packageName) && LogProvider.AUTHORITY.equals(pushService.getPackageName()) && !LogProvider.AUTHORITY.equals(packageName)) {
            int type = getNetworkType(pushService);
            if (-1 != type) {
                boolean listEmpty;
                synchronized (lock) {
                    listEmpty = trafficList.isEmpty();
                    insertTrafficInfo2List(new TrafficInfo(packageName, ts, type, isRx ? 1 : 0, type == 0 ? getIMSI(pushService) : "", bytesLength));
                }
                if (listEmpty) {
                    mAsyncProcessor.addNewTaskWithDelayed(new SerializedAsyncTask() {
                        public void process() {
                            Throwable th;
                            synchronized (TrafficUtils.lock) {
                                try {
                                    List<TrafficInfo> infoList = new ArrayList(TrafficUtils.trafficList);
                                    try {
                                        TrafficUtils.trafficList.clear();
                                        TrafficUtils.insertTraffic(pushService, infoList);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        List<TrafficInfo> list = infoList;
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    throw th;
                                }
                            }
                        }
                    }, WRITE2DB_INTERVAL);
                }
            }
        }
    }

    private static void insertTraffic(Context context, List<TrafficInfo> infoList) {
        synchronized (TrafficDatabaseHelper.DataBaseLock) {
            SQLiteDatabase db = getTrafficDatabaseHelper(context).getWritableDatabase();
            db.beginTransaction();
            try {
                for (TrafficInfo trafficInfo : infoList) {
                    ContentValues values = new ContentValues();
                    values.put(MIPushAccount.PREF_KEY_PACKAGENAME, trafficInfo.packageName);
                    values.put(TrafficColumns.MESSAGE_TS, Long.valueOf(trafficInfo.messageTs));
                    values.put(TrafficColumns.NETWORK_TYPE, Integer.valueOf(trafficInfo.networkType));
                    values.put(TrafficColumns.BYTES, Long.valueOf(trafficInfo.bytes));
                    values.put(TrafficColumns.RCV, Integer.valueOf(trafficInfo.rcv));
                    values.put(TrafficColumns.IMSI, trafficInfo.imsi);
                    db.insert(TrafficProvider.TRAFFIC_TABLE_NAME, null, values);
                }
                db.setTransactionSuccessful();
                db.endTransaction();
            } catch (Throwable th) {
                db.endTransaction();
            }
        }
    }

    private static TrafficDatabaseHelper getTrafficDatabaseHelper(Context context) {
        if (dbHelper != null) {
            return dbHelper;
        }
        dbHelper = new TrafficDatabaseHelper(context);
        return dbHelper;
    }

    public static int getTrafficFlow(String s) {
        try {
            return s.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            return s.getBytes().length;
        }
    }

    private static void insertTrafficInfo2List(TrafficInfo info) {
        for (TrafficInfo element : trafficList) {
            if (element.canAccumulate(info)) {
                element.bytes += info.bytes;
                return;
            }
        }
        trafficList.add(info);
    }
}
