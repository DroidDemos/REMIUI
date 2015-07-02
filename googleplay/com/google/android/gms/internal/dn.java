package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.google.android.gms.ads.AdSize;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

@fd
public class dn {
    static final Set<String> rl;
    private int lh;
    private int li;
    private final Context mContext;
    private final gz mr;
    private final Map<String, String> re;
    private int rm;
    private int rn;
    private boolean ro;
    private String rp;

    static {
        rl = new HashSet(Arrays.asList(new String[]{"top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"}));
    }

    public dn(gz gzVar, Map<String, String> map) {
        this.lh = -1;
        this.li = -1;
        this.rm = 0;
        this.rn = 0;
        this.ro = true;
        this.rp = "top-right";
        this.mr = gzVar;
        this.re = map;
        this.mContext = gzVar.dI();
    }

    private void bQ() {
        int R;
        int[] t = gn.t(this.mContext);
        if (!TextUtils.isEmpty((CharSequence) this.re.get("width"))) {
            R = gn.R((String) this.re.get("width"));
            if (b(R, t[0])) {
                this.lh = R;
            }
        }
        if (!TextUtils.isEmpty((CharSequence) this.re.get("height"))) {
            R = gn.R((String) this.re.get("height"));
            if (c(R, t[1])) {
                this.li = R;
            }
        }
        if (!TextUtils.isEmpty((CharSequence) this.re.get("offsetX"))) {
            this.rm = gn.R((String) this.re.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence) this.re.get("offsetY"))) {
            this.rn = gn.R((String) this.re.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence) this.re.get("allowOffscreen"))) {
            this.ro = Boolean.parseBoolean((String) this.re.get("allowOffscreen"));
        }
        String str = (String) this.re.get("customClosePosition");
        if (!TextUtils.isEmpty(str) && rl.contains(str)) {
            this.rp = str;
        }
    }

    boolean b(int i, int i2) {
        return i >= 50 && i < i2;
    }

    boolean bR() {
        return this.lh > -1 && this.li > -1;
    }

    void bS() {
        try {
            this.mr.b("onSizeChanged", new JSONObject().put("x", this.rm).put("y", this.rn).put("width", this.lh).put("height", this.li));
        } catch (Throwable e) {
            gw.e("Error occured while dispatching size change.", e);
        }
    }

    void bT() {
        try {
            this.mr.b("onStateChanged", new JSONObject().put("state", "resized"));
        } catch (Throwable e) {
            gw.e("Error occured while dispatching state change.", e);
        }
    }

    boolean c(int i, int i2) {
        return i >= 50 && i < i2;
    }

    public void execute() {
        gw.i("PLEASE IMPLEMENT mraid.resize()");
        if (this.mContext == null) {
            gw.w("Not an activity context. Cannot resize.");
        } else if (this.mr.ac().os) {
            gw.w("Is interstitial. Cannot resize an interstitial.");
        } else if (this.mr.dH()) {
            gw.w("Is expanded. Cannot resize an expanded banner.");
        } else {
            bQ();
            if (bR()) {
                WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                int a = gv.a(displayMetrics, this.lh) + 16;
                int a2 = gv.a(displayMetrics, this.li) + 16;
                ViewParent parent = this.mr.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.mr);
                }
                View linearLayout = new LinearLayout(this.mContext);
                linearLayout.setBackgroundColor(0);
                PopupWindow popupWindow = new PopupWindow(this.mContext);
                popupWindow.setHeight(a2);
                popupWindow.setWidth(a);
                popupWindow.setClippingEnabled(!this.ro);
                popupWindow.setContentView(linearLayout);
                linearLayout.addView(this.mr, -1, -1);
                popupWindow.showAtLocation(((Activity) this.mContext).getWindow().getDecorView(), 0, this.rm, this.rn);
                this.mr.a(new bd(this.mContext, new AdSize(this.lh, this.li)));
                bS();
                bT();
                return;
            }
            gw.w("Invalid width and height options. Cannot resize.");
        }
    }
}
