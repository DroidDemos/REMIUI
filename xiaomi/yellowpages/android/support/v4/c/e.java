package android.support.v4.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ParcelableCompat */
class e implements Creator {
    final d gA;

    public e(d dVar) {
        this.gA = dVar;
    }

    public Object createFromParcel(Parcel parcel) {
        return this.gA.createFromParcel(parcel, null);
    }

    public Object[] newArray(int i) {
        return this.gA.newArray(i);
    }
}
