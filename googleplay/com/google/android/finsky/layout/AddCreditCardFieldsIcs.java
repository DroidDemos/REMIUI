package com.google.android.finsky.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.billing.creditcard.CreditCardType;

public class AddCreditCardFieldsIcs extends AddCreditCardFields implements OnClickListener {
    private static String KEY_PARENT_STATE;
    private static String KEY_STATE;
    private ImageButton mEditNumberButton;
    private ImageView mGeneralLogo;
    private TextView mNumberConcealed;
    private boolean mRestorePositionsOnLayout;
    private State mState;

    private enum State {
        ENTERING_NUMBER,
        ENTERING_MONTH_YEAR,
        ENTERING_CVC,
        ENTERING_ADDRESS_CONTRACTED_CARD,
        ENTERING_ADDRESS_EXPANDED_CARD
    }

    static {
        KEY_PARENT_STATE = "parent_instance_state";
        KEY_STATE = "state";
    }

    public AddCreditCardFieldsIcs(Context context) {
        super(context);
        this.mState = State.ENTERING_NUMBER;
    }

    public AddCreditCardFieldsIcs(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mState = State.ENTERING_NUMBER;
    }

    protected void onFinishInflate() {
        this.mNumberConcealed = (TextView) findViewById(R.id.cc_box_concealed);
        this.mEditNumberButton = (ImageButton) findViewById(R.id.edit_number_button);
        this.mEditNumberButton.setOnClickListener(this);
        this.mGeneralLogo = (ImageView) findViewById(R.id.general_logo);
        super.onFinishInflate();
        this.mBillingAddress.setOnHeightOffsetChangedListener(new OnHeightOffsetChangedListener() {
            public void onHeightOffsetChanged(float heightOffset) {
                AddCreditCardFieldsIcs.this.mPrivacyFooter.setTranslationY((AddCreditCardFieldsIcs.this.mBillingAddress.getY() - ((float) AddCreditCardFieldsIcs.this.mBillingAddress.getTop())) + heightOffset);
            }
        });
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PARENT_STATE, super.onSaveInstanceState());
        bundle.putInt(KEY_STATE, this.mState.ordinal());
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mState = State.values()[bundle.getInt(KEY_STATE)];
            restoreVisibilities(this.mState);
            this.mRestorePositionsOnLayout = true;
            super.onRestoreInstanceState(bundle.getParcelable(KEY_PARENT_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mRestorePositionsOnLayout) {
            this.mRestorePositionsOnLayout = false;
            restorePositions(this.mState);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mState.ordinal() <= State.ENTERING_CVC.ordinal()) {
            setMeasuredDimension(getMeasuredWidth(), (getMeasuredHeight() - this.mBillingAddress.getMeasuredHeight()) - this.mPrivacyFooter.getMeasuredHeight());
        } else if (this.mState == State.ENTERING_ADDRESS_CONTRACTED_CARD || this.mState == State.ENTERING_ADDRESS_EXPANDED_CARD) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + getBillingAddressTranslationY());
        }
    }

    private void restoreVisibilities(State state) {
        if (state == State.ENTERING_MONTH_YEAR || state == State.ENTERING_CVC || state == State.ENTERING_ADDRESS_EXPANDED_CARD) {
            this.mMonthField.setVisibility(0);
            this.mYearField.setVisibility(0);
            this.mCvcField.setVisibility(0);
            this.mMonthYearSeparator.setVisibility(0);
        }
        if (state == State.ENTERING_CVC || state == State.ENTERING_ADDRESS_EXPANDED_CARD) {
            this.mCvcImage.setVisibility(0);
        }
        if (state.ordinal() >= State.ENTERING_ADDRESS_CONTRACTED_CARD.ordinal()) {
            this.mBillingAddress.setVisibility(0);
            this.mPrivacyFooter.setVisibility(0);
        }
        if (state == State.ENTERING_ADDRESS_CONTRACTED_CARD) {
            this.mEditNumberButton.setVisibility(0);
            this.mNumberConcealed.setVisibility(0);
            this.mNumberField.setVisibility(4);
            this.mNumberField.setAlpha(0.0f);
            this.mMonthField.setAlpha(0.0f);
            this.mMonthYearSeparator.setAlpha(0.0f);
            this.mYearField.setAlpha(0.0f);
            this.mCvcImage.setAlpha(0.0f);
            this.mCvcField.setAlpha(0.0f);
        } else if (state == State.ENTERING_ADDRESS_EXPANDED_CARD) {
            this.mEditNumberButton.setVisibility(4);
            this.mNumberConcealed.setVisibility(4);
            indentNumberField();
        }
    }

    private void restorePositions(State state) {
        int numberTranslationY = getNumberTranslationY();
        int monthYearCvcTranslationY = getMonthYearCvcTranslationY();
        int billingTranslationY = getBillingAddressTranslationY();
        this.mNumberField.setTranslationY((float) numberTranslationY);
        this.mMonthField.setTranslationY((float) monthYearCvcTranslationY);
        this.mMonthYearSeparator.setTranslationY((float) monthYearCvcTranslationY);
        this.mYearField.setTranslationY((float) monthYearCvcTranslationY);
        this.mCvcImage.setTranslationY((float) monthYearCvcTranslationY);
        this.mCvcField.setTranslationY((float) monthYearCvcTranslationY);
        this.mNumberConcealed.setTranslationY((float) numberTranslationY);
        this.mEditNumberButton.setTranslationY((float) numberTranslationY);
        this.mBillingAddress.setTranslationY((float) billingTranslationY);
        this.mPrivacyFooter.setTranslationY((float) billingTranslationY);
        if (state.ordinal() >= State.ENTERING_ADDRESS_CONTRACTED_CARD.ordinal()) {
            ((CreditCardImagesAnimatorIcs) this.mImagesAnimator).switchToOneCardMode();
        } else {
            ((CreditCardImagesAnimatorIcs) this.mImagesAnimator).restoreCardType(this.mCurrentCardType);
        }
    }

    protected CreditCardImagesAnimator createCreditCardImagesAnimator() {
        return new CreditCardImagesAnimatorIcs(this.mCreditCardImages, CREDIT_CARD_IMAGES_TYPE_ORDER, this.mGeneralLogo);
    }

    public void onNumberEntered() {
        if (this.mState == State.ENTERING_NUMBER) {
            moveToEnteringMonthYearState();
        }
    }

    private void moveToEnteringMonthYearState() {
        this.mState = State.ENTERING_MONTH_YEAR;
        int deltaY = this.mMonthField.getTop() - this.mNumberField.getTop();
        setupNumberEnteredAnimation(this.mMonthField, deltaY);
        setupNumberEnteredAnimation(this.mMonthYearSeparator, deltaY);
        setupNumberEnteredAnimation(this.mYearField, deltaY);
        setupNumberEnteredAnimation(this.mCvcField, deltaY);
        this.mNumberField.setNextFocusDownId(R.id.expiration_date_entry_1st);
    }

    private void setAnimationDelay(long startDelay) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).animate().setStartDelay(startDelay);
        }
    }

    private void setupNumberEnteredAnimation(View v, int deltaY) {
        v.setVisibility(0);
        v.setTranslationY((float) (-deltaY));
        v.setAlpha(0.0f);
        v.animate().alpha(1.0f).translationY(0.0f);
    }

    public void onCvcFocused() {
        if (this.mState == State.ENTERING_MONTH_YEAR) {
            moveToEnteringCvcState();
        }
    }

    private void moveToEnteringCvcState() {
        this.mState = State.ENTERING_CVC;
        this.mCvcImage.setVisibility(0);
        this.mCvcImage.setAlpha(0.0f);
        this.mCvcImage.animate().alpha(1.0f);
    }

    public void onCvcEntered() {
        if (this.mState == State.ENTERING_CVC) {
            moveToEnteringAddressContractedCardState();
        }
    }

    protected void updateIconsFromTheme(boolean isLightTheme) {
        this.mEditNumberButton.setImageResource(isLightTheme ? R.drawable.ic_more_arrow_down : R.drawable.ic_more_arrow_down_dark);
    }

    public boolean expandFields() {
        if (this.mState != State.ENTERING_ADDRESS_CONTRACTED_CARD) {
            return false;
        }
        moveToEnteringAddressExpandedCardState();
        return true;
    }

    private void moveToEnteringAddressContractedCardState() {
        this.mState = State.ENTERING_ADDRESS_CONTRACTED_CARD;
        setAnimationDelay(150);
        int numberFieldTranslationY = getNumberTranslationY();
        int monthYearCvcTranslationY = getMonthYearCvcTranslationY();
        animateToTranslationFadeOut(this.mNumberField, numberFieldTranslationY);
        animateToTranslationFadeOut(this.mMonthField, monthYearCvcTranslationY);
        animateToTranslationFadeOut(this.mMonthYearSeparator, monthYearCvcTranslationY);
        animateToTranslationFadeOut(this.mYearField, monthYearCvcTranslationY);
        animateToTranslationFadeOut(this.mCvcImage, monthYearCvcTranslationY);
        animateToTranslationFadeOut(this.mCvcField, monthYearCvcTranslationY);
        this.mNumberConcealed.setVisibility(0);
        this.mNumberConcealed.setText(concealNumber(this.mNumberField.getText().toString()));
        this.mNumberConcealed.setAlpha(0.0f);
        this.mNumberConcealed.animate().translationY((float) numberFieldTranslationY).alpha(1.0f);
        this.mEditNumberButton.setVisibility(0);
        this.mEditNumberButton.setAlpha(0.0f);
        this.mEditNumberButton.animate().translationY((float) numberFieldTranslationY).alpha(1.0f);
        int billingAddressTranslationY = getBillingAddressTranslationY();
        this.mBillingAddress.setVisibility(0);
        this.mBillingAddress.setAlpha(0.0f);
        this.mBillingAddress.animate().alpha(1.0f).translationY((float) billingAddressTranslationY);
        this.mPrivacyFooter.setVisibility(0);
        this.mPrivacyFooter.setAlpha(0.0f);
        this.mPrivacyFooter.animate().alpha(1.0f).translationY((float) billingAddressTranslationY);
        ((CreditCardImagesAnimatorIcs) this.mImagesAnimator).switchToOneCardMode();
        this.mCvcField.setNextFocusDownId(R.id.name_entry);
        this.mBillingAddress.animate().setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                AddCreditCardFields.focusNext(AddCreditCardFieldsIcs.this.mCvcField);
                AddCreditCardFieldsIcs.this.mBillingAddress.animate().setListener(null);
            }
        });
        onAllFieldsVisible();
        requestLayout();
    }

    public void onClick(View v) {
        if (v == this.mEditNumberButton && this.mState == State.ENTERING_ADDRESS_CONTRACTED_CARD) {
            moveToEnteringAddressExpandedCardState();
        }
    }

    private void moveToEnteringAddressExpandedCardState() {
        this.mState = State.ENTERING_ADDRESS_EXPANDED_CARD;
        setAnimationDelay(150);
        this.mNumberConcealed.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                AddCreditCardFieldsIcs.this.mNumberConcealed.setVisibility(4);
                AddCreditCardFieldsIcs.this.mNumberConcealed.animate().setListener(null);
            }
        });
        indentNumberField();
        this.mNumberField.setVisibility(0);
        this.mNumberField.animate().alpha(1.0f);
        this.mEditNumberButton.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                AddCreditCardFieldsIcs.this.mEditNumberButton.setVisibility(4);
                AddCreditCardFieldsIcs.this.mEditNumberButton.animate().setListener(null);
            }
        });
        int monthYearCvcTranslationY = getMonthYearCvcTranslationY();
        animateToTranslationFadeIn(this.mMonthField, monthYearCvcTranslationY);
        animateToTranslationFadeIn(this.mMonthYearSeparator, monthYearCvcTranslationY);
        animateToTranslationFadeIn(this.mYearField, monthYearCvcTranslationY);
        animateToTranslationFadeIn(this.mCvcField, monthYearCvcTranslationY);
        animateToTranslationFadeIn(this.mCvcImage, monthYearCvcTranslationY);
        int billingAddressTranslationY = getBillingAddressTranslationY();
        this.mBillingAddress.animate().translationY((float) billingAddressTranslationY);
        this.mPrivacyFooter.animate().translationY((float) billingAddressTranslationY);
        requestLayout();
    }

    private String concealNumber(String number) {
        CreditCardType type = this.mCurrentCardType;
        if (type == null) {
            type = CreditCardType.MC;
        }
        return type.concealNumber(CreditCardType.normalizeNumber(number));
    }

    private int getNumberTranslationY() {
        if (this.mState.ordinal() >= State.ENTERING_ADDRESS_CONTRACTED_CARD.ordinal()) {
            return -this.mNumberField.getMeasuredHeight();
        }
        return 0;
    }

    private int getMonthYearCvcTranslationY() {
        if (this.mState == State.ENTERING_ADDRESS_CONTRACTED_CARD) {
            return (-this.mMonthField.getMeasuredHeight()) - this.mNumberField.getMeasuredHeight();
        }
        if (this.mState == State.ENTERING_ADDRESS_EXPANDED_CARD) {
            return -this.mNumberField.getMeasuredHeight();
        }
        return 0;
    }

    private int getBillingAddressTranslationY() {
        return getMonthYearCvcTranslationY();
    }

    private void indentNumberField() {
        ((LayoutParams) this.mNumberField.getLayoutParams()).leftMargin = getResources().getDimensionPixelSize(R.dimen.credit_card_number_collapsed_left_padding);
        this.mNumberField.setLayoutParams(this.mNumberField.getLayoutParams());
    }

    private void animateToTranslationFadeIn(View v, int translationY) {
        v.setVisibility(0);
        v.animate().alpha(1.0f).translationY((float) translationY);
    }

    private void animateToTranslationFadeOut(final View v, int translationY) {
        v.animate().translationY((float) translationY).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(4);
                v.animate().setListener(null);
            }
        });
    }
}
