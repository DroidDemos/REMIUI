package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.List;
import org.json.JSONArray;

/* compiled from: ExpressInquiryProgressFragment */
class di extends BaseResult {
    public String HW;
    public JSONArray HX;
    public int HY;
    public List HZ;
    public String iS;
    public int mState;
    public int mStatus;
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    private di(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public boolean hasData() {
        if (this.HZ.size() != 1) {
            return true;
        }
        switch (this.mStatus) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return true;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                if (this.iS == null || this.HX == null) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    public BaseResult shallowClone() {
        BaseResult diVar = new di(this.mY);
        diVar.iS = this.iS;
        diVar.HW = this.HW;
        diVar.HX = this.HX;
        diVar.mStatus = this.mStatus;
        diVar.mState = this.mState;
        diVar.HZ = this.HZ;
        diVar.HY = this.HY;
        return diVar;
    }
}
