package com.xiaomi.smack;

import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.measite.smack.AndroidDebugger;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.debugger.SmackDebugger;
import com.xiaomi.smack.filter.PacketFilter;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Type;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Connection {
    public static boolean DEBUG_ENABLED = false;
    private static final long EFFECTIVE_STATUS = 1800000;
    private static final int MAX_STATUS_CNT = 6;
    public static final long PING_TIMEOUT = 15000;
    private static final AtomicInteger connectionCounter;
    protected String challenge;
    protected ConnectionConfiguration config;
    protected int connTimes;
    private int connectStatus;
    protected long connectTime;
    protected final int connectionCounterValue;
    private final Collection<ConnectionListener> connectionListeners;
    protected SmackDebugger debugger;
    private LinkedList<Pair<Integer, Long>> mCachedStatus;
    protected XMPushService mPushService;
    private long readAlive;
    protected Reader reader;
    protected final Map<PacketListener, ListenerWrapper> recvListeners;
    protected final Map<PacketListener, ListenerWrapper> sendListeners;
    protected Writer writer;

    protected static class ListenerWrapper {
        private PacketFilter packetFilter;
        private PacketListener packetListener;

        public ListenerWrapper(PacketListener packetListener, PacketFilter packetFilter) {
            this.packetListener = packetListener;
            this.packetFilter = packetFilter;
        }

        public void notifyListener(Packet packet) {
            if (this.packetFilter == null || this.packetFilter.accept(packet)) {
                this.packetListener.processPacket(packet);
            }
        }
    }

    public abstract void batchSendPacket(Packet[] packetArr) throws XMPPException;

    public abstract void bind(ClientLoginInfo clientLoginInfo) throws XMPPException;

    public abstract void connect() throws XMPPException;

    public abstract void disconnect(Presence presence, int i, Exception exception);

    public abstract String getUser();

    public abstract void login(String str, String str2, String str3, String str4, String str5, boolean z, String str6) throws XMPPException;

    public abstract void sendPacket(Packet packet) throws XMPPException;

    public abstract void sendPingString() throws XMPPException;

    public abstract void unbind(String str, String str2) throws XMPPException;

    static {
        connectionCounter = new AtomicInteger(0);
        DEBUG_ENABLED = false;
        try {
            DEBUG_ENABLED = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception e) {
        }
        SmackConfiguration.getVersion();
    }

    protected Connection(XMPushService pushService, ConnectionConfiguration configuration) {
        this.connTimes = 0;
        this.connectTime = -1;
        this.mCachedStatus = new LinkedList();
        this.connectionListeners = new CopyOnWriteArrayList();
        this.recvListeners = new ConcurrentHashMap();
        this.sendListeners = new ConcurrentHashMap();
        this.debugger = null;
        this.challenge = "";
        this.connectStatus = 2;
        this.connectionCounterValue = connectionCounter.getAndIncrement();
        this.readAlive = 0;
        this.config = configuration;
        this.mPushService = pushService;
    }

    protected ConnectionConfiguration getConfiguration() {
        return this.config;
    }

    public String getServiceName() {
        return this.config.getServiceName();
    }

    public String getHost() {
        return this.config.getHost();
    }

    public String getConnectionPoint() {
        return this.config.getConnectionPoint();
    }

    public int getPort() {
        return this.config.getPort();
    }

    protected boolean isReconnectionAllowed() {
        return this.config.isReconnectionAllowed();
    }

    public void disconnect() {
        disconnect(new Presence(Type.unavailable), 0, null);
    }

    public void addConnectionListener(ConnectionListener connectionListener) {
        if (connectionListener != null && !this.connectionListeners.contains(connectionListener)) {
            this.connectionListeners.add(connectionListener);
        }
    }

    public void removeConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.remove(connectionListener);
    }

    public Collection<ConnectionListener> getConnectionListeners() {
        return this.connectionListeners;
    }

    public void addPacketListener(PacketListener packetListener, PacketFilter packetFilter) {
        if (packetListener == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.recvListeners.put(packetListener, new ListenerWrapper(packetListener, packetFilter));
    }

    public void removePacketListener(PacketListener packetListener) {
        this.recvListeners.remove(packetListener);
    }

    protected Map<PacketListener, ListenerWrapper> getPacketListeners() {
        return this.recvListeners;
    }

    public void addPacketSendingListener(PacketListener packetListener, PacketFilter packetFilter) {
        if (packetListener == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.sendListeners.put(packetListener, new ListenerWrapper(packetListener, packetFilter));
    }

    public void removePacketSendingListener(PacketListener packetListener) {
        this.sendListeners.remove(packetListener);
    }

    protected Map<PacketListener, ListenerWrapper> getPacketSendingListeners() {
        return this.sendListeners;
    }

    protected void firePacketSendingListeners(Packet packet) {
        for (ListenerWrapper listenerWrapper : this.sendListeners.values()) {
            listenerWrapper.notifyListener(packet);
        }
    }

    protected void initDebugger() {
        if (this.reader != null && this.writer != null && this.config.isDebuggerEnabled()) {
            if (this.debugger == null) {
                String className = null;
                try {
                    className = System.getProperty("smack.debuggerClass");
                } catch (Throwable th) {
                }
                Class<?> debuggerClass = null;
                if (className != null) {
                    try {
                        debuggerClass = Class.forName(className);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (debuggerClass == null) {
                    this.debugger = new AndroidDebugger(this, this.writer, this.reader);
                    this.reader = this.debugger.getReader();
                    this.writer = this.debugger.getWriter();
                    return;
                }
                try {
                    this.debugger = (SmackDebugger) debuggerClass.getConstructor(new Class[]{Connection.class, Writer.class, Reader.class}).newInstance(new Object[]{this, this.writer, this.reader});
                    this.reader = this.debugger.getReader();
                    this.writer = this.debugger.getWriter();
                    return;
                } catch (Exception e2) {
                    throw new IllegalArgumentException("Can't initialize the configured debugger!", e2);
                }
            }
            this.reader = this.debugger.newConnectionReader(this.reader);
            this.writer = this.debugger.newConnectionWriter(this.writer);
        }
    }

    public boolean isConnecting() {
        return this.connectStatus == 0;
    }

    public boolean isConnected() {
        return this.connectStatus == 1;
    }

    public int getConnTryTimes() {
        return this.connTimes;
    }

    public void resetConnTryTimes() {
        this.connTimes = 0;
    }

    public long getConnectTime() {
        return this.connectTime;
    }

    public void resetConnectTime() {
        this.connectTime = -1;
    }

    public void setConnectionStatus(int newStatus, int reason, Exception e) {
        if (newStatus != this.connectStatus) {
            MyLog.warn(String.format("update the connection status. %1$s -> %2$s : %3$s ", new Object[]{getDesc(this.connectStatus), getDesc(newStatus), PushConstants.getErrorDesc(reason)}));
        }
        if (Network.hasNetwork(this.mPushService)) {
            addStatus(newStatus);
        }
        if (newStatus == 1) {
            this.mPushService.removeJobs(10);
            if (this.connectStatus != 0) {
                MyLog.warn("try set connected while not connecting.");
            }
            this.connectStatus = newStatus;
            for (ConnectionListener listener : this.connectionListeners) {
                listener.reconnectionSuccessful();
            }
        } else if (newStatus == 0) {
            this.mPushService.setConnectingTimeout();
            if (this.connectStatus != 2) {
                MyLog.warn("try set connecting while not disconnected.");
            }
            this.connectStatus = newStatus;
            for (ConnectionListener listener2 : this.connectionListeners) {
                listener2.connectionStarted();
            }
        } else if (newStatus == 2) {
            this.mPushService.removeJobs(10);
            if (this.connectStatus == 0) {
                for (ConnectionListener listener22 : this.connectionListeners) {
                    listener22.reconnectionFailed(e == null ? new CancellationException("disconnect while connecting") : e);
                }
            } else if (this.connectStatus == 1) {
                for (ConnectionListener listener222 : this.connectionListeners) {
                    listener222.connectionClosed(reason, e);
                }
            }
            this.connectStatus = newStatus;
        }
    }

    private String getDesc(int connectStatus) {
        if (connectStatus == 1) {
            return "connected";
        }
        if (connectStatus == 0) {
            return "connecting";
        }
        if (connectStatus == 2) {
            return "disconnected";
        }
        return "unknown";
    }

    public int getConnectionStatus() {
        return this.connectStatus;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
        setConnectionStatus(1, 0, null);
    }

    public void setReadAlive() {
        this.readAlive = System.currentTimeMillis();
    }

    public boolean isReadAlive() {
        return System.currentTimeMillis() - this.readAlive < ((long) SmackConfiguration.getCheckAliveInterval());
    }

    public boolean isReadAlive(long checkTime) {
        return this.readAlive >= checkTime;
    }

    private void addStatus(int newStatus) {
        synchronized (this.mCachedStatus) {
            if (newStatus == 1) {
                this.mCachedStatus.clear();
            } else {
                this.mCachedStatus.add(new Pair(Integer.valueOf(newStatus), Long.valueOf(System.currentTimeMillis())));
                if (this.mCachedStatus.size() > MAX_STATUS_CNT) {
                    this.mCachedStatus.remove(0);
                }
            }
        }
    }

    public boolean isAlwaysFailed() {
        boolean z;
        synchronized (this.mCachedStatus) {
            ArrayList<Pair<Integer, Long>> acceptedStatus = new ArrayList();
            Iterator i$ = this.mCachedStatus.iterator();
            while (i$.hasNext()) {
                Pair<Integer, Long> status = (Pair) i$.next();
                if (System.currentTimeMillis() - ((Long) status.second).longValue() < EFFECTIVE_STATUS) {
                    acceptedStatus.add(status);
                }
            }
            this.mCachedStatus.clear();
            this.mCachedStatus.addAll(acceptedStatus);
            z = this.mCachedStatus.size() >= MAX_STATUS_CNT;
        }
        return z;
    }

    public void clearCachedStatus() {
        synchronized (this.mCachedStatus) {
            this.mCachedStatus.clear();
        }
    }
}
