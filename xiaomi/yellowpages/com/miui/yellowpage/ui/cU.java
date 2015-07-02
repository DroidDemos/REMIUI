package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: CityPickerFragment */
class cU extends BaseResult {
    ArrayList Gj;
    ArrayList Gk;
    ArrayList Gl;
    ArrayList Gm;
    final /* synthetic */ bD ge;

    private cU(bD bDVar) {
        this.ge = bDVar;
    }

    public boolean hasData() {
        return this.Gl != null && this.Gl.size() > 0 && this.Gm != null && this.Gm.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult cUVar = new cU(this.ge);
        cUVar.Gk = this.Gk;
        cUVar.Gj = this.Gj;
        cUVar.Gl = this.Gl;
        cUVar.Gm = this.Gm;
        return cUVar;
    }
}
