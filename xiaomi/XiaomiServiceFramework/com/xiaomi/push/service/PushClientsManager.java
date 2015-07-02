package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.DateTimeHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PushClientsManager {
    private static PushClientsManager sInstance;
    private List<ClientChangeListener> clientChangeListeners;
    private ConcurrentHashMap<String, HashMap<String, ClientLoginInfo>> mActiveClients;

    public interface ClientChangeListener {
        void onChange();
    }

    public static class ClientLoginInfo {
        public static final int TYPE_CHANNEL_CLOSE = 2;
        public static final int TYPE_CHANNEL_NO_NOTIFY = 0;
        public static final int TYPE_CHANNEL_OPEN_RESULT = 1;
        public static final int TYPE_CHANNEL_SERVER_KICK = 3;
        public String authMethod;
        public String chid;
        public String clientExtra;
        public String cloudExtra;
        public Context context;
        private int currentRetrys;
        public boolean kick;
        public ClientEventDispatcher mClientEventDispatcher;
        private XMPushService mPushService;
        public String pkgName;
        public String security;
        public String session;
        ClientStatus status;
        private List<ClientStatusListener> statusChangeListeners;
        private BindTimeoutJob timeOutJob;
        public String token;
        public String userId;

        public interface ClientStatusListener {
            void onChange(ClientStatus clientStatus, ClientStatus clientStatus2, int i);
        }

        public ClientLoginInfo(XMPushService pushService) {
            this.status = ClientStatus.unbind;
            this.currentRetrys = TYPE_CHANNEL_NO_NOTIFY;
            this.statusChangeListeners = new ArrayList();
            this.timeOutJob = new BindTimeoutJob(this);
            this.mPushService = pushService;
            addClientStatusListener(new ClientStatusListener() {
                public void onChange(ClientStatus oldStatus, ClientStatus newStatus, int reason) {
                    if (newStatus == ClientStatus.binding) {
                        ClientLoginInfo.this.mPushService.executeJobDelayed(ClientLoginInfo.this.timeOutJob, DateTimeHelper.sMinuteInMilliseconds);
                    } else {
                        ClientLoginInfo.this.mPushService.removeJobs(ClientLoginInfo.this.timeOutJob);
                    }
                }
            });
        }

        public void setStatus(ClientStatus status, int notifyType, int reason, String reasonMessage, String errorType) {
            boolean succeeded = true;
            for (ClientStatusListener listener : this.statusChangeListeners) {
                listener.onChange(this.status, status, reason);
            }
            if (this.status != status) {
                MyLog.warn(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", new Object[]{this.status, status, getDesc(notifyType), PushConstants.getErrorDesc(reason), reasonMessage, errorType, this.chid}));
                this.status = status;
            }
            if (this.mClientEventDispatcher == null) {
                MyLog.e("status changed while the client dispatcher is missing");
            } else if (notifyType == TYPE_CHANNEL_CLOSE) {
                this.mClientEventDispatcher.notifyChannelClosed(this.context, this, reason);
            } else if (notifyType == TYPE_CHANNEL_SERVER_KICK) {
                this.mClientEventDispatcher.notifyKickedByServer(this.context, this, errorType, reasonMessage);
            } else if (notifyType == TYPE_CHANNEL_OPEN_RESULT) {
                if (status != ClientStatus.binded) {
                    succeeded = false;
                }
                if (!succeeded && "wait".equals(errorType)) {
                    this.currentRetrys += TYPE_CHANNEL_OPEN_RESULT;
                } else if (succeeded) {
                    this.currentRetrys = TYPE_CHANNEL_NO_NOTIFY;
                }
                this.mClientEventDispatcher.notifyChannelOpenResult(this.context, this, succeeded, reason, reasonMessage);
            }
        }

        public String getDesc(int notifyType) {
            switch (notifyType) {
                case TYPE_CHANNEL_OPEN_RESULT /*1*/:
                    return "OPEN";
                case TYPE_CHANNEL_CLOSE /*2*/:
                    return "CLOSE";
                case TYPE_CHANNEL_SERVER_KICK /*3*/:
                    return "KICK";
                default:
                    return "unknown";
            }
        }

        public void addClientStatusListener(ClientStatusListener statusListener) {
            this.statusChangeListeners.add(statusListener);
        }

        public long getNextRetryInterval() {
            return 1000 * (((long) ((Math.random() * 20.0d) - 10.0d)) + ((long) ((this.currentRetrys + TYPE_CHANNEL_OPEN_RESULT) * 15)));
        }
    }

    public enum ClientStatus {
        unbind,
        binding,
        binded
    }

    public static synchronized PushClientsManager getInstance() {
        PushClientsManager pushClientsManager;
        synchronized (PushClientsManager.class) {
            if (sInstance == null) {
                sInstance = new PushClientsManager();
            }
            pushClientsManager = sInstance;
        }
        return pushClientsManager;
    }

    private PushClientsManager() {
        this.mActiveClients = new ConcurrentHashMap();
        this.clientChangeListeners = new ArrayList();
    }

    public synchronized void addActiveClient(ClientLoginInfo info) {
        HashMap<String, ClientLoginInfo> infos = (HashMap) this.mActiveClients.get(info.chid);
        if (infos == null) {
            infos = new HashMap();
            this.mActiveClients.put(info.chid, infos);
        }
        infos.put(getSmtpLocalPart(info.userId), info);
        for (ClientChangeListener client : this.clientChangeListeners) {
            client.onChange();
        }
    }

    public synchronized void deactivateClient(String chid, String userId) {
        HashMap<String, ClientLoginInfo> infos = (HashMap) this.mActiveClients.get(chid);
        if (infos != null) {
            infos.remove(getSmtpLocalPart(userId));
            if (infos.isEmpty()) {
                this.mActiveClients.remove(chid);
            }
        }
        for (ClientChangeListener client : this.clientChangeListeners) {
            client.onChange();
        }
    }

    public synchronized void deactivateAllClientByChid(String chid) {
        HashMap<String, ClientLoginInfo> infos = (HashMap) this.mActiveClients.get(chid);
        if (infos != null) {
            infos.clear();
            this.mActiveClients.remove(chid);
        }
        for (ClientChangeListener client : this.clientChangeListeners) {
            client.onChange();
        }
    }

    public synchronized List<String> queryChannelIdByPackage(String pkgName) {
        List<String> result;
        result = new ArrayList();
        for (HashMap<String, ClientLoginInfo> infos : this.mActiveClients.values()) {
            for (ClientLoginInfo info : infos.values()) {
                if (pkgName.equals(info.pkgName)) {
                    result.add(info.chid);
                }
            }
        }
        return result;
    }

    public synchronized ArrayList<ClientLoginInfo> getAllClients() {
        ArrayList<ClientLoginInfo> clients;
        clients = new ArrayList();
        for (HashMap<String, ClientLoginInfo> infos : this.mActiveClients.values()) {
            clients.addAll(infos.values());
        }
        return clients;
    }

    public synchronized Collection<ClientLoginInfo> getAllClientLoginInfoByChid(String chid) {
        Collection<ClientLoginInfo> values;
        if (this.mActiveClients.containsKey(chid)) {
            values = ((HashMap) ((HashMap) this.mActiveClients.get(chid)).clone()).values();
        } else {
            values = new ArrayList();
        }
        return values;
    }

    public synchronized ClientLoginInfo getClientLoginInfoByChidAndUserId(String chid, String userId) {
        ClientLoginInfo clientLoginInfo;
        HashMap<String, ClientLoginInfo> infos = (HashMap) this.mActiveClients.get(chid);
        if (infos == null) {
            clientLoginInfo = null;
        } else {
            clientLoginInfo = (ClientLoginInfo) infos.get(getSmtpLocalPart(userId));
        }
        return clientLoginInfo;
    }

    public synchronized void resetAllClients(Context context, int reason) {
        for (HashMap<String, ClientLoginInfo> infos : this.mActiveClients.values()) {
            for (ClientLoginInfo info : infos.values()) {
                info.setStatus(ClientStatus.unbind, 2, reason, null, null);
            }
        }
    }

    public synchronized int getActiveClientCount() {
        return this.mActiveClients.size();
    }

    public synchronized void removeActiveClients() {
        this.mActiveClients.clear();
    }

    public synchronized void notifyConnectionFailed(Context context) {
        for (HashMap<String, ClientLoginInfo> infos : this.mActiveClients.values()) {
            for (ClientLoginInfo info : infos.values()) {
                info.setStatus(ClientStatus.unbind, 1, 3, null, null);
            }
        }
    }

    private String getSmtpLocalPart(String smtp) {
        if (TextUtils.isEmpty(smtp)) {
            return null;
        }
        int iAt = smtp.indexOf("@");
        if (iAt > 0) {
            return smtp.substring(0, iAt);
        }
        return smtp;
    }

    public synchronized void addClientChangeListener(ClientChangeListener clientChange) {
        this.clientChangeListeners.add(clientChange);
    }

    public synchronized void removeClientChangeListener(ClientChangeListener clientChange) {
        this.clientChangeListeners.remove(clientChange);
    }

    public synchronized void removeAllClientChangeListeners() {
        this.clientChangeListeners.clear();
    }
}
