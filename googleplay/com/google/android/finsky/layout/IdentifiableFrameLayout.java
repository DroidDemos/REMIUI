package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.finsky.layout.play.Identifiable;

public class IdentifiableFrameLayout extends FrameLayout implements Identifiable {
    private String mIdentifier;

    public IdentifiableFrameLayout(Context context) {
        super(context);
    }

    public IdentifiableFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }
}
