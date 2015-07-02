package com.alipay.sdk.widget;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

public class SystemDefaultDialog {
    private static boolean a;

    static {
        a = VERSION.SDK_INT >= 11;
    }

    public static Dialog a(Context context, String str, String str2, String str3, OnClickListener onClickListener, String str4, OnClickListener onClickListener2) {
        Builder a = a(context, str, str3, onClickListener, str4, onClickListener2);
        a.setTitle(str);
        a.setMessage(str2);
        Dialog create = a.create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    return true;
                }
                return false;
            }
        });
        create.show();
        return create;
    }

    private static Builder a(Context context, String str, String str2, OnClickListener onClickListener, String str3, OnClickListener onClickListener2) {
        Builder builder = new Builder(context);
        if (a) {
            if (!(TextUtils.isEmpty(str3) || onClickListener2 == null)) {
                builder.setPositiveButton(str3, onClickListener2);
            }
            if (!(TextUtils.isEmpty(str2) || onClickListener == null)) {
                builder.setNegativeButton(str2, onClickListener);
            }
        } else {
            if (!(TextUtils.isEmpty(str2) || onClickListener == null)) {
                builder.setPositiveButton(str2, onClickListener);
            }
            if (!(TextUtils.isEmpty(str3) || onClickListener2 == null)) {
                builder.setNegativeButton(str3, onClickListener2);
            }
        }
        return builder;
    }

    public static Dialog a(Context context, String str, View view, String str2, OnClickListener onClickListener, String str3, OnClickListener onClickListener2) {
        Builder a = a(context, str, str2, onClickListener, str3, onClickListener2);
        a.setView(view);
        Dialog create = a.create();
        create.show();
        return create;
    }
}
