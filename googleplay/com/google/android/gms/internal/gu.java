package com.google.android.gms.internal;

import android.content.Context;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@fd
public final class gu extends gk {
    private final Context mContext;
    private final String mJ;
    private final String vf;
    private String wk;

    public gu(Context context, String str, String str2) {
        this.wk = null;
        this.mContext = context;
        this.mJ = str;
        this.vf = str2;
    }

    public gu(Context context, String str, String str2, String str3) {
        this.wk = null;
        this.mContext = context;
        this.mJ = str;
        this.vf = str2;
        this.wk = str3;
    }

    public void cx() {
        HttpURLConnection httpURLConnection;
        try {
            gw.v("Pinging URL: " + this.vf);
            httpURLConnection = (HttpURLConnection) new URL(this.vf).openConnection();
            if (this.wk == null) {
                gn.a(this.mContext, this.mJ, true, httpURLConnection);
            } else {
                gn.a(this.mContext, this.mJ, true, httpURLConnection, this.wk);
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                gw.w("Received non-success response code " + responseCode + " from pinging URL: " + this.vf);
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            gw.w("Error while parsing ping URL: " + this.vf + ". " + e.getMessage());
        } catch (IOException e2) {
            gw.w("Error while pinging URL: " + this.vf + ". " + e2.getMessage());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }
}
