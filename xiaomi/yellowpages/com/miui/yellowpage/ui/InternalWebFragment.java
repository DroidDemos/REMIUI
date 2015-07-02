package com.miui.yellowpage.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.a.a.a;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.e;
import com.miui.yellowpage.activity.ExpressAddressListActivity;
import com.miui.yellowpage.base.model.Image;
import com.miui.yellowpage.base.provider.InternalYellowPageContract.Profile;
import com.miui.yellowpage.base.provider.InternalYellowPageContract.WebResource;
import com.miui.yellowpage.base.utils.BaseWebEvent.CurrentUrlSource;
import com.miui.yellowpage.base.utils.BaseWebEvent.LOGIN_STATE;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.InternalWebEvent;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.WebService;
import com.miui.yellowpage.base.utils.XiaomiAccount;
import com.miui.yellowpage.base.utils.XiaomiAccount.LoginCallBack;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.request.BaseResult.State;
import com.miui.yellowpage.utils.I;
import com.miui.yellowpage.utils.c;
import com.miui.yellowpage.utils.x;
import com.miui.yellowpage.utils.z;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import com.xiaomi.snslib.ShareToSNS;
import com.xiaomi.snslib.ShareToSNS.SNSType;
import com.xiaomi.snslib.j;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

public class InternalWebFragment extends bw implements e {
    private static final Object sLock;
    private LoadingProgressView mLoadingProgressView;
    private String mOnAliMSPayFinish;
    private String mOnMipayFinish;
    private String mScanQRMsgId;
    private String rp;
    private volatile boolean rq;
    private WebFragmentHandler rr;
    private ProfileDataContentObserver rs;
    private OnCustomActionSetListener rt;
    private LoginCallBack ru;

    public interface OnCustomActionSetListener {
        void onLoadingProgressChanged(int i);

        void onOrderActionSet(String str, String str2);
    }

    class InternalWebChromeClient extends C {
        private InternalWebChromeClient() {
            super(InternalWebFragment.this);
        }

        public void onProgressChanged(WebView webView, int i) {
            if (InternalWebFragment.this.rt != null) {
                InternalWebFragment.this.rt.onLoadingProgressChanged(i);
            }
        }
    }

    class InternalWebViewClient extends bi {
        private InternalWebViewClient() {
            super(InternalWebFragment.this);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (x.x(InternalWebFragment.this.mActivity, str) != null || HostManager.isInternalUrl(str)) {
                boolean shouldOverrideUrlLoading = super.shouldOverrideUrlLoading(webView, str);
                if (shouldOverrideUrlLoading || InternalWebFragment.this.rt == null) {
                    return shouldOverrideUrlLoading;
                }
                InternalWebFragment.this.rt.onLoadingProgressChanged(10);
                return shouldOverrideUrlLoading;
            }
            Log.d("InternalWebFragment", "shouldOverrideUrlLoading internal url " + str + " gotoOpenWeb");
            WebBackForwardList copyBackForwardList = InternalWebFragment.this.mWebView.copyBackForwardList();
            if (copyBackForwardList == null || copyBackForwardList.getSize() == 0) {
                InternalWebFragment.this.mActivity.finish();
            }
            InternalWebFragment.this.aJ(str);
            return true;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            Log.d("InternalWebFragment", "errorCode:" + i + ", description:" + str);
            super.onReceivedError(webView, i, str, str2);
            if (i == -2) {
                InternalWebFragment.this.a(State.NETWORK_ERROR);
            } else {
                InternalWebFragment.this.a(State.SERVICE_ERROR);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            InternalWebFragment.this.rq = true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            Log.d("InternalWebFragment", "onPageStarted, url" + str);
            if (!InternalWebFragment.this.rq) {
                InternalWebFragment.this.mWebView.setVisibility(0);
                InternalWebFragment.this.mLoadingProgressView.setVisibility(8);
                ThreadPool.execute(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(30000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!InternalWebFragment.this.rq) {
                            InternalWebFragment.this.a(State.SERVICE_ERROR);
                        }
                    }
                });
            }
        }
    }

    class OnErrorTask implements Runnable {
        private State mState;

        public OnErrorTask(State state) {
            this.mState = state;
        }

        public void run() {
            InternalWebFragment.this.mLoadingProgressView.a(false, this.mState);
            if (InternalWebFragment.this.mWebView != null) {
                InternalWebFragment.this.mWebView.stopLoading();
                InternalWebFragment.this.mWebView.loadUrl("about:blank");
                InternalWebFragment.this.mWebView.clearHistory();
                InternalWebFragment.this.mWebView.setVisibility(8);
            }
        }
    }

    class ProfileDataContentObserver extends ContentObserver {
        private ProfileDataContentObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            Log.d("InternalWebFragment", "profile data changed");
            InternalWebFragment.this.rr.post(new Runnable() {
                public void run() {
                    InternalWebFragment.this.mWebEvent.handleWebEvent("profileUpdated");
                }
            });
        }
    }

    class UpdateWebResourcesTask extends AsyncTask {
        private UpdateWebResourcesTask() {
        }

        protected Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(InternalWebFragment.this.mActivity.getContentResolver().update(WebResource.CONTENT_URI, new ContentValues(), null, null) > 0);
        }

        protected void onPostExecute(Boolean bool) {
            InternalWebFragment.this.mPullToRefreshWebView.fv();
            if (!bool.booleanValue()) {
                Toast.makeText(InternalWebFragment.this.mActivity, R.string.network_unavaliable, 0).show();
            }
            InternalWebFragment.this.reload();
        }
    }

    public class WebEvent extends InternalWebEvent {
        public WebEvent(Context context, Handler handler, CurrentUrlSource currentUrlSource) {
            super(context, handler, currentUrlSource);
        }

        @JavascriptInterface
        public void saveYellowPages(final String str, final String str2) {
            Log.d("InternalWebFragment", "[saveYellowPages] hotCatId:" + str);
            ThreadPool.execute(new Runnable() {
                public void run() {
                    synchronized (InternalWebFragment.sLock) {
                        WebEvent.this.setYellowPageHotCatIdToDefault(str);
                        WebEvent.this.insertYellowPagesFromRetrievedList(str, str2);
                    }
                }
            });
        }

        @JavascriptInterface
        public boolean getAddress(String str) {
            Log.d("InternalWebFragment", "getAddress");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                InternalWebFragment.this.rp = new JSONObject(str).getString("msgid");
                if (TextUtils.isEmpty(InternalWebFragment.this.rp)) {
                    return false;
                }
                Intent intent = new Intent(InternalWebFragment.this.mActivity, ExpressAddressListActivity.class);
                intent.setAction("com.miui.yellowpage.action.PICK_ADDRESS");
                InternalWebFragment.this.startActivityForResult(intent, 0);
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        @JavascriptInterface
        public boolean startMipay(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                Object string = jSONObject.getJSONObject(MiniDefine.aM).getString("orderInfo");
                InternalWebFragment.this.mOnMipayFinish = jSONObject.getString("msgid");
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(InternalWebFragment.this.mOnMipayFinish)) {
                    return false;
                }
                a.s(InternalWebFragment.this.mActivity).a(InternalWebFragment.this, string, null);
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        @JavascriptInterface
        public boolean startAliMSPay(String str) {
            boolean z = false;
            if (!TextUtils.isEmpty(str) || this.mHandler == null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Object string = jSONObject.getJSONObject(MiniDefine.aM).getString("orderInfo");
                    InternalWebFragment.this.mOnAliMSPayFinish = jSONObject.getString("msgid");
                    if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(InternalWebFragment.this.mOnAliMSPayFinish))) {
                        z = c.a(InternalWebFragment.this.mActivity, this.mHandler, string);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return z;
        }

        @JavascriptInterface
        public boolean isMipayExist() {
            return a.t(this.mContext);
        }

        @JavascriptInterface
        public boolean isAliMSPayExist() {
            return c.r(this.mContext);
        }

        @JavascriptInterface
        public void saveImage(String str) {
            z.e(this.mContext, str, null);
        }

        @JavascriptInterface
        public void scanQR(String str) {
            Log.d("InternalWebFragment", "Start scan QR");
            try {
                InternalWebFragment.this.mScanQRMsgId = new JSONObject(str).getString("msgid");
                if (TextUtils.isEmpty(InternalWebFragment.this.mScanQRMsgId)) {
                    Log.e("InternalWebFragment", "scan QR msgid is empty");
                    return;
                }
                Log.d("InternalWebFragment", "Cur ScanQR msgid is " + InternalWebFragment.this.mScanQRMsgId);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.scanbarcode");
                intent.putExtra("isBackToThirdApp", true);
                InternalWebFragment.this.startActivityForResult(intent, 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void shareMessageToSNS(final String str) {
            Log.d("InternalWebFragment", "shareMessageToSNS");
            if (!TextUtils.isEmpty(str)) {
                ThreadPool.execute(new Runnable() {
                    public void run() {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            final String string = jSONObject.getString("msgid");
                            jSONObject = jSONObject.getJSONObject(MiniDefine.aM);
                            String string2 = jSONObject.getString("picUrl");
                            String string3 = jSONObject.getString(MiniDefine.ax);
                            SNSType sNSType = SNSType.values()[jSONObject.getInt("snsType")];
                            Object downloadImage = WebEvent.this.downloadImage(string2);
                            if (TextUtils.isEmpty(downloadImage)) {
                                WebEvent.this.onShareToSNSFinished(string, false);
                            } else {
                                ShareToSNS.a(WebEvent.this.mContext, string3, downloadImage, sNSType, new j() {
                                    public void onShareStart() {
                                    }

                                    public void onShareSuccess() {
                                        WebEvent.this.onShareToSNSFinished(string, true);
                                    }

                                    public void onShareFail() {
                                        WebEvent.this.onShareToSNSFinished(string, false);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

        protected String downloadImage(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String str2 = z.Cc + "/" + new Image(str).getName();
            if (!Files.downLoadFile(this.mContext, str, str2)) {
                return null;
            }
            Log.d("InternalWebFragment", "downloadImage " + str + " success!!");
            return str2;
        }

        @JavascriptInterface
        public void updateWebResource() {
            ThreadPool.execute(new Runnable() {
                public void run() {
                    I.ap(WebEvent.this.mContext);
                    I.ik().ak(WebEvent.this.mContext);
                }
            });
        }
    }

    class WebFragmentHandler extends Handler {
        private final WeakReference mFragment;

        public void clearReference() {
            this.mFragment.clear();
        }

        public WebFragmentHandler(InternalWebFragment internalWebFragment) {
            this.mFragment = new WeakReference(internalWebFragment);
        }

        public void handleMessage(Message message) {
            InternalWebFragment internalWebFragment = (InternalWebFragment) this.mFragment.get();
            if (internalWebFragment != null && internalWebFragment.mLoadingProgressView != null) {
                String buildCallbackResult;
                switch (message.what) {
                    case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                        if (!TextUtils.isEmpty(internalWebFragment.mOnAliMSPayFinish)) {
                            buildCallbackResult = WebService.buildCallbackResult(internalWebFragment.mOnAliMSPayFinish, "callback", 0, message.obj);
                            internalWebFragment.mWebView.loadUrl(String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", new Object[]{buildCallbackResult}));
                            Log.d("InternalWebFragment", "The callback " + buildCallbackResult);
                            return;
                        }
                        return;
                    case 14:
                        String[] split = ((String) message.obj).split(",");
                        if (internalWebFragment.rt != null) {
                            internalWebFragment.rt.onOrderActionSet(split[0], split[1]);
                            return;
                        }
                        return;
                    case 15:
                        internalWebFragment.mActivity.setTitle((String) message.obj);
                        return;
                    case Base64.NO_CLOSE /*16*/:
                    case 17:
                    case 18:
                    case 22:
                    case 23:
                    case 25:
                    case 26:
                    case 31:
                    case 34:
                    case 39:
                        buildCallbackResult = (String) message.obj;
                        internalWebFragment.mWebView.evaluateJavascript(String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", new Object[]{buildCallbackResult}), null);
                        return;
                    case Encoder.LINE_GROUPS /*19*/:
                        internalWebFragment.mLoadingProgressView.onStartLoading(((Boolean) message.obj).booleanValue());
                        return;
                    case 20:
                        internalWebFragment.mLoadingProgressView.onStopLoading(((Boolean) message.obj).booleanValue());
                        return;
                    case 21:
                        int i = message.arg1;
                        State[] values = State.values();
                        if (i >= 0 && i < values.length) {
                            internalWebFragment.mLoadingProgressView.a(((Boolean) message.obj).booleanValue(), values[i]);
                            return;
                        }
                        return;
                    case 27:
                        internalWebFragment.mActivity.finish();
                        return;
                    case 28:
                        if (!internalWebFragment.goBack()) {
                            internalWebFragment.mActivity.finish();
                            return;
                        }
                        return;
                    case 29:
                        if (!TextUtils.isEmpty(internalWebFragment.mOnMipayFinish)) {
                            buildCallbackResult = WebService.buildCallbackResult(internalWebFragment.mOnMipayFinish, "callback", 0, message.obj);
                            internalWebFragment.mWebView.loadUrl(String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", new Object[]{buildCallbackResult}));
                            Log.d("InternalWebFragment", "The callback " + buildCallbackResult);
                            return;
                        }
                        return;
                    case ConfigConstant.DEFAULT_LOCATE_INTERVAL /*30*/:
                        Toast.makeText(internalWebFragment.mActivity, R.string.toast_text_copied, 0).show();
                        return;
                    case 35:
                        internalWebFragment.disablePullToRefresh();
                        return;
                    case 36:
                        internalWebFragment.enablePullToRefresh();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    static {
        sLock = new Object();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mLoadingProgressView = (LoadingProgressView) onCreateView.findViewById(R.id.loading_view);
        this.mLoadingProgressView.a(false, false, (e) this);
        return onCreateView;
    }

    protected void onInitWebViewSettings() {
        super.onInitWebViewSettings();
        this.rr = new WebFragmentHandler(this);
        this.mWebEvent = new WebEvent(this.mActivity, this.rr, new CurrentUrlSource() {
            public String getCurrentUrl() {
                if (InternalWebFragment.this.mWebView != null) {
                    return InternalWebFragment.this.mWebView.getUrl();
                }
                return null;
            }
        });
        this.mWebEvent.setStatisticsContext(getStatisticsContext());
        this.mWebView.addJavascriptInterface(this.mWebEvent, "WE");
        this.ru = new LoginCallBack() {
            public void onLoginSuccess() {
                Log.d("InternalWebFragment", "onLoginSuccess");
                sendStateChangeMessage(LOGIN_STATE.LOGGED_IN);
            }

            public void onLoginFailed() {
                Log.d("InternalWebFragment", "onLoginFailed");
                sendStateChangeMessage(LOGIN_STATE.FAILED);
            }

            public void onInvalidToken() {
                Log.d("InternalWebFragment", "onInvalidToken");
                sendStateChangeMessage(LOGIN_STATE.INVALID_TOKEN);
            }

            public void onLogout() {
                Log.d("InternalWebFragment", "onLogout");
                sendStateChangeMessage(LOGIN_STATE.LOGGED_OUT);
            }

            private void sendStateChangeMessage(final LOGIN_STATE login_state) {
                InternalWebFragment.this.rr.post(new Runnable() {
                    public void run() {
                        InternalWebFragment.this.mWebEvent.handleWebEvent("loginStateChanged", login_state.getDescription());
                    }
                });
            }
        };
        XiaomiAccount.registerLoginCallBackListener(this.mActivity, this.ru);
    }

    public void onDestroyView() {
        super.onDestroyView();
        XiaomiAccount.unregisterLoginCallBackListener(this.mActivity, this.ru);
        this.ru = null;
    }

    public void a(OnCustomActionSetListener onCustomActionSetListener) {
        this.rt = onCustomActionSetListener;
    }

    public boolean onBackPressed() {
        if (this.mWebEvent.handleWebEvent("backPressed")) {
            return true;
        }
        return super.onBackPressed();
    }

    public boolean handleHomePressed() {
        return this.mWebEvent.handleWebEvent("homePressed");
    }

    public void reload() {
        Log.d("InternalWebFragment", "reload");
        loadUrl(this.mWebView.getUrl());
    }

    public void loadUrl(String str) {
        if (HostManager.isInternalUrl(str)) {
            super.loadUrl(str);
            Log.d("InternalWebFragment", "loadUrl: " + str);
            this.rq = false;
            if (this.rt != null) {
                this.rt.onLoadingProgressChanged(10);
                return;
            }
            return;
        }
        Log.d("InternalWebFragment", "loadUrl: open url " + str);
        if (!this.mWebView.canGoBack()) {
            Log.d("InternalWebFragment", "Can not go back, finish current sesssion");
            this.mActivity.finish();
        }
        aJ(str);
    }

    private void aJ(String str) {
        Intent intent = new Intent("com.miui.yellowpage.action.LOAD_OPEN_WEBVIEW");
        intent.putExtra("web_url", str);
        this.mActivity.startActivity(intent);
    }

    protected WebChromeClient onCreateWebChromeClient() {
        return new InternalWebChromeClient();
    }

    private void a(State state) {
        this.rq = true;
        if (this.mActivity != null) {
            this.mActivity.runOnUiThread(new OnErrorTask(state));
        }
    }

    protected int onGetViewId() {
        return R.layout.internal_web_view;
    }

    protected WebViewClient onCreateWebViewClient() {
        return new InternalWebViewClient();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                Object buildCallbackResult;
                if (i2 != -1 || intent == null) {
                    buildCallbackResult = WebService.buildCallbackResult(this.rp, "callback", 1, null);
                } else {
                    buildCallbackResult = WebService.buildCallbackResult(this.rp, "callback", 0, ExpressAddress.a((Address) intent.getExtras().getParcelable("extra_address")).eW());
                }
                this.mWebEvent.sendAsyncCallbackMessage(26, buildCallbackResult);
                return;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                onScanQRResult(i2, intent);
                return;
            case 20140424:
                this.mWebEvent.sendAsyncCallbackMessage(29, intent == null ? ConfigConstant.WIRELESS_FILENAME : intent.getStringExtra(GlobalDefine.g));
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    private void onScanQRResult(int i, Intent intent) {
        if (i != -1) {
            return;
        }
        if (TextUtils.isEmpty(this.mScanQRMsgId)) {
            Log.e("InternalWebFragment", "scan QR msg id is empty, can not parse result.");
            return;
        }
        Object obj;
        if (intent == null || intent.getExtras() == null) {
            obj = null;
        } else {
            obj = intent.getExtras().getString(GlobalDefine.g);
        }
        String buildCallbackResult = WebService.buildCallbackResult(this.mScanQRMsgId, "callback", 0, obj);
        this.mScanQRMsgId = null;
        this.mWebEvent.sendAsyncCallbackMessage(31, buildCallbackResult);
        Log.d("InternalWebFragment", "Scan QR finished");
    }

    public void onDestroy() {
        super.onDestroy();
        this.rr.clearReference();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dw();
    }

    public void onDetach() {
        super.onDetach();
        dx();
    }

    private void dw() {
        ContentResolver contentResolver = this.mActivity.getContentResolver();
        this.rs = new ProfileDataContentObserver(this.rr);
        contentResolver.registerContentObserver(Profile.CONTENT_URI, true, this.rs);
    }

    private void dx() {
        this.mActivity.getContentResolver().unregisterContentObserver(this.rs);
    }

    protected com.miui.yellowpage.widget.pulltorefresh.a getOnRefreshListener() {
        return new com.miui.yellowpage.widget.pulltorefresh.a() {
            public void onRefresh(PullToRefreshBase pullToRefreshBase) {
                I.ik();
                I.ap(InternalWebFragment.this.mActivity);
                new UpdateWebResourcesTask().execute(new Void[0]);
            }
        };
    }
}
