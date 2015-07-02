package com.google.android.play.onboard;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.play.R;
import com.google.android.play.onboard.OnboardUtils.Predicate;
import com.google.android.play.utils.collections.Lists;
import com.google.android.play.widget.PulsatingDotDrawable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class InterstitialOverlay extends FrameLayout {
    public static final int[] DEFAULT_ACCENT_COLORS_RES_IDS;
    private static final int LAYOUT;
    private int[] mColors;
    private List<PulsatingDotDrawable> mDots;
    private final Random mRandom;

    static {
        LAYOUT = R.layout.play_onboard_interstitial_overlay;
        DEFAULT_ACCENT_COLORS_RES_IDS = new int[]{R.color.play_onboard_accent_color_a, R.color.play_onboard_accent_color_b, R.color.play_onboard_accent_color_c, R.color.play_onboard_accent_color_d};
    }

    public InterstitialOverlay(Context context) {
        this(context, null);
    }

    public InterstitialOverlay(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InterstitialOverlay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mRandom = new Random();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initializeDotsIfNeeded();
        startAnimation();
    }

    protected void onDetachedFromWindow() {
        stopAnimation();
        super.onDetachedFromWindow();
    }

    private void initializeDotsIfNeeded() {
        if (this.mDots == null) {
            final String cellTag = getResources().getString(R.string.play_onboard_interstitial_grid_cell);
            Collection<View> cells = OnboardUtils.getAllDescendants(this, new Predicate<View>() {
                public boolean apply(View view) {
                    return cellTag.equals(view.getTag());
                }
            });
            if (this.mColors == null || this.mColors.length == 0) {
                setColorResIds(getContext(), DEFAULT_ACCENT_COLORS_RES_IDS);
            }
            List<PulsatingDotDrawable> dots = Lists.newArrayList(cells.size());
            for (View cell : cells) {
                dots.add(initializeDot(cell));
            }
            this.mDots = dots;
        }
    }

    private PulsatingDotDrawable initializeDot(View cellView) {
        PulsatingDotDrawable dot = new PulsatingDotDrawable(getRandomColor(), 800, (long) this.mRandom.nextInt(800), 0.1f, 1.0f);
        if (VERSION.SDK_INT < 16) {
            cellView.setBackgroundDrawable(dot);
        } else {
            cellView.setBackground(dot);
        }
        return dot;
    }

    private int getRandomColor() {
        return this.mColors[this.mRandom.nextInt(this.mColors.length)];
    }

    public void startAnimation() {
        for (PulsatingDotDrawable dot : this.mDots) {
            dot.start();
        }
    }

    public void stopAnimation() {
        for (PulsatingDotDrawable dot : this.mDots) {
            dot.stop();
        }
    }

    public void setCaption(CharSequence text) {
        ((TextView) findViewById(R.id.caption)).setText(text);
    }

    public void setCaption(int stringResId) {
        ((TextView) findViewById(R.id.caption)).setText(stringResId);
    }

    protected void setColors(int[] colors) {
        this.mColors = colors;
    }

    protected void setColorResIds(Context context, int[] colorResIds) {
        int[] colors = new int[colorResIds.length];
        Resources resources = context.getResources();
        for (int i = 0; i < colorResIds.length; i++) {
            colors[i] = resources.getColor(colorResIds[i]);
        }
        setColors(colors);
    }
}
