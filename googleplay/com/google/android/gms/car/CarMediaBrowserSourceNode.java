package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserRootNode.CarMediaSource;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarMediaBrowserSourceNode implements SafeParcelable {
    public static final Creator<CarMediaBrowserSourceNode> CREATOR;
    public CarMediaList[] lists;
    final int mVersionCode;
    public CarMediaSource mediaSource;
    public int start;
    public int total;

    public static class CarMediaList implements SafeParcelable {
        public static final Creator<CarMediaList> CREATOR;
        public byte[] albumArt;
        final int mVersionCode;
        public String name;
        public String path;
        public int type;

        static {
            CREATOR = new n();
        }

        public CarMediaList() {
            this.mVersionCode = 1;
        }

        public CarMediaList(int versionCode, String path, String name, byte[] albumArt, int type) {
            this.mVersionCode = versionCode;
            this.path = path;
            this.name = name;
            this.albumArt = albumArt;
            this.type = type;
        }

        public int describeContents() {
            return 0;
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public void writeToParcel(Parcel dest, int flags) {
            n.a(this, dest, flags);
        }
    }

    static {
        CREATOR = new m();
    }

    public CarMediaBrowserSourceNode() {
        this.mVersionCode = 1;
        this.mediaSource = new CarMediaSource();
    }

    public CarMediaBrowserSourceNode(int versionCode, CarMediaSource mediaSource, int start, int total, CarMediaList[] lists) {
        this.mVersionCode = versionCode;
        this.mediaSource = mediaSource;
        this.start = start;
        this.total = total;
        this.lists = lists;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        m.a(this, dest, flags);
    }
}
