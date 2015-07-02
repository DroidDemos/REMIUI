package com.google.android.gms.internal;

import com.google.android.wallet.instrumentmanager.R;
import java.util.HashMap;
import java.util.Map;

@fd
public class cm implements ci {
    static final Map<String, Integer> qc;

    static {
        qc = new HashMap();
        qc.put("resize", Integer.valueOf(1));
        qc.put("playVideo", Integer.valueOf(2));
        qc.put("storePicture", Integer.valueOf(3));
        qc.put("createCalendarEvent", Integer.valueOf(4));
    }

    public void a(gz gzVar, Map<String, String> map) {
        switch (((Integer) qc.get((String) map.get("a"))).intValue()) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                new dn(gzVar, map).execute();
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                new do(gzVar, map).execute();
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                new dm(gzVar, map).execute();
                return;
            default:
                gw.i("Unknown MRAID command called.");
                return;
        }
    }
}
