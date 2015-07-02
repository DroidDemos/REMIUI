package com.google.common.hash;

import java.nio.charset.Charset;

/* compiled from: Hasher */
public interface m extends f {
    m b(CharSequence charSequence, Charset charset);

    m b(Object obj, Funnel funnel);

    HashCode hk();

    m k(byte[] bArr);
}
