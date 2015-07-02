package com.google.android.finsky.activities.myapps;

import android.widget.Adapter;
import com.google.android.finsky.api.model.Document;

public interface MyAppsListAdapter extends Adapter {
    Document getDocument(int i);
}
