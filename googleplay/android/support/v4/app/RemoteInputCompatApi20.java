package android.support.v4.app;

import android.app.RemoteInput;
import android.app.RemoteInput.Builder;

class RemoteInputCompatApi20 {
    static RemoteInput[] fromCompat(RemoteInputCompatBase.RemoteInput[] srcArray) {
        if (srcArray == null) {
            return null;
        }
        RemoteInput[] result = new RemoteInput[srcArray.length];
        for (int i = 0; i < srcArray.length; i++) {
            RemoteInputCompatBase.RemoteInput src = srcArray[i];
            result[i] = new Builder(src.getResultKey()).setLabel(src.getLabel()).setChoices(src.getChoices()).setAllowFreeFormInput(src.getAllowFreeFormInput()).addExtras(src.getExtras()).build();
        }
        return result;
    }
}
