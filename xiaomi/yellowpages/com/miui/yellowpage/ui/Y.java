package com.miui.yellowpage.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.YellowPageActivity.ViewType;
import com.miui.yellowpage.b.r;
import com.miui.yellowpage.base.mipub.MiPubDeviceManager;
import com.miui.yellowpage.base.model.Coupon;
import com.miui.yellowpage.base.model.Coupon.More;
import com.miui.yellowpage.base.model.Review.Detail;
import com.miui.yellowpage.base.model.Review.Summary;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.model.YellowPageDataEntry;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;
import com.miui.yellowpage.base.reference.RefMethods.Telephony;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.request.HttpRequest.HttpRequestStatusListener;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.MultiModule;
import com.miui.yellowpage.base.utils.Permission;
import com.miui.yellowpage.base.utils.YellowPageHandler;
import com.miui.yellowpage.model.YellowPageDataDetailEntry;
import com.miui.yellowpage.model.YellowPageDataDetailEntry.Type;
import com.miui.yellowpage.utils.D;
import com.miui.yellowpage.utils.o;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.ArrayList;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: YellowPageFragment */
public class Y extends cs implements OnItemClickListener, HttpRequestStatusListener {
    private ListView bK;
    private cQ lb;
    private cN lc;
    private long ld;
    private String le;
    private String lf;
    private YellowPagePhone lg;
    private YellowPage lh;
    private String li;
    private ViewType lj;
    private r lk;
    private boolean ll;
    private MiPubDeviceManager lm;
    private YellowPageModuleEntry ln;
    private LoadingProgressView mLoadingProgressView;

    public void a(cQ cQVar) {
        this.lb = cQVar;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.ld = arguments.getLong("com.miui.yellowpage.extra.yid");
            this.le = arguments.getString("com.miui.yellowpage.extra.number");
            this.lf = arguments.getString("mi_id");
            this.lj = (ViewType) arguments.getSerializable("yp_view_type");
            this.ll = arguments.getBoolean("yp_internal_jump");
        }
        View inflate = layoutInflater.inflate(R.layout.yellow_page_fragment, viewGroup, false);
        int dimension = (int) this.mActivity.getResources().getDimension(R.dimen.yellow_page_detail_list_bottom_placeholder_height);
        View view = new View(this.mActivity);
        view.setLayoutParams(new LayoutParams(-1, dimension));
        this.bK = (ListView) inflate.findViewById(16908298);
        this.bK.addFooterView(view);
        this.bK.setOnCreateContextMenuListener(this);
        this.bK.setOnItemClickListener(this);
        this.lk = new r(this.mActivity, this);
        this.bK.setAdapter(this.lk);
        this.mLoadingProgressView = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        if (this.ll) {
            this.mLoadingProgressView.setVisibility(8);
        } else {
            this.mLoadingProgressView.a(false, true, null);
        }
        this.lm = MiPubDeviceManager.getInstance(this.mActivity.getApplicationContext());
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.lc = new cN();
        getLoaderManager().initLoader(1, null, this.lc);
    }

    public void reload() {
        getLoaderManager().restartLoader(1, null, this.lc);
    }

    private ArrayList bk() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(j(Permission.networkingAllowed(this.mActivity)));
        return arrayList;
    }

    private BaseRequest j(boolean z) {
        HttpRequest httpRequest = null;
        switch (bx.wx[this.lj.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                httpRequest = new HttpRequest(this.mActivity, HostManager.getYellowDetailUrl(), 0);
                httpRequest.addParam("sid", String.valueOf(this.ld));
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                String normalizedNumber = YellowPageUtils.getNormalizedNumber(this.mActivity, this.le);
                httpRequest = new HttpRequest(this.mActivity, HostManager.getYellowPageQueryUrl(), 0);
                httpRequest.addParam("phone", normalizedNumber);
                httpRequest.addParam("raw_phone", this.le);
                break;
            case WindowData.d /*3*/:
                httpRequest = new HttpRequest(this.mActivity, HostManager.getYellowPageByMiIdUrl(), 0);
                httpRequest.addParam("miid", this.lf);
                break;
        }
        if (httpRequest != null) {
            httpRequest.setStatusListener(this);
            httpRequest.setRequestServer(z);
            httpRequest.setRequestYellowPage(true);
        }
        return httpRequest;
    }

    public void onRequestRemoteYellowPageFinished(String str) {
        try {
            YellowPage fromJson;
            if (this.lj == ViewType.MIID || this.lj == ViewType.NUMBER) {
                fromJson = YellowPage.fromJson(new JSONObject(str).getString("yp"));
            } else {
                fromJson = YellowPage.fromJson(str);
            }
            if (fromJson == null) {
                YellowPageHandler.deleteYellowPage(getActivity(), this.ld);
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.activities.UnknownContactActivity"));
                intent.putExtra("number", this.le);
                startActivity(intent);
                this.mActivity.finish();
                return;
            }
            a(fromJson.getPhoneInfo(this.mActivity, this.le));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(YellowPagePhone yellowPagePhone) {
        if (yellowPagePhone != null && this.lg != null) {
            if (!TextUtils.equals(yellowPagePhone.getYellowPageName(), this.lg.getYellowPageName()) || !TextUtils.equals(yellowPagePhone.getTag(), this.lg.getTag())) {
                Log.d("YellowPageFragment", "phone info changed, rebuit t9 and change antispam tag");
                YellowPageUtils.updateYellowPageT9Index(this.mActivity, this.le);
            }
        }
    }

    public ListView getListView() {
        return this.bK;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) contextMenuInfo;
        if (adapterContextMenuInfo.position >= this.lk.getCount()) {
            Log.v("YellowPageFragment", "footer views");
            return;
        }
        YellowPageDataEntry yellowPageDataEntry = (YellowPageDataEntry) this.lk.getItem(adapterContextMenuInfo.position);
        if (yellowPageDataEntry instanceof YellowPageDataDetailEntry) {
            YellowPageDataDetailEntry yellowPageDataDetailEntry = (YellowPageDataDetailEntry) yellowPageDataEntry;
            contextMenu.setHeaderTitle(yellowPageDataDetailEntry.getData());
            contextMenu.add(0, 0, 0, getString(R.string.menu_copy_text));
            if (yellowPageDataDetailEntry.du() == Type.PHONE) {
                contextMenu.add(0, 1, 0, getString(R.string.menu_call_ip_number));
                if (Telephony.isInBlacklist(this.mActivity, yellowPageDataDetailEntry.getData())) {
                    contextMenu.add(0, 4, 0, getString(R.string.menu_remove_black_list));
                } else {
                    contextMenu.add(0, 3, 0, getString(R.string.menu_add_black_list));
                }
            }
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        try {
            AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) menuItem.getMenuInfo();
            YellowPageDataEntry yellowPageDataEntry = (YellowPageDataEntry) this.lk.getItem(adapterContextMenuInfo.position);
            switch (menuItem.getItemId()) {
                case TransactionXMLFile.MODE_PRIVATE /*0*/:
                    p(adapterContextMenuInfo.position);
                    return true;
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    q(adapterContextMenuInfo.position);
                    return true;
                case WindowData.d /*3*/:
                    D.z(this.mActivity, ((YellowPageDataDetailEntry) yellowPageDataEntry).getData());
                    return true;
                case Base64.CRLF /*4*/:
                    D.A(this.mActivity, ((YellowPageDataDetailEntry) yellowPageDataEntry).getData());
                    return true;
                default:
                    return false;
            }
        } catch (Throwable e) {
            Log.e("YellowPageFragment", "bad menuInfo", e);
            return false;
        }
    }

    private void p(int i) {
        YellowPageDataDetailEntry yellowPageDataDetailEntry = (YellowPageDataDetailEntry) this.lk.getItem(i);
        CharSequence data = yellowPageDataDetailEntry.getData();
        if (!TextUtils.isEmpty(data)) {
            o.a(this.mActivity, data.toString(), yellowPageDataDetailEntry.du());
        }
    }

    private void q(int i) {
        CharSequence data = ((YellowPageDataDetailEntry) this.lk.getItem(i)).getData();
        if (!TextUtils.isEmpty(data)) {
            o.a(this.mActivity, data, true);
        }
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        if (i >= this.lk.getCount()) {
            Log.v("YellowPageFragment", "footer views");
            return;
        }
        YellowPageDataEntry yellowPageDataEntry = (YellowPageDataEntry) this.lk.getItem(i);
        String valueOf = String.valueOf(this.ld);
        if (yellowPageDataEntry instanceof YellowPageDataDetailEntry) {
            YellowPageDataDetailEntry yellowPageDataDetailEntry = (YellowPageDataDetailEntry) yellowPageDataEntry;
            if (yellowPageDataDetailEntry.ds() != null) {
                this.mActivity.startActivity(yellowPageDataDetailEntry.ds());
            }
            BusinessStatistics.clickYellowPageItem(this.mActivity, valueOf, yellowPageDataDetailEntry.du().ar(), yellowPageDataDetailEntry.getData(), getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
        } else if (yellowPageDataEntry instanceof YellowPageModuleEntry) {
            this.ln = (YellowPageModuleEntry) yellowPageDataEntry;
            MultiModule.setHit(this.mActivity, this.ld, this.ln.getModuleTplId(), this.ln.getModuleId());
            this.ln.setIsNew(false);
            this.lk.getView(i, view, adapterView);
            Intent intent = new Intent("com.miui.yellowpage.action.MULTI_MODULE_CLICKED");
            intent.putExtra("com.miui.yellowpage.extra.MULTI_MODULE_ENTRY", this.ln);
            getStatisticsContext().attach(intent);
            intent.putExtra("com.miui.yellowpage.extra.yid", this.ld);
            this.mActivity.startActivity(intent);
        } else if (yellowPageDataEntry instanceof Detail) {
            Detail detail = (Detail) yellowPageDataEntry;
            this.mActivity.startActivity(detail.getIntent());
            BusinessStatistics.clickReviewDetail(this.mActivity, valueOf, detail, getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
        } else if (yellowPageDataEntry instanceof Summary) {
            this.mActivity.startActivity(((Summary) yellowPageDataEntry).getIntent());
            BusinessStatistics.clickReviewSummary(this.mActivity, valueOf, getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
        } else if (yellowPageDataEntry instanceof Coupon.Detail) {
            Coupon.Detail detail2 = (Coupon.Detail) yellowPageDataEntry;
            this.mActivity.startActivity(detail2.getIntent());
            BusinessStatistics.clickCoupon(this.mActivity, valueOf, detail2.getTitle(), getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
        } else if (yellowPageDataEntry instanceof More) {
            this.mActivity.startActivity(((More) yellowPageDataEntry).getIntent());
            BusinessStatistics.clickMoreCoupon(this.mActivity, valueOf, getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
        }
    }

    public YellowPage bl() {
        return this.lh;
    }

    public String bm() {
        return this.le;
    }
}
