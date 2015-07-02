package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.a;
import java.util.ArrayList;

public final class LabelValueRow implements SafeParcelable {
    public static final Creator<LabelValueRow> CREATOR;
    String aVL;
    String aVM;
    ArrayList<LabelValue> aVN;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    LabelValueRow() {
        this.mVersionCode = 1;
        this.aVN = a.ji();
    }

    LabelValueRow(int versionCode, String hexFontColor, String hexBackgroundColor, ArrayList<LabelValue> columns) {
        this.mVersionCode = versionCode;
        this.aVL = hexFontColor;
        this.aVM = hexBackgroundColor;
        this.aVN = columns;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
