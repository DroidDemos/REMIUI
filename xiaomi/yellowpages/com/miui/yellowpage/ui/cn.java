package com.miui.yellowpage.ui;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;

/* compiled from: FavoriteYellowPageFragment */
class cn implements OnCreateContextMenuListener {
    final /* synthetic */ bc mE;

    cn(bc bcVar) {
        this.mE = bcVar;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        this.mE.getActivity().getMenuInflater().inflate(R.menu.favorite_yellow_page_context, contextMenu);
        YellowPage yellowPage = (YellowPage) this.mE.uC.getItem(((AdapterContextMenuInfo) contextMenuInfo).position);
        contextMenu.setHeaderTitle(yellowPage.getName());
        contextMenu.findItem(R.id.menu_unfavorite).setOnMenuItemClickListener(new bQ(this, yellowPage));
    }
}
