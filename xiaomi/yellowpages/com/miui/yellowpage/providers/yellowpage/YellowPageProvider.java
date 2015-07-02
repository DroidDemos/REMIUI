package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Pair;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.protocol.WindowData;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.mipub.MiPubUtils;
import com.miui.yellowpage.base.model.City;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.request.JSONRequest;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.Cache;
import com.miui.yellowpage.base.utils.GeoLocationManager;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Permission;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.YellowPageModule;
import com.miui.yellowpage.utils.F;
import com.miui.yellowpage.utils.I;
import com.miui.yellowpage.utils.J;
import com.miui.yellowpage.utils.KeyGen;
import com.miui.yellowpage.utils.antispam.dataprocessor.d;
import com.miui.yellowpage.utils.antispam.dataprocessor.i;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.telephony.PhoneNumberUtils;
import miui.yellowpage.YellowPageContract.Navigation;
import miui.yellowpage.YellowPagePhone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YellowPageProvider extends ContentProvider {
    private static final c sZ;
    private static q ta;
    private static long tc;
    private static AtomicBoolean td;
    private static BloomFilter te;
    private static BloomFilter tf;
    private GeoLocationManager tb;

    static {
        tc = -1;
        td = new AtomicBoolean(false);
        sZ = new c(-1);
        sZ.a("miui.yellowpage", "yellow_page", 0, false);
        sZ.a("miui.yellowpage", "yellow_page_all", 32, false);
        sZ.a("miui.yellowpage", "phone_lookup/*", 2, false);
        sZ.a("miui.yellowpage", "phone_lookup_cloud/*", 33, true);
        sZ.a("miui.yellowpage", "phone_lookup_normalized_number/*", 36, false);
        sZ.a("miui.yellowpage", "phone_lookup_fraud_verify", 34, true);
        sZ.a("miui.yellowpage", "phone_lookup", 1, false);
        sZ.a("miui.yellowpage", "antispam_category", 3, false);
        sZ.a("miui.yellowpage", "antispam_number", 4, false);
        sZ.a("miui.yellowpage", "antispam_mark_number", 37, false);
        sZ.a("miui.yellowpage", "antispam_number_suspect_service/*", 38, false);
        sZ.a("miui.yellowpage", "antispam_update_call_log_tag/*", 39, false);
        sZ.a("miui.yellowpage", "provider", 7, false);
        sZ.a("miui.yellowpage", "image/*", 8, true);
        sZ.a("miui.yellowpage", "antispam_number_phone_lookup/*", 5, false);
        sZ.a("miui.yellowpage", "antispam_number_preset_phone_lookup/*", 6, false);
        sZ.a("miui.yellowpage", "phone_usage", 9, false);
        sZ.a("miui.yellowpage", "t9_lookup", 10, false);
        sZ.a("miui.yellowpage", "data_status", 11, false);
        sZ.a("miui.yellowpage", "antispam_white_list", 12, false);
        sZ.a("miui.yellowpage", "user_area", 13, true);
        sZ.a("miui.yellowpage", "cache", 14, false);
        sZ.a("miui.yellowpage", "uuid", 15, false);
        sZ.a("miui.yellowpage", "navigation", 18, false);
        sZ.a("miui.yellowpage", "navigation/update", 19, true);
        sZ.a("miui.yellowpage", "search/local/*", 20, false);
        sZ.a("miui.yellowpage", "search/remote/*", 21, true);
        sZ.a("miui.yellowpage", "search/suggestion/*", 22, true);
        sZ.a("miui.yellowpage", "search/tips", 23, false);
        sZ.a("miui.yellowpage", "search/tips/update", 24, true);
        sZ.a("miui.yellowpage", "current_location", 25, true);
        sZ.a("miui.yellowpage", "search_location_update", 26, true);
        sZ.a("miui.yellowpage", "statistic", 27, false);
        sZ.a("miui.yellowpage", "region", 28, false);
        sZ.a("miui.yellowpage", "ivr/*", 29, true);
        sZ.a("miui.yellowpage", "non_ivr/*", 30, true);
        sZ.a("miui.yellowpage", "permission", 31, false);
        sZ.a("miui.yellowpage", "settings/*", 35, false);
        sZ.a("miui.yellowpage", "statistic/*", 40, false);
        sZ.a("miui.yellowpage", "service_number/*", 41, false);
        sZ.a("miui.yellowpage", "yellowpage/*", 42, true);
        sZ.a("miui.yellowpage", "image_cloud/*", 43, true);
        sZ.a("miui.yellowpage", "image_avatar/*", 44, false);
        sZ.a("miui.yellowpage", "image_url/*", 45, false);
        sZ.a("miui.yellowpage", "flow_of_package/*", 46, true);
        sZ.a("miui.yellowpage", "profile", 47, false);
        sZ.a("miui.yellowpage", "profile/update", 48, true);
        sZ.a("miui.yellowpage", "web_res", 49, true);
        sZ.a("miui.yellowpage", "hotword", 50, false);
        sZ.a("miui.yellowpage", "hotword/update", 51, true);
    }

    public boolean onCreate() {
        ta = q.ad(getContext());
        this.tb = GeoLocationManager.getInstance(getContext());
        dY();
        return false;
    }

    public void dY() {
        ThreadPool.execute(new s(this));
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        Log.d("YellowPageProvider", "[" + System.currentTimeMillis() + "] start to build phone lookup bloom filter");
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("phone_lookup", new String[]{"normalized_number"}, null, null, null, null, null);
        if (query != null) {
            try {
                te = BloomFilter.a(Funnels.a(Charset.forName("UTF-8")), Math.min(query.getCount(), 20000), 0.001d);
                while (query.moveToNext()) {
                    te.put(query.getString(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                query.close();
            }
        }
        Log.d("YellowPageProvider", "[" + System.currentTimeMillis() + "] constructed phone lookup bloom filter");
    }

    public void c(SQLiteDatabase sQLiteDatabase) {
        Log.d("YellowPageProvider", "[" + System.currentTimeMillis() + "] start to build antispam lookup bloom filter");
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("antispam_number", new String[]{"normalized_number"}, null, null, null, null, null);
        if (query != null) {
            try {
                tf = BloomFilter.a(Funnels.a(Charset.forName("UTF-8")), Math.min(query.getCount(), 20000), 0.001d);
                while (query.moveToNext()) {
                    tf.put(query.getString(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                query.close();
            }
        }
        Log.d("YellowPageProvider", "[" + System.currentTimeMillis() + "] constructed antispam lookup bloom filter");
    }

    private void dZ() {
        if (TextUtils.isEmpty(Preference.getString(getContext(), "pref_uuid", ConfigConstant.WIRELESS_FILENAME))) {
            try {
                Preference.setString(getContext(), "pref_uuid", KeyGen.getUUID(getContext()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String ea() {
        if (TextUtils.isEmpty(Preference.getString(getContext(), "pref_uuid", ConfigConstant.WIRELESS_FILENAME))) {
            dZ();
        }
        return Preference.getString(getContext(), "pref_uuid", ConfigConstant.WIRELESS_FILENAME);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Exception e;
        int i = 1;
        int i2 = 0;
        Cursor cursor = null;
        SQLiteDatabase readableDatabase = ta.getReadableDatabase();
        String lastPathSegment;
        String normalizedNumber;
        Object lastPathSegment2;
        MatrixCursor matrixCursor;
        int i3;
        MatrixCursor matrixCursor2;
        Object[] objArr;
        String queryParameter;
        String queryParameter2;
        String decode;
        switch (sZ.a(getContext(), uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return readableDatabase.query("yellow_page", strArr, "subscribe_stats IN (1,2)" + aM(str), strArr2, null, null, str2);
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return readableDatabase.query("phone_lookup", strArr, str, strArr2, null, null, str2);
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                lastPathSegment = uri.getLastPathSegment();
                normalizedNumber = o.getNormalizedNumber(getContext(), lastPathSegment);
                if (!td.get() || te.w(normalizedNumber)) {
                    return readableDatabase.query(a.h(getContext(), lastPathSegment), strArr, str, strArr2, null, null, str2);
                }
                return null;
            case WindowData.d /*3*/:
                return readableDatabase.query("antispam_category", strArr, str, strArr2, null, null, str2);
            case Base64.CRLF /*4*/:
                return readableDatabase.query("antispam_number", strArr, str, strArr2, null, null, str2);
            case WindowData.f /*5*/:
                lastPathSegment2 = uri.getLastPathSegment();
                lastPathSegment = o.getNormalizedNumber(getContext(), lastPathSegment2);
                if ((td.get() && !tf.w(lastPathSegment)) || TextUtils.isEmpty(lastPathSegment2)) {
                    return null;
                }
                return readableDatabase.query("antispam_number", strArr, a.b(getContext(), "normalized_number", lastPathSegment2) + aM(str), strArr2, null, null, str2);
            case WindowData.g /*6*/:
                Object lastPathSegment3 = uri.getLastPathSegment();
                if (TextUtils.isEmpty(lastPathSegment3)) {
                    return null;
                }
                return a(readableDatabase, strArr, lastPathSegment3);
            case WindowData.h /*7*/:
                return readableDatabase.query("provider", strArr, str, strArr2, null, null, str2);
            case WindowData.j /*9*/:
                return readableDatabase.query("phone_usage", strArr, str, strArr2, null, null, str2);
            case WindowData.k /*10*/:
                return readableDatabase.query("t9_lookup", strArr, str, strArr2, null, null, str2);
            case 11:
                return readableDatabase.query("data_status", strArr, str, strArr2, null, null, str2);
            case 12:
                return readableDatabase.query("antispam_white_list", strArr, str, strArr2, null, null, str2);
            case 13:
                if (TextUtils.isEmpty(getUserAreaCode())) {
                    return null;
                }
                matrixCursor = new MatrixCursor(new String[]{"area_code"});
                matrixCursor.addRow(new Object[]{r3});
                return matrixCursor;
            case 14:
                return readableDatabase.query("cache", strArr, str, strArr2, null, null, str2);
            case 15:
                if (TextUtils.isEmpty(ea())) {
                    return null;
                }
                matrixCursor = new MatrixCursor(new String[]{"uuid"});
                matrixCursor.addRow(new Object[]{r3});
                return matrixCursor;
            case 18:
                return readableDatabase.query("cache", strArr, ("cache_key = '" + Cache.NAVIGATION_DATA_CACHE_KEY + "'") + aM(str), strArr2, null, null, str2);
            case Encoder.LINE_GROUPS /*19*/:
                c(getContext(), readableDatabase);
                return null;
            case 20:
                return a(uri, readableDatabase);
            case 21:
                return d(uri);
            case 22:
                return f(uri);
            case 23:
                Cursor query = readableDatabase.query("cache", strArr, ("cache_key = '" + Cache.NAVIGATION_SEARCH_TIPS_CACHE_KEY + "'") + aM(str), strArr2, null, null, str2);
                if (query == null || query.getCount() >= 1) {
                    cursor = query;
                } else {
                    query.close();
                }
                if (cursor != null) {
                    return cursor;
                }
                matrixCursor = new MatrixCursor(strArr);
                matrixCursor.addRow(new Object[]{getContext().getString(R.string.navigation_search_default_hint)});
                return matrixCursor;
            case ConfigConstant.DEFAULT_LOCATE_LINES /*24*/:
                d(getContext(), readableDatabase);
                return null;
            case 25:
                return b(null);
            case 26:
                this.tb.initLocation();
                return null;
            case 27:
                return readableDatabase.query("statistic", strArr, str, strArr2, null, null, str2);
            case 28:
                return readableDatabase.query("region", strArr, str, strArr2, null, null, str2);
            case 29:
                if (TextUtils.equals(strArr[0], "data")) {
                    return a(uri);
                }
                return b(uri);
            case ConfigConstant.DEFAULT_LOCATE_INTERVAL /*30*/:
                return c(uri);
            case 31:
                return readableDatabase.query("permission", strArr, str, strArr2, null, null, str2);
            case 32:
                return readableDatabase.query("yellow_page", strArr, str, strArr2, null, null, str2);
            case 33:
                if (!p.aa(getContext()) || strArr == null) {
                    return null;
                }
                lastPathSegment = uri.getLastPathSegment();
                if (!y.J(getContext(), lastPathSegment)) {
                    Log.d("YellowPageProvider", "The number has just updated, obtain nothing");
                    return null;
                } else if (TextUtils.isEmpty(lastPathSegment)) {
                    return null;
                } else {
                    try {
                        YellowPage K = y.K(getContext(), lastPathSegment);
                        if (K != null) {
                            YellowPagePhone phoneInfo = K.getPhoneInfo(getContext(), lastPathSegment);
                            if (phoneInfo.isYellowPage()) {
                                if (y.c(readableDatabase, K) > 0) {
                                    for (YellowPagePhone normalizedNumber2 : K.getPhones()) {
                                        aN(normalizedNumber2.getNormalizedNumber());
                                    }
                                }
                                matrixCursor = new MatrixCursor(strArr);
                                try {
                                    Object[] objArr2 = new Object[strArr.length];
                                    for (i3 = 0; i3 < objArr2.length; i3++) {
                                        if ("yid".equals(strArr[i3])) {
                                            objArr2[i3] = Long.valueOf(phoneInfo.getYellowPageId());
                                        } else if ("provider_id".equals(strArr[i3])) {
                                            objArr2[i3] = Integer.valueOf(phoneInfo.getProviderId());
                                        } else if ("photo_url".equals(strArr[i3])) {
                                            objArr2[i3] = K.getPhotoName();
                                        } else if ("thumbnail_url".equals(strArr[i3])) {
                                            objArr2[i3] = K.getThumbnailName();
                                        } else if ("yellow_page_name".equals(strArr[i3])) {
                                            objArr2[i3] = phoneInfo.getYellowPageName();
                                        } else if ("yellow_page_name_pinyin".equals(strArr[i3])) {
                                            objArr2[i3] = phoneInfo.getYellowPagePinyin();
                                        } else if ("hide".equals(strArr[i3])) {
                                            objArr2[i3] = Integer.valueOf(phoneInfo.isVisible() ? 1 : 0);
                                        } else if ("normalized_number".equals(strArr[i3])) {
                                            objArr2[i3] = phoneInfo.getNormalizedNumber();
                                        } else if ("suspect".equals(strArr[i3])) {
                                            objArr2[i3] = Integer.valueOf(phoneInfo.isSuspect(getContext()) ? 1 : 0);
                                        } else if ("call_menu".equals(strArr[i3])) {
                                            objArr2[i3] = Integer.valueOf(phoneInfo.hasCallMenu() ? 1 : 0);
                                        } else if ("t9_rank".equals(strArr[i3])) {
                                            objArr2[i3] = Long.valueOf(phoneInfo.getT9Rank());
                                        } else if ("tag".equals(strArr[i3])) {
                                            objArr2[i3] = phoneInfo.getTag();
                                        } else if ("tag_pinyin".equals(strArr[i3])) {
                                            objArr2[i3] = phoneInfo.getTagPinyin();
                                        } else if ("atd_category_id".equals(strArr[i3])) {
                                            objArr2[i3] = Integer.valueOf(phoneInfo.getCid());
                                        } else {
                                            Log.d("YellowPageProvider", "Not supported projection cloumn " + strArr[i3]);
                                            objArr2[i3] = null;
                                        }
                                    }
                                    matrixCursor.addRow(objArr2);
                                } catch (Exception e2) {
                                    e = e2;
                                    Object obj = matrixCursor;
                                    Log.d("YellowPageProvider", e.getMessage());
                                    return cursor;
                                }
                            }
                            aO(phoneInfo.getNormalizedNumber());
                            b.a(readableDatabase, phoneInfo);
                            matrixCursor = null;
                            return matrixCursor;
                        }
                        long b = y.b(getContext(), readableDatabase, lastPathSegment);
                        if (b <= 0) {
                            b.a(getContext(), readableDatabase, lastPathSegment);
                            return null;
                        }
                        a.a(getContext(), readableDatabase, b);
                        return null;
                    } catch (Exception e3) {
                        e = e3;
                        Log.d("YellowPageProvider", e.getMessage());
                        return cursor;
                    }
                }
            case 34:
                boolean isFraudIncomingNumber = b.isFraudIncomingNumber(getContext(), Integer.valueOf(uri.getQueryParameter("simIndex")).intValue(), uri.getQueryParameter("incoming"), uri.getQueryParameter("yid"));
                matrixCursor = new MatrixCursor(new String[]{"isFraud"});
                matrixCursor2 = matrixCursor;
                objArr = new Object[1];
                if (isFraudIncomingNumber) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                objArr[0] = Integer.valueOf(i3);
                matrixCursor2.addRow(objArr);
                return matrixCursor;
            case 35:
                matrixCursor = new MatrixCursor(new String[]{uri.getLastPathSegment()});
                matrixCursor2 = matrixCursor;
                objArr = new Object[1];
                if (!p.B(getContext(), uri.getLastPathSegment())) {
                    i = 0;
                }
                objArr[0] = Integer.valueOf(i);
                matrixCursor2.addRow(objArr);
                return matrixCursor;
            case 36:
                normalizedNumber = uri.getLastPathSegment();
                queryParameter = uri.getQueryParameter("defaultCountryCode");
                queryParameter2 = uri.getQueryParameter("withCountryCode");
                matrixCursor = new MatrixCursor(new String[]{"normalized_number"});
                matrixCursor.addRow(new Object[]{o.getNormalizedNumber(getContext(), normalizedNumber, MiniDefine.F.equals(queryParameter2), queryParameter)});
                return matrixCursor;
            case 38:
                normalizedNumber = uri.getLastPathSegment();
                matrixCursor = new MatrixCursor(new String[]{"suspect"});
                matrixCursor2 = matrixCursor;
                objArr = new Object[1];
                if (!o.cf(normalizedNumber)) {
                    i = 0;
                }
                objArr[0] = Integer.valueOf(i);
                matrixCursor2.addRow(objArr);
                return matrixCursor;
            case 41:
                normalizedNumber = Uri.decode(uri.getLastPathSegment());
                matrixCursor = new MatrixCursor(new String[]{"service"});
                matrixCursor2 = matrixCursor;
                objArr = new Object[1];
                if (!MiPubUtils.isServiceNumber(normalizedNumber)) {
                    i = 0;
                }
                objArr[0] = Integer.valueOf(i);
                matrixCursor2.addRow(objArr);
                return matrixCursor;
            case 42:
                YellowPage yellowPage = MiPubUtils.getYellowPage(getContext(), Uri.decode(uri.getLastPathSegment()));
                if (yellowPage == null) {
                    return null;
                }
                matrixCursor = new MatrixCursor(strArr);
                Object[] objArr3 = new Object[strArr.length];
                while (i2 < strArr.length) {
                    if ("yellow_page_name".equals(strArr[i2])) {
                        objArr3[i2] = yellowPage.getName();
                    } else if ("yid".equals(strArr[i2])) {
                        objArr3[i2] = Long.valueOf(yellowPage.getId());
                    } else if ("thumbnail_url".equals(strArr[i2])) {
                        objArr3[i2] = yellowPage.getThumbnailName();
                    } else {
                        objArr3[i2] = null;
                        Log.d("YellowPageProvider", "Not supported project " + strArr[i2]);
                    }
                    i2++;
                }
                matrixCursor.addRow(objArr3);
                return matrixCursor;
            case 43:
                Object decode2 = Uri.decode(uri.getLastPathSegment());
                lastPathSegment = uri.getQueryParameter("fileName");
                if (TextUtils.isEmpty(decode2)) {
                    return null;
                }
                e.d(getContext(), lastPathSegment, decode2);
                return null;
            case 44:
                decode = Uri.decode(uri.getLastPathSegment());
                normalizedNumber = uri.getQueryParameter(MiniDefine.m);
                matrixCursor = new MatrixCursor(new String[]{"url"});
                if ("thumbnail".equals(normalizedNumber)) {
                    lastPathSegment2 = HostManager.getYellowPageThumbnail(getContext(), decode);
                } else {
                    normalizedNumber = HostManager.getYellowPagePhotoUrl(getContext(), decode);
                }
                matrixCursor.addRow(new Object[]{lastPathSegment2});
                return matrixCursor;
            case 45:
                normalizedNumber = Uri.decode(uri.getLastPathSegment());
                decode = uri.getQueryParameter(MiniDefine.K);
                queryParameter = uri.getQueryParameter(MiniDefine.B);
                queryParameter2 = uri.getQueryParameter(MiniDefine.P);
                matrixCursor = new MatrixCursor(new String[]{"url"});
                normalizedNumber = HostManager.getImageUrl(getContext(), normalizedNumber, Integer.valueOf(decode).intValue(), Integer.valueOf(queryParameter).intValue(), "jpg".equals(queryParameter2) ? ImageFormat.JPG : ImageFormat.PNG);
                matrixCursor.addRow(new Object[]{normalizedNumber});
                return matrixCursor;
            case 46:
                lastPathSegment2 = uri.getLastPathSegment();
                if (TextUtils.isEmpty(lastPathSegment2)) {
                    return null;
                }
                matrixCursor = new MatrixCursor(new String[]{"available"});
                matrixCursor2 = matrixCursor;
                objArr = new Object[1];
                if (!YellowPageModule.isFlowOfPackageAvailable(getContext(), Integer.valueOf(lastPathSegment2).intValue())) {
                    i = 0;
                }
                objArr[0] = Integer.valueOf(i);
                matrixCursor2.addRow(objArr);
                return matrixCursor;
            case 47:
                return readableDatabase.query("cache", strArr, ("cache_key = '" + Cache.PROFILE_CACHE_KEY + "'") + aM(str), strArr2, null, null, str2);
            case 48:
                b(getContext(), readableDatabase);
                return null;
            case 50:
                return readableDatabase.query("cache", strArr, ("cache_key = '" + Cache.HOT_WORDS_CACHE_KEY + "'") + aM(str), strArr2, null, null, str2);
            case 51:
                a(getContext(), readableDatabase);
                return null;
            default:
                return null;
        }
    }

    private Cursor a(Uri uri, SQLiteDatabase sQLiteDatabase) {
        String[] strArr = new String[]{"yid"};
        String[] strArr2 = new String[]{MiniDefine.at, String.valueOf(0)};
        String str = "yid IN (" + SQLiteQueryBuilder.buildQueryString(true, "phone_lookup", strArr, "yellow_page_name LIKE " + DatabaseUtils.sqlEscapeString("%" + uri.getLastPathSegment() + "%"), null, null, null, null) + ") AND " + MiniDefine.m + "=?";
        return sQLiteDatabase.query("yellow_page", strArr2, str, new String[]{String.valueOf(1)}, null, null, null);
    }

    private Cursor a(Uri uri) {
        if (TextUtils.isEmpty(F.D(getContext(), uri.getLastPathSegment()))) {
            return null;
        }
        Cursor matrixCursor = new MatrixCursor(new String[]{"data"});
        matrixCursor.addRow(new Object[]{r1});
        return matrixCursor;
    }

    private Cursor b(Uri uri) {
        int i = 1;
        boolean F = F.F(getContext(), uri.getLastPathSegment());
        Cursor matrixCursor = new MatrixCursor(new String[]{"exist"});
        Object[] objArr = new Object[1];
        if (!F) {
            i = 0;
        }
        objArr[0] = Integer.valueOf(i);
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    private Cursor c(Uri uri) {
        if (TextUtils.isEmpty(F.a(getContext(), uri.getLastPathSegment(), uri.getBooleanQueryParameter("cache", true)))) {
            return null;
        }
        Cursor matrixCursor = new MatrixCursor(new String[]{"data"});
        matrixCursor.addRow(new Object[]{r1});
        return matrixCursor;
    }

    private Cursor d(Uri uri) {
        Bundle bundle = new Bundle();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{MiniDefine.at, MiniDefine.m});
        Pair e = e(uri);
        int intValue = ((Integer) e.first).intValue();
        if (intValue == 0) {
            String str = (String) e.second;
            Log.d("YellowPageProvider", "[doRemoteQuery] json length:" + str.length());
            try {
                JSONObject jSONObject = new JSONObject(str);
                a(matrixCursor, jSONObject);
                bundle.putInt("result_state", b(matrixCursor, jSONObject));
            } catch (JSONException e2) {
                bundle.putInt("result_state", 4);
                e2.printStackTrace();
            }
        } else if (intValue == 4) {
            bundle.putInt("result_state", 4);
        } else if (intValue == 1) {
            bundle.putInt("result_state", 3);
        } else if (intValue == 5) {
            bundle.putInt("result_state", 5);
        }
        matrixCursor.setExtras(bundle);
        return matrixCursor;
    }

    private Pair e(Uri uri) {
        JSONRequest b = b(uri.getLastPathSegment(), uri.getQueryParameter("pn"), uri.getQueryParameter("locId"), uri.getQueryParameter("sugidx"));
        int status = b.getStatus();
        if (status == 0) {
            return new Pair(Integer.valueOf(0), b.requestData());
        }
        return new Pair(Integer.valueOf(status), null);
    }

    private Cursor f(Uri uri) {
        int i = 0;
        JSONRequest jSONRequest = new JSONRequest(getContext(), HostManager.getNavigationSearchSuggestionUrl());
        Object lastPathSegment = uri.getLastPathSegment();
        if (TextUtils.isEmpty(lastPathSegment)) {
            return null;
        }
        jSONRequest.addParam("keyword", lastPathSegment);
        Object queryParameter = uri.getQueryParameter("locId");
        if (!TextUtils.isEmpty(queryParameter)) {
            jSONRequest.addParam("destlocid", queryParameter);
        }
        queryParameter = getLocId();
        if (!TextUtils.isEmpty(queryParameter)) {
            jSONRequest.addParam("locid", queryParameter);
        }
        queryParameter = uri.getQueryParameter("catid");
        if (!TextUtils.isEmpty(queryParameter)) {
            jSONRequest.addParam("catid", queryParameter);
        }
        Cursor matrixCursor = new MatrixCursor(new String[]{MiniDefine.at});
        matrixCursor.addRow(new Object[]{lastPathSegment});
        if (jSONRequest.getStatus() != 0) {
            return matrixCursor;
        }
        try {
            JSONArray jSONArray = new JSONObject(jSONRequest.requestData()).getJSONArray("sugWords");
            while (i < jSONArray.length()) {
                matrixCursor.addRow(new Object[]{jSONArray.getString(i)});
                i++;
            }
            return matrixCursor;
        } catch (JSONException e) {
            e.printStackTrace();
            return matrixCursor;
        }
    }

    private Cursor b(Cursor cursor) {
        Log.d("YellowPageProvider", "getCityInfo");
        if (this.tb.getCity() == null) {
            return cursor;
        }
        Cursor matrixCursor = new MatrixCursor(new String[]{"location_name", "location_id"});
        ((MatrixCursor) matrixCursor).addRow(new Object[]{r2.getName(), r2.getLocId()});
        return matrixCursor;
    }

    private String aM(String str) {
        return !TextUtils.isEmpty(str) ? " AND (" + str + ")" : ConfigConstant.WIRELESS_FILENAME;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        long j = 0;
        SQLiteDatabase readableDatabase = ta.getReadableDatabase();
        switch (sZ.match(uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                YellowPage fromJson = YellowPage.fromJson(contentValues.getAsString(MiniDefine.at));
                if (fromJson != null) {
                    long c = y.c(readableDatabase, fromJson);
                    if (c <= 0) {
                        j = c;
                        break;
                    }
                    for (YellowPagePhone normalizedNumber : fromJson.getPhones()) {
                        aN(normalizedNumber.getNormalizedNumber());
                    }
                    j = c;
                    break;
                }
                break;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                j = readableDatabase.insert("phone_lookup", null, contentValues);
                break;
            case WindowData.d /*3*/:
                j = readableDatabase.insert("antispam_category", null, contentValues);
                break;
            case Base64.CRLF /*4*/:
                if (!contentValues.containsKey("number") || !contentValues.containsKey("normalized_number")) {
                    Log.d("YellowPageProvider", "The inserted antispam values must contains number and normalizedNumber");
                    break;
                }
                contentValues.put("min_match", PhoneNumberUtils.toCallerIDMinMatch(contentValues.getAsString("normalized_number")));
                j = readableDatabase.replace("antispam_number", null, contentValues);
                break;
                break;
            case WindowData.h /*7*/:
                j = readableDatabase.replace("provider", null, contentValues);
                break;
            case WindowData.j /*9*/:
                if (contentValues.getAsInteger("query_status").intValue() != 0) {
                    readableDatabase.replace("phone_usage", null, contentValues);
                    break;
                }
                readableDatabase.insertWithOnConflict("phone_usage", null, contentValues, 4);
                break;
            case WindowData.k /*10*/:
                j = readableDatabase.replace("t9_lookup", null, contentValues);
                break;
            case 11:
                j = readableDatabase.insert("data_status", null, contentValues);
                break;
            case 12:
                j = readableDatabase.replace("antispam_white_list", null, contentValues);
                break;
            case 14:
                j = readableDatabase.replace("cache", null, contentValues);
                break;
            case 18:
                j = readableDatabase.replace("cache", null, contentValues);
                getContext().getContentResolver().notifyChange(Navigation.CONTENT_URI, null);
                break;
            case 27:
                j = readableDatabase.insert("statistic", null, contentValues);
                break;
            case 31:
                j = readableDatabase.insert("permission", null, contentValues);
                break;
            case 40:
                CharSequence lastPathSegment = uri.getLastPathSegment();
                if (contentValues != null && TextUtils.equals("phone", lastPathSegment)) {
                    BusinessStatistics.viewYellowPageInPhoneCall(getContext(), contentValues.getAsString("number"), contentValues.getAsInteger(MiniDefine.m).intValue(), contentValues.getAsBoolean("hit").booleanValue(), contentValues.getAsString("yid"));
                    break;
                }
        }
        return ContentUris.withAppendedId(uri, j);
    }

    private void aN(String str) {
        if (td.get()) {
            te.put(str);
        }
    }

    private void aO(String str) {
        if (td.get()) {
            tf.put(str);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase readableDatabase = ta.getReadableDatabase();
        switch (sZ.match(uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return y.a(getContext(), readableDatabase, str, strArr);
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return readableDatabase.delete("phone_lookup", str, strArr);
            case WindowData.d /*3*/:
                return readableDatabase.delete("antispam_category", str, strArr);
            case Base64.CRLF /*4*/:
                return readableDatabase.delete("antispam_number", str, strArr);
            case WindowData.f /*5*/:
                Object lastPathSegment = uri.getLastPathSegment();
                if (TextUtils.isEmpty(lastPathSegment)) {
                    return 0;
                }
                readableDatabase.delete("antispam_number", a.b(getContext(), "normalized_number", lastPathSegment) + aM(str), strArr);
                return 0;
            case WindowData.h /*7*/:
                return readableDatabase.delete("provider", str, strArr);
            case WindowData.j /*9*/:
                return readableDatabase.delete("phone_usage", str, strArr);
            case WindowData.k /*10*/:
                return readableDatabase.delete("t9_lookup", str, strArr);
            case 11:
                return readableDatabase.delete("data_status", str, strArr);
            case 12:
                return readableDatabase.delete("antispam_white_list", str, strArr);
            case 14:
            case 18:
                return readableDatabase.delete("cache", str, strArr);
            case 27:
                return readableDatabase.delete("statistic", str, strArr);
            case 31:
                return readableDatabase.delete("permission", str, strArr);
            default:
                return 0;
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase readableDatabase = ta.getReadableDatabase();
        switch (sZ.match(uri)) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return readableDatabase.update("yellow_page", contentValues, str, strArr);
            case WindowData.d /*3*/:
                return readableDatabase.update("antispam_category", contentValues, str, strArr);
            case Base64.CRLF /*4*/:
                return readableDatabase.update("antispam_number", contentValues, str, strArr);
            case WindowData.h /*7*/:
                return readableDatabase.update("provider", contentValues, str, strArr);
            case WindowData.j /*9*/:
                return readableDatabase.update("phone_usage", contentValues, str, strArr);
            case 11:
                return readableDatabase.update("data_status", contentValues, str, strArr);
            case 14:
                return readableDatabase.update("cache", contentValues, str, strArr);
            case 18:
                int update = readableDatabase.update("cache", contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(Navigation.CONTENT_URI, null);
                return update;
            case 31:
                return readableDatabase.update("permission", contentValues, str, strArr);
            case 37:
                b.a(getContext(), readableDatabase, contentValues.getAsString("number"), contentValues.getAsInteger("categoryId").intValue(), contentValues.getAsBoolean("delete").booleanValue());
                return 0;
            case 49:
                if (!Permission.networkingAllowed(getContext())) {
                    return 0;
                }
                if (TextUtils.equals(MiniDefine.F, uri.getQueryParameter("forced"))) {
                    I.ap(getContext());
                }
                try {
                    I.ik().aj(getContext());
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            default:
                return 0;
        }
    }

    private int g(Uri uri) {
        if (uri == null) {
            return -1;
        }
        String uri2 = uri.toString();
        if (uri2.startsWith("content://miui.yellowpage/web/static")) {
            return 17;
        }
        if (uri2.startsWith("content://miui.yellowpage/web/service")) {
            return 16;
        }
        return sZ.match(uri);
    }

    public AssetFileDescriptor openAssetFile(Uri uri, String str) {
        AssetFileDescriptor assetFileDescriptor = null;
        Object lastPathSegment = uri.getLastPathSegment();
        if (!TextUtils.isEmpty(lastPathSegment)) {
            switch (g(uri)) {
                case Base64.URL_SAFE /*8*/:
                    assetFileDescriptor = a(e.y(getContext(), lastPathSegment), str);
                    break;
                case Base64.NO_CLOSE /*16*/:
                case 20:
                case 21:
                    assetFileDescriptor = a(openPipeHelper(uri, null, null, null, new r()), -1);
                    break;
                case 17:
                    assetFileDescriptor = a(new File(I.H(getContext(), uri.toString())), str);
                    break;
                default:
                    Log.e("YellowPageProvider", "Not supported uri " + uri);
                    break;
            }
            if (assetFileDescriptor == null) {
                throw new FileNotFoundException("cannot find " + uri);
            }
        }
        return assetFileDescriptor;
    }

    private AssetFileDescriptor a(File file, String str) {
        if (TextUtils.equals("w", str)) {
            return a(ParcelFileDescriptor.open(file, 939524096), -1);
        }
        if (file.exists()) {
            return a(ParcelFileDescriptor.open(file, 268435456), -1);
        }
        return null;
    }

    private AssetFileDescriptor a(ParcelFileDescriptor parcelFileDescriptor, long j) {
        return parcelFileDescriptor != null ? new AssetFileDescriptor(parcelFileDescriptor, 0, j) : null;
    }

    private Location getLocation() {
        return this.tb.getLocation();
    }

    private String getUserAreaCode() {
        Object lastKnownAreaCode = this.tb.getLastKnownAreaCode();
        if (TextUtils.isEmpty(lastKnownAreaCode)) {
            ThreadPool.execute(new u(this));
        }
        return lastKnownAreaCode;
    }

    private void a(Context context, SQLiteDatabase sQLiteDatabase) {
        Log.d("YellowPageProvider", "retrieveHotWords");
        ThreadPool.execute(new t(this, context, sQLiteDatabase));
    }

    private void b(Context context, SQLiteDatabase sQLiteDatabase) {
        Log.d("YellowPageProvider", "retrieveProfileData");
        ThreadPool.execute(new w(this, sQLiteDatabase, context));
    }

    private void c(Context context, SQLiteDatabase sQLiteDatabase) {
        Log.d("YellowPageProvider", "retrieveNavigationData");
        ThreadPool.execute(new v(this, sQLiteDatabase, context));
    }

    private void d(Context context, SQLiteDatabase sQLiteDatabase) {
        Log.d("YellowPageProvider", "retrieveSearchTipsData");
        ThreadPool.execute(new x(this, sQLiteDatabase, context));
    }

    private JSONRequest b(String str, String str2, String str3, String str4) {
        JSONRequest jSONRequest = new JSONRequest(getContext(), HostManager.getNavigationSearchUrl());
        jSONRequest.addParam("keyword", str);
        String str5 = "pn";
        if (TextUtils.isEmpty(str2)) {
            str2 = Profile.devicever;
        }
        jSONRequest.addParam(str5, str2);
        if (!TextUtils.isEmpty(str3)) {
            jSONRequest.addParam("destlocid", str3);
        }
        a(jSONRequest);
        Location location = getLocation();
        if (location != null) {
            Object locId = getLocId();
            if (!TextUtils.isEmpty(locId)) {
                jSONRequest.addParam("locid", locId);
                jSONRequest.addParam("latitude", String.valueOf(location.getLatitude()));
                jSONRequest.addParam("longitude", String.valueOf(location.getLongitude()));
                jSONRequest.addParam("sugidx", String.valueOf(str4));
                jSONRequest.addParam("timestamp", String.valueOf(System.currentTimeMillis()));
                jSONRequest.addParam("locpackage", location.getProvider());
            }
        }
        return jSONRequest;
    }

    private String getLocId() {
        Log.d("YellowPageProvider", "getLocId");
        City city = this.tb.getCity();
        return city == null ? null : city.getLocId();
    }

    private void a(JSONRequest jSONRequest) {
        int simCount = Sim.getSimCount(getContext());
        String simOpCode;
        String simAreaCode;
        if (simCount == 2) {
            simOpCode = Sim.getSimOpCode(getContext(), 0);
            simAreaCode = Sim.getSimAreaCode(getContext(), 0);
            jSONRequest.addParam("msimop", simOpCode);
            jSONRequest.addParam("msimarea", simAreaCode);
            simOpCode = Sim.getSimOpCode(getContext(), 1);
            simAreaCode = Sim.getSimAreaCode(getContext(), 1);
            jSONRequest.addParam("ssimop", simOpCode);
            jSONRequest.addParam("ssimarea", simAreaCode);
        } else if (simCount == 1) {
            simCount = Sim.getReadySimIndexOnOneInserted(getContext());
            simAreaCode = Sim.getSimOpCode(getContext(), simCount);
            simOpCode = Sim.getSimAreaCode(getContext(), simCount);
            jSONRequest.addParam("msimop", simAreaCode);
            jSONRequest.addParam("msimarea", simOpCode);
        }
    }

    private void b(JSONRequest jSONRequest) {
        Object locId = getLocId();
        if (!TextUtils.isEmpty(locId)) {
            jSONRequest.addParam("locid", locId);
        }
    }

    private void a(MatrixCursor matrixCursor, JSONObject jSONObject) {
        if (jSONObject.has("service")) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("service");
                for (int i = 0; i < jSONArray.length(); i++) {
                    matrixCursor.addRow(new Object[]{jSONArray.getJSONObject(i).toString(), String.valueOf(1)});
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private int b(MatrixCursor matrixCursor, JSONObject jSONObject) {
        JSONException jSONException;
        int i = 4;
        if (!jSONObject.has("yellowpage")) {
            return i;
        }
        int i2;
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("yellowpage");
            if (jSONArray.length() <= 0) {
                return 2;
            }
            i2 = 0;
            while (i2 < jSONArray.length()) {
                try {
                    matrixCursor.addRow(new Object[]{jSONArray.getJSONObject(i2).toString(), String.valueOf(0)});
                    i2++;
                } catch (JSONException e) {
                    JSONException jSONException2 = e;
                    i2 = 1;
                    jSONException = jSONException2;
                }
            }
            return 1;
        } catch (JSONException e2) {
            jSONException = e2;
            i2 = i;
            jSONException.printStackTrace();
            return i2;
        }
    }

    private Cursor a(SQLiteDatabase sQLiteDatabase, String[] strArr, String str) {
        RandomAccessFile randomAccessFile;
        IOException e;
        i M;
        NumberFormatException e2;
        Throwable th;
        if (J.av(getContext()) || J.aw(getContext())) {
            String normalizedNumber = o.getNormalizedNumber(getContext(), str);
            try {
                randomAccessFile = new RandomAccessFile(J.as(getContext()), "r");
                try {
                    long M2 = J.M(getContext());
                    if (tc != M2) {
                        if (d.a(randomAccessFile)) {
                            tc = M2;
                        } else {
                            Log.d("YellowPageProvider", "failed to init");
                            b.a(sQLiteDatabase, str, normalizedNumber, null);
                            aO(normalizedNumber);
                            if (randomAccessFile == null) {
                                return null;
                            }
                            try {
                                randomAccessFile.close();
                                return null;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                return null;
                            }
                        }
                    }
                    M = new d(randomAccessFile).M(normalizedNumber);
                    try {
                        Log.d("YellowPageProvider", "tuple:" + M);
                        if (M == null) {
                            b.a(sQLiteDatabase, str, normalizedNumber, M);
                            aO(normalizedNumber);
                            if (randomAccessFile == null) {
                                return null;
                            }
                            try {
                                randomAccessFile.close();
                                return null;
                            } catch (IOException e32) {
                                e32.printStackTrace();
                                return null;
                            }
                        }
                        int parseInt = Integer.parseInt(M.getCategory());
                        if (parseInt == 0) {
                            Log.d("YellowPageProvider", "dummy feed");
                            b.a(sQLiteDatabase, str, normalizedNumber, M);
                            aO(normalizedNumber);
                            if (randomAccessFile == null) {
                                return null;
                            }
                            try {
                                randomAccessFile.close();
                                return null;
                            } catch (IOException e322) {
                                e322.printStackTrace();
                                return null;
                            }
                        }
                        MatrixCursor matrixCursor = new MatrixCursor(strArr);
                        Object[] objArr = new Object[strArr.length];
                        for (int i = 0; i < strArr.length; i++) {
                            Object obj = strArr[i];
                            if (TextUtils.equals(obj, "_id")) {
                                objArr[i] = Integer.valueOf(0);
                            } else if (TextUtils.equals(obj, "marked_count")) {
                                objArr[i] = Integer.valueOf(Integer.parseInt(M.gO()));
                            } else if (TextUtils.equals(obj, "normalized_number")) {
                                objArr[i] = normalizedNumber;
                            } else if (TextUtils.equals(obj, "min_match")) {
                                objArr[i] = PhoneNumberUtils.toCallerIDMinMatch(normalizedNumber);
                            } else if (TextUtils.equals(obj, "number")) {
                                objArr[i] = normalizedNumber;
                            } else if (TextUtils.equals(obj, "cid")) {
                                objArr[i] = Integer.valueOf(parseInt);
                            } else if (TextUtils.equals(obj, "pid")) {
                                objArr[i] = Integer.valueOf(Integer.parseInt(M.getProvider()));
                            } else if (TextUtils.equals(obj, MiniDefine.m)) {
                                objArr[i] = Integer.valueOf(1);
                            } else {
                                throw new IllegalArgumentException("unknown column:" + obj);
                            }
                        }
                        matrixCursor.addRow(objArr);
                        b.a(sQLiteDatabase, str, normalizedNumber, M);
                        aO(normalizedNumber);
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        return matrixCursor;
                    } catch (IOException e5) {
                        e322 = e5;
                    } catch (NumberFormatException e6) {
                        e2 = e6;
                    }
                } catch (IOException e7) {
                    e322 = e7;
                    M = null;
                    try {
                        e322.printStackTrace();
                        b.a(sQLiteDatabase, str, normalizedNumber, M);
                        aO(normalizedNumber);
                        if (randomAccessFile != null) {
                            return null;
                        }
                        try {
                            randomAccessFile.close();
                            return null;
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                            return null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        b.a(sQLiteDatabase, str, normalizedNumber, M);
                        aO(normalizedNumber);
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e32222) {
                                e32222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (NumberFormatException e8) {
                    e2 = e8;
                    M = null;
                    e2.printStackTrace();
                    b.a(sQLiteDatabase, str, normalizedNumber, M);
                    aO(normalizedNumber);
                    if (randomAccessFile != null) {
                        return null;
                    }
                    try {
                        randomAccessFile.close();
                        return null;
                    } catch (IOException e322222) {
                        e322222.printStackTrace();
                        return null;
                    }
                } catch (Throwable th3) {
                    M = null;
                    th = th3;
                    b.a(sQLiteDatabase, str, normalizedNumber, M);
                    aO(normalizedNumber);
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            } catch (IOException e9) {
                e322222 = e9;
                M = null;
                randomAccessFile = null;
                e322222.printStackTrace();
                b.a(sQLiteDatabase, str, normalizedNumber, M);
                aO(normalizedNumber);
                if (randomAccessFile != null) {
                    return null;
                }
                randomAccessFile.close();
                return null;
            } catch (NumberFormatException e10) {
                e2 = e10;
                M = null;
                randomAccessFile = null;
                e2.printStackTrace();
                b.a(sQLiteDatabase, str, normalizedNumber, M);
                aO(normalizedNumber);
                if (randomAccessFile != null) {
                    return null;
                }
                randomAccessFile.close();
                return null;
            } catch (Throwable th32) {
                M = null;
                randomAccessFile = null;
                th = th32;
                b.a(sQLiteDatabase, str, normalizedNumber, M);
                aO(normalizedNumber);
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        }
        Log.d("YellowPageProvider", "failed to import cached antispam data");
        return null;
    }
}
