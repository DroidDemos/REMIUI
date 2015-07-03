package miui.external;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ SdkErrorActivity b;

    a(SdkErrorActivity sdkErrorActivity) {
        this.b = sdkErrorActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.b.finish();
        System.exit(0);
    }
}
