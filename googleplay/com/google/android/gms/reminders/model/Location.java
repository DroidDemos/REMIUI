package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Location extends Parcelable, Freezable<Location> {

    public static class Builder {
        private Double aKT;
        private Double aKU;
        private Integer aKV;
        private Integer aKW;
        private String aKX;
        private String mName;

        public Location build() {
            return new d(this.aKT, this.aKU, this.mName, this.aKV, this.aKW, this.aKX, true);
        }

        public Builder setDisplayAddress(String displayAddress) {
            this.aKX = displayAddress;
            return this;
        }

        public Builder setLat(Double lat) {
            this.aKT = lat;
            return this;
        }

        public Builder setLng(Double lng) {
            this.aKU = lng;
            return this;
        }

        public Builder setLocationType(Integer locationType) {
            this.aKW = locationType;
            return this;
        }

        public Builder setName(String name) {
            this.mName = name;
            return this;
        }

        public Builder setRadiusMeters(Integer radiusMeters) {
            this.aKV = radiusMeters;
            return this;
        }
    }

    String getDisplayAddress();

    Double getLat();

    Double getLng();

    Integer getLocationType();

    String getName();

    Integer getRadiusMeters();
}
