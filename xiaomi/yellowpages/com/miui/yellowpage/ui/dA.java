package com.miui.yellowpage.ui;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.provider.MiuiSettings.AntiSpam;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.reference.RefMethods.Settings.System;

/* compiled from: SettingFragment */
class dA implements OnPreferenceChangeListener {
    final /* synthetic */ dq JF;

    dA(dq dqVar) {
        this.JF = dqVar;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (((Boolean) obj).booleanValue()) {
            System.setCloudAntispam(this.JF.getActivity(), true);
            this.JF.IC.setSummary(R.string.turn_on_smart_antispam_summary_on);
        } else {
            AntiSpam.resetMarkedNumberBlockSettings(this.JF.getActivity());
            System.setCloudAntispam(this.JF.getActivity(), false);
            this.JF.IC.setSummary(R.string.turn_on_smart_antispam_summary_off);
        }
        return true;
    }
}
