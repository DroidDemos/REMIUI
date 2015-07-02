package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.a;

public class f extends a<Integer> {
    public f(String str, int i) {
        super(str, i);
    }

    protected /* synthetic */ Object k(Bundle bundle) {
        return n(bundle);
    }

    protected Integer n(Bundle bundle) {
        return Integer.valueOf(bundle.getInt(getName()));
    }
}
