package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayCardViewFeatured;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.wallet.instrumentmanager.R;

public class FeaturedItemsSection extends DetailsPackSection {
    private boolean mHasDetailsLoaded;

    public FeaturedItemsSection(Context context) {
        this(context, null);
    }

    public FeaturedItemsSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHasDetailsLoaded = false;
    }

    public void bind(Document doc, String header, String subheader, String url, String moreUrl, int maxItemsPerRow, int maxRowCount, boolean hasDetailsLoaded, PlayStoreUiElementNode parentNode) {
        super.bind(doc, header, subheader, url, null, 0, 0, hasDetailsLoaded, parentNode);
        this.mHasDetailsLoaded = hasDetailsLoaded;
    }

    protected void populateContent() {
        this.mListingLayout.removeAllViews();
        int itemCount = Math.min(this.mItemListRequest.getCount(), 3);
        if (itemCount == 0) {
            handleEmptyData();
            return;
        }
        int clusterLayoutId;
        this.mListingContent.setVisibility(0);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        switch (itemCount) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                clusterLayoutId = com.android.vending.R.layout.play_card_featured_cluster_1_item;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                clusterLayoutId = com.android.vending.R.layout.play_card_featured_cluster_2_items;
                break;
            default:
                clusterLayoutId = com.android.vending.R.layout.play_card_featured_cluster_3_items;
                break;
        }
        ViewGroup layout = (ViewGroup) inflater.inflate(clusterLayoutId, this.mListingLayout, false);
        for (int itemIndex = 0; itemIndex < itemCount; itemIndex++) {
            int cardLayoutId;
            switch (itemIndex) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    cardLayoutId = com.android.vending.R.id.primary_item;
                    break;
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    cardLayoutId = com.android.vending.R.id.second_item;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    cardLayoutId = com.android.vending.R.id.third_item;
                    break;
                default:
                    cardLayoutId = com.android.vending.R.id.primary_item;
                    break;
            }
            Document data = (Document) this.mItemListRequest.getItem(itemIndex);
            PlayCardViewFeatured childCard = (PlayCardViewFeatured) layout.findViewById(cardLayoutId);
            if (data != null) {
                PlayCardUtils.bindCard(childCard, data, this.mItemListRequest.getInitialUrl(), this.mBitmapLoader, this.mNavigationManager, this);
                PlayCardUtils.updatePrimaryActionButton(data, getContext(), childCard, this, this.mNavigationManager);
                childCard.bind(data, this.mBitmapLoader, this.mHasDetailsLoaded);
            } else {
                childCard.clearCardState();
            }
        }
        this.mListingLayout.addView(layout);
    }
}
