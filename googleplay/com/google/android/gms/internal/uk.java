package com.google.android.gms.internal;

import com.google.android.gms.common.data.c;

public class uk extends c {
    protected Double getAsDouble(String column) {
        return bb(column) ? null : Double.valueOf(getDouble(column));
    }

    protected Integer getAsInteger(String column) {
        return bb(column) ? null : Integer.valueOf(getInteger(column));
    }

    protected Long getAsLong(String column) {
        return bb(column) ? null : Long.valueOf(getLong(column));
    }

    @Deprecated
    protected boolean getBoolean(String column) {
        return super.getBoolean(column);
    }

    @Deprecated
    protected double getDouble(String column) {
        return super.getDouble(column);
    }

    @Deprecated
    protected float getFloat(String column) {
        return super.getFloat(column);
    }

    @Deprecated
    protected int getInteger(String column) {
        return super.getInteger(column);
    }

    @Deprecated
    protected long getLong(String column) {
        return super.getLong(column);
    }
}
