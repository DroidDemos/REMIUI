package com.xiaomi.smack.provider;

import com.xiaomi.smack.packet.IQ;
import org.xmlpull.v1.XmlPullParser;

public interface IQProvider {
    IQ parseIQ(XmlPullParser xmlPullParser) throws Exception;
}
