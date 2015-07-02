package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.base.model.YellowPageTitleDataEntry;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: YellowPageTitleListItem */
class cj implements OnClickListener {
    final /* synthetic */ YellowPageTitleDataEntry Dr;
    final /* synthetic */ YellowPageTitleListItem Ds;

    cj(YellowPageTitleListItem yellowPageTitleListItem, YellowPageTitleDataEntry yellowPageTitleDataEntry) {
        this.Ds = yellowPageTitleListItem;
        this.Dr = yellowPageTitleDataEntry;
    }

    public void onClick(View view) {
        this.Ds.getContext().startActivity(this.Dr.getAlbumIntent());
        BusinessStatistics.clickGallery(this.Ds.getContext(), String.valueOf(this.Dr.getYid()), this.Dr.getStatisticsContext().getSource(), this.Dr.getStatisticsContext().getSourceModuleId());
    }
}
