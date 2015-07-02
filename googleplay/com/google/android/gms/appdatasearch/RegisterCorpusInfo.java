package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class RegisterCorpusInfo implements SafeParcelable {
    public static final y CREATOR;
    public final Account account;
    public final Uri contentProviderUri;
    public final GlobalSearchCorpusConfig globalSearchConfig;
    public final RegisterCorpusIMEInfo imeConfig;
    final int mVersionCode;
    public final String name;
    public final String schemaOrgType;
    public final RegisterSectionInfo[] sections;
    public final boolean trimmable;
    public final String version;

    static {
        CREATOR = new y();
    }

    RegisterCorpusInfo(int versionCode, String name, String version, Uri contentProviderUri, RegisterSectionInfo[] sections, GlobalSearchCorpusConfig globalSearchConfig, boolean trimmable, Account account, RegisterCorpusIMEInfo imeConfig, String schemaOrgType) {
        this.mVersionCode = versionCode;
        this.name = name;
        this.version = version;
        this.contentProviderUri = contentProviderUri;
        this.sections = sections;
        this.globalSearchConfig = globalSearchConfig;
        this.trimmable = trimmable;
        this.account = account;
        this.imeConfig = imeConfig;
        this.schemaOrgType = schemaOrgType;
    }

    public int describeContents() {
        y yVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof RegisterCorpusInfo)) {
            return false;
        }
        RegisterCorpusInfo registerCorpusInfo = (RegisterCorpusInfo) object;
        return kl.equal(this.name, registerCorpusInfo.name) && kl.equal(this.contentProviderUri, registerCorpusInfo.contentProviderUri) && kl.equal(this.sections, registerCorpusInfo.sections);
    }

    public void writeToParcel(Parcel out, int flags) {
        y yVar = CREATOR;
        y.a(this, out, flags);
    }
}
