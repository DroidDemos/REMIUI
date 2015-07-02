package org.apache.thrift;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;

public class TSerializer {
    private final ByteArrayOutputStream baos_;
    private TProtocol protocol_;
    private final TIOStreamTransport transport_;

    public TSerializer() {
        this(new Factory());
    }

    public TSerializer(TProtocolFactory protocolFactory) {
        this.baos_ = new ByteArrayOutputStream();
        this.transport_ = new TIOStreamTransport(this.baos_);
        this.protocol_ = protocolFactory.getProtocol(this.transport_);
    }

    public byte[] serialize(TBase base) throws TException {
        this.baos_.reset();
        base.write(this.protocol_);
        return this.baos_.toByteArray();
    }

    public String toString(TBase base, String charset) throws TException {
        try {
            return new String(serialize(base), charset);
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT ENCODING: " + charset);
        }
    }

    public String toString(TBase base) throws TException {
        return new String(serialize(base));
    }
}
