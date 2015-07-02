package com.miui.yellowpage.widget.pulltorefresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView.ScaleType;
import com.miui.yellowpage.R;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Orientation;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

@SuppressLint({"ViewConstructor"})
/* compiled from: FlipLoadingLayout */
public class i extends k {
    private final Animation vy;
    private final Animation vz;

    public i(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
        super(context, mode, orientation, typedArray);
        int i = mode == Mode.PULL_FROM_START ? -180 : 180;
        this.vy = new RotateAnimation(0.0f, (float) i, 1, 0.5f, 1, 0.5f);
        this.vy.setInterpolator(Bk);
        this.vy.setDuration(150);
        this.vy.setFillAfter(true);
        this.vz = new RotateAnimation((float) i, 0.0f, 1, 0.5f, 1, 0.5f);
        this.vz.setInterpolator(Bk);
        this.vz.setDuration(150);
        this.vz.setFillAfter(true);
    }

    protected void b(Drawable drawable) {
        if (drawable != null) {
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            LayoutParams layoutParams = this.Bm.getLayoutParams();
            int max = Math.max(intrinsicHeight, intrinsicWidth);
            layoutParams.height = max;
            layoutParams.width = max;
            this.Bm.requestLayout();
            this.Bm.setScaleType(ScaleType.MATRIX);
            Matrix matrix = new Matrix();
            matrix.postTranslate(((float) (layoutParams.width - intrinsicWidth)) / 2.0f, ((float) (layoutParams.height - intrinsicHeight)) / 2.0f);
            matrix.postRotate(fi(), ((float) layoutParams.width) / 2.0f, ((float) layoutParams.height) / 2.0f);
            this.Bm.setImageMatrix(matrix);
        }
    }

    protected void c(float f) {
    }

    protected void fd() {
        if (this.vy == this.Bm.getAnimation()) {
            this.Bm.startAnimation(this.vz);
        }
    }

    protected void fe() {
        this.Bm.clearAnimation();
        this.Bm.setVisibility(4);
        this.Bn.setVisibility(0);
    }

    protected void ff() {
        this.Bm.startAnimation(this.vy);
    }

    protected void fg() {
        this.Bm.clearAnimation();
        this.Bn.setVisibility(8);
        this.Bm.setVisibility(0);
    }

    protected int fh() {
        return R.drawable.default_ptr_flip;
    }

    private float fi() {
        switch (l.gd[this.wN.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                if (this.Br == Orientation.HORIZONTAL) {
                    return 90.0f;
                }
                return 180.0f;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                if (this.Br == Orientation.HORIZONTAL) {
                    return 270.0f;
                }
                return 0.0f;
            default:
                return 0.0f;
        }
    }
}
