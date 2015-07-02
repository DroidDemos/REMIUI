package com.miui.yellowpage.ui;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.utils.e;

/* compiled from: RecentYellowPageFragment */
class cR implements OnMenuItemClickListener {
    final /* synthetic */ YellowPage Ap;
    final /* synthetic */ ag FS;

    cR(ag agVar, YellowPage yellowPage) {
        this.FS = agVar;
        this.Ap = yellowPage;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_delete:
                e.b(this.FS.hS.mActivity, this.Ap.getId());
                this.FS.hS.mLoader.reload();
                Toast.makeText(this.FS.hS.mActivity, R.string.general_delete_recent_ok, 0).show();
                break;
        }
        return true;
    }
}
