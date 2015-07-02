package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput;

class RemoteInputCompatJellybean {
    static Bundle toBundle(RemoteInput remoteInput) {
        Bundle data = new Bundle();
        data.putString("resultKey", remoteInput.getResultKey());
        data.putCharSequence("label", remoteInput.getLabel());
        data.putCharSequenceArray("choices", remoteInput.getChoices());
        data.putBoolean("allowFreeFormInput", remoteInput.getAllowFreeFormInput());
        data.putBundle("extras", remoteInput.getExtras());
        return data;
    }

    static Bundle[] toBundleArray(RemoteInput[] remoteInputs) {
        if (remoteInputs == null) {
            return null;
        }
        Bundle[] bundles = new Bundle[remoteInputs.length];
        for (int i = 0; i < remoteInputs.length; i++) {
            bundles[i] = toBundle(remoteInputs[i]);
        }
        return bundles;
    }
}
