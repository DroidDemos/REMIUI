package com.xiaomi.xmsf.activate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.xiaomi.activate.SysEventSink;

public class SimStateReceiver extends BroadcastReceiver {
    private static final String TAG = "xmsf/SimStateReceiver";

    public static BroadcastReceiver register(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SIM_STATE_CHANGED");
        BroadcastReceiver receiver = new SimStateReceiver();
        context.registerReceiver(receiver, filter);
        Log.v(TAG, "SIM_STATE_CHANGED receiver registered to system");
        return receiver;
    }

    public static void unregister(Context context, BroadcastReceiver r) {
        context.unregisterReceiver(r);
        Log.v(TAG, "SIM_STATE_CHANGED receiver unregistered from system");
    }

    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra("ss");
        Log.v(TAG, "receives SIM_STATE_CHANGED broadcast, state = " + state);
        if ("ABSENT".equals(state)) {
            notifySimStateChanged(context, intent, false);
        } else if ("LOADED".equals(state)) {
            notifySimStateChanged(context, intent, true);
        }
    }

    private void notifySimStateChanged(Context context, Intent intent, boolean inserted) {
        int simIndex = intent.getIntExtra("slot_id", -1);
        if (simIndex < 0) {
            Log.w(TAG, "no value in intent for key slot_id, bail.");
        } else {
            SysEventSink.onSimStateChanged(simIndex, inserted);
        }
    }
}
