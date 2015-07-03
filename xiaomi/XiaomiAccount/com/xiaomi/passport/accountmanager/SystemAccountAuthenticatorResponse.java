package com.xiaomi.passport.accountmanager;

import android.accounts.AccountAuthenticatorResponse;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class SystemAccountAuthenticatorResponse implements IAccountAuthenticatorResponse {
    public static final Creator<SystemAccountAuthenticatorResponse> CREATOR;
    AccountAuthenticatorResponse mResponse;

    public SystemAccountAuthenticatorResponse(AccountAuthenticatorResponse response) {
        this.mResponse = response;
    }

    public SystemAccountAuthenticatorResponse(Parcel parcel) {
        this.mResponse = new AccountAuthenticatorResponse(parcel);
    }

    public void onResult(Bundle result) {
        this.mResponse.onResult(result);
    }

    public void onRequestContinued() {
        this.mResponse.onRequestContinued();
    }

    public void onError(int errorCode, String errorMessage) {
        this.mResponse.onError(errorCode, errorMessage);
    }

    public int describeContents() {
        return this.mResponse.describeContents();
    }

    public void writeToParcel(Parcel dest, int flags) {
        this.mResponse.writeToParcel(dest, flags);
    }

    static {
        CREATOR = new Creator<SystemAccountAuthenticatorResponse>() {
            public SystemAccountAuthenticatorResponse createFromParcel(Parcel source) {
                return new SystemAccountAuthenticatorResponse(source);
            }

            public SystemAccountAuthenticatorResponse[] newArray(int size) {
                return new SystemAccountAuthenticatorResponse[size];
            }
        };
    }
}
