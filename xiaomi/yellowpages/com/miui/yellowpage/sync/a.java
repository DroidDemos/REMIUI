package com.miui.yellowpage.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.content.SyncStats;
import android.os.Bundle;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.exception.YellowPageRequestException;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Network;
import com.miui.yellowpage.sync.action.UpdateAction;
import com.miui.yellowpage.utils.u;
import java.io.IOException;
import org.json.JSONException;

/* compiled from: SyncAdapter */
public class a extends AbstractThreadedSyncAdapter {
    public a(Context context, boolean z) {
        super(context, z);
    }

    public void onPerformSync(Account account, Bundle bundle, String str, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        SyncStats syncStats;
        long j = bundle.getLong("delay", 0);
        boolean z = bundle.getBoolean("immortal", false);
        boolean z2 = bundle.getBoolean("triggered_by_push", false);
        long j2 = bundle.getLong("timestamp", 0);
        Log.d("SyncAdapter", "onPerformSync:" + j2 + ", bundle:" + bundle);
        if (!z2) {
            Log.d("SyncAdapter", "automatically triggered, ignore");
        } else if (!u.P(getContext())) {
        } else {
            if (j + j2 > System.currentTimeMillis()) {
                Log.d("SyncAdapter", "wait");
                return;
            }
            UpdateAction i = UpdateAction.i(bundle.getString(MiniDefine.at));
            Log.v("SyncAdapter", "update action:" + i);
            if (i != null) {
                try {
                    if (!i.isWifiOnly() || Network.isWifiConnected(getContext())) {
                        com.miui.yellowpage.sync.a.a aVar = (com.miui.yellowpage.sync.a.a) i.cJ().aD().newInstance();
                        Log.v("SyncAdapter", "task:" + aVar);
                        aVar.a(getContext(), i);
                        if (!z && !syncResult.hasError()) {
                            Log.d("SyncAdapter", "remove periodic sync");
                            ContentResolver.removePeriodicSync(account, str, bundle);
                            return;
                        }
                        return;
                    }
                    throw new IOException("data connection maybe metered");
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    syncStats = syncResult.stats;
                    syncStats.numIoExceptions++;
                    if (!z && !syncResult.hasError()) {
                        Log.d("SyncAdapter", "remove periodic sync");
                        ContentResolver.removePeriodicSync(account, str, bundle);
                    }
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    syncStats = syncResult.stats;
                    syncStats.numIoExceptions++;
                    if (!z && !syncResult.hasError()) {
                        Log.d("SyncAdapter", "remove periodic sync");
                        ContentResolver.removePeriodicSync(account, str, bundle);
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    syncStats = syncResult.stats;
                    syncStats.numParseExceptions++;
                    if (!z && !syncResult.hasError()) {
                        Log.d("SyncAdapter", "remove periodic sync");
                        ContentResolver.removePeriodicSync(account, str, bundle);
                    }
                } catch (IllegalStateException e4) {
                    e4.printStackTrace();
                    syncStats = syncResult.stats;
                    syncStats.numIoExceptions++;
                    if (!z && !syncResult.hasError()) {
                        Log.d("SyncAdapter", "remove periodic sync");
                        ContentResolver.removePeriodicSync(account, str, bundle);
                    }
                } catch (YellowPageRequestException e5) {
                    e5.printStackTrace();
                    syncStats = syncResult.stats;
                    syncStats.numIoExceptions++;
                    if (!z && !syncResult.hasError()) {
                        Log.d("SyncAdapter", "remove periodic sync");
                        ContentResolver.removePeriodicSync(account, str, bundle);
                    }
                } catch (IOException e6) {
                    e6.printStackTrace();
                    syncStats = syncResult.stats;
                    syncStats.numIoExceptions++;
                    if (!z && !syncResult.hasError()) {
                        Log.d("SyncAdapter", "remove periodic sync");
                        ContentResolver.removePeriodicSync(account, str, bundle);
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                    if (!z && !syncResult.hasError()) {
                        Log.d("SyncAdapter", "remove periodic sync");
                        ContentResolver.removePeriodicSync(account, str, bundle);
                    }
                } catch (Throwable th) {
                    if (!(z || syncResult.hasError())) {
                        Log.d("SyncAdapter", "remove periodic sync");
                        ContentResolver.removePeriodicSync(account, str, bundle);
                    }
                }
            }
        }
    }
}
