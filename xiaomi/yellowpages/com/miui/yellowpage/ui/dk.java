package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.miui.yellowpage.base.model.Module;
import com.miui.yellowpage.base.model.SearchResultDataEntry;
import com.miui.yellowpage.base.model.SearchResultServiceDataEntry;
import com.miui.yellowpage.base.model.SearchResultYellowPageDataEntry;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.YellowPageHandler;

/* compiled from: NearbyYellowPageFragment */
class dk implements OnItemClickListener {
    final /* synthetic */ db cg;

    dk(db dbVar) {
        this.cg = dbVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        Intent intent;
        SearchResultDataEntry searchResultDataEntry = (SearchResultDataEntry) this.cg.Gw.getItem(i);
        if (searchResultDataEntry instanceof SearchResultYellowPageDataEntry) {
            SearchResultYellowPageDataEntry searchResultYellowPageDataEntry = (SearchResultYellowPageDataEntry) searchResultDataEntry;
            intent = new Intent("com.miui.yellowpage.action.VIEW");
            intent.putExtra("com.miui.yellowpage.extra.yid", searchResultYellowPageDataEntry.getYid());
            intent.putExtra("source", "nearby");
            YellowPageHandler.insertYellowPage(this.cg.mActivity, (YellowPage) searchResultYellowPageDataEntry.getData());
            BusinessStatistics.clickYellowPage(this.cg.mActivity, String.valueOf(searchResultYellowPageDataEntry.getYid()), this.cg.GB, this.cg.getStatisticsContext().getSource(), this.cg.getStatisticsContext().getSourceModuleId());
        } else {
            Module module = (Module) ((SearchResultServiceDataEntry) searchResultDataEntry).getData();
            if (module != null) {
                Intent filterFirstValidResult = Module.filterFirstValidResult(this.cg.mActivity, module.getActions());
                filterFirstValidResult.putExtra("source", "nearby");
                intent = filterFirstValidResult;
            } else {
                Log.d("NearbyYellowPageFragment", "module is null");
                intent = null;
            }
            BusinessStatistics.clickModuleItem(this.cg.mActivity, String.valueOf(module.getModuleId()), null, this.cg.getStatisticsContext().getSource(), this.cg.GB, this.cg.getStatisticsContext().getSourceModuleId());
        }
        this.cg.mActivity.startActivity(intent);
    }
}
