package com.google.android.finsky.detailspage;

import android.text.Html;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.DetailsTextSection;
import com.google.android.finsky.layout.DetailsTextSection.ExpandedData;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.protos.DocDetails.ProductDetailsDescription;
import com.google.android.finsky.protos.DocDetails.ProductDetailsSection;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.play.utils.collections.Lists;
import java.util.List;

public class DescriptionTextModule extends TextModule implements InstallerListener {
    private Document mDetailsDoc;

    protected Data getData(Document detailsDoc, boolean hasDetailsLoaded) {
        Badge badge = null;
        String promotionalDescription = detailsDoc.getPromotionalDescription();
        if (TextUtils.isEmpty(promotionalDescription) && !hasDetailsLoaded) {
            return null;
        }
        CharSequence whatsNew;
        boolean z;
        Installer installer = FinskyApp.get().getInstaller();
        installer.removeListener(this);
        installer.addListener(this);
        this.mDetailsDoc = detailsDoc;
        Data data = new Data();
        data.backend = detailsDoc.getBackend();
        data.docType = detailsDoc.getDocumentType();
        CharSequence fullDesc = Html.fromHtml(detailsDoc.getRawDescription());
        String translated = detailsDoc.getRawTranslatedDescription();
        CharSequence translatedDesc = TextUtils.isEmpty(translated) ? null : Html.fromHtml(translated);
        data.callout = promotionalDescription;
        data.calloutGravity = 1;
        data.restrictCalloutMaxLines = false;
        if (TextUtils.isEmpty(data.callout) && detailsDoc.getDocumentType() != 1) {
            data.callout = fullDesc;
            data.calloutGravity = 3;
            data.restrictCalloutMaxLines = true;
            fullDesc = null;
        }
        data.bodyHeader = null;
        data.body = fullDesc;
        data.translatedBody = translatedDesc;
        if (detailsDoc.hasWhatsNew()) {
            whatsNew = detailsDoc.getWhatsNew();
        } else {
            whatsNew = null;
        }
        data.whatsNew = whatsNew;
        if (isOwned(detailsDoc)) {
            z = false;
        } else {
            z = true;
        }
        data.hideWhatsNewInCollapsed = z;
        if (detailsDoc.getDocumentType() == 8 && detailsDoc.hasCreatorBadges()) {
            badge = detailsDoc.getCreatorBadges()[0];
        }
        data.creatorBadge = badge;
        data.iconDescriptionPairs = getIconDescriptionPairs(detailsDoc);
        return data;
    }

    protected ExpandedData getExpandedData(Document detailsDoc, boolean hasDetailsLoaded, Data moduleData) {
        ExpandedData data = super.getExpandedData(detailsDoc, hasDetailsLoaded, moduleData);
        if (data == null) {
            return null;
        }
        DetailsTextSection.fillContentRatingDetails(detailsDoc, this.mContext, data);
        DetailsTextSection.fillExtraCreditsList(detailsDoc, this.mContext, data);
        DetailsTextSection.fillExtraPrimaryList(detailsDoc, data);
        DetailsTextSection.fillExtraSecondaryList(detailsDoc, this.mContext, data);
        DetailsTextSection.fillExtraAttributionsString(detailsDoc, data);
        DetailsTextSection.addProductDetails(detailsDoc, data);
        return data;
    }

    private boolean isOwned(Document doc) {
        if (doc.getDocumentType() != 1) {
            return LibraryUtils.isOwned(doc, this.mLibraries.getAccountLibrary(this.mDfeApi.getAccount()));
        }
        boolean isInstalled;
        String appPackageName = doc.getAppDetails().packageName;
        if (FinskyApp.get().getPackageInfoRepository().get(appPackageName) != null) {
            isInstalled = true;
        } else {
            isInstalled = false;
        }
        boolean isTrackedByInstaller;
        if (FinskyApp.get().getInstaller().getState(appPackageName) != InstallerState.NOT_TRACKED) {
            isTrackedByInstaller = true;
        } else {
            isTrackedByInstaller = false;
        }
        if (isInstalled || isTrackedByInstaller) {
            return true;
        }
        return false;
    }

    private List<Pair<Image, String>> getIconDescriptionPairs(Document doc) {
        Image badgeImage;
        List<Pair<Image, String>> iconDescriptionPairs = Lists.newArrayList();
        if (doc.hasBadgeContainer()) {
            for (Badge badge : doc.getBadgeContainer().badge) {
                badgeImage = BadgeUtils.getImage(badge, 6);
                if (badgeImage != null) {
                    iconDescriptionPairs.add(new Pair(badgeImage, badge.title));
                }
            }
        }
        if (doc.hasProductDetails()) {
            for (ProductDetailsSection section : doc.getProductDetails().section) {
                for (ProductDetailsDescription description : section.description) {
                    badgeImage = description.image;
                    if (badgeImage != null) {
                        iconDescriptionPairs.add(new Pair(badgeImage, section.title));
                    }
                }
            }
        }
        return iconDescriptionPairs;
    }

    public void onDestroyModule() {
        super.onDestroyModule();
        FinskyApp.get().getInstaller().removeListener(this);
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        AppDetails appDetails = this.mDetailsDoc.getAppDetails();
        if (appDetails != null && packageName.equals(appDetails.packageName)) {
            ((Data) this.mModuleData).hideWhatsNewInCollapsed = !isOwned(this.mDetailsDoc);
            this.mFinskyModuleController.refreshModule(this, true);
        }
    }
}
