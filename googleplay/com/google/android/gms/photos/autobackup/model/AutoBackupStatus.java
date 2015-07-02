package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class AutoBackupStatus implements SafeParcelable {
    public static final Creator<AutoBackupStatus> CREATOR;
    private String Fl;
    private int aGG;
    private String aGH;
    private float aGI;
    private int aGJ;
    private int aGK;
    private int aGL;
    private String[] aGM;
    private String aGN;
    public final int mVersionCode;

    static {
        CREATOR = new b();
    }

    public AutoBackupStatus() {
        this.mVersionCode = 1;
    }

    AutoBackupStatus(int versionCode, int autoBackupState, String accountName, String currentItem, float currentItemProgress, int numCompleted, int numPending, int numFailed, String[] failedItems, String enabledAccountName) {
        this.mVersionCode = versionCode;
        this.aGG = autoBackupState;
        this.Fl = accountName;
        this.aGH = currentItem;
        this.aGI = currentItemProgress;
        this.aGJ = numCompleted;
        this.aGK = numPending;
        this.aGL = numFailed;
        this.aGM = failedItems;
        this.aGN = enabledAccountName;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.Fl;
    }

    public int getAutoBackupState() {
        return this.aGG;
    }

    public String getCurrentItem() {
        return this.aGH;
    }

    public float getCurrentItemProgress() {
        return this.aGI;
    }

    public String getEnabledAccountName() {
        return this.aGN;
    }

    public String[] getFailedItems() {
        return this.aGM;
    }

    public int getNumCompleted() {
        return this.aGJ;
    }

    public int getNumFailed() {
        return this.aGL;
    }

    public int getNumPending() {
        return this.aGK;
    }

    public String toString() {
        return kl.j(this).a("accountName", this.Fl).a("autoBackupState", Integer.valueOf(this.aGG)).a("currentItem", this.aGH).a("currentItemProgress", Float.valueOf(this.aGI)).a("numCompleted", Integer.valueOf(this.aGJ)).a("numPending", Integer.valueOf(this.aGK)).a("numFailed", Integer.valueOf(this.aGL)).a("failedItems", this.aGM).a("enabledAccountName", this.aGN).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        b.a(this, parcel, flags);
    }
}
