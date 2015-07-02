package org.apache.thrift;

import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.b;

public class f {
    private final a Ej;
    private final b Ek;
    private org.apache.thrift.protocol.b El;

    public f() {
        this(new Factory());
    }

    public f(TProtocolFactory tProtocolFactory) {
        this.Ej = new a();
        this.Ek = new b(this.Ej);
        this.El = tProtocolFactory.a(this.Ek);
    }

    public byte[] a(TBase tBase) {
        this.Ej.reset();
        tBase.b(this.El);
        return this.Ej.toByteArray();
    }
}
