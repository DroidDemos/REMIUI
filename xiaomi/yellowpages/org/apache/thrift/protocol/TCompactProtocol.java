package org.apache.thrift.protocol;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.e;
import org.apache.thrift.transport.c;

public final class TCompactProtocol extends b {
    private static final f De;
    private static final e fp;
    private static final byte[] g;
    private e Df;
    private Boolean Dg;
    private byte[] Dh;
    byte[] cq;
    private e fT;
    private short i;
    byte[] mP;
    byte[] vx;

    public class Factory implements TProtocolFactory {
        public b a(c cVar) {
            return new TCompactProtocol(cVar);
        }
    }

    static {
        De = new f(ConfigConstant.WIRELESS_FILENAME);
        fp = new e(ConfigConstant.WIRELESS_FILENAME, (byte) 0, (short) 0);
        g = new byte[16];
        g[0] = (byte) 0;
        g[2] = (byte) 1;
        g[3] = (byte) 3;
        g[6] = (byte) 4;
        g[8] = (byte) 5;
        g[10] = (byte) 6;
        g[4] = (byte) 7;
        g[11] = (byte) 8;
        g[15] = (byte) 9;
        g[14] = (byte) 10;
        g[13] = (byte) 11;
        g[12] = (byte) 12;
    }

    public TCompactProtocol(c cVar) {
        super(cVar);
        this.Df = new e(15);
        this.i = (short) 0;
        this.fT = null;
        this.Dg = null;
        this.mP = new byte[5];
        this.vx = new byte[10];
        this.Dh = new byte[1];
        this.cq = new byte[1];
    }

    private void W(int i) {
        int i2 = 0;
        while ((i & -128) != 0) {
            int i3 = i2 + 1;
            this.mP[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2 = i3;
        }
        int i4 = i2 + 1;
        this.mP[i2] = (byte) i;
        this.lv.e(this.mP, 0, i4);
    }

    private int X(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private void Y(int i) {
        d((byte) i);
    }

    private byte[] Z(int i) {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.lv.j(bArr, 0, i);
        return bArr;
    }

    private void a(e eVar, byte b) {
        if (b == (byte) -1) {
            b = g(eVar.b);
        }
        if (eVar.An <= this.i || eVar.An - this.i > 15) {
            d(b);
            a(eVar.An);
        } else {
            Y(((eVar.An - this.i) << 4) | b);
        }
        this.i = eVar.An;
    }

    private int aa(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    private void b(long j) {
        int i = 0;
        while ((-128 & j) != 0) {
            int i2 = i + 1;
            this.vx[i] = (byte) ((int) (128 | (127 & j)));
            j >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.vx[i] = (byte) ((int) j);
        this.lv.e(this.vx, 0, i3);
    }

    private void d(byte b) {
        this.Dh[0] = b;
        this.lv.l(this.Dh);
    }

    private boolean e(byte b) {
        int i = b & 15;
        return i == 1 || i == 2;
    }

    private byte f(byte b) {
        switch ((byte) (b & 15)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return (byte) 0;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return (byte) 2;
            case WindowData.d /*3*/:
                return (byte) 3;
            case Base64.CRLF /*4*/:
                return (byte) 6;
            case WindowData.f /*5*/:
                return (byte) 8;
            case WindowData.g /*6*/:
                return (byte) 10;
            case WindowData.h /*7*/:
                return (byte) 4;
            case Base64.URL_SAFE /*8*/:
                return (byte) 11;
            case WindowData.j /*9*/:
                return (byte) 15;
            case WindowData.k /*10*/:
                return (byte) 14;
            case (byte) 11:
                return (byte) 13;
            case (byte) 12:
                return (byte) 12;
            default:
                throw new TProtocolException("don't know what type: " + ((byte) (b & 15)));
        }
    }

    private byte g(byte b) {
        return g[b];
    }

    private void g(byte[] bArr, int i, int i2) {
        W(i2);
        this.lv.e(bArr, i, i2);
    }

    private long gX() {
        long j = null;
        long j2 = 0;
        if (this.lv.c() >= 10) {
            byte[] ag = this.lv.ag();
            int ah = this.lv.ah();
            long j3 = 0;
            int i = 0;
            while (true) {
                byte b = ag[ah + i];
                j3 |= ((long) (b & 127)) << j;
                if ((b & 128) != 128) {
                    this.lv.a(i + 1);
                    return j3;
                }
                j += 7;
                i++;
            }
        } else {
            while (true) {
                byte by = by();
                j2 |= ((long) (by & 127)) << j;
                if ((by & 128) != 128) {
                    return j2;
                }
                j += 7;
            }
        }
    }

    private int gY() {
        int i = 0;
        int i2;
        if (this.lv.c() >= 5) {
            byte[] ag = this.lv.ag();
            int ah = this.lv.ah();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b = ag[ah + i3];
                i |= (b & 127) << i2;
                if ((b & 128) != 128) {
                    break;
                }
                i2 += 7;
                i3++;
            }
            this.lv.a(i3 + 1);
        } else {
            i2 = 0;
            while (true) {
                byte by = by();
                i |= (by & 127) << i2;
                if ((by & 128) != 128) {
                    break;
                }
                i2 += 7;
            }
        }
        return i;
    }

    private long j(byte[] bArr) {
        return ((((((((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[1]) & 255) << 8)) | (((long) bArr[0]) & 255);
    }

    private long n(long j) {
        return (j << 1) ^ (j >> 63);
    }

    private long o(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public void a() {
        this.i = this.Df.he();
    }

    protected void a(byte b, int i) {
        if (i <= 14) {
            Y((i << 4) | g(b));
            return;
        }
        Y(g(b) | 240);
        W(i);
    }

    public void a(int i) {
        W(X(i));
    }

    public void a(long j) {
        b(n(j));
    }

    public void a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            g(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new TException("UTF-8 not supported!");
        }
    }

    public void a(a aVar) {
        a(aVar.gG, aVar.b);
    }

    public void a(d dVar) {
        if (dVar.c == 0) {
            Y(0);
            return;
        }
        W(dVar.c);
        Y((g(dVar.gG) << 4) | g(dVar.b));
    }

    public void a(e eVar) {
        if (eVar.b == (byte) 2) {
            this.fT = eVar;
        } else {
            a(eVar, (byte) -1);
        }
    }

    public void a(f fVar) {
        this.Df.a(this.i);
        this.i = (short) 0;
    }

    public void a(g gVar) {
        a(gVar.gG, gVar.b);
    }

    public void a(short s) {
        W(X(s));
    }

    public void a(boolean z) {
        byte b = (byte) 2;
        if (this.fT != null) {
            e eVar = this.fT;
            if (z) {
                b = (byte) 1;
            }
            a(eVar, b);
            this.fT = null;
            return;
        }
        if (z) {
            b = (byte) 1;
        }
        d(b);
    }

    public void am() {
        this.i = this.Df.he();
    }

    public void ao() {
    }

    public void b() {
    }

    public int bA() {
        return aa(gY());
    }

    public long bB() {
        return o(gX());
    }

    public double bC() {
        byte[] bArr = new byte[8];
        this.lv.j(bArr, 0, 8);
        return Double.longBitsToDouble(j(bArr));
    }

    public String bD() {
        int gY = gY();
        if (gY == 0) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        try {
            if (this.lv.c() < gY) {
                return new String(Z(gY), "UTF-8");
            }
            String str = new String(this.lv.ag(), this.lv.ah(), gY, "UTF-8");
            this.lv.a(gY);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new TException("UTF-8 not supported!");
        }
    }

    public ByteBuffer bE() {
        int gY = gY();
        if (gY == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[gY];
        this.lv.j(bArr, 0, gY);
        return ByteBuffer.wrap(bArr);
    }

    public void bF() {
        this.Df.b();
        this.i = (short) 0;
    }

    public f bq() {
        this.Df.a(this.i);
        this.i = (short) 0;
        return De;
    }

    public e br() {
        byte by = by();
        if (by == (byte) 0) {
            return fp;
        }
        short s = (short) ((by & 240) >> 4);
        e eVar = new e(ConfigConstant.WIRELESS_FILENAME, f((byte) (by & 15)), s == (short) 0 ? bz() : (short) (s + this.i));
        if (e(by)) {
            this.Dg = ((byte) (by & 15)) == (byte) 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.i = eVar.An;
        return eVar;
    }

    public d bs() {
        int gY = gY();
        int by = gY == 0 ? 0 : by();
        return new d(f((byte) (by >> 4)), f((byte) (by & 15)), gY);
    }

    public g bt() {
        byte by = by();
        int i = (by >> 4) & 15;
        if (i == 15) {
            i = gY();
        }
        return new g(f(by), i);
    }

    public void bu() {
    }

    public a bv() {
        return new a(bt());
    }

    public void bw() {
    }

    public boolean bx() {
        if (this.Dg == null) {
            return by() == (byte) 1;
        } else {
            boolean booleanValue = this.Dg.booleanValue();
            this.Dg = null;
            return booleanValue;
        }
    }

    public byte by() {
        if (this.lv.c() > 0) {
            byte b = this.lv.ag()[this.lv.ah()];
            this.lv.a(1);
            return b;
        }
        this.lv.j(this.cq, 0, 1);
        return this.cq[0];
    }

    public short bz() {
        return (short) aa(gY());
    }

    public void c() {
        d((byte) 0);
    }

    public void d() {
    }

    public void e() {
    }

    public void f() {
    }

    public void f(ByteBuffer byteBuffer) {
        g(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset());
    }

    public void j() {
    }
}
