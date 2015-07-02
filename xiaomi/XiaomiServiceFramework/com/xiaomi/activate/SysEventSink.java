package com.xiaomi.activate;

import android.content.Intent;
import android.util.Log;
import com.xiaomi.accountsdk.activate.ActivateManager;

public class SysEventSink {
    private static final String TAG = "SysEventSink";

    public static void onSimStateChanged(int simIndex, boolean inserted) {
        Log.v(TAG, "SysEventSink : onSimStateChanged simIndex=" + simIndex + " inserted=" + inserted);
        Intent intent = new Intent(ActivateService.ACTION_INTERNAL_NOTIFY_SIM_STATE_CHANGED);
        intent.putExtra("extra_sim_index", simIndex);
        intent.putExtra("extra_sim_inserted", inserted);
        intent.setPackage(ActivateManager.sActivateServiceHostPackage);
        ActivateExternal.getApp().startService(intent);
    }

    public static void onActivateSmsReceived(int simIndex, String address, String vkey1, String msgId) {
        Log.v(TAG, "SysEventSink : onActivateSmsReceived simIndex=" + simIndex + " msgId=" + msgId);
        Intent intent = new Intent("com.xiaomi.action.ACTIVATION_SMS_RECEIVED");
        intent.putExtra("extra_sim_index", simIndex);
        intent.putExtra("extra_address", address);
        intent.putExtra("extra_msg_id", msgId);
        intent.putExtra("extra_vkey1", vkey1);
        ActivateExternal.getApp().sendBroadcast(intent);
    }
}
