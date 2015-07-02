package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.KeyEvent;

/* compiled from: KeyEventCompat */
public class n {
    static final ac lr;

    static {
        if (VERSION.SDK_INT >= 11) {
            lr = new ae();
        } else {
            lr = new q();
        }
    }

    public static boolean a(KeyEvent keyEvent, int i) {
        return lr.metaStateHasModifiers(keyEvent.getMetaState(), i);
    }

    public static boolean a(KeyEvent keyEvent) {
        return lr.metaStateHasNoModifiers(keyEvent.getMetaState());
    }
}
