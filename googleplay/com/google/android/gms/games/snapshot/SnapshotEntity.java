package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class SnapshotEntity implements SafeParcelable, Snapshot {
    public static final SnapshotEntityCreator CREATOR;
    private final SnapshotMetadataEntity aqY;
    private final SnapshotContentsEntity aqZ;
    private final int mVersionCode;

    static {
        CREATOR = new SnapshotEntityCreator();
    }

    SnapshotEntity(int versionCode, SnapshotMetadata metadata, SnapshotContentsEntity contents) {
        this.mVersionCode = versionCode;
        this.aqY = new SnapshotMetadataEntity(metadata);
        this.aqZ = contents;
    }

    static boolean a(Snapshot snapshot, Object obj) {
        if (!(obj instanceof Snapshot)) {
            return false;
        }
        if (snapshot == obj) {
            return true;
        }
        Snapshot snapshot2 = (Snapshot) obj;
        return kl.equal(snapshot2.getMetadata(), snapshot.getMetadata()) && kl.equal(snapshot2.getSnapshotContents(), snapshot.getSnapshotContents());
    }

    static int b(Snapshot snapshot) {
        return kl.hashCode(snapshot.getMetadata(), snapshot.getSnapshotContents());
    }

    static String c(Snapshot snapshot) {
        return kl.j(snapshot).a("Metadata", snapshot.getMetadata()).a("HasContents", Boolean.valueOf(snapshot.getSnapshotContents() != null)).toString();
    }

    private boolean isClosed() {
        return this.aqZ.isClosed();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Snapshot freeze() {
        return this;
    }

    public SnapshotMetadata getMetadata() {
        return this.aqY;
    }

    public SnapshotContents getSnapshotContents() {
        return isClosed() ? null : this.aqZ;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return b(this);
    }

    public String toString() {
        return c(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        SnapshotEntityCreator.a(this, out, flags);
    }
}
