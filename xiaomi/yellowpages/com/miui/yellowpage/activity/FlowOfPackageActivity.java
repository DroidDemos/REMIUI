package com.miui.yellowpage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.ui.K;
import com.miui.yellowpage.ui.a;
import com.miui.yellowpage.ui.cf;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.utils.D;

public class FlowOfPackageActivity extends BaseActivity {
    private String oO;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.oO = getIntent().getStringExtra("sim_op_code");
        BusinessStatistics.viewNormalDisplay(this, "flowOfPackages", getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
        if (TextUtils.isEmpty(this.oO)) {
            Log.d("FlowOfPackageActivity", "The sim op code should not be null, finish()");
            finish();
            return;
        }
        Bundle bundle2 = new Bundle();
        if (cx()) {
            D.a(this, getString(R.string.packages_select_sim_cards_title), getString(R.string.packages_select_sim_cards_summmary), new x(this, bundle2));
            return;
        }
        int intExtra = getIntent().getIntExtra("sim_index", -1);
        if (intExtra == -1) {
            intExtra = ap(this.oO);
        }
        bundle2.putInt("sim_index", intExtra);
        getStatisticsContext().attach(bundle2);
        showFragment("FlowOfPackageFragment", getString(R.string.packages_title), bundle2, false);
    }

    protected cs newFragmentByTag(String str) {
        if ("FlowOfPackageFragment".equals(str)) {
            return new cf();
        }
        if ("FlowOfPackageConfirmFragment".equals(str)) {
            return new a();
        }
        if ("FlowOfPackageResultFragment".equals(str)) {
            return new K();
        }
        return null;
    }

    private int ap(String str) {
        if (Sim.getSimCount(this) == 2) {
            if (TextUtils.equals(str, Sim.getSimOpCode(this, 0))) {
                return 0;
            }
            if (TextUtils.equals(str, Sim.getSimOpCode(this, 1))) {
                return 1;
            }
        }
        return Sim.DEFAULT_SIM_INDEX;
    }

    private boolean cx() {
        if (Sim.getSimCount(this) != 2) {
            return false;
        }
        String simOpCode = Sim.getSimOpCode(this, 0);
        if (Sim.isTheSameOperator(simOpCode, Sim.getSimOpCode(this, 1)) && Sim.isTheSameOperator(this.oO, simOpCode)) {
            return true;
        }
        return false;
    }
}
