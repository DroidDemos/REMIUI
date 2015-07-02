package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class WebImage implements SafeParcelable {
    public static final Creator<WebImage> CREATOR;
    private final Uri UY;
    private final int lh;
    private final int li;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    WebImage(int versionCode, Uri url, int width, int height) {
        this.mVersionCode = versionCode;
        this.UY = url;
        this.lh = width;
        this.li = height;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) other;
        return kl.equal(this.UY, webImage.UY) && this.lh == webImage.lh && this.li == webImage.li;
    }

    public int getHeight() {
        return this.li;
    }

    public Uri getUrl() {
        return this.UY;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int getWidth() {
        return this.lh;
    }

    public int hashCode() {
        return kl.hashCode(this.UY, Integer.valueOf(this.lh), Integer.valueOf(this.li));
    }

    public String toString() {
        return String.format("Image %dx%d %s", new Object[]{Integer.valueOf(this.lh), Integer.valueOf(this.li), this.UY.toString()});
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
