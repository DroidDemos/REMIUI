package com.miui.yellowpage.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.reference.RefMethods.Telephony;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.c.b;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

/* compiled from: Ui */
public class D {
    public static AlertDialog a(Context context, int i, int i2, OnClickListener onClickListener, OnClickListener onClickListener2, int i3) {
        return a(context, i, i2, 0, 0, onClickListener, onClickListener2, i3);
    }

    @SuppressLint({"InflateParams"})
    public static AlertDialog a(Context context, int i, int i2, int i3, int i4, OnClickListener onClickListener, OnClickListener onClickListener2, int i5) {
        if (i2 < 0) {
            return null;
        }
        Resources resources = context.getResources();
        String string = resources.getString(R.string.user_agreement2);
        String string2 = resources.getString(R.string.user_agreement4);
        String string3 = resources.getString(i2, new Object[]{string, string2});
        s kVar = new k(context);
        s lVar = new l(context);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(string3);
        int indexOf = string3.indexOf(string);
        spannableStringBuilder.setSpan(new d(kVar), indexOf, string.length() + indexOf, 33);
        int indexOf2 = string3.indexOf(string2);
        spannableStringBuilder.setSpan(new d(lVar), indexOf2, string2.length() + indexOf2, 33);
        if (i3 == 0) {
            i3 = R.string.agree;
        }
        if (i4 == 0) {
            i4 = 17039360;
        }
        Builder positiveButton = new Builder(context).setMessage(spannableStringBuilder).setCancelable(false).setNegativeButton(i4, onClickListener2).setPositiveButton(i3, onClickListener);
        if (i5 != 0) {
            positiveButton.setCheckBox(true, context.getString(i5));
        }
        if (i > 0) {
            positiveButton.setTitle(i);
        }
        AlertDialog show = positiveButton.show();
        ((TextView) show.getWindow().findViewById(b.Jy)).setMovementMethod(LinkMovementMethod.getInstance());
        return show;
    }

    public static void z(Context context, String str) {
        Builder builder = new Builder(context);
        builder.setTitle(R.string.confirm_to_add_blacklist);
        builder.setPositiveButton(17039370, new g(str, context));
        builder.setNegativeButton(17039360, null);
        builder.create().show();
    }

    public static void A(Context context, String str) {
        Builder builder = new Builder(context);
        builder.setTitle(R.string.confirm_to_remove_blacklist);
        builder.setPositiveButton(17039370, new h(str, context));
        builder.setNegativeButton(17039360, null);
        builder.create().show();
    }

    @SuppressLint({"InflateParams"})
    public static boolean a(Context context, String str, String str2, C c) {
        if (Sim.getSimCount(context) != 2) {
            return false;
        }
        Builder builder = new Builder(context);
        builder.setTitle(str);
        View inflate = LayoutInflater.from(context).inflate(R.layout.two_sim_cards_dlg_custom_view, null);
        ((TextView) inflate.findViewById(R.id.message)).setText(str2);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon_sim_card1);
        imageView.setImageResource(R.drawable.ic_sim_icon_1);
        ((TextView) inflate.findViewById(R.id.text_sim_card1)).setText(Telephony.getSimDisplayName(context, 0));
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.icon_sim_card2);
        imageView2.setImageResource(R.drawable.ic_sim_icon_2);
        ((TextView) inflate.findViewById(R.id.text_sim_card2)).setText(Telephony.getSimDisplayName(context, 1));
        builder.setView(inflate);
        builder.setOnCancelListener(new i(c));
        AlertDialog create = builder.create();
        imageView.setOnClickListener(new j(c, create));
        imageView2.setOnClickListener(new f(c, create));
        create.show();
        return true;
    }
}
