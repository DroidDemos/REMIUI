package com.miui.yellowpage.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.activity.LoginActivity;
import com.miui.yellowpage.activity.YellowPagePickerActivity;
import com.miui.yellowpage.base.mipub.MiPubDeviceManager;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.XiaomiAccount;
import com.miui.yellowpage.model.a;
import com.miui.yellowpage.model.g;
import com.miui.yellowpage.model.l;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;
import miui.yellowpage.YellowPageContract.Cache;

public class ExpressInquiryProgressFragment extends dF {
    private TextView bR;
    private TextView cG;
    private View df;
    private String dg;
    private String dh;
    private String iS;
    private String iT;
    private Bundle mArgs;
    private ListView mList;
    private LoadingProgressView mLoadingProgressView;
    private TextView mTitle;
    private int sA;
    private boolean sB;
    private boolean sC;
    private boolean sD;
    private MenuItem sE;
    private MenuItem sF;
    private Toast sG;
    private Toast sH;
    private TextView sI;
    private l sJ;
    private AlertDialog sK;
    private InquiryType sL;
    private g sM;
    private e sN;
    private Intent sO;
    private com.miui.yellowpage.b.g sq;
    private cq sr;
    private di ss;
    private aD st;
    private dX su;
    private p sv;
    private LinearLayout sw;
    private View sx;
    private LinearLayout sy;
    private String sz;

    enum InquiryType {
        GENERAL,
        RECENT_HISTORY,
        BIZCODE_SPECIFIED,
        LOGISTICS_SPECIFIED,
        INQUIRY_SPECIFIED,
        INQUIRY_ATTACH;

        boolean W() {
            return this == LOGISTICS_SPECIFIED || this == INQUIRY_SPECIFIED || this == INQUIRY_ATTACH;
        }

        boolean X() {
            return (this == GENERAL || this == RECENT_HISTORY) ? false : true;
        }
    }

    public void onResume() {
        super.onResume();
        this.JP.k(false);
        if (TextUtils.isEmpty(this.dh)) {
            this.JP.setTitle((int) R.string.express_inquiry);
        } else {
            this.JP.setTitle(this.dh);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.express_progress_fragment, viewGroup, false);
        this.mList = (ListView) inflate.findViewById(R.id.progress_list);
        this.mTitle = (TextView) inflate.findViewById(R.id.title);
        this.bR = (TextView) inflate.findViewById(R.id.description);
        this.cG = (TextView) inflate.findViewById(R.id.state);
        this.mLoadingProgressView = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.sy = (LinearLayout) inflate.findViewById(R.id.root_view);
        this.sw = (LinearLayout) layoutInflater.inflate(R.layout.express_progress_list_footer, this.mList, false);
        this.sx = layoutInflater.inflate(R.layout.express_provider_info_container, this.sw, false);
        this.df = this.sx.findViewById(R.id.source_info_container);
        this.sI = (TextView) inflate.findViewById(R.id.progress_err_result);
        this.mArgs = getArguments();
        if (this.mArgs == null) {
            this.mArgs = new Bundle();
        }
        dH();
        if (this.sL == InquiryType.GENERAL) {
            if (TextUtils.isEmpty(this.dg)) {
                this.sL = InquiryType.GENERAL;
            } else if (TextUtils.isEmpty(this.dh)) {
                this.sL = InquiryType.BIZCODE_SPECIFIED;
            } else {
                this.sL = InquiryType.LOGISTICS_SPECIFIED;
            }
        }
        if (this.sL.W()) {
            dG();
        } else {
            if (this.su == null) {
                this.su = new dX();
            }
            if (getLoaderManager().getLoader(3) == null) {
                getLoaderManager().initLoader(3, null, new dX());
            }
        }
        this.sq = new com.miui.yellowpage.b.g(this.JP);
        this.mList.addFooterView(this.sw, null, false);
        this.mList.setAdapter(this.sq);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.st = new aD(this, this.JP);
        this.st.a(new dm(this, this.JP, this.df, null));
        getLoaderManager().initLoader(2, null, this.st);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.inquiry_history_detail_edit_menu, menu);
        this.sE = menu.findItem(R.id.edit_logistics);
        this.sF = menu.findItem(R.id.trace);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.remark:
                cz();
                return true;
            case R.id.trace:
                dK();
                return true;
            case R.id.edit_logistics:
                startActivityForResult(this.sO, 4);
                return true;
            default:
                return false;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        String stringExtra;
        if (i == 3) {
            if (i2 == -1 && intent != null) {
                stringExtra = intent.getStringExtra("order_remark");
                if (stringExtra != null) {
                    this.iT = stringExtra;
                    dI();
                }
            }
        } else if (i == 4) {
            if (i2 == -1 && intent != null) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    Object string = extras.getString("result_backend_data");
                    stringExtra = extras.getString("result_presentation");
                    Log.d("ExpressProgressFragment", "bizcode is " + string);
                    if (!TextUtils.isEmpty(string)) {
                        n(string, stringExtra);
                    }
                }
            }
        } else if (i == 10) {
            if (i2 == -1) {
                this.mLoader.reload();
            }
        } else if (i == 11 && i2 == -1) {
            dK();
        }
    }

    public void a(e eVar) {
        this.sN = eVar;
    }

    private void dG() {
        this.sr = new cq();
        getLoaderManager().initLoader(1, null, this.sr);
    }

    private void n(String str, String str2) {
        this.dg = str;
        this.dh = str2;
        this.mLoader.a(cy());
        this.sI.setVisibility(4);
        this.sy.setVisibility(4);
        setHasOptionsMenu(false);
        if (this.sM != null) {
            b(this.sM);
        }
        getLoaderManager().restartLoader(1, null, this.sr);
    }

    private void dH() {
        CharSequence string = this.mArgs.getString("inquiry_type");
        if (TextUtils.equals(string, "inquiry_last")) {
            this.sL = InquiryType.RECENT_HISTORY;
        } else if (TextUtils.equals(string, "inquiry_specified")) {
            this.sL = InquiryType.INQUIRY_SPECIFIED;
        } else if (TextUtils.equals(string, "inquiry_attach")) {
            this.sL = InquiryType.INQUIRY_ATTACH;
            this.sz = this.mArgs.getString("internalId");
        } else {
            this.sL = InquiryType.GENERAL;
        }
        this.iS = this.mArgs.getString("order");
        this.dg = this.mArgs.getString("bizCode");
        this.dh = this.mArgs.getString("logistics_name");
    }

    private void dI() {
        dJ();
        CharSequence charSequence = getString(R.string.express_inquiry_serial_number_prefix) + this.iS;
        if (TextUtils.isEmpty(this.iT)) {
            this.mTitle.setText(charSequence);
            this.bR.setVisibility(8);
            return;
        }
        this.bR.setText(charSequence);
        this.mTitle.setText(this.iT);
        this.bR.setVisibility(0);
    }

    private void dJ() {
        this.iT = null;
        if (this.sM != null) {
            Cursor query = this.JP.getContentResolver().query(Cache.CONTENT_URI, new String[]{"remark"}, "cache_key = ?", new String[]{this.sM.getKey()}, null);
            if (query != null && query.moveToFirst()) {
                this.iT = query.getString(0);
            }
        }
    }

    private void cz() {
        if (this.sM != null) {
            Intent intent = new Intent("com.miui.yellowpage.action.REMARK");
            intent.putExtra("android.intent.extra.TITLE", this.dh + " " + this.iS);
            intent.putExtra("order_key", this.sM.getKey());
            intent.putExtra("order_remark", this.iT);
            startActivityForResult(intent, 3);
        }
    }

    private void dK() {
        if (!XiaomiAccount.hasLogin(this.JP)) {
            a("account", null, 10);
        } else if (!this.sC && !isDeviceLogin()) {
            a("device", getString(R.string.express_inquiry_trace_device_login_notice), 11);
        } else if (this.sC || !Preference.getBoolean(this.JP, "pref_express_show_trace_notice", true)) {
            dL();
        } else {
            new Builder(this.JP).setTitle(R.string.express_inquiry_trace).setMessage(R.string.express_inquiry_trace_user_notice_content).setPositiveButton(17039370, new bG(this)).setNegativeButton(17039360, null).show();
        }
    }

    private boolean isDeviceLogin() {
        return MiPubDeviceManager.getInstance(this.JP).isDeviceLogin();
    }

    private void a(String str, String str2, int i) {
        Intent intent = new Intent(this.JP, LoginActivity.class);
        intent.putExtra("service_token_id", "spbook");
        intent.putExtra("com.miui.yellowpage.extra.LOGIN_TYPE", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("com.miui.yellowpage.extra.LOGIN_MESSAGE", str2);
        }
        if (i == -1) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, i);
        }
    }

    private void dL() {
        if (this.mRequestLoader == null) {
            dM();
        }
        this.mRequestLoader.a(new at());
        this.mRequestLoader.a(dT(), new bF());
    }

    private void dM() {
        this.mRequestLoader = new d();
        this.mRequestLoader.a(this.mLoadingProgressView);
    }

    private void t(boolean z) {
        this.sC = z;
        this.sF.setChecked(z);
        if (z) {
            this.sF.setIcon(R.drawable.action_button_untrace);
            this.sF.setTitle(R.string.express_inquiry_untrace);
            if (!this.sD && !isDeviceLogin()) {
                this.sD = true;
                a("device", getString(R.string.express_inquiry_trace_device_login_notice), -1);
                return;
            }
            return;
        }
        this.sF.setIcon(R.drawable.action_button_trace);
        this.sF.setTitle(R.string.express_inquiry_trace);
    }

    private void dN() {
        boolean z = true;
        setHasOptionsMenu(true);
        if (!this.sL.X()) {
            this.sE.setVisible(true);
        }
        if (this.sM.aR()) {
            this.sF.setVisible(false);
            return;
        }
        this.sF.setVisible(true);
        if (this.ss.HY != 1) {
            z = false;
        }
        t(z);
    }

    private void dO() {
        this.sy.setVisibility(0);
        dI();
        dN();
        switch (this.ss.mStatus) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                this.mList.setVisibility(4);
                this.cG.setVisibility(4);
                this.sI.setVisibility(0);
                this.sI.setText(R.string.express_inquiry_no_result);
                return;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.cG.setText(this.ss.HW);
                this.cG.setVisibility(0);
                this.sq.updateData(a.a(this.ss.HX));
                this.mList.setVisibility(0);
                this.sI.setVisibility(4);
                if (dW()) {
                    dQ();
                    return;
                }
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                this.mList.setVisibility(4);
                this.cG.setVisibility(4);
                this.sI.setVisibility(0);
                this.sI.setText(R.string.express_inquiry_internal_failure);
                return;
            default:
                return;
        }
    }

    private BaseRequest cy() {
        BaseRequest baseRequest = null;
        if (!TextUtils.isEmpty(this.iS)) {
            baseRequest = new HttpRequest(this.JP, HostManager.getExpressQueryUrl(), 1);
            baseRequest.addParam("order", this.iS);
            baseRequest.setRequireLogin(true);
            if (TextUtils.isEmpty(this.dg) || this.sL == InquiryType.INQUIRY_ATTACH) {
                baseRequest.setRequestCache(false);
            }
            if (!TextUtils.isEmpty(this.dg)) {
                baseRequest.addParam("bizcode", this.dg);
            }
        }
        return baseRequest;
    }

    private BaseRequest dP() {
        if (TextUtils.isEmpty(this.iS) || TextUtils.isEmpty(this.sz)) {
            return null;
        }
        HttpRequest httpRequest = new HttpRequest(this.JP, HostManager.getExpressAttachUrl(), 0);
        httpRequest.setRequestMethod("POST");
        httpRequest.setRequireLogin(true);
        httpRequest.setRequestCache(false);
        httpRequest.addParam("order", this.iS);
        httpRequest.addParam("internalId", this.sz);
        return httpRequest;
    }

    private void dQ() {
        if (this.mRequestLoader == null) {
            dM();
        }
        this.mRequestLoader.a(new bv());
        this.mRequestLoader.a(dP(), new an());
    }

    private void dR() {
        if (this.sM != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cache_key", this.sM.getKey());
            contentValues.put(MiniDefine.at, this.sM.getContent());
            Cursor query = this.JP.getContentResolver().query(Cache.CONTENT_URI, null, "cache_key=?", new String[]{this.sM.getKey()}, null);
            if (query != null) {
                try {
                    if (query.getCount() <= 0) {
                        contentValues.put("etag", this.sM.getTag());
                        this.JP.getContentResolver().insert(Cache.CONTENT_URI, contentValues);
                    } else {
                        this.JP.getContentResolver().update(Cache.CONTENT_URI, contentValues, "cache_key=?", new String[]{this.sM.getKey()});
                    }
                    query.close();
                } catch (Throwable th) {
                    query.close();
                }
            }
        }
    }

    private void b(g gVar) {
        ThreadPool.execute(new bH(this, gVar));
    }

    private BaseRequest dS() {
        return new HttpRequest(this.JP, HostManager.getExpressQueryCompanyListUrl(), 0);
    }

    private BaseRequest dT() {
        HttpRequest httpRequest = new HttpRequest(this.JP, HostManager.getExpressTraceUrl(), 0);
        httpRequest.setRequireLogin(true);
        httpRequest.setRequestCache(false);
        httpRequest.setRequestMethod("POST");
        httpRequest.addParam("bizcode", this.dg);
        httpRequest.addParam("order", this.iS);
        httpRequest.addParam(MiniDefine.b, this.sC ? GlobalConstants.d : Profile.devicever);
        return httpRequest;
    }

    private void dU() {
        this.sO = new Intent(this.JP, YellowPagePickerActivity.class);
        this.sO.putStringArrayListExtra("picker_backend_data", this.sJ.fj());
        this.sO.putStringArrayListExtra("picker_presentation", this.sJ.fk());
        this.sO.putStringArrayListExtra("picker_recommend_presentation", this.sJ.fl());
        this.sO.putStringArrayListExtra("picker_recommend_backend_data", this.sJ.fm());
        this.sO.putExtra("picker_title", getResources().getString(R.string.express_inqiury_select_logistics));
        this.sO.putExtra("picker_index_target", 2);
        this.sO.putExtra("picker_recommend_section_text", getResources().getString(R.string.expresss_inquiry_frequently_used_logistics));
        this.sO.putExtra("picker_recommend_presentation_text", getResources().getString(R.string.expresss_inquiry_all_logistics));
    }

    private String[] d(List list) {
        List arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CharSequence aL = aL((String) it.next());
            if (TextUtils.isEmpty(aL)) {
                it.remove();
            } else {
                arrayList.add(aL);
            }
        }
        if (arrayList.size() > 0) {
            arrayList.add(getString(R.string.express_inquiry_logistics_select_others_indication));
        } else {
            arrayList.add(getString(R.string.express_inquiry_logistics));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private String aL(String str) {
        List fj = this.sJ.fj();
        List fk = this.sJ.fk();
        for (int i = 0; i < fj.size(); i++) {
            if (TextUtils.equals(str, (CharSequence) fj.get(i))) {
                return (String) fk.get(i);
            }
        }
        return null;
    }

    private void e(List list) {
        if (this.sK == null || !this.sK.isShowing()) {
            this.sB = true;
            CharSequence[] d = d(list);
            this.sK = new Builder(this.JP).setSingleChoiceItems(d, 0, new bJ(this, d, list)).setOnDismissListener(new bI(this)).create();
            this.sK.show();
        }
    }

    private void dV() {
        if (this.sL != InquiryType.INQUIRY_SPECIFIED && !TextUtils.isEmpty(this.dg)) {
            Preference.setString(this.JP, "pref_last_express_inquiry", this.iS + ',' + this.dg);
        }
    }

    public boolean dW() {
        return this.sL == InquiryType.INQUIRY_ATTACH;
    }
}
