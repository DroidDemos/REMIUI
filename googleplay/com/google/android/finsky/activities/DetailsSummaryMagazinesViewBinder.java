package com.google.android.finsky.activities;

import android.accounts.Account;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.play.layout.PlayActionButton;
import java.util.List;

public class DetailsSummaryMagazinesViewBinder extends DetailsSummaryViewBinder {
    private List<Document> mSubscriptions;

    public DetailsSummaryMagazinesViewBinder(DfeToc dfeToc, Account account) {
        super(dfeToc, account);
    }

    public void bind(Document document, boolean bindDynamicSection, View... views) {
        this.mSubscriptions = DocUtils.getSubscriptions(document, this.mDfeToc, FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount));
        super.bind(document, bindDynamicSection, views);
    }

    protected void setupActionButtons(boolean isInTransientState) {
        super.setupActionButtons(isInTransientState);
        findViewById(R.id.download_button).setVisibility(8);
        syncButtonSectionVisibility();
    }

    protected boolean shouldDisplayOfferNote() {
        return LibraryUtils.getOwnerWithCurrentAccount(this.mSubscriptions, FinskyApp.get().getLibraries(), this.mAccount) == null;
    }

    protected boolean displayActionButtonsIfNecessary(PlayActionButton launchButton, PlayActionButton buyButton, PlayActionButton buyButton2, PlayActionButton tryButton, PlayActionButton downloadButton) {
        return displayActionButtonsIfNecessaryNew(launchButton, buyButton, buyButton2, tryButton, downloadButton);
    }
}
