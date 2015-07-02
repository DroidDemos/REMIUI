package android.support.v4.app;

import android.os.Handler;
import android.os.Message;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: FragmentActivity */
class l extends Handler {
    final /* synthetic */ FragmentActivity vp;

    l(FragmentActivity fragmentActivity) {
        this.vp = fragmentActivity;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                if (this.vp.mStopped) {
                    this.vp.m(false);
                    return;
                }
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                this.vp.ck();
                this.vp.oa.execPendingActions();
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }
}
