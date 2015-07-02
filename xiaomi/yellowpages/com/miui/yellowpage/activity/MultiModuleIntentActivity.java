package com.miui.yellowpage.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListAdapter;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.mipub.MiPubDeviceManager;
import com.miui.yellowpage.base.model.Module.Action;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;
import com.miui.yellowpage.base.model.action.DropdownMenuAction;
import com.miui.yellowpage.base.model.action.MiPubClickAction;
import com.miui.yellowpage.base.model.action.OperatorSpecificSmsAction;
import com.miui.yellowpage.base.model.action.SmsAction;
import com.miui.yellowpage.base.model.action.WebViewAction;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.Contact;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.base.utils.XiaomiAccount;
import com.miui.yellowpage.model.YellowPageDataDetailEntry.Type;
import com.miui.yellowpage.utils.D;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.Iterator;
import java.util.List;

public class MultiModuleIntentActivity extends BaseActivity {
    private long ld;
    private MiPubDeviceManager lm;
    private YellowPageModuleEntry oD;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.lm = MiPubDeviceManager.getInstance(getApplicationContext());
        this.oD = (YellowPageModuleEntry) getIntent().getSerializableExtra("com.miui.yellowpage.extra.MULTI_MODULE_ENTRY");
        this.ld = getIntent().getLongExtra("com.miui.yellowpage.extra.yid", 0);
        Log.d("MultiModuleIntentActivity", "The source " + getStatisticsContext().getSource() + " and yellowpage id " + this.ld);
        if (this.oD == null) {
            Log.d("MultiModuleIntentActivity", "The module entry should no be null");
            finish();
        } else if (!XiaomiAccount.hasLogin(this)) {
            login("account");
        } else if (!this.lm.isDeviceLogin()) {
            login("device");
        } else if (this.oD.getModuleTplId() == 12) {
            ct();
        } else {
            cs();
        }
    }

    protected boolean supportsBanner() {
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                if (i2 != -1) {
                    finish();
                    return;
                } else if (!XiaomiAccount.hasLogin(this) || !this.lm.isDeviceLogin()) {
                    return;
                } else {
                    if (this.oD.getModuleTplId() == 12) {
                        ct();
                        return;
                    } else {
                        cs();
                        return;
                    }
                }
            default:
                finish();
                return;
        }
    }

    private void login(String str) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("service_token_id", "spbook");
        intent.putExtra("android.intent.extra.TITLE", this.oD.getTitle());
        intent.putExtra("com.miui.yellowpage.extra.LOGIN_TYPE", str);
        startActivityForResult(intent, 0);
    }

    private void cs() {
        if (this.oD.getModuleTplId() == 0) {
            startActivity(this.oD.getAction().toIntent());
            finish();
        } else if (this.oD.getModuleTplId() == 1) {
            a((SmsAction) this.oD.getAction());
        } else if (this.oD.getAction() != null) {
            Intent toIntent = this.oD.getAction().toIntent();
            if (TextUtils.equals(toIntent.getAction(), "android.intent.action.CREATE_SHORTCUT")) {
                startActivity(toIntent, null);
            } else {
                startActivity(this.oD.getAction().toIntent());
            }
            finish();
        }
        BusinessStatistics.clickYellowPageItem(this, String.valueOf(this.ld), Type.MODULE.ar(), String.valueOf(this.oD.getModuleId()), getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
    }

    private void ct() {
        Action action = this.oD.getAction();
        if (action instanceof DropdownMenuAction) {
            a((DropdownMenuAction) action);
            return;
        }
        Intent toIntent = action.toIntent();
        if (action instanceof WebViewAction) {
            startActivity(toIntent);
            finish();
        } else {
            startService(toIntent);
            finish();
        }
        a(action);
    }

    private void a(Action action) {
        String ar;
        String url;
        if (action instanceof WebViewAction) {
            ar = Type.MI_PUB_VIEW.ar();
            url = ((WebViewAction) action).getUrl();
        } else {
            ar = Type.MI_PUB_CLICK.ar();
            url = ((MiPubClickAction) action).getStatisticKey();
        }
        BusinessStatistics.clickYellowPageItem(this, String.valueOf(this.ld), ar, url, getStatisticsContext().getSource(), getStatisticsContext().getSourceModuleId());
    }

    private void a(DropdownMenuAction dropdownMenuAction) {
        Builder builder = new Builder(this);
        builder.setTitle(dropdownMenuAction.getName());
        ListAdapter wVar = new w(this, this, 17367043);
        Iterator it = dropdownMenuAction.getDropdownActions().iterator();
        while (it.hasNext()) {
            wVar.add((Action) it.next());
        }
        builder.setAdapter(wVar, new v(this, wVar));
        builder.show();
    }

    private void a(SmsAction smsAction) {
        int readySimIndexOnOneInserted;
        int i = 0;
        int i2 = Sim.DEFAULT_SIM_INDEX;
        if (Sim.getSimCount(this) <= 1) {
            readySimIndexOnOneInserted = Sim.getReadySimIndexOnOneInserted(this);
        } else if (smsAction instanceof OperatorSpecificSmsAction) {
            List operators = ((OperatorSpecificSmsAction) smsAction).getOperators();
            String simOpCode = Sim.getSimOpCode(this, 0);
            String simOpCode2 = Sim.getSimOpCode(this, 1);
            Log.d("MultiModuleIntentActivity", "operators:" + operators);
            Log.d("MultiModuleIntentActivity", "matersOp:" + simOpCode);
            Log.d("MultiModuleIntentActivity", "slaveOp:" + simOpCode2);
            boolean contains = operators.contains(simOpCode);
            boolean contains2 = operators.contains(simOpCode2);
            if (contains && contains2) {
                readySimIndexOnOneInserted = i2;
                i = 1;
            } else if (contains) {
                readySimIndexOnOneInserted = 0;
            } else if (contains2) {
                readySimIndexOnOneInserted = 1;
            } else {
                readySimIndexOnOneInserted = i2;
            }
        } else {
            readySimIndexOnOneInserted = i2;
            i = 1;
        }
        if (i != 0) {
            D.a(this, getString(R.string.packages_select_sim_cards_title), getString(R.string.balance_inquiry_send_sms_summmary), new u(this, smsAction));
            return;
        }
        a(smsAction, readySimIndexOnOneInserted);
        finish();
    }

    private void a(SmsAction smsAction, int i) {
        String content = smsAction.getContent();
        String number = smsAction.getNumber();
        Contact.directlySendSms(this, number, content, Contact.getSmsSendIntent(this, content, number), i);
        Log.d("MultiModuleIntentActivity", "send " + content + " to " + number + " with sim:" + i);
    }
}
