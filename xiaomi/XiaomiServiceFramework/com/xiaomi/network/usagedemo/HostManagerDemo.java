package com.xiaomi.network.usagedemo;

import android.content.Context;
import com.xiaomi.network.AccessHistory;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager;
import com.xiaomi.network.HttpHelper;
import com.xiaomi.network.HttpProcessor;
import com.xiaomi.network.Network;
import com.xiaomi.push.service.PushServiceConstants;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;

public class HostManagerDemo {
    public void requestURL() throws MalformedURLException {
        HostManager.init(null, new HostFilter() {
            public boolean accept(String host) {
                return host.endsWith("xiaomi.net") || host.endsWith(PushServiceConstants.XMPP_SERVICE_NAME) || host.endsWith("miliao.com");
            }
        }, null, PushServiceConstants.EXTENSION_ATTRIBUTE_SUB_TYPE_GRAFFITI);
        HttpHelper.httpRequest(null, "http://www.sina.com.cn/index.html", null, new HttpProcessor(1) {
            public String visit(Context context, String url, List<NameValuePair> list) throws IOException {
                return Network.downloadXml(context, new URL(url));
            }

            public boolean prepare(Context context, String url, List<NameValuePair> list) throws IOException {
                return true;
            }
        });
        Fallback fbs = HostManager.getInstance().getFallbacksByHost("www.xiaomi.com");
        Iterator i$ = fbs.getHosts().iterator();
        while (i$.hasNext()) {
            String currentHost = (String) i$.next();
            try {
                fbs.succeedHost(currentHost, 0, 0);
                break;
            } catch (Exception e) {
                fbs.failedHost(currentHost, 0, 0, e);
            }
        }
        fbs = HostManager.getInstance().getFallbacksByHost("www.xiaomi.com");
        i$ = fbs.getHosts().iterator();
        while (i$.hasNext()) {
            currentHost = (String) i$.next();
            try {
                AccessHistory ah = visit(currentHost);
                fbs.accessHost(currentHost, ah);
                if (isSuccess(ah)) {
                    return;
                }
            } catch (Exception e2) {
                fbs.failedHost(currentHost, 0, 0, e2);
            }
        }
    }

    private boolean isSuccess(AccessHistory ah) {
        return false;
    }

    private static AccessHistory visit(String host) {
        return new AccessHistory(-1, 0, 0, null);
    }
}
