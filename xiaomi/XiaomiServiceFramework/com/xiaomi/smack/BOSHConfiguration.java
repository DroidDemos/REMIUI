package com.xiaomi.smack;

import android.text.TextUtils;
import com.xiaomi.network.Fallback;
import com.xiaomi.push.service.PushServiceConstants;
import java.net.URI;
import java.net.URISyntaxException;

public class BOSHConfiguration extends ConnectionConfiguration {
    private String file;
    private String host;
    private Fallback hostFallback;
    private boolean ssl;

    public BOSHConfiguration(boolean https, Fallback hostFallback, int port, String filePath, String serviceName, HttpRequestProxy httpProxy) {
        super(null, port, serviceName, httpProxy);
        this.hostFallback = null;
        this.host = PushServiceConstants.XMPP_BOSH_HOST;
        this.hostFallback = hostFallback;
        this.ssl = https;
        if (filePath == null) {
            filePath = "/";
        }
        this.file = filePath;
    }

    public boolean isUsingSSL() {
        return this.ssl;
    }

    public Fallback getHostFallback() {
        return this.hostFallback;
    }

    public void setFallback(Fallback fb) {
        if (fb != null) {
            this.hostFallback = fb;
            this.host = PushServiceConstants.XMPP_BOSH_HOST;
            if (!this.hostFallback.getHosts().isEmpty()) {
                String preferedHost = (String) this.hostFallback.getHosts().get(0);
                if (!TextUtils.isEmpty(preferedHost)) {
                    this.host = preferedHost;
                }
            }
        }
    }

    public String getCurrentHost() {
        return this.host;
    }

    public URI getURI() throws URISyntaxException {
        if (this.file.charAt(0) != '/') {
            this.file = '/' + this.file;
        }
        return new URI((this.ssl ? "https://" : "http://") + this.host + ":" + getPort() + this.file);
    }
}
