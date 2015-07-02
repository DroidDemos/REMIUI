package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DocumentContents implements SafeParcelable {
    public static final d CREATOR;
    final DocumentSection[] CO;
    public final Account account;
    public final boolean globalSearchEnabled;
    final int mVersionCode;
    public final String schemaOrgType;

    static {
        CREATOR = new d();
    }

    DocumentContents(int versionCode, DocumentSection[] sectionContents, String schemaOrgType, boolean globalSearchEnabled, Account account) {
        this.mVersionCode = versionCode;
        this.CO = sectionContents;
        this.schemaOrgType = schemaOrgType;
        this.globalSearchEnabled = globalSearchEnabled;
        this.account = account;
    }

    public int describeContents() {
        d dVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        d dVar = CREATOR;
        d.a(this, dest, flags);
    }
}
