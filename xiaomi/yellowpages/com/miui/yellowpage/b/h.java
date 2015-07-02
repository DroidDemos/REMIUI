package com.miui.yellowpage.b;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.ui.ExpressAddressListFragment.ActionType;
import com.miui.yellowpage.ui.ExpressAddressListItem;

/* compiled from: ExpressAddressAdapter */
class h implements OnClickListener {
    final /* synthetic */ ExpressAddressListItem tY;
    final /* synthetic */ t tZ;

    h(t tVar, ExpressAddressListItem expressAddressListItem) {
        this.tZ = tVar;
        this.tY = expressAddressListItem;
    }

    public void onClick(View view) {
        if (this.tZ.Me != null) {
            this.tZ.Me.F(false);
        }
        this.tZ.Me = this.tY;
        this.tZ.Me.F(true);
        if (this.tZ.bJ == ActionType.MANAGE) {
            this.tY.iw();
        } else {
            this.tY.iv();
        }
    }
}
