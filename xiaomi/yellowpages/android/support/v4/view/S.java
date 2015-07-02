package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;

/* compiled from: ViewPager */
public class S extends LayoutParams {
    int Dp;
    public int gravity;
    public boolean isDecor;
    boolean needsMeasure;
    int position;
    float widthFactor;

    public S() {
        super(-1, -1);
        this.widthFactor = 0.0f;
    }

    public S(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.widthFactor = 0.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.jr);
        this.gravity = obtainStyledAttributes.getInteger(0, 48);
        obtainStyledAttributes.recycle();
    }
}
