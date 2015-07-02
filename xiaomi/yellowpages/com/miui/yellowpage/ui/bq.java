package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.model.w;

/* compiled from: YellowPagePickerFragment */
class bq implements OnItemClickListener {
    final /* synthetic */ dv vM;

    bq(dv dvVar) {
        this.vM = dvVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        w N = this.vM.N(i);
        Intent intent = new Intent();
        intent.putExtra("result_backend_data", N.getData());
        intent.putExtra("result_presentation", N.in());
        this.vM.mActivity.setResult(-1, intent);
        this.vM.mActivity.finish();
    }
}
