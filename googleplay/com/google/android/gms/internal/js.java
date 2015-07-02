package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;

public final class js extends lc<a, Drawable> {

    public static final class a {
        public final int Vx;
        public final int Vy;

        public a(int i, int i2) {
            this.Vx = i;
            this.Vy = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            return aVar.Vx == this.Vx && aVar.Vy == this.Vy;
        }

        public int hashCode() {
            return kl.hashCode(Integer.valueOf(this.Vx), Integer.valueOf(this.Vy));
        }
    }
}
