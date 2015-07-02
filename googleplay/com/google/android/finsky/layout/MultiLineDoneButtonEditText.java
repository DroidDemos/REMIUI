package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class MultiLineDoneButtonEditText extends EditText {
    public MultiLineDoneButtonEditText(Context context) {
        super(context);
    }

    public MultiLineDoneButtonEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        InputConnection inputConnection = super.onCreateInputConnection(outAttrs);
        outAttrs.imeOptions = 6;
        return inputConnection;
    }
}
