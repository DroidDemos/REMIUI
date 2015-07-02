package com.miui.yellowpage.ui;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.activity.ExpressAddressEditorActivity.EditType;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.model.ExpressAddress.Type;
import com.miui.yellowpage.model.ExpressAddress.Validator;
import com.miui.yellowpage.model.ExpressAddress.Validator.Result;
import com.miui.yellowpage.model.ExpressAddress.Validator.Result.State;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public class ExpressAddressEditorFragment extends dO {
    private Type bH;
    private EditText ha;
    private Button hb;
    private EditText hc;
    private TextView hd;
    private EditText he;
    private Button hf;
    private CheckBox hg;
    private LoadingProgressView hh;
    private EditType hi;
    private ExpressAddress hj;
    private ExpressAddress hk;
    private d hl;
    private aX hm;
    private String hn;

    enum AddressOperation {
        ADD,
        DELETE,
        UPDATE
    }

    class EditResult extends BaseResult {
        State Ei;

        enum State {
            SUCCEEDED,
            FAILED
        }

        private EditResult() {
        }

        public boolean hasData() {
            return true;
        }

        public BaseResult shallowClone() {
            BaseResult editResult = new EditResult();
            editResult.Ei = this.Ei;
            return editResult;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.express_address_editor_fragment, viewGroup, false);
        Bundle arguments = getArguments();
        this.ha = (EditText) inflate.findViewById(R.id.name);
        this.hb = (Button) inflate.findViewById(R.id.pick_name);
        this.hc = (EditText) inflate.findViewById(R.id.phone);
        this.he = (EditText) inflate.findViewById(R.id.detailed_address);
        this.hd = (TextView) inflate.findViewById(R.id.area);
        this.hf = (Button) inflate.findViewById(R.id.delete);
        this.hg = (CheckBox) inflate.findViewById(R.id.save_to_contact);
        this.hh = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.hh.setVisibility(8);
        this.KC = new aQ(this);
        this.hm = new aX();
        this.hl = new d();
        this.hl.a(this.hh);
        this.hl.a(this.hm);
        this.hj = (ExpressAddress) arguments.getParcelable("extra_address");
        this.hi = (EditType) arguments.getSerializable("extra_edit_type");
        this.bH = (Type) arguments.getSerializable("extra_address_type");
        this.ha.setText(this.hj.getName());
        this.hc.setText(this.hj.getPhone());
        this.he.setText(this.hj.eQ());
        this.hd.setText(this.hj.eV());
        this.hd.setOnClickListener(new cC(this));
        if (this.hi == EditType.NEW) {
            this.hf.setVisibility(8);
        } else {
            this.hf.setOnClickListener(new cB(this));
        }
        switch (cy.DW[this.bH.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.ha.setHint(R.string.express_sender_name);
                if (TextUtils.isEmpty(this.hd.getText().toString())) {
                    this.hd.setText(R.string.express_sender_region);
                    break;
                }
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                this.ha.setHint(R.string.express_addressee_name);
                if (TextUtils.isEmpty(this.hd.getText().toString())) {
                    this.hd.setText(R.string.express_addressee_region);
                    break;
                }
                break;
            case WindowData.d /*3*/:
                this.ha.setHint(R.string.express_address_name);
                if (TextUtils.isEmpty(this.hd.getText().toString())) {
                    this.hd.setText(R.string.express_address_region);
                    break;
                }
                break;
        }
        this.hk = this.hj;
        this.hb.setOnClickListener(new au(this));
        return inflate;
    }

    private void ax() {
        ExpressAddress a = ExpressAddress.a(this.hj);
        a.setCountryName(this.hk.getCountryName());
        a.setAdminArea(this.hk.getAdminArea());
        a.bf(this.hk.eR());
        a.setSubAdminArea(this.hk.getSubAdminArea());
        a.bg(this.hk.eS());
        a.setLocality(this.hk.getLocality());
        a.bh(this.hk.eT());
        a.setSubLocality(this.hk.getSubLocality());
        a.bi(this.hk.eU());
        a.setName(this.ha.getText().toString());
        a.setPhone(this.hc.getText().toString());
        a.be(this.he.getText().toString());
        a.setPostalCode(this.hk.getPostalCode());
        this.hj = a;
    }

    public boolean ay() {
        ax();
        Result f = Validator.f(this.hj);
        if (f.hf() != State.FAILED) {
            return true;
        }
        Toast.makeText(this.mActivity, f.hg(), 1).show();
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != 0) {
            switch (i) {
                case TransactionXMLFile.MODE_PRIVATE /*0*/:
                    this.hk = ExpressAddress.a((Address) intent.getExtras().getParcelable("extra_address"));
                    this.hd.setText(this.hk.eV());
                    this.he.setText(this.hk.bj(ConfigConstant.WIRELESS_FILENAME));
                    this.he.requestFocus();
                    Object obj = this.he.getText().toString();
                    if (!TextUtils.isEmpty(obj)) {
                        this.he.setSelection(obj.length());
                        return;
                    }
                    return;
                case 103:
                    this.hg.setVisibility(0);
                    k(intent);
                    return;
                default:
                    return;
            }
        }
    }

    public void az() {
        if (this.hj != null && !TextUtils.isEmpty(this.hj.getId())) {
            Builder builder = new Builder(this.mActivity);
            builder.setCancelable(true).setTitle(R.string.express_address_delete_warning).setNegativeButton(17039360, null).setPositiveButton(17039370, new bT());
            builder.create().show();
        }
    }

    public void aA() {
        switch (cy.DX[this.hi.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.hl.a(a(AddressOperation.ADD), new EditResult());
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                this.hl.a(a(AddressOperation.UPDATE), new EditResult());
                return;
            default:
                return;
        }
    }

    public void onResume() {
        super.onResume();
        switch (cy.DW[this.bH.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.mActivity.setTitle(R.string.express_sender_info);
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                this.mActivity.setTitle(R.string.express_addressee_info);
                return;
            case WindowData.d /*3*/:
                this.mActivity.setTitle(R.string.express_address_info);
                return;
            default:
                return;
        }
    }

    protected void a(dd ddVar) {
        this.ha.setText(ddVar.name);
        this.he.setText(ddVar.address);
        this.hc.setText(ddVar.Hb);
    }

    protected void a(Cursor cursor) {
        this.hn = cursor.getString(3);
    }

    private BaseRequest a(AddressOperation addressOperation) {
        ax();
        HttpRequest httpRequest = null;
        switch (cy.DY[addressOperation.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                httpRequest = new HttpRequest(this.mActivity, HostManager.getAddressAddUrl(), 0);
                httpRequest.setRequestMethod("POST");
                httpRequest.addParam("address", this.hj.eQ());
                httpRequest.addParam("admin_area_id", this.hj.eR());
                httpRequest.addParam("locality_id", this.hj.eT());
                httpRequest.addParam("sub_locality_id", this.hj.eU());
                httpRequest.addParam("owner", this.hj.getName());
                httpRequest.addParam("postal_code", this.hj.getPostalCode());
                httpRequest.addParam("phone", this.hj.getPhone());
                httpRequest.setRequestMethod("POST");
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                httpRequest = new HttpRequest(this.mActivity, HostManager.getAddressDeleteUrl(), 2);
                httpRequest.addParam("address_id", this.hj.getId());
                break;
            case WindowData.d /*3*/:
                httpRequest = new HttpRequest(this.mActivity, HostManager.getAddressUpdateUrl(), 1);
                httpRequest.setRequestMethod("POST");
                httpRequest.addParam("address_id", this.hj.getId());
                httpRequest.addParam("address", this.hj.eQ());
                httpRequest.addParam("admin_area_id", this.hj.eR());
                httpRequest.addParam("locality_id", this.hj.eT());
                httpRequest.addParam("sub_locality_id", this.hj.eU());
                httpRequest.addParam("owner", this.hj.getName());
                httpRequest.addParam("postal_code", this.hj.getPostalCode());
                httpRequest.addParam("phone", this.hj.getPhone());
                httpRequest.setRequestMethod("POST");
                break;
        }
        if (httpRequest != null) {
            httpRequest.setRequireLogin(true);
            httpRequest.setDecryptData(false);
        }
        return httpRequest;
    }
}
