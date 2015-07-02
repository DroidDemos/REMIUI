package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

public final class AppContentAnnotationRef extends MultiDataBufferRef implements AppContentAnnotation {
    AppContentAnnotationRef(ArrayList<DataHolder> dataHolders, int dataRow) {
        super(dataHolders, 2, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return AppContentAnnotationEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mB();
    }

    public String getDescription() {
        return getString("annotation_description");
    }

    public String getTitle() {
        return getString("annotation_title");
    }

    public String getType() {
        return getString("annotation_type");
    }

    public int hashCode() {
        return AppContentAnnotationEntity.a(this);
    }

    public Uri mA() {
        return ba("annotation_image_uri");
    }

    public AppContentAnnotation mB() {
        return new AppContentAnnotationEntity(this);
    }

    public String toString() {
        return AppContentAnnotationEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentAnnotationEntity) mB()).writeToParcel(dest, flags);
    }
}
