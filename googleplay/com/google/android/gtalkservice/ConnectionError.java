package com.google.android.gtalkservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.wallet.instrumentmanager.R;

public final class ConnectionError implements Parcelable {
    public static final Creator<ConnectionError> CREATOR;
    private int mError;

    public ConnectionError(Parcel source) {
        this.mError = source.readInt();
    }

    public final String toString() {
        return toString(this.mError);
    }

    public static final String toString(int state) {
        switch (state) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "NO NETWORK";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "CONNECTION FAILED";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "UNKNOWN HOST";
            case R.styleable.WalletImFormEditText_required /*4*/:
                return "AUTH FAILED";
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return "AUTH EXPIRED";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return "HEARTBEAT TIMEOUT";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return "SERVER FAILED";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return "SERVER REJECT - RATE LIMIT";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return "UNKNOWN";
            default:
                return "NO ERROR";
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mError);
    }

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Creator<ConnectionError>() {
            public ConnectionError createFromParcel(Parcel source) {
                return new ConnectionError(source);
            }

            public ConnectionError[] newArray(int size) {
                return new ConnectionError[size];
            }
        };
    }
}
