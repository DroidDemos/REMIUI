package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.TabbedBrowseFragment.BackgroundViewConfigurator;
import com.google.android.finsky.adapters.QuickLinkHelper.QuickLinkInfo;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.ContainerList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.BucketRow;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.Identifiable;
import com.google.android.finsky.layout.play.PlayCardActionBannerClusterView;
import com.google.android.finsky.layout.play.PlayCardAddToCirclesClusterView;
import com.google.android.finsky.layout.play.PlayCardClusterMetadata;
import com.google.android.finsky.layout.play.PlayCardClusterRepository;
import com.google.android.finsky.layout.play.PlayCardClusterView;
import com.google.android.finsky.layout.play.PlayCardClusterViewHeader;
import com.google.android.finsky.layout.play.PlayCardDismissListener;
import com.google.android.finsky.layout.play.PlayCardEmptyClusterView;
import com.google.android.finsky.layout.play.PlayCardHeap;
import com.google.android.finsky.layout.play.PlayCardMerchClusterRepository;
import com.google.android.finsky.layout.play.PlayCardMerchClusterView;
import com.google.android.finsky.layout.play.PlayCardMiniClusterRepository;
import com.google.android.finsky.layout.play.PlayCardMyCirclesClusterView;
import com.google.android.finsky.layout.play.PlayCardOrderedClusterView;
import com.google.android.finsky.layout.play.PlayCardPersonClusterRepository;
import com.google.android.finsky.layout.play.PlayCardRateAndSuggestClusterRepository;
import com.google.android.finsky.layout.play.PlayCardRateAndSuggestClusterView;
import com.google.android.finsky.layout.play.PlayCardRateClusterRepository;
import com.google.android.finsky.layout.play.PlayCardRateClusterView;
import com.google.android.finsky.layout.play.PlayCardSingleCardClusterRepository;
import com.google.android.finsky.layout.play.PlayCardTrustedSourceClusterRepository;
import com.google.android.finsky.layout.play.PlayCardTrustedSourceClusterView;
import com.google.android.finsky.layout.play.PlayCardViewPerson;
import com.google.android.finsky.layout.play.PlayListView;
import com.google.android.finsky.layout.play.PlayMerchBannerView;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlaySocialHeader;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.WarmWelcomeCard;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Containers.ContainerMetadata;
import com.google.android.finsky.protos.Containers.ContainerView;
import com.google.android.finsky.protos.DocumentV2.ActionBanner;
import com.google.android.finsky.protos.DocumentV2.CallToAction;
import com.google.android.finsky.protos.DocumentV2.ContainerWithBanner;
import com.google.android.finsky.protos.DocumentV2.Reason;
import com.google.android.finsky.protos.DocumentV2.RecommendationsContainerWithHeader;
import com.google.android.finsky.protos.DocumentV2.WarmWelcome;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntMath;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.UiUtils.ClusterFadeOutListener;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardViewBase;
import java.util.ArrayList;
import java.util.List;

public class CardRecyclerViewAdapter extends FinskyRecyclerViewAdapter implements BackgroundViewConfigurator, PlayCardDismissListener {
    protected final BitmapLoader mBitmapLoader;
    protected final int mCardContentPadding;
    private final PlayCardHeap mCardHeap;
    private final ClientMutationCache mClientMutationCache;
    private ClusterFadeOutListener mClusterFadeOutListener;
    private final int mColumnCount;
    private final String mContainerId;
    private final DfeApi mDfeApi;
    private final int mExtraLeadingSpacerHeight;
    private final boolean mHasBannerHeader;
    private boolean mHasFilters;
    private final boolean mHasPlainHeader;
    private boolean mHasSearchHighlight;
    private final boolean mHasSocialHeader;
    private boolean mIsOnTablet;
    private final boolean mIsOrdered;
    public ArrayList<ItemEntry> mItems;
    protected final int mLeadingSpacerHeight;
    private final List<Document> mLooseDocsWithReasons;
    protected int mLooseItemCellId;
    protected int mLooseItemColCount;
    private final int mNumQuickLinkRows;
    private final int mNumQuickLinksPerRow;
    protected final PlayStoreUiElementNode mParentNode;
    private final QuickLinkInfo[] mQuickLinks;
    private final boolean mShowLooseItemReasons;
    private final String mTitle;
    private final DfeToc mToc;
    private final boolean mUseMiniCards;
    private final boolean mUseTallTemplates;
    private final int mWarmWelcomeCardColumns;
    private final boolean mWarmWelcomeHideGraphic;

    public static class ItemEntry implements Parcelable {
        public static Creator<ItemEntry> CREATOR;
        private final boolean mIsLooseItemRow;
        private int mTrueEndIndex;
        private final int mTrueStartIndex;

        private ItemEntry(int startIndex, int endIndex, boolean isLooseItemRow) {
            this.mTrueEndIndex = -1;
            this.mTrueStartIndex = startIndex;
            this.mTrueEndIndex = endIndex;
            this.mIsLooseItemRow = isLooseItemRow;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mTrueStartIndex);
            dest.writeInt(this.mTrueEndIndex);
            dest.writeInt(this.mIsLooseItemRow ? 1 : 0);
        }

        static {
            CREATOR = new Creator<ItemEntry>() {
                public ItemEntry createFromParcel(Parcel source) {
                    boolean isLooseItemRow = true;
                    int trueStartIndex = source.readInt();
                    int trueEndIndex = source.readInt();
                    if (source.readInt() != 1) {
                        isLooseItemRow = false;
                    }
                    return new ItemEntry(trueStartIndex, trueEndIndex, isLooseItemRow);
                }

                public ItemEntry[] newArray(int size) {
                    return new ItemEntry[size];
                }
            };
        }
    }

    public CardRecyclerViewAdapter(Context context, DfeApi dfeApi, NavigationManager navManager, BitmapLoader loader, DfeToc toc, ClientMutationCache clientMutationCache, ContainerList containerList, QuickLinkInfo[] quickLinks, String title, boolean isRestoring, boolean showLooseItemReasons, int tabMode, PlayStoreUiElementNode parentNode) {
        super(context, navManager, containerList);
        this.mItems = Lists.newArrayList();
        Resources res = context.getResources();
        this.mDfeApi = dfeApi;
        this.mBitmapLoader = loader;
        this.mToc = toc;
        this.mClientMutationCache = clientMutationCache;
        this.mTitle = title;
        this.mColumnCount = UiUtils.getFeaturedGridColumnCount(res, 1.0d);
        this.mCardHeap = new PlayCardHeap();
        this.mUseTallTemplates = res.getDisplayMetrics().heightPixels > res.getDimensionPixelSize(R.dimen.play_min_height_for_large_templates);
        this.mUseMiniCards = res.getBoolean(R.bool.play_can_use_mini_cards);
        this.mCardContentPadding = UiUtils.getGridHorizontalPadding(context.getResources());
        int quickLinkCount = quickLinks != null ? quickLinks.length : 0;
        this.mNumQuickLinksPerRow = UiUtils.getStreamQuickLinkColumnCount(res, quickLinkCount, 0);
        this.mQuickLinks = quickLinks;
        this.mNumQuickLinkRows = IntMath.ceil(quickLinkCount, this.mNumQuickLinksPerRow);
        this.mWarmWelcomeCardColumns = res.getInteger(R.integer.warm_welcome_card_columns);
        boolean z = this.mWarmWelcomeCardColumns == 1 && !res.getBoolean(R.bool.play_warm_welcome_single_column_shows_graphic);
        this.mWarmWelcomeHideGraphic = z;
        this.mParentNode = parentNode;
        Document containerDocument = this.mContainerList.getContainerDocument();
        this.mIsOrdered = containerDocument != null ? containerDocument.isOrdered() : false;
        this.mHasFilters = containerDocument != null ? containerDocument.hasContainerViews() : false;
        this.mContainerId = containerDocument != null ? containerDocument.getDocId() : (String) this.mContainerList.getListPageUrls().get(0);
        this.mShowLooseItemReasons = showLooseItemReasons;
        computeLooseItemRowsValues(res);
        this.mLooseDocsWithReasons = Lists.newArrayList();
        this.mHasBannerHeader = containerDocument != null ? containerDocument.hasContainerWithBannerTemplate() : false;
        this.mHasSocialHeader = containerDocument != null ? containerDocument.hasRecommendationsContainerWithHeaderTemplate() : false;
        if (this.mHasBannerHeader || this.mHasSocialHeader) {
            this.mHasPlainHeader = false;
        } else {
            this.mHasPlainHeader = !TextUtils.isEmpty(this.mTitle);
        }
        this.mIsOnTablet = this.mContext.getResources().getBoolean(R.bool.use_small_as_listing_card);
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(this.mContext, tabMode, 0);
        this.mExtraLeadingSpacerHeight = res.getDimensionPixelSize(R.dimen.card_list_vpadding);
        if (!isRestoring) {
            syncItemEntries();
        }
        this.mClusterFadeOutListener = new ClusterFadeOutListener() {
            public void onClusterFadeOutFinish() {
                CardRecyclerViewAdapter.this.mItems.clear();
                CardRecyclerViewAdapter.this.onDataChanged();
            }
        };
    }

    protected void computeLooseItemRowsValues(Resources res) {
        boolean useMiniCardsForLooseItems;
        boolean isPeopleList;
        if (!this.mUseMiniCards || this.mShowLooseItemReasons || this.mIsOrdered) {
            useMiniCardsForLooseItems = false;
        } else {
            useMiniCardsForLooseItems = true;
        }
        if (this.mContainerList.getBackendId() == 9) {
            isPeopleList = true;
        } else {
            isPeopleList = false;
        }
        if (isPeopleList) {
            this.mLooseItemCellId = R.layout.play_card_person;
            this.mLooseItemColCount = PlayCardPersonClusterRepository.getMetadata(this.mColumnCount, this.mUseTallTemplates).getTileCount();
        } else if (this.mShowLooseItemReasons) {
            this.mLooseItemCellId = R.layout.play_card_small;
            this.mLooseItemColCount = this.mColumnCount;
        } else if (useMiniCardsForLooseItems) {
            this.mLooseItemCellId = R.layout.play_card_mini;
            this.mLooseItemColCount = res.getInteger(R.integer.related_items_per_row);
        } else {
            this.mLooseItemCellId = R.layout.play_card_listing;
            this.mLooseItemColCount = UiUtils.getRegularGridColumnCount(res);
        }
    }

    public boolean hasBackgroundView() {
        return this.mHasSocialHeader;
    }

    public void configureBackgroundView(HeroGraphicView backgroundView) {
        backgroundView.bindGeneric(this.mContainerList.getContainerDocument(), 14);
    }

    protected boolean hasLeadingSpacer() {
        return true;
    }

    protected boolean hasExtraLeadingSpacer() {
        return (this.mHasBannerHeader || this.mHasSocialHeader) ? false : true;
    }

    protected boolean hasFilters() {
        return this.mHasFilters;
    }

    protected boolean shouldHidePlainHeaderDuringInitialLoading() {
        return false;
    }

    protected boolean shouldHidePlainHeaderOnEmpty() {
        return false;
    }

    public void onSaveInstanceState(PlayRecyclerView view, Bundle bundle) {
        super.onSaveInstanceState(view, bundle);
        if (this.mItems.size() != 0) {
            int firstVisibleRow = 0;
            if (view.getLayoutManager() instanceof LinearLayoutManager) {
                firstVisibleRow = ((LinearLayoutManager) view.getLayoutManager()).findFirstVisibleItemPosition();
            }
            int rowPixelOffset = 0;
            int rowPixelHeight = 0;
            if (view.getChildCount() > 0) {
                View firstVisibleView = view.getChildAt(0);
                rowPixelOffset = firstVisibleView.getTop();
                rowPixelHeight = firstVisibleView.getHeight();
            }
            bundle.putInt("CardListAdapter.firstVisibleRow", firstVisibleRow);
            bundle.putInt("CardListAdapter.rowPixelOffset", rowPixelOffset);
            bundle.putInt("CardListAdapter.rowPixelHeight", rowPixelHeight);
            bundle.putInt("CardListAdapter.columnCount", this.mLooseItemColCount);
            bundle.putParcelableArrayList("CardListAdapter.itemEntriesList", this.mItems);
        }
    }

    public static boolean hasRestoreData(Bundle bundle) {
        return bundle != null && bundle.containsKey("CardListAdapter.itemEntriesList");
    }

    public void onRestoreInstanceState(PlayRecyclerView view, Bundle restoreBundle) {
        ArrayList<ItemEntry> restoredItems = restoreBundle.getParcelableArrayList("CardListAdapter.itemEntriesList");
        if (restoredItems == null || restoredItems.size() == 0) {
            syncItemEntries();
            notifyDataSetChanged();
            this.mContainerList.startLoadItems();
            super.onRestoreInstanceState(view, restoreBundle);
            return;
        }
        int firstVisibleRow = restoreBundle.getInt("CardListAdapter.firstVisibleRow");
        int rowPixelOffset = restoreBundle.getInt("CardListAdapter.rowPixelOffset");
        int rowPixelHeight = restoreBundle.getInt("CardListAdapter.rowPixelHeight");
        int oldLooseItemColCount = restoreBundle.getInt("CardListAdapter.columnCount", -1);
        if (oldLooseItemColCount == this.mLooseItemColCount || oldLooseItemColCount == -1) {
            this.mItems = restoredItems;
            notifyDataSetChanged();
            if (view.getLayoutManager() instanceof LinearLayoutManager) {
                ((LinearLayoutManager) view.getLayoutManager()).scrollToPositionWithOffset(firstVisibleRow, rowPixelOffset);
                return;
            }
            return;
        }
        restoreDespiteColumnCountChange(view, restoredItems, firstVisibleRow, rowPixelOffset, rowPixelHeight, oldLooseItemColCount);
    }

    private void restoreDespiteColumnCountChange(PlayRecyclerView list, ArrayList<ItemEntry> restoredItems, int oldFirstVisibleRow, int oldRowPixelOffset, int oldRowPixelHeight, int oldLooseItemColCount) {
        int newFirstVisibleRow;
        int i;
        oldFirstVisibleRow += ((((float) (oldRowPixelHeight + oldRowPixelOffset)) / ((float) oldRowPixelHeight)) > 0.5f ? 1 : ((((float) (oldRowPixelHeight + oldRowPixelOffset)) / ((float) oldRowPixelHeight)) == 0.5f ? 0 : -1)) > 0 ? 0 : 1;
        if (oldFirstVisibleRow < getPrependedRowsCount()) {
            newFirstVisibleRow = oldFirstVisibleRow;
        } else if (oldFirstVisibleRow < getPrependedRowsCount() + restoredItems.size()) {
            int firstVisibleItem = oldFirstVisibleRow - getPrependedRowsCount();
            int numLooseRowsBeforeFirstVisibleItem = 0;
            for (i = 0; i < firstVisibleItem; i++) {
                if (((ItemEntry) restoredItems.get(i)).mIsLooseItemRow) {
                    numLooseRowsBeforeFirstVisibleItem++;
                }
            }
            newFirstVisibleRow = ((firstVisibleItem - numLooseRowsBeforeFirstVisibleItem) + IntMath.floor(oldLooseItemColCount * numLooseRowsBeforeFirstVisibleItem, this.mLooseItemColCount)) + getPrependedRowsCount();
        } else {
            newFirstVisibleRow = Integer.MAX_VALUE;
        }
        ArrayList<ItemEntry> newOutput = Lists.newArrayList();
        i = 0;
        while (i < restoredItems.size()) {
            if (((ItemEntry) restoredItems.get(i)).mIsLooseItemRow) {
                int lastLooseItemRow = i;
                int totalDocsFound = 0;
                for (int j = i; j < restoredItems.size(); j++) {
                    ItemEntry row = (ItemEntry) restoredItems.get(j);
                    if (!row.mIsLooseItemRow) {
                        break;
                    }
                    totalDocsFound += (row.mTrueEndIndex + 1) - row.mTrueStartIndex;
                    lastLooseItemRow = j;
                }
                int newRowCount = IntMath.ceil(totalDocsFound, this.mLooseItemColCount);
                int firstLooseItemIndex = ((ItemEntry) restoredItems.get(i)).mTrueStartIndex;
                int lastLooseItemIndex = ((ItemEntry) restoredItems.get(lastLooseItemRow)).mTrueEndIndex;
                for (int newRowIndex = 0; newRowIndex < newRowCount; newRowIndex++) {
                    newOutput.add(new ItemEntry(firstLooseItemIndex, Math.min((this.mLooseItemColCount + firstLooseItemIndex) - 1, lastLooseItemIndex), true));
                    firstLooseItemIndex += this.mLooseItemColCount;
                }
                i = lastLooseItemRow;
            } else {
                newOutput.add(restoredItems.get(i));
            }
            i++;
        }
        this.mItems = newOutput;
        notifyDataSetChanged();
        if (newFirstVisibleRow >= getItemCount()) {
            newFirstVisibleRow = getItemCount() - 1;
        }
        list.getLayoutManager().scrollToPosition(newFirstVisibleRow);
    }

    public void onDataChanged() {
        syncItemEntries();
        super.onDataChanged();
    }

    public void updateAdapterData(ContainerList<?> containerList) {
        super.updateAdapterData(containerList);
        this.mItems.clear();
        syncItemEntries();
        notifyDataSetChanged();
    }

    private void syncItemEntries() {
        int underlyingCount = this.mContainerList.getCount();
        int lastItemEntryIndex = 0;
        if (this.mItems.size() > 0) {
            lastItemEntryIndex = ((ItemEntry) this.mItems.get(this.mItems.size() - 1)).mTrueEndIndex + 1;
        }
        int i = lastItemEntryIndex;
        while (i < underlyingCount) {
            Document doc = (Document) this.mContainerList.getItem(i, false);
            if (doc == null) {
                FinskyLog.d("Loaded null doc, forcing a hard reload of list data.", new Object[0]);
                this.mContainerList.resetItems();
                this.mContainerList.startLoadItems();
                this.mItems.clear();
                return;
            }
            if (doc.isHighlighted()) {
                this.mHasSearchHighlight = true;
            }
            if (doc.hasContainerAnnotation() || doc.hasAntennaInfo() || doc.hasDealOfTheDayInfo() || doc.hasNextBanner() || doc.isRateCluster() || doc.isRateAndSuggestCluster() || doc.isActionBanner() || doc.isWarmWelcome() || doc.isEmptyContainer()) {
                endLastEntry(this.mItems, i);
                if (!((PlayListView.ENABLE_ANIMATION && ((doc.isRateCluster() && PlayCardRateClusterView.isClusterDismissed(this.mClientMutationCache, doc)) || (doc.isRateAndSuggestCluster() && PlayCardRateAndSuggestClusterView.isClusterDismissed(this.mClientMutationCache, doc)))) || (doc.isWarmWelcome() && this.mClientMutationCache.isDismissedRecommendation(doc.getDocId())))) {
                    this.mItems.add(new ItemEntry(i, i, false));
                }
            } else {
                boolean rowReused = false;
                if (this.mItems.size() > 0) {
                    ItemEntry existingItem = (ItemEntry) this.mItems.get(this.mItems.size() - 1);
                    if (existingItem.mIsLooseItemRow && i - existingItem.mTrueStartIndex < this.mLooseItemColCount) {
                        rowReused = true;
                    }
                }
                if (!rowReused) {
                    endLastEntry(this.mItems, i);
                    this.mItems.add(new ItemEntry(i, -1, true));
                }
            }
            i++;
        }
        if (underlyingCount > 0) {
            endLastEntry(this.mItems, this.mContainerList.getCount());
        }
    }

    private void endLastEntry(List<ItemEntry> list, int newStartIndex) {
        int count = this.mItems.size();
        if (count > 0) {
            ItemEntry lastItem = (ItemEntry) this.mItems.get(count - 1);
            if (lastItem.mIsLooseItemRow) {
                lastItem.mTrueEndIndex = newStartIndex - 1;
            }
        }
    }

    private int getBaseCount() {
        int count = this.mItems.size();
        if (getFooterMode() != FooterMode.NONE) {
            count++;
        }
        return getBasePrependedRowsCount() + count;
    }

    private int getBasePrependedRowsCount() {
        int i = 1;
        int i2 = (hasFilters() ? 1 : 0) + ((this.mNumQuickLinkRows + (isShowingPlainHeader() ? 1 : 0)) + (this.mHasBannerHeader ? 1 : 0));
        if (!this.mHasSocialHeader) {
            i = 0;
        }
        return i2 + i;
    }

    public final int getItemCount() {
        int dataRowsCount = getDataRowsCount();
        if (dataRowsCount == 0) {
            return 0;
        }
        int result = dataRowsCount;
        if (hasLeadingSpacer()) {
            result++;
        }
        if (hasExtraLeadingSpacer()) {
            return result + 1;
        }
        return result;
    }

    protected int getDataRowsCount() {
        return getBaseCount();
    }

    protected int getPrependedRowsCount() {
        return getBasePrependedRowsCount();
    }

    private boolean isShowingPlainHeader() {
        if (!this.mHasPlainHeader) {
            return false;
        }
        if (this.mContainerList.getCount() > 0) {
            return this.mHasPlainHeader;
        }
        FooterMode footerMode = getFooterMode();
        if (shouldHidePlainHeaderDuringInitialLoading() && footerMode == FooterMode.LOADING) {
            return false;
        }
        if (shouldHidePlainHeaderOnEmpty() && footerMode == FooterMode.NONE) {
            return false;
        }
        return true;
    }

    private Object getItem(int position) {
        return this.mItems.get(position);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                v = createErrorFooterView(parent);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                v = createLoadingFooterView(parent);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                v = QuickLinkHelper.inflateQuickLinksRow(parent, this.mLayoutInflater, this.mNumQuickLinksPerRow);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                v = inflate(R.layout.play_merch_banner, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                v = inflate(R.layout.play_card_merch_cluster, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                v = inflate(R.layout.play_card_merch_cluster, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                v = this.mShowLooseItemReasons ? inflate(R.layout.play_card_cluster, parent, false) : createRowOfLooseItemsWithoutReasons(parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                v = inflate(R.layout.play_card_cluster_header, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                v = inflate(R.layout.play_selector_header, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                v = createOrderedCluster(parent);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                v = inflate(R.layout.play_card_add_to_circles_cluster, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomControls /*11*/:
                v = inflate(R.layout.play_card_trusted_source_cluster, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomGestures /*12*/:
                v = inflate(R.layout.play_card_rate_cluster, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_useViewLifecycle /*13*/:
                v = inflate(R.layout.play_card_rate_and_suggest_cluster, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_zOrderOnTop /*14*/:
                v = inflate(R.layout.play_card_action_banner_cluster, parent, false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiMapToolbar /*15*/:
                v = inflate(R.layout.play_card_empty_cluster, parent, false);
                break;
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                v = inflate(R.layout.container_list_header, parent, false);
                break;
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                v = inflate(R.layout.social_header, parent, false);
                break;
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                v = inflate(this.mWarmWelcomeCardColumns == 1 ? R.layout.warm_welcome_card_single_column : R.layout.warm_welcome_card_double_column, parent, false);
                break;
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                v = inflate(R.layout.play_card_my_circles_cluster, parent, false);
                break;
            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                v = inflate(R.layout.play_card_merch_cluster, parent, false);
                break;
            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                v = inflate(R.layout.play_list_vspacer, parent, false);
                break;
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                v = createRowOfLooseItemsWithoutReasons(parent, true);
                break;
            default:
                v = inflate(R.layout.play_card_cluster, parent, false);
                break;
        }
        return new PlayRecyclerView.ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int i;
        int itemViewType = viewHolder.getItemViewType();
        int prependedRowsCount = position - getPrependedRowsCount();
        if (hasLeadingSpacer()) {
            i = 1;
        } else {
            i = 0;
        }
        int adjustedIndex = (prependedRowsCount - i) - (hasExtraLeadingSpacer() ? 1 : 0);
        ItemEntry entry = null;
        if (adjustedIndex < this.mItems.size() && adjustedIndex >= 0) {
            entry = (ItemEntry) this.mItems.get(adjustedIndex);
        }
        View v = viewHolder.itemView;
        switch (itemViewType) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                bindErrorFooterView(v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                bindLoadingFooterView(v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                bindQuickLinksRow(v, position);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                bindMerchBanner(entry.mTrueStartIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                bindMerchCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                bindSingleDocCluster(entry.mTrueStartIndex, v, false);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                bindLooseItemRow(entry.mTrueStartIndex, entry.mTrueEndIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                bindPlainHeaderView(v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                bindContainerFilterView(v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                bindOrderedCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                bindAddToCirclesCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomControls /*11*/:
                bindTrustedSourceCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiZoomGestures /*12*/:
                bindRateCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_useViewLifecycle /*13*/:
                bindQuickSuggestionsCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_zOrderOnTop /*14*/:
                bindActionBannerCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.MapAttrs_uiMapToolbar /*15*/:
                bindEmptyCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                bindBannerHeaderView(v);
                return;
            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                bindSocialHeaderView(v);
                return;
            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                bindWarmWelcomeCardView(entry.mTrueStartIndex, v);
                return;
            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                bindMyCirclesCluster(entry.mTrueStartIndex, v);
                return;
            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                bindSingleDocCluster(entry.mTrueStartIndex, v, true);
                return;
            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                bindLeadingSpacer(v);
                return;
            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                bindExtraLeadingSpacer(v);
                return;
            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                bindRowOfLooseItemsWithoutReasons(entry.mTrueStartIndex, entry.mTrueEndIndex, v, true);
                return;
            default:
                bindGenericCluster(entry.mTrueStartIndex, v);
                return;
        }
    }

    private View createOrderedCluster(ViewGroup parent) {
        PlayCardOrderedClusterView clusterView = (PlayCardOrderedClusterView) inflate(R.layout.play_card_ordered_cluster, parent, false);
        Resources res = this.mContext.getResources();
        clusterView.inflateGrid(res.getInteger(R.integer.search_bucket_rows), UiUtils.getRegularGridColumnCount(res), this.mCardContentPadding);
        return clusterView;
    }

    private void bindOrderedCluster(int startIndex, View view) {
        PlayCardOrderedClusterView clusterView = (PlayCardOrderedClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        clusterView.setIdentifier(clusterDoc.getDocId());
        clusterView.bind(clusterDoc, clusterDoc.getChildren(), this.mBitmapLoader, this.mNavigationManager, getClusterClickListener(clusterDoc, clusterView), this.mParentNode);
    }

    private void bindLeadingSpacer(View view) {
        view.getLayoutParams().height = this.mLeadingSpacerHeight;
        view.setId(R.id.play_header_spacer);
    }

    private void bindExtraLeadingSpacer(View view) {
        view.getLayoutParams().height = this.mExtraLeadingSpacerHeight;
    }

    private void bindPlainHeaderView(View view) {
        PlayCardClusterViewHeader plainHeader = (PlayCardClusterViewHeader) view;
        plainHeader.setContent(this.mContainerList.getBackendId(), this.mTitle, null, null, null);
        plainHeader.setExtraHorizontalPadding(this.mCardContentPadding);
        plainHeader.setIdentifier("plain_header:" + this.mTitle);
    }

    private void bindBannerHeaderView(View view) {
        DocImageView bannerImage = (DocImageView) view;
        Document containerDocument = this.mContainerList.getContainerDocument();
        ContainerWithBanner template = containerDocument.getContainerWithBannerTemplate();
        if (!TextUtils.isEmpty(template.colorThemeArgb)) {
            bannerImage.setBackgroundColor(Color.parseColor(template.colorThemeArgb));
        }
        bannerImage.bind(containerDocument, this.mBitmapLoader, 9);
    }

    private void bindSocialHeaderView(View view) {
        PlaySocialHeader header = (PlaySocialHeader) view;
        RecommendationsContainerWithHeader template = this.mContainerList.getContainerDocument().getRecommendationsContainerWithHeaderTemplate();
        header.bind(template.primaryFace, template.secondaryFace, this.mNavigationManager, this.mParentNode);
    }

    private void bindQuickLinksRow(View view, int position) {
        int i;
        int i2 = 1;
        if (hasLeadingSpacer()) {
            i = 1;
        } else {
            i = 0;
        }
        if (!hasExtraLeadingSpacer()) {
            i2 = 0;
        }
        QuickLinkHelper.bindQuickLinksRow(this.mToc, this.mNavigationManager, this.mBitmapLoader, (ViewGroup) view, this.mQuickLinks, position - (i + i2), this.mNumQuickLinksPerRow, this.mParentNode);
        ((Identifiable) view).setIdentifier("quick_link_row:" + position);
        view.setPadding(view.getPaddingLeft() + this.mCardContentPadding, view.getPaddingTop(), view.getPaddingRight() + this.mCardContentPadding, view.getPaddingBottom());
    }

    private void bindLooseItemRow(int mTrueStartIndex, int mTrueEndIndex, View view) {
        if (this.mShowLooseItemReasons) {
            bindRowOfLooseItemsWithReasons(mTrueStartIndex, mTrueEndIndex, view);
        } else {
            bindRowOfLooseItemsWithoutReasons(mTrueStartIndex, mTrueEndIndex, view, false);
        }
    }

    private View createRowOfLooseItemsWithoutReasons(ViewGroup parent, boolean isHighlighted) {
        int colCount = (!isHighlighted || this.mIsOnTablet) ? this.mLooseItemColCount : 1;
        BucketRow bucketRow = (BucketRow) inflate(R.layout.bucket_row, parent, false);
        bucketRow.setContentHorizontalPadding(this.mCardContentPadding);
        for (int column = 0; column < colCount; column++) {
            bucketRow.addView(inflate(this.mLooseItemCellId, bucketRow, false));
        }
        return bucketRow;
    }

    private void bindRowOfLooseItemsWithoutReasons(int trueStartIndex, int trueEndIndex, View view, boolean isHighlighted) {
        int colCount = (!isHighlighted || this.mIsOnTablet) ? this.mLooseItemColCount : 1;
        BucketRow bucketRow = (BucketRow) view;
        String firstDocId = null;
        for (int i = 0; i < colCount; i++) {
            int trueIndex = trueStartIndex + i;
            boolean pastBounds = trueIndex > trueEndIndex;
            Document doc = pastBounds ? null : (Document) this.mContainerList.getItem(trueIndex);
            if (TextUtils.isEmpty(firstDocId) && doc != null) {
                firstDocId = doc.getDocId();
            }
            bindLooseItem(doc, trueIndex, bucketRow.getChildAt(i), pastBounds);
        }
        bucketRow.setIdentifier(firstDocId);
        configureContainerOfLooseItemsWithoutReasons(bucketRow);
    }

    protected void configureContainerOfLooseItemsWithoutReasons(BucketRow container) {
    }

    private void bindLooseItem(Document document, int trueIndex, View docEntry, boolean bindNoDoc) {
        PlayCardViewBase cardView = (PlayCardViewBase) docEntry;
        if (document != null) {
            PlayCardUtils.bindCard(cardView, document, this.mContainerId, this.mBitmapLoader, this.mNavigationManager, isDismissed(document), getPlayCardDismissListener(), this.mParentNode, true, getDisplayIndex(trueIndex));
            if (cardView instanceof PlayCardViewPerson) {
                ((PlayCardViewPerson) cardView).showCirclesIcon(!this.mContainerList.getContainerDocument().isMyCirclesContainer());
            }
        } else if (!bindNoDoc || trueIndex < this.mContainerList.getCount()) {
            cardView.setVisibility(0);
            cardView.bindLoading();
        } else {
            cardView.clearCardState();
        }
    }

    protected int getDisplayIndex(int trueIndex) {
        int accountForHighlight = 0;
        if (this.mHasSearchHighlight) {
            accountForHighlight = -(this.mIsOnTablet ? 1 : this.mLooseItemColCount);
        }
        if (!this.mIsOrdered) {
            trueIndex = -1;
        }
        return trueIndex + accountForHighlight;
    }

    protected boolean isDismissed(Document document) {
        return document.isDismissable() && this.mClientMutationCache.isDismissedRecommendation(document.getDocId());
    }

    public void onDestroyView() {
    }

    public int getItemViewType(int position) {
        return getCardListAdapterViewType(position);
    }

    private int getCardListAdapterViewType(int position) {
        if (hasLeadingSpacer()) {
            if (position == 0) {
                return 22;
            }
            position--;
        }
        if (hasExtraLeadingSpacer()) {
            if (position == 0) {
                return 23;
            }
            position--;
        }
        FooterMode footerMode = getFooterMode();
        if (footerMode == FooterMode.NONE || position != getBaseCount() - 1) {
            if (position < this.mNumQuickLinkRows) {
                return 2;
            }
            position -= this.mNumQuickLinkRows;
            if (this.mHasSocialHeader) {
                if (position == 0) {
                    return 17;
                }
                position--;
            }
            if (this.mHasBannerHeader) {
                if (position == 0) {
                    return 16;
                }
                position--;
            }
            if (isShowingPlainHeader()) {
                if (position == 0) {
                    return 7;
                }
                position--;
            }
            if (hasFilters()) {
                if (position == 0) {
                    return 8;
                }
                position--;
            }
            Document firstItem = (Document) this.mContainerList.getItem(((ItemEntry) getItem(position)).mTrueStartIndex);
            if (firstItem == null) {
                return 6;
            }
            if (firstItem.hasNextBanner()) {
                return 3;
            }
            if (firstItem.hasDealOfTheDayInfo()) {
                return 5;
            }
            if (firstItem.isRateCluster()) {
                return 12;
            }
            if (firstItem.isRateAndSuggestCluster()) {
                return 13;
            }
            if (firstItem.isAddToCirclesContainer()) {
                return 10;
            }
            if (firstItem.isMyCirclesContainer()) {
                return 19;
            }
            if (firstItem.isTrustedSourceContainer()) {
                return 11;
            }
            if (firstItem.isEmptyContainer()) {
                return 15;
            }
            if (firstItem.isActionBanner()) {
                return 14;
            }
            if (firstItem.isWarmWelcome()) {
                return 18;
            }
            if (firstItem.isSingleCardWithButton()) {
                return 21;
            }
            if (firstItem.isHighlighted()) {
                return 24;
            }
            boolean hasContainer = firstItem.hasContainerAnnotation();
            if (!hasContainer && !firstItem.hasAntennaInfo()) {
                return 6;
            }
            if (firstItem.hasImages(14)) {
                return 4;
            }
            if (hasContainer && firstItem.getContainerAnnotation().ordered) {
                return 9;
            }
            return 20;
        } else if (footerMode == FooterMode.LOADING) {
            return 1;
        } else {
            if (footerMode == FooterMode.ERROR) {
                return 0;
            }
            FinskyLog.wtf("Unexpected footer mode: %d", footerMode);
            return 0;
        }
    }

    private void bindRowOfLooseItemsWithReasons(int trueStartIndex, int trueEndIndex, View view) {
        PlayCardClusterView cluster = (PlayCardClusterView) view;
        this.mLooseDocsWithReasons.clear();
        Document firstNonNull = null;
        for (int i = trueStartIndex; i <= trueEndIndex; i++) {
            Document child = (Document) this.mContainerList.getItem(i);
            if (child != null) {
                if (firstNonNull == null) {
                    firstNonNull = child;
                }
                this.mLooseDocsWithReasons.add(child);
            }
        }
        int documentType = firstNonNull != null ? firstNonNull.getDocumentType() : -1;
        cluster.setIdentifier(firstNonNull != null ? firstNonNull.getDocId() : "");
        cluster.withLooseDocumentsData(this.mLooseDocsWithReasons, this.mContainerId).createContent(documentType == 28 ? PlayCardPersonClusterRepository.getMetadata(this.mColumnCount, this.mUseTallTemplates) : PlayCardClusterRepository.getMetadata(documentType, this.mColumnCount, this.mUseTallTemplates, 0), this.mClientMutationCache, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getPlayCardDismissListener(), this.mCardHeap, this.mParentNode);
        cluster.setCardContentHorizontalPadding(this.mCardContentPadding);
        cluster.hideHeader();
    }

    private void bindGenericCluster(int startIndex, View view) {
        PlayCardClusterView cluster = (PlayCardClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        bindCluster(clusterDoc, getGenericClusterMetadata(clusterDoc), cluster, getClusterClickListener(clusterDoc, cluster));
    }

    private void bindAddToCirclesCluster(int startIndex, View view) {
        PlayCardAddToCirclesClusterView cluster = (PlayCardAddToCirclesClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        bindCluster(clusterDoc, PlayCardPersonClusterRepository.getMetadata(this.mColumnCount, this.mUseTallTemplates), cluster, getClusterClickListener(clusterDoc, cluster));
        for (int i = 0; i < cluster.getCardChildCount(); i++) {
            ((PlayCardViewPerson) cluster.getCardChildAt(i)).showCirclesIcon(true);
        }
    }

    private void bindMyCirclesCluster(int startIndex, View view) {
        PlayCardMyCirclesClusterView cluster = (PlayCardMyCirclesClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        bindCluster(clusterDoc, PlayCardPersonClusterRepository.getMetadata(this.mColumnCount, this.mUseTallTemplates), cluster, getClusterClickListener(clusterDoc, cluster));
        for (int i = 0; i < cluster.getCardChildCount(); i++) {
            ((PlayCardViewPerson) cluster.getCardChildAt(i)).showCirclesIcon(false);
        }
    }

    private void bindTrustedSourceCluster(int startIndex, View view) {
        PlayCardTrustedSourceClusterView cluster = (PlayCardTrustedSourceClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        bindCluster(clusterDoc, PlayCardTrustedSourceClusterRepository.getMetadata(this.mColumnCount, this.mUseTallTemplates), cluster, getClusterClickListener(clusterDoc, cluster));
        cluster.configurePersonProfile(this.mNavigationManager, this.mBitmapLoader, clusterDoc.getTrustedSourcePersonDoc(), cluster);
    }

    private void bindActionBannerCluster(int startIndex, View view) {
        CallToAction action;
        String buttonText;
        final PlayCardActionBannerClusterView cluster = (PlayCardActionBannerClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        PlayCardClusterMetadata clusterMetadata = PlayCardTrustedSourceClusterRepository.getMetadata(this.mColumnCount, this.mUseTallTemplates);
        ActionBanner actionBanner = clusterDoc.getActionBanner();
        CallToAction[] actions = actionBanner.action;
        if (actions.length > 0) {
            action = actions[0];
        } else {
            action = null;
        }
        bindCluster(clusterDoc, clusterMetadata, cluster, null);
        if (action != null) {
            buttonText = action.buttonText;
        } else {
            buttonText = null;
        }
        cluster.configureExtraContent(this.mNavigationManager, this.mBitmapLoader, clusterDoc, actionBanner.primaryFace, actionBanner.secondaryFace, buttonText, cluster, new OnClickListener() {
            public void onClick(View v) {
                FinskyApp.get().getEventLogger().logClickEvent(1230, null, cluster);
                CardRecyclerViewAdapter.this.mNavigationManager.resolveCallToAction(action, CardRecyclerViewAdapter.this.mDfeApi, CardRecyclerViewAdapter.this.mToc, FinskyApp.get().getPackageManager());
            }
        });
    }

    private void bindRateCluster(int startIndex, View view) {
        PlayCardRateClusterView cluster = (PlayCardRateClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        cluster.setClusterFadeOutListener(this.mClusterFadeOutListener);
        bindCluster(clusterDoc, PlayCardRateClusterRepository.getMetadata(this.mColumnCount), cluster, null);
    }

    private void bindQuickSuggestionsCluster(int startIndex, View view) {
        PlayCardRateAndSuggestClusterView cluster = (PlayCardRateAndSuggestClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        cluster.setClusterFadeOutListener(this.mClusterFadeOutListener);
        bindCluster(clusterDoc, PlayCardRateAndSuggestClusterRepository.getMetadata(clusterDoc.getChildAt(0).getDocumentType(), this.mColumnCount, this.mUseTallTemplates), cluster, null);
    }

    private void bindEmptyCluster(int startIndex, View view) {
        PlayCardEmptyClusterView cluster = (PlayCardEmptyClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        cluster.showHeader(clusterDoc.getBackend(), clusterDoc.getTitle(), clusterDoc.getSubtitle(), null, null, this.mCardContentPadding);
        cluster.createContent(clusterDoc, this.mParentNode);
        cluster.setIdentifier(clusterDoc.getDocId());
    }

    private void bindWarmWelcomeCardView(int index, View view) {
        Image image;
        WarmWelcomeCard warmWelcomeCard = (WarmWelcomeCard) view;
        final Document doc = (Document) this.mContainerList.getItem(index);
        WarmWelcome warmWelcomeTemplate = doc.getWarmWelcome();
        List<Image> docImages = doc.getImages(4);
        Image warmWelcomeGraphic = docImages.isEmpty() ? null : (Image) docImages.get(0);
        CharSequence title = doc.getTitle();
        CharSequence description = doc.getDescription();
        if (this.mWarmWelcomeHideGraphic) {
            image = null;
        } else {
            image = warmWelcomeGraphic;
        }
        warmWelcomeCard.configureContent(title, description, image, doc.getBackend(), this.mParentNode, doc.getServerLogsCookie());
        CallToAction dismissAction = null;
        CallToAction secondaryAction = null;
        for (CallToAction callToAction : warmWelcomeTemplate.action) {
            if (callToAction.type == 1) {
                dismissAction = callToAction;
            } else {
                secondaryAction = callToAction;
            }
        }
        final CallToAction finalDismissAction = dismissAction;
        final CallToAction finalSecondaryAction = secondaryAction;
        WarmWelcomeCard finalWarmWelcomeCard = warmWelcomeCard;
        final WarmWelcomeCard warmWelcomeCard2 = finalWarmWelcomeCard;
        warmWelcomeCard.configureDismissAction(finalDismissAction.buttonText, finalDismissAction.buttonIcon, new OnClickListener() {
            public void onClick(View v) {
                FinskyApp.get().getEventLogger().logClickEvent(1231, null, warmWelcomeCard2);
                CardRecyclerViewAdapter.this.mNavigationManager.resolveCallToAction(finalDismissAction, CardRecyclerViewAdapter.this.mDfeApi, CardRecyclerViewAdapter.this.mToc, FinskyApp.get().getPackageManager());
                CardRecyclerViewAdapter.this.mClientMutationCache.dismissRecommendation(doc.getDocId());
                UiUtils.fadeOutCluster(warmWelcomeCard2, CardRecyclerViewAdapter.this.mClusterFadeOutListener, 0);
            }
        });
        if (finalSecondaryAction == null) {
            warmWelcomeCard.hideSecondaryAction();
        } else {
            warmWelcomeCard2 = finalWarmWelcomeCard;
            warmWelcomeCard.showSecondaryButton(finalSecondaryAction.buttonText, finalSecondaryAction.buttonIcon, new OnClickListener() {
                public void onClick(View v) {
                    FinskyApp.get().getEventLogger().logClickEvent(1232, null, warmWelcomeCard2);
                    CardRecyclerViewAdapter.this.mNavigationManager.resolveCallToAction(finalSecondaryAction, CardRecyclerViewAdapter.this.mDfeApi, CardRecyclerViewAdapter.this.mToc, FinskyApp.get().getPackageManager());
                }
            });
        }
        warmWelcomeCard.setPadding(this.mCardContentPadding, 0, this.mCardContentPadding, 0);
        warmWelcomeCard.setIdentifier(doc.getDocId());
    }

    private void bindCluster(Document clusterDoc, PlayCardClusterMetadata clusterMetadata, PlayCardClusterView cluster, OnClickListener clusterClickListener) {
        if (cluster.hasHeader()) {
            cluster.showHeader(clusterDoc.getBackend(), clusterDoc.getTitle(), clusterDoc.getSubtitle(), clusterClickListener != null ? getMoreResultsStringForCluster(clusterDoc, clusterMetadata) : null, clusterClickListener, this.mCardContentPadding);
        }
        cluster.withClusterDocumentData(clusterDoc).createContent(clusterMetadata, this.mClientMutationCache, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getPlayCardDismissListener(), this.mCardHeap, this.mParentNode);
        cluster.setCardContentHorizontalPadding(this.mCardContentPadding);
        cluster.setIdentifier(clusterDoc.getDocId());
    }

    protected OnClickListener getClusterClickListener(Document clusterDoc, PlayStoreUiElementNode clusterNode) {
        return NavigationManager.hasClickListener(clusterDoc) ? this.mNavigationManager.getClickListener(clusterDoc, clusterNode) : null;
    }

    private String getMoreResultsStringForCluster(Document clusterDoc, PlayCardClusterMetadata clusterMetadata) {
        ContainerMetadata containerData = clusterDoc.getContainerAnnotation();
        if (containerData != null && containerData.browseUrl.length() > 0) {
            long estimatedResults = containerData.estimatedResults;
            if (estimatedResults <= 0) {
                return this.mContext.getString(R.string.more_results_no_count);
            }
            long remaining = estimatedResults - ((long) Math.min(clusterDoc.getChildCount(), clusterMetadata.getTileCount()));
            if (remaining <= 0) {
                return null;
            }
            if (remaining > 99) {
                return this.mContext.getString(R.string.more_results_no_count);
            }
            return this.mContext.getString(R.string.more_results, new Object[]{Long.valueOf(remaining)});
        } else if (clusterDoc.hasAntennaInfo()) {
            return this.mContext.getString(R.string.more_results_no_count);
        } else {
            return null;
        }
    }

    private void bindMerchCluster(int startIndex, View view) {
        PlayCardMerchClusterView cluster = (PlayCardMerchClusterView) view;
        Document clusterDoc = (Document) this.mContainerList.getItem(startIndex);
        PlayCardClusterMetadata clusterMetadata = getMerchClusterMetadata(clusterDoc);
        OnClickListener clusterClickListener = getClusterClickListener(clusterDoc, cluster);
        bindCluster(clusterDoc, clusterMetadata, cluster, clusterClickListener);
        cluster.configureMerch(this.mBitmapLoader, 0, (Image) clusterDoc.getImages(14).get(0), clusterDoc.getTitle(), clusterClickListener);
    }

    private void bindSingleDocCluster(int trueStartIndex, View view, boolean showActionButton) {
        PlayCardMerchClusterView cluster = (PlayCardMerchClusterView) view;
        final Document clusterDoc = (Document) this.mContainerList.getItem(trueStartIndex);
        Document child = clusterDoc.getChildAt(0);
        cluster.setIdentifier(child.getDocId());
        child.setDescription(clusterDoc.getRawDescription());
        final int i = showActionButton ? 417 : 407;
        OnClickListener headerClickListener = new OnClickListener() {
            public void onClick(View v) {
                FinskyApp.get().getEventLogger().logClickEvent(i, clusterDoc.getServerLogsCookie(), CardRecyclerViewAdapter.this.mParentNode);
                CardRecyclerViewAdapter.this.mNavigationManager.goToDocPage(clusterDoc.getDetailsUrl());
            }
        };
        cluster.withClusterDocumentData(clusterDoc).createContent(PlayCardSingleCardClusterRepository.getMetadata(child.getDocumentType(), this.mColumnCount, showActionButton), this.mClientMutationCache, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, null, this.mCardHeap, this.mParentNode);
        List<Image> promoImages = clusterDoc.getImages(14);
        if (this.mColumnCount <= 2 || promoImages == null || promoImages.isEmpty()) {
            cluster.configureNoMerch();
        } else {
            cluster.configureMerch(this.mBitmapLoader, 1, (Image) promoImages.get(0), clusterDoc.getTitle(), headerClickListener);
        }
        PlayCardViewBase card = cluster.getCardChildAt(0);
        if (showActionButton) {
            PlayCardUtils.updatePrimaryActionButton(child, cluster.getContext(), card, cluster, this.mNavigationManager);
        }
        cluster.showHeader(child.getBackend(), clusterDoc.getTitle(), clusterDoc.getSubtitle(), null, headerClickListener, this.mCardContentPadding);
        cluster.setCardContentHorizontalPadding(this.mCardContentPadding);
    }

    private void bindMerchBanner(int trueStartIndex, View view) {
        PlayMerchBannerView banner = (PlayMerchBannerView) view;
        banner.init(this.mColumnCount, this.mCardContentPadding);
        Document clusterDoc = (Document) this.mContainerList.getItem(trueStartIndex);
        banner.setIdentifier(clusterDoc.getDocId());
        banner.configureMerch(clusterDoc.getNextBannerInfo(), this.mBitmapLoader, (Image) clusterDoc.getImages(14).get(0), this.mNavigationManager.getClickListener(clusterDoc, banner), this.mParentNode, clusterDoc.getServerLogsCookie());
    }

    private void bindContainerFilterView(View vew) {
        Spinner spinner = (Spinner) vew.findViewById(R.id.section_corpus_spinner);
        bindSpinnerData((Identifiable) vew, spinner, vew.findViewById(R.id.corpus_header_strip));
        spinner.setVisibility(0);
    }

    protected void bindSpinnerData(Identifiable identifiable, final Spinner spinner, View corpusHeaderStrip) {
        Document containerDocument = this.mContainerList.getContainerDocument();
        identifiable.setIdentifier(containerDocument.getDocId());
        spinner.setBackgroundResource(CorpusResourceUtils.getCorpusSpinnerDrawable(containerDocument.getBackend()));
        if (corpusHeaderStrip != null) {
            corpusHeaderStrip.setBackgroundColor(CorpusResourceUtils.getPrimaryColor(this.mContext, containerDocument.getBackend()));
        }
        int horizontalMargin = spinner.getResources().getDimensionPixelSize(R.dimen.search_spinner_selected_margin_left) + this.mCardContentPadding;
        MarginLayoutParams spinnerLp = (MarginLayoutParams) spinner.getLayoutParams();
        spinnerLp.leftMargin = horizontalMargin;
        spinnerLp.rightMargin = horizontalMargin;
        spinner.setLayoutParams(spinnerLp);
        final ContainerView[] containerViews = containerDocument.getContainerViews();
        spinner.setAdapter(new ContainerViewSpinnerAdapter(this.mContext, containerViews));
        for (int i = 0; i < containerViews.length; i++) {
            if (containerViews[i].selected) {
                spinner.setSelection(i);
                break;
            }
        }
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ContainerView item = (ContainerView) spinner.getAdapter().getItem(position);
                if (spinner.getVisibility() == 0 && !item.selected) {
                    FinskyApp.get().getEventLogger().logClickEvent(249, item.serverLogsCookie, CardRecyclerViewAdapter.this.mParentNode);
                    FinskyEventLog.startNewImpression(CardRecyclerViewAdapter.this.mParentNode);
                    for (ContainerView containerView : containerViews) {
                        containerView.selected = containerView == item;
                        containerView.hasSelected = true;
                    }
                    CardRecyclerViewAdapter.this.mItems.clear();
                    CardRecyclerViewAdapter.this.mContainerList.clearDataAndReplaceInitialUrl(item.listUrl);
                    CardRecyclerViewAdapter.this.mContainerList.startLoadItems();
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        if (view instanceof Recyclable) {
            ((Recyclable) view).onRecycle();
        }
        if (view instanceof PlayCardClusterView) {
            this.mCardHeap.recycle((PlayCardClusterView) view);
        }
    }

    protected PlayCardDismissListener getPlayCardDismissListener() {
        return this;
    }

    public void onDismissDocument(Document doc, PlayCardViewBase card) {
        this.mClientMutationCache.dismissRecommendation(doc.getDocId());
        notifyDataSetChanged();
    }

    private PlayCardClusterMetadata getMerchClusterMetadata(Document doc) {
        return PlayCardMerchClusterRepository.getMetadata(doc.getChildCount() > 0 ? doc.getChildAt(0).getDocumentType() : doc.getDocumentType(), this.mColumnCount, this.mUseTallTemplates);
    }

    private PlayCardClusterMetadata getGenericClusterMetadata(Document doc) {
        int numChildren = doc.getChildCount();
        boolean firstChildHasReason = false;
        int documentType = doc.getDocumentType();
        if (numChildren > 0) {
            Document firstChild = doc.getChildAt(0);
            documentType = firstChild.getDocumentType();
            if (PlayCardUtils.findHighestPriorityReason(firstChild.getSuggestionReasons()) != null) {
                firstChildHasReason = true;
            } else {
                firstChildHasReason = false;
            }
        }
        int signalStrength = getSignalStrengthForCluster(doc);
        if (this.mUseMiniCards && !firstChildHasReason && signalStrength == 0) {
            return PlayCardMiniClusterRepository.getMetadata(documentType, this.mColumnCount + 1);
        }
        return PlayCardClusterRepository.getMetadata(documentType, this.mColumnCount, this.mUseTallTemplates, signalStrength);
    }

    private int getSignalStrengthForCluster(Document clusterDoc) {
        if (clusterDoc.getChildCount() < 2) {
            FinskyLog.w("Not enough children in cluster.", new Object[0]);
            return 0;
        }
        Document firstChild = clusterDoc.getChildAt(0);
        Document secondChild = clusterDoc.getChildAt(1);
        Reason firstReason = PlayCardUtils.findHighestPriorityReason(firstChild.getSuggestionReasons());
        Reason secondReason = PlayCardUtils.findHighestPriorityReason(secondChild.getSuggestionReasons());
        if (firstReason == null) {
            return 0;
        }
        if (firstReason.reasonReview != null) {
            return (secondReason == null || secondReason.reasonReview == null) ? 2 : 4;
        } else {
            if (firstReason.reasonPlusOne != null) {
                return 3;
            }
            return 0;
        }
    }
}
