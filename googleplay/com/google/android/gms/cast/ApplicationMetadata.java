package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.im;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.List;

public final class ApplicationMetadata implements SafeParcelable {
    public static final Creator<ApplicationMetadata> CREATOR;
    String NA;
    List<WebImage> NB;
    List<String> NC;
    String ND;
    Uri NE;
    String mName;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    private ApplicationMetadata() {
        this.mVersionCode = 1;
        this.NB = new ArrayList();
        this.NC = new ArrayList();
    }

    ApplicationMetadata(int versionCode, String applicationId, String name, List<WebImage> images, List<String> namespaces, String senderAppIdentifier, Uri senderAppLaunchUrl) {
        this.mVersionCode = versionCode;
        this.NA = applicationId;
        this.mName = name;
        this.NB = images;
        this.NC = namespaces;
        this.ND = senderAppIdentifier;
        this.NE = senderAppLaunchUrl;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return im.a(this.NA, applicationMetadata.NA) && im.a(this.NB, applicationMetadata.NB) && im.a(this.mName, applicationMetadata.mName) && im.a(this.NC, applicationMetadata.NC) && im.a(this.ND, applicationMetadata.ND) && im.a(this.NE, applicationMetadata.NE);
    }

    public String getApplicationId() {
        return this.NA;
    }

    public List<WebImage> getImages() {
        return this.NB;
    }

    public String getName() {
        return this.mName;
    }

    public String getSenderAppIdentifier() {
        return this.ND;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), this.NA, this.mName, this.NB, this.NC, this.ND, this.NE);
    }

    public Uri hl() {
        return this.NE;
    }

    public String toString() {
        return this.mName;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
