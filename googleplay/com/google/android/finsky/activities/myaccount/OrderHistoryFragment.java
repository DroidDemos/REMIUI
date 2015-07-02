package com.google.android.finsky.activities.myaccount;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.vending.R;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.adapters.EmptyRecyclerViewAdapter;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.HeaderLayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.OrderHistoryRowView.OnRefundActionListener;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.FinskyConfigurator;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.utils.AppSupport;
import com.google.android.finsky.utils.AppSupport.RefundListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.OrderHistoryHelper;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.headerlist.PlayHeaderListLayout;

public class OrderHistoryFragment extends PageFragment implements Listener, OnRefundActionListener {
    private OrderHistoryAdapter mAdapter;
    private String mBreadcrumb;
    private DfeList mDfeList;
    private boolean mIsAdapterSet;
    private long mLastRequestTimeMs;
    private PlayRecyclerView mOrderHistoryView;
    private Bundle mRecyclerViewRestoreBundle;
    private PlayStoreUiElement mUiElementProto;

    public OrderHistoryFragment() {
        this.mRecyclerViewRestoreBundle = new Bundle();
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(11);
    }

    public static OrderHistoryFragment newInstance(String listUrl, String title, DfeToc dfeToc) {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("list_url", listUrl);
        fragment.setArguments(args);
        fragment.setDfeToc(dfeToc);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContentFrame frame = (ContentFrame) super.onCreateView(inflater, container, savedInstanceState);
        FinskyHeaderListLayout headerListLayout = this.mDataView;
        headerListLayout.configure(new FinskyConfigurator(headerListLayout.getContext()) {
            protected void addContentView(LayoutInflater inflater, ViewGroup root) {
                inflater.inflate(R.layout.order_history_list, root);
            }

            protected int getListViewId() {
                return R.id.order_history_list;
            }
        });
        headerListLayout.setContentViewId(R.id.order_history_list);
        return frame;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mBreadcrumb = getArguments().getString("title");
        this.mOrderHistoryView = (PlayRecyclerView) this.mDataView.findViewById(R.id.order_history_list);
        Resources resources = this.mOrderHistoryView.getResources();
        int horizontalPadding = UiUtils.getGridHorizontalPadding(resources) + resources.getDimensionPixelSize(R.dimen.play_card_default_inset);
        this.mOrderHistoryView.setPadding(horizontalPadding, this.mOrderHistoryView.getPaddingTop(), horizontalPadding, this.mOrderHistoryView.getPaddingBottom());
        this.mOrderHistoryView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mOrderHistoryView.setAdapter(new EmptyRecyclerViewAdapter());
        rebindActionBar();
        if (OrderHistoryHelper.hasMutationOccurredSince(this.mLastRequestTimeMs)) {
            clearDfeList();
        }
        if (isDataReady()) {
            rebindAdapter();
        } else {
            requestData();
            switchToLoading();
        }
        this.mActionBarController.enableActionBarOverlay();
    }

    protected LayoutSwitcher createLayoutSwitcher(ContentFrame frame) {
        return new HeaderLayoutSwitcher(frame, R.id.page_content, R.id.page_error_indicator, R.id.loading_indicator, this, 2);
    }

    public void rebindActionBar() {
        if (this.mDataView != null) {
            this.mDataView.setFloatingControlsBackground(new ColorDrawable(getActionBarColor()));
        }
        this.mPageFragmentHost.updateBreadcrumb(this.mBreadcrumb);
        this.mPageFragmentHost.updateCurrentBackendId(0, true);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    protected int getLayoutRes() {
        return R.layout.header_list_container;
    }

    public boolean isDataReady() {
        if (this.mDfeList == null) {
            return false;
        }
        return this.mDfeList.isReady();
    }

    protected void onInitViewBinders() {
    }

    protected void requestData() {
        clearDfeList();
        this.mDfeList = new DfeList(this.mDfeApi, getArguments().getString("list_url"), true);
        this.mDfeList.addDataChangedListener(this);
        this.mDfeList.addErrorListener(this);
        this.mDfeList.startLoadItems();
        this.mLastRequestTimeMs = System.currentTimeMillis();
    }

    private void clearDfeList() {
        if (this.mDfeList != null) {
            this.mDfeList.removeDataChangedListener(this);
            this.mDfeList.removeErrorListener(this);
            this.mDfeList = null;
        }
    }

    protected void rebindViews() {
        rebindAdapter();
    }

    private void rebindAdapter() {
        if (this.mOrderHistoryView == null) {
            FinskyLog.w("Recycler view null, ignoring.", new Object[0]);
            return;
        }
        if (this.mAdapter == null) {
            Document containerDocument = this.mDfeList.getContainerDocument();
            if (containerDocument != null) {
                FinskyEventLog.setServerLogCookie(this.mUiElementProto, containerDocument.getServerLogsCookie());
            }
            this.mAdapter = new OrderHistoryAdapter(this.mContext, this.mDfeList, this.mBitmapLoader, this.mNavigationManager, this, true, this);
        }
        if (this.mIsAdapterSet) {
            this.mAdapter.updateAdapterData(this.mDfeList);
            return;
        }
        this.mIsAdapterSet = true;
        this.mOrderHistoryView.post(new Runnable() {
            public void run() {
                if (OrderHistoryFragment.this.mOrderHistoryView != null && OrderHistoryFragment.this.mAdapter != null) {
                    OrderHistoryFragment.this.mOrderHistoryView.setAdapter(OrderHistoryFragment.this.mAdapter);
                    if (!OrderHistoryFragment.this.mRecyclerViewRestoreBundle.isEmpty()) {
                        OrderHistoryFragment.this.mAdapter.onRestoreInstanceState(OrderHistoryFragment.this.mOrderHistoryView, OrderHistoryFragment.this.mRecyclerViewRestoreBundle);
                        OrderHistoryFragment.this.mRecyclerViewRestoreBundle.clear();
                    }
                }
            }
        });
    }

    public void onDestroyView() {
        if (!(this.mOrderHistoryView == null || this.mAdapter == null)) {
            this.mAdapter.onSaveInstanceState(this.mOrderHistoryView, this.mRecyclerViewRestoreBundle);
        }
        this.mOrderHistoryView = null;
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter.onDestroy();
            this.mAdapter = null;
            this.mIsAdapterSet = false;
        }
        if (this.mDataView instanceof PlayHeaderListLayout) {
            ((PlayHeaderListLayout) this.mDataView).detach();
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
                    Toast.makeText(OrderHistoryFragment.this.mContext, R.string.refunding, 1).show();
                }

                public void onRefundComplete(boolean succeeded) {
                    if (succeeded) {
                        OrderHistoryFragment.this.refresh();
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
