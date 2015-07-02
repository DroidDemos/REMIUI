package com.google.android.finsky.billing.lightpurchase;

import android.accounts.Account;
import android.os.Bundle;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.carrierbilling.Dcb3Util;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.ParcelableProto;

public class CompleteDcb3FlowFragment extends LegacyFlowWrapperFragment {
    public static CompleteDcb3FlowFragment newInstance(Account account, Instrument instrument) {
        Bundle args = new Bundle();
        args.putParcelable("CompleteDcb3Flow.account", account);
        args.putParcelable("CompleteDcb3Flow.instrument", ParcelableProto.forProto(instrument));
        CompleteDcb3FlowFragment result = new CompleteDcb3FlowFragment();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startOrResumeLegacyFlow(savedInstanceState);
    }

    protected BillingFlow getLegacyPurchaseFlow() {
        Bundle args = getArguments();
        Account account = (Account) args.getParcelable("CompleteDcb3Flow.account");
        Bundle params = new Bundle();
        params.putString("authAccount", account.name);
        return Dcb3Util.getCompletePurchaseFlow(this, this, params, (Instrument) ParcelableProto.getProtoFromBundle(args, "CompleteDcb3Flow.instrument"));
    }
}
