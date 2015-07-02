package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.ThreadPool;

/* compiled from: MarkNumberActivity */
class P implements OnClickListener {
    final /* synthetic */ TextView uD;
    final /* synthetic */ B uE;

    P(B b, TextView textView) {
        this.uE = b;
        this.uD = textView;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Object trim = this.uD.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(this.uE.rR, R.string.mark_title_null_hint, 0).show();
        } else {
            ThreadPool.execute(new ag(this, trim));
        }
        if (this.uE.rR.mDialog.isShowing()) {
            this.uE.rR.mDialog.dismiss();
        }
    }
}
