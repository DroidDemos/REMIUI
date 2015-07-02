package com.google.android.instrumentedzip;

public final class HeapBufferIterator {
    private final byte[] buffer;
    private final int offset;
    private int position;

    HeapBufferIterator(byte[] buffer, int offset) {
        this.buffer = buffer;
        this.offset = offset;
    }

    public void seek(int offset) {
        this.position = offset;
    }

    public void skip(int byteCount) {
        this.position += byteCount;
    }

    public int readInt() {
        int readOffset = this.offset + this.position;
        this.position += 4;
        int readOffset2 = readOffset + 1;
        readOffset = readOffset2 + 1;
        return ((((this.buffer[readOffset] & 255) << 0) | ((this.buffer[readOffset2] & 255) << 8)) | ((this.buffer[readOffset] & 255) << 16)) | ((this.buffer[readOffset + 1] & 255) << 24);
    }

    public short readShort() {
        int readOffset = this.offset + this.position;
        this.position += 2;
        return (short) (((this.buffer[readOffset] & 255) << 0) | ((this.buffer[readOffset + 1] & 255) << 8));
    }
}
