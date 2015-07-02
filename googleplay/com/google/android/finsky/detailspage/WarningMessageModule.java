package com.google.android.finsky.detailspage;

import android.accounts.Account;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.WarningMessageSection;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.VoucherUtils;
import java.util.List;

public class WarningMessageModule extends FinskyModule<WarningMessageModuleData> implements Listener {
    private boolean mNeedsRefresh;

    protected static class WarningMessageModuleData extends ModuleData {
        Document detailsDoc;
        boolean shouldShowWarningMessage;

        protected WarningMessageModuleData() {
        }
    }

    public int getLayoutResId() {
        return R.layout.details_warning_message;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null && ((WarningMessageModuleData) this.mModuleData).shouldShowWarningMessage;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null) {
            this.mLibraries.addListener(this);
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null) {
            this.mModuleData = new WarningMessageModuleData();
            this.mLibraries.addListener(this);
        }
        ((WarningMessageModuleData) this.mModuleData).detailsDoc = detailsDoc;
        ((WarningMessageModuleData) this.mModuleData).shouldShowWarningMessage = shouldShowWarningMessage(detailsDoc);
    }

    public void bindView(View view) {
        WarningMessageSection warningMessageSection = (WarningMessageSection) view;
        if (!warningMessageSection.hasBinded() || this.mNeedsRefresh) {
            warningMessageSection.bind(((WarningMessageModuleData) this.mModuleData).detailsDoc, this.mDfeToc, this.mLibraries, this.mDfeApi.getAccount());
            this.mNeedsRefresh = false;
        }
    }

    public void onDestroyModule() {
        this.mLibraries.removeListener(this);
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        ((WarningMessageModuleData) this.mModuleData).shouldShowWarningMessage = shouldShowWarningMessage(((WarningMessageModuleData) this.mModuleData).detailsDoc);
        if (((WarningMessageModuleData) this.mModuleData).shouldShowWarningMessage) {
            this.mFinskyModuleController.refreshModule(this, true);
            this.mNeedsRefresh = true;
            return;
        }
        this.mFinskyModuleController.removeModule(this);
    }

    private CharSequence getAlternateAccountOwnerText(Document document, AccountLibrary accountLibrary, Libraries libraries, Account currentAccount, DfeToc toc) {
        if (document.getDocumentType() == 1) {
            return null;
        }
        if (LibraryUtils.isOwned(document, (Library) accountLibrary)) {
            return null;
        }
        if (LibraryUtils.getFirstOwner(document, libraries) != null) {
            return Html.fromHtml(this.mContext.getString(R.string.owned_by_other_account, new Object[]{owner.name}));
        }
        if (document.hasSubscriptions()) {
            List subscriptions = DocUtils.getSubscriptions(document, toc, libraries);
            if (LibraryUtils.getOwnerWithCurrentAccount(subscriptions, libraries, currentAccount) != null) {
                return null;
            }
            for (int i = 0; i < subscriptions.size(); i++) {
                if (LibraryUtils.getFirstOwner((Document) subscriptions.get(i), libraries) != null) {
                    return Html.fromHtml(this.mContext.getString(R.string.owned_by_other_account, new Object[]{subscriptionOwner.name}));
                }
            }
        }
        return null;
    }

    private boolean shouldShowWarningMessage(Document doc) {
        Account currentAccount = this.mDfeApi.getAccount();
        AccountLibrary accountLibrary = this.mLibraries.getAccountLibrary(currentAccount);
        boolean isAvailable = LibraryUtils.isAvailable(doc, this.mDfeToc, accountLibrary);
        boolean hasApplicableVoucher;
        if (VoucherUtils.hasApplicableVouchers(doc, accountLibrary) && doc.hasApplicableVoucherDescription()) {
            hasApplicableVoucher = true;
        } else {
            hasApplicableVoucher = false;
        }
        boolean hasWarningMessage = doc.hasWarningMessage();
        boolean showLicenceMessage = shouldShowLicenseMessage(doc, accountLibrary);
        boolean showExternallyHosted = shouldShowExternallyHostedMessage(doc);
        CharSequence accountOwnerWarning = getAlternateAccountOwnerText(doc, accountLibrary, this.mLibraries, currentAccount, this.mDfeToc);
        if (!isAvailable || hasWarningMessage || hasApplicableVoucher || showLicenceMessage || showExternallyHosted || !TextUtils.isEmpty(accountOwnerWarning)) {
            return true;
        }
        return false;
    }

    private static boolean shouldShowLicenseMessage(Document document, AccountLibrary library) {
        boolean z = true;
        if (document.getDocumentType() != 1) {
            return false;
        }
        Offer[] arr$ = document.getAvailableOffers();
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            Offer offer = arr$[i$];
            if (offer.licenseTerms == null || (offer.micros <= 0 && !offer.temporarilyFree)) {
                i$++;
            } else {
                if (LibraryUtils.isOwned(document, (Library) library)) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    private static boolean shouldShowExternallyHostedMessage(Document document) {
        return document.getAppDetails() != null && document.getAppDetails().externallyHosted;
    }
}
