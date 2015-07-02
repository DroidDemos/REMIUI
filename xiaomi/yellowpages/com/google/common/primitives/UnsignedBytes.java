package com.google.common.primitives;

import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Comparator;
import sun.misc.Unsafe;

public final class UnsignedBytes {

    class LexicographicalComparatorHolder {
        static final String Hv;
        static final Comparator Hw;

        enum PureJavaComparator implements Comparator {
            INSTANCE;

            public int a(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                for (int i = 0; i < min; i++) {
                    int compare = UnsignedBytes.compare(bArr[i], bArr2[i]);
                    if (compare != 0) {
                        return compare;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        enum UnsafeComparator implements Comparator {
            INSTANCE;
            
            static final boolean DS;
            static final Unsafe DT;
            static final int DU;

            static {
                DS = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
                DT = getUnsafe();
                DU = DT.arrayBaseOffset(byte[].class);
                if (DT.arrayIndexScale(byte[].class) != 1) {
                    throw new AssertionError();
                }
            }

            private static Unsafe getUnsafe() {
                Unsafe unsafe;
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (SecurityException e) {
                    try {
                        unsafe = (Unsafe) AccessController.doPrivileged(new c());
                    } catch (PrivilegedActionException e2) {
                        throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                    }
                }
                return unsafe;
            }

            public int a(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                int i = min / 8;
                int i2 = 0;
                while (i2 < i * 8) {
                    long j = DT.getLong(bArr, ((long) DU) + ((long) i2));
                    long j2 = DT.getLong(bArr2, ((long) DU) + ((long) i2));
                    if (j == j2) {
                        i2 += 8;
                    } else if (DS) {
                        return b.compare(j, j2);
                    } else {
                        i2 = Long.numberOfTrailingZeros(j ^ j2) & -8;
                        return (int) (((j >>> i2) & 255) - ((j2 >>> i2) & 255));
                    }
                }
                for (i *= 8; i < min; i++) {
                    i2 = UnsignedBytes.compare(bArr[i], bArr2[i]);
                    if (i2 != 0) {
                        return i2;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        LexicographicalComparatorHolder() {
        }

        static {
            Hv = LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator";
            Hw = hL();
        }

        static Comparator hL() {
            try {
                return (Comparator) Class.forName(Hv).getEnumConstants()[0];
            } catch (Throwable th) {
                return UnsignedBytes.hT();
            }
        }
    }

    private UnsignedBytes() {
    }

    public static int h(byte b) {
        return b & 255;
    }

    public static int compare(byte b, byte b2) {
        return h(b) - h(b2);
    }

    static Comparator hT() {
        return PureJavaComparator.INSTANCE;
    }
}
