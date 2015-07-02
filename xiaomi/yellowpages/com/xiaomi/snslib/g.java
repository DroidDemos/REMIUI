package com.xiaomi.snslib;

import android.content.Context;
import android.os.AsyncTask;
import com.xiaomi.snslib.ShareToSNS.SNSType;
import miui.cloud.XiaomiAccountManager;

/* compiled from: ShareToSNS */
class g extends AsyncTask {
    final /* synthetic */ Context val$context;
    final /* synthetic */ f wy;

    g(f fVar, Context context) {
        this.wy = fVar;
        this.val$context = context;
    }

    protected /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Integer[]) objArr);
    }

    protected Boolean a(Integer... numArr) {
        return Boolean.valueOf(XiaomiAccountManager.invalidateSnsAccessToken(this.val$context, d.b(SNSType.SINA), this.wy.nq));
    }

    protected void onPostExecute(Boolean bool) {
        if (bool.booleanValue()) {
            d.b(this.val$context, SNSType.SINA);
        }
    }
}
