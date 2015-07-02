package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* compiled from: YellowPageDatabaseHelper */
class d implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ q Ah;

    d(q qVar, SQLiteDatabase sQLiteDatabase) {
        this.Ah = qVar;
        this.Ag = sQLiteDatabase;
    }

    public void run() {
        IOException e;
        Throwable th;
        UnsupportedOperationException e2;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.Ah.mContext.getResources().openRawResource(R.raw.region), "UTF8"));
            try {
                this.Ah.c(this.Ag, "region", bufferedReader.readLine().split(":")[1]);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    String[] split = readLine.split("\t");
                    if (split.length == 5) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(MiniDefine.m, split[3]);
                        contentValues.put("parent", split[1]);
                        contentValues.put("_id", split[0]);
                        contentValues.put(MiniDefine.l, split[2]);
                        contentValues.put("postal_code", split[4]);
                        this.Ag.insert("region", null, contentValues);
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (IOException e4) {
                e3 = e4;
                try {
                    e3.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (UnsupportedOperationException e6) {
                e2 = e6;
                e2.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
            }
        } catch (IOException e7) {
            e322 = e7;
            bufferedReader = null;
            e322.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (UnsupportedOperationException e8) {
            e2 = e8;
            bufferedReader = null;
            e2.printStackTrace();
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
