package com.google.android.gms.reminders;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class LoadRemindersOptions implements SafeParcelable {
    public static final Creator<LoadRemindersOptions> CREATOR;
    private final List<Long> aKp;
    private final List<String> aKq;
    private final List<Integer> aKr;
    public final int mVersionCode;

    static {
        CREATOR = new a();
    }

    LoadRemindersOptions(int versionCode, List<Long> serverAssignedIds, List<String> clientAssignedIds, List<Integer> taskListIds) {
        this.mVersionCode = versionCode;
        this.aKp = serverAssignedIds;
        this.aKq = clientAssignedIds;
        this.aKr = taskListIds;
    }

    public int describeContents() {
        return 0;
    }

    public List<String> getClientAssignedIds() {
        return this.aKq;
    }

    public List<Long> getServerAssignedIds() {
        return this.aKp;
    }

    public List<Integer> getTaskListIds() {
        return this.aKr;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
