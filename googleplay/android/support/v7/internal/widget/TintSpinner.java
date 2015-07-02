package android.support.v7.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import java.lang.reflect.Field;

public class TintSpinner extends Spinner {
    private static final int[] TINT_ATTRS;

    static {
        TINT_ATTRS = new int[]{16842964, 16843126};
    }

    public TintSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, 16842881);
    }

    public TintSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, TINT_ATTRS, defStyleAttr, 0);
        setBackgroundDrawable(a.getDrawable(0));
        if (a.hasValue(1)) {
            Drawable background = a.getDrawable(1);
            if (VERSION.SDK_INT >= 16) {
                setPopupBackgroundDrawable(background);
            } else if (VERSION.SDK_INT >= 11) {
                setPopupBackgroundDrawableV11(this, background);
            }
        }
        a.recycle();
    }

    private static void setPopupBackgroundDrawableV11(Spinner view, Drawable background) {
        try {
            Field popupField = Spinner.class.getDeclaredField("mPopup");
            popupField.setAccessible(true);
            Object popup = popupField.get(view);
            if (popup instanceof ListPopupWindow) {
                ((ListPopupWindow) popup).setBackgroundDrawable(background);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }
}
