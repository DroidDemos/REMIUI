package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserListNode.CarMediaSong;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarMediaBrowserSongNode implements SafeParcelable {
    public static final Creator<CarMediaBrowserSongNode> CREATOR;
    public byte[] albumArt;
    public int durationSeconds;
    final int mVersionCode;
    public CarMediaSong song;

    static {
        CREATOR = new l();
    }

    public CarMediaBrowserSongNode() {
        this.mVersionCode = 1;
        this.song = new CarMediaSong();
    }

    public CarMediaBrowserSongNode(int versionCode, CarMediaSong song, byte[] albumArt, int durationSeconds) {
        this.mVersionCode = versionCode;
        this.song = song;
        this.albumArt = albumArt;
        this.durationSeconds = durationSeconds;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        l.a(this, dest, flags);
    }
}
