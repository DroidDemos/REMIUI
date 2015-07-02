package org.apache.thrift.transport;

import java.io.UnsupportedEncodingException;
import org.apache.thrift.TByteArrayOutputStream;

public class TMemoryBuffer extends TTransport {
    private TByteArrayOutputStream arr_;
    private int pos_;

    public TMemoryBuffer(int size) {
        this.arr_ = new TByteArrayOutputStream(size);
    }

    public boolean isOpen() {
        return true;
    }

    public void open() {
    }

    public void close() {
    }

    public int read(byte[] buf, int off, int len) {
        int amtToRead;
        byte[] src = this.arr_.get();
        if (len > this.arr_.len() - this.pos_) {
            amtToRead = this.arr_.len() - this.pos_;
        } else {
            amtToRead = len;
        }
        if (amtToRead > 0) {
            System.arraycopy(src, this.pos_, buf, off, amtToRead);
            this.pos_ += amtToRead;
        }
        return amtToRead;
    }

    public void write(byte[] buf, int off, int len) {
        this.arr_.write(buf, off, len);
    }

    public String toString(String enc) throws UnsupportedEncodingException {
        return this.arr_.toString(enc);
    }

    public String inspect() {
        String buf = "";
        byte[] bytes = this.arr_.toByteArray();
        int i = 0;
        while (i < bytes.length) {
            buf = buf + (this.pos_ == i ? "==>" : "") + Integer.toHexString(bytes[i] & 255) + " ";
            i++;
        }
        return buf;
    }

    public int length() {
        return this.arr_.size();
    }

    public byte[] getArray() {
        return this.arr_.get();
    }
}
