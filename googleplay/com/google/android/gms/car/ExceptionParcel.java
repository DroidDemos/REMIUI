package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ExceptionParcel implements Parcelable {
    public static final Creator<ExceptionParcel> CREATOR;
    private final Throwable Ma;

    static {
        CREATOR = new Creator<ExceptionParcel>() {
            public ExceptionParcel bB(Parcel parcel) {
                try {
                    return new ExceptionParcel((Throwable) parcel.readSerializable());
                } catch (Throwable e) {
                    return new ExceptionParcel(e);
                }
            }

            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return bB(x0);
            }

            public ExceptionParcel[] cs(int i) {
                return new ExceptionParcel[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return cs(x0);
            }
        };
    }

    ExceptionParcel(Throwable exception) {
        this.Ma = exception;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(this.Ma);
    }
}
