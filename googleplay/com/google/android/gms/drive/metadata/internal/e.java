package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.lp;
import com.google.android.gms.internal.lr;
import com.google.android.gms.internal.lt;
import java.util.HashMap;
import java.util.Map;

public final class e {
    private static Map<String, MetadataField<?>> abp;

    static {
        abp = new HashMap();
        b(lp.abr);
        b(lp.abR);
        b(lp.abI);
        b(lp.abP);
        b(lp.abS);
        b(lp.abC);
        b(lp.abD);
        b(lp.abA);
        b(lp.abF);
        b(lp.abN);
        b(lp.abs);
        b(lp.abK);
        b(lp.abu);
        b(lp.abB);
        b(lp.abv);
        b(lp.abw);
        b(lp.abx);
        b(lp.abH);
        b(lp.abE);
        b(lp.abJ);
        b(lp.abL);
        b(lp.abM);
        b(lp.abO);
        b(lp.abT);
        b(lp.abU);
        b(lp.abz);
        b(lp.aby);
        b(lp.abQ);
        b(lp.abG);
        b(lp.abt);
        b(lp.abV);
        b(lp.abW);
        b(lp.abX);
        b(lr.abY);
        b(lr.aca);
        b(lr.acb);
        b(lr.acc);
        b(lr.abZ);
        b(lt.ace);
        b(lt.acf);
    }

    private static void b(MetadataField<?> metadataField) {
        if (abp.containsKey(metadataField.getName())) {
            throw new IllegalArgumentException("Duplicate field name registered: " + metadataField.getName());
        }
        abp.put(metadataField.getName(), metadataField);
    }

    public static MetadataField<?> bw(String str) {
        return (MetadataField) abp.get(str);
    }
}
