package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* compiled from: YellowPageDatabaseHelper */
class l implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ q Ah;

    l(q qVar, SQLiteDatabase sQLiteDatabase) {
        this.Ah = qVar;
        this.Ag = sQLiteDatabase;
    }

    public void run() {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        Log.d("YellowPageDatabaseHelper", "import white list");
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.Ah.mContext.getResources().openRawResource(R.raw.antispam_white_list)));
            try {
                this.Ag.delete("antispam_white_list", null, null);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (!readLine.startsWith("#")) {
                        if (readLine.startsWith("version")) {
                            this.Ah.c(this.Ag, "antispam_white_list", readLine.split(":")[1]);
                        } else {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("number", readLine);
                            this.Ag.insert("antispam_white_list", null, contentValues);
                        }
                    }
                }
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
