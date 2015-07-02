package com.miui.yellowpage.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.IntentFilter;
import android.widget.ListView;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.activity.BaseActivity;
import com.miui.yellowpage.base.utils.BusinessStatistics.StatsContext;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Network;
import java.util.LinkedList;
import java.util.List;

/* compiled from: BaseFragment */
public class cs extends Fragment {
    private static final String TAG = "BaseFragment";
    protected Activity mActivity;
    protected List mLifecycleCallbacks;
    protected p mLoader;
    protected boolean mNetworkConnected;
    private bP mNetworkConnectivityReceiver;
    protected d mRequestLoader;

    protected StatsContext getStatisticsContext() {
        if (this.mActivity instanceof BaseActivity) {
            return ((BaseActivity) this.mActivity).getStatisticsContext();
        }
        return StatsContext.EMPTY;
    }

    public void addLifecycleCallback(dT dTVar) {
        if (this.mLifecycleCallbacks == null) {
            this.mLifecycleCallbacks = new LinkedList();
        }
        this.mLifecycleCallbacks.add(dTVar);
    }

    public void removeLifecycleCallback(dT dTVar) {
        if (this.mLifecycleCallbacks != null) {
            this.mLifecycleCallbacks.remove(dTVar);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mLifecycleCallbacks != null) {
            for (dT onResume : this.mLifecycleCallbacks) {
                onResume.onResume();
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mLifecycleCallbacks != null) {
            for (dT onPause : this.mLifecycleCallbacks) {
                onPause.onPause();
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
        registerConnectivityReceiver();
        this.mNetworkConnected = Network.isNetWorkConnected(activity);
    }

    public void onDetach() {
        super.onDetach();
        unregisterConnectivityReceiver();
        unregisterLoaderCallBack();
    }

    private void unregisterLoaderCallBack() {
        if (this.mLoader != null) {
            this.mLoader.iH();
        }
        if (this.mRequestLoader != null) {
            this.mRequestLoader.a(null);
            this.mRequestLoader.a(null);
        }
    }

    protected void onNetworkConnected() {
        if (this.mLoader != null) {
            this.mLoader.reload();
        }
    }

    private void registerConnectivityReceiver() {
        Log.d(TAG, "Register network connectivity changed receiver");
        if (this.mNetworkConnectivityReceiver == null) {
            this.mNetworkConnectivityReceiver = new bP();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.mActivity.registerReceiver(this.mNetworkConnectivityReceiver, intentFilter);
    }

    private void unregisterConnectivityReceiver() {
        Log.d(TAG, "Unregister network connectivity changed receiver");
        this.mActivity.unregisterReceiver(this.mNetworkConnectivityReceiver);
    }

    public ListView getListView() {
        return null;
    }

    public boolean onBackPressed() {
        return false;
    }
}
