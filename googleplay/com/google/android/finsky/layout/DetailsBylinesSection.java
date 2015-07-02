package com.google.android.finsky.layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppsPermissionsActivity;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.DocDetails.AlbumDetails;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.protos.DocDetails.ArtistDetails;
import com.google.android.finsky.protos.DocDetails.ArtistExternalLinks;
import com.google.android.finsky.protos.DocDetails.MusicDetails;
import com.google.android.finsky.protos.DocDetails.TalentDetails;
import com.google.android.finsky.protos.DocDetails.TalentExternalLinks;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.IntMath;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.utils.collections.Lists;
import java.util.List;

public class DetailsBylinesSection extends LinearLayout {
    private boolean mBinded;
    private DecoratedTextView mHeaderView;
    private LayoutInflater mInflater;
    private LinearLayout mListingLayout;
    private PlayStoreUiElementNode mParentNode;

    public static class DetailsBylineEntry {
        public int iconResourceId;
        public OnClickListener onClickListener;
        public CharSequence text;
        public int textResourceId;

        public DetailsBylineEntry(int textResourceId, int iconResourceId, OnClickListener onClickListener) {
            this.text = null;
            this.textResourceId = textResourceId;
            this.iconResourceId = iconResourceId;
            this.onClickListener = onClickListener;
        }

        public DetailsBylineEntry(CharSequence text) {
            this.text = text;
            this.textResourceId = -1;
            this.iconResourceId = -1;
            this.onClickListener = null;
        }
    }

    public DetailsBylinesSection(Context context) {
        this(context, null);
    }

    public DetailsBylinesSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mInflater = LayoutInflater.from(context);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mListingLayout = (LinearLayout) findViewById(R.id.listing_layout);
        this.mHeaderView = (DecoratedTextView) findViewById(R.id.section_header);
    }

    @Deprecated
    public void bind(Document doc, boolean hasDetailsLoaded, PlayStoreUiElementNode parentNode) {
        BitmapLoader bitmapLoader = FinskyApp.get().getBitmapLoader();
        Context context = getContext();
        bind(hasDetailsLoaded, bitmapLoader, getBylinesTitle(doc, context), getBylinesTitleBadge(doc), getBylineEntries(doc, context, parentNode), parentNode);
    }

    public void bind(boolean hasDetailsLoaded, BitmapLoader bitmapLoader, String title, Badge titleBadge, List<DetailsBylineEntry> bylineEntryList, PlayStoreUiElementNode parentNode) {
        if (hasDetailsLoaded) {
            setVisibility(0);
            this.mListingLayout.removeAllViews();
            this.mParentNode = parentNode;
            if (bylineEntryList.isEmpty()) {
                setVisibility(8);
                return;
            }
            int bylinesEntryCount = bylineEntryList.size();
            int itemsInRow = getResources().getInteger(R.integer.details_extra_primary_items_per_row);
            int rowCount = IntMath.ceil(bylinesEntryCount, itemsInRow);
            for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
                ViewGroup row = (ViewGroup) this.mInflater.inflate(R.layout.details_bylines_row, this.mListingLayout, false);
                for (int colIndex = 0; colIndex < itemsInRow; colIndex++) {
                    int entryIndex = (itemsInRow * rowIndex) + colIndex;
                    DetailsBylinesCell bylineCell = (DetailsBylinesCell) this.mInflater.inflate(R.layout.details_bylines_cell, row, false);
                    if (entryIndex >= bylinesEntryCount) {
                        bylineCell.setVisibility(4);
                    } else {
                        bylineCell.populate((DetailsBylineEntry) bylineEntryList.get(entryIndex));
                    }
                    row.addView(bylineCell);
                }
                this.mListingLayout.addView(row);
            }
            if (TextUtils.isEmpty(title)) {
                this.mHeaderView.setVisibility(8);
            } else {
                this.mHeaderView.setVisibility(0);
                this.mHeaderView.setText(title);
                if (titleBadge != null) {
                    BadgeUtils.configureBadge(titleBadge, bitmapLoader, this.mHeaderView);
                }
            }
            this.mBinded = true;
            return;
        }
        setVisibility(8);
    }

    public static String getBylinesTitle(Document doc, Context context) {
        int headerId = -1;
        switch (doc.getDocumentType()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                headerId = R.string.details_developer_links;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                headerId = R.string.details_artist_links;
                break;
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                headerId = R.string.details_artist_links;
                break;
        }
        if (headerId < 0) {
            return null;
        }
        return context.getString(headerId).toUpperCase();
    }

    public static Badge getBylinesTitleBadge(Document doc) {
        if (doc.getDocumentType() == 1 && doc.hasCreatorBadges()) {
            return doc.getFirstCreatorBadge();
        }
        return null;
    }

    public static List<DetailsBylineEntry> getBylineEntries(Document doc, Context context, PlayStoreUiElementNode parentNode) {
        int documentType = doc.getDocumentType();
        List<DetailsBylineEntry> bylineEntries = Lists.newArrayList();
        switch (documentType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                AppDetails appDetails = doc.getAppDetails();
                if (!TextUtils.isEmpty(appDetails.developerWebsite)) {
                    bylineEntries.add(getWebLinkEntry(context, R.string.link_website, appDetails.developerWebsite, R.drawable.ic_developer_website, 114, parentNode));
                }
                if (!TextUtils.isEmpty(appDetails.developerEmail)) {
                    bylineEntries.add(getEmailLinkEntry(context, doc.getTitle(), R.string.link_email, appDetails.developerEmail, R.drawable.ic_developer_email, parentNode));
                }
                String privacyPolicyUrl = doc.getPrivacyPolicyUrl();
                if (!TextUtils.isEmpty(privacyPolicyUrl)) {
                    bylineEntries.add(getWebLinkEntry(context, R.string.privacy_policy, privacyPolicyUrl, R.drawable.ic_developer_privacy, 116, parentNode));
                }
                final Document document = doc;
                final Context context2 = context;
                bylineEntries.add(new DetailsBylineEntry(R.string.permission_details, R.drawable.ic_developer_permission, new OnClickListener() {
                    public void onClick(View v) {
                        context2.startActivity(AppsPermissionsActivity.createIntent(FinskyApp.get().getCurrentAccountName(), document.getDocId(), document, true));
                    }
                }));
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                AlbumDetails albumDetailWrapper = doc.getAlbumDetails();
                if (albumDetailWrapper != null) {
                    MusicDetails albumDetails = albumDetailWrapper.details;
                    if (!TextUtils.isEmpty(albumDetails.label)) {
                        String copyrightText;
                        if (TextUtils.isEmpty(albumDetails.releaseDate) || albumDetails.releaseDate.length() < 4) {
                            copyrightText = context.getString(R.string.music_copyright, new Object[]{albumDetails.label});
                        } else {
                            copyrightText = context.getString(R.string.music_copyright_with_year, new Object[]{albumDetails.releaseDate.substring(0, 4), albumDetails.label});
                        }
                        bylineEntries.add(new DetailsBylineEntry(copyrightText));
                    }
                    if (albumDetails.genre.length > 0) {
                        bylineEntries.add(new DetailsBylineEntry(TextUtils.join(",", albumDetails.genre)));
                        break;
                    }
                }
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                ArtistDetails artistDetails = doc.getArtistDetails();
                if (!(artistDetails == null || artistDetails.externalLinks == null)) {
                    ArtistExternalLinks artistLinks = artistDetails.externalLinks;
                    if (artistLinks.websiteUrl.length > 0) {
                        for (String websiteUrl : artistLinks.websiteUrl) {
                            if (!TextUtils.isEmpty(websiteUrl)) {
                                bylineEntries.add(getWebLinkEntry(context, R.string.link_website, websiteUrl, R.drawable.ic_developer_website, 117, parentNode));
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(artistLinks.youtubeChannelUrl)) {
                        bylineEntries.add(getWebLinkEntry(context, R.string.link_youtube, artistLinks.youtubeChannelUrl, R.drawable.ic_developer_youtube, 118, parentNode));
                    }
                    if (!TextUtils.isEmpty(artistLinks.googlePlusProfileUrl)) {
                        bylineEntries.add(getWebLinkEntry(context, R.string.link_googleplus, artistLinks.googlePlusProfileUrl, R.drawable.ic_developer_googleplus, 119, parentNode));
                        break;
                    }
                }
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                AppDetails developerDetails = doc.getAppDetails();
                if (!TextUtils.isEmpty(developerDetails.developerWebsite)) {
                    bylineEntries.add(getWebLinkEntry(context, R.string.link_website, developerDetails.developerWebsite, R.drawable.ic_developer_website, 114, parentNode));
                }
                if (!TextUtils.isEmpty(developerDetails.developerEmail)) {
                    bylineEntries.add(getEmailLinkEntry(context, doc.getTitle(), R.string.link_email, developerDetails.developerEmail, R.drawable.ic_developer_email, parentNode));
                    break;
                }
                break;
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                TalentDetails talentDetails = doc.getTalentDetails();
                if (!(talentDetails == null || talentDetails.externalLinks == null)) {
                    TalentExternalLinks talentLinks = talentDetails.externalLinks;
                    if (talentLinks.websiteUrl.length > 0) {
                        for (Link websiteUrl2 : talentLinks.websiteUrl) {
                            if (!TextUtils.isEmpty(websiteUrl2.uri)) {
                                bylineEntries.add(getWebLinkEntry(context, R.string.link_website, websiteUrl2.uri, R.drawable.ic_developer_website, 117, parentNode));
                            }
                        }
                    }
                    if (!(talentLinks.googlePlusProfileUrl == null || TextUtils.isEmpty(talentLinks.googlePlusProfileUrl.uri))) {
                        bylineEntries.add(getWebLinkEntry(context, R.string.link_email, talentLinks.googlePlusProfileUrl.uri, R.drawable.ic_developer_googleplus, 119, parentNode));
                    }
                    if (!(talentLinks.youtubeChannelUrl == null || TextUtils.isEmpty(talentLinks.youtubeChannelUrl.uri))) {
                        bylineEntries.add(getWebLinkEntry(context, R.string.link_email, talentLinks.youtubeChannelUrl.uri, R.drawable.ic_developer_youtube, 118, parentNode));
                        break;
                    }
                }
        }
        return bylineEntries;
    }

    public boolean hasBinded() {
        return this.mBinded;
    }

    private static DetailsBylineEntry getWebLinkEntry(final Context context, int titleId, String link, int iconId, final int clickEventType, final PlayStoreUiElementNode parentNode) {
        final Intent clickIntent = IntentUtils.createViewIntentForUrl(Uri.parse(link));
        return new DetailsBylineEntry(titleId, iconId, new OnClickListener() {
            public void onClick(View v) {
                context.startActivity(clickIntent);
                FinskyApp.get().getEventLogger().logClickEvent(clickEventType, null, parentNode);
            }
        });
    }

    private static DetailsBylineEntry getEmailLinkEntry(final Context context, String docTitle, int titleId, String link, int iconId, final PlayStoreUiElementNode parentNode) {
        final Intent clickIntent = IntentUtils.createSendIntentForUrl(Uri.fromParts("mailto", link, null));
        clickIntent.putExtra("android.intent.extra.SUBJECT", docTitle);
        return new DetailsBylineEntry(titleId, iconId, new OnClickListener() {
            public void onClick(View v) {
                context.startActivity(clickIntent);
                FinskyApp.get().getEventLogger().logClickEvent(115, null, parentNode);
            }
        });
    }
}
