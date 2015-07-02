package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class k implements SafeParcelable, TaskList {
    public static final Creator<k> CREATOR;
    private final Integer aLu;
    public final int mVersionCode;

    static {
        CREATOR = new j();
    }

    k(int i, Integer num) {
        this.aLu = num;
        this.mVersionCode = i;
    }

    public k(TaskList taskList) {
        this(taskList.getSystemListId(), false);
    }

    k(Integer num, boolean z) {
        this(1, num);
    }

    public static boolean a(TaskList taskList, TaskList taskList2) {
        return kl.equal(taskList.getSystemListId(), taskList2.getSystemListId());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            return this == obj ? true : a(this, (TaskList) obj);
        } else {
            return false;
        }
    }

    public /* synthetic */ Object freeze() {
        return si();
    }

    public Integer getSystemListId() {
        return this.aLu;
    }

    public int hashCode() {
        return kl.hashCode(getSystemListId());
    }

    public TaskList si() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        j.a(this, out, flags);
    }
}
