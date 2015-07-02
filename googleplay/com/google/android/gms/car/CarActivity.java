package com.google.android.gms.car;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;
import android.view.Window;

public class CarActivity implements Factory {
    public static SparseArray<String> sStateNameMap;
    private LayoutInflater IX;
    private Window IY;
    private Context mContext;
    private boolean nF;

    static {
        sStateNameMap = new SparseArray(6);
        sStateNameMap.put(0, "STATE_INITIALIZING");
        sStateNameMap.put(1, "STATE_CREATED");
        sStateNameMap.put(2, "STATE_STOPPED");
        sStateNameMap.put(3, "STATE_STARTED");
        sStateNameMap.put(4, "STATE_PAUSED");
        sStateNameMap.put(5, "STATE_RESUMED");
        sStateNameMap.put(6, "STATE_FINISHED");
    }

    public ClassLoader getClassLoader() {
        return this.mContext.getClassLoader();
    }

    public Context getContext() {
        if (CarLog.isLoggable("CAR.PROJECTION", 3)) {
            Log.d("CAR.PROJECTION", "Context DPI: " + this.mContext.getResources().getConfiguration().densityDpi);
        }
        return this.mContext;
    }

    public LayoutInflater getLayoutInflater() {
        return this.IX;
    }

    public Resources getResources() {
        if (this.mContext != null) {
            return this.mContext.getResources();
        }
        throw new IllegalStateException("CarProjectionActivity not initialized with attach()");
    }

    public Window getWindow() {
        return this.IY;
    }

    public boolean isFinishing() {
        return this.nF;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }
}
