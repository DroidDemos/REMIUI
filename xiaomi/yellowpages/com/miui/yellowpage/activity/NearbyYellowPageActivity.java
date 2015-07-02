package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.ui.db;

public class NearbyYellowPageActivity extends BaseActivity {
    private db ro;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        dq();
        if (this.ro != null) {
            this.mPermissionRequestListener = new av(this);
        }
    }

    private void dq() {
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            Uri data = intent.getData();
            if (data != null && TextUtils.equals(data.getScheme(), "yellowpage")) {
                CharSequence string = getString(R.string.nearby_yellow_page_title);
                Bundle bundle = new Bundle();
                getStatisticsContext().attach(bundle);
                Object host = data.getHost();
                String queryParameter;
                CharSequence queryParameter2;
                if (TextUtils.equals(host, "nearby")) {
                    queryParameter = data.getQueryParameter("sid");
                    String queryParameter3 = data.getQueryParameter("groupid");
                    queryParameter2 = data.getQueryParameter(MiniDefine.au);
                    bundle.putString("com.miui.yellowpage.extra.yid", queryParameter);
                    bundle.putString("yellowpage_branch_group_id", queryParameter3);
                    bundle.putString(MiniDefine.p, "nearby_yp");
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        string = getString(R.string.nearby_yellow_page_title_format, new Object[]{string, queryParameter2});
                    }
                    BusinessStatistics.viewNearbyYellowPage(this, queryParameter, queryParameter3, this.mStatsContext.getSourceModuleId(), this.mStatsContext.getSource());
                } else if (TextUtils.equals(host, "nearby_hotcat")) {
                    queryParameter = data.getQueryParameter("hotcatid");
                    queryParameter2 = data.getQueryParameter("hotcattitle");
                    bundle.putString("hot_cat_id", queryParameter);
                    bundle.putString(MiniDefine.p, "nearby_hotcat");
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        string = getString(R.string.nearby_yellow_page_title_format, new Object[]{string, queryParameter2});
                    }
                    BusinessStatistics.viewNearbyHotCategory(this, queryParameter, this.mStatsContext.getSourceModuleId(), this.mStatsContext.getSource());
                } else {
                    Log.e("NearbyYellowPageActivity", "Not supported host " + host);
                    finish();
                    return;
                }
                setTitle(string);
                this.ro = (db) showFragment("NearbyYellowPageFragment", null, bundle, false);
                return;
            }
            return;
        }
        finish();
    }

    protected cs newFragmentByTag(String str) {
        if ("NearbyYellowPageFragment".equals(str)) {
            return new db();
        }
        return null;
    }
}
