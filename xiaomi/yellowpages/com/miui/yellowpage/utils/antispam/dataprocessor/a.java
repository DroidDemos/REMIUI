package com.miui.yellowpage.utils.antispam.dataprocessor;

import java.math.BigInteger;

/* compiled from: Feed */
public class a {
    public static BigInteger a(e eVar) {
        return new BigInteger(String.valueOf((((int) eVar.bN()) << 9) | ((int) eVar.getCount()))).multiply(new BigInteger("10").pow(new Integer((int) eVar.bQ()).intValue()));
    }
}
