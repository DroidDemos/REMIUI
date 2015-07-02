package com.google.android.finsky.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelUtils;
import com.google.android.finsky.widget.consumption.ConsumptionAppDocList;
import com.google.android.wallet.instrumentmanager.R;
import java.io.File;

public class LoadConsumptionDataService extends IntentService {
    public LoadConsumptionDataService() {
        super(LoadConsumptionDataService.class.getSimpleName());
    }

    public static boolean isBackendSupported(int backendId) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSupportedDataType(int dataType) {
        switch (dataType) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return true;
            default:
                return false;
        }
    }

    public static void scheduleAlarmForUpdate(Context context, int... backendIds) {
        Intent i = new Intent(context, LoadConsumptionDataService.class);
        i.setAction(String.valueOf(backendIds.hashCode()));
        i.putExtra("backendIds", backendIds);
        context.startService(i);
    }

    protected void onHandleIntent(Intent intent) {
        ConsumptionAppDataCache cache = ConsumptionAppDataCache.get();
        int[] backendIds = intent.getIntArrayExtra("backendIds");
        int[] backendIdsToUpdate = new int[backendIds.length];
        int[] arr$ = backendIds;
        int len$ = arr$.length;
        int i$ = 0;
        int numBackendsToUpdate = 0;
        while (i$ < len$) {
            int numBackendsToUpdate2;
            int backendId = arr$[i$];
            ConsumptionAppDocList cachedData = readDataFromCache(this, backendId);
            if (cachedData.isEmpty()) {
                numBackendsToUpdate2 = numBackendsToUpdate;
            } else {
                numBackendsToUpdate2 = numBackendsToUpdate + 1;
                backendIdsToUpdate[numBackendsToUpdate] = backendId;
                cache.setConsumptionAppData((Context) this, cachedData, false);
                FinskyLog.d("Was able to read from cache for %d", Integer.valueOf(backendId));
            }
            FetchConsumptionDataService.fetch(this, backendId);
            i$++;
            numBackendsToUpdate = numBackendsToUpdate2;
        }
        for (int i = 0; i < numBackendsToUpdate; i++) {
            cache.updateWidgets(this, backendIdsToUpdate[i]);
        }
    }

    private ConsumptionAppDocList readDataFromCache(Context context, int backend) {
        ConsumptionAppDocList docList = new ConsumptionAppDocList(backend);
        File cacheFile = ConsumptionAppDataCache.getCacheFile(context, backend);
        if (cacheFile.exists()) {
            ConsumptionAppDocList cachedList = (ConsumptionAppDocList) ParcelUtils.readFromDisk(cacheFile);
            if (cachedList != null) {
                docList = cachedList;
            }
            return docList;
        }
        FinskyLog.d("%s doesn't exist", cacheFile.getAbsolutePath());
        return docList;
    }
}
