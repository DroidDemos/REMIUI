package com.google.android.finsky.activities;

import android.view.View;
import com.google.android.finsky.utils.ObjectMap;

public interface ViewPagerTab {
    View getView(int i);

    void onDestroy();

    void onRestoreInstanceState(ObjectMap objectMap);

    ObjectMap onSaveInstanceState();

    void setTabSelected(boolean z);
}
