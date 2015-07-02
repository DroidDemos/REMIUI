package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class ParcelableEvent implements SafeParcelable {
    public static final Creator<ParcelableEvent> CREATOR;
    final String ada;
    final boolean adb;
    final List<String> adc;
    final String aeN;
    final TextInsertedDetails aeO;
    final TextDeletedDetails aeP;
    final ValuesAddedDetails aeQ;
    final ValuesRemovedDetails aeR;
    final ValuesSetDetails aeS;
    final ValueChangedDetails aeT;
    final ReferenceShiftedDetails aeU;
    final ObjectChangedDetails aeV;
    final String ael;
    final int mVersionCode;
    final String vY;

    static {
        CREATOR = new b();
    }

    ParcelableEvent(int versionCode, String sessionId, String userId, List<String> compoundOperationNames, boolean isLocal, String objectId, String objectType, TextInsertedDetails textInsertedDetails, TextDeletedDetails textDeletedDetails, ValuesAddedDetails valuesAddedDetails, ValuesRemovedDetails valuesRemovedDetails, ValuesSetDetails valuesSetDetails, ValueChangedDetails valueChangedDetails, ReferenceShiftedDetails referenceShiftedDetails, ObjectChangedDetails objectChangedDetails) {
        this.mVersionCode = versionCode;
        this.vY = sessionId;
        this.ada = userId;
        this.adc = compoundOperationNames;
        this.adb = isLocal;
        this.ael = objectId;
        this.aeN = objectType;
        this.aeO = textInsertedDetails;
        this.aeP = textDeletedDetails;
        this.aeQ = valuesAddedDetails;
        this.aeR = valuesRemovedDetails;
        this.aeS = valuesSetDetails;
        this.aeT = valueChangedDetails;
        this.aeU = referenceShiftedDetails;
        this.aeV = objectChangedDetails;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
