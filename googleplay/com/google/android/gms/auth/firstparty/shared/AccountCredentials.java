package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountCredentials implements SafeParcelable {
    public static final a CREATOR;
    String Fl;
    boolean Ik;
    String Il;
    String Im;
    String In;
    String Io;
    String Ip;
    final int version;

    static {
        CREATOR = new a();
    }

    public AccountCredentials() {
        this.version = 1;
    }

    AccountCredentials(int version, boolean isBrowserRequired, String accountName, String longLivedLoginToken, String authorizationCode, String password, String verifier, String redirectUri) {
        this.version = version;
        this.Ik = isBrowserRequired;
        this.Fl = accountName;
        this.Il = longLivedLoginToken;
        this.Im = authorizationCode;
        this.In = password;
        this.Io = verifier;
        this.Ip = redirectUri;
    }

    public int describeContents() {
        return 0;
    }

    public AccountCredentials setAccountName(String accountName) {
        this.Fl = accountName;
        return this;
    }

    public AccountCredentials setPassword(String password) {
        this.In = password;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
