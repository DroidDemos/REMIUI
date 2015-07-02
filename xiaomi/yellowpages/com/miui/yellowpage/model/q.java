package com.miui.yellowpage.model;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Pair;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Module;
import java.util.LinkedList;
import java.util.List;
import miui.preference.ValuePreference;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: AccountPreferences */
public class q {
    private static List Aa;
    private List Ab;

    static {
        Aa = new LinkedList();
        Aa.add(new Pair(Integer.valueOf(R.string.account_yellowpage_recent), new Intent("com.miui.yellowpage.action.VIEW_RECENT")));
        Aa.add(new Pair(Integer.valueOf(R.string.account_yellowpage_favorite), new Intent("com.miui.yellowpage.action.VIEW_FAVORITE")));
        Aa.add(new Pair(Integer.valueOf(R.string.account_order), new Intent("com.miui.yellowpage.action.ORDER")));
        Aa.add(new Pair(Integer.valueOf(R.string.account_address), new Intent("com.miui.yellowpage.action.MANAGE_ADDRESS")));
    }

    private q() {
    }

    public static q w(Context context, String str) {
        q qVar = new q();
        if (!(context == null && TextUtils.isEmpty(str))) {
            List linkedList = new LinkedList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    Module create = Module.create(context, jSONArray.getJSONObject(i));
                    if (create != null) {
                        Intent filterFirstValidResult = Module.filterFirstValidResult(context, create.getActions());
                        Preference preference = new Preference(context);
                        preference.setTitle(create.getTitle());
                        preference.setIntent(filterFirstValidResult);
                        preference.setOrder((i + 0) + 1);
                        linkedList.add(preference);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            qVar.Ab = linkedList;
        }
        return qVar;
    }

    public void a(Context context, PreferenceScreen preferenceScreen) {
        c(context, preferenceScreen);
        if (this.Ab != null && this.Ab.size() > 0 && preferenceScreen != null) {
            for (Preference addPreference : this.Ab) {
                preferenceScreen.addPreference(addPreference);
            }
        }
    }

    public static void b(Context context, PreferenceScreen preferenceScreen) {
        c(context, preferenceScreen);
        if (preferenceScreen != null) {
            int i = 1;
            for (Pair pair : Aa) {
                Preference preference = new Preference(context);
                preference.setIntent((Intent) pair.second);
                preference.setTitle(((Integer) pair.first).intValue());
                int i2 = i + 1;
                preference.setOrder(i);
                preferenceScreen.addPreference(preference);
                i = i2;
            }
        }
    }

    private static void c(Context context, PreferenceScreen preferenceScreen) {
        if (preferenceScreen != null) {
            ValuePreference valuePreference = new ValuePreference(context);
            valuePreference.setIntent(new Intent("android.settings.XIAOMI_ACCOUNT_SYNC_SETTINGS"));
            valuePreference.setKey("pref_account_info");
            valuePreference.setOrder(0);
            valuePreference.setTitle(R.string.account_type_name);
            preferenceScreen.addPreference(valuePreference);
        }
    }
}
