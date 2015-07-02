package com.miui.yellowpage.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.reference.RefMethods.Settings.System;
import com.miui.yellowpage.base.utils.Permission;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.utils.D;
import com.miui.yellowpage.utils.x;

/* compiled from: SettingFragment */
public class dq extends PreferenceFragment {
    private PreferenceCategory IB;
    private CheckBoxPreference IC;
    private CheckBoxPreference ID;
    private CheckBoxPreference IE;
    private PreferenceScreen IF;
    private PreferenceScreen IG;
    private PreferenceScreen IH;
    private Dialog II;
    private OnPreferenceClickListener IJ;
    private OnPreferenceClickListener IK;
    private OnPreferenceClickListener IL;
    private OnPreferenceChangeListener IM;
    private OnPreferenceChangeListener IN;
    private OnPreferenceChangeListener IO;
    private OnClickListener IP;

    public dq() {
        this.IJ = new dw(this);
        this.IK = new dx(this);
        this.IL = new dy(this);
        this.IM = new dA(this);
        this.IN = new dz(this);
        this.IO = new dC(this);
        this.IP = new dB(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.settings);
        this.IB = (PreferenceCategory) findPreference("pref_category_setting");
        this.IC = (CheckBoxPreference) findPreference("pref_enable_smart_antispam");
        this.IC.setOnPreferenceChangeListener(this.IM);
        this.ID = (CheckBoxPreference) findPreference("pref_enable_fraud_num_identification_online");
        this.ID.setOnPreferenceChangeListener(this.IN);
        this.IE = (CheckBoxPreference) findPreference("pref_show_user_notice_yp_detail");
        this.IE.setOnPreferenceChangeListener(this.IO);
        this.IF = (PreferenceScreen) findPreference("pref_about_smart_antispam");
        this.IF.setOnPreferenceClickListener(this.IJ);
        this.IG = (PreferenceScreen) findPreference("pref_about_fraud_num_identification_online");
        this.IG.setOnPreferenceClickListener(this.IK);
        this.IH = (PreferenceScreen) findPreference("pref_about_telephone_fraud");
        this.IH.setOnPreferenceClickListener(this.IL);
    }

    public void onResume() {
        boolean z;
        int i = 1;
        int i2 = 0;
        super.onResume();
        boolean isCloudAntispamEnable = System.isCloudAntispamEnable(getActivity());
        boolean z2 = Preference.getBoolean(getActivity(), "pref_enable_fraud_num_identification_online", false);
        this.IC.setChecked(isCloudAntispamEnable);
        this.IC.setSummary(isCloudAntispamEnable ? R.string.turn_on_smart_antispam_summary_on : R.string.turn_on_smart_antispam_summary_off);
        this.ID.setChecked(z2);
        Preference.setBoolean(getActivity(), "pref_enable_fraud_num_identification_online", z2);
        this.ID.setSummary(z2 ? R.string.upload_and_verify_fraud_num_on : R.string.upload_and_verify_fraud_num_off);
        CheckBoxPreference checkBoxPreference = this.IE;
        if (Permission.networkingAllowedPermanently(getActivity())) {
            z = false;
        } else {
            z = true;
        }
        checkBoxPreference.setChecked(z);
        if (!x.X(getActivity())) {
            this.IB.removePreference(this.IE);
        }
        z = System.isNeverRemindSmartAntispamEnable(getActivity());
        if (x.X(getActivity()) || !Preference.getBoolean(getActivity(), "pref_show_user_notice_enable_identify", false)) {
            i = 0;
        }
        if (!isCloudAntispamEnable && r1 == 0 && !z) {
            if (this.II == null || !this.II.isShowing()) {
                int i3 = x.X(getActivity()) ? R.string.user_notice_identify_summary_format_cta : R.string.user_notice_identify_summary_format;
                Context activity = getActivity();
                OnClickListener onClickListener = this.IP;
                if (x.X(getActivity())) {
                    i2 = R.string.do_not_remind_me;
                }
                this.II = D.a(activity, R.string.user_notice_title, i3, onClickListener, null, i2);
            }
        }
    }
}
