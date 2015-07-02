package com.miui.yellowpage.widget.pulltorefresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Orientation;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

@SuppressLint({"ViewConstructor"})
/* compiled from: LoadingLayout */
public abstract class k extends FrameLayout {
    static final Interpolator Bk;
    private View Bl;
    protected final ImageView Bm;
    protected final ProgressBar Bn;
    private boolean Bo;
    private final TextView Bp;
    private final TextView Bq;
    protected final Orientation Br;
    private CharSequence Bs;
    private CharSequence Bt;
    private CharSequence Bu;
    protected final Mode wN;

    protected abstract void b(Drawable drawable);

    protected abstract void c(float f);

    protected abstract void fd();

    protected abstract void fe();

    protected abstract void ff();

    protected abstract void fg();

    protected abstract int fh();

    static {
        Bk = new LinearInterpolator();
    }

    public k(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
        Drawable drawable;
        ColorStateList colorStateList;
        super(context);
        this.wN = mode;
        this.Br = orientation;
        int i = b.gc[orientation.ordinal()];
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical, this);
        this.Bl = findViewById(R.id.fl_inner);
        this.Bp = (TextView) this.Bl.findViewById(R.id.pull_to_refresh_text);
        this.Bn = (ProgressBar) this.Bl.findViewById(R.id.pull_to_refresh_progress);
        this.Bq = (TextView) this.Bl.findViewById(R.id.pull_to_refresh_sub_text);
        this.Bm = (ImageView) this.Bl.findViewById(R.id.pull_to_refresh_image);
        LayoutParams layoutParams = (LayoutParams) this.Bl.getLayoutParams();
        int i2 = b.gd[mode.ordinal()];
        layoutParams.gravity = orientation == Orientation.VERTICAL ? 80 : 5;
        this.Bs = context.getString(R.string.pull_to_refresh_pull_label);
        this.Bt = context.getString(R.string.pull_to_refresh_refreshing_label);
        this.Bu = context.getString(R.string.pull_to_refresh_release_label);
        if (typedArray.hasValue(1)) {
            drawable = typedArray.getDrawable(1);
            if (drawable != null) {
                f.a((View) this, drawable);
            }
        }
        if (typedArray.hasValue(10)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(10, typedValue);
            S(typedValue.data);
        }
        if (typedArray.hasValue(11)) {
            typedValue = new TypedValue();
            typedArray.getValue(11, typedValue);
            R(typedValue.data);
        }
        if (typedArray.hasValue(2)) {
            colorStateList = typedArray.getColorStateList(2);
            if (colorStateList != null) {
                setTextColor(colorStateList);
            }
        }
        if (typedArray.hasValue(3)) {
            colorStateList = typedArray.getColorStateList(3);
            if (colorStateList != null) {
                a(colorStateList);
            }
        }
        drawable = null;
        if (typedArray.hasValue(6)) {
            drawable = typedArray.getDrawable(6);
        }
        switch (b.gd[mode.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                if (!typedArray.hasValue(8)) {
                    if (typedArray.hasValue(19)) {
                        drawable = typedArray.getDrawable(19);
                        break;
                    }
                }
                drawable = typedArray.getDrawable(8);
                break;
                break;
            default:
                if (!typedArray.hasValue(7)) {
                    if (typedArray.hasValue(18)) {
                        drawable = typedArray.getDrawable(18);
                        break;
                    }
                }
                drawable = typedArray.getDrawable(7);
                break;
                break;
        }
        if (drawable == null) {
            drawable = context.getResources().getDrawable(fh());
        }
        d(drawable);
        reset();
    }

    public final void setHeight(int i) {
        getLayoutParams().height = i;
        requestLayout();
    }

    public final void setWidth(int i) {
        getLayoutParams().width = i;
        requestLayout();
    }

    public final int gG() {
        switch (b.gc[this.Br.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return this.Bl.getWidth();
            default:
                return this.Bl.getHeight();
        }
    }

    public final void onPull(float f) {
        if (!this.Bo) {
            c(f);
        }
    }

    public final void gH() {
        if (this.Bp != null) {
            this.Bp.setText(this.Bs);
        }
        fd();
    }

    public final void gI() {
        if (this.Bp != null) {
            this.Bp.setText(this.Bt);
        }
        if (this.Bo) {
            ((AnimationDrawable) this.Bm.getDrawable()).start();
        } else {
            fe();
        }
        if (this.Bq != null) {
            this.Bq.setVisibility(8);
        }
    }

    public final void gJ() {
        if (this.Bp != null) {
            this.Bp.setText(this.Bu);
        }
        ff();
    }

    public final void reset() {
        if (this.Bp != null) {
            this.Bp.setText(this.Bs);
        }
        this.Bm.setVisibility(0);
        if (this.Bo) {
            ((AnimationDrawable) this.Bm.getDrawable()).stop();
        } else {
            fg();
        }
        if (this.Bq == null) {
            return;
        }
        if (TextUtils.isEmpty(this.Bq.getText())) {
            this.Bq.setVisibility(8);
        } else {
            this.Bq.setVisibility(0);
        }
    }

    public final void d(Drawable drawable) {
        this.Bm.setImageDrawable(drawable);
        this.Bo = drawable instanceof AnimationDrawable;
        b(drawable);
    }

    private void R(int i) {
        if (this.Bq != null) {
            this.Bq.setTextAppearance(getContext(), i);
        }
    }

    private void a(ColorStateList colorStateList) {
        if (this.Bq != null) {
            this.Bq.setTextColor(colorStateList);
        }
    }

    private void S(int i) {
        if (this.Bp != null) {
            this.Bp.setTextAppearance(getContext(), i);
        }
        if (this.Bq != null) {
            this.Bq.setTextAppearance(getContext(), i);
        }
    }

    private void setTextColor(ColorStateList colorStateList) {
        if (this.Bp != null) {
            this.Bp.setTextColor(colorStateList);
        }
        if (this.Bq != null) {
            this.Bq.setTextColor(colorStateList);
        }
    }
}
