package com.google.android.gms.car;

import android.os.Handler;
import android.util.Log;
import com.google.android.gms.car.ba.a;
import java.util.HashMap;

public class CarSensorManager {
    private b LK;
    private final HashMap<Integer, Object> LL;
    private final HashMap<Integer, Object> LM;
    private final Handler mHandler;

    private class b extends a {
        final /* synthetic */ CarSensorManager LN;

        public void onSensorChanged(CarSensorEvent event) {
            this.LN.mHandler.sendMessage(this.LN.mHandler.obtainMessage(0, event));
        }
    }

    void handleCarDisconnection() {
        if (CarLog.isLoggable("CAR.SENSOR", 3)) {
            Log.d("CAR.SENSOR", "handleCarDisconnection");
        }
        synchronized (this.LL) {
            this.LL.clear();
            this.LK = null;
        }
        synchronized (this.LM) {
            this.LM.clear();
        }
    }
}
