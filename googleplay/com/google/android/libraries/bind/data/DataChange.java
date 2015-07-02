package com.google.android.libraries.bind.data;

public class DataChange {
    public static final DataChange AFFECTS_PRIMARY_KEY;
    public static final DataChange DOESNT_AFFECT_PRIMARY_KEY;
    public static final DataChange INVALIDATION;
    public final boolean affectsPrimaryKey;
    public final boolean hasRefreshException;
    public final boolean isInvalidation;
    public final DataException refreshException;

    static {
        INVALIDATION = new DataChange(true, true);
        AFFECTS_PRIMARY_KEY = new DataChange(false, true);
        DOESNT_AFFECT_PRIMARY_KEY = new DataChange(false, false);
    }

    public DataChange(DataException refreshException) {
        this.isInvalidation = true;
        this.affectsPrimaryKey = true;
        this.hasRefreshException = true;
        this.refreshException = refreshException;
    }

    protected DataChange(boolean isInvalidation, boolean affectsPrimaryKey) {
        this.isInvalidation = isInvalidation;
        this.affectsPrimaryKey = affectsPrimaryKey;
        this.hasRefreshException = false;
        this.refreshException = null;
    }

    public String toString() {
        return String.format("isInvalidation: %b, affectsPrimaryKey: %b, exception: %s", new Object[]{Boolean.valueOf(this.isInvalidation), Boolean.valueOf(this.affectsPrimaryKey), this.refreshException});
    }
}
