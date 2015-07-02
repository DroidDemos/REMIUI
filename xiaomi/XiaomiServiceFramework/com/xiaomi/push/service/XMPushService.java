package com.xiaomi.push.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.kenai.jbosh.BOSHClient;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.PushClientsManager.ClientChangeListener;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo.ClientStatusListener;
import com.xiaomi.push.service.PushClientsManager.ClientStatus;
import com.xiaomi.push.service.timers.AlarmManagerTimer;
import com.xiaomi.smack.BOSHConfiguration;
import com.xiaomi.smack.BOSHConnection;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.ConnectionListener;
import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.filter.PacketFilter;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Type;
import com.xiaomi.smack.util.StringUtils;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.Target;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.thrift.TBase;

public class XMPushService extends Service implements ConnectionListener {
    public static final String ACTION_CONNECTIVITY_INFO = "com.xiaomi.channel.CONNECTIVITY_INFO";
    public static final String ACTION_MILIAO_PUSH_STARTED = "com.xiaomi.channel.PUSH_STARTED";
    public static final int CHECK_ALIVE_INTERVAL = 30000;
    private static final String CHID_MILIAO = "1";
    public static final int CONNECTING_TIMEOUT = 120000;
    public static final int LOGIN_TIMEOUT = 30000;
    public static final String MSG_DATA_KEY_HOST = "msg_data_hots";
    private static final String PACKAGE_NAME_MILIAO = "com.xiaomi.channel";
    public static int START_STICKY = 0;
    public static final int TIMER_RESET_CONNECTION = 30000;
    private static final String VERSION = "2.1";
    private static final boolean XMPPCONNECTION_DEBUG_ENABLED = true;
    private BOSHConfiguration boshConfig;
    private ConnectionConfiguration connConfig;
    private long lastAlive;
    private BOSHConnection mBOSHConnection;
    private ClientEventDispatcher mClientEventDispatcher;
    private Connection mCurrentConnection;
    private ConnectionJobController mJobController;
    private PacketListener mPacketListener;
    private PacketSync mPacketSync;
    private ReconnectionManager mReconnManager;
    private AlarmManagerTimer mTimer;
    private XMPPConnection mXMPPConnection;

    public static abstract class Job {
        public static final int TYPE_BIND_TIMEOUT = 12;
        public static final int TYPE_BIND_UNBIND = 9;
        public static final int TYPE_CLEAR_ACCOUNT_CACHE = 14;
        public static final int TYPE_CONNECT = 1;
        public static final int TYPE_CONNECTING_TIMEOUT = 10;
        public static final int TYPE_DISCONNECT = 2;
        public static final int TYPE_MAX = 15;
        public static final int TYPE_MIN = 1;
        public static final int TYPE_PING_TIMEOUT = 13;
        public static final int TYPE_PREPARE_MIPUSH_ACCOUNT = 11;
        public static final int TYPE_QUIT = 5;
        public static final int TYPE_RECEIVE_CHALLENGE = 7;
        public static final int TYPE_RECEIVE_MSG = 8;
        public static final int TYPE_RECEIVE_TIMEOUT = 6;
        public static final int TYPE_RESET_CONNECT = 3;
        public static final int TYPE_SEND_MSG = 4;
        protected int type;

        public abstract String getDesc();

        public abstract void process();

        public Job(int type) {
            this.type = type;
        }

        public void run() {
            if (!(this.type == TYPE_SEND_MSG || this.type == TYPE_RECEIVE_MSG)) {
                MyLog.warn("JOB: " + getDesc());
            }
            process();
        }
    }

    class BindJob extends Job {
        ClientLoginInfo mLoginInfo;

        public BindJob(ClientLoginInfo info) {
            super(9);
            this.mLoginInfo = null;
            this.mLoginInfo = info;
        }

        public void process() {
            try {
                if (!XMPushService.this.isConnected()) {
                    MyLog.e("trying bind while the connection is not created, quit!");
                } else if (this.mLoginInfo.status == ClientStatus.unbind) {
                    this.mLoginInfo.setStatus(ClientStatus.binding, 0, 0, null, null);
                    XMPushService.this.mCurrentConnection.bind(this.mLoginInfo);
                } else {
                    MyLog.warn("trying duplicate bind, ingore! " + this.mLoginInfo.status);
                }
            } catch (Throwable e) {
                MyLog.e(e);
                XMPushService.this.disconnect(10, e);
            }
        }

        public String getDesc() {
            return "bind the client. " + this.mLoginInfo.chid + ", " + this.mLoginInfo.userId;
        }
    }

    static class BindTimeoutJob extends Job {
        public static final int BIND_TIMEOUT = 60000;
        private final ClientLoginInfo mLoginInfo;

        public BindTimeoutJob(ClientLoginInfo info) {
            super(12);
            this.mLoginInfo = info;
        }

        public void process() {
            this.mLoginInfo.setStatus(ClientStatus.unbind, 1, 21, null, null);
        }

        public String getDesc() {
            return "bind time out. chid=" + this.mLoginInfo.chid;
        }

        public boolean equals(Object o) {
            if (o instanceof BindTimeoutJob) {
                return TextUtils.equals(((BindTimeoutJob) o).mLoginInfo.chid, this.mLoginInfo.chid);
            }
            return false;
        }

        public int hashCode() {
            return this.mLoginInfo.chid.hashCode();
        }
    }

    public class ConnectJob extends Job {
        ConnectJob() {
            super(1);
        }

        public void process() {
            if (XMPushService.this.shouldReconnect()) {
                XMPushService.this.connect();
            } else {
                MyLog.warn("should not connect. quit the job.");
            }
        }

        public String getDesc() {
            return "do reconnect..";
        }
    }

    public class DisconnectJob extends Job {
        public Exception e;
        public int reason;

        DisconnectJob(int reason, Exception e) {
            super(2);
            this.reason = reason;
            this.e = e;
        }

        public void process() {
            XMPushService.this.disconnect(this.reason, this.e);
        }

        public String getDesc() {
            return "disconnect the connection.";
        }
    }

    class KillJob extends Job {
        public KillJob() {
            super(5);
        }

        public void process() {
            XMPushService.this.mJobController.quit();
        }

        public String getDesc() {
            return "ask the job queue to quit";
        }
    }

    public class MessageChannel extends Binder {
        public void sendMessage(Message message) {
            if (message != null) {
                XMPushService.this.executeJob(new SendMessageJob(XMPushService.this, message));
            }
        }
    }

    class PacketReceiveJob extends Job {
        private Packet mPacket;

        public PacketReceiveJob(Packet packet) {
            super(8);
            this.mPacket = null;
            this.mPacket = packet;
        }

        public void process() {
            XMPushService.this.mPacketSync.onPacketReceive(this.mPacket);
        }

        public String getDesc() {
            return "receive a message.";
        }
    }

    class PingJob extends Job {
        public PingJob() {
            super(4);
        }

        public void process() {
            if (XMPushService.this.isConnected()) {
                try {
                    XMPushService.this.mCurrentConnection.sendPingString();
                } catch (Throwable e) {
                    MyLog.e(e);
                    XMPushService.this.disconnect(10, e);
                }
            }
        }

        public String getDesc() {
            return "send ping..";
        }
    }

    class ReBindJob extends Job {
        ClientLoginInfo mLoginInfo;

        public ReBindJob(ClientLoginInfo info) {
            super(4);
            this.mLoginInfo = null;
            this.mLoginInfo = info;
        }

        public void process() {
            try {
                this.mLoginInfo.setStatus(ClientStatus.unbind, 1, 16, null, null);
                XMPushService.this.mCurrentConnection.unbind(this.mLoginInfo.chid, this.mLoginInfo.userId);
                this.mLoginInfo.setStatus(ClientStatus.binding, 1, 16, null, null);
                XMPushService.this.mCurrentConnection.bind(this.mLoginInfo);
            } catch (Throwable e) {
                MyLog.e(e);
                XMPushService.this.disconnect(10, e);
            }
        }

        public String getDesc() {
            return "bind the client. " + this.mLoginInfo.chid + ", " + this.mLoginInfo.userId;
        }
    }

    class ResetConnectionJob extends Job {
        ResetConnectionJob() {
            super(3);
        }

        public void process() {
            XMPushService.this.disconnect(11, null);
            if (XMPushService.this.shouldReconnect()) {
                XMPushService.this.connect();
            }
        }

        public String getDesc() {
            return "reset the connection.";
        }
    }

    class UnbindJob extends Job {
        String kickType;
        ClientLoginInfo mLoginInfo;
        int mNotifyType;
        String reason;

        public UnbindJob(ClientLoginInfo info, int notifyType, String kickType, String reason) {
            super(9);
            this.mLoginInfo = null;
            this.mLoginInfo = info;
            this.mNotifyType = notifyType;
            this.kickType = kickType;
            this.reason = reason;
        }

        public void process() {
            if (!(this.mLoginInfo.status == ClientStatus.unbind || XMPushService.this.mCurrentConnection == null)) {
                try {
                    XMPushService.this.mCurrentConnection.unbind(this.mLoginInfo.chid, this.mLoginInfo.userId);
                } catch (Throwable e) {
                    MyLog.e(e);
                    XMPushService.this.disconnect(10, e);
                }
            }
            this.mLoginInfo.setStatus(ClientStatus.unbind, this.mNotifyType, 0, this.reason, this.kickType);
        }

        public String getDesc() {
            return "unbind the channel. " + this.mLoginInfo.chid + ", " + this.mLoginInfo.userId;
        }
    }

    public XMPushService() {
        this.lastAlive = 0;
        this.mPacketSync = null;
        this.mTimer = null;
        this.mJobController = null;
        this.mPacketListener = new PacketListener() {
            public void processPacket(Packet packet) {
                XMPushService.this.executeJob(new PacketReceiveJob(packet));
            }
        };
    }

    static {
        HostManager.addReservedHost(ConnectionConfiguration.XMPP_SERVER_HOST_P, "58.68.235.232");
        HostManager.addReservedHost(ConnectionConfiguration.XMPP_SERVER_HOST_P, "app01.nodes.gslb.mi-idc.com");
        HostManager.addReservedHost(ConnectionConfiguration.XMPP_SERVER_HOST_P, "42.62.48.181");
        HostManager.addReservedHost(ConnectionConfiguration.XMPP_SERVER_HOST_P, "223.202.68.46");
        HostManager.addReservedHost(ConnectionConfiguration.XMPP_SERVER_HOST_P, "app02.nodes.gslb.mi-idc.com");
        XMPPConnection.DEBUG_ENABLED = XMPPCONNECTION_DEBUG_ENABLED;
        if (BuildSettings.IsDebugBuild || BuildSettings.IsTestBuild || BuildSettings.IsLogableBuild || BuildSettings.IsRCBuild) {
            MyLog.setLogLevel(0);
        }
        START_STICKY = 1;
    }

    public void onCreate() {
        super.onCreate();
        MIPushAccount account = MIPushAccountUtils.getMIPushAccount(this);
        if (account != null) {
            BuildSettings.setEnvType(account.envType);
        }
        HostManager.init(this, null, null, "0", "push", VERSION);
        this.connConfig = new ConnectionConfiguration(null, PushServiceConstants.XMPP_SERVER_PORT, PushServiceConstants.XMPP_SERVICE_NAME, null);
        this.connConfig.setDebuggerEnabled(XMPPCONNECTION_DEBUG_ENABLED);
        this.mXMPPConnection = createXMPPConnection(this.connConfig);
        this.mXMPPConnection.setPingString(generatePingString(PushServiceConstants.XMPP_SERVICE_NAME));
        this.boshConfig = new BOSHConfiguration(false, new Fallback(PushServiceConstants.XMPP_BOSH_HOST), 80, "mibind/http-bind", PushServiceConstants.XMPP_SERVICE_NAME, null);
        System.setProperty(BOSHClient.class.getName() + ".emptyRequestDelay", String.valueOf(1000));
        this.mBOSHConnection = new BOSHConnection(this, this.boshConfig);
        this.mClientEventDispatcher = createClientEventDispatcher();
        this.mClientEventDispatcher.notifyServiceStarted(this);
        this.mTimer = new AlarmManagerTimer(this);
        this.mXMPPConnection.addConnectionListener(this);
        this.mBOSHConnection.addConnectionListener(this);
        this.mPacketSync = new PacketSync(this);
        this.mReconnManager = new ReconnectionManager(this);
        new CommonPacketExtensionProvider().register();
        this.mJobController = new ConnectionJobController("Connection Controller Thread");
        this.mJobController.start();
        executeJob(new Job(11) {
            public void process() {
                XMPushService.this.prepareMIPushAccount();
            }

            public String getDesc() {
                return "prepare the mi push account.";
            }
        });
        PushClientsManager pcm = PushClientsManager.getInstance();
        pcm.removeAllClientChangeListeners();
        pcm.addClientChangeListener(new ClientChangeListener() {
            public void onChange() {
                XMPushService.this.updateAlarmTimer();
                if (PushClientsManager.getInstance().getActiveClientCount() <= 0) {
                    XMPushService.this.executeJob(new DisconnectJob(12, null));
                }
            }
        });
    }

    private void prepareMIPushAccount() {
        if (MIPushAccountUtils.getMIPushAccount(getApplicationContext()) != null) {
            ClientLoginInfo loginInfo = MIPushAccountUtils.getMIPushAccount(getApplicationContext()).toClientLoginInfo(this);
            prepareMIPushClientLoginInfo(loginInfo);
            PushClientsManager.getInstance().addActiveClient(loginInfo);
            if (Network.hasNetwork(getApplicationContext())) {
                scheduleConnect(XMPPCONNECTION_DEBUG_ENABLED);
            }
        }
    }

    public void onStart(Intent intent, int startId) {
        if (intent == null) {
            MyLog.e("onStart() with intent NULL");
        } else {
            MyLog.v("onStart() with intent.Action = " + intent.getAction());
        }
        PushClientsManager pcm = PushClientsManager.getInstance();
        if (intent != null && intent.getAction() != null) {
            String chid;
            String session;
            if (PushConstants.ACTION_OPEN_CHANNEL.equalsIgnoreCase(intent.getAction()) || PushConstants.ACTION_FORCE_RECONNECT.equalsIgnoreCase(intent.getAction())) {
                chid = intent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
                session = intent.getStringExtra(PushConstants.EXTRA_SESSION);
                if (TextUtils.isEmpty(intent.getStringExtra(PushConstants.EXTRA_SECURITY))) {
                    MyLog.warn("security is empty. ignore.");
                } else if (chid != null) {
                    boolean sessionChanged = false;
                    ClientLoginInfo cLoginInfo = updatePushClient(chid, intent);
                    if (!TextUtils.isEmpty(cLoginInfo.session)) {
                        if (!TextUtils.equals(session, cLoginInfo.session)) {
                            sessionChanged = XMPPCONNECTION_DEBUG_ENABLED;
                            MyLog.warn("session changed. old session=" + cLoginInfo.session + ", new session=" + session);
                        }
                    }
                    cLoginInfo.session = session;
                    if (!Network.hasNetwork(this)) {
                        this.mClientEventDispatcher.notifyChannelOpenResult(this, cLoginInfo, false, 2, null);
                    } else if (!isConnected()) {
                        scheduleConnect(XMPPCONNECTION_DEBUG_ENABLED);
                    } else if (sessionChanged) {
                        executeJob(new ReBindJob(cLoginInfo));
                    } else if (cLoginInfo.status == ClientStatus.binding) {
                        MyLog.warn(String.format("the client is binding. %1$s %2$s.", new Object[]{cLoginInfo.chid, cLoginInfo.userId}));
                    } else if (cLoginInfo.status == ClientStatus.binded) {
                        this.mClientEventDispatcher.notifyChannelOpenResult(this, cLoginInfo, XMPPCONNECTION_DEBUG_ENABLED, null, null);
                    } else {
                        executeJob(new BindJob(cLoginInfo));
                    }
                } else {
                    MyLog.e("channel id is empty, do nothing!");
                }
            } else if (PushConstants.ACTION_CLOSE_CHANNEL.equalsIgnoreCase(intent.getAction())) {
                pkgName = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                chid = intent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
                userId = intent.getStringExtra(PushConstants.EXTRA_USER_ID);
                if (TextUtils.isEmpty(chid)) {
                    for (String closeAllChannelByChid : pcm.queryChannelIdByPackage(pkgName)) {
                        closeAllChannelByChid(closeAllChannelByChid, 2);
                    }
                } else if (TextUtils.isEmpty(userId)) {
                    closeAllChannelByChid(chid, 2);
                } else {
                    closeChannel(chid, userId, 2, null, null);
                }
            } else if (PushConstants.ACTION_SEND_MESSAGE.equalsIgnoreCase(intent.getAction())) {
                pkgName = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                session = intent.getStringExtra(PushConstants.EXTRA_SESSION);
                Bundle msgBundle = intent.getBundleExtra(PushConstants.EXTRA_PACKET);
                encrypt = intent.getBooleanExtra(PushConstants.EXTRA_ENCYPT, XMPPCONNECTION_DEBUG_ENABLED);
                Packet newPacket = preparePacket(new Message(msgBundle), pkgName, session, encrypt);
                if (newPacket != null) {
                    executeJob(new SendMessageJob(this, newPacket));
                }
            } else if (PushConstants.ACTION_BATCH_SEND_MESSAGE.equalsIgnoreCase(intent.getAction())) {
                pkgName = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                session = intent.getStringExtra(PushConstants.EXTRA_SESSION);
                Parcelable[] msgParcelable = intent.getParcelableArrayExtra(PushConstants.EXTRA_PACKETS);
                Message[] messages = new Message[msgParcelable.length];
                encrypt = intent.getBooleanExtra(PushConstants.EXTRA_ENCYPT, XMPPCONNECTION_DEBUG_ENABLED);
                int i = 0;
                while (i < msgParcelable.length) {
                    messages[i] = new Message((Bundle) msgParcelable[i]);
                    messages[i] = (Message) preparePacket(messages[i], pkgName, session, encrypt);
                    if (messages[i] != null) {
                        i++;
                    } else {
                        return;
                    }
                }
                executeJob(new BatchSendMessageJob(this, messages));
            } else if (PushConstants.ACTION_SEND_IQ.equalsIgnoreCase(intent.getAction())) {
                pkgName = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                session = intent.getStringExtra(PushConstants.EXTRA_SESSION);
                r0 = new IQ(intent.getBundleExtra(PushConstants.EXTRA_PACKET));
                if (preparePacket(r0, pkgName, session, false) != null) {
                    executeJob(new SendMessageJob(this, r0));
                }
            } else if (PushConstants.ACTION_SEND_PRESENCE.equalsIgnoreCase(intent.getAction())) {
                pkgName = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                session = intent.getStringExtra(PushConstants.EXTRA_SESSION);
                r0 = new Presence(intent.getBundleExtra(PushConstants.EXTRA_PACKET));
                if (preparePacket(r0, pkgName, session, false) != null) {
                    executeJob(new SendMessageJob(this, r0));
                }
            } else if (PushServiceConstants.ACTION_TIMER.equalsIgnoreCase(intent.getAction()) || PushServiceConstants.ACTION_CHECK_ALIVE.equalsIgnoreCase(intent.getAction())) {
                if (PushServiceConstants.ACTION_TIMER.equalsIgnoreCase(intent.getAction())) {
                    MyLog.warn("Service called on timer");
                } else if (System.currentTimeMillis() - this.lastAlive >= 30000) {
                    this.lastAlive = System.currentTimeMillis();
                    MyLog.warn("Service called on check alive.");
                } else {
                    return;
                }
                if (this.mJobController.isBlocked()) {
                    MyLog.e("ERROR, the job controller is blocked.");
                    PushClientsManager.getInstance().resetAllClients(this, 14);
                    stopSelf();
                } else if (isConnected()) {
                    if (this.mCurrentConnection.isReadAlive()) {
                        executeJob(new PingJob());
                        return;
                    }
                    executeJob(new DisconnectJob(17, null));
                } else if (PushServiceConstants.ACTION_TIMER.equalsIgnoreCase(intent.getAction())) {
                    scheduleConnect(false);
                } else {
                    scheduleConnect(XMPPCONNECTION_DEBUG_ENABLED);
                }
            } else if (PushServiceConstants.ACTION_NETWORK_STATUS_CHANGED.equalsIgnoreCase(intent.getAction())) {
                NetworkInfo currentNetworkInfo = null;
                try {
                    currentNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
                } catch (Throwable e) {
                    MyLog.e(e);
                }
                if (currentNetworkInfo != null) {
                    MyLog.warn("network changed, " + currentNetworkInfo.toString());
                } else {
                    MyLog.warn("network changed, no active network");
                }
                this.mXMPPConnection.clearCachedStatus();
                this.mBOSHConnection.clearCachedStatus();
                if (!Network.hasNetwork(this)) {
                    executeJob(new DisconnectJob(2, null));
                } else if (!(isConnected() || isConnecting())) {
                    this.mJobController.removeJobs(1);
                    executeJob(new ConnectJob());
                }
                updateAlarmTimer();
            } else if (PushConstants.ACTION_RESET_CONNECTION.equals(intent.getAction())) {
                chid = intent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
                if (chid != null) {
                    updatePushClient(chid, intent).session = intent.getStringExtra(PushConstants.EXTRA_SESSION);
                }
                executeJob(new ResetConnectionJob());
            } else if (PushConstants.ACTION_UPDATE_CHANNEL_INFO.equals(intent.getAction())) {
                pkgName = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                List<String> ids = pcm.queryChannelIdByPackage(pkgName);
                if (ids.isEmpty()) {
                    MyLog.warn("open channel should be called first before update info, pkg=" + pkgName);
                    return;
                }
                chid = intent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
                userId = intent.getStringExtra(PushConstants.EXTRA_USER_ID);
                if (TextUtils.isEmpty(chid)) {
                    chid = (String) ids.get(0);
                }
                ClientLoginInfo loginInfo = null;
                if (TextUtils.isEmpty(userId)) {
                    Collection<ClientLoginInfo> infos = pcm.getAllClientLoginInfoByChid(chid);
                    if (!(infos == null || infos.isEmpty())) {
                        loginInfo = (ClientLoginInfo) infos.iterator().next();
                    }
                } else {
                    loginInfo = pcm.getClientLoginInfoByChidAndUserId(chid, userId);
                }
                if (loginInfo != null) {
                    if (intent.hasExtra(PushConstants.EXTRA_CLIENT_ATTR)) {
                        loginInfo.clientExtra = intent.getStringExtra(PushConstants.EXTRA_CLIENT_ATTR);
                    }
                    if (intent.hasExtra(PushConstants.EXTRA_CLOUD_ATTR)) {
                        loginInfo.cloudExtra = intent.getStringExtra(PushConstants.EXTRA_CLOUD_ATTR);
                    }
                }
            } else if (PushConstants.MIPUSH_ACTION_REGISTER_APP.equals(intent.getAction())) {
                if (ServiceClient.getInstance(getApplicationContext()).checkProvisioned() && ServiceClient.getInstance(getApplicationContext()).getProvisioned() == 0) {
                    MyLog.warn("register without being provisioned. " + intent.getStringExtra(PushConstants.MIPUSH_EXTRA_APP_PACKAGE));
                    return;
                }
                payload = intent.getByteArrayExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD);
                packageName = intent.getStringExtra(PushConstants.MIPUSH_EXTRA_APP_PACKAGE);
                boolean isEnvChanage = intent.getBooleanExtra(PushConstants.MIPUSH_EXTRA_ENV_CHANAGE, false);
                final int envType = intent.getIntExtra(PushConstants.MIPUSH_EXTRA_ENV_TYPE, 1);
                MIPushAppInfo.getInstance(this).removeUnRegisteredPkg(packageName);
                if (!isEnvChanage || LogProvider.AUTHORITY.equals(getPackageName())) {
                    registerForMiPushApp(payload, packageName);
                } else {
                    executeJob(new Job(14) {
                        public void process() {
                            MIPushAccountUtils.clearAccount(XMPushService.this);
                            PushClientsManager.getInstance().deactivateAllClientByChid(PushConstants.MIPUSH_CHANNEL);
                            BuildSettings.setEnvType(envType);
                            XMPushService.this.connConfig.setHost(ConnectionConfiguration.getXmppServerHost());
                            XMPushService.this.registerForMiPushApp(payload, packageName);
                        }

                        public String getDesc() {
                            return "clear account cache.";
                        }
                    });
                }
            } else if (PushConstants.MIPUSH_ACTION_SEND_MESSAGE.equals(intent.getAction()) || PushConstants.MIPUSH_ACTION_UNREGISTER_APP.equals(intent.getAction())) {
                packageName = intent.getStringExtra(PushConstants.MIPUSH_EXTRA_APP_PACKAGE);
                payload = intent.getByteArrayExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD);
                Collection<ClientLoginInfo> loginInfos = PushClientsManager.getInstance().getAllClientLoginInfoByChid(PushConstants.MIPUSH_CHANNEL);
                if (PushConstants.MIPUSH_ACTION_UNREGISTER_APP.equals(intent.getAction())) {
                    MIPushAppInfo.getInstance(this).addUnRegisteredPkg(packageName);
                }
                if (loginInfos.isEmpty()) {
                    MIPushClientManager.addPendingMessages(packageName, payload);
                    return;
                }
                if (((ClientLoginInfo) loginInfos.iterator().next()).status != ClientStatus.binded) {
                    MIPushClientManager.addPendingMessages(packageName, payload);
                    return;
                }
                closeAllChannelByChid = packageName;
                executeJob(new Job(4) {
                    public void process() {
                        try {
                            XMPushService.this.sendMIPushPacket(closeAllChannelByChid, payload);
                        } catch (Throwable e) {
                            MyLog.e(e);
                            XMPushService.this.disconnect(10, e);
                        }
                    }

                    public String getDesc() {
                        return "send mi push message";
                    }
                });
            } else if (PushServiceConstants.ACTION_UNINSTALL.equals(intent.getAction())) {
                pkgName = intent.getStringExtra(LogProvider.AUTHORITY);
                if (pkgName != null && !TextUtils.isEmpty(pkgName.trim())) {
                    boolean uninstalled = false;
                    try {
                        getPackageManager().getPackageInfo(pkgName, 256);
                    } catch (NameNotFoundException e2) {
                        uninstalled = XMPPCONNECTION_DEBUG_ENABLED;
                    }
                    if (PACKAGE_NAME_MILIAO.equals(pkgName) && !PushClientsManager.getInstance().getAllClientLoginInfoByChid(CHID_MILIAO).isEmpty() && uninstalled) {
                        closeAllChannelByChid(CHID_MILIAO, 0);
                        MyLog.warn("close the miliao channel as the app is uninstalled.");
                        return;
                    }
                    SharedPreferences sp = getSharedPreferences(PushServiceConstants.PREF_KEY_REGISTERED_PKGS, 0);
                    if (sp.contains(pkgName) && uninstalled) {
                        Editor editor = sp.edit();
                        editor.remove(pkgName);
                        editor.commit();
                        if (isConnected()) {
                            String appId = sp.getString(pkgName, null);
                            if (appId != null) {
                                try {
                                    sendMIPushPacket(contructAppAbsentMessage(pkgName, appId));
                                    MyLog.warn("\"uninstall " + pkgName + "\" msg sent");
                                } catch (Exception e3) {
                                    MyLog.e("Fail to send Message: " + e3.getMessage());
                                    disconnect(10, e3);
                                }
                            }
                        }
                    }
                }
            } else if (PushConstants.MIPUSH_ACTION_CLEAR_NOTIFICATION.equals(intent.getAction())) {
                String pkg = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                int notifyId = intent.getIntExtra(PushConstants.EXTRA_NOTIFY_ID, 0);
                if (!TextUtils.isEmpty(pkg)) {
                    if (notifyId >= 0) {
                        MIPushNotificationHelper.clearNotification(this, pkg, notifyId);
                    } else if (notifyId == -1) {
                        MIPushNotificationHelper.clearNotification(this, pkg);
                    }
                }
            }
        }
    }

    public void registerForMiPushApp(byte[] payload, String packageName) {
        if (payload == null) {
            MIPushClientManager.notifyError(this, packageName, payload, PushConstants.MIPUSH_ERROR_INVALID_PAYLOAD, "null payload");
            MyLog.warn("register request without payload");
            return;
        }
        XmPushActionContainer container = new XmPushActionContainer();
        try {
            XmPushThriftSerializeUtils.convertByteArrayToThriftObject(container, payload);
            if (container.action == ActionType.Registration) {
                XmPushActionRegistration registerPacket = new XmPushActionRegistration();
                try {
                    XmPushThriftSerializeUtils.convertByteArrayToThriftObject(registerPacket, container.getPushAction());
                    MIPushClientManager.registerApp(container.getPackageName(), payload);
                    executeJob(new MIPushAppRegisterJob(this, container.getPackageName(), registerPacket.getAppId(), registerPacket.getToken(), payload));
                    return;
                } catch (Throwable e) {
                    MyLog.e(e);
                    MIPushClientManager.notifyError(this, packageName, payload, PushConstants.MIPUSH_ERROR_INVALID_PAYLOAD, " data action error.");
                    return;
                }
            }
            MIPushClientManager.notifyError(this, packageName, payload, PushConstants.MIPUSH_ERROR_INVALID_PAYLOAD, " registration action required.");
            MyLog.warn("register request with invalid payload");
        } catch (Throwable e2) {
            MyLog.e(e2);
            MIPushClientManager.notifyError(this, packageName, payload, PushConstants.MIPUSH_ERROR_INVALID_PAYLOAD, " data container error.");
        }
    }

    public XmPushActionContainer contructAppAbsentMessage(String pkgName, String appId) {
        XmPushActionNotification message = new XmPushActionNotification();
        message.setAppId(appId);
        message.setType("package uninstalled");
        message.setId(Packet.nextID());
        message.setRequireAck(false);
        return generateRequestContainer(pkgName, appId, message, ActionType.Notification);
    }

    public <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(String pkgName, String appId, T message, ActionType action) {
        byte[] msgbytes = XmPushThriftSerializeUtils.convertThriftObjectToBytes(message);
        XmPushActionContainer container = new XmPushActionContainer();
        Target target = new Target();
        target.channelId = 5;
        target.userId = "fakeid";
        container.setTarget(target);
        container.setPushAction(ByteBuffer.wrap(msgbytes));
        container.setAction(action);
        container.setIsRequest(XMPPCONNECTION_DEBUG_ENABLED);
        container.setPackageName(pkgName);
        container.setEncryptAction(false);
        container.setAppid(appId);
        return container;
    }

    public Message constructMIPushMessage(byte[] payload) {
        XmPushActionContainer container = new XmPushActionContainer();
        try {
            XmPushThriftSerializeUtils.convertByteArrayToThriftObject(container, payload);
            return constructMIPushMessage(container);
        } catch (Throwable e) {
            MyLog.e(e);
            return null;
        }
    }

    public Message constructMIPushMessage(XmPushActionContainer container) {
        try {
            Message message = new Message();
            message.setChannelId(PushConstants.MIPUSH_CHANNEL);
            message.setTo(PushServiceConstants.XMPP_SERVICE_NAME);
            message.setFrom(MIPushAccountUtils.getMIPushAccount(this).account);
            message.setEncrypted(XMPPCONNECTION_DEBUG_ENABLED);
            message.setType("push");
            message.setPackageName(container.packageName);
            String accountStr = MIPushAccountUtils.getMIPushAccount(this).account;
            container.target.userId = accountStr.substring(0, accountStr.indexOf("@"));
            container.target.resource = accountStr.substring(accountStr.indexOf("/") + 1);
            String encryptedContent = String.valueOf(Base64Coder.encode(RC4Cryption.encrypt(RC4Cryption.generateKeyForRC4(MIPushAccountUtils.getMIPushAccount(this).security, message.getPacketID()), XmPushThriftSerializeUtils.convertThriftObjectToBytes(container))));
            CommonPacketExtension contentExt = new CommonPacketExtension("s", null, (String[]) null, (String[]) null);
            contentExt.setText(encryptedContent);
            message.addExtension(contentExt);
            MyLog.warn("try send mi push message. packagename:" + container.packageName + " action:" + container.action);
            return message;
        } catch (Throwable e) {
            MyLog.e(e);
            return null;
        }
    }

    public void prepareMIPushClientLoginInfo(ClientLoginInfo loginInfo) {
        loginInfo.addClientStatusListener(new ClientStatusListener() {
            public void onChange(ClientStatus oldStatus, ClientStatus newStatus, int reason) {
                if (newStatus == ClientStatus.binded) {
                    MIPushClientManager.processPendingRegistrationRequest(XMPushService.this);
                    MIPushClientManager.processPendingMessages(XMPushService.this);
                } else if (newStatus == ClientStatus.unbind) {
                    MIPushClientManager.notifyRegisterError(XMPushService.this, PushConstants.MIPUSH_ERROR_SERVICE_UNAVAILABLE, " the push is not connected.");
                }
            }
        });
    }

    private Packet preparePacket(Packet packet, String sourcePackage, String session, boolean entrypt) {
        PushClientsManager pcm = PushClientsManager.getInstance();
        List<String> ids = pcm.queryChannelIdByPackage(sourcePackage);
        if (ids.isEmpty()) {
            MyLog.warn("open channel should be called first before sending a packet, pkg=" + sourcePackage);
        } else {
            packet.setPackageName(sourcePackage);
            String chid = packet.getChannelId();
            if (TextUtils.isEmpty(chid)) {
                chid = (String) ids.get(0);
                packet.setChannelId(chid);
            }
            ClientLoginInfo loginInfo = pcm.getClientLoginInfoByChidAndUserId(chid, packet.getFrom());
            if (!isConnected()) {
                MyLog.warn("drop a packet as the channel is not connected, chid=" + chid);
            } else if (loginInfo == null || loginInfo.status != ClientStatus.binded) {
                MyLog.warn("drop a packet as the channel is not opened, chid=" + chid);
            } else if (!TextUtils.equals(session, loginInfo.session)) {
                MyLog.warn("invalid session. " + session);
            } else if ((packet instanceof Message) && entrypt) {
                return encrypt((Message) packet, loginInfo.security);
            } else {
                return packet;
            }
        }
        return null;
    }

    private Message encrypt(Message packet, String security) {
        byte[] key = RC4Cryption.generateKeyForRC4(security, packet.getPacketID());
        Message targetMessage = new Message();
        targetMessage.setFrom(packet.getFrom());
        targetMessage.setTo(packet.getTo());
        targetMessage.setPacketID(packet.getPacketID());
        targetMessage.setChannelId(packet.getChannelId());
        targetMessage.setEncrypted(XMPPCONNECTION_DEBUG_ENABLED);
        String encryptedContent = RC4Cryption.encrypt(key, StringUtils.stripInvalidXMLChars(packet.toXML()));
        CommonPacketExtension contentExt = new CommonPacketExtension("s", null, (String[]) null, (String[]) null);
        contentExt.setText(encryptedContent);
        targetMessage.addExtension(contentExt);
        return targetMessage;
    }

    private ClientLoginInfo updatePushClient(String chid, Intent intent) {
        ClientLoginInfo cLoginInfo = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(chid, intent.getStringExtra(PushConstants.EXTRA_USER_ID));
        if (cLoginInfo == null) {
            cLoginInfo = new ClientLoginInfo(this);
        }
        cLoginInfo.chid = intent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
        cLoginInfo.userId = intent.getStringExtra(PushConstants.EXTRA_USER_ID);
        cLoginInfo.token = intent.getStringExtra(PushConstants.EXTRA_TOKEN);
        cLoginInfo.pkgName = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
        cLoginInfo.clientExtra = intent.getStringExtra(PushConstants.EXTRA_CLIENT_ATTR);
        cLoginInfo.cloudExtra = intent.getStringExtra(PushConstants.EXTRA_CLOUD_ATTR);
        cLoginInfo.kick = intent.getBooleanExtra(PushConstants.EXTRA_KICK, false);
        cLoginInfo.security = intent.getStringExtra(PushConstants.EXTRA_SECURITY);
        cLoginInfo.authMethod = intent.getStringExtra(PushConstants.EXTRA_AUTH_METHOD);
        cLoginInfo.mClientEventDispatcher = this.mClientEventDispatcher;
        cLoginInfo.context = getApplicationContext();
        PushClientsManager.getInstance().addActiveClient(cLoginInfo);
        return cLoginInfo;
    }

    public void closeChannel(String chid, String userId, int notifyType, String reasonMsg, String kickType) {
        ClientLoginInfo loginInfo = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(chid, userId);
        if (loginInfo != null) {
            executeJob(new UnbindJob(loginInfo, notifyType, kickType, reasonMsg));
        }
        PushClientsManager.getInstance().deactivateClient(chid, userId);
    }

    private void closeAllChannelByChid(String chid, int notifyType) {
        Collection<ClientLoginInfo> loginInfos = PushClientsManager.getInstance().getAllClientLoginInfoByChid(chid);
        if (loginInfos != null) {
            for (ClientLoginInfo loginInfo : loginInfos) {
                if (loginInfo != null) {
                    executeJob(new UnbindJob(loginInfo, notifyType, null, null));
                }
            }
        }
        PushClientsManager.getInstance().deactivateAllClientByChid(chid);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        onStart(intent, startId);
        return START_STICKY;
    }

    public IBinder onBind(Intent arg0) {
        return new MessageChannel();
    }

    public void executeJob(Job job) {
        executeJobDelayed(job, 0);
    }

    public void executeJobDelayed(Job job, long delay) {
        this.mJobController.executeJobDelayed(job, delay);
    }

    public void onDestroy() {
        this.mJobController.removeAllJobs();
        executeJob(new Job(2) {
            public void process() {
                if (XMPushService.this.mCurrentConnection != null) {
                    XMPushService.this.mCurrentConnection.disconnect(new Presence(Type.unavailable), 15, null);
                    XMPushService.this.mCurrentConnection = null;
                }
            }

            public String getDesc() {
                return "disconnect for service destroy.";
            }
        });
        executeJob(new KillJob());
        PushClientsManager.getInstance().removeAllClientChangeListeners();
        PushClientsManager.getInstance().resetAllClients(this, 15);
        PushClientsManager.getInstance().removeActiveClients();
        this.mXMPPConnection.removeConnectionListener(this);
        this.mBOSHConnection.removeConnectionListener(this);
        this.mTimer.stop();
        super.onDestroy();
        MyLog.warn("Service destroyed");
    }

    public void sendPacket(Packet p) throws XMPPException {
        if (this.mCurrentConnection != null) {
            this.mCurrentConnection.sendPacket(p);
            return;
        }
        throw new XMPPException("try send msg while connection is null.");
    }

    public void sendMIPushPacket(String packageName, byte[] payload) throws XMPPException {
        if (this.mCurrentConnection != null) {
            Message message = constructMIPushMessage(payload);
            if (message != null) {
                this.mCurrentConnection.sendPacket(message);
                return;
            } else {
                MIPushClientManager.notifyError(this, packageName, payload, PushConstants.MIPUSH_ERROR_INVALID_PAYLOAD, "not a valid message");
                return;
            }
        }
        throw new XMPPException("try send msg while connection is null.");
    }

    public void sendMIPushPacket(XmPushActionContainer container) throws XMPPException {
        if (this.mCurrentConnection != null) {
            Message message = constructMIPushMessage(container);
            if (message != null) {
                this.mCurrentConnection.sendPacket(message);
                return;
            }
            return;
        }
        throw new XMPPException("try send msg while connection is null.");
    }

    public void batchSendPacket(Packet[] packets) throws XMPPException {
        if (this.mCurrentConnection != null) {
            this.mCurrentConnection.batchSendPacket(packets);
            return;
        }
        throw new XMPPException("try send msg while connection is null.");
    }

    public boolean isConnectAllowed() {
        return shouldReconnect();
    }

    private String generatePingString(String serviceName) {
        return "<iq to='" + serviceName + "' id='0' chid='0' type='get'><ping xmlns='urn:xmpp:ping'>%1$s</ping></iq>";
    }

    public void scheduleConnect(boolean rightNow) {
        this.mReconnManager.tryReconnect(rightNow);
    }

    public void scheduleRebindChannel(ClientLoginInfo info) {
        if (info != null) {
            long interval = info.getNextRetryInterval();
            MyLog.warn("schedule rebind job in " + (interval / 1000));
            executeJobDelayed(new BindJob(info), interval);
        }
    }

    public void disconnect(int reason, Exception e) {
        MyLog.warn("disconnect " + hashCode() + ", " + (this.mCurrentConnection == null ? null : Integer.valueOf(this.mCurrentConnection.hashCode())));
        if (this.mCurrentConnection != null) {
            this.mCurrentConnection.disconnect(new Presence(Type.unavailable), reason, e);
            this.mCurrentConnection = null;
        }
        removeJobs(7);
        removeJobs(4);
        PushClientsManager.getInstance().resetAllClients(this, reason);
    }

    public boolean shouldReconnect() {
        return (!Network.hasNetwork(this) || PushClientsManager.getInstance().getActiveClientCount() <= 0) ? false : XMPPCONNECTION_DEBUG_ENABLED;
    }

    private void updateAlarmTimer() {
        if (!shouldReconnect()) {
            this.mTimer.stop();
        } else if (!this.mTimer.isAlive()) {
            this.mTimer.registerPing(XMPPCONNECTION_DEBUG_ENABLED);
        }
    }

    private void connect() {
        if (this.mCurrentConnection != null && this.mCurrentConnection.isConnecting()) {
            MyLog.e("try to connect while connecting.");
        } else if (this.mCurrentConnection == null || !this.mCurrentConnection.isConnected()) {
            this.connConfig.setConnectionPoint(Network.getActiveConnPoint(this));
            if (this.mXMPPConnection.isAlwaysFailed()) {
                connectByBosh();
                if (this.mCurrentConnection == null || this.mCurrentConnection.getConnectionStatus() == 2) {
                    connectByXMPP();
                }
            } else {
                connectByXMPP();
                if (this.mCurrentConnection == null || this.mCurrentConnection.getConnectionStatus() == 2) {
                    connectByBosh();
                }
            }
            if (this.mCurrentConnection == null) {
                NetworkCheckup.doCheckup();
                PushClientsManager.getInstance().notifyConnectionFailed(this);
            }
        } else {
            MyLog.e("try to connect while is connected.");
        }
    }

    private void connectByXMPP() {
        try {
            this.mXMPPConnection.connect();
            this.mXMPPConnection.addPacketListener(this.mPacketListener, new PacketFilter() {
                public boolean accept(Packet packet) {
                    return XMPushService.XMPPCONNECTION_DEBUG_ENABLED;
                }
            });
            this.mCurrentConnection = this.mXMPPConnection;
        } catch (XMPPException e) {
            MyLog.e("fail to create xmpp connection", e);
            this.mXMPPConnection.disconnect(new Presence(Type.unavailable), 3, e);
        }
    }

    private void connectByBosh() {
        try {
            Fallback fb = HostManager.getInstance().getFallbacksByHost(PushServiceConstants.XMPP_BOSH_HOST);
            if (fb != null) {
                this.boshConfig.setFallback(fb);
            }
            this.mBOSHConnection.connect();
            this.mBOSHConnection.addPacketListener(this.mPacketListener, new PacketFilter() {
                public boolean accept(Packet packet) {
                    return XMPushService.XMPPCONNECTION_DEBUG_ENABLED;
                }
            });
            this.mCurrentConnection = this.mBOSHConnection;
        } catch (XMPPException e) {
            MyLog.e("fail to create BOSH connection", e);
            this.mBOSHConnection.disconnect(new Presence(Type.unavailable), 3, e);
        }
    }

    public ClientEventDispatcher createClientEventDispatcher() {
        return new ClientEventDispatcher();
    }

    public XMPPConnection createXMPPConnection(ConnectionConfiguration config) {
        return new XMPPConnection(this, config);
    }

    public ClientEventDispatcher getClientEventDispatcher() {
        return this.mClientEventDispatcher;
    }

    public boolean isConnected() {
        return (this.mCurrentConnection == null || !this.mCurrentConnection.isConnected()) ? false : XMPPCONNECTION_DEBUG_ENABLED;
    }

    public boolean isConnecting() {
        return (this.mCurrentConnection == null || !this.mCurrentConnection.isConnecting()) ? false : XMPPCONNECTION_DEBUG_ENABLED;
    }

    public Connection getCurrentConnection() {
        return this.mCurrentConnection;
    }

    public void removeJobs(int jobType) {
        this.mJobController.removeJobs(jobType);
    }

    public boolean hasJob(int jobType) {
        return this.mJobController.hasJob(jobType);
    }

    public void removeJobs(Job job) {
        this.mJobController.removeJobs(job.type, job);
    }

    public boolean hasJob(Job job) {
        return this.mJobController.hasJob(job.type, job);
    }

    public void connectionStarted() {
        MyLog.v("begin to connect...");
    }

    public void setConnectingTimeout() {
        executeJobDelayed(new Job(10) {
            public void process() {
                XMPushService.this.disconnect(18, null);
            }

            public String getDesc() {
                return "disconnect because of connecting timeout";
            }
        }, 120000);
    }

    public void connectionClosed(int reason, Exception e) {
        scheduleConnect(false);
    }

    public void reconnectionSuccessful() {
        this.mReconnManager.onConnectSucceeded();
        Iterator i$ = PushClientsManager.getInstance().getAllClients().iterator();
        while (i$.hasNext()) {
            executeJob(new BindJob((ClientLoginInfo) i$.next()));
        }
    }

    public void reconnectionFailed(Exception e) {
        scheduleConnect(false);
    }
}
