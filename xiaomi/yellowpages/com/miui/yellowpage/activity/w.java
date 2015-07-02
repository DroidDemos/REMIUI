package com.miui.yellowpage.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.miui.yellowpage.base.model.Module.Action;

/* compiled from: MultiModuleIntentActivity */
class w extends ArrayAdapter {
    final /* synthetic */ MultiModuleIntentActivity mL;

    w(MultiModuleIntentActivity multiModuleIntentActivity, Context context, int i) {
        this.mL = multiModuleIntentActivity;
        super(context, i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mL).inflate(17367043, null);
        }
        ((TextView) view.findViewById(16908308)).setText(((Action) getItem(i)).getName());
        return view;
    }
}
