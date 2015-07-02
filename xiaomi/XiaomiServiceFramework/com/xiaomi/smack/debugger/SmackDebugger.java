package com.xiaomi.smack.debugger;

import com.xiaomi.smack.PacketListener;
import java.io.Reader;
import java.io.Writer;

public interface SmackDebugger {
    Reader getReader();

    PacketListener getReaderListener();

    Writer getWriter();

    PacketListener getWriterListener();

    Reader newConnectionReader(Reader reader);

    Writer newConnectionWriter(Writer writer);
}
