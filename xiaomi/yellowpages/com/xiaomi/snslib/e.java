package com.xiaomi.snslib;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.miui.yellowpage.R;
import com.weibo.sdk.android.a;
import com.weibo.sdk.android.d;
import com.xiaomi.snslib.ShareToSNS.SNSType;
import miui.cloud.XiaomiAccountManager;

/* compiled from: ShareToSNS */
final class e extends AsyncTask {
    final /* synthetic */ boolean mZ;
    final /* synthetic */ boolean na;
    final /* synthetic */ String nb;
    final /* synthetic */ String nc;
    final /* synthetic */ j nd;
    final /* synthetic */ String ne;
    final /* synthetic */ String nf;
    final /* synthetic */ boolean ng;
    final /* synthetic */ Context val$context;

    e(boolean z, Context context, boolean z2, String str, String str2, j jVar, String str3, String str4, boolean z3) {
        this.mZ = z;
        this.val$context = context;
        this.na = z2;
        this.nb = str;
        this.nc = str2;
        this.nd = jVar;
        this.ne = str3;
        this.nf = str4;
        this.ng = z3;
    }

    protected /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        return b((String[]) objArr);
    }

    protected String b(String... strArr) {
        try {
            boolean z = this.mZ || ShareToSNS.H(this.val$context);
            a.u(z);
        } catch (SecurityException e) {
        }
        return XiaomiAccountManager.getSnsAccessToken(this.val$context, d.a(SNSType.SINA));
    }

    protected void onPostExecute(String str) {
        if (this.val$context instanceof Activity) {
            Activity activity = (Activity) this.val$context;
            if (!TextUtils.isEmpty(str)) {
                if (this.nd != null) {
                    this.nd.onShareStart();
                }
                d a = d.a(str, this.nb, this.nc, this.ne, this.nf);
                String str2 = TextUtils.isEmpty(this.nc) ? "https://api.weibo.com/2/statuses/update.json" : "https://upload.api.weibo.com/2/statuses/upload.json";
                if (this.ng) {
                    ShareToSNS.b(this.val$context, (int) R.string.sina_share_pendding);
                }
                com.weibo.sdk.android.net.e.a(str2, a, "POST", new f(this, activity, str));
            } else if (this.na || !d.a((Context) activity, SNSType.SINA)) {
                d.a(activity, SNSType.SINA);
            } else {
                d.a(activity, this.nb, this.nc, SNSType.SINA);
            }
        }
    }
}
