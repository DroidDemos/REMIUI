package com.google.common.a;

/* compiled from: MathPreconditions */
final class a {
    static void c(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    private a() {
    }
}
