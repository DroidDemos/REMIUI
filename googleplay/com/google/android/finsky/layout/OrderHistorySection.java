package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.myaccount.OrderHistoryAdapter;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.OrderHistoryRowView.OnRefundActionListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.play.image.BitmapLoader;

public class OrderHistorySection extends CardLinearLayout implements PlayStoreUiElementNode {
    private OrderHistoryAdapter mAdapter;
    protected DfeApi mDfeApi;
    private DfeList mDfeList;
    private FinskyEventLog mEventLogger;
    private ViewGroup mMoreFooter;
    protected NavigationManager mNavigationManager;
    private ViewGroup mOrderHistoryHolder;
    private PlayStoreUiElementNode mParentNode;
    private View mProgressIndicator;
    private TextView mTitleView;
    private DfeToc mToc;
    private PlayStoreUiElement mUiElementProto;

    public OrderHistorySection(Context context) {
        super(context);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(2601);
    }

    public OrderHistorySection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(2601);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitleView = (TextView) findViewById(R.id.title);
        this.mOrderHistoryHolder = (ViewGroup) findViewById(R.id.order_history_holder);
        this.mMoreFooter = (ViewGroup) findViewById(R.id.more_footer);
        this.mProgressIndicator = findViewById(R.id.loading_indicator);
    }

    public void bind(DfeApi dfeApi, DfeToc toc, String title, DfeList dfeList, BitmapLoader bitmapLoader, OnRefundActionListener onRefundActionListener, NavigationManager navManager, PlayStoreUiElementNode parentNode) {
        this.mTitleView.setText(title);
        this.mDfeApi = dfeApi;
        this.mToc = toc;
        this.mDfeList = dfeList;
        this.mNavigationManager = navManager;
        this.mParentNode = parentNode;
        this.mEventLogger = FinskyApp.get().getEventLogger(this.mDfeApi.getAccount());
        if (this.mDfeList == null || !this.mDfeList.isReady()) {
            this.mProgressIndicator.setVisibility(0);
            this.mOrderHistoryHolder.setVisibility(8);
            this.mMoreFooter.setVisibility(8);
            return;
        }
        this.mAdapter = new OrderHistoryAdapter(getContext(), this.mDfeList, bitmapLoader, this.mNavigationManager, onRefundActionListener, false, this);
        this.mProgressIndicator.setVisibility(8);
        this.mOrderHistoryHolder.setVisibility(0);
        Document containerDocument = this.mDfeList.getContainerDocument();
        if (containerDocument != null) {
            FinskyEventLog.setServerLogCookie(this.mUiElementProto, containerDocument.getServerLogsCookie());
        }
        getParentNode().childImpression(this);
        renderOrderHistoryList();
    }

    private void renderOrderHistoryList() {
        int orderHistoryCount = this.mAdapter.getItemCount();
        this.mOrderHistoryHolder.removeAllViews();
        int numRows = Math.min(5, orderHistoryCount);
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            this.mOrderHistoryHolder.addView(this.mAdapter.getView(rowIndex, this.mOrderHistoryHolder));
        }
        if (orderHistoryCount > 5) {
            this.mMoreFooter.setVisibility(0);
            this.mMoreFooter.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    OrderHistorySection.this.mEventLogger.logClickEvent(2600, null, OrderHistorySection.this);
                    OrderHistorySection.this.mNavigationManager.goToOrderHistory(OrderHistorySection.this.mDfeList.getInitialUrl(), OrderHistorySection.this.mTitleView.getText().toString(), OrderHistorySection.this.mToc);
                }
            });
            return;
        }
        this.mMoreFooter.setVisibility(8);
    }

    public void onDestroyView() {
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter.onDestroy();
            this.mAdapter = null;
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
