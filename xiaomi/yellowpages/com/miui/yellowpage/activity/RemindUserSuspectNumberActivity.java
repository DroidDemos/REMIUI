package com.miui.yellowpage.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import com.miui.yellowpage.R;

public class RemindUserSuspectNumberActivity extends BaseActivity {
    private boolean rb;

    protected boolean supportsBanner() {
        return false;
    }

    protected boolean requireNetworking() {
        return false;
    }

    @SuppressLint({"InflateParams"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(this).inflate(R.layout.remind_user_suspect_number_dialog_view, null);
        ((CheckBox) inflate.findViewById(R.id.checkbox)).setOnCheckedChangeListener(new r(this));
        this.rb = true;
        new Builder(this).setTitle(R.string.remind_user_suspect_number_title).setPositiveButton(R.string.remind_user_confirm, new t(this)).setView(inflate).setOnCancelListener(new s(this)).show();
    }
}
