package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.google.android.finsky.layout.play.Identifiable;

public class IdentifiableTextView extends TextView implements Identifiable {
    private String mIdentifier;

    public IdentifiableTextView(Context context) {
        super(context);
    }

    public IdentifiableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }
}
