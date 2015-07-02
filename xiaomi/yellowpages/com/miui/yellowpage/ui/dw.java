package com.miui.yellowpage.ui;

import android.content.Intent;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.InternalWebActivity;

/* compiled from: SettingFragment */
class dw implements OnPreferenceClickListener {
    final /* synthetic */ dq JF;

    dw(dq dqVar) {
        this.JF = dqVar;
    }

    public boolean onPreferenceClick(Preference preference) {
        Intent intent = new Intent(this.JF.getActivity(), InternalWebActivity.class);
        intent.putExtra("web_title", this.JF.getActivity().getResources().getString(R.string.about_smart_antispam_summary));
        intent.putExtra("web_url", "http://web.huangye.miui.com/portal/html/yellowpage/about-telais.html");
        this.JF.getActivity().startActivity(intent);
        return false;
    }
}
