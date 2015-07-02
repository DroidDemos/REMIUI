package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class ActivityRecognitionResultCreator implements Creator<ActivityRecognitionResult> {
    static void a(ActivityRecognitionResult activityRecognitionResult, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, activityRecognitionResult.atg, false);
        b.c(parcel, 1000, activityRecognitionResult.getVersionCode());
        b.a(parcel, 2, activityRecognitionResult.ath);
        b.a(parcel, 3, activityRecognitionResult.ati);
        b.J(parcel, bU);
    }

    public ActivityRecognitionResult createFromParcel(Parcel parcel) {
        long j = 0;
        int bT = a.bT(parcel);
        int i = 0;
        List list = null;
        long j2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = a.c(parcel, bS, DetectedActivity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ActivityRecognitionResult(i, list, j2, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ActivityRecognitionResult[] newArray(int size) {
        return new ActivityRecognitionResult[size];
    }
}
