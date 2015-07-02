package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.google.android.finsky.layout.play.Identifiable;

public class IdentifiableLinearLayout extends LinearLayout implements Identifiable {
    private String mIdentifier;

    public IdentifiableLinearLayout(Context context) {
        super(context);
    }

    public IdentifiableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }
}
