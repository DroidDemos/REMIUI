package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.View;

public class NativeActionModeAwareLayout extends ContentFrameLayout {
    private OnActionModeForChildListener mActionModeForChildListener;

    public interface OnActionModeForChildListener {
        ActionMode startActionModeForChild(View view, Callback callback);
    }

    public NativeActionModeAwareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setActionModeForChildListener(OnActionModeForChildListener listener) {
        this.mActionModeForChildListener = listener;
    }

    public ActionMode startActionModeForChild(View originalView, Callback callback) {
        if (this.mActionModeForChildListener != null) {
            return this.mActionModeForChildListener.startActionModeForChild(originalView, callback);
        }
        return super.startActionModeForChild(originalView, callback);
    }
}
