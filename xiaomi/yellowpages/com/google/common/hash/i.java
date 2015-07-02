package com.google.common.hash;

/* compiled from: AbstractStreamingHashFunction */
abstract class i implements g {
    i() {
    }

    public HashCode a(Object obj, Funnel funnel) {
        return av().b(obj, funnel).hk();
    }
}
