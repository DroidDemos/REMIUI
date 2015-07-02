package com.xiaomi.c;

import android.content.Context;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.ui.bw;
import com.xiaomi.a.a.a.a;
import com.xiaomi.a.a.a.a.b;
import com.xiaomi.a.a.a.a.c;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import org.apache.http.message.BasicNameValuePair;
import org.apache.thrift.TBase;
import org.apache.thrift.f;
import org.apache.thrift.protocol.TCompactProtocol.Factory;

public class l {
    private static l vO;
    private boolean d;
    private List rP;
    private final Random vP;
    private Timer vQ;
    private Context vR;

    private l(Context context) {
        this.rP = new ArrayList();
        this.vP = new Random();
        this.vQ = new Timer("Upload Http Record Timer");
        this.d = false;
        this.vR = null;
        this.vR = context.getApplicationContext();
    }

    private String a(String str) {
        try {
            MessageDigest.getInstance("MD5").update(ak(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, r0.digest())});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void a(Context context) {
        synchronized (l.class) {
            try {
                if (vO == null) {
                    vO = new l(context);
                }
            } catch (Throwable th) {
                Class cls = l.class;
            }
        }
    }

    private void a(String str, String str2) {
        List arrayList = new ArrayList();
        String valueOf = String.valueOf(System.nanoTime());
        String valueOf2 = String.valueOf(System.currentTimeMillis());
        arrayList.add(new BasicNameValuePair("n", valueOf));
        arrayList.add(new BasicNameValuePair("d", str2));
        arrayList.add(new BasicNameValuePair("t", valueOf2));
        arrayList.add(new BasicNameValuePair("s", a(valueOf + str2 + valueOf2 + "56C6A520%$C99119A0&^229(!@2746C7")));
        c.a(this.vR, String.format("http://%1$s/diagnoses/v1/report", new Object[]{str}), arrayList);
    }

    private void a(List list, double d) {
        for (b bVar : list) {
            TBase cVar = new c();
            cVar.t("httpapi");
            cVar.a(bVar);
            cVar.a(new a());
            String str = new String(a.d(new f(new Factory()).a(cVar)));
            if (((double) this.vP.nextInt(bw.FILE_CHOOSER_RESULT_CODE)) < 10000.0d * d) {
                try {
                    a("f3.mi-stat.gslb.mi-idc.com", str);
                } catch (Throwable e) {
                    Log.e("uploadhoststat", null, e);
                } catch (Throwable e2) {
                    Log.e("uploadhoststat", null, e2);
                }
            }
        }
    }

    private byte[] ak(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static l fn() {
        synchronized (l.class) {
            try {
            } finally {
                Object obj = l.class;
            }
        }
        l lVar = vO;
        return lVar;
    }

    public void a(n nVar) {
        this.rP.add(nVar);
    }

    public void b() {
        if (!this.d) {
            this.d = true;
            this.vQ.schedule(new o(this), ConfigConstant.LOCATE_INTERVAL_UINT);
        }
    }
}
