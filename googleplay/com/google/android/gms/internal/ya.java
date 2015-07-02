package com.google.android.gms.internal;

class ya {
    private static final yb aYm;
    private boolean aYn;
    private int[] aYo;
    private yb[] aYp;
    private int mSize;

    static {
        aYm = new yb();
    }

    public ya() {
        this(10);
    }

    public ya(int i) {
        this.aYn = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.aYo = new int[idealIntArraySize];
        this.aYp = new yb[idealIntArraySize];
        this.mSize = 0;
    }

    private boolean a(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean a(yb[] ybVarArr, yb[] ybVarArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!ybVarArr[i2].equals(ybVarArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private void gc() {
        int i = this.mSize;
        int[] iArr = this.aYo;
        yb[] ybVarArr = this.aYp;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            yb ybVar = ybVarArr[i3];
            if (ybVar != aYm) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    ybVarArr[i2] = ybVar;
                    ybVarArr[i3] = null;
                }
                i2++;
            }
        }
        this.aYn = false;
        this.mSize = i2;
    }

    private int idealByteArraySize(int need) {
        for (int i = 4; i < 32; i++) {
            if (need <= (1 << i) - 12) {
                return (1 << i) - 12;
            }
        }
        return need;
    }

    private int idealIntArraySize(int need) {
        return idealByteArraySize(need * 4) / 4;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ya)) {
            return false;
        }
        ya yaVar = (ya) o;
        if (size() != yaVar.size()) {
            return false;
        }
        return a(this.aYo, yaVar.aYo, this.mSize) && a(this.aYp, yaVar.aYp, this.mSize);
    }

    public int hashCode() {
        if (this.aYn) {
            gc();
        }
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.aYo[i2]) * 31) + this.aYp[i2].hashCode();
        }
        return i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public yb mr(int i) {
        if (this.aYn) {
            gc();
        }
        return this.aYp[i];
    }

    public int size() {
        if (this.aYn) {
            gc();
        }
        return this.mSize;
    }
}
