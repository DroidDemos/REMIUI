package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.google.android.finsky.layout.play.Identifiable;

public class IdentifiableRelativeLayout extends RelativeLayout implements Identifiable {
    private String mIdentifier;

    public IdentifiableRelativeLayout(Context context) {
        super(context);
    }

    public IdentifiableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }
}
