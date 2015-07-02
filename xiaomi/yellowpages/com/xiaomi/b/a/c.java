package com.xiaomi.b.a;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class c {
    private static final SecureRandom nB;
    private static final Lock nC;
    private AtomicLong nD;

    static {
        nB = new SecureRandom();
        nC = new ReentrantLock();
    }

    c() {
        this.nD = new AtomicLong();
        this.nD = new AtomicLong(b());
    }

    private long b() {
        long nextLong;
        nC.lock();
        while (true) {
            try {
                nextLong = nB.nextLong() & 9007199254740991L;
                if (nextLong <= 9007194959773696L) {
                    break;
                }
            } finally {
                nC.unlock();
            }
        }
        return nextLong;
    }

    public long a() {
        return this.nD.getAndIncrement();
    }
}
