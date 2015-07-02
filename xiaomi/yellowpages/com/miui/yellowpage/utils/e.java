package com.miui.yellowpage.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.model.Provider;
import com.miui.yellowpage.base.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import miui.telephony.PhoneNumberUtils;
import miui.text.ChinesePinyinConverter;
import miui.text.ChinesePinyinConverter.Token;
import miui.yellowpage.YellowPageContract.PhoneLookup;
import miui.yellowpage.YellowPageContract.YellowPage;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;

/* compiled from: YellowPageDB */
public class e {
    private static final String[] lo;

    static {
        lo = new String[]{"provider_id", "tag", "yellow_page_name", "yellow_page_name_pinyin", "tag_pinyin", "hide", "number", "normalized_number"};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r8, long r9) {
        /*
        r6 = 1;
        r7 = 0;
        r0 = r8.getContentResolver();
        r1 = miui.yellowpage.YellowPageContract.YellowPage.CONTENT_URI;
        r2 = new java.lang.String[r6];
        r3 = "favorite";
        r2[r7] = r3;
        r3 = "yid = ?";
        r4 = new java.lang.String[r6];
        r5 = java.lang.String.valueOf(r9);
        r4[r7] = r5;
        r5 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x0036;
    L_0x001f:
        r0 = r1.moveToFirst();	 Catch:{ Exception -> 0x0038 }
        if (r0 == 0) goto L_0x0033;
    L_0x0025:
        r0 = 0;
        r0 = r1.getInt(r0);	 Catch:{ Exception -> 0x0038 }
        if (r0 != r6) goto L_0x0031;
    L_0x002c:
        r0 = r6;
    L_0x002d:
        r1.close();
    L_0x0030:
        return r0;
    L_0x0031:
        r0 = r7;
        goto L_0x002d;
    L_0x0033:
        r1.close();
    L_0x0036:
        r0 = r7;
        goto L_0x0030;
    L_0x0038:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0040 }
        r1.close();
        goto L_0x0036;
    L_0x0040:
        r0 = move-exception;
        r1.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.utils.e.a(android.content.Context, long):boolean");
    }

    public static void a(Context context, long j, boolean z) {
        int i = z ? 1 : 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", Integer.valueOf(i));
        context.getContentResolver().update(YellowPage.CONTENT_URI, contentValues, "yid = ?", new String[]{String.valueOf(j)});
    }

    public static void b(Context context, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("last_use_time", Integer.valueOf(0));
        context.getContentResolver().update(YellowPage.CONTENT_URI, contentValues, "yid = ?", new String[]{String.valueOf(j)});
    }

    public static com.miui.yellowpage.base.model.YellowPage i(Context context, String str) {
        com.miui.yellowpage.base.model.YellowPage yellowPage = null;
        Cursor query = context.getContentResolver().query(YellowPage.CONTENT_URI, new String[]{MiniDefine.at}, "yid = ?", new String[]{str}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    yellowPage = com.miui.yellowpage.base.model.YellowPage.fromJson(query.getString(0));
                } else {
                    query.close();
                }
            } finally {
                query.close();
            }
        }
        return yellowPage;
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Cursor query = context.getContentResolver().query(Uri.withAppendedPath(PhoneLookup.CONTENT_URI, str2), null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() <= 0) {
                        query.close();
                    } else {
                        return;
                    }
                } finally {
                    query.close();
                }
            }
            Object O = O(str);
            Log.d("YellowPageDB", "The name " + str + " 's pin yin is " + O);
            if (!TextUtils.isEmpty(O)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("hide", GlobalConstants.d);
                String normalizedNumber = YellowPageUtils.getNormalizedNumber(context, str2);
                contentValues.put("number", str2);
                contentValues.put("normalized_number", normalizedNumber);
                contentValues.put("min_match", PhoneNumberUtils.toCallerIDMinMatch(normalizedNumber));
                contentValues.put("provider_id", Integer.valueOf(Provider.DEFAULT_PROVIDER.getId()));
                contentValues.put("tag", str);
                contentValues.put("tag_pinyin", O);
                contentValues.put("photo_url", ConfigConstant.WIRELESS_FILENAME);
                contentValues.put("yellow_page_name", "e\u4ee3\u9a7e");
                contentValues.put("yellow_page_name_pinyin", "e dai jia");
                contentValues.put("yid", Integer.valueOf(7740882));
                contentValues.put("suspect", Integer.valueOf(0));
                context.getContentResolver().insert(PhoneLookup.CONTENT_URI, contentValues);
            }
        }
    }

    private static String O(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = ChinesePinyinConverter.getInstance().get(str);
        if (arrayList == null || arrayList.size() != str.length()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Token token = (Token) it.next();
            if (stringBuilder.length() != 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(token.target);
        }
        return stringBuilder.toString();
    }

    public static ArrayList c(Context context, long j) {
        if (j <= 0) {
            return null;
        }
        Cursor query = context.getContentResolver().query(PhoneLookup.CONTENT_URI, lo, "yid = ? AND hide = ?", new String[]{String.valueOf(j), String.valueOf(1)}, null);
        if (query == null) {
            return null;
        }
        ArrayList arrayList = null;
        while (query.moveToNext()) {
            try {
                int i = query.getInt(0);
                String string = query.getString(1);
                YellowPagePhone yellowPagePhone = new YellowPagePhone(j, query.getString(2), string, query.getString(6), query.getString(7), 1, i, 0, query.getInt(5) == 0, query.getString(3), query.getString(4));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(yellowPagePhone);
            } finally {
                query.close();
            }
        }
        return arrayList;
    }
}
