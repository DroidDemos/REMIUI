package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.ReviewItemLayout;
import com.google.android.finsky.layout.play.PlayRatingBar;
import com.google.android.finsky.layout.play.PlayRatingBar.OnRatingChangeListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.utils.DocV2Utils;

public class RateReviewModuleLayout extends LinearLayout {
    private boolean mHasBinded;
    private FifeImageView mMyAvatar;
    private TextView mMyDisplayName;
    private PlayRatingBar mMyRatingBar;
    private TextView mMyRatingText;
    private RateReviewClickListener mRateReviewClickListener;
    private LinearLayout mRatingLayout;
    private ReviewItemLayout mReviewItemLayout;

    public interface RateReviewClickListener {
        void onEditClicked();

        void onRatingClicked(int i);
    }

    public RateReviewModuleLayout(Context context) {
        this(context, null);
    }

    public RateReviewModuleLayout(Context context, AttributeSet attrs) {
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

    public void bind(Review review, Document document, DocV2 authorDocV2, RateReviewClickListener rateReviewClickListener, NavigationManager navigationManager, PlayStoreUiElementNode mParentNode) {
        int rating;
        String header;
        this.mRateReviewClickListener = rateReviewClickListener;
        this.mMyRatingBar.setVerticalPadding(R.dimen.details_review_section_rating_vpadding);
        this.mMyRatingBar.configure(0, document.getBackend(), new OnRatingChangeListener() {
            public void onRatingChanged(PlayRatingBar ratingBar, int rating) {
                if (RateReviewModuleLayout.this.mRateReviewClickListener != null) {
                    RateReviewModuleLayout.this.mRateReviewClickListener.onRatingClicked(rating);
                }
            }
        });
        this.mReviewItemLayout.setActionClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (RateReviewModuleLayout.this.mRateReviewClickListener != null) {
                    RateReviewModuleLayout.this.mRateReviewClickListener.onEditClicked();
                }
            }
        });
        Resources res = getResources();
        if (review != null) {
            rating = review.starRating;
            this.mReviewItemLayout.setReviewInfo(document, review, Integer.MAX_VALUE, navigationManager, true, mParentNode);
            this.mReviewItemLayout.setVisibility(0);
            this.mRatingLayout.setVisibility(8);
        } else {
            rating = 0;
            this.mReviewItemLayout.setVisibility(8);
            this.mRatingLayout.setVisibility(0);
        }
        if (rating > 0) {
            header = res.getString(R.string.my_review);
            setContentDescription(res.getQuantityString(R.plurals.content_description_rated, rating, new Object[]{Integer.valueOf(rating)}));
        } else {
            header = CorpusResourceUtils.getRateString(res, document.getDocumentType());
            setContentDescription(res.getString(R.string.content_description_rate_and_review));
        }
        if (this.mRatingLayout.getVisibility() != 8) {
            this.mMyRatingBar.setRating(rating);
            this.mMyRatingText.setText(Utils.getItalicSafeString(header));
        }
        if (authorDocV2 != null) {
            this.mMyDisplayName.setText(authorDocV2.title);
            Image avatarImage = DocV2Utils.getFirstImageOfType(authorDocV2, 4);
            this.mMyAvatar.setImage(avatarImage.imageUrl, avatarImage.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
        } else {
            this.mMyAvatar.setVisibility(8);
            this.mMyDisplayName.setVisibility(8);
        }
        this.mHasBinded = true;
    }

    public boolean hasBinded() {
        return this.mHasBinded;
    }
}
