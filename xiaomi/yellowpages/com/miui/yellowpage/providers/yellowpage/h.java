package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* compiled from: YellowPageDatabaseHelper */
class h implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ q Ah;

    h(q qVar, SQLiteDatabase sQLiteDatabase) {
        this.Ah = qVar;
        this.Ag = sQLiteDatabase;
    }

    public void run() {
        Exception e;
        Throwable th;
        Log.d("YellowPageDatabaseHelper", "import antispam category");
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.Ah.mContext.getResources().openRawResource(R.raw.antispam_category)));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (!readLine.startsWith("#")) {
                        String[] split = readLine.split(" ");
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("cid", split[0]);
                        contentValues.put("names", split[1]);
                        this.Ag.insert("antispam_category", null, contentValues);
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Exception e4) {
            e3 = e4;
            bufferedReader = null;
            try {
                e3.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e32) {
                        e32.printStackTrace();
                    }
                }
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
    }
}
