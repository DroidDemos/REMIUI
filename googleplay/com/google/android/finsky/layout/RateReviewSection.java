package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.RateReviewActivity;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayRatingBar;
import com.google.android.finsky.layout.play.PlayRatingBar.OnRatingChangeListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.RateReviewHelper;
import com.google.android.finsky.utils.RateReviewHelper.CheckAndConfirmGPlusListener;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.utils.DocV2Utils;

public class RateReviewSection extends LinearLayout {
    private ClientMutationCache mClientMutationCache;
    private Document mDocument;
    private Fragment mFragment;
    private FifeImageView mMyAvatar;
    private TextView mMyDisplayName;
    private PlayRatingBar mMyRatingBar;
    private TextView mMyRatingText;
    private NavigationManager mNavigationManager;
    private PlayStoreUiElementNode mParentNode;
    private LinearLayout mRatingLayout;
    private Review mReview;
    private Review mReviewFromServer;
    private ReviewItemLayout mReviewItemLayout;

    public RateReviewSection(Context context) {
        this(context, null);
    }

    public RateReviewSection(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMyAvatar = (FifeImageView) findViewById(R.id.my_avatar);
        this.mMyDisplayName = (TextView) findViewById(R.id.my_display_name);
        this.mMyRatingText = (TextView) findViewById(R.id.my_rating_text);
        this.mMyRatingBar = (PlayRatingBar) findViewById(R.id.my_rating_bar);
        this.mReviewItemLayout = (ReviewItemLayout) findViewById(R.id.review_item_container);
        this.mRatingLayout = (LinearLayout) findViewById(R.id.rating_layout);
    }

    private void updateVisibility(Libraries libraries, Document document, boolean hasDetailsLoaded) {
        if (hasDetailsLoaded && DocUtils.canRate(libraries, document)) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void configure(ClientMutationCache clientMutationCache, Libraries libraries, final Fragment fragment, Document document, boolean hasDetailsLoaded, Review review, NavigationManager navigationManager, PlayStoreUiElementNode parentNode) {
        updateVisibility(libraries, document, hasDetailsLoaded);
        if (getVisibility() == 0) {
            this.mFragment = fragment;
            this.mClientMutationCache = clientMutationCache;
            this.mDocument = document;
            this.mReviewFromServer = review;
            this.mNavigationManager = navigationManager;
            this.mParentNode = parentNode;
            refresh();
            this.mMyRatingBar.setVerticalPadding(R.dimen.details_review_section_rating_vpadding);
            this.mMyRatingBar.configure(0, document.getBackend(), new OnRatingChangeListener() {
                public void onRatingChanged(PlayRatingBar ratingBar, int rating) {
                    FinskyApp.get().getEventLogger().logClickEvent(1201, null, RateReviewSection.this.mParentNode);
                    RateReviewSection.this.launchReviewsDialog(fragment, RateReviewSection.this.mReview, rating);
                }
            });
            this.mReviewItemLayout.setActionClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (RateReviewSection.this.mReview != null) {
                        FinskyApp.get().getEventLogger().logClickEvent(1202, null, RateReviewSection.this.mParentNode);
                        RateReviewSection.this.launchReviewsDialog(fragment, RateReviewSection.this.mReview, RateReviewSection.this.mReview.starRating);
                    }
                }
            });
        }
    }

    private void launchReviewsDialog(Fragment fragment, final Review review, final int prefilledRating) {
        RateReviewHelper.checkAndConfirmGPlus(fragment.getActivity(), new CheckAndConfirmGPlusListener() {
            public void onCheckAndConfirmGPlusPassed(Document plusDoc) {
                RateReviewSection.this.mFragment.startActivityForResult(RateReviewActivity.createIntent(FinskyApp.get().getCurrentAccountName(), RateReviewSection.this.mDocument, plusDoc, review, prefilledRating, false), 43);
            }

            public void onCheckAndConfirmGPlusFailed() {
                RateReviewSection.this.refresh();
            }
        });
    }

    public void refresh() {
        int newRating;
        String header;
        Resources res = getResources();
        this.mReview = this.mClientMutationCache.getCachedReview(this.mDocument.getDocId(), this.mReviewFromServer);
        if (this.mReview != null) {
            newRating = this.mReview.starRating;
            this.mReviewItemLayout.setReviewInfo(this.mDocument, this.mReview, Integer.MAX_VALUE, this.mNavigationManager, true, this.mParentNode);
            this.mReviewItemLayout.setVisibility(0);
            this.mRatingLayout.setVisibility(8);
        } else {
            newRating = 0;
            this.mReviewItemLayout.setVisibility(8);
            this.mRatingLayout.setVisibility(0);
        }
        if (newRating > 0) {
            header = res.getString(R.string.my_review);
            setContentDescription(res.getQuantityString(R.plurals.content_description_rated, newRating, new Object[]{Integer.valueOf(newRating)}));
        } else {
            header = CorpusResourceUtils.getRateString(res, this.mDocument.getDocumentType());
            setContentDescription(res.getString(R.string.content_description_rate_and_review));
        }
        if (this.mRatingLayout.getVisibility() != 8) {
            this.mMyRatingBar.setRating(newRating);
            this.mMyRatingText.setText(Utils.getItalicSafeString(header));
            FinskyApp.get().getPlayDfeApi().getPlusProfile(new Listener<PlusProfileResponse>() {
                public void onResponse(PlusProfileResponse response) {
                    DocV2 plusDocV2 = response.partialUserProfile;
                    if (plusDocV2 != null) {
                        RateReviewSection.this.mMyDisplayName.setText(plusDocV2.title);
                        Image avatarImage = DocV2Utils.getFirstImageOfType(plusDocV2, 4);
                        RateReviewSection.this.mMyAvatar.setImage(avatarImage.imageUrl, avatarImage.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
                        return;
                    }
                    RateReviewSection.this.mMyAvatar.setVisibility(8);
                    RateReviewSection.this.mMyDisplayName.setVisibility(8);
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError e) {
                    RateReviewSection.this.mMyAvatar.setVisibility(8);
                    RateReviewSection.this.mMyDisplayName.setVisibility(8);
                }
            }, true);
        }
    }
}
