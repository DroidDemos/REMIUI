package com.google.android.gms.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.common.util.m;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@fd
public class as extends Thread {
    private final Object mK;
    private boolean nE;
    private boolean nF;
    private final ar nG;
    private final aq nH;
    private final fc nI;
    private final int nJ;
    private final int nK;
    private final int nL;
    private final int ns;
    private final int nu;

    @fd
    class a {
        final /* synthetic */ as nN;
        final int nS;
        final int nT;

        a(as asVar, int i, int i2) {
            this.nN = asVar;
            this.nS = i;
            this.nT = i2;
        }
    }

    private void a(Activity activity) {
        if (activity != null) {
            View view = null;
            if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                view = activity.getWindow().getDecorView().findViewById(16908290);
            }
            if (view != null) {
                g(view);
            }
        }
    }

    private boolean a(final WebView webView, final ap apVar) {
        if (!m.jr()) {
            return false;
        }
        apVar.aW();
        webView.post(new Runnable(this) {
            final /* synthetic */ as nN;
            ValueCallback<String> nO;

            public void run() {
                if (webView.getSettings().getJavaScriptEnabled()) {
                    webView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.nO);
                }
            }
        });
        return true;
    }

    private boolean bb() {
        try {
            Context context = this.nG.getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (activityManager == null || keyguardManager == null || powerManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && powerManager.isScreenOn()) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    a a(View view, ap apVar) {
        int i = 0;
        if (view == null) {
            return new a(this, 0, 0);
        }
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            apVar.l(((TextView) view).getText().toString());
            return new a(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof gz)) {
            apVar.aW();
            return a((WebView) view, apVar) ? new a(this, 0, 1) : new a(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new a(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            int i3 = 0;
            while (i < viewGroup.getChildCount()) {
                a a = a(viewGroup.getChildAt(i), apVar);
                i3 += a.nS;
                i2 += a.nT;
                i++;
            }
            return new a(this, i3, i2);
        }
    }

    void a(ap apVar, WebView webView, String str) {
        apVar.aV();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (TextUtils.isEmpty(webView.getTitle())) {
                    apVar.k(optString);
                } else {
                    apVar.k(webView.getTitle() + "\n" + optString);
                }
            }
            if (apVar.aS()) {
                this.nH.b(apVar);
            }
        } catch (JSONException e) {
            gw.d("Json string may be malformed.");
        } catch (Throwable th) {
            gw.d("Failed to get webview content.", th);
            this.nI.b(th);
        }
    }

    public void bd() {
        synchronized (this.mK) {
            this.nE = true;
            gw.d("ContentFetchThread: paused, mPause = " + this.nE);
        }
    }

    boolean g(final View view) {
        if (view == null) {
            return false;
        }
        view.post(new Runnable(this) {
            final /* synthetic */ as nN;

            public void run() {
                this.nN.h(view);
            }
        });
        return true;
    }

    void h(View view) {
        try {
            ap apVar = new ap(this.ns, this.nK, this.nu, this.nL);
            a a = a(view, apVar);
            apVar.aX();
            if (a.nS != 0 || a.nT != 0) {
                if (a.nT != 0 || apVar.aY() != 0) {
                    if (a.nT != 0 || !this.nH.a(apVar)) {
                        this.nH.c(apVar);
                    }
                }
            }
        } catch (Throwable e) {
            gw.e("Exception in fetchContentOnUIThread", e);
            this.nI.b(e);
        }
    }

    public void run() {
        while (!this.nF) {
            try {
                if (bb()) {
                    Activity activity = this.nG.getActivity();
                    if (activity == null) {
                        gw.d("ContentFetchThread: no activity");
                    } else {
                        a(activity);
                    }
                } else {
                    gw.d("ContentFetchTask: sleeping");
                    bd();
                }
                Thread.sleep((long) (this.nJ * 1000));
            } catch (Throwable th) {
                gw.e("Error in ContentFetchTask", th);
                this.nI.b(th);
            }
            synchronized (this.mK) {
                while (this.nE) {
                    try {
                        gw.d("ContentFetchTask: waiting");
                        this.mK.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }
}
