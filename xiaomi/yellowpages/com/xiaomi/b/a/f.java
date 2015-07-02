package com.xiaomi.b.a;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

final class f {
    private static final Logger nR;
    private final z nS;
    private final Lock nT;
    private final Condition nU;
    private b nV;

    static {
        nR = Logger.getLogger(f.class.getName());
    }

    f(z zVar) {
        this.nT = new ReentrantLock();
        this.nU = this.nT.newCondition();
        if (zVar == null) {
            throw new IllegalArgumentException("Request body cannot be null");
        }
        this.nS = zVar;
    }

    void a(b bVar) {
        this.nT.lock();
        try {
            if (this.nV != null) {
                throw new IllegalStateException("HTTPResponse was already set");
            }
            this.nV = bVar;
            this.nU.signalAll();
        } finally {
            this.nT.unlock();
        }
    }

    z cf() {
        return this.nS;
    }

    b cg() {
        this.nT.lock();
        while (this.nV == null) {
            try {
                this.nU.await();
            } catch (Throwable e) {
                nR.log(Level.FINEST, "Interrupted", e);
            } catch (Throwable th) {
                this.nT.unlock();
            }
        }
        b bVar = this.nV;
        this.nT.unlock();
        return bVar;
    }
}
