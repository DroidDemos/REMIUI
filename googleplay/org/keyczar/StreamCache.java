package org.keyczar;

import java.util.concurrent.ConcurrentHashMap;
import org.keyczar.interfaces.Stream;

class StreamCache<T extends Stream> {
    private final ConcurrentHashMap<KeyczarKey, StreamQueue<T>> cacheMap;

    StreamCache() {
        this.cacheMap = new ConcurrentHashMap();
    }

    void put(KeyczarKey key, T s) {
        getQueue(key).add(s);
    }

    T get(KeyczarKey key) {
        return (Stream) getQueue(key).poll();
    }

    StreamQueue<T> getQueue(KeyczarKey key) {
        StreamQueue<T> queue = (StreamQueue) this.cacheMap.get(key);
        if (queue != null) {
            return queue;
        }
        StreamQueue<T> freshQueue = new StreamQueue();
        queue = (StreamQueue) this.cacheMap.putIfAbsent(key, freshQueue);
        if (queue != null) {
            return queue;
        }
        return freshQueue;
    }
}
