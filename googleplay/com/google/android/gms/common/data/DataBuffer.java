package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;
import java.util.Iterator;

public abstract class DataBuffer<T> implements Releasable, Iterable<T> {
    protected final DataHolder mDataHolder;

    protected DataBuffer(DataHolder dataHolder) {
        this.mDataHolder = dataHolder;
        if (this.mDataHolder != null) {
            this.mDataHolder.g(this);
        }
    }

    @Deprecated
    public final void close() {
        release();
    }

    public abstract T get(int i);

    public int getCount() {
        return this.mDataHolder == null ? 0 : this.mDataHolder.getCount();
    }

    public Bundle getMetadata() {
        return this.mDataHolder.getMetadata();
    }

    public Iterator<T> iterator() {
        return new b(this);
    }

    public void release() {
        if (this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
}
