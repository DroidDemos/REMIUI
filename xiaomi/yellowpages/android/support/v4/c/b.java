package android.support.v4.c;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

/* compiled from: ParcelableCompatHoneycombMR2 */
class b implements ClassLoaderCreator {
    private final d gA;

    public b(d dVar) {
        this.gA = dVar;
    }

    public Object createFromParcel(Parcel parcel) {
        return this.gA.createFromParcel(parcel, null);
    }

    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return this.gA.createFromParcel(parcel, classLoader);
    }

    public Object[] newArray(int i) {
        return this.gA.newArray(i);
    }
}
