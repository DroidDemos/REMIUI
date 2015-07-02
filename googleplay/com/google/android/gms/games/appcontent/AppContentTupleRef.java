package com.google.android.gms.games.appcontent;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;

public final class AppContentTupleRef extends c implements AppContentTuple {
    AppContentTupleRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return AppContentTupleEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mS();
    }

    public String getName() {
        return getString("tuple_name");
    }

    public String getValue() {
        return getString("tuple_value");
    }

    public int hashCode() {
        return AppContentTupleEntity.a(this);
    }

    public AppContentTuple mS() {
        return new AppContentTupleEntity(this);
    }

    public String toString() {
        return AppContentTupleEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentTupleEntity) mS()).writeToParcel(dest, flags);
    }
}
