package com.miui.yellowpage.utils;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.reference.RefMethods.IntentScope;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.YellowPageDataDetailEntry.Type;
import com.miui.yellowpage.model.e;
import com.miui.yellowpage.model.x;
import com.miui.yellowpage.ui.cO;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import miui.telephony.MultiSimManager;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.telephony.SimInfoManager;
import miui.yellowpage.YellowPagePhone;

/* compiled from: Contact */
public class o {
    public static void a(int i, int i2, ImageView imageView) {
        if (imageView != null) {
            switch (i) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (i2 == 1) {
                        imageView.setImageResource(R.drawable.dialer_ic_call_log_header_incoming_forwarding_call);
                        return;
                    } else {
                        imageView.setImageResource(R.drawable.dialer_ic_call_log_header_incoming_call);
                        return;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    imageView.setImageResource(R.drawable.dialer_ic_call_log_header_outgoing_call);
                    return;
                case WindowData.d /*3*/:
                    if (i2 == 1) {
                        imageView.setImageResource(R.drawable.dialer_ic_call_log_header_missed_forwarding_call);
                        return;
                    } else {
                        imageView.setImageResource(R.drawable.dialer_ic_call_log_header_missed_call);
                        return;
                    }
                case Base64.CRLF /*4*/:
                    imageView.setImageResource(R.drawable.dialer_ic_call_log_header_voicemail);
                    return;
                case WindowData.k /*10*/:
                    imageView.setImageResource(R.drawable.dialer_ic_call_log_header_newcontact);
                    return;
                default:
                    imageView.setVisibility(8);
                    return;
            }
        }
    }

    public static boolean cN() {
        return false;
    }

    public static void a(Context context, CharBuffer charBuffer, long j, int i) {
        long j2;
        long j3;
        if (j >= 60) {
            j2 = j / 60;
            j3 = j - (60 * j2);
        } else {
            j2 = 0;
            j3 = j;
        }
        if (i == 3) {
            a(context, charBuffer, j);
        } else if (j == 0 && (i == 1 || i == 2)) {
            charBuffer.append(context.getString(i == 1 ? R.string.callrecordview_item_op_time_rejected : R.string.callrecordview_item_op_time_missed));
        } else if (i == 1) {
            charBuffer.append(context.getString(R.string.callrecordview_item_op_time_short_incoming));
            if (j2 == 0) {
                charBuffer.append(context.getString(R.string.callrecordview_item_op_time_only_seconds, new Object[]{Long.valueOf(j3)}));
            } else if (j3 == 0) {
                charBuffer.append(context.getString(R.string.callrecordview_item_op_time_only_minutes, new Object[]{Long.valueOf(j2)}));
            } else {
                charBuffer.append(context.getString(R.string.callrecordview_item_op_time_minutes_seconds, new Object[]{Long.valueOf(j2), Long.valueOf(j3)}));
            }
        } else if (i == 2) {
            charBuffer.append(context.getString(R.string.callrecordview_item_op_time_short_outgoing));
            if (j2 == 0) {
                charBuffer.append(context.getString(R.string.callrecordview_item_op_time_only_seconds, new Object[]{Long.valueOf(j3)}));
            } else if (j3 == 0) {
                charBuffer.append(context.getString(R.string.callrecordview_item_op_time_only_minutes, new Object[]{Long.valueOf(j2)}));
            } else {
                charBuffer.append(context.getString(R.string.callrecordview_item_op_time_minutes_seconds, new Object[]{Long.valueOf(j2), Long.valueOf(j3)}));
            }
        } else if (i == 10) {
            charBuffer.append(context.getString(R.string.callrecordview_item_newcontact));
        } else if (i == 4) {
            charBuffer.append(context.getString(R.string.voicemail));
        }
    }

    public static void a(Context context, CharBuffer charBuffer, long j) {
        charBuffer.append(context.getString(R.string.callNoAnswerTimeFormat, new Object[]{Long.valueOf((j / 5) + 1)}));
    }

    public static void a(Context context, CharSequence charSequence, boolean z) {
        Intent intent = new Intent("android.intent.action.CALL_PRIVILEGED", Uri.fromParts("tel", charSequence.toString(), null));
        intent.putExtra("com.android.phone.IS_IPCALL", z);
        IntentScope.processIntentScope(context, intent, IntentScope.PACKAGE_NAME_PHONE);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, Type type) {
        if (!TextUtils.isEmpty(str)) {
            if (type == Type.PHONE) {
                str = PhoneNumberUtils.removeDashesAndBlanks(str);
            }
            Log.d("Contact", "copy text " + str);
            ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(new ClipData(null, new String[]{type.ar()}, new Item(str)));
            Toast.makeText(context, context.getString(R.string.toast_text_copied), 0).show();
        }
    }

    public static x j(Context context, String str) {
        Cursor query = context.getContentResolver().query(B.URI, B.COLUMNS, "PHONE_NUMBERS_EQUAL(number, ?, 0)", new String[]{str}, "date ASC");
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    long j = query.getLong(4);
                    long j2 = query.getLong(0);
                    long j3 = query.getLong(1);
                    int i = query.getInt(3);
                    String string = query.getString(5);
                    x aj = new x().u(j).s(j2).t(j3).cs(str).ai(i).ct(string).aj(query.getInt(6));
                    return aj;
                }
                query.close();
            } finally {
                query.close();
            }
        }
        return null;
    }

    public static e k(Context context, String str) {
        Cursor query = context.getContentResolver().query(m.URI, m.COLUMNS, "PHONE_NUMBERS_EQUAL(address, ?, 0)", new String[]{str}, "date ASC");
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    e eVar = new e(query.getLong(0), query.getInt(1), query.getLong(2), query.getString(3));
                    return eVar;
                }
                query.close();
            } finally {
                query.close();
            }
        }
        return null;
    }

    private static long cO() {
        Calendar instance = Calendar.getInstance();
        instance.roll(6, -1);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        return instance.getTimeInMillis();
    }

    public static ArrayList J(Context context) {
        long cO = cO() + ConfigConstant.MAIN_SWITCH_INTERVAL_UINT;
        Cursor query = context.getContentResolver().query(B.URI, B.COLUMNS, "date BETWEEN ? AND ?", new String[]{String.valueOf(cO()), String.valueOf(cO)}, null);
        if (query == null) {
            return null;
        }
        ArrayList arrayList = null;
        while (query.moveToNext()) {
            try {
                if (!n(context, query.getString(2))) {
                    long j = query.getLong(4);
                    long j2 = query.getLong(1);
                    int i = query.getInt(3);
                    String string = query.getString(5);
                    int i2 = query.getInt(6);
                    long j3 = query.getLong(0);
                    String string2 = query.getString(7);
                    Log.d("Contact", "yesterday stranger calls " + string2);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(new x().u(j).s(j3).t(j2).cs(string2).ai(i).ct(string).aj(i2));
                    if (arrayList.size() == 100) {
                        break;
                    }
                }
            } catch (Throwable th) {
                query.close();
            }
        }
        query.close();
        return arrayList;
    }

    public static ArrayList K(Context context) {
        long cO = cO() + ConfigConstant.MAIN_SWITCH_INTERVAL_UINT;
        Cursor query = context.getContentResolver().query(m.URI, m.COLUMNS, "date BETWEEN ? AND ?", new String[]{String.valueOf(cO()), String.valueOf(cO)}, null);
        if (query == null) {
            return null;
        }
        ArrayList arrayList = null;
        while (query.moveToNext()) {
            try {
                long j = query.getLong(0);
                int i = query.getInt(1);
                long j2 = query.getLong(2);
                String string = query.getString(3);
                if (!n(context, string)) {
                    ArrayList arrayList2;
                    if (arrayList == null) {
                        arrayList2 = new ArrayList();
                    } else {
                        arrayList2 = arrayList;
                    }
                    Log.d("Contact", "yesterday stranger sms " + string);
                    arrayList2.add(new e(j, i, j2, string));
                    if (arrayList2.size() == 100) {
                        arrayList = arrayList2;
                        break;
                    }
                    arrayList = arrayList2;
                }
            } catch (Throwable th) {
                query.close();
            }
        }
        query.close();
        return arrayList;
    }

    public static ArrayList L(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = B.URI;
        Cursor query = contentResolver.query(uri, B.COLUMNS, "type=3", null, null);
        if (query == null) {
            return null;
        }
        ArrayList arrayList = null;
        while (query.moveToNext()) {
            if (!n(context, query.getString(2))) {
                ArrayList arrayList2;
                String string = query.getString(7);
                uri = context.getContentResolver().query(B.URI, null, "normalized_number = ?", new String[]{string}, null);
                if (uri != null) {
                    try {
                        if (uri.getCount() == 1) {
                            if (arrayList == null) {
                                arrayList2 = new ArrayList();
                            } else {
                                arrayList2 = arrayList;
                            }
                            arrayList2.add(string);
                            Log.d("Contact", "The number " + string + " is one call ring number");
                        } else {
                            arrayList2 = arrayList;
                        }
                    } catch (Throwable th) {
                        arrayList2 = th;
                    } finally {
                        uri.close();
                    }
                } else {
                    arrayList2 = arrayList;
                }
                arrayList = arrayList2;
            }
        }
        query.close();
        return arrayList;
    }

    public static String[] l(Context context, String str) {
        YellowPage i = e.i(context, str);
        if (i == null) {
            return null;
        }
        List b = x.b(i.getPhones(), e.c(context, i.getId()));
        if (b == null || b.size() <= 0) {
            return null;
        }
        String[] strArr = new String[b.size()];
        for (int i2 = 0; i2 < b.size(); i2++) {
            PhoneNumber parse = PhoneNumber.parse(((YellowPagePhone) b.get(i2)).getNormalizedNumber());
            strArr[i2] = parse.getNormalizedNumber(false, true);
            parse.recycle();
        }
        return strArr;
    }

    public static boolean a(Context context, String[] strArr) {
        boolean z = false;
        if (strArr != null) {
            Cursor request = cO.b(context, strArr).request();
            if (request != null) {
                try {
                    if (request.getCount() > 0) {
                        z = true;
                    }
                    request.close();
                } catch (Throwable th) {
                    request.close();
                }
            }
        }
        return z;
    }

    public static boolean m(Context context, String str) {
        return a(context, l(context, str));
    }

    public static boolean f(Context context, long j) {
        return m(context, String.valueOf(j));
    }

    public static boolean n(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor query = context.getContentResolver().query(Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(PhoneNumberUtils.normalizeNumber(str))), null, null, null, null);
        if (query == null) {
            return false;
        }
        try {
            boolean z;
            if (query.getCount() > 0) {
                z = true;
            } else {
                z = false;
            }
            query.close();
            return z;
        } catch (Throwable th) {
            query.close();
        }
    }

    public static int d(Context context, int i) {
        return SimInfoManager.getSlotIdBySimInfoId(context, (long) i);
    }

    public static boolean cP() {
        int availableSimCount = MultiSimManager.getInstance().getAvailableSimCount();
        Log.d("Contact", "available count " + availableSimCount);
        if (availableSimCount > 1) {
            return true;
        }
        return false;
    }
}
