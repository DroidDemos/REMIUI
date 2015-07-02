package android.support.v4.c;

import android.os.Build.VERSION;
import android.os.Parcelable.Creator;

/* compiled from: ParcelableCompat */
public class c {
    public static Creator b(d dVar) {
        if (VERSION.SDK_INT >= 13) {
            a.a(dVar);
        }
        return new e(dVar);
    }
}
