package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class TintTypedArray {
    private final Context mContext;
    private TintManager mTintManager;
    private final TypedArray mWrapped;

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet set, int[] attrs) {
        return new TintTypedArray(context, context.obtainStyledAttributes(set, attrs));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes) {
        return new TintTypedArray(context, context.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes));
    }

    private TintTypedArray(Context context, TypedArray array) {
        this.mContext = context;
        this.mWrapped = array;
    }

    public Drawable getDrawable(int index) {
        if (this.mWrapped.hasValue(index)) {
            int resourceId = this.mWrapped.getResourceId(index, 0);
            if (resourceId != 0) {
                return getTintManager().getDrawable(resourceId);
            }
        }
        return this.mWrapped.getDrawable(index);
    }

    public int length() {
        return this.mWrapped.length();
    }

    public CharSequence getText(int index) {
        return this.mWrapped.getText(index);
    }

    public String getString(int index) {
        return this.mWrapped.getString(index);
    }

    public boolean getBoolean(int index, boolean defValue) {
        return this.mWrapped.getBoolean(index, defValue);
    }

    public int getInt(int index, int defValue) {
        return this.mWrapped.getInt(index, defValue);
    }

    public float getFloat(int index, float defValue) {
        return this.mWrapped.getFloat(index, defValue);
    }

    public int getInteger(int index, int defValue) {
        return this.mWrapped.getInteger(index, defValue);
    }

    public int getDimensionPixelOffset(int index, int defValue) {
        return this.mWrapped.getDimensionPixelOffset(index, defValue);
    }

    public int getDimensionPixelSize(int index, int defValue) {
        return this.mWrapped.getDimensionPixelSize(index, defValue);
    }

    public int getLayoutDimension(int index, int defValue) {
        return this.mWrapped.getLayoutDimension(index, defValue);
    }

    public int getResourceId(int index, int defValue) {
        return this.mWrapped.getResourceId(index, defValue);
    }

    public boolean hasValue(int index) {
        return this.mWrapped.hasValue(index);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }

    public TintManager getTintManager() {
        if (this.mTintManager == null) {
            this.mTintManager = new TintManager(this.mContext);
        }
        return this.mTintManager;
    }
}
