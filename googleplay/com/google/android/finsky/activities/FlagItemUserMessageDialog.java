package com.google.android.finsky.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.vending.R;

public class FlagItemUserMessageDialog extends DialogFragment {
    private String mMessage;

    public interface Listener {
        void onPositiveClick(String str);
    }

    public static FlagItemUserMessageDialog newInstance(int textEntryStringResId) {
        FlagItemUserMessageDialog dialogFragment = new FlagItemUserMessageDialog();
        Bundle arguments = new Bundle();
        arguments.putInt("prompt_string_res_id", textEntryStringResId);
        dialogFragment.setArguments(arguments);
        return dialogFragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle source;
        Context context = getActivity();
        Bundle arguments = getArguments();
        int promptStringResId = arguments.getInt("prompt_string_res_id");
        if (savedInstanceState != null) {
            source = savedInstanceState;
        } else {
            source = arguments;
        }
        this.mMessage = source.getString("previous_message");
        View flagMessageView = LayoutInflater.from(getActivity()).inflate(R.layout.flag_item_message, null, false);
        final TextView commentBox = (TextView) flagMessageView.findViewById(R.id.flag_message);
        commentBox.setText(this.mMessage);
        Builder builder = new Builder(context);
        builder.setTitle(promptStringResId);
        builder.setView(flagMessageView);
        builder.setCancelable(true);
        builder.setPositiveButton(17039370, new OnClickListener() {
            public void onClick(DialogInterface di, int buttonIndex) {
                Listener l = FlagItemUserMessageDialog.this.getListener();
                if (l != null) {
                    l.onPositiveClick(commentBox.getText().toString());
                }
            }
        });
        builder.setNegativeButton(17039360, null);
        return builder.create();
    }

    public void onStart() {
        super.onStart();
        AlertDialog alertDialog = (AlertDialog) getDialog();
        final Button okButton = alertDialog.getButton(-1);
        okButton.setEnabled(!TextUtils.isEmpty(this.mMessage));
        ((TextView) alertDialog.findViewById(R.id.flag_message)).addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                FlagItemUserMessageDialog.this.mMessage = s == null ? null : s.toString();
                okButton.setEnabled(!TextUtils.isEmpty(FlagItemUserMessageDialog.this.mMessage));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    public void onSaveInstanceState(Bundle icicle) {
        if (!TextUtils.isEmpty(this.mMessage)) {
            icicle.putString("previous_message", this.mMessage);
        }
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
