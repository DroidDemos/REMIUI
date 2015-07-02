package com.google.android.libraries.bind.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment.SavedState;
import com.google.android.libraries.bind.util.ParcelUtil;
import com.google.android.libraries.bind.util.Util;

public abstract class FragmentStateDataPagerAdapter extends FragmentDataPagerAdapter {

    static class FragmentAdapterState implements Parcelable {
        public static final Creator<FragmentAdapterState> CREATOR;
        public final Object key;
        public final SavedState state;

        public FragmentAdapterState(Object key, SavedState state) {
            this.key = key;
            this.state = state;
        }

        public boolean equals(Object other) {
            if (!(other instanceof FragmentAdapterState)) {
                return false;
            }
            FragmentAdapterState adapterState = (FragmentAdapterState) other;
            if (Util.objectsEqual(this.key, adapterState.key) && Util.objectsEqual(this.state, adapterState.state)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Util.hashCode(this.key, this.state);
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return String.format("key: %s, fragmentState: %s", new Object[]{this.key, this.state});
        }

        public void writeToParcel(Parcel dest, int flags) {
            ParcelUtil.writeObjectToParcel(this.key, dest, flags);
            dest.writeParcelable(this.state, flags);
        }

        static {
            CREATOR = new Creator<FragmentAdapterState>() {
                public FragmentAdapterState createFromParcel(Parcel source) {
                    return new FragmentAdapterState(ParcelUtil.readObjectFromParcel(source, FragmentAdapterState.class.getClassLoader()), (SavedState) source.readParcelable(FragmentAdapterState.class.getClassLoader()));
                }

                public FragmentAdapterState[] newArray(int size) {
                    return new FragmentAdapterState[size];
                }
            };
        }
    }
}
