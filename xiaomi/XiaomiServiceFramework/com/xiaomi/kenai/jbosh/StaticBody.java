package com.xiaomi.kenai.jbosh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

final class StaticBody extends AbstractBody {
    private static final int BUFFER_SIZE = 1024;
    private static final BodyParser PARSER;
    private final Map<BodyQName, String> attrs;
    private final String raw;

    static {
        PARSER = new BodyParserXmlPull();
    }

    private StaticBody(Map<BodyQName, String> attrMap, String rawXML) {
        this.attrs = attrMap;
        this.raw = rawXML;
    }

    public static StaticBody fromStream(InputStream inStream) throws BOSHException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int read;
            do {
                read = inStream.read(buffer);
                if (read > 0) {
                    byteOut.write(buffer, 0, read);
                    continue;
                }
            } while (read >= 0);
            return fromString(byteOut.toString());
        } catch (IOException iox) {
            throw new BOSHException("Could not read body data", iox);
        }
    }

    public static StaticBody fromString(String rawXML) throws BOSHException {
        return new StaticBody(PARSER.parse(rawXML).getAttributes(), rawXML);
    }

    public Map<BodyQName, String> getAttributes() {
        return Collections.unmodifiableMap(this.attrs);
    }

    public String toXML() {
        return this.raw;
    }
}
