package com.miui.yellowpage.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.b.m;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.g;
import java.util.HashSet;
import java.util.Set;
import miui.provider.BatchOperation;
import miui.widget.EditableListViewWrapper;
import miui.yellowpage.YellowPageContract.Cache;

/* compiled from: InquiryHistoryFragment */
public class aH extends cs {
    private cc cZ;
    private p mLoader;
    private EditableListViewWrapper oR;
    private m oS;
    private bW oT;
    private Set oU;

    public aH() {
        this.oU = new HashSet();
    }

    public void onDestroy() {
        super.onDestroy();
        Cursor cursor = this.oS.getCursor();
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public void onResume() {
        super.onResume();
        this.mActivity.setTitle(R.string.express_inquiry_history);
        if (this.mLoader != null) {
            this.mLoader.reload();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.inquiry_history_fragment, viewGroup, false);
        setHasOptionsMenu(true);
        i(inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.oT = new bW();
        getLoaderManager().initLoader(0, null, this.oT);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            this.mLoader.reload();
        }
    }

    private void i(View view) {
        this.oS = new m(this.mActivity, null, false);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setChoiceMode(3);
        listView.setOnItemClickListener(new aO());
        listView.setEmptyView(view.findViewById(16908292));
        this.oR = new EditableListViewWrapper(listView);
        this.oR.setMultiChoiceModeListener(new bk());
        this.oR.setAdapter(this.oS);
    }

    private BaseRequest cy() {
        Log.d("InquiryHistoryFragment", "getRequest");
        LocalRequest localRequest = new LocalRequest(this.mActivity, 0);
        localRequest.setUri(Cache.CONTENT_URI);
        localRequest.setProjection(m.PROJECTION);
        localRequest.setSelection("cache_key LIKE 'inquiry_history_item%'");
        localRequest.setSortOrder("etag DESC");
        return localRequest;
    }

    private void n(boolean z) {
        if (z) {
            for (int i = 0; i < this.oS.getCount(); i++) {
                this.oU.add(Integer.valueOf(i));
            }
            return;
        }
        this.oU.clear();
    }

    private String A(int i) {
        return this.oS.d((Cursor) this.oS.getItem(i)).getKey();
    }

    private void a(ActionMode actionMode) {
        CharSequence string;
        if (this.oR.getCheckedItemCount() == 1) {
            string = this.mActivity.getResources().getString(R.string.express_inquiry_delete_warning_single);
        } else {
            string = this.mActivity.getResources().getString(R.string.express_inquiry_delete_warning_multiple, new Object[]{Integer.valueOf(r0)});
        }
        new Builder(this.mActivity).setTitle(R.string.delete).setMessage(string).setPositiveButton(17039370, new bf(this)).setNegativeButton(17039360, null).setOnDismissListener(new bh(this, actionMode)).show();
    }

    private void cz() {
        if (this.oU.size() == 1) {
            int i = -1;
            for (Integer intValue : this.oU) {
                i = intValue.intValue();
            }
            a(this.oS.d((Cursor) this.oS.getItem(i)));
        }
    }

    private void a(g gVar) {
        Intent intent = new Intent("com.miui.yellowpage.action.REMARK");
        intent.putExtra("android.intent.extra.TITLE", gVar.aQ() + " " + gVar.getSerialNumber());
        intent.putExtra("order_key", gVar.getKey());
        intent.putExtra("order_remark", gVar.aS());
        startActivityForResult(intent, 1);
    }

    private void cA() {
        BatchOperation batchOperation = new BatchOperation(this.mActivity.getContentResolver(), "miui.yellowpage");
        for (Integer intValue : this.oU) {
            int intValue2 = intValue.intValue();
            ContentProviderOperation.Builder newDelete = ContentProviderOperation.newDelete(Cache.CONTENT_URI);
            newDelete.withSelection("cache_key=?", new String[]{A(intValue2)});
            batchOperation.add(newDelete.build());
            if (batchOperation.size() >= 100) {
                batchOperation.execute();
            }
        }
        if (batchOperation.size() > 0) {
            batchOperation.execute();
        }
        this.mLoader.reload();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof cc) {
            this.cZ = (cc) activity;
        } else {
            Log.e("InquiryHistoryFragment", "parent activity is not an instance of ExpressInquiryListener");
        }
    }
}
