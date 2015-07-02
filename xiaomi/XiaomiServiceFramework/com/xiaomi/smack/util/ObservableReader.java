package com.xiaomi.smack.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ObservableReader extends Reader {
    List listeners;
    Reader wrappedReader;

    public ObservableReader(Reader wrappedReader) {
        this.wrappedReader = null;
        this.listeners = new ArrayList();
        this.wrappedReader = wrappedReader;
    }

    public int read(char[] cbuf, int off, int len) throws IOException {
        int count = this.wrappedReader.read(cbuf, off, len);
        if (count > 0) {
            ReaderListener[] readerListeners;
            String str = new String(cbuf, off, count);
            synchronized (this.listeners) {
                readerListeners = new ReaderListener[this.listeners.size()];
                this.listeners.toArray(readerListeners);
            }
            for (ReaderListener read : readerListeners) {
                read.read(str);
            }
        }
        return count;
    }

    public void close() throws IOException {
        this.wrappedReader.close();
    }

    public int read() throws IOException {
        return this.wrappedReader.read();
    }

    public int read(char[] cbuf) throws IOException {
        return this.wrappedReader.read(cbuf);
    }

    public long skip(long n) throws IOException {
        return this.wrappedReader.skip(n);
    }

    public boolean ready() throws IOException {
        return this.wrappedReader.ready();
    }

    public boolean markSupported() {
        return this.wrappedReader.markSupported();
    }

    public void mark(int readAheadLimit) throws IOException {
        this.wrappedReader.mark(readAheadLimit);
    }

    public void reset() throws IOException {
        this.wrappedReader.reset();
    }

    public void addReaderListener(ReaderListener readerListener) {
        if (readerListener != null) {
            synchronized (this.listeners) {
                if (!this.listeners.contains(readerListener)) {
                    this.listeners.add(readerListener);
                }
            }
        }
    }

    public void removeReaderListener(ReaderListener readerListener) {
        synchronized (this.listeners) {
            this.listeners.remove(readerListener);
        }
    }
}
