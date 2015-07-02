package com.xiaomi.d;

import com.xiaomi.d.c.c;
import com.xiaomi.d.c.d;
import java.io.PrintStream;
import java.io.PrintWriter;

public class v extends Exception {
    private d a;
    private c b;
    private Throwable c;

    public v() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public v(d dVar) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = dVar;
    }

    public v(String str) {
        super(str);
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public v(String str, c cVar) {
        super(str);
        this.a = null;
        this.b = null;
        this.c = null;
        this.b = cVar;
    }

    public v(String str, Throwable th) {
        super(str);
        this.a = null;
        this.b = null;
        this.c = null;
        this.c = th;
    }

    public v(Throwable th) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.c = th;
    }

    public String getMessage() {
        String message = super.getMessage();
        return (message != null || this.b == null) ? (message != null || this.a == null) ? message : this.a.toString() : this.b.toString();
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.c != null) {
            printStream.println("Nested Exception: ");
            this.c.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.c != null) {
            printWriter.println("Nested Exception: ");
            this.c.printStackTrace(printWriter);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            stringBuilder.append(message).append(": ");
        }
        if (this.b != null) {
            stringBuilder.append(this.b);
        }
        if (this.a != null) {
            stringBuilder.append(this.a);
        }
        if (this.c != null) {
            stringBuilder.append("\n  -- caused by: ").append(this.c);
        }
        return stringBuilder.toString();
    }
}
