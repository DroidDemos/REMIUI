package com.google.android.finsky.utils;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.text.TextUtils;
import android.util.FloatMath;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.layout.ObservableScrollView;
import com.google.android.finsky.layout.ObservableScrollView.ScrollListener;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.layout.actionbar.OverlayableImageHost;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.EditorialSeriesContainer;
import com.google.android.finsky.protos.DocumentV2.NextBanner;
import com.google.android.finsky.protos.DocumentV2.SeriesAntenna;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import java.util.Map;

public class UiUtils {
    private static Handler sPaddingRequestHandler;
    private static Map<OverlayableImageHost, Runnable> sPendingPaddingRequests;
    private static Rect sTempRect;

    public interface ClusterFadeOutListener {
        void onClusterFadeOutFinish();
    }

    static {
        sTempRect = new Rect();
        sPendingPaddingRequests = Maps.newHashMap();
        sPaddingRequestHandler = new Handler(Looper.myLooper());
    }

    public static int getGridHorizontalPadding(Resources res) {
        return Math.max(res.getDimensionPixelSize(R.dimen.play_collection_edge_padding_minus_card_half_spacing), (res.getDisplayMetrics().widthPixels - res.getDimensionPixelSize(R.dimen.play_collection_max_width)) / 2);
    }

    public static int getGridColumnContentWidth(Resources res) {
        return res.getDisplayMetrics().widthPixels - (getGridHorizontalPadding(res) * 2);
    }

    public static int getFeaturedGridColumnCount(Resources res, double percent) {
        if (!res.getBoolean(R.bool.play_dynamic_column_count)) {
            return res.getInteger(R.integer.play_featured_grid_width);
        }
        return Math.min(((int) (((double) getGridColumnContentWidth(res)) * percent)) / res.getDimensionPixelSize(R.dimen.play_column_min_size), 5);
    }

    public static int getRegularGridColumnCount(Resources res) {
        if (res.getBoolean(R.bool.play_dynamic_column_count)) {
            return Math.min(getGridColumnContentWidth(res) / res.getDimensionPixelSize(R.dimen.play_column_min_size), 5);
        }
        return res.getInteger(R.integer.play_regular_grid_width);
    }

    public static int getStreamQuickLinkColumnCount(Resources res, int requiredLinkCount, int optionalLinkCount) {
        int gridColumnCount = getFeaturedGridColumnCount(res, 1.0d);
        if (requiredLinkCount > gridColumnCount) {
            int result = gridColumnCount;
            while (true) {
                int leading = requiredLinkCount % result;
                if (leading == 0 || result - leading <= 1 || result <= 2) {
                    return result;
                }
                result--;
            }
        } else {
            int totalLinkCount = requiredLinkCount + optionalLinkCount;
            if (totalLinkCount == 1 && gridColumnCount == 2) {
                return gridColumnCount;
            }
            return Math.min(gridColumnCount, Math.max((int) Math.ceil((double) (((float) gridColumnCount) / 2.0f)), totalLinkCount));
        }
    }

    private static int getColor(String colorRgb, int fallbackColor) {
        if (colorRgb.length() > 0) {
            try {
                fallbackColor = Color.parseColor(colorRgb.trim());
            } catch (IllegalArgumentException exc) {
                if (((Boolean) DfeApiConfig.showStagingData.get()).booleanValue()) {
                    FinskyLog.wtf("Bad color: " + colorRgb, exc);
                    throw exc;
                }
            }
        }
        return fallbackColor;
    }

    public static int getFillColor(Image image, int fallbackColor) {
        return getColor(image.fillColorRgb, fallbackColor);
    }

    public static int getFillColor(SeriesAntenna antenna, int fallbackColor) {
        return getColor(antenna.colorThemeArgb, fallbackColor);
    }

    public static int getFillColor(EditorialSeriesContainer editorial, int fallbackColor) {
        return getColor(editorial.colorThemeArgb, fallbackColor);
    }

    public static int getTextColor(NextBanner banner, int fallbackColor) {
        return getColor(banner.colorTextArgb, fallbackColor);
    }

    public static boolean isColorBright(int color) {
        return ((Color.red(color) * 21) + (Color.green(color) * 72)) + (Color.blue(color) * 7) >= 12800;
    }

    public static void showKeyboard(Activity activity, final EditText focusView) {
        focusView.requestFocus();
        final InputMethodManager imm = (InputMethodManager) activity.getSystemService("input_method");
        focusView.postDelayed(new Runnable() {
            public void run() {
                imm.showSoftInput(focusView, 1);
            }
        }, 300);
    }

    public static void hideKeyboard(Activity activity, View view) {
        ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void playShakeAnimationIfPossible(Context context, EditText view) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, 1.0f});
            final float shakeDelta = context.getResources().getDimension(R.dimen.shake_animation_delta);
            animator.setInterpolator(new TimeInterpolator() {
                public float getInterpolation(float input) {
                    return (FloatMath.sin(((2.0f * input) * 3.1415927f) * 3.0f) * (1.0f - input)) * shakeDelta;
                }
            });
            animator.start();
        }
    }

    public static boolean isAccessibilityEnabled(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        return VERSION.SDK_INT >= 14 ? accessibilityManager.isTouchExplorationEnabled() : accessibilityManager.isEnabled();
    }

    public static void sendAccessibilityEventWithText(Context context, String textToAnnounce, View view) {
        if (isAccessibilityEnabled(context)) {
            AccessibilityEvent event = AccessibilityEvent.obtain(VERSION.SDK_INT >= 16 ? 16384 : 8);
            event.getText().add(textToAnnounce);
            event.setEnabled(true);
            if (view != null) {
                AccessibilityEventCompat.asRecord(event).setSource(view);
            }
            ((AccessibilityManager) context.getSystemService("accessibility")).sendAccessibilityEvent(event);
        }
    }

    public static void setErrorOnTextView(TextView textView, String viewLabel, String viewError) {
        textView.setError(viewError);
        sendAccessibilityEventWithText(textView.getContext(), textView.getResources().getString(R.string.accessibility_event_form_field_error, new Object[]{viewLabel, viewError}), textView);
    }

    public static boolean isVisibleOnScreen(View view) {
        return view.getGlobalVisibleRect(sTempRect);
    }

    public static void fadeOutCluster(final View cluster, final ClusterFadeOutListener listener, long animationDelay) {
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            public void run() {
                cluster.startAnimation(PlayAnimationUtils.getFadeOutAnimation(cluster.getContext(), 150, new AnimationListenerAdapter() {
                    public void onAnimationEnd(Animation animation) {
                        if (listener != null) {
                            listener.onClusterFadeOutFinish();
                        }
                    }
                }));
            }
        }, animationDelay);
    }

    public static ScrollListener enableActionBarOverlayScrolling(final ObservableScrollView scrollView, int heroImageResourceId, final ActionBarController actionBarController) {
        actionBarController.enableActionBarOverlay();
        final OverlayableImageHost heroImageHost = (OverlayableImageHost) scrollView.findViewById(heroImageResourceId);
        scrollView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                UiUtils.syncActionBarAlpha(scrollView, actionBarController, heroImageHost, scrollView.getScrollY());
                scrollView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
        ScrollListener scrollListener = new ScrollListener() {
            public void onScroll(int left, int top) {
                UiUtils.syncActionBarAlpha(scrollView, actionBarController, heroImageHost, top);
            }
        };
        scrollView.addOnScrollListener(scrollListener);
        return scrollListener;
    }

    private static void syncActionBarAlpha(ObservableScrollView scrollView, ActionBarController actionBarController, final OverlayableImageHost heroImageHost, int scrollTop) {
        int heroImageHeight = heroImageHost.getOverlayableImageHeight();
        if (scrollTop > heroImageHeight) {
            actionBarController.setActionBarAlpha(255, false);
            return;
        }
        float overlayFraction;
        int heroOverlayTransparencyHeight = heroImageHost.getOverlayTransparencyHeightFromTop();
        if (scrollTop <= heroOverlayTransparencyHeight) {
            overlayFraction = 0.0f;
        } else {
            overlayFraction = Math.abs(((float) (scrollTop - heroOverlayTransparencyHeight)) / ((float) (heroImageHeight - heroOverlayTransparencyHeight)));
        }
        actionBarController.setActionBarAlpha((int) (255.0f * overlayFraction), false);
        final int heroTopPadding = (int) ((((float) heroImageHeight) * 0.5f) * (((float) Math.max(0, scrollTop)) / ((float) heroImageHeight)));
        Runnable pendingRequest = (Runnable) sPendingPaddingRequests.get(heroImageHost);
        if (pendingRequest != null) {
            sPaddingRequestHandler.removeCallbacks(pendingRequest);
            sPendingPaddingRequests.remove(heroImageHost);
        }
        if (scrollView.isViewInLayout()) {
            Runnable newRequest = new Runnable() {
                public void run() {
                    heroImageHost.setOverlayableImageTopPadding(heroTopPadding);
                    UiUtils.sPendingPaddingRequests.remove(heroImageHost);
                }
            };
            sPendingPaddingRequests.put(heroImageHost, newRequest);
            sPaddingRequestHandler.post(newRequest);
            return;
        }
        heroImageHost.setOverlayableImageTopPadding(heroTopPadding);
    }

    public static void disableActionBarOverlayScrolling(ObservableScrollView scrollView, ScrollListener scrollListener, ActionBarController actionBarController) {
        actionBarController.disableActionBarOverlay();
        actionBarController.setActionBarAlpha(255, false);
        if (scrollView != null) {
            scrollView.removeOnScrollListener(scrollListener);
        }
    }

    public static void syncContainerVisibility(ViewGroup container, int visibilityIfNoVisibleChildren) {
        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (container.getChildAt(i).getVisibility() == 0) {
                container.setVisibility(0);
                return;
            }
        }
        container.setVisibility(visibilityIfNoVisibleChildren);
    }

    public static View findSharedElementView(View view, String transitionNamePrefix) {
        if (!NavigationManager.areTransitionsEnabled()) {
            return null;
        }
        String viewTransitionName = view.getTransitionName();
        if (!TextUtils.isEmpty(viewTransitionName) && viewTransitionName.startsWith(transitionNamePrefix)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View sharedElementView = findSharedElementView(viewGroup.getChildAt(i), transitionNamePrefix);
                if (sharedElementView != null) {
                    return sharedElementView;
                }
            }
        }
        return null;
    }

    public static int interpolateColor(int color1, int color2, float color1Likeness) {
        float invertedLikeness = 1.0f - color1Likeness;
        return (((((int) ((((float) ((color1 >> 24) & 255)) * color1Likeness) + (((float) ((color2 >> 24) & 255)) * invertedLikeness))) << 24) | (((int) ((((float) ((color1 >> 16) & 255)) * color1Likeness) + (((float) ((color2 >> 16) & 255)) * invertedLikeness))) << 16)) | (((int) ((((float) ((color1 >> 8) & 255)) * color1Likeness) + (((float) ((color2 >> 8) & 255)) * invertedLikeness))) << 8)) | ((int) ((((float) (color1 & 255)) * color1Likeness) + (((float) (color2 & 255)) * invertedLikeness)));
    }
}
