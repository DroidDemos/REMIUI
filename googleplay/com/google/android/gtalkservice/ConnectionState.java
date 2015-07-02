package com.google.android.gtalkservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.wallet.instrumentmanager.R;

public final class ConnectionState implements Parcelable {
    public static final Creator<ConnectionState> CREATOR;
    private volatile int mState;

    public ConnectionState(Parcel source) {
        this.mState = source.readInt();
    }

    public final String toString() {
        return toString(this.mState);
    }

    public static final String toString(int state) {
        switch (state) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "RECONNECTION_SCHEDULED";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "CONNECTING";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "AUTHENTICATED";
            case R.styleable.WalletImFormEditText_required /*4*/:
                return "ONLINE";
            default:
                return "IDLE";
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mState);
    }

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Creator<ConnectionState>() {
            public ConnectionState createFromParcel(Parcel source) {
                return new ConnectionState(source);
            }

            public ConnectionState[] newArray(int size) {
                return new ConnectionState[size];
            }
        };
    }
}
