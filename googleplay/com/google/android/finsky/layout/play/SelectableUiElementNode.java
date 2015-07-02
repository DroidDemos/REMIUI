package com.google.android.finsky.layout.play;

import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;

public class SelectableUiElementNode extends GenericUiElementNode {
    private boolean mSelected;

    public SelectableUiElementNode(int elementType, byte[] elementServerCookie, PlayStoreUiElementInfo elementInfo, PlayStoreUiElementNode parent) {
        super(elementType, elementServerCookie, elementInfo, parent);
        this.mSelected = false;
        this.mSelected = false;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        if (this.mSelected) {
            super.childImpression(childNode);
            return;
        }
        PlayStoreUiElement newChild = childNode.getPlayStoreUiElement();
        if (newChild == null) {
            throw new IllegalArgumentException("childNode has null element");
        }
        FinskyEventLog.findOrAddChild(this, newChild);
    }

    public void setNodeSelected(boolean isSelected) {
        PlayStoreUiElementNode parentNode = getParentNode();
        if (isSelected) {
            FinskyEventLog.findOrAddChild(parentNode, getPlayStoreUiElement());
            if (getPlayStoreUiElement().child.length > 0) {
                parentNode.childImpression(this);
            }
        } else {
            FinskyEventLog.flushImpressionAtRoot(parentNode);
            FinskyEventLog.removeChild(parentNode.getPlayStoreUiElement(), getPlayStoreUiElement());
        }
        this.mSelected = isSelected;
    }
}
