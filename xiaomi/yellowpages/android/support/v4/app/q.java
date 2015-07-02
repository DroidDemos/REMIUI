package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: FragmentManager */
final class q implements Creator {
    q() {
    }

    public /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return b(parcel);
    }

    public /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return U(i);
    }

    public i b(Parcel parcel) {
        return new i(parcel);
    }

    public i[] U(int i) {
        return new i[i];
    }
}
