package com.google.android.wallet.instrumentmanager.analytics;

import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import java.util.List;

public interface UiNode {
    List<UiNode> getChildren();

    UiNode getParentUiNode();

    InstrumentManagerUiElement getUiElement();
}
