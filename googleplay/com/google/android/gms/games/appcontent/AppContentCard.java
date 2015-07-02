package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public interface AppContentCard extends Parcelable, Freezable<AppContentCard> {
    List<AppContentAction> getActions();

    String getDescription();

    Uri getIconImageUri();

    String getTitle();

    String getType();

    Uri mA();

    List<AppContentAnnotation> mC();

    int mD();

    Bundle mE();

    String mF();

    int mG();

    List<AppContentCondition> mw();

    String mx();
}
