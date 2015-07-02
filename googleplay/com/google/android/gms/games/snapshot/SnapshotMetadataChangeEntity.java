package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public final class SnapshotMetadataChangeEntity extends SnapshotMetadataChange implements SafeParcelable {
    public static final SnapshotMetadataChangeCreator CREATOR;
    private final Uri arc;
    private final Long ard;
    private BitmapTeleporter are;
    private final String mDescription;
    private final int mVersionCode;

    static {
        CREATOR = new SnapshotMetadataChangeCreator();
    }

    SnapshotMetadataChangeEntity() {
        this(4, null, null, null, null);
    }

    SnapshotMetadataChangeEntity(int versionCode, String description, Long playedTimeMillis, BitmapTeleporter coverImage, Uri coverImageUri) {
        boolean z = true;
        this.mVersionCode = versionCode;
        this.mDescription = description;
        this.ard = playedTimeMillis;
        this.are = coverImage;
        this.arc = coverImageUri;
        if (this.are != null) {
            if (this.arc != null) {
                z = false;
            }
            kn.a(z, "Cannot set both a URI and an image");
        } else if (this.arc != null) {
            if (this.are != null) {
                z = false;
            }
            kn.a(z, "Cannot set both a URI and an image");
        }
    }

    public int describeContents() {
        return 0;
    }

    public Uri getCoverImageUri() {
        return this.arc;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Long getPlayedTimeMillis() {
        return this.ard;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public BitmapTeleporter oI() {
        return this.are;
    }

    public void writeToParcel(Parcel out, int flags) {
        SnapshotMetadataChangeCreator.a(this, out, flags);
    }
}
