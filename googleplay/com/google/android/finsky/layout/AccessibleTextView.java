package com.google.android.finsky.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;

public class AccessibleTextView extends TextView {
    public AccessibleTextView(Context context) {
        super(context);
    }

    public AccessibleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AccessibleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        if (isShown()) {
            CharSequence text = getText();
            if (TextUtils.isEmpty(text)) {
                text = getHint();
            }
            if (!TextUtils.isEmpty(text)) {
                if (text.length() > 500) {
                    text = text.subSequence(0, 501);
                }
                event.getText().add(text.toString().toLowerCase());
            }
        }
        return false;
    }
}
