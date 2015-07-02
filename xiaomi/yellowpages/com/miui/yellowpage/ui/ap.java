package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.b.r;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.model.YellowPageDataEntry;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.YellowPageDataDetailEntry;
import com.miui.yellowpage.model.YellowPageDataDetailEntry.Type;
import com.miui.yellowpage.utils.D;
import com.miui.yellowpage.utils.o;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import miui.provider.ExtraTelephony;
import miui.yellowpage.YellowPageContract;

/* compiled from: YellowPagePhonesFragment */
public class ap extends cs implements OnItemClickListener {
    private long ld;
    private YellowPage lh;
    private r ni;
    private cp nj;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.nj = new cp();
        getLoaderManager().initLoader(0, null, this.nj);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.yellow_page_phones_fragment, viewGroup, false);
        ListView listView = (ListView) inflate.findViewById(16908298);
        this.ni = new cT(this, this.mActivity, null);
        listView.setAdapter(this.ni);
        listView.setOnCreateContextMenuListener(this);
        listView.setOnItemClickListener(this);
        this.ld = getArguments().getLong("id");
        return inflate;
    }

    private BaseRequest bR() {
        LocalRequest localRequest = new LocalRequest(this.mActivity, 0);
        localRequest.setUri(YellowPageContract.YellowPage.CONTENT_URI);
        localRequest.setProjection(new String[]{MiniDefine.at});
        localRequest.setSelection("yid=?");
        localRequest.setArgs(new String[]{String.valueOf(this.ld)});
        return localRequest;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        YellowPageDataEntry yellowPageDataEntry = (YellowPageDataEntry) this.ni.getItem(((AdapterContextMenuInfo) contextMenuInfo).position);
        if (yellowPageDataEntry instanceof YellowPageDataDetailEntry) {
            YellowPageDataDetailEntry yellowPageDataDetailEntry = (YellowPageDataDetailEntry) yellowPageDataEntry;
            contextMenu.setHeaderTitle(yellowPageDataDetailEntry.getData());
            contextMenu.add(0, 0, 0, this.mActivity.getString(R.string.menu_copy_text));
            if (yellowPageDataDetailEntry.du() == Type.PHONE) {
                contextMenu.add(0, 1, 0, getString(R.string.menu_call_ip_number));
                if (ExtraTelephony.isInBlacklist(this.mActivity, yellowPageDataDetailEntry.getData())) {
                    contextMenu.add(0, 4, 0, this.mActivity.getString(R.string.menu_remove_black_list));
                } else {
                    contextMenu.add(0, 3, 0, this.mActivity.getString(R.string.menu_add_black_list));
                }
            }
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        try {
            AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) menuItem.getMenuInfo();
            switch (menuItem.getItemId()) {
                case TransactionXMLFile.MODE_PRIVATE /*0*/:
                    p(adapterContextMenuInfo.position);
                    return true;
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    q(adapterContextMenuInfo.position);
                    return true;
                case WindowData.d /*3*/:
                    D.z(this.mActivity, ((YellowPageDataDetailEntry) ((YellowPageDataEntry) this.ni.getItem(adapterContextMenuInfo.position))).getData());
                    return true;
                case Base64.CRLF /*4*/:
                    D.A(this.mActivity, ((YellowPageDataDetailEntry) ((YellowPageDataEntry) this.ni.getItem(adapterContextMenuInfo.position))).getData());
                    return true;
                default:
                    return false;
            }
        } catch (Throwable e) {
            Log.e("YellowPagePhonesFragment", "bad menuInfo", e);
            return false;
        }
    }

    private void p(int i) {
        YellowPageDataDetailEntry yellowPageDataDetailEntry = (YellowPageDataDetailEntry) this.ni.getItem(i);
        CharSequence data = yellowPageDataDetailEntry.getData();
        if (!TextUtils.isEmpty(data)) {
            o.a(this.mActivity, data.toString(), yellowPageDataDetailEntry.du());
        }
    }

    private void q(int i) {
        CharSequence data = ((YellowPageDataDetailEntry) this.ni.getItem(i)).getData();
        if (!TextUtils.isEmpty(data)) {
            o.a(this.mActivity, data, true);
        }
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        YellowPageDataEntry yellowPageDataEntry = (YellowPageDataEntry) this.ni.getItem(i);
        if (yellowPageDataEntry instanceof YellowPageDataDetailEntry) {
            YellowPageDataDetailEntry yellowPageDataDetailEntry = (YellowPageDataDetailEntry) yellowPageDataEntry;
            if (yellowPageDataDetailEntry.ds() != null) {
                this.mActivity.startActivity(yellowPageDataDetailEntry.ds());
            }
            BusinessStatistics.clickYellowPageItem(this.mActivity, String.valueOf(this.ld), yellowPageDataDetailEntry.du().ar(), yellowPageDataDetailEntry.getData(), "quick_yellow_page", getStatisticsContext().getSourceModuleId());
        }
    }
}
