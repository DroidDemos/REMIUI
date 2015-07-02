package com.google.android.finsky.widget.consumption;

import android.content.res.Resources;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NowPlayingCellSorter {
    Map<String, Integer> mSequenceMapping;

    private class CellInformation implements Comparable<CellInformation> {
        int blockIndexInRow;
        long cellAreaInPixels;
        int cellIndexInBlock;
        int rowIndexInWidget;

        public CellInformation(int rowIndexInWidget, int blockIndexInRow, int cellIndexInBlock) {
            this.rowIndexInWidget = rowIndexInWidget;
            this.blockIndexInRow = blockIndexInRow;
            this.cellIndexInBlock = cellIndexInBlock;
        }

        public int compareTo(CellInformation another) {
            if (this.cellAreaInPixels != another.cellAreaInPixels) {
                if (this.cellAreaInPixels < another.cellAreaInPixels) {
                    return 1;
                }
                return -1;
            } else if (this.rowIndexInWidget != another.rowIndexInWidget) {
                if (this.rowIndexInWidget <= another.rowIndexInWidget) {
                    return -1;
                }
                return 1;
            } else if (this.blockIndexInRow != another.blockIndexInRow) {
                if (this.blockIndexInRow <= another.blockIndexInRow) {
                    return -1;
                }
                return 1;
            } else if (this.cellIndexInBlock == another.cellIndexInBlock) {
                return 0;
            } else {
                if (this.cellIndexInBlock <= another.cellIndexInBlock) {
                    return -1;
                }
                return 1;
            }
        }
    }

    public NowPlayingCellSorter() {
        this.mSequenceMapping = Maps.newHashMap();
    }

    private String getCellDescriptor(int rowIndex, int blockIndex, int cellIndex) {
        return rowIndex + ":" + blockIndex + ":" + cellIndex;
    }

    public void sort(List<List<Block>> rows, Resources res) {
        List<CellInformation> allCells = Lists.newArrayList();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            List<Block> row = (List) rows.get(rowIndex);
            for (int blockIndex = 0; blockIndex < row.size(); blockIndex++) {
                Block block = (Block) row.get(blockIndex);
                int cellCount = block.getNumImages();
                for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
                    CellInformation cellInfo = new CellInformation(rowIndex, blockIndex, cellIndex);
                    cellInfo.cellAreaInPixels = (long) (res.getDimensionPixelSize(block.getImageWidthRes(cellIndex)) * res.getDimensionPixelSize(block.getImageHeightRes(cellIndex)));
                    allCells.add(cellInfo);
                }
            }
        }
        Collections.sort(allCells);
        for (int sortedIndex = 0; sortedIndex < allCells.size(); sortedIndex++) {
            CellInformation currentCellInfo = (CellInformation) allCells.get(sortedIndex);
            this.mSequenceMapping.put(getCellDescriptor(currentCellInfo.rowIndexInWidget, currentCellInfo.blockIndexInRow, currentCellInfo.cellIndexInBlock), Integer.valueOf(sortedIndex));
        }
    }

    public int getSortedIndex(int rowIndexInWidget, int blockIndexInRow, int cellIndexInBlock) {
        String key = getCellDescriptor(rowIndexInWidget, blockIndexInRow, cellIndexInBlock);
        if (this.mSequenceMapping.containsKey(key)) {
            return ((Integer) this.mSequenceMapping.get(key)).intValue();
        }
        return -1;
    }
}
