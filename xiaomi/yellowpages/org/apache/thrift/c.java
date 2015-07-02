package org.apache.thrift;

import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.b;
import org.apache.thrift.transport.a;

public class c {
    private final b wb;
    private final a wc;

    public c() {
        this(new Factory());
    }

    public c(TProtocolFactory tProtocolFactory) {
        this.wc = new a();
        this.wb = tProtocolFactory.a(this.wc);
    }

    public void a(TBase tBase, byte[] bArr) {
        try {
            this.wc.f(bArr);
            tBase.a(this.wb);
        } finally {
            this.wb.bF();
        }
    }
}
