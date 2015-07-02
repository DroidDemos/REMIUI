package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    public Object getValueObject(String key) {
        return null;
    }

    public boolean isPrimitiveFieldSet(String outputField) {
        return false;
    }
}
