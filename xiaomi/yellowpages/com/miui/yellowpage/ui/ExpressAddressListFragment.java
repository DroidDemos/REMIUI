package com.miui.yellowpage.ui;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.b.t;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.model.ExpressAddress.Type;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.Serializable;
import java.util.ArrayList;

public class ExpressAddressListFragment extends cs {
    private Type bH;
    private ExpressAddress bI;
    private ActionType bJ;
    private ListView bK;
    private t bL;
    private ExpressAddressListItem bM;
    private ArrayList bN;
    private aZ bO;
    private View mEmptyView;
    private p mLoader;
    private LoadingProgressView mLoadingProgressView;
    private View mRootView;

    public enum ActionType {
        MANAGE,
        PICK
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        if ("com.miui.yellowpage.action.MANAGE_ADDRESS".equals(arguments.getString(MiniDefine.i))) {
            this.bJ = ActionType.MANAGE;
        } else {
            this.bJ = ActionType.PICK;
        }
        this.bL.a(this.bJ);
        Serializable serializable = arguments.getSerializable("extra_address_type");
        if (serializable == null) {
            this.bH = Type.ANY;
        } else {
            this.bH = (Type) serializable;
        }
        Parcelable parcelable = arguments.getParcelable("extra_address");
        if (parcelable != null) {
            this.bI = ExpressAddress.a((Address) parcelable);
        } else {
            this.bI = new ExpressAddress();
        }
        this.bL.i(this.bI);
        this.bO = new aZ();
        getLoaderManager().initLoader(0, null, this.bO);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.express_address_list_fragment, viewGroup, false);
        this.mRootView = inflate.findViewById(R.id.root);
        this.mLoadingProgressView = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.bK = (ListView) inflate.findViewById(R.id.list);
        this.mEmptyView = inflate.findViewById(16908292);
        this.bK.setEmptyView(this.mEmptyView);
        this.bL = new t(this.mActivity);
        this.bL.a(new m(this));
        this.bL.a(new dK(this));
        this.bK.setAdapter(this.bL);
        return inflate;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.address_list_fragment, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.new_address:
                p();
                break;
        }
        return true;
    }

    private void p() {
        Intent intent = new Intent("com.miui.yellowpage.action.NEW_ADDRESS");
        intent.putExtra("extra_address_type", this.bH);
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                if (i2 == -1 && intent != null) {
                    if (this.bJ == ActionType.MANAGE) {
                        a(intent);
                        return;
                    } else {
                        a(ExpressAddress.a((Address) intent.getParcelableExtra("extra_address")));
                        return;
                    }
                }
                return;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                if (i2 == -1 && intent != null) {
                    a(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a(Intent intent) {
        ExpressAddress a = ExpressAddress.a((Address) intent.getExtras().getParcelable("extra_address"));
        if (this.bN != null) {
            switch (a.getStatus()) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    this.bN.add(a);
                    reload();
                    break;
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    this.bN.set(this.bN.indexOf(a), a);
                    reload();
                    break;
                case WindowData.d /*3*/:
                    this.bN.remove(a);
                    reload();
                    break;
            }
            this.bL.updateData(this.bN);
        }
    }

    private void reload() {
        this.mRootView.setVisibility(8);
        getLoaderManager().restartLoader(0, null, this.bO);
    }

    private void a(ExpressAddress expressAddress) {
        if (expressAddress != null) {
            Intent intent = new Intent();
            intent.putExtra("extra_address", expressAddress);
            this.mActivity.setResult(-1, intent);
            this.mActivity.finish();
            return;
        }
        this.mActivity.setResult(0);
        this.mActivity.finish();
    }

    private void b(ExpressAddress expressAddress) {
        if (expressAddress != null) {
            Intent intent = new Intent("com.miui.yellowpage.action.EDIT_ADDRESS");
            intent.putExtra("extra_address", expressAddress);
            intent.putExtra("extra_address_type", this.bH);
            startActivityForResult(intent, 1);
        }
    }

    private BaseRequest q() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getAddressListUrl(), 2);
        httpRequest.setRequireLogin(true);
        httpRequest.setRequestCache(false);
        return httpRequest;
    }

    public void onResume() {
        super.onResume();
        switch (dL.Kh[this.bJ.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.mActivity.setTitle(R.string.account_address);
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                switch (dL.DW[this.bH.ordinal()]) {
                    case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                        this.mActivity.setTitle(R.string.express_select_sender);
                        return;
                    case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                        this.mActivity.setTitle(R.string.express_select_addressee);
                        return;
                    case WindowData.d /*3*/:
                        this.mActivity.setTitle(R.string.express_select_address);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }
}
