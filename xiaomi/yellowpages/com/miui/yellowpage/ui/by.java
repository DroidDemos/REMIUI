package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.base.model.Banners.Banner;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: BannerView */
class by implements OnClickListener {
    final /* synthetic */ p wA;
    final /* synthetic */ Banner wz;

    by(p pVar, Banner banner) {
        this.wA = pVar;
        this.wz = banner;
    }

    public void onClick(View view) {
        this.wA.fI.getContext().startActivity(this.wz.getIntent());
        BusinessStatistics.clickBanner(this.wA.fI.getContext(), String.valueOf(this.wA.fI.Iq.getYid()), this.wz, this.wA.fI.Iq.getStatisticsContext().getSource(), this.wA.fI.Iq.getStatisticsContext().getSourceModuleId());
    }
}
