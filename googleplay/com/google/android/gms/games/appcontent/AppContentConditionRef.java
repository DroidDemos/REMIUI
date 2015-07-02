package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

public final class AppContentConditionRef extends MultiDataBufferRef implements AppContentCondition {
    AppContentConditionRef(ArrayList<DataHolder> dataHolderCollection, int dataRow) {
        super(dataHolderCollection, 4, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return AppContentConditionEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mM();
    }

    public int hashCode() {
        return AppContentConditionEntity.a(this);
    }

    public String mI() {
        return getString("condition_default_value");
    }

    public String mJ() {
        return getString("condition_expected_value");
    }

    public String mK() {
        return getString("condition_predicate");
    }

    public Bundle mL() {
        return AppContentUtils.d(this.mDataHolder, this.akc, "condition_predicate_parameters", this.TX);
    }

    public AppContentCondition mM() {
        return new AppContentConditionEntity(this);
    }

    public String toString() {
        return AppContentConditionEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentConditionEntity) mM()).writeToParcel(dest, flags);
    }
}
