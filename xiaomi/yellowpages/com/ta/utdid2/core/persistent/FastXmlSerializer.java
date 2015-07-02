package com.ta.utdid2.core.persistent;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import org.xmlpull.v1.XmlSerializer;

class FastXmlSerializer implements XmlSerializer {
    private static final int BUFFER_LEN = 8192;
    private static final String[] ESCAPE_TABLE;
    private ByteBuffer mBytes;
    private CharsetEncoder mCharset;
    private boolean mInTag;
    private OutputStream mOutputStream;
    private int mPos;
    private final char[] mText;
    private Writer mWriter;

    FastXmlSerializer() {
        this.mText = new char[BUFFER_LEN];
        this.mBytes = ByteBuffer.allocate(BUFFER_LEN);
    }

    static {
        ESCAPE_TABLE = new String[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&quot;", null, null, null, "&amp;", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&lt;", null, "&gt;", null};
    }

    private void append(char c) {
        int i = this.mPos;
        if (i >= 8191) {
            flush();
            i = this.mPos;
        }
        this.mText[i] = c;
        this.mPos = i + 1;
    }

    private void append(String str, int i, int i2) {
        if (i2 > BUFFER_LEN) {
            int i3 = i + i2;
            while (i < i3) {
                int i4 = i + BUFFER_LEN;
                append(str, i, i4 < i3 ? BUFFER_LEN : i3 - i);
                i = i4;
            }
            return;
        }
        int i5 = this.mPos;
        if (i5 + i2 > BUFFER_LEN) {
            flush();
            i5 = this.mPos;
        }
        str.getChars(i, i + i2, this.mText, i5);
        this.mPos = i5 + i2;
    }

    private void append(char[] cArr, int i, int i2) {
        if (i2 > BUFFER_LEN) {
            int i3 = i + i2;
            while (i < i3) {
                int i4 = i + BUFFER_LEN;
                append(cArr, i, i4 < i3 ? BUFFER_LEN : i3 - i);
                i = i4;
            }
            return;
        }
        int i5 = this.mPos;
        if (i5 + i2 > BUFFER_LEN) {
            flush();
            i5 = this.mPos;
        }
        System.arraycopy(cArr, i, this.mText, i5, i2);
        this.mPos = i5 + i2;
    }

    private void append(String str) {
        append(str, 0, str.length());
    }

    private void escapeAndAppendString(String str) {
        int i = 0;
        int length = str.length();
        char length2 = (char) ESCAPE_TABLE.length;
        String[] strArr = ESCAPE_TABLE;
        int i2 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt < length2) {
                String str2 = strArr[charAt];
                if (str2 != null) {
                    if (i < i2) {
                        append(str, i, i2 - i);
                    }
                    i = i2 + 1;
                    append(str2);
                }
            }
            i2++;
        }
        if (i < i2) {
            append(str, i, i2 - i);
        }
    }

    private void escapeAndAppendString(char[] cArr, int i, int i2) {
        char length = (char) ESCAPE_TABLE.length;
        String[] strArr = ESCAPE_TABLE;
        int i3 = i + i2;
        int i4 = i;
        while (i < i3) {
            char c = cArr[i];
            if (c < length) {
                String str = strArr[c];
                if (str != null) {
                    if (i4 < i) {
                        append(cArr, i4, i - i4);
                    }
                    i4 = i + 1;
                    append(str);
                }
            }
            i++;
        }
        if (i4 < i) {
            append(cArr, i4, i - i4);
        }
    }

    public XmlSerializer attribute(String str, String str2, String str3) {
        append(' ');
        if (str != null) {
            append(str);
            append(':');
        }
        append(str2);
        append("=\"");
        escapeAndAppendString(str3);
        append('\"');
        return this;
    }

    public void cdsect(String str) {
        throw new UnsupportedOperationException();
    }

    public void comment(String str) {
        throw new UnsupportedOperationException();
    }

    public void docdecl(String str) {
        throw new UnsupportedOperationException();
    }

    public void endDocument() {
        flush();
    }

    public XmlSerializer endTag(String str, String str2) {
        if (this.mInTag) {
            append(" />\n");
        } else {
            append("</");
            if (str != null) {
                append(str);
                append(':');
            }
            append(str2);
            append(">\n");
        }
        this.mInTag = false;
        return this;
    }

    public void entityRef(String str) {
        throw new UnsupportedOperationException();
    }

    private void flushBytes() {
        int position = this.mBytes.position();
        if (position > 0) {
            this.mBytes.flip();
            this.mOutputStream.write(this.mBytes.array(), 0, position);
            this.mBytes.clear();
        }
    }

    public void flush() {
        if (this.mPos > 0) {
            if (this.mOutputStream != null) {
                CharBuffer wrap = CharBuffer.wrap(this.mText, 0, this.mPos);
                CoderResult encode = this.mCharset.encode(wrap, this.mBytes, true);
                while (!encode.isError()) {
                    if (encode.isOverflow()) {
                        flushBytes();
                        encode = this.mCharset.encode(wrap, this.mBytes, true);
                    } else {
                        flushBytes();
                        this.mOutputStream.flush();
                    }
                }
                throw new IOException(encode.toString());
            }
            this.mWriter.write(this.mText, 0, this.mPos);
            this.mWriter.flush();
            this.mPos = 0;
        }
    }

    public int getDepth() {
        throw new UnsupportedOperationException();
    }

    public boolean getFeature(String str) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getNamespace() {
        throw new UnsupportedOperationException();
    }

    public String getPrefix(String str, boolean z) {
        throw new UnsupportedOperationException();
    }

    public Object getProperty(String str) {
        throw new UnsupportedOperationException();
    }

    public void ignorableWhitespace(String str) {
        throw new UnsupportedOperationException();
    }

    public void processingInstruction(String str) {
        throw new UnsupportedOperationException();
    }

    public void setFeature(String str, boolean z) {
        if (!str.equals("http://xmlpull.org/v1/doc/features.html#indent-output")) {
            throw new UnsupportedOperationException();
        }
    }

    public void setOutput(OutputStream outputStream, String str) {
        if (outputStream == null) {
            throw new IllegalArgumentException();
        }
        try {
            this.mCharset = Charset.forName(str).newEncoder();
            this.mOutputStream = outputStream;
        } catch (Throwable e) {
            throw ((UnsupportedEncodingException) new UnsupportedEncodingException(str).initCause(e));
        } catch (Throwable e2) {
            throw ((UnsupportedEncodingException) new UnsupportedEncodingException(str).initCause(e2));
        }
    }

    public void setOutput(Writer writer) {
        this.mWriter = writer;
    }

    public void setPrefix(String str, String str2) {
        throw new UnsupportedOperationException();
    }

    public void setProperty(String str, Object obj) {
        throw new UnsupportedOperationException();
    }

    public void startDocument(String str, Boolean bool) {
        append("<?xml version='1.0' encoding='utf-8' standalone='" + (bool.booleanValue() ? "yes" : "no") + "' ?>\n");
    }

    public XmlSerializer startTag(String str, String str2) {
        if (this.mInTag) {
            append(">\n");
        }
        append('<');
        if (str != null) {
            append(str);
            append(':');
        }
        append(str2);
        this.mInTag = true;
        return this;
    }

    public XmlSerializer text(char[] cArr, int i, int i2) {
        if (this.mInTag) {
            append(">");
            this.mInTag = false;
        }
        escapeAndAppendString(cArr, i, i2);
        return this;
    }

    public XmlSerializer text(String str) {
        if (this.mInTag) {
            append(">");
            this.mInTag = false;
        }
        escapeAndAppendString(str);
        return this;
    }
}
