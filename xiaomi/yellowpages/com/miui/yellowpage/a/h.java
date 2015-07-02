package com.miui.yellowpage.a;

import android.os.Message;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.request.BaseResult.State;
import org.json.JSONException;

/* compiled from: BaseLoader */
class h implements Runnable {
    private volatile boolean Bv;
    final /* synthetic */ p Bw;

    private h(p pVar) {
        this.Bw = pVar;
    }

    public void execute() {
        onPreExecute();
        ThreadPool.execute(this);
    }

    public void onPreExecute() {
        this.Bw.Mj = true;
        if (this.Bw.is != null) {
            this.Bw.is.onStartLoading(this.Bw.iJ());
        }
    }

    private void a(m mVar) {
        this.Bw.Mj = false;
        State state = mVar.Gn;
        String str = mVar.description;
        if (state == State.OK) {
            if (this.Bw.Mk) {
                this.Bw.deliverResult(this.Bw.rA);
                this.Bw.Ml = true;
            } else {
                this.Bw.Mk = true;
            }
            if (this.Bw.is != null && this.Bv) {
                this.Bw.is.onStopLoading(this.Bw.iJ());
            }
        } else if (this.Bw.is != null) {
            this.Bw.is.a(this.Bw.iJ(), state, str);
        }
    }

    private void b(int i, Object obj) {
        try {
            a(i, obj, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(int i, Object obj, boolean z) {
        Log.d("BaseLoader", "postResultInBackground data");
        BaseResult a = this.Bw.Mp.a(i, obj, this.Bw.Mp.r(), z);
        this.Bw.rA = this.Bw.a(i, this.Bw.rA, a, z);
        if (this.Bw.Mr != null) {
            this.Bw.Mr.a(i, a.getState(), z);
        }
        b(new m(this.Bw, a.getState(), null));
    }

    private void b(m mVar) {
        Message obtainMessage = this.Bw.Mn.obtainMessage();
        obtainMessage.what = 0;
        obtainMessage.obj = mVar;
        this.Bw.Mn.sendMessage(obtainMessage);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r3 = 0;
        r5 = 1;
        r6 = 0;
        r7 = r6;
        r4 = r3;
    L_0x0005:
        r1 = r10.Bw;	 Catch:{ Exception -> 0x00f9 }
        r1 = r1.Mm;	 Catch:{ Exception -> 0x00f9 }
        r1 = r1.size();	 Catch:{ Exception -> 0x00f9 }
        if (r7 >= r1) goto L_0x0071;
    L_0x0011:
        r1 = r10.Bw;	 Catch:{ Exception -> 0x00f9 }
        r1 = r1.Mm;	 Catch:{ Exception -> 0x00f9 }
        r2 = r1.get(r7);	 Catch:{ Exception -> 0x00f9 }
        r2 = (com.miui.yellowpage.base.request.BaseRequest) r2;	 Catch:{ Exception -> 0x00f9 }
        r1 = r2 instanceof com.miui.yellowpage.base.request.HttpRequest;	 Catch:{ Exception -> 0x00c4 }
        if (r1 == 0) goto L_0x005a;
    L_0x0021:
        r0 = r2;
        r0 = (com.miui.yellowpage.base.request.HttpRequest) r0;	 Catch:{ Exception -> 0x00c4 }
        r1 = r0;
        r4 = r1.getRequestCache();	 Catch:{ Exception -> 0x00c4 }
        if (r4 == 0) goto L_0x0104;
    L_0x002b:
        r4 = "BaseLoader";
        r8 = "request cached data";
        com.miui.yellowpage.base.utils.Log.d(r4, r8);	 Catch:{ Exception -> 0x00c4 }
        r1 = r1.requestLocal();	 Catch:{ Exception -> 0x00c4 }
    L_0x0036:
        r4 = r1;
    L_0x0037:
        r1 = r10.Bw;	 Catch:{ Exception -> 0x00c4 }
        r1 = r1.Mm;	 Catch:{ Exception -> 0x00c4 }
        r1 = r1.size();	 Catch:{ Exception -> 0x00c4 }
        r1 = r1 + -1;
        if (r7 != r1) goto L_0x006f;
    L_0x0045:
        r1 = r5;
    L_0x0046:
        r10.Bv = r1;	 Catch:{ Exception -> 0x00c4 }
        if (r4 != 0) goto L_0x004e;
    L_0x004a:
        r1 = r2 instanceof com.miui.yellowpage.base.request.LocalRequest;	 Catch:{ Exception -> 0x00c4 }
        if (r1 == 0) goto L_0x0055;
    L_0x004e:
        r1 = r2.getRequestCode();	 Catch:{ Exception -> 0x00c4 }
        r10.b(r1, r4);	 Catch:{ Exception -> 0x00c4 }
    L_0x0055:
        r1 = r7 + 1;
        r7 = r1;
        r4 = r2;
        goto L_0x0005;
    L_0x005a:
        r1 = r2 instanceof com.miui.yellowpage.base.request.LocalRequest;	 Catch:{ Exception -> 0x00c4 }
        if (r1 == 0) goto L_0x0101;
    L_0x005e:
        r1 = "BaseLoader";
        r4 = "request local data";
        com.miui.yellowpage.base.utils.Log.d(r1, r4);	 Catch:{ Exception -> 0x00c4 }
        r0 = r2;
        r0 = (com.miui.yellowpage.base.request.LocalRequest) r0;	 Catch:{ Exception -> 0x00c4 }
        r1 = r0;
        r1 = r1.request();	 Catch:{ Exception -> 0x00c4 }
        r4 = r1;
        goto L_0x0037;
    L_0x006f:
        r1 = r6;
        goto L_0x0046;
    L_0x0071:
        r3 = r6;
        r2 = r4;
    L_0x0073:
        r1 = r10.Bw;	 Catch:{ Exception -> 0x00c4 }
        r1 = r1.Mm;	 Catch:{ Exception -> 0x00c4 }
        r1 = r1.size();	 Catch:{ Exception -> 0x00c4 }
        if (r3 >= r1) goto L_0x00e0;
    L_0x007f:
        r1 = r10.Bw;	 Catch:{ Exception -> 0x00c4 }
        r1 = r1.Mm;	 Catch:{ Exception -> 0x00c4 }
        r1 = r1.get(r3);	 Catch:{ Exception -> 0x00c4 }
        r1 = (com.miui.yellowpage.base.request.BaseRequest) r1;	 Catch:{ Exception -> 0x00c4 }
        r2 = r1 instanceof com.miui.yellowpage.base.request.HttpRequest;	 Catch:{ Exception -> 0x00fc }
        if (r2 == 0) goto L_0x00bd;
    L_0x008f:
        r0 = r1;
        r0 = (com.miui.yellowpage.base.request.HttpRequest) r0;	 Catch:{ Exception -> 0x00fc }
        r2 = r0;
        r4 = r2.getRequestServer();	 Catch:{ Exception -> 0x00fc }
        if (r4 == 0) goto L_0x00bd;
    L_0x0099:
        r4 = "BaseLoader";
        r7 = "request server data";
        com.miui.yellowpage.base.utils.Log.d(r4, r7);	 Catch:{ Exception -> 0x00fc }
        r4 = r2.requestServer();	 Catch:{ Exception -> 0x00fc }
        r2 = r10.Bw;	 Catch:{ Exception -> 0x00fc }
        r2 = r2.Mm;	 Catch:{ Exception -> 0x00fc }
        r2 = r2.size();	 Catch:{ Exception -> 0x00fc }
        r2 = r2 + -1;
        if (r3 != r2) goto L_0x00c2;
    L_0x00b2:
        r2 = r5;
    L_0x00b3:
        r10.Bv = r2;	 Catch:{ Exception -> 0x00fc }
        r2 = r1.getRequestCode();	 Catch:{ Exception -> 0x00fc }
        r7 = 0;
        r10.a(r2, r4, r7);	 Catch:{ Exception -> 0x00fc }
    L_0x00bd:
        r2 = r3 + 1;
        r3 = r2;
        r2 = r1;
        goto L_0x0073;
    L_0x00c2:
        r2 = r6;
        goto L_0x00b3;
    L_0x00c4:
        r1 = move-exception;
    L_0x00c5:
        r1.printStackTrace();
        r3 = com.miui.yellowpage.request.BaseResult.State.SERVICE_ERROR;
        r3 = r1 instanceof org.json.JSONException;
        if (r3 == 0) goto L_0x00e1;
    L_0x00ce:
        r1 = com.miui.yellowpage.request.BaseResult.State.DATA_ERROR;
    L_0x00d0:
        r10.Bv = r5;
        r3 = new com.miui.yellowpage.a.m;
        r4 = r10.Bw;
        r2 = r2.getDescription();
        r3.<init>(r4, r1, r2);
        r10.b(r3);
    L_0x00e0:
        return;
    L_0x00e1:
        r3 = r1 instanceof java.net.UnknownServiceException;
        if (r3 == 0) goto L_0x00e8;
    L_0x00e5:
        r1 = com.miui.yellowpage.request.BaseResult.State.SERVICE_ERROR;
        goto L_0x00d0;
    L_0x00e8:
        r3 = r1 instanceof com.miui.yellowpage.base.exception.NetworkUnavailableException;
        if (r3 == 0) goto L_0x00ef;
    L_0x00ec:
        r1 = com.miui.yellowpage.request.BaseResult.State.NETWORK_ERROR;
        goto L_0x00d0;
    L_0x00ef:
        r1 = r1 instanceof com.miui.yellowpage.base.exception.ServerException;
        if (r1 == 0) goto L_0x00f6;
    L_0x00f3:
        r1 = com.miui.yellowpage.request.BaseResult.State.SERVICE_ERROR;
        goto L_0x00d0;
    L_0x00f6:
        r1 = com.miui.yellowpage.request.BaseResult.State.SERVICE_ERROR;
        goto L_0x00d0;
    L_0x00f9:
        r1 = move-exception;
        r2 = r4;
        goto L_0x00c5;
    L_0x00fc:
        r2 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00c5;
    L_0x0101:
        r4 = r3;
        goto L_0x0037;
    L_0x0104:
        r1 = r3;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.a.h.run():void");
    }
}
