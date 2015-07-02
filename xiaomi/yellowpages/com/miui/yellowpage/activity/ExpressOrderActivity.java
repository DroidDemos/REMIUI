package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.ui.B;
import com.miui.yellowpage.ui.ExpressOrderItemFragment;
import com.miui.yellowpage.ui.cs;
import java.io.Serializable;

public class ExpressOrderActivity extends BaseActivity implements B {
    private Serializable CP;
    private boolean CQ;
    private String dV;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (gU()) {
            Bundle bundle2 = new Bundle();
            if (TextUtils.isEmpty(this.dV)) {
                finish();
                return;
            }
            bundle2.putString("id", this.dV);
            bundle2.putSerializable("extra_state", this.CP);
            showFragment("ExpressOrderItemFragment", null, bundle2, false);
            return;
        }
        Log.e("ExpressOrderActivity", "invalid scheme or query parameters:" + getIntent().getData());
        finish();
    }

    private boolean gU() {
        boolean z = false;
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            Uri data = intent.getData();
            if (TextUtils.equals(data.getScheme(), "yellowpage") && TextUtils.equals(data.getHost(), "express_order")) {
                z = true;
                this.dV = data.getQueryParameter("id");
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    this.CP = extras.getSerializable("extra_state");
                }
            }
        }
        return z;
    }

    protected cs newFragmentByTag(String str) {
        if (!"ExpressOrderItemFragment".equals(str)) {
            return null;
        }
        cs expressOrderItemFragment = new ExpressOrderItemFragment();
        expressOrderItemFragment.a((B) this);
        return expressOrderItemFragment;
    }

    public void aF() {
        this.CQ = true;
    }

    public void onBackPressed() {
        if (this.CQ) {
            setResult(3);
        } else {
            setResult(2);
        }
        finish();
    }
}
