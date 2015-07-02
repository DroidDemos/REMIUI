package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.utils.Cache;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* compiled from: YellowPageDatabaseHelper */
class j implements Runnable {
    final /* synthetic */ SQLiteDatabase Ag;
    final /* synthetic */ q Ah;

    j(q qVar, SQLiteDatabase sQLiteDatabase) {
        this.Ah = qVar;
        this.Ag = sQLiteDatabase;
    }

    public void run() {
        IOException e;
        Throwable th;
        Log.d("YellowPageDatabaseHelper", "start to import profile data");
        BufferedReader bufferedReader;
        try {
            AssetManager assets = this.Ah.mContext.getAssets();
            bufferedReader = new BufferedReader(new InputStreamReader(assets.open("profile/profile")));
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("cache_key", Cache.PROFILE_CACHE_KEY);
                contentValues.put(MiniDefine.at, stringBuilder.toString());
                this.Ag.replace("cache", null, contentValues);
                Files.copyWebAssetDirectory(assets, "profile/icons", this.Ah.mContext.getCacheDir().getAbsolutePath());
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
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
