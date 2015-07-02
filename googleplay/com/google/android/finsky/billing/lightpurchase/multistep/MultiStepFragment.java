package com.google.android.finsky.billing.lightpurchase.multistep;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.layout.IconButtonGroup;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.setup.SetupWizardNavBar;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.UiUtils;

public abstract class MultiStepFragment extends Fragment {
    protected Account mAccount;
    private boolean mAnimateContinueButtonBar;
    private boolean mButtonBarVisible;
    protected View mContinueButton;
    private View mContinueButtonBar;
    protected StepFragment<?> mCurrentFragment;
    private FinskyEventLog mEventLog;
    private View mFragmentContainer;
    private boolean mIsLoading;
    protected View mMainView;
    private View mProgressBar;
    private final Runnable mProgressBarToFragmentTransition;

    public MultiStepFragment() {
        this.mProgressBarToFragmentTransition = new Runnable() {
            public void run() {
                if (MultiStepFragment.this.mCurrentFragment == null) {
                    FinskyLog.w("Current fragment null.", new Object[0]);
                    return;
                }
                MultiStepFragment.this.fadeOutProgressBar();
                MultiStepFragment.this.fadeInFragment();
                MultiStepFragment.this.syncButtonBar(MultiStepFragment.this.mCurrentFragment);
            }
        };
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("MultiStepFragment.account")) {
            this.mAccount = (Account) getArguments().getParcelable("MultiStepFragment.account");
        } else if (getArguments().containsKey("authAccount")) {
            this.mAccount = AccountHandler.findAccount(getArguments().getString("authAccount"), FinskyApp.get());
        }
        if (this.mAccount == null) {
            throw new IllegalStateException("No account specified.");
        }
        this.mEventLog = FinskyApp.get().getEventLogger(this.mAccount);
        if (savedInstanceState != null) {
            this.mIsLoading = savedInstanceState.getBoolean("MultiStepFragment.isLoading");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("MultiStepFragment.isLoading", this.mIsLoading);
    }

    public void onStart() {
        super.onStart();
        this.mCurrentFragment = (StepFragment) getChildFragmentManager().findFragmentById(R.id.content_frame_above_button);
        restoreUi();
    }

    public void onStop() {
        this.mMainView.removeCallbacks(this.mProgressBarToFragmentTransition);
        super.onStop();
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mMainView = view;
        SetupWizardNavBar setupWizardNavBar = SetupWizardUtils.getNavBarIfPossible(getActivity());
        if (setupWizardNavBar != null) {
            this.mAnimateContinueButtonBar = false;
            this.mContinueButtonBar = setupWizardNavBar.getView();
            this.mContinueButton = setupWizardNavBar.getNextButton();
        } else {
            this.mAnimateContinueButtonBar = true;
            this.mContinueButtonBar = this.mMainView.findViewById(R.id.continue_button_bar);
            this.mContinueButton = this.mMainView.findViewById(R.id.continue_button);
        }
        this.mContinueButtonBar.setVisibility(8);
        this.mContinueButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (MultiStepFragment.this.mCurrentFragment != null && MultiStepFragment.this.mButtonBarVisible) {
                    UiUtils.hideKeyboard(MultiStepFragment.this.getActivity(), MultiStepFragment.this.mCurrentFragment.getView());
                    MultiStepFragment.this.mCurrentFragment.onContinueButtonClicked();
                }
            }
        });
        this.mProgressBar = this.mMainView.findViewById(R.id.progress_bar);
        this.mFragmentContainer = this.mMainView.findViewById(R.id.content_frame_above_button);
    }

    public void showLoading() {
        if (this.mButtonBarVisible) {
            fadeOutButtonBar();
        }
        if (!this.mIsLoading) {
            if (this.mCurrentFragment != null) {
                slideFragmentOut();
                this.mProgressBar.setVisibility(0);
                this.mProgressBar.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right));
            } else {
                this.mFragmentContainer.setVisibility(4);
                this.mProgressBar.setVisibility(0);
                this.mProgressBar.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.play_fade_in));
            }
            this.mIsLoading = true;
            logImpression(213);
        }
    }

    public void hideLoading() {
        if (this.mCurrentFragment == null) {
            FinskyLog.wtf("Illegal state: hideLoading called without fragment.", new Object[0]);
            return;
        }
        this.mFragmentContainer.setVisibility(0);
        this.mFragmentContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.play_fade_in));
        fadeOutProgressBar();
        syncButtonBar(this.mCurrentFragment);
        this.mIsLoading = false;
        logImpression(this.mCurrentFragment);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public void showStep(StepFragment<?> step) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (this.mIsLoading) {
            this.mFragmentContainer.setVisibility(4);
            this.mMainView.postDelayed(this.mProgressBarToFragmentTransition, 100);
        } else {
            if (this.mCurrentFragment != null) {
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            this.mFragmentContainer.setVisibility(0);
            syncButtonBar(step);
        }
        if (this.mCurrentFragment != null) {
            transaction.remove(this.mCurrentFragment);
        }
        transaction.add((int) R.id.content_frame_above_button, (Fragment) step);
        transaction.commit();
        this.mCurrentFragment = step;
        this.mIsLoading = false;
    }

    public void setContinueButtonEnabled(boolean enabled) {
        this.mContinueButton.setEnabled(enabled);
    }

    public boolean isContinueButtonEnabled() {
        return this.mContinueButton.isEnabled();
    }

    public void logImpression(PlayStoreUiElementNode leaf) {
        this.mEventLog.logPathImpression(0, leaf);
    }

    public void logImpression(int leafType) {
        this.mEventLog.logPathImpression(0, leafType, (PlayStoreUiElementNode) getActivity());
    }

    public void logClick(int leafType, PlayStoreUiElementInfo leafClientLogsCookie, PlayStoreUiElementNode parent) {
        this.mEventLog.logClickEventWithClientCookie(leafType, leafClientLogsCookie, parent);
    }

    private void fadeInFragment() {
        this.mFragmentContainer.setVisibility(0);
        this.mFragmentContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.play_fade_in));
    }

    private void slideFragmentOut() {
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left);
        anim.setAnimationListener(new AnimationListenerAdapter() {
            public void onAnimationEnd(Animation animation) {
                MultiStepFragment.this.mFragmentContainer.setVisibility(4);
            }
        });
        this.mFragmentContainer.startAnimation(anim);
    }

    private void syncButtonBar(StepFragment<?> step) {
        if (step.allowButtonBar()) {
            if (!this.mButtonBarVisible) {
                fadeInButtonBar();
            }
            String continueButtonLabel = step.getContinueButtonLabel(getResources());
            if (continueButtonLabel != null) {
                configureContinueButton(Boolean.valueOf(step.allowContinueButtonIcon()), continueButtonLabel);
                return;
            } else {
                this.mContinueButton.setVisibility(8);
                return;
            }
        }
        this.mContinueButtonBar.setVisibility(8);
    }

    private void configureContinueButton(Boolean allowContinueButtonIcon, String continueButtonLabel) {
        this.mContinueButton.setVisibility(0);
        if (this.mContinueButton instanceof IconButtonGroup) {
            ((IconButtonGroup) this.mContinueButton).setAllowIcon(allowContinueButtonIcon.booleanValue());
            ((IconButtonGroup) this.mContinueButton).setText(continueButtonLabel);
        } else if (this.mContinueButton instanceof Button) {
            ((Button) this.mContinueButton).setText(continueButtonLabel);
        }
    }

    private void fadeInButtonBar() {
        if (!this.mButtonBarVisible) {
            this.mButtonBarVisible = true;
            this.mContinueButtonBar.setVisibility(0);
            if (this.mAnimateContinueButtonBar) {
                this.mContinueButtonBar.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.play_fade_in));
            }
        }
    }

    private void fadeOutButtonBar() {
        if (this.mButtonBarVisible) {
            this.mButtonBarVisible = false;
            if (this.mAnimateContinueButtonBar) {
                fadeOutView(this.mContinueButtonBar);
            } else {
                this.mContinueButton.setVisibility(0);
            }
        }
    }

    private void fadeOutProgressBar() {
        fadeOutView(this.mProgressBar);
    }

    private void fadeOutView(final View view) {
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        anim.setAnimationListener(new AnimationListenerAdapter() {
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(4);
            }
        });
        view.startAnimation(anim);
    }

    private void restoreUi() {
        if (this.mIsLoading) {
            this.mProgressBar.setVisibility(0);
        } else if (this.mCurrentFragment != null) {
            this.mFragmentContainer.setVisibility(0);
            syncButtonBar(this.mCurrentFragment);
        }
    }
}
