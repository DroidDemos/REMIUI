package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.base.model.AppRecommendation;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: AppRecommendationListItem */
class bR implements OnClickListener {
    final /* synthetic */ AppRecommendation Au;
    final /* synthetic */ AppRecommendationListItem Av;

    bR(AppRecommendationListItem appRecommendationListItem, AppRecommendation appRecommendation) {
        this.Av = appRecommendationListItem;
        this.Au = appRecommendation;
    }

    public void onClick(View view) {
        this.Av.getContext().startActivity(this.Au.getIntent());
        BusinessStatistics.clickAppRecommendation(this.Av.getContext(), String.valueOf(this.Au.getYid()), this.Au.getName(), this.Au.getStatisticsContext().getSource(), this.Au.getStatisticsContext().getSourceModuleId());
    }
}
