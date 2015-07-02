package com.xiaomi.b.a;

import java.util.HashMap;
import java.util.Map;

final class n {
    private static final Map o;
    static final n ol;
    static final n om;
    static final n on;
    static final n oo;
    static final n oq;
    static final n or;
    static final n os;
    static final n ot;
    static final n ou;
    static final n ov;
    static final n ow;
    static final n ox;
    static final n oy;
    private static final Map oz;
    private final String p;
    private final String q;

    static {
        oz = new HashMap();
        o = new HashMap();
        ol = a("bad-request", "The format of an HTTP header or binding element received from the client is unacceptable (e.g., syntax error).", Integer.valueOf(400));
        om = k("host-gone", "The target domain specified in the 'to' attribute or the target host or port specified in the 'route' attribute is no longer serviced by the connection manager.");
        on = k("host-unknown", "The target domain specified in the 'to' attribute or the target host or port specified in the 'route' attribute is unknown to the connection manager.");
        oo = k("improper-addressing", "The initialization element lacks a 'to' or 'route' attribute (or the attribute has no value) but the connection manager requires one.");
        oq = k("internal-server-error", "The connection manager has experienced an internal error that prevents it from servicing the request.");
        or = a("item-not-found", "(1) 'sid' is not valid, (2) 'stream' is not valid, (3) 'rid' is larger than the upper limit of the expected window, (4) connection manager is unable to resend response, (5) 'key' sequence is invalid.", Integer.valueOf(404));
        os = k("other-request", "Another request being processed at the same time as this request caused the session to terminate.");
        ot = a("policy-violation", "The client has broken the session rules (polling too frequently, requesting too frequently, sending too many simultaneous requests).", Integer.valueOf(403));
        ou = k("remote-connection-failed", "The connection manager was unable to connect to, or unable to connect securely to, or has lost its connection to, the server.");
        ov = k("remote-stream-error", "Encapsulated transport protocol error.");
        ow = k("see-other-uri", "The connection manager does not operate at this URI (e.g., the connection manager accepts only SSL or TLS connections at some https: URI rather than the http: URI requested by the client).");
        ox = k("system-shutdown", "The connection manager is being shut down. All active HTTP sessions are being terminated. No new sessions can be created.");
        oy = k("undefined-condition", "Unknown or undefined error condition.");
    }

    private n(String str, String str2) {
        this.p = str;
        this.q = str2;
    }

    private static n a(String str, String str2, Integer num) {
        if (str == null) {
            throw new IllegalArgumentException("condition may not be null");
        } else if (str2 == null) {
            throw new IllegalArgumentException("message may not be null");
        } else if (oz.get(str) != null) {
            throw new IllegalStateException("Multiple definitions of condition: " + str);
        } else {
            n nVar = new n(str, str2);
            oz.put(str, nVar);
            if (num != null) {
                if (o.get(num) != null) {
                    throw new IllegalStateException("Multiple definitions of code: " + num);
                }
                o.put(num, nVar);
            }
            return nVar;
        }
    }

    static n aa(String str) {
        return (n) oz.get(str);
    }

    private static n k(String str, String str2) {
        return a(str, str2, null);
    }

    static n y(int i) {
        return (n) o.get(Integer.valueOf(i));
    }

    String a() {
        return this.p;
    }

    String b() {
        return this.q;
    }
}
