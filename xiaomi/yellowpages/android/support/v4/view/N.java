package android.support.v4.view;

import android.view.KeyEvent;

/* compiled from: KeyEventCompatHoneycomb */
class N {
    N() {
    }

    public static int normalizeMetaState(int i) {
        return KeyEvent.normalizeMetaState(i);
    }

    public static boolean metaStateHasModifiers(int i, int i2) {
        return KeyEvent.metaStateHasModifiers(i, i2);
    }

    public static boolean metaStateHasNoModifiers(int i) {
        return KeyEvent.metaStateHasNoModifiers(i);
    }
}
