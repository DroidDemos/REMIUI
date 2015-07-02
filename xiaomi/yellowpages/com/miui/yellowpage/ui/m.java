package com.miui.yellowpage.ui;

import com.miui.yellowpage.model.ExpressAddress;

/* compiled from: ExpressAddressListFragment */
class m implements cW {
    final /* synthetic */ ExpressAddressListFragment ep;

    m(ExpressAddressListFragment expressAddressListFragment) {
        this.ep = expressAddressListFragment;
    }

    public void a(ExpressAddressListItem expressAddressListItem) {
        this.ep.a((ExpressAddress) this.ep.bL.getItem(expressAddressListItem.getPosition()));
    }
}
