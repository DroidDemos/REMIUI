package com.miui.yellowpage.ui;

/* compiled from: CityPickerFragment */
class Q implements Comparable {
    final /* synthetic */ bD ge;
    private String iP;
    private String iQ;
    private l[] iR;
    private long mId;
    private String mName;

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return a((Q) obj);
    }

    public Q(bD bDVar, String str, String str2, String str3, long j) {
        this.ge = bDVar;
        this.mName = str;
        this.iP = str3;
        this.iQ = str2;
        this.mId = j;
        String[] split = str2.trim().split(" +");
        this.iR = new l[Math.min(this.mName.length(), split.length)];
        for (int i = 0; i < this.iR.length; i++) {
            this.iR[i] = new l(this, Character.valueOf(this.mName.charAt(i)), split[i]);
        }
        bDVar.yC.put(j, this.iP);
    }

    public String aO() {
        return this.iQ + ":" + this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public int a(Q q) {
        int min = Math.min(this.iR.length, q.iR.length);
        for (int i = 0; i < min; i++) {
            int a = this.iR[i].a(q.iR[i]);
            if (a != 0) {
                return a;
            }
        }
        return this.iR.length - q.iR.length;
    }
}
