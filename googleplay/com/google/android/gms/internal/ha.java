package com.google.android.gms.internal;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import java.util.Map;

@fd
public class ha extends WebViewClient {
    private final Object mK;
    protected final gz mr;
    private cg pQ;
    private cj qd;
    private aa qe;
    private a tu;
    private final HashMap<String, ci> xd;
    private y xe;
    private dx xf;
    private boolean xg;
    private boolean xh;
    private ea xi;
    private final dq xj;

    public interface a {
        void a(gz gzVar);
    }

    public ha(gz gzVar, boolean z) {
        this(gzVar, z, new dq(gzVar, gzVar.getContext(), new bq(gzVar.getContext())));
    }

    ha(gz gzVar, boolean z, dq dqVar) {
        this.xd = new HashMap();
        this.mK = new Object();
        this.xg = false;
        this.mr = gzVar;
        this.xh = z;
        this.xj = dqVar;
    }

    private static boolean d(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private void e(Uri uri) {
        String path = uri.getPath();
        ci ciVar = (ci) this.xd.get(path);
        if (ciVar != null) {
            Map c = gn.c(uri);
            if (gw.v(2)) {
                gw.v("Received GMSG: " + path);
                for (String path2 : c.keySet()) {
                    gw.v("  " + path2 + ": " + ((String) c.get(path2)));
                }
            }
            ciVar.a(this.mr, c);
            return;
        }
        gw.v("No GMSG handler found for GMSG: " + uri);
    }

    public final void A(boolean z) {
        this.xg = z;
    }

    public final void a(dt dtVar) {
        dx dxVar = null;
        boolean dH = this.mr.dH();
        y yVar = (!dH || this.mr.ac().os) ? this.xe : null;
        if (!dH) {
            dxVar = this.xf;
        }
        a(new dw(dtVar, yVar, dxVar, this.xi, this.mr.dG()));
    }

    protected void a(dw dwVar) {
        du.a(this.mr.getContext(), dwVar);
    }

    public final void a(a aVar) {
        this.tu = aVar;
    }

    public void a(y yVar, dx dxVar, cg cgVar, ea eaVar, boolean z, cj cjVar, aa aaVar) {
        if (aaVar == null) {
            aaVar = new aa(false);
        }
        a("/appEvent", new cf(cgVar));
        a("/canOpenURLs", ch.pS);
        a("/canOpenIntents", ch.pT);
        a("/click", ch.pU);
        a("/close", ch.pV);
        a("/customClose", ch.pW);
        a("/httpTrack", ch.pX);
        a("/log", ch.pY);
        a("/open", new cn(cjVar, aaVar));
        a("/touch", ch.pZ);
        a("/video", ch.qa);
        a("/mraid", new cm());
        this.xe = yVar;
        this.xf = dxVar;
        this.pQ = cgVar;
        this.qd = cjVar;
        this.xi = eaVar;
        this.qe = aaVar;
        A(z);
    }

    public final void a(String str, ci ciVar) {
        this.xd.put(str, ciVar);
    }

    public final void a(boolean z, int i) {
        y yVar = (!this.mr.dH() || this.mr.ac().os) ? this.xe : null;
        a(new dw(yVar, this.xf, this.xi, this.mr, z, i, this.mr.dG()));
    }

    public final void a(boolean z, int i, String str) {
        dx dxVar = null;
        boolean dH = this.mr.dH();
        y yVar = (!dH || this.mr.ac().os) ? this.xe : null;
        if (!dH) {
            dxVar = this.xf;
        }
        a(new dw(yVar, dxVar, this.pQ, this.xi, this.mr, z, i, str, this.mr.dG(), this.qd));
    }

    public final void a(boolean z, int i, String str, String str2) {
        boolean dH = this.mr.dH();
        y yVar = (!dH || this.mr.ac().os) ? this.xe : null;
        a(new dw(yVar, dH ? null : this.xf, this.pQ, this.xi, this.mr, z, i, str, str2, this.mr.dG(), this.qd));
    }

    public final void cg() {
        synchronized (this.mK) {
            this.xg = false;
            this.xh = true;
            final du dC = this.mr.dC();
            if (dC != null) {
                if (gv.dB()) {
                    dC.cg();
                } else {
                    gv.wQ.post(new Runnable(this) {
                        final /* synthetic */ ha xl;

                        public void run() {
                            dC.cg();
                        }
                    });
                }
            }
        }
    }

    public aa dM() {
        return this.qe;
    }

    public boolean dN() {
        boolean z;
        synchronized (this.mK) {
            z = this.xh;
        }
        return z;
    }

    public final void onLoadResource(WebView webView, String url) {
        gw.v("Loading resource: " + url);
        Uri parse = Uri.parse(url);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            e(parse);
        }
    }

    public final void onPageFinished(WebView webView, String url) {
        if (this.tu != null) {
            this.tu.a(this.mr);
            this.tu = null;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String url) {
        gw.v("AdWebView shouldOverrideUrlLoading: " + url);
        Uri parse = Uri.parse(url);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            e(parse);
        } else if (this.xg && webView == this.mr && d(parse)) {
            return super.shouldOverrideUrlLoading(webView, url);
        } else {
            if (this.mr.willNotDraw()) {
                gw.w("AdWebView unable to handle URL: " + url);
            } else {
                Uri uri;
                try {
                    k dF = this.mr.dF();
                    if (dF != null && dF.isGoogleAdUrl(parse)) {
                        parse = dF.b(parse, this.mr.getContext());
                    }
                    uri = parse;
                } catch (l e) {
                    gw.w("Unable to append parameter to URL: " + url);
                    uri = parse;
                }
                if (this.qe == null || this.qe.az()) {
                    a(new dt("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
                } else {
                    this.qe.f(url);
                }
            }
        }
        return true;
    }
}
