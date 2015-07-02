package com.xiaomi.network;

import android.content.Context;
import java.io.IOException;
import java.util.List;
import org.apache.http.NameValuePair;

public abstract class HttpProcessor {
    public static final int HTTP_GET = 1;
    public static final int HTTP_POST = 2;
    private int httpRequest;

    public abstract boolean prepare(Context context, String str, List<NameValuePair> list) throws IOException;

    public abstract String visit(Context context, String str, List<NameValuePair> list) throws IOException;

    public HttpProcessor(int httpRequest) {
        this.httpRequest = httpRequest;
    }

    public int getRequestType() {
        return this.httpRequest;
    }
}
