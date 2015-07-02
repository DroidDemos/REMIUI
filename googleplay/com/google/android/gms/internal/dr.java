package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

@fd
public final class dr {
    public static boolean a(Context context, dt dtVar, ea eaVar) {
        if (dtVar == null) {
            gw.w("No intent data for launcher overlay.");
            return false;
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(dtVar.url)) {
            gw.w("Open GMSG did not contain a URL.");
            return false;
        }
        if (TextUtils.isEmpty(dtVar.mimeType)) {
            intent.setData(Uri.parse(dtVar.url));
        } else {
            intent.setDataAndType(Uri.parse(dtVar.url), dtVar.mimeType);
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(dtVar.packageName)) {
            intent.setPackage(dtVar.packageName);
        }
        if (!TextUtils.isEmpty(dtVar.rI)) {
            String[] split = dtVar.rI.split("/", 2);
            if (split.length < 2) {
                gw.w("Could not parse component name from open GMSG: " + dtVar.rI);
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        try {
            gw.v("Launching an intent: " + intent);
            context.startActivity(intent);
            eaVar.af();
            return true;
        } catch (ActivityNotFoundException e) {
            gw.w(e.getMessage());
            return false;
        }
    }
}
