package com.xiaomi.smack;

import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.XMPushService.Job;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Type;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class XMPPConnection extends Connection {
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int MAX_SHORT_CONN_COUNT = 2;
    private static final int SHORT_CONNECTION_THRESHOLD = 300000;
    private final String PING_PERF;
    private String connectedHost;
    String connectionID;
    private int curShortConnCount;
    public Exception failedException;
    private volatile long lastConnectedTime;
    private volatile long lastPingReceived;
    private volatile long lastPingSent;
    PacketReader packetReader;
    PacketWriter packetWriter;
    private String pingString;
    private XMPushService pushService;
    protected Socket socket;
    private String user;

    public XMPPConnection(XMPushService pushService, ConnectionConfiguration config) {
        super(pushService, config);
        this.failedException = null;
        this.connectionID = null;
        this.user = null;
        this.pingString = "";
        this.lastPingSent = 0;
        this.lastPingReceived = 0;
        this.PING_PERF = "<pf><p>t:%1$d</p></pf>";
        this.lastConnectedTime = 0;
        this.pushService = pushService;
    }

    public void sendPingString() throws XMPPException {
        if (this.packetWriter != null) {
            this.packetWriter.sendPingString();
            final long current = System.currentTimeMillis();
            this.pushService.executeJobDelayed(new Job(13) {
                public void process() {
                    if (XMPPConnection.this.isConnected() && !XMPPConnection.this.isReadAlive(current)) {
                        XMPPConnection.this.pushService.disconnect(22, null);
                    }
                }

                public String getDesc() {
                    return "check the ping-pong.";
                }
            }, Connection.PING_TIMEOUT);
            return;
        }
        throw new XMPPException("the packetwriter is null.");
    }

    public String getUser() {
        if (isConnected()) {
            return this.user;
        }
        return null;
    }

    public String getChallenge() {
        return this.challenge;
    }

    public String getHost() {
        return this.connectedHost;
    }

    public synchronized void login(String username, String sid, String token, String chid, String resource, boolean kick, String extra) throws XMPPException {
    }

    public synchronized void bind(ClientLoginInfo info) throws XMPPException {
        new XMBinder().doBind(info, getChallenge(), this);
    }

    public synchronized void unbind(String chid, String userName) throws XMPPException {
        Presence unbindPres = new Presence(Type.unavailable);
        unbindPres.setChannelId(chid);
        unbindPres.setFrom(userName);
        if (this.packetWriter != null) {
            this.packetWriter.sendPacket(unbindPres);
        }
    }

    protected synchronized void shutdown(Presence unavailablePresence, int reason, Exception ex) {
        if (getConnectionStatus() != MAX_SHORT_CONN_COUNT) {
            setConnectionStatus(MAX_SHORT_CONN_COUNT, reason, ex);
            this.challenge = "";
            if (this.packetReader != null) {
                this.packetReader.shutdown();
                this.packetReader.cleanup();
                this.packetReader = null;
            }
            if (this.packetWriter != null) {
                try {
                    this.packetWriter.shutdown();
                } catch (Throwable e) {
                    MyLog.e(e);
                }
                this.packetWriter.cleanup();
                this.packetWriter = null;
            }
            try {
                this.socket.close();
            } catch (Throwable th) {
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
            this.lastPingSent = 0;
            this.lastPingReceived = 0;
        }
    }

    public void disconnect(Presence unavailablePresence) {
        disconnect(unavailablePresence, 0, null);
    }

    public void disconnect(Presence unavailablePresence, int reason, Exception e) {
        shutdown(unavailablePresence, reason, e);
        if (e != null && this.lastConnectedTime != 0) {
            sinkdownHost(e);
        }
    }

    private void sinkdownHost(Exception e) {
        if (SystemClock.elapsedRealtime() - this.lastConnectedTime >= 300000) {
            this.curShortConnCount = 0;
        } else if (Network.hasNetwork(this.pushService)) {
            this.curShortConnCount++;
            if (this.curShortConnCount >= MAX_SHORT_CONN_COUNT) {
                String failedHost = getHost();
                MyLog.warn("max short conn time reached, sink down current host:" + failedHost);
                sinkdownHost(failedHost, 0, e);
                this.curShortConnCount = 0;
            }
        }
    }

    private void sinkdownHost(String host, long cost, Exception e) {
        Fallback fb = HostManager.getInstance().getFallbacksByHost(ConnectionConfiguration.getXmppServerHost());
        if (fb != null) {
            fb.failedHost(host, cost, 0, e);
            HostManager.getInstance().persist();
        }
    }

    public void sendPacket(Packet packet) throws XMPPException {
        if (this.packetWriter != null) {
            this.packetWriter.sendPacket(packet);
            return;
        }
        throw new XMPPException("the writer is null.");
    }

    public void batchSendPacket(Packet[] packets) throws XMPPException {
        for (Packet packet : packets) {
            sendPacket(packet);
        }
    }

    private void connectUsingConfiguration(ConnectionConfiguration config) throws XMPPException, IOException {
        connectDirectly(config.getHost(), config.getPort());
    }

    private void connectDirectly(String host, int port) throws XMPPException {
        boolean succeeded = false;
        this.failedException = null;
        ArrayList<String> hosts = new ArrayList();
        int code = MyLog.ps("get bucket for host : " + host).intValue();
        Fallback fallback = getFallback(host);
        MyLog.pe(Integer.valueOf(code));
        if (fallback != null) {
            hosts = fallback.getHosts();
        }
        if (hosts.isEmpty()) {
            hosts.add(host);
        }
        this.lastConnectedTime = 0;
        StringBuilder errorSB = new StringBuilder();
        Iterator i$ = hosts.iterator();
        while (i$.hasNext()) {
            String currentHost = (String) i$.next();
            long start = System.currentTimeMillis();
            this.connTimes++;
            try {
                MyLog.warn("begin to connect to " + currentHost);
                this.socket = createSocket();
                this.socket.bind(null);
                this.socket.connect(new InetSocketAddress(currentHost, port), CONNECTION_TIMEOUT);
                this.socket.setTcpNoDelay(true);
                this.connectedHost = currentHost;
                initConnection();
                succeeded = true;
                this.connectTime = System.currentTimeMillis() - start;
                if (fallback != null) {
                    fallback.succeedHost(currentHost, this.connectTime, 0);
                }
                this.lastConnectedTime = SystemClock.elapsedRealtime();
                MyLog.warn("connected to " + currentHost + " in " + this.connectTime);
                HostManager.getInstance().persist();
                if (!succeeded) {
                    throw new XMPPException(errorSB.toString());
                }
            } catch (IOException e1) {
                if (fallback != null) {
                    fallback.failedHost(currentHost, System.currentTimeMillis() - start, 0, e1);
                }
                this.failedException = e1;
                MyLog.e("SMACK: Could not connect to:" + currentHost);
                errorSB.append("SMACK: Could not connect to ").append(currentHost).append(" port:").append(port).append(" ").append(e1.getMessage()).append("\n");
            } catch (XMPPException e) {
                if (fallback != null) {
                    fallback.failedHost(currentHost, System.currentTimeMillis() - start, 0, e);
                }
                this.failedException = e;
                MyLog.e("SMACK: Could not connect to:" + currentHost);
                errorSB.append("SMACK: Could not connect to ").append(currentHost).append(" port:").append(port).append(" ").append(e.getMessage()).append("\n");
            } catch (Throwable e2) {
                MyLog.e(e2);
            }
        }
        HostManager.getInstance().persist();
        if (!succeeded) {
            throw new XMPPException(errorSB.toString());
        }
    }

    private synchronized void initConnection() throws XMPPException, IOException {
        initReaderAndWriter();
        this.packetWriter = new PacketWriter(this);
        this.packetReader = new PacketReader(this);
        if (this.config.isDebuggerEnabled()) {
            addPacketListener(this.debugger.getReaderListener(), null);
            if (this.debugger.getWriterListener() != null) {
                addPacketSendingListener(this.debugger.getWriterListener(), null);
            }
        }
        this.packetWriter.openStream();
        this.packetReader.startup();
    }

    private void initReaderAndWriter() throws XMPPException {
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"), 4096);
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), "UTF-8"));
            if (this.reader != null && this.writer != null) {
                initDebugger();
            }
        } catch (Throwable e) {
            throw new XMPPException("Error to init reader and writer", e);
        }
    }

    public synchronized void connect() throws XMPPException {
        try {
            if (isConnected() || isConnecting()) {
                MyLog.warn("WARNING: current xmpp has connected");
            } else {
                setConnectionStatus(0, 0, null);
                connectUsingConfiguration(this.config);
            }
        } catch (Throwable e) {
            throw new XMPPException(e);
        }
    }

    public void setPingString(String pingStr) {
        this.pingString = pingStr;
    }

    public String getPingString() {
        String pf;
        if (this.lastPingReceived == 0 || this.lastPingSent == 0) {
            pf = "";
        } else {
            pf = String.format("<pf><p>t:%1$d</p></pf>", new Object[]{Long.valueOf(this.lastPingReceived - this.lastPingSent)});
        }
        return String.format(this.pingString, new Object[]{pf});
    }

    public void notifyConnectionError(final int error, final Exception e) {
        this.pushService.executeJob(new Job(MAX_SHORT_CONN_COUNT) {
            public void process() {
                XMPPConnection.this.pushService.disconnect(error, e);
            }

            public String getDesc() {
                return "shutdown the connection. " + error + ", " + e;
            }
        });
    }

    public Socket createSocket() {
        return new Socket();
    }

    public Fallback getFallback(String host) {
        return HostManager.getInstance().getFallbacksByHost(host);
    }

    public void updateLastSent() {
        this.lastPingSent = SystemClock.uptimeMillis();
    }

    public void updateLastReceived() {
        this.lastPingReceived = SystemClock.uptimeMillis();
    }
}
