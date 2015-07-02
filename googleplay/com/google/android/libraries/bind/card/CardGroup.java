package com.google.android.libraries.bind.card;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewParent;
import com.google.android.libraries.bind.data.BindingViewGroup;
import com.google.android.libraries.bind.data.DataList;
import com.google.android.libraries.bind.experimental.card.EditableAdapterView;
import com.google.android.libraries.bind.logging.Logd;

public abstract class CardGroup {
    private static final Logd LOGD;
    private static final OnLongClickListener editableViewOnLongClickListener;
    private final DataList groupList;

    static {
        LOGD = Logd.get(CardGroup.class);
        editableViewOnLongClickListener = new OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (view instanceof BindingViewGroup) {
                    return ((BindingViewGroup) view).startEditingIfPossible();
                }
                return false;
            }
        };
    }

    public boolean startEditing(View view, int position) {
        LOGD.i("startEditing position: %d", Integer.valueOf(position));
        EditableAdapterView editableListView = null;
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof EditableAdapterView) {
                editableListView = (EditableAdapterView) parent;
                break;
            }
        }
        if (editableListView != null && this.groupList.getData(position).getAsBoolean(EditableAdapterView.DK_IS_EDITABLE, false)) {
            return editableListView.startEditing(view, this, position, this.groupList.getItemId(position));
        }
        return false;
    }
}
