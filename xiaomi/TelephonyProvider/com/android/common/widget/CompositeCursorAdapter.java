package com.android.common.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class CompositeCursorAdapter extends BaseAdapter {
    private static final int INITIAL_CAPACITY = 2;
    private boolean mCacheValid;
    private final Context mContext;
    private int mCount;
    private boolean mNotificationNeeded;
    private boolean mNotificationsEnabled;
    private ArrayList<Partition> mPartitions;

    public static class Partition {
        int count;
        Cursor cursor;
        boolean hasHeader;
        int idColumnIndex;
        boolean showIfEmpty;

        public Partition(boolean showIfEmpty, boolean hasHeader) {
            this.showIfEmpty = showIfEmpty;
            this.hasHeader = hasHeader;
        }

        public boolean getShowIfEmpty() {
            return this.showIfEmpty;
        }

        public boolean getHasHeader() {
            return this.hasHeader;
        }
    }

    protected abstract void bindView(View view, int i, Cursor cursor, int i2);

    protected abstract View newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup);

    public CompositeCursorAdapter(Context context) {
        this(context, INITIAL_CAPACITY);
    }

    public CompositeCursorAdapter(Context context, int initialCapacity) {
        this.mCount = 0;
        this.mCacheValid = true;
        this.mNotificationsEnabled = true;
        this.mContext = context;
        this.mPartitions = new ArrayList();
    }

    public Context getContext() {
        return this.mContext;
    }

    public void addPartition(boolean showIfEmpty, boolean hasHeader) {
        addPartition(new Partition(showIfEmpty, hasHeader));
    }

    public void addPartition(Partition partition) {
        this.mPartitions.add(partition);
        invalidate();
        notifyDataSetChanged();
    }

    public void addPartition(int location, Partition partition) {
        this.mPartitions.add(location, partition);
        invalidate();
        notifyDataSetChanged();
    }

    public void removePartition(int partitionIndex) {
        Cursor cursor = ((Partition) this.mPartitions.get(partitionIndex)).cursor;
        if (!(cursor == null || cursor.isClosed())) {
            cursor.close();
        }
        this.mPartitions.remove(partitionIndex);
        invalidate();
        notifyDataSetChanged();
    }

    public void clearPartitions() {
        Iterator i$ = this.mPartitions.iterator();
        while (i$.hasNext()) {
            ((Partition) i$.next()).cursor = null;
        }
        invalidate();
        notifyDataSetChanged();
    }

    public void close() {
        Iterator i$ = this.mPartitions.iterator();
        while (i$.hasNext()) {
            Cursor cursor = ((Partition) i$.next()).cursor;
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
        }
        this.mPartitions.clear();
        invalidate();
        notifyDataSetChanged();
    }

    public void setHasHeader(int partitionIndex, boolean flag) {
        ((Partition) this.mPartitions.get(partitionIndex)).hasHeader = flag;
        invalidate();
    }

    public void setShowIfEmpty(int partitionIndex, boolean flag) {
        ((Partition) this.mPartitions.get(partitionIndex)).showIfEmpty = flag;
        invalidate();
    }

    public Partition getPartition(int partitionIndex) {
        return (Partition) this.mPartitions.get(partitionIndex);
    }

    protected void invalidate() {
        this.mCacheValid = false;
    }

    public int getPartitionCount() {
        return this.mPartitions.size();
    }

    protected void ensureCacheValid() {
        if (!this.mCacheValid) {
            this.mCount = 0;
            Iterator i$ = this.mPartitions.iterator();
            while (i$.hasNext()) {
                int count;
                Partition partition = (Partition) i$.next();
                Cursor cursor = partition.cursor;
                if (cursor != null) {
                    count = cursor.getCount();
                } else {
                    count = 0;
                }
                if (partition.hasHeader && (count != 0 || partition.showIfEmpty)) {
                    count++;
                }
                partition.count = count;
                this.mCount += count;
            }
            this.mCacheValid = true;
        }
    }

    public boolean hasHeader(int partition) {
        return ((Partition) this.mPartitions.get(partition)).hasHeader;
    }

    public int getCount() {
        ensureCacheValid();
        return this.mCount;
    }

    public Cursor getCursor(int partition) {
        return ((Partition) this.mPartitions.get(partition)).cursor;
    }

    public void changeCursor(int partition, Cursor cursor) {
        Cursor prevCursor = ((Partition) this.mPartitions.get(partition)).cursor;
        if (prevCursor != cursor) {
            if (!(prevCursor == null || prevCursor.isClosed())) {
                prevCursor.close();
            }
            ((Partition) this.mPartitions.get(partition)).cursor = cursor;
            if (cursor != null) {
                ((Partition) this.mPartitions.get(partition)).idColumnIndex = cursor.getColumnIndex("_id");
            }
            invalidate();
            notifyDataSetChanged();
        }
    }

    public boolean isPartitionEmpty(int partition) {
        Cursor cursor = ((Partition) this.mPartitions.get(partition)).cursor;
        return cursor == null || cursor.getCount() == 0;
    }

    public int getPartitionForPosition(int position) {
        ensureCacheValid();
        int start = 0;
        int n = this.mPartitions.size();
        for (int i = 0; i < n; i++) {
            int end = start + ((Partition) this.mPartitions.get(i)).count;
            if (position >= start && position < end) {
                return i;
            }
            start = end;
        }
        return -1;
    }

    public int getOffsetInPartition(int position) {
        ensureCacheValid();
        int start = 0;
        Iterator i$ = this.mPartitions.iterator();
        while (i$.hasNext()) {
            Partition partition = (Partition) i$.next();
            int end = start + partition.count;
            if (position < start || position >= end) {
                start = end;
            } else {
                int offset = position - start;
                if (partition.hasHeader) {
                    return offset - 1;
                }
                return offset;
            }
        }
        return -1;
    }

    public int getPositionForPartition(int partition) {
        ensureCacheValid();
        int position = 0;
        for (int i = 0; i < partition; i++) {
            position += ((Partition) this.mPartitions.get(i)).count;
        }
        return position;
    }

    public int getViewTypeCount() {
        return getItemViewTypeCount() + 1;
    }

    public int getItemViewTypeCount() {
        return 1;
    }

    protected int getItemViewType(int partition, int position) {
        return 1;
    }

    public int getItemViewType(int position) {
        ensureCacheValid();
        int start = 0;
        int i = 0;
        int n = this.mPartitions.size();
        while (i < n) {
            int end = start + ((Partition) this.mPartitions.get(i)).count;
            if (position < start || position >= end) {
                start = end;
                i++;
            } else {
                int offset = position - start;
                if (((Partition) this.mPartitions.get(i)).hasHeader) {
                    offset--;
                }
                if (offset == -1) {
                    return -1;
                }
                return getItemViewType(i, offset);
            }
        }
        throw new ArrayIndexOutOfBoundsException(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ensureCacheValid();
        int start = 0;
        int i = 0;
        int n = this.mPartitions.size();
        while (i < n) {
            int end = start + ((Partition) this.mPartitions.get(i)).count;
            if (position < start || position >= end) {
                start = end;
                i++;
            } else {
                View view;
                int offset = position - start;
                if (((Partition) this.mPartitions.get(i)).hasHeader) {
                    offset--;
                }
                if (offset == -1) {
                    view = getHeaderView(i, ((Partition) this.mPartitions.get(i)).cursor, convertView, parent);
                } else if (((Partition) this.mPartitions.get(i)).cursor.moveToPosition(offset)) {
                    view = getView(i, ((Partition) this.mPartitions.get(i)).cursor, offset, convertView, parent);
                } else {
                    throw new IllegalStateException("Couldn't move cursor to position " + offset);
                }
                if (view != null) {
                    return view;
                }
                throw new NullPointerException("View should not be null, partition: " + i + " position: " + offset);
            }
        }
        throw new ArrayIndexOutOfBoundsException(position);
    }

    protected View getHeaderView(int partition, Cursor cursor, View convertView, ViewGroup parent) {
        View view = convertView != null ? convertView : newHeaderView(this.mContext, partition, cursor, parent);
        bindHeaderView(view, partition, cursor);
        return view;
    }

    protected View newHeaderView(Context context, int partition, Cursor cursor, ViewGroup parent) {
        return null;
    }

    protected void bindHeaderView(View view, int partition, Cursor cursor) {
    }

    protected View getView(int partition, Cursor cursor, int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = newView(this.mContext, partition, cursor, position, parent);
        }
        bindView(view, partition, cursor, position);
        return view;
    }

    public Object getItem(int position) {
        ensureCacheValid();
        int start = 0;
        Iterator i$ = this.mPartitions.iterator();
        while (i$.hasNext()) {
            Partition mPartition = (Partition) i$.next();
            int end = start + mPartition.count;
            if (position < start || position >= end) {
                start = end;
            } else {
                int offset = position - start;
                if (mPartition.hasHeader) {
                    offset--;
                }
                if (offset == -1) {
                    return null;
                }
                Object cursor = mPartition.cursor;
                cursor.moveToPosition(offset);
                return cursor;
            }
        }
        return null;
    }

    public long getItemId(int position) {
        ensureCacheValid();
        int start = 0;
        Iterator i$ = this.mPartitions.iterator();
        while (i$.hasNext()) {
            Partition mPartition = (Partition) i$.next();
            int end = start + mPartition.count;
            if (position < start || position >= end) {
                start = end;
            } else {
                int offset = position - start;
                if (mPartition.hasHeader) {
                    offset--;
                }
                if (offset == -1 || mPartition.idColumnIndex == -1) {
                    return 0;
                }
                Cursor cursor = mPartition.cursor;
                if (cursor == null || cursor.isClosed() || !cursor.moveToPosition(offset)) {
                    return 0;
                }
                return cursor.getLong(mPartition.idColumnIndex);
            }
        }
        return 0;
    }

    public boolean areAllItemsEnabled() {
        Iterator i$ = this.mPartitions.iterator();
        while (i$.hasNext()) {
            if (((Partition) i$.next()).hasHeader) {
                return false;
            }
        }
        return true;
    }

    public boolean isEnabled(int position) {
        ensureCacheValid();
        int start = 0;
        int i = 0;
        int n = this.mPartitions.size();
        while (i < n) {
            int end = start + ((Partition) this.mPartitions.get(i)).count;
            if (position < start || position >= end) {
                start = end;
                i++;
            } else {
                int offset = position - start;
                if (((Partition) this.mPartitions.get(i)).hasHeader && offset == 0) {
                    return false;
                }
                return isEnabled(i, offset);
            }
        }
        return false;
    }

    protected boolean isEnabled(int partition, int position) {
        return true;
    }

    public void setNotificationsEnabled(boolean flag) {
        this.mNotificationsEnabled = flag;
        if (flag && this.mNotificationNeeded) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        if (this.mNotificationsEnabled) {
            this.mNotificationNeeded = false;
            super.notifyDataSetChanged();
            return;
        }
        this.mNotificationNeeded = true;
    }
}
