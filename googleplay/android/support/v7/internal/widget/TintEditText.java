package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class TintEditText extends EditText {
    private static final int[] TINT_ATTRS;

    static {
        TINT_ATTRS = new int[]{16842964};
    }

    public TintEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 16842862);
    }

    public TintEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, TINT_ATTRS, defStyleAttr, 0);
        setBackgroundDrawable(a.getDrawable(0));
        a.recycle();
    }
}
