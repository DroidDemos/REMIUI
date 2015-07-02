package com.google.android.finsky.layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ContentFilterActivity;
import com.google.android.finsky.activities.TextSectionStateListener;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.protos.DocDetails.AlbumDetails;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.protos.DocDetails.MagazineDetails;
import com.google.android.finsky.protos.DocDetails.MusicDetails;
import com.google.android.finsky.protos.DocDetails.ProductDetailsDescription;
import com.google.android.finsky.protos.DocDetails.ProductDetailsSection;
import com.google.android.finsky.protos.DocDetails.VideoCredit;
import com.google.android.finsky.protos.DocDetails.VideoDetails;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FastHtmlParser;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.layout.PlayTextView;
import com.google.android.play.utils.PlayCorpusUtils;
import com.google.android.play.utils.UrlSpanUtils;
import com.google.android.play.utils.UrlSpanUtils.Listener;
import com.google.android.play.utils.collections.Lists;
import java.text.ParseException;
import java.util.List;

public class DetailsTextSection extends ForegroundLinearLayout {
    protected DetailsTextBlock mBodyContainerView;
    private PlayTextView mCalloutView;
    private ExpandedData mExpandedData;
    protected TextView mFooterLabel;
    private DetailsTextIconContainer mIconContainer;
    protected final int mMaxCollapsedLines;
    private NavigationManager mNavigationManager;
    private TextSectionStateListener mSectionStateListener;
    private View mSpacerView;
    private final int mTopDeveloperColor;
    private DecoratedTextView mTopDeveloperLabel;
    protected boolean mUrlSpanClicked;
    private final int mWhatsNewVerticalMargin;

    public static class DetailsExtraCredits implements Parcelable {
        public static final Creator<DetailsExtraCredits> CREATOR;
        public String credit;
        public String names;

        public DetailsExtraCredits(String credit, String names) {
            this.credit = credit;
            this.names = names;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.credit);
            dest.writeString(this.names);
        }

        static {
            CREATOR = new Creator<DetailsExtraCredits>() {
                public DetailsExtraCredits createFromParcel(Parcel source) {
                    return new DetailsExtraCredits(source.readString(), source.readString());
                }

                public DetailsExtraCredits[] newArray(int size) {
                    return new DetailsExtraCredits[size];
                }
            };
        }
    }

    public static class DetailsExtraPrimary implements Parcelable {
        public static final Creator<DetailsExtraPrimary> CREATOR;
        public String description;
        public Image image;
        public boolean skipInCollapsedMode;
        public String title;
        public String url;

        public DetailsExtraPrimary(String title, String description, String url, Image image, boolean skipInCollapsedMode) {
            this.title = title;
            this.description = description;
            this.url = url;
            this.image = image;
            this.skipInCollapsedMode = skipInCollapsedMode;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            int i = 0;
            dest.writeString(this.title);
            dest.writeString(this.description);
            dest.writeString(this.url);
            dest.writeParcelable(ParcelableProto.forProto(this.image), 0);
            if (this.skipInCollapsedMode) {
                i = 1;
            }
            dest.writeInt(i);
        }

        static {
            CREATOR = new Creator<DetailsExtraPrimary>() {
                public DetailsExtraPrimary createFromParcel(Parcel source) {
                    boolean z = true;
                    String readString = source.readString();
                    String readString2 = source.readString();
                    String readString3 = source.readString();
                    Image image = (Image) ParcelableProto.getProtoFromParcel(source);
                    if (source.readInt() != 1) {
                        z = false;
                    }
                    return new DetailsExtraPrimary(readString, readString2, readString3, image, z);
                }

                public DetailsExtraPrimary[] newArray(int size) {
                    return new DetailsExtraPrimary[size];
                }
            };
        }
    }

    public static class DetailsExtraSecondary implements Parcelable {
        public static final Creator<DetailsExtraSecondary> CREATOR;
        public String description;
        public String title;

        public DetailsExtraSecondary(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.description);
        }

        static {
            CREATOR = new Creator<DetailsExtraSecondary>() {
                public DetailsExtraSecondary createFromParcel(Parcel source) {
                    return new DetailsExtraSecondary(source.readString(), source.readString());
                }

                public DetailsExtraSecondary[] newArray(int size) {
                    return new DetailsExtraSecondary[size];
                }
            };
        }
    }

    public static class ExpandedData implements Parcelable {
        public static final Creator<ExpandedData> CREATOR;
        public int backend;
        public CharSequence body;
        public String bodyHeader;
        public CharSequence callout;
        public int calloutGravity;
        public String extraAttributions;
        public String extraCreditsHeader;
        public List<DetailsExtraCredits> extraCreditsList;
        public List<DetailsExtraPrimary> extraPrimaryList;
        public List<DetailsExtraSecondary> extraSecondaryList;
        public boolean promoteWhatsNew;
        public String title;
        public CharSequence translatedBody;
        public CharSequence whatsNew;
        public CharSequence whatsNewHeader;

        public ExpandedData() {
            this.extraCreditsList = Lists.newArrayList();
            this.extraPrimaryList = Lists.newArrayList();
            this.extraSecondaryList = Lists.newArrayList();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            int i = 0;
            dest.writeInt(this.backend);
            dest.writeString(this.title);
            TextUtils.writeToParcel(this.callout, dest, 0);
            dest.writeInt(this.calloutGravity);
            dest.writeString(this.bodyHeader);
            TextUtils.writeToParcel(this.body, dest, 0);
            TextUtils.writeToParcel(this.translatedBody, dest, 0);
            TextUtils.writeToParcel(this.whatsNewHeader, dest, 0);
            TextUtils.writeToParcel(this.whatsNew, dest, 0);
            if (this.promoteWhatsNew) {
                i = 1;
            }
            dest.writeInt(i);
            dest.writeString(this.extraCreditsHeader);
            dest.writeTypedList(this.extraCreditsList);
            dest.writeTypedList(this.extraPrimaryList);
            dest.writeTypedList(this.extraSecondaryList);
            dest.writeString(this.extraAttributions);
        }

        static {
            CREATOR = new Creator<ExpandedData>() {
                public ExpandedData createFromParcel(Parcel source) {
                    ExpandedData data = new ExpandedData();
                    data.backend = source.readInt();
                    data.title = source.readString();
                    data.callout = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
                    data.calloutGravity = source.readInt();
                    data.bodyHeader = source.readString();
                    data.body = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
                    data.translatedBody = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
                    data.whatsNewHeader = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
                    data.whatsNew = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
                    data.promoteWhatsNew = source.readInt() == 1;
                    data.extraCreditsHeader = source.readString();
                    data.extraCreditsList = Lists.newArrayList();
                    source.readTypedList(data.extraCreditsList, DetailsExtraCredits.CREATOR);
                    data.extraPrimaryList = Lists.newArrayList();
                    source.readTypedList(data.extraPrimaryList, DetailsExtraPrimary.CREATOR);
                    data.extraSecondaryList = Lists.newArrayList();
                    source.readTypedList(data.extraSecondaryList, DetailsExtraSecondary.CREATOR);
                    data.extraAttributions = source.readString();
                    return data;
                }

                public ExpandedData[] newArray(int size) {
                    return new ExpandedData[size];
                }
            };
        }
    }

    public DetailsTextSection(Context context) {
        this(context, null);
    }

    public DetailsTextSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mMaxCollapsedLines = res.getInteger(R.integer.details_text_collapsed_lines);
        this.mTopDeveloperColor = res.getColor(R.color.top_developer);
        this.mWhatsNewVerticalMargin = res.getDimensionPixelSize(R.dimen.details_whatsnew_vmargin);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCalloutView = (PlayTextView) findViewById(R.id.callout);
        this.mSpacerView = findViewById(R.id.spacer);
        this.mBodyContainerView = (DetailsTextBlock) findViewById(R.id.body_container);
        this.mIconContainer = (DetailsTextIconContainer) findViewById(R.id.icon_container);
        this.mTopDeveloperLabel = (DecoratedTextView) findViewById(R.id.top_developer_label);
        this.mFooterLabel = (TextView) findViewById(R.id.footer_message);
        this.mFooterLabel.setText(getContext().getString(R.string.read_more).toUpperCase());
    }

    public void bindDescription(NavigationManager navigationManager, Document doc, boolean hasDetailsDataLoaded, boolean isOwned, Bundle savedState, PlayStoreUiElementNode parentNode, TextSectionStateListener sectionStateListener) {
        if (hasDetailsDataLoaded) {
            boolean z;
            CharSequence fullDesc = Html.fromHtml(doc.getRawDescription());
            String translated = doc.getRawTranslatedDescription();
            CharSequence translatedDesc = TextUtils.isEmpty(translated) ? null : Html.fromHtml(translated);
            CharSequence callout = doc.getPromotionalDescription();
            int calloutGravity = 1;
            int calloutMaxLines = Integer.MAX_VALUE;
            if (doc.getDocumentType() == 8 && doc.hasCreatorBadges()) {
                Badge creatorBadge = doc.getCreatorBadges()[0];
                this.mTopDeveloperLabel.setText(creatorBadge.title);
                this.mTopDeveloperLabel.setTextColor(this.mTopDeveloperColor);
                BadgeUtils.configureBadge(creatorBadge, FinskyApp.get().getBitmapLoader(), this.mTopDeveloperLabel);
                this.mTopDeveloperLabel.setVisibility(0);
            } else {
                this.mTopDeveloperLabel.setVisibility(8);
            }
            this.mExpandedData = new ExpandedData();
            updateExtras(doc);
            if (TextUtils.isEmpty(callout) && doc.getDocumentType() != 1) {
                callout = fullDesc;
                calloutGravity = 3;
                calloutMaxLines = this.mMaxCollapsedLines;
                fullDesc = null;
            }
            CharSequence whatsNew = doc.hasWhatsNew() ? doc.getWhatsNew() : null;
            if (isOwned) {
                z = false;
            } else {
                z = true;
            }
            bind(navigationManager, doc, callout, calloutGravity, calloutMaxLines, null, fullDesc, translatedDesc, whatsNew, z, this.mMaxCollapsedLines, sectionStateListener);
            return;
        }
        setVisibility(8);
    }

    public void bindEditorialDescription(NavigationManager navigationManager, Document doc, boolean hasDetailsDataLoaded, Bundle savedState, PlayStoreUiElementNode parentNode, int backgroundFillColor, TextSectionStateListener sectionStateListener) {
        if (hasDetailsDataLoaded) {
            CharSequence fullDesc = Html.fromHtml(doc.getRawDescription());
            this.mExpandedData = new ExpandedData();
            bind(navigationManager, doc, null, 3, Integer.MAX_VALUE, null, fullDesc, null, null, true, this.mMaxCollapsedLines, sectionStateListener);
            int textColor = getResources().getColor(UiUtils.isColorBright(backgroundFillColor) ? R.color.play_fg_primary : R.color.white);
            setBackgroundColor(backgroundFillColor);
            this.mBodyContainerView.setEditorialStyle(backgroundFillColor, textColor);
            this.mFooterLabel.setTextColor(textColor);
            return;
        }
        setVisibility(8);
    }

    public void updateExtras(Document doc) {
        this.mExpandedData.extraPrimaryList = Lists.newArrayList();
        this.mExpandedData.extraSecondaryList = Lists.newArrayList();
        Context context = getContext();
        fillContentRatingDetails(doc, context, this.mExpandedData);
        fillExtraCreditsList(doc, context, this.mExpandedData);
        fillExtraPrimaryList(doc, this.mExpandedData);
        fillExtraSecondaryList(doc, context, this.mExpandedData);
        fillExtraAttributionsString(doc, this.mExpandedData);
        addProductDetails(doc, this.mExpandedData);
    }

    public static void fillExtraCreditsList(Document doc, Context context, ExpandedData expandedData) {
        VideoCredit[] videoCredits = doc.getVideoCredits();
        if (videoCredits != null && videoCredits.length != 0) {
            expandedData.extraCreditsHeader = context.getString(R.string.details_cast_crew);
            expandedData.extraCreditsList = Lists.newArrayList();
            for (VideoCredit videoCredit : videoCredits) {
                expandedData.extraCreditsList.add(new DetailsExtraCredits(videoCredit.credit, TextUtils.join(", ", videoCredit.name)));
            }
        }
    }

    public static void fillContentRatingDetails(Document doc, Context context, ExpandedData expandedData) {
        if (doc.getDocumentType() == 1) {
            Badge contentRatingBadge = doc.getBadgeForContentRating();
            if (contentRatingBadge == null) {
                expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.app_content_rating), ContentFilterActivity.getLabel(context, doc.getNormalizedContentRating())));
            } else {
                expandedData.extraPrimaryList.add(new DetailsExtraPrimary(contentRatingBadge.title, contentRatingBadge.description, null, contentRatingBadge.image[0], true));
            }
        }
    }

    public static void addProductDetails(Document doc, ExpandedData expandedData) {
        if (doc.hasProductDetails()) {
            for (ProductDetailsSection section : doc.getProductDetails().section) {
                for (ProductDetailsDescription description : section.description) {
                    if (description.image != null) {
                        expandedData.extraPrimaryList.add(new DetailsExtraPrimary(section.title, description.description, null, description.image, false));
                    } else {
                        expandedData.extraSecondaryList.add(new DetailsExtraSecondary(section.title, description.description));
                    }
                }
            }
        }
    }

    public static void fillExtraPrimaryList(Document doc, ExpandedData expandedData) {
        if (doc.hasCreatorBadges()) {
            for (Badge creatorBadge : doc.getCreatorBadges()) {
                expandedData.extraPrimaryList.add(new DetailsExtraPrimary(creatorBadge.title, creatorBadge.description, creatorBadge.browseUrl, BadgeUtils.getImage(creatorBadge, 6), true));
            }
        }
        if (doc.hasItemBadges()) {
            for (Badge itemBadge : doc.getItemBadges()) {
                expandedData.extraPrimaryList.add(new DetailsExtraPrimary(itemBadge.title, itemBadge.description, itemBadge.browseUrl, BadgeUtils.getImage(itemBadge, 6), true));
            }
        }
        if (doc.hasBadgeContainer()) {
            for (Badge badge : doc.getBadgeContainer().badge) {
                expandedData.extraPrimaryList.add(new DetailsExtraPrimary(badge.title, badge.description, badge.browseUrl, BadgeUtils.getImage(badge, 6), false));
            }
        }
    }

    public static void fillExtraSecondaryList(Document doc, Context context, ExpandedData expandedData) {
        int docType = doc.getDocumentType();
        switch (docType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                AppDetails appDetails = doc.getAppDetails();
                if (!TextUtils.isEmpty(appDetails.versionString)) {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.app_version), appDetails.versionString));
                }
                if (!TextUtils.isEmpty(appDetails.uploadDate)) {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.app_update_date), appDetails.uploadDate));
                }
                if (!TextUtils.isEmpty(appDetails.numDownloads)) {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.app_downloads), appDetails.numDownloads));
                }
                if (appDetails.hasInstallationSize) {
                    if (appDetails.installationSize > 0) {
                        expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.app_size), Formatter.formatFileSize(context, appDetails.installationSize)));
                        return;
                    }
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                AlbumDetails albumDetailWrapper = doc.getAlbumDetails();
                if (albumDetailWrapper != null) {
                    MusicDetails albumDetails = albumDetailWrapper.details;
                    if (!TextUtils.isEmpty(albumDetails.originalReleaseDate)) {
                        try {
                            expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.album_release_date), DateUtils.formatIso8601Date(albumDetails.originalReleaseDate)));
                        } catch (ParseException e) {
                            FinskyLog.e(e, "Cannot parse ISO 8601 date", new Object[0]);
                        }
                    }
                    if (!TextUtils.isEmpty(albumDetails.label)) {
                        String copyrightText;
                        if (TextUtils.isEmpty(albumDetails.releaseDate) || albumDetails.releaseDate.length() < 4) {
                            Object[] objArr = new Object[1];
                            objArr[0] = albumDetails.label;
                            copyrightText = context.getString(R.string.music_copyright, objArr);
                        } else {
                            String[] strArr = new Object[2];
                            strArr[0] = albumDetails.releaseDate.substring(0, 4);
                            strArr[1] = albumDetails.label;
                            copyrightText = context.getString(R.string.music_copyright_with_year, strArr);
                        }
                        expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.album_copyright), copyrightText));
                    }
                    if (albumDetails.genre.length > 0) {
                        expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.album_genre), TextUtils.join(",", albumDetails.genre)));
                        return;
                    }
                    return;
                }
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                VideoDetails videoDetails = doc.getVideoDetails();
                if (TextUtils.isEmpty(videoDetails.contentRating)) {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.movie_rating), context.getString(R.string.no_movie_rating)));
                } else {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.movie_rating), videoDetails.contentRating));
                }
                if (!TextUtils.isEmpty(videoDetails.releaseDate)) {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.movie_release_date), videoDetails.releaseDate));
                }
                if (!TextUtils.isEmpty(videoDetails.duration)) {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.movie_duration), videoDetails.duration));
                }
                if (videoDetails.audioLanguage.length > 0) {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.movie_audio_languages), TextUtils.join(",", videoDetails.audioLanguage)));
                }
                if (videoDetails.captionLanguage.length > 0) {
                    expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.movie_subtitle_languages), TextUtils.join(",", videoDetails.captionLanguage)));
                    return;
                }
                return;
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                MagazineDetails magazineDetails = doc.getMagazineDetails();
                if (magazineDetails != null) {
                    if (!TextUtils.isEmpty(magazineDetails.deliveryFrequencyDescription)) {
                        expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.magazine_delivery_frequency), magazineDetails.deliveryFrequencyDescription));
                    }
                    if (!TextUtils.isEmpty(magazineDetails.psvDescription)) {
                        expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.magazine_print_subscription_verification), magazineDetails.psvDescription));
                    }
                }
                Document currentIssue = doc;
                if (docType == 16 || docType == 24) {
                    currentIssue = DocUtils.getMagazineCurrentIssueDocument(doc);
                }
                if (currentIssue != null) {
                    MagazineDetails issueDetails = currentIssue.getMagazineDetails();
                    if (issueDetails != null && !TextUtils.isEmpty(issueDetails.deviceAvailabilityDescriptionHtml)) {
                        expandedData.extraSecondaryList.add(new DetailsExtraSecondary(context.getString(R.string.magazine_device_availability), issueDetails.deviceAvailabilityDescriptionHtml));
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void fillExtraAttributionsString(Document doc, ExpandedData expandedData) {
        expandedData.extraAttributions = doc.getAttributions();
    }

    public void bindAboutAuthor(NavigationManager navigationManager, Document doc, boolean hasDetailsDataLoaded, Bundle savedState, PlayStoreUiElementNode parentNode, TextSectionStateListener sectionStateListener) {
        if (hasDetailsDataLoaded && doc.getDocumentType() == 5 && doc.getBookDetails() != null && doc.getBookDetails().hasAboutTheAuthor) {
            CharSequence aboutAuthorText = FastHtmlParser.fromHtml(doc.getBookDetails().aboutTheAuthor);
            String header = getResources().getString(R.string.details_about_author).toUpperCase();
            this.mExpandedData = new ExpandedData();
            bind(navigationManager, doc, null, 3, Integer.MAX_VALUE, header, aboutAuthorText, null, null, true, this.mMaxCollapsedLines, sectionStateListener);
            return;
        }
        setVisibility(8);
    }

    private void bind(NavigationManager navigationManager, Document doc, CharSequence callout, int calloutGravity, int calloutMaxLines, String bodyHeader, CharSequence body, CharSequence translatedBody, CharSequence whatsNew, boolean hideWhatsNewInCollapsed, int collapsedMaxLines, TextSectionStateListener sectionStateListener) {
        this.mNavigationManager = navigationManager;
        this.mSectionStateListener = sectionStateListener;
        Context context = getContext();
        Resources res = context.getResources();
        syncCollapsedExtraIcons();
        if (this.mFooterLabel != null) {
            this.mFooterLabel.setTextColor(PlayCorpusUtils.getPrimaryTextColor(context, doc.getBackend()));
        }
        selfishifyUrlSpans(callout);
        selfishifyUrlSpans(body);
        selfishifyUrlSpans(translatedBody);
        selfishifyUrlSpans(whatsNew);
        boolean isApp = doc.getDocumentType() == 1;
        boolean hasCallout = !TextUtils.isEmpty(callout);
        this.mExpandedData.backend = doc.getBackend();
        this.mExpandedData.callout = callout;
        this.mExpandedData.calloutGravity = calloutGravity;
        this.mExpandedData.bodyHeader = bodyHeader;
        this.mExpandedData.body = body;
        this.mExpandedData.translatedBody = translatedBody;
        this.mExpandedData.whatsNewHeader = res.getString(R.string.details_whats_new).toUpperCase();
        this.mExpandedData.whatsNew = whatsNew;
        if (hasCallout) {
            this.mCalloutView.setVisibility(0);
            this.mCalloutView.setText(callout);
            this.mCalloutView.setMaxLines(calloutMaxLines);
            this.mCalloutView.setGravity(isApp ? 1 : 3);
        } else {
            this.mCalloutView.setVisibility(8);
        }
        this.mSpacerView.setVisibility(8);
        if (!hideWhatsNewInCollapsed && !TextUtils.isEmpty(whatsNew)) {
            this.mBodyContainerView.bind(this.mExpandedData.whatsNewHeader, whatsNew, collapsedMaxLines);
            this.mBodyContainerView.setCorpusStyle(doc.getBackend(), this.mWhatsNewVerticalMargin, this.mWhatsNewVerticalMargin);
            this.mExpandedData.promoteWhatsNew = true;
        } else if (isApp && hasCallout) {
            this.mBodyContainerView.setVisibility(8);
        } else {
            this.mBodyContainerView.bind(bodyHeader, body, collapsedMaxLines);
            this.mBodyContainerView.removeCorpusStyle();
            if (!hasCallout && TextUtils.isEmpty(bodyHeader)) {
                this.mSpacerView.setVisibility(0);
            }
            this.mExpandedData.promoteWhatsNew = false;
        }
        configureContentClicks();
        setVisibility(0);
    }

    public void syncCollapsedExtraIcons() {
        List<Pair<Image, String>> extraIconPairs = Lists.newArrayList();
        if (this.mExpandedData.extraPrimaryList != null) {
            for (DetailsExtraPrimary primary : this.mExpandedData.extraPrimaryList) {
                if (!(primary.skipInCollapsedMode || primary.image == null)) {
                    extraIconPairs.add(new Pair(primary.image, primary.title));
                }
            }
        }
        this.mIconContainer.populate(extraIconPairs, FinskyApp.get().getBitmapLoader());
    }

    private void selfishifyUrlSpans(CharSequence string) {
        if (!TextUtils.isEmpty(string)) {
            UrlSpanUtils.selfishifyUrlSpans(string, new Listener() {
                public void onClick(View view, String url) {
                    DetailsTextSection.this.mUrlSpanClicked = true;
                    Context context = view.getContext();
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(url));
                    intent.setPackage(context.getPackageName());
                    if (context.getPackageManager().resolveActivity(intent, 65536) != null) {
                        DetailsTextSection.this.mNavigationManager.handleDeepLink(url, null);
                        return;
                    }
                    intent.setPackage(null);
                    context.startActivity(intent);
                }
            });
        }
    }

    private void configureContentClicks() {
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DetailsTextSection.this.handleClick();
            }
        });
        this.mBodyContainerView.setBodyClickListener(new OnClickListener() {
            public void onClick(View v) {
                DetailsTextSection.this.handleClick();
            }
        });
    }

    protected void handleClick() {
        if (this.mUrlSpanClicked) {
            this.mUrlSpanClicked = false;
        } else {
            expandContent();
        }
    }

    private void expandContent() {
        if (this.mSectionStateListener != null) {
            this.mSectionStateListener.onSectionExpanded(getId());
        }
    }

    public ExpandedData getExpandedData() {
        return this.mExpandedData;
    }
}
