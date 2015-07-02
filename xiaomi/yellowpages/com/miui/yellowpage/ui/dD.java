package com.miui.yellowpage.ui;

import android.database.Cursor;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.model.v;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeFragment */
class dD implements c {
    final /* synthetic */ aL ie;

    private dD(aL aLVar) {
        this.ie = aLVar;
    }

    public void onPreRequest(int i) {
        if (i != 6) {
        }
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        cL cLVar = (cL) baseResult;
        if (!cLVar.hasData()) {
            cLVar.mName = this.ie.getString(R.string.recharge_unknown_number);
        }
        if (i == 6) {
            ((v) this.ie.qA.get(this.ie.qA.indexOf(new v(null, cLVar.mNumber)))).setName(cLVar.mName);
            this.ie.qF.notifyDataSetChanged();
        } else if (TextUtils.equals(this.ie.qz, cLVar.mNumber)) {
            this.ie.qt.setVisibility(0);
            this.ie.qt.setText(cLVar.mName);
            Editable editableText = this.ie.qo.getEditableText();
            Layout layout = this.ie.qo.getLayout();
            if (layout != null && editableText != null) {
                this.ie.qt.setPadding(((int) layout.getPrimaryHorizontal(editableText.length())) + this.ie.qo.getPaddingLeft(), this.ie.qt.getPaddingTop(), this.ie.qt.getPaddingRight(), this.ie.qt.getPaddingBottom());
            }
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        cL cLVar = (cL) baseResult;
        if (obj != null && (i == 5 || i == 6)) {
            Cursor cursor = (Cursor) obj;
            try {
                if (cursor.moveToNext()) {
                    cLVar.mName = cursor.getString(0);
                } else {
                    String f = this.ie.qz;
                    if (Sim.isLocalPhoneNumber(this.ie.mActivity, cLVar.mNumber) && this.ie.l(f, this.ie.qz)) {
                        cLVar.mName = this.ie.qy;
                    }
                }
                cursor.close();
            } catch (Throwable th) {
                cursor.close();
            }
        }
        return cLVar;
    }
}
