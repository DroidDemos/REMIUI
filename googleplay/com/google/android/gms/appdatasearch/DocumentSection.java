package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.appdatasearch.RegisterSectionInfo.Builder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public class DocumentSection implements SafeParcelable {
    public static final g CREATOR;
    private static final RegisterSectionInfo CZ;
    public static final int INVALID_GLOBAL_SEARCH_SECTION_ID;
    final RegisterSectionInfo Da;
    public final byte[] blobContent;
    public final String content;
    public final int globalSearchSectionType;
    final int mVersionCode;

    static {
        INVALID_GLOBAL_SEARCH_SECTION_ID = Integer.parseInt("-1");
        CREATOR = new g();
        CZ = new Builder("SsbContext").noIndex(true).format("blob").build();
    }

    DocumentSection(int versionCode, String content, RegisterSectionInfo sectionInfo, int globalSearchSectionType, byte[] blobContent) {
        boolean z = globalSearchSectionType == INVALID_GLOBAL_SEARCH_SECTION_ID || GlobalSearchSections.getSectionName(globalSearchSectionType) != null;
        kn.b(z, "Invalid section type " + globalSearchSectionType);
        this.mVersionCode = versionCode;
        this.content = content;
        this.Da = sectionInfo;
        this.globalSearchSectionType = globalSearchSectionType;
        this.blobContent = blobContent;
        String validate = validate();
        if (validate != null) {
            throw new IllegalArgumentException(validate);
        }
    }

    public int describeContents() {
        g gVar = CREATOR;
        return 0;
    }

    public String validate() {
        return (this.globalSearchSectionType == INVALID_GLOBAL_SEARCH_SECTION_ID || GlobalSearchSections.getSectionName(this.globalSearchSectionType) != null) ? (this.content == null || this.blobContent == null) ? null : "Both content and blobContent set" : "Invalid section type " + this.globalSearchSectionType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        g gVar = CREATOR;
        g.a(this, dest, flags);
    }
}
