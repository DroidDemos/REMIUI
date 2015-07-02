package com.google.android.gms.internal;

import android.os.Bundle;

@fd
public final class bs {
    public static jj<String> pi;
    public static jj<String> pj;
    public static jj<Boolean> pk;
    public static jj<Boolean> pl;
    public static jj<Boolean> pm;
    public static jj<String> pn;
    public static jj<Boolean> po;
    public static jj<String> pp;
    public static jj<Boolean> pq;
    public static jj<Integer> pr;
    public static jj<Integer> ps;
    public static jj<Integer> pt;
    public static jj<Integer> pu;
    public static jj<Integer> pv;
    private static final Bundle pw;
    private static boolean px;

    static {
        pw = new Bundle();
        px = false;
        pi = b("gads:sdk_core_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html");
        pj = b("gads:sdk_core_experiment_id", (String) null);
        pk = c("gads:sdk_crash_report_enabled", false);
        pl = c("gads:sdk_crash_report_full_stacktrace", false);
        pm = c("gads:block_autoclicks", false);
        pn = b("gads:block_autoclicks_experiment_id", (String) null);
        px = true;
        pq = c("gads:enable_content_fetching", false);
        pr = a("gads:content_length_weight", 1);
        ps = a("gads:content_age_weight", 1);
        pt = a("gads:min_content_len", 11);
        pu = a("gads:fingerprint_number", 10);
        pv = a("gads:sleep_sec", 10);
        po = c("gads:spam_app_context:enabled", false);
        pp = b("gads:spam_app_context:experiment_id", (String) null);
    }

    private static jj<Integer> a(String str, int i) {
        pw.putInt(str, i);
        return jj.a(str, Integer.valueOf(i));
    }

    private static jj<String> b(String str, String str2) {
        pw.putString(str, str2);
        return jj.s(str, str2);
    }

    public static Bundle by() {
        return pw;
    }

    private static jj<Boolean> c(String str, boolean z) {
        pw.putBoolean(str, z);
        return jj.j(str, z);
    }
}
