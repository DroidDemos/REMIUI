package com.google.android.gms.location.copresence;

import com.google.android.gms.internal.kn;
import com.google.android.gms.internal.on;

public abstract class Strategy {
    @Deprecated
    private static Strategy auu;
    @Deprecated
    private static Strategy auv;
    @Deprecated
    private static Strategy auw;

    public static class Builder {
        private boolean auA;
        private boolean auB;
        private boolean aux;
        private boolean auy;
        private int auz;

        public Builder() {
            this.auz = 1;
        }

        public Strategy build() {
            return new on(!this.aux, this.auy, this.auz, this.auA, this.auB);
        }

        public Builder setLowPower() {
            this.aux = true;
            return this;
        }

        public Builder setWakeUpOthers() {
            kn.a(!this.auB, "Cannot call setNoOptInRequired() in conjunction with setWakeUpOthers().");
            this.auy = true;
            return this;
        }
    }

    static {
        auu = new Builder().setWakeUpOthers().build();
        auv = new Builder().build();
        auw = new Builder().setLowPower().build();
    }
}
