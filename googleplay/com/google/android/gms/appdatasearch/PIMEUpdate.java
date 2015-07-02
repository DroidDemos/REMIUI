package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PIMEUpdate implements SafeParcelable {
    public static final r CREATOR;
    final byte[] Dv;
    final byte[] Dw;
    final Bundle Dx;
    public final Account account;
    public final long createdTimestamp;
    public final boolean inputByUser;
    final int mVersionCode;
    public final long score;
    public final int sourceClass;
    public final String sourceCorpusHandle;
    public final String sourcePackageName;

    static {
        CREATOR = new r();
    }

    PIMEUpdate(int versionCode, byte[] contents, byte[] varIntLengths, int sourceClass, String sourcePackageName, String sourceCorpusHandle, boolean inputByUser, Bundle languageScores, long score, long createdTimestamp, Account account) {
        this.mVersionCode = versionCode;
        this.Dv = contents;
        this.Dw = varIntLengths;
        this.sourceClass = sourceClass;
        this.sourcePackageName = sourcePackageName;
        this.sourceCorpusHandle = sourceCorpusHandle;
        this.inputByUser = inputByUser;
        this.Dx = languageScores;
        this.score = score;
        this.createdTimestamp = createdTimestamp;
        this.account = account;
    }

    public int describeContents() {
        r rVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        r rVar = CREATOR;
        r.a(this, out, flags);
    }
}
