package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.ui.ExpressInquiryProgressFragment;
import com.miui.yellowpage.ui.aH;
import com.miui.yellowpage.ui.cc;
import com.miui.yellowpage.ui.cs;

public class ExpressInquiryHistoryActivity extends ExpressBaseActivity implements cc {
    private boolean Hu;
    private Bundle mArguments;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        u();
        if (this.mArguments == null) {
            Log.d("ExpressInquiryHistoryActivity", "invalid data:" + getIntent().getData());
            finish();
        } else if (this.Hu) {
            j(this.mArguments);
        } else {
            showFragment("InquiryHistoryFragment", null, this.mArguments, false);
        }
    }

    private void u() {
        Bundle bundle = null;
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            Uri data = intent.getData();
            CharSequence host = data.getHost();
            if (TextUtils.equals("yellowpage", data.getScheme()) && TextUtils.equals("inquiry_history", host)) {
                bundle = new Bundle();
                Object queryParameter = data.getQueryParameter("order");
                Object queryParameter2 = data.getQueryParameter("bizCode");
                Object queryParameter3 = data.getQueryParameter("logistics_name");
                if (!(TextUtils.isEmpty(queryParameter) || TextUtils.isEmpty(queryParameter2) || TextUtils.isEmpty(queryParameter3))) {
                    this.Hu = true;
                    bundle.putString("order", queryParameter);
                    bundle.putString("bizCode", queryParameter2);
                    bundle.putString("logistics_name", queryParameter3);
                }
            }
        }
        this.mArguments = bundle;
    }

    protected cs newFragmentByTag(String str) {
        if ("ExpressProgressFragment".equals(str)) {
            return new ExpressInquiryProgressFragment();
        }
        if ("InquiryHistoryFragment".equals(str)) {
            return new aH();
        }
        return null;
    }

    public void g(Bundle bundle) {
        j(bundle);
    }

    private void j(Bundle bundle) {
        bundle.putString("inquiry_type", "inquiry_specified");
        showFragment("ExpressProgressFragment", null, bundle, !this.Hu);
    }
}
