package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.protos.Tos.AcceptTosResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.GetTocHelper;
import com.google.android.finsky.utils.GetTocHelper.Listener;

public class TosAckedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            FinskyLog.w("Invalid Broadcast: requires extras.", new Object[0]);
            return;
        }
        Bundle args = intent.getExtras();
        String account = args.getString("TosAckedReceiver.account");
        boolean optIn = args.getBoolean("TosAckedReceiver.optIn");
        if (account == null) {
            FinskyLog.w("Invalid Broadcast: no account.", new Object[0]);
        } else {
            fetchToc(account, optIn);
        }
    }

    private void fetchToc(final String account, final boolean optIn) {
        DfeApi dfeApi = FinskyApp.get().getDfeApi(account);
        if (dfeApi == null) {
            FinskyLog.e("Could not get DFE API, returning.", new Object[0]);
        } else {
            GetTocHelper.getToc(dfeApi, false, new Listener() {
                public void onResponse(TocResponse response) {
                    DfeToc toc = new DfeToc(response);
                    FinskyApp.get().setToc(toc);
                    TosAckedReceiver.this.ackTos(account, optIn, toc);
                }

                public void onErrorResponse(VolleyError error) {
                    FinskyLog.e("Error fetching TOC: %s", error);
                }
            });
        }
    }

    private void ackTos(final String account, boolean optIn, final DfeToc toc) {
        DfeApi dfeApi = FinskyApp.get().getDfeApi(account);
        if (dfeApi == null) {
            FinskyLog.e("Could not get DFE API, returning.", new Object[0]);
        } else {
            dfeApi.acceptTos(toc.getTosToken(), Boolean.valueOf(optIn), new Response.Listener<AcceptTosResponse>() {
                public void onResponse(AcceptTosResponse response) {
                    FinskyPreferences.acceptedTosToken.get(account).put(toc.getTosToken());
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    FinskyLog.e("Error sending TOS acceptance: %s", error);
                }
            });
        }
    }
}
