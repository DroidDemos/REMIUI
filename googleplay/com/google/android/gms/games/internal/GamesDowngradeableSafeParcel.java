package com.google.android.gms.games.internal;

import com.google.android.gms.common.util.h;
import com.google.android.gms.internal.jy;

public abstract class GamesDowngradeableSafeParcel extends jy {
    protected static boolean c(Integer num) {
        return num == null ? false : h.dC(num.intValue());
    }
}
