package com.xiaomi.smack.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ObservableWriter extends Writer {
    List listeners;
    Writer wrappedWriter;

    public ObservableWriter(Writer wrappedWriter) {
        this.wrappedWriter = null;
        this.listeners = new ArrayList();
        this.wrappedWriter = wrappedWriter;
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        this.wrappedWriter.write(cbuf, off, len);
        notifyListeners(new String(cbuf, off, len));
    }

    public void flush() throws IOException {
        this.wrappedWriter.flush();
    }

    public void close() throws IOException {
        this.wrappedWriter.close();
    }

    public void write(int c) throws IOException {
        this.wrappedWriter.write(c);
    }

    public void write(char[] cbuf) throws IOException {
        this.wrappedWriter.write(cbuf);
        notifyListeners(new String(cbuf));
    }

    public void write(String str) throws IOException {
        this.wrappedWriter.write(str);
        notifyListeners(str);
    }

    public void write(String str, int off, int len) throws IOException {
        this.wrappedWriter.write(str, off, len);
        notifyListeners(str.substring(off, off + len));
    }

    private void notifyListeners(String str) {
        synchronized (this.listeners) {
            WriterListener[] writerListeners = new WriterListener[this.listeners.size()];
            this.listeners.toArray(writerListeners);
        }
        for (WriterListener write : writerListeners) {
            write.write(str);
        }
    }

    public void addWriterListener(WriterListener writerListener) {
        if (writerListener != null) {
            synchronized (this.listeners) {
                if (!this.listeners.contains(writerListener)) {
                    this.listeners.add(writerListener);
                }
            }
        }
    }

    public void removeWriterListener(WriterListener writerListener) {
        synchronized (this.listeners) {
            this.listeners.remove(writerListener);
        }
    }
}
