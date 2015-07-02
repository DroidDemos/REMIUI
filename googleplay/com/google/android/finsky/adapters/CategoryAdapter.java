package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.adapters.QuickLinkHelper.QuickLinkInfo;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.layout.CategoryRow;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Browse.BrowseLink;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.IntMath;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;

public class CategoryAdapter extends Adapter {
    private final int mBackendId;
    private final BitmapLoader mBitmapLoader;
    private final BrowseLink[] mCategories;
    private final LayoutInflater mLayoutInflater;
    private final int mLeadingExtraSpacerHeight;
    private final int mLeadingSpacerHeight;
    private final NavigationManager mNavigationManager;
    private final int mNumQuickLinksPerRow;
    private final PlayStoreUiElementNode mParent;
    private final int mQuickLinkRowCount;
    private final int mQuickLinkRowPadding;
    private final QuickLinkInfo[] mQuickLinks;
    private final String mSubheaderTitle;
    private final ColorStateList mTextColor;
    private final DfeToc mToc;

    public CategoryAdapter(Context context, BrowseLink[] categories, NavigationManager navigationManager, int backendId, DfeToc toc, BitmapLoader bitmapLoader, QuickLinkInfo[] quickLinks, String subheaderTitle, int tabMode, PlayStoreUiElementNode parent) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mCategories = categories;
        this.mNavigationManager = navigationManager;
        this.mBackendId = backendId;
        this.mTextColor = CorpusResourceUtils.getSecondaryTextColor(context, backendId);
        this.mToc = toc;
        this.mBitmapLoader = bitmapLoader;
        this.mSubheaderTitle = subheaderTitle;
        Resources res = context.getResources();
        this.mNumQuickLinksPerRow = UiUtils.getFeaturedGridColumnCount(context.getResources(), (double) (((float) context.getResources().getInteger(R.integer.category_tab_width_in_percent)) / 100.0f));
        this.mQuickLinks = quickLinks;
        this.mQuickLinkRowCount = IntMath.ceil(this.mQuickLinks != null ? this.mQuickLinks.length : 0, this.mNumQuickLinksPerRow);
        this.mQuickLinkRowPadding = res.getDimensionPixelSize(R.dimen.play_card_default_inset);
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(context, tabMode, 0);
        this.mLeadingExtraSpacerHeight = res.getDimensionPixelSize(R.dimen.card_list_vpadding);
        this.mParent = parent;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch (viewType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                v = inflate(R.layout.play_list_vspacer, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                v = inflate(R.layout.category_item, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                v = QuickLinkHelper.inflateQuickLinksRow(parent, this.mLayoutInflater, this.mNumQuickLinksPerRow);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                v = inflate(R.layout.category_subheader, parent, false);
                break;
        }
        return new PlayRecyclerView.ViewHolder(v);
    }

    private View inflate(int resource, ViewGroup root, boolean attachToRoot) {
        return this.mLayoutInflater.inflate(resource, root, attachToRoot);
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
                bindCategoryRowView((CategoryRow) v, position - ((this.mQuickLinkRowCount > 0 ? 1 : 0) + (this.mQuickLinkRowCount + 2)));
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                bindQuickLinksRow(v, position);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                bindSubheader(v);
                return;
            default:
                return;
        }
    }

    private void bindLeadingSpacer(View view) {
        view.getLayoutParams().height = this.mLeadingSpacerHeight;
        view.setId(R.id.play_header_spacer);
    }

    private void bindExtraLeadingSpacer(View view) {
        view.getLayoutParams().height = this.mLeadingExtraSpacerHeight;
    }

    private void bindQuickLinksRow(View view, int position) {
        QuickLinkHelper.bindQuickLinksRow(this.mToc, this.mNavigationManager, this.mBitmapLoader, (ViewGroup) view, this.mQuickLinks, position - 2, this.mNumQuickLinksPerRow, this.mParent);
        view.setPadding(view.getPaddingLeft() + this.mQuickLinkRowPadding, view.getPaddingTop(), view.getPaddingRight() + this.mQuickLinkRowPadding, view.getPaddingBottom());
    }

    private void bindSubheader(View view) {
        ((TextView) view).setText(this.mSubheaderTitle);
    }

    private void bindCategoryRowView(final CategoryRow categoryRowView, int position) {
        final BrowseLink category = this.mCategories[position];
        categoryRowView.bind(category, this.mTextColor, this.mParent);
        categoryRowView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CategoryAdapter.this.mNavigationManager.goBrowse(category.dataUrl, category.name, CategoryAdapter.this.mBackendId, CategoryAdapter.this.mToc, categoryRowView);
            }
        });
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        position--;
        if (position == 0) {
            return 1;
        }
        position--;
        if (isQuickLinkRow(position)) {
            return 3;
        }
        if (isSubheaderRow(position)) {
            return 4;
        }
        return 2;
    }

    public int getItemCount() {
        return (this.mQuickLinkRowCount > 0 ? 1 : 0) + (this.mQuickLinkRowCount + (this.mCategories.length + 2));
    }

    public long getItemId(int position) {
        return (long) position;
    }

    private boolean isQuickLinkRow(int position) {
        return this.mQuickLinkRowCount > 0 && position < this.mQuickLinkRowCount;
    }

    private boolean isSubheaderRow(int position) {
        return this.mQuickLinkRowCount > 0 && position == this.mQuickLinkRowCount;
    }
}
