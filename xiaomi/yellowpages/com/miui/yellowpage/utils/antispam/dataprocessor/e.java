package com.miui.yellowpage.utils.antispam.dataprocessor;

/* compiled from: Feed */
public class e {
    private static boolean mH;
    private long count;
    private long mI;
    private long mJ;
    private long offset;

    static {
        mH = false;
    }

    public e(long j, long j2, long j3, long j4) {
        if (mH) {
            throw new IllegalStateException("execute exitOutputMode() first");
        }
        this.offset = j;
        this.mI = j2;
        this.mJ = j3;
        this.count = j4;
    }

    public static e e(byte[] bArr) {
        int byteArrayToInt = h.byteArrayToInt(bArr);
        return new e((long) ((byteArrayToInt >>> 13) & 32767), (long) ((byteArrayToInt >>> 28) & 15), (long) ((byteArrayToInt >>> 9) & 15), (long) (byteArrayToInt & 511));
    }

    public long bN() {
        return this.offset;
    }

    public boolean bO() {
        return this.mI == 0;
    }

    public long bP() {
        return this.mI;
    }

    public long bQ() {
        return this.mJ;
    }

    public long getCount() {
        return this.count;
    }
}
