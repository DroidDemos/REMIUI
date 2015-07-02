package miui.external;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class c implements OnClickListener {
    final /* synthetic */ SdkErrorActivity bt;

    c(SdkErrorActivity sdkErrorActivity) {
        this.bt = sdkErrorActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.bt.finish();
        System.exit(0);
    }
}
