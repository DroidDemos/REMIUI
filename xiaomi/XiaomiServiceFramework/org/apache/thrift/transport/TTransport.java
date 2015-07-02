package org.apache.thrift.transport;

public abstract class TTransport {
    public abstract void close();

    public abstract boolean isOpen();

    public abstract void open() throws TTransportException;

    public abstract int read(byte[] bArr, int i, int i2) throws TTransportException;

    public abstract void write(byte[] bArr, int i, int i2) throws TTransportException;

    public boolean peek() {
        return isOpen();
    }

    public int readAll(byte[] buf, int off, int len) throws TTransportException {
        int got = 0;
        while (got < len) {
            int ret = read(buf, off + got, len - got);
            if (ret <= 0) {
                throw new TTransportException("Cannot read. Remote side has closed. Tried to read " + len + " bytes, but only got " + got + " bytes.");
            }
            got += ret;
        }
        return got;
    }

    public void write(byte[] buf) throws TTransportException {
        write(buf, 0, buf.length);
    }

    public void flush() throws TTransportException {
    }

    public byte[] getBuffer() {
        return null;
    }

    public int getBufferPosition() {
        return 0;
    }

    public int getBytesRemainingInBuffer() {
        return -1;
    }

    public void consumeBuffer(int len) {
    }
}
