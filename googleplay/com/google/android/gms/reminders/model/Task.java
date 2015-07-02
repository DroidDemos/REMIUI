package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Task extends Parcelable, Freezable<Task> {
    Boolean getArchived();

    Long getArchivedTimeMs();

    Long getCreatedTimeMillis();

    Boolean getDeleted();

    DateTime getDueDate();

    DateTime getEventDate();

    Location getLocation();

    Long getLocationSnoozedUntilMs();

    Boolean getPinned();

    Boolean getSnoozed();

    Long getSnoozedTimeMillis();

    TaskId getTaskId();

    TaskList getTaskList();

    String getTitle();
}
