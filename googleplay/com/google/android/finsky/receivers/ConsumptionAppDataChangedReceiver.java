package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.services.LoadConsumptionDataService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.widget.WidgetTypeMap;
import com.google.android.finsky.widget.WidgetUtils;
import com.google.android.finsky.widget.consumption.NowPlayingWidgetProvider;

public class ConsumptionAppDataChangedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            FinskyLog.d("Consumption app update contained no extras.", new Object[0]);
        } else if (!extras.containsKey("Play.BackendId")) {
            FinskyLog.d("Consumption app did not specify backend id, ignoring!", new Object[0]);
        } else if (extras.containsKey("Play.DataType")) {
            int backendId = extras.getInt("Play.BackendId");
            int dataType = extras.getInt("Play.DataType");
            if (WidgetTypeMap.get(context).getWidgets(NowPlayingWidgetProvider.class, WidgetUtils.translate(backendId)).length == 0) {
                if (FinskyLog.DEBUG) {
                    FinskyLog.v("Ignoring update because no widgets are listening to [%s].", Integer.valueOf(backendId));
                }
            } else if (LoadConsumptionDataService.isBackendSupported(backendId) && LoadConsumptionDataService.isSupportedDataType(dataType)) {
                LoadConsumptionDataService.scheduleAlarmForUpdate(context, backendId);
            } else {
                FinskyLog.d("Either backendId=[%d] or dataType=[%d] is not supported.", Integer.valueOf(backendId), Integer.valueOf(dataType));
            }
        } else {
            FinskyLog.d("Consumption app did not specify which list type was updated, ignoring!", new Object[0]);
        }
    }
}
