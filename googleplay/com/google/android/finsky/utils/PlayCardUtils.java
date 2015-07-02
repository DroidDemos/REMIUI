package com.google.android.finsky.utils;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata;
import com.google.android.finsky.layout.play.PlayCardDismissListener;
import com.google.android.finsky.layout.play.PlayCardViewMediumPlus;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.OverflowLink;
import com.google.android.finsky.protos.DocumentV2.Reason;
import com.google.android.finsky.protos.DocumentV2.ReasonPlusOne;
import com.google.android.finsky.protos.DocumentV2.ReasonReview;
import com.google.android.finsky.protos.DocumentV2.ReasonUserAction;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.protos.DocumentV2.SuggestionReasons;
import com.google.android.finsky.protos.RateSuggestedContentResponse;
import com.google.android.finsky.utils.PurchaseButtonHelper.DocumentAction;
import com.google.android.finsky.utils.PurchaseButtonHelper.DocumentActions;
import com.google.android.finsky.utils.PurchaseButtonHelper.TextStyle;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.layout.PlayActionButton;
import com.google.android.play.layout.PlayCardLabelView;
import com.google.android.play.layout.PlayCardSnippet;
import com.google.android.play.layout.PlayCardThumbnail;
import com.google.android.play.layout.PlayCardViewBase;
import com.google.android.play.layout.PlayCardViewMini;
import com.google.android.play.layout.PlayCardViewSmall;
import com.google.android.play.layout.PlayCardWindowLifecycleTracker;
import com.google.android.play.layout.PlayCardWindowLifecycleTracker.CardLifecycleListener;
import com.google.android.play.layout.PlayPopupMenu;
import com.google.android.play.layout.PlayPopupMenu.OnActionSelectedListener;
import com.google.android.play.layout.PlayTextView;
import com.google.android.play.layout.StarRatingBar;
import com.google.android.play.utils.DocV2Utils;
import java.util.Set;

public class PlayCardUtils {
    private static final TextStyle sActionStyle;
    private static String sCachedExperimentAccountName;
    private static final DocumentActions sDocumentActions;
    private static boolean sHideSalesPricesExperimentEnabled;
    private static final TextStyle sListingStyle;
    private static StringBuilder sTransitionNameCardBuilder;
    private static StringBuilder sTransitionNameCoverBuilder;
    private static Set<PlayCardViewBase> sWindowAttachedCards;

    private static class CardDismissalAction implements OnActionSelectedListener {
        private final PlayCardViewBase mCard;
        private final PlayStoreUiElementNode mClickedNode;
        private final DfeApi mDfeApi;
        private final PlayCardDismissListener mDismissListener;
        private final Document mDoc;

        public CardDismissalAction(PlayCardViewBase card, Document doc, DfeApi dfeApi, PlayCardDismissListener dismissListener, PlayStoreUiElementNode clickedNode) {
            this.mCard = card;
            this.mDoc = doc;
            this.mDfeApi = dfeApi;
            this.mDismissListener = dismissListener;
            this.mClickedNode = clickedNode;
        }

        public void onActionSelected() {
            FinskyApp.get().getEventLogger().logClickEvent(212, null, this.mClickedNode);
            this.mDfeApi.rateSuggestedContent(this.mDoc.getNeutralDismissal().url, new Listener<RateSuggestedContentResponse>() {
                public void onResponse(RateSuggestedContentResponse response) {
                    CardDismissalAction.this.mDismissListener.onDismissDocument(CardDismissalAction.this.mDoc, CardDismissalAction.this.mCard);
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    FinskyLog.d("Volley error while dismissing %s: %s", CardDismissalAction.this.mDoc.getDocId(), error);
                }
            });
        }
    }

    private static class CardUiElementNode implements PlayStoreUiElementNode {
        private PlayStoreUiElementNode mParentNode;
        private PlayStoreUiElement mUiElementProto;

        public CardUiElementNode(PlayCardViewBase card, PlayStoreUiElementNode parentNode) {
            this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(PlayCardUtils.convertCardTypeToUiElementType(card.getCardType()));
            this.mParentNode = parentNode;
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

        public void setParentNode(PlayStoreUiElementNode parentNode) {
            this.mParentNode = parentNode;
        }

        public void onRecycle() {
            this.mParentNode = null;
        }
    }

    static {
        sDocumentActions = new DocumentActions();
        sListingStyle = new TextStyle();
        sActionStyle = new TextStyle();
        sWindowAttachedCards = Sets.newHashSet();
        if (NavigationManager.areTransitionsEnabled()) {
            sTransitionNameCardBuilder = new StringBuilder();
            sTransitionNameCardBuilder.append("transition_card_details:card:");
            sTransitionNameCoverBuilder = new StringBuilder();
            sTransitionNameCoverBuilder.append("transition_card_details:cover:");
        }
    }

    public static void initializeCardTrackers() {
        FinskyApp.get().getInstaller().addListener(new InstallerListener() {
            public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
                PlayCardUtils.updateTrackedCardLabels();
            }
        });
        FinskyApp.get().getLibraries().addListener(new Libraries.Listener() {
            public void onLibraryContentsChanged(AccountLibrary library) {
                PlayCardUtils.updateTrackedCardLabels();
            }

            public void onAllLibrariesLoaded() {
            }
        });
        PlayCardWindowLifecycleTracker.getInstance().registerListener(new CardLifecycleListener() {
            public void onCardAttachedToWindow(PlayCardViewBase card) {
                PlayCardUtils.sWindowAttachedCards.add(card);
            }

            public void onCardDetachedFromWindow(PlayCardViewBase card) {
                PlayCardUtils.sWindowAttachedCards.remove(card);
            }
        });
        PlayCardCustomizerRepository<PlayCardViewBase> customizerRepository = PlayCardCustomizerRepository.getInstance();
        customizerRepository.registerCardCustomizer(0, new PlayCardCustomizer<PlayCardViewMini>() {
            public void preBind(PlayCardViewMini card, Document doc) {
                super.preBind(card, doc);
                int newTitleMaxLines = 2;
                int docType = doc.getDocumentType();
                if (docType == 2 || docType == 4) {
                    FinskyApp finskyApp = FinskyApp.get();
                    Libraries libraries = finskyApp.getLibraries();
                    Account currentAccount = finskyApp.getCurrentAccount();
                    if (LibraryUtils.getOwnerWithCurrentAccount(doc, libraries, currentAccount) == null) {
                        Offer listingOffer = DocUtils.getListingOffer(doc, finskyApp.getToc(), libraries.getAccountLibrary(currentAccount));
                        if (listingOffer != null && listingOffer.hasFormattedFullAmount) {
                            newTitleMaxLines = 1;
                        }
                    }
                }
                card.setTitleMaxLines(newTitleMaxLines);
            }
        });
        customizerRepository.registerCardCustomizer(1, new PlayCardCustomizer<PlayCardViewSmall>() {
            public void preBind(PlayCardViewSmall card, Document doc) {
                super.preBind(card, doc);
                boolean shouldShowReasons = doc.hasReasons() || doc.hasOptimalDeviceClassWarning();
                card.setSnippetVisible(shouldShowReasons);
            }
        });
        customizerRepository.registerCardCustomizer(10, new PlayCardCustomizer<PlayCardViewMediumPlus>() {
            public void preBind(PlayCardViewMediumPlus card, Document doc) {
                boolean goToLargeMode;
                int i = 1;
                SuggestionReasons reasons = doc.getSuggestionReasons();
                if (reasons == null || reasons.reason.length != 1) {
                    goToLargeMode = false;
                } else {
                    goToLargeMode = true;
                }
                PlayCardSnippet snippet2 = card.getSnippet2();
                if (!goToLargeMode) {
                    i = 0;
                }
                snippet2.setSizeMode(i);
            }
        });
    }

    private static void updateTrackedCardLabels() {
        for (PlayCardViewBase card : sWindowAttachedCards) {
            updateCardLabel((Document) card.getData(), card);
        }
    }

    private static void bindReason(PlayCardSnippet cardSnippet, Reason reason, BitmapLoader bitmapLoader, NavigationManager navigationManager, int textOnlyReasonMarginLeft, int avatarReasonMarginLeft, PlayStoreUiElementNode cardNode) {
        if (reason != null) {
            cardSnippet.setVisibility(0);
            FifeImageView snippetAvatar = (FifeImageView) cardSnippet.getImageView();
            Resources res = cardSnippet.getResources();
            ReasonReview reasonReview = reason.reasonReview;
            if (reasonReview != null) {
                String reviewText;
                Review review = reasonReview.review;
                String author = review.author != null ? review.author.title : null;
                boolean hasAuthor = !TextUtils.isEmpty(author);
                int stars = review.starRating;
                boolean hasTitle = !TextUtils.isEmpty(review.title);
                boolean hasComment = !TextUtils.isEmpty(review.comment);
                if (hasAuthor) {
                    if (hasComment && hasTitle) {
                        if (review.comment.indexOf(review.title) != 0) {
                            r24 = new Object[4];
                            r24[2] = review.title;
                            r24[3] = review.comment;
                            reviewText = res.getQuantityString(R.plurals.review_author_stars_title_comment, stars, r24);
                        }
                    }
                    if (hasComment || hasTitle) {
                        String[] strArr = new Object[3];
                        strArr[0] = author;
                        strArr[1] = Integer.valueOf(stars);
                        strArr[2] = hasComment ? review.comment : review.title;
                        reviewText = res.getQuantityString(R.plurals.review_author_stars_title, stars, strArr);
                    } else {
                        reviewText = res.getQuantityString(R.plurals.review_author_stars, stars, new Object[]{author, Integer.valueOf(stars)});
                    }
                } else if (hasComment || hasTitle) {
                    Integer num;
                    Integer[] numArr = new Object[2];
                    numArr[0] = Integer.valueOf(stars);
                    if (hasComment) {
                        num = review.comment;
                    } else {
                        Object obj = review.title;
                    }
                    numArr[1] = num;
                    reviewText = res.getQuantityString(R.plurals.review_stars_title, stars, numArr);
                } else {
                    reviewText = res.getQuantityString(R.plurals.review_just_stars, stars, new Object[]{Integer.valueOf(stars)});
                }
                cardSnippet.setSnippetText(Html.fromHtml(reviewText), textOnlyReasonMarginLeft, avatarReasonMarginLeft);
                DocV2 user = review.author;
                Image reasonImage = user != null ? DocV2Utils.getFirstImageOfType(user, 4) : null;
                if (reasonImage != null) {
                    snippetAvatar.setImage(reasonImage.imageUrl, reasonImage.supportsFifeUrlOptions, bitmapLoader);
                    bindReasonUser(snippetAvatar, user, navigationManager, cardNode);
                    snippetAvatar.setVisibility(0);
                    return;
                }
                snippetAvatar.setVisibility(8);
            } else if (reason.reasonPlusOne != null) {
                ReasonPlusOne profiles = reason.reasonPlusOne;
                int count = profiles.person.length;
                DocV2 firstUser;
                Object[] objArr;
                if (count > 1) {
                    firstUser = profiles.person[0];
                    DocV2 secondUser = profiles.person[1];
                    objArr = new Object[2];
                    objArr[0] = firstUser.title;
                    objArr[1] = secondUser.title;
                    cardSnippet.setSnippetText(Html.fromHtml(res.getString(R.string.plus_one_multiple_friends_bold, objArr)), textOnlyReasonMarginLeft, avatarReasonMarginLeft);
                    image = DocV2Utils.getFirstImageOfType(firstUser, 4);
                    snippetAvatar.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
                    bindReasonUser(snippetAvatar, firstUser, navigationManager, cardNode);
                    snippetAvatar.setVisibility(0);
                } else if (count == 1) {
                    firstUser = profiles.person[0];
                    objArr = new Object[1];
                    objArr[0] = firstUser.title;
                    cardSnippet.setSnippetText(Html.fromHtml(res.getString(R.string.plus_one_single_friend_bold, objArr)), textOnlyReasonMarginLeft, avatarReasonMarginLeft);
                    image = DocV2Utils.getFirstImageOfType(firstUser, 4);
                    snippetAvatar.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
                    bindReasonUser(snippetAvatar, firstUser, navigationManager, cardNode);
                    snippetAvatar.setVisibility(0);
                } else {
                    FinskyLog.e("Server returned plus profile reason with no profiles", new Object[0]);
                }
            } else if (reason.reasonUserAction != null) {
                ReasonUserAction reasonUserAction = reason.reasonUserAction;
                cardSnippet.setSnippetText(Html.fromHtml(reasonUserAction.localizedDescriptionHtml), textOnlyReasonMarginLeft, avatarReasonMarginLeft);
                DocV2 person = reasonUserAction.person;
                image = DocV2Utils.getFirstImageOfType(person, 4);
                snippetAvatar.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
                snippetAvatar.setVisibility(0);
                bindReasonUser(snippetAvatar, person, navigationManager, cardNode);
            } else {
                cardSnippet.setSnippetText(Html.fromHtml(reason.descriptionHtml), textOnlyReasonMarginLeft, avatarReasonMarginLeft);
                snippetAvatar.setVisibility(8);
            }
        }
    }

    private static void bindReasonUser(FifeImageView reasonAvatarImage, final DocV2 reasonUser, final NavigationManager navigationManager, final PlayStoreUiElementNode cardNode) {
        reasonAvatarImage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (navigationManager != null && reasonUser.hasDetailsUrl) {
                    Document doc = new Document(reasonUser);
                    navigationManager.getClickListener(doc, new GenericUiElementNode(279, doc.getServerLogsCookie(), null, cardNode)).onClick(v);
                }
            }
        });
        reasonAvatarImage.setContentDescription(reasonUser.title);
    }

    private static void bindCardReasons(Document doc, PlayCardViewBase card, BitmapLoader bitmapLoader, NavigationManager navigationManager) {
        PlayCardSnippet snippet1 = card.getSnippet1();
        PlayCardSnippet snippet2 = card.getSnippet2();
        if (snippet1 != null || snippet2 != null) {
            if (snippet1 != null) {
                snippet1.setVisibility(8);
                snippet1.setSeparatorVisible(false);
            }
            if (snippet2 != null) {
                snippet2.setVisibility(8);
                snippet2.setSeparatorVisible(false);
            }
            int textOnlySnippetMarginLeft = card.getTextOnlySnippetMarginLeft();
            int avatarSnippetMarginLeft = card.getAvatarSnippetMarginLeft();
            SuggestionReasons reasons = doc.getSuggestionReasons();
            if (reasons != null && reasons.reason.length != 0) {
                PlayStoreUiElementNode cardNode = (PlayStoreUiElementNode) card.getLoggingData();
                Reason highestPriReason;
                if (reasons.reason.length == 1 || snippet1 == null || snippet2 == null) {
                    highestPriReason = findHighestPriorityReason(reasons);
                    if (snippet2 != null) {
                        snippet1 = snippet2;
                    }
                    bindReason(snippet1, highestPriReason, bitmapLoader, navigationManager, textOnlySnippetMarginLeft, avatarSnippetMarginLeft, cardNode);
                    return;
                }
                snippet2.setSeparatorVisible(true);
                highestPriReason = findHighestPriorityReason(reasons);
                Reason secondHighestPriReason = findHighestPriorityReason(reasons, highestPriReason);
                bindReason(snippet1, highestPriReason, bitmapLoader, navigationManager, textOnlySnippetMarginLeft, avatarSnippetMarginLeft, cardNode);
                bindReason(snippet2, secondHighestPriReason, bitmapLoader, navigationManager, textOnlySnippetMarginLeft, avatarSnippetMarginLeft, cardNode);
            } else if (snippet2 != null && doc.hasOptimalDeviceClassWarning()) {
                snippet2.setSnippetText(doc.getOptimalDeviceClassWarning().localizedMessage, textOnlySnippetMarginLeft, avatarSnippetMarginLeft);
                ((FifeImageView) snippet2.getImageView()).setVisibility(8);
                snippet2.setVisibility(0);
            }
        }
    }

    public static Reason findHighestPriorityReason(SuggestionReasons reasons) {
        return findHighestPriorityReason(reasons, null);
    }

    public static Reason findHighestPriorityReason(SuggestionReasons reasons, Reason reasonToIgnore) {
        if (reasons == null) {
            return null;
        }
        Reason highestPri = null;
        for (Reason reason : reasons.reason) {
            if (reason != reasonToIgnore) {
                if (reason.reasonReview != null) {
                    return reason;
                }
                if (reason.reasonPlusOne != null) {
                    highestPri = reason;
                } else if (reason.descriptionHtml.length() > 0 && (highestPri == null || highestPri.reasonPlusOne == null)) {
                    highestPri = reason;
                }
            }
        }
        return highestPri;
    }

    public static String getDocDisplaySubtitle(Document doc) {
        switch (doc.getDocumentType()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                return doc.getCreator();
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                return doc.getSubtitle();
            default:
                return null;
        }
    }

    private static synchronized void updateCardLabel(Document doc, PlayCardViewBase card) {
        synchronized (PlayCardUtils.class) {
            PlayCardLabelView label = card.getLabel();
            if (!(doc == null || label == null)) {
                int ownershipRenderingType = card.getOwnershipRenderingType();
                FinskyApp finskyApp = FinskyApp.get();
                PurchaseButtonHelper.getDocumentActions(finskyApp.getCurrentAccount(), finskyApp.getInstaller(), finskyApp.getLibraries(), finskyApp.getAppStates(), finskyApp.getToc(), doc, sDocumentActions);
                PurchaseButtonHelper.getListingStyle(sDocumentActions, sListingStyle);
                boolean displayAsOwned = sDocumentActions.displayAsOwned;
                boolean supportsIconRenderingForOwnedItems = (ownershipRenderingType & 1) != 0;
                boolean supportsLabelRenderingForOwnedItems = (ownershipRenderingType & 2) != 0;
                card.setItemOwned(displayAsOwned);
                label.setVisibility(8);
                if (displayAsOwned && supportsIconRenderingForOwnedItems) {
                    label.setVisibility(0);
                    label.setIcon(CorpusResourceUtils.getOwnedItemIndicator(doc.getBackend()));
                } else {
                    label.clearIcon();
                }
                boolean showLabel = !displayAsOwned || supportsLabelRenderingForOwnedItems;
                if (showLabel) {
                    String currentAccountName = finskyApp.getCurrentAccountName();
                    if (sCachedExperimentAccountName == null || !sCachedExperimentAccountName.equals(currentAccountName)) {
                        sHideSalesPricesExperimentEnabled = finskyApp.getExperiments().isEnabled("cl:billing.hide_sale_prices");
                        sCachedExperimentAccountName = currentAccountName;
                    }
                    label.setVisibility(0);
                    if (TextUtils.isEmpty(sListingStyle.offerFullText) || sHideSalesPricesExperimentEnabled) {
                        label.setText(sListingStyle.getString(label.getResources()), doc.getBackend());
                    } else {
                        label.setText(sListingStyle.offerText, sListingStyle.offerFullText, doc.getBackend(), label.getResources().getString(R.string.content_description_on_sale_price, new Object[]{sListingStyle.offerFullText, sListingStyle.offerText}));
                    }
                } else {
                    label.setText(null, doc.getBackend());
                }
                if (label.getVisibility() == 0 && displayAsOwned && supportsIconRenderingForOwnedItems && !supportsLabelRenderingForOwnedItems) {
                    label.setContentDescription(sListingStyle.getString(label.getResources()));
                }
            }
        }
    }

    private static void updateTitleContentDescription(Document doc, PlayCardViewBase card) {
        CharSequence title = doc != null ? doc.getTitle() : null;
        if (doc != null && !TextUtils.isEmpty(title)) {
            Resources res = card.getResources();
            int titleDescriptionResourceId = CorpusResourceUtils.getTitleContentDescriptionResourceId(res, doc.getDocumentType());
            if (titleDescriptionResourceId >= 0) {
                TextView cardTitleView = card.getTitle();
                if (cardTitleView != null) {
                    cardTitleView.setContentDescription(res.getString(titleDescriptionResourceId, new Object[]{title}));
                }
            }
        }
    }

    public static void bindCard(PlayCardViewBase card, Document doc, String parentId, BitmapLoader bitmapLoader, NavigationManager navigationManager, PlayStoreUiElementNode parentNode) {
        bindCard(card, doc, parentId, bitmapLoader, navigationManager, false, null, parentNode, true, -1);
    }

    public static void bindCard(PlayCardViewBase card, Document doc, String parentId, BitmapLoader bitmapLoader, NavigationManager navigationManager, boolean isDismissed, PlayCardDismissListener dismissListener, PlayStoreUiElementNode parentNode, boolean useDocumentAspectRatio, int displayIndex) {
        Utils.ensureOnMainThread();
        setLoggingDataIfNecessary(card, parentNode);
        CardUiElementNode cardLoggingData = (CardUiElementNode) card.getLoggingData();
        Resources res = card.getResources();
        boolean isHighlighted = doc.isHighlighted();
        PlayCardCustomizerRepository.getInstance().getCardCustomizer(card).preBind(card, doc);
        TextView title = card.getTitle();
        String displayTitle = doc.getTitle();
        if (displayIndex >= 0 && !isHighlighted) {
            displayTitle = card.getResources().getString(R.string.numbered_title, new Object[]{Integer.valueOf(displayIndex + 1), displayTitle});
        }
        title.setVisibility(0);
        title.setText(displayTitle);
        updateTitleContentDescription(doc, card);
        PlayCardThumbnail thumbnail = card.getThumbnail();
        thumbnail.setVisibility(0);
        thumbnail.updateCoverPadding(doc.getBackend());
        DocImageView thumbnailCover = (DocImageView) thumbnail.getImageView();
        thumbnailCover.bind(doc, bitmapLoader, card instanceof PlayCardImageTypeSequence ? ((PlayCardImageTypeSequence) card).getImageTypePreference() : PlayCardImageTypeSequence.CORE_IMAGE_TYPES);
        if (NavigationManager.areTransitionsEnabled()) {
            int docType = doc.getDocumentType();
            boolean skipTransition = docType == 2 || docType == 4 || docType == 24 || docType == 25;
            if (skipTransition) {
                card.setTransitionGroup(false);
            } else {
                sTransitionNameCardBuilder.setLength("transition_card_details:card:".length());
                sTransitionNameCardBuilder.append(doc.getDocId());
                sTransitionNameCardBuilder.append(':');
                sTransitionNameCardBuilder.append(parentId);
                card.setTransitionName(sTransitionNameCardBuilder.toString());
                sTransitionNameCoverBuilder.setLength("transition_card_details:cover:".length());
                sTransitionNameCoverBuilder.append(doc.getDocId());
                sTransitionNameCoverBuilder.append(':');
                sTransitionNameCoverBuilder.append(parentId);
                thumbnailCover.setTransitionName(sTransitionNameCoverBuilder.toString());
                card.setTransitionGroup(true);
            }
        }
        if (useDocumentAspectRatio) {
            card.setThumbnailAspectRatio(PlayCardClusterMetadata.getAspectRatio(doc.getDocumentType()));
        }
        DecoratedTextView subtitle = (DecoratedTextView) card.getSubtitle();
        StarRatingBar ratingBar = card.getRatingBar();
        DecoratedTextView itemBadge = (DecoratedTextView) card.getItemBadge();
        if (subtitle != null) {
            subtitle.setVisibility(4);
        }
        if (ratingBar != null) {
            ratingBar.setVisibility(4);
        }
        if (itemBadge != null) {
            itemBadge.setVisibility(4);
        }
        boolean supportsSubtitleAndRating = card.supportsSubtitleAndRating();
        boolean preferRating = ratingBar != null && doc.getDocumentType() == 1;
        boolean showRating = supportsSubtitleAndRating || preferRating;
        boolean showSubtitle = supportsSubtitleAndRating || !preferRating;
        if (showRating) {
            BadgeUtils.configureRatingItemSection(doc, ratingBar, itemBadge);
        }
        if (subtitle != null) {
            if (showSubtitle) {
                subtitle.setVisibility(0);
                subtitle.setText(getDocDisplaySubtitle(doc));
                if (card.shouldShowInlineCreatorBadge()) {
                    BadgeUtils.configureCreatorBadge(doc, bitmapLoader, subtitle);
                }
            } else {
                subtitle.setVisibility(8);
            }
        }
        updateCardLabel(doc, card);
        PlayTextView description = card.getDescription();
        if (description != null) {
            CharSequence docDescription = doc.getDescription();
            description.setText(docDescription);
            description.setVisibility(TextUtils.isEmpty(docDescription) ? 8 : 0);
        }
        bindCardReasons(doc, card, bitmapLoader, navigationManager);
        ImageView overflow = card.getOverflow();
        if (!(overflow == null || doc == null)) {
            if (isDismissed) {
                overflow.setVisibility(4);
                overflow.setOnClickListener(null);
            } else {
                overflow.setVisibility(0);
                configureOverflowView(card, overflow, doc, navigationManager, dismissListener, cardLoggingData);
                if (overflow.isFocusable()) {
                    PlayCardSnippet reason1 = card.getSnippet1();
                    PlayCardSnippet reason2 = card.getSnippet2();
                    PlayCardSnippet toWire = null;
                    if (reason1 != null && reason1.getVisibility() == 0) {
                        toWire = reason1;
                    } else if (reason2 != null && reason2.getVisibility() == 0) {
                        toWire = reason2;
                    }
                    View toWireImage = toWire == null ? null : toWire.getImageView();
                    if (toWire == null || toWireImage.getVisibility() != 0) {
                        overflow.setNextFocusDownId(-1);
                    } else {
                        overflow.setNextFocusDownId(toWireImage.getId());
                        toWireImage.setNextFocusUpId(overflow.getId());
                        toWireImage.setFocusable(true);
                    }
                }
            }
        }
        TextView highlightLabel = card.getHighlightLabel();
        if (highlightLabel != null) {
            if (!isHighlighted) {
                highlightLabel.setVisibility(8);
            } else if (isDismissed) {
                highlightLabel.setVisibility(4);
            } else {
                highlightLabel.setVisibility(0);
                highlightLabel.setText(doc.getHighlightBadgeText());
            }
        }
        TextView highlightSubtitleView = card.getHighlightSubtitle();
        if (!(highlightSubtitleView == null || !isHighlighted || res.getBoolean(R.bool.use_small_as_listing_card))) {
            CharSequence highlightSubtitleText = doc.getHighlightText();
            highlightSubtitleView.setText(highlightSubtitleText);
            highlightSubtitleView.setVisibility(TextUtils.isEmpty(highlightSubtitleText) ? 8 : 0);
        }
        if (isDismissed) {
            card.setDisplayAsDisabled(true);
            card.setOnClickListener(null);
            card.setClickable(false);
        } else {
            card.setDisplayAsDisabled(false);
            if (navigationManager != null) {
                card.setOnClickListener(navigationManager.getClickListener(doc, cardLoggingData));
            }
        }
        card.getLoadingIndicator().setVisibility(8);
        FinskyEventLog.setServerLogCookie(cardLoggingData.getPlayStoreUiElement(), doc.getServerLogsCookie());
        cardLoggingData.getParentNode().childImpression(cardLoggingData);
        card.setVisibility(0);
    }

    private static void configureOverflowView(PlayCardViewBase card, ImageView overflowView, Document doc, NavigationManager navigationManager, PlayCardDismissListener dismissListener, PlayStoreUiElementNode clickedNode) {
        final Context context = overflowView.getContext();
        final ImageView imageView = overflowView;
        final PlayStoreUiElementNode playStoreUiElementNode = clickedNode;
        final Document document = doc;
        final NavigationManager navigationManager2 = navigationManager;
        final PlayCardViewBase playCardViewBase = card;
        final PlayCardDismissListener playCardDismissListener = dismissListener;
        overflowView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PlayPopupMenu popupMenu = new PlayPopupMenu(context, imageView);
                Resources res = context.getResources();
                final FinskyEventLog eventLog = FinskyApp.get().getEventLogger();
                eventLog.logClickEvent(238, null, playStoreUiElementNode);
                final DfeApi dfeApi = FinskyApp.get().getDfeApi();
                Account account = dfeApi.getAccount();
                PlayCardUtils.configureActions(popupMenu, context, document, account, navigationManager2, playStoreUiElementNode);
                if (!WishlistHelper.shouldHideWishlistAction(document, dfeApi)) {
                    int wishlistActionTitleId;
                    int wishlistClickEventType;
                    if (WishlistHelper.isInWishlist(document, account)) {
                        wishlistActionTitleId = R.string.wishlist_remove;
                        wishlistClickEventType = 205;
                    } else {
                        wishlistActionTitleId = R.string.wishlist_add;
                        wishlistClickEventType = 204;
                    }
                    final int i = wishlistClickEventType;
                    popupMenu.addMenuItem(res.getString(wishlistActionTitleId), true, new OnActionSelectedListener() {
                        public void onActionSelected() {
                            eventLog.logClickEvent(i, null, playStoreUiElementNode);
                            HighlightUtils.trackClickUrl(document);
                            WishlistHelper.processWishlistClick(playCardViewBase, document, dfeApi);
                        }
                    });
                }
                if (playCardDismissListener != null && document.isDismissable()) {
                    String dismissalText = document.getNeutralDismissal().descriptionHtml;
                    if (TextUtils.isEmpty(dismissalText)) {
                        FinskyLog.wtf("Empty dismissal text received from the server for doc %s", document.getDocId());
                    } else {
                        popupMenu.addMenuItem(dismissalText, true, new CardDismissalAction(playCardViewBase, document, dfeApi, playCardDismissListener, playStoreUiElementNode));
                    }
                }
                OverflowLink[] overflowLinks = document.getOverflowLinks();
                if (overflowLinks != null && overflowLinks.length > 0) {
                    for (OverflowLink overflowLink : overflowLinks) {
                        final OverflowLink overflowLink2 = overflowLink;
                        popupMenu.addMenuItem(overflowLink.title, true, new OnActionSelectedListener() {
                            public void onActionSelected() {
                                navigationManager2.resolveLink(overflowLink2.link, FinskyApp.get().getToc(), context.getPackageManager());
                            }
                        });
                    }
                }
                imageView.setImageResource(R.drawable.play_overflow_menu_open);
                popupMenu.setOnPopupDismissListener(new OnDismissListener() {
                    public void onDismiss() {
                        imageView.setImageResource(R.drawable.play_overflow_menu);
                    }
                });
                popupMenu.show();
            }
        });
    }

    public static void updatePrimaryActionButton(Document doc, Context context, PlayCardViewBase card, PlayStoreUiElementNode parentNode, NavigationManager navigationManager) {
        if (doc != null && parentNode != null) {
            PlayActionButton button = (PlayActionButton) card.findViewById(R.id.li_primary_action_button);
            if (button != null) {
                button.setVisibility(8);
                FinskyApp finskyApp = FinskyApp.get();
                PurchaseButtonHelper.getDocumentActions(finskyApp.getCurrentAccount(), finskyApp.getInstaller(), finskyApp.getLibraries(), finskyApp.getAppStates(), finskyApp.getToc(), doc, sDocumentActions);
                PurchaseButtonHelper.getListingStyle(sDocumentActions, sListingStyle);
                OnClickListener purchaseOpenListener = null;
                CharSequence priceActionTitle = null;
                if (sDocumentActions.hasAction()) {
                    Resources resources = context.getResources();
                    for (int i = 0; i < sDocumentActions.actionCount; i++) {
                        DocumentAction action = sDocumentActions.getAction(i);
                        if (PurchaseButtonHelper.canCreateClickListener(action)) {
                            PurchaseButtonHelper.getActionStyleLong(action, sDocumentActions.backend, sActionStyle);
                            purchaseOpenListener = PurchaseButtonHelper.getActionClickListener(action, sDocumentActions.backend, navigationManager, null, parentNode, context);
                            priceActionTitle = sActionStyle.getString(resources);
                            break;
                        }
                        FinskyLog.w("Can't make click listener for action %d", Integer.valueOf(action.actionType));
                    }
                }
                if (purchaseOpenListener != null && priceActionTitle != null) {
                    button.configure(doc.getBackend(), priceActionTitle.toString(), purchaseOpenListener);
                    button.setVisibility(0);
                }
            }
        }
    }

    private static void configureActions(PlayPopupMenu popupMenu, Context context, Document doc, Account account, NavigationManager navigationManager, PlayStoreUiElementNode clickedNode) {
        FinskyApp finskyApp = FinskyApp.get();
        PurchaseButtonHelper.getDocumentActions(finskyApp.getCurrentAccount(), finskyApp.getInstaller(), finskyApp.getLibraries(), finskyApp.getAppStates(), finskyApp.getToc(), doc, sDocumentActions);
        Resources resources = context.getResources();
        int actionsAdded = 0;
        if (sDocumentActions.hasAction()) {
            for (int i = 0; i < sDocumentActions.actionCount; i++) {
                DocumentAction action = sDocumentActions.getAction(i);
                if (PurchaseButtonHelper.canCreateClickListener(action)) {
                    PurchaseButtonHelper.getActionStyleLong(action, sDocumentActions.backend, sActionStyle);
                    final OnClickListener purchaseOpenListener = PurchaseButtonHelper.getActionClickListener(action, sDocumentActions.backend, navigationManager, null, clickedNode, context);
                    final Document document = doc;
                    popupMenu.addMenuItem(sActionStyle.getString(resources), true, new OnActionSelectedListener() {
                        public void onActionSelected() {
                            HighlightUtils.trackClickUrl(document);
                            purchaseOpenListener.onClick(null);
                        }
                    });
                    actionsAdded++;
                } else {
                    FinskyLog.w("Can't make click listener for action %d", Integer.valueOf(action.actionType));
                }
            }
        }
        if (actionsAdded == 0 && sDocumentActions.hasStatus()) {
            PurchaseButtonHelper.getActionStatus(sDocumentActions, sActionStyle);
            popupMenu.addMenuItem(sActionStyle.getString(resources), false, null);
        }
    }

    private static int convertCardTypeToUiElementType(int cardType) {
        switch (cardType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return 509;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 511;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 507;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                return 506;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return 512;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return 513;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return 504;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return 510;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return 501;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return 508;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomControls /*11*/:
                return 505;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomGestures /*12*/:
                return 515;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_useViewLifecycle /*13*/:
                return 514;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_zOrderOnTop /*14*/:
                return 518;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiMapToolbar /*15*/:
                return 0;
            default:
                throw new IllegalArgumentException("Unknown card type: " + cardType);
        }
    }

    public static void recycleLoggingData(PlayCardViewBase card) {
        CardUiElementNode loggingData = (CardUiElementNode) card.getLoggingData();
        if (loggingData != null) {
            loggingData.onRecycle();
        }
    }

    private static void setLoggingDataIfNecessary(PlayCardViewBase card, PlayStoreUiElementNode parentNode) {
        CardUiElementNode loggingData = (CardUiElementNode) card.getLoggingData();
        if (loggingData != null) {
            loggingData.setParentNode(parentNode);
        } else {
            card.setLoggingData(new CardUiElementNode(card, parentNode));
        }
    }

    public static PlayStoreUiElementNode getCardParentNode(PlayCardViewBase card) {
        CardUiElementNode loggingData = (CardUiElementNode) card.getLoggingData();
        if (loggingData == null) {
            return null;
        }
        return loggingData.getParentNode();
    }
}
