package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import java.util.List;

public final class AppContentCardRef extends MultiDataBufferRef implements AppContentCard {
    AppContentCardRef(ArrayList<DataHolder> dataHolderCollection, int dataRow) {
        super(dataHolderCollection, 0, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return AppContentCardEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mH();
    }

    public List<AppContentAction> getActions() {
        return AppContentUtils.a(this.mDataHolder, this.akc, "card_actions", this.TX);
    }

    public String getDescription() {
        return getString("card_description");
    }

    public Uri getIconImageUri() {
        return ba("card_icon_image_uri");
    }

    public String getTitle() {
        return getString("card_title");
    }

    public String getType() {
        return getString("card_type");
    }

    public int hashCode() {
        return AppContentCardEntity.a(this);
    }

    public Uri mA() {
        return ba("card_image_uri");
    }

    public List<AppContentAnnotation> mC() {
        return AppContentUtils.b(this.mDataHolder, this.akc, "card_annotations", this.TX);
    }

    public int mD() {
        return getInteger("card_current_steps");
    }

    public Bundle mE() {
        return AppContentUtils.d(this.mDataHolder, this.akc, "card_data", this.TX);
    }

    public String mF() {
        return getString("card_subtitle");
    }

    public int mG() {
        return getInteger("card_total_steps");
    }

    public AppContentCard mH() {
        return new AppContentCardEntity(this);
    }

    public List<AppContentCondition> mw() {
        return AppContentUtils.c(this.mDataHolder, this.akc, "card_conditions", this.TX);
    }

    public String mx() {
        return getString("card_content_description");
    }

    public String toString() {
        return AppContentCardEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentCardEntity) mH()).writeToParcel(dest, flags);
    }
}
