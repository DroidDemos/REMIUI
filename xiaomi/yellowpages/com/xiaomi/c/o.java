package com.xiaomi.c;

import java.util.List;
import java.util.TimerTask;
import org.apache.thrift.TException;

class o extends TimerTask {
    final /* synthetic */ l Mb;

    o(l lVar) {
        this.Mb = lVar;
    }

    public void run() {
        for (n nVar : this.Mb.rP) {
            List a = nVar.a();
            double b = nVar.b();
            if (a != null) {
                try {
                    if (a.size() > 0) {
                        this.Mb.a(a, b);
                    }
                } catch (TException e) {
                    e.printStackTrace();
                }
            }
        }
        this.Mb.d = false;
    }
}
