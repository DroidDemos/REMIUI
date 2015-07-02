package android.support.v4.a;

import java.util.Iterator;

/* compiled from: MapCollections */
final class j implements Iterator {
    final /* synthetic */ c fd;
    boolean mCanRemove;
    int mIndex;
    final int mOffset;
    int mSize;

    j(c cVar, int i) {
        this.fd = cVar;
        this.mCanRemove = false;
        this.mOffset = i;
        this.mSize = cVar.colGetSize();
    }

    public boolean hasNext() {
        return this.mIndex < this.mSize;
    }

    public Object next() {
        Object colGetEntry = this.fd.colGetEntry(this.mIndex, this.mOffset);
        this.mIndex++;
        this.mCanRemove = true;
        return colGetEntry;
    }

    public void remove() {
        if (this.mCanRemove) {
            this.mIndex--;
            this.mSize--;
            this.mCanRemove = false;
            this.fd.colRemoveAt(this.mIndex);
            return;
        }
        throw new IllegalStateException();
    }
}
