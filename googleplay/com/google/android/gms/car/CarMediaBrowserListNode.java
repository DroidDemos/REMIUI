package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserSourceNode.CarMediaList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarMediaBrowserListNode implements SafeParcelable {
    public static final Creator<CarMediaBrowserListNode> CREATOR;
    public CarMediaList list;
    final int mVersionCode;
    public CarMediaSong[] songs;
    public int start;
    public int total;

    public static class CarMediaSong implements SafeParcelable {
        public static final Creator<CarMediaSong> CREATOR;
        public String album;
        public String artist;
        final int mVersionCode;
        public String name;
        public String path;

        static {
            CREATOR = new o();
        }

        public CarMediaSong() {
            this.mVersionCode = 1;
        }

        public CarMediaSong(int versionCode, String path, String name, String artist, String album) {
            this.mVersionCode = versionCode;
            this.path = path;
            this.name = name;
            this.artist = artist;
            this.album = album;
        }

        public int describeContents() {
            return 0;
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public void writeToParcel(Parcel dest, int flags) {
            o.a(this, dest, flags);
        }
    }

    static {
        CREATOR = new j();
    }

    public CarMediaBrowserListNode() {
        this.list = new CarMediaList();
        this.mVersionCode = 1;
    }

    public CarMediaBrowserListNode(int versionCode, CarMediaList list, int start, int total, CarMediaSong[] songs) {
        this.list = new CarMediaList();
        this.mVersionCode = versionCode;
        this.list = list;
        this.start = start;
        this.total = total;
        this.songs = songs;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
