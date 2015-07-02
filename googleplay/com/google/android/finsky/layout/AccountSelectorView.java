package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.finsky.FinskyApp;

public class AccountSelectorView extends IdentifiableTextView {
    public AccountSelectorView(Context context) {
        this(context, null);
    }

    public AccountSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void configure() {
        setText(FinskyApp.get().getCurrentAccountName());
    }
}
