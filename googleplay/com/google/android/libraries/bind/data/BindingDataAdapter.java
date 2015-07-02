package com.google.android.libraries.bind.data;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.bind.R;
import com.google.android.libraries.bind.card.ViewGenerator;
import com.google.android.libraries.bind.util.Util;
import java.util.List;

public class BindingDataAdapter extends DataAdapter {
    private static SparseIntArray viewResIdMap;
    private static final int[] viewResIds;
    private final int a11yCardCountKey;
    private final int equalityFieldsKey;
    private DataList originalDataList;
    private final int viewGeneratorKey;
    private final int viewResourceIdKey;
    private final List<Integer> viewTypes;

    static {
        viewResIds = new int[]{R.layout.bind__card_edit_placeholder, R.layout.bind__card_list_padding};
    }

    public View getView(int position, View convertView, ViewGroup parent, Data data) {
        Integer viewResId = (Integer) data.get(this.viewResourceIdKey);
        if (viewResId == null) {
            ViewGenerator viewGenerator = (ViewGenerator) data.get(this.viewGeneratorKey);
            Util.checkPrecondition(viewGenerator != null, "Missing both view resource ID and view generator");
            return viewGenerator.makeView(convertView, parent, this.viewHeap);
        }
        View bindingLayout = this.viewHeap.get(viewResId.intValue(), convertView, makeLayoutParams());
        int[] equalityFields = (int[]) data.get(this.equalityFieldsKey);
        if (bindingLayout instanceof DataView) {
            ((DataView) bindingLayout).setDataRow(this.originalDataList.filterRow(this.originalDataList.getItemId(position), new LayoutResIdFilter(viewResId.intValue(), this.viewResourceIdKey), equalityFields));
        }
        return bindingLayout;
    }

    public int getItemViewType(int position) {
        return position < this.viewTypes.size() ? ((Integer) this.viewTypes.get(position)).intValue() : -1;
    }

    public int getViewTypeCount() {
        return viewResIdMap.size();
    }

    public int findRowWithCardId(Object cardId) {
        for (int i = 0; i < getCount(); i++) {
            Data data = getItem(i);
            if (data != null) {
                if (!data.containsKey(this.viewResourceIdKey)) {
                    for (Object generatorCardId : ((ViewGenerator) data.get(this.viewGeneratorKey)).getCardIds()) {
                        if (cardId.equals(generatorCardId)) {
                            return i;
                        }
                    }
                    continue;
                } else if (cardId.equals(this.dataList.getItemId(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Object getRowFirstCardId(int position) {
        Data data = getItem(position);
        if (data == null) {
            return null;
        }
        if (data.containsKey(this.viewResourceIdKey)) {
            return this.dataList.getItemId(position);
        }
        List<Object> generatorCardIds = ((ViewGenerator) data.get(this.viewGeneratorKey)).getCardIds();
        if (generatorCardIds.size() > 0) {
            return generatorCardIds.get(0);
        }
        return null;
    }

    public int getA11yRowCount(int position) {
        Data data = getItem(position);
        if (data == null) {
            return 1;
        }
        Integer count = (Integer) data.get(this.a11yCardCountKey);
        if (count != null) {
            return count.intValue();
        }
        return 1;
    }
}
