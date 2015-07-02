package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.internal.kn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class AppVisibleCustomProperties implements SafeParcelable, Iterable<CustomProperty> {
    public static final Creator<AppVisibleCustomProperties> CREATOR;
    public static final AppVisibleCustomProperties abl;
    final List<CustomProperty> abm;
    final int mVersionCode;

    public static class a {
        private final Map<CustomPropertyKey, CustomProperty> abn;

        public a() {
            this.abn = new HashMap();
        }

        public AppVisibleCustomProperties ki() {
            return new AppVisibleCustomProperties(this.abn.values());
        }
    }

    static {
        CREATOR = new a();
        abl = new a().ki();
    }

    AppVisibleCustomProperties(int versionCode, Collection<CustomProperty> properties) {
        this.mVersionCode = versionCode;
        kn.k(properties);
        this.abm = new ArrayList(properties);
    }

    private AppVisibleCustomProperties(Collection<CustomProperty> properties) {
        this(1, (Collection) properties);
    }

    public int describeContents() {
        return 0;
    }

    public Iterator<CustomProperty> iterator() {
        return this.abm.iterator();
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
