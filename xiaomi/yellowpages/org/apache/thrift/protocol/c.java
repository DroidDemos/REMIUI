package org.apache.thrift.protocol;

import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import org.apache.thrift.TException;

public class c {
    private static int a;

    static {
        a = Integer.MAX_VALUE;
    }

    public static void a(b bVar, byte b) {
        a(bVar, b, a);
    }

    public static void a(b bVar, byte b, int i) {
        int i2 = 0;
        if (i <= 0) {
            throw new TException("Maximum skip depth exceeded");
        }
        switch (b) {
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                bVar.bx();
                return;
            case WindowData.d /*3*/:
                bVar.by();
                return;
            case Base64.CRLF /*4*/:
                bVar.bC();
                return;
            case WindowData.g /*6*/:
                bVar.bz();
                return;
            case Base64.URL_SAFE /*8*/:
                bVar.bA();
                return;
            case WindowData.k /*10*/:
                bVar.bB();
                return;
            case (byte) 11:
                bVar.bE();
                return;
            case (byte) 12:
                bVar.bq();
                while (true) {
                    e br = bVar.br();
                    if (br.b == (byte) 0) {
                        bVar.am();
                        return;
                    } else {
                        a(bVar, br.b, i - 1);
                        bVar.j();
                    }
                }
            case (byte) 13:
                d bs = bVar.bs();
                while (i2 < bs.c) {
                    a(bVar, bs.gG, i - 1);
                    a(bVar, bs.b, i - 1);
                    i2++;
                }
                bVar.ao();
                return;
            case (byte) 14:
                a bv = bVar.bv();
                while (i2 < bv.b) {
                    a(bVar, bv.gG, i - 1);
                    i2++;
                }
                bVar.bw();
                return;
            case (byte) 15:
                g bt = bVar.bt();
                while (i2 < bt.b) {
                    a(bVar, bt.gG, i - 1);
                    i2++;
                }
                bVar.bu();
                return;
            default:
                return;
        }
    }
}
