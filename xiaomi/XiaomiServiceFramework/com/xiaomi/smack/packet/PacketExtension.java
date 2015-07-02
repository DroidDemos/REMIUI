package com.xiaomi.smack.packet;

public interface PacketExtension {
    String getElementName();

    String getNamespace();

    String toXML();
}
