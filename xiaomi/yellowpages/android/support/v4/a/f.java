package android.support.v4.a;

import android.util.Log;
import java.io.Writer;

/* compiled from: LogWriter */
public class f extends Writer {
    private StringBuilder mBuilder;
    private final String mTag;

    public f(String str) {
        this.mBuilder = new StringBuilder(128);
        this.mTag = str;
    }

    public void close() {
        eL();
    }

    public void flush() {
        eL();
    }

    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                eL();
            } else {
                this.mBuilder.append(c);
            }
        }
    }

    private void eL() {
        if (this.mBuilder.length() > 0) {
            Log.d(this.mTag, this.mBuilder.toString());
            this.mBuilder.delete(0, this.mBuilder.length());
        }
    }
}
