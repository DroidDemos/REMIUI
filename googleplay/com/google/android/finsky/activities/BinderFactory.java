package com.google.android.finsky.activities;

import android.accounts.Account;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.wallet.instrumentmanager.R;

public class BinderFactory {
    public static DetailsSummaryViewBinder getSummaryViewBinder(DfeToc dfeToc, int backendId, Account currentAccount) {
        switch (backendId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return new DetailsSummaryBooksViewBinder(dfeToc, currentAccount);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return new DetailsSummaryMusicViewBinder(dfeToc, currentAccount);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return new DetailsSummaryAppsViewBinder(dfeToc, currentAccount, FinskyApp.get().getPackageMonitorReceiver(), FinskyApp.get().getInstaller(), FinskyApp.get().getAppStates(), FinskyApp.get().getLibraries());
            case R.styleable.WalletImFormEditText_required /*4*/:
                return new DetailsSummaryMoviesViewBinder(dfeToc, currentAccount);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return new DetailsSummaryMagazinesViewBinder(dfeToc, currentAccount);
            default:
                return new DetailsSummaryViewBinder(dfeToc, currentAccount);
        }
    }
}
