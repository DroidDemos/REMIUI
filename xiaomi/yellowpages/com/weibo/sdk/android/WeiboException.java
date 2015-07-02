package com.weibo.sdk.android;

public class WeiboException extends Exception {
    private static final long serialVersionUID = 475022994858770424L;
    private int statusCode;

    public WeiboException(String str) {
        super(str);
        this.statusCode = -1;
    }

    public WeiboException(Exception exception) {
        super(exception);
        this.statusCode = -1;
    }

    public WeiboException(String str, int i) {
        super(str);
        this.statusCode = -1;
        this.statusCode = i;
    }

    public WeiboException() {
        this.statusCode = -1;
    }

    public WeiboException(String str, Throwable th) {
        super(str, th);
        this.statusCode = -1;
    }

    public WeiboException(Throwable th) {
        super(th);
        this.statusCode = -1;
    }
}
