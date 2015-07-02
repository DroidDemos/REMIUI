package com.miui.yellowpage.activity;

import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.mipub.MiPubDeviceManager;
import com.miui.yellowpage.base.utils.BusinessStatistics.StatsContext;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Permission;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.WebService;
import com.miui.yellowpage.base.utils.XiaomiAccount;
import com.miui.yellowpage.ui.cs;
import miui.app.ActionBar;
import miui.app.Activity;

public class BaseActivity extends Activity {
    private static final int REQUEST_CHECK_PERMISSION_CODE = 2;
    private static final int REQUEST_LOGIN_CODE = 1;
    private static final String TAG = "BaseActivity";
    private static final String YELLOW_PAGE_PREFIX = "yellowpage://";
    protected ActionBar mActionBar;
    private ActivityOptions mActivityOptions;
    protected WebView mBannerView;
    protected O mPermissionRequestListener;
    protected StatsContext mStatsContext;

    protected boolean requireLogin() {
        return false;
    }

    protected boolean requireNetworking() {
        return true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!hasCustomContentView()) {
            setContentView(R.layout.base_activity);
            if (Permission.networkingAllowed(this) && supportsBanner()) {
                configureBanner();
            }
        }
        initActionBar();
        this.mActivityOptions = ActivityOptions.makeCustomAnimation(this, R.anim.activity_open_enter, R.anim.activity_open_exit);
        initStatsContext();
    }

    private void initStatsContext() {
        this.mStatsContext = StatsContext.parse(getIntent());
    }

    protected Bundle parseQueryParams() {
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        }
        if (!"android.intent.action.VIEW".equals(intent.getAction())) {
            return null;
        }
        Uri data = intent.getData();
        Bundle bundle = new Bundle();
        for (String str : data.getQueryParameterNames()) {
            bundle.putString(str, data.getQueryParameter(str));
        }
        return bundle;
    }

    public StatsContext getStatisticsContext() {
        return this.mStatsContext;
    }

    protected boolean supportsBanner() {
        return true;
    }

    protected boolean hasCustomContentView() {
        return false;
    }

    protected void configureBanner() {
        ThreadPool.execute(new ao());
    }

    protected String getBannerLocation() {
        return YELLOW_PAGE_PREFIX + getClass().getSimpleName();
    }

    protected void onResume() {
        super.onResume();
        checkNetworking();
        checkLogin();
    }

    private void checkLogin() {
        if (!requireLogin()) {
            return;
        }
        if (!XiaomiAccount.hasLogin(this)) {
            login("account");
        } else if (!MiPubDeviceManager.getInstance(this).isDeviceLogin()) {
            login("device");
        }
    }

    private void checkNetworking() {
        if (requireNetworking() && !Permission.networkingAllowed(this)) {
            startActivityForResult(Permission.createUserNoticeIntent(), REQUEST_CHECK_PERMISSION_CODE);
        }
    }

    private void login(String str) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("service_token_id", "spbook");
        intent.putExtra("android.intent.extra.TITLE", this.mActionBar.getTitle());
        intent.putExtra("com.miui.yellowpage.extra.LOGIN_TYPE", str);
        startActivityForResult(intent, REQUEST_LOGIN_CODE);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case REQUEST_LOGIN_CODE /*1*/:
                if (i2 == 0) {
                    finish();
                    return;
                }
                return;
            case REQUEST_CHECK_PERMISSION_CODE /*2*/:
                if (i2 == 0) {
                    if (this.mPermissionRequestListener != null) {
                        this.mPermissionRequestListener.w(false);
                        return;
                    } else {
                        finish();
                        return;
                    }
                } else if (i2 == -1 && this.mPermissionRequestListener != null) {
                    this.mPermissionRequestListener.w(true);
                    return;
                } else {
                    return;
                }
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    protected void initActionBar() {
        this.mActionBar = getActionBar();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    protected cs newFragmentByTag(String str) {
        return null;
    }

    public cs showFragment(String str, String str2, Bundle bundle, boolean z) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        cs csVar = (cs) fragmentManager.findFragmentByTag(str);
        if (csVar == null) {
            csVar = newFragmentByTag(str);
            if (bundle != null) {
                csVar.setArguments(bundle);
            }
        }
        if (csVar == null) {
            return null;
        }
        beginTransaction.setTransition(4099);
        beginTransaction.replace(R.id.content, csVar, str);
        if (!TextUtils.isEmpty(str2)) {
            this.mActionBar.setTitle(str2);
        }
        if (z) {
            beginTransaction.addToBackStack(str);
        }
        beginTransaction.commitAllowingStateLoss();
        notifyFragmentChanges(str);
        return csVar;
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent, this.mActivityOptions.toBundle());
    }

    public void setTitle(int i) {
        if (this.mActionBar != null) {
            this.mActionBar.setTitle(i);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (this.mActionBar != null) {
            this.mActionBar.setTitle(charSequence);
        }
    }

    private void notifyFragmentChanges(String str) {
        if (supportsBanner() && this.mBannerView != null && this.mBannerView.getVisibility() == 0) {
            String buildCallbackResult = WebService.buildCallbackResult("show_fragment", "event", 0, str);
            WebView webView = this.mBannerView;
            Object[] objArr = new Object[REQUEST_LOGIN_CODE];
            objArr[0] = buildCallbackResult;
            webView.loadUrl(String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", objArr));
            Log.d(TAG, "notify fragment change");
        }
    }
}
