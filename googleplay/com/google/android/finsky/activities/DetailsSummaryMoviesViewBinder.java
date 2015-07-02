package com.google.android.finsky.activities;

import android.accounts.Account;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.play.layout.PlayActionButton;

public class DetailsSummaryMoviesViewBinder extends DetailsSummaryViewBinder {
    public DetailsSummaryMoviesViewBinder(DfeToc dfeToc, Account account) {
        super(dfeToc, account);
    }

    protected OnClickListener getDownloadClickListener(final Document doc, final Account owner) {
        return new OnClickListener() {
            public void onClick(View v) {
                DetailsSummaryMoviesViewBinder.this.mEventLogger.logClickEvent(224, null, DetailsSummaryMoviesViewBinder.this.mParentNode);
                if (IntentUtils.isConsumptionAppInstalled(DetailsSummaryMoviesViewBinder.this.mContext.getPackageManager(), doc.getBackend())) {
                    DetailsSummaryMoviesViewBinder.this.mContext.startActivity(IntentUtils.buildConsumptionAppManageItemIntent(DetailsSummaryMoviesViewBinder.this.mContext, doc, owner.name));
                    return;
                }
                DetailsSummaryMoviesViewBinder.this.mNavigationManager.showAppNeededDialog(doc.getBackend());
            }
        };
    }

    protected boolean displayActionButtonsIfNecessary(PlayActionButton launchButton, PlayActionButton buyButton, PlayActionButton buyButton2, PlayActionButton tryButton, PlayActionButton downloadButton) {
        return displayActionButtonsIfNecessaryNew(launchButton, buyButton, buyButton2, tryButton, downloadButton);
    }
}
