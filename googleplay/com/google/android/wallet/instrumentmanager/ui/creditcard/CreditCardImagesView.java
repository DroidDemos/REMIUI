package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.common.util.ParcelableProto;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.config.G.images;
import com.google.android.wallet.instrumentmanager.ui.common.FifeNetworkImageView;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CardType;
import java.util.ArrayList;

public class CreditCardImagesView extends RelativeLayout {
    ImageView[] mCardImages;
    CreditCardImagesAnimator mCardImagesAnimator;
    CardType mCurrentCardType;
    ImageView mGeneralCardImage;
    boolean mOneCardMode;
    private boolean mSuspendAnimations;

    public CreditCardImagesView(Context context) {
        super(context);
        this.mSuspendAnimations = true;
    }

    public CreditCardImagesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSuspendAnimations = true;
    }

    public CreditCardImagesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mSuspendAnimations = true;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mGeneralCardImage = (ImageView) findViewById(R.id.general_logo);
    }

    public void setCardTypes(CardType[] cardTypes) {
        ArrayList<ImageView> images = new ArrayList(cardTypes.length);
        int previousCardId = -1;
        int currentCardId = WalletUiUtils.generateViewId();
        int cardIconWidth = getResources().getDimensionPixelSize(R.dimen.wallet_im_credit_card_icon_width);
        int cardIconHeight = getResources().getDimensionPixelSize(R.dimen.wallet_im_credit_card_icon_height);
        for (CardType cardType : cardTypes) {
            ImageView cardImage;
            if (PaymentUtils.isEmbeddedImageUri(cardType.icon.imageUri)) {
                cardImage = new ImageView(getContext());
                cardImage.setImageResource(PaymentUtils.embeddedImageUriToDrawableResourceId(cardType.icon.imageUri));
            } else {
                cardImage = new FifeNetworkImageView(getContext());
            }
            if (VERSION.SDK_INT >= 11) {
                cardImage.setLayerType(2, null);
            }
            cardImage.setId(currentCardId);
            LayoutParams params = new LayoutParams(cardIconWidth, cardIconHeight);
            if (previousCardId == -1) {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.wallet_im_credit_card_logos_left_margin);
            } else {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.wallet_im_spacing_between_credit_card_images);
                params.addRule(1, previousCardId);
            }
            addView(cardImage, params);
            if (cardImage instanceof FifeNetworkImageView) {
                FifeNetworkImageView fifeCardImage = (FifeNetworkImageView) cardImage;
                fifeCardImage.setFadeIn(true);
                fifeCardImage.setFifeImageUrl(cardType.icon.imageUri, PaymentUtils.getImageLoader(getContext().getApplicationContext()), ((Boolean) images.useWebPForFife.get()).booleanValue());
                fifeCardImage.setErrorImageResId(R.drawable.wallet_im_card_general);
            }
            if (!TextUtils.isEmpty(cardType.icon.altText)) {
                cardImage.setContentDescription(cardType.icon.altText);
            }
            cardImage.setTag(cardType);
            images.add(cardImage);
            previousCardId = currentCardId;
            currentCardId = WalletUiUtils.generateViewId();
        }
        this.mCardImages = (ImageView[]) images.toArray(new ImageView[images.size()]);
        this.mCardImagesAnimator = createImagesAnimator(this.mCardImages);
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("parentState", super.onSaveInstanceState());
        bundle.putParcelable("cardType", ParcelableProto.forProto(this.mCurrentCardType));
        bundle.putBoolean("oneCardMode", this.mOneCardMode);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mCurrentCardType = (CardType) ParcelableProto.getProtoFromBundle(bundle, "cardType");
            this.mOneCardMode = bundle.getBoolean("oneCardMode");
            super.onRestoreInstanceState(bundle.getParcelable("parentState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mSuspendAnimations) {
            this.mSuspendAnimations = false;
            this.mCardImagesAnimator.restoreCardType(this.mCurrentCardType);
            if (this.mOneCardMode) {
                switchToOneCardMode();
            }
        }
    }

    public void switchToOneCardMode() {
        if (this.mCardImagesAnimator instanceof CreditCardImagesAnimatorIcs) {
            this.mOneCardMode = true;
            if (!this.mSuspendAnimations) {
                ((CreditCardImagesAnimatorIcs) this.mCardImagesAnimator).switchToOneCardMode();
            }
        }
    }

    CreditCardImagesAnimator createImagesAnimator(ImageView[] imageViews) {
        if (VERSION.SDK_INT >= 14) {
            return new CreditCardImagesAnimatorIcs(imageViews, this.mGeneralCardImage);
        }
        return new CreditCardImagesAnimatorGingerbread(getContext(), imageViews);
    }

    public void setCreditCardType(CardType newType) {
        this.mCurrentCardType = newType;
        if (!this.mSuspendAnimations) {
            this.mCardImagesAnimator.animateToType(newType);
        }
    }
}
