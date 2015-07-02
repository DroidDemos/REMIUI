package com.miui.yellowpage.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.ThreadPool;

/* compiled from: MarkNumberActivity */
class B implements OnClickListener {
    final /* synthetic */ MarkNumberActivity rR;

    B(MarkNumberActivity markNumberActivity) {
        this.rR = markNumberActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i < this.rR.Gs.getCount() - 1) {
            this.rR.Gs.moveToPosition(i);
            int i2 = this.rR.Gs.getInt(0);
            if (this.rR.Gt != i) {
                ThreadPool.execute(new Q(this, i2));
            }
            if (this.rR.mDialog.isShowing()) {
                this.rR.mDialog.dismiss();
                return;
            }
            return;
        }
        View inflate = LayoutInflater.from(this.rR).inflate(R.layout.mark_number_keyword_text, null);
        new Builder(this.rR).setView(inflate).setCancelable(false).setTitle(R.string.mark_number_dialog_maintitle).setNegativeButton(17039360, null).setPositiveButton(17039370, new P(this, (TextView) inflate.findViewById(R.id.keyword_text))).show();
    }
}
