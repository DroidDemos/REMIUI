package com.xiaomi.xmsf.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.xiaomi.push.service.PushServiceConstants;
import miui.R;

public class WarningActivity extends Activity {
    private static final String TAG = "WarningActivity";
    private Dialog mDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null) {
            Log.w(TAG, "no intent");
            finish();
            return;
        }
        if ("com.xiaomi.action.WARN_INVALID_DEVICE_ID".equals(intent.getAction())) {
            String deviceId = intent.getStringExtra(PushServiceConstants.EXTRA_DEVICE_ID);
            Builder builder = new Builder(this, R.style.Theme_Light_Dialog_Alert);
            builder.setTitle(com.xiaomi.xmsf.R.string.error_device_id_title);
            builder.setMessage(getString(com.xiaomi.xmsf.R.string.error_device_id_message, new Object[]{deviceId}));
            builder.setPositiveButton(com.xiaomi.xmsf.R.string.accept, null);
            builder.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    WarningActivity.this.finish();
                }
            });
            this.mDialog = builder.show();
            this.mDialog.getWindow().setCloseOnTouchOutside(false);
            return;
        }
        finish();
    }
}
