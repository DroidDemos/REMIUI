package com.google.android.finsky.layout;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.android.vending.R;
import com.google.android.finsky.billing.creditcard.CreditCardType;
import java.util.Arrays;

public class CreditCardImagesAnimatorFroyo extends CreditCardImagesAnimator {
    private Animation mFadeInAnimation;
    private Animation mFadeOutAnimation;
    private boolean[] mVisible;

    public CreditCardImagesAnimatorFroyo(Context ctx, ImageView[] images, CreditCardType[] types) {
        super(images, types);
        this.mVisible = new boolean[images.length];
        Arrays.fill(this.mVisible, true);
        this.mFadeInAnimation = AnimationUtils.loadAnimation(ctx, R.anim.fade_in_from_half);
        this.mFadeOutAnimation = AnimationUtils.loadAnimation(ctx, R.anim.fade_out_to_half);
    }

    public void animateToType(CreditCardType type) {
        if (type != this.mCurrentType) {
            int index = findIndex(type);
            for (int i = 0; i < this.mImages.length; i++) {
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
}
