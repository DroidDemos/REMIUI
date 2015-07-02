package com.google.android.gms.car;

import android.util.Log;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Iterator;
import java.util.LinkedList;

public class CarAudioManager {
    private final CarAudioTrack[] JI;
    private final Object JJ;
    private final LinkedList<CarAudioRecord> JK;
    private volatile boolean yZ;

    private int bU(int i) {
        switch (i) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return 3;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 2;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 0;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return 1;
            default:
                throw new IllegalArgumentException("Unknown stream type " + i);
        }
    }

    void a(CarAudioRecord carAudioRecord) {
        if (carAudioRecord.getStreamType() != 0) {
            throw new RuntimeException("wrong stream type " + carAudioRecord.getStreamType());
        }
        synchronized (this.JJ) {
            this.JK.remove(carAudioRecord);
        }
    }

    void bT(int i) {
        synchronized (this.JI) {
            int bU = bU(i);
            if (this.JI[bU] != null) {
                this.JI[bU] = null;
            }
        }
    }

    void handleCarDisconnection() {
        int i = 0;
        if (CarLog.isLoggable("CAR.AUDIO", 3)) {
            Log.d("CAR.AUDIO", "handleCarDisconnection");
        }
        this.yZ = false;
        synchronized (this.JI) {
            while (i < this.JI.length) {
                if (this.JI[i] != null) {
                    this.JI[i].release();
                    this.JI[i] = null;
                }
                i++;
            }
        }
        synchronized (this.JJ) {
            Iterator it = this.JK.iterator();
            while (it.hasNext()) {
                ((CarAudioRecord) it.next()).G(false);
            }
            this.JK.clear();
        }
    }
}
