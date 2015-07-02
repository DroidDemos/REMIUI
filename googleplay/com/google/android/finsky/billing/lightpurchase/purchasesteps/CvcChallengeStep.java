package com.google.android.finsky.billing.lightpurchase.purchasesteps;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.vending.R;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.billing.creditcard.CreditCardType;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.protos.ChallengeProto.CvnChallenge;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;

public class CvcChallengeStep extends StepFragment<PurchaseFragment> {
    private CvnChallenge mChallenge;
    private EditText mCodeEntry;
    private CreditCardType mCreditCardType;
    private View mMainView;
    private final PlayStoreUiElement mUiElement;

    public CvcChallengeStep() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(1270);
    }

    public static CvcChallengeStep newInstance(String accountName, CvnChallenge challenge) {
        CvcChallengeStep result = new CvcChallengeStep();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putParcelable("CvcChallengeStep.challenge", ParcelableProto.forProto(challenge));
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mChallenge = (CvnChallenge) ParcelableProto.getProtoFromBundle(getArguments(), "CvcChallengeStep.challenge");
        this.mCreditCardType = CreditCardType.getTypeForProtobufType(this.mChallenge.creditCardType);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mMainView = inflater.inflate(R.layout.light_purchase_cvc_challenge_step, container, false);
        TextView title = (TextView) this.mMainView.findViewById(R.id.title);
        if (!TextUtils.isEmpty(this.mChallenge.title)) {
            title.setText(this.mChallenge.title);
        }
        TextView description = (TextView) this.mMainView.findViewById(R.id.message);
        if (TextUtils.isEmpty(this.mChallenge.descriptionHtml)) {
            description.setVisibility(8);
        } else {
            description.setText(Html.fromHtml(this.mChallenge.descriptionHtml));
            description.setMovementMethod(LinkMovementMethod.getInstance());
            description.setLinkTextColor(description.getTextColors());
        }
        this.mCodeEntry = (EditText) this.mMainView.findViewById(R.id.cvc_entry);
        this.mCodeEntry.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                CvcChallengeStep.this.syncContinueButtonState();
            }
        });
        if (this.mCreditCardType != null) {
            this.mCodeEntry.setFilters(new InputFilter[]{new LengthFilter(this.mCreditCardType.cvcLength)});
        }
        this.mCodeEntry.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView tv, int id, KeyEvent event) {
                if (id == 6 && ((PurchaseFragment) CvcChallengeStep.this.getMultiStepFragment()).isContinueButtonEnabled()) {
                    CvcChallengeStep.this.handleClick(true);
                }
                return false;
            }
        });
        ((ImageView) this.mMainView.findViewById(R.id.cvc_image)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CvcChallengeStep.this.getFragmentManager().findFragmentByTag("CvcChallengeStep.cvc_popup") == null) {
                    UiUtils.hideKeyboard(CvcChallengeStep.this.getActivity(), CvcChallengeStep.this.mMainView);
                    Builder builder = new Builder();
                    builder.setLayoutId(R.layout.billing_addcreditcard_cvc_popup).setPositiveId(R.string.close);
                    builder.build().show(CvcChallengeStep.this.getFragmentManager(), "CvcChallengeStep.cvc_popup");
                }
            }
        });
        syncContinueButtonState();
        return this.mMainView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UiUtils.showKeyboard(getActivity(), this.mCodeEntry);
    }

    private void syncContinueButtonState() {
        boolean isCvcValid = true;
        if (this.mCreditCardType != null) {
            if (this.mCodeEntry.getText().length() != this.mCreditCardType.cvcLength) {
                isCvcValid = false;
            }
        } else if (TextUtils.isEmpty(this.mCodeEntry.getText())) {
            isCvcValid = false;
        }
        ((PurchaseFragment) getMultiStepFragment()).setContinueButtonEnabled(isCvcValid);
    }

    public String getContinueButtonLabel(Resources resources) {
        return resources.getString(R.string.verify);
    }

    public void onContinueButtonClicked() {
        handleClick(false);
    }

    private void handleClick(boolean isImeAction) {
        PlayStoreUiElementInfo clientLogsCookie = null;
        if (isImeAction) {
            clientLogsCookie = new PlayStoreUiElementInfo();
            clientLogsCookie.isImeAction = true;
            clientLogsCookie.hasIsImeAction = true;
        }
        ((PurchaseFragment) getMultiStepFragment()).logClick(1271, clientLogsCookie, this);
        ((PurchaseFragment) getMultiStepFragment()).escrowCvcCode(this.mCodeEntry.getText().toString(), this.mChallenge.escrowHandleParam);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }
}
