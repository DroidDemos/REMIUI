package com.xiaomi.account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.account.ui.AccountWebActivity;
import com.xiaomi.passport.Constants;
import miui.accounts.ExtraAccountManager;

public class BarCodeScannerReceiver extends BroadcastReceiver {
    private static final String KEY_RESULT = "result";
    private static final String TAG = "BarCodeScannerReceiver";

    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "com.miui.barcodescanner.receiver.senderbarcodescanner")) {
            String url = intent.getStringExtra(KEY_RESULT);
            if (!TextUtils.isEmpty(url) && url.contains("account.xiaomi.com/longPolling/login")) {
                proccessBarCodeResult(context, url);
                abortBroadcast();
            }
        }
    }

    private void proccessBarCodeResult(Context context, String url) {
        if (ExtraAccountManager.getXiaomiAccount(context) != null) {
            startLoginWebPage(context, url);
        } else {
            startAddAccountIntent(context);
        }
    }

    private void startLoginWebPage(Context context, String url) {
        Intent webViewIntent = new Intent(context, AccountWebActivity.class);
        webViewIntent.putExtra(AccountWebActivity.EXTRA_REQUEST_URL, url);
        webViewIntent.addFlags(268435456);
        context.startActivity(webViewIntent);
    }

    private void startAddAccountIntent(Context context) {
        Intent newAccountIntent = new Intent(Constants.ACTION_WELCOME);
        newAccountIntent.setPackage(context.getPackageName());
        newAccountIntent.putExtra(Constants.EXTRA_ADD_ACCOUNT_PROMPT, context.getString(R.string.barcode_add_account_prompt));
        newAccountIntent.addFlags(335544320);
        context.startActivity(newAccountIntent);
    }
}
