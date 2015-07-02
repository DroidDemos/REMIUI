package com.google.android.finsky.adapters;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public class EmptyRecyclerViewAdapter extends Adapter {
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getItemCount() {
        return 0;
    }
}
