package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import java.util.Date;

public class lr {
    public static final a abY;
    public static final b abZ;
    public static final d aca;
    public static final c acb;
    public static final e acc;

    public static class a extends com.google.android.gms.drive.metadata.internal.d implements SortableMetadataField<Date> {
        public a(String str, int i) {
            super(str, i);
        }
    }

    public static class b extends com.google.android.gms.drive.metadata.internal.d implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date> {
        public b(String str, int i) {
            super(str, i);
        }
    }

    public static class c extends com.google.android.gms.drive.metadata.internal.d implements SortableMetadataField<Date> {
        public c(String str, int i) {
            super(str, i);
        }
    }

    public static class d extends com.google.android.gms.drive.metadata.internal.d implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date> {
        public d(String str, int i) {
            super(str, i);
        }
    }

    public static class e extends com.google.android.gms.drive.metadata.internal.d implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date> {
        public e(String str, int i) {
            super(str, i);
        }
    }

    static {
        abY = new a("created", 4100000);
        abZ = new b("lastOpenedTime", 4300000);
        aca = new d("modified", 4100000);
        acb = new c("modifiedByMe", 4100000);
        acc = new e("sharedWithMe", 4100000);
    }
}
