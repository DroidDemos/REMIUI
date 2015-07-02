package com.xiaomi.smack;

import com.xiaomi.channel.commonutils.misc.BuildSettings;
import java.util.Map;

public class ConnectionConfiguration implements Cloneable {
    public static final int CONNECT_STATUS_CONNECTED = 1;
    public static final int CONNECT_STATUS_CONNECTING = 0;
    public static final int CONNECT_STATUS_DISCONNECT = 2;
    public static final String PREF_NETWORK_DIAGNOSE = "network_diagnose";
    public static final String PREF_NETWORK_DIAG_RESULT = "logs_network_diag";
    public static final String XMPP_SERVER_HOST_ONEBOX = "10.237.12.17";
    public static final String XMPP_SERVER_HOST_P = "app.chat.xiaomi.net";
    public static final String XMPP_SERVER_HOST_SANDBOX = "sandbox.xmpush.xiaomi.com";
    public static final String XMPP_SERVER_HOST_T = "10.237.12.2";
    private String connectionPoint;
    private boolean debuggerEnabled;
    private String host;
    private HttpRequestProxy httpProxy;
    private int port;
    private boolean reconnectionAllowed;
    private String resource;
    private String serviceName;
    private String sid;
    private String token;
    private String username;

    public static final String getXmppServerHost() {
        if (BuildSettings.IsTestBuild) {
            return XMPP_SERVER_HOST_T;
        }
        if (BuildSettings.IsSandBoxBuild()) {
            return XMPP_SERVER_HOST_SANDBOX;
        }
        if (BuildSettings.IsOneBoxBuild()) {
            return XMPP_SERVER_HOST_ONEBOX;
        }
        if (BuildSettings.IsDebugBuild) {
            return "58.68.235.106";
        }
        return XMPP_SERVER_HOST_P;
    }

    public ConnectionConfiguration(Map<String, Integer> hosts, int port, String serviceName, HttpRequestProxy httpProxy) {
        this.debuggerEnabled = Connection.DEBUG_ENABLED;
        this.reconnectionAllowed = true;
        init(hosts, port, serviceName, httpProxy);
    }

    public ConnectionConfiguration(Map<String, Integer> hosts, int port, HttpRequestProxy httpProxy) {
        this.debuggerEnabled = Connection.DEBUG_ENABLED;
        this.reconnectionAllowed = true;
        init(hosts, port, "", httpProxy);
    }

    private void init(Map<String, Integer> map, int port, String serviceName, HttpRequestProxy httpProxy) {
        this.host = getXmppServerHost();
        this.port = port;
        this.serviceName = serviceName;
        this.httpProxy = httpProxy;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setConnectionPoint(String connPt) {
        this.connectionPoint = connPt;
    }

    public String getConnectionPoint() {
        return this.connectionPoint;
    }

    public HttpRequestProxy getHttpRequestProxy() {
        return this.httpProxy;
    }

    public int getPort() {
        return this.port;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isDebuggerEnabled() {
        return this.debuggerEnabled;
    }

    public void setDebuggerEnabled(boolean debuggerEnabled) {
        this.debuggerEnabled = debuggerEnabled;
    }

    public void setReconnectionAllowed(boolean isAllowed) {
        this.reconnectionAllowed = isAllowed;
    }

    public boolean isReconnectionAllowed() {
        return this.reconnectionAllowed;
    }

    public synchronized String getUsername() {
        return this.username;
    }

    synchronized String getSid() {
        return this.sid;
    }

    synchronized String getToken() {
        return this.token;
    }

    synchronized String getResource() {
        return this.resource;
    }

    public synchronized void setLoginInfo(String username, String sid, String token, String resource) {
        this.username = username;
        this.sid = sid;
        this.token = token;
        this.resource = resource;
    }
}
