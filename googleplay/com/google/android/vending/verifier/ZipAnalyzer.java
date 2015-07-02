package com.google.android.vending.verifier;

import java.util.Collections;
import java.util.List;

public class ZipAnalyzer {
    private static final String[] IMPORTANT_FILES;

    static com.google.android.vending.verifier.api.PackageVerificationApi.FileInfo[] analyzeZipFile(java.io.File r15) throws java.io.IOException, java.security.NoSuchAlgorithmException {
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
        r6 = com.google.android.finsky.utils.Lists.newArrayList();
        r8 = com.google.android.finsky.utils.Lists.newArrayList();
        r7 = com.google.android.finsky.utils.Lists.newArrayList();
        r10 = new com.google.android.instrumentedzip.ZipFile;
        r10.<init>(r15);
        r2 = r10.allEntries();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r5 = 0;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x0016:
        r12 = r2.size();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        if (r5 >= r12) goto L_0x0046;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x001c:
        r3 = r2.get(r5);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r3 = (com.google.android.instrumentedzip.ZipEntry) r3;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r10.verifyEntry(r3);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = r3.getName();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = isImportantFile(r12);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        if (r12 == 0) goto L_0x0035;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x002f:
        r6.add(r3);	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x0032:
        r5 = r5 + 1;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        goto L_0x0016;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x0035:
        r12 = r3.verificationErrors;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        if (r12 == 0) goto L_0x0042;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x0039:
        r8.add(r3);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        goto L_0x0032;
    L_0x003d:
        r12 = move-exception;
        com.google.android.finsky.utils.Utils.safeClose(r10);
        throw r12;
    L_0x0042:
        r7.add(r3);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        goto L_0x0032;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x0046:
        r12 = 20;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r6 = shuffleAndChoose(r6, r12);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = 10;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r8 = shuffleAndChoose(r8, r12);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = 10;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r7 = shuffleAndChoose(r7, r12);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = r6.size();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r13 = r8.size();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = r12 + r13;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r13 = r7.size();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = r12 + r13;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r0.<init>(r12);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r0.addAll(r6);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r0.addAll(r8);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r0.addAll(r7);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = r0.size();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r4 = new com.google.android.vending.verifier.api.PackageVerificationApi.FileInfo[r12];	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r5 = 0;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x007b:
        r12 = r0.size();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        if (r5 >= r12) goto L_0x00a8;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x0081:
        r9 = r0.get(r5);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r9 = (com.google.android.instrumentedzip.ZipEntry) r9;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r11 = 0;
        r11 = r10.getInputStream(r9);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r1 = com.google.android.vending.verifier.PackageVerificationService.getSha256Hash(r11);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12 = new com.google.android.vending.verifier.api.PackageVerificationApi$FileInfo;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r13 = r9.getName();	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r14 = r9.verificationErrors;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r12.<init>(r13, r1, r14);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r4[r5] = r12;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        com.google.android.finsky.utils.Utils.safeClose(r11);	 Catch:{ all -> 0x00a3, all -> 0x003d }
        r5 = r5 + 1;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        goto L_0x007b;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x00a3:
        r12 = move-exception;	 Catch:{ all -> 0x00a3, all -> 0x003d }
        com.google.android.finsky.utils.Utils.safeClose(r11);
        throw r12;	 Catch:{ all -> 0x00a3, all -> 0x003d }
    L_0x00a8:
        com.google.android.finsky.utils.Utils.safeClose(r10);
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.vending.verifier.ZipAnalyzer.analyzeZipFile(java.io.File):com.google.android.vending.verifier.api.PackageVerificationApi$FileInfo[]");
    }

    static {
        IMPORTANT_FILES = new String[]{"classes.dex", "AndroidManifest.xml", "resources.arsc", "META-INF/MANIFEST.MF"};
    }

    private static boolean isImportantFile(String fileName) {
        String[] importantFiles = IMPORTANT_FILES;
        for (String equals : importantFiles) {
            if (equals.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    private static final <T> List<T> shuffleAndChoose(List<T> list, int count) {
        if (list.size() <= count) {
            return list;
        }
        Collections.shuffle(list);
        return list.subList(0, count);
    }
}
