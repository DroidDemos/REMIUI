package com.miui.yellowpage.providers.telocation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.miui.yellowpage.base.utils.Log;

/* compiled from: CustomTelocationDatabaseHelper */
public class a extends SQLiteOpenHelper {
    public a(Context context) {
        super(context, "customtelocation.db", null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE location (_id INTEGER PRIMARY KEY AUTOINCREMENT,number TEXT, location TEXT, type INTEGER NOT NULL DEFAULT 0);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d("CustomTelocationDatabaseHelper", "Upgrading custom telocation database from version " + i + " to " + i2 + ", which will destroy all old data");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS location");
        onCreate(sQLiteDatabase);
    }
}
