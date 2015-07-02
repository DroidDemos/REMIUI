package com.google.android.gms.games.snapshot;

public abstract class SnapshotMetadataChange {
    public static final SnapshotMetadataChange EMPTY_CHANGE;

    static {
        EMPTY_CHANGE = new SnapshotMetadataChangeEntity();
    }

    protected SnapshotMetadataChange() {
    }
}
