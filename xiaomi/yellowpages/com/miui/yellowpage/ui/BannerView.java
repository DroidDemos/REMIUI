package com.miui.yellowpage.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import com.imbryk.viewPager.LoopViewPager;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Banners;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.widget.SeekBarIndicator;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class BannerView extends RelativeLayout {
    private static int In;
    private static int Io;
    private static int Ip;
    private ImageProcessor IA;
    private Banners Iq;
    private p Ir;
    private SeekBarIndicator Is;
    private LoopViewPager It;
    private boolean Iu;
    private boolean Iv;
    private boolean Iw;
    private long Ix;
    private int Iy;
    private Runnable Iz;
    private Handler mHandler;

    public BannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Ix = TimeUnit.SECONDS.toMillis(5);
        this.Iy = (int) TimeUnit.SECONDS.toMillis(2);
        this.mHandler = new Handler();
        this.Iz = new s(this);
        this.IA = new u(this);
        if (Io == 0) {
            In = (int) context.getResources().getDimension(R.dimen.yellow_page_banner_item_height);
            Io = (int) context.getResources().getDimension(R.dimen.yellow_page_banner_item_width);
            Ip = (int) context.getResources().getDimension(R.dimen.yellow_page_banner_corner_radius);
        }
    }

    public void a(Banners banners, cs csVar) {
        this.Iq = banners;
        if (this.Iq.getBannerList().size() > 1) {
            this.Is.w(this.Iq.getBannerList().size());
            this.Is.x(this.It.getCurrentItem());
            this.Is.setVisibility(0);
        } else {
            this.Is.setVisibility(8);
        }
        if (!this.Iu) {
            csVar.addLifecycleCallback(new t(this));
            this.Iu = true;
        }
        D(true);
        hU();
        this.Ir.notifyDataSetChanged();
        this.It.setCurrentItem(0);
    }

    private void D(boolean z) {
        this.Iv = z;
        hU();
    }

    private void hU() {
        Object obj = (!this.Iv || this.Iw) ? null : 1;
        if (obj != null) {
            hV();
        } else {
            hW();
        }
    }

    private void hV() {
        hW();
        if (this.Ir.getCount() > 1) {
            this.mHandler.postDelayed(this.Iz, this.Ix);
        }
    }

    private void hW() {
        this.mHandler.removeCallbacks(this.Iz);
    }

    private void hX() {
        this.It.setCurrentItem(this.It.getCurrentItem() + 1, true);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.Is = (SeekBarIndicator) findViewById(R.id.indicator);
        this.It = (LoopViewPager) findViewById(R.id.pager);
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("ju");
            declaredField2.setAccessible(true);
            declaredField.set(this.It, new cI(this, getContext(), (Interpolator) declaredField2.get(null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.Ir = new p();
        this.It.a(new v(this));
        this.It.a(this.Ir);
    }
}
