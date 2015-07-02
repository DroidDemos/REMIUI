package com.miui.yellowpage.ui;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.utils.e;

/* compiled from: FavoriteYellowPageFragment */
class bQ implements OnMenuItemClickListener {
    final /* synthetic */ YellowPage Ap;
    final /* synthetic */ cn Aq;

    bQ(cn cnVar, YellowPage yellowPage) {
        this.Aq = cnVar;
        this.Ap = yellowPage;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_unfavorite:
                e.a(this.Aq.mE.mActivity, this.Ap.getId(), false);
                this.Aq.mE.mLoader.reload();
                Toast.makeText(this.Aq.mE.mActivity, R.string.general_cancel_favorite_ok, 0).show();
                break;
        }
        return true;
    }
}
