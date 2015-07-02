package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.b;
import java.util.Collection;
import java.util.Collections;

public class i<T extends Parcelable> extends b<T> {
    public i(String str, int i) {
        super(str, Collections.emptySet(), Collections.singleton(str), i);
    }

    protected /* synthetic */ Object k(Bundle bundle) {
        return p(bundle);
    }

    protected Collection<T> p(Bundle bundle) {
        return bundle.getParcelableArrayList(getName());
    }
}
