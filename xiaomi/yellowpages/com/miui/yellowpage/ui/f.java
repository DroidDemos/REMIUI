package com.miui.yellowpage.ui;

import com.miui.yellowpage.model.x;
import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: CallLogFragment */
class f extends BaseResult {
    private ArrayList cB;
    final /* synthetic */ cO cC;

    public f(cO cOVar) {
        this.cC = cOVar;
        this.cB = new ArrayList();
    }

    public boolean hasData() {
        return this.cB != null && this.cB.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult fVar = new f(this.cC);
        fVar.cB = this.cB;
        return fVar;
    }

    public void a(x xVar) {
        this.cB.add(xVar);
    }
}
