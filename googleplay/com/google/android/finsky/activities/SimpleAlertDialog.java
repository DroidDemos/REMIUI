package com.google.android.finsky.activities;

import android.accounts.Account;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;

public class SimpleAlertDialog extends DialogFragment {
    private ConfigurableView mConfigurableView;
    private PlayStoreUiElementNode mDialogNode;
    private FinskyEventLog mEventLogger;
    private boolean mHasBeenDismissed;

    public interface Listener {
        void onNegativeClick(int i, Bundle bundle);

        void onPositiveClick(int i, Bundle bundle);
    }

    public static class Builder {
        private Bundle mArguments;
        private Fragment mTarget;

        public Builder() {
            this.mArguments = new Bundle();
            this.mTarget = null;
        }

        public Builder setMessageId(int messageId) {
            this.mArguments.putInt("message_id", messageId);
            return this;
        }

        public Builder setMessage(String message) {
            this.mArguments.putString("message", message);
            return this;
        }

        public Builder setMessageHtml(String messageHtml) {
            this.mArguments.putString("messageHtml", messageHtml);
            return this;
        }

        public Builder setLayoutId(int layoutId) {
            this.mArguments.putInt("layoutId", layoutId);
            return this;
        }

        public Builder setTitleId(int titleId) {
            this.mArguments.putInt("title_id", titleId);
            return this;
        }

        public Builder setPositiveId(int positiveId) {
            this.mArguments.putInt("positive_id", positiveId);
            return this;
        }

        public Builder setNegativeId(int negativeId) {
            this.mArguments.putInt("negative_id", negativeId);
            return this;
        }

        public Builder setThemeId(int themeId) {
            this.mArguments.putInt("theme_id", themeId);
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.mArguments.putBoolean("cancel_on_touch_outside", canceledOnTouchOutside);
            return this;
        }

        public Builder setViewConfiguration(Bundle configArguments) {
            this.mArguments.putBundle("config_arguments", configArguments);
            return this;
        }

        public Builder setEventLog(int impressionType, byte[] serverLogsCookie, int positiveClickType, int negativeClickType, Account account) {
            if (account == null) {
                account = FinskyApp.get().getCurrentAccount();
            }
            this.mArguments.putParcelable("log_account", account);
            this.mArguments.putInt("impression_type", impressionType);
            this.mArguments.putByteArray("impression_cookie", serverLogsCookie);
            this.mArguments.putInt("click_event_type_positive", positiveClickType);
            this.mArguments.putInt("click_event_type_negative", negativeClickType);
            return this;
        }

        public Builder setCallback(Fragment target, int requestCode, Bundle extraArguments) {
            this.mTarget = target;
            if (!(extraArguments == null && requestCode == 0)) {
                this.mArguments.putBundle("extra_arguments", extraArguments);
                this.mArguments.putInt("target_request_code", requestCode);
            }
            return this;
        }

        public SimpleAlertDialog build() {
            SimpleAlertDialog dialogFragment = new SimpleAlertDialog();
            configureDialog(dialogFragment);
            return dialogFragment;
        }

        public void configureDialog(SimpleAlertDialog dialogFragment) {
            dialogFragment.setArguments(this.mArguments);
            if (this.mTarget != null) {
                dialogFragment.setTargetFragment(this.mTarget, 0);
            }
        }
    }

    public interface ConfigurableView {
        void configureView(Bundle bundle);

        Bundle getResult();
    }

    public SimpleAlertDialog() {
        this.mHasBeenDismissed = false;
        this.mConfigurableView = null;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        setUpEventLogger(arguments);
        if (savedInstanceState == null && this.mDialogNode != null) {
            this.mEventLogger.logPathImpression(0, this.mDialogNode);
        }
        return buildAlertDialog(arguments);
    }

    private void setMessageViewClickable(final AlertDialog alertDialog) {
        alertDialog.setOnShowListener(new OnShowListener() {
            public void onShow(DialogInterface dialog) {
                ((TextView) alertDialog.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }

    private void setUpEventLogger(Bundle arguments) {
        Account logAccount = (Account) arguments.getParcelable("log_account");
        if (logAccount == null) {
            logAccount = FinskyApp.get().getCurrentAccount();
        }
        this.mEventLogger = FinskyApp.get().getEventLogger(logAccount);
        this.mDialogNode = null;
        if (arguments.containsKey("impression_type")) {
            this.mDialogNode = new GenericUiElementNode(arguments.getInt("impression_type"), arguments.getByteArray("impression_cookie"), null, null);
        }
    }

    private void setCustomLayout(AlertDialog alertDialog, Bundle arguments) {
        View view = alertDialog.getLayoutInflater().inflate(arguments.getInt("layoutId"), null);
        alertDialog.setView(view);
        if (view instanceof ConfigurableView) {
            this.mConfigurableView = (ConfigurableView) view;
            if (arguments.containsKey("config_arguments")) {
                this.mConfigurableView.configureView(arguments.getBundle("config_arguments"));
            }
        }
    }

    private AlertDialog buildAlertDialog(Bundle arguments) {
        android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(new ContextThemeWrapper(getActivity(), arguments.containsKey("theme_id") ? arguments.getInt("theme_id") : R.style.FinskyDialogTheme));
        if (arguments.containsKey("title_id")) {
            b.setTitle(arguments.getInt("title_id"));
        }
        if (arguments.containsKey("message_id")) {
            b.setMessage(arguments.getInt("message_id"));
        } else if (arguments.containsKey("message")) {
            b.setMessage(arguments.getString("message"));
        } else if (arguments.containsKey("messageHtml")) {
            b.setMessage(Html.fromHtml(arguments.getString("messageHtml")));
        }
        if (arguments.containsKey("positive_id")) {
            b.setPositiveButton(arguments.getInt("positive_id"), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    SimpleAlertDialog.this.doPositiveClick();
                }
            });
        }
        if (arguments.containsKey("negative_id")) {
            b.setNegativeButton(arguments.getInt("negative_id"), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    SimpleAlertDialog.this.doNegativeClick();
                }
            });
        }
        AlertDialog alertDialog = b.create();
        if (arguments.containsKey("layoutId")) {
            setCustomLayout(alertDialog, arguments);
        } else {
            setMessageViewClickable(alertDialog);
        }
        if (arguments.containsKey("cancel_on_touch_outside")) {
            alertDialog.setCanceledOnTouchOutside(arguments.getBoolean("cancel_on_touch_outside"));
        }
        return alertDialog;
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        doNegativeClick();
    }

    private void doPositiveClick() {
        dismiss();
        if (!this.mHasBeenDismissed) {
            this.mHasBeenDismissed = true;
            Bundle arguments = getArguments();
            int requestCode = arguments.getInt("target_request_code");
            Bundle extraArguments = addViewResults(arguments.getBundle("extra_arguments"));
            int clickEventTypePositive = arguments.getInt("click_event_type_positive", -1);
            if (clickEventTypePositive != -1) {
                this.mEventLogger.logClickEvent(clickEventTypePositive, null, this.mDialogNode);
            }
            Listener l = getListener();
            if (l != null) {
                l.onPositiveClick(requestCode, extraArguments);
            }
            onPositiveClick();
        }
    }

    private void doNegativeClick() {
        dismiss();
        if (!this.mHasBeenDismissed) {
            this.mHasBeenDismissed = true;
            Bundle arguments = getArguments();
            int requestCode = arguments.getInt("target_request_code");
            Bundle extraArguments = addViewResults(arguments.getBundle("extra_arguments"));
            int clickEventTypeNegative = arguments.getInt("click_event_type_negative", -1);
            if (clickEventTypeNegative != -1) {
                this.mEventLogger.logClickEvent(clickEventTypeNegative, null, this.mDialogNode);
            }
            Listener l = getListener();
            if (l != null) {
                l.onNegativeClick(requestCode, extraArguments);
            }
            onNegativeClick();
        }
    }

    private Bundle addViewResults(Bundle arguments) {
        if (this.mConfigurableView != null) {
            Bundle viewResultBundle = this.mConfigurableView.getResult();
            if (viewResultBundle != null) {
                if (arguments == null) {
                    arguments = new Bundle();
                }
                arguments.putAll(viewResultBundle);
            }
        }
        return arguments;
    }

    protected void onPositiveClick() {
    }

    protected void onNegativeClick() {
    }

    private Listener getListener() {
        Fragment f = getTargetFragment();
        if (f instanceof Listener) {
            return (Listener) f;
        }
        Activity a = getActivity();
        if (a instanceof Listener) {
            return (Listener) a;
        }
        return null;
    }
}
