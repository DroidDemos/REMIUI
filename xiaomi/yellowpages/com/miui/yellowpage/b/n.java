package com.miui.yellowpage.b;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.p;
import com.miui.yellowpage.ui.ContactsSuggestionItem;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.PhoneNumberUtils.PhoneNumber;

/* compiled from: ContactsSuggestionAdapter */
public class n extends ResourceCursorAdapter {
    private String DD;
    private Context mContext;

    public n(Context context) {
        super(context, R.layout.recharge_contact_suggestion_item, null, false);
        this.mContext = context;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null) {
            p c = p.c(cursor);
            if (c != null) {
                ((ContactsSuggestionItem) view).a(c, this.DD);
            }
        }
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            PhoneNumber parse = PhoneNumber.parse(charSequence);
            this.DD = parse.getNormalizedNumber(false, true);
            if (this.DD.length() < 3) {
                this.DD = PhoneNumberUtils.normalizeNumber(charSequence.toString());
            }
            parse.recycle();
        }
        return this.mContext.getContentResolver().query(Phone.CONTENT_FILTER_URI.buildUpon().appendPath(this.DD).build(), p.zX, null, null, null);
    }

    public CharSequence convertToString(Cursor cursor) {
        return p.c(cursor).getNormalizedNumber();
    }
}
