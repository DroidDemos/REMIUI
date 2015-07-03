package com.android.common.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import miui.provider.yellowpage.YellowPageUtils.YellowPagePhone;

public abstract class GroupingListAdapter extends BaseAdapter {
    private static final long EXPANDED_GROUP_MASK = Long.MIN_VALUE;
    private static final int GROUP_METADATA_ARRAY_INCREMENT = 128;
    private static final int GROUP_METADATA_ARRAY_INITIAL_SIZE = 16;
    private static final long GROUP_OFFSET_MASK = 4294967295L;
    private static final long GROUP_SIZE_MASK = 9223372032559808512L;
    public static final int ITEM_TYPE_GROUP_HEADER = 1;
    public static final int ITEM_TYPE_IN_GROUP = 2;
    public static final int ITEM_TYPE_STANDALONE = 0;
    protected ContentObserver mChangeObserver;
    private Context mContext;
    private int mCount;
    private Cursor mCursor;
    protected DataSetObserver mDataSetObserver;
    private int mGroupCount;
    private long[] mGroupMetadata;
    private int mLastCachedCursorPosition;
    private int mLastCachedGroup;
    private int mLastCachedListPosition;
    private SparseIntArray mPositionCache;
    private PositionMetadata mPositionMetadata;
    private int mRowIdColumnIndex;

    protected static class PositionMetadata {
        int childCount;
        int cursorPosition;
        private int groupPosition;
        boolean isExpanded;
        int itemType;
        private int listPosition;

        protected PositionMetadata() {
            this.listPosition = -1;
        }
    }

    protected abstract void addGroups(Cursor cursor);

    protected abstract void bindChildView(View view, Context context, Cursor cursor);

    protected abstract void bindGroupView(View view, Context context, Cursor cursor, int i, boolean z);

    protected abstract void bindStandAloneView(View view, Context context, Cursor cursor);

    protected abstract View newChildView(Context context, ViewGroup viewGroup);

    protected abstract View newGroupView(Context context, ViewGroup viewGroup);

    protected abstract View newStandAloneView(Context context, ViewGroup viewGroup);

    public GroupingListAdapter(Context context) {
        this.mPositionCache = new SparseIntArray();
        this.mPositionMetadata = new PositionMetadata();
        this.mChangeObserver = new ContentObserver(new Handler()) {
            public boolean deliverSelfNotifications() {
                return true;
            }

            public void onChange(boolean selfChange) {
                GroupingListAdapter.this.onContentChanged();
            }
        };
        this.mDataSetObserver = new DataSetObserver() {
            public void onChanged() {
                GroupingListAdapter.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                GroupingListAdapter.this.notifyDataSetInvalidated();
            }
        };
        this.mContext = context;
        resetCache();
    }

    private void resetCache() {
        this.mCount = -1;
        this.mLastCachedListPosition = -1;
        this.mLastCachedCursorPosition = -1;
        this.mLastCachedGroup = -1;
        this.mPositionMetadata.listPosition = -1;
        this.mPositionCache.clear();
    }

    protected void onContentChanged() {
    }

    public void changeCursor(Cursor cursor) {
        if (cursor != this.mCursor) {
            if (this.mCursor != null) {
                this.mCursor.unregisterContentObserver(this.mChangeObserver);
                this.mCursor.unregisterDataSetObserver(this.mDataSetObserver);
                this.mCursor.close();
            }
            this.mCursor = cursor;
            resetCache();
            findGroups();
            if (cursor != null) {
                cursor.registerContentObserver(this.mChangeObserver);
                cursor.registerDataSetObserver(this.mDataSetObserver);
                this.mRowIdColumnIndex = cursor.getColumnIndexOrThrow("_id");
                notifyDataSetChanged();
                return;
            }
            notifyDataSetInvalidated();
        }
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    private void findGroups() {
        this.mGroupCount = 0;
        this.mGroupMetadata = new long[GROUP_METADATA_ARRAY_INITIAL_SIZE];
        if (this.mCursor != null) {
            addGroups(this.mCursor);
        }
    }

    protected void addGroup(int cursorPosition, int size, boolean expanded) {
        if (this.mGroupCount >= this.mGroupMetadata.length) {
            long[] array = new long[idealLongArraySize(this.mGroupMetadata.length + GROUP_METADATA_ARRAY_INCREMENT)];
            System.arraycopy(this.mGroupMetadata, 0, array, 0, this.mGroupCount);
            this.mGroupMetadata = array;
        }
        long metadata = (((long) size) << 32) | ((long) cursorPosition);
        if (expanded) {
            metadata |= EXPANDED_GROUP_MASK;
        }
        long[] jArr = this.mGroupMetadata;
        int i = this.mGroupCount;
        this.mGroupCount = i + ITEM_TYPE_GROUP_HEADER;
        jArr[i] = metadata;
    }

    private int idealLongArraySize(int need) {
        return idealByteArraySize(need * 8) / 8;
    }

    private int idealByteArraySize(int need) {
        for (int i = 4; i < 32; i += ITEM_TYPE_GROUP_HEADER) {
            if (need <= (ITEM_TYPE_GROUP_HEADER << i) - 12) {
                return (ITEM_TYPE_GROUP_HEADER << i) - 12;
            }
        }
        return need;
    }

    public int getCount() {
        if (this.mCursor == null) {
            return 0;
        }
        if (this.mCount != -1) {
            return this.mCount;
        }
        int cursorPosition = 0;
        int count = 0;
        for (int i = 0; i < this.mGroupCount; i += ITEM_TYPE_GROUP_HEADER) {
            boolean expanded;
            long metadata = this.mGroupMetadata[i];
            int offset = (int) (GROUP_OFFSET_MASK & metadata);
            if ((EXPANDED_GROUP_MASK & metadata) != 0) {
                expanded = true;
            } else {
                expanded = false;
            }
            int size = (int) ((GROUP_SIZE_MASK & metadata) >> 32);
            count += offset - cursorPosition;
            if (expanded) {
                count += size + ITEM_TYPE_GROUP_HEADER;
            } else {
                count += ITEM_TYPE_GROUP_HEADER;
            }
            cursorPosition = offset + size;
        }
        this.mCount = (this.mCursor.getCount() + count) - cursorPosition;
        return this.mCount;
    }

    public void obtainPositionMetadata(PositionMetadata metadata, int position) {
        if (metadata.listPosition != position) {
            int listPosition = 0;
            int cursorPosition = 0;
            int firstGroupToCheck = 0;
            if (this.mLastCachedListPosition != -1) {
                if (position <= this.mLastCachedListPosition) {
                    int index = this.mPositionCache.indexOfKey(position);
                    if (index < 0) {
                        index = (index ^ -1) - 1;
                        if (index >= this.mPositionCache.size()) {
                            index--;
                        }
                    }
                    if (index >= 0) {
                        listPosition = this.mPositionCache.keyAt(index);
                        firstGroupToCheck = this.mPositionCache.valueAt(index);
                        cursorPosition = (int) (GROUP_OFFSET_MASK & this.mGroupMetadata[firstGroupToCheck]);
                    }
                } else {
                    firstGroupToCheck = this.mLastCachedGroup;
                    listPosition = this.mLastCachedListPosition;
                    cursorPosition = this.mLastCachedCursorPosition;
                }
            }
            for (int i = firstGroupToCheck; i < this.mGroupCount; i += ITEM_TYPE_GROUP_HEADER) {
                long group = this.mGroupMetadata[i];
                int offset = (int) (GROUP_OFFSET_MASK & group);
                listPosition += offset - cursorPosition;
                cursorPosition = offset;
                if (i > this.mLastCachedGroup) {
                    this.mPositionCache.append(listPosition, i);
                    this.mLastCachedListPosition = listPosition;
                    this.mLastCachedCursorPosition = cursorPosition;
                    this.mLastCachedGroup = i;
                }
                if (position < listPosition) {
                    metadata.itemType = 0;
                    metadata.cursorPosition = cursorPosition - (listPosition - position);
                    return;
                }
                boolean expanded = (EXPANDED_GROUP_MASK & group) != 0;
                int size = (int) ((GROUP_SIZE_MASK & group) >> 32);
                if (position == listPosition) {
                    metadata.itemType = ITEM_TYPE_GROUP_HEADER;
                    metadata.groupPosition = i;
                    metadata.isExpanded = expanded;
                    metadata.childCount = size;
                    metadata.cursorPosition = offset;
                    return;
                }
                if (!expanded) {
                    listPosition += ITEM_TYPE_GROUP_HEADER;
                } else if (position < (listPosition + size) + ITEM_TYPE_GROUP_HEADER) {
                    metadata.itemType = ITEM_TYPE_IN_GROUP;
                    metadata.cursorPosition = ((position - listPosition) + cursorPosition) - 1;
                    return;
                } else {
                    listPosition += size + ITEM_TYPE_GROUP_HEADER;
                }
                cursorPosition += size;
            }
            metadata.itemType = 0;
            metadata.cursorPosition = (position - listPosition) + cursorPosition;
        }
    }

    public boolean isGroupHeader(int position) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        if (this.mPositionMetadata.itemType == ITEM_TYPE_GROUP_HEADER) {
            return true;
        }
        return false;
    }

    public int getGroupSize(int position) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        return this.mPositionMetadata.childCount;
    }

    public void toggleGroup(int position) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        if (this.mPositionMetadata.itemType != ITEM_TYPE_GROUP_HEADER) {
            throw new IllegalArgumentException("Not a group at position " + position);
        }
        long[] jArr;
        int access$100;
        if (this.mPositionMetadata.isExpanded) {
            jArr = this.mGroupMetadata;
            access$100 = this.mPositionMetadata.groupPosition;
            jArr[access$100] = jArr[access$100] & Long.MAX_VALUE;
        } else {
            jArr = this.mGroupMetadata;
            access$100 = this.mPositionMetadata.groupPosition;
            jArr[access$100] = jArr[access$100] | EXPANDED_GROUP_MASK;
        }
        resetCache();
        notifyDataSetChanged();
    }

    public int getViewTypeCount() {
        return 3;
    }

    public int getItemViewType(int position) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        return this.mPositionMetadata.itemType;
    }

    public Object getItem(int position) {
        if (this.mCursor == null) {
            return null;
        }
        obtainPositionMetadata(this.mPositionMetadata, position);
        if (this.mCursor.moveToPosition(this.mPositionMetadata.cursorPosition)) {
            return this.mCursor;
        }
        return null;
    }

    public long getItemId(int position) {
        if (getItem(position) != null) {
            return this.mCursor.getLong(this.mRowIdColumnIndex);
        }
        return -1;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        obtainPositionMetadata(this.mPositionMetadata, position);
        View view = convertView;
        if (view == null) {
            switch (this.mPositionMetadata.itemType) {
                case YellowPagePhone.TYPE_UNKNOWN /*0*/:
                    view = newStandAloneView(this.mContext, parent);
                    break;
                case ITEM_TYPE_GROUP_HEADER /*1*/:
                    view = newGroupView(this.mContext, parent);
                    break;
                case ITEM_TYPE_IN_GROUP /*2*/:
                    view = newChildView(this.mContext, parent);
                    break;
            }
        }
        this.mCursor.moveToPosition(this.mPositionMetadata.cursorPosition);
        switch (this.mPositionMetadata.itemType) {
            case YellowPagePhone.TYPE_UNKNOWN /*0*/:
                bindStandAloneView(view, this.mContext, this.mCursor);
                break;
            case ITEM_TYPE_GROUP_HEADER /*1*/:
                bindGroupView(view, this.mContext, this.mCursor, this.mPositionMetadata.childCount, this.mPositionMetadata.isExpanded);
                break;
            case ITEM_TYPE_IN_GROUP /*2*/:
                bindChildView(view, this.mContext, this.mCursor);
                break;
        }
        return view;
    }
}
