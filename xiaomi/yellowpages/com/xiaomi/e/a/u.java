package com.xiaomi.e.a;

import com.xiaomi.f.a.c.b;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.c;
import org.apache.thrift.f;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;

public class u {
    public static void a(TBase tBase, byte[] bArr) {
        if (bArr == null) {
            throw new TException("the message byte is empty.");
        }
        new c().a(tBase, bArr);
    }

    public static byte[] a(TBase tBase) {
        byte[] bArr = null;
        if (tBase != null) {
            try {
                bArr = new f(new Factory()).a(tBase);
            } catch (Throwable e) {
                b.b("convertThriftObjectToBytes catch TException.", e);
            }
        }
        return bArr;
    }
}
