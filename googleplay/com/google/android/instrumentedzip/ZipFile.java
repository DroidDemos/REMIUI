package com.google.android.instrumentedzip;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

public class ZipFile implements Closeable {
    private final List<ZipEntry> entryList;
    private final LinkedHashMap<String, ZipEntry> entryMap;
    private RandomAccessFile raf;

    static class RAFStream extends InputStream {
        private long endOffset;
        private long offset;
        private final RandomAccessFile sharedRaf;

        public RAFStream(RandomAccessFile raf, long initialOffset) throws IOException {
            this.sharedRaf = raf;
            this.offset = initialOffset;
            this.endOffset = raf.length();
        }

        public int available() throws IOException {
            return this.offset < this.endOffset ? 1 : 0;
        }

        public int read() throws IOException {
            int value;
            synchronized (this.sharedRaf) {
                this.sharedRaf.seek(this.offset);
                value = this.sharedRaf.read();
                if (value != -1) {
                    this.offset++;
                }
            }
            return value;
        }

        public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
            int count;
            synchronized (this.sharedRaf) {
                long length = this.endOffset - this.offset;
                if (((long) byteCount) > length) {
                    byteCount = (int) length;
                }
                this.sharedRaf.seek(this.offset);
                count = this.sharedRaf.read(buffer, byteOffset, byteCount);
                if (count > 0) {
                    this.offset += (long) count;
                } else {
                    count = -1;
                }
            }
            return count;
        }

        public long skip(long byteCount) throws IOException {
            if (byteCount > this.endOffset - this.offset) {
                byteCount = this.endOffset - this.offset;
            }
            this.offset += byteCount;
            return byteCount;
        }
    }

    static class ZipInflaterInputStream extends InflaterInputStream {
        private long bytesRead;
        private final ZipEntry entry;

        public ZipInflaterInputStream(InputStream is, Inflater inf, int bsize, ZipEntry entry) {
            super(is, inf, bsize);
            this.bytesRead = 0;
            this.entry = entry;
        }

        public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
            try {
                int i = super.read(buffer, byteOffset, byteCount);
                if (i != -1) {
                    this.bytesRead += (long) i;
                } else if (this.entry.size != this.bytesRead) {
                    throw new IOException("Size mismatch on inflated file: " + this.bytesRead + " vs " + this.entry.size);
                }
                return i;
            } catch (IOException e) {
                throw new IOException("Error reading data for " + this.entry.getName() + " near offset " + this.bytesRead);
            }
        }

        public int available() throws IOException {
            return super.available() == 0 ? 0 : (int) (this.entry.getSize() - this.bytesRead);
        }
    }

    public ZipFile(File file) throws ZipException, IOException {
        this.entryMap = new LinkedHashMap();
        this.entryList = new ArrayList();
        this.raf = new RandomAccessFile(file.getPath(), "r");
        readCentralDir();
    }

    public void close() throws IOException {
        RandomAccessFile localRaf = this.raf;
        if (localRaf != null) {
            synchronized (localRaf) {
                this.raf = null;
                localRaf.close();
            }
        }
    }

    private void checkNotClosed() {
        if (this.raf == null) {
            throw new IllegalStateException("Zip file closed");
        }
    }

    public List<? extends ZipEntry> allEntries() {
        return Collections.unmodifiableList(this.entryList);
    }

    public ZipEntry getEntry(String entryName) {
        checkNotClosed();
        if (entryName == null) {
            throw new NullPointerException("entryName == null");
        }
        ZipEntry ze = (ZipEntry) this.entryMap.get(entryName);
        if (ze == null) {
            return (ZipEntry) this.entryMap.get(entryName + "/");
        }
        return ze;
    }

    public void verifyEntry(ZipEntry entry) throws IOException {
        verifyAndGetInputStream(entry, true);
    }

    public InputStream getInputStream(ZipEntry entry) throws IOException {
        return verifyAndGetInputStream(entry, false);
    }

    private InputStream verifyAndGetInputStream(ZipEntry entry, boolean onlyVerify) throws IOException {
        entry = getEntry(entry.getName());
        if (entry == null) {
            return null;
        }
        RandomAccessFile localRaf = this.raf;
        synchronized (localRaf) {
            InputStream rafStream = new RAFStream(localRaf, entry.localHeaderRelOffset);
            DataInputStream is = new DataInputStream(rafStream);
            int localMagic = Integer.reverseBytes(is.readInt());
            if (((long) localMagic) != 67324752) {
                throwZipException("Local File Header", localMagic);
            }
            is.skipBytes(2);
            int gpbf = Short.reverseBytes(is.readShort()) & 65535;
            if ((gpbf & 1) != 0) {
                throw new ZipException("Invalid General Purpose Bit Flag: " + gpbf);
            }
            is.skipBytes(18);
            int fileNameLength = Short.reverseBytes(is.readShort()) & 65535;
            int extraFieldLength = Short.reverseBytes(is.readShort()) & 65535;
            is.close();
            if (fileNameLength != entry.nameLength) {
                entry.verificationErrors |= 8;
            }
            if (extraFieldLength >= 32768) {
                entry.verificationErrors |= 4;
            }
            if (onlyVerify) {
                return null;
            }
            rafStream.skip((long) (fileNameLength + extraFieldLength));
            if (entry.compressionMethod == 0) {
                rafStream.endOffset = rafStream.offset + entry.size;
                return rafStream;
            }
            rafStream.endOffset = rafStream.offset + entry.compressedSize;
            InputStream zipInflaterInputStream = new ZipInflaterInputStream(rafStream, new Inflater(true), Math.max(1024, (int) Math.min(entry.getSize(), 4096)), entry);
            return zipInflaterInputStream;
        }
    }

    private void readCentralDir() throws IOException {
        long scanOffset = this.raf.length() - 22;
        if (scanOffset < 0) {
            throw new ZipException("File too short to be a zip file: " + this.raf.length());
        }
        this.raf.seek(0);
        if (((long) Integer.reverseBytes(this.raf.readInt())) != 67324752) {
            throw new ZipException("Not a zip archive");
        }
        long stopOffset = scanOffset - 65536;
        if (stopOffset < 0) {
            stopOffset = 0;
        }
        do {
            this.raf.seek(scanOffset);
            if (((long) Integer.reverseBytes(this.raf.readInt())) == 101010256) {
                byte[] eocd = new byte[18];
                this.raf.readFully(eocd);
                HeapBufferIterator it = new HeapBufferIterator(eocd, 0);
                int diskNumber = it.readShort() & 65535;
                int diskWithCentralDir = it.readShort() & 65535;
                int numEntries = it.readShort() & 65535;
                int totalNumEntries = it.readShort() & 65535;
                it.skip(4);
                long centralDirOffset = ((long) it.readInt()) & 4294967295L;
                if (numEntries == totalNumEntries && diskNumber == 0 && diskWithCentralDir == 0) {
                    BufferedInputStream bufferedStream = new BufferedInputStream(new RAFStream(this.raf, centralDirOffset), 4096);
                    byte[] hdrBuf = new byte[46];
                    for (int i = 0; i < numEntries; i++) {
                        ZipEntry newEntry = new ZipEntry(hdrBuf, bufferedStream);
                        if (newEntry.localHeaderRelOffset >= centralDirOffset) {
                            throw new ZipException("Local file header offset is after central directory");
                        }
                        String entryName = newEntry.getName();
                        ZipEntry oldEntry = (ZipEntry) this.entryMap.put(entryName, newEntry);
                        if (oldEntry != null) {
                            oldEntry.verificationErrors |= 32;
                            newEntry.verificationErrors |= 32;
                        }
                        this.entryList.add(newEntry);
                    }
                    return;
                }
                throw new ZipException("Spanned archives not supported");
            }
            scanOffset--;
        } while (scanOffset >= stopOffset);
        throw new ZipException("End Of Central Directory signature not found");
    }

    static void throwZipException(String msg, int magic) throws ZipException {
        throw new ZipException(msg + " signature not found; was " + String.format("%08x", new Object[]{Integer.valueOf(magic)}));
    }
}
