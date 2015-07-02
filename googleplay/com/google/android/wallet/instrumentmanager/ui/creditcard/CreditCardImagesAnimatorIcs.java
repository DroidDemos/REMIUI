package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.widget.ImageView;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CardType;

public class CreditCardImagesAnimatorIcs extends CreditCardImagesAnimator {
    private final ImageView mGeneralImage;
    private boolean mInOneCardMode;

    public CreditCardImagesAnimatorIcs(ImageView[] images, ImageView generalImage) {
        super(images);
        this.mGeneralImage = generalImage;
    }

    public void animateToType(CardType type) {
        if (!PaymentUtils.equals(type, this.mCurrentType)) {
            float inactiveAlpha;
            int index = findIndex(type);
            if (index != -1 || this.mInOneCardMode) {
                inactiveAlpha = 0.0f;
            } else {
                inactiveAlpha = 1.0f;
            }
            int length = this.mImages.length;
            for (int i = 0; i < length; i++) {
                if (i == index) {
                    this.mImages[i].animate().alpha(1.0f);
                    if (!this.mInOneCardMode) {
                        this.mImages[i].animate().x((float) this.mImages[0].getLeft());
                    }
                } else {
                    this.mImages[i].animate().alpha(inactiveAlpha);
                    if (!this.mInOneCardMode) {
                        this.mImages[i].animate().translationX(0.0f);
                    }
                }
            }
            if (this.mInOneCardMode) {
                if (index == -1) {
                    this.mGeneralImage.animate().alpha(1.0f);
                } else {
                    this.mGeneralImage.animate().alpha(0.0f);
                }
            }
            this.mCurrentType = type;
        }
    }

    public void restoreCardType(CardType type) {
        float inactiveAlpha;
        int index = findIndex(type);
        if (index == -1) {
            inactiveAlpha = 1.0f;
        } else {
            inactiveAlpha = 0.0f;
        }
        int length = this.mImages.length;
        for (int i = 0; i < length; i++) {
            this.mImages[i].animate().cancel();
            if (i == index) {
                this.mImages[i].setAlpha(1.0f);
                this.mImages[i].setX((float) this.mImages[0].getLeft());
            } else {
                this.mImages[i].setAlpha(inactiveAlpha);
                this.mImages[i].setTranslationX(0.0f);
            }
        }
        this.mCurrentType = type;
    }

    public void switchToOneCardMode() {
        if (!this.mInOneCardMode) {
            int currentIndex = findIndex(this.mCurrentType);
            int length = this.mImages.length;
            for (int i = 0; i < length; i++) {
                this.mImages[i].animate().cancel();
                this.mImages[i].setX((float) this.mImages[0].getLeft());
                if (i == currentIndex) {
                    this.mImages[i].setAlpha(1.0f);
                } else {
                    this.mImages[i].setAlpha(0.0f);
                }
            }
            this.mGeneralImage.animate().cancel();
            this.mGeneralImage.setX((float) this.mImages[0].getLeft());
            this.mGeneralImage.setVisibility(0);
            if (currentIndex == -1) {
                this.mGeneralImage.setAlpha(1.0f);
            } else {
                this.mGeneralImage.setAlpha(0.0f);
            }
        }
        this.mInOneCardMode = true;
    }
}
