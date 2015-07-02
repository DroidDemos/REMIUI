package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class FifeNetworkImageView extends NetworkImageView {
    private boolean mFadeIn;
    private int mFadeInDuration;

    public FifeNetworkImageView(Context context) {
        super(context);
    }

    public FifeNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FifeNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFadeIn(boolean fadeIn) {
        if (VERSION.SDK_INT < 14) {
            this.mFadeIn = false;
            return;
        }
        this.mFadeIn = fadeIn;
        if (this.mFadeIn) {
            this.mFadeInDuration = getContext().getResources().getInteger(17694720);
        }
    }

    public void setFifeImageUrl(String url, ImageLoader imageLoader, boolean preferWebP) {
        int desiredWidth = getWidth();
        int desiredHeight = getHeight();
        if (desiredWidth == 0 && desiredHeight == 0) {
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
                desiredWidth = layoutParams.width;
                desiredHeight = layoutParams.height;
            }
        }
        setImageUrl(createFifeUrl(url, desiredWidth, desiredHeight, preferWebP), imageLoader);
    }

    private static String createFifeUrl(String baseUrl, int width, int height, boolean preferWebP) {
        boolean useWebP;
        if (!preferWebP || VERSION.SDK_INT < 18) {
            useWebP = false;
        } else {
            useWebP = true;
        }
        String webPFifeArg = useWebP ? "-rw" : "";
        return String.format("%s=w%d-h%d-e365%s", new Object[]{baseUrl, Integer.valueOf(width), Integer.valueOf(height), webPFifeArg});
    }

    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        if (this.mFadeIn) {
            boolean fullyVisible = getVisibility() == 0;
            if (VERSION.SDK_INT >= 14) {
                fullyVisible = fullyVisible && getAlpha() == 1.0f;
            }
            if (fullyVisible) {
                WalletUiUtils.animateViewAppearing(this, 0, 0, (long) this.mFadeInDuration);
            }
        }
    }
}
