package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.Containers.ContainerMetadata;
import com.google.android.finsky.protos.Containers.ContainerView;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.protos.DocAnnotations.BadgeContainer;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.DocAnnotations.PurchaseHistoryDetails;
import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.protos.DocAnnotations.Warning;
import com.google.android.finsky.protos.DocDetails.AlbumDetails;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.protos.DocDetails.ArtistDetails;
import com.google.android.finsky.protos.DocDetails.BookDetails;
import com.google.android.finsky.protos.DocDetails.MagazineDetails;
import com.google.android.finsky.protos.DocDetails.PersonDetails;
import com.google.android.finsky.protos.DocDetails.ProductDetails;
import com.google.android.finsky.protos.DocDetails.SongDetails;
import com.google.android.finsky.protos.DocDetails.TalentDetails;
import com.google.android.finsky.protos.DocDetails.TvEpisodeDetails;
import com.google.android.finsky.protos.DocDetails.TvSeasonDetails;
import com.google.android.finsky.protos.DocDetails.VideoCredit;
import com.google.android.finsky.protos.DocDetails.VideoDetails;
import com.google.android.finsky.protos.DocumentV2.ActionBanner;
import com.google.android.finsky.protos.DocumentV2.Annotations;
import com.google.android.finsky.protos.DocumentV2.ContainerWithBanner;
import com.google.android.finsky.protos.DocumentV2.DealOfTheDay;
import com.google.android.finsky.protos.DocumentV2.Dismissal;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.EditorialSeriesContainer;
import com.google.android.finsky.protos.DocumentV2.EmptyContainer;
import com.google.android.finsky.protos.DocumentV2.NextBanner;
import com.google.android.finsky.protos.DocumentV2.OverflowLink;
import com.google.android.finsky.protos.DocumentV2.PlusOneData;
import com.google.android.finsky.protos.DocumentV2.RecommendationsContainerWithHeader;
import com.google.android.finsky.protos.DocumentV2.SeriesAntenna;
import com.google.android.finsky.protos.DocumentV2.SuggestionReasons;
import com.google.android.finsky.protos.DocumentV2.Template;
import com.google.android.finsky.protos.DocumentV2.VoucherInfo;
import com.google.android.finsky.protos.DocumentV2.WarmWelcome;
import com.google.android.finsky.protos.Rating.AggregateRating;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FastHtmlParser;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document implements Parcelable {
    public static Creator<Document> CREATOR;
    private Document[] mChildDocuments;
    private final DocV2 mDocument;
    private Map<Integer, List<Image>> mImageTypeMap;
    private List<Document> mSubscriptionsList;
    private Document mTrustedSourceProfileDocument;

    public Document(DocV2 document) {
        this.mDocument = document;
    }

    public byte[] getServerLogsCookie() {
        return this.mDocument.serverLogsCookie;
    }

    public int getChildCount() {
        return this.mDocument.child.length;
    }

    public Document getChildAt(int index) {
        if (this.mChildDocuments == null) {
            this.mChildDocuments = new Document[getChildCount()];
        }
        if (this.mChildDocuments[index] == null) {
            this.mChildDocuments[index] = new Document(this.mDocument.child[index]);
        }
        return this.mChildDocuments[index];
    }

    public Document[] getChildren() {
        if (this.mChildDocuments == null) {
            this.mChildDocuments = new Document[getChildCount()];
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (this.mChildDocuments[i] == null) {
                this.mChildDocuments[i] = new Document(this.mDocument.child[i]);
            }
        }
        return this.mChildDocuments;
    }

    public String getDocId() {
        return this.mDocument.docid;
    }

    public Docid getFullDocid() {
        return DocUtils.createDocid(getBackend(), getDocumentType(), getBackendDocId());
    }

    public int getBackend() {
        return this.mDocument.backendId;
    }

    public String getBackendDocId() {
        return this.mDocument.backendDocid;
    }

    public boolean hasDocumentType() {
        return this.mDocument.hasDocType;
    }

    public int getDocumentType() {
        return this.mDocument.docType;
    }

    public String getDetailsUrl() {
        return this.mDocument.detailsUrl;
    }

    public String getShareUrl() {
        return this.mDocument.shareUrl;
    }

    public String getReviewsUrl() {
        return this.mDocument.reviewsUrl;
    }

    public int getVersionCode() {
        return getDocumentType() == 1 ? getAppDetails().versionCode : -1;
    }

    public String getRelatedDocTypeHeader() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations == null || annotations.sectionRelatedDocType == null) {
            return "";
        }
        return annotations.sectionRelatedDocType.header;
    }

    public String getRelatedDocTypeListUrl() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations == null || annotations.sectionRelatedDocType == null) {
            return "";
        }
        return annotations.sectionRelatedDocType.listUrl;
    }

    public boolean hasBodyOfWork() {
        Annotations annotations = this.mDocument.annotations;
        return (annotations == null || annotations.sectionBodyOfWork == null) ? false : true;
    }

    public String getBodyOfWorkHeader() {
        Annotations annotations = this.mDocument.annotations;
        if (hasBodyOfWork()) {
            return annotations.sectionBodyOfWork.header;
        }
        return "";
    }

    public String getBodyOfWorkBrowseUrl() {
        Annotations annotations = this.mDocument.annotations;
        if (hasBodyOfWork()) {
            return annotations.sectionBodyOfWork.browseUrl;
        }
        return "";
    }

    public String getBodyOfWorkListUrl() {
        Annotations annotations = this.mDocument.annotations;
        if (hasBodyOfWork()) {
            return annotations.sectionBodyOfWork.listUrl;
        }
        return "";
    }

    public String getCoreContentHeader() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations == null || annotations.sectionCoreContent == null) {
            return "";
        }
        return annotations.sectionCoreContent.header;
    }

    public String getCoreContentListUrl() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations == null || annotations.sectionCoreContent == null) {
            return "";
        }
        return annotations.sectionCoreContent.listUrl;
    }

    public boolean hasCreatorRelatedContent() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations == null || annotations.sectionMoreBy == null || annotations.sectionMoreBy.listUrl.length() <= 0) {
            return false;
        }
        return true;
    }

    public ArtistDetails getDisplayArtist() {
        if (getAlbumDetails() != null) {
            return getAlbumDetails().displayArtist;
        }
        return null;
    }

    public boolean hasLinkAnnotation() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations == null || annotations.link == null) {
            return false;
        }
        return true;
    }

    public Link getLinkAnnotation() {
        if (this.mDocument.annotations != null) {
            return this.mDocument.annotations.link;
        }
        return null;
    }

    public boolean hasOptimalDeviceClassWarning() {
        Annotations annotations = this.mDocument.annotations;
        return (annotations == null || annotations.optimalDeviceClassWarning == null) ? false : true;
    }

    public Warning getOptimalDeviceClassWarning() {
        if (hasOptimalDeviceClassWarning()) {
            return this.mDocument.annotations.optimalDeviceClassWarning;
        }
        return null;
    }

    public boolean hasContainerAnnotation() {
        return this.mDocument.containerMetadata != null;
    }

    public ContainerMetadata getContainerAnnotation() {
        return this.mDocument.containerMetadata;
    }

    public boolean hasContainerViews() {
        if (hasContainerAnnotation() && getContainerAnnotation().containerView.length > 0) {
            return true;
        }
        return false;
    }

    public ContainerView[] getContainerViews() {
        return getContainerAnnotation().containerView;
    }

    public SectionMetadata getCrossSellSection() {
        return this.mDocument.annotations != null ? this.mDocument.annotations.sectionCrossSell : null;
    }

    public SectionMetadata getPostPurchaseCrossSellSection() {
        return this.mDocument.annotations != null ? this.mDocument.annotations.sectionPurchaseCrossSell : null;
    }

    public boolean hasMoreBy() {
        return getMoreBySectionMetadata() != null;
    }

    public String getMoreByHeader() {
        return hasMoreBy() ? getMoreBySectionMetadata().header : "";
    }

    public String getMoreByListUrl() {
        return hasMoreBy() ? getMoreBySectionMetadata().listUrl : "";
    }

    public String getMoreByBrowseUrl() {
        return hasMoreBy() ? getMoreBySectionMetadata().browseUrl : "";
    }

    private SectionMetadata getMoreBySectionMetadata() {
        return this.mDocument.annotations != null ? this.mDocument.annotations.sectionMoreBy : null;
    }

    public boolean hasRelated() {
        return getRelatedSectionMetadata() != null;
    }

    public String getRelatedHeader() {
        return hasRelated() ? getRelatedSectionMetadata().header : "";
    }

    public String getRelatedBrowseUrl() {
        return hasRelated() ? getRelatedSectionMetadata().browseUrl : "";
    }

    public String getRelatedListUrl() {
        return hasRelated() ? getRelatedSectionMetadata().listUrl : "";
    }

    private SectionMetadata getRelatedSectionMetadata() {
        return this.mDocument.annotations != null ? this.mDocument.annotations.sectionRelated : null;
    }

    public boolean hasCrossSell() {
        return getCrossSellSectionMetadata() != null;
    }

    public String getCrossSellHeader() {
        return hasCrossSell() ? getCrossSellSectionMetadata().header : "";
    }

    public String getCrossSellListUrl() {
        return hasCrossSell() ? getCrossSellSectionMetadata().listUrl : "";
    }

    public String getCrossSellBrowseUrl() {
        return hasCrossSell() ? getCrossSellSectionMetadata().browseUrl : "";
    }

    private SectionMetadata getCrossSellSectionMetadata() {
        return this.mDocument.annotations != null ? this.mDocument.annotations.sectionCrossSell : null;
    }

    public SectionMetadata getSuggestForRatingSection() {
        Annotations annotations = this.mDocument.annotations;
        return annotations != null ? annotations.sectionSuggestForRating : null;
    }

    public String getOfferNote() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations != null) {
            return annotations.offerNote;
        }
        return "";
    }

    public String getPrivacyPolicyUrl() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations != null) {
            return annotations.privacyPolicyUrl;
        }
        return null;
    }

    public OverflowLink[] getOverflowLinks() {
        Annotations annotations = this.mDocument.annotations;
        if (annotations != null) {
            return annotations.overflowLink;
        }
        return null;
    }

    public String getCreator() {
        return this.mDocument.creator;
    }

    public Document getCreatorDoc() {
        if (hasCreatorDoc()) {
            return new Document(this.mDocument.annotations.creatorDoc);
        }
        return null;
    }

    public boolean hasCreatorDoc() {
        return (this.mDocument.annotations == null || this.mDocument.annotations.creatorDoc == null) ? false : true;
    }

    public String getTitle() {
        return this.mDocument.title;
    }

    public String getSubtitle() {
        return this.mDocument.subtitle;
    }

    public String getPromotionalDescription() {
        return this.mDocument.promotionalDescription;
    }

    public String getRawDescription() {
        return this.mDocument.descriptionHtml;
    }

    public CharSequence getDescription() {
        return FastHtmlParser.fromHtml(getRawDescription());
    }

    public void setDescription(String description) {
        this.mDocument.descriptionHtml = description;
        this.mDocument.hasDescriptionHtml = true;
    }

    public boolean hasVouchers() {
        return this.mDocument.annotations != null && this.mDocument.annotations.voucherInfo.length > 0;
    }

    public VoucherInfo[] getVouchers() {
        return this.mDocument.annotations.voucherInfo;
    }

    public boolean hasApplicableVoucherDescription() {
        return (this.mDocument.annotations == null || TextUtils.isEmpty(this.mDocument.annotations.applicableVoucherDescription)) ? false : true;
    }

    public String getApplicableVoucherDescription() {
        return this.mDocument.annotations.applicableVoucherDescription;
    }

    public String getRawTranslatedDescription() {
        return this.mDocument.translatedDescriptionHtml;
    }

    public boolean hasWhatsNew() {
        return (!hasDetails() || getAppDetails() == null || TextUtils.isEmpty(getAppDetails().recentChangesHtml)) ? false : true;
    }

    public CharSequence getWhatsNew() {
        if (!hasDetails() || getAppDetails() == null) {
            return "";
        }
        return FastHtmlParser.fromHtml(getAppDetails().recentChangesHtml);
    }

    public boolean hasRating() {
        return this.mDocument.aggregateRating != null;
    }

    public float getStarRating() {
        return this.mDocument.aggregateRating.starRating;
    }

    public long getRatingCount() {
        return this.mDocument.aggregateRating.ratingsCount;
    }

    public int[] getRatingHistogram() {
        if (!hasRating()) {
            return new int[]{0, 0, 0, 0, 0};
        }
        AggregateRating agg = this.mDocument.aggregateRating;
        return new int[]{(int) agg.fiveStarRatings, (int) agg.fourStarRatings, (int) agg.threeStarRatings, (int) agg.twoStarRatings, (int) agg.oneStarRatings};
    }

    public boolean isMature() {
        return this.mDocument.mature;
    }

    public List<Image> getImages(int imageType) {
        return (List) getImageTypeMap().get(Integer.valueOf(imageType));
    }

    public boolean hasImages(int imageType) {
        return getImageTypeMap().containsKey(Integer.valueOf(imageType));
    }

    private Map<Integer, List<Image>> getImageTypeMap() {
        if (this.mImageTypeMap == null) {
            this.mImageTypeMap = new HashMap();
            for (Image image : this.mDocument.image) {
                int imageType = image.imageType;
                if (!this.mImageTypeMap.containsKey(Integer.valueOf(imageType))) {
                    this.mImageTypeMap.put(Integer.valueOf(imageType), new ArrayList());
                }
                ((List) this.mImageTypeMap.get(Integer.valueOf(imageType))).add(image);
            }
        }
        return this.mImageTypeMap;
    }

    public boolean hasDetails() {
        return this.mDocument.details != null;
    }

    public AppDetails getAppDetails() {
        return hasDetails() ? this.mDocument.details.appDetails : null;
    }

    public AlbumDetails getAlbumDetails() {
        return hasDetails() ? this.mDocument.details.albumDetails : null;
    }

    public ArtistDetails getArtistDetails() {
        return hasDetails() ? this.mDocument.details.artistDetails : null;
    }

    public SongDetails getSongDetails() {
        return hasDetails() ? this.mDocument.details.songDetails : null;
    }

    public BookDetails getBookDetails() {
        return hasDetails() ? this.mDocument.details.bookDetails : null;
    }

    public VideoDetails getVideoDetails() {
        return hasDetails() ? this.mDocument.details.videoDetails : null;
    }

    public TvEpisodeDetails getTvEpisodeDetails() {
        return hasDetails() ? this.mDocument.details.tvEpisodeDetails : null;
    }

    public TvSeasonDetails getTvSeasonDetails() {
        return hasDetails() ? this.mDocument.details.tvSeasonDetails : null;
    }

    public MagazineDetails getMagazineDetails() {
        return hasDetails() ? this.mDocument.details.magazineDetails : null;
    }

    public PersonDetails getPersonDetails() {
        return hasDetails() ? this.mDocument.details.personDetails : null;
    }

    public TalentDetails getTalentDetails() {
        return hasDetails() ? this.mDocument.details.talentDetails : null;
    }

    public boolean hasProductDetails() {
        return this.mDocument.productDetails != null;
    }

    public ProductDetails getProductDetails() {
        return this.mDocument.productDetails;
    }

    public String getAttributions() {
        return this.mDocument.annotations != null ? this.mDocument.annotations.attributionHtml : "";
    }

    public boolean isInProgressSeason() {
        TvSeasonDetails seasonDetails = getTvSeasonDetails();
        return this.mDocument.docType == 19 && seasonDetails != null && seasonDetails.hasExpectedEpisodeCount && seasonDetails.episodeCount != seasonDetails.expectedEpisodeCount;
    }

    public int getNormalizedContentRating() {
        AppDetails details = getAppDetails();
        return details == null ? -1 : details.contentRating - 1;
    }

    public VideoCredit[] getVideoCredits() {
        VideoDetails details = getVideoDetails();
        return details != null ? details.credit : null;
    }

    public String getYouTubeWatchUrl() {
        if (getBackend() == 4) {
            return this.mDocument.backendUrl;
        }
        return null;
    }

    public Offer[] getAvailableOffers() {
        return this.mDocument.offer;
    }

    public Offer getOffer(int offerType) {
        for (Offer offer : getAvailableOffers()) {
            if (offer.offerType == offerType) {
                return offer;
            }
        }
        return null;
    }

    public static boolean isPreorderOffer(Offer offer) {
        return offer != null && ((offer.offerType == 1 || offer.offerType == 7) && offer.hasOnSaleDate && offer.onSaleDate > System.currentTimeMillis());
    }

    public boolean hasPreorderOffer() {
        if (isPreorderOffer(getOffer(1)) || isPreorderOffer(getOffer(7))) {
            return true;
        }
        return false;
    }

    public int getAvailabilityRestriction() {
        return this.mDocument.availability != null ? this.mDocument.availability.restriction : -1;
    }

    public boolean isAvailableIfOwned() {
        return this.mDocument.availability != null && this.mDocument.availability.availableIfOwned;
    }

    public boolean hasSample() {
        for (Offer offer : this.mDocument.offer) {
            if (offer.offerType == 2) {
                return true;
            }
        }
        return false;
    }

    public String getFormattedPrice(int offerType) {
        Offer offer = getOffer(offerType);
        if (offer == null || !offer.hasFormattedAmount) {
            return null;
        }
        return offer.formattedAmount;
    }

    public boolean needsCheckoutFlow(int offerType) {
        Offer offer = getOffer(offerType);
        if (offer != null) {
            return offer.checkoutFlowRequired;
        }
        return false;
    }

    public boolean hasScreenshots() {
        List<Image> imageList = getImages(1);
        if (imageList == null || imageList.isEmpty() || 1 == getBackend()) {
            return false;
        }
        return true;
    }

    public boolean hasPlusOneData() {
        if (this.mDocument.annotations == null || this.mDocument.annotations.plusOneData == null) {
            return false;
        }
        return true;
    }

    public PlusOneData getPlusOneData() {
        if (this.mDocument.annotations != null) {
            return this.mDocument.annotations.plusOneData;
        }
        return null;
    }

    public boolean hasWarningMessage() {
        return this.mDocument.annotations != null && this.mDocument.annotations.warning.length > 0;
    }

    public CharSequence getWarningMessage() {
        StringBuilder sb = new StringBuilder();
        Annotations annotations = this.mDocument.annotations;
        int warningCount = annotations.warning.length;
        for (int i = 0; i < warningCount; i++) {
            if (i != 0) {
                sb.append("<br />");
            }
            sb.append(annotations.warning[i].localizedMessage);
        }
        return FastHtmlParser.fromHtml(sb.toString());
    }

    public boolean hasCreatorBadges() {
        return this.mDocument.annotations.badgeForCreator.length > 0;
    }

    public Badge getFirstCreatorBadge() {
        return this.mDocument.annotations.badgeForCreator[0];
    }

    public Badge[] getCreatorBadges() {
        return this.mDocument.annotations.badgeForCreator;
    }

    public boolean hasItemBadges() {
        return this.mDocument.annotations.badgeForDoc.length > 0;
    }

    public Badge getFirstItemBadge() {
        return this.mDocument.annotations.badgeForDoc[0];
    }

    public Badge[] getItemBadges() {
        return this.mDocument.annotations.badgeForDoc;
    }

    public Badge getBadgeForContentRating() {
        return this.mDocument.annotations.badgeForContentRating;
    }

    public boolean hasBadgeContainer() {
        return (this.mDocument.annotations == null || this.mDocument.annotations.docBadgeContainer == null || this.mDocument.annotations.docBadgeContainer.length <= 0) ? false : true;
    }

    public BadgeContainer getBadgeContainer() {
        return this.mDocument.annotations.docBadgeContainer[0];
    }

    public boolean hasDealOfTheDayInfo() {
        return (getTemplate() == null || getTemplate().dealOfTheDay == null) ? false : true;
    }

    public boolean hasNextBanner() {
        return (getTemplate() == null || getTemplate().nextBanner == null) ? false : true;
    }

    public boolean hasAntennaInfo() {
        return (getTemplate() == null || getTemplate().seriesAntenna == null) ? false : true;
    }

    public boolean isRateCluster() {
        return (getTemplate() == null || getTemplate().rateContainer == null) ? false : true;
    }

    public boolean isRateAndSuggestCluster() {
        return (getTemplate() == null || getTemplate().rateAndSuggestContainer == null) ? false : true;
    }

    public boolean isAddToCirclesContainer() {
        return (getTemplate() == null || getTemplate().addToCirclesContainer == null) ? false : true;
    }

    public boolean isMyCirclesContainer() {
        return (getTemplate() == null || getTemplate().myCirclesContainer == null) ? false : true;
    }

    public boolean isTrustedSourceContainer() {
        return (getTemplate() == null || getTemplate().trustedSourceContainer == null || getTemplate().trustedSourceContainer.source == null) ? false : true;
    }

    public Document getTrustedSourcePersonDoc() {
        if (this.mTrustedSourceProfileDocument == null) {
            this.mTrustedSourceProfileDocument = new Document(getTemplate().trustedSourceContainer.source);
        }
        return this.mTrustedSourceProfileDocument;
    }

    public boolean isEmptyContainer() {
        return (getTemplate() == null || getTemplate().emptyContainer == null) ? false : true;
    }

    public boolean hasEditorialSeriesContainer() {
        return (this.mDocument.annotations == null || this.mDocument.annotations.template == null || this.mDocument.annotations.template.editorialSeriesContainer == null) ? false : true;
    }

    public EditorialSeriesContainer getEditorialSeriesContainer() {
        return this.mDocument.annotations.template.editorialSeriesContainer;
    }

    public boolean hasRecommendationsContainerTemplate() {
        return (this.mDocument.annotations == null || this.mDocument.annotations.template == null || this.mDocument.annotations.template.recommendationsContainer == null) ? false : true;
    }

    public boolean hasRecommendationsContainerWithHeaderTemplate() {
        return (this.mDocument.annotations == null || this.mDocument.annotations.template == null || this.mDocument.annotations.template.recommendationsContainerWithHeader == null) ? false : true;
    }

    public RecommendationsContainerWithHeader getRecommendationsContainerWithHeaderTemplate() {
        return this.mDocument.annotations.template.recommendationsContainerWithHeader;
    }

    public boolean hasContainerWithBannerTemplate() {
        return (this.mDocument.annotations == null || this.mDocument.annotations.template == null || this.mDocument.annotations.template.containerWithBanner == null) ? false : true;
    }

    public SeriesAntenna getAntennaInfo() {
        return getTemplate().seriesAntenna;
    }

    public DealOfTheDay getDealOfTheDayInfo() {
        return getTemplate().dealOfTheDay;
    }

    public NextBanner getNextBannerInfo() {
        return getTemplate().nextBanner;
    }

    public SuggestionReasons getSuggestionReasons() {
        if (this.mDocument.annotations == null || this.mDocument.annotations.suggestionReasons == null) {
            return null;
        }
        return this.mDocument.annotations.suggestionReasons;
    }

    public PurchaseHistoryDetails getPurchaseHistoryDetails() {
        if (this.mDocument.annotations == null || this.mDocument.annotations.purchaseHistoryDetails == null) {
            return null;
        }
        return this.mDocument.annotations.purchaseHistoryDetails;
    }

    public boolean hasEmptyContainer() {
        return (getTemplate() == null || getTemplate().emptyContainer == null) ? false : true;
    }

    public EmptyContainer getEmptyContainer() {
        return getTemplate().emptyContainer;
    }

    public boolean isActionBanner() {
        return (getTemplate() == null || getTemplate().actionBanner == null) ? false : true;
    }

    public ActionBanner getActionBanner() {
        return isActionBanner() ? getTemplate().actionBanner : null;
    }

    public boolean isSingleCardWithButton() {
        return false;
    }

    public boolean isWarmWelcome() {
        return (getTemplate() == null || getTemplate().warmWelcome == null) ? false : true;
    }

    public WarmWelcome getWarmWelcome() {
        return isWarmWelcome() ? getTemplate().warmWelcome : null;
    }

    public boolean isHighlighted() {
        return (getTemplate() == null || getTemplate().snow == null) ? false : true;
    }

    public String getClickUrl() {
        Template template = getTemplate();
        return (template == null || template.snow == null) ? null : template.snow.clickUrl;
    }

    public CharSequence getHighlightText() {
        Template template = getTemplate();
        return (template == null || template.snow == null) ? null : FastHtmlParser.fromHtml(template.snow.snowText);
    }

    public CharSequence getHighlightBadgeText() {
        Template template = getTemplate();
        return (template == null || template.snow == null) ? null : template.snow.snowBadgeText;
    }

    public ContainerWithBanner getContainerWithBannerTemplate() {
        return this.mDocument.annotations.template.containerWithBanner;
    }

    public boolean hasNeutralDismissal() {
        SuggestionReasons suggestionReasons = getSuggestionReasons();
        return (suggestionReasons == null || suggestionReasons.neutralDismissal == null) ? false : true;
    }

    public Dismissal getNeutralDismissal() {
        return hasNeutralDismissal() ? getSuggestionReasons().neutralDismissal : null;
    }

    @Deprecated
    public String getDescriptionReason() {
        SuggestionReasons reasons = getSuggestionReasons();
        return (reasons == null || reasons.reason.length <= 0) ? "" : reasons.reason[0].descriptionHtml;
    }

    private Template getTemplate() {
        return this.mDocument.annotations != null ? this.mDocument.annotations.template : null;
    }

    public boolean hasCensoring() {
        AlbumDetails albumDetails = getAlbumDetails();
        return (albumDetails == null || albumDetails.details == null || !albumDetails.details.hasCensoring) ? false : true;
    }

    public int getCensoring() {
        return this.mDocument.details.albumDetails.details.censoring;
    }

    public boolean hasReleaseType() {
        AlbumDetails albumDetails = getAlbumDetails();
        return (albumDetails == null || albumDetails.details == null || albumDetails.details.releaseType.length <= 0) ? false : true;
    }

    public int getReleaseType() {
        return this.mDocument.details.albumDetails.details.releaseType[0];
    }

    public boolean hasSubscriptions() {
        return this.mDocument.annotations != null && this.mDocument.annotations.subscription.length > 0;
    }

    public List<Document> getSubscriptionsList() {
        if (!hasSubscriptions()) {
            return null;
        }
        if (this.mSubscriptionsList == null) {
            this.mSubscriptionsList = new ArrayList(this.mDocument.annotations.subscription.length);
            DocV2[] subscriptions = this.mDocument.annotations.subscription;
            for (DocV2 child : subscriptions) {
                this.mSubscriptionsList.add(new Document(child));
            }
        }
        return this.mSubscriptionsList;
    }

    public boolean canUseAsPartialDocument() {
        if (getDocumentType() != 12 && getSongDetails() == null && this.mDocument.detailsReusable) {
            return true;
        }
        return false;
    }

    public boolean hasReviewHistogramData() {
        for (int count : getRatingHistogram()) {
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasReasons() {
        return (this.mDocument == null || getSuggestionReasons() == null || getSuggestionReasons().reason.length <= 0) ? false : true;
    }

    public boolean isDismissable() {
        return hasReasons() && hasNeutralDismissal();
    }

    public DocV2 getBackingDocV2() {
        return this.mDocument;
    }

    public boolean isOrdered() {
        if (this.mDocument.containerMetadata == null) {
            return false;
        }
        return this.mDocument.containerMetadata.ordered;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(ParcelableProto.forProto(this.mDocument), 0);
    }

    static {
        CREATOR = new Creator<Document>() {
            public Document createFromParcel(Parcel source) {
                return new Document((DocV2) ParcelableProto.getProtoFromParcel(source));
            }

            public Document[] newArray(int size) {
                return new Document[size];
            }
        };
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append(getDocId());
        if (getDocumentType() == 1) {
            sb.append(" v=").append(getAppDetails().versionCode);
        }
        sb.append('}');
        return sb.toString();
    }
}
