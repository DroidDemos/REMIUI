package com.google.android.finsky.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.widget.consumption.ConsumptionAppDocList;
import com.google.android.finsky.widget.consumption.NowPlayingWidgetProvider;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ConsumptionAppDataCache {
    private static final String CACHE_FILE_PREFIX;
    private static ConsumptionAppDataCache mInstance;
    private SparseArray<List<ConsumptionAppDoc>> mConsumptionAppData;
    private SparseArray<Integer> mDataLoadState;
    private final Handler mHandler;

    public ConsumptionAppDataCache() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mConsumptionAppData = new SparseArray();
        this.mDataLoadState = new SparseArray();
    }

    static {
        CACHE_FILE_PREFIX = ConsumptionAppDataCache.class.getSimpleName();
    }

    public static ConsumptionAppDataCache get() {
        if (mInstance == null) {
            mInstance = new ConsumptionAppDataCache();
        }
        return mInstance;
    }

    public boolean hasConsumptionAppData(int backendId) {
        Utils.ensureOnMainThread();
        return getDataStateForBackend(backendId) == 2;
    }

    public boolean isLoadingData(int backendId) {
        Utils.ensureOnMainThread();
        if (getDataStateForBackend(backendId) == 1) {
            return true;
        }
        return false;
    }

    private int getDataStateForBackend(int backendId) {
        Utils.ensureOnMainThread();
        return this.mDataLoadState.get(backendId) != null ? ((Integer) this.mDataLoadState.get(backendId)).intValue() : 0;
    }

    public ConsumptionAppDocList getConsumptionAppData(int backendId) {
        Utils.ensureOnMainThread();
        ConsumptionAppDocList docList = new ConsumptionAppDocList(backendId);
        if (hasConsumptionAppData(backendId)) {
            docList.addAll((Collection) this.mConsumptionAppData.get(backendId));
        }
        return docList;
    }

    public int getConsumptionAppDataSize(int backendId) {
        Utils.ensureOnMainThread();
        List<ConsumptionAppDoc> docList = (List) this.mConsumptionAppData.get(backendId);
        return docList == null ? 0 : docList.size();
    }

    public void startLoading(int backendId) {
        Utils.ensureOnMainThread();
        if (!hasConsumptionAppData(backendId)) {
            this.mDataLoadState.put(backendId, Integer.valueOf(1));
        }
    }

    public void setConsumptionAppData(final Context context, final int backendId, final List<Bundle> data) {
        filter(data, backendId);
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    ConsumptionAppDataCache.this.setConsumptionAppData(context, backendId, data);
                }
            });
        } else if (data != null) {
            setConsumptionAppData(context, ConsumptionAppDocList.createFromBundles(backendId, data), true);
        }
    }

    public void setConsumptionAppData(final Context context, final ConsumptionAppDocList data, final boolean updateWidgets) {
        int backendId = data.getBackend();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    ConsumptionAppDataCache.this.setConsumptionAppData(context, data, updateWidgets);
                }
            });
            return;
        }
        boolean isDataDifferent = false;
        if (hasConsumptionAppData(backendId)) {
            if (getConsumptionAppData(backendId).equals(data)) {
                isDataDifferent = false;
            } else {
                isDataDifferent = true;
            }
        } else if (data.size() > 0) {
            isDataDifferent = true;
        }
        this.mConsumptionAppData.put(backendId, data);
        this.mDataLoadState.put(backendId, Integer.valueOf(2));
        if (!isDataDifferent) {
            FinskyLog.d("data didn't change for backend=[%s], ignoring!", Integer.valueOf(backendId));
        } else if (updateWidgets) {
            updateWidgets(context, backendId);
        }
    }

    public void updateWidgets(Context context, int backendId) {
        Intent intent = new Intent("com.android.vending.action.APPWIDGET_UPDATE_CONSUMPTION_DATA");
        intent.setClass(context, NowPlayingWidgetProvider.class);
        intent.putExtra("backendId", backendId);
        context.sendBroadcast(intent);
    }

    void filter(List<Bundle> data, int backendId) {
        String filter = (String) G.consumptionAppDataFilter.get();
        if (((Boolean) G.debugOptionsEnabled.get()).booleanValue() && !TextUtils.isEmpty(filter)) {
            String[] filterStrings = null;
            for (String backendFilter : filter.split(";")) {
                String[] tokens = backendFilter.trim().split(":");
                int length = tokens.length;
                if (r0 != 2) {
                    FinskyLog.d("Bad corpus filter expression %s", backendFilter);
                } else {
                    int filterBackendId = Integer.parseInt(tokens[0]);
                    if (filterBackendId == 0 || filterBackendId == backendId) {
                        filterStrings = tokens[1].trim().split(",");
                    }
                    if (filterBackendId == backendId) {
                        break;
                    }
                }
            }
            if (filterStrings == null) {
                data.clear();
                return;
            }
            int numFilterStrings = filterStrings.length;
            if (!filterStrings[numFilterStrings + -1].equals("...")) {
                while (data.size() > numFilterStrings) {
                    data.remove(data.size() - 1);
                }
            } else {
                numFilterStrings--;
            }
            long now = System.currentTimeMillis();
            for (int i = 0; i < data.size(); i++) {
                ((Bundle) data.get(i)).putLong("Play.LastUpdateTimeMillis", (long) (((float) now) - (8.64E7f * Float.parseFloat(filterStrings[Math.min(numFilterStrings - 1, i)]))));
            }
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Filtered data for corpus %d:", Integer.valueOf(backendId));
                Iterator i$ = data.iterator();
                while (i$.hasNext()) {
                    FinskyLog.v("%s", new ConsumptionAppDoc((Bundle) i$.next()).toString());
                }
            }
        }
    }

    public static File getCacheFile(Context context, int backend) {
        File cacheDir = new File(context.getCacheDir(), "consumption");
        cacheDir.mkdirs();
        return new File(cacheDir, CACHE_FILE_PREFIX + "_" + backend + ".cache");
    }
}
