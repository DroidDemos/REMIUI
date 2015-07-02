package com.xiaomi.b.a;

public final class j {
    private final an nZ;

    private j(an anVar) {
        this.nZ = anVar;
    }

    public static j X(String str) {
        return b("xm", str, null);
    }

    public static j b(String str, String str2, String str3) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("URI is required and may not be null/empty");
        } else if (str2 != null && str2.length() != 0) {
            return (str3 == null || str3.length() == 0) ? new j(new an(str, str2)) : new j(new an(str, str2, str3));
        } else {
            throw new IllegalArgumentException("Local arg is required and may not be null/empty");
        }
    }

    public static j j(String str, String str2) {
        return b(str, str2, null);
    }

    public String a() {
        return this.nZ.a();
    }

    boolean a(an anVar) {
        return this.nZ.equals(anVar);
    }

    public String b() {
        return this.nZ.b();
    }

    public String c() {
        return this.nZ.c();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        return this.nZ.equals(((j) obj).nZ);
    }

    public int hashCode() {
        return this.nZ.hashCode();
    }
}
