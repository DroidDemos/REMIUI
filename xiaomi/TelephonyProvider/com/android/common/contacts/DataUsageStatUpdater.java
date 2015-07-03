package com.android.common.contacts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;

public class DataUsageStatUpdater {
    private static final String TAG;
    private final ContentResolver mResolver;

    public static final class DataUsageFeedback {
        static final Uri FEEDBACK_URI;
        static final String USAGE_TYPE = "type";
        public static final String USAGE_TYPE_CALL = "call";
        public static final String USAGE_TYPE_LONG_TEXT = "long_text";
        public static final String USAGE_TYPE_SHORT_TEXT = "short_text";

        static {
            FEEDBACK_URI = Uri.withAppendedPath(Data.CONTENT_URI, "usagefeedback");
        }
    }

    static {
        TAG = DataUsageStatUpdater.class.getSimpleName();
    }

    public DataUsageStatUpdater(Context context) {
        this.mResolver = context.getContentResolver();
    }

    public boolean updateWithRfc822Address(Collection<CharSequence> texts) {
        if (texts == null) {
            return false;
        }
        Set<String> addresses = new HashSet();
        for (CharSequence text : texts) {
            for (Rfc822Token token : Rfc822Tokenizer.tokenize(text.toString().trim())) {
                addresses.add(token.getAddress());
            }
        }
        return updateWithAddress(addresses);
    }

    public boolean updateWithAddress(Collection<String> addresses) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "updateWithAddress: " + Arrays.toString(addresses.toArray()));
        }
        if (!(addresses == null || addresses.isEmpty())) {
            ArrayList<String> whereArgs = new ArrayList();
            StringBuilder whereBuilder = new StringBuilder();
            String[] questionMarks = new String[addresses.size()];
            whereArgs.addAll(addresses);
            Arrays.fill(questionMarks, "?");
            whereBuilder.append("data1 IN (").append(TextUtils.join(",", questionMarks)).append(")");
            Cursor cursor = this.mResolver.query(Email.CONTENT_URI, new String[]{"contact_id", "_id"}, whereBuilder.toString(), (String[]) whereArgs.toArray(new String[0]), null);
            if (cursor == null) {
                Log.w(TAG, "Cursor for Email.CONTENT_URI became null.");
            } else {
                Set<Long> contactIds = new HashSet(cursor.getCount());
                Set<Long> dataIds = new HashSet(cursor.getCount());
                try {
                    cursor.move(-1);
                    while (cursor.moveToNext()) {
                        contactIds.add(Long.valueOf(cursor.getLong(0)));
                        dataIds.add(Long.valueOf(cursor.getLong(1)));
                    }
                    return update(contactIds, dataIds, DataUsageFeedback.USAGE_TYPE_LONG_TEXT);
                } finally {
                    cursor.close();
                }
            }
        }
        return false;
    }

    public boolean updateWithPhoneNumber(Collection<String> numbers) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "updateWithPhoneNumber: " + Arrays.toString(numbers.toArray()));
        }
        if (!(numbers == null || numbers.isEmpty())) {
            ArrayList<String> whereArgs = new ArrayList();
            StringBuilder whereBuilder = new StringBuilder();
            String[] questionMarks = new String[numbers.size()];
            whereArgs.addAll(numbers);
            Arrays.fill(questionMarks, "?");
            whereBuilder.append("data1 IN (").append(TextUtils.join(",", questionMarks)).append(")");
            Cursor cursor = this.mResolver.query(Phone.CONTENT_URI, new String[]{"contact_id", "_id"}, whereBuilder.toString(), (String[]) whereArgs.toArray(new String[0]), null);
            if (cursor == null) {
                Log.w(TAG, "Cursor for Phone.CONTENT_URI became null.");
            } else {
                Set<Long> contactIds = new HashSet(cursor.getCount());
                Set<Long> dataIds = new HashSet(cursor.getCount());
                try {
                    cursor.move(-1);
                    while (cursor.moveToNext()) {
                        contactIds.add(Long.valueOf(cursor.getLong(0)));
                        dataIds.add(Long.valueOf(cursor.getLong(1)));
                    }
                    return update(contactIds, dataIds, DataUsageFeedback.USAGE_TYPE_SHORT_TEXT);
                } finally {
                    cursor.close();
                }
            }
        }
        return false;
    }

    private boolean update(Collection<Long> contactIds, Collection<Long> dataIds, String type) {
        long currentTimeMillis = System.currentTimeMillis();
        if (VERSION.SDK_INT >= 14) {
            if (!dataIds.isEmpty()) {
                if (this.mResolver.update(DataUsageFeedback.FEEDBACK_URI.buildUpon().appendPath(TextUtils.join(",", dataIds)).appendQueryParameter(AntispamNumber.TYPE, type).build(), new ContentValues(), null, null) > 0) {
                    return true;
                }
                if (!Log.isLoggable(TAG, 3)) {
                    return false;
                }
                Log.d(TAG, "update toward data rows " + dataIds + " failed");
                return false;
            } else if (!Log.isLoggable(TAG, 3)) {
                return false;
            } else {
                Log.d(TAG, "Given list for data IDs is null. Ignoring.");
                return false;
            }
        } else if (!contactIds.isEmpty()) {
            StringBuilder whereBuilder = new StringBuilder();
            ArrayList<String> whereArgs = new ArrayList();
            String[] questionMarks = new String[contactIds.size()];
            for (Long longValue : contactIds) {
                whereArgs.add(String.valueOf(longValue.longValue()));
            }
            Arrays.fill(questionMarks, "?");
            whereBuilder.append("_id IN (").append(TextUtils.join(",", questionMarks)).append(")");
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "contactId where: " + whereBuilder.toString());
                Log.d(TAG, "contactId selection: " + whereArgs);
            }
            ContentValues values = new ContentValues();
            values.put("last_time_contacted", Long.valueOf(currentTimeMillis));
            if (this.mResolver.update(Contacts.CONTENT_URI, values, whereBuilder.toString(), (String[]) whereArgs.toArray(new String[0])) > 0) {
                return true;
            }
            if (!Log.isLoggable(TAG, 3)) {
                return false;
            }
            Log.d(TAG, "update toward raw contacts " + contactIds + " failed");
            return false;
        } else if (!Log.isLoggable(TAG, 3)) {
            return false;
        } else {
            Log.d(TAG, "Given list for contact IDs is null. Ignoring.");
            return false;
        }
    }
}
