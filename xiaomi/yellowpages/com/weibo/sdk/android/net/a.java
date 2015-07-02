package com.weibo.sdk.android.net;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* compiled from: HttpManager */
class a implements X509TrustManager {
    final /* synthetic */ d hr;

    a(d dVar) {
        this.hr = dVar;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
