package com.miui.yellowpage.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.miui.yellowpage.R;
import com.miui.yellowpage.b.c;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;
import com.miui.yellowpage.base.model.YellowPageServicesDataEntry;
import java.util.ArrayList;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

public class YellowPageServiceContainerItem extends LinearLayout {
    private ArrayList Dm;
    private c Dn;
    private AlertDialog Do;
    private long ld;

    public YellowPageServiceContainerItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(YellowPageServicesDataEntry yellowPageServicesDataEntry) {
        this.Dn.updateData(yellowPageServicesDataEntry.getServices());
        this.Dm = yellowPageServicesDataEntry.getServices();
        this.ld = yellowPageServicesDataEntry.getYellowPageId();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        YellowPageServiceGridView yellowPageServiceGridView = (YellowPageServiceGridView) findViewById(R.id.container);
        this.Dn = new c(getContext(), true);
        yellowPageServiceGridView.setAdapter(this.Dn);
        yellowPageServiceGridView.setOnItemClickListener(new cM(this));
    }

    @SuppressLint({"InflateParams"})
    private void gZ() {
        Context context = getContext();
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.more_services_view, null);
        GridView gridView = (GridView) inflate.findViewById(R.id.grid);
        c cVar = new c(context, false);
        gridView.setAdapter(cVar);
        cVar.updateData(this.Dm);
        gridView.setOnItemClickListener(new cK(this, cVar));
        builder.setTitle(R.string.all_services);
        builder.setView(inflate);
        this.Do = builder.create();
        this.Do.show();
    }

    private void c(YellowPageModuleEntry yellowPageModuleEntry) {
        Intent intent = new Intent("com.miui.yellowpage.action.MULTI_MODULE_CLICKED");
        intent.putExtra("com.miui.yellowpage.extra.MULTI_MODULE_ENTRY", yellowPageModuleEntry);
        intent.putExtra("source", "service_grid_view");
        intent.putExtra("com.miui.yellowpage.extra.yid", this.ld);
        getContext().startActivity(intent);
    }
}
