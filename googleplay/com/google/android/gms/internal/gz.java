package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@fd
public class gz extends WebView implements DownloadListener {
    private final Object mK;
    private final WindowManager mU;
    private bd qJ;
    private final gx qK;
    private final k tl;
    private final ha wV;
    private final a wW;
    private du wX;
    private boolean wY;
    private boolean wZ;
    private boolean xa;
    private boolean xb;

    @fd
    protected static class a extends MutableContextWrapper {
        private Context mR;
        private Activity xc;

        public a(Context context) {
            super(context);
            setBaseContext(context);
        }

        public Context dI() {
            return this.xc;
        }

        public void setBaseContext(Context base) {
            this.mR = base.getApplicationContext();
            this.xc = base instanceof Activity ? (Activity) base : null;
            super.setBaseContext(this.mR);
        }

        public void startActivity(Intent intent) {
            if (this.xc != null) {
                this.xc.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.mR.startActivity(intent);
        }
    }

    protected gz(a aVar, bd bdVar, boolean z, boolean z2, k kVar, gx gxVar) {
        super(aVar);
        this.mK = new Object();
        this.wW = aVar;
        this.qJ = bdVar;
        this.wY = z;
        this.tl = kVar;
        this.qK = gxVar;
        this.mU = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        gn.a((Context) aVar, gxVar.wR, settings);
        if (VERSION.SDK_INT >= 17) {
            gt.a(getContext(), settings);
        } else if (VERSION.SDK_INT >= 11) {
            gr.a(getContext(), settings);
        }
        setDownloadListener(this);
        if (VERSION.SDK_INT >= 11) {
            this.wV = new hc(this, z2);
        } else {
            this.wV = new ha(this, z2);
        }
        setWebViewClient(this.wV);
        if (VERSION.SDK_INT >= 14) {
            setWebChromeClient(new hd(this));
        } else if (VERSION.SDK_INT >= 11) {
            setWebChromeClient(new hb(this));
        }
        dJ();
    }

    public static gz a(Context context, bd bdVar, boolean z, boolean z2, k kVar, gx gxVar) {
        return new gz(new a(context), bdVar, z, z2, kVar, gxVar);
    }

    private void dJ() {
        synchronized (this.mK) {
            if (this.wY || this.qJ.os) {
                if (VERSION.SDK_INT < 14) {
                    gw.d("Disabling hardware acceleration on an overlay.");
                    dK();
                } else {
                    gw.d("Enabling hardware acceleration on an overlay.");
                    dL();
                }
            } else if (VERSION.SDK_INT < 18) {
                gw.d("Disabling hardware acceleration on an AdView.");
                dK();
            } else {
                gw.d("Enabling hardware acceleration on an AdView.");
                dL();
            }
        }
    }

    private void dK() {
        synchronized (this.mK) {
            if (!this.wZ && VERSION.SDK_INT >= 11) {
                gr.i(this);
            }
            this.wZ = true;
        }
    }

    private void dL() {
        synchronized (this.mK) {
            if (this.wZ && VERSION.SDK_INT >= 11) {
                gr.j(this);
            }
            this.wZ = false;
        }
    }

    protected void X(String str) {
        synchronized (this.mK) {
            if (isDestroyed()) {
                gw.w("The webview is destroyed. Ignoring action.");
            } else {
                loadUrl(str);
            }
        }
    }

    public void a(bd bdVar) {
        synchronized (this.mK) {
            this.qJ = bdVar;
            requestLayout();
        }
    }

    public void a(du duVar) {
        synchronized (this.mK) {
            this.wX = duVar;
        }
    }

    public void a(String str, Map<String, ?> map) {
        try {
            b(str, gn.t((Map) map));
        } catch (JSONException e) {
            gw.w("Could not convert parameters to JSON.");
        }
    }

    public bd ac() {
        bd bdVar;
        synchronized (this.mK) {
            bdVar = this.qJ;
        }
        return bdVar;
    }

    public void b(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:AFMA_ReceiveMessage('");
        stringBuilder.append(str);
        stringBuilder.append("'");
        stringBuilder.append(",");
        stringBuilder.append(jSONObject2);
        stringBuilder.append(");");
        gw.v("Dispatching AFMA event: " + stringBuilder);
        X(stringBuilder.toString());
    }

    public void ci() {
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.qK.wR);
        a("onshow", hashMap);
    }

    public void cj() {
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.qK.wR);
        a("onhide", hashMap);
    }

    public du dC() {
        du duVar;
        synchronized (this.mK) {
            duVar = this.wX;
        }
        return duVar;
    }

    public ha dD() {
        return this.wV;
    }

    public boolean dE() {
        return this.xb;
    }

    public k dF() {
        return this.tl;
    }

    public gx dG() {
        return this.qK;
    }

    public boolean dH() {
        boolean z;
        synchronized (this.mK) {
            z = this.wY;
        }
        return z;
    }

    public Context dI() {
        return this.wW.dI();
    }

    public void destroy() {
        synchronized (this.mK) {
            if (this.wX != null) {
                this.wX.close();
            }
            this.xa = true;
            super.destroy();
        }
    }

    public void evaluateJavascript(String script, ValueCallback<String> resultCallback) {
        synchronized (this.mK) {
            if (isDestroyed()) {
                gw.w("The webview is destroyed. Ignoring action.");
                if (resultCallback != null) {
                    resultCallback.onReceiveValue(null);
                }
                return;
            }
            super.evaluateJavascript(script, resultCallback);
        }
    }

    public boolean isDestroyed() {
        boolean z;
        synchronized (this.mK) {
            z = this.xa;
        }
        return z;
    }

    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long size) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(url), mimeType);
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            gw.d("Couldn't find an Activity to view url/mimetype: " + url + " / " + mimeType);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = Integer.MAX_VALUE;
        synchronized (this.mK) {
            if (isInEditMode() || this.wY) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                return;
            }
            int mode = MeasureSpec.getMode(widthMeasureSpec);
            int size = MeasureSpec.getSize(widthMeasureSpec);
            int mode2 = MeasureSpec.getMode(heightMeasureSpec);
            int size2 = MeasureSpec.getSize(heightMeasureSpec);
            mode = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i = size2;
            }
            if (this.qJ.widthPixels > mode || this.qJ.heightPixels > r0) {
                float f = this.wW.getResources().getDisplayMetrics().density;
                gw.w("Not enough space to show ad. Needs " + ((int) (((float) this.qJ.widthPixels) / f)) + "x" + ((int) (((float) this.qJ.heightPixels) / f)) + " dp, but only has " + ((int) (((float) size) / f)) + "x" + ((int) (((float) size2) / f)) + " dp.");
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
            } else {
                if (getVisibility() != 8) {
                    setVisibility(0);
                }
                setMeasuredDimension(this.qJ.widthPixels, this.qJ.heightPixels);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.tl != null) {
            this.tl.a(event);
        }
        return super.onTouchEvent(event);
    }

    public void q(boolean z) {
        synchronized (this.mK) {
            if (this.wX != null) {
                this.wX.q(z);
            } else {
                this.xb = z;
            }
        }
    }

    public void setContext(Context context) {
        this.wW.setBaseContext(context);
    }

    public void z(boolean z) {
        synchronized (this.mK) {
            this.wY = z;
            dJ();
        }
    }
}
