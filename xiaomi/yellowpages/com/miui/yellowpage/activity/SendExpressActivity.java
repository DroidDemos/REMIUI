package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.ui.SendExpressFragment;
import com.miui.yellowpage.ui.U;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.ui.dn;

public class SendExpressActivity extends ExpressBaseActivity {
    private au AM;
    private at AN;
    private boolean AO;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.AM = new au();
        this.AN = new at();
        showFragment("SendExpressFragment", null, parseQueryParams(), false);
        bM();
    }

    private void bM() {
        BusinessStatistics.viewNormalDisplay(this, "expressSend", getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
    }

    private void cV() {
        ThreadPool.execute(new a(this));
    }

    protected cs newFragmentByTag(String str) {
        cs sendExpressFragment;
        if ("SendExpressFragment".equals(str)) {
            sendExpressFragment = new SendExpressFragment();
            sendExpressFragment.a(this.AM);
            return sendExpressFragment;
        } else if ("SendExpressResultFragment".equals(str)) {
            sendExpressFragment = new dn();
            sendExpressFragment.a(new aq());
            sendExpressFragment.a(new af());
            return sendExpressFragment;
        } else if (!"SendExpressConfirmFragment".equals(str)) {
            return null;
        } else {
            sendExpressFragment = new U();
            sendExpressFragment.a(this.AN);
            return sendExpressFragment;
        }
    }

    private void gF() {
        do {
        } while (getFragmentManager().popBackStackImmediate());
        this.AO = false;
    }

    public void onBackPressed() {
        if (this.AO) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean requireLogin() {
        return true;
    }

    protected boolean bo() {
        return true;
    }

    protected void bp() {
        Intent intent = new Intent("com.miui.yellowpage.action.ORDER");
        intent.setData(Uri.parse("yellowpage://order?view=type&id=6"));
        startActivity(intent);
    }
}
