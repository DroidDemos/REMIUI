package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileTeleporter implements SafeParcelable {
    public static final Creator<FileTeleporter> CREATOR;
    ParcelFileDescriptor TQ;
    private File TT;
    final String afU;
    final String afV;
    private byte[] afW;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    FileTeleporter(int versionCode, ParcelFileDescriptor parcelFileDescriptor, String mimeType, String fileName) {
        this.mVersionCode = versionCode;
        this.TQ = parcelFileDescriptor;
        this.afU = mimeType;
        this.afV = fileName;
    }

    private void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (Throwable e) {
            Log.w("FileTeleporter", "Could not close stream", e);
        }
    }

    private FileOutputStream ip() {
        if (this.TT == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File createTempFile = File.createTempFile("teleporter", ".tmp", this.TT);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                this.TQ = ParcelFileDescriptor.open(createTempFile, 268435456);
                createTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (Throwable e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (this.TQ == null) {
            Closeable dataOutputStream = new DataOutputStream(ip());
            try {
                dataOutputStream.writeInt(this.afW.length);
                dataOutputStream.writeUTF(this.afU);
                dataOutputStream.writeUTF(this.afV);
                dataOutputStream.write(this.afW);
                a(dataOutputStream);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                a(dataOutputStream);
            }
        }
        c.a(this, dest, flags);
    }
}
