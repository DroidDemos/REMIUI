package com.google.android.finsky.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.layout.play.PlayRatingBar;
import com.google.android.finsky.layout.play.PlayRatingBar.OnRatingChangeListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.RateReviewHelper;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.image.ThumbnailUtils;
import com.google.android.play.image.FifeImageView;

public class RateReviewActivity extends FragmentActivity implements PlayStoreUiElementNode {
    private String mAccountName;
    private Document mAuthor;
    private int mBackend;
    private ButtonBar mButtonBar;
    private boolean mClickDebounce;
    private TextView mCommentView;
    private String mDocDetailsUrl;
    private String mDocId;
    TextWatcher mEmptyTextWatcher;
    private FinskyEventLog mEventLogger;
    private long mImpressionId;
    private boolean mIsExternalRequest;
    private PlayRatingBar mRatingBar;
    private TextView mRatingDescription;
    private int mReviewMode;
    private Bundle mSavedInstanceState;
    private TextView mTitleView;
    private PlayStoreUiElement mUiElementProto;

    public RateReviewActivity() {
        this.mEmptyTextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                RateReviewActivity.this.syncButtonEnabledState();
            }

            public void afterTextChanged(Editable arg0) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        };
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(1203);
        this.mClickDebounce = false;
    }

    public static Intent createIntent(String accountName, Document doc, Document author, Review previousReview, int prefilledRating, boolean isExternalRequest) {
        Intent intent = new Intent(FinskyApp.get(), RateReviewActivity.class);
        intent.putExtra("account_name", accountName);
        intent.putExtra("doc_id", doc.getDocId());
        intent.putExtra("doc_details_url", doc.getDetailsUrl());
        intent.putExtra("doc_title", doc.getTitle());
        intent.putExtra("author", author);
        intent.putExtra("backend", doc.getBackend());
        intent.putExtra("previous_rating", prefilledRating);
        if (previousReview != null) {
            intent.putExtra("previous_title", previousReview.title);
            intent.putExtra("previous_comment", previousReview.comment);
            intent.putExtra("previous_author", ParcelableProto.forProto(previousReview.author));
        }
        intent.putExtra("server_logs_cookie", doc.getServerLogsCookie());
        intent.putExtra("impression_id", FinskyEventLog.getNextImpressionId());
        intent.putExtra("is_external_request", isExternalRequest);
        if (isExternalRequest) {
            intent.putExtra("doc_creator", doc.getCreator());
            Image thumbnailImage = ThumbnailUtils.getImageFromDocument(doc, 0, FinskyApp.get().getResources().getDimensionPixelSize(R.dimen.base_row_height), new int[]{4, 0});
            if (thumbnailImage != null) {
                intent.putExtra("doc_thumbnail_url", thumbnailImage.imageUrl);
            }
            intent.putExtra("doc_thumbnail_is_fife", thumbnailImage.supportsFifeUrlOptions);
        }
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        int previousRating;
        String previousTitle;
        String previousComment;
        this.mSavedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_review_dialog);
        this.mReviewMode = ((Boolean) G.enableReviewComments.get()).booleanValue() ? 2 : 1;
        Intent intent = getIntent();
        this.mAccountName = intent.getStringExtra("account_name");
        this.mIsExternalRequest = intent.getBooleanExtra("is_external_request", true);
        this.mDocId = intent.getStringExtra("doc_id");
        this.mDocDetailsUrl = intent.getStringExtra("doc_details_url");
        String docTitle = intent.getStringExtra("doc_title");
        this.mBackend = intent.getIntExtra("backend", 0);
        DocV2 previousAuthorDocV2 = (DocV2) ParcelableProto.getProtoFromIntent(intent, "previous_author");
        Document previousAuthor = previousAuthorDocV2 != null ? new Document(previousAuthorDocV2) : null;
        Document author = (Document) intent.getParcelableExtra("author");
        if (this.mSavedInstanceState != null) {
            previousRating = this.mSavedInstanceState.getInt("previous_rating");
            previousTitle = this.mSavedInstanceState.getString("previous_title");
            previousComment = this.mSavedInstanceState.getString("previous_comment");
        } else {
            previousRating = intent.getIntExtra("previous_rating", 0);
            previousTitle = intent.getStringExtra("previous_title");
            previousComment = intent.getStringExtra("previous_comment");
        }
        View ratingSetterFrame = findViewById(R.id.rate_review_container);
        this.mTitleView = (EditText) ratingSetterFrame.findViewById(R.id.review_subject);
        this.mCommentView = (EditText) ratingSetterFrame.findViewById(R.id.review_body);
        int i = this.mReviewMode;
        if (r0 == 1) {
            this.mTitleView.setVisibility(8);
            this.mCommentView.setVisibility(8);
        } else {
            this.mTitleView.setText(previousTitle);
            this.mCommentView.setText(previousComment);
        }
        this.mTitleView.addTextChangedListener(this.mEmptyTextWatcher);
        this.mCommentView.addTextChangedListener(this.mEmptyTextWatcher);
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, intent.getByteArrayExtra("server_logs_cookie"));
        this.mEventLogger = FinskyApp.get().getEventLogger(this.mAccountName);
        this.mImpressionId = intent.getLongExtra("impression_id", 0);
        if (savedInstanceState == null) {
            this.mEventLogger.logPathImpression(this.mImpressionId, this);
        }
        final boolean isEditingReview = previousAuthorDocV2 != null;
        this.mButtonBar = (ButtonBar) ratingSetterFrame.findViewById(R.id.button_bar);
        this.mButtonBar.setPositiveButtonEnabled(true);
        this.mButtonBar.setPositiveButtonTitle(isEditingReview ? R.string.save_review : R.string.submit_review);
        this.mButtonBar.setNegativeButtonVisible(isEditingReview);
        this.mButtonBar.setNegativeButtonTitle((int) R.string.delete_review);
        this.mButtonBar.setClickListener(new ClickListener() {
            public void onPositiveButtonClick() {
                if (!RateReviewActivity.this.mClickDebounce) {
                    RateReviewActivity.this.mClickDebounce = true;
                    RateReviewActivity.this.mEventLogger.logClickEvent(isEditingReview ? 1205 : 1204, null, RateReviewActivity.this);
                    if (RateReviewActivity.this.mIsExternalRequest) {
                        RateReviewHelper.updateReview(RateReviewActivity.this.mAccountName, RateReviewActivity.this.mDocId, RateReviewActivity.this.mDocDetailsUrl, RateReviewActivity.this.getUserRating(), RateReviewActivity.this.getUserTitle(), RateReviewActivity.this.getUserComment(), RateReviewActivity.this.mAuthor, RateReviewActivity.this, null, 1203);
                    }
                    Intent intent = new Intent();
                    intent.putExtra("doc_id", RateReviewActivity.this.mDocId);
                    intent.putExtra("rating", RateReviewActivity.this.getUserRating());
                    intent.putExtra("review_title", RateReviewActivity.this.getUserTitle());
                    intent.putExtra("review_comment", RateReviewActivity.this.getUserComment());
                    if (!RateReviewActivity.this.mIsExternalRequest) {
                        intent.putExtra("author", RateReviewActivity.this.mAuthor);
                    }
                    intent.putExtra("author_title", RateReviewActivity.this.mAuthor.getTitle());
                    intent.putExtra("author_profile_image_url", ((Image) RateReviewActivity.this.mAuthor.getImages(4).get(0)).imageUrl);
                    RateReviewActivity.this.setResult(1, intent);
                    RateReviewActivity.this.finish();
                }
            }

            public void onNegativeButtonClick() {
                if (!RateReviewActivity.this.mClickDebounce) {
                    RateReviewActivity.this.mClickDebounce = true;
                    RateReviewActivity.this.mEventLogger.logClickEvent(1206, null, RateReviewActivity.this);
                    if (RateReviewActivity.this.mIsExternalRequest) {
                        RateReviewHelper.deleteReview(RateReviewActivity.this.mAccountName, RateReviewActivity.this.mDocId, RateReviewActivity.this.mDocDetailsUrl, RateReviewActivity.this, null);
                    }
                    Intent intent = new Intent();
                    intent.putExtra("doc_id", RateReviewActivity.this.mDocId);
                    RateReviewActivity.this.setResult(2, intent);
                    RateReviewActivity.this.finish();
                }
            }
        });
        this.mRatingBar = (PlayRatingBar) ratingSetterFrame.findViewById(R.id.rating_setter);
        this.mRatingDescription = (TextView) ratingSetterFrame.findViewById(R.id.rating_description);
        syncRatingDescription(previousRating);
        this.mRatingBar.configure(previousRating, this.mBackend, new OnRatingChangeListener() {
            public void onRatingChanged(PlayRatingBar ratingBar, int rating) {
                RateReviewActivity.this.syncButtonEnabledState();
                RateReviewActivity.this.syncRatingDescription(rating);
                if (rating > 0) {
                    UiUtils.sendAccessibilityEventWithText(RateReviewActivity.this, RateReviewActivity.this.getResources().getQuantityString(R.plurals.content_description_rated, rating, new Object[]{Integer.valueOf(rating)}), RateReviewActivity.this.mRatingDescription);
                }
            }
        });
        TextView itemTitle = (TextView) ratingSetterFrame.findViewById(R.id.item_title);
        if (this.mIsExternalRequest) {
            itemTitle.setVisibility(0);
            itemTitle.setText(docTitle);
        } else {
            itemTitle.setVisibility(8);
        }
        if (previousAuthor != null) {
            this.mAuthor = previousAuthor;
        } else {
            this.mAuthor = author;
        }
        TextView reviewBy = (TextView) findViewById(R.id.review_by);
        Resources resources = getResources();
        Object[] objArr = new Object[1];
        objArr[0] = this.mAuthor.getTitle();
        reviewBy.setText(resources.getString(R.string.review_by, objArr));
        Image image = (Image) this.mAuthor.getImages(4).get(0);
        ((FifeImageView) findViewById(R.id.user_profile_image)).setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
    }

    protected void onDestroy() {
        this.mTitleView.removeTextChangedListener(this.mEmptyTextWatcher);
        this.mCommentView.removeTextChangedListener(this.mEmptyTextWatcher);
        super.onDestroy();
    }

    public void onStart() {
        super.onStart();
        syncButtonEnabledState();
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        View decorView = getWindow().getDecorView();
        if (action == 0 && (x < 0 || x >= decorView.getWidth() || y < 0 || y >= decorView.getHeight())) {
            finishAsCanceled();
            return true;
        } else if (action != 4) {
            return super.onTouchEvent(event);
        } else {
            finishAsCanceled();
            return true;
        }
    }

    private void finishAsCanceled() {
        this.mEventLogger.logClickEvent(1207, null, this);
        Intent intent = new Intent();
        intent.putExtra("doc_id", this.mDocId);
        setResult(3, intent);
        finish();
    }

    private int getUserRating() {
        return this.mRatingBar.getRating();
    }

    private String getUserTitle() {
        return this.mTitleView.getText().toString().trim();
    }

    private String getUserComment() {
        return this.mCommentView.getText().toString().trim();
    }

    private void syncRatingDescription(int rating) {
        Resources res = getResources();
        this.mRatingDescription.setText(RateReviewHelper.getRatingDescription(rating));
        if (rating == 0) {
            this.mRatingDescription.setTextColor(res.getColor(R.color.play_fg_secondary));
            return;
        }
        this.mRatingDescription.setTextColor(CorpusResourceUtils.getSecondaryTextColor(this, this.mBackend));
    }

    private void syncButtonEnabledState() {
        boolean hasRatings;
        boolean enabled;
        if (getUserRating() > 0) {
            hasRatings = true;
        } else {
            hasRatings = false;
        }
        if (this.mReviewMode == 3) {
            boolean hasTitle;
            if (TextUtils.isEmpty(getUserTitle())) {
                hasTitle = false;
            } else {
                hasTitle = true;
            }
            boolean hasComment;
            if (TextUtils.isEmpty(getUserComment())) {
                hasComment = false;
            } else {
                hasComment = true;
            }
            if (hasTitle && hasComment && hasRatings) {
                enabled = true;
            } else {
                enabled = false;
            }
        } else {
            enabled = hasRatings;
        }
        this.mButtonBar.setPositiveButtonEnabled(enabled);
    }

    public void onBackPressed() {
        this.mEventLogger.logClickEvent(1207, null, this);
        Intent intent = new Intent();
        intent.putExtra("doc_id", this.mDocId);
        setResult(3, intent);
        super.onBackPressed();
    }

    public void onSaveInstanceState(Bundle icicle) {
        icicle.putInt("previous_rating", getUserRating());
        icicle.putString("previous_title", getUserTitle());
        icicle.putString("previous_comment", getUserComment());
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return null;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new IllegalStateException("unwanted children");
    }
}
