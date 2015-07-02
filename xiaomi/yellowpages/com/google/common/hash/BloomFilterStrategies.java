package com.google.common.hash;

import com.google.common.primitives.a;

enum BloomFilterStrategies implements Strategy {
    MURMUR128_MITZ_32 {
        public boolean a(Object obj, Funnel funnel, int i, a aVar) {
            long t = aVar.t();
            long y = Hashing.bG().a(obj, funnel).y();
            int i2 = (int) y;
            int i3 = (int) (y >>> 32);
            boolean z = false;
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 ^= -1;
                }
                z |= aVar.c(((long) i5) % t);
            }
            return z;
        }

        public boolean b(Object obj, Funnel funnel, int i, a aVar) {
            long t = aVar.t();
            long y = Hashing.bG().a(obj, funnel).y();
            int i2 = (int) y;
            int i3 = (int) (y >>> 32);
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 ^= -1;
                }
                if (!aVar.d(((long) i5) % t)) {
                    return false;
                }
            }
            return true;
        }
    },
    MURMUR128_MITZ_64 {
        public boolean a(Object obj, Funnel funnel, int i, a aVar) {
            int i2 = 0;
            long t = aVar.t();
            byte[] A = Hashing.bG().a(obj, funnel).A();
            long h = h(A);
            long i3 = i(A);
            boolean z = false;
            while (i2 < i) {
                z |= aVar.c((Long.MAX_VALUE & h) % t);
                h += i3;
                i2++;
            }
            return z;
        }

        public boolean b(Object obj, Funnel funnel, int i, a aVar) {
            long t = aVar.t();
            byte[] A = Hashing.bG().a(obj, funnel).A();
            long h = h(A);
            long i2 = i(A);
            for (int i3 = 0; i3 < i; i3++) {
                if (!aVar.d((Long.MAX_VALUE & h) % t)) {
                    return false;
                }
                h += i2;
            }
            return true;
        }

        private long h(byte[] bArr) {
            return a.a(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long i(byte[] bArr) {
            return a.a(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }
    };
}
