package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.w;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class MetadataBundle implements SafeParcelable {
    public static final Creator<MetadataBundle> CREATOR;
    final Bundle abq;
    final int mVersionCode;

    static {
        CREATOR = new h();
    }

    MetadataBundle(int versionCode, Bundle valueBundle) {
        this.mVersionCode = versionCode;
        this.abq = (Bundle) kn.k(valueBundle);
        this.abq.setClassLoader(getClass().getClassLoader());
        List<String> arrayList = new ArrayList();
        for (String str : this.abq.keySet()) {
            if (e.bw(str) == null) {
                arrayList.add(str);
                w.w("MetadataBundle", "Ignored unknown metadata field in bundle: " + str);
            }
        }
        for (String str2 : arrayList) {
            this.abq.remove(str2);
        }
    }

    public <T> T a(MetadataField<T> metadataField) {
        return metadataField.j(this.abq);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetadataBundle)) {
            return false;
        }
        MetadataBundle metadataBundle = (MetadataBundle) obj;
        Set<String> keySet = this.abq.keySet();
        if (!keySet.equals(metadataBundle.abq.keySet())) {
            return false;
        }
        for (String str : keySet) {
            if (!kl.equal(this.abq.get(str), metadataBundle.abq.get(str))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (String str : this.abq.keySet()) {
            i *= 31;
            i = this.abq.get(str).hashCode() + i;
        }
        return i;
    }

    public Set<MetadataField<?>> km() {
        Set<MetadataField<?>> hashSet = new HashSet();
        for (String bw : this.abq.keySet()) {
            hashSet.add(e.bw(bw));
        }
        return hashSet;
    }

    public String toString() {
        return "MetadataBundle [values=" + this.abq + "]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
