package com.google.android.libraries.bind.experimental.card;

import android.view.View;
import com.google.android.libraries.bind.R;
import com.google.android.libraries.bind.card.CardGroup;

public interface EditableAdapterView {
    public static final int DK_IS_EDITABLE;
    public static final int DK_IS_REMOVABLE;

    boolean startEditing(View view, CardGroup cardGroup, int i, Object obj);

    static {
        DK_IS_EDITABLE = R.id.EditableAdapterView_isEditable;
        DK_IS_REMOVABLE = R.id.EditableAdapterView_isRemovable;
    }
}
