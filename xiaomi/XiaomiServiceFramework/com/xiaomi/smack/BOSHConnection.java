package com.xiaomi.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.kenai.jbosh.BOSHClient;
import com.xiaomi.kenai.jbosh.BOSHClientConfig.Builder;
import com.xiaomi.kenai.jbosh.BOSHClientConnEvent;
import com.xiaomi.kenai.jbosh.BOSHClientConnListener;
import com.xiaomi.kenai.jbosh.BOSHClientRequestListener;
import com.xiaomi.kenai.jbosh.BOSHClientResponseListener;
import com.xiaomi.kenai.jbosh.BOSHException;
import com.xiaomi.kenai.jbosh.BOSHMessageEvent;
import com.xiaomi.kenai.jbosh.BodyQName;
import com.xiaomi.kenai.jbosh.ComposableBody;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.XMPushService.Job;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Type;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.packet.XMPPError.Condition;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class BOSHConnection extends Connection {
    public static final String BOSH_URI = "xm";
    public static final String XMPP_BOSH_NS = "xm";
    private boolean anonymous;
    protected String authID;
    private boolean authenticated;
    private BOSHClient client;
    private final BOSHConfiguration config;
    private Object connectionLock;
    private boolean done;
    private boolean isFirstInitialization;
    private ExecutorService listenerExecutor;
    private Thread readerConsumer;
    private PipedWriter readerPipe;
    protected String sessionID;
    private String user;
    private boolean wasAuthenticated;

    private class BOSHConnectionListener implements BOSHClientConnListener {
        private BOSHConnectionListener() {
        }

        public void connectionEvent(BOSHClientConnEvent connEvent) {
            if (!connEvent.isConnected()) {
                BOSHConnection.this.setConnectionStatus(2, 0, null);
                BOSHConnection.this.notifyConnectionError((Exception) connEvent.getCause());
            }
            synchronized (BOSHConnection.this.connectionLock) {
                BOSHConnection.this.connectionLock.notifyAll();
            }
        }
    }

    private class ListenerNotification implements Runnable {
        private Packet packet;

        public ListenerNotification(Packet packet) {
            this.packet = packet;
        }

        public void run() {
            for (ListenerWrapper listenerWrapper : BOSHConnection.this.recvListeners.values()) {
                listenerWrapper.notifyListener(this.packet);
            }
        }
    }

    public BOSHConnection(XMPushService pushService, BOSHConfiguration config) {
        super(pushService, config);
        this.authenticated = false;
        this.anonymous = false;
        this.isFirstInitialization = true;
        this.wasAuthenticated = false;
        this.done = false;
        this.authID = null;
        this.sessionID = null;
        this.user = null;
        this.connectionLock = new Object();
        this.config = config;
    }

    public void connect() throws XMPPException {
        if (isConnected()) {
            MyLog.e("SMACK-BOSH: Already connected to a server.");
            return;
        }
        long startTime = System.currentTimeMillis();
        this.done = false;
        this.sessionID = null;
        this.authID = null;
        try {
            setConnectionStatus(0, 0, null);
            URI uri = this.config.getURI();
            MyLog.v("SMACK-BOSH: connecting using uri:" + uri.toString());
            this.client = BOSHClient.create(Builder.create(uri, this.config.getServiceName()).build(), this.mPushService.getApplicationContext());
            this.listenerExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
                public Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "Smack Listener Processor (" + BOSHConnection.this.connectionCounterValue + ")");
                }
            });
            this.client.addBOSHClientConnListener(new BOSHConnectionListener());
            this.client.addBOSHClientResponseListener(new BOSHPacketReader(this));
            if (this.config.isDebuggerEnabled()) {
                initDebugger();
                if (this.isFirstInitialization) {
                    if (this.debugger.getReaderListener() != null) {
                        addPacketListener(this.debugger.getReaderListener(), null);
                    }
                    if (this.debugger.getWriterListener() != null) {
                        addPacketSendingListener(this.debugger.getWriterListener(), null);
                    }
                }
            }
            this.client.send(ComposableBody.builder().setAttribute(BodyQName.create(XMPP_BOSH_NS, "version"), "102").build());
            synchronized (this.connectionLock) {
                if (!isConnected()) {
                    try {
                        this.connectionLock.wait((long) (SmackConfiguration.getPacketReplyTimeout() * 6));
                    } catch (InterruptedException e) {
                    }
                }
            }
            long cost = System.currentTimeMillis() - startTime;
            if (isConnected()) {
                this.config.getHostFallback().succeedHost(this.config.getCurrentHost(), cost, 0);
                return;
            }
            this.done = true;
            String errorMessage = "Timeout reached for the connection to " + this.config.getCurrentHost() + ":" + getPort() + ".";
            this.config.getHostFallback().failedHost(this.config.getCurrentHost(), cost, 0, null);
            throw new XMPPException(errorMessage, new XMPPError(Condition.remote_server_timeout, errorMessage));
        } catch (Throwable e2) {
            throw new XMPPException("Can't connect to " + getServiceName(), e2);
        }
    }

    public String getConnectionID() {
        if (!isConnected()) {
            return null;
        }
        if (this.authID != null) {
            return this.authID;
        }
        return this.sessionID;
    }

    public String getUser() {
        return this.user;
    }

    public void sendPacket(Packet packet) throws XMPPException {
        if (this.done) {
            throw new XMPPException("try send packet while the connection is done.");
        }
        try {
            send(ComposableBody.builder().setPayloadXML(packet.toXML()).build());
            firePacketSendingListeners(packet);
        } catch (Throwable e) {
            throw new XMPPException(e);
        }
    }

    public void batchSendPacket(Packet[] packets) throws XMPPException {
        if (this.done) {
            throw new XMPPException("try send packet while connection is done.");
        }
        StringBuilder payloadBuilder = new StringBuilder();
        for (Packet packet : packets) {
            if (packet == null) {
                throw new NullPointerException("Packet is null.");
            }
            payloadBuilder.append(packet.toXML());
        }
        try {
            send(ComposableBody.builder().setPayloadXML(payloadBuilder.toString()).build());
            for (Packet packet2 : packets) {
                firePacketSendingListeners(packet2);
            }
        } catch (Throwable e) {
            throw new XMPPException(e);
        }
    }

    public void disconnect(Presence unavailablePresence) {
        disconnect(unavailablePresence, 0, null);
    }

    public void disconnect(Presence unavailablePresence, int reason, Exception e) {
        if (getConnectionStatus() != 2) {
            shutdown(unavailablePresence, reason, e);
            this.sendListeners.clear();
            this.recvListeners.clear();
            this.wasAuthenticated = false;
            this.isFirstInitialization = true;
        }
    }

    protected void shutdown(Presence unavailablePresence, int reason, Exception ex) {
        this.authID = null;
        this.sessionID = null;
        this.done = true;
        this.authenticated = false;
        setConnectionStatus(2, reason, ex);
        this.isFirstInitialization = false;
        this.challenge = "";
        try {
            this.client.disconnect(ComposableBody.builder().setNamespaceDefinition("xmpp", XMPP_BOSH_NS).build());
            Thread.sleep(150);
        } catch (Exception e) {
        }
        if (this.client != null) {
            this.client.close();
            this.client = null;
        }
        if (this.readerPipe != null) {
            try {
                this.readerPipe.close();
            } catch (Throwable th) {
            }
            this.reader = null;
        }
        if (this.reader != null) {
            try {
                this.reader.close();
            } catch (Throwable th2) {
            }
            this.reader = null;
        }
        if (this.writer != null) {
            try {
                this.writer.close();
            } catch (Throwable th3) {
            }
            this.writer = null;
        }
        if (this.listenerExecutor != null) {
            this.listenerExecutor.shutdown();
        }
        for (ConnectionListener listener : getConnectionListeners()) {
            try {
                listener.connectionClosed(reason, ex);
            } catch (Exception e2) {
                MyLog.e("SMACK-BOSH: Error while shut down connection", e2);
            }
        }
        this.readerConsumer = null;
    }

    protected void send(ComposableBody body) throws BOSHException {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected to a server!");
        } else if (body == null) {
            throw new NullPointerException("Body mustn't be null!");
        } else {
            if (this.sessionID != null) {
                body = body.rebuild().setAttribute(BodyQName.create(XMPP_BOSH_NS, PushServiceConstants.EXTRA_SID), this.sessionID).build();
            }
            this.client.send(body);
        }
    }

    protected void processPacket(Packet packet) {
        if (packet != null) {
            this.listenerExecutor.submit(new ListenerNotification(packet));
        }
    }

    protected void initDebugger() {
        this.writer = new Writer() {
            public void write(char[] cbuf, int off, int len) {
            }

            public void close() {
            }

            public void flush() {
            }
        };
        try {
            this.readerPipe = new PipedWriter();
            this.reader = new PipedReader(this.readerPipe);
        } catch (IOException e) {
        }
        super.initDebugger();
        this.client.addBOSHClientResponseListener(new BOSHClientResponseListener() {
            public boolean responseReceived(BOSHMessageEvent event) {
                if (event.getBody() != null) {
                    try {
                        BOSHConnection.this.readerPipe.write(event.getBody().toXML());
                        BOSHConnection.this.readerPipe.flush();
                    } catch (Exception e) {
                    }
                }
                return false;
            }
        });
        this.client.addBOSHClientRequestListener(new BOSHClientRequestListener() {
            public void requestSent(BOSHMessageEvent event) {
                if (event.getBody() != null) {
                    try {
                        BOSHConnection.this.writer.write(event.getBody().toXML());
                    } catch (Exception e) {
                    }
                }
            }
        });
        this.readerConsumer = new Thread() {
            private int bufferLength;
            private Thread thread;

            {
                this.thread = this;
                this.bufferLength = 1024;
            }

            public void run() {
                try {
                    char[] cbuf = new char[this.bufferLength];
                    while (BOSHConnection.this.readerConsumer == this.thread && !BOSHConnection.this.done) {
                        BOSHConnection.this.reader.read(cbuf, 0, this.bufferLength);
                    }
                } catch (Throwable th) {
                }
            }
        };
        this.readerConsumer.setDaemon(true);
        this.readerConsumer.start();
    }

    protected void notifyConnectionError(final Exception e) {
        this.mPushService.executeJob(new Job(2) {
            public void process() {
                BOSHConnection.this.mPushService.disconnect(0, e);
            }

            public String getDesc() {
                return "shutdown the connection. " + e;
            }
        });
    }

    public synchronized void bind(ClientLoginInfo info) throws XMPPException {
        new XMBinder().doBind(info, this.challenge, this);
    }

    public synchronized void unbind(String chid, String userName) throws XMPPException {
        Presence unbindPres = new Presence(Type.unavailable);
        unbindPres.setChannelId(chid);
        unbindPres.setFrom(userName);
        sendPacket(unbindPres);
    }

    public void login(String username, String sid, String token, String chid, String resource, boolean kick, String extra) throws XMPPException {
    }

    public void sendPingString() {
        if (isConnected()) {
            MyLog.v("SMACK-BOSH: scheduling empty request for ping");
            this.client.sendEmptyRequestIfNeeded();
        }
    }
}
