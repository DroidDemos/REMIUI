package com.xiaomi.smack;

import android.text.TextUtils;
import com.xiaomi.kenai.jbosh.AbstractBody;
import com.xiaomi.kenai.jbosh.BOSHClientResponseListener;
import com.xiaomi.kenai.jbosh.BOSHMessageEvent;
import com.xiaomi.kenai.jbosh.BodyQName;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Type;
import com.xiaomi.smack.util.PacketParserUtils;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class BOSHPacketReader implements BOSHClientResponseListener {
    private BOSHConnection connection;

    public BOSHPacketReader(BOSHConnection connection) {
        this.connection = connection;
    }

    public boolean responseReceived(BOSHMessageEvent event) {
        AbstractBody body = event.getBody();
        boolean handled = false;
        if (body != null) {
            try {
                if (this.connection.isConnecting()) {
                    String challenge = body.getAttribute(BodyQName.create(BOSHConnection.XMPP_BOSH_NS, "challenge"));
                    if (!TextUtils.isEmpty(challenge)) {
                        handled = true;
                        this.connection.setChallenge(challenge);
                    }
                }
                if (this.connection.sessionID == null) {
                    handled = true;
                    this.connection.sessionID = body.getAttribute(BodyQName.create(BOSHConnection.XMPP_BOSH_NS, PushServiceConstants.EXTRA_SID));
                }
                if (this.connection.authID == null) {
                    handled = true;
                    this.connection.authID = body.getAttribute(BodyQName.create(BOSHConnection.XMPP_BOSH_NS, "authid"));
                }
                XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                parser.setInput(new StringReader(body.toXML()));
                int eventType = parser.getEventType();
                do {
                    eventType = parser.next();
                    this.connection.setReadAlive();
                    if (eventType == 2 && !parser.getName().equals(PushServiceConstants.EXTRA_BODY)) {
                        if (parser.getName().equals("message")) {
                            handled = true;
                            this.connection.processPacket(PacketParserUtils.parseMessage(parser));
                            continue;
                        } else if (parser.getName().equals("iq")) {
                            handled = true;
                            this.connection.processPacket(PacketParserUtils.parseIQ(parser, this.connection));
                            continue;
                        } else if (parser.getName().equals("presence")) {
                            handled = true;
                            this.connection.processPacket(PacketParserUtils.parsePresence(parser));
                            continue;
                        } else if (parser.getName().equals("challenge")) {
                            handled = true;
                            this.connection.setChallenge(parser.nextText());
                            continue;
                        } else if (parser.getName().equals(Message.MSG_TYPE_ERROR)) {
                            throw new XMPPException(PacketParserUtils.parseStreamError(parser));
                        } else if (parser.getName().equals("warning")) {
                            handled = true;
                            parser.next();
                            if (parser.getName().equals("multi-login")) {
                                this.connection.disconnect(new Presence(Type.unavailable), 6, null);
                                continue;
                            } else {
                                continue;
                            }
                        } else if (parser.getName().equals("bind")) {
                            handled = true;
                            this.connection.processPacket(PacketParserUtils.parseBindResult(parser));
                            continue;
                        } else {
                            continue;
                        }
                    }
                } while (eventType != 1);
            } catch (Exception e) {
                if (this.connection.isConnected()) {
                    this.connection.notifyConnectionError(e);
                }
            }
        }
        return handled;
    }
}
