package com.google.android.instrumentedzip;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static void checkOffsetAndCount(int arrayLength, int offset, int count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void readFully(InputStream in, byte[] dst, int offset, int byteCount) throws IOException {
        if (byteCount != 0) {
            if (in == null) {
                throw new NullPointerException("in == null");
            } else if (dst == null) {
                throw new NullPointerException("dst == null");
            } else {
                checkOffsetAndCount(dst.length, offset, byteCount);
                while (byteCount > 0) {
                    int bytesRead = in.read(dst, offset, byteCount);
                    if (bytesRead < 0) {
                        throw new EOFException();
                    }
                    offset += bytesRead;
                    byteCount -= bytesRead;
                }
            }
        }
    }

    public static void skipFully(InputStream in, int byteCount) throws IOException {
        if (byteCount != 0) {
            while (byteCount > 0) {
                long bytesSkipped = in.skip((long) byteCount);
                if (bytesSkipped < 0) {
                    throw new EOFException();
                }
                byteCount = (int) (((long) byteCount) - bytesSkipped);
            }
        }
    }
}
