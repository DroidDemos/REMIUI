package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.rf.a;

public class ra extends a {
    public void a(int i, Bundle bundle, Bundle bundle2) {
    }

    public final void a(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) {
        throw new RuntimeException("Shouldn't be called");
    }

    public void a(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle2) {
    }

    public void a(int i, Bundle bundle, DataHolder dataHolder) {
    }

    public void a(int i, Bundle bundle, DataHolder[] dataHolderArr) {
    }
}
