package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.a;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DataType implements SafeParcelable {
    public static final DataType AGGREGATE_ACTIVITY_SUMMARY;
    public static final DataType AGGREGATE_DISTANCE_DELTA;
    public static final DataType AGGREGATE_HEART_RATE_SUMMARY;
    public static final Set<DataType> AGGREGATE_INPUT_TYPES;
    public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX;
    public static final DataType AGGREGATE_POWER_SUMMARY;
    public static final DataType AGGREGATE_SPEED_SUMMARY;
    public static final DataType AGGREGATE_STEP_COUNT_DELTA;
    public static final DataType AGGREGATE_WEIGHT_SUMMARY;
    public static final DataType[] ALL_DATATYPES;
    public static final String[] ALL_DATATYPE_NAMES;
    public static final Creator<DataType> CREATOR;
    public static final DataType TYPE_ACCELEROMETER;
    public static final DataType TYPE_ACTIVITY_SAMPLE;
    public static final DataType TYPE_ACTIVITY_SEGMENT;
    public static final DataType TYPE_CALORIES_CONSUMED;
    public static final DataType TYPE_CALORIES_EXPENDED;
    public static final DataType TYPE_CYCLING_PEDALING_CADENCE;
    public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE;
    public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION;
    public static final DataType TYPE_CYCLING_WHEEL_RPM;
    public static final DataType TYPE_DISTANCE_CUMULATIVE;
    public static final DataType TYPE_DISTANCE_DELTA;
    public static final DataType TYPE_HEART_RATE_BPM;
    public static final DataType TYPE_HEIGHT;
    public static final DataType TYPE_LOCATION_SAMPLE;
    public static final DataType TYPE_POWER_SAMPLE;
    public static final DataType TYPE_SPEED;
    public static final DataType TYPE_STEP_COUNT_CADENCE;
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE;
    public static final DataType TYPE_STEP_COUNT_DELTA;
    public static final DataType TYPE_WEIGHT;
    public static final DataType agB;
    private static final Map<DataType, List<DataType>> agC;
    private final List<Field> agD;
    private final String mName;
    private final int mVersionCode;

    static {
        TYPE_STEP_COUNT_DELTA = new DataType("com.google.step_count.delta", Field.FIELD_STEPS);
        TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", Field.FIELD_STEPS);
        TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", Field.FIELD_RPM);
        TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", Field.FIELD_ACTIVITY);
        TYPE_CALORIES_CONSUMED = new DataType("com.google.calories.consumed", Field.FIELD_CALORIES);
        TYPE_CALORIES_EXPENDED = new DataType("com.google.calories.expended", Field.FIELD_CALORIES);
        TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", Field.FIELD_WATTS);
        TYPE_ACTIVITY_SAMPLE = new DataType("com.google.activity.sample", Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE);
        agB = new DataType("com.google.activity.edge", Field.FIELD_ACTIVITY, Field.agI);
        TYPE_ACCELEROMETER = new DataType("com.google.accelerometer", Field.agJ, Field.agK, Field.agL);
        TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", Field.FIELD_BPM);
        TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
        TYPE_DISTANCE_DELTA = new DataType("com.google.distance.delta", Field.FIELD_DISTANCE);
        TYPE_DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", Field.FIELD_DISTANCE);
        TYPE_SPEED = new DataType("com.google.speed", Field.FIELD_SPEED);
        TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", Field.FIELD_REVOLUTIONS);
        TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", Field.FIELD_RPM);
        TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", Field.FIELD_REVOLUTIONS);
        TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", Field.FIELD_RPM);
        TYPE_HEIGHT = new DataType("com.google.height", Field.FIELD_HEIGHT);
        TYPE_WEIGHT = new DataType("com.google.weight", Field.FIELD_WEIGHT);
        AGGREGATE_INPUT_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new DataType[]{TYPE_STEP_COUNT_DELTA, TYPE_DISTANCE_DELTA, TYPE_ACTIVITY_SEGMENT, TYPE_SPEED, TYPE_HEART_RATE_BPM, TYPE_WEIGHT, TYPE_LOCATION_SAMPLE})));
        AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS);
        AGGREGATE_STEP_COUNT_DELTA = TYPE_STEP_COUNT_DELTA;
        AGGREGATE_DISTANCE_DELTA = TYPE_DISTANCE_DELTA;
        AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE);
        AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        agC = new HashMap<DataType, List<DataType>>() {
            {
                put(DataType.TYPE_ACTIVITY_SEGMENT, Arrays.asList(new DataType[]{DataType.AGGREGATE_ACTIVITY_SUMMARY}));
                put(DataType.TYPE_DISTANCE_DELTA, Arrays.asList(new DataType[]{DataType.AGGREGATE_DISTANCE_DELTA}));
                put(DataType.TYPE_LOCATION_SAMPLE, Arrays.asList(new DataType[]{DataType.AGGREGATE_LOCATION_BOUNDING_BOX}));
                put(DataType.TYPE_POWER_SAMPLE, Arrays.asList(new DataType[]{DataType.AGGREGATE_POWER_SUMMARY}));
                put(DataType.TYPE_HEART_RATE_BPM, Arrays.asList(new DataType[]{DataType.AGGREGATE_HEART_RATE_SUMMARY}));
                put(DataType.TYPE_SPEED, Arrays.asList(new DataType[]{DataType.AGGREGATE_SPEED_SUMMARY}));
                put(DataType.TYPE_STEP_COUNT_DELTA, Arrays.asList(new DataType[]{DataType.AGGREGATE_STEP_COUNT_DELTA}));
                put(DataType.TYPE_WEIGHT, Arrays.asList(new DataType[]{DataType.AGGREGATE_WEIGHT_SUMMARY}));
            }
        };
        ALL_DATATYPES = new DataType[]{TYPE_ACCELEROMETER, agB, TYPE_ACTIVITY_SAMPLE, TYPE_ACTIVITY_SEGMENT, AGGREGATE_ACTIVITY_SUMMARY, TYPE_CALORIES_CONSUMED, TYPE_CALORIES_EXPENDED, TYPE_CYCLING_PEDALING_CADENCE, TYPE_CYCLING_PEDALING_CUMULATIVE, TYPE_CYCLING_WHEEL_REVOLUTION, TYPE_CYCLING_WHEEL_RPM, TYPE_DISTANCE_CUMULATIVE, TYPE_DISTANCE_DELTA, TYPE_HEART_RATE_BPM, AGGREGATE_HEART_RATE_SUMMARY, TYPE_HEIGHT, AGGREGATE_LOCATION_BOUNDING_BOX, TYPE_LOCATION_SAMPLE, TYPE_POWER_SAMPLE, AGGREGATE_POWER_SUMMARY, TYPE_SPEED, AGGREGATE_SPEED_SUMMARY, TYPE_STEP_COUNT_CADENCE, TYPE_STEP_COUNT_CUMULATIVE, TYPE_STEP_COUNT_DELTA, TYPE_WEIGHT, AGGREGATE_WEIGHT_SUMMARY};
        ALL_DATATYPE_NAMES = new String[]{TYPE_ACCELEROMETER.getName(), agB.getName(), TYPE_ACTIVITY_SAMPLE.getName(), TYPE_ACTIVITY_SEGMENT.getName(), AGGREGATE_ACTIVITY_SUMMARY.getName(), TYPE_CALORIES_CONSUMED.getName(), TYPE_CALORIES_EXPENDED.getName(), TYPE_CYCLING_PEDALING_CADENCE.getName(), TYPE_CYCLING_PEDALING_CUMULATIVE.getName(), TYPE_CYCLING_WHEEL_REVOLUTION.getName(), TYPE_CYCLING_WHEEL_RPM.getName(), TYPE_DISTANCE_CUMULATIVE.getName(), TYPE_DISTANCE_DELTA.getName(), TYPE_HEART_RATE_BPM.getName(), AGGREGATE_HEART_RATE_SUMMARY.getName(), TYPE_HEIGHT.getName(), AGGREGATE_LOCATION_BOUNDING_BOX.getName(), TYPE_LOCATION_SAMPLE.getName(), TYPE_POWER_SAMPLE.getName(), AGGREGATE_POWER_SUMMARY.getName(), TYPE_SPEED.getName(), AGGREGATE_SPEED_SUMMARY.getName(), TYPE_STEP_COUNT_CADENCE.getName(), TYPE_STEP_COUNT_CUMULATIVE.getName(), TYPE_STEP_COUNT_DELTA.getName(), TYPE_WEIGHT.getName(), AGGREGATE_WEIGHT_SUMMARY.getName()};
        CREATOR = new g();
    }

    DataType(int versionCode, String name, List<Field> fields) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.agD = Collections.unmodifiableList(fields);
    }

    public DataType(String name, Field... fields) {
        this(1, name, a.b(fields));
    }

    private boolean a(DataType dataType) {
        return this.mName.equals(dataType.mName) && this.agD.equals(dataType.agD);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof DataType) && a((DataType) that));
    }

    public List<Field> getFields() {
        return this.agD;
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

    public String toShortName() {
        return this.mName.startsWith("com.google.") ? this.mName.substring(11) : this.mName;
    }

    public String toString() {
        return String.format("DataType{%s%s}", new Object[]{this.mName, this.agD});
    }

    public void writeToParcel(Parcel dest, int flags) {
        g.a(this, dest, flags);
    }
}
