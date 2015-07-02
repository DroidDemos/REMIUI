package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;

@fd
public class ap {
    private final Object mK;
    private int nA;
    private String nB;
    private final int ns;
    private final int nt;
    private final int nu;
    private final au nv;
    private ArrayList<String> nw;
    private int nx;
    private int ny;
    private int nz;

    public ap(int i, int i2, int i3, int i4) {
        this.mK = new Object();
        this.nw = new ArrayList();
        this.nx = 0;
        this.ny = 0;
        this.nz = 0;
        this.nB = "";
        this.ns = i;
        this.nt = i2;
        this.nu = i3;
        this.nv = new au(i4);
    }

    private String a(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
            stringBuffer.append(' ');
            if (stringBuffer.length() > i) {
                break;
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() >= i ? stringBuffer2.substring(0, i) : stringBuffer2;
    }

    private void m(String str) {
        if (str != null && str.length() >= this.nu) {
            synchronized (this.mK) {
                this.nw.add(str);
                this.nx += str.length();
            }
        }
    }

    int a(int i, int i2) {
        return (this.ns * i) + (this.nt * i2);
    }

    public boolean aS() {
        boolean z;
        synchronized (this.mK) {
            z = this.nz == 0;
        }
        return z;
    }

    public String aT() {
        return this.nB;
    }

    public void aV() {
        synchronized (this.mK) {
            this.nz--;
        }
    }

    public void aW() {
        synchronized (this.mK) {
            this.nz++;
        }
    }

    public void aX() {
        synchronized (this.mK) {
            int a = a(this.nx, this.ny);
            if (a > this.nA) {
                this.nA = a;
                this.nB = this.nv.a(this.nw);
            }
        }
    }

    int aY() {
        return this.nx;
    }

    public void c(int i) {
        this.ny = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ap)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ap apVar = (ap) obj;
        return apVar.aT() != null && apVar.aT().equals(aT());
    }

    public int hashCode() {
        return aT().hashCode();
    }

    public void k(String str) {
        m(str);
        synchronized (this.mK) {
            if (this.nz < 0) {
                gw.d("ActivityContent: negative number of WebViews.");
            }
            aX();
        }
    }

    public void l(String str) {
        m(str);
    }

    public String toString() {
        return "ActivityContent fetchId: " + this.ny + " score:" + this.nA + " total_length:" + this.nx + "\n text: " + a(this.nw, 200) + "\n signture: " + this.nB;
    }
}
