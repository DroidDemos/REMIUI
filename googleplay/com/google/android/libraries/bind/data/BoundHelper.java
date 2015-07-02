package com.google.android.libraries.bind.data;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.libraries.bind.R;
import com.google.android.libraries.bind.logging.Logd;

public class BoundHelper {
    private static final Logd LOGD;
    public final Integer bindBackgroundKey;
    public final Integer bindContentDescriptionKey;
    public final Integer bindEnabledKey;
    public final Integer bindInvisibilityKey;
    public final Integer bindMinHeightKey;
    public final Integer bindOnClickListenerKey;
    public final Integer bindPaddingTopKey;
    public final Integer bindTransitionNameKey;
    public final Integer bindVisibilityKey;
    protected final View view;

    static {
        LOGD = Logd.get(BoundHelper.class);
    }

    public BoundHelper(Context context, AttributeSet attrs, View view) {
        this.view = view;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BoundView);
        this.bindBackgroundKey = getInteger(a, R.styleable.BoundView_bindBackground);
        this.bindContentDescriptionKey = getInteger(a, R.styleable.BoundView_bindContentDescription);
        this.bindEnabledKey = getInteger(a, R.styleable.BoundView_bindEnabled);
        this.bindOnClickListenerKey = getInteger(a, R.styleable.BoundView_bindOnClickListener);
        this.bindInvisibilityKey = getInteger(a, R.styleable.BoundView_bindInvisibility);
        this.bindMinHeightKey = getInteger(a, R.styleable.BoundView_bindMinHeight);
        this.bindPaddingTopKey = getInteger(a, R.styleable.BoundView_bindPaddingTop);
        this.bindVisibilityKey = getInteger(a, R.styleable.BoundView_bindVisibility);
        this.bindTransitionNameKey = getInteger(a, R.styleable.BoundView_bindTransitionName);
        a.recycle();
    }

    public void updateBoundData(Data data) {
        bindBackground(this.view, this.bindBackgroundKey, data);
        bindContentDescription(this.view, this.bindContentDescriptionKey, data);
        bindEnabled(this.view, this.bindEnabledKey, data);
        bindInvisibility(this.view, this.bindInvisibilityKey, data);
        bindMinHeight(this.view, this.bindMinHeightKey, data);
        bindOnClickListener(this.view, this.bindOnClickListenerKey, data);
        bindPaddingTop(this.view, this.bindPaddingTopKey, data);
        bindTransitionName(this.view, this.bindTransitionNameKey, data);
        bindVisibility(this.view, this.bindVisibilityKey, data);
    }

    public static Integer getInteger(TypedArray a, int attr) {
        int value = a.getResourceId(attr, Integer.MAX_VALUE);
        return value == Integer.MAX_VALUE ? null : Integer.valueOf(value);
    }

    public static void bindBackground(View view, Integer bindBackgroundKey, Data data) {
        if (bindBackgroundKey != null) {
            Object background = data == null ? null : data.get(bindBackgroundKey.intValue());
            if (background == null) {
                view.setBackgroundResource(0);
            } else if (background instanceof Integer) {
                view.setBackgroundResource(((Integer) background).intValue());
            } else if (background instanceof Drawable) {
                view.setBackgroundDrawable((Drawable) background);
            } else {
                LOGD.e("Unrecognized bound background for key: %s", bindBackgroundKey);
            }
        }
    }

    public static void bindContentDescription(View view, Integer bindContentDescriptionKey, Data data) {
        if (bindContentDescriptionKey != null) {
            view.setContentDescription(data == null ? null : (CharSequence) data.get(bindContentDescriptionKey.intValue()));
        }
    }

    public static void bindTransitionName(View view, Integer bindTransitionNameKey, Data data) {
        if (bindTransitionNameKey != null && VERSION.SDK_INT >= 21) {
            view.setTransitionName(data == null ? null : data.getAsString(bindTransitionNameKey.intValue()));
        }
    }

    public static void bindEnabled(View view, Integer bindEnabledKey, Data data) {
        if (bindEnabledKey != null) {
            boolean enabled = (data == null || !data.containsKey(bindEnabledKey.intValue()) || data.get(bindEnabledKey.intValue()).equals(Boolean.FALSE)) ? false : true;
            view.setEnabled(enabled);
        }
    }

    public static void bindMinHeight(View view, Integer bindMinHeightKey, Data data) {
        int minHeight = 0;
        if (bindMinHeightKey != null) {
            Number optMinHeight = data == null ? Integer.valueOf(0) : (Number) data.get(bindMinHeightKey.intValue());
            if (optMinHeight != null) {
                minHeight = optMinHeight.intValue();
            }
            view.setMinimumHeight(minHeight);
        }
    }

    public static void bindOnClickListener(View view, Integer bindOnClickListenerKey, Data data) {
        if (bindOnClickListenerKey != null) {
            view.setOnClickListener(data == null ? null : (OnClickListener) data.get(bindOnClickListenerKey.intValue()));
        }
    }

    public static void bindPaddingTop(View view, Integer bindPaddingTopKey, Data data) {
        int paddingTop = 0;
        if (bindPaddingTopKey != null) {
            Number optPaddingTop = data == null ? Integer.valueOf(0) : (Number) data.get(bindPaddingTopKey.intValue());
            if (optPaddingTop != null) {
                paddingTop = optPaddingTop.intValue();
            }
            int paddingBottom = view.getPaddingBottom();
            if (VERSION.SDK_INT < 17 || !view.isPaddingRelative()) {
                view.setPadding(view.getPaddingLeft(), paddingTop, view.getPaddingRight(), paddingBottom);
            } else {
                view.setPaddingRelative(view.getPaddingStart(), paddingTop, view.getPaddingEnd(), paddingBottom);
            }
        }
    }

    public static void bindInvisibility(View view, Integer bindInvisibilityKey, Data data) {
        bindVisibility(view, bindInvisibilityKey, data, true);
    }

    public static void bindVisibility(View view, Integer bindVisibilityKey, Data data) {
        bindVisibility(view, bindVisibilityKey, data, false);
    }

    private static void bindVisibility(View view, Integer bindVisibilityKey, Data data, boolean negate) {
        if (bindVisibilityKey != null) {
            int visibility;
            if (data == null || !data.containsKey(bindVisibilityKey.intValue())) {
                visibility = 8;
            } else if (!(data.get(bindVisibilityKey.intValue()) instanceof Boolean)) {
                visibility = 0;
            } else if (((Boolean) data.get(bindVisibilityKey.intValue())).booleanValue()) {
                visibility = 0;
            } else {
                visibility = 8;
            }
            if (negate) {
                if (visibility == 0) {
                    visibility = 8;
                } else {
                    visibility = 0;
                }
            }
            view.setVisibility(visibility);
        }
    }
}
