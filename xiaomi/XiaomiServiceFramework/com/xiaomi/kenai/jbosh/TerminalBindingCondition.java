package com.xiaomi.kenai.jbosh;

import java.util.HashMap;
import java.util.Map;

final class TerminalBindingCondition {
    static final TerminalBindingCondition BAD_REQUEST;
    private static final Map<Integer, TerminalBindingCondition> CODE_TO_INSTANCE;
    private static final Map<String, TerminalBindingCondition> COND_TO_INSTANCE;
    static final TerminalBindingCondition HOST_GONE;
    static final TerminalBindingCondition HOST_UNKNOWN;
    static final TerminalBindingCondition IMPROPER_ADDRESSING;
    static final TerminalBindingCondition INTERNAL_SERVER_ERROR;
    static final TerminalBindingCondition ITEM_NOT_FOUND;
    static final TerminalBindingCondition OTHER_REQUEST;
    static final TerminalBindingCondition POLICY_VIOLATION;
    static final TerminalBindingCondition REMOTE_CONNECTION_FAILED;
    static final TerminalBindingCondition REMOTE_STREAM_ERROR;
    static final TerminalBindingCondition SEE_OTHER_URI;
    static final TerminalBindingCondition SYSTEM_SHUTDOWN;
    static final TerminalBindingCondition UNDEFINED_CONDITION;
    private final String cond;
    private final String msg;

    static {
        COND_TO_INSTANCE = new HashMap();
        CODE_TO_INSTANCE = new HashMap();
        BAD_REQUEST = createWithCode("bad-request", "The format of an HTTP header or binding element received from the client is unacceptable (e.g., syntax error).", Integer.valueOf(400));
        HOST_GONE = create("host-gone", "The target domain specified in the 'to' attribute or the target host or port specified in the 'route' attribute is no longer serviced by the connection manager.");
        HOST_UNKNOWN = create("host-unknown", "The target domain specified in the 'to' attribute or the target host or port specified in the 'route' attribute is unknown to the connection manager.");
        IMPROPER_ADDRESSING = create("improper-addressing", "The initialization element lacks a 'to' or 'route' attribute (or the attribute has no value) but the connection manager requires one.");
        INTERNAL_SERVER_ERROR = create("internal-server-error", "The connection manager has experienced an internal error that prevents it from servicing the request.");
        ITEM_NOT_FOUND = createWithCode("item-not-found", "(1) 'sid' is not valid, (2) 'stream' is not valid, (3) 'rid' is larger than the upper limit of the expected window, (4) connection manager is unable to resend response, (5) 'key' sequence is invalid.", Integer.valueOf(404));
        OTHER_REQUEST = create("other-request", "Another request being processed at the same time as this request caused the session to terminate.");
        POLICY_VIOLATION = createWithCode("policy-violation", "The client has broken the session rules (polling too frequently, requesting too frequently, sending too many simultaneous requests).", Integer.valueOf(403));
        REMOTE_CONNECTION_FAILED = create("remote-connection-failed", "The connection manager was unable to connect to, or unable to connect securely to, or has lost its connection to, the server.");
        REMOTE_STREAM_ERROR = create("remote-stream-error", "Encapsulated transport protocol error.");
        SEE_OTHER_URI = create("see-other-uri", "The connection manager does not operate at this URI (e.g., the connection manager accepts only SSL or TLS connections at some https: URI rather than the http: URI requested by the client).");
        SYSTEM_SHUTDOWN = create("system-shutdown", "The connection manager is being shut down. All active HTTP sessions are being terminated. No new sessions can be created.");
        UNDEFINED_CONDITION = create("undefined-condition", "Unknown or undefined error condition.");
    }

    private TerminalBindingCondition(String condition, String message) {
        this.cond = condition;
        this.msg = message;
    }

    private static TerminalBindingCondition create(String condition, String message) {
        return createWithCode(condition, message, null);
    }

    private static TerminalBindingCondition createWithCode(String condition, String message, Integer code) {
        if (condition == null) {
            throw new IllegalArgumentException("condition may not be null");
        } else if (message == null) {
            throw new IllegalArgumentException("message may not be null");
        } else if (COND_TO_INSTANCE.get(condition) != null) {
            throw new IllegalStateException("Multiple definitions of condition: " + condition);
        } else {
            TerminalBindingCondition result = new TerminalBindingCondition(condition, message);
            COND_TO_INSTANCE.put(condition, result);
            if (code != null) {
                if (CODE_TO_INSTANCE.get(code) != null) {
                    throw new IllegalStateException("Multiple definitions of code: " + code);
                }
                CODE_TO_INSTANCE.put(code, result);
            }
            return result;
        }
    }

    static TerminalBindingCondition forString(String condStr) {
        return (TerminalBindingCondition) COND_TO_INSTANCE.get(condStr);
    }

    static TerminalBindingCondition forHTTPResponseCode(int httpRespCode) {
        return (TerminalBindingCondition) CODE_TO_INSTANCE.get(Integer.valueOf(httpRespCode));
    }

    String getCondition() {
        return this.cond;
    }

    String getMessage() {
        return this.msg;
    }
}
