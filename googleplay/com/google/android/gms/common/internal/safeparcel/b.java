package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class b {
    private static int H(Parcel parcel, int i) {
        parcel.writeInt(-65536 | i);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void I(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(i2);
        parcel.setDataPosition(dataPosition);
    }

    public static void J(Parcel parcel, int i) {
        I(parcel, i);
    }

    public static void a(Parcel parcel, int i, byte b) {
        b(parcel, i, 4);
        parcel.writeInt(b);
    }

    public static void a(Parcel parcel, int i, double d) {
        b(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void a(Parcel parcel, int i, float f) {
        b(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void a(Parcel parcel, int i, long j) {
        b(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void a(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle != null) {
            int H = H(parcel, i);
            parcel.writeBundle(bundle);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int H = H(parcel, i);
            parcel.writeStrongBinder(iBinder);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, Parcel parcel2, boolean z) {
        if (parcel2 != null) {
            int H = H(parcel, i);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int H = H(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, Boolean bool, boolean z) {
        int i2 = 0;
        if (bool != null) {
            b(parcel, i, 4);
            if (bool.booleanValue()) {
                i2 = 1;
            }
            parcel.writeInt(i2);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, Double d, boolean z) {
        if (d != null) {
            b(parcel, i, 8);
            parcel.writeDouble(d.doubleValue());
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, Integer num, boolean z) {
        if (num != null) {
            b(parcel, i, 4);
            parcel.writeInt(num.intValue());
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, Long l, boolean z) {
        if (l != null) {
            b(parcel, i, 8);
            parcel.writeLong(l.longValue());
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int H = H(parcel, i);
            parcel.writeString(str);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, List<Integer> list, boolean z) {
        if (list != null) {
            int H = H(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(((Integer) list.get(i2)).intValue());
            }
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, short s) {
        b(parcel, i, 4);
        parcel.writeInt(s);
    }

    public static void a(Parcel parcel, int i, boolean z) {
        b(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void a(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr != null) {
            int H = H(parcel, i);
            parcel.writeByteArray(bArr);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, double[] dArr, boolean z) {
        if (dArr != null) {
            int H = H(parcel, i);
            parcel.writeDoubleArray(dArr);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, float[] fArr, boolean z) {
        if (fArr != null) {
            int H = H(parcel, i);
            parcel.writeFloatArray(fArr);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, int[] iArr, boolean z) {
        if (iArr != null) {
            int H = H(parcel, i);
            parcel.writeIntArray(iArr);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static <T extends Parcelable> void a(Parcel parcel, int i, T[] tArr, int i2, boolean z) {
        if (tArr != null) {
            int H = H(parcel, i);
            parcel.writeInt(r3);
            for (Parcelable parcelable : tArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    a(parcel, parcelable, i2);
                }
            }
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr != null) {
            int H = H(parcel, i);
            parcel.writeStringArray(strArr);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void a(Parcel parcel, int i, byte[][] bArr, boolean z) {
        int i2 = 0;
        if (bArr != null) {
            int H = H(parcel, i);
            int length = bArr.length;
            parcel.writeInt(length);
            while (i2 < length) {
                parcel.writeByteArray(bArr[i2]);
                i2++;
            }
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    private static <T extends Parcelable> void a(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    private static void b(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(-65536 | i);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt((i2 << 16) | i);
    }

    public static void b(Parcel parcel, int i, List<Long> list, boolean z) {
        if (list != null) {
            int H = H(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeLong(((Long) list.get(i2)).longValue());
            }
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static int bU(Parcel parcel) {
        return H(parcel, 20293);
    }

    public static void c(Parcel parcel, int i, int i2) {
        b(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void c(Parcel parcel, int i, List<String> list, boolean z) {
        if (list != null) {
            int H = H(parcel, i);
            parcel.writeStringList(list);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static <T extends Parcelable> void d(Parcel parcel, int i, List<T> list, boolean z) {
        if (list != null) {
            int H = H(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    a(parcel, parcelable, 0);
                }
            }
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }

    public static void f(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int H = H(parcel, i);
            parcel.writeList(list);
            I(parcel, H);
        } else if (z) {
            b(parcel, i, 0);
        }
    }
}
