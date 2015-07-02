package com.alipay.sdk.protocol;

import com.alipay.sdk.data.Request;
import com.alipay.sdk.data.Response;

public abstract class WindowData extends FrameData {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = -10;
    private boolean m;
    private boolean n;
    private int o;

    public abstract boolean f();

    public abstract int g();

    public abstract String h();

    protected WindowData(Request request, Response response) {
        super(request, response);
        this.n = false;
        this.o = -1;
        this.m = false;
    }

    public void j() {
        this.o += b;
    }

    public int k() {
        return this.o;
    }

    public boolean l() {
        return this.m;
    }

    public void a(boolean z) {
        this.m = z;
    }

    public boolean m() {
        return this.n;
    }

    public void b(boolean z) {
        this.n = z;
    }
}
