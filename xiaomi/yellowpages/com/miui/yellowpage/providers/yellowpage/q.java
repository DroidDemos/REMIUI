package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.sync.c;
import com.miui.yellowpage.utils.J;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import miui.yellowpage.YellowPagePhone;

/* compiled from: YellowPageDatabaseHelper */
public class q extends SQLiteOpenHelper {
    private static q GQ;
    private boolean GR;
    private boolean GS;
    private boolean GT;
    private boolean GU;
    private boolean GV;
    private boolean GW;
    private boolean GX;
    private boolean GY;
    private boolean GZ;
    protected Context mContext;

    public q(Context context) {
        this(context, "yellowpage.db", 47);
        this.mContext = context;
    }

    public q(Context context, String str, int i) {
        super(context, str, null, i);
        this.mContext = context;
    }

    public static synchronized q ad(Context context) {
        q qVar;
        synchronized (q.class) {
            if (GQ == null) {
                GQ = new q(context);
            }
            qVar = GQ;
        }
        return qVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        X(sQLiteDatabase);
        Z(sQLiteDatabase);
        ab(sQLiteDatabase);
        ac(sQLiteDatabase);
        aa(sQLiteDatabase);
        ad(sQLiteDatabase);
        ae(sQLiteDatabase);
        af(sQLiteDatabase);
        ag(sQLiteDatabase);
        ah(sQLiteDatabase);
        Y(sQLiteDatabase);
        ai(sQLiteDatabase);
        aj(sQLiteDatabase);
        ak(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        int i3;
        if (i == 1) {
            d(sQLiteDatabase);
            i3 = i + 1;
        } else {
            i3 = i;
        }
        if (i3 == 2) {
            e(sQLiteDatabase);
            i3++;
        }
        if (i3 == 3) {
            f(sQLiteDatabase);
            i3++;
        }
        if (i3 == 4) {
            g(sQLiteDatabase);
            i3++;
        }
        if (i3 == 5) {
            h(sQLiteDatabase);
            i3++;
        }
        if (i3 == 6) {
            i(sQLiteDatabase);
            i3++;
        }
        if (i3 == 7) {
            j(sQLiteDatabase);
            i3++;
        }
        if (i3 == 8) {
            k(sQLiteDatabase);
            i3++;
        }
        if (i3 == 9) {
            l(sQLiteDatabase);
            i3++;
        }
        if (i3 == 10) {
            m(sQLiteDatabase);
            i3++;
        }
        if (i3 == 11) {
            n(sQLiteDatabase);
            i3++;
        }
        if (i3 == 12) {
            o(sQLiteDatabase);
            i3++;
        }
        if (i3 == 13) {
            p(sQLiteDatabase);
            i3++;
        }
        if (i3 == 14) {
            q(sQLiteDatabase);
            i3++;
        }
        if (i3 == 15) {
            r(sQLiteDatabase);
            i3++;
        }
        if (i3 == 16) {
            s(sQLiteDatabase);
            i3++;
        }
        if (i3 == 17) {
            t(sQLiteDatabase);
            i3++;
        }
        if (i3 == 18) {
            u(sQLiteDatabase);
            i3++;
        }
        if (i3 == 19) {
            v(sQLiteDatabase);
            i3++;
        }
        if (i3 == 20) {
            w(sQLiteDatabase);
            i3++;
        }
        if (i3 == 21) {
            x(sQLiteDatabase);
            i3++;
        }
        if (i3 == 22) {
            y(sQLiteDatabase);
            i3++;
        }
        if (i3 == 23) {
            z(sQLiteDatabase);
            i3++;
        }
        if (i3 == 24) {
            A(sQLiteDatabase);
            i3++;
        }
        if (i3 == 25) {
            B(sQLiteDatabase);
            i3++;
        }
        if (i3 == 26) {
            C(sQLiteDatabase);
            i3++;
        }
        if (i3 == 27) {
            D(sQLiteDatabase);
            i3++;
        }
        if (i3 == 28) {
            E(sQLiteDatabase);
            i3++;
        }
        if (i3 == 29) {
            F(sQLiteDatabase);
            i3++;
        }
        if (i3 == 30) {
            G(sQLiteDatabase);
            i3++;
        }
        if (i3 == 31) {
            H(sQLiteDatabase);
            i3++;
        }
        if (i3 == 32) {
            I(sQLiteDatabase);
            i3++;
        }
        if (i3 == 33) {
            J(sQLiteDatabase);
            i3++;
        }
        if (i3 == 34) {
            K(sQLiteDatabase);
            i3++;
        }
        if (i3 == 35) {
            L(sQLiteDatabase);
            i3++;
        }
        if (i3 == 36) {
            M(sQLiteDatabase);
            i3++;
        }
        if (i3 == 37) {
            N(sQLiteDatabase);
            i3++;
        }
        if (i3 == 38) {
            O(sQLiteDatabase);
            i3++;
        }
        if (i3 == 39) {
            P(sQLiteDatabase);
            i3++;
        }
        if (i3 == 40) {
            Q(sQLiteDatabase);
            i3++;
        }
        if (i3 == 41) {
            R(sQLiteDatabase);
            i3++;
        }
        if (i3 == 42) {
            S(sQLiteDatabase);
            i3++;
        }
        if (i3 == 43) {
            T(sQLiteDatabase);
            i3++;
        }
        if (i3 == 44) {
            U(sQLiteDatabase);
            i3++;
        }
        if (i3 == 45) {
            V(sQLiteDatabase);
            i3++;
        }
        if (i3 == 46) {
            W(sQLiteDatabase);
            i3++;
        }
        if (this.GR) {
            am(sQLiteDatabase);
        }
        if (this.GT) {
            ap(sQLiteDatabase);
        }
        if (this.GU) {
            ao(sQLiteDatabase);
        }
        if (this.GV) {
            an(sQLiteDatabase);
        }
        if (this.GW) {
            al(sQLiteDatabase);
        }
        if (this.GX) {
            as(sQLiteDatabase);
        }
        if (this.GY) {
            ar(sQLiteDatabase);
        }
        if (this.GZ) {
            aq(sQLiteDatabase);
        }
        if (this.GS) {
            hK();
        }
        if (i3 != i2) {
            Log.e("YellowPageDatabaseHelper", "upgrade failed, oldVersion:" + i3 + ", newVersion:" + i2);
        } else {
            Log.d("YellowPageDatabaseHelper", "upgraded to version:" + i2);
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        this.GR = true;
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        this.GR = true;
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE provider ADD COLUMN icon_big BLOB");
        sQLiteDatabase.execSQL("ALTER TABLE phone_lookup ADD COLUMN normalized_number TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE antispam_number ADD COLUMN normalized_number TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE phone_lookup ADD COLUMN hide INTEGER NOT NULL DEFAULT 0");
        this.GV = true;
        this.GW = true;
        this.GR = true;
    }

    private void g(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS antispam_white_list");
        sQLiteDatabase.execSQL("CREATE TABLE antispam_white_list(number TEXT PRIMARY KEY);");
        this.GU = true;
    }

    private void h(SQLiteDatabase sQLiteDatabase) {
        this.GV = true;
    }

    private void i(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE phone_usage ADD COLUMN query_114_status INTEGER NOT NULL DEFAULT 0");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache");
        sQLiteDatabase.execSQL("CREATE TABLE cache (cache_key TEXT PRIMARY KEY, content TEXT NOT NULL, etag TEXT, remark TEXT)");
    }

    private void j(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE phone_usage ADD COLUMN update_time INTEGER NOT NULL DEFAULT 0");
    }

    private void k(SQLiteDatabase sQLiteDatabase) {
        this.GV = true;
    }

    private void l(SQLiteDatabase sQLiteDatabase) {
    }

    private void m(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE antispam_category ADD COLUMN type INTEGER NOT NULL DEFAULT 0");
    }

    private void n(SQLiteDatabase sQLiteDatabase) {
        this.GR = true;
    }

    private void o(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE yellow_page ADD COLUMN update_time INTEGER NOT NULL DEFAULT 0");
    }

    private void p(SQLiteDatabase sQLiteDatabase) {
        ThreadPool.execute(new f(this, sQLiteDatabase));
    }

    private void q(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE phone_lookup ADD COLUMN suspect INTEGER NOT NULL DEFAULT 0");
    }

    private void r(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE yellow_page ADD COLUMN hotCatId TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE yellow_page ADD COLUMN hotSort INTEGER NOT NULL DEFAULT 0");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS yellowpage_lookup_hot_category_id ON yellow_page (hotCatId);");
        this.GT = true;
        this.GS = true;
        this.GR = true;
    }

    private void s(SQLiteDatabase sQLiteDatabase) {
        this.GS = true;
    }

    private void t(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS statistic");
        sQLiteDatabase.execSQL("CREATE TABLE statistic(_id INTEGER PRIMARY KEY,date INTEGER NOT NULL,data TEXT NOT NULL);");
    }

    private void u(SQLiteDatabase sQLiteDatabase) {
        this.GR = true;
    }

    private void v(SQLiteDatabase sQLiteDatabase) {
        this.GR = true;
    }

    private void w(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE yellow_page ADD COLUMN last_use_time INTEGER NOT NULL DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE yellow_page ADD COLUMN favorite INTEGER NOT NULL DEFAULT 0");
    }

    private void x(SQLiteDatabase sQLiteDatabase) {
        this.GT = true;
    }

    private void y(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE cache ADD COLUMN account_id TEXT");
    }

    private void z(SQLiteDatabase sQLiteDatabase) {
        this.GS = true;
    }

    private void A(SQLiteDatabase sQLiteDatabase) {
        this.GS = true;
    }

    private void B(SQLiteDatabase sQLiteDatabase) {
        this.GS = true;
        Preference.setBoolean(this.mContext, "pref_show_user_notice_yp_detail", Preference.getBoolean(this.mContext, "pref_show_user_notice_yp_detail", PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean("pref_show_user_notice_yp_detail", false)));
    }

    private void C(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region");
        sQLiteDatabase.execSQL("CREATE TABLE region ( _id INTEGER PRIMARY KEY, name TEXT NOT NULL, parent INTEGER, postal_code TEXT, type INTEGER DEFAULT 0)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS region_id_index ON region (_id);");
        this.GX = true;
    }

    private void D(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE yellow_page ADD COLUMN miid TEXT");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS miid_lookup ON yellow_page (miid);");
    }

    private void E(SQLiteDatabase sQLiteDatabase) {
    }

    private void F(SQLiteDatabase sQLiteDatabase) {
    }

    private void G(SQLiteDatabase sQLiteDatabase) {
    }

    private void H(SQLiteDatabase sQLiteDatabase) {
    }

    private void I(SQLiteDatabase sQLiteDatabase) {
    }

    private void J(SQLiteDatabase sQLiteDatabase) {
        this.GT = true;
        this.GY = true;
        this.GS = true;
    }

    private void K(SQLiteDatabase sQLiteDatabase) {
        if (!b(sQLiteDatabase, "phone_lookup", "call_menu")) {
            sQLiteDatabase.execSQL("ALTER TABLE phone_lookup ADD COLUMN call_menu INTEGER NOT NULL DEFAULT 0");
        }
    }

    private void L(SQLiteDatabase sQLiteDatabase) {
        this.GY = true;
    }

    private void M(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE cache ADD COLUMN remark TEXT");
    }

    private void N(SQLiteDatabase sQLiteDatabase) {
        if (!b(sQLiteDatabase, "cache", "remark")) {
            sQLiteDatabase.execSQL("ALTER TABLE cache ADD COLUMN remark TEXT");
        }
    }

    private void O(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE t9_lookup ADD COLUMN t9_rank INTEGER NOT NULL DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE phone_lookup ADD COLUMN t9_rank INTEGER NOT NULL DEFAULT 0");
    }

    private void P(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS permission");
        sQLiteDatabase.execSQL("CREATE TABLE permission(host TEXT NOT NULL,permission TEXT NOT NULL,state INTEGER DEFAULT 0,PRIMARY KEY (host,permission))");
    }

    private void Q(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE yellow_page ADD COLUMN subscribe_stats INTEGER NOT NULL DEFAULT 2");
    }

    private void R(SQLiteDatabase sQLiteDatabase) {
        this.GS = true;
    }

    private void S(SQLiteDatabase sQLiteDatabase) {
        c.z(this.mContext);
        c.y(this.mContext);
    }

    private void T(SQLiteDatabase sQLiteDatabase) {
        this.GT = true;
        this.GZ = true;
        this.GS = true;
    }

    private void U(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE phone_lookup ADD COLUMN atd_category_id INTEGER NOT NULL DEFAULT 0");
    }

    private void V(SQLiteDatabase sQLiteDatabase) {
        c.z(this.mContext);
        c.y(this.mContext);
    }

    private void W(SQLiteDatabase sQLiteDatabase) {
        J.au(this.mContext);
    }

    private static boolean b(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        Cursor query = sQLiteDatabase.query(str, null, null, null, null, null, null, GlobalConstants.d);
        if (query == null) {
            return false;
        }
        try {
            boolean z;
            for (String equalsIgnoreCase : query.getColumnNames()) {
                if (str2.equalsIgnoreCase(equalsIgnoreCase)) {
                    z = true;
                    break;
                }
            }
            z = false;
            query.close();
            return z;
        } catch (Throwable th) {
            query.close();
        }
    }

    private void hK() {
        ThreadPool.execute(new g(this));
    }

    private void X(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS yellow_page");
        sQLiteDatabase.execSQL("CREATE TABLE yellow_page(yid INTEGER PRIMARY KEY,content TEXT NOT NULL,miid TEXT,hotSort INTEGER NOT NULL DEFAULT 0,update_time INTEGER NOT NULL DEFAULT 0,last_use_time INTEGER NOT NULL DEFAULT 0,favorite INTEGER NOT NULL DEFAULT 0,hotCatId TEXT,subscribe_stats INTEGER NOT NULL DEFAULT 2,type INTEGER NOT NULL DEFAULT 3);");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS category_id_lookup ON yellow_page (hotCatId);");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS miid_lookup ON yellow_page (miid);");
    }

    private void Y(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS statistic");
        sQLiteDatabase.execSQL("CREATE TABLE statistic(_id INTEGER PRIMARY KEY,date INTEGER NOT NULL,data TEXT NOT NULL);");
    }

    private void Z(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS phone_lookup");
        sQLiteDatabase.execSQL("CREATE TABLE phone_lookup(number TEXT PRIMARY KEY,min_match TEXT NOT NULL,normalized_number TEXT NOT NULL,tag TEXT NOT NULL,yellow_page_name TEXT NOT NULL,photo_url TEXT,thumbnail_url TEXT,yellow_page_name_pinyin TEXT NOT NULL,tag_pinyin TEXT NOT NULL,hide INTEGER NOT NULL DEFAULT 0,suspect INTEGER NOT NULL DEFAULT 0,call_menu INTEGER NOT NULL DEFAULT 0,t9_rank INTEGER NOT NULL DEFAULT 0,atd_category_id INTEGER NOT NULL DEFAULT 0,provider_id INTEGER NOT NULL DEFAULT 0,yid INTEGER NOT NULL DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS phone_lookup_min_match ON phone_lookup (min_match);");
    }

    private void aa(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS antispam_category");
        sQLiteDatabase.execSQL("CREATE TABLE antispam_category(cid INTEGER PRIMARY KEY,type INTEGER NOT NULL DEFAULT 0,names TEXT NOT NULL);");
    }

    private void ab(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS antispam_number");
        sQLiteDatabase.execSQL("CREATE TABLE antispam_number(_id INTEGER PRIMARY KEY,number TEXT NOT NULL,min_match TEXT NOT NULL,normalized_number TEXT NOT NULL,pid INTEGER NOT NULL DEFAULT 0,upload INTEGER NOT NULL DEFAULT 0,marked_count INTEGER NOT NULL DEFAULT 0,type INTEGER NOT NULL DEFAULT 3,cid INTEGER NOT NULL DEFAULT 0,UNIQUE(number,type));");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS antispam_phone ON antispam_number (number);");
    }

    private void ac(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS provider");
        sQLiteDatabase.execSQL("CREATE TABLE provider(pid INTEGER PRIMARY KEY,name TEXT NOT NULL,icon BLOB,icon_big BLOB,icon_url TEXT);");
    }

    private void ad(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS phone_usage");
        sQLiteDatabase.execSQL("CREATE TABLE phone_usage(number TEXT PRIMARY KEY,action TEXT,query_114_status INTEGER NOT NULL DEFAULT 0,update_time INTEGER NOT NULL DEFAULT 0,query_status INTEGER NOT NULL DEFAULT 0);");
    }

    private void ae(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS t9_lookup");
        sQLiteDatabase.execSQL("CREATE TABLE t9_lookup(number TEXT PRIMARY KEY,name TEXT NOT NULL,tag TEXT NOT NULL,pinyin TEXT NOT NULL,t9_rank INTEGER NOT NULL DEFAULT 0,yid INTEGER NOT NULL DEFAULT 0);");
    }

    private void af(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS data_status");
        sQLiteDatabase.execSQL("CREATE TABLE data_status(_id INTEGER PRIMARY KEY, mime_type TEXT NOT NULL, data TEXT NOT NULL);");
    }

    private void ag(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS antispam_white_list");
        sQLiteDatabase.execSQL("CREATE TABLE antispam_white_list(number TEXT PRIMARY KEY);");
    }

    private void ah(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache");
        sQLiteDatabase.execSQL("CREATE TABLE cache (cache_key TEXT PRIMARY KEY, content TEXT NOT NULL, account_id TEXT, remark TEXT,etag TEXT)");
    }

    private void ai(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region");
        sQLiteDatabase.execSQL("CREATE TABLE region ( _id INTEGER PRIMARY KEY, name TEXT NOT NULL, parent INTEGER, postal_code TEXT, type INTEGER DEFAULT 0)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS region_id_index ON region (_id);");
    }

    private void aj(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS permission");
        sQLiteDatabase.execSQL("CREATE TABLE permission(host TEXT NOT NULL,permission TEXT NOT NULL,state INTEGER DEFAULT 0,PRIMARY KEY (host,permission))");
    }

    private void ak(SQLiteDatabase sQLiteDatabase) {
        if (p.hz()) {
            al(sQLiteDatabase);
            an(sQLiteDatabase);
            am(sQLiteDatabase);
            ao(sQLiteDatabase);
            hK();
            ap(sQLiteDatabase);
            as(sQLiteDatabase);
            ar(sQLiteDatabase);
            aq(sQLiteDatabase);
        }
    }

    private void al(SQLiteDatabase sQLiteDatabase) {
        ThreadPool.execute(new h(this, sQLiteDatabase));
    }

    private void am(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        Throwable th;
        Log.d("YellowPageDatabaseHelper", "start importing yellow page");
        BufferedReader bufferedReader;
        try {
            sQLiteDatabase.execSQL("DELETE FROM t9_lookup WHERE yid IN (SELECT yid FROM yellow_page WHERE type = 1)");
            sQLiteDatabase.execSQL("DELETE FROM yellow_page WHERE type <> 3");
            bufferedReader = new BufferedReader(new InputStreamReader(this.mContext.getResources().openRawResource(R.raw.yellow_pages)));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    c(sQLiteDatabase, "yellow_page_v2", readLine.split(":")[1]);
                }
                while (true) {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    YellowPage fromJson = YellowPage.fromJson(readLine);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("yid", Long.valueOf(fromJson.getId()));
                    contentValues.put("miid", fromJson.getMiId());
                    contentValues.put(MiniDefine.at, readLine);
                    contentValues.put(MiniDefine.m, Integer.valueOf(fromJson.isHot() ? 1 : 2));
                    contentValues.put("hotCatId", fromJson.getHotCatId());
                    contentValues.put("hotSort", Integer.valueOf(fromJson.getHotSort()));
                    sQLiteDatabase.insert("yellow_page", null, contentValues);
                    a.a(sQLiteDatabase, fromJson);
                    if (fromJson.isHot()) {
                        List<YellowPagePhone> phones = fromJson.getPhones();
                        if (phones != null && phones.size() > 0) {
                            for (YellowPagePhone yellowPagePhone : phones) {
                                if (yellowPagePhone.isVisible()) {
                                    ContentValues contentValues2 = new ContentValues();
                                    CharSequence yellowPageName = yellowPagePhone.getYellowPageName();
                                    Object tag = yellowPagePhone.getTag();
                                    contentValues2.put(MiniDefine.l, yellowPageName);
                                    contentValues2.put("number", yellowPagePhone.getNumber());
                                    contentValues2.put("yid", Long.valueOf(yellowPagePhone.getYellowPageId()));
                                    contentValues2.put(MiniDefine.aq, fromJson.getPinyin());
                                    contentValues2.put("t9_rank", Long.valueOf(yellowPagePhone.getT9Rank()));
                                    if (TextUtils.equals(yellowPageName, tag)) {
                                        contentValues2.put("tag", ConfigConstant.WIRELESS_FILENAME);
                                    } else {
                                        contentValues2.put("tag", tag);
                                    }
                                    sQLiteDatabase.insert("t9_lookup", null, contentValues2);
                                }
                            }
                            continue;
                        }
                    }
                }
                ThreadPool.execute(new n(this));
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
            }
        } catch (Exception e4) {
            e2 = e4;
            bufferedReader = null;
            try {
                e2.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                }
                Log.d("YellowPageDatabaseHelper", "finished importing yellow page");
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        Log.d("YellowPageDatabaseHelper", "finished importing yellow page");
    }

    protected void c(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        sQLiteDatabase.delete("data_status", "mime_type=? AND data LIKE ?", new String[]{"version", str + "%"});
        ContentValues contentValues = new ContentValues();
        contentValues.put("mime_type", "version");
        contentValues.put("data", str + ":" + str2);
        sQLiteDatabase.insert("data_status", null, contentValues);
    }

    private void an(SQLiteDatabase sQLiteDatabase) {
        ThreadPool.execute(new m(this, sQLiteDatabase));
    }

    private void ao(SQLiteDatabase sQLiteDatabase) {
        ThreadPool.execute(new l(this, sQLiteDatabase));
    }

    private void ap(SQLiteDatabase sQLiteDatabase) {
        ThreadPool.execute(new k(this, sQLiteDatabase));
    }

    private void aq(SQLiteDatabase sQLiteDatabase) {
        ThreadPool.execute(new j(this, sQLiteDatabase));
    }

    private byte[] cj(String str) {
        InputStream openRawResource;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            openRawResource = this.mContext.getResources().openRawResource(this.mContext.getResources().getIdentifier(str, "raw", this.mContext.getPackageName()));
            try {
                byte[] bArr2 = new byte[1024];
                byteArrayOutputStream = bArr;
                while (true) {
                    try {
                        int read = openRawResource.read(bArr2);
                        if (read <= 0) {
                            break;
                        }
                        if (byteArrayOutputStream == null) {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                bArr = byteArrayOutputStream.toByteArray();
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e32) {
                        e32.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                e32 = e4;
                byteArrayOutputStream = bArr;
                try {
                    e32.printStackTrace();
                    if (openRawResource != null) {
                        try {
                            openRawResource.close();
                        } catch (Exception e322) {
                            e322.printStackTrace();
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e3222) {
                            e3222.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (openRawResource != null) {
                        try {
                            openRawResource.close();
                        } catch (Exception e32222) {
                            e32222.printStackTrace();
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e322222) {
                            e322222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                byteArrayOutputStream = bArr;
                th = th3;
                if (openRawResource != null) {
                    openRawResource.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            e322222 = e5;
            byteArrayOutputStream = bArr;
            openRawResource = bArr;
            e322222.printStackTrace();
            if (openRawResource != null) {
                openRawResource.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return bArr;
        } catch (Throwable th32) {
            byteArrayOutputStream = bArr;
            openRawResource = bArr;
            th = th32;
            if (openRawResource != null) {
                openRawResource.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
        return bArr;
    }

    private void ar(SQLiteDatabase sQLiteDatabase) {
        ThreadPool.execute(new i(this, sQLiteDatabase));
    }

    private void as(SQLiteDatabase sQLiteDatabase) {
        ThreadPool.execute(new d(this, sQLiteDatabase));
    }
}
