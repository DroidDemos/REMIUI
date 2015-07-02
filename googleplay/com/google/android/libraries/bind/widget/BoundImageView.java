package com.google.android.libraries.bind.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.libraries.bind.R;
import com.google.android.libraries.bind.data.Bound;
import com.google.android.libraries.bind.data.BoundHelper;
import com.google.android.libraries.bind.data.Data;
import com.google.android.libraries.bind.util.Util;

public class BoundImageView extends ImageView implements Bound {
    private Integer bindDrawableKey;
    private Integer bindImageUriKey;
    private final BoundHelper boundHelper;
    private Integer currentDrawableRef;

    public BoundImageView(Context context) {
        this(context, null);
    }

    public BoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.boundHelper = new BoundHelper(context, attrs, this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BoundImageView, defStyle, 0);
        this.bindImageUriKey = BoundHelper.getInteger(a, R.styleable.BoundImageView_bindImageUri);
        this.bindDrawableKey = BoundHelper.getInteger(a, R.styleable.BoundImageView_bindDrawable);
        a.recycle();
    }

    public void updateBoundData(Data data) {
        this.boundHelper.updateBoundData(data);
        if (this.bindImageUriKey != null) {
            Object imageUri = data.get(this.bindImageUriKey.intValue());
            if (imageUri instanceof Uri) {
                setImageURI((Uri) imageUri);
            } else {
                setImageURI(null);
            }
        }
        if (this.bindDrawableKey != null) {
            Integer drawableRef = data == null ? null : data.getAsInteger(this.bindDrawableKey.intValue());
            if (!Util.objectsEqual(this.currentDrawableRef, drawableRef)) {
                this.currentDrawableRef = drawableRef;
                setImageDrawable(drawableRef == null ? null : getContext().getResources().getDrawable(drawableRef.intValue()));
            }
        }
    }
}
