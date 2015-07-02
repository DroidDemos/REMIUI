package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class m implements SafeParcelable, Time {
    public static final Creator<m> CREATOR;
    private final Integer aLv;
    private final Integer aLw;
    private final Integer aLx;
    public final int mVersionCode;

    static {
        CREATOR = new l();
    }

    m(int i, Integer num, Integer num2, Integer num3) {
        this.aLv = num;
        this.aLw = num2;
        this.aLx = num3;
        this.mVersionCode = i;
    }

    public m(Time time) {
        this(time.getHour(), time.getMinute(), time.getSecond(), false);
    }

    m(Integer num, Integer num2, Integer num3, boolean z) {
        this(1, num, num2, num3);
    }

    public static boolean a(Time time, Time time2) {
        return kl.equal(time.getHour(), time2.getHour()) && kl.equal(time.getMinute(), time2.getMinute()) && kl.equal(time.getSecond(), time2.getSecond());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Time) {
            return this == obj ? true : a(this, (Time) obj);
        } else {
            return false;
        }
    }

    public /* synthetic */ Object freeze() {
        return sj();
    }

    public Integer getHour() {
        return this.aLv;
    }

    public Integer getMinute() {
        return this.aLw;
    }

    public Integer getSecond() {
        return this.aLx;
    }

    public int hashCode() {
        return kl.hashCode(getHour(), getMinute(), getSecond());
    }

    public Time sj() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        l.a(this, out, flags);
    }
}
