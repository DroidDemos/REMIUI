package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.a;

public class l extends a<String> {
    public l(String str, int i) {
        super(str, i);
    }

    protected /* synthetic */ Object k(Bundle bundle) {
        return r(bundle);
    }

    protected String r(Bundle bundle) {
        return bundle.getString(getName());
    }
}
