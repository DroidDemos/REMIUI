package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class kl {

    public static final class a {
        private final List<String> Wu;
        private final Object Wv;

        private a(Object obj) {
            this.Wv = kn.k(obj);
            this.Wu = new ArrayList();
        }

        public a a(String str, Object obj) {
            this.Wu.add(((String) kn.k(str)) + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.Wv.getClass().getSimpleName()).append('{');
            int size = this.Wu.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.Wu.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }

    public static a j(Object obj) {
        return new a(obj);
    }
}
