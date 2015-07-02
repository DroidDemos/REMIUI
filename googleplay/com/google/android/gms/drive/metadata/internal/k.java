package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.b;
import java.util.Collection;
import java.util.Collections;

public class k extends b<String> {
    public k(String str, int i) {
        super(str, Collections.singleton(str), Collections.emptySet(), i);
    }

    protected /* synthetic */ Object k(Bundle bundle) {
        return p(bundle);
    }

    protected Collection<String> p(Bundle bundle) {
        return bundle.getStringArrayList(getName());
    }
}
