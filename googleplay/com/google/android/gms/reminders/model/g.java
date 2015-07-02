package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class g implements SafeParcelable, Task {
    public static final Creator<g> CREATOR;
    private final String Yv;
    private final Long aLb;
    private final Long aLc;
    private final Boolean aLd;
    private final Boolean aLe;
    private final Boolean aLf;
    private final Boolean aLg;
    private final Long aLh;
    private final Long aLl;
    private final i aLm;
    private final k aLn;
    private final b aLo;
    private final b aLp;
    private final d aLq;
    public final int mVersionCode;

    static {
        CREATOR = new f();
    }

    g(int i, i iVar, k kVar, String str, Long l, Long l2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Long l3, b bVar, b bVar2, d dVar, Long l4) {
        this.aLm = iVar;
        this.aLn = kVar;
        this.Yv = str;
        this.aLb = l;
        this.aLc = l2;
        this.aLd = Boolean.valueOf(bool == null ? false : bool.booleanValue());
        this.aLe = Boolean.valueOf(bool2 == null ? false : bool2.booleanValue());
        this.aLf = Boolean.valueOf(bool3 == null ? false : bool3.booleanValue());
        this.aLg = Boolean.valueOf(bool4 == null ? false : bool4.booleanValue());
        this.aLh = l3;
        this.aLo = bVar;
        this.aLp = bVar2;
        this.aLq = dVar;
        this.aLl = l4;
        this.mVersionCode = i;
    }

    public g(Task task) {
        this(task.getTaskId(), task.getTaskList(), task.getTitle(), task.getCreatedTimeMillis(), task.getArchivedTimeMs(), task.getArchived(), task.getDeleted(), task.getPinned(), task.getSnoozed(), task.getSnoozedTimeMillis(), task.getDueDate(), task.getEventDate(), task.getLocation(), task.getLocationSnoozedUntilMs(), false);
    }

    g(TaskId taskId, TaskList taskList, String str, Long l, Long l2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Long l3, DateTime dateTime, DateTime dateTime2, Location location, Long l4, boolean z) {
        this.mVersionCode = 1;
        this.Yv = str;
        this.aLb = l;
        this.aLc = l2;
        this.aLd = Boolean.valueOf(bool == null ? false : bool.booleanValue());
        this.aLe = Boolean.valueOf(bool2 == null ? false : bool2.booleanValue());
        this.aLf = Boolean.valueOf(bool3 == null ? false : bool3.booleanValue());
        this.aLg = Boolean.valueOf(bool4 == null ? false : bool4.booleanValue());
        this.aLh = l3;
        this.aLl = l4;
        if (z) {
            this.aLm = (i) taskId;
            this.aLn = (k) taskList;
            this.aLo = (b) dateTime;
            this.aLp = (b) dateTime2;
            this.aLq = (d) location;
            return;
        }
        this.aLm = taskId == null ? null : new i(taskId);
        this.aLn = taskList == null ? null : new k(taskList);
        this.aLo = dateTime == null ? null : new b(dateTime);
        this.aLp = dateTime2 == null ? null : new b(dateTime2);
        this.aLq = location == null ? null : new d(location);
    }

    public static boolean a(Task task, Task task2) {
        return kl.equal(task.getTaskId(), task2.getTaskId()) && kl.equal(task.getTaskList(), task2.getTaskList()) && kl.equal(task.getTitle(), task2.getTitle()) && kl.equal(task.getCreatedTimeMillis(), task2.getCreatedTimeMillis()) && kl.equal(task.getArchivedTimeMs(), task2.getArchivedTimeMs()) && kl.equal(task.getArchived(), task2.getArchived()) && kl.equal(task.getDeleted(), task2.getDeleted()) && kl.equal(task.getPinned(), task2.getPinned()) && kl.equal(task.getSnoozed(), task2.getSnoozed()) && kl.equal(task.getSnoozedTimeMillis(), task2.getSnoozedTimeMillis()) && kl.equal(task.getDueDate(), task2.getDueDate()) && kl.equal(task.getEventDate(), task2.getEventDate()) && kl.equal(task.getLocation(), task2.getLocation()) && kl.equal(task.getLocationSnoozedUntilMs(), task2.getLocationSnoozedUntilMs());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return this == obj ? true : a(this, (Task) obj);
        } else {
            return false;
        }
    }

    public /* synthetic */ Object freeze() {
        return se();
    }

    public Boolean getArchived() {
        return this.aLd;
    }

    public Long getArchivedTimeMs() {
        return this.aLc;
    }

    public Long getCreatedTimeMillis() {
        return this.aLb;
    }

    public Boolean getDeleted() {
        return this.aLe;
    }

    public DateTime getDueDate() {
        return this.aLo;
    }

    public DateTime getEventDate() {
        return this.aLp;
    }

    public Location getLocation() {
        return this.aLq;
    }

    public Long getLocationSnoozedUntilMs() {
        return this.aLl;
    }

    public Boolean getPinned() {
        return this.aLf;
    }

    public Boolean getSnoozed() {
        return this.aLg;
    }

    public Long getSnoozedTimeMillis() {
        return this.aLh;
    }

    public TaskId getTaskId() {
        return this.aLm;
    }

    public TaskList getTaskList() {
        return this.aLn;
    }

    public String getTitle() {
        return this.Yv;
    }

    public int hashCode() {
        return kl.hashCode(getTaskId(), getTaskList(), getTitle(), getCreatedTimeMillis(), getArchivedTimeMs(), getArchived(), getDeleted(), getPinned(), getSnoozed(), getSnoozedTimeMillis(), getDueDate(), getEventDate(), getLocation(), getLocationSnoozedUntilMs());
    }

    public Task se() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        f.a(this, out, flags);
    }
}
