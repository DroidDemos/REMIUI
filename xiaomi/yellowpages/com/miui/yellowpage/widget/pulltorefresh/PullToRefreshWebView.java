package com.miui.yellowpage.widget.pulltorefresh;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;
import android.webkit.WebView;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.AnimationStyle;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Orientation;

public class PullToRefreshWebView extends PullToRefreshBase {
    protected /* bridge */ /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        return c(context, attributeSet);
    }

    public PullToRefreshWebView(Context context) {
        super(context);
    }

    public PullToRefreshWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshWebView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshWebView(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final Orientation fw() {
        return Orientation.VERTICAL;
    }

    protected WebView c(Context context, AttributeSet attributeSet) {
        return new WebView(context, attributeSet);
    }

    protected boolean fB() {
        return ((WebView) this.wP).getScrollY() == 0 && !fu();
    }

    protected boolean fA() {
        return ((float) ((WebView) this.wP).getScrollY()) >= FloatMath.floor(((WebView) this.wP).getScale() * ((float) ((WebView) this.wP).getContentHeight())) - ((float) ((WebView) this.wP).getHeight());
    }

    protected void d(Bundle bundle) {
        super.d(bundle);
        ((WebView) this.wP).restoreState(bundle);
    }

    protected void e(Bundle bundle) {
        super.e(bundle);
        ((WebView) this.wP).saveState(bundle);
    }
}
