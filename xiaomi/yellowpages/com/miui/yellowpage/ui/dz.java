package com.miui.yellowpage.ui;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import com.miui.yellowpage.R;

/* compiled from: SettingFragment */
class dz implements OnPreferenceChangeListener {
    final /* synthetic */ dq JF;

    dz(dq dqVar) {
        this.JF = dqVar;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        com.miui.yellowpage.base.utils.Preference.setBoolean(this.JF.getActivity(), "pref_enable_fraud_num_identification_online", booleanValue);
        this.JF.ID.setSummary(booleanValue ? R.string.upload_and_verify_fraud_num_on : R.string.upload_and_verify_fraud_num_off);
        return true;
    }
}
