package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.PaintDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.DetailsSectionStack.NoBottomSeparator;
import com.google.android.finsky.layout.DetailsSectionStack.NoTopSeparator;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.layout.play.PlayCardClusterViewHeader;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardViewBase;

public class DetailsPackSection extends LinearLayout implements ErrorListener, OnDataChangedListener, NoBottomSeparator, NoTopSeparator, PlayStoreUiElementNode {
    protected BitmapLoader mBitmapLoader;
    private DfeApi mDfeApi;
    protected Document mDoc;
    protected String mHeader;
    protected PlayCardClusterViewHeader mHeaderView;
    protected DfeList mItemListRequest;
    private LayoutSwitcher mLayoutSwitcher;
    protected FrameLayout mListingContent;
    protected LinearLayout mListingLayout;
    private int mMaxItemsCount;
    private int mMaxItemsPerRow;
    private String mMoreUrl;
    protected NavigationManager mNavigationManager;
    private String mNoDataHeader;
    private PlayStoreUiElementNode mParentNode;
    protected String mSubheader;
    protected DfeToc mToc;
    protected PlayStoreUiElement mUiElementProto;
    private String mUrl;

    public DetailsPackSection(Context context) {
        this(context, null);
    }

    public DetailsPackSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mParentNode = null;
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(400);
        Resources res = context.getResources();
        int mainBgColor = res.getColor(R.color.play_main_background);
        if (res.getBoolean(R.bool.use_wide_details_layout)) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            int extraInset = res.getDimensionPixelSize(R.dimen.play_card_inset);
            setBackgroundDrawable(new InsetDrawable(new PaintDrawable(mainBgColor), extraInset, 0, extraInset, 0));
            setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            return;
        }
        setBackgroundColor(mainBgColor);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeaderView = (PlayCardClusterViewHeader) findViewById(R.id.cluster_header);
        this.mListingContent = (FrameLayout) findViewById(R.id.listing_content);
        this.mListingLayout = (LinearLayout) this.mListingContent.findViewById(R.id.listing_layout);
    }

    public void init(DfeApi api, NavigationManager navManager, BitmapLoader bitmapLoader, DfeToc toc) {
        this.mDfeApi = api;
        this.mNavigationManager = navManager;
        this.mBitmapLoader = bitmapLoader;
        this.mToc = toc;
    }

    public void setNoDataHeader(String noDataHeader) {
        this.mNoDataHeader = noDataHeader;
    }

    public void bind(Document doc, String header, String subheader, String url, String moreUrl, int maxItemsPerRow, int maxRowCount, boolean hasDetailsLoaded, PlayStoreUiElementNode parentNode) {
        if (hasDetailsLoaded) {
            this.mDoc = doc;
            this.mUrl = url;
            this.mHeader = header;
            this.mSubheader = subheader;
            this.mMoreUrl = moreUrl;
            this.mMaxItemsPerRow = maxItemsPerRow;
            this.mMaxItemsCount = maxRowCount * maxItemsPerRow;
            this.mParentNode = parentNode;
            if (this.mLayoutSwitcher == null) {
                this.mLayoutSwitcher = new LayoutSwitcher(this, R.id.listing_layout, new RetryButtonListener() {
                    public void onRetry() {
                        DetailsPackSection.this.mItemListRequest.retryLoadItems();
                    }
                });
            }
            setupView();
            return;
        }
        setVisibility(8);
    }

    private void detachListeners() {
        if (this.mItemListRequest != null) {
            this.mItemListRequest.removeDataChangedListener(this);
            this.mItemListRequest.removeErrorListener(this);
        }
    }

    protected void populateContent() {
        this.mListingLayout.removeAllViews();
        int itemCount = Math.min(this.mItemListRequest.getCount(), this.mMaxItemsCount);
        int rowCount = ((this.mMaxItemsPerRow + itemCount) - 1) / this.mMaxItemsPerRow;
        int itemIndex = 0;
        if (rowCount == 0) {
            handleEmptyData();
            return;
        }
        this.mListingContent.setVisibility(0);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        int rowNum = 0;
        while (rowNum < rowCount) {
            BucketRow rowView = (BucketRow) inflater.inflate(R.layout.details_pack_row, this.mListingLayout, false);
            Document firstEntryInRowData = itemIndex < itemCount ? (Document) this.mItemListRequest.getItem(itemIndex) : null;
            if (firstEntryInRowData != null) {
                int cardLayoutId = firstEntryInRowData.getArtistDetails() != null ? R.layout.play_card_artist : R.layout.play_pack_card;
                for (int colNum = 0; colNum < this.mMaxItemsPerRow; colNum++) {
                    Document data = itemIndex < itemCount ? (Document) this.mItemListRequest.getItem(itemIndex) : null;
                    PlayCardViewBase childCard = (PlayCardViewBase) inflater.inflate(cardLayoutId, rowView, false);
                    if (data != null) {
                        PlayCardUtils.bindCard(childCard, data, this.mItemListRequest.getInitialUrl(), this.mBitmapLoader, this.mNavigationManager, this);
                    } else {
                        childCard.clearCardState();
                    }
                    rowView.addView(childCard);
                    itemIndex++;
                }
                this.mListingLayout.addView(rowView);
                rowNum++;
            } else {
                return;
            }
        }
    }

    private void setupView() {
        if (this.mItemListRequest != null) {
            this.mItemListRequest.clearTransientState();
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            handleEmptyData();
        } else {
            setupItemListRequest();
        }
    }

    private void attachToInternalRequest() {
        if (this.mItemListRequest == null) {
            throw new IllegalStateException("Cannot attach when no request is held internally");
        }
        this.mItemListRequest.addDataChangedListener(this);
        this.mItemListRequest.addErrorListener(this);
        if (this.mItemListRequest.getCount() != 0) {
            setVisibility(0);
            this.mLayoutSwitcher.switchToDataMode();
            prepareAndPopulateContent();
        } else if (!this.mItemListRequest.isMoreAvailable()) {
            handleEmptyData();
        } else if (this.mItemListRequest.inErrorState()) {
            this.mLayoutSwitcher.switchToErrorMode(ErrorStrings.get(getContext(), this.mItemListRequest.getVolleyError()));
        }
    }

    protected void handleEmptyData() {
        if (TextUtils.isEmpty(this.mNoDataHeader)) {
            setVisibility(8);
            return;
        }
        this.mHeaderView.setContent(this.mDoc.getBackend(), this.mHeader, this.mNoDataHeader, null, null);
        this.mListingContent.setVisibility(8);
    }

    private void setupItemListRequest() {
        detachListeners();
        this.mItemListRequest = new DfeList(this.mDfeApi, this.mUrl, false);
        attachToInternalRequest();
        this.mItemListRequest.addErrorListener(this);
        this.mItemListRequest.startLoadItems();
    }

    private void prepareAndPopulateContent() {
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mItemListRequest.getContainerDocument().getServerLogsCookie());
        populateHeader();
        populateContent();
    }

    protected void populateHeader() {
        if (TextUtils.isEmpty(this.mMoreUrl)) {
            this.mHeaderView.setContent(this.mDoc.getBackend(), this.mHeader, this.mSubheader, null, null);
        } else {
            this.mHeaderView.setContent(this.mDoc.getBackend(), this.mHeader, this.mSubheader, getResources().getString(R.string.more_results_no_count), new OnClickListener() {
                public void onClick(View v) {
                    DetailsPackSection.this.mNavigationManager.goBrowse(DetailsPackSection.this.mMoreUrl, null, DetailsPackSection.this.mDoc.getBackend(), DetailsPackSection.this.mToc, DetailsPackSection.this);
                }
            });
        }
    }

    public void onDataChanged() {
        this.mLayoutSwitcher.switchToDataMode();
        if (this.mItemListRequest.getCount() == 0) {
            handleEmptyData();
            return;
        }
        setVisibility(0);
        prepareAndPopulateContent();
    }

    public void onErrorResponse(VolleyError error) {
        if (this.mLayoutSwitcher != null) {
            this.mLayoutSwitcher.switchToErrorMode(ErrorStrings.get(getContext(), error));
        }
    }

    protected void onDetachedFromWindow() {
        detachListeners();
        if (this.mListingLayout != null) {
            this.mListingLayout.removeAllViews();
        }
        super.onDetachedFromWindow();
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
