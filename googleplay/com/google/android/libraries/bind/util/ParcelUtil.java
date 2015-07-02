package com.google.android.libraries.bind.util;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ParcelUtil {
    public static void writeObjectToParcel(Object object, Parcel out, int flags) {
        boolean isParcelable = object instanceof Parcelable;
        out.writeByte((byte) (isParcelable ? 1 : 0));
        if (isParcelable) {
            out.writeParcelable((Parcelable) object, flags);
        } else {
            out.writeSerializable((Serializable) object);
        }
    }

    public static Object readObjectFromParcel(Parcel in, ClassLoader loader) {
        boolean isParcelable = true;
        if (in.readByte() != (byte) 1) {
            isParcelable = false;
        }
        if (isParcelable) {
            return in.readParcelable(loader);
        }
        return in.readSerializable();
    }
}
