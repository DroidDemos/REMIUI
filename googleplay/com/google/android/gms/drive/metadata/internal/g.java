package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.a;

public class g extends a<Long> {
    public g(String str, int i) {
        super(str, i);
    }

    protected /* synthetic */ Object k(Bundle bundle) {
        return o(bundle);
    }

    protected Long o(Bundle bundle) {
        return Long.valueOf(bundle.getLong(getName()));
    }
}
