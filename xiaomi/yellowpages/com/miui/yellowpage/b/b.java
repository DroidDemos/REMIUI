package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.SearchResultDataEntry;
import com.miui.yellowpage.base.model.SearchResultDataEntry.Type;
import com.miui.yellowpage.ui.du;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: NearbyYellowPageAdapter */
public class b extends a {
    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (SearchResultDataEntry) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (SearchResultDataEntry) obj, viewGroup);
    }

    public b(Context context) {
        super(context);
    }

    public int getItemViewType(int i) {
        return ((SearchResultDataEntry) getItem(i)).getType().ordinal();
    }

    public int getViewTypeCount() {
        return Type.values().length;
    }

    public void a(View view, int i, SearchResultDataEntry searchResultDataEntry) {
        ((du) view).a(searchResultDataEntry);
    }

    public View a(Context context, SearchResultDataEntry searchResultDataEntry, ViewGroup viewGroup) {
        switch (k.$SwitchMap$com$miui$yellowpage$base$model$SearchResultDataEntry$Type[searchResultDataEntry.getType().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return LayoutInflater.from(context).inflate(R.layout.search_result_service_item, null);
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return LayoutInflater.from(context).inflate(R.layout.search_result_yellowpage_item, null);
            default:
                return null;
        }
    }
}
