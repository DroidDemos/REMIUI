package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public final class Field implements SafeParcelable {
    public static final Creator<Field> CREATOR;
    public static final Field FIELD_ACCURACY;
    public static final Field FIELD_ACTIVITY;
    public static final Field FIELD_ALTITUDE;
    public static final Field FIELD_AVERAGE;
    public static final Field FIELD_BPM;
    public static final Field FIELD_CALORIES;
    public static final Field FIELD_CONFIDENCE;
    public static final Field FIELD_DISTANCE;
    public static final Field FIELD_DURATION;
    public static final Field FIELD_HEIGHT;
    public static final Field FIELD_HIGH_LATITUDE;
    public static final Field FIELD_HIGH_LONGITUDE;
    public static final Field FIELD_LATITUDE;
    public static final Field FIELD_LONGITUDE;
    public static final Field FIELD_LOW_LATITUDE;
    public static final Field FIELD_LOW_LONGITUDE;
    public static final Field FIELD_MAX;
    public static final Field FIELD_MIN;
    public static final Field FIELD_NUM_SEGMENTS;
    public static final Field FIELD_REVOLUTIONS;
    public static final Field FIELD_RPM;
    public static final Field FIELD_SPEED;
    public static final Field FIELD_STEPS;
    public static final Field FIELD_WATTS;
    public static final Field FIELD_WEIGHT;
    public static final Field agI;
    public static final Field agJ;
    public static final Field agK;
    public static final Field agL;
    private final int agM;
    private final String mName;
    private final int mVersionCode;

    static {
        FIELD_ACTIVITY = bN("activity");
        FIELD_CONFIDENCE = bO("confidence");
        FIELD_STEPS = bN("steps");
        FIELD_DURATION = bN("duration");
        FIELD_BPM = bO("bpm");
        FIELD_LATITUDE = bO("latitude");
        FIELD_LONGITUDE = bO("longitude");
        FIELD_ACCURACY = bO("accuracy");
        FIELD_ALTITUDE = bO("altitude");
        FIELD_DISTANCE = bO("distance");
        FIELD_HEIGHT = bO("height");
        FIELD_WEIGHT = bO("weight");
        FIELD_SPEED = bO("speed");
        FIELD_RPM = bO("rpm");
        FIELD_REVOLUTIONS = bN("revolutions");
        FIELD_CALORIES = bO("calories");
        FIELD_WATTS = bO("watts");
        FIELD_NUM_SEGMENTS = bN("num_segments");
        FIELD_AVERAGE = bO("average");
        FIELD_MAX = bO("max");
        FIELD_MIN = bO("min");
        FIELD_LOW_LATITUDE = bO("low_latitude");
        FIELD_LOW_LONGITUDE = bO("low_longitude");
        FIELD_HIGH_LATITUDE = bO("high_latitude");
        FIELD_HIGH_LONGITUDE = bO("high_longitude");
        agI = bN("edge_type");
        agJ = bO("x");
        agK = bO("y");
        agL = bO("z");
        CREATOR = new i();
    }

    Field(int versionCode, String name, int format) {
        this.mVersionCode = versionCode;
        this.mName = (String) kn.k(name);
        this.agM = format;
    }

    public Field(String name, int format) {
        this(1, name, format);
    }

    private boolean a(Field field) {
        return this.mName.equals(field.mName) && this.agM == field.agM;
    }

    private static Field bN(String str) {
        return new Field(str, 1);
    }

    private static Field bO(String str) {
        return new Field(str, 2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof Field) && a((Field) that));
    }

    public int getFormat() {
        return this.agM;
    }

    public String getName() {
        return this.mName;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public String toString() {
        String str = "%s(%s)";
        Object[] objArr = new Object[2];
        objArr[0] = this.mName;
        objArr[1] = this.agM == 1 ? "i" : "f";
        return String.format(str, objArr);
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
