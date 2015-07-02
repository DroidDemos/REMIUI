package com.miui.yellowpage.ui;

import android.support.v4.view.D;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Banners.Banner;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

/* compiled from: BannerView */
class p extends D {
    final /* synthetic */ BannerView fI;

    private p(BannerView bannerView) {
        this.fI = bannerView;
    }

    public /* bridge */ /* synthetic */ Object instantiateItem(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public int getCount() {
        if (this.fI.Iq == null || this.fI.Iq.getBannerList() == null) {
            return 0;
        }
        return this.fI.Iq.getBannerList().size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public View a(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.fI.getContext()).inflate(R.layout.banner_item, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.picture);
        Banner banner = (Banner) this.fI.Iq.getBannerList().get(i);
        YellowPageImgLoader.loadImage(this.fI.getContext(), imageView, this.fI.IA, ImageFormat.PNG, banner.getPictureUrl(), BannerView.Io, BannerView.In, R.drawable.default_banner);
        if (banner.getIntent() != null) {
            inflate.setOnClickListener(new by(this, banner));
        }
        inflate.setTag(banner);
        viewGroup.addView(inflate);
        return inflate;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getItemPosition(Object obj) {
        int indexOf = this.fI.Iq.getBannerList().indexOf((Banner) ((View) obj).getTag());
        if (indexOf == -1) {
            return -2;
        }
        return indexOf;
    }
}
