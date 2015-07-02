package com.google.android.finsky.billing.storedvalue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.billing.lightpurchase.PurchaseActivity;
import com.google.android.finsky.billing.lightpurchase.PurchaseParams;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.CommonDevice.TopupInfo;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;

public class StoredValueTopUpActivity extends FragmentActivity implements OnItemClickListener, Listener, SidecarFragment.Listener, ClickListener {
    private String mAccountName;
    private ButtonBar mButtonBar;
    private Document[] mDocuments;
    private int mLastShownErrorId;
    private ListSidecar mListSidecar;
    private ListView mListView;
    private View mLoadingIndicator;
    private String mSelectedDocumentFormattedAmount;

    public static final class ListSidecar extends SidecarFragment implements ErrorListener, OnDataChangedListener {
        private DfeList mDfeList;
        private VolleyError mLastError;

        private static ListSidecar newInstance(String account, String dfeListUrl) {
            Bundle args = new Bundle();
            args.putString("list_url", dfeListUrl);
            args.putString("authAccount", account);
            ListSidecar result = new ListSidecar();
            result.setArguments(args);
            return result;
        }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            DfeApi dfeApi = FinskyApp.get().getDfeApi(getArguments().getString("authAccount"));
            if (this.mDfeList == null) {
                this.mDfeList = new DfeList(dfeApi, getArguments().getString("list_url"), false);
            } else {
                this.mDfeList.setDfeApi(dfeApi);
            }
            this.mDfeList.addDataChangedListener(this);
            this.mDfeList.addErrorListener(this);
        }

        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putParcelable("list", this.mDfeList);
        }

        protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
            super.restoreFromSavedInstanceState(savedInstanceState);
            this.mDfeList = (DfeList) savedInstanceState.getParcelable("list");
        }

        public void load() {
            this.mDfeList.startLoadItems();
            setState(1, 0);
        }

        public Document[] getDocuments() {
            return this.mDfeList.getContainerDocument().getChildren();
        }

        public VolleyError getLastError() {
            return this.mLastError;
        }

        public void onDataChanged() {
            if (this.mDfeList.getContainerDocument() == null || this.mDfeList.getContainerDocument().getChildCount() <= 0) {
                setState(3, 1);
            } else {
                setState(2, 0);
            }
        }

        public void onErrorResponse(VolleyError volleyError) {
            this.mLastError = volleyError;
            setState(3, 0);
        }
    }

    public StoredValueTopUpActivity() {
        this.mLastShownErrorId = 0;
    }

    public static Intent createIntent(String accountName, TopupInfo topupInfo) {
        Intent result = new Intent(FinskyApp.get(), StoredValueTopUpActivity.class);
        result.putExtra("authAccount", accountName);
        result.putExtra("list_url", topupInfo.optionsListUrl);
        return result;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_topup_chooser);
        this.mLoadingIndicator = findViewById(R.id.loading_indicator);
        this.mListView = (ListView) findViewById(R.id.choices);
        this.mButtonBar = (ButtonBar) findViewById(R.id.button_bar);
        this.mButtonBar.setPositiveButtonTitle((int) R.string.ok);
        this.mButtonBar.setNegativeButtonTitle((int) R.string.cancel);
        this.mButtonBar.setClickListener(this);
        syncPositiveButton();
        this.mAccountName = getIntent().getStringExtra("authAccount");
        if (savedInstanceState == null) {
            this.mListSidecar = ListSidecar.newInstance(this.mAccountName, getIntent().getStringExtra("list_url"));
            getSupportFragmentManager().beginTransaction().add(this.mListSidecar, "list_sidecar").commit();
            return;
        }
        this.mSelectedDocumentFormattedAmount = savedInstanceState.getString("selected_document_formatted_amount");
        this.mLastShownErrorId = savedInstanceState.getInt("last_shown_error");
        this.mListSidecar = (ListSidecar) getSupportFragmentManager().findFragmentByTag("list_sidecar");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("last_shown_error", this.mLastShownErrorId);
        outState.putString("selected_document_formatted_amount", this.mSelectedDocumentFormattedAmount);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        syncPositiveButton();
    }

    protected void onStart() {
        super.onStart();
        this.mListSidecar.setListener(this);
        if (this.mListSidecar.getState() == 0) {
            this.mListSidecar.load();
        }
    }

    protected void onStop() {
        this.mListSidecar.setListener(null);
        super.onStop();
    }

    public void onStateChange(SidecarFragment fragment) {
        this.mLoadingIndicator.setVisibility(8);
        this.mListView.setVisibility(8);
        this.mButtonBar.setVisibility(4);
        int state = fragment.getState();
        if (state == 2) {
            this.mDocuments = this.mListSidecar.getDocuments();
            ArrayList<String> titles = Lists.newArrayList(this.mDocuments.length);
            for (Document doc : this.mDocuments) {
                titles.add(doc.getTitle());
            }
            this.mListView.setAdapter(new ArrayAdapter(this, 17367055, titles));
            this.mListView.setItemsCanFocus(false);
            this.mListView.setChoiceMode(1);
            this.mListView.setOnItemClickListener(this);
            this.mListView.setVisibility(0);
            this.mButtonBar.setVisibility(0);
            syncPositiveButton();
        } else if (state == 1) {
            this.mLoadingIndicator.setVisibility(0);
        } else if (fragment.getState() != 3) {
        } else {
            if (this.mLastShownErrorId == fragment.getStateInstance()) {
                FinskyLog.d("Already showed error %d, ignoring.", Integer.valueOf(this.mLastShownErrorId));
                return;
            }
            this.mLastShownErrorId = fragment.getStateInstance();
            String errorMessage = null;
            if (fragment.getSubstate() == 0) {
                errorMessage = ErrorStrings.get(FinskyApp.get(), this.mListSidecar.getLastError());
            } else if (fragment.getSubstate() == 1) {
                errorMessage = FinskyApp.get().getString(R.string.topup_not_available);
            }
            if (errorMessage != null) {
                Builder builder = new Builder();
                builder.setMessage(errorMessage).setPositiveId(R.string.ok).setCallback(null, 0, null);
                builder.build().show(getSupportFragmentManager(), "error_dialog");
                return;
            }
            FinskyLog.w("Received error without error message.", new Object[0]);
            setResult(0);
            finish();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        syncPositiveButton();
    }

    private void syncPositiveButton() {
        this.mButtonBar.setPositiveButtonEnabled(this.mListView.getCheckedItemPosition() != -1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Intent returnedData = new Intent();
            if (resultCode == -1) {
                Toast.makeText(this, getString(R.string.topup_success, new Object[]{this.mSelectedDocumentFormattedAmount}), 0).show();
            }
            setResult(resultCode, returnedData);
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onPositiveButtonClick() {
        Document doc = this.mDocuments[this.mListView.getCheckedItemPosition()];
        Offer offer = doc.getOffer(1);
        if (offer == null) {
            FinskyLog.w("Document selected without PURCHASE offer. Ignoring.", new Object[0]);
            return;
        }
        this.mSelectedDocumentFormattedAmount = offer.formattedAmount;
        startActivityForResult(PurchaseActivity.createIntent(AccountHandler.findAccount(this.mAccountName, this), PurchaseParams.builder().setDocument(doc).setOfferType(1).build(), doc.getServerLogsCookie(), null), 1);
    }

    public void onNegativeButtonClick() {
        setResult(0);
        finish();
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 0) {
            setResult(0);
            finish();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }
}
