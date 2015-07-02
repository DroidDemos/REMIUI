package com.google.android.finsky.layout;

import android.widget.ImageView;
import com.google.android.finsky.billing.creditcard.CreditCardType;

public abstract class CreditCardImagesAnimator {
    protected CreditCardType mCurrentType;
    protected ImageView[] mImages;
    protected CreditCardType[] mTypes;

    public abstract void animateToType(CreditCardType creditCardType);

    public CreditCardImagesAnimator(ImageView[] images, CreditCardType[] types) {
        if (images.length == 0) {
            throw new IllegalArgumentException("images must have at least one entry");
        } else if (images.length != types.length) {
            throw new IllegalArgumentException("types must have same length as images");
        } else {
            this.mImages = images;
            this.mTypes = types;
        }
    }

    protected int findIndex(CreditCardType type) {
        for (int i = 0; i < this.mTypes.length; i++) {
            if (this.mTypes[i] == type) {
                return i;
            }
        }
        return -1;
    }
}
