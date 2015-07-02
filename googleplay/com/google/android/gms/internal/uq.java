package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.DateTime.Builder;
import com.google.android.gms.reminders.model.Location;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.android.gms.reminders.model.TaskList;
import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.g;

public class uq extends uk implements Task {
    private DateTime dk(String str) {
        Time dl = dl(str);
        return (bb(new StringBuilder().append(str).append("year").toString()) && bb(str + "month") && bb(str + "day") && dl == null && bb(str + "period") && bb(str + "absolute_time_ms")) ? null : new Builder().setYear(getAsInteger(str + "year")).setMonth(getAsInteger(str + "month")).setDay(getAsInteger(str + "day")).setTime(dl).setPeriod(getAsInteger(str + "period")).setAbsoluteTimeMs(getAsLong(str + "absolute_time_ms")).build();
    }

    private Time dl(String str) {
        return (bb(new StringBuilder().append(str).append("hour").toString()) && bb(str + "minute") && bb(str + "second")) ? null : new Time.Builder().setHour(Integer.valueOf(getInteger(str + "hour"))).setMinute(Integer.valueOf(getInteger(str + "minute"))).setSecond(Integer.valueOf(getInteger(str + "second"))).build();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return this == obj ? true : g.a(this, (Task) obj);
        } else {
            return false;
        }
    }

    public /* synthetic */ Object freeze() {
        return se();
    }

    public Boolean getArchived() {
        return Boolean.valueOf(getBoolean("archived"));
    }

    public Long getArchivedTimeMs() {
        return getAsLong("archived_time_ms");
    }

    public Long getCreatedTimeMillis() {
        return getAsLong("created_time_millis");
    }

    public Boolean getDeleted() {
        return Boolean.valueOf(getBoolean("deleted"));
    }

    public DateTime getDueDate() {
        return dk("due_date_");
    }

    public DateTime getEventDate() {
        return dk("event_date_");
    }

    public Location getLocation() {
        return (bb("lat") && bb("lng") && bb("name") && bb("radius_meters") && bb("location_type") && bb("display_address")) ? null : new Location.Builder().setLat(getAsDouble("lat")).setLng(getAsDouble("lng")).setName(getString("name")).setRadiusMeters(getAsInteger("radius_meters")).setLocationType(getAsInteger("location_type")).setDisplayAddress(getString("display_address")).build();
    }

    public Long getLocationSnoozedUntilMs() {
        return getAsLong("location_snoozed_until_ms");
    }

    public Boolean getPinned() {
        return Boolean.valueOf(getBoolean("pinned"));
    }

    public Boolean getSnoozed() {
        return Boolean.valueOf(getBoolean("snoozed"));
    }

    public Long getSnoozedTimeMillis() {
        return getAsLong("snoozed_time_millis");
    }

    public TaskId getTaskId() {
        return new TaskId.Builder().setServerAssignedId(getAsLong("server_assigned_id")).setClientAssignedId(getString("client_assigned_id")).setClientAssignedThreadId(getString("client_assigned_thread_id")).build();
    }

    public TaskList getTaskList() {
        return new TaskList.Builder().setSystemListId(getAsInteger("task_list")).build();
    }

    public String getTitle() {
        return getString("title");
    }

    public int hashCode() {
        return kl.hashCode(getTaskId(), getTaskList(), getTitle(), getCreatedTimeMillis(), getArchivedTimeMs(), getArchived(), getDeleted(), getPinned(), getSnoozed(), getSnoozedTimeMillis(), getDueDate(), getEventDate(), getLocation(), getLocationSnoozedUntilMs());
    }

    public Task se() {
        return new g(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        new g(this).writeToParcel(parcel, i);
    }
}
