package com.google.android.finsky.activities.myaccount;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.FinskyRecyclerViewAdapter;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.OrderHistoryRowView;
import com.google.android.finsky.layout.OrderHistoryRowView.OnRefundActionListener;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.image.BitmapLoader;

public class OrderHistoryAdapter extends FinskyRecyclerViewAdapter {
    private final BitmapLoader mBitmapLoader;
    private final FinskyEventLog mEventLogger;
    private final boolean mIsFullList;
    private final int mLeadingExtraSpacerHeight;
    private final int mLeadingSpacerHeight;
    private final OnRefundActionListener mOnRefundActionListener;
    private final PlayStoreUiElementNode mParentNode;
    private int mSelectedPosition;
    private OrderHistoryRowView mSelectedRowView;

    public OrderHistoryAdapter(Context context, DfeList dfeList, BitmapLoader bitmapLoader, NavigationManager navigationManager, OnRefundActionListener onRefundActionListener, boolean isFullList, PlayStoreUiElementNode parentNode) {
        super(context, navigationManager, dfeList);
        this.mSelectedPosition = -1;
        this.mBitmapLoader = bitmapLoader;
        this.mOnRefundActionListener = onRefundActionListener;
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(context, 2, 0);
        Resources res = context.getResources();
        this.mLeadingExtraSpacerHeight = res.getDimensionPixelSize(R.dimen.card_list_vpadding) + res.getDimensionPixelSize(R.dimen.play_card_default_inset);
        this.mIsFullList = isFullList;
        this.mParentNode = parentNode;
        this.mEventLogger = FinskyApp.get().getEventLogger();
    }

    public void onSaveInstanceState(PlayRecyclerView view, Bundle bundle) {
        super.onSaveInstanceState(view, bundle);
        bundle.putInt("selectedPosition", this.mSelectedPosition);
    }

    public void onRestoreInstanceState(PlayRecyclerView view, Bundle restoreBundle) {
        super.onRestoreInstanceState(view, restoreBundle);
        this.mSelectedPosition = restoreBundle.getInt("selectedPosition", -1);
    }

    public int getItemCount() {
        int countData = this.mContainerList.getCount();
        int countAll = countData;
        if (this.mIsFullList) {
            countAll += 2;
        }
        if (countData == 0 && !this.mContainerList.isMoreAvailable()) {
            countAll++;
        }
        if (getFooterMode() != FooterMode.NONE) {
            return countAll + 1;
        }
        return countAll;
    }

    public int getItemViewType(int position) {
        if (this.mIsFullList) {
            if (position == 0) {
                return 0;
            }
            if (position == 1) {
                return 1;
            }
        }
        if (this.mContainerList.getCount() == 0 && !this.mContainerList.isMoreAvailable()) {
            return 3;
        }
        if (position != getItemCount() - 1) {
            return 2;
        }
        FooterMode footerMode = getFooterMode();
        if (footerMode == FooterMode.LOADING) {
            return 4;
        }
        if (footerMode == FooterMode.ERROR) {
            return 5;
        }
        if (footerMode == FooterMode.NONE) {
            return 2;
        }
        FinskyLog.wtf("No footer or item in last row", new Object[0]);
        return 5;
    }

    private Document getItem(int position) {
        if (this.mIsFullList) {
            if (position <= 1) {
                return null;
            }
            position -= 2;
        }
        return (Document) this.mContainerList.getItem(position);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                v = inflate(R.layout.play_list_vspacer, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                v = inflate(R.layout.order_history_row, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                v = inflate(R.layout.order_history_no_result_row, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                v = createLoadingFooterView(parent);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                v = createErrorFooterView(parent);
                break;
            default:
                throw new IllegalStateException("Unknown type for getView " + viewType);
        }
        return new PlayRecyclerView.ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int itemViewType = viewHolder.getItemViewType();
        View v = viewHolder.itemView;
        switch (itemViewType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                bindLeadingSpacer(v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                bindExtraLeadingSpacer(v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                bindOrderHistoryRowView(v, position);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                bindLoadingFooterView(v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                bindErrorFooterView(v);
                return;
            default:
                throw new IllegalStateException("Unknown type for getView " + itemViewType);
        }
    }

    public void onDestroyView() {
    }

    private void bindLeadingSpacer(View view) {
        view.getLayoutParams().height = this.mLeadingSpacerHeight;
        view.setId(R.id.play_header_spacer);
    }

    private void bindExtraLeadingSpacer(View view) {
        view.getLayoutParams().height = this.mLeadingExtraSpacerHeight;
    }

    private void bindOrderHistoryRowView(View view, final int position) {
        boolean selected;
        int collapsedBackgroundResourceId;
        final OrderHistoryRowView rowView = (OrderHistoryRowView) view;
        if (this.mSelectedPosition == position) {
            selected = true;
        } else {
            selected = false;
        }
        if (this.mSelectedRowView == rowView) {
            this.mSelectedRowView = null;
        }
        if (selected) {
            this.mSelectedRowView = rowView;
        }
        Document item = getItem(position);
        if (this.mIsFullList) {
            boolean isFirstOrder;
            boolean isLastOrder;
            if (item == this.mContainerList.getItem(0)) {
                isFirstOrder = true;
            } else {
                isFirstOrder = false;
            }
            if (item == this.mContainerList.getItem(this.mContainerList.getCount() - 1)) {
                isLastOrder = true;
            } else {
                isLastOrder = false;
            }
            if (isFirstOrder && isLastOrder) {
                collapsedBackgroundResourceId = R.drawable.my_account_order_single;
            } else if (isFirstOrder) {
                collapsedBackgroundResourceId = R.drawable.my_account_order_first;
            } else if (isLastOrder) {
                collapsedBackgroundResourceId = R.drawable.my_account_order_last;
            } else {
                collapsedBackgroundResourceId = R.color.play_card_light_background;
            }
        } else {
            collapsedBackgroundResourceId = 0;
        }
        rowView.bind(item, this.mBitmapLoader, this.mNavigationManager, Boolean.valueOf(selected), this.mParentNode, collapsedBackgroundResourceId, this.mOnRefundActionListener);
        rowView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                OrderHistoryAdapter.this.mEventLogger.logClickEvent(2604, null, rowView);
                if (rowView.toggleExpansion()) {
                    if (!(OrderHistoryAdapter.this.mSelectedPosition == -1 || OrderHistoryAdapter.this.mSelectedRowView == null)) {
                        OrderHistoryAdapter.this.mSelectedRowView.toggleExpansion();
                    }
                    OrderHistoryAdapter.this.mSelectedPosition = position;
                    OrderHistoryAdapter.this.mSelectedRowView = rowView;
                    return;
                }
                OrderHistoryAdapter.this.mSelectedPosition = -1;
                OrderHistoryAdapter.this.mSelectedRowView = null;
            }
        });
    }

    public View getView(int position, ViewGroup parent) {
        ViewHolder holder = createViewHolder(parent, getItemViewType(position));
        bindViewHolder(holder, position);
        return holder.itemView;
    }
}
