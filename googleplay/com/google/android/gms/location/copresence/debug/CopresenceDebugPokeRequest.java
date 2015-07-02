package com.google.android.gms.location.copresence.debug;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.oh;
import com.google.android.gms.internal.oh.a;

public class CopresenceDebugPokeRequest implements SafeParcelable {
    public static int COMMAND_CLEAR_CACHE;
    public static int COMMAND_CLEAR_DIRECTIVES;
    public static final a CREATOR;
    private int auD;
    private byte[] auE;
    private final int mVersionCode;
    public final oh messageListener;

    static {
        CREATOR = new a();
        COMMAND_CLEAR_CACHE = 2;
        COMMAND_CLEAR_DIRECTIVES = 32;
    }

    CopresenceDebugPokeRequest(int versionCode, int command, byte[] protoData, IBinder messageListenerAsBinder) {
        this(versionCode, command, protoData, messageListenerAsBinder == null ? null : a.cm(messageListenerAsBinder));
    }

    private CopresenceDebugPokeRequest(int versionCode, int command, byte[] protoData, oh messageListener) {
        this.mVersionCode = versionCode;
        this.auD = command;
        this.auE = protoData;
        this.messageListener = messageListener;
    }

    public int describeContents() {
        return 0;
    }

    public int getCommand() {
        return this.auD;
    }

    public byte[] getProtoData() {
        return this.auE;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    IBinder pg() {
        return this.messageListener == null ? null : this.messageListener.asBinder();
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
