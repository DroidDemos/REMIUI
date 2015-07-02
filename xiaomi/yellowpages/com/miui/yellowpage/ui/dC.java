package com.miui.yellowpage.ui;

import android.content.Context;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import com.miui.yellowpage.base.utils.Permission;

/* compiled from: SettingFragment */
class dC implements OnPreferenceChangeListener {
    final /* synthetic */ dq JF;

    dC(dq dqVar) {
        this.JF = dqVar;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean z;
        boolean z2 = false;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Context activity = this.JF.getActivity();
        if (booleanValue) {
            z = false;
        } else {
            z = true;
        }
        Permission.setNetworkingAllowedPermanently(activity, z);
        Context activity2 = this.JF.getActivity();
        if (!booleanValue) {
            z2 = true;
        }
        Permission.setNetworkingAllowedTemporarily(activity2, z2);
        return true;
    }
}
