package com.miui.yellowpage.ui;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.CallLog.Calls;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.x;
import miui.provider.BatchOperation;

/* compiled from: CallLogFragment */
class cE implements OnClickListener {
    final /* synthetic */ cO cC;

    cE(cO cOVar) {
        this.cC = cOVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BatchOperation batchOperation = new BatchOperation(this.cC.mActivity.getContentResolver(), "call_log");
        for (int i2 = 0; i2 < this.cC.ET.getCount(); i2++) {
            batchOperation.add(ContentProviderOperation.newDelete(ContentUris.appendId(Calls.CONTENT_URI_WITH_VOICEMAIL.buildUpon(), ((x) this.cC.ET.getItem(i2)).getId()).build()).build());
            if (batchOperation.size() > 100) {
                batchOperation.execute();
            }
        }
        if (batchOperation.size() > 0) {
            batchOperation.execute();
        }
        Toast.makeText(this.cC.mActivity, this.cC.getString(R.string.toast_finish_delete_call_log), 1).show();
        this.cC.mLoader.reload();
    }
}
