package com.miui.yellowpage.ui;

/* compiled from: CityPickerFragment */
class l implements Comparable {
    private Character dA;
    private String dB;
    final /* synthetic */ Q dC;

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return a((l) obj);
    }

    public l(Q q, Character ch, String str) {
        this.dC = q;
        this.dA = ch;
        this.dB = str;
    }

    public int a(l lVar) {
        int compareTo = this.dB.compareTo(lVar.dB);
        if (compareTo != 0) {
            return compareTo;
        }
        return this.dA.compareTo(lVar.dA);
    }
}
