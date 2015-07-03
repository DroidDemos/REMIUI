package com.android.common.contacts;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import com.android.common.speech.LoggingEvents;
import com.android.common.widget.CompositeCursorAdapter;
import com.android.common.widget.CompositeCursorAdapter.Partition;
import com.mediatek.encapsulation.android.telephony.EncapsulatedSimInfoManager;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEmailAddressAdapter extends CompositeCursorAdapter implements Filterable {
    private static final int ALLOWANCE_FOR_DUPLICATES = 5;
    private static final int DEFAULT_PREFERRED_MAX_RESULT_COUNT = 10;
    private static final long DIRECTORY_LOCAL_INVISIBLE = 1;
    private static final String DIRECTORY_PARAM_KEY = "directory";
    private static final String LIMIT_PARAM_KEY = "limit";
    private static final int MESSAGE_SEARCH_PENDING = 1;
    private static final int MESSAGE_SEARCH_PENDING_DELAY = 1000;
    private static final String PRIMARY_ACCOUNT_NAME = "name_for_primary_account";
    private static final String PRIMARY_ACCOUNT_TYPE = "type_for_primary_account";
    private static final String SEARCHING_CURSOR_MARKER = "searching";
    private static final String TAG = "BaseEmailAddressAdapter";
    private Account mAccount;
    protected final ContentResolver mContentResolver;
    private boolean mDirectoriesLoaded;
    private Handler mHandler;
    private int mPreferredMaxResultCount;

    private final class DefaultPartitionFilter extends Filter {
        private DefaultPartitionFilter() {
        }

        protected FilterResults performFiltering(CharSequence constraint) {
            Cursor directoryCursor = null;
            if (!BaseEmailAddressAdapter.this.mDirectoriesLoaded) {
                directoryCursor = BaseEmailAddressAdapter.this.mContentResolver.query(DirectoryListQuery.URI, DirectoryListQuery.PROJECTION, null, null, null);
                BaseEmailAddressAdapter.this.mDirectoriesLoaded = true;
            }
            FilterResults results = new FilterResults();
            Cursor cursor = null;
            if (!TextUtils.isEmpty(constraint)) {
                Builder builder = Email.CONTENT_FILTER_URI.buildUpon().appendPath(constraint.toString()).appendQueryParameter(BaseEmailAddressAdapter.LIMIT_PARAM_KEY, String.valueOf(BaseEmailAddressAdapter.this.mPreferredMaxResultCount));
                if (BaseEmailAddressAdapter.this.mAccount != null) {
                    builder.appendQueryParameter(BaseEmailAddressAdapter.PRIMARY_ACCOUNT_NAME, BaseEmailAddressAdapter.this.mAccount.name);
                    builder.appendQueryParameter(BaseEmailAddressAdapter.PRIMARY_ACCOUNT_TYPE, BaseEmailAddressAdapter.this.mAccount.type);
                }
                cursor = BaseEmailAddressAdapter.this.mContentResolver.query(builder.build(), EmailQuery.PROJECTION, null, null, null);
                results.count = cursor.getCount();
            }
            results.values = new Cursor[]{directoryCursor, cursor};
            return results;
        }

        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                Cursor[] cursors = (Cursor[]) results.values;
                BaseEmailAddressAdapter.this.onDirectoryLoadFinished(constraint, cursors[0], cursors[BaseEmailAddressAdapter.MESSAGE_SEARCH_PENDING]);
            }
            results.count = BaseEmailAddressAdapter.this.getCount();
        }

        public CharSequence convertResultToString(Object resultValue) {
            return BaseEmailAddressAdapter.this.makeDisplayString((Cursor) resultValue);
        }
    }

    private static class DirectoryListQuery {
        public static final int ACCOUNT_NAME = 1;
        public static final int ACCOUNT_TYPE = 2;
        private static final String DIRECTORY_ACCOUNT_NAME = "accountName";
        private static final String DIRECTORY_ACCOUNT_TYPE = "accountType";
        private static final String DIRECTORY_DISPLAY_NAME = "displayName";
        private static final String DIRECTORY_ID = "_id";
        private static final String DIRECTORY_PACKAGE_NAME = "packageName";
        private static final String DIRECTORY_TYPE_RESOURCE_ID = "typeResourceId";
        public static final int DISPLAY_NAME = 3;
        public static final int ID = 0;
        public static final int PACKAGE_NAME = 4;
        public static final String[] PROJECTION;
        public static final int TYPE_RESOURCE_ID = 5;
        public static final Uri URI;

        private DirectoryListQuery() {
        }

        static {
            URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories");
            PROJECTION = new String[]{DIRECTORY_ID, DIRECTORY_ACCOUNT_NAME, DIRECTORY_ACCOUNT_TYPE, DIRECTORY_DISPLAY_NAME, DIRECTORY_PACKAGE_NAME, DIRECTORY_TYPE_RESOURCE_ID};
        }
    }

    public static final class DirectoryPartition extends Partition {
        public String accountName;
        public String accountType;
        public CharSequence constraint;
        public long directoryId;
        public String directoryType;
        public String displayName;
        public DirectoryPartitionFilter filter;
        public boolean loading;

        public DirectoryPartition() {
            super(false, false);
        }
    }

    private final class DirectoryPartitionFilter extends Filter {
        private final long mDirectoryId;
        private int mLimit;
        private final int mPartitionIndex;

        public DirectoryPartitionFilter(int partitionIndex, long directoryId) {
            this.mPartitionIndex = partitionIndex;
            this.mDirectoryId = directoryId;
        }

        public synchronized void setLimit(int limit) {
            this.mLimit = limit;
        }

        public synchronized int getLimit() {
            return this.mLimit;
        }

        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (!TextUtils.isEmpty(constraint)) {
                results.values = BaseEmailAddressAdapter.this.mContentResolver.query(Email.CONTENT_FILTER_URI.buildUpon().appendPath(constraint.toString()).appendQueryParameter(BaseEmailAddressAdapter.DIRECTORY_PARAM_KEY, String.valueOf(this.mDirectoryId)).appendQueryParameter(BaseEmailAddressAdapter.LIMIT_PARAM_KEY, String.valueOf(getLimit() + BaseEmailAddressAdapter.ALLOWANCE_FOR_DUPLICATES)).build(), EmailQuery.PROJECTION, null, null, null);
            }
            return results;
        }

        protected void publishResults(CharSequence constraint, FilterResults results) {
            BaseEmailAddressAdapter.this.onPartitionLoadFinished(constraint, this.mPartitionIndex, results.values);
            results.count = BaseEmailAddressAdapter.this.getCount();
        }
    }

    private static class EmailQuery {
        public static final int ADDRESS = 1;
        public static final int NAME = 0;
        public static final String[] PROJECTION;

        private EmailQuery() {
        }

        static {
            PROJECTION = new String[]{EncapsulatedSimInfoManager.DISPLAY_NAME, "data1"};
        }
    }

    protected abstract void bindView(View view, String str, String str2, String str3, String str4);

    protected abstract void bindViewLoading(View view, String str, String str2);

    protected abstract View inflateItemView(ViewGroup viewGroup);

    protected abstract View inflateItemViewLoading(ViewGroup viewGroup);

    public BaseEmailAddressAdapter(Context context) {
        this(context, DEFAULT_PREFERRED_MAX_RESULT_COUNT);
    }

    public BaseEmailAddressAdapter(Context context, int preferredMaxResultCount) {
        super(context);
        this.mContentResolver = context.getContentResolver();
        this.mPreferredMaxResultCount = preferredMaxResultCount;
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                BaseEmailAddressAdapter.this.showSearchPendingIfNotComplete(msg.arg1);
            }
        };
    }

    public void setAccount(Account account) {
        this.mAccount = account;
    }

    protected int getItemViewType(int partitionIndex, int position) {
        return ((DirectoryPartition) getPartition(partitionIndex)).loading ? MESSAGE_SEARCH_PENDING : 0;
    }

    protected View newView(Context context, int partitionIndex, Cursor cursor, int position, ViewGroup parent) {
        if (((DirectoryPartition) getPartition(partitionIndex)).loading) {
            return inflateItemViewLoading(parent);
        }
        return inflateItemView(parent);
    }

    protected void bindView(View v, int partition, Cursor cursor, int position) {
        DirectoryPartition directoryPartition = (DirectoryPartition) getPartition(partition);
        String directoryType = directoryPartition.directoryType;
        String directoryName = directoryPartition.displayName;
        if (directoryPartition.loading) {
            bindViewLoading(v, directoryType, directoryName);
            return;
        }
        String displayName = cursor.getString(0);
        String emailAddress = cursor.getString(MESSAGE_SEARCH_PENDING);
        if (TextUtils.isEmpty(displayName) || TextUtils.equals(displayName, emailAddress)) {
            displayName = emailAddress;
            emailAddress = null;
        }
        bindView(v, directoryType, directoryName, displayName, emailAddress);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    protected boolean isEnabled(int partitionIndex, int position) {
        return !isLoading(partitionIndex);
    }

    private boolean isLoading(int partitionIndex) {
        return ((DirectoryPartition) getPartition(partitionIndex)).loading;
    }

    public Filter getFilter() {
        return new DefaultPartitionFilter();
    }

    protected void onDirectoryLoadFinished(CharSequence constraint, Cursor directoryCursor, Cursor defaultPartitionCursor) {
        DirectoryPartition partition;
        int i;
        if (directoryCursor != null) {
            PackageManager packageManager = getContext().getPackageManager();
            DirectoryPartition preferredDirectory = null;
            List<DirectoryPartition> directories = new ArrayList();
            while (directoryCursor.moveToNext()) {
                long id = directoryCursor.getLong(0);
                if (id != DIRECTORY_LOCAL_INVISIBLE) {
                    partition = new DirectoryPartition();
                    partition.directoryId = id;
                    partition.displayName = directoryCursor.getString(3);
                    partition.accountName = directoryCursor.getString(MESSAGE_SEARCH_PENDING);
                    partition.accountType = directoryCursor.getString(2);
                    String packageName = directoryCursor.getString(4);
                    int resourceId = directoryCursor.getInt(ALLOWANCE_FOR_DUPLICATES);
                    if (!(packageName == null || resourceId == 0)) {
                        try {
                            partition.directoryType = packageManager.getResourcesForApplication(packageName).getString(resourceId);
                            if (partition.directoryType == null) {
                                Log.e(TAG, "Cannot resolve directory name: " + resourceId + "@" + packageName);
                            }
                        } catch (NameNotFoundException e) {
                            Log.e(TAG, "Cannot resolve directory name: " + resourceId + "@" + packageName, e);
                        }
                    }
                    if (this.mAccount != null) {
                        if (this.mAccount.name.equals(partition.accountName)) {
                            if (this.mAccount.type.equals(partition.accountType)) {
                                preferredDirectory = partition;
                            }
                        }
                    }
                    directories.add(partition);
                }
            }
            if (preferredDirectory != null) {
                directories.add(MESSAGE_SEARCH_PENDING, preferredDirectory);
            }
            for (Partition addPartition : directories) {
                addPartition(addPartition);
            }
        }
        int count = getPartitionCount();
        setNotificationsEnabled(false);
        if (defaultPartitionCursor != null) {
            try {
                if (getPartitionCount() > 0) {
                    changeCursor(0, defaultPartitionCursor);
                }
            } catch (Throwable th) {
                setNotificationsEnabled(true);
            }
        }
        int limit = this.mPreferredMaxResultCount - (defaultPartitionCursor == null ? 0 : defaultPartitionCursor.getCount());
        for (i = MESSAGE_SEARCH_PENDING; i < count; i += MESSAGE_SEARCH_PENDING) {
            partition = (DirectoryPartition) getPartition(i);
            partition.constraint = constraint;
            if (limit <= 0) {
                partition.loading = false;
                changeCursor(i, null);
            } else if (!partition.loading) {
                partition.loading = true;
                changeCursor(i, null);
            }
        }
        setNotificationsEnabled(true);
        for (i = MESSAGE_SEARCH_PENDING; i < count; i += MESSAGE_SEARCH_PENDING) {
            partition = (DirectoryPartition) getPartition(i);
            if (partition.loading) {
                this.mHandler.removeMessages(MESSAGE_SEARCH_PENDING, partition);
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(MESSAGE_SEARCH_PENDING, i, 0, partition), 1000);
                if (partition.filter == null) {
                    partition.filter = new DirectoryPartitionFilter(i, partition.directoryId);
                }
                partition.filter.setLimit(limit);
                partition.filter.filter(constraint);
            } else if (partition.filter != null) {
                partition.filter.filter(null);
            }
        }
    }

    void showSearchPendingIfNotComplete(int partitionIndex) {
        if (partitionIndex < getPartitionCount() && ((DirectoryPartition) getPartition(partitionIndex)).loading) {
            changeCursor(partitionIndex, createLoadingCursor());
        }
    }

    private Cursor createLoadingCursor() {
        String[] strArr = new String[MESSAGE_SEARCH_PENDING];
        strArr[0] = SEARCHING_CURSOR_MARKER;
        MatrixCursor cursor = new MatrixCursor(strArr);
        Object[] objArr = new Object[MESSAGE_SEARCH_PENDING];
        objArr[0] = LoggingEvents.EXTRA_CALLING_APP_NAME;
        cursor.addRow(objArr);
        return cursor;
    }

    public void onPartitionLoadFinished(CharSequence constraint, int partitionIndex, Cursor cursor) {
        if (partitionIndex < getPartitionCount()) {
            DirectoryPartition partition = (DirectoryPartition) getPartition(partitionIndex);
            if (partition.loading && TextUtils.equals(constraint, partition.constraint)) {
                partition.loading = false;
                this.mHandler.removeMessages(MESSAGE_SEARCH_PENDING, partition);
                changeCursor(partitionIndex, removeDuplicatesAndTruncate(partitionIndex, cursor));
            } else if (cursor != null) {
                cursor.close();
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    private Cursor removeDuplicatesAndTruncate(int partition, Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        if (cursor.getCount() <= DEFAULT_PREFERRED_MAX_RESULT_COUNT && !hasDuplicates(cursor, partition)) {
            return cursor;
        }
        int count = 0;
        MatrixCursor newCursor = new MatrixCursor(EmailQuery.PROJECTION);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext() && count < DEFAULT_PREFERRED_MAX_RESULT_COUNT) {
            String displayName = cursor.getString(0);
            if (!isDuplicate(cursor.getString(MESSAGE_SEARCH_PENDING), partition)) {
                newCursor.addRow(new Object[]{displayName, cursor.getString(MESSAGE_SEARCH_PENDING)});
                count += MESSAGE_SEARCH_PENDING;
            }
        }
        cursor.close();
        return newCursor;
    }

    private boolean hasDuplicates(Cursor cursor, int partition) {
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            if (isDuplicate(cursor.getString(MESSAGE_SEARCH_PENDING), partition)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicate(String emailAddress, int excludePartition) {
        int partitionCount = getPartitionCount();
        int partition = 0;
        while (partition < partitionCount) {
            if (!(partition == excludePartition || isLoading(partition))) {
                Cursor cursor = getCursor(partition);
                if (cursor != null) {
                    cursor.moveToPosition(-1);
                    while (cursor.moveToNext()) {
                        if (TextUtils.equals(emailAddress, cursor.getString(MESSAGE_SEARCH_PENDING))) {
                            return true;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
            partition += MESSAGE_SEARCH_PENDING;
        }
        return false;
    }

    private final String makeDisplayString(Cursor cursor) {
        if (cursor.getColumnName(0).equals(SEARCHING_CURSOR_MARKER)) {
            return LoggingEvents.EXTRA_CALLING_APP_NAME;
        }
        String displayName = cursor.getString(0);
        String emailAddress = cursor.getString(MESSAGE_SEARCH_PENDING);
        return (TextUtils.isEmpty(displayName) || TextUtils.equals(displayName, emailAddress)) ? emailAddress : new Rfc822Token(displayName, emailAddress, null).toString();
    }
}
