package com.miui.yellowpage.a;

import android.os.Handler.Callback;
import android.os.Message;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: RequestLoader */
class j implements Callback {
    final /* synthetic */ f CN;

    j(f fVar) {
        this.CN = fVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                this.CN.dA();
                break;
        }
        return true;
    }
}
