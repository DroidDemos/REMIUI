package com.google.common.hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: Murmur3_128HashFunction */
final class d extends l {
    private long iA;
    private long iz;
    private int length;

    d(int i) {
        super(16);
        this.iz = (long) i;
        this.iA = (long) i;
        this.length = 0;
    }

    protected void d(ByteBuffer byteBuffer) {
        b(byteBuffer.getLong(), byteBuffer.getLong());
        this.length += 16;
    }

    private void b(long j, long j2) {
        this.iz ^= h(j);
        this.iz = Long.rotateLeft(this.iz, 27);
        this.iz += this.iA;
        this.iz = (this.iz * 5) + 1390208809;
        this.iA ^= i(j2);
        this.iA = Long.rotateLeft(this.iA, 31);
        this.iA += this.iz;
        this.iA = (this.iA * 5) + 944331445;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void e(java.nio.ByteBuffer r13) {
        /*
        r12 = this;
        r11 = 40;
        r10 = 32;
        r9 = 24;
        r8 = 16;
        r7 = 8;
        r2 = 0;
        r0 = 0;
        r4 = r12.length;
        r5 = r13.remaining();
        r4 = r4 + r5;
        r12.length = r4;
        r4 = r13.remaining();
        switch(r4) {
            case 1: goto L_0x00e2;
            case 2: goto L_0x00d6;
            case 3: goto L_0x00ca;
            case 4: goto L_0x00be;
            case 5: goto L_0x00b2;
            case 6: goto L_0x00a6;
            case 7: goto L_0x0098;
            case 8: goto L_0x0080;
            case 9: goto L_0x0076;
            case 10: goto L_0x0069;
            case 11: goto L_0x005c;
            case 12: goto L_0x004f;
            case 13: goto L_0x0042;
            case 14: goto L_0x0035;
            case 15: goto L_0x0026;
            default: goto L_0x001e;
        };
    L_0x001e:
        r0 = new java.lang.AssertionError;
        r1 = "Should never get here.";
        r0.<init>(r1);
        throw r0;
    L_0x0026:
        r4 = 14;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r6 = 48;
        r4 = r4 << r6;
        r0 = r0 ^ r4;
    L_0x0035:
        r4 = 13;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r11;
        r0 = r0 ^ r4;
    L_0x0042:
        r4 = 12;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r10;
        r0 = r0 ^ r4;
    L_0x004f:
        r4 = 11;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r9;
        r0 = r0 ^ r4;
    L_0x005c:
        r4 = 10;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r8;
        r0 = r0 ^ r4;
    L_0x0069:
        r4 = 9;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r7;
        r0 = r0 ^ r4;
    L_0x0076:
        r4 = r13.get(r7);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r0 = r0 ^ r4;
    L_0x0080:
        r4 = r13.getLong();
        r2 = r2 ^ r4;
    L_0x0085:
        r4 = r12.iz;
        r2 = h(r2);
        r2 = r2 ^ r4;
        r12.iz = r2;
        r2 = r12.iA;
        r0 = i(r0);
        r0 = r0 ^ r2;
        r12.iA = r0;
        return;
    L_0x0098:
        r4 = 6;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r6 = 48;
        r4 = r4 << r6;
        r2 = r2 ^ r4;
    L_0x00a6:
        r4 = 5;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r11;
        r2 = r2 ^ r4;
    L_0x00b2:
        r4 = 4;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r10;
        r2 = r2 ^ r4;
    L_0x00be:
        r4 = 3;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r9;
        r2 = r2 ^ r4;
    L_0x00ca:
        r4 = 2;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r8;
        r2 = r2 ^ r4;
    L_0x00d6:
        r4 = 1;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r4 = r4 << r7;
        r2 = r2 ^ r4;
    L_0x00e2:
        r4 = 0;
        r4 = r13.get(r4);
        r4 = com.google.common.primitives.UnsignedBytes.h(r4);
        r4 = (long) r4;
        r2 = r2 ^ r4;
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.d.e(java.nio.ByteBuffer):void");
    }

    public HashCode aJ() {
        this.iz ^= (long) this.length;
        this.iA ^= (long) this.length;
        this.iz += this.iA;
        this.iA += this.iz;
        this.iz = g(this.iz);
        this.iA = g(this.iA);
        this.iz += this.iA;
        this.iA += this.iz;
        return HashCode.c(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.iz).putLong(this.iA).array());
    }

    private static long g(long j) {
        long j2 = ((j >>> 33) ^ j) * -49064778989728563L;
        j2 = (j2 ^ (j2 >>> 33)) * -4265267296055464877L;
        return j2 ^ (j2 >>> 33);
    }

    private static long h(long j) {
        return Long.rotateLeft(-8663945395140668459L * j, 31) * 5545529020109919103L;
    }

    private static long i(long j) {
        return Long.rotateLeft(5545529020109919103L * j, 33) * -8663945395140668459L;
    }
}
