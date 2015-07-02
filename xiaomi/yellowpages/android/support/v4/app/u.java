package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: BackStackRecord */
final class u implements Creator {
    u() {
    }

    public /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return c(parcel);
    }

    public /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return ab(i);
    }

    public e c(Parcel parcel) {
        return new e(parcel);
    }

    public e[] ab(int i) {
        return new e[i];
    }
}
