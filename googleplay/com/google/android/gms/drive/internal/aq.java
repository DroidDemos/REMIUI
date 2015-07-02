package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.wallet.instrumentmanager.R;

public class aq implements Creator<OnEventResponse> {
    static void a(OnEventResponse onEventResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onEventResponse.mVersionCode);
        b.c(parcel, 2, onEventResponse.YZ);
        b.a(parcel, 3, onEventResponse.aaX, i, false);
        b.a(parcel, 5, onEventResponse.aaY, i, false);
        b.J(parcel, bU);
    }

    public OnEventResponse cK(Parcel parcel) {
        CompletionEvent completionEvent = null;
        int i = 0;
        int bT = a.bT(parcel);
        ChangeEvent changeEvent = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            ChangeEvent changeEvent2;
            int i3;
            CompletionEvent completionEvent2;
            int bS = a.bS(parcel);
            CompletionEvent completionEvent3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    completionEvent3 = completionEvent;
                    changeEvent2 = changeEvent;
                    i3 = i;
                    i = a.g(parcel, bS);
                    completionEvent2 = completionEvent3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = i2;
                    ChangeEvent changeEvent3 = changeEvent;
                    i3 = a.g(parcel, bS);
                    completionEvent2 = completionEvent;
                    changeEvent2 = changeEvent3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i3 = i;
                    i = i2;
                    completionEvent3 = completionEvent;
                    changeEvent2 = (ChangeEvent) a.a(parcel, bS, ChangeEvent.CREATOR);
                    completionEvent2 = completionEvent3;
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    completionEvent2 = (CompletionEvent) a.a(parcel, bS, CompletionEvent.CREATOR);
                    changeEvent2 = changeEvent;
                    i3 = i;
                    i = i2;
                    break;
                default:
                    a.b(parcel, bS);
                    completionEvent2 = completionEvent;
                    changeEvent2 = changeEvent;
                    i3 = i;
                    i = i2;
                    break;
            }
            i2 = i;
            i = i3;
            changeEvent = changeEvent2;
            completionEvent = completionEvent2;
        }
        if (parcel.dataPosition() == bT) {
            return new OnEventResponse(i2, i, changeEvent, completionEvent);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cK(x0);
    }

    public OnEventResponse[] es(int i) {
        return new OnEventResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return es(x0);
    }
}
