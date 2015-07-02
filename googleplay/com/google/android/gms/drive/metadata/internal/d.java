package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import java.util.Date;

public class d extends com.google.android.gms.drive.metadata.d<Date> {
    public d(String str, int i) {
        super(str, i);
    }

    protected /* synthetic */ Object k(Bundle bundle) {
        return m(bundle);
    }

    protected Date m(Bundle bundle) {
        return new Date(bundle.getLong(getName()));
    }
}
