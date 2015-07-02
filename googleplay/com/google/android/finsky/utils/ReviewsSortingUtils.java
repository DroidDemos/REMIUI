package com.google.android.finsky.utils;

import android.content.Context;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeReviews;

public class ReviewsSortingUtils {
    private static SortingInfo[] sSortingInfoArray;

    private static class SortingInfo {
        public final int mDisplayStringId;
        public final int mProtocolSortType;

        public SortingInfo(int protocolSortType, int displayStringId) {
            this.mProtocolSortType = protocolSortType;
            this.mDisplayStringId = displayStringId;
        }
    }

    static {
        sSortingInfoArray = new SortingInfo[]{new SortingInfo(4, R.string.reviews_sort_by_helpfulness), new SortingInfo(0, R.string.reviews_sort_by_date), new SortingInfo(1, R.string.reviews_sort_by_rating)};
    }

    public static int convertDataSortTypeToDisplayIndex(DfeReviews data) {
        int dataSortType = data.getSortType();
        for (int i = 0; i < sSortingInfoArray.length; i++) {
            if (dataSortType == sSortingInfoArray[i].mProtocolSortType) {
                return i;
            }
        }
        return -1;
    }

    public static int convertDisplayIndexToDataSortType(int index) {
        if (index < 0 || index >= sSortingInfoArray.length) {
            return -1;
        }
        return sSortingInfoArray[index].mProtocolSortType;
    }

    public static String getDisplayString(Context context, int dataSortType) {
        for (SortingInfo sortingInfo : sSortingInfoArray) {
            if (dataSortType == sortingInfo.mProtocolSortType) {
                return context.getString(sortingInfo.mDisplayStringId);
            }
        }
        return null;
    }

    public static CharSequence[] getAllDisplayStrings(Context context) {
        CharSequence[] result = new CharSequence[sSortingInfoArray.length];
        for (int i = 0; i < sSortingInfoArray.length; i++) {
            result[i] = context.getString(sSortingInfoArray[i].mDisplayStringId);
        }
        return result;
    }
}
