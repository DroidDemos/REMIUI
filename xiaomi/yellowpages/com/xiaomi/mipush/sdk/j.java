package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import com.xiaomi.e.a.a;
import com.xiaomi.e.a.d;
import com.xiaomi.e.a.e;
import com.xiaomi.e.a.g;
import com.xiaomi.e.a.h;
import com.xiaomi.e.a.i;
import com.xiaomi.e.a.k;
import com.xiaomi.e.a.m;
import com.xiaomi.e.a.n;
import com.xiaomi.e.a.p;
import com.xiaomi.e.a.r;
import com.xiaomi.e.a.t;
import com.xiaomi.e.a.u;
import com.xiaomi.f.a.c.b;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

public class j {
    private static final byte[] mP;

    static {
        mP = new byte[]{(byte) 100, (byte) 23, (byte) 84, (byte) 114, (byte) 72, (byte) 0, (byte) 4, (byte) 97, (byte) 73, (byte) 97, (byte) 2, (byte) 52, (byte) 84, (byte) 102, (byte) 18, (byte) 32};
    }

    protected static h a(Context context, TBase tBase, a aVar) {
        return a(context, tBase, aVar, !aVar.equals(a.Registration));
    }

    protected static h a(Context context, TBase tBase, a aVar, boolean z) {
        byte[] a = u.a(tBase);
        h hVar = new h();
        if (z) {
            byte[] a2 = com.xiaomi.f.a.a.a.a(k.U(context).e());
            b.b(Arrays.toString(a2));
            try {
                a = d(a2, a);
            } catch (Throwable e) {
                throw new RuntimeException("aes decode error.", e);
            }
        }
        d dVar = new d();
        dVar.a = 5;
        dVar.b = "fakeid";
        hVar.a(dVar);
        hVar.g(ByteBuffer.wrap(a));
        hVar.b(aVar);
        hVar.A(true);
        hVar.bF(context.getPackageName());
        hVar.z(z);
        hVar.bE(k.U(context).b());
        return hVar;
    }

    private static Cipher a(byte[] bArr, int i) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(mP);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(i, secretKeySpec, ivParameterSpec);
        return instance;
    }

    protected static TBase a(Context context, h hVar) {
        if (hVar.c()) {
            try {
                byte[] c = c(com.xiaomi.f.a.a.a.a(k.U(context).e()), hVar.go());
            } catch (Throwable e) {
                throw new TException("the aes decrypt failed.", e);
            }
        }
        c = hVar.go();
        TBase a = a(hVar.gn());
        if (a != null) {
            u.a(a, c);
        }
        return a;
    }

    private static TBase a(a aVar) {
        switch (o.a[aVar.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return new k();
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return new r();
            case WindowData.d /*3*/:
                return new p();
            case Base64.CRLF /*4*/:
                return new t();
            case WindowData.f /*5*/:
                return new n();
            case WindowData.g /*6*/:
                return new e();
            case WindowData.h /*7*/:
                return new g();
            case Base64.URL_SAFE /*8*/:
                return new m();
            case WindowData.j /*9*/:
                return new i();
            case WindowData.k /*10*/:
                return new g();
            default:
                return null;
        }
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        return a(bArr, 2).doFinal(bArr2);
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        return a(bArr, 1).doFinal(bArr2);
    }
}
