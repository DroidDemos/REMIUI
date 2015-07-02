package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.base.model.ShopInformation;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: ShopInformationListItem */
class dt implements OnClickListener {
    final /* synthetic */ ShopInformation IY;
    final /* synthetic */ ShopInformationListItem IZ;

    dt(ShopInformationListItem shopInformationListItem, ShopInformation shopInformation) {
        this.IZ = shopInformationListItem;
        this.IY = shopInformation;
    }

    public void onClick(View view) {
        this.IZ.getContext().startActivity(this.IY.getIntent());
        BusinessStatistics.clickShopDetailedInfo(this.IZ.getContext(), String.valueOf(this.IY.getYid()), this.IY, this.IY.getStatisticsContext().getSource(), this.IY.getStatisticsContext().getSourceModuleId());
    }
}
