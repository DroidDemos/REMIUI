package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@fd
public final class cn implements ci {
    private final cj qd;
    private final aa qe;

    public cn(cj cjVar, aa aaVar) {
        this.qd = cjVar;
        this.qe = aaVar;
    }

    private static boolean b(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int c(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return gn.dv();
            }
            if ("l".equalsIgnoreCase(str)) {
                return gn.du();
            }
        }
        return -1;
    }

    public void a(gz gzVar, Map<String, String> map) {
        String str = (String) map.get("a");
        if (str == null) {
            gw.w("Action missing from an open GMSG.");
        } else if (this.qe == null || this.qe.az()) {
            ha dD = gzVar.dD();
            if ("expand".equalsIgnoreCase(str)) {
                if (gzVar.dH()) {
                    gw.w("Cannot expand WebView that is already expanded.");
                } else {
                    dD.a(b(map), c(map));
                }
            } else if ("webapp".equalsIgnoreCase(str)) {
                str = (String) map.get("u");
                if (str != null) {
                    dD.a(b(map), c(map), str);
                } else {
                    dD.a(b(map), c(map), (String) map.get("html"), (String) map.get("baseurl"));
                }
            } else if ("in_app_purchase".equalsIgnoreCase(str)) {
                str = (String) map.get("product_id");
                String str2 = (String) map.get("report_urls");
                if (this.qd == null) {
                    return;
                }
                if (str2 == null || str2.isEmpty()) {
                    this.qd.a(str, new ArrayList());
                    return;
                }
                this.qd.a(str, new ArrayList(Arrays.asList(str2.split(" "))));
            } else {
                dD.a(new dt((String) map.get("i"), (String) map.get("u"), (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
            }
        } else {
            this.qe.f((String) map.get("u"));
        }
    }
}
