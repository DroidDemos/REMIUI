package com.google.common.hash;

import com.google.common.b.a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: AbstractStreamingHashFunction */
public abstract class l extends k {
    private final ByteBuffer HQ;
    private final int bufferSize;
    private final int chunkSize;

    abstract HashCode aJ();

    protected abstract void d(ByteBuffer byteBuffer);

    protected l(int i) {
        this(i, i);
    }

    protected l(int i, int i2) {
        a.checkArgument(i2 % i == 0);
        this.HQ = ByteBuffer.allocate(i2 + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.bufferSize = i2;
        this.chunkSize = i;
    }

    protected void e(ByteBuffer byteBuffer) {
        byteBuffer.position(byteBuffer.limit());
        byteBuffer.limit(this.chunkSize + 7);
        while (byteBuffer.position() < this.chunkSize) {
            byteBuffer.putLong(0);
        }
        byteBuffer.limit(this.chunkSize);
        byteBuffer.flip();
        d(byteBuffer);
    }

    public final m k(byte[] bArr) {
        return i(bArr, 0, bArr.length);
    }

    public final m i(byte[] bArr, int i, int i2) {
        return h(ByteBuffer.wrap(bArr, i, i2).order(ByteOrder.LITTLE_ENDIAN));
    }

    private m h(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= this.HQ.remaining()) {
            this.HQ.put(byteBuffer);
            hQ();
        } else {
            int position = this.bufferSize - this.HQ.position();
            for (int i = 0; i < position; i++) {
                this.HQ.put(byteBuffer.get());
            }
            hR();
            while (byteBuffer.remaining() >= this.chunkSize) {
                d(byteBuffer);
            }
            this.HQ.put(byteBuffer);
        }
        return this;
    }

    public final m b(Object obj, Funnel funnel) {
        funnel.a(obj, this);
        return this;
    }

    public final HashCode hk() {
        hR();
        this.HQ.flip();
        if (this.HQ.remaining() > 0) {
            e(this.HQ);
        }
        return aJ();
    }

    private void hQ() {
        if (this.HQ.remaining() < 8) {
            hR();
        }
    }

    private void hR() {
        this.HQ.flip();
        while (this.HQ.remaining() >= this.chunkSize) {
            d(this.HQ);
        }
        this.HQ.compact();
    }
}
