package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.base.model.ShopRecommendation;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: ShopRecommendationListItem */
class h implements OnClickListener {
    final /* synthetic */ Intent cT;
    final /* synthetic */ ShopRecommendation cU;
    final /* synthetic */ YellowPage cV;
    final /* synthetic */ ShopRecommendationListItem cW;

    h(ShopRecommendationListItem shopRecommendationListItem, Intent intent, ShopRecommendation shopRecommendation, YellowPage yellowPage) {
        this.cW = shopRecommendationListItem;
        this.cT = intent;
        this.cU = shopRecommendation;
        this.cV = yellowPage;
    }

    public void onClick(View view) {
        this.cW.getContext().startActivity(this.cT);
        BusinessStatistics.clickShopRecommendation(this.cW.getContext(), String.valueOf(this.cU.getYid()), String.valueOf(this.cV.getId()), this.cU.getStatisticsContext().getSource(), this.cU.getStatisticsContext().getSourceModuleId());
    }
}
