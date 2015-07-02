package com.xiaomi.b.a;

class M implements Runnable {
    final /* synthetic */ H pt;

    M(H h) {
        this.pt = h;
    }

    public void run() {
        /* JADX: method processing error */
/*
        Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r3 = this;
    L_0x0000:
        r0 = r3.pt;	 Catch:{ all -> 0x003f }
        r0 = r0.pc;	 Catch:{ all -> 0x003f }
        r0.lock();	 Catch:{ all -> 0x003f }
        r0 = r3.pt;	 Catch:{ all -> 0x003f }
        r0 = r0.pp;	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x001d;	 Catch:{ all -> 0x003f }
    L_0x0011:
        r0 = r3.pt;	 Catch:{ all -> 0x003f }
        r0 = r0.pp;	 Catch:{ all -> 0x003f }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x0027;
    L_0x001d:
        r0 = r3.pt;
        r0 = r0.pc;
        r0.unlock();
        return;
    L_0x0027:
        r0 = r3.pt;
        r0 = r0.pc;
        r0.unlock();
        r0 = r3.pt;
        r1 = java.lang.System.currentTimeMillis();
        r0.z = r1;
        r0 = r3.pt;
        r0.e();
        goto L_0x0000;
    L_0x003f:
        r0 = move-exception;
        r1 = r3.pt;
        r1 = r1.pc;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.b.a.M.run():void");
    }
}
