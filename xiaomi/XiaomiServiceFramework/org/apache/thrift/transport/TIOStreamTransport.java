package org.apache.thrift.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TIOStreamTransport extends TTransport {
    protected InputStream inputStream_;
    protected OutputStream outputStream_;

    protected TIOStreamTransport() {
        this.inputStream_ = null;
        this.outputStream_ = null;
    }

    public TIOStreamTransport(InputStream is) {
        this.inputStream_ = null;
        this.outputStream_ = null;
        this.inputStream_ = is;
    }

    public TIOStreamTransport(OutputStream os) {
        this.inputStream_ = null;
        this.outputStream_ = null;
        this.outputStream_ = os;
    }

    public TIOStreamTransport(InputStream is, OutputStream os) {
        this.inputStream_ = null;
        this.outputStream_ = null;
        this.inputStream_ = is;
        this.outputStream_ = os;
    }

    public boolean isOpen() {
        return true;
    }

    public void open() throws TTransportException {
    }

    public void close() {
        if (this.inputStream_ != null) {
            try {
                this.inputStream_.close();
            } catch (IOException e) {
            }
            this.inputStream_ = null;
        }
        if (this.outputStream_ != null) {
            try {
                this.outputStream_.close();
            } catch (IOException e2) {
            }
            this.outputStream_ = null;
        }
    }

    public int read(byte[] buf, int off, int len) throws TTransportException {
        if (this.inputStream_ == null) {
            throw new TTransportException(1, "Cannot read from null inputStream");
        }
        try {
            int bytesRead = this.inputStream_.read(buf, off, len);
            if (bytesRead >= 0) {
                return bytesRead;
            }
            throw new TTransportException(4);
        } catch (Throwable iox) {
            throw new TTransportException(0, iox);
        }
    }

    public void write(byte[] buf, int off, int len) throws TTransportException {
        if (this.outputStream_ == null) {
            throw new TTransportException(1, "Cannot write to null outputStream");
        }
        try {
            this.outputStream_.write(buf, off, len);
        } catch (Throwable iox) {
            throw new TTransportException(0, iox);
        }
    }

    public void flush() throws TTransportException {
        if (this.outputStream_ == null) {
            throw new TTransportException(1, "Cannot flush null outputStream");
        }
        try {
            this.outputStream_.flush();
        } catch (Throwable iox) {
            throw new TTransportException(0, iox);
        }
    }
}
