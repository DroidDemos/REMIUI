package com.google.common.a;

import com.alipay.sdk.protocol.WindowData;
import com.google.common.b.a;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.math.RoundingMode;

/* compiled from: LongMath */
public final class b {
    public static long a(long j, long j2, RoundingMode roundingMode) {
        a.checkNotNull(roundingMode);
        long j3 = j / j2;
        long j4 = j - (j2 * j3);
        if (j4 == 0) {
            return j3;
        }
        int i;
        int i2 = ((int) ((j ^ j2) >> 63)) | 1;
        switch (c.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                a.c(j4 == 0);
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                break;
            case WindowData.d /*3*/:
                i = 1;
                break;
            case Base64.CRLF /*4*/:
                if (i2 <= 0) {
                    i = 0;
                    break;
                }
                i = 1;
                break;
            case WindowData.f /*5*/:
                if (i2 >= 0) {
                    i = 0;
                    break;
                }
                i = 1;
                break;
            case WindowData.g /*6*/:
            case WindowData.h /*7*/:
            case Base64.URL_SAFE /*8*/:
                j4 = Math.abs(j4);
                j4 -= Math.abs(j2) - j4;
                if (j4 != 0) {
                    if (j4 <= 0) {
                        i = 0;
                        break;
                    }
                    i = 1;
                    break;
                }
                i = (roundingMode == RoundingMode.HALF_UP ? 1 : 0) | (((1 & j3) != 0 ? 1 : 0) & (roundingMode == RoundingMode.HALF_EVEN ? 1 : 0));
                break;
            default:
                throw new AssertionError();
        }
        i = 0;
        if (i != 0) {
            j4 = ((long) i2) + j3;
        } else {
            j4 = j3;
        }
        return j4;
    }

    private b() {
    }
}
