package com.miui.yellowpage.model;

import android.database.Cursor;
import android.text.TextUtils;

/* compiled from: ContactsSuggestionEntry */
public class p {
    public static final String[] zX;
    private String mDisplayName;
    private String zY;

    static {
        zX = new String[]{"_id", "data4", "display_name"};
    }

    public static p c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        Object string = cursor.getString(1);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        Object string2 = cursor.getString(2);
        if (TextUtils.isEmpty(string2)) {
            return null;
        }
        return new p(string, string2);
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getNormalizedNumber() {
        return this.zY;
    }

    private p(String str, String str2) {
        this.zY = str;
        this.mDisplayName = str2;
    }
}
