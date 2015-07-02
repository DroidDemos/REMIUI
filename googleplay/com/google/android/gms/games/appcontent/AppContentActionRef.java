package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import java.util.List;

public final class AppContentActionRef extends MultiDataBufferRef implements AppContentAction {
    AppContentActionRef(ArrayList<DataHolder> dataHolderCollection, int dataRow) {
        super(dataHolderCollection, 1, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return AppContentActionEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mz();
    }

    public Bundle getExtras() {
        return AppContentUtils.d(this.mDataHolder, this.akc, "action_data", this.TX);
    }

    public String getLabel() {
        return getString("action_label");
    }

    public String getType() {
        return getString("action_type");
    }

    public int hashCode() {
        return AppContentActionEntity.a(this);
    }

    public List<AppContentCondition> mw() {
        return AppContentUtils.c(this.mDataHolder, this.akc, "action_conditions", this.TX);
    }

    public String mx() {
        return getString("action_content_description");
    }

    public String my() {
        return getString("action_label_style");
    }

    public AppContentAction mz() {
        return new AppContentActionEntity(this);
    }

    public String toString() {
        return AppContentActionEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentActionEntity) mz()).writeToParcel(dest, flags);
    }
}
