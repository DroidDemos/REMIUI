package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.lightpurchase.AgeVerificationActivity;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.previews.SongSnippetStatusListener;
import com.google.android.finsky.previews.StatusListener;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.protos.DocDetails.MusicDetails;
import com.google.android.finsky.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import com.google.android.play.layout.PlayActionButton;

public class SongSnippet extends RelativeLayout implements PlayStoreUiElementNode {
    private ImageView mAddedDrawable;
    private TextView mAddedState;
    private Document mAlbumDocument;
    private BitmapLoader mBitmapLoader;
    private PlayActionButton mBuyButton;
    private final PreviewController mConnection;
    private boolean mInitialized;
    private boolean mIsNewPurchase;
    private boolean mIsPlayable;
    private MusicDetails mMusicDetails;
    private NavigationManager mNavigationManager;
    private PlayStoreUiElementNode mParentNode;
    private boolean mShouldShowArtistName;
    private ImageView mSongBadge;
    private SongDetails mSongDetails;
    private Document mSongDocument;
    private TextView mSongDuration;
    private SongIndex mSongIndex;
    private DecoratedTextView mSongSubTitle;
    private DecoratedTextView mSongTitle;
    private final StatusListener mStatusListener;
    private int mTrackNumber;
    private PlayStoreUiElement mUiElementProto;

    public SongSnippet(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(502);
        this.mStatusListener = new SongSnippetStatusListener() {
            protected void update(int state, boolean highlight) {
                SongSnippet.this.mInitialized = true;
                if (isSameAsSnippetSong()) {
                    SongSnippet.this.mSongIndex.setState(state);
                    SongSnippet.this.setHighlighted(highlight);
                    return;
                }
                SongSnippet.this.resetUI();
            }

            private boolean isSameAsSnippetSong() {
                if (SongSnippet.this.mSongDetails == null) {
                    return false;
                }
                SongDetails trackToCheck = SongSnippet.this.mConnection.getCurrentTrack();
                String previewUrl = SongSnippet.this.mSongDetails.previewUrl;
                if (trackToCheck == null || previewUrl == null || !previewUrl.equals(trackToCheck.previewUrl)) {
                    return false;
                }
                return true;
            }
        };
        this.mConnection = new PreviewController(this.mStatusListener);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mMusicDetails == null) {
            setVisibility(8);
            return;
        }
        updateContentView();
        if (this.mIsPlayable) {
            final boolean isMature = this.mSongDocument.isMature();
            final String accountName = FinskyApp.get().getCurrentAccount().name;
            setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (isMature && FinskyApp.get().getClientMutationCache(accountName).isAgeVerificationRequired()) {
                        SongSnippet.this.getContext().startActivity(AgeVerificationActivity.createIntent(accountName, 2, null));
                        return;
                    }
                    SongSnippet.this.mConnection.togglePlayback(SongSnippet.this.mSongDetails);
                }
            });
        } else {
            setOnClickListener(null);
        }
        if (((Boolean) G.prePurchaseSharingEnabled.get()).booleanValue()) {
            setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View v) {
                    SongSnippet.this.getContext().startActivity(Intent.createChooser(IntentUtils.buildShareIntent(SongSnippet.this.getContext(), SongSnippet.this.mSongDocument), SongSnippet.this.getContext().getString(R.string.share_dialog_title, new Object[]{SongSnippet.this.mSongDocument.getTitle()})));
                    FinskyApp.get().getEventLogger().logClickEvent(202, null, SongSnippet.this);
                    return true;
                }
            });
        }
    }

    protected void onDetachedFromWindow() {
        this.mConnection.unbind();
        super.onDetachedFromWindow();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSongIndex = (SongIndex) findViewById(R.id.song_index);
        this.mBuyButton = (PlayActionButton) findViewById(R.id.buy_button);
        this.mSongBadge = (ImageView) findViewById(R.id.badge);
        this.mSongDuration = (TextView) findViewById(R.id.song_duration);
        this.mSongTitle = (DecoratedTextView) findViewById(R.id.song_title);
        this.mSongSubTitle = (DecoratedTextView) findViewById(R.id.song_subtitle);
        this.mAddedState = (TextView) findViewById(R.id.added_state);
        this.mAddedDrawable = (ImageView) findViewById(R.id.added_drawable);
    }

    public void setSongDetails(BitmapLoader bitmapLoader, Document albumDocument, Document songDocument, int trackNumber, boolean shouldShowArtistName, NavigationManager navigationManager, boolean isNewPurchase, PlayStoreUiElementNode parentNode) {
        this.mBitmapLoader = bitmapLoader;
        this.mShouldShowArtistName = shouldShowArtistName;
        this.mAlbumDocument = albumDocument;
        this.mSongDocument = songDocument;
        this.mNavigationManager = navigationManager;
        this.mTrackNumber = trackNumber;
        this.mIsNewPurchase = isNewPurchase;
        this.mParentNode = parentNode;
        this.mSongDetails = this.mSongDocument.getSongDetails();
        this.mMusicDetails = this.mSongDetails.details;
        boolean z = (this.mMusicDetails == null || this.mMusicDetails.durationSec <= 0 || TextUtils.isEmpty(this.mSongDetails.previewUrl)) ? false : true;
        this.mIsPlayable = z;
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mSongDocument.getServerLogsCookie());
        this.mParentNode.childImpression(this);
    }

    public void updateContentView() {
        this.mSongIndex.setTrackNumber(this.mTrackNumber);
        if (this.mIsPlayable) {
            this.mSongDuration.setText(DateUtils.formatElapsedTime((long) this.mMusicDetails.durationSec));
            this.mSongDuration.setContentDescription(getResources().getString(R.string.content_description_track_duration, new Object[]{formattedDuration}));
            this.mSongDuration.setVisibility(0);
        } else {
            this.mSongDuration.setVisibility(8);
        }
        this.mSongTitle.setText(this.mSongDocument.getTitle());
        if (this.mSongDetails.badge != null) {
            this.mSongBadge.setVisibility(0);
            Badge songBadge = this.mSongDetails.badge;
            int badgeSize = this.mSongBadge.getMeasuredHeight();
            Image badgeImage = BadgeUtils.getImage(songBadge, 6);
            if (badgeImage != null) {
                int requestSize;
                if (badgeImage.supportsFifeUrlOptions) {
                    requestSize = badgeSize;
                } else {
                    requestSize = 0;
                }
                BitmapContainer holder = this.mBitmapLoader.get(badgeImage.imageUrl, requestSize, requestSize, new BitmapLoadedHandler() {
                    public void onResponse(BitmapContainer result) {
                        if (result != null) {
                            SongSnippet.this.mSongBadge.setImageBitmap(result.getBitmap());
                        }
                    }
                });
                if (holder.getBitmap() != null) {
                    this.mSongBadge.setImageBitmap(holder.getBitmap());
                }
            }
        } else {
            this.mSongBadge.setVisibility(4);
        }
        if (this.mShouldShowArtistName) {
            this.mSongSubTitle.setText(this.mSongDocument.getCreator());
            BadgeUtils.configureCreatorBadge(this.mSongDocument, this.mBitmapLoader, this.mSongSubTitle);
        } else {
            this.mSongSubTitle.setVisibility(8);
        }
        updateBuyButtonState();
        this.mSongIndex.setClickable(false);
        this.mConnection.getStatusUpdate(this.mStatusListener);
    }

    public Document getDocument() {
        return this.mSongDocument;
    }

    public boolean isPlayable() {
        return this.mIsPlayable;
    }

    public void initialize() {
        if (!this.mInitialized) {
            setState(1);
            this.mInitialized = true;
        }
    }

    public void setState(int state) {
        int i = 0;
        switch (state) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mSongIndex.setState(5);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                setHighlighted(true);
                SongIndex songIndex = this.mSongIndex;
                if (this.mIsPlayable) {
                    i = 3;
                }
                songIndex.setState(i);
                return;
            default:
                this.mSongIndex.setState(0);
                return;
        }
    }

    private void resetUI() {
        setHighlighted(false);
        this.mSongIndex.setState(0);
    }

    private void setHighlighted(boolean isHighlighted) {
        Resources r = getResources();
        if (isHighlighted) {
            setBackgroundResource(R.drawable.active_song_with_highlight);
            int colorWhite = r.getColor(R.color.play_white);
            this.mSongTitle.setTextColor(colorWhite);
            this.mSongSubTitle.setTextColor(colorWhite);
            this.mSongDuration.setTextColor(colorWhite);
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        setBackgroundResource(R.drawable.play_highlight_overlay_light);
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        this.mSongTitle.setTextColor(r.getColor(R.color.play_fg_secondary));
        this.mSongSubTitle.setTextColor(r.getColor(R.color.play_secondary_text));
        this.mSongDuration.setTextColor(r.getColor(R.color.play_secondary_text));
    }

    private void updateBuyButtonState() {
        this.mBuyButton.setVisibility(0);
        Library library = FinskyApp.get().getLibraries().getAccountLibrary(FinskyApp.get().getCurrentAccount());
        Account currentAccount = FinskyApp.get().getCurrentAccount();
        final Account owner = LibraryUtils.getOwnerWithCurrentAccount(this.mSongDocument, FinskyApp.get().getLibraries(), currentAccount);
        if (owner != null) {
            setBuyButtonStyle();
            this.mBuyButton.configure(2, (int) R.string.listen, new OnClickListener() {
                public void onClick(View v) {
                    FinskyApp.get().getEventLogger().logClickEvent(218, null, SongSnippet.this);
                    SongSnippet.this.mNavigationManager.openItem(owner, SongSnippet.this.mSongDocument);
                }
            });
        } else if (this.mSongDocument.getOffer(1) != null) {
            setBuyButtonStyle();
            this.mBuyButton.configure(2, this.mSongDocument.getFormattedPrice(1), this.mNavigationManager.getBuyImmediateClickListener(currentAccount, this.mSongDocument, 1, null, null, 200, this));
        } else if (!LibraryUtils.isAvailable(this.mSongDocument, FinskyApp.get().getToc(), library)) {
            switch (this.mSongDocument.getAvailabilityRestriction()) {
                case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    clearBuyButtonStyle(R.string.album_only_purchase);
                    break;
                default:
                    this.mBuyButton.setVisibility(4);
                    break;
            }
        } else if (this.mAlbumDocument == null || this.mAlbumDocument.getOffer(1) == null) {
            this.mBuyButton.setVisibility(4);
        } else {
            clearBuyButtonStyle(R.string.album_only_purchase);
        }
        updateAddedState();
    }

    private void setBuyButtonStyle() {
        this.mBuyButton.setDrawAsLabel(false);
        this.mBuyButton.setActionStyle(2);
        this.mBuyButton.setEnabled(true);
    }

    private void clearBuyButtonStyle(int buttonTextResourceId) {
        this.mBuyButton.setDrawAsLabel(true);
        this.mBuyButton.setActionStyle(2);
        this.mBuyButton.setEnabled(false);
        this.mBuyButton.configure(2, buttonTextResourceId, null);
    }

    private void updateAddedState() {
        if (this.mIsNewPurchase) {
            this.mSongDuration.setVisibility(8);
            this.mAddedState.setVisibility(0);
            this.mAddedDrawable.setVisibility(0);
            return;
        }
        this.mSongDuration.setVisibility(0);
        this.mAddedState.setVisibility(8);
        this.mAddedDrawable.setVisibility(8);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new IllegalStateException("unwanted children");
    }
}
