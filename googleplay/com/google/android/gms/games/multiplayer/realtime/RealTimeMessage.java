package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.kn;

public final class RealTimeMessage implements Parcelable {
    public static final Creator<RealTimeMessage> CREATOR;
    private final String aqe;
    private final byte[] aqf;
    private final int aqg;

    static {
        CREATOR = new Creator<RealTimeMessage>() {
            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return eT(x0);
            }

            public RealTimeMessage eT(Parcel parcel) {
                return new RealTimeMessage(parcel);
            }

            public RealTimeMessage[] gZ(int i) {
                return new RealTimeMessage[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return gZ(x0);
            }
        };
    }

    private RealTimeMessage(Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }

    public RealTimeMessage(String senderParticipantId, byte[] messageData, int isReliable) {
        this.aqe = (String) kn.k(senderParticipantId);
        this.aqf = (byte[]) ((byte[]) kn.k(messageData)).clone();
        this.aqg = isReliable;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeString(this.aqe);
        parcel.writeByteArray(this.aqf);
        parcel.writeInt(this.aqg);
    }
}
