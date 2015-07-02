package com.google.android.instrumentedzip;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipException;

public class ZipEntry {
    long compressedSize;
    int compressionMethod;
    long localHeaderRelOffset;
    String name;
    int nameLength;
    long size;
    public int verificationErrors;

    public String getName() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }

    public String toString() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    ZipEntry(byte[] cdeHdrBuf, InputStream cdStream) throws IOException {
        this.compressedSize = -1;
        this.size = -1;
        this.compressionMethod = -1;
        this.nameLength = -1;
        this.localHeaderRelOffset = -1;
        Utils.readFully(cdStream, cdeHdrBuf, 0, cdeHdrBuf.length);
        HeapBufferIterator it = new HeapBufferIterator(cdeHdrBuf, 0);
        int sig = it.readInt();
        if (((long) sig) != 33639248) {
            ZipFile.throwZipException("Central Directory Entry", sig);
        }
        it.seek(8);
        int gpbf = it.readShort() & 65535;
        if ((gpbf & 1) != 0) {
            throw new ZipException("Invalid General Purpose Bit Flag: " + gpbf);
        }
        this.compressionMethod = it.readShort() & 65535;
        it.readShort();
        it.readShort();
        it.readInt();
        this.compressedSize = ((long) it.readInt()) & 4294967295L;
        this.size = ((long) it.readInt()) & 4294967295L;
        this.nameLength = it.readShort() & 65535;
        int extraLength = it.readShort() & 65535;
        int commentByteCount = it.readShort() & 65535;
        if (extraLength >= 32768) {
            this.verificationErrors |= 1;
        }
        if (commentByteCount >= 32768) {
            this.verificationErrors |= 2;
        }
        it.seek(42);
        this.localHeaderRelOffset = ((long) it.readInt()) & 4294967295L;
        byte[] nameBytes = new byte[this.nameLength];
        Utils.readFully(cdStream, nameBytes, 0, nameBytes.length);
        if (containsNulByte(nameBytes)) {
            this.verificationErrors |= 16;
        }
        try {
            this.name = new String(nameBytes, 0, nameBytes.length, "UTF-8");
            if (extraLength > 0) {
                Utils.skipFully(cdStream, extraLength);
            }
            if (commentByteCount > 0) {
                Utils.skipFully(cdStream, commentByteCount);
            }
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static boolean containsNulByte(byte[] bytes) {
        for (byte b : bytes) {
            if (b == (byte) 0) {
                return true;
            }
        }
        return false;
    }
}
