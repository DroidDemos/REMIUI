package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class i implements SafeParcelable, TaskId {
    public static final Creator<i> CREATOR;
    private final Long aLr;
    private final String aLs;
    private final String aLt;
    public final int mVersionCode;

    static {
        CREATOR = new h();
    }

    i(int i, Long l, String str, String str2) {
        this.aLr = l;
        this.aLs = str;
        this.aLt = str2;
        this.mVersionCode = i;
    }

    public i(TaskId taskId) {
        this(taskId.getServerAssignedId(), taskId.getClientAssignedId(), taskId.getClientAssignedThreadId(), false);
    }

    i(Long l, String str, String str2, boolean z) {
        this(1, l, str, str2);
    }

    public static boolean a(TaskId taskId, TaskId taskId2) {
        return kl.equal(taskId.getServerAssignedId(), taskId2.getServerAssignedId()) && kl.equal(taskId.getClientAssignedId(), taskId2.getClientAssignedId()) && kl.equal(taskId.getClientAssignedThreadId(), taskId2.getClientAssignedThreadId());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TaskId) {
            return this == obj ? true : a(this, (TaskId) obj);
        } else {
            return false;
        }
    }

    public /* synthetic */ Object freeze() {
        return sh();
    }

    public String getClientAssignedId() {
        return this.aLs;
    }

    public String getClientAssignedThreadId() {
        return this.aLt;
    }

    public Long getServerAssignedId() {
        return this.aLr;
    }

    public int hashCode() {
        return kl.hashCode(getServerAssignedId(), getClientAssignedId(), getClientAssignedThreadId());
    }

    public TaskId sh() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        h.a(this, out, flags);
    }
}
