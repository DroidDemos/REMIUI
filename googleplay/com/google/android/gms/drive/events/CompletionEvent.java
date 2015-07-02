package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.List;
import java.util.Locale;

public final class CompletionEvent implements SafeParcelable, ResourceEvent {
    public static final Creator<CompletionEvent> CREATOR;
    final String Fl;
    final int Ob;
    final ParcelFileDescriptor YL;
    final ParcelFileDescriptor YM;
    final MetadataBundle YN;
    final List<String> YO;
    final IBinder YP;
    private boolean YQ;
    private boolean YR;
    private boolean YS;
    final DriveId Yb;
    final int mVersionCode;

    static {
        CREATOR = new b();
    }

    CompletionEvent(int versionCode, DriveId driveId, String accountName, ParcelFileDescriptor baseParcelFileDescriptor, ParcelFileDescriptor modifiedParcelFileDescriptor, MetadataBundle modifiedMetadataBundle, List<String> trackingTags, int status, IBinder releaseCallback) {
        this.YQ = false;
        this.YR = false;
        this.YS = false;
        this.mVersionCode = versionCode;
        this.Yb = driveId;
        this.Fl = accountName;
        this.YL = baseParcelFileDescriptor;
        this.YM = modifiedParcelFileDescriptor;
        this.YN = modifiedMetadataBundle;
        this.YO = trackingTags;
        this.Ob = status;
        this.YP = releaseCallback;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        String str = this.YO == null ? "<null>" : "'" + TextUtils.join("','", this.YO) + "'";
        return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", new Object[]{this.Yb, Integer.valueOf(this.Ob), str});
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
