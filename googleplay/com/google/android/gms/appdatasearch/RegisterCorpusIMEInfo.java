package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RegisterCorpusIMEInfo implements SafeParcelable {
    public static final x CREATOR;
    final int mVersionCode;
    public final String[] sectionNames;
    public final int sourceClass;
    public final String toAddressesSectionName;
    public final String userInputSectionName;
    public final String[] userInputSectionValues;
    public final String userInputTag;

    static {
        CREATOR = new x();
    }

    RegisterCorpusIMEInfo(int versionCode, int sourceClass, String[] sectionNames, String userInputTag, String userInputSectionName, String[] userInputSectionValues, String toAddressesSectionName) {
        this.mVersionCode = versionCode;
        this.sourceClass = sourceClass;
        this.sectionNames = sectionNames;
        this.userInputTag = userInputTag;
        this.userInputSectionName = userInputSectionName;
        this.userInputSectionValues = userInputSectionValues;
        this.toAddressesSectionName = toAddressesSectionName;
    }

    public int describeContents() {
        x xVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        x xVar = CREATOR;
        x.a(this, out, flags);
    }
}
