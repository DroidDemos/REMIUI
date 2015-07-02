package com.google.common.hash;

import java.nio.charset.Charset;

/* compiled from: AbstractCompositeHashFunction */
class j implements m {
    final /* synthetic */ m[] Eu;
    final /* synthetic */ b Ev;

    j(b bVar, m[] mVarArr) {
        this.Ev = bVar;
        this.Eu = mVarArr;
    }

    public /* bridge */ /* synthetic */ f a(CharSequence charSequence, Charset charset) {
        return b(charSequence, charset);
    }

    public m k(byte[] bArr) {
        for (m k : this.Eu) {
            k.k(bArr);
        }
        return this;
    }

    public m b(CharSequence charSequence, Charset charset) {
        for (m b : this.Eu) {
            b.b(charSequence, charset);
        }
        return this;
    }

    public m b(Object obj, Funnel funnel) {
        for (m b : this.Eu) {
            b.b(obj, funnel);
        }
        return this;
    }

    public HashCode hk() {
        return this.Ev.a(this.Eu);
    }
}
