package com.miui.yellowpage.business.recharge;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.BaseActivity;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.utils.x;

public class RechargeActivity extends BaseActivity {
    private cs mB;
    private Button mC;
    private View mCustomView;
    private String mD;

    @SuppressLint({"InflateParams"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCustomView = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.recharge_history_custom_view, null);
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mActionBar.setCustomView(this.mCustomView, new LayoutParams(-2, -2, 21));
        this.mC = (Button) this.mCustomView.findViewById(R.id.history_button);
        this.mC.setOnClickListener(new q(this));
        Bundle bL = bL();
        getStatisticsContext().attach(bL);
        showFragment("MiPayRechargeFragment", getString(R.string.recharge_title), bL, false);
        bM();
    }

    private Bundle bL() {
        Bundle parseQueryParams = parseQueryParams();
        if (parseQueryParams == null) {
            parseQueryParams = new Bundle();
        }
        Intent intent = getIntent();
        if (intent != null) {
            parseQueryParams.putAll(intent.getExtras());
        }
        return parseQueryParams;
    }

    private void bM() {
        BusinessStatistics.viewNormalDisplay(this, "recharge", getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
    }

    protected cs newFragmentByTag(String str) {
        if ("MiPayRechargeFragment".equals(str)) {
            return new D();
        }
        if ("MiPayRechargeOrderListFragment".equals(str)) {
            return new N();
        }
        return null;
    }

    public cs showFragment(String str, String str2, Bundle bundle, boolean z) {
        if ("MiPayRechargeFragment".equals(str)) {
            this.mCustomView.setVisibility(0);
        } else {
            this.mCustomView.setVisibility(4);
        }
        this.mB = super.showFragment(str, str2, bundle, z);
        return this.mB;
    }

    public void onBackPressed() {
        if (this.mB != null && !this.mB.onBackPressed()) {
            if ("MiPayRechargeOrderListFragment".equals(this.mB.getTag())) {
                this.mCustomView.setVisibility(0);
                this.mActionBar.setTitle(this.mD);
            } else {
                this.mCustomView.setVisibility(4);
            }
            x.a(getWindow().getDecorView(), false);
            super.onBackPressed();
        }
    }

    protected boolean requireLogin() {
        return true;
    }
}
