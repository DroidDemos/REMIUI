package com.google.android.finsky.navigationmanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NavigationState implements Parcelable {
    public static final Creator<NavigationState> CREATOR;
    public final String backstackName;
    public boolean canTriggerSearchSurvey;
    public boolean isActionBarOverlayEnabled;
    public final int pageType;

    public NavigationState(int pageType) {
        this(pageType, Integer.toString((int) (Math.random() * 2.147483646E9d)));
    }

    NavigationState(int pageType, String backstackName) {
        this.pageType = pageType;
        this.backstackName = backstackName;
    }

    public String toString() {
        return "[type: " + this.pageType + ", name: " + this.backstackName + "]";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeInt(this.pageType);
        dest.writeString(this.backstackName);
        if (this.isActionBarOverlayEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (!this.canTriggerSearchSurvey) {
            i2 = 0;
        }
        dest.writeByte((byte) i2);
    }

    static {
        CREATOR = new Creator<NavigationState>() {
            public NavigationState createFromParcel(Parcel in) {
                boolean z;
                boolean z2 = true;
                NavigationState result = new NavigationState(in.readInt(), in.readString());
                if (in.readByte() > (byte) 0) {
                    z = true;
                } else {
                    z = false;
                }
                result.isActionBarOverlayEnabled = z;
                if (in.readByte() <= (byte) 0) {
                    z2 = false;
                }
                result.canTriggerSearchSurvey = z2;
                return result;
            }

            public NavigationState[] newArray(int size) {
                return new NavigationState[size];
            }
        };
    }
}
