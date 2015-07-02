package com.google.android.finsky.layout.play;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.play.PlayCardViewRate.RateListener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.UiUtils.ClusterFadeOutListener;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardViewBase;
import java.util.List;

public class PlayCardRateAndSuggestClusterView extends PlayCardClusterView implements ErrorListener, OnDataChangedListener, PlayCardDismissListener {
    private Document mClusterDoc;
    private ClusterFadeOutListener mClusterFadeOutListener;
    private PlayCardRateAndSuggestContentScroller mContentScroller;
    private TextView mEmptySadface;
    private int mIndexOfItemToRate;
    private DfeList mRecommendedItems;
    private boolean mShouldScrollRateCardOnDataLoad;
    private int mState;
    private PlayCardDismissListener mSuggestionsDismissListener;

    public PlayCardRateAndSuggestClusterView(Context context) {
        this(context, null);
    }

    public PlayCardRateAndSuggestClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mState = 0;
        this.mIndexOfItemToRate = 0;
    }

    protected int getPlayStoreUiElementType() {
        return 413;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContentScroller = (PlayCardRateAndSuggestContentScroller) findViewById(R.id.content_scroller);
        this.mEmptySadface = (TextView) findViewById(R.id.empty_sadface);
    }

    public void setClusterFadeOutListener(ClusterFadeOutListener clusterFadeOutListener) {
        this.mClusterFadeOutListener = clusterFadeOutListener;
    }

    private void setState(int newState, boolean isDuringInitialLoading) {
        this.mState = newState;
        this.mContent.setState(this.mState, isDuringInitialLoading);
    }

    private void syncIndexOfItemToRate(boolean isDuringInitialLoading) {
        boolean z;
        if (isDuringInitialLoading) {
            z = false;
        } else {
            z = true;
        }
        this.mShouldScrollRateCardOnDataLoad = z;
        int mTotalItemCount = this.mClusterDoc.getChildCount();
        while (this.mIndexOfItemToRate < mTotalItemCount) {
            Document candidate = this.mClusterDoc.getChildAt(this.mIndexOfItemToRate);
            if (candidate == null) {
                FinskyLog.wtf("Got a null document at index " + this.mIndexOfItemToRate, new Object[0]);
                this.mIndexOfItemToRate++;
            } else {
                String candidateId = candidate.getDocId();
                if (this.mClientMutationCache.isDismissedRecommendation(candidateId)) {
                    this.mIndexOfItemToRate++;
                } else {
                    Review candidateReview = this.mClientMutationCache.getCachedReview(candidateId, null);
                    if (candidateReview != null) {
                        if (candidateReview.starRating < ((Integer) G.positiveRateThreshold.get()).intValue()) {
                            this.mIndexOfItemToRate++;
                        } else {
                            boolean hasSuggestions;
                            SectionMetadata suggestions = candidate.getSuggestForRatingSection();
                            if (suggestions == null || TextUtils.isEmpty(suggestions.listUrl)) {
                                hasSuggestions = false;
                            } else {
                                hasSuggestions = true;
                            }
                            if (!hasSuggestions) {
                                this.mIndexOfItemToRate++;
                            } else {
                                return;
                            }
                        }
                    }
                    return;
                }
            }
        }
    }

    public static boolean isClusterDismissed(ClientMutationCache cache, Document clusterDoc) {
        for (int i = 0; i < clusterDoc.getChildCount(); i++) {
            Document childDoc = clusterDoc.getChildAt(i);
            if (!(childDoc == null || cache.isDismissedRecommendation(childDoc.getDocId()))) {
                Review review = cache.getCachedReview(childDoc.getDocId(), null);
                if (review == null || review.starRating >= ((Integer) G.positiveRateThreshold.get()).intValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void syncState(boolean isDuringInitialLoading) {
        boolean z = true;
        if (getCardChildCount() != 0) {
            int oldState = this.mState;
            if (this.mIndexOfItemToRate >= this.mClusterDoc.getChildCount()) {
                setState(3, isDuringInitialLoading);
                if (oldState != this.mState) {
                    if (isDuringInitialLoading) {
                        z = false;
                    }
                    transitionToEmptyState(z);
                    return;
                }
                return;
            }
            Document currentItemToRate = this.mClusterDoc.getChildAt(this.mIndexOfItemToRate);
            if (this.mClientMutationCache.getCachedReview(currentItemToRate.getDocId(), null) != null) {
                setState(2, isDuringInitialLoading);
                SectionMetadata suggestions = this.mClusterDoc.getChildAt(this.mIndexOfItemToRate).getSuggestForRatingSection();
                if (isDuringInitialLoading) {
                    this.mContentScroller.hideRateCard();
                    this.mHeader.replaceTitles(suggestions.header, Html.fromHtml(suggestions.descriptionHtml));
                }
                clearRecommendedItemsList();
                this.mRecommendedItems = new DfeList(this.mDfeApi, suggestions.listUrl, false);
                this.mRecommendedItems.addDataChangedListener(this);
                this.mRecommendedItems.addErrorListener(this);
                this.mRecommendedItems.startLoadItems();
                return;
            }
            setState(0, isDuringInitialLoading);
            this.mContent.setLooseDocumentsData(Lists.newArrayList(this.mClusterDoc.getChildAt(this.mIndexOfItemToRate)), this.mClusterDoc.getDocId());
            final PlayCardViewRate rateCard = (PlayCardViewRate) getCardChildAt(0);
            final float rateContentAspectRatio = PlayCardClusterMetadata.getAspectRatio(currentItemToRate.getDocumentType());
            if (!isDuringInitialLoading) {
                rateCard.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getContext(), 150, new AnimationListenerAdapter() {
                    public void onAnimationEnd(Animation animation) {
                        rateCard.setState(0);
                        PlayCardRateAndSuggestClusterView.this.mContent.bindCardAt(0, 0, PlayCardRateAndSuggestClusterView.this);
                        rateCard.setThumbnailAspectRatio(rateContentAspectRatio);
                        rateCard.startAnimation(PlayAnimationUtils.getFadeInAnimation(PlayCardRateAndSuggestClusterView.this.getContext(), 150, null));
                        PlayCardRateAndSuggestClusterView.this.setState(0, false);
                    }
                }));
            }
        }
    }

    public void onDismissDocument(Document doc, PlayCardViewBase card) {
        if (card.getCardType() == 13) {
            this.mClientMutationCache.dismissRecommendation(doc.getDocId());
            syncIndexOfItemToRate(false);
            syncState(false);
            return;
        }
        this.mSuggestionsDismissListener.onDismissDocument(doc, card);
    }

    public PlayCardClusterView withClusterDocumentData(Document clusterDoc) {
        this.mClusterDoc = clusterDoc;
        this.mContent.setClusterLoggingDocument(this.mClusterDoc);
        return this;
    }

    public PlayCardClusterView withLooseDocumentsData(List<Document> list, String parentId) {
        throw new UnsupportedOperationException("This cluster does not support loose data");
    }

    public void createContent(PlayCardClusterMetadata metadata, ClientMutationCache clientMutationCache, DfeApi dfeApi, NavigationManager navigationManager, BitmapLoader bitmapLoader, PlayCardDismissListener dismissListener, PlayCardHeap cardHeap, PlayStoreUiElementNode parentNode) {
        this.mClientMutationCache = clientMutationCache;
        syncIndexOfItemToRate(true);
        List<Document> passThrough = Lists.newArrayList();
        if (this.mIndexOfItemToRate < this.mClusterDoc.getChildCount()) {
            passThrough.add(this.mClusterDoc.getChildAt(this.mIndexOfItemToRate));
        }
        this.mContent.setLooseDocumentsData(passThrough, this.mClusterDoc.getDocId());
        this.mSuggestionsDismissListener = dismissListener;
        super.createContent(metadata, clientMutationCache, dfeApi, navigationManager, bitmapLoader, this, cardHeap, parentNode);
        syncState(true);
        PlayCardViewRate rateCard = (PlayCardViewRate) getCardChildAt(0);
        rateCard.setRateListener(new RateListener() {
            public void onRate(int rating, boolean isCommitted) {
                if (isCommitted) {
                    PlayCardRateAndSuggestClusterView.this.syncIndexOfItemToRate(false);
                    PlayCardRateAndSuggestClusterView.this.syncState(false);
                    return;
                }
                PlayCardRateAndSuggestClusterView.this.setState(1, false);
            }

            public void onRateCleared() {
                PlayCardRateAndSuggestClusterView.this.setState(0, false);
            }
        });
        rateCard.setState(0);
    }

    protected void onDetachedFromWindow() {
        if (hasCards()) {
            ((PlayCardViewRate) getCardChildAt(0)).setRateListener(null);
        }
        clearRecommendedItemsList();
        super.onDetachedFromWindow();
    }

    private void clearRecommendedItemsList() {
        if (this.mRecommendedItems != null) {
            this.mRecommendedItems.removeDataChangedListener(this);
            this.mRecommendedItems.removeErrorListener(this);
            this.mRecommendedItems = null;
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        if (!(this.mHeader == null || this.mHeader.getVisibility() == 8)) {
            this.mHeader.measure(widthMeasureSpec, 0);
            height += this.mHeader.getMeasuredHeight();
        }
        this.mContentScroller.measure(widthMeasureSpec, 0);
        height += this.mContentScroller.getMeasuredHeight();
        this.mEmptySadface.measure(MeasureSpec.makeMeasureSpec(this.mContentScroller.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.mContentScroller.getMeasuredHeight(), Integer.MIN_VALUE));
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int y = getPaddingTop();
        if (!(this.mHeader == null || this.mHeader.getVisibility() == 8)) {
            this.mHeader.layout(0, y, width, this.mHeader.getMeasuredHeight() + y);
            y += this.mHeader.getMeasuredHeight();
        }
        int contentScrollerHeight = this.mContentScroller.getMeasuredHeight();
        this.mContentScroller.layout(0, y, width, y + contentScrollerHeight);
        int sadfaceY = y + ((contentScrollerHeight - this.mEmptySadface.getMeasuredHeight()) / 2);
        this.mEmptySadface.layout(0, sadfaceY, width, this.mEmptySadface.getMeasuredHeight() + sadfaceY);
    }

    public void onErrorResponse(VolleyError volleyError) {
        handleErrorOrNoSuggestionsResponse();
    }

    private void handleErrorOrNoSuggestionsResponse() {
        Document ratedDoc = this.mClusterDoc.getChildAt(this.mIndexOfItemToRate);
        if (!(ratedDoc == null || TextUtils.isEmpty(ratedDoc.getDocId()))) {
            this.mClientMutationCache.dismissRecommendation(ratedDoc.getDocId());
        }
        syncIndexOfItemToRate(false);
        syncState(false);
    }

    public void onDataChanged() {
        int recommendedItemsCount = this.mRecommendedItems.getCount();
        if (recommendedItemsCount == 0) {
            handleErrorOrNoSuggestionsResponse();
            return;
        }
        int i;
        List<Document> documents = Lists.newArrayList();
        documents.add(this.mClusterDoc.getChildAt(this.mIndexOfItemToRate));
        int extraDocCount = Math.min(getCardChildCount() - 1, recommendedItemsCount);
        for (i = 0; i < extraDocCount; i++) {
            documents.add(this.mRecommendedItems.getItem(i));
        }
        this.mContent.setLooseDocumentsData(documents, this.mClusterDoc.getDocId());
        for (i = 1; i <= extraDocCount; i++) {
            this.mContent.bindCardAt(i, i, this);
        }
        for (i = extraDocCount + 1; i < getCardChildCount(); i++) {
            this.mContent.hideCardAt(i);
        }
        final SectionMetadata suggestions = this.mClusterDoc.getChildAt(this.mIndexOfItemToRate).getSuggestForRatingSection();
        if (!this.mShouldScrollRateCardOnDataLoad) {
            this.mContentScroller.hideRateCard();
            this.mHeader.replaceTitles(suggestions.header, Html.fromHtml(suggestions.descriptionHtml));
        } else if (this.mContent.mMetadata.getWidth() <= 2) {
            this.mContentScroller.scrollAwayRateCard();
            this.mHeader.replaceTitles(suggestions.header, Html.fromHtml(suggestions.descriptionHtml));
        } else {
            new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                public void run() {
                    PlayCardRateAndSuggestClusterView.this.mContentScroller.scrollAwayRateCard();
                    PlayCardRateAndSuggestClusterView.this.mHeader.replaceTitles(suggestions.header, Html.fromHtml(suggestions.descriptionHtml));
                }
            }, 750);
        }
        setState(2, false);
    }

    private void transitionToEmptyState(boolean toAnimate) {
        if (toAnimate) {
            this.mContentScroller.startAnimation(PlayAnimationUtils.getFadeOutAnimation(getContext(), 250, new AnimationListenerAdapter() {
                public void onAnimationEnd(Animation animation) {
                    PlayCardRateAndSuggestClusterView.this.mContentScroller.setVisibility(8);
                    if (PlayListView.ENABLE_ANIMATION) {
                        UiUtils.fadeOutCluster(PlayCardRateAndSuggestClusterView.this, PlayCardRateAndSuggestClusterView.this.mClusterFadeOutListener, 2500);
                    }
                }
            }));
            Animation fadeInAnimation = PlayAnimationUtils.getFadeInAnimation(getContext(), 250, null);
            this.mEmptySadface.setVisibility(0);
            UiUtils.sendAccessibilityEventWithText(getContext(), this.mEmptySadface.getText().toString(), this.mEmptySadface);
            this.mEmptySadface.startAnimation(fadeInAnimation);
            return;
        }
        this.mContentScroller.setVisibility(8);
        this.mEmptySadface.setVisibility(0);
    }
}
