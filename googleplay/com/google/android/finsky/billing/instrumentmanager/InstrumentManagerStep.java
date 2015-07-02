package com.google.android.finsky.billing.instrumentmanager;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.lightpurchase.multistep.MultiStepFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.protos.SingleFopPaymentsIntegrator.SingleFopPaymentsIntegratorContext;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.wallet.instrumentmanager.pub.InstrumentManagerFragment;
import com.google.android.wallet.instrumentmanager.pub.InstrumentManagerFragment.ResultListener;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerAnalyticsEventDispatcher;

public abstract class InstrumentManagerStep<T extends MultiStepFragment> extends StepFragment<T> implements ResultListener {
    protected static Bundle createArgs(String accountName, SingleFopPaymentsIntegratorContext tokens) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putParcelable("InstrumentManagerStep.tokens", ParcelableProto.forProto(tokens));
        return args;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.instrument_manager_step, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        InstrumentManagerAnalyticsEventDispatcher.setEventListener(new InstrumentManagerLogger(this, FinskyApp.get().getEventLogger(args.getString("authAccount"))));
        if (getChildFragmentManager().findFragmentById(R.id.instrument_manager_host) == null) {
            SingleFopPaymentsIntegratorContext tokens = (SingleFopPaymentsIntegratorContext) ParcelableProto.getProtoFromBundle(args, "InstrumentManagerStep.tokens");
            getChildFragmentManager().beginTransaction().add((int) R.id.instrument_manager_host, InstrumentManagerFragment.newInstance(tokens.commonToken, tokens.instrumentToken, R.style.Theme.InstrumentManager.BuyFlow)).commit();
        }
    }

    public void onDestroy() {
        InstrumentManagerAnalyticsEventDispatcher.setEventListener(null);
        super.onDestroy();
    }

    protected boolean isSuccess(int resultCode) {
        switch (resultCode) {
            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                return true;
            case com.google.android.play.R.styleable.Theme_activityChooserViewStyle /*51*/:
            case com.google.android.play.R.styleable.Theme_toolbarStyle /*52*/:
                return false;
            default:
                throw new IllegalStateException("Unexpected InstrumentManager resultCode: " + resultCode);
        }
    }

    public boolean allowButtonBar() {
        return false;
    }

    public String getContinueButtonLabel(Resources resources) {
        return null;
    }

    public void onContinueButtonClicked() {
    }
}
