package com.miui.yellowpage.ui;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.b.f;
import com.miui.yellowpage.base.reference.RefMethods.IntentScope;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.x;
import com.miui.yellowpage.utils.B;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.ArrayList;
import miui.date.DateUtils;

/* compiled from: CallLogFragment */
public class cO extends cs implements OnItemClickListener {
    private aR EQ;
    private bp ER;
    private String[] ES;
    private f ET;
    private ListView bK;

    public void a(aR aRVar) {
        this.EQ = aRVar;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.ES = arguments.getStringArray("phone_numbers");
        }
        View inflate = layoutInflater.inflate(R.layout.call_log_fragment, viewGroup, false);
        this.bK = (ListView) inflate.findViewById(16908298);
        this.ET = new f(this.mActivity);
        this.bK.setAdapter(this.ET);
        this.bK.setOnItemClickListener(this);
        this.bK.setOnCreateContextMenuListener(this);
        setHasOptionsMenu(true);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.ER = new bp();
        getLoaderManager().initLoader(1, null, this.ER);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        startActivity(IntentScope.processIntentScope(this.mActivity, new Intent("android.intent.action.CALL_PRIVILEGED", Uri.fromParts("tel", ((x) this.ET.getItem(i)).getNumber(), null)), IntentScope.PACKAGE_NAME_PHONE));
    }

    private ArrayList bk() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(b(this.mActivity, this.ES));
        return arrayList;
    }

    public static LocalRequest b(Context context, String[] strArr) {
        LocalRequest localRequest = new LocalRequest(context, 1);
        localRequest.setProjection(B.COLUMNS);
        localRequest.setSortOrder("date DESC");
        localRequest.setUri(B.URI);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" OR ");
            }
            stringBuilder.append("normalized_number");
            stringBuilder.append(" = ?");
        }
        localRequest.setSelection(stringBuilder.toString());
        localRequest.setArgs(strArr);
        return localRequest;
    }

    public ListView getListView() {
        return this.bK;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) contextMenuInfo;
        if (adapterContextMenuInfo != null) {
            x xVar = (x) this.ET.getItem(adapterContextMenuInfo.position);
            if (xVar != null) {
                contextMenu.setHeaderTitle(DateUtils.formatDateTime(xVar.getDate(), 33676));
                contextMenu.add(0, 2, 0, getString(R.string.menu_delete_one));
            }
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        try {
            AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) menuItem.getMenuInfo();
            switch (menuItem.getItemId()) {
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (adapterContextMenuInfo != null) {
                        ac(adapterContextMenuInfo.position);
                    }
                    return true;
                default:
                    return false;
            }
        } catch (Throwable e) {
            Log.e("CallLogFragment", "bad menuInfo", e);
            return false;
        }
    }

    private void ac(int i) {
        x xVar = (x) this.ET.getItem(i);
        if (xVar != null) {
            new Builder(this.mActivity).setTitle(R.string.confirm_delete_call_log_title).setIconAttribute(16843605).setNegativeButton(17039360, null).setPositiveButton(R.string.delete, new cG(this, xVar, i)).setCancelable(true).show();
        }
    }

    private void hl() {
        if (this.ET.getCount() > 0) {
            new Builder(this.mActivity).setTitle(R.string.confirm_delete_all_call_log_title).setIconAttribute(16843605).setNegativeButton(17039360, null).setPositiveButton(R.string.delete, new cE(this)).setCancelable(true).show();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.yellow_page_call_log, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_delete_call_log:
                hl();
                break;
        }
        return true;
    }
}
