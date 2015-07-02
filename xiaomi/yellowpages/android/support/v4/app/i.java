package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: FragmentManager */
final class i implements Parcelable {
    public static final Creator CREATOR;
    int[] mAdded;
    d[] ua;
    e[] ub;

    public i(Parcel parcel) {
        this.ua = (d[]) parcel.createTypedArray(d.CREATOR);
        this.mAdded = parcel.createIntArray();
        this.ub = (e[]) parcel.createTypedArray(e.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.ua, i);
        parcel.writeIntArray(this.mAdded);
        parcel.writeTypedArray(this.ub, i);
    }

    static {
        CREATOR = new q();
    }
}
