package org.keyczar;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.keyczar.interfaces.Stream;

class StreamQueue<T extends Stream> extends ConcurrentLinkedQueue<T> {
    StreamQueue() {
    }
}
