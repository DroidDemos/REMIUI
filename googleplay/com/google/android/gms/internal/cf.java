package com.google.android.gms.internal;

import java.util.Map;

@fd
public final class cf implements ci {
    private final cg pQ;

    public cf(cg cgVar) {
        this.pQ = cgVar;
    }

    public void a(gz gzVar, Map<String, String> map) {
        String str = (String) map.get("name");
        if (str == null) {
            gw.w("App event with no name parameter.");
        } else {
            this.pQ.onAppEvent(str, (String) map.get("info"));
        }
    }
}
