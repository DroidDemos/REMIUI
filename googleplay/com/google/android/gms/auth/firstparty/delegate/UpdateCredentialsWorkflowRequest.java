package com.google.android.gms.auth.firstparty.delegate;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UpdateCredentialsWorkflowRequest implements SafeParcelable {
    public static final e CREATOR;
    Bundle Ho;
    String accountName;
    AppDescription callingAppDescription;
    final int version;

    static {
        CREATOR = new e();
    }

    public UpdateCredentialsWorkflowRequest() {
        this.version = 1;
        this.Ho = new Bundle();
    }

    UpdateCredentialsWorkflowRequest(int version, String accountName, AppDescription callingAppDescription, Bundle options) {
        this.version = version;
        this.accountName = accountName;
        this.callingAppDescription = callingAppDescription;
        this.Ho = options;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
