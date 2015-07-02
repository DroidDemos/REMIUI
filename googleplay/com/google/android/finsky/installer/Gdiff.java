package com.google.android.finsky.installer;

import com.google.android.wallet.instrumentmanager.R;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class Gdiff {

    public static class FileFormatException extends IOException {
        public FileFormatException(String reason) {
            super(reason);
        }
    }

    public static long patch(RandomAccessFile inputFile, InputStream patchFile, OutputStream output, long maxOutputLength) throws IOException {
        byte[] buffer = new byte[16384];
        long outputSize = 0;
        DataInputStream patchDataStream = new DataInputStream(new BufferedInputStream(patchFile, 256));
        if (patchDataStream.readInt() != -771763713) {
            throw new FileFormatException("Unexpected magic=" + String.format("%x", new Object[]{Integer.valueOf(magic)}));
        }
        int version = patchDataStream.read();
        if (version != 4) {
            throw new FileFormatException("Unexpected version=" + version);
        }
        do {
            int copyLength;
            int command = patchDataStream.read();
            long copyOffset;
            switch (command) {
                case -1:
                    throw new IOException("Patch file overrun");
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    return outputSize;
                case 247:
                    copyLength = patchDataStream.readUnsignedShort();
                    copyFromPatch(buffer, patchDataStream, output, copyLength);
                    break;
                case 248:
                    copyLength = patchDataStream.readInt();
                    copyFromPatch(buffer, patchDataStream, output, copyLength);
                    break;
                case 249:
                    copyOffset = (long) patchDataStream.readUnsignedShort();
                    copyLength = patchDataStream.read();
                    if (copyLength != -1) {
                        copyFromOriginal(buffer, inputFile, output, copyOffset, copyLength);
                        break;
                    }
                    throw new IOException("Unexpected end of patch");
                case 250:
                    copyOffset = (long) patchDataStream.readUnsignedShort();
                    copyLength = patchDataStream.readUnsignedShort();
                    copyFromOriginal(buffer, inputFile, output, copyOffset, copyLength);
                    break;
                case 251:
                    copyOffset = (long) patchDataStream.readUnsignedShort();
                    copyLength = patchDataStream.readInt();
                    copyFromOriginal(buffer, inputFile, output, copyOffset, copyLength);
                    break;
                case 252:
                    copyOffset = (long) patchDataStream.readInt();
                    copyLength = patchDataStream.read();
                    if (copyLength != -1) {
                        copyFromOriginal(buffer, inputFile, output, copyOffset, copyLength);
                        break;
                    }
                    throw new IOException("Unexpected end of patch");
                case 253:
                    copyOffset = (long) patchDataStream.readInt();
                    copyLength = patchDataStream.readUnsignedShort();
                    copyFromOriginal(buffer, inputFile, output, copyOffset, copyLength);
                    break;
                case 254:
                    copyOffset = (long) patchDataStream.readInt();
                    copyLength = patchDataStream.readInt();
                    copyFromOriginal(buffer, inputFile, output, copyOffset, copyLength);
                    break;
                case 255:
                    copyOffset = patchDataStream.readLong();
                    copyLength = patchDataStream.readInt();
                    copyFromOriginal(buffer, inputFile, output, copyOffset, copyLength);
                    break;
                default:
                    copyLength = command;
                    copyFromPatch(buffer, patchDataStream, output, copyLength);
                    break;
            }
            outputSize += (long) copyLength;
        } while (outputSize <= maxOutputLength);
        throw new IOException("Output length overrun");
    }

    private static void copyFromPatch(byte[] buffer, DataInputStream patchDataStream, OutputStream output, int copyLength) throws IOException {
        if (copyLength < 0) {
            throw new IOException("copyLength negative");
        }
        while (copyLength > 0) {
            int spanLength;
            if (copyLength < 16384) {
                spanLength = copyLength;
            } else {
                spanLength = 16384;
            }
            try {
                patchDataStream.readFully(buffer, 0, spanLength);
                output.write(buffer, 0, spanLength);
                copyLength -= spanLength;
            } catch (EOFException e) {
                throw new IOException("patch underrun");
            }
        }
    }

    private static void copyFromOriginal(byte[] buffer, RandomAccessFile inputFile, OutputStream output, long inputOffset, int copyLength) throws IOException {
        if (copyLength < 0) {
            throw new IOException("copyLength negative");
        } else if (inputOffset < 0) {
            throw new IOException("inputOffset negative");
        } else {
            try {
                inputFile.seek(inputOffset);
                while (copyLength > 0) {
                    int spanLength;
                    if (copyLength < 16384) {
                        spanLength = copyLength;
                    } else {
                        spanLength = 16384;
                    }
                    inputFile.readFully(buffer, 0, spanLength);
                    output.write(buffer, 0, spanLength);
                    copyLength -= spanLength;
                }
            } catch (EOFException e) {
                throw new IOException("patch underrun");
            }
        }
    }
}
