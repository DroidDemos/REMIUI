package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.a;

public class b extends a<Boolean> {
    public b(String str, int i) {
        super(str, i);
    }

    protected /* synthetic */ Object k(Bundle bundle) {
        return l(bundle);
    }

    protected Boolean l(Bundle bundle) {
        return Boolean.valueOf(bundle.getBoolean(getName()));
    }
}
