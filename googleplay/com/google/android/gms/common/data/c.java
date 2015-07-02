package com.google.android.gms.common.data;

import android.net.Uri;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;

public abstract class c {
    protected int TX;
    private int TY;
    protected final DataHolder mDataHolder;

    public c(DataHolder dataHolder, int i) {
        this.mDataHolder = (DataHolder) kn.k(dataHolder);
        cV(i);
    }

    public boolean aZ(String str) {
        return this.mDataHolder.aZ(str);
    }

    protected Uri ba(String str) {
        return this.mDataHolder.h(str, this.TX, this.TY);
    }

    protected boolean bb(String str) {
        return this.mDataHolder.i(str, this.TX, this.TY);
    }

    protected void cV(int i) {
        boolean z = i >= 0 && i < this.mDataHolder.getCount();
        kn.K(z);
        this.TX = i;
        this.TY = this.mDataHolder.cW(this.TX);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return kl.equal(Integer.valueOf(cVar.TX), Integer.valueOf(this.TX)) && kl.equal(Integer.valueOf(cVar.TY), Integer.valueOf(this.TY)) && cVar.mDataHolder == this.mDataHolder;
    }

    protected boolean getBoolean(String column) {
        return this.mDataHolder.d(column, this.TX, this.TY);
    }

    protected byte[] getByteArray(String column) {
        return this.mDataHolder.g(column, this.TX, this.TY);
    }

    protected double getDouble(String column) {
        return this.mDataHolder.f(column, this.TX, this.TY);
    }

    protected float getFloat(String column) {
        return this.mDataHolder.e(column, this.TX, this.TY);
    }

    protected int getInteger(String column) {
        return this.mDataHolder.b(column, this.TX, this.TY);
    }

    protected long getLong(String column) {
        return this.mDataHolder.a(column, this.TX, this.TY);
    }

    protected String getString(String column) {
        return this.mDataHolder.c(column, this.TX, this.TY);
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.TX), Integer.valueOf(this.TY), this.mDataHolder);
    }

    protected int iq() {
        return this.TX;
    }
}
