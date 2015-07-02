package com.google.android.finsky.layout;

import android.widget.BaseAdapter;

public abstract class PermissionAdapter extends BaseAdapter {
    public abstract boolean isAppInstalled();

    public abstract boolean showTheNoPermissionMessage();
}
