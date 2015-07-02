package com.google.android.play.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.play.utils.Compound;
import com.google.android.wallet.instrumentmanager.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FlowLayoutManager extends LayoutManager {
    public static final int HALF_VISIBLE_HEIGHT;
    private static final Rect sDecorInsets;
    private int mComputedScrollExtent;
    private int mComputedScrollOffset;
    private int mComputedScrollRange;
    private int mExtraRenderAreaBottomCompound;
    private int mExtraRenderAreaTopCompound;
    private FillState mFillState;
    private boolean mItemChangesAffectFlow;
    private SavedState mPendingSavedState;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private final List<ParagraphInfo> mSections;
    private boolean mTrueAnchorAtPositionZero;
    private OnViewRenderedListener mViewRenderedListener;
    private boolean mWasViewRenderedListenerAutoRegistered;

    class AnonymousClass1 extends LinearSmoothScroller {
        final /* synthetic */ FlowLayoutManager this$0;

        public PointF computeScrollVectorForPosition(int targetPosition) {
            if (getChildCount() == 0) {
                return null;
            }
            return new PointF(0.0f, (float) (targetPosition < this.this$0.getPosition(this.this$0.getChildAt(0)) ? -1 : 1));
        }
    }

    public interface OnViewRenderedListener {
        void onViewRendered(ViewHolder viewHolder);
    }

    public interface AutoRegisteredOnViewRenderedListener extends OnViewRenderedListener {
    }

    private static final class FillState {
        int mHeightFilled;
        int mNextAnchorPosition;
        ItemInfo mNextItem;
        int mNextItemChildIndex;
        LayoutParams mNextItemLayoutParams;
        int mNextItemPosition;

        private FillState() {
        }

        public ItemInfo takeNextItem() {
            ItemInfo nextItem = this.mNextItem;
            this.mNextItem = null;
            return nextItem;
        }

        public void reset() {
            this.mHeightFilled = 0;
            this.mNextAnchorPosition = -1;
            this.mNextItemPosition = -1;
            this.mNextItemChildIndex = -1;
            this.mNextItemLayoutParams = null;
            if (this.mNextItem != null) {
                this.mNextItem.recycle();
                this.mNextItem = null;
            }
        }

        public String toString() {
            return "FillState{mHeightFilled=" + this.mHeightFilled + ",mNextAnchorPosition=" + this.mNextAnchorPosition + ",mNextItem=" + (this.mNextItem == null ? "null" : "notnull") + ",mNextItemPosition=" + this.mNextItemPosition + ",mNextItemChildIndex=" + this.mNextItemChildIndex;
        }
    }

    private static abstract class MultiItemInfo {
        public int mMeasureMode;
        public int mPositionStart;
        public int mTotalHeight;

        protected abstract boolean invalidateFromInternal(int i);

        protected abstract int onArrange(boolean z, int i);

        public abstract int validPositionEnd();

        private MultiItemInfo() {
        }

        public final void invalidateHeight() {
            this.mTotalHeight = -1;
        }

        public final int arrangeIfNecessary(int totalItemCount) {
            int i;
            boolean z = true;
            if (this.mPositionStart == 0) {
                i = 1;
            } else {
                i = 0;
            }
            int newMeasureMode = i | (validPositionEnd() == totalItemCount ? 2 : 0);
            if (this.mTotalHeight == -1 || newMeasureMode != this.mMeasureMode) {
                if (this.mTotalHeight != -1) {
                    z = false;
                }
                this.mTotalHeight = Math.max(0, onArrange(z, totalItemCount));
                this.mMeasureMode = newMeasureMode;
            }
            return this.mTotalHeight;
        }

        public final int invalidateFrom(int position) {
            if (position <= this.mPositionStart) {
                invalidateHeight();
                return 2;
            } else if (!invalidateFromInternal(position)) {
                return 0;
            } else {
                invalidateHeight();
                return 1;
            }
        }

        public void offsetPositions(int delta) {
            this.mPositionStart += delta;
        }

        protected void reset() {
            invalidateHeight();
            this.mPositionStart = -1;
            this.mMeasureMode = 0;
        }
    }

    private static abstract class LineInfo extends MultiItemInfo {
        public int mOffsetStart;

        public abstract void clearMeasuredInCurrentPass();

        abstract void debugPrint(int i, StringBuilder stringBuilder);

        public abstract int getItemTopOffset(int i);

        public abstract void recycle();

        private LineInfo() {
            super();
        }
    }

    private static final class InlineFlowLineInfo extends LineInfo {
        private static final Pool<InlineFlowLineInfo> sPool;
        public final List<ItemInfo> mItems;
        public int mLineWidth;
        public int mUsedWidth;

        static {
            sPool = new SimplePool(10);
        }

        public static InlineFlowLineInfo obtain(int positionStart, int lineWidth, int offsetStart, ItemInfo measuredFirstItem) {
            InlineFlowLineInfo line = (InlineFlowLineInfo) sPool.acquire();
            if (line == null) {
                line = new InlineFlowLineInfo();
            }
            line.mPositionStart = positionStart;
            line.mOffsetStart = offsetStart;
            line.mLineWidth = lineWidth;
            line.addMeasuredItem(measuredFirstItem);
            return line;
        }

        private InlineFlowLineInfo() {
            super();
            this.mItems = new ArrayList();
            reset();
        }

        public void clearMeasuredInCurrentPass() {
            for (int i = this.mItems.size() - 1; i >= 0; i--) {
                ((ItemInfo) this.mItems.get(i)).mMeasuredInCurrentPass = false;
            }
        }

        public void addMeasuredItem(ItemInfo item) {
            if (item.mMeasuredInCurrentPass) {
                this.mUsedWidth += (item.mDecoratedWidth + item.mMarginStart) + item.mMarginEnd;
                this.mItems.add(item);
                invalidateHeight();
                return;
            }
            throw new IllegalArgumentException("Item not measured");
        }

        public int validPositionEnd() {
            return this.mPositionStart + this.mItems.size();
        }

        protected int onArrange(boolean fullArrangementRequired, int totalItemCount) {
            boolean isFirstLine;
            boolean isLastLine;
            if (fullArrangementRequired) {
                verticallyAlignItems();
            }
            if (this.mPositionStart == 0) {
                isFirstLine = true;
            } else {
                isFirstLine = false;
            }
            if (this.mPositionStart + this.mItems.size() == totalItemCount) {
                isLastLine = true;
            } else {
                isLastLine = false;
            }
            return normalizeOffsetsAndAddMargins(isFirstLine, isLastLine);
        }

        private void verticallyAlignItems() {
            int i;
            int itemCount = this.mItems.size();
            int lineTop = 0;
            int lineBottom = 0;
            int nonBaselineAlignedMaxHeight = Integer.MIN_VALUE;
            for (i = 0; i < itemCount; i++) {
                ItemInfo item = (ItemInfo) this.mItems.get(i);
                if (item.mVAlign == 0) {
                    int itemTop = -item.mBaseline;
                    item.mTopOffset = itemTop;
                    if (itemTop < lineTop) {
                        lineTop = itemTop;
                    }
                    int itemBottom = itemTop + item.mDecoratedHeight;
                    if (itemBottom > lineBottom) {
                        lineBottom = itemBottom;
                    }
                } else if (item.mDecoratedHeight > nonBaselineAlignedMaxHeight) {
                    nonBaselineAlignedMaxHeight = item.mDecoratedHeight;
                }
            }
            if (nonBaselineAlignedMaxHeight != Integer.MIN_VALUE) {
                int baselineLineHeight = lineBottom - lineTop;
                if (baselineLineHeight < nonBaselineAlignedMaxHeight) {
                    lineTop = baselineLineHeight == 0 ? 0 : (int) (((float) nonBaselineAlignedMaxHeight) * (((float) lineTop) / ((float) baselineLineHeight)));
                    lineBottom = lineTop + nonBaselineAlignedMaxHeight;
                }
                for (i = 0; i < itemCount; i++) {
                    item = (ItemInfo) this.mItems.get(i);
                    switch (item.mVAlign) {
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            item.mTopOffset = lineBottom - item.mDecoratedHeight;
                            break;
                        case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            item.mTopOffset = lineTop;
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        private int normalizeOffsetsAndAddMargins(boolean isFirstLine, boolean isLastLine) {
            int itemCount = this.mItems.size();
            if (itemCount == 0) {
                return 0;
            }
            int i;
            int lineTop = Integer.MAX_VALUE;
            int lineBottom = Integer.MIN_VALUE;
            for (i = 0; i < itemCount; i++) {
                ItemInfo item = (ItemInfo) this.mItems.get(i);
                int itemTop = item.mTopOffset - (isFirstLine ? item.mMarginTopForFirstLine : item.mMarginTop);
                if (itemTop < lineTop) {
                    lineTop = itemTop;
                }
                int itemBottom = (item.mDecoratedHeight + item.mTopOffset) + (isLastLine ? item.mMarginBottomForLastLine : item.mMarginBottom);
                if (itemBottom > lineBottom) {
                    lineBottom = itemBottom;
                }
            }
            if (lineTop != 0) {
                for (i = 0; i < itemCount; i++) {
                    ItemInfo itemInfo = (ItemInfo) this.mItems.get(i);
                    itemInfo.mTopOffset -= lineTop;
                }
                lineBottom -= lineTop;
            }
            return Math.max(0, lineBottom);
        }

        public int getItemTopOffset(int position) {
            return ((ItemInfo) this.mItems.get(position - this.mPositionStart)).mTopOffset;
        }

        protected boolean invalidateFromInternal(int position) {
            int itemCount = this.mItems.size();
            if (this.mPositionStart + itemCount <= position) {
                return false;
            }
            int firstInvalidatedIndex = position - this.mPositionStart;
            for (int i = itemCount - 1; i >= firstInvalidatedIndex; i--) {
                ItemInfo item = (ItemInfo) this.mItems.remove(i);
                this.mUsedWidth -= (item.mDecoratedWidth + item.mMarginStart) + item.mMarginEnd;
                item.recycle();
            }
            return true;
        }

        public void recycle() {
            reset();
            sPool.release(this);
        }

        protected void reset() {
            super.reset();
            this.mLineWidth = 0;
            this.mUsedWidth = 0;
            for (int i = this.mItems.size() - 1; i >= 0; i--) {
                ((ItemInfo) this.mItems.get(i)).recycle();
            }
            this.mItems.clear();
        }

        void debugPrint(int lineIndex, StringBuilder output) {
            output.append('@').append(this.mPositionStart).append('-').append(validPositionEnd());
        }
    }

    private static final class ItemInfo {
        private static final Pool<ItemInfo> sPool;
        public int mBaseline;
        public int mDecoratedHeight;
        public int mDecoratedWidth;
        public float mGridCellSize;
        public int mGridInsetEnd;
        public int mGridInsetStart;
        public int mMarginBottom;
        public int mMarginBottomForLastLine;
        public int mMarginEnd;
        public int mMarginStart;
        public int mMarginTop;
        public int mMarginTopForFirstLine;
        public boolean mMeasuredInCurrentPass;
        public int mTopOffset;
        public int mVAlign;

        static {
            sPool = new SimplePool(30);
        }

        public static ItemInfo obtain() {
            ItemInfo item = (ItemInfo) sPool.acquire();
            return item != null ? item : new ItemInfo();
        }

        private ItemInfo() {
            reset();
        }

        public void loadParams(LayoutParams params, int fullContextWidth) {
            params.getGridDefinition(fullContextWidth, this);
            this.mMarginTop = params.getTopMargin(this.mGridCellSize);
            this.mMarginTopForFirstLine = params.getTopMarginForFirstLine(this.mGridCellSize);
            this.mMarginStart = params.getStartMargin(this.mGridCellSize);
            this.mMarginEnd = params.getEndMargin(this.mGridCellSize);
            this.mMarginBottom = params.getBottomMargin(this.mGridCellSize);
            this.mMarginBottomForLastLine = params.getBottomMarginForLastLine(this.mGridCellSize);
            this.mVAlign = params.vAlign;
        }

        public boolean hasSameGridAs(ItemInfo other) {
            return Float.compare(this.mGridCellSize, other.mGridCellSize) == 0 && this.mGridInsetStart == other.mGridInsetStart && this.mGridInsetEnd == other.mGridInsetEnd;
        }

        public void loadMeasurements(FlowLayoutManager layoutManager, View child, boolean expectSame) {
            int decoratedWidth = layoutManager.getDecoratedMeasuredWidth(child);
            int decoratedHeight = layoutManager.getDecoratedMeasuredHeight(child);
            int baseline = child.getBaseline();
            if (baseline < 0 || baseline > child.getMeasuredHeight()) {
                baseline = decoratedHeight;
            } else {
                baseline += layoutManager.getTopDecorationHeight(child);
            }
            if (expectSame && !(decoratedWidth == this.mDecoratedWidth && decoratedHeight == this.mDecoratedHeight && baseline == this.mBaseline)) {
                Log.w("FlowLayoutManager", "Child measurement changed without notifying from the adapter! Some layout may be incorrect.");
            }
            this.mDecoratedWidth = decoratedWidth;
            this.mDecoratedHeight = decoratedHeight;
            this.mBaseline = baseline;
            this.mMeasuredInCurrentPass = true;
        }

        public void recycle() {
            reset();
            sPool.release(this);
        }

        private void reset() {
            this.mDecoratedWidth = -1;
            this.mDecoratedHeight = -1;
            this.mMarginTop = 0;
            this.mMarginTopForFirstLine = 0;
            this.mMarginStart = 0;
            this.mMarginEnd = 0;
            this.mMarginBottom = 0;
            this.mMarginBottomForLastLine = 0;
            this.mBaseline = 0;
            this.mVAlign = 0;
            this.mGridInsetStart = 0;
            this.mGridInsetEnd = 0;
            this.mGridCellSize = Float.NaN;
            this.mTopOffset = 0;
            this.mMeasuredInCurrentPass = false;
        }
    }

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        static Field sViewHolderField;
        public int bottomMarginCompound;
        public int endMarginCompound;
        public int firstLineTopMarginCompound;
        public int flow;
        public int flowHeightCompound;
        public int flowInsetBottomCompound;
        public int flowInsetEndCompound;
        public int flowInsetStartCompound;
        public int flowInsetTopCompound;
        public int flowWidthCompound;
        public float gridColumnCount;
        public int gridInsetEnd;
        public int gridInsetStart;
        public int gridMinCellSize;
        public int heightCompound;
        public int lastLineBottomMarginCompound;
        public int lineWrap;
        public int maxGridWidth;
        public int startMarginCompound;
        public int topMarginCompound;
        public int vAlign;
        public int widthCompound;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.gridInsetStart = 0;
            this.gridInsetEnd = 0;
            this.maxGridWidth = 0;
            this.gridColumnCount = 0.0f;
            this.gridMinCellSize = 0;
            this.topMarginCompound = Integer.MAX_VALUE;
            this.startMarginCompound = Integer.MAX_VALUE;
            this.endMarginCompound = Integer.MAX_VALUE;
            this.bottomMarginCompound = Integer.MAX_VALUE;
            this.firstLineTopMarginCompound = Integer.MAX_VALUE;
            this.lastLineBottomMarginCompound = Integer.MAX_VALUE;
            this.vAlign = 0;
            this.flow = 0;
            this.flowInsetTopCompound = 0;
            this.flowInsetStartCompound = 0;
            this.flowInsetEndCompound = 0;
            this.flowInsetBottomCompound = 0;
            this.flowWidthCompound = -1;
            this.flowHeightCompound = -1;
            this.lineWrap = 0;
            TypedArray typedArray = context.obtainStyledAttributes(attrs, com.google.android.play.R.styleable.FlowLayoutManager_Layout_Style);
            int defaultStyle = typedArray.getResourceId(com.google.android.play.R.styleable.FlowLayoutManager_Layout_Style_layout_flmStyle, com.google.android.play.R.style.FlowLayoutManager_Layout_Default);
            typedArray.recycle();
            typedArray = context.obtainStyledAttributes(attrs, com.google.android.play.R.styleable.FlowLayoutManager_Layout, 0, defaultStyle);
            this.widthCompound = getCompoundLayoutDimension(typedArray, "layout_flmWidth", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmWidth, this.width);
            this.heightCompound = getCompoundLayoutDimension(typedArray, "layout_flmHeight", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmHeight, this.height);
            this.gridInsetStart = typedArray.getDimensionPixelOffset(com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmGridInsetStart, 0);
            this.gridInsetEnd = typedArray.getDimensionPixelOffset(com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmGridInsetEnd, 0);
            this.maxGridWidth = typedArray.getDimensionPixelSize(com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmMaxGridWidth, 0);
            this.gridColumnCount = typedArray.getFloat(com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmGridColumnCount, 0.0f);
            this.gridMinCellSize = typedArray.getDimensionPixelSize(com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmGridMinCellSize, 0);
            int defaultMargin = Compound.getCompound(typedArray, "layout_flmMargin", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmMargin, false);
            this.topMarginCompound = getCompoundMargin(typedArray, "layout_flmMarginTop", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmMarginTop, defaultMargin);
            this.startMarginCompound = getCompoundMargin(typedArray, "layout_flmMarginStart", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmMarginStart, defaultMargin);
            this.endMarginCompound = getCompoundMargin(typedArray, "layout_flmMarginEnd", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmMarginEnd, defaultMargin);
            this.bottomMarginCompound = getCompoundMargin(typedArray, "layout_flmMarginBottom", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmMarginBottom, defaultMargin);
            this.firstLineTopMarginCompound = Compound.getCompound(typedArray, "layout_flmMarginTopForFirstLine", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmMarginTopForFirstLine, false);
            this.lastLineBottomMarginCompound = Compound.getCompound(typedArray, "layout_flmMarginBottomForLastLine", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmMarginBottomForLastLine, false);
            this.vAlign = typedArray.getInteger(com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmVAlign, 0);
            this.flow = typedArray.getInteger(com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmFlow, 0);
            this.flowInsetTopCompound = Compound.getCompound(typedArray, "layout_flmFlowInsetTop", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmFlowInsetTop, false);
            this.flowInsetStartCompound = Compound.getCompound(typedArray, "layout_flmFlowInsetStart", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmFlowInsetStart, false);
            this.flowInsetEndCompound = Compound.getCompound(typedArray, "layout_flmFlowInsetEnd", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmFlowInsetEnd, false);
            this.flowInsetBottomCompound = Compound.getCompound(typedArray, "layout_flmFlowInsetBottom", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmFlowInsetBottom, false);
            this.flowWidthCompound = Compound.getCompound(typedArray, "layout_flmFlowWidth", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmFlowWidth, true);
            this.flowHeightCompound = Compound.getCompound(typedArray, "layout_flmFlowHeight", com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmFlowHeight, true);
            this.lineWrap = typedArray.getInteger(com.google.android.play.R.styleable.FlowLayoutManager_Layout_layout_flmLineWrap, 0);
            typedArray.recycle();
        }

        public LayoutParams(LayoutParams source) {
            super((MarginLayoutParams) source);
            this.gridInsetStart = 0;
            this.gridInsetEnd = 0;
            this.maxGridWidth = 0;
            this.gridColumnCount = 0.0f;
            this.gridMinCellSize = 0;
            this.topMarginCompound = Integer.MAX_VALUE;
            this.startMarginCompound = Integer.MAX_VALUE;
            this.endMarginCompound = Integer.MAX_VALUE;
            this.bottomMarginCompound = Integer.MAX_VALUE;
            this.firstLineTopMarginCompound = Integer.MAX_VALUE;
            this.lastLineBottomMarginCompound = Integer.MAX_VALUE;
            this.vAlign = 0;
            this.flow = 0;
            this.flowInsetTopCompound = 0;
            this.flowInsetStartCompound = 0;
            this.flowInsetEndCompound = 0;
            this.flowInsetBottomCompound = 0;
            this.flowWidthCompound = -1;
            this.flowHeightCompound = -1;
            this.lineWrap = 0;
            this.widthCompound = source.widthCompound;
            this.heightCompound = source.heightCompound;
            this.maxGridWidth = source.maxGridWidth;
            this.gridInsetStart = source.gridInsetStart;
            this.gridInsetEnd = source.gridInsetEnd;
            this.gridColumnCount = source.gridColumnCount;
            this.gridMinCellSize = source.gridMinCellSize;
            this.topMarginCompound = source.topMarginCompound;
            this.startMarginCompound = source.startMarginCompound;
            this.endMarginCompound = source.endMarginCompound;
            this.bottomMarginCompound = source.bottomMarginCompound;
            this.firstLineTopMarginCompound = source.firstLineTopMarginCompound;
            this.lastLineBottomMarginCompound = source.lastLineBottomMarginCompound;
            this.vAlign = source.vAlign;
            this.flow = source.flow;
            this.flowInsetTopCompound = source.flowInsetTopCompound;
            this.flowInsetStartCompound = source.flowInsetStartCompound;
            this.flowInsetEndCompound = source.flowInsetEndCompound;
            this.flowInsetBottomCompound = source.flowInsetBottomCompound;
            this.flowWidthCompound = source.flowWidthCompound;
            this.flowHeightCompound = source.flowHeightCompound;
            this.lineWrap = source.lineWrap;
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
            this.gridInsetStart = 0;
            this.gridInsetEnd = 0;
            this.maxGridWidth = 0;
            this.gridColumnCount = 0.0f;
            this.gridMinCellSize = 0;
            this.topMarginCompound = Integer.MAX_VALUE;
            this.startMarginCompound = Integer.MAX_VALUE;
            this.endMarginCompound = Integer.MAX_VALUE;
            this.bottomMarginCompound = Integer.MAX_VALUE;
            this.firstLineTopMarginCompound = Integer.MAX_VALUE;
            this.lastLineBottomMarginCompound = Integer.MAX_VALUE;
            this.vAlign = 0;
            this.flow = 0;
            this.flowInsetTopCompound = 0;
            this.flowInsetStartCompound = 0;
            this.flowInsetEndCompound = 0;
            this.flowInsetBottomCompound = 0;
            this.flowWidthCompound = -1;
            this.flowHeightCompound = -1;
            this.lineWrap = 0;
            this.widthCompound = this.width;
            this.heightCompound = this.height;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
            this.gridInsetStart = 0;
            this.gridInsetEnd = 0;
            this.maxGridWidth = 0;
            this.gridColumnCount = 0.0f;
            this.gridMinCellSize = 0;
            this.topMarginCompound = Integer.MAX_VALUE;
            this.startMarginCompound = Integer.MAX_VALUE;
            this.endMarginCompound = Integer.MAX_VALUE;
            this.bottomMarginCompound = Integer.MAX_VALUE;
            this.firstLineTopMarginCompound = Integer.MAX_VALUE;
            this.lastLineBottomMarginCompound = Integer.MAX_VALUE;
            this.vAlign = 0;
            this.flow = 0;
            this.flowInsetTopCompound = 0;
            this.flowInsetStartCompound = 0;
            this.flowInsetEndCompound = 0;
            this.flowInsetBottomCompound = 0;
            this.flowWidthCompound = -1;
            this.flowHeightCompound = -1;
            this.lineWrap = 0;
            this.widthCompound = this.width;
            this.heightCompound = this.height;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gridInsetStart = 0;
            this.gridInsetEnd = 0;
            this.maxGridWidth = 0;
            this.gridColumnCount = 0.0f;
            this.gridMinCellSize = 0;
            this.topMarginCompound = Integer.MAX_VALUE;
            this.startMarginCompound = Integer.MAX_VALUE;
            this.endMarginCompound = Integer.MAX_VALUE;
            this.bottomMarginCompound = Integer.MAX_VALUE;
            this.firstLineTopMarginCompound = Integer.MAX_VALUE;
            this.lastLineBottomMarginCompound = Integer.MAX_VALUE;
            this.vAlign = 0;
            this.flow = 0;
            this.flowInsetTopCompound = 0;
            this.flowInsetStartCompound = 0;
            this.flowInsetEndCompound = 0;
            this.flowInsetBottomCompound = 0;
            this.flowWidthCompound = -1;
            this.flowHeightCompound = -1;
            this.lineWrap = 0;
            this.widthCompound = width;
            this.heightCompound = height;
        }

        protected void setBaseAttributes(TypedArray typedArray, int widthAttr, int heightAttr) {
            this.width = typedArray.getLayoutDimension(widthAttr, Integer.MAX_VALUE);
            this.height = typedArray.getLayoutDimension(heightAttr, Integer.MAX_VALUE);
        }

        private static int getCompoundLayoutDimension(TypedArray typedArray, String attrName, int index, int androidNamespaceParam) {
            if (typedArray.hasValue(index) || androidNamespaceParam == Integer.MAX_VALUE) {
                return Compound.getCompound(typedArray, attrName, index, true);
            }
            if (-2 <= androidNamespaceParam && androidNamespaceParam <= 16777215) {
                return androidNamespaceParam;
            }
            throw new IllegalArgumentException(typedArray.getPositionDescription() + ": out-of-range dimension length for " + attrName);
        }

        private static int getCompoundMargin(TypedArray typedArray, String attrName, int index, int defaultMarginCompound) {
            if (typedArray.hasValue(index)) {
                return Compound.getCompound(typedArray, attrName, index, false);
            }
            return defaultMarginCompound;
        }

        int getEffectiveLineWrapAction() {
            if (this.widthCompound == -1 || this.flow != 0) {
                return 2;
            }
            return this.lineWrap & 3;
        }

        int getLineWrapFlow() {
            return this.lineWrap & 12;
        }

        boolean isAnchorCandidate() {
            return getEffectiveLineWrapAction() == 2 && getLineWrapFlow() == 8;
        }

        float getGridDefinition(int fullContextWidth, ItemInfo item) {
            float effectiveColumnCount;
            int effectiveGridInsetStart = this.gridInsetStart;
            int effectiveGridInsetEnd = this.gridInsetEnd;
            int effectiveGridWidth = (fullContextWidth - effectiveGridInsetStart) - effectiveGridInsetEnd;
            if (this.maxGridWidth > 0 && effectiveGridWidth > this.maxGridWidth) {
                int unused = effectiveGridWidth - this.maxGridWidth;
                effectiveGridWidth = this.maxGridWidth;
                effectiveGridInsetStart += unused >> 1;
                effectiveGridInsetEnd += (unused >> 1) + (unused & 1);
            }
            if (this.gridMinCellSize > 0) {
                float maxColumnCount = ((float) effectiveGridWidth) / ((float) this.gridMinCellSize);
                if (0.0f >= this.gridColumnCount || this.gridColumnCount > maxColumnCount) {
                    effectiveColumnCount = (float) Math.max(1.0d, Math.floor((double) maxColumnCount));
                } else {
                    effectiveColumnCount = this.gridColumnCount;
                }
            } else if (this.gridColumnCount <= 0.0f) {
                effectiveColumnCount = Float.NaN;
            } else {
                effectiveColumnCount = this.gridColumnCount;
            }
            if (item != null) {
                item.mGridCellSize = ((float) Math.max(0, effectiveGridWidth)) / effectiveColumnCount;
                item.mGridInsetStart = effectiveGridInsetStart;
                item.mGridInsetEnd = effectiveGridInsetEnd;
            }
            return effectiveColumnCount;
        }

        int getTopMargin(float cellSize) {
            if (this.topMarginCompound == Integer.MAX_VALUE) {
                return this.topMargin;
            }
            return getPixelSize("layout_flmMarginTop", this.topMarginCompound, cellSize, false);
        }

        int getTopMarginForFirstLine(float cellSize) {
            if (this.firstLineTopMarginCompound == Integer.MAX_VALUE) {
                return getTopMargin(cellSize);
            }
            return getPixelSize("layout_flmMarginTopForFirstLine", this.firstLineTopMarginCompound, cellSize, false);
        }

        int getStartMargin(float cellSize) {
            if (this.startMarginCompound == Integer.MAX_VALUE) {
                return MarginLayoutParamsCompat.getMarginStart(this);
            }
            return getPixelSize("layout_flmMarginStart", this.startMarginCompound, cellSize, false);
        }

        int getEndMargin(float cellSize) {
            if (this.endMarginCompound == Integer.MAX_VALUE) {
                return MarginLayoutParamsCompat.getMarginEnd(this);
            }
            return getPixelSize("layout_flmMarginEnd", this.endMarginCompound, cellSize, false);
        }

        int getBottomMargin(float cellSize) {
            if (this.bottomMarginCompound == Integer.MAX_VALUE) {
                return this.bottomMargin;
            }
            return getPixelSize("layout_flmMarginBottom", this.bottomMarginCompound, cellSize, false);
        }

        int getBottomMarginForLastLine(float cellSize) {
            if (this.lastLineBottomMarginCompound == Integer.MAX_VALUE) {
                return getBottomMargin(cellSize);
            }
            return getPixelSize("layout_flmMarginBottomForLastLine", this.lastLineBottomMarginCompound, cellSize, false);
        }

        int getFlowWidth(float cellSize) {
            return getPixelSize("layout_flmFlowWidth", this.flowWidthCompound, cellSize, true);
        }

        int getFlowHeight(float cellSize) {
            return getPixelSize("layout_flmFlowHeight", this.flowHeightCompound, cellSize, true);
        }

        int getFlowInsetTop(float cellSize) {
            return getPixelSize("layout_flmFlowInsetTop", this.flowInsetTopCompound, cellSize, false);
        }

        int getFlowInsetStart(float cellSize) {
            return getPixelSize("layout_flmFlowInsetStart", this.flowInsetStartCompound, cellSize, false);
        }

        int getFlowInsetEnd(float cellSize) {
            return getPixelSize("layout_flmFlowInsetEnd", this.flowInsetEndCompound, cellSize, false);
        }

        int getFlowInsetBottom(float cellSize) {
            return getPixelSize("layout_flmFlowInsetBottom", this.flowInsetBottomCompound, cellSize, false);
        }

        private int getPixelSize(String name, int compound, float unitSize, boolean isSize) {
            if (!Compound.isCompoundFloat(compound)) {
                return compound;
            }
            if (isSize && compound < 0) {
                return compound;
            }
            if (!Float.isNaN(unitSize)) {
                return (int) (Float.intBitsToFloat(compound) * unitSize);
            }
            throw new IllegalArgumentException(name + " uses grid-based measurement, which is disabled");
        }

        int resolveWidthAndMakeMeasureSpec(float cellSize, int remainingLineWidth, int horizontalDecorationSize) {
            int measureMode;
            int pixelSize = getPixelSize("layout_flmWidth", this.widthCompound, cellSize, true);
            switch (pixelSize) {
                case -3:
                    pixelSize = remainingLineWidth;
                    measureMode = 1073741824;
                    this.width = remainingLineWidth;
                    break;
                case -2:
                    pixelSize = remainingLineWidth;
                    measureMode = Integer.MIN_VALUE;
                    this.width = -2;
                    break;
                case -1:
                    pixelSize = remainingLineWidth;
                    measureMode = 1073741824;
                    this.width = -1;
                    break;
                default:
                    if (Compound.isCompoundFloat(this.widthCompound)) {
                        pixelSize = Math.max(0, pixelSize - horizontalDecorationSize);
                    } else if (pixelSize < 0) {
                        throw new IllegalArgumentException("Unknown enum value for layout_flmWidth: " + pixelSize);
                    }
                    measureMode = 1073741824;
                    this.width = pixelSize;
                    break;
            }
            return MeasureSpec.makeMeasureSpec(pixelSize, measureMode);
        }

        int resolveHeightAndMakeMeasureSpec(float cellSize, int verticalDecorationSize, FlowLayoutManager manager) {
            int measureMode;
            int pixelSize = getPixelSize("layout_flmHeight", this.heightCompound, cellSize, true);
            switch (pixelSize) {
                case -4:
                    if (this.flow == 0 || this.flowHeightCompound < 0) {
                        pixelSize = 0;
                        measureMode = 0;
                    } else {
                        pixelSize = Math.max(0, (getFlowHeight(cellSize) + getFlowInsetTop(cellSize)) + getFlowInsetBottom(cellSize));
                        measureMode = 1073741824;
                    }
                    this.height = -1;
                    break;
                case -2:
                    pixelSize = 0;
                    measureMode = 0;
                    this.height = -2;
                    break;
                case -1:
                    pixelSize = ((((manager.getHeight() - manager.getPaddingTop()) - manager.getPaddingBottom()) - verticalDecorationSize) - getTopMargin(cellSize)) - getBottomMargin(cellSize);
                    measureMode = 1073741824;
                    this.height = -1;
                    break;
                default:
                    if (Compound.isCompoundFloat(this.heightCompound)) {
                        pixelSize = Math.max(0, pixelSize - verticalDecorationSize);
                    } else if (pixelSize < 0) {
                        throw new IllegalArgumentException("Unknown value for layout_flmHeight: " + pixelSize);
                    }
                    measureMode = 1073741824;
                    this.height = pixelSize;
                    break;
            }
            return MeasureSpec.makeMeasureSpec(pixelSize, measureMode);
        }

        String debugGetViewHolderDump() {
            if (sViewHolderField == null) {
                try {
                    Field field = android.support.v7.widget.RecyclerView.LayoutParams.class.getDeclaredField("mViewHolder");
                    field.setAccessible(true);
                    sViewHolderField = field;
                } catch (Exception e) {
                    return "failed: " + e.getMessage();
                }
            }
            try {
                return String.valueOf(sViewHolderField.get(this));
            } catch (Exception e2) {
                return "failed: " + e2.getMessage();
            }
        }
    }

    private static class NestedFlowLineInfo extends LineInfo {
        private static final Pool<NestedFlowLineInfo> sPool;
        public ItemInfo mCreator;
        public boolean mCreatorHeightWrapsChildFlow;
        public int mExtraHeight;
        public int mFlowHeight;
        public int mFlowInsetBottom;
        public int mFlowInsetTop;
        public int mFlowStartOffset;
        public int mFlowWidth;
        public ParagraphInfo mParagraph;

        static {
            sPool = new SimplePool(10);
        }

        public static NestedFlowLineInfo obtain(int positionStart, int lineWidth, int offsetStart, ItemInfo measuredCreator, LayoutParams creatorLayoutParams) {
            NestedFlowLineInfo line = (NestedFlowLineInfo) sPool.acquire();
            if (line == null) {
                line = new NestedFlowLineInfo();
            }
            line.mPositionStart = positionStart;
            line.mOffsetStart = offsetStart;
            line.init(measuredCreator, creatorLayoutParams, lineWidth);
            return line;
        }

        private NestedFlowLineInfo() {
            super();
            reset();
        }

        private void init(ItemInfo measuredCreator, LayoutParams layoutParams, int lineWidth) {
            boolean z = true;
            if (measuredCreator.mMeasuredInCurrentPass) {
                boolean flowUnder;
                boolean flowEnd;
                if ((layoutParams.flow & 4) != 0) {
                    flowUnder = true;
                } else {
                    flowUnder = false;
                }
                if ((layoutParams.flow & 2) != 0) {
                    flowEnd = true;
                } else {
                    flowEnd = false;
                }
                boolean flowStart;
                if ((layoutParams.flow & 1) != 0) {
                    flowStart = true;
                } else {
                    flowStart = false;
                }
                if (flowUnder || flowEnd || flowStart) {
                    int widthUsedByCreator;
                    this.mCreator = measuredCreator;
                    if (layoutParams.heightCompound != -4) {
                        z = false;
                    }
                    this.mCreatorHeightWrapsChildFlow = z;
                    if (flowUnder) {
                        widthUsedByCreator = 0;
                    } else {
                        widthUsedByCreator = (this.mCreator.mMarginStart + this.mCreator.mDecoratedWidth) + this.mCreator.mMarginEnd;
                    }
                    int flowInsetStart = layoutParams.getFlowInsetStart(this.mCreator.mGridCellSize);
                    int flowInsetEnd = layoutParams.getFlowInsetEnd(this.mCreator.mGridCellSize);
                    if ((flowEnd || flowUnder) && Compound.isCompoundFloat(layoutParams.flowInsetStartCompound) && this.mCreator.mGridInsetStart != 0 && this.mOffsetStart < this.mCreator.mGridInsetStart) {
                        flowInsetStart += this.mCreator.mGridInsetStart - this.mOffsetStart;
                    }
                    this.mFlowWidth = layoutParams.getFlowWidth(this.mCreator.mGridCellSize);
                    if (this.mFlowWidth < 0) {
                        this.mFlowWidth = Math.max(0, ((lineWidth - widthUsedByCreator) - flowInsetStart) - flowInsetEnd);
                    }
                    if (flowEnd) {
                        this.mCreator.mMarginStart = (lineWidth - this.mCreator.mMarginEnd) - this.mCreator.mDecoratedWidth;
                        this.mFlowStartOffset = ((lineWidth - widthUsedByCreator) - flowInsetEnd) - this.mFlowWidth;
                    } else {
                        this.mFlowStartOffset = widthUsedByCreator + flowInsetStart;
                    }
                    this.mFlowInsetTop = layoutParams.getFlowInsetTop(this.mCreator.mGridCellSize);
                    this.mFlowInsetBottom = layoutParams.getFlowInsetBottom(this.mCreator.mGridCellSize);
                    this.mFlowHeight = layoutParams.getFlowHeight(this.mCreator.mGridCellSize);
                    if (this.mFlowHeight < 0) {
                        this.mFlowHeight = Math.max(0, (this.mCreator.mDecoratedHeight - this.mFlowInsetTop) - this.mFlowInsetBottom);
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("Unknown flow value: 0x" + Integer.toHexString(layoutParams.flow));
            }
            throw new IllegalArgumentException("creator not measured");
        }

        public void clearMeasuredInCurrentPass() {
            if (this.mCreator != null) {
                this.mCreator.mMeasuredInCurrentPass = false;
            }
            if (this.mParagraph != null) {
                this.mParagraph.clearMeasuredInCurrentPass();
            }
        }

        public int validPositionEnd() {
            if (this.mCreator == null) {
                return this.mPositionStart;
            }
            return this.mParagraph == null ? this.mPositionStart + 1 : this.mParagraph.validPositionEnd();
        }

        protected int onArrange(boolean fullArrangementRequired, int totalItemCount) {
            if (this.mCreator == null) {
                return 0;
            }
            boolean isFirstLine;
            boolean isLastLine;
            int paragraphHeight;
            int flowContextHeightPlusInsetBottom;
            if (this.mPositionStart == 0) {
                isFirstLine = true;
            } else {
                isFirstLine = false;
            }
            if (this.mPositionStart + 1 == totalItemCount) {
                isLastLine = true;
            } else {
                isLastLine = false;
            }
            this.mCreator.mTopOffset = isFirstLine ? this.mCreator.mMarginTopForFirstLine : this.mCreator.mMarginTop;
            int creatorHeight = Math.max(0, (isLastLine ? this.mCreator.mMarginBottomForLastLine : this.mCreator.mMarginBottom) + (this.mCreator.mDecoratedHeight + this.mCreator.mTopOffset));
            if (this.mParagraph != null) {
                paragraphHeight = this.mParagraph.arrangeIfNecessary(totalItemCount);
            } else {
                paragraphHeight = 0;
            }
            if (this.mCreatorHeightWrapsChildFlow) {
                this.mExtraHeight = Math.max(0, ((this.mFlowInsetTop + paragraphHeight) + this.mFlowInsetBottom) - this.mCreator.mDecoratedHeight);
                flowContextHeightPlusInsetBottom = Math.max(this.mFlowHeight, paragraphHeight) + this.mFlowInsetBottom;
            } else {
                this.mExtraHeight = 0;
                flowContextHeightPlusInsetBottom = Math.max(this.mFlowHeight + this.mFlowInsetBottom, paragraphHeight);
            }
            return Math.max(creatorHeight, (this.mCreator.mTopOffset + this.mFlowInsetTop) + flowContextHeightPlusInsetBottom);
        }

        public int getItemTopOffset(int position) {
            if (position == this.mPositionStart) {
                return this.mCreator.mTopOffset;
            }
            if (this.mParagraph != null) {
                return (this.mCreator.mTopOffset + this.mFlowInsetTop) + this.mParagraph.getItemTopOffset(position);
            }
            return 0;
        }

        protected boolean invalidateFromInternal(int position) {
            if (this.mParagraph == null) {
                return false;
            }
            switch (this.mParagraph.invalidateFrom(position)) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    return false;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    this.mParagraph.recycle();
                    this.mParagraph = null;
                    return true;
                default:
                    return true;
            }
        }

        public void offsetPositions(int delta) {
            super.offsetPositions(delta);
            if (this.mParagraph != null) {
                this.mParagraph.offsetPositions(delta);
            }
        }

        public void recycle() {
            reset();
            sPool.release(this);
        }

        protected void reset() {
            super.reset();
            if (this.mCreator != null) {
                this.mCreator.recycle();
                this.mCreator = null;
            }
            this.mFlowWidth = 0;
            this.mFlowHeight = 0;
            this.mFlowStartOffset = 0;
            this.mFlowInsetTop = 0;
            this.mFlowInsetBottom = 0;
            this.mExtraHeight = 0;
            if (this.mParagraph != null) {
                this.mParagraph.recycle();
                this.mParagraph = null;
            }
        }

        void debugPrint(int lineIndex, StringBuilder output) {
            output.append('@').append(this.mPositionStart).append("(flow");
            if (this.mParagraph != null) {
                this.mParagraph.debugPrint(output);
            } else {
                output.append("{}");
            }
            output.append(')');
        }
    }

    private static final class ParagraphInfo extends MultiItemInfo {
        private static final Pool<ParagraphInfo> sPool;
        public final List<LineInfo> mLines;

        static {
            sPool = new SimplePool(15);
        }

        public static ParagraphInfo obtain(int positionStart) {
            ParagraphInfo paragraph = (ParagraphInfo) sPool.acquire();
            if (paragraph == null) {
                paragraph = new ParagraphInfo();
            }
            paragraph.mPositionStart = positionStart;
            return paragraph;
        }

        private ParagraphInfo() {
            super();
            this.mLines = new ArrayList();
            reset();
        }

        public final LineInfo getLastLine() {
            return this.mLines.isEmpty() ? null : (LineInfo) this.mLines.get(this.mLines.size() - 1);
        }

        public void addLine(LineInfo newLine) {
            this.mLines.add(newLine);
            invalidateHeight();
        }

        public void clearMeasuredInCurrentPass() {
            for (int i = this.mLines.size() - 1; i >= 0; i--) {
                ((LineInfo) this.mLines.get(i)).clearMeasuredInCurrentPass();
            }
        }

        public int validPositionEnd() {
            return this.mLines.isEmpty() ? this.mPositionStart : ((LineInfo) this.mLines.get(this.mLines.size() - 1)).validPositionEnd();
        }

        protected int onArrange(boolean fullArrangementRequired, int totalItemCount) {
            int totalHeight = 0;
            for (int i = 0; i < this.mLines.size(); i++) {
                totalHeight += ((LineInfo) this.mLines.get(i)).arrangeIfNecessary(totalItemCount);
            }
            return totalHeight;
        }

        public int getItemTopOffset(int position) {
            int topOffset = 0;
            boolean found = false;
            for (int i = this.mLines.size() - 1; i >= 0; i--) {
                LineInfo line = (LineInfo) this.mLines.get(i);
                if (found) {
                    topOffset += line.mTotalHeight;
                } else if (line.mPositionStart <= position) {
                    topOffset = line.getItemTopOffset(position);
                    found = true;
                }
            }
            return topOffset;
        }

        protected boolean invalidateFromInternal(int position) {
            int lineCount = this.mLines.size();
            if (lineCount == 0) {
                return false;
            }
            for (int i = lineCount - 1; i >= 0; i--) {
                LineInfo line = (LineInfo) this.mLines.get(i);
                switch (line.invalidateFrom(position)) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        if (i == lineCount - 1) {
                            return false;
                        }
                        return true;
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        return true;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        line.recycle();
                        this.mLines.remove(i);
                        break;
                    default:
                        break;
                }
            }
            throw new Error("Should not reach here");
        }

        public void offsetPositions(int delta) {
            super.offsetPositions(delta);
            for (int i = this.mLines.size() - 1; i >= 0; i--) {
                ((LineInfo) this.mLines.get(i)).offsetPositions(delta);
            }
        }

        public void recycle() {
            reset();
            sPool.release(this);
        }

        protected void reset() {
            super.reset();
            for (int i = this.mLines.size() - 1; i >= 0; i--) {
                ((LineInfo) this.mLines.get(i)).recycle();
            }
            this.mLines.clear();
        }

        void debugPrint(StringBuilder output) {
            output.append('{');
            if (!this.mLines.isEmpty()) {
                ((LineInfo) this.mLines.get(0)).debugPrint(0, output);
                for (int i = 1; i < this.mLines.size(); i++) {
                    output.append(',');
                    ((LineInfo) this.mLines.get(i)).debugPrint(i, output);
                }
            }
            output.append('}');
        }
    }

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        float mReferenceOffset;
        int mReferencePosition;

        SavedState(Parcel in) {
            this.mReferencePosition = in.readInt();
            this.mReferenceOffset = in.readFloat();
        }

        public SavedState(SavedState other) {
            this.mReferencePosition = other.mReferencePosition;
            this.mReferenceOffset = other.mReferenceOffset;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mReferencePosition);
            dest.writeFloat(this.mReferenceOffset);
        }

        static {
            CREATOR = new Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    return new SavedState(in);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
        }
    }

    static {
        HALF_VISIBLE_HEIGHT = Compound.floatLengthToCompound(0.5f);
        sDecorInsets = new Rect();
    }

    public boolean checkLayoutParams(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(Context context, AttributeSet attrs) {
        return new LayoutParams(context, attrs);
    }

    public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public void measureChildWithMargins(View child, int widthUsed, int heightUsed) {
        if (child.getLayoutParams() instanceof LayoutParams) {
            throw new UnsupportedOperationException("Views using FlowLayoutManager.LayoutParams should not be measured with measureChildWithMargins()");
        }
        super.measureChildWithMargins(child, widthUsed, heightUsed);
    }

    private int findSectionIndexByPosition(int position) {
        int sectionCount = this.mSections.size();
        if (sectionCount == 0 || ((ParagraphInfo) this.mSections.get(0)).mPositionStart > position) {
            return -1;
        }
        if (((ParagraphInfo) this.mSections.get(sectionCount - 1)).validPositionEnd() <= position) {
            return sectionCount ^ -1;
        }
        int startIndex = 0;
        int endIndex = sectionCount;
        while (startIndex < endIndex) {
            int midIndex = (startIndex + endIndex) / 2;
            ParagraphInfo midSection = (ParagraphInfo) this.mSections.get(midIndex);
            if (position < midSection.mPositionStart) {
                endIndex = midIndex;
            } else if (position < midSection.validPositionEnd()) {
                return midIndex;
            } else {
                startIndex = midIndex + 1;
            }
        }
        return startIndex ^ -1;
    }

    private void removeSectionAt(int sectionIndex) {
        ((ParagraphInfo) this.mSections.remove(sectionIndex)).recycle();
        if (sectionIndex == 0) {
            this.mTrueAnchorAtPositionZero = true;
        }
    }

    private void recycleAllSections() {
        for (int i = this.mSections.size() - 1; i >= 0; i--) {
            ((ParagraphInfo) this.mSections.get(i)).recycle();
        }
        this.mSections.clear();
    }

    public void onDetachedFromWindow(RecyclerView view, Recycler recycler) {
        recycleAllSections();
        super.onDetachedFromWindow(view, recycler);
    }

    public void onAdapterChanged(Adapter oldAdapter, Adapter newAdapter) {
        handleAutoRegisteredOnViewRenderedLister(newAdapter);
        recycleAllSections();
        super.onAdapterChanged(oldAdapter, newAdapter);
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        recycleAllSections();
        super.onItemsChanged(recyclerView);
    }

    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        invalidateAndOffsetPositions(positionStart, positionStart, itemCount);
        super.onItemsAdded(recyclerView, positionStart, itemCount);
    }

    public void onItemsRemoved(RecyclerView recyclerView, int positionStart, int itemCount) {
        invalidateAndOffsetPositions(positionStart, positionStart + itemCount, -itemCount);
        super.onItemsRemoved(recyclerView, positionStart, itemCount);
    }

    public void onItemsMoved(RecyclerView recyclerView, int from, int to, int itemCount) {
        invalidateAndOffsetPositions(Math.min(from, to), Math.max(from + itemCount, to + itemCount), 0);
        super.onItemsMoved(recyclerView, from, to, itemCount);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int positionStart, int itemCount) {
        if (this.mItemChangesAffectFlow) {
            invalidateAndOffsetPositions(positionStart, positionStart + itemCount, 0);
        }
        super.onItemsUpdated(recyclerView, positionStart, itemCount);
    }

    private void invalidateAndOffsetPositions(int positionStart, int positionEnd, int offset) {
        if (!this.mSections.isEmpty()) {
            int lastOffsetSectionIndex = 0;
            int i = this.mSections.size() - 1;
            while (i >= 0) {
                ParagraphInfo section = (ParagraphInfo) this.mSections.get(i);
                if (section.mPositionStart < positionEnd || (section.mPositionStart <= 0 && !this.mTrueAnchorAtPositionZero)) {
                    lastOffsetSectionIndex = i + 1;
                    break;
                } else {
                    section.offsetPositions(offset);
                    i--;
                }
            }
            i = lastOffsetSectionIndex - 1;
            while (i >= 0) {
                if (((ParagraphInfo) this.mSections.get(i)).invalidateFrom(positionStart) == 2) {
                    removeSectionAt(i);
                    i--;
                } else {
                    int lastRemovedSectionIndex = i + 1;
                    return;
                }
            }
        }
    }

    private void debugPrintSections() {
        int sectionCount = this.mSections.size();
        Log.d("FlowLayoutManager", "Layout in bookkeeping: " + sectionCount + " section(s)");
        if (sectionCount > 0) {
            StringBuilder output = new StringBuilder();
            ParagraphInfo section = (ParagraphInfo) this.mSections.get(0);
            output.append("  \u00a70@").append(section.mPositionStart);
            if (section.mPositionStart == 0) {
                output.append(this.mTrueAnchorAtPositionZero ? "(real)" : "(fake)");
            }
            output.append(':');
            section.debugPrint(output);
            Log.d("FlowLayoutManager", output.toString());
            for (int i = 1; i < sectionCount; i++) {
                output.setLength(0);
                section = (ParagraphInfo) this.mSections.get(i);
                output.append("  \u00a7").append(i).append('@').append(section.mPositionStart).append(':');
                section.debugPrint(output);
                Log.d("FlowLayoutManager", output.toString());
            }
        }
    }

    private int findChildIndexByPosition(int position) {
        int childCount = getChildCount();
        if (childCount == 0 || getPosition(getChildAt(0)) > position) {
            return -1;
        }
        if (getPosition(getChildAt(childCount - 1)) < position) {
            return childCount ^ -1;
        }
        int startIndex = 0;
        int endIndex = childCount;
        while (startIndex < endIndex) {
            int midIndex = (startIndex + endIndex) / 2;
            int midPosition = getPosition(getChildAt(midIndex));
            if (midPosition == position) {
                return midIndex;
            }
            if (midPosition < position) {
                startIndex = midIndex + 1;
            } else {
                endIndex = midIndex;
            }
        }
        return startIndex ^ -1;
    }

    public View findViewByPosition(int position) {
        int childIndex = findChildIndexByPosition(position);
        return childIndex < 0 ? null : getChildAt(childIndex);
    }

    private int getOrAddChildWithHint(Recycler recycler, int position, int positionHint, int indexHint) {
        int potentialIndex;
        int insertIndex;
        switch (position - positionHint) {
            case -1:
                potentialIndex = indexHint - 1;
                insertIndex = indexHint;
                break;
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                potentialIndex = indexHint;
                insertIndex = indexHint;
                break;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                potentialIndex = indexHint + 1;
                insertIndex = indexHint + 1;
                break;
            default:
                potentialIndex = -1;
                insertIndex = -1;
                break;
        }
        if (potentialIndex >= 0 && potentialIndex < getChildCount()) {
            int realChildPosition = getPosition(getChildAt(potentialIndex));
            if (realChildPosition == position) {
                return potentialIndex;
            }
            Object obj;
            Object obj2;
            if (potentialIndex == insertIndex) {
                obj = 1;
            } else {
                obj = null;
            }
            if (realChildPosition > position) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj != obj2) {
                throw new IllegalArgumentException("Wrong hint precondition.\n\t position=" + position + "\n\t positionHint=" + positionHint + "\n\t indexHint=" + indexHint + "\n\t potentialIndex=" + potentialIndex + "\n\t insertIndex=" + insertIndex + "\n\t realChildPosition(potentialIndex)=" + realChildPosition);
            }
        }
        boolean usedBinarySearch = false;
        if (insertIndex < 0) {
            potentialIndex = findChildIndexByPosition(position);
            usedBinarySearch = true;
            if (potentialIndex >= 0) {
                return potentialIndex;
            }
            insertIndex = potentialIndex ^ -1;
        }
        View child = recycler.getViewForPosition(position);
        try {
            int actualPosition = getPosition(child);
            if (actualPosition != position) {
                throw new IllegalStateException("Recycler.getViewForPosition(" + position + ") returned a view @" + actualPosition);
            }
            addView(child, insertIndex);
            int startIndex = Math.max(0, insertIndex - 1);
            child = getChildAt(startIndex);
            int previousPosition = getPosition(child);
            String previousViewHolderDump = ((LayoutParams) child.getLayoutParams()).debugGetViewHolderDump();
            int endIndex = Math.min(insertIndex + 1, getChildCount() - 1);
            for (int i = startIndex + 1; i <= endIndex; i++) {
                child = getChildAt(i);
                int currentPosition = getPosition(child);
                String currentViewHolderDump = ((LayoutParams) child.getLayoutParams()).debugGetViewHolderDump();
                if (currentPosition <= previousPosition) {
                    throw new IllegalStateException("Index/position monotonicity broken!\n\t position=" + position + "\n\t positionHint=" + positionHint + "\n\t indexHint=" + indexHint + "\n\t potentialIndex=" + potentialIndex + "\n\t insertIndex=" + insertIndex + "\n\t usedBinarySearch=" + usedBinarySearch + "\n\t p(childAt(" + (i - 1) + "))=" + previousPosition + "\n\t   viewHolderDump=" + previousViewHolderDump + "\n\t p(childAt(" + i + "))=" + currentPosition + "\n\t   viewHolderDump=" + currentViewHolderDump);
                }
                previousPosition = currentPosition;
                previousViewHolderDump = currentViewHolderDump;
            }
            return insertIndex;
        } catch (RuntimeException e) {
            LayoutParams childLayoutParams = (LayoutParams) child.getLayoutParams();
            Log.d("FlowLayoutManager", "getOrAddChildWithHint() states at exception:\n\t position=" + position + "\n\t positionHint=" + positionHint + "\n\t indexHint=" + indexHint + "\n\t potentialIndex=" + potentialIndex + "\n\t insertIndex=" + insertIndex + "\n\t usedBinarySearch=" + usedBinarySearch + "\n\t child's viewHolderDump=" + (childLayoutParams == null ? "failed: no LayoutParams" : childLayoutParams.debugGetViewHolderDump()));
            throw e;
        }
    }

    private void recycleViewsBeforePosition(Recycler recycler, int position) {
        int childIndex = findChildIndexByPosition(position);
        if (childIndex < 0) {
            childIndex ^= -1;
        }
        for (int i = childIndex - 1; i >= 0; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
    }

    private void recycleViewsAtOrAfter(Recycler recycler, int childIndex) {
        int i = getChildCount() - 1;
        while (i >= 0 && i >= childIndex) {
            removeAndRecycleViewAt(i, recycler);
            i--;
        }
    }

    private void debugPrintChildren() {
        int childCount = getChildCount();
        Log.d("FlowLayoutManager", "current child list: " + childCount + " child(ren)");
        if (childCount > 0) {
            RecyclerView recyclerView = (RecyclerView) getChildAt(0).getParent();
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < childCount; i++) {
                output.append("  #").append(i).append('@');
                View child = getChildAt(i);
                output.append(getPosition(child)).append(',').append(recyclerView.getChildViewHolder(child));
                Log.d("FlowLayoutManager", output.toString());
                output.setLength(0);
            }
        }
    }

    private void resetFillState() {
        if (this.mFillState == null) {
            this.mFillState = new FillState();
        }
        this.mFillState.reset();
    }

    private View getNextChildIntoFillState(Recycler recycler, int position, int fullContextWidth) {
        int childIndex = getOrAddChildWithHint(recycler, position, this.mFillState.mNextItemPosition, this.mFillState.mNextItemChildIndex);
        View child = getChildAt(childIndex);
        if (this.mFillState.mNextItemPosition != position) {
            this.mFillState.mNextItemPosition = position;
            this.mFillState.mNextItemChildIndex = childIndex;
            if (this.mFillState.mNextItem != null) {
                throw new IllegalStateException("Did not consume previous ItemInfo");
            }
            this.mFillState.mNextItemLayoutParams = (LayoutParams) child.getLayoutParams();
            this.mFillState.mNextItem = ItemInfo.obtain();
            this.mFillState.mNextItem.loadParams(this.mFillState.mNextItemLayoutParams, fullContextWidth);
        } else if (this.mFillState.mNextItemChildIndex != childIndex) {
            throw new IllegalStateException("Cached next child index incorrect");
        } else if (this.mFillState.mNextItem == null) {
            throw new IllegalStateException("Cached next child missing ItemInfo");
        }
        return child;
    }

    private int fillUpForPosition(Recycler recycler, int sectionIndex, int position, int totalItemCount) {
        ParagraphInfo section = sectionIndex == -1 ? null : (ParagraphInfo) this.mSections.get(sectionIndex);
        if (section != null && section.mPositionStart > position) {
            throw new IllegalArgumentException("Section at " + sectionIndex + " impossible to cover position " + position);
        } else if (sectionIndex + 1 >= this.mSections.size() || position < ((ParagraphInfo) this.mSections.get(sectionIndex + 1)).mPositionStart) {
            int validPositionEnd = section == null ? 0 : section.validPositionEnd();
            if (validPositionEnd > position) {
                section.arrangeIfNecessary(totalItemCount);
                return sectionIndex;
            }
            int positionHint = -1;
            int childIndex = -1;
            int childPosition = position;
            while (childPosition >= validPositionEnd) {
                try {
                    childIndex = getOrAddChildWithHint(recycler, childPosition, positionHint, childIndex);
                    boolean isAnchor = ((LayoutParams) getChildAt(childIndex).getLayoutParams()).isAnchorCandidate();
                    if (isAnchor || childPosition == 0) {
                        section = ParagraphInfo.obtain(childPosition);
                        sectionIndex++;
                        this.mSections.add(sectionIndex, section);
                        if (childPosition == 0) {
                            this.mTrueAnchorAtPositionZero = isAnchor;
                        }
                    } else {
                        positionHint = childPosition;
                        childPosition--;
                    }
                } catch (RuntimeException e) {
                    Log.d("FlowLayoutManager", "fillUpForPosition() state at exception: finding anchor\n\r sectionIndex=" + sectionIndex + "\n\r position=" + position + "\n\r totalItemCount=" + totalItemCount);
                    throw e;
                }
            }
            try {
                resetFillState();
                fillSection(recycler, section, position, 0, sectionIndex + 1 == this.mSections.size() ? totalItemCount : ((ParagraphInfo) this.mSections.get(sectionIndex + 1)).mPositionStart, totalItemCount);
                return sectionIndex;
            } catch (RuntimeException e2) {
                Log.d("FlowLayoutManager", "fillUpForPosition() state at exception: filling section\n\r sectionIndex=" + sectionIndex + "\n\r position=" + position + "\n\r totalItemCount=" + totalItemCount + "\n\r mFillState=" + this.mFillState);
                throw e2;
            }
        } else {
            throw new IllegalArgumentException("Section at " + sectionIndex + " impossible to cover position " + position);
        }
    }

    private int fillDownForHeight(Recycler recycler, int startSectionIndex, int height, int totalItemCount) {
        StringBuilder output;
        int lastSectionIndex = startSectionIndex;
        ParagraphInfo lastSection = (ParagraphInfo) this.mSections.get(lastSectionIndex);
        int remainingHeight = height;
        resetFillState();
        while (remainingHeight > 0) {
            ParagraphInfo nextSection;
            if (lastSectionIndex + 1 == this.mSections.size()) {
                nextSection = null;
            } else {
                try {
                    nextSection = (ParagraphInfo) this.mSections.get(lastSectionIndex + 1);
                } catch (RuntimeException e) {
                    output = new StringBuilder("fillDownForHeight() states at exception:\n\t startSectionIndex=").append(startSectionIndex).append("\n\t height=").append(height).append("\n\t totalItemCount=").append(totalItemCount).append("\n\t remainingHeight=").append(remainingHeight).append("\n\t lastSectionIndex=").append(lastSectionIndex).append("\n\t lastSection=");
                    if (lastSection == null) {
                        output.append("null");
                    } else {
                        lastSection.debugPrint(output);
                    }
                    output.append("\n\t mFillState=").append(this.mFillState);
                    Log.d("FlowLayoutManager", output.toString());
                    throw e;
                }
            }
            fillSection(recycler, lastSection, -1, remainingHeight, nextSection == null ? totalItemCount : nextSection.mPositionStart, totalItemCount);
            remainingHeight -= this.mFillState.mHeightFilled;
            int nextAnchorPosition = this.mFillState.mNextAnchorPosition;
            if (nextAnchorPosition != -1) {
                lastSection = ParagraphInfo.obtain(nextAnchorPosition);
                lastSectionIndex++;
                this.mSections.add(lastSectionIndex, lastSection);
            } else if (lastSection.validPositionEnd() == totalItemCount) {
                break;
            } else {
                lastSectionIndex++;
                lastSection = nextSection;
            }
        }
        return remainingHeight;
    }

    private void fillSection(Recycler recycler, ParagraphInfo section, int positionToCover, int minHeight, int nextSectionStart, int totalItemCount) {
        if (positionToCover == -1 && minHeight <= 0) {
            throw new IllegalArgumentException("Both criteria met before any processing");
        } else if (section.mPositionStart >= nextSectionStart) {
            throw new IllegalArgumentException("Section started after limit");
        } else if (positionToCover >= nextSectionStart || nextSectionStart > totalItemCount) {
            throw new IllegalArgumentException("positionToCover < nextSectionStart <= totalItemCount does not hold");
        } else {
            int fullContextWidth = (getWidth() - getPaddingLeft()) - getPaddingRight();
            this.mFillState.mNextAnchorPosition = -1;
            this.mFillState.mHeightFilled = section.arrangeIfNecessary(totalItemCount);
            int nextPosition = section.mPositionStart;
            LineInfo lastLine = section.getLastLine();
            if (lastLine != null) {
                FillState fillState = this.mFillState;
                fillState.mHeightFilled -= lastLine.mTotalHeight;
                nextPosition = lastLine.mPositionStart;
                if (r0 <= positionToCover || this.mFillState.mHeightFilled < minHeight) {
                    int lastValidPositionEnd = lastLine.validPositionEnd();
                    nextPosition = fillExistingLine(recycler, lastLine, nextSectionStart, totalItemCount, fullContextWidth, false, -1);
                    if (nextPosition != lastValidPositionEnd) {
                        section.invalidateHeight();
                    }
                    this.mFillState.mHeightFilled = section.arrangeIfNecessary(totalItemCount);
                } else {
                    return;
                }
            }
            while (true) {
                if ((nextPosition <= positionToCover || this.mFillState.mHeightFilled < minHeight) && this.mFillState.mNextAnchorPosition == -1 && nextPosition < nextSectionStart) {
                    nextPosition = addLineToParagraph(recycler, section, nextSectionStart, totalItemCount, fullContextWidth, fullContextWidth, 0, false, -1);
                    this.mFillState.mHeightFilled = section.arrangeIfNecessary(totalItemCount);
                }
            }
            if (this.mFillState.mNextAnchorPosition != -1 && nextPosition > positionToCover && this.mFillState.mHeightFilled >= minHeight) {
                this.mFillState.mNextAnchorPosition = -1;
            }
        }
    }

    private int fillExistingLine(Recycler recycler, LineInfo line, int nextSectionStart, int totalItemCount, int fullContextWidth, boolean inNestedFlow, int remainingFlowHeight) {
        if (line instanceof InlineFlowLineInfo) {
            return fillInlineFlowLine(recycler, (InlineFlowLineInfo) line, nextSectionStart, fullContextWidth, inNestedFlow, remainingFlowHeight);
        }
        return fillNestedFlowLine(recycler, (NestedFlowLineInfo) line, nextSectionStart, totalItemCount, fullContextWidth);
    }

    private int fillInlineFlowLine(Recycler recycler, InlineFlowLineInfo line, int nextSectionStart, int fullContextWidth, boolean inNestedFlow, int remainingFlowHeight) {
        if (line.mItems.isEmpty()) {
            throw new IllegalArgumentException("Line must not be empty");
        }
        int nextPosition = line.validPositionEnd();
        while (nextPosition < nextSectionStart && line.mLineWidth - line.mUsedWidth > 1) {
            if (!measureNextItem(recycler, nextPosition, fullContextWidth, line.mItems, line.mLineWidth, line.mUsedWidth, line.mOffsetStart, false, inNestedFlow, remainingFlowHeight)) {
                break;
            }
            line.addMeasuredItem(this.mFillState.takeNextItem());
            nextPosition++;
        }
        return nextPosition;
    }

    private int fillNestedFlowLine(Recycler recycler, NestedFlowLineInfo line, int nextSectionStart, int totalItemCount, int fullContextWidth) {
        if (line.mCreator == null) {
            throw new IllegalArgumentException("Line must not be empty");
        }
        int oldValidPositionEnd = line.validPositionEnd();
        int nextPosition = oldValidPositionEnd;
        ParagraphInfo nestedParagraph = line.mParagraph;
        if (nestedParagraph != null) {
            int paragraphHeight = nestedParagraph.arrangeIfNecessary(totalItemCount);
            LineInfo lastLine = nestedParagraph.getLastLine();
            if (lastLine == null) {
                throw new IllegalStateException("Empty nested paragraph found!");
            }
            Recycler recycler2 = recycler;
            int i = nextSectionStart;
            int i2 = totalItemCount;
            int i3 = fullContextWidth;
            nextPosition = fillExistingLine(recycler2, lastLine, i, i2, i3, true, line.mFlowHeight - (paragraphHeight - lastLine.mTotalHeight));
            if (nextPosition > oldValidPositionEnd) {
                nestedParagraph.invalidateHeight();
            }
        } else if (line.mFlowWidth == 0 || line.mFlowHeight == 0) {
            return nextPosition;
        } else {
            nestedParagraph = ParagraphInfo.obtain(nextPosition);
            nextPosition = addLineToParagraph(recycler, nestedParagraph, nextSectionStart, totalItemCount, fullContextWidth, line.mFlowWidth, line.mOffsetStart + line.mFlowStartOffset, true, line.mFlowHeight);
            if (nextPosition == nestedParagraph.mPositionStart) {
                nestedParagraph.recycle();
                return nextPosition;
            }
            line.mParagraph = nestedParagraph;
        }
        int oldNextPosition;
        do {
            oldNextPosition = nextPosition;
            Recycler recycler3 = recycler;
            ParagraphInfo paragraphInfo = nestedParagraph;
            int i4 = nextSectionStart;
            int i5 = totalItemCount;
            int i6 = fullContextWidth;
            nextPosition = addLineToParagraph(recycler3, paragraphInfo, i4, i5, i6, line.mFlowWidth, line.mOffsetStart + line.mFlowStartOffset, true, line.mFlowHeight - nestedParagraph.arrangeIfNecessary(totalItemCount));
        } while (nextPosition > oldNextPosition);
        if (nextPosition > oldValidPositionEnd) {
            line.invalidateHeight();
        }
        return nextPosition;
    }

    private int addLineToParagraph(Recycler recycler, ParagraphInfo paragraph, int nextSectionStart, int totalItemCount, int fullContextWidth, int lineWidth, int lineOffsetStart, boolean inNestedFlow, int remainingFlowHeight) {
        int nextPosition = paragraph.validPositionEnd();
        if (nextPosition < nextSectionStart) {
            if (!measureNextItem(recycler, nextPosition, fullContextWidth, null, lineWidth, 0, lineOffsetStart, paragraph.mPositionStart == nextPosition, inNestedFlow, remainingFlowHeight)) {
                return nextPosition;
            }
            if (this.mFillState.mNextItemLayoutParams.flow == 0) {
                InlineFlowLineInfo line = InlineFlowLineInfo.obtain(nextPosition, lineWidth, lineOffsetStart, this.mFillState.takeNextItem());
                nextPosition = fillInlineFlowLine(recycler, line, nextSectionStart, fullContextWidth, inNestedFlow, remainingFlowHeight);
                paragraph.addLine(line);
            } else {
                NestedFlowLineInfo line2 = NestedFlowLineInfo.obtain(nextPosition, lineWidth, lineOffsetStart, this.mFillState.takeNextItem(), this.mFillState.mNextItemLayoutParams);
                nextPosition = fillNestedFlowLine(recycler, line2, nextSectionStart, totalItemCount, fullContextWidth);
                paragraph.addLine(line2);
            }
            return nextPosition;
        } else if (nextPosition <= nextSectionStart) {
            return nextPosition;
        } else {
            throw new IllegalArgumentException("\u00b6@[" + paragraph.mPositionStart + "," + nextPosition + ") should not cover nextSectionStart@" + nextSectionStart);
        }
    }

    private boolean measureNextItem(Recycler recycler, int nextPosition, int fullContextWidth, List<ItemInfo> previousItems, int lineWidth, int usedLineWidth, int lineOffsetStart, boolean atParagraphStart, boolean inNestedFlow, int remainingFlowHeight) {
        View child = getNextChildIntoFillState(recycler, nextPosition, fullContextWidth);
        LayoutParams layoutParams = this.mFillState.mNextItemLayoutParams;
        boolean allowSoftWrap = true;
        boolean atLineStart = previousItems == null || previousItems.isEmpty();
        if (layoutParams.isAnchorCandidate()) {
            if (inNestedFlow || !atParagraphStart) {
                this.mFillState.mNextAnchorPosition = nextPosition;
                return false;
            }
            allowSoftWrap = false;
        }
        int lineWrapFlow = layoutParams.getLineWrapFlow();
        if (inNestedFlow) {
            switch (lineWrapFlow) {
                case R.styleable.WalletImFormEditText_required /*4*/:
                    if (atLineStart) {
                        allowSoftWrap = false;
                        break;
                    }
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    return false;
                default:
                    if (atLineStart && remainingFlowHeight <= 1) {
                        return false;
                    }
            }
        }
        ItemInfo item = this.mFillState.mNextItem;
        int lineWrapAction = layoutParams.getEffectiveLineWrapAction();
        if (!atLineStart) {
            switch (lineWrapAction) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return false;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    allowSoftWrap = false;
                    break;
                default:
                    if (!item.hasSameGridAs((ItemInfo) previousItems.get(previousItems.size() - 1))) {
                        return false;
                    }
                    break;
            }
        }
        int i = layoutParams.widthCompound;
        if (r0 == -1 && lineWidth < fullContextWidth) {
            return false;
        }
        if (allowSoftWrap && atLineStart && lineWidth >= fullContextWidth) {
            allowSoftWrap = false;
        }
        int remainingWidthAfterMargin = Math.max(0, ((lineWidth - usedLineWidth) - item.mMarginStart) - item.mMarginEnd);
        int extraMarginStart = 0;
        int extraMarginEnd = 0;
        if (Compound.isCompoundFloat(layoutParams.widthCompound) && !(item.mGridInsetStart == 0 && item.mGridInsetEnd == 0)) {
            if (item.mGridInsetStart > 0) {
                extraMarginStart = Math.max(0, ((item.mGridInsetStart - lineOffsetStart) - usedLineWidth) - item.mMarginStart);
            } else if (atLineStart && lineOffsetStart == 0 && item.mMarginStart == 0) {
                extraMarginStart = item.mGridInsetStart;
            }
            int lineOffsetEnd = (fullContextWidth - lineOffsetStart) - lineWidth;
            if (item.mGridInsetEnd > 0) {
                extraMarginEnd = Math.max(0, (item.mGridInsetEnd - lineOffsetEnd) - item.mMarginEnd);
            } else if (lineOffsetEnd == 0 && item.mMarginEnd == 0) {
                extraMarginEnd = item.mGridInsetEnd;
            }
            remainingWidthAfterMargin = Math.max(0, (remainingWidthAfterMargin - extraMarginStart) - extraMarginEnd);
        }
        calculateItemDecorationsForChild(child, sDecorInsets);
        int horizontalDecorationSize = sDecorInsets.left + sDecorInsets.right;
        int verticalDecorationSize = sDecorInsets.top + sDecorInsets.bottom;
        remainingWidthAfterMargin = Math.max(0, remainingWidthAfterMargin - horizontalDecorationSize);
        int widthMeasureSpec = layoutParams.resolveWidthAndMakeMeasureSpec(item.mGridCellSize, remainingWidthAfterMargin, horizontalDecorationSize);
        int heightMeasureSpec = layoutParams.resolveHeightAndMakeMeasureSpec(item.mGridCellSize, verticalDecorationSize, this);
        if (allowSoftWrap && (remainingWidthAfterMargin == 0 || MeasureSpec.getSize(widthMeasureSpec) > remainingWidthAfterMargin)) {
            return false;
        }
        child.measure(widthMeasureSpec, heightMeasureSpec);
        if (allowSoftWrap) {
            if (child.getMeasuredWidth() > remainingWidthAfterMargin) {
                return false;
            }
            if ((ViewCompat.getMeasuredWidthAndState(child) & 16777216) != 0) {
                return false;
            }
        }
        item.loadMeasurements(this, child, false);
        if ((layoutParams.flow & 2) == 2) {
            item.mMarginEnd += extraMarginEnd;
        } else {
            item.mMarginStart += extraMarginStart;
        }
        return true;
    }

    public FlowLayoutManager setOnViewRenderedListener(OnViewRenderedListener listener) {
        this.mViewRenderedListener = listener;
        this.mWasViewRenderedListenerAutoRegistered = false;
        return this;
    }

    private void handleAutoRegisteredOnViewRenderedLister(Adapter newAdapter) {
        if (this.mWasViewRenderedListenerAutoRegistered) {
            setOnViewRenderedListener(null);
        }
        if (newAdapter instanceof AutoRegisteredOnViewRenderedListener) {
            this.mViewRenderedListener = (AutoRegisteredOnViewRenderedListener) newAdapter;
            this.mWasViewRenderedListenerAutoRegistered = true;
        }
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        int referencePosition;
        int referenceOffset;
        if (this.mPendingSavedState != null) {
            this.mPendingScrollPosition = this.mPendingSavedState.mReferencePosition;
            this.mPendingScrollPositionOffset = (int) (((float) getHeight()) * this.mPendingSavedState.mReferenceOffset);
            this.mPendingSavedState = null;
        }
        if (this.mPendingScrollPosition != -1) {
            if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                this.mPendingScrollPositionOffset = getPaddingTop();
            }
        }
        if (this.mPendingScrollPosition != -1) {
            referencePosition = this.mPendingScrollPosition;
            referenceOffset = this.mPendingScrollPositionOffset;
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        } else {
            View referenceChild = getReferenceChild();
            if (referenceChild != null) {
                referencePosition = getPosition(referenceChild);
                referenceOffset = getDecoratedTop(referenceChild);
            } else {
                referencePosition = -1;
                referenceOffset = 0;
            }
        }
        layoutViewport(recycler, state, referencePosition, referenceOffset);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int layoutViewport(android.support.v7.widget.RecyclerView.Recycler r37, android.support.v7.widget.RecyclerView.State r38, int r39, int r40) {
        /*
        r36 = this;
        r11 = r38.getItemCount();	 Catch:{ RuntimeException -> 0x025a }
        if (r11 != 0) goto L_0x000e;
    L_0x0006:
        r36.removeAndRecycleAllViews(r37);	 Catch:{ RuntimeException -> 0x025a }
        r36.recycleAllSections();	 Catch:{ RuntimeException -> 0x025a }
        r3 = 0;
    L_0x000d:
        return r3;
    L_0x000e:
        if (r39 < 0) goto L_0x0066;
    L_0x0010:
        r0 = r39;
        if (r0 >= r11) goto L_0x0066;
    L_0x0014:
        r34 = 1;
    L_0x0016:
        r9 = r36.getPaddingTop();	 Catch:{ RuntimeException -> 0x025a }
        r3 = r36.getHeight();	 Catch:{ RuntimeException -> 0x025a }
        r4 = r36.getPaddingBottom();	 Catch:{ RuntimeException -> 0x025a }
        r10 = r3 - r4;
        if (r34 != 0) goto L_0x0069;
    L_0x0026:
        r5 = r9;
    L_0x0027:
        r0 = r36;
        r3 = r0.mExtraRenderAreaBottomCompound;	 Catch:{ RuntimeException -> 0x025a }
        r3 = com.google.android.play.utils.Compound.isCompoundInt(r3);	 Catch:{ RuntimeException -> 0x025a }
        if (r3 == 0) goto L_0x008d;
    L_0x0031:
        r0 = r36;
        r3 = r0.mExtraRenderAreaBottomCompound;	 Catch:{ RuntimeException -> 0x025a }
        r14 = r10 + r3;
    L_0x0037:
        r3 = r38.didStructureChange();	 Catch:{ RuntimeException -> 0x025a }
        if (r3 == 0) goto L_0x0040;
    L_0x003d:
        r36.detachAndScrapAttachedViews(r37);	 Catch:{ RuntimeException -> 0x025a }
    L_0x0040:
        r3 = r36.getChildCount();	 Catch:{ RuntimeException -> 0x025a }
        r19 = r3 + -1;
    L_0x0046:
        if (r19 < 0) goto L_0x00a0;
    L_0x0048:
        r0 = r36;
        r1 = r19;
        r12 = r0.getChildAt(r1);	 Catch:{ RuntimeException -> 0x025a }
        r22 = r12.getLayoutParams();	 Catch:{ RuntimeException -> 0x025a }
        r22 = (com.google.android.play.layout.FlowLayoutManager.LayoutParams) r22;	 Catch:{ RuntimeException -> 0x025a }
        r3 = r22.viewNeedsUpdate();	 Catch:{ RuntimeException -> 0x025a }
        if (r3 == 0) goto L_0x0063;
    L_0x005c:
        r0 = r36;
        r1 = r37;
        r0.detachAndScrapView(r12, r1);	 Catch:{ RuntimeException -> 0x025a }
    L_0x0063:
        r19 = r19 + -1;
        goto L_0x0046;
    L_0x0066:
        r34 = 0;
        goto L_0x0016;
    L_0x0069:
        r0 = r36;
        r3 = r0.mExtraRenderAreaTopCompound;	 Catch:{ RuntimeException -> 0x025a }
        r3 = com.google.android.play.utils.Compound.isCompoundInt(r3);	 Catch:{ RuntimeException -> 0x025a }
        if (r3 == 0) goto L_0x007a;
    L_0x0073:
        r0 = r36;
        r3 = r0.mExtraRenderAreaTopCompound;	 Catch:{ RuntimeException -> 0x025a }
        r5 = r9 - r3;
        goto L_0x0027;
    L_0x007a:
        r3 = r10 - r9;
        r3 = (float) r3;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r36;
        r4 = r0.mExtraRenderAreaTopCompound;	 Catch:{ RuntimeException -> 0x025a }
        r4 = java.lang.Float.intBitsToFloat(r4);	 Catch:{ RuntimeException -> 0x025a }
        r3 = r3 * r4;
        r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r3 = r3 + r4;
        r3 = (int) r3;	 Catch:{ RuntimeException -> 0x025a }
        r5 = r9 - r3;
        goto L_0x0027;
    L_0x008d:
        r3 = r10 - r9;
        r3 = (float) r3;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r36;
        r4 = r0.mExtraRenderAreaBottomCompound;	 Catch:{ RuntimeException -> 0x025a }
        r4 = java.lang.Float.intBitsToFloat(r4);	 Catch:{ RuntimeException -> 0x025a }
        r3 = r3 * r4;
        r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r3 = r3 + r4;
        r3 = (int) r3;	 Catch:{ RuntimeException -> 0x025a }
        r14 = r10 + r3;
        goto L_0x0037;
    L_0x00a0:
        r3 = r36.getWidth();	 Catch:{ RuntimeException -> 0x025a }
        r35 = java.lang.Integer.valueOf(r3);	 Catch:{ RuntimeException -> 0x025a }
        r3 = r36.getPaddingStart();	 Catch:{ RuntimeException -> 0x025a }
        r25 = java.lang.Integer.valueOf(r3);	 Catch:{ RuntimeException -> 0x025a }
        r3 = r36.getPaddingEnd();	 Catch:{ RuntimeException -> 0x025a }
        r24 = java.lang.Integer.valueOf(r3);	 Catch:{ RuntimeException -> 0x025a }
        r3 = com.google.android.play.R.id.flm_width;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r38;
        r3 = r0.get(r3);	 Catch:{ RuntimeException -> 0x025a }
        r0 = r35;
        r3 = r0.equals(r3);	 Catch:{ RuntimeException -> 0x025a }
        if (r3 == 0) goto L_0x00e8;
    L_0x00c8:
        r3 = com.google.android.play.R.id.flm_paddingStart;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r38;
        r3 = r0.get(r3);	 Catch:{ RuntimeException -> 0x025a }
        r0 = r25;
        r3 = r0.equals(r3);	 Catch:{ RuntimeException -> 0x025a }
        if (r3 == 0) goto L_0x00e8;
    L_0x00d8:
        r3 = com.google.android.play.R.id.flm_paddingEnd;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r38;
        r3 = r0.get(r3);	 Catch:{ RuntimeException -> 0x025a }
        r0 = r24;
        r3 = r0.equals(r3);	 Catch:{ RuntimeException -> 0x025a }
        if (r3 != 0) goto L_0x0106;
    L_0x00e8:
        r36.recycleAllSections();	 Catch:{ RuntimeException -> 0x025a }
        r3 = com.google.android.play.R.id.flm_width;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r38;
        r1 = r35;
        r0.put(r3, r1);	 Catch:{ RuntimeException -> 0x025a }
        r3 = com.google.android.play.R.id.flm_paddingStart;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r38;
        r1 = r25;
        r0.put(r3, r1);	 Catch:{ RuntimeException -> 0x025a }
        r3 = com.google.android.play.R.id.flm_paddingEnd;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r38;
        r1 = r24;
        r0.put(r3, r1);	 Catch:{ RuntimeException -> 0x025a }
    L_0x0106:
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r3 = r3.size();	 Catch:{ RuntimeException -> 0x025a }
        r19 = r3 + -1;
    L_0x0110:
        if (r19 < 0) goto L_0x0124;
    L_0x0112:
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r19;
        r3 = r3.get(r0);	 Catch:{ RuntimeException -> 0x025a }
        r3 = (com.google.android.play.layout.FlowLayoutManager.ParagraphInfo) r3;	 Catch:{ RuntimeException -> 0x025a }
        r3.clearMeasuredInCurrentPass();	 Catch:{ RuntimeException -> 0x025a }
        r19 = r19 + -1;
        goto L_0x0110;
    L_0x0124:
        if (r34 == 0) goto L_0x01a5;
    L_0x0126:
        r15 = r39;
    L_0x0128:
        r0 = r36;
        r28 = r0.findSectionIndexByPosition(r15);	 Catch:{ RuntimeException -> 0x025a }
        if (r28 >= 0) goto L_0x0134;
    L_0x0130:
        r3 = r28 ^ -1;
        r28 = r3 + -1;
    L_0x0134:
        r0 = r36;
        r1 = r37;
        r2 = r28;
        r30 = r0.fillUpForPosition(r1, r2, r15, r11);	 Catch:{ RuntimeException -> 0x025a }
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r30;
        r29 = r3.get(r0);	 Catch:{ RuntimeException -> 0x025a }
        r29 = (com.google.android.play.layout.FlowLayoutManager.ParagraphInfo) r29;	 Catch:{ RuntimeException -> 0x025a }
        if (r34 == 0) goto L_0x01a7;
    L_0x014c:
        r0 = r29;
        r3 = r0.getItemTopOffset(r15);	 Catch:{ RuntimeException -> 0x025a }
        r31 = r40 - r3;
    L_0x0154:
        r3 = r14 - r31;
        r0 = r36;
        r1 = r37;
        r2 = r30;
        r32 = r0.fillDownForHeight(r1, r2, r3, r11);	 Catch:{ RuntimeException -> 0x025a }
        r20 = r14 - r32;
        r26 = 0;
        if (r34 == 0) goto L_0x0171;
    L_0x0166:
        r3 = 0;
        r4 = r10 - r20;
        r26 = java.lang.Math.max(r3, r4);	 Catch:{ RuntimeException -> 0x025a }
        r31 = r31 + r26;
        r20 = r20 + r26;
    L_0x0171:
        r7 = r30;
        r8 = r31;
        r17 = r29;
    L_0x0177:
        if (r8 <= r5) goto L_0x01ad;
    L_0x0179:
        r0 = r17;
        r3 = r0.mPositionStart;	 Catch:{ RuntimeException -> 0x025a }
        if (r3 <= 0) goto L_0x01ad;
    L_0x017f:
        r3 = r7 + -1;
        r0 = r17;
        r4 = r0.mPositionStart;	 Catch:{ RuntimeException -> 0x025a }
        r4 = r4 + -1;
        r0 = r36;
        r1 = r37;
        r27 = r0.fillUpForPosition(r1, r3, r4, r11);	 Catch:{ RuntimeException -> 0x025a }
        r0 = r27;
        if (r0 != r7) goto L_0x01aa;
    L_0x0193:
        r30 = r30 + 1;
    L_0x0195:
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r17 = r3.get(r7);	 Catch:{ RuntimeException -> 0x025a }
        r17 = (com.google.android.play.layout.FlowLayoutManager.ParagraphInfo) r17;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r17;
        r3 = r0.mTotalHeight;	 Catch:{ RuntimeException -> 0x025a }
        r8 = r8 - r3;
        goto L_0x0177;
    L_0x01a5:
        r15 = 0;
        goto L_0x0128;
    L_0x01a7:
        r31 = r9;
        goto L_0x0154;
    L_0x01aa:
        r7 = r27;
        goto L_0x0195;
    L_0x01ad:
        r23 = 0;
        if (r34 == 0) goto L_0x01d4;
    L_0x01b1:
        r3 = 0;
        r4 = r8 - r9;
        r23 = java.lang.Math.max(r3, r4);	 Catch:{ RuntimeException -> 0x025a }
        r8 = r8 - r23;
        r31 = r31 - r23;
        r20 = r20 - r23;
        if (r23 <= 0) goto L_0x01d4;
    L_0x01c0:
        if (r26 != 0) goto L_0x01d4;
    L_0x01c2:
        r0 = r20;
        if (r0 >= r14) goto L_0x01d4;
    L_0x01c6:
        r3 = r14 - r31;
        r0 = r36;
        r1 = r37;
        r2 = r30;
        r32 = r0.fillDownForHeight(r1, r2, r3, r11);	 Catch:{ RuntimeException -> 0x025a }
        r20 = r14 - r32;
    L_0x01d4:
        r0 = r20;
        r6 = java.lang.Math.min(r0, r14);	 Catch:{ RuntimeException -> 0x025a }
        r3 = r36;
        r4 = r37;
        r18 = r3.renderAndRecycleViews(r4, r5, r6, r7, r8, r9, r10, r11);	 Catch:{ RuntimeException -> 0x025a }
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r3 = r3.size();	 Catch:{ RuntimeException -> 0x025a }
        r0 = r18;
        if (r0 >= r3) goto L_0x022c;
    L_0x01ee:
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r18;
        r3 = r3.get(r0);	 Catch:{ RuntimeException -> 0x025a }
        r3 = (com.google.android.play.layout.FlowLayoutManager.ParagraphInfo) r3;	 Catch:{ RuntimeException -> 0x025a }
        r3 = r3.mPositionStart;	 Catch:{ RuntimeException -> 0x025a }
        r16 = r3 + 5;
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r3 = r3.size();	 Catch:{ RuntimeException -> 0x025a }
        r19 = r3 + -1;
    L_0x0208:
        r3 = r18 + 2;
        r0 = r19;
        if (r0 < r3) goto L_0x022c;
    L_0x020e:
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r19;
        r33 = r3.get(r0);	 Catch:{ RuntimeException -> 0x025a }
        r33 = (com.google.android.play.layout.FlowLayoutManager.ParagraphInfo) r33;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r33;
        r3 = r0.mPositionStart;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r16;
        if (r3 < r0) goto L_0x022c;
    L_0x0222:
        r0 = r36;
        r1 = r19;
        r0.removeSectionAt(r1);	 Catch:{ RuntimeException -> 0x025a }
        r19 = r19 + -1;
        goto L_0x0208;
    L_0x022c:
        r0 = r17;
        r3 = r0.mPositionStart;	 Catch:{ RuntimeException -> 0x025a }
        r21 = r3 + -5;
        r3 = r7 + -2;
        r19 = r3 + -1;
    L_0x0236:
        if (r19 < 0) goto L_0x0256;
    L_0x0238:
        r0 = r36;
        r3 = r0.mSections;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r19;
        r33 = r3.get(r0);	 Catch:{ RuntimeException -> 0x025a }
        r33 = (com.google.android.play.layout.FlowLayoutManager.ParagraphInfo) r33;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r33;
        r3 = r0.mPositionStart;	 Catch:{ RuntimeException -> 0x025a }
        r0 = r21;
        if (r3 >= r0) goto L_0x0253;
    L_0x024c:
        r0 = r36;
        r1 = r19;
        r0.removeSectionAt(r1);	 Catch:{ RuntimeException -> 0x025a }
    L_0x0253:
        r19 = r19 + -1;
        goto L_0x0236;
    L_0x0256:
        r3 = r26 - r23;
        goto L_0x000d;
    L_0x025a:
        r13 = move-exception;
        r3 = "FlowLayoutManager";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = "layoutViewport() state at exception:\n\t referencePosition=";
        r4 = r4.append(r6);
        r0 = r39;
        r4 = r4.append(r0);
        r6 = "\n\t referenceOffset=";
        r4 = r4.append(r6);
        r0 = r40;
        r4 = r4.append(r0);
        r6 = "\n\t state.getItemCount()=";
        r4 = r4.append(r6);
        r6 = r38.getItemCount();
        r4 = r4.append(r6);
        r6 = "\n\t didStructureChange=";
        r4 = r4.append(r6);
        r6 = r38.didStructureChange();
        r4 = r4.append(r6);
        r4 = r4.toString();
        android.util.Log.d(r3, r4);
        r36.debugPrintSections();
        r36.debugPrintChildren();
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.layout.FlowLayoutManager.layoutViewport(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, int, int):int");
    }

    private int renderAndRecycleViews(Recycler recycler, int renderTop, int renderBottom, int firstSectionIndex, int firstSectionTop, int viewportTop, int viewportBottom, int itemCount) {
        int lineTop = firstSectionTop;
        int nextChildIndex = -1;
        int sectionIndex = firstSectionIndex;
        boolean isRtl = getLayoutDirection() == 1;
        startComputingScroll(itemCount);
        while (lineTop < renderBottom && sectionIndex < this.mSections.size()) {
            ParagraphInfo section = (ParagraphInfo) this.mSections.get(sectionIndex);
            int lineCount = section.mLines.size();
            int lineIndex = 0;
            while (lineTop < renderBottom && lineIndex < lineCount) {
                LineInfo line = (LineInfo) section.mLines.get(lineIndex);
                int newLineTop = lineTop + line.mTotalHeight;
                if (nextChildIndex == -1 && newLineTop > renderTop) {
                    recycleViewsBeforePosition(recycler, line.mPositionStart);
                    nextChildIndex = 0;
                }
                if (nextChildIndex != -1) {
                    nextChildIndex = renderLineAt(lineTop, line, nextChildIndex, recycler, isRtl);
                    addToComputedScroll(viewportTop, viewportBottom, lineTop, line);
                }
                lineTop = newLineTop;
                lineIndex++;
            }
            sectionIndex++;
        }
        recycleViewsAtOrAfter(recycler, nextChildIndex);
        return sectionIndex;
    }

    private int renderLineAt(int lineTop, LineInfo line, int nextChildIndex, Recycler recycler, boolean isRtl) {
        if (line instanceof InlineFlowLineInfo) {
            return renderInlineFlowLineAt(lineTop, (InlineFlowLineInfo) line, nextChildIndex, recycler, isRtl);
        }
        return renderNestedFlowLineAt(lineTop, (NestedFlowLineInfo) line, nextChildIndex, recycler, isRtl);
    }

    private int renderInlineFlowLineAt(int lineTop, InlineFlowLineInfo line, int nextChildIndex, Recycler recycler, boolean isRtl) {
        int itemStart = getPaddingStart() + line.mOffsetStart;
        for (int i = 0; i < line.mItems.size(); i++) {
            ItemInfo item = (ItemInfo) line.mItems.get(i);
            nextChildIndex = renderItemAt(lineTop, itemStart, item, line.mPositionStart + i, nextChildIndex, recycler, isRtl, null) + 1;
            itemStart += (item.mMarginStart + item.mDecoratedWidth) + item.mMarginEnd;
        }
        return nextChildIndex;
    }

    private int renderNestedFlowLineAt(int lineTop, NestedFlowLineInfo line, int nextChildIndex, Recycler recycler, boolean isRtl) {
        nextChildIndex = renderItemAt(lineTop, getPaddingStart() + line.mOffsetStart, line.mCreator, line.mPositionStart, nextChildIndex, recycler, isRtl, line) + 1;
        int nestedLineCount = line.mParagraph == null ? 0 : line.mParagraph.mLines.size();
        int nestedLineTop = (line.mCreator.mTopOffset + lineTop) + line.mFlowInsetTop;
        for (int i = 0; i < nestedLineCount; i++) {
            LineInfo nestedLine = (LineInfo) line.mParagraph.mLines.get(i);
            nextChildIndex = renderLineAt(nestedLineTop, nestedLine, nextChildIndex, recycler, isRtl);
            nestedLineTop += nestedLine.mTotalHeight;
        }
        return nextChildIndex;
    }

    private int renderItemAt(int itemTop, int itemStart, ItemInfo item, int position, int childIndex, Recycler recycler, boolean isRtl, NestedFlowLineInfo itemLine) {
        int childLeft;
        int childRight;
        childIndex = getOrAddChildWithHint(recycler, position, position, childIndex);
        View child = getChildAt(childIndex);
        int itemDecoratedHeight = item.mDecoratedHeight;
        if (itemLine != null && itemLine.mCreatorHeightWrapsChildFlow && itemLine.mExtraHeight > 0) {
            measureDecorated(child, item.mDecoratedWidth, item.mDecoratedHeight + itemLine.mExtraHeight);
            itemDecoratedHeight = getDecoratedMeasuredHeight(child);
        } else if (!item.mMeasuredInCurrentPass) {
            measureDecorated(child, item.mDecoratedWidth, item.mDecoratedHeight);
            item.loadMeasurements(this, child, true);
            itemDecoratedHeight = item.mDecoratedHeight;
        }
        int childTop = itemTop + item.mTopOffset;
        int childBottom = childTop + itemDecoratedHeight;
        int childStart = itemStart + item.mMarginStart;
        int childEnd = childStart + item.mDecoratedWidth;
        if (isRtl) {
            childLeft = getWidth() - childEnd;
        } else {
            childLeft = childStart;
        }
        if (isRtl) {
            childRight = getWidth() - childStart;
        } else {
            childRight = childEnd;
        }
        layoutDecorated(child, childLeft, childTop, childRight, childBottom);
        if (this.mViewRenderedListener != null) {
            this.mViewRenderedListener.onViewRendered(((RecyclerView) child.getParent()).getChildViewHolder(child));
        }
        return childIndex;
    }

    private void measureDecorated(View child, int exactDecoratedWidth, int exactDecoratedHeight) {
        calculateItemDecorationsForChild(child, sDecorInsets);
        child.measure(MeasureSpec.makeMeasureSpec((exactDecoratedWidth - sDecorInsets.left) - sDecorInsets.right, 1073741824), MeasureSpec.makeMeasureSpec((exactDecoratedHeight - sDecorInsets.top) - sDecorInsets.bottom, 1073741824));
    }

    private void startComputingScroll(int itemCount) {
        this.mComputedScrollRange = itemCount << 8;
        this.mComputedScrollOffset = 0;
        this.mComputedScrollExtent = 0;
    }

    private void addToComputedScroll(int viewportTop, int viewportBottom, int lineTop, LineInfo line) {
        int lineBottom = lineTop + line.mTotalHeight;
        if (lineBottom > viewportTop && lineTop < viewportBottom) {
            int lineWeight = (line.validPositionEnd() - line.mPositionStart) << 8;
            if (lineTop <= viewportTop) {
                this.mComputedScrollOffset = (line.mPositionStart << 8) + (((viewportTop - lineTop) * lineWeight) / line.mTotalHeight);
            }
            this.mComputedScrollExtent += (lineWeight * (Math.min(lineBottom, viewportBottom) - Math.max(lineTop, viewportTop))) / line.mTotalHeight;
        }
    }

    public boolean canScrollVertically() {
        return true;
    }

    public int computeVerticalScrollRange(State state) {
        return this.mComputedScrollRange;
    }

    public int computeVerticalScrollOffset(State state) {
        return this.mComputedScrollOffset;
    }

    public int computeVerticalScrollExtent(State state) {
        return this.mComputedScrollExtent;
    }

    public void scrollToPosition(int position) {
        this.mPendingScrollPosition = position;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    public int scrollVerticallyBy(int dy, Recycler recycler, State state) {
        View referenceChild = getReferenceChild();
        if (referenceChild == null) {
            return 0;
        }
        return dy - layoutViewport(recycler, state, getPosition(referenceChild), getDecoratedTop(referenceChild) - dy);
    }

    private View getReferenceChild() {
        int height = getHeight();
        int childCount = getChildCount();
        View alternative = null;
        int distanceOfAlternative = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (!((LayoutParams) child.getLayoutParams()).isItemRemoved()) {
                int center = (getDecoratedTop(child) + getDecoratedBottom(child)) / 2;
                if (center >= 0 && center <= height) {
                    return child;
                }
                int distance = center < 0 ? -center : center - height;
                if (distance < distanceOfAlternative) {
                    alternative = child;
                    distanceOfAlternative = distance;
                }
            }
        }
        return alternative;
    }

    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        Parcelable state = new SavedState();
        View referenceChild = getReferenceChild();
        if (referenceChild == null) {
            state.mReferencePosition = -1;
            state.mReferenceOffset = 0.0f;
            return state;
        }
        state.mReferencePosition = getPosition(referenceChild);
        state.mReferenceOffset = ((float) getDecoratedTop(referenceChild)) / ((float) getHeight());
        return state;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            this.mPendingSavedState = (SavedState) state;
            requestLayout();
        }
    }
}
