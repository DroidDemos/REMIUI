package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.ui.ExpressInquiryFragment;
import com.miui.yellowpage.ui.ExpressInquiryProgressFragment;
import com.miui.yellowpage.ui.cc;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.ui.e;

public class ExpressInquiryActivity extends ExpressBaseActivity implements cc, e {
    private boolean Ho;
    private String mTitle;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle i = i(getIntent());
        i(i);
        if (i.containsKey("order")) {
            showFragment("ExpressProgressFragment", this.mTitle, i, false);
        } else {
            showFragment("ExpressInquiryFragment", this.mTitle, i, false);
        }
        j(getIntent());
    }

    private Bundle i(Intent intent) {
        Bundle bundle = new Bundle();
        if (intent != null) {
            if ("android.intent.action.VIEW".equals(intent.getAction())) {
                Uri data = intent.getData();
                Object queryParameter = data.getQueryParameter("number");
                if (!TextUtils.isEmpty(queryParameter)) {
                    bundle.putString("order", queryParameter);
                }
                queryParameter = data.getQueryParameter("company_code");
                if (!TextUtils.isEmpty(queryParameter)) {
                    bundle.putString("bizCode", queryParameter);
                }
                Object queryParameter2 = data.getQueryParameter("company_name");
                if (!TextUtils.isEmpty(queryParameter2)) {
                    bundle.putString("logistics_name", queryParameter2);
                }
            }
            if (intent.getExtras() != null) {
                Bundle extras = intent.getExtras();
                if (extras.containsKey("bizCode")) {
                    bundle.putString("bizCode", extras.getString("bizCode"));
                }
                if (extras.containsKey("logistics_name")) {
                    bundle.putString("logistics_name", extras.getString("logistics_name"));
                }
            }
        }
        return bundle;
    }

    private void i(Bundle bundle) {
        this.mTitle = getString(R.string.express_inquiry);
        if (bundle != null) {
            Object string = bundle.getString("logistics_name");
            if (!TextUtils.isEmpty(string)) {
                this.mTitle = string;
            }
        }
    }

    private void j(Intent intent) {
        BusinessStatistics.viewNormalDisplay(this, "expressInquery", getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
    }

    protected cs newFragmentByTag(String str) {
        if ("ExpressInquiryFragment".equals(str)) {
            return new ExpressInquiryFragment();
        }
        if ("ExpressProgressFragment".equals(str)) {
            return new ExpressInquiryProgressFragment();
        }
        return null;
    }

    public void d(boolean z) {
        if (z) {
            setResult(-1);
        }
    }

    public void g(Bundle bundle) {
        ExpressInquiryProgressFragment expressInquiryProgressFragment = (ExpressInquiryProgressFragment) showFragment("ExpressProgressFragment", null, bundle, true);
        expressInquiryProgressFragment.a((e) this);
        this.Ho = expressInquiryProgressFragment.dW();
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.Ho) {
            finish();
        }
    }

    protected boolean requireNetworking() {
        return false;
    }
}
