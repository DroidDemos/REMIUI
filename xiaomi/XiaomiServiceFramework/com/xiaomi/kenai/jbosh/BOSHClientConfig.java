package com.xiaomi.kenai.jbosh;

import java.net.URI;
import javax.net.ssl.SSLContext;

public final class BOSHClientConfig {
    private final boolean compressionEnabled;
    private final String from;
    private final String lang;
    private final String proxyHost;
    private final int proxyPort;
    private final String route;
    private final SSLContext sslContext;
    private final String to;
    private final URI uri;

    public static final class Builder {
        private Boolean bCompression;
        private final String bDomain;
        private String bFrom;
        private String bLang;
        private String bProxyHost;
        private int bProxyPort;
        private String bRoute;
        private SSLContext bSSLContext;
        private final URI bURI;

        private Builder(URI cmURI, String domain) {
            this.bURI = cmURI;
            this.bDomain = domain;
        }

        public static Builder create(URI cmURI, String domain) {
            if (cmURI == null) {
                throw new IllegalArgumentException("Connection manager URI must not be null");
            } else if (domain == null) {
                throw new IllegalArgumentException("Target domain must not be null");
            } else {
                String scheme = cmURI.getScheme();
                if ("http".equals(scheme) || "https".equals(scheme)) {
                    return new Builder(cmURI, domain);
                }
                throw new IllegalArgumentException("Only 'http' and 'https' URI are allowed");
            }
        }

        public static Builder create(BOSHClientConfig cfg) {
            Builder result = new Builder(cfg.getURI(), cfg.getTo());
            result.bFrom = cfg.getFrom();
            result.bLang = cfg.getLang();
            result.bRoute = cfg.getRoute();
            result.bProxyHost = cfg.getProxyHost();
            result.bProxyPort = cfg.getProxyPort();
            result.bSSLContext = cfg.getSSLContext();
            result.bCompression = Boolean.valueOf(cfg.isCompressionEnabled());
            return result;
        }

        public Builder setFrom(String id) {
            if (id == null) {
                throw new IllegalArgumentException("Client ID must not be null");
            }
            this.bFrom = id;
            return this;
        }

        public Builder setXMLLang(String lang) {
            if (lang == null) {
                throw new IllegalArgumentException("Default language ID must not be null");
            }
            this.bLang = lang;
            return this;
        }

        public Builder setRoute(String protocol, String host, int port) {
            if (protocol == null) {
                throw new IllegalArgumentException("Protocol cannot be null");
            } else if (protocol.contains(":")) {
                throw new IllegalArgumentException("Protocol cannot contain the ':' character");
            } else if (host == null) {
                throw new IllegalArgumentException("Host cannot be null");
            } else if (host.contains(":")) {
                throw new IllegalArgumentException("Host cannot contain the ':' character");
            } else if (port <= 0) {
                throw new IllegalArgumentException("Port number must be > 0");
            } else {
                this.bRoute = protocol + ":" + host + ":" + port;
                return this;
            }
        }

        public Builder setProxy(String hostName, int port) {
            if (hostName == null || hostName.length() == 0) {
                throw new IllegalArgumentException("Proxy host name cannot be null or empty");
            } else if (port <= 0) {
                throw new IllegalArgumentException("Proxy port must be > 0");
            } else {
                this.bProxyHost = hostName;
                this.bProxyPort = port;
                return this;
            }
        }

        public Builder setSSLContext(SSLContext ctx) {
            if (ctx == null) {
                throw new IllegalArgumentException("SSL context cannot be null");
            }
            this.bSSLContext = ctx;
            return this;
        }

        public Builder setCompressionEnabled(boolean enabled) {
            this.bCompression = Boolean.valueOf(enabled);
            return this;
        }

        public BOSHClientConfig build() {
            String lang;
            int port;
            boolean compression;
            if (this.bLang == null) {
                lang = "en";
            } else {
                lang = this.bLang;
            }
            if (this.bProxyHost == null) {
                port = 0;
            } else {
                port = this.bProxyPort;
            }
            if (this.bCompression == null) {
                compression = false;
            } else {
                compression = this.bCompression.booleanValue();
            }
            return new BOSHClientConfig(this.bURI, this.bDomain, this.bFrom, lang, this.bRoute, this.bProxyHost, port, this.bSSLContext, compression);
        }
    }

    private BOSHClientConfig(URI cURI, String cDomain, String cFrom, String cLang, String cRoute, String cProxyHost, int cProxyPort, SSLContext cSSLContext, boolean cCompression) {
        this.uri = cURI;
        this.to = cDomain;
        this.from = cFrom;
        this.lang = cLang;
        this.route = cRoute;
        this.proxyHost = cProxyHost;
        this.proxyPort = cProxyPort;
        this.sslContext = cSSLContext;
        this.compressionEnabled = cCompression;
    }

    public URI getURI() {
        return this.uri;
    }

    public String getTo() {
        return this.to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getLang() {
        return this.lang;
    }

    public String getRoute() {
        return this.route;
    }

    public String getProxyHost() {
        return this.proxyHost;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public SSLContext getSSLContext() {
        return this.sslContext;
    }

    boolean isCompressionEnabled() {
        return this.compressionEnabled;
    }
}
