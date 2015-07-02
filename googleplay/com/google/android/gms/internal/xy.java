package com.google.android.gms.internal;

import java.io.IOException;

public abstract class xy<M extends xy<M>> extends ye {
    protected ya aYj;

    protected final boolean a(M m) {
        return (this.aYj == null || this.aYj.isEmpty()) ? m.aYj == null || m.aYj.isEmpty() : this.aYj.equals(m.aYj);
    }

    protected int c() {
        int i = 0;
        if (this.aYj == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.aYj.size()) {
            i2 += this.aYj.mr(i).c();
            i++;
        }
        return i2;
    }

    protected final int vL() {
        return (this.aYj == null || this.aYj.isEmpty()) ? 0 : this.aYj.hashCode();
    }

    public void writeTo(xx output) throws IOException {
        if (this.aYj != null) {
            for (int i = 0; i < this.aYj.size(); i++) {
                this.aYj.mr(i).writeTo(output);
            }
        }
    }
}
