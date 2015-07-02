package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.BaseActivity;
import com.miui.yellowpage.base.model.FlowOfPackages;
import com.miui.yellowpage.base.model.FlowOfPackages.Packages;
import com.miui.yellowpage.base.model.FlowOfPackages.PackagesGroup;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.widget.LoadingProgressView;
import java.util.List;

/* compiled from: FlowOfPackageFragment */
public class cf extends cs implements OnClickListener {
    private LinearLayout CS;
    private TextView CT;
    private TextView CU;
    private Button CV;
    private FlowOfPackages CW;
    private Packages CX;
    private FlowOfPackagesItem CY;
    private bu CZ;
    private LoadingProgressView hh;
    private int ip;
    private String iq;
    private View mRootView;

    public cf() {
        this.ip = Sim.DEFAULT_SIM_INDEX;
    }

    private void u() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.ip = arguments.getInt("sim_index");
            this.iq = arguments.getString("source");
        }
    }

    private void n(View view) {
        this.mRootView = view.findViewById(R.id.root_view);
        this.CS = (LinearLayout) view.findViewById(R.id.packages);
        this.CT = (TextView) view.findViewById(R.id.packages_title);
        this.CU = (TextView) view.findViewById(R.id.packages_description);
        this.CV = (Button) view.findViewById(R.id.pay);
        this.CV.setOnClickListener(this);
        this.hh = (LoadingProgressView) view.findViewById(R.id.loading_view);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.flow_of_package_fragment, viewGroup, false);
        n(inflate);
        u();
        return inflate;
    }

    private void a(FlowOfPackages flowOfPackages) {
        this.CX = null;
        List<PackagesGroup> flowOfPackages2 = flowOfPackages.getFlowOfPackages();
        if (flowOfPackages2 != null || flowOfPackages2.size() > 0) {
            this.CS.removeAllViews();
            for (PackagesGroup packagesGroup : flowOfPackages2) {
                TextView textView = (TextView) LayoutInflater.from(this.mActivity).inflate(R.layout.flow_of_package_group_title, this.CS, false);
                this.CS.addView(textView);
                textView.setText(packagesGroup.getTitle());
                List packages = packagesGroup.getPackages();
                for (int i = 0; i < packages.size(); i++) {
                    Packages packages2 = (Packages) packages.get(i);
                    if (!TextUtils.isEmpty(packages2.getTitle())) {
                        FlowOfPackagesItem flowOfPackagesItem = (FlowOfPackagesItem) LayoutInflater.from(this.mActivity).inflate(R.layout.flow_of_packages_item, this.CS, false);
                        flowOfPackagesItem.a(packages2, i, packages.size());
                        flowOfPackagesItem.setTag(packages2);
                        flowOfPackagesItem.setOnClickListener(new aW(this, flowOfPackagesItem));
                        this.CS.addView(flowOfPackagesItem);
                        if (TextUtils.equals(packages2.getSubscribeCode(), Preference.getString(this.mActivity, "pref_last_package_id", ConfigConstant.WIRELESS_FILENAME))) {
                            flowOfPackagesItem.E(true);
                            this.CX = packages2;
                            this.CY = flowOfPackagesItem;
                        }
                    }
                }
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.CZ = new bu();
        getLoaderManager().initLoader(1, null, this.CZ);
    }

    private BaseRequest gV() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getFlowOfPackagesUrl(), 1);
        httpRequest.addParam("simop", String.valueOf(this.ip));
        return httpRequest;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay:
                if (this.CX == null) {
                    Toast.makeText(this.mActivity, R.string.select_package_before_buy, 0).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("com.miui.yellowpage.flow_of_packages_order_tips", this.CX.getTips());
                bundle.putString("com.miui.yellowpage.flow_of_packages_group_title", this.CX.getGroupTitle());
                bundle.putString("com.miui.yellowpage.flow_of_packages_package_title", this.CX.getTitle());
                bundle.putString("phone", this.CX.getSubscribePhone());
                bundle.putString("sms_body", this.CX.getSubscribeCode());
                bundle.putInt("sim_index", this.ip);
                bundle.putString("source", this.iq);
                ((BaseActivity) this.mActivity).showFragment("FlowOfPackageConfirmFragment", null, bundle, true);
                return;
            default:
                return;
        }
    }
}
