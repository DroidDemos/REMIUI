package com.miui.yellowpage.b;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.g;
import com.miui.yellowpage.ui.InquiryHistoryListItem;

/* compiled from: InquiryHistoryAdapter */
public class m extends CursorAdapter {
    public static final String[] PROJECTION;
    private boolean mEditMode;

    static {
        PROJECTION = new String[]{"cache_key AS _id", MiniDefine.at, "remark"};
    }

    public m(Context context, Cursor cursor, boolean z) {
        super(context, cursor, z);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        InquiryHistoryListItem inquiryHistoryListItem = (InquiryHistoryListItem) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.inquiry_history_list_item, null);
        inquiryHistoryListItem.a(d(cursor), this.mEditMode);
        return inquiryHistoryListItem;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        if (!(view instanceof InquiryHistoryListItem)) {
            Log.e("InquiryHistoryAdapter", view + " is not an instance of InquiryHistoryListItem");
        }
        ((InquiryHistoryListItem) view).a(d(cursor), this.mEditMode);
    }

    public g d(Cursor cursor) {
        g K = g.K(cursor.getString(1));
        K.J(cursor.getString(2));
        return K;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void gS() {
        this.mEditMode = true;
    }

    public void gT() {
        this.mEditMode = false;
    }
}
