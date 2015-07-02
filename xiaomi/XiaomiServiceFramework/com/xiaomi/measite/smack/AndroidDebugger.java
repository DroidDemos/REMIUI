package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionListener;
import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.debugger.SmackDebugger;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.ObservableReader;
import com.xiaomi.smack.util.ObservableWriter;
import com.xiaomi.smack.util.ReaderListener;
import com.xiaomi.smack.util.WriterListener;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AndroidDebugger implements SmackDebugger {
    public static boolean printInterpreted;
    private ConnectionListener connListener;
    private Connection connection;
    private SimpleDateFormat dateFormatter;
    private PacketListener listener;
    private Reader reader;
    private ReaderListener readerListener;
    private Writer writer;
    private WriterListener writerListener;

    static {
        printInterpreted = false;
    }

    public AndroidDebugger(Connection connection, Writer writer, Reader reader) {
        this.dateFormatter = new SimpleDateFormat("hh:mm:ss aaa");
        this.connection = null;
        this.listener = null;
        this.connListener = null;
        this.connection = connection;
        this.writer = writer;
        this.reader = reader;
        createDebug();
    }

    private void createDebug() {
        ObservableReader debugReader = new ObservableReader(this.reader);
        this.readerListener = new ReaderListener() {
            public void read(String str) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " RCV  (" + AndroidDebugger.this.connection.hashCode() + "): " + str);
            }
        };
        debugReader.addReaderListener(this.readerListener);
        ObservableWriter debugWriter = new ObservableWriter(this.writer);
        this.writerListener = new WriterListener() {
            public void write(String str) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " SENT (" + AndroidDebugger.this.connection.hashCode() + "): " + str);
            }
        };
        debugWriter.addWriterListener(this.writerListener);
        this.reader = debugReader;
        this.writer = debugWriter;
        this.listener = new PacketListener() {
            public void processPacket(Packet packet) {
                if (AndroidDebugger.printInterpreted) {
                    MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " RCV PKT (" + AndroidDebugger.this.connection.hashCode() + "): " + packet.toXML());
                }
            }
        };
        this.connListener = new ConnectionListener() {
            public void connectionClosed(int reason, Exception ex) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection closed (" + AndroidDebugger.this.connection.hashCode() + ")");
            }

            public void reconnectionFailed(Exception e) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Reconnection failed due to an exception (" + AndroidDebugger.this.connection.hashCode() + ")");
                e.printStackTrace();
            }

            public void reconnectionSuccessful() {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection reconnected (" + AndroidDebugger.this.connection.hashCode() + ")");
            }

            public void connectionStarted() {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection started (" + AndroidDebugger.this.connection.hashCode() + ")");
            }
        };
    }

    public Reader newConnectionReader(Reader newReader) {
        ((ObservableReader) this.reader).removeReaderListener(this.readerListener);
        ObservableReader debugReader = new ObservableReader(newReader);
        debugReader.addReaderListener(this.readerListener);
        this.reader = debugReader;
        return this.reader;
    }

    public Writer newConnectionWriter(Writer newWriter) {
        ((ObservableWriter) this.writer).removeWriterListener(this.writerListener);
        ObservableWriter debugWriter = new ObservableWriter(newWriter);
        debugWriter.addWriterListener(this.writerListener);
        this.writer = debugWriter;
        return this.writer;
    }

    public Reader getReader() {
        return this.reader;
    }

    public Writer getWriter() {
        return this.writer;
    }

    public PacketListener getReaderListener() {
        return this.listener;
    }

    public PacketListener getWriterListener() {
        return null;
    }
}
