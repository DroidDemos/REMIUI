package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.dynamic.e;

@fd
public final class dw implements SafeParcelable {
    public static final dv CREATOR;
    public final gx lR;
    public final int orientation;
    public final dt sb;
    public final y sc;
    public final dx sd;
    public final gz se;
    public final cg sf;
    public final String sg;
    public final boolean sh;
    public final String si;
    public final ea sj;
    public final int sk;
    public final cj sl;
    public final String sm;
    public final ad sn;
    public final String url;
    public final int versionCode;

    static {
        CREATOR = new dv();
    }

    dw(int i, dt dtVar, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i2, int i3, String str3, gx gxVar, IBinder iBinder6, String str4, ad adVar) {
        this.versionCode = i;
        this.sb = dtVar;
        this.sc = (y) e.i(a.bH(iBinder));
        this.sd = (dx) e.i(a.bH(iBinder2));
        this.se = (gz) e.i(a.bH(iBinder3));
        this.sf = (cg) e.i(a.bH(iBinder4));
        this.sg = str;
        this.sh = z;
        this.si = str2;
        this.sj = (ea) e.i(a.bH(iBinder5));
        this.orientation = i2;
        this.sk = i3;
        this.url = str3;
        this.lR = gxVar;
        this.sl = (cj) e.i(a.bH(iBinder6));
        this.sm = str4;
        this.sn = adVar;
    }

    public dw(dt dtVar, y yVar, dx dxVar, ea eaVar, gx gxVar) {
        this.versionCode = 4;
        this.sb = dtVar;
        this.sc = yVar;
        this.sd = dxVar;
        this.se = null;
        this.sf = null;
        this.sg = null;
        this.sh = false;
        this.si = null;
        this.sj = eaVar;
        this.orientation = -1;
        this.sk = 4;
        this.url = null;
        this.lR = gxVar;
        this.sl = null;
        this.sm = null;
        this.sn = null;
    }

    public dw(y yVar, dx dxVar, cg cgVar, ea eaVar, gz gzVar, boolean z, int i, String str, gx gxVar, cj cjVar) {
        this.versionCode = 4;
        this.sb = null;
        this.sc = yVar;
        this.sd = dxVar;
        this.se = gzVar;
        this.sf = cgVar;
        this.sg = null;
        this.sh = z;
        this.si = null;
        this.sj = eaVar;
        this.orientation = i;
        this.sk = 3;
        this.url = str;
        this.lR = gxVar;
        this.sl = cjVar;
        this.sm = null;
        this.sn = null;
    }

    public dw(y yVar, dx dxVar, cg cgVar, ea eaVar, gz gzVar, boolean z, int i, String str, String str2, gx gxVar, cj cjVar) {
        this.versionCode = 4;
        this.sb = null;
        this.sc = yVar;
        this.sd = dxVar;
        this.se = gzVar;
        this.sf = cgVar;
        this.sg = str2;
        this.sh = z;
        this.si = str;
        this.sj = eaVar;
        this.orientation = i;
        this.sk = 3;
        this.url = null;
        this.lR = gxVar;
        this.sl = cjVar;
        this.sm = null;
        this.sn = null;
    }

    public dw(y yVar, dx dxVar, ea eaVar, gz gzVar, boolean z, int i, gx gxVar) {
        this.versionCode = 4;
        this.sb = null;
        this.sc = yVar;
        this.sd = dxVar;
        this.se = gzVar;
        this.sf = null;
        this.sg = null;
        this.sh = z;
        this.si = null;
        this.sj = eaVar;
        this.orientation = i;
        this.sk = 2;
        this.url = null;
        this.lR = gxVar;
        this.sl = null;
        this.sm = null;
        this.sn = null;
    }

    public static void a(Intent intent, dw dwVar) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", dwVar);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static dw b(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(dw.class.getClassLoader());
            return (dw) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    IBinder ck() {
        return e.q(this.sc).asBinder();
    }

    IBinder cl() {
        return e.q(this.sd).asBinder();
    }

    IBinder cm() {
        return e.q(this.se).asBinder();
    }

    IBinder cn() {
        return e.q(this.sf).asBinder();
    }

    IBinder co() {
        return e.q(this.sl).asBinder();
    }

    IBinder cp() {
        return e.q(this.sj).asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        dv.a(this, out, flags);
    }
}
