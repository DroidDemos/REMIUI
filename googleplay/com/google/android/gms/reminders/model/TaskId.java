package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface TaskId extends Parcelable, Freezable<TaskId> {

    public static class Builder {
        private Long aLr;
        private String aLs;
        private String aLt;

        public TaskId build() {
            return new i(this.aLr, this.aLs, this.aLt, true);
        }

        public Builder setClientAssignedId(String clientAssignedId) {
            this.aLs = clientAssignedId;
            return this;
        }

        public Builder setClientAssignedThreadId(String clientAssignedThreadId) {
            this.aLt = clientAssignedThreadId;
            return this;
        }

        public Builder setServerAssignedId(Long serverAssignedId) {
            this.aLr = serverAssignedId;
            return this;
        }
    }

    String getClientAssignedId();

    String getClientAssignedThreadId();

    Long getServerAssignedId();
}
