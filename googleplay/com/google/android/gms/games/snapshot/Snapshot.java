package com.google.android.gms.games.snapshot;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Snapshot extends Parcelable, Freezable<Snapshot> {
    SnapshotMetadata getMetadata();

    SnapshotContents getSnapshotContents();
}
