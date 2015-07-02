package com.google.android.finsky.billing.lightpurchase.purchasesteps;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.utils.UiUtils;

public class ErrorStep extends StepFragment<PurchaseFragment> {
    private final PlayStoreUiElement mUiElement;

    public ErrorStep() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(770);
    }

    public static ErrorStep newInstance(String title, String messageHtml, boolean purchaseFailed) {
        ErrorStep result = new ErrorStep();
        Bundle args = new Bundle();
        args.putString("ErrorStep.title", title);
        args.putString("ErrorStep.messageHtml", messageHtml);
        args.putBoolean("ErrorStep.purchaseFailed", purchaseFailed);
        result.setArguments(args);
        return result;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mainView = (ViewGroup) inflater.inflate(R.layout.light_purchase_error_step, container, false);
        TextView message = (TextView) mainView.findViewById(R.id.message);
        ((TextView) mainView.findViewById(R.id.title)).setText(getArguments().getString("ErrorStep.title"));
        message.setText(Html.fromHtml(getArguments().getString("ErrorStep.messageHtml")));
        message.setMovementMethod(LinkMovementMethod.getInstance());
        return mainView;
    }

    public void onStart() {
        super.onStart();
        View view = getView();
        UiUtils.sendAccessibilityEventWithText(getActivity(), ((TextView) view.findViewById(R.id.message)).getText().toString(), view);
    }

    public String getContinueButtonLabel(Resources resources) {
        return resources.getString(R.string.ok);
    }

    public void onContinueButtonClicked() {
        logClick(771);
        if (getArguments().getBoolean("ErrorStep.purchaseFailed")) {
            ((PurchaseFragment) getMultiStepFragment()).finish();
        } else {
            ((PurchaseFragment) getMultiStepFragment()).preparePurchase();
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }
}
