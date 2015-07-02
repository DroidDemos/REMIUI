package com.google.android.finsky.billing.lightpurchase.purchasesteps;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Purchase.ChangeSubscription;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ParcelableProto;

public class ChangeSubscriptionStep extends StepFragment<PurchaseFragment> implements PlayStoreUiElementNode {
    private int mBackend;
    private ChangeSubscription mChangeSubscription;
    private final PlayStoreUiElement mUiElement;

    public ChangeSubscriptionStep() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(1280);
    }

    public static ChangeSubscriptionStep newInstance(int backend, ChangeSubscription changeSubscription) {
        Bundle args = new Bundle();
        ChangeSubscriptionStep result = new ChangeSubscriptionStep();
        args.putInt("ChangeSubscriptionStep.backend", backend);
        args.putParcelable("ChangeSubscriptionStep.changeSubscription", ParcelableProto.forProto(changeSubscription));
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.mBackend = args.getInt("ChangeSubscriptionStep.backend");
        this.mChangeSubscription = (ChangeSubscription) ParcelableProto.getProtoFromBundle(args, "ChangeSubscriptionStep.changeSubscription");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mMainView = inflater.inflate(R.layout.light_purchase_change_subscription, container, false);
        ((TextView) mMainView.findViewById(R.id.title)).setText(this.mChangeSubscription.title);
        TextView description = (TextView) mMainView.findViewById(R.id.message);
        description.setText(BillingUtils.parseHtmlAndColorizeEm(this.mChangeSubscription.descriptionHtml, CorpusResourceUtils.getSecondaryTextColor(getActivity(), this.mBackend).getDefaultColor()));
        description.setMovementMethod(LinkMovementMethod.getInstance());
        description.setLinkTextColor(description.getTextColors());
        return mMainView;
    }

    public String getContinueButtonLabel(Resources resources) {
        return getString(R.string.continue_text);
    }

    public void onContinueButtonClicked() {
        logClick(1281);
        ((PurchaseFragment) getMultiStepFragment()).switchFromChangeSubscriptionToCart();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }
}
