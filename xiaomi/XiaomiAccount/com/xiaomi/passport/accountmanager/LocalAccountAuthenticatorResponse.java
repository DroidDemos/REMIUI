package com.xiaomi.passport.accountmanager;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.xiaomi.accounts.AccountAuthenticatorResponse;

public class LocalAccountAuthenticatorResponse implements IAccountAuthenticatorResponse {
    public static final Creator<LocalAccountAuthenticatorResponse> CREATOR;
    AccountAuthenticatorResponse mResponse;

    public LocalAccountAuthenticatorResponse(AccountAuthenticatorResponse response) {
        this.mResponse = response;
    }

    public LocalAccountAuthenticatorResponse(Parcel parcel) {
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
        CREATOR = new Creator<LocalAccountAuthenticatorResponse>() {
            public LocalAccountAuthenticatorResponse createFromParcel(Parcel source) {
                return new LocalAccountAuthenticatorResponse(source);
            }

            public LocalAccountAuthenticatorResponse[] newArray(int size) {
                return new LocalAccountAuthenticatorResponse[size];
            }
        };
    }
}
