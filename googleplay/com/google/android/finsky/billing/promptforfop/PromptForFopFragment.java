package com.google.android.finsky.billing.promptforfop;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.BillingProfileBaseFragment;
import com.google.android.finsky.billing.giftcard.RedeemCodeResult;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.RedeemCodeActivity;
import com.google.android.finsky.experiments.FinskyExperiments;
import com.google.android.finsky.layout.IconButtonGroup;
import com.google.android.finsky.layout.SeparatorLinearLayout;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.setup.SetupWizardNavBar;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.play.image.FifeImageView;
import java.util.List;

public class PromptForFopFragment extends BillingProfileBaseFragment implements OnClickListener {
    private List<ActionEntry> mActionEntries;
    private ViewGroup mActionsView;
    private View mContinueButton;
    private boolean mHasLoggedScreen;
    private ViewGroup mMainView;
    private ViewGroup mMoreActionsView;
    private SeparatorLinearLayout mMoreToggler;
    private TextView mMoreTogglerTitle;
    private boolean mMoreVisible;
    private final OnClickListener mNoneOnClickListener;
    private int mSelectedIndex;
    private PlayStoreUiElement mUiElement;
    private int mUiMode;

    public interface Listener {
        void onAlreadySetup();

        void onFatalError(String str);

        void onInstrumentCreated(String str);

        void onNoneSelected();
    }

    public PromptForFopFragment() {
        this.mSelectedIndex = -1;
        this.mNoneOnClickListener = new OnClickListener() {
            public void onClick(View view) {
                PromptForFopFragment.this.mEventLog.logClickEvent(1008, null, PromptForFopFragment.this);
                PromptForFopFragment.this.notifyListenerOnNoneSelected();
            }
        };
    }

    public static Fragment newInstance(Account account, byte[] serverLogsCookie) {
        Bundle args = buildArgumentsBundle(account, serverLogsCookie);
        PromptForFopFragment result = new PromptForFopFragment();
        result.setArguments(args);
        return result;
    }

    public static Bundle buildArgumentsBundle(Account account, byte[] serverLogsCookie) {
        Bundle args = new Bundle();
        args.putParcelable("BillingProfileFragment.account", account);
        args.putByteArray("PromptForFopFragment.server_logs_cookie", serverLogsCookie);
        return args;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mUiMode = determineUiMode();
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(getPlayStoreUiElementType());
        FinskyEventLog.setServerLogCookie(this.mUiElement, getArguments().getByteArray("PromptForFopFragment.server_logs_cookie"));
        if (savedInstanceState != null) {
            this.mMoreVisible = savedInstanceState.getBoolean("PromptForFopFragment.more_visible");
            this.mHasLoggedScreen = savedInstanceState.getBoolean("PromptForFopFragment.has_logged_screen");
        }
    }

    protected int getPlayStoreUiElementType() {
        return 1002;
    }

    protected int determineUiMode() {
        FinskyExperiments experiments = FinskyApp.get().getExperiments(this.mAccount.name);
        if (experiments.isEnabled("cl:billing.prompt_for_fop_ui_mode_radio_button")) {
            return 1;
        }
        if (experiments.isEnabled("cl:billing.prompt_for_fop_ui_mode_billing_profile_nested")) {
            return 3;
        }
        if (experiments.isEnabled("cl:billing.prompt_for_fop_ui_mode_billing_profile_more_details")) {
            return 4;
        }
        if (experiments.isEnabled("cl:billing.prompt_for_fop_ui_mode_billing_profile_not_now")) {
            return 5;
        }
        if (experiments.isEnabled("cl:billing.prompt_for_fop_ui_mode_billing_profile")) {
            return 2;
        }
        FinskyLog.w("No UI mode specified, defaulting to UI_MODE_BILLING_PROFILE", new Object[0]);
        return 2;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mMainView = (ViewGroup) inflater.inflate(getMainLayout(), container, false);
        View actionsContainer = this.mMainView.findViewById(R.id.actions_container);
        this.mActionsView = (ViewGroup) actionsContainer.findViewById(R.id.actions);
        TextView titleView = (TextView) this.mMainView.findViewById(R.id.title);
        if (titleView != null) {
            titleView.setText(R.string.setup_account_title);
        }
        TextView accountView = (TextView) this.mMainView.findViewById(R.id.account);
        if (accountView != null) {
            accountView.setText(this.mAccount.name);
            accountView.setVisibility(0);
        }
        ((TextView) this.mMainView.findViewById(R.id.primer)).setText(Html.fromHtml(getString(getPrimerStringId())));
        this.mProgressIndicator = this.mMainView.findViewById(R.id.loading_indicator);
        this.mProfileView = this.mMainView.findViewById(R.id.profile);
        SetupWizardNavBar setupWizardNavBar = SetupWizardUtils.getNavBarIfPossible(getActivity());
        if (setupWizardNavBar != null) {
            this.mContinueButton = setupWizardNavBar.getNextButton();
        } else {
            this.mContinueButton = this.mMainView.findViewById(R.id.continue_button);
        }
        this.mContinueButton.setOnClickListener(this);
        configureContinueButtonStyle(this.mContinueButton);
        this.mContinueButton.setEnabled(false);
        this.mMoreToggler = (SeparatorLinearLayout) actionsContainer.findViewById(R.id.fop_more_toggle);
        this.mMoreToggler.hideSeparator();
        this.mMoreTogglerTitle = (TextView) this.mMoreToggler.findViewById(R.id.fop_more_toggle_title);
        this.mMoreActionsView = (ViewGroup) actionsContainer.findViewById(R.id.more_actions);
        this.mMoreToggler.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PromptForFopFragment.this.mEventLog.logClickEvent(1007, null, PromptForFopFragment.this);
                PromptForFopFragment.this.toggleMore();
            }
        });
        TextView notNowButton = (TextView) this.mMainView.findViewById(R.id.not_now_button);
        TextView moreDetailsButton = (TextView) this.mMainView.findViewById(R.id.more_details_button);
        switch (this.mUiMode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                notNowButton.setVisibility(0);
                notNowButton.setOnClickListener(this.mNoneOnClickListener);
                notNowButton.setText(getString(R.string.setup_account_skip).toUpperCase());
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                int moreDetailsButtonLabel;
                int moreDetailsButtonIcon;
                this.mContinueButton.setVisibility(8);
                moreDetailsButton.setVisibility(0);
                moreDetailsButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        PromptForFopFragment.this.mEventLog.logClickEvent(1009, null, PromptForFopFragment.this);
                        PromptForFopFragment.this.showMoreDetailsDialog();
                    }
                });
                if (this.mUiMode == 4) {
                    moreDetailsButtonLabel = R.string.setup_account_more_details;
                    moreDetailsButtonIcon = R.drawable.ic_more_details_light;
                } else {
                    moreDetailsButtonLabel = R.string.not_now;
                    moreDetailsButtonIcon = R.drawable.ic_not_now;
                }
                moreDetailsButton.setText(getString(moreDetailsButtonLabel).toUpperCase());
                moreDetailsButton.setCompoundDrawablesWithIntrinsicBounds(moreDetailsButtonIcon, 0, 0, 0);
                break;
            default:
                View continueButtonBar = this.mMainView.findViewById(R.id.continue_button_bar);
                if (continueButtonBar != null) {
                    continueButtonBar.setVisibility(8);
                }
                actionsContainer.setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.prompt_for_fop_bottom_padding));
                break;
        }
        return this.mMainView;
    }

    protected int getMainLayout() {
        return R.layout.prompt_for_fop_fragment;
    }

    protected void configureContinueButtonStyle(View button) {
        if (button instanceof IconButtonGroup) {
            IconButtonGroup iconButton = (IconButtonGroup) button;
            iconButton.setBackendForLabel(3);
            iconButton.setIconDrawable(getResources().getDrawable(R.drawable.purchase_wallet));
            iconButton.setText(getString(R.string.continue_text));
            return;
        }
        FinskyLog.wtf("Unexpected continue button type: %s", button.getClass().getSimpleName());
    }

    protected int getPrimerStringId() {
        return R.string.setup_account_primer;
    }

    private void showMoreDetailsDialog() {
        Builder builder = new Builder();
        builder.setMessageId(R.string.setup_account_more_details_message).setPositiveId(R.string.ok).setNegativeId(R.string.not_now).setCallback(this, 10, null).setEventLog(1010, null, -1, -1, this.mAccount);
        SimpleAlertDialog sad = builder.build();
        sad.setCancelable(false);
        sad.show(getFragmentManager(), "PromptForFopFragment.more_details_dialog");
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            this.mSelectedIndex = savedInstanceState.getInt("PromptForFopFragment.selected_index", this.mSelectedIndex);
        }
        if (this.mSelectedIndex >= 0) {
            selectItem(this.mSelectedIndex);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("PromptForFopFragment.selected_index", this.mSelectedIndex);
        outState.putBoolean("PromptForFopFragment.more_visible", this.mMoreVisible);
        outState.putBoolean("PromptForFopFragment.has_logged_screen", this.mHasLoggedScreen);
    }

    protected void renderActions(List<ActionEntry> actionEntries) {
        boolean enableMoreSection;
        this.mActionEntries = actionEntries;
        this.mActionsView.removeAllViews();
        this.mMoreActionsView.removeAllViews();
        this.mMoreToggler.setVisibility(8);
        if (this.mUiMode == 2 || this.mUiMode == 3 || this.mUiMode == 6) {
            actionEntries.add(createNoneEntry());
        }
        if (this.mUiMode != 3 || actionEntries.size() < 4) {
            enableMoreSection = false;
        } else {
            enableMoreSection = true;
        }
        if (enableMoreSection) {
            this.mMoreToggler.setVisibility(0);
        }
        ViewGroup parent = this.mActionsView;
        int actionEntryCount = actionEntries.size();
        int i = 0;
        while (i < actionEntryCount) {
            boolean isLast;
            if (enableMoreSection && i >= 2) {
                parent = this.mMoreActionsView;
            }
            if (i == actionEntryCount - 1) {
                isLast = true;
            } else {
                isLast = false;
            }
            addActionEntry(parent, (ActionEntry) actionEntries.get(i), i, isLast);
            i++;
        }
        if (this.mUiMode == 6) {
            selectItem(0);
        }
        syncMoreActionsVisibility();
    }

    private void toggleMore() {
        this.mMoreVisible = !this.mMoreVisible;
        if (this.mMoreVisible) {
            this.mMoreTogglerTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_more_options_light, 0, R.drawable.ic_menu_expander_maximized_light, 0);
            this.mMoreTogglerTitle.setTextColor(getResources().getColor(R.color.play_fg_purchase_secondary));
            this.mMoreToggler.showSeparator();
        } else {
            this.mMoreTogglerTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_more_options_light, 0, R.drawable.ic_menu_expander_minimized_light, 0);
            this.mMoreTogglerTitle.setTextColor(getResources().getColor(R.color.play_primary_text));
            this.mMoreToggler.hideSeparator();
        }
        syncMoreActionsVisibility();
    }

    private void syncMoreActionsVisibility() {
        int i = 8;
        if (this.mUiMode == 3) {
            ViewGroup viewGroup = this.mMoreActionsView;
            if (this.mMoreVisible) {
                i = 0;
            }
            viewGroup.setVisibility(i);
            return;
        }
        this.mMoreActionsView.setVisibility(8);
    }

    private ActionEntry createNoneEntry() {
        return new ActionEntry(getString(R.string.setup_account_remind_me_later), this.mProfile.remindMeLaterIconImage, this.mNoneOnClickListener);
    }

    protected boolean shouldRender(Instrument[] instruments) {
        if (instruments.length <= 0) {
            return true;
        }
        FinskyLog.w("Unexpected instruments found.", new Object[0]);
        notifyListenerOnAlreadySetup();
        return false;
    }

    protected void renderInstruments(Instrument[] instruments) {
    }

    protected void onInstrumentCreated(String instrumentId, String redeemedOfferHtml) {
        notifyListenerOnInstrumentCreated(redeemedOfferHtml);
    }

    protected void onStoredValueAdded(String existingStoredValueInstrumentId, String redeemedOfferHtml) {
        notifyListenerOnInstrumentCreated(redeemedOfferHtml);
    }

    protected void onPromoCodeRedeemed(RedeemCodeResult redeemCodeResult) {
        FinskyLog.wtf("Unexpected promo code redemption.", new Object[0]);
    }

    protected void onFatalError(String errorMessageHtml) {
        notifyListenerOnFatalError(errorMessageHtml);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode != 10) {
            super.onPositiveClick(requestCode, extraArguments);
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 10) {
            this.mNoneOnClickListener.onClick(null);
        } else {
            super.onNegativeClick(requestCode, extraArguments);
        }
    }

    private void addActionEntry(ViewGroup parent, ActionEntry actionEntry, final int i, boolean isLast) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getActionEntryLayout(), parent, false);
        ((TextView) view.findViewById(R.id.title)).setText(actionEntry.displayTitle);
        FifeImageView imageView = (FifeImageView) view.findViewById(R.id.image_icon);
        Image image = actionEntry.iconImage;
        if (image == null) {
            imageView.setVisibility(8);
        } else {
            imageView.setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
        }
        RadioButton radio = (RadioButton) view.findViewById(R.id.radio);
        if (this.mUiMode == 1 || this.mUiMode == 6) {
            radio.setId(i);
            radio.setClickable(false);
            radio.setVisibility(0);
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PromptForFopFragment.this.selectItem(i);
                }
            });
        } else {
            radio.setVisibility(8);
            view.setOnClickListener(actionEntry.action);
        }
        if (isLast && (view instanceof SeparatorLinearLayout)) {
            ((SeparatorLinearLayout) view).hideSeparator();
        }
        parent.addView(view);
    }

    protected int getActionEntryLayout() {
        return R.layout.prompt_for_fop_row;
    }

    private void selectItem(int selected) {
        int i = 0;
        while (i < this.mActionEntries.size()) {
            ((RadioButton) this.mMainView.findViewById(i)).setChecked(selected == i);
            i++;
        }
        this.mSelectedIndex = selected;
        this.mContinueButton.setEnabled(true);
    }

    public void onClick(View view) {
        if (this.mSelectedIndex >= 0) {
            ((ActionEntry) this.mActionEntries.get(this.mSelectedIndex)).action.onClick(view);
        }
    }

    private Listener getListener() {
        if (getActivity() instanceof Listener) {
            return (Listener) getActivity();
        }
        FinskyLog.wtf("No listener registered.", new Object[0]);
        return null;
    }

    private void notifyListenerOnInstrumentCreated(String redeemedOfferHtml) {
        Listener listener = getListener();
        if (listener != null) {
            listener.onInstrumentCreated(redeemedOfferHtml);
        }
    }

    private void notifyListenerOnFatalError(String errorMessageHtml) {
        Listener listener = getListener();
        if (listener != null) {
            listener.onFatalError(errorMessageHtml);
        }
    }

    private void notifyListenerOnNoneSelected() {
        Listener listener = getListener();
        if (listener != null) {
            listener.onNoneSelected();
        }
    }

    private void notifyListenerOnAlreadySetup() {
        Listener listener = getListener();
        if (listener != null) {
            listener.onAlreadySetup();
        }
    }

    protected int getCreditCardEventType() {
        return 350;
    }

    protected int getDcbEventType() {
        return 351;
    }

    protected int getRedeemEventType() {
        return 352;
    }

    protected int getTopupEventType() {
        return 353;
    }

    protected int getGenericInstrumentEventType() {
        return 357;
    }

    protected int getBillingProfileRequestEnum() {
        return 2;
    }

    protected void logLoading() {
        this.mEventLog.logPathImpression(0, 213, (PlayStoreUiElementNode) getActivity());
    }

    protected void logScreen() {
        if (!this.mHasLoggedScreen) {
            this.mHasLoggedScreen = true;
            this.mEventLog.logPathImpression(0, this);
        }
    }

    protected void redeemCheckoutCode() {
        startActivityForResult(RedeemCodeActivity.createIntent(this.mAccount.name, 2), 4);
    }

    protected byte[] getBackgroundEventServerLogsCookie() {
        return getArguments().getByteArray("PromptForFopFragment.server_logs_cookie");
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return (PlayStoreUiElementNode) getActivity();
    }
}
