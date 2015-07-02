package com.miui.yellowpage.business.recharge;

import android.content.Intent;
import android.os.Bundle;
import com.miui.yellowpage.activity.RechargeActivity;
import miui.app.Activity;

public class RechargeRouterActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            intent = new Intent();
        }
        startActivity(e(intent));
        finish();
    }

    private Intent e(Intent intent) {
        Bundle extras = intent.getExtras();
        Object obj = null;
        if (extras != null) {
            obj = extras.getString("version");
        }
        Intent intent2 = new Intent();
        intent2.putExtras(intent);
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(intent.getData());
        }
        if ("v2".equals(obj)) {
            intent2.setClass(this, RechargeActivity.class);
        } else {
            intent2.setClass(this, RechargeActivity.class);
        }
        return intent2;
    }
}
