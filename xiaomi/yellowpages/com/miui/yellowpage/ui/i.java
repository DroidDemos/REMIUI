package com.miui.yellowpage.ui;

import com.miui.yellowpage.a.g;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;

/* compiled from: ExpressCompanyListBaseFragment */
public abstract class i extends dF {
    protected abstract g I();

    protected abstract int J();

    protected abstract int K();

    protected abstract X L();

    protected abstract void a(dU dUVar);

    private BaseRequest M() {
        return new HttpRequest(this.JP, HostManager.getExpressCompanyListUrl(), K());
    }
}
