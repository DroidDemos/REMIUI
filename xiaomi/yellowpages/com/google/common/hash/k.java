package com.google.common.hash;

import java.nio.charset.Charset;

/* compiled from: AbstractHasher */
abstract class k implements m {
    k() {
    }

    public /* bridge */ /* synthetic */ f a(CharSequence charSequence, Charset charset) {
        return b(charSequence, charset);
    }

    public m b(CharSequence charSequence, Charset charset) {
        return k(charSequence.toString().getBytes(charset));
    }
}
