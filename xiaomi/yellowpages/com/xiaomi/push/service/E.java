package com.xiaomi.push.service;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.push.service.q.c;

class E implements n {
    final /* synthetic */ m co;

    E(m mVar) {
        this.co = mVar;
    }

    public void a(c cVar, c cVar2, int i) {
        if (cVar2 == c.binding) {
            this.co.kL.a(this.co.kM, (long) ConfigConstant.LOCATE_INTERVAL_UINT);
        } else {
            this.co.kL.b(this.co.kM);
        }
    }
}
