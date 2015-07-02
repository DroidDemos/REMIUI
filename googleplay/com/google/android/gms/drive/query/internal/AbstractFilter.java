package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.c;

public abstract class AbstractFilter implements Filter {
    public String toString() {
        return String.format("Filter[%s]", new Object[]{a(new c())});
    }
}
