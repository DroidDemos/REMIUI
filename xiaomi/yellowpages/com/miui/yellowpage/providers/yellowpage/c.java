package com.miui.yellowpage.providers.yellowpage;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Permission;
import java.util.HashMap;
import java.util.Map;

/* compiled from: UriMatcherController */
public class c extends UriMatcher {
    private Map wd;

    public c(int i) {
        super(i);
        this.wd = new HashMap();
    }

    public int a(Context context, Uri uri) {
        if (!p.hz()) {
            return -1;
        }
        int match = match(uri);
        if (match == -1 || !((Boolean) this.wd.get(Integer.valueOf(match))).booleanValue() || Permission.networkingAllowed(context)) {
            return match;
        }
        Log.d("UriMatcherController", "network not allowed, NO_MATCH");
        return -1;
    }

    public void a(String str, String str2, int i, boolean z) {
        this.wd.put(Integer.valueOf(i), Boolean.valueOf(z));
        addURI(str, str2, i);
    }
}
