package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.DetailsTextViewBinder;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.BucketRow;
import com.google.android.finsky.layout.DetailsTextSection;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.StreamHeroSpacerView;
import com.google.android.finsky.layout.play.PlayCardClusterViewHeader;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.VideoSnippet;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import java.util.List;

public class EditorialRecyclerViewAdapter extends CardRecyclerViewAdapter {
    private final Document mContainerDocument;
    private DetailsTextViewBinder mDetailsTextViewBinder;
    private final int mExtraPaddingLeftRight;
    private final int mNumItemsPerFooterRow;

    private class EditorialVideoHolder {
        private TextView mDescription;
        private TextView mTitle;
        private HeroGraphicView mVideoImage;
        private ViewGroup mWrapper;

        private EditorialVideoHolder() {
        }
    }

    public EditorialRecyclerViewAdapter(Context context, DfeApi dfeApi, NavigationManager navManager, BitmapLoader loader, DfeToc toc, ClientMutationCache clientMutationCache, Document containerDocument, DfeList listData, boolean isRestoring, PlayStoreUiElementNode parentNode) {
        super(context, dfeApi, navManager, loader, toc, clientMutationCache, listData, null, null, isRestoring, false, 2, parentNode);
        Resources res = context.getResources();
        this.mNumItemsPerFooterRow = res.getInteger(R.integer.editorial_bucket_columns);
        this.mExtraPaddingLeftRight = res.getDimensionPixelSize(R.dimen.play_collection_edge_padding_minus_card_half_spacing);
        this.mContainerDocument = containerDocument;
    }

    protected void computeLooseItemRowsValues(Resources res) {
        this.mLooseItemCellId = this.mContainerList.getBackendId() == 3 ? R.layout.editorial_app_bucket_entry : R.layout.editorial_nonapp_bucket_entry;
        this.mLooseItemColCount = res.getInteger(R.integer.editorial_bucket_columns);
    }

    protected boolean hasExtraLeadingSpacer() {
        return false;
    }

    public boolean hasBackgroundView() {
        return true;
    }

    public void configureBackgroundView(HeroGraphicView backgroundView) {
        backgroundView.bindDetailsEditorial(this.mContainerDocument, this.mBitmapLoader, this.mParentNode);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                view = inflate(R.layout.play_list_hero_spacer, parent, false);
                break;
            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                view = inflate(R.layout.editorial_text_description, parent, false);
                break;
            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                view = inflate(R.layout.play_card_cluster_header, parent, false);
                break;
            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                view = inflate(R.layout.bucket_row, parent, false);
                for (int i = 0; i < this.mNumItemsPerFooterRow; i++) {
                    ViewGroup emptyEntry = (ViewGroup) inflate(R.layout.editorial_video_footer_entry, (ViewGroup) view, false);
                    emptyEntry.setTag(getEditorialVideoWrapper(emptyEntry));
                    ((ViewGroup) view).addView(emptyEntry);
                }
                break;
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
        return new PlayRecyclerView.ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int itemViewType = viewHolder.getItemViewType();
        View view = viewHolder.itemView;
        switch (itemViewType) {
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                bindEditorialSpacerView(view);
                return;
            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                bindEditorialDescriptionView(view);
                return;
            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                bindEditorialFooterHeader(view);
                return;
            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                bindEditorialFooterRow(view, position);
                return;
            default:
                super.onBindViewHolder(viewHolder, position);
                return;
        }
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return 22;
        }
        int editorialFooterRowCount = getEditorialFooterItemCount();
        boolean shouldShowEditorialFooter = editorialFooterRowCount > 0;
        int footerRowCountWithHeader = editorialFooterRowCount + 1;
        if (position == 1) {
            return 25;
        }
        if (position == 2) {
            return 26;
        }
        if (shouldShowEditorialFooter && !isMoreDataAvailable()) {
            int itemCount = getItemCount();
            if (position == itemCount - footerRowCountWithHeader) {
                return 27;
            }
            if (position >= itemCount - editorialFooterRowCount) {
                return 28;
            }
        }
        return super.getItemViewType(position - 2);
    }

    private int getEditorialFooterItemCount() {
        return (int) Math.ceil(((double) this.mContainerDocument.getEditorialSeriesContainer().videoSnippet.length) / ((double) this.mNumItemsPerFooterRow));
    }

    public int getPrependedRowsCount() {
        return super.getPrependedRowsCount() + 2;
    }

    protected int getDataRowsCount() {
        int result = super.getDataRowsCount() + 2;
        int editorialFooterItemCount = getEditorialFooterItemCount();
        if (editorialFooterItemCount > 0) {
            return result + (editorialFooterItemCount + 1);
        }
        return result;
    }

    private void bindEditorialFooterHeader(View view) {
        PlayCardClusterViewHeader plainHeader = (PlayCardClusterViewHeader) view;
        plainHeader.setContent(this.mContainerList.getBackendId(), this.mContext.getString(R.string.related_videos), null, null, null);
        plainHeader.setExtraHorizontalPadding(this.mExtraPaddingLeftRight);
    }

    private void bindEditorialFooterRow(View view, int position) {
        BucketRow bucketRow = (BucketRow) view;
        int offset = ((position - super.getDataRowsCount()) - 4) * this.mNumItemsPerFooterRow;
        bucketRow.setSameChildHeight(true);
        VideoSnippet[] videoSnippets = this.mContainerDocument.getEditorialSeriesContainer().videoSnippet;
        List<Image> videoImages = Lists.newArrayList();
        for (int i = 0; i < this.mNumItemsPerFooterRow; i++) {
            EditorialVideoHolder holder = (EditorialVideoHolder) bucketRow.getChildAt(i).getTag();
            int snippetNumber = offset + i;
            if (snippetNumber > videoSnippets.length - 1) {
                holder.mWrapper.setVisibility(4);
            } else {
                VideoSnippet snippet = videoSnippets[snippetNumber];
                holder.mWrapper.setVisibility(0);
                holder.mTitle.setText(snippet.title);
                holder.mDescription.setText(snippet.description);
                String videoUrl = null;
                for (Image image : snippet.image) {
                    if (image.imageType == 3) {
                        videoUrl = image.imageUrl;
                    } else if (image.imageType == 1) {
                        videoImages.add(image);
                    }
                }
                if (!TextUtils.isEmpty(videoUrl) && videoImages.size() > 0) {
                    holder.mVideoImage.bindEditorialVideoFooter(this.mBitmapLoader, videoImages);
                    holder.mVideoImage.showPlayIcon(videoUrl, snippet.title, false, this.mContainerDocument.isMature(), this.mContainerDocument.getBackend(), this.mParentNode);
                }
                videoImages.clear();
            }
        }
        view.setPadding(this.mExtraPaddingLeftRight, view.getPaddingTop(), this.mExtraPaddingLeftRight, view.getPaddingBottom());
    }

    private EditorialVideoHolder getEditorialVideoWrapper(ViewGroup root) {
        EditorialVideoHolder wrapper = new EditorialVideoHolder();
        wrapper.mWrapper = root;
        wrapper.mVideoImage = (HeroGraphicView) root.findViewById(R.id.videoimage);
        wrapper.mTitle = (TextView) root.findViewById(R.id.video_text);
        wrapper.mDescription = (TextView) root.findViewById(R.id.video_description);
        return wrapper;
    }

    private void bindEditorialSpacerView(View view) {
        ((StreamHeroSpacerView) view).configureHeroImage(HeroGraphicView.getHeroGraphic(this.mContainerDocument) != null, HeroGraphicView.getHeroAspectRatio(this.mContainerDocument.getDocumentType()));
    }

    private void bindEditorialDescriptionView(View view) {
        DetailsTextSection description = (DetailsTextSection) view;
        int backgroundFillColor = description.getResources().getColor(R.color.white);
        if (this.mContainerDocument.hasImages(1)) {
            backgroundFillColor = UiUtils.getFillColor(this.mContainerDocument.getEditorialSeriesContainer(), backgroundFillColor);
        }
        description.bindEditorialDescription(this.mNavigationManager, this.mContainerDocument, true, null, this.mParentNode, backgroundFillColor, null);
    }

    protected void configureContainerOfLooseItemsWithoutReasons(BucketRow container) {
        container.setSameChildHeight(true);
    }

    public void onSaveInstanceState(PlayRecyclerView view, Bundle savedInstanceState) {
        if (this.mDetailsTextViewBinder != null) {
            this.mDetailsTextViewBinder.saveInstanceState(savedInstanceState);
        }
        super.onSaveInstanceState(view, savedInstanceState);
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mDetailsTextViewBinder != null) {
            this.mDetailsTextViewBinder.onDestroyView();
            this.mDetailsTextViewBinder = null;
        }
    }
}
