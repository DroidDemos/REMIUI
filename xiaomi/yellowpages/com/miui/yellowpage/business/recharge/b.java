package com.miui.yellowpage.business.recharge;

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
class b implements c {
    final /* synthetic */ D dw;

    private b(D d) {
        this.dw = d;
    }

    public void onPreRequest(int i) {
        if (i != 6) {
        }
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        x xVar = (x) baseResult;
        if (!xVar.hasData()) {
            xVar.mName = this.dw.getString(R.string.recharge_unknown_number);
        }
        if (i == 6) {
            ((v) this.dw.qA.get(this.dw.qA.indexOf(new v(null, xVar.mNumber)))).setName(xVar.mName);
            this.dw.qF.notifyDataSetChanged();
        } else if (TextUtils.equals(this.dw.qz, xVar.mNumber)) {
            this.dw.qt.setVisibility(0);
            this.dw.qt.setText(xVar.mName);
            Editable editableText = this.dw.qo.getEditableText();
            Layout layout = this.dw.qo.getLayout();
            if (layout != null && editableText != null) {
                this.dw.qt.setPadding(((int) layout.getPrimaryHorizontal(editableText.length())) + this.dw.qo.getPaddingLeft(), this.dw.qt.getPaddingTop(), this.dw.qt.getPaddingRight(), this.dw.qt.getPaddingBottom());
            }
        }
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        x xVar = (x) baseResult;
        if (obj != null && (i == 5 || i == 6)) {
            Cursor cursor = (Cursor) obj;
            try {
                if (cursor.moveToNext()) {
                    xVar.mName = cursor.getString(0);
                } else {
                    String f = this.dw.qz;
                    if (Sim.isLocalPhoneNumber(this.dw.mActivity, xVar.mNumber) && this.dw.l(f, this.dw.qz)) {
                        xVar.mName = this.dw.qy;
                    }
                }
                cursor.close();
            } catch (Throwable th) {
                cursor.close();
            }
        }
        return xVar;
    }
}
