package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.f.a.c.b;
import java.net.InetAddress;
import java.net.UnknownHostException;

final class S implements Runnable {
    S() {
    }

    public void run() {
        String b = R.c();
        if (TextUtils.isEmpty(b)) {
            b.a("Network Checkup: cannot get gateway");
        } else {
            b.a("Network Checkup: get gateway:" + b);
            R.b(b);
        }
        try {
            InetAddress byName = InetAddress.getByName("www.baidu.com");
            b.a("Network Checkup: get address for www.baidu.com:" + byName.getAddress());
            R.b(byName.getHostAddress());
        } catch (UnknownHostException e) {
            b.a("Network Checkup: cannot resolve the host www.baidu.com");
        } catch (Exception e2) {
            b.a("the checkup failure." + e2);
        }
    }
}
