package com.miui.yellowpage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.w;
import com.miui.yellowpage.widget.AnimatedImageView;
import com.miui.yellowpage.widget.LoadingProgressView;
import miui.yellowpage.YellowPageContract.Location;

/* compiled from: CityPickerFragment */
public class bD extends dv {
    private boolean yA;
    private LoadingProgressView yB;
    private LongSparseArray yC;
    private cH yp;
    private cD yq;
    private p yr;
    private d ys;
    private boolean yt;
    private View yu;
    private CityPickerLocatedCityItem yv;
    private TextView yw;
    private String yx;
    private String yy;
    private AnimatedImageView yz;

    public bD() {
        this.yC = new LongSparseArray();
    }

    protected void a(View view, Bundle bundle) {
        if (this.yA) {
            super.a(view, bundle);
        }
    }

    private void a(bC bCVar) {
        this.yw.setText(bCVar.ym);
        this.yx = bCVar.ym;
        this.yy = bCVar.yn;
        this.yv.setSelectable(true);
    }

    private void onError() {
        this.yw.setText(R.string.navigation_search_positioning_failed);
    }

    protected void onNetworkConnected() {
        super.onNetworkConnected();
        if (this.yr != null) {
            this.yr.reload();
        }
        if (this.ys != null) {
            this.ys.a(fY(), new bC());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.yv = (CityPickerLocatedCityItem) layoutInflater.inflate(R.layout.city_picker_currently_located_city, this.bK, false);
        this.yv.setSelectable(false);
        this.yw = (TextView) this.yv.findViewById(R.id.city_name);
        this.yu = this.yv.findViewById(R.id.content);
        this.yu.setOnClickListener(new r(this));
        this.yz = (AnimatedImageView) this.yv.findViewById(R.id.progress);
        this.yB = (LoadingProgressView) onCreateView.findViewById(R.id.loading_view);
        this.yB.t(R.string.navigation_search_no_city_list);
        this.yp = new cH();
        getLoaderManager().initLoader(0, null, this.yp);
        this.ys = new d();
        this.yq = new cD();
        this.ys.a(this.yq);
        this.ys.a(fY(), new bC());
        return onCreateView;
    }

    private String bw(String str) {
        try {
            return (String) this.yC.get(Long.parseLong(str));
        } catch (Exception e) {
            return null;
        }
    }

    protected void k(View view) {
        Log.d("CityPickerFragment", "setupListView");
        if (!this.yt) {
            this.bK.addHeaderView(this.yv, null, false);
            this.yt = true;
        }
        super.k(view);
    }

    protected w N(int i) {
        return super.N(i - 1);
    }

    private BaseRequest fX() {
        return new HttpRequest(getActivity(), HostManager.getCityListUrl(), 0);
    }

    private BaseRequest fY() {
        LocalRequest localRequest = new LocalRequest(getActivity(), 1);
        localRequest.setUri(Location.CONTENT_URI);
        return localRequest;
    }

    private void u(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Intent intent = new Intent();
            intent.putExtra("result_backend_data", str2);
            intent.putExtra("result_presentation", str);
            this.mActivity.setResult(-1, intent);
            this.mActivity.finish();
        }
    }

    protected OnItemClickListener fZ() {
        return new q(this);
    }
}
