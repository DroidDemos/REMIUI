package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* compiled from: YellowPageDatabaseHelper */
class m implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ q Ah;

    m(q qVar, SQLiteDatabase sQLiteDatabase) {
        this.Ah = qVar;
        this.Ag = sQLiteDatabase;
    }

    public void run() {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        Log.d("YellowPageDatabaseHelper", "import provider");
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.Ah.mContext.getResources().openRawResource(R.raw.provider)));
            try {
                this.Ag.delete("provider", null, null);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (!readLine.startsWith("#")) {
                        String[] split = readLine.split(" ");
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("pid", split[0]);
                        contentValues.put(MiniDefine.l, split[1]);
                        if (split.length > 3) {
                            contentValues.put("icon", this.Ah.cj(split[2]));
                            contentValues.put("icon_big", this.Ah.cj(split[3]));
                        }
                        this.Ag.insert("provider", null, contentValues);
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
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e22 = e5;
            bufferedReader = null;
            e22.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
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
