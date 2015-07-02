package com.miui.yellowpage.ui;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.yellowpage.R;
import miui.widget.EditableListView.MultiChoiceModeListener;

/* compiled from: InquiryHistoryFragment */
class bk implements MultiChoiceModeListener {
    final /* synthetic */ aH qg;
    private MenuItem vc;

    private bk(aH aHVar) {
        this.qg = aHVar;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        this.qg.mActivity.getMenuInflater().inflate(R.menu.inquiry_history_edit_menu, menu);
        this.qg.oS.gS();
        this.qg.oS.notifyDataSetChanged();
        this.vc = menu.findItem(R.id.remark);
        return true;
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.delete:
                this.qg.a(actionMode);
                break;
            case R.id.remark:
                this.qg.cz();
                actionMode.finish();
                break;
        }
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        this.qg.oS.gT();
        this.qg.oU.clear();
        this.qg.oS.notifyDataSetChanged();
    }

    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
        boolean z2 = true;
        if (z) {
            this.qg.oU.add(Integer.valueOf(i));
        } else {
            this.qg.oU.remove(Integer.valueOf(i));
        }
        MenuItem menuItem = this.vc;
        if (this.qg.oU.size() != 1) {
            z2 = false;
        }
        menuItem.setEnabled(z2);
    }

    public void onAllItemCheckedStateChanged(ActionMode actionMode, boolean z) {
        boolean z2 = true;
        this.qg.n(z);
        MenuItem menuItem = this.vc;
        if (this.qg.oU.size() != 1) {
            z2 = false;
        }
        menuItem.setEnabled(z2);
    }
}
