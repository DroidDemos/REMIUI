package com.miui.yellowpage.utils.antispam.dataprocessor;

import com.alipay.sdk.data.Response;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Reader */
public class d {
    private static int kT;
    private static int kU;
    private static int kV;
    private static int kW;
    private static int kX;
    private static int kY;
    static List kZ;
    static Map la;
    private RandomAccessFile kS;

    public d(RandomAccessFile randomAccessFile) {
        this.kS = randomAccessFile;
    }

    public static boolean a(RandomAccessFile randomAccessFile) {
        kZ = new ArrayList();
        la = new HashMap();
        byte[] bArr = new byte[4];
        try {
            randomAccessFile.read(bArr);
            if (h.byteArrayToInt(bArr) != b.gY) {
                return false;
            }
            randomAccessFile.read(bArr);
            kT = h.byteArrayToInt(bArr);
            kU = be();
            a(randomAccessFile, bArr);
            b(randomAccessFile, bArr);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void a(RandomAccessFile randomAccessFile, byte[] bArr) {
        kV = d(bf(), getEntryCount());
        randomAccessFile.seek((long) bg());
        randomAccessFile.read(bArr);
        kW = h.byteArrayToInt(bArr);
        byte[] bArr2 = new byte[bh()];
        randomAccessFile.readFully(bArr2);
        d(randomAccessFile, bArr2);
    }

    private static void b(RandomAccessFile randomAccessFile, byte[] bArr) {
        kX = c(bg(), bh());
        randomAccessFile.seek((long) bi());
        randomAccessFile.read(bArr);
        kY = h.byteArrayToInt(bArr);
        byte[] bArr2 = new byte[bj()];
        randomAccessFile.readFully(bArr2);
        c(randomAccessFile, bArr2);
    }

    private static void c(RandomAccessFile randomAccessFile, byte[] bArr) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr)));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                String[] split = readLine.split("\t");
                la.put(split[0], new i(split[1], split[2], split[3]));
            } else {
                return;
            }
        }
    }

    private static void d(RandomAccessFile randomAccessFile, byte[] bArr) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr)));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                kZ.add(new BigInteger(readLine));
            } else {
                return;
            }
        }
    }

    private static int c(int i, int i2) {
        return (i + i2) + 4;
    }

    private static int d(int i, int i2) {
        return (i2 * 4) + i;
    }

    private static int be() {
        return 8;
    }

    public i M(String str) {
        i iVar = (i) la.get(f.W(str));
        if (iVar != null) {
            return iVar;
        }
        HashMap N = N(str);
        if (N != null) {
            return (i) N.get(str);
        }
        return null;
    }

    private HashMap N(String str) {
        HashMap reader$1 = new Reader$1(this);
        g dz = new g(this, new BigInteger(c.G(f.W(str)))).dz();
        int pageIndex = dz.getPageIndex();
        BigInteger dy = dz.dy();
        if (pageIndex < 0) {
            return reader$1;
        }
        try {
            this.kS.seek((long) (bf() + ((pageIndex * Response.a) * 4)));
            byte[] bArr = new byte[o(pageIndex)];
            this.kS.readFully(bArr);
            BigInteger bigInteger = dy;
            for (pageIndex = 0; pageIndex < bArr.length; pageIndex += 4) {
                e e = e.e(h.h(bArr, pageIndex, 4));
                if (pageIndex == 0) {
                    if (e.bO()) {
                        reader$1.put(c.H(dy.toString()), new i(null, null, null));
                    } else {
                        reader$1.put(c.H(dy.toString()), new i(String.valueOf(e.bP()), String.valueOf(e.bQ()), String.valueOf(e.getCount())));
                    }
                } else if (e.bO()) {
                    bigInteger = bigInteger.add(a.a(e));
                    reader$1.put(c.H(bigInteger.toString()), new i(null, null, null));
                } else {
                    bigInteger = bigInteger.add(new BigInteger(String.valueOf(e.bN())));
                    reader$1.put(c.H(bigInteger.toString()), new i(String.valueOf(e.bP()), String.valueOf(e.bQ()), String.valueOf(e.getCount())));
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return reader$1;
    }

    private static int o(int i) {
        if (i < kZ.size() - 1) {
            return 4000;
        }
        return (getEntryCount() % Response.a) * 4;
    }

    public static int getEntryCount() {
        return kT;
    }

    public static int bf() {
        return kU;
    }

    public static int bg() {
        return kV;
    }

    public static int bh() {
        return kW;
    }

    public static int bi() {
        return kX;
    }

    public static int bj() {
        return kY;
    }
}
