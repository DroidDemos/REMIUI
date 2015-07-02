package com.miui.yellowpage.ui;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.utils.v;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public class RegionPickerFragment extends cs {
    private ExpressAddress hj;
    private ListView mList;
    private p mLoader;
    private String mRegionId;
    private String mU;
    private cd mV;
    private dP mW;
    private SimpleCursorAdapter mX;

    enum RequestPurpose {
        DISPLAY_CHILDREN,
        CHECK_CHILDREN
    }

    public void onDestroy() {
        super.onDestroy();
        Cursor cursor = this.mX.getCursor();
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.region_picker_fragment, viewGroup, false);
        this.mList = (ListView) inflate.findViewById(R.id.list);
        Bundle arguments = getArguments();
        if (arguments == null) {
            this.mRegionId = GlobalConstants.d;
            this.hj = new ExpressAddress();
        } else {
            this.mRegionId = arguments.getString("extra_region_id");
            this.hj = ExpressAddress.a((Address) arguments.getParcelable("extra_address"));
        }
        this.mX = new SimpleCursorAdapter(this.mActivity, R.layout.express_address_picker_list_item, null, new String[]{MiniDefine.l}, new int[]{R.id.name}, 0);
        this.mList.setAdapter(this.mX);
        this.mList.setOnItemClickListener(new ak());
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mV = new cd();
        Bundle bundle2 = new Bundle();
        bundle2.putString("parent_id", this.mRegionId);
        bundle2.putSerializable("purpose", RequestPurpose.DISPLAY_CHILDREN);
        getLoaderManager().initLoader(0, bundle2, this.mV);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != 0) {
            switch (i) {
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    this.mActivity.setResult(i2, intent);
                    this.mActivity.finish();
                    return;
                default:
                    return;
            }
        }
    }

    private void a(ExpressAddress expressAddress, Cursor cursor) {
        int i = cursor.getInt(2);
        String string = cursor.getString(1);
        String string2 = cursor.getString(0);
        expressAddress.setPostalCode(cursor.getString(4));
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                expressAddress.setCountryName(string);
                return;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                expressAddress.setAdminArea(string);
                expressAddress.bf(string2);
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                expressAddress.setSubAdminArea(string);
                expressAddress.bg(string2);
                return;
            case WindowData.d /*3*/:
                expressAddress.setLocality(string);
                expressAddress.bh(string2);
                return;
            case Base64.CRLF /*4*/:
                expressAddress.setSubLocality(string);
                expressAddress.bi(string2);
                return;
            default:
                return;
        }
    }

    private BaseRequest a(String str, RequestPurpose requestPurpose) {
        LocalRequest localRequest;
        switch (bd.uG[requestPurpose.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                localRequest = new LocalRequest(this.mActivity, 0);
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                localRequest = new LocalRequest(this.mActivity, 1);
                break;
            default:
                localRequest = null;
                break;
        }
        if (localRequest == null) {
            return null;
        }
        localRequest.setProjection(v.PROJECTION);
        localRequest.setUri(v.CONTENT_URI);
        localRequest.setSelection("parent=?");
        localRequest.setArgs(new String[]{str});
        return localRequest;
    }

    public void onResume() {
        super.onResume();
        this.mActivity.setTitle(R.string.express_region_pick_title);
    }
}
