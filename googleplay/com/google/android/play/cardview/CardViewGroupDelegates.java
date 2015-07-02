package com.google.android.play.cardview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.google.android.play.R;

public class CardViewGroupDelegates {
    public static final CardViewGroupDelegate IMPL;
    public static final CardViewGroupDelegate NO_CARD_BG_IMPL;

    private static class CardViewGroupEclairMr1 implements CardViewGroupDelegate {
        private CardViewGroupEclairMr1() {
        }

        public void initialize(View cardView, Context context, AttributeSet attrs, int defStyle) {
            TypedArray array = getStyledAttrs(context, attrs, defStyle);
            cardView.setBackgroundDrawable(new RoundRectDrawableWithShadow(context.getResources(), getBackgroundColor(array), getRadius(array), getElevation(array), (float) getInset(array)));
            array.recycle();
        }

        protected TypedArray getStyledAttrs(Context context, AttributeSet attrs, int defStyle) {
            return context.obtainStyledAttributes(attrs, R.styleable.PlayCardViewGroup, defStyle, 0);
        }

        protected ColorStateList getBackgroundColor(TypedArray array) {
            return array.getColorStateList(R.styleable.PlayCardViewGroup_playCardBackgroundColor);
        }

        protected float getRadius(TypedArray array) {
            return array.getDimension(R.styleable.PlayCardViewGroup_playCardCornerRadius, 0.0f);
        }

        protected float getElevation(TypedArray array) {
            return array.getDimension(R.styleable.PlayCardViewGroup_playCardElevation, 0.0f);
        }

        protected int getInset(TypedArray array) {
            return array.getDimensionPixelSize(R.styleable.PlayCardViewGroup_playCardInset, 0);
        }

        public void setBackgroundColor(View cardView, int color) {
            Drawable background = cardView.getBackground();
            if (background instanceof CardViewBackgroundDrawable) {
                ((CardViewBackgroundDrawable) background).setBackgroundColor(color);
            } else {
                Log.w("CardViewGroupDelegates", "Unable to set background color. CardView is not using a CardViewBackgroundDrawable");
            }
        }

        public void setBackgroundResource(View cardView, int resId) {
            if (resId != 0) {
                Resources resources = cardView.getResources();
                Drawable background = cardView.getBackground();
                if (background instanceof CardViewBackgroundDrawable) {
                    try {
                        ((CardViewBackgroundDrawable) background).setBackgroundColorStateList(resources.getColorStateList(resId));
                        return;
                    } catch (NotFoundException ex) {
                        Log.w("CardViewGroupDelegates", "Unable to set background - ColorStateList not found.", ex);
                        return;
                    }
                }
                Log.w("CardViewGroupDelegates", "Unable to set background. CardView is not using a CardViewBackgroundDrawable.");
            }
        }

        public void setCardElevation(View cardView, float elevation) {
            Drawable background = cardView.getBackground();
            if (background instanceof RoundRectDrawableWithShadow) {
                ((RoundRectDrawableWithShadow) background).setShadowSize(elevation);
            }
        }
    }

    private static class CardViewGroupL extends CardViewGroupEclairMr1 {
        private CardViewGroupL() {
            super();
        }

        public void initialize(View cardView, Context context, AttributeSet attrs, int defStyle) {
            TypedArray array = getStyledAttrs(context, attrs, defStyle);
            Drawable cardBackground = new RoundRectDrawable(getBackgroundColor(array), getRadius(array), (float) getInset(array));
            cardView.setClipToOutline(true);
            cardView.setElevation(getElevation(array));
            cardView.setBackground(cardBackground);
            cardView.setClipToOutline(getClipToOutline(array));
            array.recycle();
        }

        protected boolean getClipToOutline(TypedArray array) {
            return array.getBoolean(R.styleable.PlayCardViewGroup_playCardClipToOutline, true);
        }

        public void setCardElevation(View cardView, float elevation) {
            cardView.setElevation(elevation);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            IMPL = new CardViewGroupL();
        } else {
            IMPL = new CardViewGroupEclairMr1();
        }
        NO_CARD_BG_IMPL = new CardViewGroupDelegate() {
            public void initialize(View cardView, Context context, AttributeSet attrs, int defStyle) {
            }

            public void setBackgroundColor(View cardView, int color) {
            }

            public void setBackgroundResource(View cardView, int resId) {
            }

            public void setCardElevation(View cardView, float elevation) {
            }
        };
    }
}
