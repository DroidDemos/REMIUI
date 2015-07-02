package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import com.google.android.gms.internal.ax.a;
import com.google.android.wallet.instrumentmanager.R;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

public class au {
    private final int nV;
    private final int nW;
    private final at nX;
    private Base64OutputStream nY;
    private ByteArrayOutputStream nZ;

    public au(int i) {
        this.nX = new aw();
        this.nW = i;
        this.nV = 6;
    }

    private String p(String str) {
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return "";
        }
        this.nZ = new ByteArrayOutputStream();
        this.nY = new Base64OutputStream(this.nZ, 10);
        Arrays.sort(split, new Comparator<String>(this) {
            final /* synthetic */ au oa;

            {
                this.oa = r1;
            }

            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });
        int i = 0;
        while (i < split.length && i < this.nW) {
            if (split[i].trim().length() != 0) {
                try {
                    this.nY.write(this.nX.o(split[i]));
                } catch (Throwable e) {
                    gw.e("Error while writing hash to byteStream", e);
                }
            }
            i++;
        }
        try {
            this.nY.flush();
            this.nY.close();
            return this.nZ.toString();
        } catch (Throwable e2) {
            gw.e("HashManager: Unable to convert to base 64", e2);
            return "";
        }
    }

    public String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(((String) it.next()).toLowerCase(Locale.US));
            stringBuffer.append('\n');
        }
        switch (null) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return q(stringBuffer.toString());
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return p(stringBuffer.toString());
            default:
                return "";
        }
    }

    String q(String str) {
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return "";
        }
        this.nZ = new ByteArrayOutputStream();
        this.nY = new Base64OutputStream(this.nZ, 10);
        PriorityQueue priorityQueue = new PriorityQueue(this.nW, new Comparator<a>(this) {
            final /* synthetic */ au oa;

            {
                this.oa = r1;
            }

            public int a(a aVar, a aVar2) {
                return (int) (aVar.value - aVar2.value);
            }

            public /* synthetic */ int compare(Object x0, Object x1) {
                return a((a) x0, (a) x1);
            }
        });
        for (String s : split) {
            String[] s2 = av.s(s);
            if (s2.length >= this.nV) {
                ax.a(s2, this.nW, this.nV, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                this.nY.write(this.nX.o(((a) it.next()).oc));
            } catch (Throwable e) {
                gw.e("Error while writing hash to byteStream", e);
            }
        }
        try {
            this.nY.flush();
            this.nY.close();
            return this.nZ.toString();
        } catch (Throwable e2) {
            gw.e("HashManager: unable to convert to base 64", e2);
            return "";
        }
    }
}
