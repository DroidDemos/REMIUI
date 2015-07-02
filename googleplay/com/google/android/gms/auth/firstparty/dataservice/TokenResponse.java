package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.CaptchaChallenge;
import com.google.android.gms.auth.firstparty.shared.ScopeDetail;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

public class TokenResponse implements SafeParcelable {
    public static final ag CREATOR;
    String GP;
    String Gv;
    String Gx;
    CaptchaChallenge Gy;
    String HM;
    String HN;
    boolean HO;
    boolean HP;
    boolean HQ;
    boolean HR;
    List<ScopeDetail> HS;
    boolean HT;
    boolean HU;
    String Hu;
    String Hz;
    String accountName;
    String firstName;
    String lastName;
    int title;
    final int version;

    static {
        CREATOR = new ag();
    }

    public TokenResponse() {
        this.version = 1;
        this.HS = new ArrayList();
    }

    TokenResponse(int version, String accountName, String statusWireCode, String token, String signInUrl, String detail, String picasaUser, String firstName, String lastName, boolean isGPlusServiceAllowed, boolean isGPlusServiceEnabled, boolean isEsMobileServiceEnabled, boolean isBrowserSignInSuggested, CaptchaChallenge captcha, List<ScopeDetail> scopeData, String ropText, String ropRevision, boolean isTokenCached, boolean hasTitle, int title) {
        this.version = version;
        this.accountName = accountName;
        this.Gv = statusWireCode;
        this.GP = token;
        this.HM = signInUrl;
        this.Gx = detail;
        this.HN = picasaUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.HO = isGPlusServiceAllowed;
        this.HP = isGPlusServiceEnabled;
        this.HQ = isEsMobileServiceEnabled;
        this.HR = isBrowserSignInSuggested;
        this.Gy = captcha;
        this.HS = scopeData;
        this.Hz = ropText;
        this.Hu = ropRevision;
        this.HT = isTokenCached;
        this.HU = hasTitle;
        this.title = title;
    }

    public int describeContents() {
        return 0;
    }

    public Status getStatus() {
        return Status.fromWireCode(this.Gv);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ag.a(this, dest, flags);
    }
}
