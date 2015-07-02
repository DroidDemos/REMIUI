package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.model.YellowPageExtraData;
import java.util.LinkedList;
import java.util.List;
import miui.provider.ExtraContacts.SmartDialer;
import miui.telephony.PhoneNumberUtils;
import miui.yellowpage.YellowPagePhone;

/* compiled from: PhoneLookupHandler */
public class a {
    public static String b(Context context, String str, String str2) {
        Object normalizedNumber = o.getNormalizedNumber(context, str2);
        return TextUtils.isEmpty(normalizedNumber) ? "1 = 0" : str + " = '" + normalizedNumber + "'";
    }

    public static String h(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("((SELECT ");
        stringBuilder.append("yid AS yellowpage_id, ");
        stringBuilder.append("photo_url,");
        stringBuilder.append("thumbnail_url,");
        stringBuilder.append("tag,");
        stringBuilder.append("yellow_page_name,");
        stringBuilder.append("yellow_page_name_pinyin,");
        stringBuilder.append("tag_pinyin,");
        stringBuilder.append("number,");
        stringBuilder.append("normalized_number,");
        stringBuilder.append("min_match,");
        stringBuilder.append("hide,");
        stringBuilder.append("suspect,");
        stringBuilder.append("call_menu,");
        stringBuilder.append("t9_rank,");
        stringBuilder.append("atd_category_id,");
        stringBuilder.append("provider_id");
        stringBuilder.append(" FROM phone_lookup");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(" WHERE ");
            stringBuilder.append(b(context, "normalized_number", str));
        }
        stringBuilder.append(") INNER JOIN yellow_page");
        stringBuilder.append(" ON ");
        stringBuilder.append("yellowpage_id = yid");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static List a(SQLiteDatabase sQLiteDatabase, YellowPage yellowPage) {
        List linkedList = new LinkedList();
        if (yellowPage != null && yellowPage.getId() > 0) {
            sQLiteDatabase.delete("phone_lookup", "yid=" + yellowPage.getId() + " AND " + "hide" + "=" + 0, null);
            List<YellowPagePhone> phones = yellowPage.getPhones();
            if (phones != null && phones.size() > 0) {
                for (YellowPagePhone yellowPagePhone : phones) {
                    if (!TextUtils.isEmpty(yellowPagePhone.getNumber())) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("number", yellowPagePhone.getNumber());
                        String normalizedNumber = yellowPagePhone.getNormalizedNumber();
                        contentValues.put("normalized_number", normalizedNumber);
                        contentValues.put("min_match", PhoneNumberUtils.toCallerIDMinMatch(normalizedNumber));
                        contentValues.put("yid", Long.valueOf(yellowPage.getId()));
                        contentValues.put("photo_url", yellowPage.getPhotoName());
                        contentValues.put("thumbnail_url", yellowPage.getThumbnailName());
                        contentValues.put("provider_id", Integer.valueOf(yellowPage.getProviderId()));
                        contentValues.put("yellow_page_name", yellowPage.getName());
                        contentValues.put("yellow_page_name_pinyin", yellowPage.getPinyin());
                        contentValues.put("tag_pinyin", yellowPagePhone.getTagPinyin());
                        contentValues.put("tag", yellowPagePhone.getTag());
                        contentValues.put("t9_rank", Long.valueOf(yellowPagePhone.getT9Rank()));
                        contentValues.put("atd_category_id", Integer.valueOf(yellowPagePhone.getCid()));
                        contentValues.put("hide", Integer.valueOf(yellowPagePhone.isVisible() ? 0 : 1));
                        contentValues.put("suspect", Boolean.valueOf(yellowPagePhone.isMarkedSuspect()));
                        Object extraData = yellowPage.getExtraData();
                        if (!TextUtils.isEmpty(extraData)) {
                            int i;
                            String str = "call_menu";
                            if (YellowPageExtraData.fromJson(extraData).hasCallMenu()) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            contentValues.put(str, Integer.valueOf(i));
                        }
                        sQLiteDatabase.insertWithOnConflict("phone_lookup", null, contentValues, 5);
                        linkedList.add(normalizedNumber);
                    }
                }
            }
        }
        return linkedList;
    }

    public static void b(SQLiteDatabase sQLiteDatabase, YellowPage yellowPage) {
        if (yellowPage != null && yellowPage.getId() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("photo_url", yellowPage.getPhotoName());
            contentValues.put("thumbnail_url", yellowPage.getThumbnailName());
            contentValues.put("provider_id", Integer.valueOf(yellowPage.getProviderId()));
            contentValues.put("yellow_page_name", yellowPage.getName());
            contentValues.put("yellow_page_name_pinyin", yellowPage.getPinyin());
            sQLiteDatabase.update("phone_lookup", contentValues, "yid = ? AND hide = ?", new String[]{String.valueOf(yellowPage.getId()), String.valueOf(1)});
        }
    }

    public static void a(Context context, SQLiteDatabase sQLiteDatabase, long j) {
        sQLiteDatabase.delete("phone_lookup", "yid=" + j, null);
        context.getContentResolver().delete(Uri.withAppendedPath(SmartDialer.CONTENT_BUILD_YELLOWPAGE_T9_URI, String.valueOf(j)), null, null);
    }
}
