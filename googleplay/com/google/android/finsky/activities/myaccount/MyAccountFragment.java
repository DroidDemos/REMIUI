package com.google.android.finsky.activities.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.billing.PaymentMethodsFragment;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.OrderHistoryRowView.OnRefundActionListener;
import com.google.android.finsky.layout.OrderHistorySection;
import com.google.android.finsky.protos.MyAccount.MyAccountResponse;
import com.google.android.finsky.utils.AppSupport;
import com.google.android.finsky.utils.AppSupport.RefundListener;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.OrderHistoryHelper;
import com.google.android.finsky.utils.UiUtils;

public class MyAccountFragment extends PageFragment implements Listener, OnRefundActionListener {
    private String mBreadcrumb;
    private long mLastRequestTimeMs;
    private DfeList mOrderHistoryDfeList;
    private OrderHistorySection mOrderHistoryView;
    private MyAccountResponse mResponse;
    private PlayStoreUiElement mUiElementProto;

    public MyAccountFragment() {
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(10);
    }

    public static MyAccountFragment newInstance() {
        MyAccountFragment fragment = new MyAccountFragment();
        fragment.setDfeToc(FinskyApp.get().getToc());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mBreadcrumb = this.mContext.getString(R.string.side_drawer_my_account);
        this.mOrderHistoryView = (OrderHistorySection) this.mDataView.findViewById(R.id.order_history);
        this.mOrderHistoryView.setVisibility(8);
        rebindActionBar();
        this.mActionBarController.disableActionBarOverlay();
        if (OrderHistoryHelper.hasMutationOccurredSince(this.mLastRequestTimeMs)) {
            clearOrderHistoryDfeList();
        }
        if (isDataReady()) {
            rebindViews();
            return;
        }
        requestData();
        bindPaymentMethodsFragment();
        switchToLoadingImmediately();
    }

    public void rebindActionBar() {
        this.mPageFragmentHost.updateBreadcrumb(this.mBreadcrumb);
        this.mPageFragmentHost.updateCurrentBackendId(0, false);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    protected int getLayoutRes() {
        return R.layout.my_account_panel;
    }

    public boolean isDataReady() {
        return (this.mResponse == null || this.mOrderHistoryDfeList == null || !this.mOrderHistoryDfeList.isReady()) ? false : true;
    }

    protected void onInitViewBinders() {
    }

    protected void requestData() {
        if (this.mResponse == null) {
            this.mDfeApi.getMyAccount(new Response.Listener<MyAccountResponse>() {
                public void onResponse(MyAccountResponse myAccountResponse) {
                    MyAccountFragment.this.mResponse = myAccountResponse;
                    MyAccountFragment.this.handleMyAccountResponse();
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    FinskyLog.e("Could not retrieve my account content: %s", volleyError);
                    MyAccountFragment.this.switchToError(ErrorStrings.get(MyAccountFragment.this.mContext, volleyError));
                }
            });
        } else {
            handleMyAccountResponse();
        }
    }

    private void handleMyAccountResponse() {
        switchToData();
        rebindViews();
        clearOrderHistoryDfeList();
        this.mOrderHistoryDfeList = new DfeList(this.mDfeApi, this.mResponse.purchaseHistoryMetadata.listUrl, true);
        this.mOrderHistoryDfeList.addDataChangedListener(this);
        this.mOrderHistoryDfeList.addErrorListener(this);
        this.mOrderHistoryDfeList.startLoadItems();
        this.mLastRequestTimeMs = System.currentTimeMillis();
    }

    private void clearOrderHistoryDfeList() {
        if (this.mOrderHistoryDfeList != null) {
            this.mOrderHistoryDfeList.removeDataChangedListener(this);
            this.mOrderHistoryDfeList.removeErrorListener(this);
            this.mOrderHistoryDfeList = null;
        }
    }

    protected void rebindViews() {
        View view = getView();
        int sideMargin = UiUtils.getGridHorizontalPadding(view.getResources());
        view.setPadding(sideMargin, view.getPaddingTop(), sideMargin, view.getPaddingBottom());
        bindPaymentMethodsFragment();
        bindOrderHistoryView();
    }

    public void onRetry() {
        super.onRetry();
        PaymentMethodsFragment fragment = (PaymentMethodsFragment) getChildFragmentManager().findFragmentById(R.id.payment_methods);
        if (fragment != null) {
            fragment.onRetry();
        }
    }

    private void bindPaymentMethodsFragment() {
        if (getChildFragmentManager().findFragmentById(R.id.payment_methods) == null) {
            Fragment fragment = PaymentMethodsFragment.newInstance(FinskyApp.get().getCurrentAccount());
            getChildFragmentManager().beginTransaction().add((int) R.id.payment_methods, fragment).setTransition(4099).commit();
            fragment.setParentDataView(this.mDataView);
        }
    }

    private void bindOrderHistoryView() {
        this.mOrderHistoryView.setVisibility(0);
        this.mOrderHistoryView.bind(this.mDfeApi, getToc(), this.mResponse.purchaseHistoryMetadata.header, this.mOrderHistoryDfeList, this.mBitmapLoader, this, this.mNavigationManager, this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.payment_methods);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onDestroyView() {
        if (this.mOrderHistoryView != null) {
            this.mOrderHistoryView.onDestroyView();
            this.mOrderHistoryView = null;
        }
        if (this.mOrderHistoryDfeList != null) {
            this.mOrderHistoryDfeList.removeDataChangedListener(this);
        }
        super.onDestroyView();
    }

    public void onRefundAction(String packageName, String appRefundAccount) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentByTag("refund_confirm") == null) {
            Builder builder = new Builder();
            builder.setMessageId(R.string.uninstall_refund_confirmation_body).setPositiveId(R.string.yes).setNegativeId(R.string.no);
            Bundle extraArguments = new Bundle();
            extraArguments.putString("package_name", packageName);
            extraArguments.putString("account_name", appRefundAccount);
            builder.setCallback(this, 1, extraArguments);
            builder.build().show(fragmentManager, "refund_confirm");
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            AppSupport.silentRefund(getFragmentManager(), extraArguments.getString("package_name"), extraArguments.getString("account_name"), true, new RefundListener() {
                public void onRefundStart() {
                    Toast.makeText(MyAccountFragment.this.mContext, R.string.refunding, 1).show();
                }

                public void onRefundComplete(boolean succeeded) {
                    if (succeeded) {
                        MyAccountFragment.this.refresh();
                    }
                }
            });
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }
}
