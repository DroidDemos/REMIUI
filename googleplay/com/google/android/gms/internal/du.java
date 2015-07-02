package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.common.util.m;
import com.google.android.wallet.instrumentmanager.R;

@fd
public class du extends com.google.android.gms.internal.ec.a {
    private static final int rL;
    private final Activity mActivity;
    private gz mr;
    private dw rM;
    private dy rN;
    private c rO;
    private dz rP;
    private boolean rQ;
    private boolean rR;
    private FrameLayout rS;
    private CustomViewCallback rT;
    private boolean rU;
    private boolean rV;
    private boolean rW;
    private RelativeLayout rX;

    @fd
    private static final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    @fd
    private static final class b extends RelativeLayout {
        private final gq lM;

        public b(Context context, String str) {
            super(context);
            this.lM = new gq(context, str);
        }

        public boolean onInterceptTouchEvent(MotionEvent event) {
            this.lM.c(event);
            return false;
        }
    }

    @fd
    private static final class c {
        public final int index;
        public final LayoutParams rZ;
        public final ViewGroup sa;

        public c(gz gzVar) throws a {
            this.rZ = gzVar.getLayoutParams();
            ViewParent parent = gzVar.getParent();
            if (parent instanceof ViewGroup) {
                this.sa = (ViewGroup) parent;
                this.index = this.sa.indexOfChild(gzVar);
                this.sa.removeView(gzVar);
                gzVar.z(true);
                return;
            }
            throw new a("Could not get the parent of the WebView for an overlay.");
        }
    }

    static {
        rL = Color.argb(0, 0, 0, 0);
    }

    private static RelativeLayout.LayoutParams a(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i, i2, 0, 0);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        return layoutParams;
    }

    public static void a(Context context, dw dwVar) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", dwVar.lR.wU);
        dw.a(intent, dwVar);
        if (!m.js()) {
            intent.addFlags(524288);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    public void X() {
        this.rQ = true;
    }

    public void a(View view, CustomViewCallback customViewCallback) {
        this.rS = new FrameLayout(this.mActivity);
        this.rS.setBackgroundColor(-16777216);
        this.rS.addView(view, -1, -1);
        this.mActivity.setContentView(this.rS);
        X();
        this.rT = customViewCallback;
        this.rR = true;
    }

    public void b(int i, int i2, int i3, int i4) {
        if (this.rN != null) {
            this.rN.setLayoutParams(a(i, i2, i3, i4));
        }
    }

    public void c(int i, int i2, int i3, int i4) {
        if (this.rN == null) {
            this.rN = new dy(this.mActivity, this.mr);
            this.rX.addView(this.rN, 0, a(i, i2, i3, i4));
            this.mr.dD().A(false);
        }
    }

    public dy ce() {
        return this.rN;
    }

    public void cf() {
        if (this.rM != null && this.rR) {
            setRequestedOrientation(this.rM.orientation);
        }
        if (this.rS != null) {
            this.mActivity.setContentView(this.rX);
            X();
            this.rS.removeAllViews();
            this.rS = null;
        }
        if (this.rT != null) {
            this.rT.onCustomViewHidden();
            this.rT = null;
        }
        this.rR = false;
    }

    public void cg() {
        this.rX.removeView(this.rP);
        p(true);
    }

    void ch() {
        if (this.mActivity.isFinishing() && !this.rV) {
            this.rV = true;
            if (this.mActivity.isFinishing()) {
                if (this.mr != null) {
                    cj();
                    this.rX.removeView(this.mr);
                    if (this.rO != null) {
                        this.mr.z(false);
                        this.rO.sa.addView(this.mr, this.rO.index, this.rO.rZ);
                    }
                }
                if (this.rM != null && this.rM.sd != null) {
                    this.rM.sd.ag();
                }
            }
        }
    }

    void ci() {
        this.mr.ci();
    }

    void cj() {
        this.mr.cj();
    }

    public void close() {
        this.mActivity.finish();
    }

    public void onCreate(Bundle savedInstanceState) {
        boolean z = false;
        if (savedInstanceState != null) {
            z = savedInstanceState.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.rU = z;
        try {
            this.rM = dw.b(this.mActivity.getIntent());
            if (this.rM == null) {
                throw new a("Could not get info for ad overlay.");
            }
            if (this.rM.sn != null) {
                this.rW = this.rM.sn.ml;
            } else {
                this.rW = false;
            }
            if (savedInstanceState == null) {
                if (this.rM.sd != null) {
                    this.rM.sd.ah();
                }
                if (!(this.rM.sk == 1 || this.rM.sc == null)) {
                    this.rM.sc.onAdClicked();
                }
            }
            switch (this.rM.sk) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    r(false);
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    this.rO = new c(this.rM.se);
                    r(false);
                    return;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    r(true);
                    return;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    if (this.rU) {
                        this.mActivity.finish();
                        return;
                    } else if (!dr.a(this.mActivity, this.rM.sb, this.rM.sj)) {
                        this.mActivity.finish();
                        return;
                    } else {
                        return;
                    }
                default:
                    throw new a("Could not determine ad overlay type.");
            }
        } catch (a e) {
            gw.w(e.getMessage());
            this.mActivity.finish();
        }
    }

    public void onDestroy() {
        if (this.rN != null) {
            this.rN.destroy();
        }
        if (this.mr != null) {
            this.rX.removeView(this.mr);
        }
        ch();
    }

    public void onPause() {
        if (this.rN != null) {
            this.rN.pause();
        }
        cf();
        if (this.mr != null && (!this.mActivity.isFinishing() || this.rO == null)) {
            gn.a(this.mr);
        }
        ch();
    }

    public void onRestart() {
    }

    public void onResume() {
        if (this.rM != null && this.rM.sk == 4) {
            if (this.rU) {
                this.mActivity.finish();
            } else {
                this.rU = true;
            }
        }
        if (this.mr != null) {
            gn.b(this.mr);
        }
    }

    public void onSaveInstanceState(Bundle outBundle) {
        outBundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.rU);
    }

    public void onStart() {
    }

    public void onStop() {
        ch();
    }

    public void p(boolean z) {
        this.rP = new dz(this.mActivity, z ? 50 : 32);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.rP.q(this.rM.sh);
        this.rX.addView(this.rP, layoutParams);
    }

    public void q(boolean z) {
        if (this.rP != null) {
            this.rP.q(z);
        }
    }

    void r(boolean z) throws a {
        if (!this.rQ) {
            this.mActivity.requestWindowFeature(1);
        }
        Window window = this.mActivity.getWindow();
        if (!this.rW || this.rM.sn.mv) {
            window.setFlags(1024, 1024);
        }
        setRequestedOrientation(this.rM.orientation);
        if (VERSION.SDK_INT >= 11) {
            gw.d("Enabling hardware acceleration on the AdActivity window.");
            gr.a(window);
        }
        this.rX = new b(this.mActivity, this.rM.sm);
        if (this.rW) {
            this.rX.setBackgroundColor(rL);
        } else {
            this.rX.setBackgroundColor(-16777216);
        }
        this.mActivity.setContentView(this.rX);
        X();
        boolean dN = this.rM.se.dD().dN();
        if (z) {
            this.mr = gz.a(this.mActivity, this.rM.se.ac(), true, dN, null, this.rM.lR);
            this.mr.dD().a(null, null, this.rM.sf, this.rM.sj, true, this.rM.sl, this.rM.se.dD().dM());
            this.mr.dD().a(new com.google.android.gms.internal.ha.a(this) {
                final /* synthetic */ du rY;

                {
                    this.rY = r1;
                }

                public void a(gz gzVar) {
                    gzVar.ci();
                }
            });
            if (this.rM.url != null) {
                this.mr.loadUrl(this.rM.url);
            } else if (this.rM.si != null) {
                this.mr.loadDataWithBaseURL(this.rM.sg, this.rM.si, "text/html", "UTF-8", null);
            } else {
                throw new a("No URL or HTML to display in ad overlay.");
            }
        }
        this.mr = this.rM.se;
        this.mr.setContext(this.mActivity);
        this.mr.a(this);
        ViewParent parent = this.mr.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.mr);
        }
        if (this.rW) {
            this.mr.setBackgroundColor(rL);
        }
        this.rX.addView(this.mr, -1, -1);
        if (!z) {
            ci();
        }
        p(dN);
        if (this.mr.dE()) {
            q(true);
        }
    }

    public void setRequestedOrientation(int requestedOrientation) {
        this.mActivity.setRequestedOrientation(requestedOrientation);
    }
}
