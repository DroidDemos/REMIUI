package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Sim;
import miui.telephony.PhoneNumberUtils;
import miui.util.CoderUtils;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;

/* compiled from: AntispamNumberHandler */
public class b {
    public static boolean a(SQLiteDatabase sQLiteDatabase, YellowPagePhone yellowPagePhone) {
        if (yellowPagePhone == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", yellowPagePhone.getNumber());
        contentValues.put("cid", Integer.valueOf(yellowPagePhone.getCid()));
        contentValues.put("pid", Integer.valueOf(yellowPagePhone.getProviderId()));
        contentValues.put(MiniDefine.m, Integer.valueOf(yellowPagePhone.getPhoneType()));
        contentValues.put("marked_count", Integer.valueOf(yellowPagePhone.getMarkedCount()));
        contentValues.put("normalized_number", yellowPagePhone.getNormalizedNumber());
        contentValues.put("min_match", PhoneNumberUtils.toCallerIDMinMatch(yellowPagePhone.getNormalizedNumber()));
        if (sQLiteDatabase.replace("antispam_number", null, contentValues) > 0) {
            return true;
        }
        return false;
    }

    public static boolean a(Context context, SQLiteDatabase sQLiteDatabase, String str) {
        return b(sQLiteDatabase, YellowPageUtils.getNormalizedNumber(context, str)) > 0;
    }

    public static boolean isFraudIncomingNumber(Context context, int i, String str, String str2) {
        Object phoneNumber = Sim.getPhoneNumber(context, i);
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.d("AntispamNumberHandler", "isFraudIncomingNumber: The parameter should not be null");
            return false;
        }
        JSONRequest jSONRequest = new JSONRequest(context, HostManager.getFraudVerifyUrl());
        jSONRequest.addParam("sid", str2);
        jSONRequest.addParam("calling", o.getNormalizedNumber(context, str));
        jSONRequest.addParam("called", CoderUtils.encodeSHA(o.getNormalizedNumber(context, phoneNumber)));
        jSONRequest.setDecryptDownloadData(false);
        if (jSONRequest.getStatus() != 0) {
            return false;
        }
        CharSequence requestData = jSONRequest.requestData();
        Log.d("AntispamNumberHandler", "isFraudIncomingNumber: the verified result is " + requestData);
        return TextUtils.equals(GlobalConstants.d, requestData);
    }

    public static void a(Context context, SQLiteDatabase sQLiteDatabase, String str, int i, boolean z) {
        sQLiteDatabase.delete("antispam_number", a.b(context, "normalized_number", str) + " AND " + MiniDefine.m + " = ?", new String[]{String.valueOf(3)});
        if (!z) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("number", str);
            String normalizedNumber = o.getNormalizedNumber(context, str);
            contentValues.put("normalized_number", normalizedNumber);
            contentValues.put("min_match", PhoneNumberUtils.toCallerIDMinMatch(normalizedNumber));
            contentValues.put("cid", Integer.valueOf(i));
            contentValues.put(MiniDefine.m, Integer.valueOf(3));
            contentValues.put("pid", Integer.valueOf(0));
            sQLiteDatabase.replace("antispam_number", null, contentValues);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.database.sqlite.SQLiteDatabase r3, java.lang.String r4, java.lang.String r5, com.miui.yellowpage.utils.antispam.dataprocessor.i r6) {
        /*
        r0 = 1;
        if (r6 == 0) goto L_0x0013;
    L_0x0003:
        r1 = r6.getCategory();	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ NumberFormatException -> 0x0071 }
        if (r1 == 0) goto L_0x0013;
    L_0x000d:
        if (r0 != 0) goto L_0x0015;
    L_0x000f:
        b(r3, r5);	 Catch:{ NumberFormatException -> 0x0071 }
    L_0x0012:
        return;
    L_0x0013:
        r0 = 0;
        goto L_0x000d;
    L_0x0015:
        r0 = new android.content.ContentValues;	 Catch:{ NumberFormatException -> 0x0071 }
        r0.<init>();	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = "number";
        r0.put(r1, r4);	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = "min_match";
        r2 = miui.telephony.PhoneNumberUtils.toCallerIDMinMatch(r5);	 Catch:{ NumberFormatException -> 0x0071 }
        r0.put(r1, r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = "normalized_number";
        r0.put(r1, r5);	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = "pid";
        r2 = r6.getProvider();	 Catch:{ NumberFormatException -> 0x0071 }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r0.put(r1, r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = "marked_count";
        r2 = r6.gO();	 Catch:{ NumberFormatException -> 0x0071 }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r0.put(r1, r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = "type";
        r2 = 1;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r0.put(r1, r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = "cid";
        r2 = r6.getCategory();	 Catch:{ NumberFormatException -> 0x0071 }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r0.put(r1, r2);	 Catch:{ NumberFormatException -> 0x0071 }
        r1 = "antispam_number";
        r2 = 0;
        r3.replace(r1, r2, r0);	 Catch:{ NumberFormatException -> 0x0071 }
        goto L_0x0012;
    L_0x0071:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.providers.yellowpage.b.a(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String, com.miui.yellowpage.utils.antispam.dataprocessor.i):void");
    }

    private static long b(SQLiteDatabase sQLiteDatabase, String str) {
        Log.d("AntispamNumberHandler", "markNumberAsInvalid");
        ContentValues contentValues = new ContentValues();
        contentValues.put(MiniDefine.m, Integer.valueOf(5));
        contentValues.put("cid", Integer.valueOf(0));
        return (long) sQLiteDatabase.update("antispam_number", contentValues, "normalized_number = ? AND type <> ?", new String[]{str, String.valueOf(3)});
    }
}
