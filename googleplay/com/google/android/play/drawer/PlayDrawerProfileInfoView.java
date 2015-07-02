package com.google.android.play.drawer;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.play.R;
import com.google.android.play.image.AvatarCropTransformation;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;
import com.google.android.play.utils.DocV2Utils;
import java.util.Map;

class PlayDrawerProfileInfoView extends FrameLayout implements OnClickListener, OnLoadedListener {
    private View mAccountInfoContainer;
    private boolean mAccountListEnabled;
    private boolean mAccountListExpanded;
    private TextView mAccountName;
    private TextView mDisplayName;
    private OnAvatarClickedListener mOnAvatarClickedListener;
    private Account mProfileAccount;
    private FifeImageView mProfileAvatarImage;
    private FifeImageView mProfileCoverImage;
    private Account mSecondaryAccountLeft;
    private Account mSecondaryAccountRight;
    private FifeImageView mSecondaryAvatarImageLeft;
    private FifeImageView mSecondaryAvatarImageRight;
    private ImageView mToggleAccountListButton;

    public interface OnAvatarClickedListener {
        void onAvatarClicked(Account account);
    }

    public PlayDrawerProfileInfoView(Context context) {
        this(context, null);
    }

    public PlayDrawerProfileInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mProfileCoverImage = (FifeImageView) findViewById(R.id.cover_photo);
        this.mProfileAvatarImage = (FifeImageView) findViewById(R.id.avatar);
        this.mSecondaryAvatarImageLeft = (FifeImageView) findViewById(R.id.secondary_avatar_left);
        this.mSecondaryAvatarImageRight = (FifeImageView) findViewById(R.id.secondary_avatar_right);
        this.mDisplayName = (TextView) findViewById(R.id.display_name);
        this.mAccountName = (TextView) findViewById(R.id.account_name);
        this.mToggleAccountListButton = (ImageView) findViewById(R.id.toggle_account_list_button);
        this.mAccountInfoContainer = findViewById(R.id.account_info_container);
        this.mProfileAvatarImage.setOnClickListener(this);
        this.mSecondaryAvatarImageLeft.setOnClickListener(this);
        this.mSecondaryAvatarImageRight.setOnClickListener(this);
    }

    public void setAccountListEnabled(boolean accountListEnabled) {
        if (this.mAccountListEnabled != accountListEnabled) {
            this.mAccountListEnabled = accountListEnabled;
            bindAccountToggler();
            if (!accountListEnabled) {
                setAccountListExpanded(false);
            }
        }
    }

    public void setAccountListExpanded(boolean accountListExpanded) {
        if (this.mAccountListExpanded != accountListExpanded) {
            this.mAccountListExpanded = accountListExpanded;
            bindAccountToggler();
        }
    }

    public void configure(Account currentAccount, Account[] nonCurrentAccounts, Map<String, DocV2> accountDocV2s, BitmapLoader bitmapLoader) {
        this.mProfileAccount = currentAccount;
        this.mSecondaryAccountRight = nonCurrentAccounts.length > 0 ? nonCurrentAccounts[0] : null;
        this.mSecondaryAccountLeft = nonCurrentAccounts.length > 1 ? nonCurrentAccounts[1] : null;
        DocV2 profileDocV2 = (DocV2) accountDocV2s.get(this.mProfileAccount.name);
        DocV2 secondaryAccountRightDocV2 = this.mSecondaryAccountRight != null ? (DocV2) accountDocV2s.get(this.mSecondaryAccountRight.name) : null;
        DocV2 secondaryAccountLeftDocV2 = this.mSecondaryAccountLeft != null ? (DocV2) accountDocV2s.get(this.mSecondaryAccountLeft.name) : null;
        setBackgroundDrawable(new ColorDrawable(R.color.play_main_background));
        if (profileDocV2 == null) {
            this.mProfileCoverImage.setImageResource(R.drawable.bg_default_profile_art);
            this.mDisplayName.setText(currentAccount.name);
            this.mAccountName.setVisibility(8);
        } else {
            this.mProfileCoverImage.clearCachedState();
            Image selectedProfileCoverImage = DocV2Utils.getFirstImageOfType(profileDocV2, 15);
            String selectedProfileDisplayName = profileDocV2.title;
            this.mProfileCoverImage.setOnLoadedListener(this);
            this.mProfileCoverImage.setImage(selectedProfileCoverImage.imageUrl, selectedProfileCoverImage.supportsFifeUrlOptions, bitmapLoader);
            if (!TextUtils.isEmpty(selectedProfileDisplayName)) {
                this.mDisplayName.setText(selectedProfileDisplayName);
            }
            this.mAccountName.setText(currentAccount.name);
            this.mAccountName.setVisibility(0);
        }
        configureAvatar(this.mProfileAvatarImage, true, false, currentAccount.name, profileDocV2, bitmapLoader);
        boolean hasSecondaryAccountLeft = this.mSecondaryAccountLeft != null;
        configureAvatar(this.mSecondaryAvatarImageLeft, hasSecondaryAccountLeft, true, hasSecondaryAccountLeft ? this.mSecondaryAccountLeft.name : null, secondaryAccountLeftDocV2, bitmapLoader);
        boolean hasSecondaryAccountRight = this.mSecondaryAccountRight != null;
        configureAvatar(this.mSecondaryAvatarImageRight, hasSecondaryAccountRight, true, hasSecondaryAccountRight ? this.mSecondaryAccountRight.name : null, secondaryAccountRightDocV2, bitmapLoader);
    }

    private void configureAvatar(FifeImageView avatarImage, boolean showAvatar, boolean setContentDescription, String accountName, DocV2 accountDocV2, BitmapLoader bitmapLoader) {
        if (showAvatar) {
            String contentDescription;
            avatarImage.setVisibility(0);
            if (setContentDescription) {
                contentDescription = getResources().getString(R.string.play_drawer_content_description_switch_account, new Object[]{accountName});
            } else {
                contentDescription = "";
            }
            avatarImage.setContentDescription(contentDescription);
            if (accountDocV2 != null) {
                Image image = DocV2Utils.getFirstImageOfType(accountDocV2, 4);
                avatarImage.clearCachedState();
                avatarImage.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
                return;
            }
            avatarImage.setImageBitmap(getDefaultAvatar());
            return;
        }
        avatarImage.setVisibility(8);
    }

    public void setCurrentAvatarClickable(boolean clickable) {
        this.mProfileAvatarImage.setEnabled(clickable);
        ViewCompat.setImportantForAccessibility(this.mProfileAvatarImage, clickable ? 1 : 2);
    }

    private Bitmap getDefaultAvatar() {
        Bitmap avatarBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_profile_none);
        return AvatarCropTransformation.getNoRingAvatarCrop(getResources()).transform(avatarBitmap, avatarBitmap.getWidth(), avatarBitmap.getHeight());
    }

    public void setOnAvatarClickListener(OnAvatarClickedListener onAvatarClickedListener) {
        this.mOnAvatarClickedListener = onAvatarClickedListener;
    }

    public void onClick(View view) {
        if (this.mOnAvatarClickedListener != null) {
            if (view == this.mProfileAvatarImage) {
                this.mOnAvatarClickedListener.onAvatarClicked(this.mProfileAccount);
            } else if (view == this.mSecondaryAvatarImageLeft) {
                this.mOnAvatarClickedListener.onAvatarClicked(this.mSecondaryAccountLeft);
            } else if (view == this.mSecondaryAvatarImageRight) {
                this.mOnAvatarClickedListener.onAvatarClicked(this.mSecondaryAccountRight);
            }
        }
    }

    private void bindAccountToggler() {
        Resources res = getResources();
        if (this.mAccountListExpanded) {
            this.mToggleAccountListButton.setVisibility(0);
            this.mToggleAccountListButton.setImageResource(R.drawable.ic_up_white_16);
            this.mToggleAccountListButton.setContentDescription(res.getString(R.string.play_drawer_content_description_hide_account_list_button));
        } else if (this.mAccountListEnabled) {
            this.mToggleAccountListButton.setVisibility(0);
            this.mToggleAccountListButton.setImageResource(R.drawable.ic_down_white_16);
            this.mToggleAccountListButton.setContentDescription(res.getString(R.string.play_drawer_content_description_show_account_list_button));
        } else {
            this.mToggleAccountListButton.setVisibility(8);
        }
    }

    public void setAccountTogglerListener(OnClickListener accountTogglerListener) {
        int paddingLeft = this.mAccountInfoContainer.getPaddingLeft();
        int paddingRight = this.mAccountInfoContainer.getPaddingRight();
        int paddingTop = this.mAccountInfoContainer.getPaddingTop();
        int paddingBottom = this.mAccountInfoContainer.getPaddingBottom();
        if (accountTogglerListener != null) {
            this.mAccountInfoContainer.setBackgroundResource(R.drawable.play_highlight_overlay_dark);
        } else {
            this.mAccountInfoContainer.setBackgroundResource(0);
        }
        this.mAccountInfoContainer.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        this.mAccountInfoContainer.setOnClickListener(accountTogglerListener);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public void onLoaded(FifeImageView coverImageView, Bitmap bitmap) {
    }

    public void onLoadedAndFadedIn(FifeImageView coverImageView) {
        setBackgroundDrawable(null);
    }
}
