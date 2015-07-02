package com.google.android.libraries.bind.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.google.android.libraries.bind.R;
import com.google.android.libraries.bind.data.Bound;
import com.google.android.libraries.bind.data.BoundHelper;
import com.google.android.libraries.bind.data.Data;

public class BoundTextView extends TextView implements Bound {
    private Integer bindDrawableEndKey;
    private Integer bindDrawableStartKey;
    private Integer bindTextColorKey;
    private Integer bindTextKey;
    private final BoundHelper boundHelper;

    public BoundTextView(Context context) {
        this(context, null, 0);
    }

    public BoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoundTextView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs, defStyle, 0);
    }

    public BoundTextView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle);
        this.boundHelper = makeBoundHelper(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BoundTextView, defStyle, defStyleRes);
        if (isInEditMode()) {
            String editModeText = a.getString(R.styleable.BoundTextView_bind__editModeText);
            if (editModeText != null) {
                setText(editModeText);
            }
        }
        this.bindTextKey = BoundHelper.getInteger(a, R.styleable.BoundTextView_bindText);
        this.bindTextColorKey = BoundHelper.getInteger(a, R.styleable.BoundTextView_bindTextColor);
        this.bindDrawableStartKey = BoundHelper.getInteger(a, R.styleable.BoundTextView_bindDrawableStart);
        this.bindDrawableEndKey = BoundHelper.getInteger(a, R.styleable.BoundTextView_bindDrawableEnd);
        a.recycle();
    }

    protected BoundHelper makeBoundHelper(Context context, AttributeSet attrs) {
        return new BoundHelper(context, attrs, this);
    }

    public void updateBoundData(Data data) {
        this.boundHelper.updateBoundData(data);
        if (this.bindTextKey != null) {
            if (data == null) {
                setText(null);
            } else {
                Object text = data.get(this.bindTextKey.intValue());
                if (text instanceof SpannableString) {
                    setText((SpannableString) text, BufferType.SPANNABLE);
                } else {
                    setText(text == null ? null : text.toString());
                }
            }
        }
        if (this.bindTextColorKey != null) {
            Object textColor = data == null ? null : data.get(this.bindTextColorKey.intValue());
            if (textColor == null) {
                setTextColor(-1);
            } else if (textColor instanceof ColorStateList) {
                setTextColor((ColorStateList) textColor);
            } else {
                setTextColor(getContext().getResources().getColor(((Integer) textColor).intValue()));
            }
        }
        if (this.bindDrawableStartKey != null || this.bindDrawableEndKey != null) {
            Drawable[] drawables = getCompoundDrawables();
            updateBoundDrawableIfSpecified(drawables, data, this.bindDrawableStartKey, true);
            updateBoundDrawableIfSpecified(drawables, data, this.bindDrawableEndKey, false);
            setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
        }
    }

    private void updateBoundDrawableIfSpecified(Drawable[] drawables, Data data, Integer bindDrawableKey, boolean isStart) {
        if (bindDrawableKey != null) {
            Integer optDrawableId = data == null ? null : data.getAsInteger(bindDrawableKey.intValue());
            int index = (ViewCompat.getLayoutDirection(this) == 0 && isStart) ? 0 : 2;
            drawables[index] = optDrawableId == null ? null : getContext().getResources().getDrawable(optDrawableId.intValue());
        }
    }
}
