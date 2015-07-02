package com.miui.yellowpage.a;

import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RequestLoader */
public class d {
    private g is;
    private c it;

    public void a(g gVar) {
        this.is = gVar;
    }

    public void a(c cVar) {
        this.it = cVar;
    }

    public void a(BaseRequest baseRequest, BaseResult baseResult) {
        if (baseRequest == null || baseResult == null) {
            throw new IllegalArgumentException("The request or result should not be null");
        }
        new f().b(baseRequest, baseResult);
    }
}
