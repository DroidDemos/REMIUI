package com.google.android.finsky.utils;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ParcelUtils {

    public static final class CacheVersionException extends RuntimeException {
        private final long mActualVersion;
        private final Class<? extends Parcelable> mClass;
        private final long mExpectedVersion;

        public CacheVersionException(Class<? extends Parcelable> cls, long expectedVersion, long actualVersion) {
            this.mClass = cls;
            this.mExpectedVersion = expectedVersion;
            this.mActualVersion = actualVersion;
        }

        public String getMessage() {
            return String.format("Failed parsing %s (wanted %d, got %d)", new Object[]{this.mClass.getSimpleName(), Long.valueOf(this.mExpectedVersion), Long.valueOf(this.mActualVersion)});
        }
    }

    private ParcelUtils() {
    }

    public static synchronized void writeToDisk(File cacheFile, Parcelable object) throws IOException {
        Throwable th;
        synchronized (ParcelUtils.class) {
            Utils.ensureNotOnMainThread();
            cacheFile.getParentFile().mkdirs();
            DataOutputStream dos = null;
            try {
                DataOutputStream dos2 = new DataOutputStream(new FileOutputStream(cacheFile));
                try {
                    writeObject(dos2, object);
                    Utils.safeClose(dos2);
                } catch (Throwable th2) {
                    th = th2;
                    dos = dos2;
                    Utils.safeClose(dos);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                Utils.safeClose(dos);
                throw th;
            }
        }
    }

    public static synchronized <T extends Parcelable> T readFromDisk(File cacheFile) {
        Throwable th;
        CacheVersionException e;
        Throwable t;
        synchronized (ParcelUtils.class) {
            T cachedObject = null;
            DataInputStream ois = null;
            try {
                DataInputStream ois2 = new DataInputStream(new FileInputStream(cacheFile));
                try {
                    T obj = readObject(ois2);
                    if (obj != null) {
                        cachedObject = obj;
                    }
                    try {
                        Utils.safeClose(ois2);
                        ois = ois2;
                    } catch (Throwable th2) {
                        th = th2;
                        ois = ois2;
                        throw th;
                    }
                } catch (CacheVersionException e2) {
                    e = e2;
                    ois = ois2;
                    try {
                        cacheFile.delete();
                        FinskyLog.e("Version mismatch in %s: %s", cacheFile.getName(), e.getMessage());
                        Utils.safeClose(ois);
                        return cachedObject;
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    ois = ois2;
                    Utils.safeClose(ois);
                    throw th;
                }
            } catch (CacheVersionException e3) {
                e = e3;
                cacheFile.delete();
                FinskyLog.e("Version mismatch in %s: %s", cacheFile.getName(), e.getMessage());
                Utils.safeClose(ois);
                return cachedObject;
            } catch (Throwable th5) {
                t = th5;
                cacheFile.delete();
                FinskyLog.e(t, "Unable to read %s from cache: %s", cacheFile.getName(), t.getMessage());
                Utils.safeClose(ois);
                return cachedObject;
            }
            return cachedObject;
        }
    }

    private static void writeObject(DataOutputStream out, Parcelable object) throws IOException {
        Parcel p = Parcel.obtain();
        try {
            p.setDataPosition(0);
            p.writeParcelable(object, 0);
            byte[] marshalled = p.marshall();
            out.writeInt(marshalled.length);
            out.write(marshalled);
        } finally {
            p.recycle();
        }
    }

    private static <T extends Parcelable> T readObject(DataInputStream in) throws IOException {
        Utils.ensureNotOnMainThread();
        ClassLoader classLoader = ParcelUtils.class.getClassLoader();
        byte[] buffer = new byte[in.readInt()];
        in.read(buffer);
        Parcel p = Parcel.obtain();
        p.setDataPosition(0);
        p.unmarshall(buffer, 0, buffer.length);
        p.setDataPosition(0);
        T t = null;
        try {
            t = p.readParcelable(classLoader);
            return t;
        } finally {
            p.recycle();
        }
    }
}
