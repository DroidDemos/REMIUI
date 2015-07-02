package com.alipay.sdk.auth;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import b.a;
import b.b;
import b.d;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.data.FrameUtils;
import com.alipay.sdk.data.InteractionData;
import com.alipay.sdk.data.MspConfig;
import com.alipay.sdk.data.Request;
import com.alipay.sdk.exception.AppErrorException;
import com.alipay.sdk.exception.FailOperatingException;
import com.alipay.sdk.exception.NetErrorException;
import com.alipay.sdk.exception.UnZipException;
import com.alipay.sdk.net.RequestWrapper;
import com.alipay.sdk.protocol.ActionType;
import com.alipay.sdk.protocol.ElementAction;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.util.ActionUtil;
import com.alipay.sdk.util.Utils;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthActivity extends Activity {
    static final String a = "params";
    static final String b = "redirectUri";
    private WebView c;
    private String d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getExtras() == null) {
            finish();
            return;
        }
        super.requestWindowFeature(1);
        GlobalContext.a().a(this, MspConfig.b());
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(17);
        relativeLayout.addView(new ProgressBar(this));
        super.setContentView(relativeLayout);
        new Thread(new Runnable(this) {
            final /* synthetic */ AuthActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        }).start();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void a() {
        String string = getIntent().getExtras().getString(a);
        this.d = getIntent().getExtras().getString(b);
        Request a = FrameUtils.a(new InteractionData(), string, new JSONObject(), true);
        a.f().c("com.alipay.mobilecashier");
        a.f().a("com.alipay.mcpay");
        a.f().e("4.0.0");
        a.f().d("/cashier/main");
        try {
            a(new RequestWrapper(new InteractionData()).a((Context) this, a, false).d());
        } catch (NetErrorException e) {
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ AuthActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.c(null);
                }
            });
        } catch (FailOperatingException e2) {
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ AuthActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.d(null);
                }
            });
        } catch (AppErrorException e3) {
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ AuthActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.d(null);
                }
            });
        } catch (UnZipException e4) {
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ AuthActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.d(null);
                }
            });
        }
    }

    private void a(JSONObject jSONObject) {
        ElementAction a = ElementAction.a(jSONObject.optJSONObject(MiniDefine.d), MiniDefine.g);
        if (a == null) {
            throw new FailOperatingException();
        }
        for (ActionType actionType : ActionType.a(a)) {
            if (actionType == ActionType.WapPay) {
                final String str = ActionUtil.a(actionType.e())[0];
                if (Utils.c(str)) {
                    runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AuthActivity b;

                        public void run() {
                            this.b.a(str);
                        }
                    });
                    return;
                } else {
                    finish();
                    return;
                }
            }
        }
    }

    private void a(String str) {
        this.c = new WebView(this);
        WebSettings settings = this.c.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + Utils.g(this));
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        this.c.setVerticalScrollbarOverlay(true);
        this.c.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ AuthActivity a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!this.a.b(str)) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                webView.stopLoading();
                return true;
            }
        });
        this.c.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ AuthActivity a;

            {
                this.a = r1;
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                String message = consoleMessage.message();
                if (TextUtils.isEmpty(message)) {
                    return super.onConsoleMessage(consoleMessage);
                }
                Object obj = null;
                if (message.startsWith("h5container.message: ")) {
                    obj = message.replaceFirst("h5container.message: ", ConfigConstant.WIRELESS_FILENAME);
                }
                if (TextUtils.isEmpty(obj)) {
                    return super.onConsoleMessage(consoleMessage);
                }
                this.a.e(obj);
                return super.onConsoleMessage(consoleMessage);
            }
        });
        this.c.setDownloadListener(new DownloadListener(this) {
            final /* synthetic */ AuthActivity a;

            {
                this.a = r1;
            }

            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        });
        setContentView(this.c);
        this.c.loadUrl(str);
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, this.d)) {
                str = str + "?resultCode=150";
            }
            AuthHelper.a((Activity) this, str);
        }
        finish();
        return true;
    }

    private void c(String str) {
        Builder builder = new Builder(this);
        builder.setMessage("\u4e0d\u80fd\u8fde\u63a5\u5230\u670d\u52a1\u5668\uff0c\u662f\u5426\u91cd\u8bd5\uff1f");
        builder.setPositiveButton("\u786e\u5b9a", new OnClickListener(this) {
            final /* synthetic */ AuthActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                new Thread(this) {
                    final /* synthetic */ AnonymousClass10 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                }.start();
            }
        });
        builder.setNeutralButton("\u53d6\u6d88", new OnClickListener(this) {
            final /* synthetic */ AuthActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.finish();
            }
        });
        builder.create().show();
    }

    private void d(String str) {
        Builder builder = new Builder(this);
        builder.setMessage("\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
        builder.setNeutralButton("\u786e\u5b9a", new OnClickListener(this) {
            final /* synthetic */ AuthActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.finish();
            }
        });
        builder.create().show();
    }

    private void e(String str) {
        new b(this, new d(this) {
            final /* synthetic */ AuthActivity a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                this.a.a(aVar);
            }
        }).a(str);
    }

    private void a(a aVar) {
        if (this.c != null && aVar != null) {
            try {
                String str = "AlipayJSBridge._invokeJS(%s)";
                final String format = String.format("AlipayJSBridge._invokeJS(%s)", new Object[]{aVar.g()});
                runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AuthActivity b;

                    public void run() {
                        try {
                            this.b.c.loadUrl("javascript:" + format);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
