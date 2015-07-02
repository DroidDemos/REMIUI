package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.play.layout.PlayActionButton;

public class DetailsSummaryMusicViewBinder extends DetailsSummaryViewBinder {
    public DetailsSummaryMusicViewBinder(DfeToc dfeToc, Account account) {
        super(dfeToc, account);
    }

    protected boolean displayActionButtonsIfNecessary(PlayActionButton launchButton, PlayActionButton buyButton, PlayActionButton buyButton2, PlayActionButton tryButton, PlayActionButton downloadButton) {
        boolean isDisplayingActionButtons = super.displayActionButtonsIfNecessary(launchButton, buyButton, buyButton2, tryButton, downloadButton);
        if (!FinskyApp.get().getExperiments().isEnabled("cl:details.album_all_access_enabled")) {
            return isDisplayingActionButtons;
        }
        if (!LibraryUtils.isAvailableInMusicAllAccess(this.mDoc)) {
            return isDisplayingActionButtons;
        }
        Libraries libraries = FinskyApp.get().getLibraries();
        final Account account = FinskyApp.get().getCurrentAccount();
        boolean isAllAccessSubscriber = LibraryUtils.isMusicAllAccessSubscriber(libraries.getAccountLibrary(account));
        boolean isOwned = buyButton.getVisibility() != 0;
        Resources resources = this.mContext.getResources();
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        ViewGroup extraLabelsBottomContainer = (ViewGroup) findViewById(R.id.title_extra_labels_bottom);
        ViewGroup extraLabelsLeading = (ViewGroup) extraLabelsBottomContainer.findViewById(R.id.extra_labels_bottom_leading);
        extraLabelsLeading.removeAllViews();
        boolean hasExtraLabelsBottom = false;
        if (!isAllAccessSubscriber) {
            downloadButton.setVisibility(0);
            downloadButton.setEnabled(true);
            isDisplayingActionButtons = true;
            downloadButton.configure(this.mDoc.getBackend(), R.string.all_access_button_free_trial, new OnClickListener() {
                public void onClick(View v) {
                    FinskyApp.get().getEventLogger().logClickEvent(286, null, DetailsSummaryMusicViewBinder.this.mParentNode);
                    if (IntentUtils.isMusicAppWithAllAccessFlowInstalled(DetailsSummaryMusicViewBinder.this.mContext.getPackageManager())) {
                        DetailsSummaryMusicViewBinder.this.mContext.startActivity(IntentUtils.buildConsumptionAppUrlIntent(DetailsSummaryMusicViewBinder.this.mContext, 2, (String) G.musicAllAccessSignUpIntentUrl.get(), account.name));
                        return;
                    }
                    DetailsSummaryMusicViewBinder.this.mNavigationManager.showAppNeededDialog(2);
                }
            });
            addExtraLabelBottom(inflater, extraLabelsLeading, resources.getString(R.string.all_access_label_bottom_try_all_access));
            hasExtraLabelsBottom = true;
        } else if (!isOwned) {
            downloadButton.setVisibility(0);
            downloadButton.setEnabled(true);
            isDisplayingActionButtons = true;
            downloadButton.configure(this.mDoc.getBackend(), R.string.listen, new OnClickListener() {
                public void onClick(View v) {
                    FinskyApp.get().getEventLogger().logClickEvent(285, null, DetailsSummaryMusicViewBinder.this.mParentNode);
                    int backendId = DetailsSummaryMusicViewBinder.this.mDoc.getBackend();
                    if (IntentUtils.isConsumptionAppInstalled(DetailsSummaryMusicViewBinder.this.mContext.getPackageManager(), backendId)) {
                        Intent intent = IntentUtils.buildConsumptionAppViewItemIntent(DetailsSummaryMusicViewBinder.this.mContext, DetailsSummaryMusicViewBinder.this.mDoc, account.name);
                        intent.addFlags(268435456);
                        DetailsSummaryMusicViewBinder.this.mContext.startActivity(intent);
                        return;
                    }
                    DetailsSummaryMusicViewBinder.this.mNavigationManager.showAppNeededDialog(backendId);
                }
            });
            addExtraLabelBottom(inflater, extraLabelsLeading, resources.getString(R.string.all_access_label_bottom_available_in_all_access));
            hasExtraLabelsBottom = true;
        }
        extraLabelsBottomContainer.setVisibility(hasExtraLabelsBottom ? 0 : 8);
        return isDisplayingActionButtons;
    }
}
