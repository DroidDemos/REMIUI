package com.google.android.finsky.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import com.google.android.finsky.utils.FinskyLog;
import java.util.ArrayList;

public class AggregatedAdapter<T extends BaseAdapter> extends BaseAdapter {
    private T[] mAdapters;
    private boolean mCachedAllItemsEnabled;
    private int mCachedCount;
    private boolean mCachedHasStableIds;
    private ArrayList<IndexTranslation> mCachedTranslations;

    private static class IndexTranslation {
        private ListAdapter targetAdapter;
        private int translatedIndex;

        private IndexTranslation(ListAdapter targetAdapter, int translatedIndex) {
            this.targetAdapter = targetAdapter;
            this.translatedIndex = translatedIndex;
        }
    }

    public AggregatedAdapter(T[] adapters) {
        this.mCachedCount = 0;
        this.mCachedAllItemsEnabled = true;
        this.mCachedHasStableIds = true;
        this.mAdapters = adapters;
    }

    public T[] getAdapters() {
        return this.mAdapters;
    }

    public boolean areAllItemsEnabled() {
        return this.mCachedAllItemsEnabled;
    }

    public boolean isEnabled(int position) {
        IndexTranslation translation = translate(position);
        return translation.targetAdapter.isEnabled(translation.translatedIndex);
    }

    public int getCount() {
        return this.mCachedCount;
    }

    public Object getItem(int position) {
        IndexTranslation translation = translate(position);
        return translation.targetAdapter.getItem(translation.translatedIndex);
    }

    public long getItemId(int position) {
        IndexTranslation translation = translate(position);
        return translation.targetAdapter.getItemId(translation.translatedIndex);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        IndexTranslation translation = translate(position);
        return translation.targetAdapter.getView(translation.translatedIndex, convertView, parent);
    }

    public int getItemViewType(int position) {
        IndexTranslation translation = translate(position);
        return translation.targetAdapter.getItemViewType(translation.translatedIndex);
    }

    public boolean hasStableIds() {
        return this.mCachedHasStableIds;
    }

    public boolean isEmpty() {
        return this.mCachedCount == 0;
    }

    private IndexTranslation translate(int position) {
        return (IndexTranslation) this.mCachedTranslations.get(position);
    }

    public void notifyDataSetChanged() {
        refreshCachedData();
    }

    public void notifyDataSetInvalidated() {
        refreshCachedData();
    }

    private synchronized void refreshCachedData() {
        int sum = 0;
        this.mCachedAllItemsEnabled = true;
        this.mCachedHasStableIds = true;
        this.mCachedTranslations = new ArrayList(numAdapters * 3);
        for (ListAdapter adapter : this.mAdapters) {
            boolean z;
            int adapterItems = adapter.getCount();
            sum += adapterItems;
            if (this.mCachedAllItemsEnabled && adapter.areAllItemsEnabled()) {
                z = true;
            } else {
                z = false;
            }
            this.mCachedAllItemsEnabled = z;
            if (this.mCachedHasStableIds && adapter.hasStableIds()) {
                z = true;
            } else {
                z = false;
            }
            this.mCachedHasStableIds = z;
            for (int offset = 0; offset < adapterItems; offset++) {
                this.mCachedTranslations.add(new IndexTranslation(adapter, offset));
            }
        }
        this.mCachedCount = sum;
    }

    public void dumpState() {
        FinskyLog.d("****** AGGREGATED ADAPTER START ******", new Object[0]);
        StringBuilder countBuilder = new StringBuilder("Total items: ");
        countBuilder.append(getCount());
        countBuilder.append(" [ ");
        for (T adapter : this.mAdapters) {
            countBuilder.append(adapter.getCount());
            countBuilder.append(" ");
        }
        countBuilder.append("]");
        FinskyLog.d(countBuilder.toString(), new Object[0]);
        StringBuilder viewTypeBuilder = new StringBuilder("Index translation: ");
        for (int i = 0; i < getCount(); i++) {
            viewTypeBuilder.append(i);
            viewTypeBuilder.append(":");
            viewTypeBuilder.append(getItemViewType(i));
            viewTypeBuilder.append(" ");
        }
        FinskyLog.d(viewTypeBuilder.toString(), new Object[0]);
        FinskyLog.d("****** AGGREGATED ADAPTER  END  ******", new Object[0]);
    }
}
