package com.weibo.sdk.android.net;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: HttpManager */
class d extends SSLSocketFactory {
    SSLContext sslContext;

    public d(KeyStore keyStore) {
        super(keyStore);
        this.sslContext = SSLContext.getInstance("TLS");
        a aVar = new a(this);
        this.sslContext.init(null, new TrustManager[]{aVar}, null);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.sslContext.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket createSocket() {
        return this.sslContext.getSocketFactory().createSocket();
    }
}
