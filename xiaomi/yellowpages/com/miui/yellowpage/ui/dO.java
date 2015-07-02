package com.miui.yellowpage.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.a.p;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.LocalRequest;

/* compiled from: ExpressContactEditorFragment */
public abstract class dO extends cs {
    protected p KB;
    protected aQ KC;

    protected abstract void a(Cursor cursor);

    protected abstract void a(dd ddVar);

    protected static String cp(String str) {
        return TextUtils.isEmpty(str) ? str : str.replaceAll(" ", ConfigConstant.WIRELESS_FILENAME);
    }

    protected static BaseRequest a(Context context, Uri uri, int i) {
        LocalRequest localRequest = new LocalRequest(context, i);
        localRequest.setUri(uri);
        localRequest.setProjection(new String[]{"lookup"});
        return localRequest;
    }

    protected static BaseRequest a(Context context, String str, int i) {
        LocalRequest localRequest = new LocalRequest(context, i);
        localRequest.setUri(Data.CONTENT_URI);
        localRequest.setProjection(new String[]{"_id", "mimetype", "data1", "raw_contact_id"});
        localRequest.setSelection("lookup=? AND mimetype IN (?, ?, ?)");
        localRequest.setArgs(new String[]{str, "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/postal-address_v2", "vnd.android.cursor.item/name"});
        return localRequest;
    }

    protected void k(Intent intent) {
        Parcelable data = intent.getData();
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", data);
        getLoaderManager().restartLoader(101, bundle, this.KC);
    }
}
