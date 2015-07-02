package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarMediaBrowserRootNode implements SafeParcelable {
    public static final Creator<CarMediaBrowserRootNode> CREATOR;
    final int mVersionCode;
    public CarMediaSource[] mediaSources;
    public String path;

    public static class CarMediaSource implements SafeParcelable {
        public static final Creator<CarMediaSource> CREATOR;
        public byte[] albumArt;
        final int mVersionCode;
        public String name;
        public String sourcePath;

        static {
            CREATOR = new p();
        }

        public CarMediaSource() {
            this.mVersionCode = 1;
        }

        public CarMediaSource(int versionCode, String sourcePath, String name, byte[] albumArt) {
            this.mVersionCode = versionCode;
            this.sourcePath = sourcePath;
            this.name = name;
            this.albumArt = albumArt;
        }

        public int describeContents() {
            return 0;
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public void writeToParcel(Parcel dest, int flags) {
            p.a(this, dest, flags);
        }
    }

    static {
        CREATOR = new k();
    }

    public CarMediaBrowserRootNode() {
        this.mVersionCode = 1;
    }

    public CarMediaBrowserRootNode(int versionCode, String path, CarMediaSource[] mediaSources) {
        this.mVersionCode = versionCode;
        this.path = path;
        this.mediaSources = mediaSources;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
