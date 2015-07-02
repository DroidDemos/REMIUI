package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

public final class AppContentSectionRef extends MultiDataBufferRef implements AppContentSection {
    private final int akf;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return AppContentSectionEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mP();
    }

    public /* synthetic */ List getActions() {
        return mQ();
    }

    public String getTitle() {
        return getString("section_title");
    }

    public String getType() {
        return getString("section_type");
    }

    public int hashCode() {
        return AppContentSectionEntity.a(this);
    }

    public Bundle mE() {
        return AppContentUtils.d(this.mDataHolder, this.akc, "section_data", this.TX);
    }

    public String mF() {
        return getString("section_subtitle");
    }

    public Uri mN() {
        return ba("section_background_image_uri");
    }

    public /* synthetic */ List mO() {
        return mR();
    }

    public AppContentSection mP() {
        return new AppContentSectionEntity(this);
    }

    public ArrayList<AppContentAction> mQ() {
        return AppContentUtils.a(this.mDataHolder, this.akc, "section_actions", this.TX);
    }

    public ArrayList<AppContentCard> mR() {
        ArrayList<AppContentCard> arrayList = new ArrayList(this.akf);
        for (int i = 0; i < this.akf; i++) {
            arrayList.add(new AppContentCardRef(this.akc, this.TX + i));
        }
        return arrayList;
    }

    public String mx() {
        return getString("section_content_description");
    }

    public String toString() {
        return AppContentSectionEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentSectionEntity) mP()).writeToParcel(dest, flags);
    }
}
