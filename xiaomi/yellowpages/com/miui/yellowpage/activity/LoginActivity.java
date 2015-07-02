package com.miui.yellowpage.activity;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.XiaomiAccount;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public class LoginActivity extends BaseActivity {
    private String MD;
    private Activity mActivity;
    private String mMessage;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = this;
        this.MD = getIntent().getStringExtra("service_token_id");
        this.mActionBar.setTitle(getIntent().getStringExtra("android.intent.extra.TITLE"));
        this.mMessage = getIntent().getStringExtra("com.miui.yellowpage.extra.LOGIN_MESSAGE");
        if ("account".equals(getIntent().getStringExtra("com.miui.yellowpage.extra.LOGIN_TYPE"))) {
            jc();
        } else {
            jf();
        }
    }

    protected boolean supportsBanner() {
        return false;
    }

    private void jc() {
        showDialog(0);
    }

    public Dialog onCreateDialog(int i) {
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return je();
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return jd();
            default:
                return null;
        }
    }

    private Dialog jd() {
        Builder builder = new Builder(this.mActivity);
        builder.setTitle(R.string.account_updating_device_failed).setNegativeButton(17039360, new l(this)).setPositiveButton(17039370, new i(this)).setOnCancelListener(new j(this));
        return builder.create();
    }

    private Dialog je() {
        Builder builder = new Builder(this.mActivity);
        builder.setTitle(R.string.login_before_use).setPositiveButton(R.string.login_ok, new g(this)).setOnCancelListener(new k(this));
        return builder.create();
    }

    private void jf() {
        AccountManager accountManager = AccountManager.get(this);
        Bundle bundle = new Bundle();
        bundle.putString("service_id", this.MD);
        if (TextUtils.isEmpty(this.mMessage)) {
            this.mMessage = getString(R.string.account_verify_password);
        }
        bundle.putString(MiniDefine.au, this.mMessage);
        accountManager.confirmCredentials(XiaomiAccount.getAccount(this), bundle, this, new f(this), null);
    }

    private void gR() {
        new h(this).execute(new Void[0]);
    }

    protected boolean requireNetworking() {
        return false;
    }
}
