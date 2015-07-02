package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.widget.ImageView;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CardType;

public abstract class CreditCardImagesAnimator {
    protected CardType mCurrentType;
    protected final ImageView[] mImages;

    public abstract void animateToType(CardType cardType);

    public abstract void restoreCardType(CardType cardType);

    public CreditCardImagesAnimator(ImageView[] images) {
        this.mImages = images;
    }

    protected int findIndex(CardType type) {
        if (type == null) {
            return -1;
        }
        int length = this.mImages.length;
        for (int i = 0; i < length; i++) {
            if (PaymentUtils.equals(type, (CardType) this.mImages[i].getTag())) {
                return i;
            }
        }
        return -1;
    }
}
