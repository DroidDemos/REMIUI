package com.xiaomi.account.ui;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.account.data.SnsAccountInfo;
import com.xiaomi.account.utils.SysHelper;
import miui.accounts.ExtraAccountManager;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;

public class SnsAddAccountActivity extends Activity {
    private static final String TAG = "SnsAddAccountActivity";
    private Account mAccount;
    private SnsAccountInfo mSnsAccountInfo;

    public static void start(Context context, String snsType) {
        Intent intent = new Intent(context, SnsAddAccountActivity.class);
        intent.putExtra("extra_sns_type", snsType);
        context.startActivity(intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sns_add_account);
        this.mAccount = ExtraAccountManager.getXiaomiAccount(this);
        if (this.mAccount == null) {
            Log.i(TAG, "no xiaomi account");
            finish();
            return;
        }
        String bindDsptStr;
        this.mSnsAccountInfo = SnsAccountInfo.newSnsAccountInfo(getIntent().getStringExtra("extra_sns_type"));
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(this.mSnsAccountInfo.getTitleResId());
        }
        ((TextView) findViewById(R.id.add_account_title)).setText(this.mSnsAccountInfo.getTitleResId());
        if (this.mSnsAccountInfo.getType().equals(SnsAccountInfo.SINA_SNS_TYPE)) {
            bindDsptStr = getString(R.string.bind_weibo_description);
        } else {
            bindDsptStr = String.format(getString(R.string.bind_sns_description), new Object[]{getString(this.mSnsAccountInfo.getTypeName())});
        }
        ((TextView) findViewById(R.id.add_account_dspt)).setText(bindDsptStr);
        ((ImageView) findViewById(R.id.add_account_img)).setImageResource(this.mSnsAccountInfo.getAddImgResId());
        ((Button) findViewById(R.id.add_account_btn)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SnsWebViewActivity.start(SnsAddAccountActivity.this, SnsAddAccountActivity.this.mSnsAccountInfo.getType(), SnsAddAccountActivity.this.getIntent().getBooleanExtra(SnsWebViewActivity.EXTRA_SHOW_SNS_ACCOUNT_AFTER_BINDING_SUCCESS, true));
                SnsAddAccountActivity.this.finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (!Build.IS_TABLET) {
            SysHelper.setOrientationPortrait(this);
        }
    }
}
