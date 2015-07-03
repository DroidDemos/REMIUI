package com.xiaomi.passport.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class FriendlyFragmentUtils {
    public static void addFragment(FragmentManager fm, int containerId, Fragment f) {
        String tag = f.getClass().getName();
        Fragment old = fm.findFragmentByTag(tag);
        FragmentTransaction transaction = fm.beginTransaction();
        if (old != null) {
            transaction.replace(containerId, f, tag);
        } else {
            transaction.add(containerId, f, tag);
        }
        transaction.commit();
    }

    public static void popUpFragment(Fragment f) {
        Activity activity = f.getActivity();
        FragmentManager fm = activity.getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            activity.finish();
        }
    }
}
