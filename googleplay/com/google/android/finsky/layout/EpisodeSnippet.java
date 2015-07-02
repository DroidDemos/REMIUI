package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.DocUtils.OfferFilter;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.ForegroundRelativeLayout;
import com.google.android.play.layout.PlayActionButton;

public class EpisodeSnippet extends ForegroundRelativeLayout implements PlayStoreUiElementNode {
    private View mAddedDrawable;
    private TextView mAddedState;
    private final int mBaseRowHeight;
    private BitmapLoader mBitmapLoader;
    private PlayActionButton mBuyButton;
    private OnCollapsedStateChanged mCollapsedStateChangedListener;
    private Document mEpisode;
    private TextView mEpisodeNumber;
    private TextView mEpisodeTitle;
    private View mExpandedContent;
    private TextView mExpandedDescription;
    private HeroGraphicView mExpandedEpisodeScreencap;
    private ViewStub mExpandedViewStub;
    private final Handler mHandler;
    private boolean mIsNewPurchase;
    private NavigationManager mNavigationManager;
    private PlayStoreUiElementNode mParentNode;
    private final Runnable mScrollerRunnable;
    private Document mSeasonDocument;
    private final int mStatusBarHeight;
    private PlayStoreUiElement mUiElementProto;

    public interface OnCollapsedStateChanged {
        void onCollapsedStateChanged(EpisodeSnippet episodeSnippet, boolean z);
    }

    public EpisodeSnippet(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(503);
        this.mHandler = new Handler(Looper.getMainLooper());
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            this.mStatusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        } else {
            this.mStatusBarHeight = (int) ((25.0f * context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        this.mBaseRowHeight = context.getResources().getDimensionPixelSize(R.dimen.base_row_height);
        this.mScrollerRunnable = new Runnable() {
            public void run() {
                int[] locations = new int[2];
                EpisodeSnippet.this.getLocationInWindow(locations);
                ViewParent parent = EpisodeSnippet.this.getParent();
                while (!(parent instanceof ObservableScrollView)) {
                    if (parent instanceof RecyclerView) {
                        ((RecyclerView) parent).smoothScrollBy(0, locations[1] - EpisodeSnippet.this.mStatusBarHeight);
                        return;
                    }
                    parent = parent.getParent();
                    if (parent == null) {
                        FinskyLog.wtf("Found no suitable parent.", new Object[0]);
                    }
                }
                ((ObservableScrollView) parent).smoothScrollBy(0, (locations[1] - EpisodeSnippet.this.mBaseRowHeight) - EpisodeSnippet.this.mStatusBarHeight);
            }
        };
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mEpisode.getTvEpisodeDetails() == null) {
            setVisibility(8);
            return;
        }
        updateContentView();
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EpisodeSnippet.this.toggleExpandedVisibility();
                EpisodeSnippet.this.logCurrentState();
            }
        });
    }

    public void updateContentView() {
        this.mEpisodeNumber.setText(Integer.toString(this.mEpisode.getTvEpisodeDetails().episodeIndex));
        this.mEpisodeNumber.setContentDescription(getResources().getString(R.string.content_description_episode_number, new Object[]{Integer.valueOf(episodeDetails.episodeIndex)}));
        this.mEpisodeTitle.setText(this.mEpisode.getTitle());
        this.mEpisodeTitle.setMaxLines(2);
        this.mEpisodeTitle.setEllipsize(TruncateAt.END);
        updateBuyButtonState(getResources(), this.mBuyButton, this.mAddedState, this.mAddedDrawable, this.mSeasonDocument, this.mEpisode, this.mIsNewPurchase, this.mNavigationManager, this);
    }

    private void logCurrentState() {
        FinskyApp.get().getEventLogger().logClickEvent(isExpanded() ? 271 : 272, null, this.mParentNode);
    }

    public Document getEpisode() {
        return this.mEpisode;
    }

    public void collapseWithoutNotifyingListeners() {
        if (this.mExpandedContent != null) {
            this.mExpandedContent.setVisibility(8);
        }
    }

    public void expand() {
        setExpandedContentVisibility(0);
    }

    public boolean isExpanded() {
        return this.mExpandedContent != null && this.mExpandedContent.getVisibility() == 0;
    }

    private void toggleExpandedVisibility() {
        setExpandedContentVisibility(isExpanded() ? 8 : 0);
    }

    private void inflateViewStubIfNecessary() {
        if (this.mExpandedContent == null) {
            this.mExpandedContent = this.mExpandedViewStub.inflate();
            this.mExpandedDescription = (TextView) findViewById(R.id.episode_description);
            this.mExpandedEpisodeScreencap = (HeroGraphicView) findViewById(R.id.episode_screencap);
        }
    }

    private void setExpandedContentVisibility(int visibility) {
        boolean z = true;
        inflateViewStubIfNecessary();
        this.mExpandedContent.setVisibility(visibility);
        if (visibility == 8) {
            this.mEpisodeTitle.setMaxLines(2);
            this.mEpisodeTitle.setEllipsize(TruncateAt.END);
        } else {
            this.mEpisodeTitle.setMaxLines(1000);
            this.mEpisodeTitle.setEllipsize(null);
        }
        if (visibility == 0) {
            this.mExpandedEpisodeScreencap.bindTvEpisode(this.mBitmapLoader, this.mEpisode);
            if (!TextUtils.isEmpty(this.mEpisode.getDescription())) {
                String episodeDescription = this.mEpisode.getDescription().toString();
                if (this.mEpisode.getTvEpisodeDetails() != null) {
                    episodeDescription = (episodeDescription + "\n\n") + getResources().getString(R.string.original_air_date, new Object[]{this.mEpisode.getTvEpisodeDetails().releaseDate});
                }
                this.mExpandedDescription.setText(episodeDescription);
            }
        }
        if (this.mCollapsedStateChangedListener != null) {
            OnCollapsedStateChanged onCollapsedStateChanged = this.mCollapsedStateChangedListener;
            if (visibility != 8) {
                z = false;
            }
            onCollapsedStateChanged.onCollapsedStateChanged(this, z);
        }
        this.mHandler.post(this.mScrollerRunnable);
    }

    protected void onDetachedFromWindow() {
        this.mHandler.removeCallbacks(this.mScrollerRunnable);
        super.onDetachedFromWindow();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mExpandedViewStub = (ViewStub) findViewById(R.id.expanded_content_stub);
        this.mEpisodeNumber = (TextView) findViewById(R.id.episode_number);
        this.mBuyButton = (PlayActionButton) findViewById(R.id.buy_button);
        this.mEpisodeTitle = (TextView) findViewById(R.id.episode_title);
        this.mAddedState = (TextView) findViewById(R.id.added_state);
        this.mAddedDrawable = findViewById(R.id.added_drawable);
    }

    public void setEpisodeDetails(Document season, Document episode, BitmapLoader bitmapLoader, NavigationManager navigationManager, boolean isNewPurchase, OnCollapsedStateChanged listener, PlayStoreUiElementNode parentNode) {
        this.mSeasonDocument = season;
        this.mEpisode = episode;
        this.mNavigationManager = navigationManager;
        this.mBitmapLoader = bitmapLoader;
        this.mIsNewPurchase = isNewPurchase;
        this.mCollapsedStateChangedListener = listener;
        this.mParentNode = parentNode;
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, episode.getServerLogsCookie());
        this.mParentNode.childImpression(this);
    }

    public static void updateBuyButtonState(Resources resources, PlayActionButton buyButton, TextView addedButton, View addedDrawable, Document parentDoc, Document doc, boolean isNewPurchase, NavigationManager navigationManager, PlayStoreUiElementNode parentNode) {
        buyButton.setVisibility(0);
        Account currentAccount = FinskyApp.get().getCurrentAccount();
        Account owner = LibraryUtils.getOwnerWithCurrentAccount(doc, FinskyApp.get().getLibraries(), currentAccount);
        Offer[] offers = doc.getAvailableOffers();
        int numValidOffers = DocUtils.getNumberOfValidOffers(offers);
        if (owner != null) {
            int buyButtonTextId;
            setBuyButtonStyle(buyButton);
            if (doc.getDocumentType() == 19) {
                buyButtonTextId = R.string.purchased_list_state;
                buyButton.setEnabled(false);
            } else {
                buyButtonTextId = R.string.play;
                buyButton.setEnabled(true);
            }
            final PlayStoreUiElementNode playStoreUiElementNode = parentNode;
            final NavigationManager navigationManager2 = navigationManager;
            final Account account = owner;
            final Document document = doc;
            buyButton.configure(4, buyButtonTextId, new OnClickListener() {
                public void onClick(View v) {
                    FinskyApp.get().getEventLogger().logClickEvent(218, null, playStoreUiElementNode);
                    navigationManager2.openItem(account, document);
                }
            });
        } else if (numValidOffers > 0) {
            Offer lowestPricedOffer = DocUtils.getLowestPricedOffer(offers, true);
            setBuyButtonStyle(buyButton);
            String buyButtonText = null;
            if (doc.getDocumentType() == 19) {
                boolean hasBuyButton = false;
                boolean hasRentButton = false;
                for (Offer offer : offers) {
                    int offerType = offer.offerType;
                    if (OfferFilter.RENTAL.matches(offerType)) {
                        hasRentButton = true;
                    } else if (OfferFilter.PURCHASE.matches(offerType)) {
                        hasBuyButton = true;
                    }
                }
                if (hasBuyButton && hasRentButton) {
                    buyButtonText = resources.getString(R.string.purchase_or_rent_resolution, new Object[]{lowestPricedOffer.formattedAmount});
                } else if (hasBuyButton) {
                    buyButtonText = numValidOffers == 1 ? resources.getString(R.string.buy, new Object[]{lowestPricedOffer.formattedAmount}) : resources.getString(R.string.purchase_resolution, new Object[]{lowestPricedOffer.formattedAmount});
                } else if (hasRentButton) {
                    buyButtonText = numValidOffers == 1 ? resources.getString(R.string.rent, new Object[]{lowestPricedOffer.formattedAmount}) : resources.getString(R.string.rent_resolution, new Object[]{lowestPricedOffer.formattedAmount});
                }
            }
            if (buyButtonText == null) {
                buyButtonText = lowestPricedOffer.hasFormattedAmount ? lowestPricedOffer.formattedAmount : null;
            }
            buyButton.configure(4, buyButtonText, navigationManager.getBuyImmediateClickListener(currentAccount, doc, numValidOffers == 1 ? lowestPricedOffer.offerType : 0, null, null, 200, parentNode));
        } else if (parentDoc == null || DocUtils.getNumberOfValidOffers(parentDoc.getAvailableOffers()) <= 0) {
            buyButton.setVisibility(4);
        } else {
            clearBuyButtonStyle(buyButton);
            buyButton.configure(4, (int) R.string.season_only_purchase, null);
        }
        if (addedButton != null) {
            int visibility = isNewPurchase ? 0 : 8;
            addedDrawable.setVisibility(visibility);
            addedButton.setVisibility(visibility);
        }
    }

    public static boolean hasSeasonOffer(Document season) {
        if (LibraryUtils.getOwnerWithCurrentAccount(season, FinskyApp.get().getLibraries(), FinskyApp.get().getCurrentAccount()) == null && DocUtils.getNumberOfValidOffers(season.getAvailableOffers()) <= 0) {
            return false;
        }
        return true;
    }

    private static void setBuyButtonStyle(PlayActionButton button) {
        button.setDrawAsLabel(false);
        button.setActionStyle(2);
        button.setEnabled(true);
    }

    private static void clearBuyButtonStyle(PlayActionButton button) {
        button.setDrawAsLabel(true);
        button.setActionStyle(2);
        button.setEnabled(false);
        button.configure(4, button.getText().toString(), null);
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
