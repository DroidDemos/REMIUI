package com.miui.yellowpage.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.mipub.MiPubDeviceManager;
import com.miui.yellowpage.base.utils.Log;

/* compiled from: LoginActivity */
class h extends AsyncTask {
    private ProgressDialog gS;
    final /* synthetic */ LoginActivity ir;

    h(LoginActivity loginActivity) {
        this.ir = loginActivity;
    }

    protected void onPreExecute() {
        if (!this.ir.isFinishing()) {
            this.gS = new ProgressDialog(this.ir);
            this.gS.setMessage(this.ir.getString(R.string.account_updating_device));
            this.gS.setCancelable(false);
            this.gS.show();
        }
    }

    protected void onPostExecute(Boolean bool) {
        if (!this.ir.isFinishing()) {
            if (this.gS != null) {
                this.gS.dismiss();
            }
            if (bool.booleanValue()) {
                this.ir.setResult(-1);
                this.ir.finish();
                return;
            }
            this.ir.showDialog(1);
        }
    }

    protected Boolean doInBackground(Void... voidArr) {
        if (!MiPubDeviceManager.getInstance(this.ir).uploadLoginDevice()) {
            return Boolean.valueOf(false);
        }
        Log.d("LoginActivity", "upload device info success");
        return Boolean.valueOf(true);
    }
}
