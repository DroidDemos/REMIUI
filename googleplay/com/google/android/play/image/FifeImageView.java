package com.google.android.play.image;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.google.android.play.R;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;

public class FifeImageView extends ImageView implements BitmapLoadedHandler {
    private static boolean IS_HC_OR_ABOVE;
    private static boolean IS_ICS_OR_ABOVE;
    BitmapLoader mBitmapLoader;
    private BitmapTransformation mBitmapTransformation;
    private boolean mBlockLayout;
    private Drawable mDefaultDrawable;
    private float mDefaultZoom;
    private int mDesiredHeight;
    private int mDesiredWidth;
    private Animation mFadeInAnimation;
    private AnimationListener mFadeInAnimationListener;
    private AnimatorListener mFadeInAnimatorListener;
    private int mFadeInDuration;
    private final PointF mFocusPoint;
    private boolean mForegroundBoundsChanged;
    private Drawable mForegroundDrawable;
    private Handler mHandler;
    private boolean mHasDefaultZoom;
    private boolean mIsFrozen;
    boolean mIsLoaded;
    private final Matrix mMatrix;
    private boolean mMayBlockLayout;
    private OnLoadedListener mOnLoadedListener;
    private float mRequestScaleFactor;
    private final Rect mSelfBounds;
    private boolean mSupportsFifeUrlOptions;
    private boolean mToFadeInAfterLoad;
    private String mUrl;

    public interface OnLoadedListener {
        void onLoaded(FifeImageView fifeImageView, Bitmap bitmap);

        void onLoadedAndFadedIn(FifeImageView fifeImageView);
    }

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        IS_HC_OR_ABOVE = z;
        if (VERSION.SDK_INT < 14) {
            z2 = false;
        }
        IS_ICS_OR_ABOVE = z2;
    }

    public FifeImageView(Context context) {
        this(context, null);
    }

    public FifeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FifeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mSelfBounds = new Rect();
        this.mForegroundBoundsChanged = false;
        this.mFocusPoint = new PointF(0.5f, 0.5f);
        this.mMatrix = new Matrix();
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.FifeImageView);
        this.mToFadeInAfterLoad = viewAttrs.getBoolean(R.styleable.FifeImageView_fade_in_after_load, true);
        this.mMayBlockLayout = viewAttrs.getBoolean(R.styleable.FifeImageView_fixed_bounds, false);
        this.mHasDefaultZoom = viewAttrs.hasValue(R.styleable.FifeImageView_zoom);
        if (this.mHasDefaultZoom) {
            this.mDefaultZoom = viewAttrs.getFraction(R.styleable.FifeImageView_zoom, 1, 1, 1.0f);
            super.setScaleType(ScaleType.MATRIX);
        } else {
            this.mDefaultZoom = 1.0f;
        }
        Resources res = context.getResources();
        if (viewAttrs.getBoolean(R.styleable.FifeImageView_is_avatar, false)) {
            this.mBitmapTransformation = AvatarCropTransformation.getFullAvatarCrop(res);
        } else if (viewAttrs.getBoolean(R.styleable.FifeImageView_is_avatar_no_ring, false)) {
            this.mBitmapTransformation = AvatarCropTransformation.getNoRingAvatarCrop(res);
        }
        this.mRequestScaleFactor = viewAttrs.getFraction(R.styleable.FifeImageView_request_scale_factor, 1, 1, 1.0f);
        viewAttrs.recycle();
        TypedArray foregroundAttrs = context.obtainStyledAttributes(attrs, new int[]{16843017});
        Drawable foregroundDrawable = foregroundAttrs.getDrawable(0);
        if (foregroundDrawable != null) {
            setForeground(foregroundDrawable);
        }
        foregroundAttrs.recycle();
        this.mFadeInDuration = res.getInteger(17694720);
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void setDefaultDrawable(Drawable drawable) {
        this.mDefaultDrawable = drawable;
    }

    public void setForeground(Drawable d) {
        if (this.mForegroundDrawable != d) {
            if (this.mForegroundDrawable != null) {
                this.mForegroundDrawable.setCallback(null);
                unscheduleDrawable(this.mForegroundDrawable);
            }
            this.mForegroundDrawable = d;
            if (d != null) {
                setWillNotDraw(false);
                d.setCallback(this);
                if (d.isStateful()) {
                    d.setState(getDrawableState());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    public void setImage(String url, boolean supportsFifeUrlOptions, BitmapLoader bitmapLoader) {
        if (!url.equals(this.mUrl)) {
            this.mUrl = url;
            this.mSupportsFifeUrlOptions = supportsFifeUrlOptions;
            setLoaded(false, null);
        }
        this.mBitmapLoader = bitmapLoader;
        loadImageIfNecessary(false);
    }

    public void clearImage() {
        this.mDefaultDrawable = null;
        this.mUrl = null;
        BitmapContainer oldContainer = (BitmapContainer) getTag();
        if (oldContainer != null) {
            oldContainer.cancelRequest();
            setTag(null);
        }
        setImageBitmap(null);
        setLoaded(false, null);
    }

    public void setOnLoadedListener(OnLoadedListener onLoadedListener) {
        this.mOnLoadedListener = onLoadedListener;
    }

    public void setBitmapTransformation(BitmapTransformation bitmapTransformation) {
        this.mBitmapTransformation = bitmapTransformation;
        setWillNotDraw(false);
    }

    public void setHasFixedBounds(boolean hasFixedBounds) {
        this.mMayBlockLayout = hasFixedBounds;
    }

    public synchronized boolean isLoaded() {
        return this.mIsLoaded;
    }

    synchronized void setLoaded(boolean isLoaded, Bitmap bitmap) {
        this.mIsLoaded = isLoaded;
        if (this.mIsLoaded && this.mOnLoadedListener != null) {
            this.mOnLoadedListener.onLoaded(this, bitmap);
        }
    }

    public void setToFadeInAfterLoad(boolean toFadeInAfterLoad) {
        this.mToFadeInAfterLoad = toFadeInAfterLoad;
    }

    private void invokeOnFadeInDone() {
        if (this.mOnLoadedListener != null) {
            this.mOnLoadedListener.onLoadedAndFadedIn(this);
        }
    }

    public void onResponse(BitmapContainer result) {
        boolean z = true;
        if (!this.mIsFrozen) {
            Bitmap response = result.getBitmap();
            if (response == null) {
                setLoaded(false, null);
                return;
            }
            boolean fadeIn;
            if (this.mBitmapTransformation != null) {
                response = this.mBitmapTransformation.transform(response, getWidth(), getHeight());
            }
            if (this.mIsLoaded) {
                fadeIn = false;
            } else {
                fadeIn = true;
            }
            setImageBitmap(response);
            if (response == null) {
                z = false;
            }
            setLoaded(z, response);
            if (!fadeIn || !this.mToFadeInAfterLoad) {
                invokeOnFadeInDone();
            } else if (IS_ICS_OR_ABOVE) {
                fadeInIcs();
            } else {
                fadeInPreIcs();
            }
        }
    }

    private void fadeInPreIcs() {
        if (this.mFadeInAnimation == null) {
            this.mFadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.play_fade_in);
        }
        if (this.mFadeInAnimationListener == null && this.mOnLoadedListener != null) {
            this.mFadeInAnimationListener = new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    FifeImageView.this.invokeOnFadeInDone();
                }
            };
        }
        this.mFadeInAnimation.setAnimationListener(this.mFadeInAnimationListener);
        startAnimation(this.mFadeInAnimation);
    }

    private void fadeInIcs() {
        if (this.mFadeInAnimatorListener == null && this.mOnLoadedListener != null) {
            this.mFadeInAnimatorListener = new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    FifeImageView.this.invokeOnFadeInDone();
                }
            };
        }
        setAlpha(0.0f);
        animate().alpha(1.0f).setDuration((long) this.mFadeInDuration).setListener(this.mFadeInAnimatorListener);
    }

    public void setScaleType(ScaleType scaleType) {
        if (this.mHasDefaultZoom) {
            throw new UnsupportedOperationException("Can't mix scale type and custom zoom");
        }
        super.setScaleType(scaleType);
    }

    public void setImageMatrix(Matrix matrix) {
        if (this.mHasDefaultZoom) {
            throw new UnsupportedOperationException("Can't mix scale type and custom zoom");
        }
        super.setImageMatrix(matrix);
    }

    public void clearCachedState() {
        setTag(null);
    }

    private void loadImageIfNecessary(boolean inLayoutPass) {
        if (!this.mIsFrozen) {
            int viewWidth = getWidth();
            int viewHeight = getHeight();
            if (viewWidth != 0 || viewHeight != 0) {
                computeDesiredWidthAndHeight();
                BitmapContainer oldContainer;
                if (TextUtils.isEmpty(this.mUrl) || (this.mDesiredWidth <= 0 && this.mDesiredHeight <= 0)) {
                    oldContainer = (BitmapContainer) getTag();
                    if (oldContainer != null) {
                        oldContainer.cancelRequest();
                        setImageBitmap(null);
                        return;
                    }
                    return;
                }
                int requestWidth = 0;
                int requestHeight = 0;
                if (this.mSupportsFifeUrlOptions) {
                    requestWidth = this.mDesiredWidth;
                    requestHeight = this.mDesiredHeight;
                }
                oldContainer = (BitmapContainer) getTag();
                if (!(oldContainer == null || oldContainer.getRequestUrl() == null)) {
                    if (!oldContainer.getRequestUrl().equals(this.mUrl) || oldContainer.getRequestWidth() != requestWidth || oldContainer.getRequestHeight() != requestHeight) {
                        oldContainer.cancelRequest();
                    } else {
                        return;
                    }
                }
                BitmapContainer newContainer = this.mBitmapLoader.get(this.mUrl, requestWidth, requestHeight, this);
                setTag(newContainer);
                Bitmap bitmap = newContainer.getBitmap();
                if (bitmap != null) {
                    if (this.mBitmapTransformation != null) {
                        bitmap = this.mBitmapTransformation.transform(bitmap, getWidth(), getHeight());
                    }
                    final Bitmap finalBitmap = bitmap;
                    final boolean isLoaded = isFinalBitmapLoaded(finalBitmap);
                    if (!inLayoutPass || this.mMayBlockLayout) {
                        loadFromCache(bitmap, isLoaded);
                        return;
                    } else {
                        this.mHandler.post(new Runnable() {
                            public void run() {
                                FifeImageView.this.loadFromCache(finalBitmap, isLoaded);
                            }
                        });
                        return;
                    }
                }
                setImageDrawable(this.mDefaultDrawable);
            }
        }
    }

    public void freezeImage() {
        this.mIsFrozen = true;
    }

    public void unfreezeImage(boolean isInLayoutPass) {
        this.mIsFrozen = false;
        setTag(null);
        loadImageIfNecessary(isInLayoutPass);
    }

    private static boolean isFinalBitmapLoaded(Bitmap bitmap) {
        return bitmap != null;
    }

    private void loadFromCache(Bitmap bitmap, boolean isLoaded) {
        if (!this.mIsFrozen) {
            setImageBitmap(bitmap);
            setLoaded(isLoaded, bitmap);
        }
    }

    private void computeDesiredWidthAndHeight() {
        boolean matchParentWidth;
        boolean matchParentHeight;
        int transformationInset;
        this.mDesiredWidth = 0;
        this.mDesiredHeight = 0;
        LayoutParams layoutParams = getLayoutParams();
        if (layoutParams.width == -1) {
            matchParentWidth = true;
        } else {
            matchParentWidth = false;
        }
        if (layoutParams.height == -1) {
            matchParentHeight = true;
        } else {
            matchParentHeight = false;
        }
        if (matchParentWidth && matchParentHeight) {
            this.mDesiredWidth = getWidth();
            this.mDesiredHeight = getHeight();
        } else if (matchParentWidth) {
            this.mDesiredWidth = getWidth();
        } else if (layoutParams.width > 0) {
            this.mDesiredWidth = getWidth();
            if (layoutParams.height > 0) {
                this.mDesiredHeight = layoutParams.height;
            }
        } else if (matchParentHeight) {
            this.mDesiredHeight = getHeight();
        } else {
            this.mDesiredWidth = getWidth();
            this.mDesiredHeight = getHeight();
        }
        if (this.mBitmapTransformation != null) {
            transformationInset = this.mBitmapTransformation.getTransformationInset(this.mDesiredWidth, this.mDesiredHeight);
        } else {
            transformationInset = 0;
        }
        if (this.mDesiredWidth > 0) {
            this.mDesiredWidth -= transformationInset;
        }
        if (this.mDesiredHeight > 0) {
            this.mDesiredHeight -= transformationInset;
        }
        float networkScaleFactor = FifeUrlUtils.getNetworkScaleFactor(getContext());
        this.mDesiredWidth = (int) ((this.mRequestScaleFactor * networkScaleFactor) * ((float) this.mDesiredWidth));
        this.mDesiredHeight = (int) ((this.mRequestScaleFactor * networkScaleFactor) * ((float) this.mDesiredHeight));
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        loadImageIfNecessary(true);
        this.mForegroundBoundsChanged = true;
    }

    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.mForegroundDrawable;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (IS_HC_OR_ABOVE && this.mForegroundDrawable != null) {
            this.mForegroundDrawable.jumpToCurrentState();
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mForegroundDrawable != null && this.mForegroundDrawable.isStateful()) {
            this.mForegroundDrawable.setState(getDrawableState());
        }
        if (this.mBitmapTransformation != null) {
            invalidate();
        }
    }

    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
        if (this.mForegroundDrawable != null) {
            this.mForegroundDrawable.setHotspot(x, y);
        }
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (this.mForegroundDrawable != null) {
            boolean isVisible;
            if (visibility == 0) {
                isVisible = true;
            } else {
                isVisible = false;
            }
            this.mForegroundDrawable.setVisible(isVisible, false);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.mBitmapTransformation == null) {
            drawForeground(canvas);
            return;
        }
        boolean drawPressed = isPressed() && (isDuplicateParentStateEnabled() || isClickable());
        if (drawPressed) {
            this.mBitmapTransformation.drawPressedOverlay(canvas, width, height);
        }
        if (isFocused()) {
            this.mBitmapTransformation.drawFocusedOverlay(canvas, width, height);
        }
    }

    private void drawForeground(Canvas canvas) {
        if (this.mForegroundDrawable != null) {
            if (this.mForegroundBoundsChanged) {
                this.mForegroundBoundsChanged = false;
                Rect selfBounds = this.mSelfBounds;
                selfBounds.set(0, 0, getWidth(), getHeight());
                this.mForegroundDrawable.setBounds(selfBounds);
            }
            this.mForegroundDrawable.draw(canvas);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        blockLayoutIfPossible();
        super.setImageDrawable(drawable);
        this.mBlockLayout = false;
        if (this.mHasDefaultZoom) {
            updateMatrix();
        }
    }

    public void setImageResource(int resId) {
        blockLayoutIfPossible();
        super.setImageResource(resId);
        this.mBlockLayout = false;
        if (this.mHasDefaultZoom) {
            updateMatrix();
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.mHasDefaultZoom) {
            updateMatrix();
        }
        this.mForegroundBoundsChanged = true;
    }

    public void setImageURI(Uri uri) {
        blockLayoutIfPossible();
        super.setImageURI(uri);
        this.mBlockLayout = false;
    }

    public void requestLayout() {
        if (!this.mBlockLayout) {
            super.requestLayout();
        }
    }

    public void setDefaultZoom(float defaultZoom) {
        if (!this.mHasDefaultZoom || this.mDefaultZoom != defaultZoom) {
            this.mHasDefaultZoom = true;
            this.mDefaultZoom = defaultZoom;
            super.setScaleType(ScaleType.MATRIX);
            updateMatrix();
        }
    }

    public void setFocusPoint(float focusX, float focusY) {
        this.mFocusPoint.set(focusX, focusY);
    }

    private void blockLayoutIfPossible() {
        if (this.mMayBlockLayout) {
            this.mBlockLayout = true;
        }
    }

    private void updateMatrix() {
        Drawable d = getDrawable();
        float dstWidth = (float) getWidth();
        float dstHeight = (float) getHeight();
        if (d != null && dstWidth != 0.0f && dstHeight != 0.0f) {
            float srcWidth = (float) d.getIntrinsicWidth();
            float srcHeight = (float) d.getIntrinsicHeight();
            if (srcWidth <= 0.0f || srcHeight <= 0.0f) {
                this.mMatrix.reset();
            } else {
                float scale = Math.max(dstWidth / srcWidth, dstHeight / srcHeight);
                float cropX = Math.max(srcWidth - (dstWidth / scale), 0.0f);
                float cropY = Math.max(srcHeight - (dstHeight / scale), 0.0f);
                float cropL = cropX * this.mFocusPoint.x;
                float cropR = cropX - cropL;
                float cropT = cropY * this.mFocusPoint.y;
                float cropB = cropY - cropT;
                float extraSrcWidth = srcWidth * (this.mDefaultZoom - 1.0f);
                float extraSrcHeight = srcHeight * (this.mDefaultZoom - 1.0f);
                float f = (extraSrcWidth / 2.0f) + cropL;
                float f2 = (extraSrcHeight / 2.0f) + cropT;
                RectF rectF = new RectF(r21, r22, (srcWidth - cropR) - (extraSrcWidth / 2.0f), (srcHeight - cropB) - (extraSrcHeight / 2.0f));
                RectF dstRect = new RectF(0.0f, 0.0f, dstWidth, dstHeight);
                this.mMatrix.setRectToRect(rectF, dstRect, ScaleToFit.FILL);
            }
            super.setImageMatrix(this.mMatrix);
        }
    }
}
