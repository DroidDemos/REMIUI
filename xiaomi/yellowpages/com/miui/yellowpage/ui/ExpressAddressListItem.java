package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.ui.ExpressAddressListFragment.ActionType;
import com.miui.yellowpage.widget.ExpressAddressView;

public class ExpressAddressListItem extends LinearLayout {
    private ExpressAddressView Ls;
    private RadioButton Lt;
    private Button Lu;
    private int Lv;
    private cW Lw;
    private bM Lx;
    private ActionType bJ;
    private int mCount;

    public ExpressAddressListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.Ls = (ExpressAddressView) findViewById(R.id.address_view);
        this.Lt = (RadioButton) findViewById(R.id.button);
        this.Lu = (Button) findViewById(R.id.detail_button);
        this.Lu.setOnClickListener(new ae(this));
    }

    public void iv() {
        if (this.Lw != null) {
            this.Lw.a(this);
        }
    }

    public void iw() {
        if (this.Lx != null) {
            this.Lx.b(this);
        }
    }

    public void a(ActionType actionType) {
        this.bJ = actionType;
        if (this.bJ == ActionType.MANAGE) {
            this.Lt.setVisibility(4);
            this.Lu.setVisibility(8);
            return;
        }
        this.Lt.setVisibility(0);
        this.Lu.setVisibility(0);
    }

    public void a(cW cWVar) {
        this.Lw = cWVar;
    }

    public void a(bM bMVar) {
        this.Lx = bMVar;
    }

    public int getPosition() {
        return this.Lv;
    }

    public void f(int i, int i2) {
        this.Lv = i;
        this.mCount = i2;
        if (this.mCount > 0) {
        }
    }

    public void h(ExpressAddress expressAddress) {
        this.Ls.h(expressAddress);
    }

    public void F(boolean z) {
        this.Lt.setChecked(z);
    }
}
