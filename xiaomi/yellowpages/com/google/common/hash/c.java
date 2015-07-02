package com.google.common.hash;

/* compiled from: Hashing */
final class c extends b {
    private final int hp;

    HashCode a(m[] mVarArr) {
        int i = 0;
        byte[] bArr = new byte[(this.hp / 8)];
        int length = mVarArr.length;
        int i2 = 0;
        while (i < length) {
            HashCode hk = mVarArr[i].hk();
            i2 += hk.b(bArr, i2, hk.w() / 8);
            i++;
        }
        return HashCode.c(bArr);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.hp != cVar.hp || this.gB.length != cVar.gB.length) {
            return false;
        }
        for (int i = 0; i < this.gB.length; i++) {
            if (!this.gB[i].equals(cVar.gB[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.hp;
        for (Object hashCode : this.gB) {
            i ^= hashCode.hashCode();
        }
        return i;
    }
}
