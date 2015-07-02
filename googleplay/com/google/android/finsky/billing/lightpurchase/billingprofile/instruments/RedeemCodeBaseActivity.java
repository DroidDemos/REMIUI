package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import android.os.Bundle;
import com.android.vending.R;
import com.google.android.finsky.billing.giftcard.RedeemCodeFragment;
import com.google.android.finsky.billing.giftcard.RedeemCodeFragment.Listener;
import com.google.android.finsky.billing.giftcard.RedeemCodeResult;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.utils.ParcelableProto;

public abstract class RedeemCodeBaseActivity extends LoggingFragmentActivity implements Listener {
    private RedeemCodeFragment mRedeemCodeFragment;

    protected abstract int getActivityLayout();

    protected abstract int getBillingUiMode();

    protected abstract int getPlayStoreUiElementType();

    protected abstract void handleMessageAndFinish(String str);

    protected static void putIntentExtras(Intent intent, String accountName, int redemptionContext, int backend, Docid docid, int offerType, String prefillCode, String partnerPayload) {
        intent.putExtra("authAccount", accountName);
        intent.putExtra("RedeemCodeBaseActivity.redemption_context", redemptionContext);
        intent.putExtra("RedeemCodeBaseActivity.backend", backend);
        intent.putExtra("RedeemCodeBaseActivity.docid", ParcelableProto.forProto(docid));
        intent.putExtra("RedeemCodeBaseActivity.offer_type", offerType);
        intent.putExtra("RedeemCodeBaseActivity.prefill_code", prefillCode);
        intent.putExtra("RedeemCodeBaseActivity.partner_payload", partnerPayload);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            getSupportFragmentManager().beginTransaction().add((int) R.id.content_frame, RedeemCodeFragment.newInstance(this.mAccountName, intent.getIntExtra("RedeemCodeBaseActivity.redemption_context", 0), intent.getIntExtra("RedeemCodeBaseActivity.backend", -1), getBillingUiMode(), (Docid) ParcelableProto.getProtoFromIntent(intent, "RedeemCodeBaseActivity.docid"), intent.getIntExtra("RedeemCodeBaseActivity.offer_type", 0), intent.getStringExtra("RedeemCodeBaseActivity.prefill_code"), intent.getStringExtra("RedeemCodeBaseActivity.partner_payload"))).commit();
        }
    }

    protected void onStart() {
        super.onStart();
        this.mRedeemCodeFragment = (RedeemCodeFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    public void finish() {
        Intent result = new Intent();
        RedeemCodeResult redeemCodeResult = getRedeemCodeResult();
        if (redeemCodeResult != null) {
            result.putExtra("RedeemCodeBaseActivity.redeem_code_result", redeemCodeResult);
        }
        setResult(-1, result);
        super.finish();
    }

    private RedeemCodeResult getRedeemCodeResult() {
        return this.mRedeemCodeFragment == null ? null : this.mRedeemCodeFragment.getRedeemCodeResult();
    }

    public void onFinished() {
        RedeemCodeResult result = getRedeemCodeResult();
        handleMessageAndFinish(result == null ? null : result.getRedeemedOfferHtml());
    }
}
