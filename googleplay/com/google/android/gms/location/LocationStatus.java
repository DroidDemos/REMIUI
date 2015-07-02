package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.wallet.instrumentmanager.R;

public class LocationStatus implements SafeParcelable {
    public static final d CREATOR;
    int atE;
    int atF;
    long atG;
    private final int mVersionCode;

    static {
        CREATOR = new d();
    }

    public LocationStatus(int versionCode, int cellStatus, int wifiStatus, long elapsedRealtimeNs) {
        this.mVersionCode = versionCode;
        this.atE = cellStatus;
        this.atF = wifiStatus;
        this.atG = elapsedRealtimeNs;
    }

    private String hw(int i) {
        switch (i) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return "STATUS_SUCCESSFUL";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "STATUS_TIMED_OUT_ON_SCAN";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "STATUS_NO_INFO_IN_DATABASE";
            case R.styleable.WalletImFormEditText_required /*4*/:
                return "STATUS_INVALID_SCAN";
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return "STATUS_UNABLE_TO_QUERY_DATABASE";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return "STATUS_SCANS_DISABLED_IN_SETTINGS";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return "STATUS_LOCATION_DISABLED_IN_SETTINGS";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return "STATUS_IN_PROGRESS";
            default:
                return "STATUS_UNKNOWN";
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (!(other instanceof LocationStatus)) {
            return false;
        }
        LocationStatus locationStatus = (LocationStatus) other;
        return this.atE == locationStatus.atE && this.atF == locationStatus.atF && this.atG == locationStatus.atG;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.atE), Integer.valueOf(this.atF), Long.valueOf(this.atG));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LocationStatus[cell status: ").append(hw(this.atE));
        stringBuilder.append(", wifi status: ").append(hw(this.atF));
        stringBuilder.append(", elapsed realtime ns: ").append(this.atG);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        d.a(this, parcel, flags);
    }
}
