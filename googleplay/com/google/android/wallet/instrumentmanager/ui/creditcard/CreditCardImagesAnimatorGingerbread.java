package com.google.android.wallet.instrumentmanager.ui.creditcard;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CardType;
import java.util.Arrays;

public class CreditCardImagesAnimatorGingerbread extends CreditCardImagesAnimator {
    private final Animation mFadeInAnimation;
    private final Animation mFadeOutAnimation;
    private final boolean[] mVisible;

    public CreditCardImagesAnimatorGingerbread(Context ctx, ImageView[] images) {
        super(images);
        this.mVisible = new boolean[images.length];
        Arrays.fill(this.mVisible, true);
        this.mFadeInAnimation = AnimationUtils.loadAnimation(ctx, R.anim.wallet_im_fade_in_from_half);
        this.mFadeOutAnimation = AnimationUtils.loadAnimation(ctx, R.anim.wallet_im_fade_out_to_half);
    }

    public void animateToType(CardType type) {
        if (!PaymentUtils.equals(type, this.mCurrentType)) {
            int index = findIndex(type);
            int length = this.mImages.length;
            for (int i = 0; i < length; i++) {
                if (i == index || index == -1) {
                    if (!this.mVisible[i]) {
                        this.mImages[i].startAnimation(this.mFadeInAnimation);
                    }
                    this.mVisible[i] = true;
                } else {
                    if (this.mVisible[i]) {
                        this.mImages[i].startAnimation(this.mFadeOutAnimation);
                    }
                    this.mVisible[i] = false;
                }
            }
            this.mCurrentType = type;
        }
    }

    public void restoreCardType(CardType type) {
        animateToType(type);
    }
}
