package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class DataTypeCreateRequest implements SafeParcelable {
    public static final Creator<DataTypeCreateRequest> CREATOR;
    private final List<Field> agD;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new i();
    }

    DataTypeCreateRequest(int versionCode, String name, List<Field> fields) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.agD = Collections.unmodifiableList(fields);
    }

    private boolean a(DataTypeCreateRequest dataTypeCreateRequest) {
        return kl.equal(this.mName, dataTypeCreateRequest.mName) && kl.equal(this.agD, dataTypeCreateRequest.agD);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof DataTypeCreateRequest) && a((DataTypeCreateRequest) o));
    }

    public List<Field> getFields() {
        return this.agD;
    }

    public String getName() {
        return this.mName;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.mName, this.agD);
    }

    public String toString() {
        return kl.j(this).a("name", this.mName).a("fields", this.agD).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
