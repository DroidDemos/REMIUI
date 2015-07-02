package com.google.android.finsky.layout;

import android.widget.ImageView;
import com.google.android.finsky.billing.creditcard.CreditCardType;

public class CreditCardImagesAnimatorIcs extends CreditCardImagesAnimator {
    private ImageView mGeneralImage;
    private boolean mInOneCardMode;

    public CreditCardImagesAnimatorIcs(ImageView[] images, CreditCardType[] types, ImageView generalImage) {
        super(images, types);
        this.mGeneralImage = generalImage;
    }

    public void animateToType(CreditCardType type) {
        if (type != this.mCurrentType) {
            float inactiveAlpha;
            int index = findIndex(type);
            if (index != -1 || this.mInOneCardMode) {
                inactiveAlpha = 0.0f;
            } else {
                inactiveAlpha = 1.0f;
            }
            for (int i = 0; i < this.mImages.length; i++) {
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

    public void restoreCardType(CreditCardType type) {
        float inactiveAlpha;
        int index = findIndex(type);
        if (index == -1) {
            inactiveAlpha = 1.0f;
        } else {
            inactiveAlpha = 0.0f;
        }
        for (int i = 0; i < this.mImages.length; i++) {
            this.mImages[i].animate().cancel();
            if (i == index) {
                this.mImages[i].setAlpha(1.0f);
                this.mImages[i].setX((float) this.mImages[0].getLeft());
            } else {
                this.mImages[i].setAlpha(inactiveAlpha);
                this.mImages[i].setTranslationX(0.0f);
            }
        }
    }

    public void switchToOneCardMode() {
        if (!this.mInOneCardMode) {
            int currentIndex = findIndex(this.mCurrentType);
            for (int i = 0; i < this.mImages.length; i++) {
                this.mImages[i].animate().cancel();
                this.mImages[i].setX((float) this.mImages[0].getLeft());
                if (i == currentIndex) {
                    this.mImages[i].setAlpha(1.0f);
                } else {
                    this.mImages[i].setAlpha(0.0f);
                }
            }
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
