package com.miui.yellowpage.model;

/* compiled from: Express */
class m implements Comparable {
    private String dg;
    private String mName;

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return a((m) obj);
    }

    public String getName() {
        return this.mName;
    }

    public String aP() {
        return this.dg;
    }

    public m(String str, String str2) {
        this.mName = str;
        this.dg = str2;
    }

    public int a(m mVar) {
        return this.dg.compareTo(mVar.dg);
    }
}
