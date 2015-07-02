package com.xiaomi.d;

import java.util.concurrent.ThreadFactory;

class m implements ThreadFactory {
    final /* synthetic */ j Af;

    m(j jVar) {
        this.Af = jVar;
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "Smack Listener Processor (" + this.Af.l + ")");
    }
}
