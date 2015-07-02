package com.xiaomi.kenai.jbosh;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class RequestIDSequence {
    private static final int INCREMENT_BITS = 32;
    private static final Lock LOCK;
    private static final long MASK = 9007199254740991L;
    private static final int MAX_BITS = 53;
    private static final long MAX_INITIAL = 9007194959773696L;
    private static final long MIN_INCREMENTS = 4294967296L;
    private static final SecureRandom RAND;
    private AtomicLong nextRequestID;

    static {
        RAND = new SecureRandom();
        LOCK = new ReentrantLock();
    }

    RequestIDSequence() {
        this.nextRequestID = new AtomicLong();
        this.nextRequestID = new AtomicLong(generateInitialValue());
    }

    public long getNextRID() {
        return this.nextRequestID.getAndIncrement();
    }

    private long generateInitialValue() {
        long result;
        LOCK.lock();
        while (true) {
            try {
                result = RAND.nextLong() & MASK;
                if (result <= MAX_INITIAL) {
                    break;
                }
            } finally {
                LOCK.unlock();
            }
        }
        return result;
    }
}
