package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoBottomDivider;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoTopDivider;
import com.google.android.finsky.layout.DetailsSectionStack.NoBottomSeparator;
import com.google.android.finsky.layout.DetailsSectionStack.NoTopSeparator;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.VoucherUtils;
import java.util.List;

public class WarningMessageSection extends LinearLayout implements NoBottomDivider, NoTopDivider, NoBottomSeparator, NoTopSeparator {
    private boolean mBinded;
    private TextView mDetailsWarningInfoFirstLineText;
    private ImageView mDetailsWarningInfoIcon;
    private TextView mDetailsWarningInfoSecondLineText;

    public WarningMessageSection(Context context) {
        super(context);
    }

    public WarningMessageSection(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDetailsWarningInfoFirstLineText = (TextView) findViewById(R.id.details_warning_info_first_line);
        this.mDetailsWarningInfoSecondLineText = (TextView) findViewById(R.id.details_warning_info_second_line);
        this.mDetailsWarningInfoIcon = (ImageView) findViewById(R.id.details_warning_info_icon);
    }

    public void bind(Document document, DfeToc toc, Libraries libraries, Account currentAccount) {
        this.mBinded = true;
        AccountLibrary accountLibrary = libraries.getAccountLibrary(currentAccount);
        boolean isAvailable = LibraryUtils.isAvailable(document, toc, accountLibrary);
        boolean hasApplicableVoucher = VoucherUtils.hasApplicableVouchers(document, accountLibrary) && document.hasApplicableVoucherDescription();
        boolean hasWarningMessage = document.hasWarningMessage();
        boolean showLicenseMessage = shouldShowLicenseMessage(document, accountLibrary);
        boolean showExternallyHostedMessage = shouldShowExternallyHostedMessage(document);
        CharSequence accountOwnerWarning = getAlternateAccountOwnerText(document, accountLibrary, libraries, currentAccount, toc);
        if (!isAvailable || showLicenseMessage || hasWarningMessage || hasApplicableVoucher || showExternallyHostedMessage || !TextUtils.isEmpty(accountOwnerWarning)) {
            setVisibility(0);
            this.mDetailsWarningInfoSecondLineText.setVisibility(8);
            int backend = document.getBackend();
            if (!isAvailable) {
                this.mDetailsWarningInfoFirstLineText.setText(DocUtils.getAvailabilityRestrictionResourceId(document));
                this.mDetailsWarningInfoIcon.setImageResource(CorpusResourceUtils.getWarningDrawable(backend));
            } else if (showLicenseMessage) {
                this.mDetailsWarningInfoFirstLineText.setText(R.string.enterprise_admin_purchased_app);
                this.mDetailsWarningInfoIcon.setImageResource(R.drawable.ic_enterprise);
            } else if (showExternallyHostedMessage) {
                this.mDetailsWarningInfoFirstLineText.setText(R.string.enterprise_externally_hosted_application);
                this.mDetailsWarningInfoIcon.setImageResource(CorpusResourceUtils.getWarningDrawable(backend));
            } else if (hasWarningMessage) {
                this.mDetailsWarningInfoFirstLineText.setText(document.getWarningMessage());
                this.mDetailsWarningInfoFirstLineText.setMovementMethod(LinkMovementMethod.getInstance());
                this.mDetailsWarningInfoIcon.setImageResource(CorpusResourceUtils.getWarningDrawable(backend));
            } else if (hasApplicableVoucher) {
                this.mDetailsWarningInfoFirstLineText.setText(document.getApplicableVoucherDescription());
                this.mDetailsWarningInfoIcon.setImageResource(CorpusResourceUtils.getRewardDrawable(backend));
            } else {
                this.mDetailsWarningInfoFirstLineText.setText(accountOwnerWarning);
                this.mDetailsWarningInfoIcon.setImageResource(CorpusResourceUtils.getWarningDrawable(backend));
            }
            Context context = getContext();
            ColorStateList secondaryTextColor = CorpusResourceUtils.getSecondaryTextColor(context, backend);
            this.mDetailsWarningInfoFirstLineText.setTextColor(secondaryTextColor);
            this.mDetailsWarningInfoSecondLineText.setTextColor(secondaryTextColor);
            int blendedFill = UiUtils.interpolateColor(CorpusResourceUtils.getPrimaryColor(context, backend), -1, 0.15f);
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int paddingRight = getPaddingRight();
            int paddingLeft = getPaddingLeft();
            setBackgroundDrawable(new LayerDrawable(new Drawable[]{new ColorDrawable(blendedFill), context.getResources().getDrawable(R.drawable.play_highlight_overlay_dark)}));
            setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            return;
        }
        setVisibility(8);
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

    public boolean hasBinded() {
        return this.mBinded;
    }

    private CharSequence getAlternateAccountOwnerText(Document document, AccountLibrary accountLibrary, Libraries libraries, Account currentAccount, DfeToc toc) {
        if (document.getDocumentType() == 1) {
            return null;
        }
        if (LibraryUtils.isOwned(document, (Library) accountLibrary)) {
            return null;
        }
        Account owner = LibraryUtils.getFirstOwner(document, libraries);
        Context context = getContext();
        if (owner != null) {
            return Html.fromHtml(context.getString(R.string.owned_by_other_account, new Object[]{owner.name}));
        }
        if (document.hasSubscriptions()) {
            List subscriptions = DocUtils.getSubscriptions(document, toc, libraries);
            if (LibraryUtils.getOwnerWithCurrentAccount(subscriptions, libraries, currentAccount) != null) {
                return null;
            }
            for (int i = 0; i < subscriptions.size(); i++) {
                if (LibraryUtils.getFirstOwner((Document) subscriptions.get(i), libraries) != null) {
                    return Html.fromHtml(context.getString(R.string.owned_by_other_account, new Object[]{LibraryUtils.getFirstOwner((Document) subscriptions.get(i), libraries).name}));
                }
            }
        }
        return null;
    }
}
