package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

class CircleImageView extends ImageView {
    private AnimationListener mListener;
    private int mShadowRadius;

    private class OvalShadow extends OvalShape {
        private int mCircleDiameter;
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;
        private int mShadowRadius;

        public OvalShadow(int shadowRadius, int circleDiameter) {
            this.mShadowPaint = new Paint();
            this.mShadowRadius = shadowRadius;
            this.mCircleDiameter = circleDiameter;
            this.mRadialGradient = new RadialGradient((float) (this.mCircleDiameter / 2), (float) (this.mCircleDiameter / 2), (float) this.mShadowRadius, new int[]{1023410176, 0}, null, TileMode.CLAMP);
            this.mShadowPaint.setShader(this.mRadialGradient);
        }

        public void draw(Canvas canvas, Paint paint) {
            int viewWidth = CircleImageView.this.getWidth();
            int viewHeight = CircleImageView.this.getHeight();
            canvas.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) ((this.mCircleDiameter / 2) + this.mShadowRadius), this.mShadowPaint);
            canvas.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) (this.mCircleDiameter / 2), paint);
        }
    }

    public CircleImageView(Context context, int color, float radius) {
        ShapeDrawable circle;
        super(context);
        float density = getContext().getResources().getDisplayMetrics().density;
        int diameter = (int) ((radius * density) * 2.0f);
        int shadowYOffset = (int) (1.75f * density);
        int shadowXOffset = (int) (0.0f * density);
        this.mShadowRadius = (int) (3.5f * density);
        if (elevationSupported()) {
            circle = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, 4.0f * density);
        } else {
            circle = new ShapeDrawable(new OvalShadow(this.mShadowRadius, diameter));
            ViewCompat.setLayerType(this, 1, circle.getPaint());
            circle.getPaint().setShadowLayer((float) this.mShadowRadius, (float) shadowXOffset, (float) shadowYOffset, 503316480);
            int padding = this.mShadowRadius;
            setPadding(padding, padding, padding, padding);
        }
        circle.getPaint().setColor(color);
        setBackgroundDrawable(circle);
    }

    private boolean elevationSupported() {
        return VERSION.SDK_INT >= 21;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!elevationSupported()) {
            setMeasuredDimension(getMeasuredWidth() + (this.mShadowRadius * 2), getMeasuredHeight() + (this.mShadowRadius * 2));
        }
    }

    public void setAnimationListener(AnimationListener listener) {
        this.mListener = listener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.mListener != null) {
            this.mListener.onAnimationStart(getAnimation());
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.mListener != null) {
            this.mListener.onAnimationEnd(getAnimation());
        }
    }

    public void setBackgroundColor(int colorRes) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(getResources().getColor(colorRes));
        }
    }
}
