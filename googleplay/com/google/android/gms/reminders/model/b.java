package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class b implements SafeParcelable, DateTime {
    public static final Creator<b> CREATOR;
    private final Integer aKM;
    private final Integer aKN;
    private final Integer aKO;
    private final Integer aKQ;
    private final Long aKR;
    private final m aKS;
    public final int mVersionCode;

    static {
        CREATOR = new a();
    }

    b(int i, Integer num, Integer num2, Integer num3, m mVar, Integer num4, Long l) {
        this.aKM = num;
        this.aKN = num2;
        this.aKO = num3;
        this.aKS = mVar;
        this.aKQ = num4;
        this.aKR = l;
        this.mVersionCode = i;
    }

    public b(DateTime dateTime) {
        this(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), dateTime.getTime(), dateTime.getPeriod(), dateTime.getAbsoluteTimeMs(), false);
    }

    b(Integer num, Integer num2, Integer num3, Time time, Integer num4, Long l, boolean z) {
        this.mVersionCode = 1;
        this.aKM = num;
        this.aKN = num2;
        this.aKO = num3;
        this.aKQ = num4;
        this.aKR = l;
        if (z) {
            this.aKS = (m) time;
        } else {
            this.aKS = time == null ? null : new m(time);
        }
    }

    public static boolean a(DateTime dateTime, DateTime dateTime2) {
        return kl.equal(dateTime.getYear(), dateTime2.getYear()) && kl.equal(dateTime.getMonth(), dateTime2.getMonth()) && kl.equal(dateTime.getDay(), dateTime2.getDay()) && kl.equal(dateTime.getTime(), dateTime2.getTime()) && kl.equal(dateTime.getPeriod(), dateTime2.getPeriod()) && kl.equal(dateTime.getAbsoluteTimeMs(), dateTime2.getAbsoluteTimeMs());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DateTime) {
            return this == obj ? true : a(this, (DateTime) obj);
        } else {
            return false;
        }
    }

    public /* synthetic */ Object freeze() {
        return sf();
    }

    public Long getAbsoluteTimeMs() {
        return this.aKR;
    }

    public Integer getDay() {
        return this.aKO;
    }

    public Integer getMonth() {
        return this.aKN;
    }

    public Integer getPeriod() {
        return this.aKQ;
    }

    public Time getTime() {
        return this.aKS;
    }

    public Integer getYear() {
        return this.aKM;
    }

    public int hashCode() {
        return kl.hashCode(getYear(), getMonth(), getDay(), getTime(), getPeriod(), getAbsoluteTimeMs());
    }

    public DateTime sf() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
