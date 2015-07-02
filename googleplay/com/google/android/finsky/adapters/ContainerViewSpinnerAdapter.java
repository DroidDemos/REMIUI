package com.google.android.finsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.protos.Containers.ContainerView;

public class ContainerViewSpinnerAdapter extends ArrayAdapter<ContainerView> {
    public ContainerViewSpinnerAdapter(Context context, ContainerView[] containerViews) {
        super(context, 17367049, containerViews);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_spinner_selected_item, parent, false);
        ((TextView) convertView).setText(((ContainerView) getItem(position)).title);
        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(17367049, parent, false);
        }
        ((TextView) convertView.findViewById(16908308)).setText(((ContainerView) getItem(position)).title);
        return convertView;
    }
}
