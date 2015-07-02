package com.xiaomi.kenai.jbosh;

interface HTTPResponse {
    void abort();

    AbstractBody getBody() throws InterruptedException, BOSHException;

    int getHTTPStatus() throws InterruptedException, BOSHException;

    long getRid();
}
