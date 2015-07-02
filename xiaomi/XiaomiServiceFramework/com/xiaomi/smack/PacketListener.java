package com.xiaomi.smack;

import com.xiaomi.smack.packet.Packet;

public interface PacketListener {
    void processPacket(Packet packet);
}
