package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface TaskList extends Parcelable, Freezable<TaskList> {

    public static class Builder {
        private Integer aLu;

        public TaskList build() {
            return new k(this.aLu, true);
        }

        public Builder setSystemListId(Integer systemListId) {
            this.aLu = systemListId;
            return this;
        }
    }

    Integer getSystemListId();
}
