package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.xx;
import com.google.android.gms.internal.xy;
import java.io.IOException;

public final class ak extends xy<ak> {
    public String aaQ;
    public long aaR;
    public long aaS;
    public int versionCode;

    public ak() {
        jV();
    }

    protected int c() {
        return (((super.c() + xx.E(1, this.versionCode)) + xx.j(2, this.aaQ)) + xx.e(3, this.aaR)) + xx.e(4, this.aaS);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ak)) {
            return false;
        }
        ak akVar = (ak) o;
        if (this.versionCode != akVar.versionCode) {
            return false;
        }
        if (this.aaQ == null) {
            if (akVar.aaQ != null) {
                return false;
            }
        } else if (!this.aaQ.equals(akVar.aaQ)) {
            return false;
        }
        return (this.aaR == akVar.aaR && this.aaS == akVar.aaS) ? a(akVar) : false;
    }

    public int hashCode() {
        return (((((((this.aaQ == null ? 0 : this.aaQ.hashCode()) + ((this.versionCode + 527) * 31)) * 31) + ((int) (this.aaR ^ (this.aaR >>> 32)))) * 31) + ((int) (this.aaS ^ (this.aaS >>> 32)))) * 31) + vL();
    }

    public ak jV() {
        this.versionCode = 1;
        this.aaQ = "";
        this.aaR = -1;
        this.aaS = -1;
        this.aYj = null;
        this.aYu = -1;
        return this;
    }

    public void writeTo(xx output) throws IOException {
        output.C(1, this.versionCode);
        output.b(2, this.aaQ);
        output.c(3, this.aaR);
        output.c(4, this.aaS);
        super.writeTo(output);
    }
}
