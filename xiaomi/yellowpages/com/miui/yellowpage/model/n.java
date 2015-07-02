package com.miui.yellowpage.model;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import java.util.ArrayList;
import java.util.Iterator;
import miui.provider.Notes.Data;
import miui.telephony.PhoneNumberUtils;

/* compiled from: CallNote */
public class n {
    private static Context mContext;
    private String[] iI;
    private ArrayList wB;

    public n(Context context, String[] strArr) {
        mContext = context;
        this.wB = new ArrayList();
        this.iI = strArr;
        fr();
    }

    private void fr() {
        String str = "mime_type=? AND " + bq("data3");
        try {
            Cursor query = mContext.getContentResolver().query(Data.CONTENT_URI, y.COLUMNS, str, new String[]{"vnd.android.cursor.item/call_note"}, null);
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        String string = query.getString(0);
                        Long valueOf = Long.valueOf(query.getLong(1));
                        Long valueOf2 = Long.valueOf(query.getLong(2));
                        String a = a(valueOf);
                        if (a != null) {
                            int indexOf = a.indexOf("\n");
                            if (indexOf != -1) {
                                a = a.substring(0, indexOf);
                            }
                        }
                        if (a != null) {
                            this.wB.add(new t(this, string, valueOf, valueOf2, a));
                        }
                    } finally {
                        query.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String a(Long l) {
        String str = null;
        Cursor query = mContext.getContentResolver().query(Data.CONTENT_URI, new String[]{MiniDefine.at}, "mime_type =? AND note_id =?", new String[]{"vnd.android.cursor.item/text_note", l.toString()}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    str = query.getString(0);
                }
                query.close();
            } catch (Throwable th) {
                query.close();
            }
        }
        return str;
    }

    public t a(Long l, String str) {
        if (this.wB == null) {
            return null;
        }
        t tVar;
        Iterator it = this.wB.iterator();
        while (it.hasNext()) {
            tVar = (t) it.next();
            if (tVar.Ep.equals(l) && PhoneNumberUtils.compare(tVar.En, str)) {
                break;
            }
        }
        tVar = null;
        return tVar;
    }

    private String bq(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : this.iI) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" OR ");
            }
            stringBuilder.append("PHONE_NUMBERS_EQUAL(" + str + ",");
            stringBuilder.append(DatabaseUtils.sqlEscapeString(str2));
            stringBuilder.append(",0)");
        }
        return stringBuilder.toString();
    }

    public static void a(Context context, View view, TextView textView, t tVar) {
        view.setOnClickListener(new r(tVar.Eo, context));
        if (TextUtils.isEmpty(tVar.Eq)) {
            textView.setText(context.getResources().getString(R.string.contact_detail_calllog_call_note_tips));
        } else {
            textView.setText(tVar.Eq);
        }
    }
}
