package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;

public class k {
    private String kV;
    private String[] kW;
    private g kX;

    private Uri a(Uri uri, Context context, String str, boolean z) throws l {
        try {
            boolean b = b(uri);
            if (b) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new l("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new l("Query parameter already exists: ms");
            }
            String a = z ? this.kX.a(context, str) : this.kX.a(context);
            return b ? b(uri, "dc_ms", a) : a(uri, "ms", a);
        } catch (UnsupportedOperationException e) {
            throw new l("Provided Uri is not in a valid state");
        }
    }

    private Uri a(Uri uri, String str, String str2) throws UnsupportedOperationException {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = uri2.indexOf("?adurl");
        }
        return indexOf != -1 ? Uri.parse(new StringBuilder(uri2.substring(0, indexOf + 1)).append(str).append("=").append(str2).append("&").append(uri2.substring(indexOf + 1)).toString()) : uri.buildUpon().appendQueryParameter(str, str2).build();
    }

    private Uri b(Uri uri, String str, String str2) {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf(";adurl");
        if (indexOf != -1) {
            return Uri.parse(new StringBuilder(uri2.substring(0, indexOf + 1)).append(str).append("=").append(str2).append(";").append(uri2.substring(indexOf + 1)).toString());
        }
        String encodedPath = uri.getEncodedPath();
        int indexOf2 = uri2.indexOf(encodedPath);
        return Uri.parse(new StringBuilder(uri2.substring(0, encodedPath.length() + indexOf2)).append(";").append(str).append("=").append(str2).append(";").append(uri2.substring(encodedPath.length() + indexOf2)).toString());
    }

    public g C() {
        return this.kX;
    }

    public void a(MotionEvent motionEvent) {
        this.kX.a(motionEvent);
    }

    public Uri b(Uri uri, Context context) throws l {
        try {
            return a(uri, context, uri.getQueryParameter("ai"), true);
        } catch (UnsupportedOperationException e) {
            throw new l("Provided Uri is not in a valid state");
        }
    }

    public boolean b(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.kV);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isGoogleAdUrl(Uri adUrl) {
        if (adUrl == null) {
            throw new NullPointerException();
        }
        try {
            String host = adUrl.getHost();
            for (String endsWith : this.kW) {
                if (host.endsWith(endsWith)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
