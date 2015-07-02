package com.miui.yellowpage.activity;

import com.miui.yellowpage.request.BaseResult;
import java.util.Map;

/* compiled from: ExpressInquiryActivity */
public class y extends BaseResult {
    public Map map;

    public boolean hasData() {
        return this.map != null && this.map.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult yVar = new y();
        yVar.map = this.map;
        return yVar;
    }
}
