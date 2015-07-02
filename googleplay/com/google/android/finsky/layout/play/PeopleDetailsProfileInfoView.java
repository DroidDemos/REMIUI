package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.IdentifiableFrameLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.StreamSpacer;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocDetails.PersonDetails;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.play.image.FifeImageView;

public class PeopleDetailsProfileInfoView extends IdentifiableFrameLayout implements OnClickListener, Recyclable, StreamSpacer {
    private final int mAvatarOverlap;
    private View mButtonContainer;
    private final int mCardInset;
    private PlayCirclesButton mCirclesButton;
    private int mCoverHeight;
    private TextView mDisplayName;
    private final int mExtraBottomMargin;
    private final int mLeadingSpacerHeight;
    private PlayStoreUiElementNode mParentNode;
    private Document mPlusDoc;
    private FifeImageView mProfileAvatarImage;
    private View mProfileBlock;
    private ProfileButton mViewProfileButton;

    public PeopleDetailsProfileInfoView(Context context) {
        this(context, null);
    }

    public PeopleDetailsProfileInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(context, 2, 0);
        Resources resources = context.getResources();
        this.mAvatarOverlap = resources.getDimensionPixelSize(R.dimen.play_profile_avatar_overlap);
        this.mExtraBottomMargin = resources.getDimensionPixelSize(R.dimen.play_card_default_elevation) * 2;
        this.mCardInset = resources.getDimensionPixelSize(R.dimen.play_card_default_inset);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mProfileAvatarImage = (FifeImageView) findViewById(R.id.avatar);
        this.mProfileBlock = findViewById(R.id.profile_block);
        this.mDisplayName = (TextView) this.mProfileBlock.findViewById(R.id.display_name);
        this.mButtonContainer = this.mProfileBlock.findViewById(R.id.button_container);
        this.mCirclesButton = (PlayCirclesButton) this.mButtonContainer.findViewById(R.id.gplus_circle_status);
        this.mViewProfileButton = (ProfileButton) this.mButtonContainer.findViewById(R.id.gplus_view_profile);
        if (VERSION.SDK_INT >= 21) {
            this.mProfileAvatarImage.setZ(this.mProfileBlock.getZ() + 1.0f);
        }
    }

    public void bind(Document document, PlayStoreUiElementNode parentNode, int contentHorizontalPadding) {
        this.mPlusDoc = document;
        this.mParentNode = parentNode;
        Image avatarImage = (Image) this.mPlusDoc.getImages(4).get(0);
        this.mProfileAvatarImage.setImage(avatarImage.imageUrl, avatarImage.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
        this.mDisplayName.setText(this.mPlusDoc.getTitle());
        this.mCirclesButton.bind(this.mPlusDoc, FinskyApp.get().getCurrentAccountName(), parentNode);
        PersonDetails personDetails = this.mPlusDoc.getPersonDetails();
        if (personDetails != null) {
            if (personDetails.personIsRequester) {
                this.mCirclesButton.setVisibility(8);
            } else {
                this.mCirclesButton.setVisibility(0);
            }
        }
        Resources res = getResources();
        this.mViewProfileButton.configure(res.getString(R.string.gplus_view_profile), R.drawable.ic_gplus, R.drawable.play_highlight_overlay_light);
        this.mViewProfileButton.setOnClickListener(this);
        this.mViewProfileButton.setContentDescription(res.getString(R.string.content_description_view_gplus_profile, new Object[]{title}));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        this.mCoverHeight = HeroGraphicView.getSpacerHeight(getContext(), width, true, 0.5625f) - this.mLeadingSpacerHeight;
        LayoutParams avatarLp = this.mProfileAvatarImage.getLayoutParams();
        this.mProfileAvatarImage.measure(MeasureSpec.makeMeasureSpec(avatarLp.width, 1073741824), MeasureSpec.makeMeasureSpec(avatarLp.height, 1073741824));
        MarginLayoutParams profileBlockLp = (MarginLayoutParams) this.mProfileBlock.getLayoutParams();
        this.mProfileBlock.measure(MeasureSpec.makeMeasureSpec(width - ((profileBlockLp.leftMargin + profileBlockLp.rightMargin) - (this.mCardInset * 2)), 1073741824), 0);
        setMeasuredDimension(width, (this.mCoverHeight + this.mProfileBlock.getMeasuredHeight()) + this.mExtraBottomMargin);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = getWidth();
        int avatarHeight = this.mProfileAvatarImage.getMeasuredHeight();
        int avatarWidth = this.mProfileAvatarImage.getMeasuredWidth();
        int avatarTop = (this.mCoverHeight - avatarHeight) + this.mAvatarOverlap;
        int avatarLeft = (width - avatarWidth) / 2;
        this.mProfileAvatarImage.layout(avatarLeft, avatarTop, avatarLeft + avatarWidth, avatarTop + avatarHeight);
        int profileBlockTop = this.mCoverHeight - this.mCardInset;
        MarginLayoutParams profileBlockLp = (MarginLayoutParams) this.mProfileBlock.getLayoutParams();
        this.mProfileBlock.layout(profileBlockLp.leftMargin - this.mCardInset, profileBlockTop, profileBlockLp.leftMargin + this.mProfileBlock.getMeasuredWidth(), this.mProfileBlock.getMeasuredHeight() + profileBlockTop);
    }

    public void onClick(View view) {
        if (view.getContext() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) view.getContext();
            if (view == this.mViewProfileButton && this.mPlusDoc != null) {
                FinskyApp.get().getEventLogger().logClickEvent(281, null, this.mParentNode);
                launchGPlusProfileView(mainActivity, mainActivity.getNavigationManager(), this.mPlusDoc.getBackendDocId());
            }
        }
    }

    private void launchGPlusProfileView(Context context, NavigationManager navigationManager, String obfusGaiaId) {
        Intent intent = IntentUtils.buildConsumptionAppViewItemIntent(context, this.mPlusDoc, FinskyApp.get().getCurrentAccountName());
        if (!IntentUtils.isConsumptionAppInstalled(context.getPackageManager(), this.mPlusDoc.getBackend()) || intent == null) {
            navigationManager.showAppNeededDialog(this.mPlusDoc.getBackend());
        } else {
            context.startActivity(intent);
        }
    }

    public void onRecycle() {
        this.mProfileAvatarImage.clearImage();
    }
}
