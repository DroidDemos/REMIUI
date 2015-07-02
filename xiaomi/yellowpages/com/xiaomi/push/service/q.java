package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.f.a.b.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class q {
    private static q vY;
    private ConcurrentHashMap vZ;
    private List wa;

    public enum c {
        unbind,
        binding,
        binded
    }

    private q() {
        this.vZ = new ConcurrentHashMap();
        this.wa = new ArrayList();
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("@");
        return indexOf > 0 ? str.substring(0, indexOf) : str;
    }

    public static q fp() {
        synchronized (q.class) {
            try {
            } finally {
                Object obj = q.class;
            }
        }
        if (vY == null) {
            vY = new q();
        }
        q qVar = vY;
        return qVar;
    }

    public void a(Context context) {
        synchronized (this) {
            for (HashMap values : this.vZ.values()) {
                for (m a : values.values()) {
                    a.a(c.unbind, 1, 3, null, null);
                }
            }
        }
    }

    public void a(l lVar) {
        synchronized (this) {
            this.wa.add(lVar);
        }
    }

    public void a(String str) {
        synchronized (this) {
            b.a(!"5".equals(str));
            HashMap hashMap = (HashMap) this.vZ.get(str);
            if (hashMap != null) {
                for (m mVar : hashMap.values()) {
                    hashMap.remove(d(mVar.b));
                }
                this.vZ.remove(str);
            }
            for (l a : this.wa) {
                a.a();
            }
        }
    }

    public void a(String str, String str2) {
        synchronized (this) {
            b.a(!"5".equals(str));
            HashMap hashMap = (HashMap) this.vZ.get(str);
            if (hashMap != null) {
                hashMap.remove(d(str2));
                if (hashMap.isEmpty()) {
                    this.vZ.remove(str);
                }
            }
            for (l a : this.wa) {
                a.a();
            }
        }
    }

    public ArrayList b() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList();
            for (HashMap values : this.vZ.values()) {
                arrayList.addAll(values.values());
            }
        }
        return arrayList;
    }

    public List bn(String str) {
        List arrayList;
        synchronized (this) {
            arrayList = new ArrayList();
            for (HashMap values : this.vZ.values()) {
                for (m mVar : values.values()) {
                    if (str.equals(mVar.a)) {
                        arrayList.add(mVar.h);
                    }
                }
            }
        }
        return arrayList;
    }

    public Collection bo(String str) {
        Collection arrayList;
        synchronized (this) {
            arrayList = !this.vZ.containsKey(str) ? new ArrayList() : ((HashMap) ((HashMap) this.vZ.get(str)).clone()).values();
        }
        return arrayList;
    }

    public int c() {
        int size;
        synchronized (this) {
            size = this.vZ.size();
        }
        return size;
    }

    public void c(m mVar) {
        synchronized (this) {
            HashMap hashMap = (HashMap) this.vZ.get(mVar.h);
            if (hashMap == null) {
                hashMap = new HashMap();
                this.vZ.put(mVar.h, hashMap);
            }
            hashMap.put(d(mVar.b), mVar);
            for (l a : this.wa) {
                a.a();
            }
        }
    }

    public void d() {
        synchronized (this) {
            this.vZ.clear();
        }
    }

    public void e() {
        synchronized (this) {
            this.wa.clear();
        }
    }

    public void e(Context context, int i) {
        synchronized (this) {
            for (HashMap values : this.vZ.values()) {
                for (m a : values.values()) {
                    a.a(c.unbind, 2, i, null, null);
                }
            }
        }
    }

    public m r(String str, String str2) {
        m mVar;
        synchronized (this) {
            HashMap hashMap = (HashMap) this.vZ.get(str);
            mVar = hashMap == null ? null : (m) hashMap.get(d(str2));
        }
        return mVar;
    }
}
