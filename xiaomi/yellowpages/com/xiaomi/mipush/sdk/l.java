package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import com.xiaomi.f.a.c.b;
import java.util.concurrent.LinkedBlockingQueue;

public class l extends Thread {
    private static String a;
    private LinkedBlockingQueue Am;
    private Context vi;

    static {
        a = "MessageHandleThread";
    }

    public l(Context context) {
        super(a);
        this.Am = new LinkedBlockingQueue();
        this.vi = context.getApplicationContext();
        if (this.vi == null) {
            this.vi = context;
        }
    }

    public void a(f fVar) {
        this.Am.add(fVar);
    }

    public void run() {
        while (true) {
            try {
                f fVar = (f) this.Am.take();
                b eJ = fVar.eJ();
                Intent eK = fVar.eK();
                switch (eK.getIntExtra("message_type", 1)) {
                    case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                        a f = g.S(this.vi).f(eK);
                        if (f != null) {
                            if (!(f instanceof MiPushMessage)) {
                                if (!(f instanceof MiPushCommandMessage)) {
                                    break;
                                }
                                eJ.onCommandResult(this.vi, (MiPushCommandMessage) f);
                                break;
                            }
                            eJ.onReceiveMessage(this.vi, (MiPushMessage) f);
                            break;
                        }
                        break;
                    case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                        eJ.onReceiveMessage(this.vi, (MiPushMessage) eK.getSerializableExtra("key_message"));
                        break;
                    case WindowData.d /*3*/:
                        eJ.onCommandResult(this.vi, (MiPushCommandMessage) eK.getSerializableExtra("key_command"));
                        break;
                    case Base64.CRLF /*4*/:
                        return;
                    default:
                        break;
                }
            } catch (Throwable e) {
                b.a(e);
            }
        }
    }
}
