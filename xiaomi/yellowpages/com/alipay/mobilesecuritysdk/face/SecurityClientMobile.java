package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import android.util.Log;
import com.alipay.mobilesecuritysdk.MainHandler;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.deviceID.DeviceIdManager;
import java.util.List;
import java.util.Map;

public class SecurityClientMobile {
    private static boolean isDebug;
    private static boolean isError;
    private static Thread workThread;

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ boolean val$isCollected;
        private final /* synthetic */ List val$tid;

        AnonymousClass1(Context context, List list, boolean z) {
            this.val$context = context;
            this.val$tid = list;
            this.val$isCollected = z;
        }

        public void run() {
            try {
                new MainHandler().mainhandler(this.val$context, this.val$tid, this.val$isCollected);
            } catch (Throwable th) {
                if (SecurityClientMobile.isDebug) {
                    Log.i(ConfigConstant.LOG_TAG, "mainThread error :" + th.getMessage());
                }
            }
        }
    }

    public static synchronized String GetApdid(Context context, Map map) {
        String GetApDid;
        synchronized (SecurityClientMobile.class) {
            GetApDid = new DeviceIdManager(context).GetApDid(map);
        }
        return GetApDid;
    }

    static {
        isError = false;
        isDebug = false;
    }

    public static synchronized void start(Context context, List list, boolean z) {
        synchronized (SecurityClientMobile.class) {
            try {
                if (isDebug) {
                    Log.i(ConfigConstant.LOG_TAG, "start have been called.");
                }
                if (context == null) {
                    if (isDebug) {
                        Log.i(ConfigConstant.LOG_TAG, "Context is null.");
                    }
                } else if (workThread == null || !workThread.isAlive()) {
                    workThread = null;
                    if (!isError) {
                        workThread = new Thread(new AnonymousClass1(context, list, z));
                        workThread.start();
                    } else if (isDebug) {
                        Log.i(ConfigConstant.LOG_TAG, "some error happend, quit.");
                    }
                } else if (isDebug) {
                    Log.i(ConfigConstant.LOG_TAG, "mainThread is working, quit.");
                }
            } catch (Throwable th) {
            }
        }
    }

    public static void stop() {
        try {
            if (isDebug) {
                Log.i(ConfigConstant.LOG_TAG, "stop have been called.");
            }
            if (workThread != null && workThread.isAlive()) {
                workThread.interrupt();
                workThread = null;
            }
        } catch (Throwable th) {
        }
    }

    public static void setError(boolean z) {
        isError = z;
    }

    public static void setDebug(boolean z) {
        isDebug = z;
    }

    public static boolean isDebug() {
        return isDebug;
    }
}
