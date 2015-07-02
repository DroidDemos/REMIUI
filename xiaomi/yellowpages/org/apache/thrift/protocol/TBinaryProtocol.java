package org.apache.thrift.protocol;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.transport.c;

public class TBinaryProtocol extends b {
    private static final f Mu;
    private byte[] Dh;
    private byte[] Mv;
    private byte[] Mw;
    private byte[] Mx;
    private byte[] My;
    private byte[] Mz;
    protected boolean a;
    protected boolean b;
    protected int c;
    protected boolean d;
    private byte[] g;
    private byte[] i;

    public class Factory implements TProtocolFactory {
        protected boolean a;
        protected boolean b;
        protected int c;

        public Factory() {
            this(false, true);
        }

        public Factory(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public Factory(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        public b a(c cVar) {
            b tBinaryProtocol = new TBinaryProtocol(cVar, this.a, this.b);
            if (this.c != 0) {
                tBinaryProtocol.ak(this.c);
            }
            return tBinaryProtocol;
        }
    }

    static {
        Mu = new f();
    }

    public TBinaryProtocol(c cVar, boolean z, boolean z2) {
        super(cVar);
        this.a = false;
        this.b = true;
        this.d = false;
        this.g = new byte[1];
        this.Mv = new byte[2];
        this.i = new byte[4];
        this.Mw = new byte[8];
        this.Mx = new byte[1];
        this.Dh = new byte[2];
        this.My = new byte[4];
        this.Mz = new byte[8];
        this.a = z;
        this.b = z2;
    }

    private int a(byte[] bArr, int i, int i2) {
        Y(i2);
        return this.lv.j(bArr, i, i2);
    }

    protected void Y(int i) {
        if (i < 0) {
            throw new TException("Negative length: " + i);
        } else if (this.d) {
            this.c -= i;
            if (this.c < 0) {
                throw new TException("Message length exceeded: " + i);
            }
        }
    }

    public void a() {
    }

    public void a(int i) {
        this.i[0] = (byte) ((i >> 24) & 255);
        this.i[1] = (byte) ((i >> 16) & 255);
        this.i[2] = (byte) ((i >> 8) & 255);
        this.i[3] = (byte) (i & 255);
        this.lv.e(this.i, 0, 4);
    }

    public void a(long j) {
        this.Mw[0] = (byte) ((int) ((j >> 56) & 255));
        this.Mw[1] = (byte) ((int) ((j >> 48) & 255));
        this.Mw[2] = (byte) ((int) ((j >> 40) & 255));
        this.Mw[3] = (byte) ((int) ((j >> 32) & 255));
        this.Mw[4] = (byte) ((int) ((j >> 24) & 255));
        this.Mw[5] = (byte) ((int) ((j >> 16) & 255));
        this.Mw[6] = (byte) ((int) ((j >> 8) & 255));
        this.Mw[7] = (byte) ((int) (255 & j));
        this.lv.e(this.Mw, 0, 8);
    }

    public void a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.lv.e(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void a(a aVar) {
        d(aVar.gG);
        a(aVar.b);
    }

    public void a(d dVar) {
        d(dVar.gG);
        d(dVar.b);
        a(dVar.c);
    }

    public void a(e eVar) {
        d(eVar.b);
        a(eVar.An);
    }

    public void a(f fVar) {
    }

    public void a(g gVar) {
        d(gVar.gG);
        a(gVar.b);
    }

    public void a(short s) {
        this.Mv[0] = (byte) ((s >> 8) & 255);
        this.Mv[1] = (byte) (s & 255);
        this.lv.e(this.Mv, 0, 2);
    }

    public void a(boolean z) {
        d(z ? (byte) 1 : (byte) 0);
    }

    public void ak(int i) {
        this.c = i;
        this.d = true;
    }

    public void am() {
    }

    public void ao() {
    }

    public String b(int i) {
        try {
            Y(i);
            byte[] bArr = new byte[i];
            this.lv.j(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void b() {
    }

    public int bA() {
        int i = 0;
        byte[] bArr = this.My;
        if (this.lv.c() >= 4) {
            bArr = this.lv.ag();
            i = this.lv.ah();
            this.lv.a(4);
        } else {
            a(this.My, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8));
    }

    public long bB() {
        int i = 0;
        byte[] bArr = this.Mz;
        if (this.lv.c() >= 8) {
            bArr = this.lv.ag();
            i = this.lv.ah();
            this.lv.a(8);
        } else {
            a(this.Mz, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((((((((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48)) | (((long) (bArr[i + 2] & 255)) << 40)) | (((long) (bArr[i + 3] & 255)) << 32)) | (((long) (bArr[i + 4] & 255)) << 24)) | (((long) (bArr[i + 5] & 255)) << 16)) | (((long) (bArr[i + 6] & 255)) << 8));
    }

    public double bC() {
        return Double.longBitsToDouble(bB());
    }

    public String bD() {
        int bA = bA();
        if (this.lv.c() < bA) {
            return b(bA);
        }
        try {
            String str = new String(this.lv.ag(), this.lv.ah(), bA, "UTF-8");
            this.lv.a(bA);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer bE() {
        int bA = bA();
        Y(bA);
        if (this.lv.c() >= bA) {
            ByteBuffer wrap = ByteBuffer.wrap(this.lv.ag(), this.lv.ah(), bA);
            this.lv.a(bA);
            return wrap;
        }
        byte[] bArr = new byte[bA];
        this.lv.j(bArr, 0, bA);
        return ByteBuffer.wrap(bArr);
    }

    public f bq() {
        return Mu;
    }

    public e br() {
        byte by = by();
        return new e(ConfigConstant.WIRELESS_FILENAME, by, by == (byte) 0 ? (short) 0 : bz());
    }

    public d bs() {
        return new d(by(), by(), bA());
    }

    public g bt() {
        return new g(by(), bA());
    }

    public void bu() {
    }

    public a bv() {
        return new a(by(), bA());
    }

    public void bw() {
    }

    public boolean bx() {
        return by() == (byte) 1;
    }

    public byte by() {
        if (this.lv.c() >= 1) {
            byte b = this.lv.ag()[this.lv.ah()];
            this.lv.a(1);
            return b;
        }
        a(this.Mx, 0, 1);
        return this.Mx[0];
    }

    public short bz() {
        int i = 0;
        byte[] bArr = this.Dh;
        if (this.lv.c() >= 2) {
            bArr = this.lv.ag();
            i = this.lv.ah();
            this.lv.a(2);
        } else {
            a(this.Dh, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public void c() {
        d((byte) 0);
    }

    public void d() {
    }

    public void d(byte b) {
        this.g[0] = b;
        this.lv.e(this.g, 0, 1);
    }

    public void e() {
    }

    public void f() {
    }

    public void f(ByteBuffer byteBuffer) {
        int limit = (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset();
        a(limit);
        this.lv.e(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public void j() {
    }
}
