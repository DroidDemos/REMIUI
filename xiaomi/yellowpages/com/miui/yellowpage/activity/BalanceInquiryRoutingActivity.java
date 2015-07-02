package com.miui.yellowpage.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.BalanceInquirySmsActionMap;
import com.miui.yellowpage.base.model.action.SmsAction;
import com.miui.yellowpage.base.utils.Contact;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.utils.D;

public class BalanceInquiryRoutingActivity extends Activity {
    private BalanceInquirySmsActionMap Gi;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.Gi = BalanceInquirySmsActionMap.fromJson(getIntent().getExtras().getString("smsList"));
        int simCount = Sim.getSimCount(this);
        if (simCount == 1) {
            Builder builder = new Builder(this);
            builder.setTitle(R.string.balance_inquiry_title);
            builder.setMessage(R.string.balance_inquiry_send_sms_hint);
            builder.setOnCancelListener(new T(this)).setPositiveButton(17039370, new S(this)).setNegativeButton(17039360, new R(this));
            builder.show();
        } else if (simCount > 1) {
            D.a(this, getString(R.string.packages_select_sim_cards_title), getString(R.string.balance_inquiry_send_sms_summmary), new U(this));
        } else {
            Toast.makeText(this, R.string.balance_inquiry_send_sms_fail_hint, 1).show();
            finish();
        }
    }

    private void b(int i, String str) {
        Log.d("BalanceInquiryRoutingActivity", "onGetSimIndexAndOperatorCode");
        SmsAction byOperatorCode = this.Gi.getByOperatorCode(str);
        if (byOperatorCode != null) {
            String content = byOperatorCode.getContent();
            String number = byOperatorCode.getNumber();
            Contact.directlySendSms(getApplicationContext(), number, content, Contact.getSmsSendIntent(getApplicationContext(), content, number), i);
            return;
        }
        Toast.makeText(this, R.string.balance_inquiry_send_sms_fail_hint, 1).show();
    }
}
