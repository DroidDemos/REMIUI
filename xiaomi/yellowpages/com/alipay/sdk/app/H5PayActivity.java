package com.alipay.sdk.app;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.util.Utils;
import com.alipay.sdk.widget.Loading;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class H5PayActivity extends Activity {
    private static final String a = "sdklite://h5quit?result=";
    private static final String b = "sdklite://h5quit";
    private static final String c = "http://m.alipay.com/?action=h5quit";
    private static final String d = "&end_code=";
    private WebView e;
    private Loading f;
    private Handler g;
    private boolean h;
    private Runnable i;

    class MyWebChromeClient extends WebChromeClient {
        final /* synthetic */ H5PayActivity a;

        private MyWebChromeClient(H5PayActivity h5PayActivity) {
            this.a = h5PayActivity;
        }

        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            new Builder(this.a).setTitle("\u63d0\u793a").setMessage(str2).setPositiveButton("\u786e\u5b9a", new OnClickListener(this) {
                final /* synthetic */ MyWebChromeClient b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            }).setNegativeButton("\u53d6\u6d88", new OnClickListener(this) {
                final /* synthetic */ MyWebChromeClient b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.cancel();
                }
            }).show();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            new Builder(this.a).setTitle("\u63d0\u793a").setMessage(str2).setPositiveButton("\u786e\u5b9a", new OnClickListener(this) {
                final /* synthetic */ MyWebChromeClient b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            }).setNegativeButton("\u53d6\u6d88", new OnClickListener(this) {
                final /* synthetic */ MyWebChromeClient b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.cancel();
                }
            }).show();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
            new Builder(this.a).setTitle("\u63d0\u793a").setMessage(str2).setPositiveButton("\u786e\u5b9a", new OnClickListener(this) {
                final /* synthetic */ MyWebChromeClient b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    jsPromptResult.confirm();
                }
            }).setNegativeButton("\u53d6\u6d88", new OnClickListener(this) {
                final /* synthetic */ MyWebChromeClient b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    jsPromptResult.cancel();
                }
            }).show();
            return true;
        }
    }

    class MyWebViewClient extends WebViewClient {
        final /* synthetic */ H5PayActivity a;

        private MyWebViewClient(H5PayActivity h5PayActivity) {
            this.a = h5PayActivity;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            this.a.h = true;
            super.onReceivedError(webView, i, str, str2);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        public void onFormResubmission(WebView webView, Message message, Message message2) {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (TextUtils.equals(str, H5PayActivity.b) || TextUtils.equals(str, H5PayActivity.c)) {
                ResultStatus a = ResultStatus.a(ResultStatus.CANCELED);
                Result.a(Result.a(a.a(), a.b(), ConfigConstant.WIRELESS_FILENAME));
                this.a.finish();
            } else if (str.startsWith(H5PayActivity.a)) {
                String substring = str.substring(str.indexOf(H5PayActivity.a) + H5PayActivity.a.length());
                int parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf(H5PayActivity.d) + H5PayActivity.d.length()));
                for (ResultStatus resultStatus : ResultStatus.values()) {
                    ResultStatus resultStatus2;
                    if (parseInt == resultStatus2.a()) {
                        resultStatus2 = ResultStatus.a(resultStatus2);
                        String decode = URLDecoder.decode(str);
                        Result.a(Result.a(resultStatus2.a(), resultStatus2.b(), decode.substring(decode.indexOf(H5PayActivity.a) + H5PayActivity.a.length(), decode.lastIndexOf(H5PayActivity.d))));
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ MyWebViewClient a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.finish();
                            }
                        });
                    }
                }
            } else {
                webView.loadUrl(str);
            }
            return true;
        }

        public void onLoadResource(WebView webView, String str) {
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.a.b();
            this.a.g.postDelayed(this.a.i, 30000);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onPageFinished(WebView webView, String str) {
            this.a.c();
            this.a.g.removeCallbacks(this.a.i);
        }
    }

    public H5PayActivity() {
        this.g = new Handler();
        this.i = new Runnable(this) {
            final /* synthetic */ H5PayActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        };
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        String string = extras.getString("url");
        if (Utils.c(string)) {
            Method method;
            Object string2 = extras.getString("cookie");
            if (!TextUtils.isEmpty(string2)) {
                CookieSyncManager.createInstance(this).sync();
                CookieManager.getInstance().setCookie(string, string2);
                CookieSyncManager.getInstance().sync();
            }
            View linearLayout = new LinearLayout(this);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            linearLayout.setOrientation(1);
            setContentView(linearLayout, layoutParams);
            this.e = new WebView(this);
            layoutParams.weight = 1.0f;
            this.e.setVisibility(0);
            linearLayout.addView(this.e, layoutParams);
            WebSettings settings = this.e.getSettings();
            settings.setUserAgentString(settings.getUserAgentString() + Utils.g(this));
            settings.setRenderPriority(RenderPriority.HIGH);
            settings.setSupportMultipleWindows(true);
            settings.setJavaScriptEnabled(true);
            settings.setSavePassword(false);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
            this.e.setVerticalScrollbarOverlay(true);
            this.e.setWebViewClient(new MyWebViewClient());
            this.e.setWebChromeClient(new MyWebChromeClient());
            this.e.loadUrl(string);
            if (VERSION.SDK_INT >= 7) {
                try {
                    method = this.e.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                    if (method != null) {
                        method.invoke(this.e.getSettings(), new Object[]{Boolean.valueOf(true)});
                    }
                } catch (Exception e) {
                }
            }
            try {
                method = this.e.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                if (method != null) {
                    method.invoke(this.e, new Object[]{"searchBoxJavaBridge_"});
                    return;
                }
                return;
            } catch (Exception e2) {
                return;
            }
        }
        finish();
    }

    public void onBackPressed() {
        ResultStatus a;
        if (!this.e.canGoBack()) {
            a = ResultStatus.a(ResultStatus.CANCELED);
            Result.a(Result.a(a.a(), a.b(), ConfigConstant.WIRELESS_FILENAME));
            finish();
        } else if (this.h) {
            a = ResultStatus.a(ResultStatus.NETWORK_ERROR);
            Result.a(Result.a(a.a(), a.b(), ConfigConstant.WIRELESS_FILENAME));
            finish();
        }
    }

    public void finish() {
        a();
        super.finish();
    }

    private void a() {
        Object obj = PayTask.a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void b() {
        if (this.f == null) {
            this.f = new Loading(this);
        }
        this.f.b();
    }

    private void c() {
        if (this.f != null && this.f.a()) {
            this.f.c();
        }
        this.f = null;
    }
}
