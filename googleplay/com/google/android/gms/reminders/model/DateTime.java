package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface DateTime extends Parcelable, Freezable<DateTime> {

    public static class Builder {
        private Integer aKM;
        private Integer aKN;
        private Integer aKO;
        private Time aKP;
        private Integer aKQ;
        private Long aKR;

        public DateTime build() {
            return new b(this.aKM, this.aKN, this.aKO, this.aKP, this.aKQ, this.aKR, true);
        }

        public Builder setAbsoluteTimeMs(Long absoluteTimeMs) {
            this.aKR = absoluteTimeMs;
            return this;
        }

        public Builder setDay(Integer day) {
            this.aKO = day;
            return this;
        }

        public Builder setMonth(Integer month) {
            this.aKN = month;
            return this;
        }

        public Builder setPeriod(Integer period) {
            this.aKQ = period;
            return this;
        }

        public Builder setTime(Time time) {
            if (time != null) {
                this.aKP = (Time) time.freeze();
            }
            return this;
        }

        public Builder setYear(Integer year) {
            this.aKM = year;
            return this;
        }
    }

    Long getAbsoluteTimeMs();

    Integer getDay();

    Integer getMonth();

    Integer getPeriod();

    Time getTime();

    Integer getYear();
}
