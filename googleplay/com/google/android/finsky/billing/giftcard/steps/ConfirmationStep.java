package com.google.android.finsky.billing.giftcard.steps;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.giftcard.RedeemCodeFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.PromoCode.UserConfirmationChallenge;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.image.FifeImageView.OnLoadedListener;

public class ConfirmationStep extends StepFragment<RedeemCodeFragment> {
    private int mBillingUiMode;
    private UserConfirmationChallenge mChallenge;
    private View mMainView;
    private PlayStoreUiElement mPlayStoreUiElement;

    public ConfirmationStep() {
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(883);
    }

    public static ConfirmationStep newInstance(UserConfirmationChallenge challenge, int mode, boolean codeScreenSkipped) {
        Bundle args = new Bundle();
        args.putParcelable("ConfirmationStep.challenge", ParcelableProto.forProto(challenge));
        args.putInt("ui_mode", mode);
        args.putBoolean("ConfirmationStep.code_screen_skipped", codeScreenSkipped);
        ConfirmationStep result = new ConfirmationStep();
        result.mChallenge = challenge;
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mChallenge = (UserConfirmationChallenge) ParcelableProto.getProtoFromBundle(getArguments(), "ConfirmationStep.challenge");
        this.mBillingUiMode = getArguments().getInt("ui_mode");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mMainView = inflater.inflate(this.mBillingUiMode == 0 ? R.layout.redeem_confirmation_step : R.layout.setup_wizard_redeem_confirmation_step, container, false);
        if (this.mBillingUiMode == 1) {
            getActivity().setTitle(getString(R.string.setup_wizard_redeem_confirmation_title));
        }
        TextView titleView = (TextView) this.mMainView.findViewById(R.id.title);
        if (TextUtils.isEmpty(this.mChallenge.title)) {
            titleView.setVisibility(8);
        } else {
            titleView.setText(this.mChallenge.title);
        }
        TextView titleBylineView = (TextView) this.mMainView.findViewById(R.id.title_byline);
        if (TextUtils.isEmpty(this.mChallenge.titleBylineHtml)) {
            titleBylineView.setVisibility(8);
        } else {
            titleBylineView.setText(Html.fromHtml(this.mChallenge.titleBylineHtml));
            titleBylineView.setMovementMethod(LinkMovementMethod.getInstance());
            titleBylineView.setLinkTextColor(titleBylineView.getTextColors());
        }
        FifeImageView imageView = (FifeImageView) this.mMainView.findViewById(R.id.image);
        Image image = this.mChallenge.thumbnailImage;
        if (image != null) {
            imageView.setOnLoadedListener(new OnLoadedListener() {
                public void onLoadedAndFadedIn(FifeImageView imageView) {
                }

                public void onLoaded(FifeImageView imageView, Bitmap bitmap) {
                    if (bitmap != null) {
                        LayoutParams imageLp = imageView.getLayoutParams();
                        imageLp.height = (int) (((float) imageLp.width) / (((float) bitmap.getWidth()) / ((float) bitmap.getHeight())));
                        ConfirmationStep.this.mMainView.requestLayout();
                    }
                }
            });
            imageView.setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
        } else {
            imageView.setVisibility(8);
        }
        TextView priceView = (TextView) this.mMainView.findViewById(R.id.price);
        if (TextUtils.isEmpty(this.mChallenge.formattedPrice)) {
            priceView.setVisibility(8);
        } else {
            priceView.setText(this.mChallenge.formattedPrice);
        }
        TextView priceBylineView = (TextView) this.mMainView.findViewById(R.id.price_byline);
        if (TextUtils.isEmpty(this.mChallenge.priceBylineHtml)) {
            priceBylineView.setVisibility(8);
        } else {
            String priceBylineHtml = this.mChallenge.priceBylineHtml;
            priceBylineView.setText(Html.fromHtml(priceBylineHtml));
            priceBylineView.setMovementMethod(LinkMovementMethod.getInstance());
            priceBylineView.setLinkTextColor(priceBylineView.getTextColors());
            if (priceBylineHtml.startsWith("<strike>") && priceBylineHtml.endsWith("</strike>")) {
                priceBylineView.setPaintFlags(priceBylineView.getPaintFlags() | 16);
            }
            priceBylineView.setContentDescription(getResources().getString(R.string.content_description_full_price, new Object[]{priceBylineView.getText()}));
            priceView.setContentDescription(getResources().getString(R.string.content_description_current_price, new Object[]{priceView.getText()}));
        }
        TextView messageView = (TextView) this.mMainView.findViewById(R.id.message);
        if (TextUtils.isEmpty(this.mChallenge.messageHtml)) {
            messageView.setVisibility(8);
        } else {
            messageView.setText(Html.fromHtml(this.mChallenge.messageHtml));
            messageView.setMovementMethod(LinkMovementMethod.getInstance());
            messageView.setLinkTextColor(messageView.getTextColors());
        }
        TextView voucherFooterView = (TextView) this.mMainView.findViewById(R.id.voucher_footer);
        if (TextUtils.isEmpty(this.mChallenge.footerHtml)) {
            voucherFooterView.setVisibility(8);
        } else {
            voucherFooterView.setText(Html.fromHtml(this.mChallenge.footerHtml));
            voucherFooterView.setMovementMethod(LinkMovementMethod.getInstance());
            voucherFooterView.setLinkTextColor(voucherFooterView.getTextColors());
        }
        TextView tosFooterView = (TextView) this.mMainView.findViewById(R.id.tos_footer);
        if (getArguments().getBoolean("ConfirmationStep.code_screen_skipped", false)) {
            tosFooterView.setText(Html.fromHtml(getString(R.string.redeem_screen_footer, BillingUtils.replaceLocale((String) G.redeemTermsAndConditionsUrl.get()))));
            tosFooterView.setMovementMethod(LinkMovementMethod.getInstance());
            tosFooterView.setLinkTextColor(tosFooterView.getTextColors());
        } else {
            tosFooterView.setVisibility(8);
        }
        return this.mMainView;
    }

    public void onResume() {
        super.onResume();
        UiUtils.sendAccessibilityEventWithText(this.mMainView.getContext(), this.mBillingUiMode == 1 ? getString(R.string.setup_wizard_redeem_confirmation_title) : this.mChallenge.title, this.mMainView);
    }

    public String getContinueButtonLabel(Resources resources) {
        return this.mChallenge.buttonLabel;
    }

    public void onContinueButtonClicked() {
        logClick(884);
        ((RedeemCodeFragment) getMultiStepFragment()).confirm();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }
}
