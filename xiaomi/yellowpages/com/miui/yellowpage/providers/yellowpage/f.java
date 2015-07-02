package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.miui.yellowpage.base.utils.Log;
import miui.telephony.PhoneNumberUtils.PhoneNumber;

/* compiled from: YellowPageDatabaseHelper */
class f implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ q Ah;

    f(q qVar, SQLiteDatabase sQLiteDatabase) {
        this.Ah = qVar;
        this.Ag = sQLiteDatabase;
    }

    public void run() {
        String string;
        PhoneNumber parse;
        ContentValues contentValues;
        Cursor query = this.Ag.query("phone_lookup", new String[]{"number"}, null, null, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    string = query.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        parse = PhoneNumber.parse(string);
                        if (TextUtils.isEmpty(parse.getCountryCode())) {
                            parse.setDefaultCountryCode("86");
                        }
                        String normalizedNumber = parse.getNormalizedNumber(true, true);
                        parse.recycle();
                        if (!(TextUtils.equals(normalizedNumber, string) || normalizedNumber.startsWith("96"))) {
                            contentValues = new ContentValues();
                            contentValues.put("normalized_number", normalizedNumber);
                            if (this.Ag.update("phone_lookup", contentValues, "number = '" + string + "'", null) > 0) {
                                Log.d("YellowPageDatabaseHelper", "Update phonelookup normalized number from " + string + " to " + normalizedNumber);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    query.close();
                }
            }
        }
        query = this.Ag.query("antispam_number", new String[]{"number"}, null, null, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    string = query.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        parse = PhoneNumber.parse(string);
                        if (TextUtils.isEmpty(parse.getCountryCode())) {
                            parse.setDefaultCountryCode("86");
                        }
                        String normalizedNumber2 = parse.getNormalizedNumber(true, true);
                        parse.recycle();
                        if (!(TextUtils.equals(normalizedNumber2, string) || normalizedNumber2.startsWith("96"))) {
                            contentValues = new ContentValues();
                            contentValues.put("normalized_number", normalizedNumber2);
                            if (this.Ag.update("antispam_number", contentValues, "number = '" + string + "'", null) > 0) {
                                Log.d("YellowPageDatabaseHelper", "Update antispam normalized number from " + string + " to " + normalizedNumber2);
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    query.close();
                }
            }
        }
        if (this.Ag.getVersion() == 47) {
            Log.d("YellowPageDatabaseHelper", "upgradeToV14: update cached data");
            Intent intent = new Intent("com.miui.data.UPDATE_CACHED");
            intent.setPackage("com.miui.yellowpage");
            this.Ah.mContext.sendBroadcast(intent);
        }
    }
}
