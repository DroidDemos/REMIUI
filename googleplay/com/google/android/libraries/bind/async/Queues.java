package com.google.android.libraries.bind.async;

public class Queues {
    public static final Queue BIND_CPU;
    public static final Queue BIND_IMMEDIATE;
    public static final Queue BIND_MAIN;

    static {
        BIND_CPU = new Queue("BIND_CPU", 2, true);
        BIND_MAIN = new Queue("BIND_MAIN", AsyncUtil.mainThreadExecutor());
        BIND_IMMEDIATE = new Queue("BIND_IMMEDIATE", AsyncUtil.immediateExecutor());
    }
}
