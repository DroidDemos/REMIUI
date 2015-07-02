package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

@fd
public class gf {
    private static final gf vW;
    public static final String vX;
    private Context mContext;
    private final Object mK;
    private ar nG;
    private aq nH;
    private fc nI;
    private gx qK;
    private boolean uV;
    private boolean uW;
    public final String vY;
    private final gg vZ;
    private BigInteger wb;
    private final HashSet<Object> wc;
    private final HashMap<String, Object> wd;
    private boolean we;
    private boolean wf;
    private as wg;
    private LinkedList<Thread> wh;
    private boolean wi;
    private Bundle wj;
    private String wk;

    static {
        vW = new gf();
        vX = vW.vY;
    }

    private gf() {
        this.mK = new Object();
        this.wb = BigInteger.ONE;
        this.wc = new HashSet();
        this.wd = new HashMap();
        this.we = false;
        this.uV = true;
        this.wf = false;
        this.uW = true;
        this.nG = null;
        this.wg = null;
        this.nH = null;
        this.wh = new LinkedList();
        this.wi = false;
        this.wj = bs.by();
        this.nI = null;
        this.vY = gn.dx();
        this.vZ = new gg(this.vY);
    }

    public static Bundle bN() {
        return vW.dp();
    }

    public static String c(int i, String str) {
        return vW.d(i, str);
    }

    public static String dn() {
        return vW.do();
    }

    public static void e(Throwable th) {
        vW.f(th);
    }

    public String d(int i, String str) {
        Resources resources = this.qK.wU ? this.mContext.getResources() : GooglePlayServicesUtil.getRemoteResource(this.mContext);
        return resources == null ? str : resources.getString(i);
    }

    public String do() {
        String str;
        synchronized (this.mK) {
            str = this.wk;
        }
        return str;
    }

    public Bundle dp() {
        Bundle bundle;
        synchronized (this.mK) {
            bundle = this.wj;
        }
        return bundle;
    }

    public void f(Throwable th) {
        if (this.wf) {
            new fc(this.mContext, this.qK, null, null).b(th);
        }
    }
}
