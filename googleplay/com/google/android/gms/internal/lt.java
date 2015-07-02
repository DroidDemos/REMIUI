package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.b;
import com.google.android.gms.drive.metadata.internal.f;

public class lt {
    public static final MetadataField<Integer> ace;
    public static final MetadataField<Boolean> acf;

    static {
        ace = new f("contentAvailability", 4300000);
        acf = new b("isPinnable", 4300000);
    }
}
