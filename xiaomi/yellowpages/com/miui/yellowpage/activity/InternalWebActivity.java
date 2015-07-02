package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import com.miui.yellowpage.R;
import com.miui.yellowpage.ui.InternalWebFragment;
import com.miui.yellowpage.ui.InternalWebFragment.OnCustomActionSetListener;

public class InternalWebActivity extends BaseWebActivity {
    private Button iB;
    private String mMerchantId;
    private String mMerchantName;
    private ProgressBar mProgressBar;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void customizeActionBar() {
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mActionBar.setCustomView(R.layout.web_action_bar_custom_view);
        this.mCustomView = this.mActionBar.getCustomView();
        this.mProgressBar = (ProgressBar) this.mCustomView.findViewById(R.id.progress_bar);
        this.iB = (Button) this.mCustomView.findViewById(R.id.history_btn);
        ((InternalWebFragment) this.mWebFragment).a(new OnCustomActionSetListener() {
            public void onLoadingProgressChanged(int i) {
                int i2 = (i <= 0 || i >= 100) ? 4 : 0;
                InternalWebActivity.this.mProgressBar.setVisibility(i2);
            }

            public void onOrderActionSet(String str, String str2) {
                InternalWebActivity.this.mMerchantName = str2;
                InternalWebActivity.this.mMerchantId = str;
                InternalWebActivity.this.aK();
            }
        });
    }

    private void aK() {
        if (TextUtils.isEmpty(this.mMerchantId) && TextUtils.isEmpty(this.mMerchantName)) {
            this.iB.setVisibility(8);
            return;
        }
        this.iB.setVisibility(0);
        this.iB.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("com.miui.yellowpage.action.ORDER");
                intent.setData(Uri.parse("yellowpage://order?view=merchant&id=" + Uri.encode(InternalWebActivity.this.mMerchantId) + "&name=" + Uri.encode(InternalWebActivity.this.mMerchantName)));
                InternalWebActivity.this.startActivity(intent);
            }
        });
    }

    protected int getWebFragmentResId() {
        return R.id.web_fragment;
    }

    protected int getContentViewResId() {
        return R.layout.internal_web_activity;
    }

    protected void onMenuHome() {
        if (!((InternalWebFragment) this.mWebFragment).handleHomePressed()) {
            finish();
        }
    }
}
