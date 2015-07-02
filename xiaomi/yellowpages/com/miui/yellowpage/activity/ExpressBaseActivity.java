package com.miui.yellowpage.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.miui.yellowpage.R;

public class ExpressBaseActivity extends BaseActivity {
    private Button dc;
    private View mCustomView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bo()) {
            bn();
            this.dc = (Button) this.mCustomView.findViewById(R.id.history_button);
            this.dc.setOnClickListener(new z(this));
        }
    }

    @SuppressLint({"InflateParams"})
    private void bn() {
        this.mCustomView = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.recharge_history_custom_view, null);
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mActionBar.setCustomView(this.mCustomView, new LayoutParams(-2, -2, 21));
    }

    protected boolean bo() {
        return false;
    }

    protected void bp() {
    }

    public void k(boolean z) {
        if (this.mCustomView != null) {
            if (z) {
                this.mCustomView.setVisibility(0);
            } else {
                this.mCustomView.setVisibility(8);
            }
        }
    }
}
