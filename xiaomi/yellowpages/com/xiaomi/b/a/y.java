package com.xiaomi.b.a;

abstract class y extends A {
    protected y(String str) {
        super(Integer.valueOf(an(str)));
    }

    private static int an(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Throwable e) {
            throw new aa("Could not parse an integer from the value provided: " + str, e);
        }
    }

    protected final void a(int i) {
        int intValue = ((Integer) cw()).intValue();
        if (intValue < i) {
            throw new aa("Illegal attribute value '" + intValue + "' provided.  " + "Must be >= " + i);
        }
    }

    public int ah() {
        return ((Integer) cw()).intValue();
    }
}
