package com.google.android.finsky.utils;

import com.google.android.finsky.api.model.Document;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardCustomizer<T extends PlayCardViewBase> {
    public void preBind(T card, Document doc) {
        card.setData(doc, doc.getBackend());
    }
}
