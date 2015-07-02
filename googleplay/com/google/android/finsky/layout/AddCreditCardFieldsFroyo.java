package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.android.vending.R;

public class AddCreditCardFieldsFroyo extends AddCreditCardFields {
    private State mState;

    private enum State {
        ENTERING_NUMBER,
        ENTERING_MONTH_YEAR_CVC,
        ENTERING_ADDRESS
    }

    public AddCreditCardFieldsFroyo(Context context) {
        super(context);
        this.mState = State.ENTERING_NUMBER;
    }

    public AddCreditCardFieldsFroyo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mState = State.ENTERING_NUMBER;
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("parent_instance_state", super.onSaveInstanceState());
        bundle.putInt("state", this.mState.ordinal());
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mState = State.values()[bundle.getInt("state")];
            restoreVisibilites(this.mState);
            super.onRestoreInstanceState(bundle.getParcelable("parent_instance_state"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private void restoreVisibilites(State state) {
        if (state.ordinal() >= State.ENTERING_MONTH_YEAR_CVC.ordinal()) {
            this.mMonthField.setVisibility(0);
            this.mMonthYearSeparator.setVisibility(0);
            this.mYearField.setVisibility(0);
            this.mCvcImage.setVisibility(0);
            this.mCvcField.setVisibility(0);
        }
        if (state.ordinal() >= State.ENTERING_ADDRESS.ordinal()) {
            this.mBillingAddress.setVisibility(0);
            this.mPrivacyFooter.setVisibility(0);
        }
    }

    protected CreditCardImagesAnimator createCreditCardImagesAnimator() {
        return new CreditCardImagesAnimatorFroyo(getContext(), this.mCreditCardImages, CREDIT_CARD_IMAGES_TYPE_ORDER);
    }

    public void onNumberEntered() {
        if (this.mState == State.ENTERING_NUMBER) {
            this.mState = State.ENTERING_MONTH_YEAR_CVC;
            fadeIn(this.mMonthField);
            fadeIn(this.mMonthYearSeparator);
            fadeIn(this.mYearField);
            fadeIn(this.mCvcImage);
            fadeIn(this.mCvcField);
            this.mNumberField.setNextFocusDownId(R.id.expiration_date_entry_1st);
        }
    }

    public void onCvcEntered() {
        if (this.mState == State.ENTERING_MONTH_YEAR_CVC) {
            this.mState = State.ENTERING_ADDRESS;
            fadeIn(this.mBillingAddress);
            fadeIn(this.mPrivacyFooter);
            this.mCvcField.setNextFocusDownId(R.id.name_entry);
            AddCreditCardFields.focusNext(this.mCvcField);
            onAllFieldsVisible();
        }
    }

    public void onCvcFocused() {
    }

    protected void updateIconsFromTheme(boolean isLightTheme) {
    }

    public boolean expandFields() {
        return false;
    }

    private void fadeIn(View v) {
        v.setVisibility(0);
        v.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.play_fade_in));
    }
}
