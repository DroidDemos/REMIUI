package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.LoggingFragmentActivity;
import com.google.android.finsky.billing.promptforfop.SetupWizardPromptForFopActivity;
import com.google.android.finsky.layout.AvailablePromoOfferContent;
import com.google.android.finsky.layout.SetupWizardIconButtonGroup;
import com.google.android.finsky.protos.CheckPromoOffer.AddCreditCardPromoOffer;
import com.google.android.finsky.protos.CheckPromoOffer.AvailablePromoOffer;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.setup.SetupWizardNavBar;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.SetupWizardUtils.SetupWizardParams;

public class SetupWizardAvailablePromoOfferActivity extends LoggingFragmentActivity implements ImageGetter, Listener {
    private Account mAccount;
    private AvailablePromoOffer mAvailablePromoOffer;
    private SetupWizardParams mSetupWizardParams;

    public static Intent createIntent(Account account, AvailablePromoOffer availablePromoOffer) {
        Bundle internalParams = new Bundle();
        internalParams.putParcelable("available_offer", ParcelableProto.forProto(availablePromoOffer));
        Intent intent = new Intent(FinskyApp.get(), SetupWizardAvailablePromoOfferActivity.class);
        intent.putExtra("account", account);
        intent.putExtra("authAccount", account.name);
        intent.putExtra("internal_parameters", internalParams);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle internalParameters = intent.getBundleExtra("internal_parameters");
        if (internalParameters == null) {
            FinskyLog.e("No internal parameters passed.", new Object[0]);
            setResultAndFinish();
            return;
        }
        this.mAvailablePromoOffer = (AvailablePromoOffer) ParcelableProto.getProtoFromBundle(internalParameters, "available_offer");
        if (this.mAvailablePromoOffer == null) {
            FinskyLog.e("No available offer passed.", new Object[0]);
            setResultAndFinish();
        } else if (this.mAvailablePromoOffer.addCreditCardOffer == null) {
            FinskyLog.w("Unsupported offer.", new Object[0]);
            setResultAndFinish();
        } else {
            this.mAccount = (Account) intent.getParcelableExtra("account");
            if (this.mAccount == null) {
                FinskyLog.e("No account passed.", new Object[0]);
                setResultAndFinish();
                return;
            }
            this.mSetupWizardParams = new SetupWizardParams(intent);
            setTheme(this.mSetupWizardParams.isLightTheme() ? R.style.SetupWizardTheme.Light : R.style.SetupWizardTheme);
            setContentView(R.layout.setup_wizard_play_frame);
            ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
            contentFrame.removeAllViews();
            getLayoutInflater().inflate(R.layout.setup_wizard_available_promo_offer, contentFrame, true);
            SetupWizardUtils.configureBasicUi(this, this.mSetupWizardParams, 0, false, false, false);
        }
    }

    protected void onResume() {
        super.onResume();
        AddCreditCardPromoOffer addCreditCardOffer = this.mAvailablePromoOffer.addCreditCardOffer;
        ((TextView) findViewById(R.id.title)).setText(addCreditCardOffer.headerText);
        CharSequence intro = Html.fromHtml(addCreditCardOffer.introductoryTextHtml);
        CharSequence description = Html.fromHtml(addCreditCardOffer.descriptionHtml);
        Image promoImage = addCreditCardOffer.image;
        CharSequence terms = Html.fromHtml(addCreditCardOffer.termsAndConditionsHtml);
        replaceUrlsWithHandlers(terms);
        final String noActionMessage = addCreditCardOffer.noActionDescription;
        AvailablePromoOfferContent content = (AvailablePromoOfferContent) findViewById(R.id.promo_offer_content);
        SetupWizardNavBar setupWizardNavBar = SetupWizardUtils.getNavBarIfPossible(this);
        if (setupWizardNavBar != null) {
            content.configure(intro, null, description, terms);
            final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.promo_offer_radio_group);
            setupWizardNavBar.getNextButton().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (radioGroup.getCheckedRadioButtonId() == R.id.promo_offer_accept) {
                        SetupWizardAvailablePromoOfferActivity.this.handleOfferContinued();
                    } else {
                        SetupWizardAvailablePromoOfferActivity.this.handleOfferSkipped(noActionMessage);
                    }
                }
            });
            return;
        }
        content.configure(intro, promoImage, description, terms);
        SetupWizardIconButtonGroup continueButton = (SetupWizardIconButtonGroup) findViewById(R.id.continue_button);
        continueButton.setIconDrawable(getResources().getDrawable(R.drawable.purchase_wallet));
        continueButton.setText(getString(R.string.add_instrument_continue));
        continueButton.setEnabled(true);
        continueButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SetupWizardAvailablePromoOfferActivity.this.handleOfferContinued();
            }
        });
        TextView notNowButton = (TextView) findViewById(R.id.not_now_button);
        notNowButton.setText(getString(R.string.setup_account_skip).toUpperCase());
        notNowButton.setVisibility(0);
        notNowButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SetupWizardAvailablePromoOfferActivity.this.handleOfferSkipped(noActionMessage);
            }
        });
    }

    private void handleOfferContinued() {
        this.mEventLog.logClickEvent(252, null, this);
        if (this.mAvailablePromoOffer.addCreditCardOffer != null) {
            startActivityForResult(SetupWizardPromptForFopActivity.createIntent(this.mAccount, this.mSetupWizardParams), 100);
            SetupWizardUtils.animateSliding(this, false);
        }
    }

    private void handleOfferSkipped(String noActionMessage) {
        this.mEventLog.logClickEvent(253, null, this);
        if (TextUtils.isEmpty(noActionMessage)) {
            setResultAndFinish();
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag("no_action_message") == null) {
            Builder builder = new Builder();
            builder.setMessage(noActionMessage).setPositiveId(R.string.ok).setCallback(null, 101, null);
            builder.build().show(fragmentManager, "no_action_message");
        }
    }

    public Drawable getDrawable(String source) {
        Drawable drawable = getResources().getDrawable(R.drawable.google_wallet_logo);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    private void replaceUrlsWithHandlers(CharSequence string) {
        if (string instanceof Spannable) {
            Spannable spannable = (Spannable) string;
            for (URLSpan span : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
                int start = spannable.getSpanStart(span);
                int end = spannable.getSpanEnd(span);
                spannable.removeSpan(span);
                spannable.setSpan(new UrlSpanHandler(span.getURL()) {
                    public void onUrlClick(View span, String url) {
                        SetupWizardAvailablePromoOfferActivity.this.showUrlWebView(url);
                    }
                }, start, end, 0);
            }
        }
    }

    public void showUrlWebView(String url) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag("policy_terms") == null) {
            Builder builder = new Builder();
            builder.setLayoutId(R.layout.billing_alertwebview).setPositiveId(R.string.close);
            Bundle argumentsBundle = new Bundle();
            argumentsBundle.putString("url_key", url);
            builder.setViewConfiguration(argumentsBundle);
            builder.build().show(fragmentManager, "policy_terms");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            boolean redeemed = data != null && data.hasExtra("redeemed_offer_message_html");
            this.mEventLog.logOperationSuccessBackgroundEvent(500, redeemed);
            setResult(-1, data);
            finish();
        }
    }

    private void setResultAndFinish() {
        setResult(-1);
        finish();
    }

    public void finish() {
        super.finish();
        SetupWizardUtils.animateSliding(this, false);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 101) {
            setResultAndFinish();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }

    protected int getPlayStoreUiElementType() {
        return 890;
    }
}
