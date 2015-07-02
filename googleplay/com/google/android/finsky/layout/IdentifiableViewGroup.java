package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.google.android.finsky.layout.play.Identifiable;

public abstract class IdentifiableViewGroup extends ViewGroup implements Identifiable {
    private String mIdentifier;

    public IdentifiableViewGroup(Context context) {
        super(context);
    }

    public IdentifiableViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }
}
