package com.miui.yellowpage.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.utils.x;
import miui.telephony.PhoneNumberUtils;

/* compiled from: BaseWebFragment */
public class bi extends WebViewClient {
    final /* synthetic */ bw this$0;

    public bi(bw bwVar) {
        this.this$0 = bwVar;
    }

    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        Log.d("BaseWebFragment", "onReceivedLoginRequest " + str);
        AccountManager accountManager = AccountManager.get(this.this$0.mActivity);
        Account[] accountsByType = accountManager.getAccountsByType(str);
        if (accountsByType == null || accountsByType.length == 0) {
            Log.d("BaseWebFragment", "onReceivedLoginRequest: account not login, should not happen, because the user must login before browsing the service");
            super.onReceivedLoginRequest(webView, str, str2, str3);
            return;
        }
        accountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, null, this.this$0.mActivity, new bY(this, webView), null);
        super.onReceivedLoginRequest(webView, str, str2, str3);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Log.d("BaseWebFragment", "shouldOverrideUrlLoading [" + str + "]");
        Intent x = x.x(this.this$0.mActivity, str);
        if (x == null) {
            return false;
        }
        Uri parse = Uri.parse(str);
        if (TextUtils.equals(parse.getScheme(), "tel")) {
            Object formatNumber = PhoneNumberUtils.formatNumber(parse.getSchemeSpecificPart());
            if (!TextUtils.isEmpty(formatNumber)) {
                Builder builder = new Builder(this.this$0.mActivity);
                builder.setTitle(formatNumber);
                builder.setPositiveButton(R.string.call, new bX(this, x, formatNumber, webView));
                builder.setNegativeButton(17039360, null);
                builder.create().show();
            }
        } else {
            if (TextUtils.equals(parse.getScheme(), "sms")) {
                String schemeSpecificPart = parse.getSchemeSpecificPart();
                int indexOf = schemeSpecificPart.indexOf(63);
                if (indexOf != -1) {
                    schemeSpecificPart = schemeSpecificPart.substring(0, indexOf);
                }
                Log.d("BaseWebFragment", "Sms the scheme specific part is " + schemeSpecificPart);
                BusinessStatistics.clickWebViewNumber(this.this$0.mActivity, PhoneNumberUtils.formatNumber(schemeSpecificPart), webView.getUrl(), "sms", this.this$0.getStatisticsContext().getSourceModuleId());
            }
            this.this$0.startActivity(x);
        }
        return true;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.this$0.injectOpenJS();
        Log.d("BaseWebFragment", "onPageFinished [" + str + "]");
        this.this$0.onPageForwardOrBackward();
        this.this$0.mPullToRefreshWebView.fv();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.this$0.mPullToRefreshWebView.fv();
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        Log.d("BaseWebFragment", "onPageStarted [" + str + "]");
        BusinessStatistics.viewWebPageUrl(this.this$0.mActivity, str, this.this$0.getStatisticsContext().getSource(), this.this$0.getStatisticsContext().getSourceModuleId());
    }
}
