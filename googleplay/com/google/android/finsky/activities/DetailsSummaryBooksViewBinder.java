package com.google.android.finsky.activities;

import android.accounts.Account;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.play.layout.PlayActionButton;

public class DetailsSummaryBooksViewBinder extends DetailsSummaryViewBinder {
    public DetailsSummaryBooksViewBinder(DfeToc dfeToc, Account account) {
        super(dfeToc, account);
    }

    protected boolean displayActionButtonsIfNecessary(PlayActionButton launchButton, PlayActionButton buyButton, PlayActionButton buyButton2, PlayActionButton tryButton, PlayActionButton downloadButton) {
        return displayActionButtonsIfNecessaryNew(launchButton, buyButton, buyButton2, tryButton, downloadButton);
    }
}
