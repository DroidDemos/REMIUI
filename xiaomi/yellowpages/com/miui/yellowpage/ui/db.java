package com.miui.yellowpage.ui;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.a;
import com.miui.yellowpage.a.e;
import com.miui.yellowpage.b.b;
import com.miui.yellowpage.base.model.City;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.GeoLocationManager;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.widget.LoadingProgressView;

/* compiled from: NearbyYellowPageFragment */
public class db extends cs implements e {
    private String GA;
    private String GB;
    private String GC;
    private a GD;
    private b GE;
    private P Gv;
    private b Gw;
    private Location Gx;
    private String Gy;
    private String Gz;
    private ListView bK;
    private LoadingProgressView hh;

    public db() {
        this.GE = new b(this, new df(this));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.nearby_yellow_page_fragment, viewGroup, false);
        this.Gw = new b(this.mActivity);
        this.bK = (ListView) inflate.findViewById(16908298);
        this.bK.setAdapter(this.Gw);
        this.bK.setOnItemClickListener(new dk(this));
        this.bK.setOnScrollListener(this.GE);
        this.hh = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.hh.a(false, false, (e) this);
        this.hh.t(R.string.nearby_no_yellow_page);
        u();
        return inflate;
    }

    private void u() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.GB = arguments.getString(MiniDefine.p, ConfigConstant.WIRELESS_FILENAME);
            if (arguments.containsKey("hot_cat_id")) {
                this.GC = arguments.getString("hot_cat_id");
                return;
            }
            this.Gz = arguments.getString("com.miui.yellowpage.extra.yid", null);
            this.GA = arguments.getString("yellowpage_branch_group_id");
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.Gv = new P();
        hC();
    }

    private void hC() {
        this.hh.onStartLoading(false);
        ThreadPool.execute(new de(this));
    }

    private void hD() {
        GeoLocationManager instance = GeoLocationManager.getInstance(this.mActivity.getApplicationContext());
        this.Gx = instance.getLocation();
        City city = instance.getCity();
        if (city != null) {
            this.Gy = city.getLocId();
        }
    }

    private BaseRequest hE() {
        HttpRequest httpRequest;
        if (TextUtils.isEmpty(this.GC)) {
            httpRequest = new HttpRequest(this.mActivity, HostManager.getNearbyYellowPageUrl(), 0);
            httpRequest.addParam("groupid", this.GA);
            httpRequest.addParam("sid", this.Gz);
        } else {
            httpRequest = new HttpRequest(this.mActivity, HostManager.getNavigationSearchUrl(), 0);
            httpRequest.addParam("hotcatid", this.GC);
        }
        httpRequest.setRequestCache(false);
        if (this.Gx != null) {
            httpRequest.addParam("longitude", String.valueOf(this.Gx.getLongitude()));
            httpRequest.addParam("latitude", String.valueOf(this.Gx.getLatitude()));
            httpRequest.addParam("locpackage", this.Gx.getProvider());
        }
        if (!TextUtils.isEmpty(this.Gy)) {
            httpRequest.addParam("locid", this.Gy);
        }
        return httpRequest;
    }

    protected void onNetworkConnected() {
        hC();
    }

    public void reload() {
        hC();
    }
}
