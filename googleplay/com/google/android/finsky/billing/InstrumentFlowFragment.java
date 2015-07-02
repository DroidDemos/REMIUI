package com.google.android.finsky.billing;

import android.os.Bundle;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;

public abstract class InstrumentFlowFragment extends BillingFlowFragment {
    protected void finishWithUpdateInstrumentResponse(UpdateInstrumentResponse response) {
        Bundle result = new Bundle();
        if (response.hasInstrumentId) {
            result.putString("instrument_id", response.instrumentId);
        }
        if (response.redeemedOffer != null) {
            result.putString("redeemed_offer_message_html", response.redeemedOffer.descriptionHtml);
        }
        finish(result);
    }
}
