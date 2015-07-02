package com.xiaomi.smack;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.StringUtils;
import com.xiaomi.smack.util.TrafficUtils;
import java.io.IOException;
import java.io.Writer;

class PacketWriter {
    private XMPPConnection connection;
    private Writer writer;

    protected PacketWriter(XMPPConnection connection) {
        this.connection = connection;
        this.writer = connection.writer;
    }

    public void sendPacket(Packet packet) throws XMPPException {
        writePackets(packet);
        this.connection.firePacketSendingListeners(packet);
    }

    void cleanup() {
        this.connection.sendListeners.clear();
    }

    public void shutdown() throws IOException {
        synchronized (this.writer) {
            this.writer.write("</stream:stream>");
            this.writer.flush();
        }
    }

    private void writePackets(Packet packet) throws XMPPException {
        synchronized (this.writer) {
            try {
                String packetStr = packet.toXML();
                this.writer.write(packetStr + "\r\n");
                this.writer.flush();
                String packageName = packet.getPackageName();
                if (!TextUtils.isEmpty(packageName)) {
                    TrafficUtils.distributionTraffic(this.connection.mPushService, packageName, (long) TrafficUtils.getTrafficFlow(packetStr), false, System.currentTimeMillis());
                }
            } catch (Throwable e) {
                throw new XMPPException(e);
            }
        }
    }

    void openStream() throws IOException {
        StringBuilder stream = new StringBuilder();
        stream.append("<stream:stream");
        stream.append(" xmlns=\"xm\"");
        stream.append(" xmlns:stream=\"xm\"");
        stream.append(" to=\"").append(this.connection.getServiceName()).append("\"");
        stream.append(" version=\"105\"");
        stream.append(" model=\"").append(StringUtils.escapeForXML(Build.MODEL)).append("\"");
        stream.append(" os=\"").append(StringUtils.escapeForXML(VERSION.INCREMENTAL)).append("\"");
        stream.append(" connpt=\"").append(StringUtils.escapeForXML(this.connection.getConnectionPoint())).append("\"");
        stream.append(" host=\"").append(this.connection.getHost()).append("\"");
        stream.append(">");
        this.writer.write(stream.toString());
        this.writer.flush();
    }

    public void sendPingString() throws XMPPException {
        synchronized (this.writer) {
            try {
                this.writer.write(this.connection.getPingString() + "\r\n");
                this.writer.flush();
                this.connection.updateLastSent();
            } catch (Throwable e) {
                throw new XMPPException(e);
            }
        }
    }
}
