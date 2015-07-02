package com.google.android.wallet.instrumentmanager.analytics;

import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import java.util.List;

public class SimpleUiNode implements UiNode {
    private final UiNode mParentNode;
    private final InstrumentManagerUiElement mUiElement;

    public SimpleUiNode(int elementId, UiNode parentNode) {
        this.mUiElement = new InstrumentManagerUiElement(elementId);
        this.mParentNode = parentNode;
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public UiNode getParentUiNode() {
        return this.mParentNode;
    }

    public List<UiNode> getChildren() {
        return null;
    }
}
