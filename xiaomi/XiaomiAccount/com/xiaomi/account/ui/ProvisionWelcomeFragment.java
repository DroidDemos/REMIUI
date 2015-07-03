package com.xiaomi.account.ui;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import com.xiaomi.account.R;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.ui.SimpleDialogFragment;
import com.xiaomi.passport.ui.SimpleDialogFragment.AlertDialogFragmentBuilder;
import com.xiaomi.passport.ui.WelcomeFragment.WelcomeActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProvisionWelcomeFragment extends Fragment implements OnClickListener, OnItemClickListener {
    private static final String TAG = "ProvisionWelcomeFragment";
    private String addAccountPrompt;
    private int currentSelectedIndex;
    private AccountActionAdapter mActionAdapter;
    private ListView mActionListView;
    private Button mButtonBack;
    private Button mButtonNext;
    private View mTextSkipLogin;
    private WelcomeActionListener mWelcomeActionListener;

    private class AccountActionAdapter extends ArrayAdapter<String> {
        public AccountActionAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewGroup vg = (ViewGroup) super.getView(position, convertView, parent);
            ((RadioButton) vg.findViewById(R.id.item_view)).setChecked(position == ProvisionWelcomeFragment.this.currentSelectedIndex);
            return vg;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        View view = inflater.inflate(R.layout.welcome_from_provision, container, false);
        this.mButtonBack = (Button) view.findViewById(R.id.btn_back);
        this.mButtonBack.setOnClickListener(this);
        this.mTextSkipLogin = view.findViewById(R.id.tv_skip_login);
        if (this.mTextSkipLogin != null) {
            this.mTextSkipLogin.setVisibility(0);
            this.mTextSkipLogin.setOnClickListener(this);
        }
        ArrayList<String> actionList = new ArrayList();
        actionList.add(getString(R.string.passport_login));
        actionList.add(getString(R.string.passport_reg_new));
        this.mActionListView = (ListView) view.findViewById(16908298);
        this.mActionAdapter = new AccountActionAdapter(getActivity(), R.layout.provision_list_item_view, R.id.item_view, actionList);
        this.mActionListView.setAdapter(this.mActionAdapter);
        this.mActionListView.setOnItemClickListener(this);
        this.mButtonNext = (Button) view.findViewById(R.id.btn_next);
        this.mButtonNext.setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        String serviceUrl = null;
        if (view == this.mButtonNext) {
            if (this.currentSelectedIndex == 0) {
                Bundle args = getArguments();
                if (args != null) {
                    serviceUrl = args.getString(Constants.EXTRA_SERVICE_URL);
                }
                this.mWelcomeActionListener.onStartSignIn(serviceUrl);
                return;
            }
            this.mWelcomeActionListener.onStartSignUp();
        } else if (view == this.mTextSkipLogin) {
            SimpleDialogFragment dialog = new AlertDialogFragmentBuilder(1).setTitle(getString(R.string.passport_skip_setup_account_title)).setMessage(getString(R.string.passport_skip_setup_account_msg)).create();
            dialog.setNegativeButton(17039360, null);
            dialog.setPositiveButton(R.string.passport_confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ProvisionWelcomeFragment.this.mWelcomeActionListener.onSkipLogin();
                }
            });
            dialog.show(getFragmentManager(), "SkipAlert");
        } else if (view == this.mButtonBack) {
            this.mWelcomeActionListener.onGoBack();
        }
    }

    public void setWelcomeActionListener(WelcomeActionListener welcomeActionListener) {
        this.mWelcomeActionListener = welcomeActionListener;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.currentSelectedIndex = position;
        if (this.mActionAdapter != null) {
            this.mActionAdapter.notifyDataSetChanged();
        }
    }
}
