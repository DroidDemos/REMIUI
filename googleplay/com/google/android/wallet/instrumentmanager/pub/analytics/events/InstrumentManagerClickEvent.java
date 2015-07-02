package com.google.android.wallet.instrumentmanager.pub.analytics.events;

import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import java.util.List;

public final class InstrumentManagerClickEvent {
    public final List<InstrumentManagerUiElement> clickPath;

    public InstrumentManagerClickEvent(List<InstrumentManagerUiElement> clickPath) {
        this.clickPath = clickPath;
    }

    public String toString() {
        StringBuilder clickString = new StringBuilder();
        int length = this.clickPath.size();
        for (int i = 0; i < length; i++) {
            if (clickString.length() > 0) {
                clickString.append(" -> ");
            }
            clickString.append(((InstrumentManagerUiElement) this.clickPath.get(i)).elementId);
        }
        return clickString.toString();
    }
}
