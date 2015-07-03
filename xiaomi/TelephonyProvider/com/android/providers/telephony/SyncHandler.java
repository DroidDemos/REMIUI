package com.android.providers.telephony;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;

public class SyncHandler extends Handler {
    private static final int MSG_REQUEST_SYNC = 1001;
    private static final int SYNC_DELAY_MILLIS = 5000;
    private static String TAG;
    private final ArrayList<SyncInfo> mSyncInfos;

    private static class SyncInfo {
        Context context;
        String name;

        public SyncInfo(String name, Context context) {
            this.name = name;
            this.context = context;
        }

        public boolean equals(Object o) {
            if (!(o instanceof SyncInfo)) {
                return false;
            }
            return this.name.equals(((SyncInfo) o).name);
        }
    }

    static {
        TAG = "SyncHandler";
    }

    public SyncHandler() {
        super(Looper.getMainLooper());
        this.mSyncInfos = new ArrayList();
    }

    public void handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_REQUEST_SYNC /*1001*/:
                SyncInfo syncInfo = msg.obj;
                Context context = syncInfo.context;
                String name = syncInfo.name;
                Account[] accounts = AccountManager.get(context).getAccountsByType("com.xiaomi");
                Bundle b = new Bundle();
                b.putString("localName", name);
                Log.d(TAG, "requestSync name is " + name);
                for (Account requestSync : accounts) {
                    ContentResolver.requestSync(requestSync, "sms", b);
                }
                this.mSyncInfos.remove(syncInfo);
                return;
            default:
                return;
        }
    }

    public void scheduleSync(Context context, String name) {
        SyncInfo syncInfo = new SyncInfo(name, context);
        int index = this.mSyncInfos.indexOf(syncInfo);
        if (index > -1) {
            removeMessages(MSG_REQUEST_SYNC, this.mSyncInfos.get(index));
            this.mSyncInfos.remove(index);
        }
        sendMessageDelayed(obtainMessage(MSG_REQUEST_SYNC, syncInfo), 5000);
        this.mSyncInfos.add(syncInfo);
    }
}
