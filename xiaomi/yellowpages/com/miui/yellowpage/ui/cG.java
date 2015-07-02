package com.miui.yellowpage.ui;

import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.CallLog.Calls;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.x;

/* compiled from: CallLogFragment */
class cG implements OnClickListener {
    final /* synthetic */ x Em;
    final /* synthetic */ cO cC;
    final /* synthetic */ int val$position;

    cG(cO cOVar, x xVar, int i) {
        this.cC = cOVar;
        this.Em = xVar;
        this.val$position = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.Em.getId() > 0) {
            if (this.cC.mActivity.getContentResolver().delete(ContentUris.appendId(Calls.CONTENT_URI_WITH_VOICEMAIL.buildUpon(), this.Em.getId()).build(), null, null) > 0) {
                this.cC.ET.E(this.val$position);
                if (this.cC.ET.getCount() == 0) {
                    this.cC.mLoader.reload();
                }
                Toast.makeText(this.cC.mActivity, this.cC.getString(R.string.toast_finish_delete_call_log), 1).show();
            }
        }
    }
}
