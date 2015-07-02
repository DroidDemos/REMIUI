package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.android.vending.R;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;

public class ScreenshotView extends FrameLayout implements OnLoadedListener {
    private Runnable mFadeInRunnable;
    private Handler mHandler;
    private FifeImageView mImageView;
    private ProgressBar mProgressBar;

    private class FadeInViewRunnable implements Runnable {
        private FadeInViewRunnable() {
        }

        public void run() {
            Animation fadeInAnimation = AnimationUtils.loadAnimation(ScreenshotView.this.mProgressBar.getContext(), R.anim.play_fade_in);
            ScreenshotView.this.mProgressBar.setVisibility(0);
            ScreenshotView.this.mProgressBar.startAnimation(fadeInAnimation);
        }
    }

    private class HideAfterEndAnimationListener implements AnimationListener {
        private HideAfterEndAnimationListener() {
        }

        public void onAnimationEnd(Animation animation) {
            ScreenshotView.this.mProgressBar.setVisibility(8);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    public ScreenshotView(Context context) {
        super(context);
        this.mFadeInRunnable = new FadeInViewRunnable();
        this.mHandler = new Handler();
    }

    public ScreenshotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFadeInRunnable = new FadeInViewRunnable();
        this.mHandler = new Handler();
    }

    public ScreenshotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mFadeInRunnable = new FadeInViewRunnable();
        this.mHandler = new Handler();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mImageView = (FifeImageView) findViewById(R.id.screenshot_content);
        this.mProgressBar = (ProgressBar) findViewById(R.id.screenshot_progress_bar);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacks(this.mFadeInRunnable);
    }

    public void setImage(Image image, BitmapLoader loader) {
        this.mImageView.setOnLoadedListener(this);
        this.mImageView.setImage(image.imageUrl, image.supportsFifeUrlOptions, loader);
        if (!this.mImageView.isLoaded()) {
            this.mHandler.postDelayed(this.mFadeInRunnable, 500);
        }
    }

    public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
        this.mHandler.removeCallbacks(this.mFadeInRunnable);
        if (this.mProgressBar.getVisibility() == 0) {
            Animation fadeOutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
            fadeOutAnimation.setAnimationListener(new HideAfterEndAnimationListener());
            this.mProgressBar.startAnimation(fadeOutAnimation);
        }
    }

    public void onLoadedAndFadedIn(FifeImageView imageView) {
    }
}
