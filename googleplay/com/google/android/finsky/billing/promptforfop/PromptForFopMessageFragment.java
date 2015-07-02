package com.google.android.finsky.billing.promptforfop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.layout.PlayActionButton;

public class PromptForFopMessageFragment extends LoggingFragment implements OnClickListener {

    public interface Listener {
        void onContinueClicked();
    }

    public static PromptForFopMessageFragment newInstance(String accountName, String message, int playlogUiElementType) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putString("PromptForFopMessageFragment.message", message);
        args.putInt("PromptForFopMessageFragment.playlog_ui_element_type", playlogUiElementType);
        PromptForFopMessageFragment result = new PromptForFopMessageFragment();
        result.setArguments(args);
        return result;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.prompt_for_fop_message_fragment, container, false);
        ((TextView) view.findViewById(R.id.message)).setText(getArguments().getString("PromptForFopMessageFragment.message"));
        ((PlayActionButton) view.findViewById(R.id.positive_button)).configure(3, (int) R.string.continue_text, (OnClickListener) this);
        return view;
    }

    public void onClick(View view) {
        if (getActivity() instanceof Listener) {
            logClickEvent(1006);
            ((Listener) getActivity()).onContinueClicked();
            return;
        }
        FinskyLog.wtf("Parent activity expected to implement Listener interface.", new Object[0]);
    }

    protected int getPlayStoreUiElementType() {
        return getArguments().getInt("PromptForFopMessageFragment.playlog_ui_element_type");
    }
}
