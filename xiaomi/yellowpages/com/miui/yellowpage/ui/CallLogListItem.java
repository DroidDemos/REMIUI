package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.reference.RefMethods.DateUtils;
import com.miui.yellowpage.model.f;
import com.miui.yellowpage.model.n;
import com.miui.yellowpage.model.x;
import com.miui.yellowpage.utils.o;
import java.nio.CharBuffer;
import java.util.HashMap;
import miui.os.Build;
import miui.telephony.PhoneNumberUtils.PhoneNumber;

public class CallLogListItem extends LinearLayout {
    private static HashMap Cb;
    private TextView BP;
    private TextView BQ;
    private TextView BR;
    private TextView BS;
    private ImageView BT;
    private ImageView BU;
    private View BV;
    private View BW;
    private TextView BX;
    private TextView BY;
    public CharBuffer BZ;
    public CharBuffer Ca;

    public CallLogListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.BZ = CharBuffer.allocate(64);
        this.Ca = CharBuffer.allocate(64);
        Cb = new HashMap();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.BP = (TextView) findViewById(R.id.call_date);
        this.BQ = (TextView) findViewById(R.id.number);
        this.BR = (TextView) findViewById(R.id.location);
        this.BS = (TextView) findViewById(R.id.duration);
        this.BT = (ImageView) findViewById(R.id.call_type);
        this.BU = (ImageView) findViewById(R.id.sim);
        this.BW = findViewById(R.id.call_record);
        this.BV = findViewById(R.id.call_note);
        this.BX = (TextView) findViewById(R.id.call_note_title);
        this.BY = (TextView) findViewById(R.id.call_record_title);
    }

    public void b(x xVar) {
        if (!xVar.iC() && !xVar.iB()) {
            this.BV.setVisibility(8);
            this.BW.setVisibility(8);
        } else if (xVar.iC() && xVar.iB()) {
            this.BV.setVisibility(0);
            this.BW.setVisibility(0);
            n.a(this.mContext, this.BV, this.BX, xVar.iD());
            f.a(this.mContext, this.BW, this.BY, xVar.iA());
        } else if (xVar.iC()) {
            this.BV.setVisibility(0);
            this.BW.setVisibility(8);
            n.a(this.mContext, this.BV, this.BX, xVar.iD());
        } else if (xVar.iB()) {
            this.BV.setVisibility(8);
            this.BW.setVisibility(0);
            f.a(this.mContext, this.BW, this.BY, xVar.iA());
        }
        o.a(xVar.getType(), xVar.iE(), this.BT);
        this.BP.setText(DateUtils.getFormattedTime(this.mContext, xVar.getDate()));
        this.BQ.setVisibility(0);
        CharSequence number = xVar.getNumber();
        this.BQ.setText(number);
        if (o.cN()) {
            this.BR.setVisibility(8);
        } else {
            CharSequence charSequence = (String) Cb.get(number);
            if (charSequence == null) {
                charSequence = PhoneNumber.getLocation(this.mContext, number);
                Cb.put(number, charSequence);
            }
            if (TextUtils.isEmpty(charSequence)) {
                this.BR.setVisibility(8);
            } else {
                this.BR.setVisibility(0);
                this.BR.setText(charSequence);
            }
        }
        this.BZ.clear();
        if (xVar.getType() == 3) {
            o.a(this.mContext, this.BZ, xVar.getDuration());
            this.BP.setTextAppearance(this.mContext, R.style.CallLogListItemMissedCallTextAppearance);
        } else {
            o.a(this.mContext, this.BZ, xVar.getDuration(), xVar.getType());
            this.BP.setTextAppearance(this.mContext, R.style.CallLogListItemTextAppearancePrimary);
        }
        if (Build.IS_CM_CUSTOMIZATION) {
            this.BS.setVisibility(8);
        } else {
            this.BS.setVisibility(0);
            this.BS.setText(this.BZ.array(), 0, this.BZ.position());
        }
        if (o.cP()) {
            this.BU.setVisibility(0);
            int d = o.d(getContext(), xVar.getSimId());
            if (d == 0) {
                this.BU.setImageResource(R.drawable.sim1);
                return;
            } else if (1 == d) {
                this.BU.setImageResource(R.drawable.sim2);
                return;
            } else {
                this.BU.setImageResource(R.drawable.simx);
                return;
            }
        }
        this.BU.setVisibility(8);
    }
}
