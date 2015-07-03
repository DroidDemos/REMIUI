package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.account.Constants;
import com.xiaomi.account.R;
import com.xiaomi.account.data.SnsAccountInfo;
import com.xiaomi.account.utils.SysHelper;
import miui.accounts.ExtraAccountManager;
import miui.os.Build;

public class SnsListFragment extends PreferenceFragment {
    private static final boolean DEBUG = true;
    private static final String PREF_FACEBOOK = "pref_facebook";
    private static final String PREF_QQ = "pref_qq";
    private static final String PREF_SINA_WEIBO = "pref_sina_weibo";
    private static final String PREF_SNS_LIST = "pref_sns_list";
    private static final String TAG = "SnsListFragment";
    private Account mAccount;
    private IntentFilter mIntentFilter;
    private Preference mPrefFacebook;
    private Preference mPrefQQ;
    private Preference mPrefSinaWeiBo;
    private BroadcastReceiver mReceiver;

    public SnsListFragment() {
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (Constants.ACTION_XIAOMI_SNS_INFO_CHANGED.equals(intent.getAction())) {
                    SnsListFragment.this.updateSnsState();
                }
            }
        };
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SysHelper.setMiuiActionBarTitle(getActivity(), getString(R.string.bind_settings));
        View v = inflater.inflate(R.layout.sns_list, container, false);
        addPreferencesFromResource(R.xml.sns_list_preference);
        this.mPrefSinaWeiBo = findPreference(PREF_SINA_WEIBO);
        this.mPrefQQ = findPreference(PREF_QQ);
        this.mPrefFacebook = findPreference(PREF_FACEBOOK);
        if (Build.IS_INTERNATIONAL_BUILD) {
            ((PreferenceCategory) findPreference(PREF_SNS_LIST)).removePreference(this.mPrefSinaWeiBo);
            ((PreferenceCategory) findPreference(PREF_SNS_LIST)).removePreference(this.mPrefQQ);
        } else {
            ((PreferenceCategory) findPreference(PREF_SNS_LIST)).removePreference(this.mPrefFacebook);
        }
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mAccount = ExtraAccountManager.getXiaomiAccount(getActivity());
        if (this.mAccount == null) {
            Log.e(TAG, "no account contained");
            finishActivity();
            return;
        }
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction(Constants.ACTION_XIAOMI_SNS_INFO_CHANGED);
    }

    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if (ExtraAccountManager.getXiaomiAccount(activity) == null) {
            Log.d(TAG, "no xiaomi account");
            finishActivity();
            return;
        }
        activity.registerReceiver(this.mReceiver, this.mIntentFilter);
        updateSnsState();
    }

    public void onPause() {
        super.onPause();
        Activity activity = getActivity();
        if (ExtraAccountManager.getXiaomiAccount(activity) != null) {
            activity.unregisterReceiver(this.mReceiver);
        }
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferences, Preference preference) {
        Activity activity = getActivity();
        AccountManager am = AccountManager.get(activity);
        String accessToken = null;
        String snsType = null;
        if (PREF_SINA_WEIBO.equals(preference.getKey())) {
            accessToken = am.getUserData(this.mAccount, Constants.EXTRA_SINA_WEIBO_ACCESSTOKEN);
            snsType = SnsAccountInfo.SINA_SNS_TYPE;
        } else if (PREF_QQ.equals(preference.getKey())) {
            accessToken = am.getUserData(this.mAccount, Constants.EXTRA_QQ_ACCESSTOKEN);
            snsType = SnsAccountInfo.QQ_SNS_TYPE;
        } else if (PREF_FACEBOOK.equals(preference.getKey())) {
            accessToken = am.getUserData(this.mAccount, Constants.EXTRA_FACEBOOK_ACCESSTOKEN);
            snsType = SnsAccountInfo.FB_SNS_TYPE;
        }
        if (TextUtils.isEmpty(accessToken)) {
            SnsAddAccountActivity.start(activity, snsType);
        } else {
            SnsAccountActivity.start(activity, snsType);
        }
        return super.onPreferenceTreeClick(preferences, preference);
    }

    private void updateSnsState() {
        updateSnsState(this.mPrefSinaWeiBo, Constants.EXTRA_SINA_WEIBO_ACCESSTOKEN);
        updateSnsState(this.mPrefQQ, Constants.EXTRA_QQ_ACCESSTOKEN);
        updateSnsState(this.mPrefFacebook, Constants.EXTRA_FACEBOOK_ACCESSTOKEN);
    }

    private void updateSnsState(Preference preference, String snsType) {
        preference.setSummary(TextUtils.isEmpty(AccountManager.get(getActivity()).getUserData(this.mAccount, snsType)) ? R.string.nobind : R.string.binded);
    }

    private void finishActivity() {
        getActivity().finish();
    }
}
