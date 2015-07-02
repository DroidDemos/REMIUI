package com.miui.yellowpage.a;

import android.os.Handler.Callback;
import android.os.Message;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: BaseLoader */
public class n implements Callback {
    final /* synthetic */ h GP;

    public n(h hVar) {
        this.GP = hVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                this.GP.a((m) message.obj);
                break;
        }
        return false;
    }
}
