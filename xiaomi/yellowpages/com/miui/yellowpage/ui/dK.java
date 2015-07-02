package com.miui.yellowpage.ui;

import com.miui.yellowpage.model.ExpressAddress;

/* compiled from: ExpressAddressListFragment */
class dK implements bM {
    final /* synthetic */ ExpressAddressListFragment ep;

    dK(ExpressAddressListFragment expressAddressListFragment) {
        this.ep = expressAddressListFragment;
    }

    public void b(ExpressAddressListItem expressAddressListItem) {
        this.ep.bM = expressAddressListItem;
        this.ep.b((ExpressAddress) this.ep.bL.getItem(expressAddressListItem.getPosition()));
    }
}
