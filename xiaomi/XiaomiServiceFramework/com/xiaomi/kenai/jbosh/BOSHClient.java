package com.xiaomi.kenai.jbosh;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.kenai.jbosh.ComposableBody.Builder;
import com.xiaomi.push.service.PushServiceConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class BOSHClient {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final boolean ASSERTIONS;
    private static final int DEFAULT_EMPTY_REQUEST_DELAY = 100;
    private static final int DEFAULT_PAUSE_MARGIN = 500;
    private static final int EMPTY_REQUEST_DELAY;
    private static final String ERROR = "error";
    private static final String INTERRUPTED = "Interrupted";
    private static final long MAX_MULTI_THREAD_EXISTING = 30000;
    private static final String NULL_LISTENER = "Listener may not be null";
    private static final int PAUSE_MARGIN;
    private static final String TERMINATE = "terminate";
    private static final String UNHANDLED = "Unhandled Exception";
    private final BOSHClientConfig cfg;
    private CMSessionParams cmParams;
    private final Set<BOSHClientConnListener> connListeners;
    private Context context;
    private ScheduledFuture<?> emptyRequestFuture;
    private final Runnable emptyRequestRunnable;
    private volatile long emptyRid;
    private Queue<HTTPExchange> exchanges;
    private ThreadPoolExecutor executor;
    private long expectedResponseRid;
    private final HTTPSender httpSender;
    private volatile long lastProcessStartedTime;
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private List<ComposableBody> pendingRequestAcks;
    private SortedSet<Long> pendingResponseAcks;
    private final RequestIDSequence requestIDSeq;
    private final Set<BOSHClientRequestListener> requestListeners;
    private Long responseAck;
    private final Set<BOSHClientResponseListener> responseListeners;
    private final Condition ridForHold;
    private final ScheduledExecutorService schedExec;

    static {
        boolean z;
        if (BOSHClient.class.desiredAssertionStatus()) {
            z = ASSERTIONS;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
        EMPTY_REQUEST_DELAY = Integer.getInteger(BOSHClient.class.getName() + ".emptyRequestDelay", DEFAULT_EMPTY_REQUEST_DELAY).intValue();
        PAUSE_MARGIN = Integer.getInteger(BOSHClient.class.getName() + ".pauseMargin", DEFAULT_PAUSE_MARGIN).intValue();
        String prop = BOSHClient.class.getSimpleName() + ".assertionsEnabled";
        boolean enabled = ASSERTIONS;
        if (System.getProperty(prop) != null) {
            enabled = Boolean.getBoolean(prop);
        } else if (!$assertionsDisabled) {
            enabled = true;
            if (1 == null) {
                throw new AssertionError();
            }
        }
        ASSERTIONS = enabled;
    }

    private BOSHClient(BOSHClientConfig sessCfg, Context context) {
        this.connListeners = new CopyOnWriteArraySet();
        this.requestListeners = new CopyOnWriteArraySet();
        this.responseListeners = new CopyOnWriteArraySet();
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        this.ridForHold = this.lock.newCondition();
        this.expectedResponseRid = 0;
        this.emptyRequestRunnable = new Runnable() {
            public void run() {
                BOSHClient.this.sendEmptyRequest();
            }
        };
        this.httpSender = new ApacheHTTPSender();
        this.requestIDSeq = new RequestIDSequence();
        this.schedExec = Executors.newSingleThreadScheduledExecutor();
        this.exchanges = new LinkedList();
        this.pendingResponseAcks = new TreeSet();
        this.responseAck = Long.valueOf(-1);
        this.pendingRequestAcks = new ArrayList();
        this.emptyRid = 0;
        this.lastProcessStartedTime = 0;
        this.cfg = sessCfg;
        this.context = context.getApplicationContext();
        init();
    }

    public static BOSHClient create(BOSHClientConfig clientCfg, Context context) {
        if (clientCfg != null) {
            return new BOSHClient(clientCfg, context);
        }
        throw new IllegalArgumentException("Client configuration may not be null");
    }

    public BOSHClientConfig getBOSHClientConfig() {
        return this.cfg;
    }

    public void addBOSHClientConnListener(BOSHClientConnListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException(NULL_LISTENER);
        }
        this.connListeners.add(listener);
    }

    public void removeBOSHClientConnListener(BOSHClientConnListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException(NULL_LISTENER);
        }
        this.connListeners.remove(listener);
    }

    public void addBOSHClientRequestListener(BOSHClientRequestListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException(NULL_LISTENER);
        }
        this.requestListeners.add(listener);
    }

    public void removeBOSHClientRequestListener(BOSHClientRequestListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException(NULL_LISTENER);
        }
        this.requestListeners.remove(listener);
    }

    public void addBOSHClientResponseListener(BOSHClientResponseListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException(NULL_LISTENER);
        }
        this.responseListeners.add(listener);
    }

    public void removeBOSHClientResponseListener(BOSHClientResponseListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException(NULL_LISTENER);
        }
        this.responseListeners.remove(listener);
    }

    public void send(ComposableBody body) throws BOSHException {
        assertUnlocked();
        if (body == null) {
            throw new IllegalArgumentException("Message body may not be null");
        }
        this.lock.lock();
        if (isWorking() || isTermination(body)) {
            try {
                long rid = this.requestIDSeq.getNextRID();
                if (TextUtils.isEmpty(body.getPayloadXML())) {
                    this.emptyRid = rid;
                }
                ComposableBody request = body;
                CMSessionParams params = this.cmParams;
                if (params == null && this.exchanges.isEmpty()) {
                    request = applySessionCreationRequest(rid, body);
                } else {
                    request = applySessionData(rid, body);
                    if (this.cmParams.isAckingRequests()) {
                        this.pendingRequestAcks.add(request);
                    }
                }
                HTTPExchange exch = new HTTPExchange(request);
                addNewExchange(exch);
                this.notEmpty.signalAll();
                clearEmptyRequest();
                AbstractBody finalReq = exch.getRequest();
                exch.setHTTPResponse(this.httpSender.send(params, finalReq, this.context));
                fireRequestSent(finalReq);
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new BOSHException("Cannot send message when session is closed");
        }
    }

    private void addNewExchange(HTTPExchange exch) {
        this.exchanges.add(exch);
        this.executor.execute(new Runnable() {
            public void run() {
                /* JADX: method processing error */
/*
                Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r3 = this;
            L_0x0000:
                r0 = com.xiaomi.kenai.jbosh.BOSHClient.this;	 Catch:{ all -> 0x003f }
                r0 = r0.lock;	 Catch:{ all -> 0x003f }
                r0.lock();	 Catch:{ all -> 0x003f }
                r0 = com.xiaomi.kenai.jbosh.BOSHClient.this;	 Catch:{ all -> 0x003f }
                r0 = r0.exchanges;	 Catch:{ all -> 0x003f }
                if (r0 == 0) goto L_0x001d;	 Catch:{ all -> 0x003f }
            L_0x0011:
                r0 = com.xiaomi.kenai.jbosh.BOSHClient.this;	 Catch:{ all -> 0x003f }
                r0 = r0.exchanges;	 Catch:{ all -> 0x003f }
                r0 = r0.isEmpty();	 Catch:{ all -> 0x003f }
                if (r0 == 0) goto L_0x0027;
            L_0x001d:
                r0 = com.xiaomi.kenai.jbosh.BOSHClient.this;
                r0 = r0.lock;
                r0.unlock();
                return;
            L_0x0027:
                r0 = com.xiaomi.kenai.jbosh.BOSHClient.this;
                r0 = r0.lock;
                r0.unlock();
                r0 = com.xiaomi.kenai.jbosh.BOSHClient.this;
                r1 = java.lang.System.currentTimeMillis();
                r0.lastProcessStartedTime = r1;
                r0 = com.xiaomi.kenai.jbosh.BOSHClient.this;
                r0.processMessages();
                goto L_0x0000;
            L_0x003f:
                r0 = move-exception;
                r1 = com.xiaomi.kenai.jbosh.BOSHClient.this;
                r1 = r1.lock;
                r1.unlock();
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.kenai.jbosh.BOSHClient.2.run():void");
            }
        });
    }

    public boolean pause() {
        assertUnlocked();
        this.lock.lock();
        try {
            if (this.cmParams == null) {
                return ASSERTIONS;
            }
            AttrMaxPause maxPause = this.cmParams.getMaxPause();
            if (maxPause == null) {
                this.lock.unlock();
                return ASSERTIONS;
            }
            this.lock.unlock();
            try {
                send(ComposableBody.builder().setAttribute(Attributes.PAUSE, maxPause.toString()).build());
            } catch (BOSHException boshx) {
                MyLog.e("Could not send pause", boshx);
            }
            return true;
        } finally {
            this.lock.unlock();
        }
    }

    public void disconnect() throws BOSHException {
        disconnect(ComposableBody.builder().build());
    }

    public void disconnect(ComposableBody msg) throws BOSHException {
        if (msg == null) {
            throw new IllegalArgumentException("Message body may not be null");
        }
        Builder builder = msg.rebuild();
        builder.setAttribute(Attributes.TYPE, TERMINATE);
        send(builder.build());
    }

    public void close() {
        dispose(new BOSHException("Session explicitly closed by caller"));
    }

    CMSessionParams getCMSessionParams() {
        this.lock.lock();
        try {
            CMSessionParams cMSessionParams = this.cmParams;
            return cMSessionParams;
        } finally {
            this.lock.unlock();
        }
    }

    private void init() {
        assertUnlocked();
        this.lock.lock();
        try {
            this.httpSender.init(this.cfg);
            this.executor = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        } finally {
            this.lock.unlock();
        }
    }

    private void dispose(Throwable cause) {
        assertUnlocked();
        this.lock.lock();
        try {
            if (this.executor != null) {
                this.executor.shutdownNow();
                this.executor = null;
                this.lock.unlock();
                if (cause == null) {
                    fireConnectionClosed();
                } else {
                    fireConnectionClosedOnError(cause);
                }
                this.lock.lock();
                try {
                    clearEmptyRequest();
                    this.exchanges = null;
                    this.cmParams = null;
                    this.pendingResponseAcks = null;
                    this.pendingRequestAcks = null;
                    this.notEmpty.signalAll();
                    this.notFull.signalAll();
                    this.ridForHold.signalAll();
                    this.httpSender.destroy();
                } finally {
                    this.lock.unlock();
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    private static boolean isTermination(AbstractBody msg) {
        return TERMINATE.equals(msg.getAttribute(Attributes.TYPE));
    }

    private TerminalBindingCondition getTerminalBindingCondition(int respCode, AbstractBody respBody) {
        assertLocked();
        if (isTermination(respBody)) {
            return TerminalBindingCondition.forString(respBody.getAttribute(Attributes.CONDITION));
        }
        if (this.cmParams == null || this.cmParams.getVersion() != null) {
            return null;
        }
        return TerminalBindingCondition.forHTTPResponseCode(respCode);
    }

    private boolean isWorking() {
        assertLocked();
        return this.executor != null ? true : ASSERTIONS;
    }

    private ComposableBody applySessionCreationRequest(long rid, ComposableBody orig) throws BOSHException {
        assertLocked();
        Builder builder = orig.rebuild();
        builder.setAttribute(Attributes.TO, this.cfg.getTo());
        builder.setAttribute(Attributes.XML_LANG, this.cfg.getLang());
        builder.setAttribute(Attributes.VER, AttrVersion.getSupportedVersion().toString());
        builder.setAttribute(Attributes.WAIT, "300");
        builder.setAttribute(Attributes.HOLD, PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI);
        builder.setAttribute(Attributes.RID, Long.toString(rid));
        applyRoute(builder);
        applyFrom(builder);
        builder.setAttribute(Attributes.ACK, PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI);
        builder.setAttribute(Attributes.SID, null);
        return builder.build();
    }

    private void applyRoute(Builder builder) {
        assertLocked();
        String route = this.cfg.getRoute();
        if (route != null) {
            builder.setAttribute(Attributes.ROUTE, route);
        }
    }

    private void applyFrom(Builder builder) {
        assertLocked();
        String from = this.cfg.getFrom();
        if (from != null) {
            builder.setAttribute(Attributes.FROM, from);
        }
    }

    private ComposableBody applySessionData(long rid, ComposableBody orig) throws BOSHException {
        assertLocked();
        Builder builder = orig.rebuild();
        builder.setAttribute(Attributes.SID, this.cmParams.getSessionID().toString());
        builder.setAttribute(Attributes.RID, Long.toString(rid));
        return builder.build();
    }

    private void processMessages() {
        HTTPExchange exch = nextExchange();
        if (exch != null) {
            this.lock.lock();
            try {
                long rid = Long.valueOf(exch.getRequest().getAttribute(Attributes.RID)).longValue();
                if (this.expectedResponseRid == 0) {
                    this.expectedResponseRid = rid;
                }
                this.lock.unlock();
                processExchange(exch);
            } catch (Throwable th) {
                this.lock.unlock();
            }
        }
    }

    private HTTPExchange nextExchange() {
        HTTPExchange exch;
        assertUnlocked();
        this.lock.lock();
        do {
            if (this.exchanges == null) {
                this.lock.unlock();
                return null;
            }
            try {
                exch = (HTTPExchange) this.exchanges.poll();
                if (exch == null) {
                    this.notEmpty.await();
                    continue;
                }
            } catch (InterruptedException intx) {
                MyLog.e(INTERRUPTED, intx);
                continue;
            } catch (Throwable th) {
                this.lock.unlock();
            }
        } while (exch == null);
        this.lock.unlock();
        return exch;
    }

    private void processExchange(HTTPExchange exch) {
        long delay;
        BOSHException boshx;
        Throwable th;
        InterruptedException e;
        assertUnlocked();
        try {
            HTTPResponse resp = exch.getHTTPResponse();
            AbstractBody body = resp.getBody();
            int respCode = resp.getHTTPStatus();
            this.lock.lock();
            try {
                long respRid = resp.getRid();
                if (this.emptyRid == respRid) {
                    this.emptyRid = 0;
                }
                if (respRid <= this.expectedResponseRid) {
                    this.ridForHold.signalAll();
                } else {
                    StringBuilder append = new StringBuilder().append("SMACK-BOSH: responded rid(");
                    MyLog.v(r22.append(respRid).append(") is not expected (").append(this.expectedResponseRid).append("), wait").toString());
                    if (!this.ridForHold.await(30, TimeUnit.SECONDS)) {
                        MyLog.e("SMACK-BOSH: wait for " + this.expectedResponseRid + " timeout, terminate!");
                        dispose(new BOSHException("wait timeout for rid" + this.expectedResponseRid));
                        this.lock.unlock();
                        return;
                    }
                }
                this.expectedResponseRid++;
                fireResponseReceived(body);
                AbstractBody req = exch.getRequest();
                List<HTTPExchange> toResend = null;
                this.lock.lock();
                try {
                    if (isWorking()) {
                        if (this.cmParams == null) {
                            this.cmParams = CMSessionParams.fromSessionInit(req, body);
                            fireConnectionEstablished();
                        }
                        CMSessionParams params = this.cmParams;
                        checkForTerminalBindingConditions(body, respCode);
                        if (isTermination(body)) {
                            this.lock.unlock();
                            dispose(null);
                            if (this.lock.isHeldByCurrentThread()) {
                                try {
                                    if (this.exchanges != null) {
                                        if (this.exchanges.isEmpty() && !isEmptyRequesting()) {
                                            delay = processPauseRequest(req);
                                            if (delay > 0) {
                                                scheduleEmptyRequest(delay);
                                            }
                                        }
                                    }
                                    this.notFull.signalAll();
                                    return;
                                } finally {
                                    this.lock.unlock();
                                }
                            } else {
                                MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                                return;
                            }
                        }
                        if (isRecoverableBindingCondition(body)) {
                            if (PAUSE_MARGIN == null) {
                                toResend = new ArrayList(this.exchanges.size());
                            }
                            for (HTTPExchange exchange : this.exchanges) {
                                toResend.add(new HTTPExchange(exchange.getRequest()));
                            }
                            for (HTTPExchange exchange2 : toResend) {
                                addNewExchange(exchange2);
                            }
                        } else {
                            processRequestAcknowledgements(req, body);
                            processResponseAcknowledgementData(req);
                            HTTPExchange resendExch = processResponseAcknowledgementReport(body);
                            if (resendExch != null && PAUSE_MARGIN == null) {
                                List<HTTPExchange> toResend2 = new ArrayList(1);
                                try {
                                    toResend2.add(resendExch);
                                    addNewExchange(resendExch);
                                    toResend = toResend2;
                                } catch (BOSHException e2) {
                                    boshx = e2;
                                    toResend = toResend2;
                                    try {
                                        MyLog.e("SMACK-BOSH: Could not process response", boshx);
                                        this.lock.unlock();
                                        dispose(boshx);
                                        if (this.lock.isHeldByCurrentThread()) {
                                            MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                                            return;
                                        }
                                        try {
                                            if (this.exchanges != null) {
                                                if (this.exchanges.isEmpty() && !isEmptyRequesting()) {
                                                    delay = processPauseRequest(req);
                                                    if (delay > 0) {
                                                        scheduleEmptyRequest(delay);
                                                    }
                                                }
                                            }
                                            this.notFull.signalAll();
                                        } finally {
                                            this.lock.unlock();
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (this.lock.isHeldByCurrentThread()) {
                                            try {
                                                if (this.exchanges != null) {
                                                    delay = processPauseRequest(req);
                                                    if (delay > 0) {
                                                        scheduleEmptyRequest(delay);
                                                    }
                                                }
                                                this.notFull.signalAll();
                                            } finally {
                                                this.lock.unlock();
                                            }
                                        } else {
                                            MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                                        }
                                        throw th;
                                    }
                                } catch (InterruptedException e3) {
                                    e = e3;
                                    toResend = toResend2;
                                    MyLog.e("SMACK-BOSH: Could not process response", e);
                                    this.lock.unlock();
                                    dispose(e);
                                    if (this.lock.isHeldByCurrentThread()) {
                                        MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                                        return;
                                    }
                                    try {
                                        if (this.exchanges != null) {
                                            if (this.exchanges.isEmpty() && !isEmptyRequesting()) {
                                                delay = processPauseRequest(req);
                                                if (delay > 0) {
                                                    scheduleEmptyRequest(delay);
                                                }
                                            }
                                        }
                                        this.notFull.signalAll();
                                    } finally {
                                        this.lock.unlock();
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    toResend = toResend2;
                                    if (this.lock.isHeldByCurrentThread()) {
                                        if (this.exchanges != null) {
                                            if (this.exchanges.isEmpty() && !isEmptyRequesting()) {
                                                delay = processPauseRequest(req);
                                                if (delay > 0) {
                                                    scheduleEmptyRequest(delay);
                                                }
                                            }
                                        }
                                        this.notFull.signalAll();
                                    } else {
                                        MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (this.lock.isHeldByCurrentThread()) {
                            try {
                                if (this.exchanges != null) {
                                    if (this.exchanges.isEmpty() && !isEmptyRequesting()) {
                                        delay = processPauseRequest(req);
                                        if (delay > 0) {
                                            scheduleEmptyRequest(delay);
                                        }
                                    }
                                }
                                this.notFull.signalAll();
                            } finally {
                                this.lock.unlock();
                            }
                        } else {
                            MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                        }
                        if (toResend != null) {
                            for (HTTPExchange resend : toResend) {
                                resend.setHTTPResponse(this.httpSender.send(params, resend.getRequest(), this.context));
                                fireRequestSent(resend.getRequest());
                            }
                            return;
                        }
                        return;
                    }
                    this.lock.unlock();
                    if (this.lock.isHeldByCurrentThread()) {
                        try {
                            if (this.exchanges != null) {
                                if (this.exchanges.isEmpty() && !isEmptyRequesting()) {
                                    delay = processPauseRequest(req);
                                    if (delay > 0) {
                                        scheduleEmptyRequest(delay);
                                    }
                                }
                            }
                            this.notFull.signalAll();
                        } finally {
                            this.lock.unlock();
                        }
                    } else {
                        MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                    }
                } catch (BOSHException e4) {
                    boshx = e4;
                    MyLog.e("SMACK-BOSH: Could not process response", boshx);
                    this.lock.unlock();
                    dispose(boshx);
                    if (this.lock.isHeldByCurrentThread()) {
                        MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                        return;
                    }
                    if (this.exchanges != null) {
                        delay = processPauseRequest(req);
                        if (delay > 0) {
                            scheduleEmptyRequest(delay);
                        }
                    }
                    this.notFull.signalAll();
                } catch (InterruptedException e5) {
                    e = e5;
                    MyLog.e("SMACK-BOSH: Could not process response", e);
                    this.lock.unlock();
                    dispose(e);
                    if (this.lock.isHeldByCurrentThread()) {
                        MyLog.v("SMACK-BOSH: lock is not held by this thread, don't schedule empty request");
                        return;
                    }
                    if (this.exchanges != null) {
                        delay = processPauseRequest(req);
                        if (delay > 0) {
                            scheduleEmptyRequest(delay);
                        }
                    }
                    this.notFull.signalAll();
                }
            } catch (InterruptedException e6) {
                dispose(e6);
            } finally {
                this.lock.unlock();
            }
        } catch (BOSHException boshx2) {
            MyLog.e("SMACK-BOSH: Could not obtain response", boshx2);
            dispose(boshx2);
        } catch (InterruptedException intx) {
            MyLog.e(INTERRUPTED, intx);
            dispose(intx);
        }
    }

    private boolean isEmptyRequesting() {
        return ((this.emptyRequestFuture == null || this.emptyRequestFuture.isDone()) && this.emptyRid <= 0) ? ASSERTIONS : true;
    }

    private void clearEmptyRequest() {
        assertLocked();
        if (this.emptyRequestFuture != null) {
            this.emptyRequestFuture.cancel(ASSERTIONS);
            this.emptyRequestFuture = null;
        }
    }

    private long getDefaultEmptyRequestDelay() {
        assertLocked();
        AttrPolling polling = this.cmParams.getPollingInterval();
        long delay = (long) EMPTY_REQUEST_DELAY;
        if (polling == null) {
            return delay;
        }
        long minDelay = (long) polling.getInMilliseconds();
        if (minDelay > delay) {
            return minDelay;
        }
        return delay;
    }

    private void scheduleEmptyRequest(long delay) {
        assertLocked();
        if (delay < 0) {
            throw new IllegalArgumentException("Empty request delay must be >= 0 (was: " + delay + ")");
        }
        clearEmptyRequest();
        if (isWorking()) {
            MyLog.v("SMACK-BOSH: Scheduling empty request in " + delay + "ms");
            try {
                this.emptyRequestFuture = this.schedExec.schedule(this.emptyRequestRunnable, delay, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException rex) {
                MyLog.e("SMACK-BOSH: Could not schedule empty request", rex);
            }
        }
    }

    public void sendEmptyRequestIfNeeded() {
        if (this.executor != null) {
            if (System.currentTimeMillis() - this.lastProcessStartedTime > MAX_MULTI_THREAD_EXISTING && this.executor.getActiveCount() > 1) {
                dispose(new BOSHException("SMACK-BOSH: request timeout happened, reset connection"));
            } else if (this.executor.getActiveCount() <= 0 || isEmptyRequesting()) {
                this.lock.lock();
                try {
                    scheduleEmptyRequest(0);
                } finally {
                    this.lock.unlock();
                }
            }
        }
    }

    private void sendEmptyRequest() {
        assertUnlocked();
        try {
            send(ComposableBody.builder().build());
        } catch (BOSHException boshx) {
            dispose(boshx);
        }
    }

    private void assertLocked() {
        if (ASSERTIONS && !this.lock.isHeldByCurrentThread()) {
            throw new AssertionError("Lock is not held by current thread");
        }
    }

    private void assertUnlocked() {
        if (ASSERTIONS && this.lock.isHeldByCurrentThread()) {
            throw new AssertionError("Lock is held by current thread");
        }
    }

    private void checkForTerminalBindingConditions(AbstractBody body, int code) throws BOSHException {
        TerminalBindingCondition cond = getTerminalBindingCondition(code, body);
        if (cond != null) {
            throw new BOSHException("Terminal binding condition encountered: " + cond.getCondition() + "  (" + cond.getMessage() + ")");
        }
    }

    private static boolean isRecoverableBindingCondition(AbstractBody resp) {
        return ERROR.equals(resp.getAttribute(Attributes.TYPE));
    }

    private long processPauseRequest(AbstractBody req) {
        assertLocked();
        if (!(this.cmParams == null || this.cmParams.getMaxPause() == null)) {
            try {
                AttrPause pause = AttrPause.createFromString(req.getAttribute(Attributes.PAUSE));
                if (pause != null) {
                    long delay = (long) (pause.getInMilliseconds() - PAUSE_MARGIN);
                    if (delay < 0) {
                        return (long) EMPTY_REQUEST_DELAY;
                    }
                    return delay;
                }
            } catch (BOSHException boshx) {
                MyLog.e("SMACK-BOSH: Could not extract", boshx);
            }
        }
        return getDefaultEmptyRequestDelay();
    }

    private void processRequestAcknowledgements(AbstractBody req, AbstractBody resp) {
        assertLocked();
        if (this.cmParams.isAckingRequests() && resp.getAttribute(Attributes.REPORT) == null) {
            Long ackUpTo;
            String acked = resp.getAttribute(Attributes.ACK);
            if (acked == null) {
                ackUpTo = Long.valueOf(Long.parseLong(req.getAttribute(Attributes.RID)));
            } else {
                ackUpTo = Long.valueOf(Long.parseLong(acked));
            }
            Iterator<ComposableBody> iter = this.pendingRequestAcks.iterator();
            while (iter.hasNext()) {
                if (Long.valueOf(Long.parseLong(((AbstractBody) iter.next()).getAttribute(Attributes.RID))).compareTo(ackUpTo) <= 0) {
                    iter.remove();
                }
            }
        }
    }

    private void processResponseAcknowledgementData(AbstractBody req) {
        assertLocked();
        Long rid = Long.valueOf(Long.parseLong(req.getAttribute(Attributes.RID)));
        if (this.responseAck.equals(Long.valueOf(-1))) {
            this.responseAck = rid;
            return;
        }
        this.pendingResponseAcks.add(rid);
        Long whileVal = Long.valueOf(this.responseAck.longValue() + 1);
        while (!this.pendingResponseAcks.isEmpty() && whileVal.equals(this.pendingResponseAcks.first())) {
            this.responseAck = whileVal;
            this.pendingResponseAcks.remove(whileVal);
            whileVal = Long.valueOf(whileVal.longValue() + 1);
        }
    }

    private HTTPExchange processResponseAcknowledgementReport(AbstractBody resp) throws BOSHException {
        assertLocked();
        String reportStr = resp.getAttribute(Attributes.REPORT);
        if (reportStr == null) {
            return null;
        }
        Long report = Long.valueOf(Long.parseLong(reportStr));
        Long time = Long.valueOf(Long.parseLong(resp.getAttribute(Attributes.TIME)));
        Iterator<ComposableBody> iter = this.pendingRequestAcks.iterator();
        AbstractBody req = null;
        while (iter.hasNext() && req == null) {
            AbstractBody pending = (AbstractBody) iter.next();
            if (report.equals(Long.valueOf(Long.parseLong(pending.getAttribute(Attributes.RID))))) {
                req = pending;
            }
        }
        if (req == null) {
            throw new BOSHException("Report of missing message with RID '" + reportStr + "' but local copy of that request was not found");
        }
        HTTPExchange exch = new HTTPExchange(req);
        addNewExchange(exch);
        this.notEmpty.signalAll();
        return exch;
    }

    private void fireRequestSent(AbstractBody request) {
        assertUnlocked();
        BOSHMessageEvent event = null;
        for (BOSHClientRequestListener listener : this.requestListeners) {
            if (event == null) {
                event = BOSHMessageEvent.createRequestSentEvent(this, request);
            }
            try {
                listener.requestSent(event);
            } catch (Exception ex) {
                MyLog.e("SMACK-BOSH:Unhandled Exception", ex);
            }
        }
    }

    private void fireResponseReceived(AbstractBody response) {
        assertUnlocked();
        BOSHMessageEvent event = null;
        for (BOSHClientResponseListener listener : this.responseListeners) {
            if (event == null) {
                event = BOSHMessageEvent.createResponseReceivedEvent(this, response);
            }
            try {
                listener.responseReceived(event);
            } catch (Exception ex) {
                MyLog.e("SMACK-BOSH:Unhandled Exception", ex);
            }
        }
    }

    private void fireConnectionEstablished() {
        boolean hadLock = this.lock.isHeldByCurrentThread();
        if (hadLock) {
            this.lock.unlock();
        }
        BOSHClientConnEvent event = null;
        try {
            for (BOSHClientConnListener listener : this.connListeners) {
                if (event == null) {
                    event = BOSHClientConnEvent.createConnectionEstablishedEvent(this);
                }
                listener.connectionEvent(event);
            }
            if (hadLock) {
                this.lock.lock();
            }
        } catch (Exception ex) {
            MyLog.e("SMACK-BOSH:Unhandled Exception", ex);
        } catch (Throwable th) {
            if (hadLock) {
                this.lock.lock();
            }
        }
    }

    private void fireConnectionClosed() {
        assertUnlocked();
        BOSHClientConnEvent event = null;
        for (BOSHClientConnListener listener : this.connListeners) {
            if (event == null) {
                event = BOSHClientConnEvent.createConnectionClosedEvent(this);
            }
            try {
                listener.connectionEvent(event);
            } catch (Exception ex) {
                MyLog.e("SMACK-BOSH:Unhandled Exception", ex);
            }
        }
    }

    private void fireConnectionClosedOnError(Throwable cause) {
        assertUnlocked();
        BOSHClientConnEvent event = null;
        for (BOSHClientConnListener listener : this.connListeners) {
            if (event == null) {
                event = BOSHClientConnEvent.createConnectionClosedOnErrorEvent(this, this.pendingRequestAcks, cause);
            }
            try {
                listener.connectionEvent(event);
            } catch (Exception ex) {
                MyLog.e("SMACK-BOSH:Unhandled Exception", ex);
            }
        }
    }
}
