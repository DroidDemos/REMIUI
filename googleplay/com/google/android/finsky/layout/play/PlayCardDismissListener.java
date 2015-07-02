package com.google.android.finsky.layout.play;

import com.google.android.finsky.api.model.Document;
import com.google.android.play.layout.PlayCardViewBase;

public interface PlayCardDismissListener {
    void onDismissDocument(Document document, PlayCardViewBase playCardViewBase);
}
