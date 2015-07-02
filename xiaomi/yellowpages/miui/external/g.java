package miui.external;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

class g extends DialogFragment {
    final /* synthetic */ SdkErrorActivity bt;
    private Dialog sf;

    public g(SdkErrorActivity sdkErrorActivity, Dialog dialog) {
        this.bt = sdkErrorActivity;
        this.sf = dialog;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return this.sf;
    }
}
