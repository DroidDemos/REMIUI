package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.utils.DocV2Utils;

public class ReviewItemLayout extends RelativeLayout {
    private View mActionContainer;
    private ImageView mActionImage;
    private TextView mActionText;
    private TextView mAuthor;
    private TextView mBody;
    private boolean mCanFeedback;
    private ReviewItemHeaderLayout mHeader;
    private boolean mIsYourReview;
    private TextView mMetadata;
    private PlayStoreUiElementNode mParentNode;
    private FifeImageView mProfilePicture;
    private ReviewReplyLayout mReviewReplyLayout;
    private ViewStub mReviewReplyStub;
    private TextView mTitle;
    private TextView mYourReviewLabel;

    public ReviewItemLayout(Context context) {
        super(context);
    }

    public ReviewItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAuthor = (TextView) findViewById(R.id.review_author);
        this.mHeader = (ReviewItemHeaderLayout) findViewById(R.id.review_header);
        this.mTitle = (TextView) findViewById(R.id.review_title);
        this.mBody = (TextView) findViewById(R.id.review_text);
        this.mMetadata = (TextView) findViewById(R.id.review_metadata);
        this.mProfilePicture = (FifeImageView) findViewById(R.id.user_profile_image);
        this.mActionContainer = findViewById(R.id.action_container);
        this.mActionImage = (ImageView) findViewById(R.id.action_image);
        this.mActionText = (TextView) findViewById(R.id.action_text);
        this.mYourReviewLabel = (TextView) findViewById(R.id.your_review_label);
        this.mReviewReplyStub = (ViewStub) findViewById(R.id.review_reply_stub);
        this.mReviewReplyLayout = (ReviewReplyLayout) findViewById(R.id.review_reply_container);
    }

    public void setReviewInfo(Document doc, Review review, int reviewTextMaxLines, NavigationManager navigationManager, boolean isYourReview, PlayStoreUiElementNode parentNode) {
        this.mParentNode = parentNode;
        Resources res = getResources();
        this.mIsYourReview = isYourReview;
        boolean z = this.mIsYourReview ? false : !TextUtils.isEmpty(review.commentId);
        this.mCanFeedback = z;
        if (this.mIsYourReview) {
            this.mActionImage.setImageDrawable(res.getDrawable(CorpusResourceUtils.getReviewEditDrawable(doc.getBackend())));
            this.mActionText.setText(res.getString(R.string.edit_review).toUpperCase());
            this.mActionText.setTextColor(CorpusResourceUtils.getPrimaryColor(getContext(), doc.getBackend()));
            this.mActionText.setVisibility(0);
            this.mYourReviewLabel.setText(res.getString(R.string.your_review_label).toUpperCase(res.getConfiguration().locale));
            this.mYourReviewLabel.setVisibility(0);
        } else {
            this.mActionImage.setImageDrawable(res.getDrawable(R.drawable.ic_review));
            this.mActionContainer.setVisibility(this.mCanFeedback ? 0 : 8);
            this.mActionText.setVisibility(8);
            this.mYourReviewLabel.setVisibility(8);
            this.mActionContainer.setContentDescription(res.getString(R.string.review_feedback_icon_description));
        }
        String authorName = review.author.title;
        if (TextUtils.isEmpty(authorName)) {
            this.mAuthor.setVisibility(8);
        } else {
            this.mAuthor.setText(authorName);
            this.mAuthor.setVisibility(0);
        }
        this.mHeader.setReviewInfo(review);
        if (TextUtils.isEmpty(review.title)) {
            this.mTitle.setVisibility(8);
        } else {
            this.mTitle.setVisibility(0);
            this.mTitle.setText(Html.fromHtml(review.title));
        }
        if (TextUtils.isEmpty(review.comment)) {
            this.mBody.setVisibility(8);
        } else {
            this.mBody.setVisibility(0);
            this.mBody.setText(Html.fromHtml(review.comment));
            this.mBody.setMaxLines(reviewTextMaxLines);
        }
        String extraInfo = getReviewExtraInfo(doc, review);
        if (TextUtils.isEmpty(extraInfo)) {
            this.mMetadata.setVisibility(8);
        } else {
            this.mMetadata.setVisibility(0);
            this.mMetadata.setText(extraInfo);
        }
        DocV2 plusDoc = review.author;
        if (plusDoc != null) {
            Image image = DocV2Utils.getFirstImageOfType(plusDoc, 4);
            this.mProfilePicture.setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
            this.mProfilePicture.setVisibility(0);
        } else {
            this.mProfilePicture.setVisibility(8);
        }
        if (plusDoc == null || !plusDoc.hasDetailsUrl) {
            this.mProfilePicture.setOnClickListener(null);
            this.mProfilePicture.setContentDescription(null);
            this.mProfilePicture.setFocusable(false);
        } else {
            this.mProfilePicture.setOnClickListener(navigationManager.getClickListener(new Document(plusDoc), new GenericUiElementNode(279, plusDoc.serverLogsCookie, null, this.mParentNode)));
            this.mProfilePicture.setContentDescription(res.getString(R.string.content_description_view_gplus_profile, new Object[]{plusDoc.title}));
            this.mProfilePicture.setFocusable(true);
        }
        if (review.hasReplyText) {
            if (this.mReviewReplyLayout == null) {
                this.mReviewReplyLayout = (ReviewReplyLayout) this.mReviewReplyStub.inflate();
            }
            this.mReviewReplyLayout.setReviewInfo(doc, review);
        } else if (this.mReviewReplyLayout != null) {
            this.mReviewReplyLayout.setVisibility(8);
        }
    }

    private String getReviewExtraInfo(Document doc, Review review) {
        if (doc.getBackend() != 3) {
            return null;
        }
        boolean hasVersion;
        boolean hasDeviceName;
        String version = review.documentVersion;
        String deviceName = review.deviceName;
        if (TextUtils.isEmpty(version)) {
            hasVersion = false;
        } else {
            hasVersion = true;
        }
        if (TextUtils.isEmpty(deviceName)) {
            hasDeviceName = false;
        } else {
            hasDeviceName = true;
        }
        if (hasVersion) {
            boolean isCurrentVersion;
            AppDetails appDetails = doc.getAppDetails();
            if (appDetails.hasVersionString && version.equals(appDetails.versionString)) {
                isCurrentVersion = true;
            } else {
                isCurrentVersion = false;
            }
            if (!isCurrentVersion) {
                String string;
                Context context = getContext();
                if (hasDeviceName) {
                    string = context.getString(R.string.review_older_version_with_device_name, new Object[]{deviceName});
                } else {
                    string = context.getString(R.string.review_older_version);
                }
                return string;
            } else if (hasDeviceName) {
                return deviceName;
            } else {
                return null;
            }
        } else if (hasDeviceName) {
            return deviceName;
        } else {
            return null;
        }
    }

    public void setActionClickListener(OnClickListener listener) {
        this.mActionContainer.setOnClickListener(listener);
    }
}
