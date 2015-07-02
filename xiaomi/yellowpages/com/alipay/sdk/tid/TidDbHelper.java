package com.alipay.sdk.tid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.encrypt.Des;
import com.alipay.sdk.util.DeviceInfo;
import com.alipay.sdk.util.LogUtils;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

final class TidDbHelper extends SQLiteOpenHelper {
    private static final String a = "msp.db";
    private static final int b = 1;
    private WeakReference c;

    public TidDbHelper(Context context) {
        super(context, a, null, b);
        this.c = new WeakReference(context);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
        onCreate(sQLiteDatabase);
    }

    public void a(String str, String str2, String str3, String str4) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            if (a(sQLiteDatabase, str, str2)) {
                b(sQLiteDatabase, str, str2, str3, str4);
            } else {
                a(sQLiteDatabase, str, str2, str3, str4);
            }
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Object e) {
            LogUtils.a(e);
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        }
    }

    public void a(String str, String str2) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            b(sQLiteDatabase, str, str2, ConfigConstant.WIRELESS_FILENAME, ConfigConstant.WIRELESS_FILENAME);
            a(sQLiteDatabase, e(str, str2));
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Exception e) {
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        }
    }

    public String b(String str, String str2) {
        Throwable th;
        String str3 = null;
        String str4 = "select tid from tb_tid where name=?";
        SQLiteDatabase readableDatabase;
        Cursor rawQuery;
        try {
            readableDatabase = getReadableDatabase();
            try {
                String[] strArr = new String[b];
                strArr[0] = e(str, str2);
                rawQuery = readableDatabase.rawQuery(str4, strArr);
                try {
                    if (rawQuery.moveToFirst()) {
                        str3 = rawQuery.getString(0);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase == null || !readableDatabase.isOpen()) {
                        str4 = str3;
                    } else {
                        readableDatabase.close();
                        str4 = str3;
                    }
                } catch (Exception e) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase == null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                        str4 = null;
                    } else {
                        str4 = null;
                    }
                    if (TextUtils.isEmpty(str4)) {
                        return str4;
                    }
                    return Des.b(str4, DeviceInfo.d((Context) this.c.get()));
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase == null) {
                }
                str4 = null;
                if (TextUtils.isEmpty(str4)) {
                    return str4;
                }
                return Des.b(str4, DeviceInfo.d((Context) this.c.get()));
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            rawQuery = null;
            readableDatabase = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase == null) {
            }
            str4 = null;
            if (TextUtils.isEmpty(str4)) {
                return Des.b(str4, DeviceInfo.d((Context) this.c.get()));
            }
            return str4;
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            readableDatabase.close();
            throw th;
        }
        if (TextUtils.isEmpty(str4)) {
            return Des.b(str4, DeviceInfo.d((Context) this.c.get()));
        }
        return str4;
    }

    public long c(String str, String str2) {
        SQLiteDatabase readableDatabase;
        Throwable th;
        Cursor cursor = null;
        long j = 0;
        String str3 = "select dt from tb_tid where name=?";
        try {
            readableDatabase = getReadableDatabase();
            try {
                String[] strArr = new String[b];
                strArr[0] = e(str, str2);
                cursor = readableDatabase.rawQuery(str3, strArr);
                if (cursor.moveToFirst()) {
                    j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(cursor.getString(0)).getTime();
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
            } catch (Exception e) {
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            readableDatabase = cursor;
            if (cursor != null) {
                cursor.close();
            }
            readableDatabase.close();
            return j;
        } catch (Throwable th3) {
            th = th3;
            readableDatabase = cursor;
            if (cursor != null) {
                cursor.close();
            }
            readableDatabase.close();
            throw th;
        }
        return j;
    }

    public List a() {
        SQLiteDatabase readableDatabase;
        Cursor rawQuery;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor = null;
        List arrayList = new ArrayList();
        try {
            readableDatabase = getReadableDatabase();
            try {
                rawQuery = readableDatabase.rawQuery("select tid from tb_tid", null);
                while (rawQuery.moveToNext()) {
                    try {
                        Object string = rawQuery.getString(0);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(Des.b(string, DeviceInfo.d((Context) this.c.get())));
                        }
                    } catch (Exception e) {
                        cursor = rawQuery;
                        sQLiteDatabase = readableDatabase;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
            } catch (Exception e2) {
                sQLiteDatabase = readableDatabase;
                if (cursor != null) {
                    cursor.close();
                }
                sQLiteDatabase.close();
                return arrayList;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            sQLiteDatabase = null;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
            return arrayList;
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th;
        }
        return arrayList;
    }

    public String d(String str, String str2) {
        SQLiteDatabase readableDatabase;
        Cursor rawQuery;
        Throwable th;
        String str3 = null;
        String str4 = "select key_tid from tb_tid where name=?";
        try {
            readableDatabase = getReadableDatabase();
            try {
                String[] strArr = new String[b];
                strArr[0] = e(str, str2);
                rawQuery = readableDatabase.rawQuery(str4, strArr);
            } catch (Exception e) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                return str3;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                rawQuery = null;
                th = th3;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
                throw th;
            }
            try {
                if (rawQuery.moveToFirst()) {
                    str3 = rawQuery.getString(0);
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase != null && readableDatabase.isOpen()) {
                    readableDatabase.close();
                }
            } catch (Exception e2) {
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                return str3;
            } catch (Throwable th4) {
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            rawQuery = null;
            readableDatabase = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            readableDatabase.close();
            return str3;
        } catch (Throwable th22) {
            readableDatabase = null;
            th = th22;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            readableDatabase.close();
            throw th;
        }
        return str3;
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        int i;
        String str3 = "select count(*) from tb_tid where name=?";
        Cursor cursor = null;
        try {
            int i2;
            String[] strArr = new String[b];
            strArr[0] = e(str, str2);
            cursor = sQLiteDatabase.rawQuery(str3, strArr);
            if (cursor.moveToFirst()) {
                i2 = cursor.getInt(0);
            } else {
                i2 = 0;
            }
            if (cursor != null) {
                cursor.close();
                i = i2;
            } else {
                i = i2;
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
                i = 0;
            } else {
                i = 0;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    private String e(String str, String str2) {
        return str + str2;
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        String a = Des.a(str3, DeviceInfo.d((Context) this.c.get()));
        sQLiteDatabase.execSQL("insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))", new Object[]{e(str, str2), a, str4});
        a(sQLiteDatabase);
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        sQLiteDatabase.execSQL("update tb_tid set tid=?, key_tid=?, dt=datetime('now', 'localtime') where name=?", new Object[]{Des.a(str3, DeviceInfo.d((Context) this.c.get())), str4, e(str, str2)});
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            String[] strArr = new String[b];
            strArr[0] = str;
            sQLiteDatabase.delete("tb_tid", "name=?", strArr);
        } catch (Object e) {
            LogUtils.a(e);
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        int i = 0;
        Cursor rawQuery = sQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
        if (rawQuery.getCount() <= 14) {
            rawQuery.close();
            return;
        }
        int count = rawQuery.getCount() - 14;
        String[] strArr = new String[count];
        if (rawQuery.moveToFirst()) {
            int i2 = 0;
            do {
                strArr[i2] = rawQuery.getString(0);
                i2 += b;
                if (!rawQuery.moveToNext()) {
                    break;
                }
            } while (count > i2);
        }
        rawQuery.close();
        while (i < strArr.length) {
            if (!TextUtils.isEmpty(strArr[i])) {
                a(sQLiteDatabase, strArr[i]);
            }
            i += b;
        }
    }
}
