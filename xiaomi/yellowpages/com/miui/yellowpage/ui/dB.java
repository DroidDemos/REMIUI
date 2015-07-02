package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.reference.RefMethods.Settings.System;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.utils.x;
import miui.app.AlertDialog;

/* compiled from: SettingFragment */
class dB implements OnClickListener {
    final /* synthetic */ dq JF;

    dB(dq dqVar) {
        this.JF = dqVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        System.setCloudAntispam(this.JF.getActivity(), true);
        this.JF.IC.setChecked(true);
        this.JF.IC.setSummary(R.string.turn_on_smart_antispam_summary_on);
        Preference.setBoolean(this.JF.getActivity(), "pref_show_user_notice_enable_identify", true);
        if ((x.X(this.JF.getActivity()) && ((AlertDialog) dialogInterface).isChecked()) || !x.X(this.JF.getActivity())) {
            System.setNeverRemindSmartAntispam(this.JF.getActivity(), true);
        }
    }
}
