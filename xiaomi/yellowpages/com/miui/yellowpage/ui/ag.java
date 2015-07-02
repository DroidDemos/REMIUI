package com.miui.yellowpage.ui;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;

/* compiled from: RecentYellowPageFragment */
class ag implements OnCreateContextMenuListener {
    final /* synthetic */ cm hS;

    ag(cm cmVar) {
        this.hS = cmVar;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        this.hS.getActivity().getMenuInflater().inflate(R.menu.recent_yellow_page_context, contextMenu);
        YellowPage yellowPage = (YellowPage) this.hS.Dv.getItem(((AdapterContextMenuInfo) contextMenuInfo).position);
        contextMenu.setHeaderTitle(yellowPage.getName());
        contextMenu.findItem(R.id.menu_delete).setOnMenuItemClickListener(new cR(this, yellowPage));
    }
}
