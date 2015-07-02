package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Time extends Parcelable, Freezable<Time> {

    public static class Builder {
        private Integer aLv;
        private Integer aLw;
        private Integer aLx;

        public Time build() {
            return new m(this.aLv, this.aLw, this.aLx, true);
        }

        public Builder setHour(Integer hour) {
            this.aLv = hour;
            return this;
        }

        public Builder setMinute(Integer minute) {
            this.aLw = minute;
            return this;
        }

        public Builder setSecond(Integer second) {
            this.aLx = second;
            return this;
        }
    }

    Integer getHour();

    Integer getMinute();

    Integer getSecond();
}
