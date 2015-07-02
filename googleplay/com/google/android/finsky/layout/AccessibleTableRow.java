package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TableRow;

public class AccessibleTableRow extends TableRow {
    public AccessibleTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AccessibleTableRow(Context context) {
        super(context);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        boolean result = super.dispatchPopulateAccessibilityEvent(event);
        event.getText().clear();
        return result;
    }
}
