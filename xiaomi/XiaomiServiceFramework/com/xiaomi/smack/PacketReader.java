package com.xiaomi.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class PacketReader {
    private XMPPConnection connection;
    private boolean done;
    private XmlPullParser parser;
    private Thread readerThread;

    protected PacketReader(XMPPConnection connection) {
        this.connection = connection;
        init();
    }

    protected void init() {
        this.done = false;
        this.readerThread = new Thread("Smack Packet Reader (" + this.connection.connectionCounterValue + ")") {
            public void run() {
                PacketReader.this.parsePackets();
            }
        };
    }

    public void startup() throws XMPPException {
        this.readerThread.start();
    }

    public void shutdown() {
        this.done = true;
    }

    void cleanup() {
        this.connection.recvListeners.clear();
    }

    void notifyConnectionError(int error, Exception e) {
        this.done = true;
        this.connection.notifyConnectionError(error, e);
    }

    private void setParser() throws XmlPullParserException {
        this.parser = XmlPullParserFactory.newInstance().newPullParser();
        this.parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        this.parser.setInput(this.connection.reader);
    }

    private void parsePackets() {
        try {
            setParser();
            int eventType = this.parser.getEventType();
            String lastEleName = "";
            do {
                this.connection.setReadAlive();
                if (eventType == 2) {
                    lastEleName = this.parser.getName();
                    if (this.parser.getName().equals("message")) {
                        processPacket(PacketParserUtils.parseMessage(this.parser));
                    } else if (this.parser.getName().equals("iq")) {
                        processPacket(PacketParserUtils.parseIQ(this.parser, this.connection));
                    } else if (this.parser.getName().equals("presence")) {
                        processPacket(PacketParserUtils.parsePresence(this.parser));
                    } else if (this.parser.getName().equals("stream")) {
                        String challengeData = "";
                        for (int i = 0; i < this.parser.getAttributeCount(); i++) {
                            if (this.parser.getAttributeName(i).equals(PushServiceConstants.EXTRA_FROM)) {
                                this.connection.config.setServiceName(this.parser.getAttributeValue(i));
                            } else if (this.parser.getAttributeName(i).equals("challenge")) {
                                challengeData = this.parser.getAttributeValue(i);
                            }
                        }
                        this.connection.setChallenge(challengeData);
                    } else if (this.parser.getName().equals(Message.MSG_TYPE_ERROR)) {
                        throw new XMPPException(PacketParserUtils.parseStreamError(this.parser));
                    } else if (this.parser.getName().equals("warning")) {
                        this.parser.next();
                        if (this.parser.getName().equals("multi-login")) {
                            notifyConnectionError(6, null);
                        }
                    } else if (this.parser.getName().equals("bind")) {
                        processPacket(PacketParserUtils.parseBindResult(this.parser));
                    }
                } else if (eventType == 3 && this.parser.getName().equals("stream")) {
                    notifyConnectionError(13, null);
                }
                eventType = this.parser.next();
                if (this.done) {
                    break;
                }
            } while (eventType != 1);
            if (eventType == 1) {
                throw new Exception("SMACK: server close the connection or timeout happened, last element name=" + lastEleName + " host=" + this.connection.getHost());
            }
        } catch (Throwable e) {
            MyLog.e(e);
            if (this.done) {
                MyLog.v("reader is shutdown, ignore the exception.");
            } else {
                notifyConnectionError(9, e);
            }
        }
    }

    private void processPacket(Packet packet) {
        if (packet != null) {
            for (ListenerWrapper listenerWrapper : this.connection.recvListeners.values()) {
                listenerWrapper.notifyListener(packet);
            }
        }
    }
}
