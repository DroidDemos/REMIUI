package com.google.android.finsky.billing.lightpurchase.ageverification;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.protos.ChallengeProto.Challenge;
import com.google.android.finsky.protos.ChallengeProto.ChallengeError;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.layout.PlayActionButton;

public class ChallengeErrorFragment extends LoggingFragment implements OnClickListener {
    private PlayActionButton mCancelButton;
    private ChallengeError mChallengeError;
    private View mMainView;
    private PlayActionButton mSubmitButton;

    public interface Listener {
        void onFail();

        void onStartChallenge(Challenge challenge);
    }

    public static ChallengeErrorFragment newInstance(String accountName, int backend, ChallengeError challengeError) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putInt("ChallengeErrorFragment.backend", backend);
        args.putParcelable("ChallengeErrorFragment.challenge", ParcelableProto.forProto(challengeError));
        ChallengeErrorFragment result = new ChallengeErrorFragment();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mChallengeError = (ChallengeError) ParcelableProto.getProtoFromBundle(getArguments(), "ChallengeErrorFragment.challenge");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mMainView = inflater.inflate(R.layout.age_verification_error_fragment, container, false);
        TextView title = (TextView) this.mMainView.findViewById(R.id.title);
        if (TextUtils.isEmpty(this.mChallengeError.title)) {
            throw new IllegalStateException("No title returned.");
        }
        title.setText(this.mChallengeError.title);
        TextView message = (TextView) this.mMainView.findViewById(R.id.message);
        if (TextUtils.isEmpty(this.mChallengeError.descriptionHtml)) {
            message.setVisibility(8);
        } else {
            message.setText(Html.fromHtml(this.mChallengeError.descriptionHtml));
        }
        int backend = getArguments().getInt("ChallengeErrorFragment.backend");
        this.mSubmitButton = (PlayActionButton) this.mMainView.findViewById(R.id.positive_button);
        if (this.mChallengeError.submitButton == null || TextUtils.isEmpty(this.mChallengeError.submitButton.label)) {
            throw new IllegalStateException("No submit button returned.");
        }
        this.mSubmitButton.configure(backend, this.mChallengeError.submitButton.label, (OnClickListener) this);
        this.mCancelButton = (PlayActionButton) this.mMainView.findViewById(R.id.negative_button);
        if (this.mChallengeError.cancelButton == null || TextUtils.isEmpty(this.mChallengeError.cancelButton.label)) {
            this.mCancelButton.setVisibility(8);
        } else {
            this.mCancelButton.configure(backend, this.mChallengeError.cancelButton.label, (OnClickListener) this);
        }
        return this.mMainView;
    }

    public void onResume() {
        super.onResume();
        UiUtils.sendAccessibilityEventWithText(this.mMainView.getContext(), this.mChallengeError.title, this.mMainView);
    }

    private Listener getListener() {
        if (getTargetFragment() instanceof Listener) {
            return (Listener) getTargetFragment();
        }
        if (getParentFragment() instanceof Listener) {
            return (Listener) getParentFragment();
        }
        if (getActivity() instanceof Listener) {
            return (Listener) getActivity();
        }
        throw new IllegalStateException("No listener registered.");
    }

    protected int getPlayStoreUiElementType() {
        return 1406;
    }

    public void onClick(View v) {
        if (v == this.mSubmitButton) {
            logClickEvent(1407);
            if (this.mChallengeError.submitButton.actionFailChallenge) {
                getListener().onFail();
            } else if (this.mChallengeError.submitButton.actionChallenge.length == 1) {
                getListener().onStartChallenge(this.mChallengeError.submitButton.actionChallenge[0]);
            } else {
                throw new IllegalStateException("Unexpected submit button action.");
            }
        } else if (v == this.mCancelButton) {
            logClickEvent(1409);
            if (this.mChallengeError.cancelButton.actionFailChallenge) {
                getListener().onFail();
                return;
            }
            throw new IllegalStateException("Unexpected cancel button action.");
        }
    }
}
