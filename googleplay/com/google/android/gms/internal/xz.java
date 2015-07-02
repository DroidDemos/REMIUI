package com.google.android.gms.internal;

import com.google.android.wallet.instrumentmanager.R;
import java.io.IOException;
import java.lang.reflect.Array;

public class xz<M extends xy<M>, T> {
    protected final Class<T> aYk;
    protected final boolean aYl;
    public final int tag;
    protected final int type;

    int I(Object obj) {
        return this.aYl ? J(obj) : K(obj);
    }

    protected int J(Object obj) {
        int i = 0;
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += K(Array.get(obj, i2));
            }
        }
        return i;
    }

    protected int K(Object obj) {
        int mu = yh.mu(this.tag);
        switch (this.type) {
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return xx.b(mu, (ye) obj);
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                return xx.c(mu, (ye) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }

    void a(Object obj, xx xxVar) throws IOException {
        if (this.aYl) {
            c(obj, xxVar);
        } else {
            b(obj, xxVar);
        }
    }

    protected void b(Object obj, xx xxVar) {
        try {
            xxVar.mm(this.tag);
            switch (this.type) {
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    ye yeVar = (ye) obj;
                    int mu = yh.mu(this.tag);
                    xxVar.b(yeVar);
                    xxVar.G(mu, 4);
                    return;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    xxVar.c((ye) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    protected void c(Object obj, xx xxVar) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                b(obj2, xxVar);
            }
        }
    }
}
