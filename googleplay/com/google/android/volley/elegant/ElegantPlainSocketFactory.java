package com.google.android.volley.elegant;

import com.android.volley.VolleyLog;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public final class ElegantPlainSocketFactory implements SocketFactory {
    private static final ElegantPlainSocketFactory DEFAULT_FACTORY;
    private final HostNameResolver nameResolver;

    static {
        DEFAULT_FACTORY = new ElegantPlainSocketFactory();
    }

    public static ElegantPlainSocketFactory getSocketFactory() {
        return DEFAULT_FACTORY;
    }

    public ElegantPlainSocketFactory(HostNameResolver nameResolver) {
        this.nameResolver = nameResolver;
    }

    public ElegantPlainSocketFactory() {
        this(null);
    }

    public Socket createSocket() {
        return new Socket();
    }

    public Socket connectSocket(Socket sock, String host, int port, InetAddress localAddress, int localPort, HttpParams params) throws IOException {
        InetSocketAddress remoteAddress;
        if (host == null) {
            throw new IllegalArgumentException("Target host may not be null.");
        } else if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null.");
        } else {
            if (sock == null) {
                sock = createSocket();
            }
            if (localAddress != null || localPort > 0) {
                if (localPort < 0) {
                    localPort = 0;
                }
                sock.bind(new InetSocketAddress(localAddress, localPort));
            }
            int timeout = HttpConnectionParams.getConnectionTimeout(params);
            if (this.nameResolver != null) {
                remoteAddress = new InetSocketAddress(this.nameResolver.resolve(host), port);
            } else {
                remoteAddress = new InetSocketAddress(host, port);
            }
            try {
                long startTime = System.currentTimeMillis();
                sock.connect(remoteAddress, timeout);
                long totalDuration = System.currentTimeMillis() - startTime;
                VolleyLog.v("Established connection to [host=%s] in [%s ms]", host, Long.valueOf(totalDuration));
                return sock;
            } catch (SocketTimeoutException e) {
                throw new ConnectTimeoutException("Connect to " + remoteAddress + " timed out");
            }
        }
    }

    public final boolean isSecure(Socket sock) throws IllegalArgumentException {
        if (sock == null) {
            throw new IllegalArgumentException("Socket may not be null.");
        } else if (sock.getClass() != Socket.class) {
            throw new IllegalArgumentException("Socket not created by this factory.");
        } else if (!sock.isClosed()) {
            return false;
        } else {
            throw new IllegalArgumentException("Socket is closed.");
        }
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return ElegantPlainSocketFactory.class.hashCode();
    }
}
