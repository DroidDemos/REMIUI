package com.xiaomi.account.utils;

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
}
