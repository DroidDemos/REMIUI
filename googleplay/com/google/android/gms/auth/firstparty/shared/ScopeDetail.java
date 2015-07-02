package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class ScopeDetail implements SafeParcelable {
    public static final j CREATOR;
    String Gx;
    String HE;
    String IL;
    String IM;
    List<String> IN;
    String description;
    public FACLData friendPickerData;
    final int version;

    static {
        CREATOR = new j();
    }

    ScopeDetail(int version, String description, String detail, String iconBase64, String paclPickerDataBase64, String service, List<String> warnings, FACLData friendPickerData) {
        this.version = version;
        this.description = description;
        this.Gx = detail;
        this.IL = iconBase64;
        this.IM = paclPickerDataBase64;
        this.HE = service;
        this.IN = warnings;
        this.friendPickerData = friendPickerData;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
