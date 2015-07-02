package com.google.android.finsky.widget.consumption;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.finsky.widget.WidgetUtils;

public class Block {
    private int mChildHeightRes;
    private SparseArray<int[]> mChildResArray;
    private int mChildWidthRes;
    private int mHeightRes;
    private Block mLastInRowReplacement;
    private final int mLayoutId;
    private int mMaxRowStartCount;
    private int mNumImages;
    private boolean mSupportsMetadata;
    private int mWidthRes;

    public Block(int layoutId) {
        this.mLayoutId = layoutId;
        this.mNumImages = 1;
        this.mMaxRowStartCount = -1;
    }

    public Block sized(int widthRes, int heightRes) {
        this.mWidthRes = widthRes;
        this.mHeightRes = heightRes;
        this.mChildWidthRes = this.mWidthRes;
        this.mChildHeightRes = this.mHeightRes;
        return this;
    }

    public Block hosting(int imageCount) {
        this.mNumImages = imageCount;
        return this;
    }

    public Block hosting(int imageCount, int childWidthRes, int childHeightRes) {
        this.mNumImages = imageCount;
        this.mChildWidthRes = childWidthRes;
        this.mChildHeightRes = childHeightRes;
        return this;
    }

    public Block withChild(int childIndex, int childWidthRes, int childHeightRes) {
        if (this.mChildResArray == null) {
            this.mChildResArray = new SparseArray(this.mNumImages);
        }
        this.mChildResArray.put(childIndex, new int[]{childWidthRes, childHeightRes});
        return this;
    }

    public Block markToSupportMetadata() {
        this.mSupportsMetadata = true;
        return this;
    }

    public Block limitRowStartTo(int maxRowStart) {
        this.mMaxRowStartCount = maxRowStart;
        return this;
    }

    public Block replaceLastOccurenceInRowWith(Block replacement) {
        this.mLastInRowReplacement = replacement;
        return this;
    }

    public boolean hasMaxRowStartCount() {
        return this.mMaxRowStartCount >= 0;
    }

    public int getMaxRowStartCount() {
        return this.mMaxRowStartCount;
    }

    public int getLayoutId() {
        return this.mLayoutId;
    }

    public int getNumImages() {
        return this.mNumImages;
    }

    public int getImageWidthRes(int imageNumber) {
        if (this.mChildResArray != null) {
            return ((int[]) this.mChildResArray.get(imageNumber))[0];
        }
        return this.mChildWidthRes;
    }

    public int getImageHeightRes(int imageNumber) {
        if (this.mChildResArray != null) {
            return ((int[]) this.mChildResArray.get(imageNumber))[1];
        }
        return this.mChildHeightRes;
    }

    public boolean supportsMetadata() {
        return this.mSupportsMetadata;
    }

    public boolean hasLastOccurenceInRowReplacement() {
        return this.mLastInRowReplacement != null;
    }

    public Block getLastOccurenceInRowReplacement() {
        return this.mLastInRowReplacement;
    }

    public int getWidth(Context context) {
        return WidgetUtils.getDips(context, this.mWidthRes);
    }

    public int getHeight(Context context) {
        return WidgetUtils.getDips(context, this.mHeightRes);
    }

    public int hashCode() {
        return (getLayoutId() * 31) + getMaxRowStartCount();
    }
}
