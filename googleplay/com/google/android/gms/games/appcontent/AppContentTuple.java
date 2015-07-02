package com.google.android.gms.games.appcontent;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface AppContentTuple extends Parcelable, Freezable<AppContentTuple> {
    String getName();

    String getValue();
}
