package com.alipay.sdk.data;

import java.util.ArrayList;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class InteractionData {
    public static final String a = "application/octet-stream;binary/octet-stream";
    private Header[] b;
    private String c;
    private String d;

    public InteractionData() {
        this.b = null;
        this.c = null;
        this.d = null;
    }

    public void a(Header[] headerArr) {
        this.b = headerArr;
    }

    public Header[] a() {
        return this.b;
    }

    public ArrayList b() {
        if (this.b == null || this.b.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Header header : this.b) {
            arrayList.add(new BasicHeader(header.getName(), header.getValue()));
        }
        return arrayList;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public void e() {
        this.d = null;
        this.c = null;
    }
}
