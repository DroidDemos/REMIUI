package com.xiaomi.smack;

import java.util.List;
import org.apache.http.NameValuePair;

public interface HttpRequestProxy {
    String doHttpGet(String str, List<NameValuePair> list);

    String doHttpPost(String str, List<NameValuePair> list);

    boolean isWapNetwork();
}
