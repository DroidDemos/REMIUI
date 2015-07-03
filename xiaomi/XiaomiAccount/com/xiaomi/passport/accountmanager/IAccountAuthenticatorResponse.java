package com.xiaomi.passport.accountmanager;

import android.os.Bundle;
import android.os.Parcelable;

public interface IAccountAuthenticatorResponse extends Parcelable {
    int describeContents();

    void onError(int i, String str);

    void onRequestContinued();

    void onResult(Bundle bundle);
}
