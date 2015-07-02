package miui.external;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ SdkErrorActivity bt;

    a(SdkErrorActivity sdkErrorActivity) {
        this.bt = sdkErrorActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Dialog a = this.bt.hp();
        new g(this.bt, a).show(this.bt.getFragmentManager(), "SdkUpdatePromptDialog");
        new j(this, a).execute(new Void[0]);
    }
}
