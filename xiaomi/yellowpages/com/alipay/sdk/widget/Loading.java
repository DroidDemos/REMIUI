package com.alipay.sdk.widget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnCancelListener;

public class Loading {
    private Activity a;
    private ProgressDialog b;

    public Loading(Activity activity) {
        this.a = activity;
    }

    public boolean a() {
        return this.b != null && this.b.isShowing();
    }

    public void b() {
        a("\u52a0\u8f7d\u4e2d");
    }

    public void a(String str) {
        a(str, false, null);
    }

    public void a(final CharSequence charSequence, final boolean z, final OnCancelListener onCancelListener) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ Loading d;

            public void run() {
                if (this.d.b == null || !this.d.b.isShowing()) {
                    this.d.c();
                    this.d.b = new ProgressDialog(this.d.a);
                    this.d.b.setCancelable(z);
                    this.d.b.setOnCancelListener(onCancelListener);
                    this.d.b.setMessage(charSequence);
                    this.d.b.show();
                    return;
                }
                this.d.b.setMessage(charSequence);
            }
        });
    }

    public void c() {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ Loading a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    if (this.a.a()) {
                        this.a.b.dismiss();
                        this.a.b = null;
                    }
                } catch (Exception e) {
                }
            }
        });
    }
}
