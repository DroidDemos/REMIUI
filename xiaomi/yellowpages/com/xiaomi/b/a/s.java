package com.xiaomi.b.a;

final class s extends A implements Comparable {
    private static final s oC;
    private final int b;
    private final int c;

    static {
        try {
            oC = ai("1.8");
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private s(String str) {
        super(str);
        int indexOf = str.indexOf(46);
        if (indexOf <= 0) {
            throw new aa("Illegal ver attribute value (not in major.minor form): " + str);
        }
        String substring = str.substring(0, indexOf);
        try {
            this.b = Integer.parseInt(substring);
            if (this.b < 0) {
                throw new aa("Major version may not be < 0");
            }
            substring = str.substring(indexOf + 1);
            try {
                this.c = Integer.parseInt(substring);
                if (this.c < 0) {
                    throw new aa("Minor version may not be < 0");
                }
            } catch (Throwable e) {
                throw new aa("Could not parse ver attribute value (minor ver): " + substring, e);
            }
        } catch (Throwable e2) {
            throw new aa("Could not parse ver attribute value (major ver): " + substring, e2);
        }
    }

    static s ai(String str) {
        return str == null ? null : new s(str);
    }

    static s cr() {
        return oC;
    }

    public int compareTo(Object obj) {
        if (!(obj instanceof s)) {
            return 0;
        }
        s sVar = (s) obj;
        if (this.b >= sVar.b) {
            if (this.b > sVar.b) {
                return 1;
            }
            if (this.c >= sVar.c) {
                return this.c <= sVar.c ? 0 : 1;
            }
        }
        return -1;
    }
}
