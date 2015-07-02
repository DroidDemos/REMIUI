package com.miui.yellowpage.openapi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import com.a.a.a;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.e;
import com.miui.yellowpage.base.utils.BaseWebEvent.CurrentUrlSource;
import com.miui.yellowpage.base.utils.GeoLocationManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Network;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.WebService;
import com.miui.yellowpage.openapi.PermissionManager.OnCheckFinishedListener;
import com.miui.yellowpage.request.BaseResult.State;
import com.miui.yellowpage.ui.C;
import com.miui.yellowpage.ui.bi;
import com.miui.yellowpage.ui.bw;
import com.miui.yellowpage.utils.c;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import miui.app.AlertDialog.Builder;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenWebFragment extends bw implements OnClickListener, e {
    private static final int MENU_ORDER_ID = 0;
    private static final int REQUEST_CODE_SCAN_QR = 100;
    private static final String TAG = "OpenWebFragment";
    private ImageButton mBackButton;
    private View mBottomBarContainer;
    private Map mCachedPermissions;
    private View mContainer;
    private MerchantInfo mCurrentMerchant;
    private WebFragmentHandler mHandler;
    private Location mLastKnownLocation;
    private State mLoadState;
    private LoadingProgressView mLoadingProgressView;
    private Runnable mNotifyLocationRunnable;
    private String mOnAliMSPayFinish;
    private String mOnMipayFinish;
    private OnOptionsMenuUpdatedListener mOnOptionsMenuUpdatedListener;
    private Dialog mPermissionDialog;
    private PermissionManager mPermissionManager;
    private ImageButton mRefreshButton;
    private String mScanQRMsgId;
    private Runnable mUpdateLocationRunnable;

    public interface OnOptionsMenuUpdatedListener {
        void onOptionsMenuUpdated();
    }

    interface OnRequestFinishedListener {
        void onRequestFinished(boolean z);
    }

    class OpenWebChromeClient extends C {
        private OpenWebChromeClient() {
            super(OpenWebFragment.this);
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            OpenWebFragment.this.mActivity.setTitle(str);
        }

        public void onGeolocationPermissionsShowPrompt(final String str, final Callback callback) {
            OpenWebFragment.this.requestWebPermission("com.miui.yellowpage.permission.LOCATION", new OnRequestFinishedListener() {
                public void onRequestFinished(boolean z) {
                    callback.invoke(str, z, false);
                }
            });
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (OpenWebFragment.this.mLoadingProgressView != null && i - OpenWebFragment.this.mLoadingProgressView.getProgress() > 0 && i >= 0 && i <= OpenWebFragment.REQUEST_CODE_SCAN_QR) {
                OpenWebFragment.this.mLoadingProgressView.setProgress(i);
            }
        }
    }

    class OpenWebviewClient extends bi {
        private OpenWebviewClient() {
            super(OpenWebFragment.this);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            boolean shouldOverrideUrlLoading = super.shouldOverrideUrlLoading(webView, str);
            OpenWebFragment.this.mLoadState = State.OK;
            if (!shouldOverrideUrlLoading) {
                OpenWebFragment.this.mLoadingProgressView.setProgress(10);
                OpenWebFragment.this.mLoadingProgressView.onStartLoading(false);
            }
            return shouldOverrideUrlLoading;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (OpenWebFragment.this.mLoadState != State.OK) {
                OpenWebFragment.this.mLoadingProgressView.a(false, OpenWebFragment.this.mLoadState);
                OpenWebFragment.this.mContainer.setVisibility(8);
                return;
            }
            OpenWebFragment.this.mLoadingProgressView.onStopLoading(false);
            OpenWebFragment.this.mLoadingProgressView.setVisibility(8);
            OpenWebFragment.this.mContainer.setVisibility(OpenWebFragment.MENU_ORDER_ID);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            if (Network.isNetWorkConnected(OpenWebFragment.this.mActivity)) {
                OpenWebFragment.this.mLoadState = State.SERVICE_ERROR;
            } else {
                OpenWebFragment.this.mLoadState = State.NETWORK_ERROR;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }
    }

    class WebFragmentHandler extends Handler {
        private final WeakReference mFragment;

        public void clearReference() {
            this.mFragment.clear();
        }

        public WebFragmentHandler(OpenWebFragment openWebFragment) {
            this.mFragment = new WeakReference(openWebFragment);
        }

        public void handleMessage(Message message) {
            OpenWebFragment openWebFragment = (OpenWebFragment) this.mFragment.get();
            if (openWebFragment != null) {
                String str = (String) message.obj;
                switch (message.what) {
                    case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                        if (!TextUtils.isEmpty(openWebFragment.mOnAliMSPayFinish)) {
                            openWebFragment.mWebView.loadUrl(String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", new Object[]{WebService.buildCallbackResult(openWebFragment.mOnAliMSPayFinish, "callback", OpenWebFragment.MENU_ORDER_ID, str)}));
                            Log.d(OpenWebFragment.TAG, "The callback " + str);
                            return;
                        }
                        return;
                    case 11:
                        openWebFragment.mActivity.setTitle(str);
                        return;
                    case 12:
                        openWebFragment.mWebView.loadUrl(String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", new Object[]{str}));
                        Log.d(OpenWebFragment.TAG, "The callback " + str);
                        return;
                    case 13:
                        openWebFragment.mBottomBarContainer.setVisibility(Boolean.valueOf(str).booleanValue() ? OpenWebFragment.MENU_ORDER_ID : 8);
                        return;
                    case 14:
                        String[] split = str.split(",");
                        openWebFragment.mCurrentMerchant = new MerchantInfo(split[OpenWebFragment.MENU_ORDER_ID], split[1]);
                        if (openWebFragment.mOnOptionsMenuUpdatedListener != null && openWebFragment.mCurrentMerchant.isValid()) {
                            openWebFragment.mOnOptionsMenuUpdatedListener.onOptionsMenuUpdated();
                            return;
                        }
                        return;
                    case Base64.NO_CLOSE /*16*/:
                    case 22:
                    case 23:
                    case ConfigConstant.DEFAULT_LOCATE_LINES /*24*/:
                    case 31:
                        openWebFragment.mWebView.loadUrl(String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", new Object[]{str}));
                        Log.d(OpenWebFragment.TAG, "The callback " + str);
                        return;
                    case 27:
                        openWebFragment.mActivity.finish();
                        return;
                    case 28:
                        if (!openWebFragment.goBack()) {
                            openWebFragment.mActivity.finish();
                            return;
                        }
                        return;
                    case 29:
                        if (!TextUtils.isEmpty(openWebFragment.mOnMipayFinish)) {
                            openWebFragment.mWebView.loadUrl(String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", new Object[]{WebService.buildCallbackResult(openWebFragment.mOnMipayFinish, "callback", OpenWebFragment.MENU_ORDER_ID, str)}));
                            Log.d(OpenWebFragment.TAG, "The callback " + str);
                            return;
                        }
                        return;
                    case 35:
                        openWebFragment.disablePullToRefresh();
                        return;
                    case 36:
                        openWebFragment.enablePullToRefresh();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public class YellowPageJSBridge extends OpenWebEvent {
        public YellowPageJSBridge(Context context, Handler handler, CurrentUrlSource currentUrlSource) {
            super(context, handler, currentUrlSource);
        }

        @JavascriptInterface
        public void requestLocation() {
            Log.d(OpenWebFragment.TAG, "request location");
            OpenWebFragment.this.requestWebPermission("com.miui.yellowpage.permission.LOCATION", new OnRequestFinishedListener() {
                public void onRequestFinished(boolean z) {
                    if (z) {
                        OpenWebFragment.this.requestNetworkLocationUpdateListener();
                    }
                }
            });
        }

        @JavascriptInterface
        public void cancelLocationRequest() {
            OpenWebFragment.this.removeNetworkLocationUpdateListener();
            if (this.mHandler != null) {
                this.mHandler.removeCallbacks(OpenWebFragment.this.mNotifyLocationRunnable);
            }
            Log.d(OpenWebFragment.TAG, "cancel location request");
        }

        @JavascriptInterface
        public boolean startMipay(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Object string = jSONObject.getJSONObject(MiniDefine.aM).getString("orderInfo");
                    OpenWebFragment.this.mOnMipayFinish = jSONObject.getString("msgid");
                    if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(OpenWebFragment.this.mOnMipayFinish))) {
                        a.s(OpenWebFragment.this.mActivity).a(OpenWebFragment.this, string, null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return false;
        }

        @JavascriptInterface
        public boolean startAliMSPay(String str) {
            boolean z = false;
            if (!TextUtils.isEmpty(str) || this.mHandler == null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Object string = jSONObject.getJSONObject(MiniDefine.aM).getString("orderInfo");
                    OpenWebFragment.this.mOnAliMSPayFinish = jSONObject.getString("msgid");
                    if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(OpenWebFragment.this.mOnAliMSPayFinish))) {
                        z = c.a(OpenWebFragment.this.mActivity, this.mHandler, string);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return z;
        }

        @JavascriptInterface
        public boolean getLocation(final String str) {
            Log.d(OpenWebFragment.TAG, "getLocation");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return OpenWebFragment.this.requestWebPermission("com.miui.yellowpage.permission.LOCATION", new OnRequestFinishedListener() {
                public void onRequestFinished(boolean z) {
                    if (z) {
                        OpenWebFragment.this.getLocationForJS(str);
                    }
                }
            });
        }

        @JavascriptInterface
        public void scanQR(final String str) {
            Log.d(OpenWebFragment.TAG, "Start Scan QR");
            OpenWebFragment.this.requestWebPermission("com.miui.yellowpage.permission.CAMERA", new OnRequestFinishedListener() {
                public void onRequestFinished(boolean z) {
                    if (z) {
                        YellowPageJSBridge.this.scanQRInJS(str);
                    } else {
                        Log.d(OpenWebFragment.TAG, "user dont allow CAMERA permission");
                    }
                }
            });
        }

        private void scanQRInJS(String str) {
            try {
                OpenWebFragment.this.mScanQRMsgId = new JSONObject(str).getString("msgid");
                if (TextUtils.isEmpty(OpenWebFragment.this.mScanQRMsgId)) {
                    Log.e(OpenWebFragment.TAG, "scan QR msgid is empty");
                    return;
                }
                Log.d(OpenWebFragment.TAG, "Cur ScanQR msgid is " + OpenWebFragment.this.mScanQRMsgId);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.scanbarcode");
                intent.putExtra("isBackToThirdApp", true);
                OpenWebFragment.this.startActivityForResult(intent, OpenWebFragment.REQUEST_CODE_SCAN_QR);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void requestPermission(String str) {
            Log.d(OpenWebFragment.TAG, "request permission");
            try {
                JSONObject jSONObject = new JSONObject(str);
                final String string = jSONObject.getString("msgid");
                if (!TextUtils.isEmpty(string)) {
                    String string2 = jSONObject.getJSONObject(MiniDefine.aM).getString("permission");
                    if (!OpenWebFragment.this.requestWebPermission(string2, new OnRequestFinishedListener() {
                        public void onRequestFinished(boolean z) {
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put(GlobalDefine.g, z);
                                YellowPageJSBridge.this.sendAsyncCallbackMessage(24, WebService.buildCallbackResult(string, "callback", OpenWebFragment.MENU_ORDER_ID, jSONObject.toString()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    })) {
                        sendAsyncCallbackMessage(24, WebService.buildCallbackResult(string, "callback", 1, "Permission " + string2 + " not found.", null));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public OpenWebFragment() {
        this.mNotifyLocationRunnable = new Runnable() {
            public void run() {
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (OpenWebFragment.this.mLastKnownLocation != null) {
                        jSONObject.put("longitude", OpenWebFragment.this.mLastKnownLocation.getLongitude());
                        jSONObject.put("latitude", OpenWebFragment.this.mLastKnownLocation.getLatitude());
                        jSONObject.put("provider", OpenWebFragment.this.mLastKnownLocation.getProvider());
                        Log.d(OpenWebFragment.TAG, "Notify location with new location [" + OpenWebFragment.this.mLastKnownLocation.getLongitude() + "," + OpenWebFragment.this.mLastKnownLocation.getLatitude() + "]");
                    }
                    OpenWebFragment.this.mWebEvent.sendAsyncCallbackMessage(12, WebService.buildCallbackResult("geolocation", "event", OpenWebFragment.MENU_ORDER_ID, jSONObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        this.mUpdateLocationRunnable = new Runnable() {
            public void run() {
                ThreadPool.execute(new Runnable() {
                    public void run() {
                        OpenWebFragment.this.mLastKnownLocation = GeoLocationManager.getInstance(OpenWebFragment.this.mActivity.getApplicationContext()).getLocation();
                        if (OpenWebFragment.this.mWebView != null) {
                            OpenWebFragment.this.mHandler.post(OpenWebFragment.this.mNotifyLocationRunnable);
                            OpenWebFragment.this.mHandler.postDelayed(OpenWebFragment.this.mUpdateLocationRunnable, 5000);
                        }
                    }
                });
            }
        };
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mContainer = onCreateView.findViewById(R.id.container);
        this.mBottomBarContainer = onCreateView.findViewById(R.id.bottom_bar);
        this.mBackButton = (ImageButton) onCreateView.findViewById(R.id.back);
        this.mBackButton.setOnClickListener(this);
        this.mBackButton.setEnabled(false);
        this.mRefreshButton = (ImageButton) onCreateView.findViewById(R.id.refresh);
        this.mRefreshButton.setOnClickListener(this);
        this.mCachedPermissions = new HashMap();
        this.mPermissionManager = PermissionManager.getInstance(this.mActivity);
        this.mLoadingProgressView = (LoadingProgressView) onCreateView.findViewById(R.id.loading_view);
        this.mLoadingProgressView.setIndeterminate(false);
        this.mLoadingProgressView.a(false, false, (e) this);
        return onCreateView;
    }

    protected void onInitWebViewSettings() {
        super.onInitWebViewSettings();
        this.mHandler = new WebFragmentHandler(this);
        this.mWebEvent = new YellowPageJSBridge(this.mActivity, this.mHandler, new CurrentUrlSource() {
            public String getCurrentUrl() {
                if (OpenWebFragment.this.mWebView != null) {
                    return OpenWebFragment.this.mWebView.getUrl();
                }
                return null;
            }
        });
        this.mWebEvent.setStatisticsContext(getStatisticsContext());
        this.mWebView.addJavascriptInterface(this.mWebEvent, "WE");
    }

    public void onStop() {
        super.onStop();
        if (this.mPermissionDialog != null && this.mPermissionDialog.isShowing()) {
            this.mPermissionDialog.dismiss();
            this.mPermissionDialog = null;
        }
    }

    public void onDetach() {
        super.onDetach();
        removeNetworkLocationUpdateListener();
        this.mHandler.removeCallbacks(this.mNotifyLocationRunnable);
    }

    public void onCreateWebMenu(Menu menu) {
        if (this.mCurrentMerchant != null && this.mCurrentMerchant.isValid()) {
            menu.add(MENU_ORDER_ID, MENU_ORDER_ID, MENU_ORDER_ID, R.string.view_orders);
        }
        setHasOptionsMenu(true);
    }

    public void reload() {
        Log.d(TAG, "reload");
        this.mWebView.reload();
    }

    public void loadUrl(String str) {
        super.loadUrl(str);
        this.mLoadingProgressView.setProgress(10);
        this.mLoadingProgressView.onStartLoading(false);
        this.mLoadState = State.OK;
    }

    private boolean requestWebPermission(String str, final OnRequestFinishedListener onRequestFinishedListener) {
        final PermissionInfo permissionInfoByKey = this.mPermissionManager.getPermissionInfoByKey(str);
        if (permissionInfoByKey == null) {
            return false;
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                final String host = Uri.parse(OpenWebFragment.this.mWebView.getUrl()).getHost();
                final String access$1400 = OpenWebFragment.this.generatePermissionCacheKey(permissionInfoByKey.getKey());
                Boolean bool = (Boolean) OpenWebFragment.this.mCachedPermissions.get(access$1400);
                if (bool != null) {
                    onRequestFinishedListener.onRequestFinished(bool.booleanValue());
                }
                OpenWebFragment.this.mPermissionManager.check(OpenWebFragment.this.mActivity, host, permissionInfoByKey, new OnCheckFinishedListener() {
                    public void onCheckFinished(int i) {
                        if (i == 3) {
                            onRequestFinishedListener.onRequestFinished(true);
                            OpenWebFragment.this.mCachedPermissions.put(access$1400, Boolean.valueOf(true));
                            return;
                        }
                        if (i == -1) {
                            OpenWebFragment.this.mPermissionManager.insert(OpenWebFragment.this.mActivity, host, permissionInfoByKey.getKey(), OpenWebFragment.MENU_ORDER_ID);
                        }
                        OpenWebFragment.this.showRequestDialog(host, permissionInfoByKey, onRequestFinishedListener);
                    }
                });
            }
        });
        return true;
    }

    private void showRequestDialog(String str, final PermissionInfo permissionInfo, final OnRequestFinishedListener onRequestFinishedListener) {
        if ((this.mPermissionDialog == null || !this.mPermissionDialog.isShowing()) && !this.mActivity.isFinishing()) {
            CharSequence string = getString(R.string.web_permission_message, new Object[]{str, permissionInfo.getLabel()});
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.user_notice_do_not_remind_view, null);
            final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkbox);
            checkBox.setChecked(true);
            ((TextView) inflate.findViewById(R.id.hint)).setText(R.string.remember_preference);
            Builder view = new Builder(getActivity()).setMessage(string).setView(inflate);
            final String str2 = str;
            final PermissionInfo permissionInfo2 = permissionInfo;
            final OnRequestFinishedListener onRequestFinishedListener2 = onRequestFinishedListener;
            this.mPermissionDialog = view.setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (checkBox.isChecked()) {
                        OpenWebFragment.this.mPermissionManager.update(OpenWebFragment.this.mActivity, str2, permissionInfo2.getKey(), 3);
                    }
                    onRequestFinishedListener2.onRequestFinished(true);
                    OpenWebFragment.this.mCachedPermissions.put(OpenWebFragment.this.generatePermissionCacheKey(permissionInfo2.getKey()), Boolean.valueOf(true));
                }
            }).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    onRequestFinishedListener.onRequestFinished(false);
                    OpenWebFragment.this.mCachedPermissions.put(OpenWebFragment.this.generatePermissionCacheKey(permissionInfo.getKey()), Boolean.valueOf(false));
                }
            }).setCancelable(false).show();
        }
    }

    private String generatePermissionCacheKey(String str) {
        return Uri.parse(this.mWebView.getUrl()).getHost() + "," + str;
    }

    private void getLocationForJS(final String str) {
        ThreadPool.execute(new Runnable() {
            public void run() {
                try {
                    String string = new JSONObject(str).getString("msgid");
                    Location location = GeoLocationManager.getInstance(OpenWebFragment.this.mActivity.getApplicationContext()).getLocation();
                    JSONObject jSONObject = new JSONObject();
                    if (location != null) {
                        jSONObject.put("longitude", location.getLongitude());
                        jSONObject.put("latitude", location.getLatitude());
                        jSONObject.put("provider", location.getProvider());
                    }
                    OpenWebFragment.this.mWebEvent.sendAsyncCallbackMessage(16, WebService.buildCallbackResult(string, "callback", OpenWebFragment.MENU_ORDER_ID, jSONObject.toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void requestNetworkLocationUpdateListener() {
        if (!this.mHandler.hasCallbacks(this.mUpdateLocationRunnable)) {
            this.mHandler.post(this.mUpdateLocationRunnable);
            Log.d(TAG, "requestNetworkLocationUpdateListener");
        }
    }

    private void removeNetworkLocationUpdateListener() {
        if (this.mHandler.hasCallbacks(this.mUpdateLocationRunnable)) {
            this.mHandler.removeCallbacks(this.mUpdateLocationRunnable);
            Log.d(TAG, "removeNetworkLocationUpdateListener");
        }
    }

    protected WebChromeClient onCreateWebChromeClient() {
        return new OpenWebChromeClient();
    }

    protected WebViewClient onCreateWebViewClient() {
        return new OpenWebviewClient();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                super.onBackPressed();
                return;
            case R.id.refresh:
                reload();
                return;
            default:
                return;
        }
    }

    protected void onPageForwardOrBackward() {
        super.onPageForwardOrBackward();
        if (this.mWebView == null || !this.mWebView.canGoBack()) {
            this.mBackButton.setEnabled(false);
        } else {
            this.mBackButton.setEnabled(true);
        }
    }

    public boolean onBackPressed() {
        if (this.mLoadState == State.OK && this.mWebEvent.handleWebEvent("backPressed")) {
            return true;
        }
        return super.onBackPressed();
    }

    public boolean handleHomePressed() {
        if (this.mLoadState == State.OK && this.mWebEvent.handleWebEvent("homePressed")) {
            return true;
        }
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Log.d(TAG, "onActivityResult " + i2);
        switch (i) {
            case REQUEST_CODE_SCAN_QR /*100*/:
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
            Log.e(TAG, "scan QR msg id is empty, can not parse result.");
            return;
        }
        Object obj;
        if (intent == null || intent.getExtras() == null) {
            obj = null;
        } else {
            obj = intent.getExtras().getString(GlobalDefine.g);
        }
        String buildCallbackResult = WebService.buildCallbackResult(this.mScanQRMsgId, "callback", MENU_ORDER_ID, obj);
        this.mScanQRMsgId = null;
        this.mWebEvent.sendAsyncCallbackMessage(31, buildCallbackResult);
        Log.d(TAG, "Scan QR finished");
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.clearReference();
    }

    protected com.miui.yellowpage.widget.pulltorefresh.a getOnRefreshListener() {
        return new com.miui.yellowpage.widget.pulltorefresh.a() {
            public void onRefresh(PullToRefreshBase pullToRefreshBase) {
                OpenWebFragment.this.reload();
            }
        };
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 0 || this.mCurrentMerchant == null || !this.mCurrentMerchant.isValid()) {
            return false;
        }
        Intent intent = new Intent("com.miui.yellowpage.action.ORDER");
        intent.setData(Uri.parse("yellowpage://order?view=merchant&id=" + Uri.encode(this.mCurrentMerchant.getMerchantId()) + "&name=" + Uri.encode(this.mCurrentMerchant.getMerchantName())));
        startActivity(intent);
        return true;
    }

    public void setOnOptionsMenuUpdatedListener(OnOptionsMenuUpdatedListener onOptionsMenuUpdatedListener) {
        this.mOnOptionsMenuUpdatedListener = onOptionsMenuUpdatedListener;
    }
}
