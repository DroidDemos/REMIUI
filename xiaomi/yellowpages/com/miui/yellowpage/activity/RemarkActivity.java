package com.miui.yellowpage.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.R;

public class RemarkActivity extends Activity {
    private String iT;
    private String mKey;
    private String mTitle;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTitle = getIntent().getStringExtra("android.intent.extra.TITLE");
        this.mKey = getIntent().getStringExtra("order_key");
        this.iT = getIntent().getStringExtra("order_remark");
        this.iT = this.iT == null ? ConfigConstant.WIRELESS_FILENAME : this.iT;
        gW();
    }

    private void gW() {
        Builder builder = new Builder(this);
        EditText editText = (EditText) View.inflate(this, R.layout.express_inquiry_history_remark, null);
        editText.setText(this.iT);
        editText.setSelection(this.iT.length());
        builder.setTitle(this.mTitle).setView(editText).setPositiveButton(17039370, new n(this, editText)).setNegativeButton(17039360, new p(this)).setOnCancelListener(new q(this)).setOnDismissListener(new o(this)).show();
    }
}
