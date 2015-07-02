package com.google.android.gms.common.data;

import com.google.android.gms.internal.kn;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class b<T> implements Iterator<T> {
    protected final DataBuffer<T> TU;
    protected int TV;

    public b(DataBuffer<T> dataBuffer) {
        this.TU = (DataBuffer) kn.k(dataBuffer);
        this.TV = -1;
    }

    public boolean hasNext() {
        return this.TV < this.TU.getCount() + -1;
    }

    public T next() {
        if (hasNext()) {
            DataBuffer dataBuffer = this.TU;
            int i = this.TV + 1;
            this.TV = i;
            return dataBuffer.get(i);
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.TV);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
