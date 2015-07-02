package com.miui.yellowpage.model;

/* compiled from: Express */
class d extends m {
    private int iC;

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return a((m) obj);
    }

    public d(String str, String str2, int i) {
        super(str, str2);
        this.iC = i;
    }

    public int a(m mVar) {
        if (mVar instanceof d) {
            return this.iC - ((d) mVar).iC;
        }
        throw new UnsupportedOperationException("Provided object is not an instance of RecommendEntry");
    }
}
